/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import BaseDeDatos.Bd;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author berli
 */
public class Prestamo {

    private int idPrestamo;
    private Date fechaInicial;
    private Date fechaTermino;
    private String estado; //A,E,T
    private double monto;
    private int idCobrador;
    private Cliente cliente;
    private int idUsuario;
    private Date ultima_mod;

    public Prestamo(int idPrestamo, Date fechaInicial, Date fechaTermino, String estado, double monto, int idCobrador, Cliente cliente, int idUsuario, Date ultima_mod) {
        this.idPrestamo = idPrestamo;
        this.fechaInicial = fechaInicial;
        this.fechaTermino = fechaTermino;
        this.estado = estado;
        this.monto = monto;
        this.idCobrador = idCobrador;
        this.cliente = cliente;
        this.idUsuario = idUsuario;
        this.ultima_mod = ultima_mod;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaInicialFormateada() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(fechaInicial);
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public String getMontoFormateado() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        return numberFormat.format(monto);
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdCobrador() {
        return idCobrador;
    }

    public void setIdCobrador(int idCobrador) {
        this.idCobrador = idCobrador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getUltima_mod() {
        return ultima_mod;
    }

    public String getDatosDeUltimaModificacion() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss ", new Locale("ES"));
        
        String nombre = Bd.buscarUsuario(idUsuario + "").get(0).getUsuario();
        String fecha = format.format(ultima_mod);
        
        format = null;

        return nombre + " " + fecha;
    }
    

    public void setUltima_mod(Date ultima_mod) {
        this.ultima_mod = ultima_mod;
    }

    @Override
    public String toString() {
        return idPrestamo + "";
    }
}
