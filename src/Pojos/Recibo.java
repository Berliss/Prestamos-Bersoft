/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author berli
 */
public class Recibo {

    private int idRecibo;
    private int idPrestamo;
    private int numeroCuota;
    private Date fecha;
    private double cuota;
    private double balance;
    private boolean adelanto;
    private double valorDeAtraso;

    public Recibo(int idRecibo, int idPrestamo, int numeroCuota, Date fecha, double cuota, double balance, boolean adelanto) {
        this.idRecibo = idRecibo;
        this.idPrestamo = idPrestamo;
        this.numeroCuota = numeroCuota;
        this.fecha = fecha;
        this.cuota = cuota;
        this.balance = balance;
        this.adelanto = adelanto;
    }

    //constructor con atrasos
    public Recibo(int idRecibo, int idPrestamo, int numeroCuota, Date fecha, double cuota, double balance, boolean adelanto, double atraso) {
        this.idRecibo = idRecibo;
        this.idPrestamo = idPrestamo;
        this.numeroCuota = numeroCuota;
        this.fecha = fecha;
        this.cuota = cuota;
        this.balance = balance;
        this.adelanto = adelanto;
        this.valorDeAtraso = atraso;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getFechaFormateada() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(fecha);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCuota() {
        return cuota;
    }

    public String getCuotaFormateada() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);
        return numberFormat.format(cuota);
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public boolean estaEnAdelanto() {
        return adelanto;
    }

    public void ponerEnAdelanto(boolean adelanto) {
        this.adelanto = adelanto;
    }

    public double getValorDeAtraso() {
        return valorDeAtraso;
    }

    public void setValorDeAtraso(double valorDeAtraso) {
        this.valorDeAtraso = valorDeAtraso;
    }

    @Override
    public String toString() {
        return numeroCuota + "";
    }

}
