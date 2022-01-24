/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author berli
 */
public class expresionRegula {
    public static void main(String[] args) {
       String valor = "oqwoi";
       boolean esUnNumero;
       boolean terna;
       String expresion = "^(?!\\\\s*$)[0-9\\\\s]*$";
       
       // /^-?\d+$/
       
       // ^[0-9]*$
       
      int id = (valor.matches(expresion)?Integer.parseInt(valor):0);
       
       esUnNumero = valor.matches(expresion);
        System.out.println(valor.trim().isEmpty());
       
        System.out.println(esUnNumero);
        System.out.println(id);
       
       
    }
}
