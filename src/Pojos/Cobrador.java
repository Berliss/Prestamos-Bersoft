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
public class Cobrador {

    private int idCobrador;
    private String nombre;
    private String apellido;
    private String telefono;
    private Zona zona;
    private int idUsuario;

    public Cobrador(int idCobrador, String nombre, String apellido, String telefono, Zona zona, int idUsuario) {
        this.idCobrador = idCobrador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.zona = zona;
        this.idUsuario = idUsuario;
    }

    public int getIdCobrador() {
        return idCobrador;
    }

    public void setIdCobrador(int idCobrador) {
        this.idCobrador = idCobrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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
