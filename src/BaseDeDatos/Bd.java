/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Pojos.Abono;
import Pojos.Atraso;
import Pojos.Cliente;
import Pojos.Cobrador;
import Pojos.ConfiguracionPrestamos;
import Pojos.Entrega;
import Pojos.Prestamo;
import Pojos.Recibo;
import Pojos.Usuario;
import Pojos.Zona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author berli
 */
public class Bd {

    private static Connection conn;
    private static PreparedStatement Pst;
    private static ResultSet rs;
    private static Statement stment;
    private static CallableStatement callableStment;
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String Conexion = "jdbc:mysql://localhost:3306/pruebass?zeroDateTimeBehavior=convertToNull";
    private static String Usuario = "root";
    private static String Contrasena = "";

    private Bd() {
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }

        System.out.println("La Base de Datos a sido invocada");
    }

    public static void insertarConfiguracionPrestamos(ConfiguracionPrestamos c) {
        try {
            String sql = "insert into configuracion_prestamos "
                    + "(modalidad , nombre_compania , nombre_configuracion , numero_de_cuotas , porciento_beneficio)\n"
                    + "VALUES (?,?,?,?,?);";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getModalida());
            Pst.setString(2, c.getNombreCompania());
            Pst.setString(3, c.getNombreConfiguracion());
            Pst.setInt(4, c.getNumeroDeCuotas());
            Pst.setInt(5, c.getPorCientoBeneficio());

            Pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    public static List<ConfiguracionPrestamos> buscarConfiguracionPrestamos(String criterio) {
        List<ConfiguracionPrestamos> lista = new ArrayList();
        int iD = validarSiCriterioEsUnID(criterio);

        String sql = "Select *from configuracion_prestamos where nombre_configuracion like '" + criterio + "%' or nombre_compania like '" + criterio + "%' or configuracion_prestamos.id = " + iD + " Order by id";

        try {
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            stment = conn.createStatement();

            rs = stment.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt(1);
                String nombre_configuracion = rs.getString(2);
                String nombre_compania = rs.getString(3);
                int cuotas = rs.getInt(4);
                String modalidad = rs.getString(5);
                int beneficio = rs.getInt(6);

                lista.add(new ConfiguracionPrestamos(id, nombre_configuracion, nombre_compania, cuotas, modalidad, beneficio));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void editarConfiguracionPrestamos(ConfiguracionPrestamos c) {
        try {
            String sql = "update configuracion_prestamos set nombre_configuracion = ?,"
                    + "nombre_compania = ?,numero_de_cuotas = ?,modalidad = ?,porciento_beneficio= ? where id = ?";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getNombreConfiguracion());
            Pst.setString(2, c.getNombreCompania());
            Pst.setInt(3, c.getNumeroDeCuotas());
            Pst.setString(4, c.getModalida());
            Pst.setInt(5, c.getPorCientoBeneficio());
            Pst.setInt(6, c.getIdConf());

            Pst.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    public static boolean insertarUsuario(Usuario u) {
        try {
            String sql = "insert into usuarios (usuario,contrasena,id_configuracion_prestamo)"
                    + "values (?,?,?);";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, u.getUsuario());
            Pst.setString(2, u.getContrasena());
            Pst.setInt(3, u.getConfig().getIdConf());

            Pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Usuario> buscarUsuario(String criterio) {
        List<Usuario> lista = new ArrayList();
        int id = validarSiCriterioEsUnID(criterio);

        try {

            String sql = "SELECT usuarios.*,configuracion_prestamos.* from usuarios,configuracion_prestamos where (usuarios.id_configuracion_prestamo = configuracion_prestamos.id) and (usuarios.usuario like '" + criterio + "%' or usuarios.id =" + id + ")";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            stment = conn.createStatement();

            rs = stment.executeQuery(sql);

            while (rs.next()) {
                int idUsuario = rs.getInt(1);
                String nombreUsuario = rs.getString(2);
                String contrasena = rs.getString(3);
                int idConfiguracion = rs.getInt(5);
                String nombreConf = rs.getString(6);
                String nombreComp = rs.getString(7);
                int numeroCuotas = rs.getInt(8);
                String modalidad = rs.getString(9);
                int beneficio = rs.getInt(10);

                ConfiguracionPrestamos c = new ConfiguracionPrestamos(idConfiguracion, nombreConf, nombreComp, numeroCuotas, modalidad, beneficio);
                lista.add(new Usuario(idUsuario, nombreUsuario, contrasena, c));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void editarUsuario(Usuario u) {

        try {
            String sql = "update usuarios set usuario = ?,contrasena = ? , id_configuracion_prestamo = ? where id = ? ";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, u.getUsuario());
            Pst.setString(2, u.getContrasena());
            Pst.setInt(3, u.getConfig().getIdConf());
            Pst.setInt(4, u.getIdUsuario());

            Pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Usuario> validarUsuario(String usuario, String password) {
        List<Usuario> lista = new ArrayList();
        try {

            String sql = "{Call validar_usuario(?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setString(1, usuario);
            callableStment.setString(2, password);

            rs = callableStment.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt(1);
                String nombreUsuario = rs.getString(2);
                String contrasena = rs.getString(3);
                int idConfiguracion = rs.getInt(5);
                String nombreConf = rs.getString(6);
                String nombreComp = rs.getString(7);
                int numeroCuotas = rs.getInt(8);
                String modalidad = rs.getString(9);
                int beneficio = rs.getInt(10);

                ConfiguracionPrestamos c = new ConfiguracionPrestamos(idConfiguracion, nombreConf, nombreComp, numeroCuotas, modalidad, beneficio);
                lista.add(new Usuario(idUsuario, nombreUsuario, contrasena, c));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static boolean insertarZona(Zona z) {
        try {
            String sql = "insert into zona (nombre,direccion,numero_telefonico,id_usuario)"
                    + "values (?,?,?,?);";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, z.getNombre());
            Pst.setString(2, z.getDireccion());
            Pst.setString(3, z.getNumeroTelefonico());
            Pst.setInt(4, z.getIdUsuario());

            Pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Zona> buscarZona(String criterio) {
        List<Zona> lista = new ArrayList();
        int id = validarSiCriterioEsUnID(criterio);

        try {

            String sql = "SELECT zona.* from zona where zona.nombre like '" + criterio + "%' or id = " + id;

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            stment = conn.createStatement();

            rs = stment.executeQuery(sql);

            while (rs.next()) {
                int idZona = rs.getInt(1);
                String nombreZona = rs.getString(2);
                String direccion = rs.getString(3);
                String telefono = rs.getString(4);
                int idUsuario = rs.getInt(5);

                lista.add(new Zona(idZona, nombreZona, direccion, telefono, idUsuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static List<Zona> buscarZonaDeTuConfiguracion(String criterio, int idConfig) {
        List<Zona> lista = new ArrayList();
        int id = validarSiCriterioEsUnID(criterio);

        try {

            String sql = "SELECT zona.* from zona , usuarios where "
                    + "( zona.nombre like '" + criterio + "%' or zona.id =" + id + ") and "
                    + "(usuarios.id = zona.id_usuario) and"
                    + " usuarios.id_configuracion_prestamo =" + idConfig + "";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            stment = conn.createStatement();

            rs = stment.executeQuery(sql);

            int i = 0;

            while (rs.next()) {
                int idZona = rs.getInt(1);
                String nombreZona = rs.getString(2);
                String direccion = rs.getString(3);
                String telefono = rs.getString(4);
                int idUsuario = rs.getInt(5);
                System.out.println(i++);
                lista.add(new Zona(idZona, nombreZona, direccion, telefono, idUsuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void editarZona(Zona z) {

        try {
            String sql = "update zona set nombre = ?,direccion = ? , numero_telefonico = ?, id_usuario = ? where id = ? ";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, z.getNombre());
            Pst.setString(2, z.getDireccion());
            Pst.setString(3, z.getNumeroTelefonico());
            Pst.setInt(4, z.getIdUsuario());
            Pst.setInt(5, z.getIdZona());

            Pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static boolean insertarCobrador(Cobrador c) {
        try {
            String sql = "insert into cobrador (nombre,apellido,telefono,id_zona,id_usuario)"
                    + "values (?,?,?,?,?);";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getNombre());
            Pst.setString(2, c.getApellido());
            Pst.setString(3, c.getTelefono());
            Pst.setInt(4, c.getZona().getIdZona());
            Pst.setInt(5, c.getIdUsuario());

            Pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Cobrador> buscarCobrador(String criterio) {
        List<Cobrador> lista = new ArrayList();
        int id = validarSiCriterioEsUnID(criterio);

        try {
            String sql = "SELECT cobrador.*,zona.* from cobrador,zona where"
                    + " cobrador.id_zona = zona.id and"
                    + " (cobrador.nombre like '" + criterio + "%' or cobrador.id =" + id + ")";
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            stment = conn.createStatement();

            rs = stment.executeQuery(sql);

            while (rs.next()) {
                int idCobrador = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String telefono = rs.getString(4);
                int idUSuario = rs.getInt(5);

                int idZona = rs.getInt(7);
                String nombreZona = rs.getString(8);
                String direccion = rs.getString(9);
                String telefonoZona = rs.getString(10);
                int idUsuario = rs.getInt(11);

                Zona z = new Zona(idZona, nombreZona, direccion, telefonoZona, idUsuario);
                lista.add(new Cobrador(idCobrador, nombre, apellido, telefono, z, idUSuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static List<Cobrador> buscarCobradorDeTuConfiguracion(String criterio, int idConfig) {
        List<Cobrador> lista = new ArrayList();
        int idCobra = validarSiCriterioEsUnID(criterio);

        try {
            String sql = "{Call buscar_cobrador_de_tu_configuracion(?,?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setString(1, criterio);
            callableStment.setInt(2, idCobra);
            callableStment.setInt(3, idConfig);

            rs = callableStment.executeQuery();

            while (rs.next()) {
                int idCobrador = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String telefono = rs.getString(4);
                int idUSuario = rs.getInt(5);

                int idZona = rs.getInt(7);
                String nombreZona = rs.getString(8);
                String direccion = rs.getString(9);
                String telefonoZona = rs.getString(10);
                int idUsuario = rs.getInt(11);

                Zona z = new Zona(idZona, nombreZona, direccion, telefonoZona, idUsuario);
                lista.add(new Cobrador(idCobrador, nombre, apellido, telefono, z, idUSuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void editarCobrador(Cobrador c) {

        try {
            String sql = "update cobrador set nombre = ?,apellido = ? , telefono = ? , id_zona  = ?,id_usuario = ? where id = ? ";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getNombre());
            Pst.setString(2, c.getApellido());
            Pst.setString(3, c.getTelefono());
            Pst.setInt(4, c.getZona().getIdZona());
            Pst.setInt(5, c.getIdUsuario());
            Pst.setInt(6, c.getIdCobrador());

            Pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static boolean insertarCliente(Cliente c) {
        try {
            String sql = "insert into clientes (nombre,apellido,cedula,direccion,telefono1,telefono2,telefono3,ocupacion,recomendado,fecha_ingreso,id_zona,id_usuario)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?);";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getNombre());
            Pst.setString(2, c.getApellido());
            Pst.setString(3, c.getCedula());
            Pst.setString(4, c.getDireccion());
            Pst.setString(5, c.getTelefono1());
            Pst.setString(6, c.getTelefono2());
            Pst.setString(7, c.getTelefono3());
            Pst.setString(8, c.getOcupacion());
            Pst.setString(9, c.getRecomendado());
            Pst.setDate(10, c.getFechaIngreso());
            Pst.setInt(11, c.getZona().getIdZona());
            Pst.setInt(12, c.getIdUsuario());

            Pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Cliente> buscarCliente(String criterio) {
        List<Cliente> lista = new ArrayList();
        int id = 0;

        try {//este try para abilitar la busqueda por id.
            id = Integer.parseInt(criterio);
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            String sql = "{Call buscar_clientes(?,?)}";
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setString(1, criterio);
            callableStment.setInt(2, id);

            rs = callableStment.executeQuery();

            while (rs.next()) {
                int iD = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String cedula = rs.getString(4);
                String direccion = rs.getString(5);
                String tel1 = rs.getString(6);
                String tel2 = rs.getString(7);
                String tel3 = rs.getString(8);
                String ocupacion = rs.getString(9);
                String rec = rs.getString(10);
                Date fecha = rs.getDate(11);
                int idUsuario = rs.getInt(13);

                int idZona = rs.getInt(14);
                String nombreZona = rs.getString(15);
                String direccionZona = rs.getString(16);
                String telefonoZona = rs.getString(17);
                int idUsuarioZona = rs.getInt(18);

                Zona z = new Zona(idZona, nombreZona, direccionZona, telefonoZona, idUsuarioZona);

                lista.add(new Cliente(iD, nombre, apellido, cedula,
                        direccion, tel1, tel2, tel3, ocupacion,
                        rec, fecha, z, idUsuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static List<Cliente> buscarClienteDependiendoLaZona(String criterio, int id_zona) {
        List<Cliente> lista = new ArrayList();
        int id = 0;

        try {//este try para abilitar la busqueda por id.
            id = Integer.parseInt(criterio);
        } catch (NumberFormatException e) {
            id = 0;
        }

        try {
            String sql = "{Call buscar_clientes_dependiendo_zona(?,?,?)}";
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setString(1, criterio);
            callableStment.setInt(2, id);
            callableStment.setInt(3, id_zona);

            rs = callableStment.executeQuery();

            while (rs.next()) {
                int iD = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String cedula = rs.getString(4);
                String direccion = rs.getString(5);
                String tel1 = rs.getString(6);
                String tel2 = rs.getString(7);
                String tel3 = rs.getString(8);
                String ocupacion = rs.getString(9);
                String rec = rs.getString(10);
                Date fecha = rs.getDate(11);
                int idUsuario = rs.getInt(13);

                int idZona = rs.getInt(14);
                String nombreZona = rs.getString(15);
                String direccionZona = rs.getString(16);
                String telefonoZona = rs.getString(17);
                int idUsuarioZona = rs.getInt(18);

                Zona z = new Zona(idZona, nombreZona, direccionZona, telefonoZona, idUsuarioZona);

                lista.add(new Cliente(iD, nombre, apellido, cedula,
                        direccion, tel1, tel2, tel3, ocupacion,
                        rec, fecha, z, idUsuario));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void editarCliente(Cliente c) {

        try {
            String sql = "update clientes set nombre = ?,apellido = ? , cedula = ? , direccion = ? , "
                    + "telefono1 = ?,telefono2 = ?,telefono3 = ?, ocupacion = ?,recomendado = ?,"
                    + "fecha_ingreso = ?,id_zona = ?,id_usuario = ? where id = ? ";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            Pst = conn.prepareStatement(sql);

            Pst.setString(1, c.getNombre());
            Pst.setString(2, c.getApellido());
            Pst.setString(3, c.getCedula());
            Pst.setString(4, c.getDireccion());
            Pst.setString(5, c.getTelefono1());
            Pst.setString(6, c.getTelefono2());
            Pst.setString(7, c.getTelefono3());
            Pst.setString(8, c.getOcupacion());
            Pst.setString(9, c.getRecomendado());
            Pst.setDate(10, c.getFechaIngreso());
            Pst.setInt(11, c.getZona().getIdZona());
            Pst.setInt(12, c.getIdUsuario());
            Pst.setInt(13, c.getIdCliente());

            Pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Prestamo> buscarPrestamoPorCriterio(String criterio, int idCobrador, long fecha1, long fecha2, String estados) {
        List<Prestamo> lista = new ArrayList();
        int id_prestamo = 0;
        try {//este try para abilitar la busqueda por id.
            id_prestamo = Integer.parseInt(criterio);
        } catch (NumberFormatException e) {
            id_prestamo = 0;
        }
        try {

            String sql = "{Call buscar_prestamos(?,?,?,?,?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setString(1, criterio);
            callableStment.setInt(2, idCobrador);
            callableStment.setInt(3, id_prestamo);
            callableStment.setDate(4, new java.sql.Date(fecha1));
            callableStment.setDate(5, new java.sql.Date(fecha2));
            callableStment.setString(6, estados);

            rs = callableStment.executeQuery();

            while (rs.next()) {
                int idPrestamo = rs.getInt(1);
                Date fechaInicial = rs.getDate(2);
                Date fechaTermino = rs.getDate(3);
                String estado = rs.getString(4);
                double monto = rs.getDouble(5);
                int idCliente = rs.getInt(6);
                int id_Cobrador = rs.getInt(7);
                int idUsuario = rs.getInt(8);
                java.util.Date ultima_mod = rs.getTimestamp(9);

                String nombre = rs.getString(11);
                String apellido = rs.getString(12);
                String cedula = rs.getString(13);
                String direccion = rs.getString(14);
                String tel1 = rs.getString(15);
                String tel2 = rs.getString(16);
                String tel3 = rs.getString(17);
                String ocupacion = rs.getString(18);
                String recomendado = rs.getString(19);
                Date fechaIngresoCliente = rs.getDate(20);
                int idZona = rs.getInt(21);
                int idUsCliente = rs.getInt(22);

                String nombreZona = rs.getString(24);
                String direccionZona = rs.getString(25);
                String telZona = rs.getString(26);
                int idUzuarioZona = rs.getInt(27);

                Zona zona = new Zona(idZona, nombreZona, direccionZona, telZona, idUzuarioZona);

                Cliente cliente = new Cliente(idCliente, nombre, apellido, cedula, direccion, tel1, tel2, tel3,
                        ocupacion, recomendado, fechaIngresoCliente, zona, idUsCliente);

                Prestamo p = new Prestamo(idPrestamo, fechaInicial, fechaTermino, estado, monto, id_Cobrador, cliente, idUsuario, ultima_mod);

                lista.add(p);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public static void insertarPrestamos(Prestamo p, int numeroDeCuotas, int porciento, int tipoDePrestamo) {

        try {
            String sql = "{Call insertar_prestamos(?,?,?,?,?,?,?,?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setDate(1, new java.sql.Date(p.getFechaInicial().getTime()));
            callableStment.setString(2, p.getEstado());
            callableStment.setDouble(3, p.getMonto());
            callableStment.setInt(4, p.getCliente().getIdCliente());
            callableStment.setInt(5, p.getIdCobrador());
            callableStment.setInt(6, p.getIdUsuario());
            callableStment.setInt(7, numeroDeCuotas);
            callableStment.setInt(8, porciento);
            callableStment.setInt(9, tipoDePrestamo);

            callableStment.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
    }

    public static void eliminarPrestamos(int idPrestamo, int idDeUsuario) {
        try {
            String sql = "{Call eliminar_prestamos(?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idPrestamo);
            callableStment.setInt(2, idDeUsuario);

            callableStment.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Recibo> buscarRecibos(int idPrestamo) {
        List<Recibo> lista = new ArrayList();
        try {
            String sql = "{Call buscar_recibos(?)}";
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idPrestamo);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                int id_prestamo = rs.getInt(2);
                int numero_cuotas = rs.getInt(3);
                Date fecha = rs.getDate(4);
                double cuota = rs.getDouble(5);
                double balance = rs.getDouble(6);
                boolean adelanto = rs.getBoolean(7);

                lista.add(new Recibo(id, id_prestamo, numero_cuotas, fecha, cuota, balance, adelanto));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static List<Recibo> buscarRecibosConSusAtrasos(int idPrestamo) {
        List<Recibo> lista = new ArrayList();
        try {
            String sql = "{call buscar_recibos_y_atrasos(?)}";
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idPrestamo);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                int id_prestamo = rs.getInt(2);
                int numero_cuotas = rs.getInt(3);
                Date fecha = rs.getDate(4);
                double cuota = rs.getDouble(5);
                double balance = rs.getDouble(6);
                boolean adelanto = rs.getBoolean(7);
                double atraso = rs.getDouble(8);

                lista.add(new Recibo(id, id_prestamo, numero_cuotas, fecha, cuota, balance, adelanto, atraso));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

        return lista;
    }

    public static void ponerRecibosEnAdelantos(int idPrestamo, List<Integer> indicesDeRecibos) {

        try {
            String sql = "{Call quitar_todos_los_adelantos_de_recibos(?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idPrestamo);

            callableStment.executeUpdate();
            callableStment.close();

            String sql1 = "{Call poner_este_recibo_en_adenlanto(?)}";

            callableStment = conn.prepareCall(sql1);

            for (int indice : indicesDeRecibos) {
                callableStment.setInt(1, indice);
                callableStment.executeUpdate();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Entrega> buscarEntregas(int idCobrador, long fecha1) {
        List<Entrega> lista = new ArrayList();
        Entrega entregas;

        try {

            String sql = "{Call buscar_entregas(?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idCobrador);
            callableStment.setDate(2, new java.sql.Date(fecha1));

            rs = callableStment.executeQuery();

            while (rs.next()) {

                entregas = new Entrega();

                entregas.setCodigo(rs.getString("Codigo"));
                entregas.setTotalAPagar(rs.getDouble("total_a_pagar"));
                entregas.setCuota(rs.getDouble("cuota"));
                entregas.setBalance(rs.getDouble("balance"));
                entregas.setMontoPrestado(rs.getDouble("monto_prestado"));
                entregas.setNombreApellido(rs.getString("nombre"));

                lista.add(entregas);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public static void insertarAtrasos(List<Atraso> miLista) {

        try {
            String sql = "{Call insertar_atrasos(?,?,?,?,?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            for (Atraso a : miLista) {
                callableStment.setInt(1, a.getIdPrestamos());
                callableStment.setInt(2, a.getNum());
                callableStment.setDouble(3, a.getValor());
                callableStment.setDate(4, new java.sql.Date(a.getFecha().getTime()));
                callableStment.setInt(5, a.getIdUSuario());
                callableStment.setInt(6, a.getIdCobrador());

                callableStment.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Atraso> buscarAtrasos(String criterio, int idCobrador, long fecha1, long fecha2, String estado) {

        int id = validarSiCriterioEsUnID(criterio);

        String sql = "{Call buscar_atrasos(?,?,?,?,?,?)} ";

        Atraso a;
        List<Atraso> listaDeAtrasos = new ArrayList();

        try {

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idCobrador);
            callableStment.setInt(2, id);
            callableStment.setString(3, criterio);
            callableStment.setDate(4, new java.sql.Date(fecha1));
            callableStment.setDate(5, new java.sql.Date(fecha2));
            callableStment.setString(6, estado);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                a = new Atraso();

                a.setNombres(rs.getString(1));
                a.setIdCliente(rs.getInt(2));
                a.setIdAtraso(rs.getInt(3));
                a.setIdPrestamos(rs.getInt(4));
                a.setNum(rs.getInt(5));
                a.setFecha(rs.getDate(6));
                a.setValor(rs.getDouble(7));
                a.setEstado(rs.getString(8));
                a.setIdUSuario(rs.getInt(9));
                a.setUltima_mod(new Date(rs.getTimestamp(10).getTime()));
                a.setIdCobrador(rs.getInt(11));

                listaDeAtrasos.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return listaDeAtrasos;
    }

    public static List<Atraso> buscarAtrasosDeEstePrestamo(String criterio) {

        int id = validarSiCriterioEsUnID(criterio);

        String sql = "{Call buscar_atrasos_de_este_prestamo(?)} ";

        Atraso a;
        List<Atraso> listaDeAtrasos = new ArrayList();

        try {

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, id);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                a = new Atraso();

                a.setNombres(rs.getString(1));
                a.setIdCliente(rs.getInt(2));
                a.setIdAtraso(rs.getInt(3));
                a.setIdPrestamos(rs.getInt(4));
                a.setNum(rs.getInt(5));
                a.setFecha(rs.getDate(6));
                a.setValor(rs.getDouble(7));
                a.setEstado(rs.getString(8));
                a.setIdUSuario(rs.getInt(9));
                a.setUltima_mod(new Date(rs.getTimestamp(10).getTime()));
                a.setIdCobrador(rs.getInt(11));

                listaDeAtrasos.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return listaDeAtrasos;
    }

    public static List<Atraso> buscarAtrasosConAbonosYBalance(int idPrestamo, int numeroDeCuotas) {

        String sql = "{Call buscar_atrasos_con_sus_abonos_y_balances(?,?)} ";

        Atraso a;
        List<Atraso> listaDeAtrasos = new ArrayList();

        try {

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idPrestamo);
            callableStment.setInt(2, numeroDeCuotas);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                a = new Atraso();

                a.setNombres(rs.getString(1));

                a.setNum(rs.getInt(1));
                a.setValor(rs.getDouble(2));
                a.setAbonosAplicados(rs.getDouble(3));
                a.setBalance(rs.getDouble(4));

                listaDeAtrasos.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return listaDeAtrasos;
    }

    public static void eliminarAtrasos(List<Object> miLista) {

        try {

            String sql = "{Call eliminar_atrasos(?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            for (Object elemento : miLista) {

                Atraso a = (Atraso) elemento;

                callableStment.setInt(1, a.getIdPrestamos());
                callableStment.setInt(2, a.getNum());

                callableStment.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static void insertarAbonos(List<Abono> miLista) {

        try {
            String sql = "{Call insertar_abonos(?,?,?,?,?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            for (Abono a : miLista) {
                callableStment.setInt(1, a.getIdPrestamos());
                callableStment.setInt(2, a.getNum());
                callableStment.setDouble(3, a.getValor());
                callableStment.setDate(4, new java.sql.Date(a.getFecha().getTime()));
                callableStment.setInt(5, a.getIdUSuario());
                callableStment.setInt(6, a.getIdCobrador());

                callableStment.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static List<Abono> buscarAbonos(String criterio, int idCobrador, long fecha1, long fecha2, String estado) {

        int id = validarSiCriterioEsUnID(criterio);

        String sql = "{Call buscar_abonos(?,?,?,?,?,?)} ";

        Abono a;
        List<Abono> listaDeAbonos = new ArrayList();

        try {

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idCobrador);
            callableStment.setInt(2, id);
            callableStment.setString(3, criterio);
            callableStment.setDate(4, new java.sql.Date(fecha1));
            callableStment.setDate(5, new java.sql.Date(fecha2));
            callableStment.setString(6, estado);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                a = new Abono();

                a.setNombres(rs.getString(1));
                a.setIdCliente(rs.getInt(2));
                a.setIdAbono(rs.getInt(3));
                a.setIdPrestamos(rs.getInt(4));
                a.setNum(rs.getInt(5));
                a.setFecha(rs.getDate(6));
                a.setValor(rs.getDouble(7));
                a.setEstado(rs.getString(8));
                a.setIdUSuario(rs.getInt(9));
                a.setUltima_mod(new Date(rs.getTimestamp(10).getTime()));
                a.setIdCobrador(rs.getInt(11));

                listaDeAbonos.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return listaDeAbonos;
    }

    public static List<Abono> buscarAbonosAplicadosAlAtraso(int idPrestamos, int idCobrador, int noCuota) {

        String sql = "{Call buscar_abonos_aplicados_al_atraso(?,?,?)} ";

        Abono a;
        List<Abono> listaDeAbonos = new ArrayList();

        try {

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            callableStment.setInt(1, idCobrador);
            callableStment.setInt(2, idPrestamos);
            callableStment.setInt(3, noCuota);

            rs = callableStment.executeQuery();

            while (rs.next()) {

                a = new Abono();

                a.setNombres(rs.getString(1));
                a.setIdCliente(rs.getInt(2));
                a.setIdAbono(rs.getInt(3));
                a.setIdPrestamos(rs.getInt(4));
                a.setNum(rs.getInt(5));
                a.setFecha(rs.getDate(6));
                a.setValor(rs.getDouble(7));
                a.setEstado(rs.getString(8));
                a.setIdUSuario(rs.getInt(9));
                a.setUltima_mod(new Date(rs.getTimestamp(10).getTime()));
                a.setIdCobrador(rs.getInt(11));

                listaDeAbonos.add(a);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return listaDeAbonos;
    }

    public static void eliminarAbonos(List<Object> miLista) {

        try {

            String sql = "{Call eliminar_abonos(?,?)}";

            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
            callableStment = conn.prepareCall(sql);

            for (Object elemento : miLista) {

                Abono a = (Abono) elemento;

                callableStment.setInt(1, a.getIdPrestamos());
                callableStment.setInt(2, a.getNum());

                callableStment.executeUpdate();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        } finally {
            cerrarRecursos();
        }

    }

    public static Connection getConexion() {
        try {
            conn = DriverManager.getConnection(Conexion, Usuario, Contrasena);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void cerrarRecursos() {

        List<AutoCloseable> listaDeRecursos = Arrays.asList(conn, Pst, rs, stment, callableStment);

        listaDeRecursos.forEach(recurso -> {
            if (recurso != null) {
                try {
                    recurso.close();
                    recurso = null;
                    System.out.println("Cerre Recursos");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private static int validarSiCriterioEsUnID(String criterio) {

        try {//este try para abilitar la busqueda por id.
            return Integer.parseInt(criterio);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void respaldarBaseDeDatos() {

        try {

            String fecha = (new SimpleDateFormat("dd-MM-yyyy(hhmmss)")).format(new java.util.Date());
            String nombreDeMiBackUp = "backup" + fecha + ".bat";
            String ruta = obtenerRutaDeRespaldo().get(0).toString() ;
            System.out.println(ruta);
            

            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("cmd /c mysqldump -v --opt --events --routines --triggers --default-character-set=utf8 -u " + Usuario + " --password=" + Contrasena + " pruebass > "+ruta+"backup" + nombreDeMiBackUp);
            p.waitFor();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null, ioe.getMessage());
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            JOptionPane.showMessageDialog(null, ie.getMessage());
        }
    }
    
        private static ArrayList obtenerRutaDeRespaldo() {

        ArrayList<String> listaParametros = new ArrayList();

        try {
            System.out.println(new File("").getAbsoluteFile());
            FileReader fileReader = new FileReader(new File("").getAbsoluteFile() + File.separator + "Conf.txt");
            BufferedReader bufferReader = new BufferedReader(fileReader);

            String lineaActual;

            while ((lineaActual = bufferReader.readLine()) != null) {
                listaParametros.add(lineaActual);
            }

            fileReader.close();
            bufferReader.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        return listaParametros;
    }

}
