/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import BaseDeDatos.Bd;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author berli
 */
public class Abono {

    private String nombres;
    private int idCliente;
    private int idAbono;
    private int idPrestamos;
    private int num;
    private Date fecha;
    private double valor;
    private String estado;
    private int idUSuario;
    private int idCobrador;
    private Date ultima_mod;

    public Abono() {
        this.nombres = "vacio";
        this.idCliente = 0;
        this.idAbono = 0;
        this.idPrestamos = 0;
        this.num = 0;
        this.fecha = null;
        this.valor = 0;
        this.estado = "vacio";
        this.idUSuario = 0;
        this.idCobrador = 0;
        this.ultima_mod = null;
    }

    public Abono(String nombres, int idCliente, int idAbono, int idPrestamos, int num, Date fecha, double valor, String estado, int idUSuario, int idCobrador, Date ultima_mod) {
        this.nombres = nombres;
        this.idCliente = idCliente;
        this.idAbono = idAbono;
        this.idPrestamos = idPrestamos;
        this.num = num;
        this.fecha = fecha;
        this.valor = valor;
        this.estado = estado;
        this.idUSuario = idUSuario;
        this.idCobrador = idCobrador;
        this.ultima_mod = ultima_mod;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(int idAbono) {
        this.idAbono = idAbono;
    }

    public int getIdPrestamos() {
        return idPrestamos;
    }

    public void setIdPrestamos(int idPrestamos) {
        this.idPrestamos = idPrestamos;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUSuario() {
        return idUSuario;
    }

    public void setIdUSuario(int idUSuario) {
        this.idUSuario = idUSuario;
    }

    public int getIdCobrador() {
        return idCobrador;
    }

    public void setIdCobrador(int idCobrador) {
        this.idCobrador = idCobrador;
    }

    public Date getUltima_mod() {
        return ultima_mod;
    }

    public void setUltima_mod(Date ultima_mod) {
        this.ultima_mod = ultima_mod;
    }
    
        public String getDatosDeUltimaModificacion() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss ", new Locale("ES"));
        
        String nombre = idUSuario+"";
        String fecha = format.format(ultima_mod);
        
        format = null;

        return nombre + " " + fecha;
    }
    
    public String verInformacionPrincipal(){
        //"Fecha: "+fecha + " Numero: " + num + " Valor: " +valor+"\n";
        
        return String.format("%-20s%-20s%-20s%n",fecha+"",num+"",valor+"");
    }

    @Override
    public String toString() {
        return  idPrestamos + "";
    }

    
}