/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.util.Date;

/**
 *
 * @author berli
 */
public class Atraso {

    private String nombres;
    private int idCliente;
    private int idAtraso;
    private int idPrestamos;
    private int num;
    private Date fecha;
    private double valor;
    private String estado;
    private int idUSuario;
    private int idCobrador;
    private Date ultima_mod;
    private double abonosAplicados;
    private double balance;

    public Atraso() {
        this.nombres = "vacio";
        this.idCliente = 0;
        this.idAtraso = 0;
        this.idPrestamos = 0;
        this.num = 0;
        this.fecha = null;
        this.valor = 0;
        this.estado = "vacio";
        this.idUSuario = 0;
        this.idCobrador = 0;
        this.ultima_mod = null;
        this.abonosAplicados = 0;
        this.balance = 0;
    }

    public Atraso(String nombres, int idCliente, int idAtraso, int idPrestamos, int num, Date fecha, double valor, String estado, int idUSuario, int idCobrador, Date ultima_mod, double abonosAplicados, double balance) {
        this.nombres = nombres;
        this.idCliente = idCliente;
        this.idAtraso = idAtraso;
        this.idPrestamos = idPrestamos;
        this.num = num;
        this.fecha = fecha;
        this.valor = valor;
        this.estado = estado;
        this.idUSuario = idUSuario;
        this.idCobrador = idCobrador;
        this.ultima_mod = ultima_mod;
        this.abonosAplicados = abonosAplicados;
        this.balance = balance;
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

    public int getIdAtraso() {
        return idAtraso;
    }

    public void setIdAtraso(int idAtraso) {
        this.idAtraso = idAtraso;
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

    public double getAbonosAplicados() {
        return abonosAplicados;
    }

    public void setAbonosAplicados(double abonosAplicados) {
        this.abonosAplicados = abonosAplicados;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return idPrestamos + "";
    }
    

}
