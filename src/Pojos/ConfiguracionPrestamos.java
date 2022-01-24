/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

/**
 *
 * @author berli
 */
public class ConfiguracionPrestamos {

    private int idConf;
    private String nombreConfiguracion;
    private String nombreCompania;
    private int numeroDeCuotas;
    private String modalida; //Diario,Semanal,Quincenal.
    private int porCientoBeneficio;

    public ConfiguracionPrestamos(int idConfiguracionPrestamos, String nombreConfiguracion, String nombreCompania, int numeroDeCuotas, String modalida, int porCientoBeneficio) {
        this.idConf = idConfiguracionPrestamos;
        this.nombreConfiguracion = nombreConfiguracion;
        this.nombreCompania = nombreCompania;
        this.numeroDeCuotas = numeroDeCuotas;
        this.modalida = modalida;
        this.porCientoBeneficio = porCientoBeneficio;
    }

    public int getIdConf() {
        return idConf;
    }

    public void setIdConf(int idConfiguracionPrestamos) {
        this.idConf = idConfiguracionPrestamos;
    }

    public String getNombreConfiguracion() {
        return nombreConfiguracion;
    }

    public void setNombreConfiguracion(String nombreConfiguracion) {
        this.nombreConfiguracion = nombreConfiguracion;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public int getNumeroDeCuotas() {
        return numeroDeCuotas;
    }

    public void setNumeroDeCuotas(int numeroDeCuotas) {
        this.numeroDeCuotas = numeroDeCuotas;
    }

    public String getModalida() {
        return modalida;
    }

    public void setModalida(String modalida) {
        this.modalida = modalida;
    }

    public int getPorCientoBeneficio() {
        return porCientoBeneficio;
    }

    public void setPorCientoBeneficio(int porCientoBeneficio) {
        this.porCientoBeneficio = porCientoBeneficio;
    }
    
    @Override
    public String toString(){
        return this.nombreConfiguracion;
    }

}
