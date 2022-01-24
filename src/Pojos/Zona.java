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
public class Zona {

    private int idZona;
    private String nombre;
    private String direccion;
    private String numeroTelefonico;
    private int idUsuario;

    public Zona(int idZona, String nombre, String direccion, String numeroTelefonico, int idUsuario) {
        this.idZona = idZona;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numeroTelefonico = numeroTelefonico;
        this.idUsuario = idUsuario;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
