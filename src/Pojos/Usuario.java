/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import BaseDeDatos.Bd;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author berli
 */
public class Usuario {

    private int idUsuario;
    private String usuario;
    private String contrasena;
    private ConfiguracionPrestamos config;

    public Usuario(int idUsuario, String usuario, String contrasena, ConfiguracionPrestamos config) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.config = config;
    }

    public ConfiguracionPrestamos getConfig() {
        return config;
    }

    public void setConfig(ConfiguracionPrestamos config) {
        this.config = config;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

/*
    public String getDatosDeUltimaModificacion(){
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss ",new Locale("ES"));
        return format.format(ultimaModificacion);
    }
*/

    @Override
    public String toString() {
        return usuario;
    }
}
