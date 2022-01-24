/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diseno;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author berli
 */
public class DisenoLogin {

    List<JPanel> contenedores;
    List<JLabel> etiquetas;
    List<JTextField> listaTxt;
    JButton btn;
    List<JPanel> paneles;

    public DisenoLogin(List<JPanel> contenedores, List<JLabel> etiquetas, List<JTextField> listaTxt, JButton btn, List<JPanel> paneles) {
        this.contenedores = contenedores;
        this.etiquetas = etiquetas;
        this.listaTxt = listaTxt;
        this.btn = btn;
        this.paneles = paneles;

        ponerIconosAEtiquetas();
        estilosEtiquetas();
        estilosTxt();
        estilosContenedorDeTxt();
        estilosPaneles();
        estiloBoton();
    }

    private void ponerIconosAEtiquetas() {
        List<String> listaDeIconos = Arrays.asList("usuario","candado");

        for (int i = 0; i < listaDeIconos.size(); i++) {
            etiquetas.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/" + listaDeIconos.get(i) + ".png")));
        }
    }

    private void estilosEtiquetas() {
        etiquetas.forEach(e -> {
            e.setBackground(new java.awt.Color(255, 255, 255));
            e.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            e.setOpaque(true);
        });
    }

    private void estilosTxt() {
        listaTxt.forEach(txt -> {

            txt.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
            txt.setForeground(new java.awt.Color(153, 153, 153));
            txt.setText("Ingresa tu usuario...");
            txt.setBorder(null);

        });

    }

    private void estilosContenedorDeTxt() {
        contenedores.forEach(p -> {

            p.setBackground(new java.awt.Color(255, 255, 255));
            p.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 2));

        });

    }

    private void estilosPaneles() {
            paneles.get(1).setBackground(new java.awt.Color(255, 255, 255));
            paneles.get(1).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 98, 196), 2));
            
            paneles.get(0).setBackground(new java.awt.Color(153,204,255));
            paneles.get(0).setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 98, 196), 2));
            

    }
    
    private void estiloBoton(){
        btn.setOpaque(true);
        btn.setBackground(Color.WHITE);
    }

}
