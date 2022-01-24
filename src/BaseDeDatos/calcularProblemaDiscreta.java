/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

/**
 *
 * @author berli
 */
public class calcularProblemaDiscreta {
    
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            if(i%3 == 2 && i%5 == 3 && i%7 ==2){
                System.out.println(i);
            }
        }
    }
    
}
