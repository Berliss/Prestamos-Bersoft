/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diseno;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author berli
 */
public class DisenoPrincipal {

    List<JButton> botones;
    JPanel panelLateral;
    JPanel panelCentral;
    JPanel panelContenedor;
    List<JMenuItem> opcionesPopups;
    JPopupMenu popup;

    public DisenoPrincipal(List<JButton> botones, JPanel panelLateral, JPanel panelCentral, JPanel panelContenedor, List<JMenuItem> opcionesPopups, JPopupMenu popup) {
        this.botones = botones;
        this.panelLateral = panelLateral;
        this.panelCentral = panelCentral;
        this.panelContenedor = panelContenedor;
        this.opcionesPopups = opcionesPopups;
        this.popup = popup;

        estiloVentana();
        estiloPopups();
        disenoBotonesLaterales();
        eventoParaBotones();
        colorDeMisPaneles();
        ponerIconosABotones();

    }

    private void disenoBotonesLaterales() {
        botones.forEach(b -> {
            b.setFont(new java.awt.Font("Segoe UI", 0, 16));
            b.setForeground(Color.WHITE);
            b.setContentAreaFilled(false);
            b.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            b.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        });
    }

    private void eventoParaBotones() {

        botones.forEach(b -> {
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.grabFocus();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    panelCentral.grabFocus();
                }
            });

            b.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    b.setOpaque(true);
                    b.setBackground(new Color(0, 28, 183));
                    //7, 61, 141 azul oscuro
                    //b.setForeground(Color.black);
                    b.setFocusPainted(false);
                    
                }

                @Override
                public void focusLost(FocusEvent e) {
                    b.setOpaque(false);
                    //b.setForeground(Color.white);
                    b.setBackground(new Color(153, 153, 255));
                    System.out.println("Voy pasando");
                }
            });
        });
    }

    private void colorDeMisPaneles() {
        panelLateral.setBackground(new Color(10, 80, 211));
        panelCentral.setBackground(Color.WHITE);

        //new Color(7, 71, 166) azul oscuro
        //new Color(0, 30, 214)
        //new Color(51, 153, 255)
    }

    private void ponerIconosABotones() {

        List<String> listaDeIconos = Arrays.asList("usuario1", "zona", "cobrador",
                "clientes", "prestamos", "informacion",
                "deudas", "recibos", "configuracion",
                "salida");

        for (int i = 0; i < listaDeIconos.size(); i++) {
            botones.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/" + listaDeIconos.get(i) + ".png")));
        }

    }

    private void estiloVentana() {
        panelContenedor.setBorder(new javax.swing.border.LineBorder(Color.black, 1, true));
        // new java.awt.Color(51, 51, 255)
    }

    private void estiloPopups() {

        popup.setBackground(new Color(51, 153, 255));
        popup.setBorder(new javax.swing.border.LineBorder(new Color(0, 24, 171),3,true));

        opcionesPopups.forEach(o -> {
            o.setOpaque(true);
            o.setBackground(Color.WHITE);
            o.setFont(new java.awt.Font("Century Gothic", 0, 16));
            o.setForeground(Color.BLACK);
        });

    }

}
