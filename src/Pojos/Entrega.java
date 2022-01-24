/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author berli
 */
public class Entrega {

    private String codigo;
    private double totalAPagar;
    private double cuota;
    private double balance;
    private double montoPrestado;
    private String nombreApellido;
    private NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA);

    public Entrega(String codigo, double totalAPagar, double cuota, double balance, double montoPrestado, String nombreApellido) {
        this.codigo = codigo;
        this.totalAPagar = totalAPagar;
        this.cuota = cuota;
        this.balance = balance;
        this.montoPrestado = montoPrestado;
        this.nombreApellido = nombreApellido;
    }

    public Entrega() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }
    
    public String getTotalAPagarFormateado(){
        return numberFormat.format(totalAPagar);
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public double getCuota() {
        return cuota;
    }

    public String getCuotaFormateada() {
        return numberFormat.format(cuota);
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public double getBalance() {
        return balance;
    }
    
    public String getBalanceFormateado(){
        return numberFormat.format(balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMontoPrestado() {
        return montoPrestado;
    }
    
    public String getMontoPrestadoFormateado(){
        return numberFormat.format(montoPrestado);
    }

    public void setMontoPrestado(double montoPrestado) {
        this.montoPrestado = montoPrestado;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

}
