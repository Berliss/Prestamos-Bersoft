/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author berli
 */
public class metodosGenerales {

    /*  public static void formatoNumericoTextField(KeyEvent evt, JTextField txtCampoTexto, int digitosMaximo, int posicionGuion, int posicionGuion2) {
        char digitoIngresado = evt.getKeyChar();

        if (txtCampoTexto.getText().length() != digitosMaximo) {

            if (digitoIngresado < '0' || digitoIngresado > '9') {
                evt.consume();
            }
            if (txtCampoTexto.getText().length() == posicionGuion) {
                txtCampoTexto.setText(txtCampoTexto.getText() + "-");
            }

            if (txtCampoTexto.getText().length() == posicionGuion2) {
                txtCampoTexto.setText(txtCampoTexto.getText() + "-");
            }

        } else {
            evt.consume();
        }

    }
     */
    public static void formatoNumericoTextField(KeyEvent evt, JTextField txtCampoTexto, int digitosMaximo) {
        char digitoIngresado = evt.getKeyChar();

        if (txtCampoTexto.getText().length() != digitosMaximo) {

            if ((digitoIngresado < '0' || digitoIngresado > '9')) {
                evt.consume();
            }
        } else {
            evt.consume();
        }

    }

    public static void limitarCaracteres(KeyEvent evt, JTextField txtCampoTexto, int digitosMaximo) {
        if (txtCampoTexto.getText().length() >= digitosMaximo) {
            evt.consume();
        }
    }

    public static void impedirCiertosCaracteres(ArrayList<JTextField> listaCampos) {

        for (JTextField listaCampo : listaCampos) {
            listaCampo.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    char charecter = evt.getKeyChar();
                    if (charecter == '|' || charecter == '*') {
                        evt.consume();
                    }
                }
            });
        }
    }

    public static java.util.Date FechaDelDia() {

        Calendar Calendario = Calendar.getInstance();
        java.util.Date date = Calendario.getTime();

        return date;
    }

    public static void fechaActualACampoDeTexto(JTextField campoTexto) {
        Date fechaDelDia = FechaDelDia();
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        String stringFechaDelDia = formatoDeFecha.format(fechaDelDia);
        campoTexto.setText(stringFechaDelDia);
    }

    public static String DateAString(Date date) {

        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        String stringFechaDelDia = formatoDeFecha.format(date);
        return stringFechaDelDia;
    }

    public static void camposDeTextoMayuscula(ArrayList<JTextField> listaCampos) {
        for (JTextField listaCampo : listaCampos) {
            listaCampo.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    char charecter = evt.getKeyChar();
                    if (Character.isLowerCase(charecter)) {
                        evt.setKeyChar(Character.toUpperCase(charecter));
                    }
                }
            });
        }
    }

    public static long DiasEntreFecha(String fechaIni, String fechaFin) {
        java.util.Date fechaInicial = null;
        java.util.Date fechaFinal = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd"); //esta parte aqui es obligatoria para parsear nuestro string como si fuera una fecha
            fechaInicial = formato.parse(fechaIni);//aqui creamos el segundo date el que usaremos con la nueva fecha.

            SimpleDateFormat formato2 = new SimpleDateFormat("yyyy/MM/dd"); //esta parte aqui es obligatoria para parsear nuestro string como si fuera una fecha
            fechaFinal = formato2.parse(fechaFin);//aqui creamos el segundo date el que usaremos con la nueva fecha.

            //dias;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return ChronoUnit.DAYS.between(fechaInicial.toInstant(), fechaFinal.toInstant());
    }

    public static void FormatoTablas(JTable Tabla, DefaultTableModel modeloTabla, String[] nombreColumnas, int[] tamanoColumna) {
        //En este metodo le pongo los nombre a las columnas y el tamano preferido

        for (int i = 0; i < nombreColumnas.length; i++) {
            modeloTabla.addColumn(nombreColumnas[i]);
        }

        for (int i = 0; i < tamanoColumna.length; i++) {
            Tabla.getColumnModel().getColumn(i).setPreferredWidth(tamanoColumna[i]);
        }

    }

    public static boolean hayAlmenosUnCampoLleno(List<JTextField> lista) {

        for (JTextField txtActual : lista) {
            if (!txtActual.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;

    }

    public static boolean hayUnCampoVacio(List<JTextField> lista) {

        for (JTextField txtActual : lista) {
            if (txtActual.getText().trim().isEmpty()) {
                txtActual.grabFocus();
                txtActual.setBorder(BorderFactory.createEtchedBorder(Color.RED, null));
                return true;
            }
        }
        return false;
    }

    public static void focusPerdidoTxt(List<JTextField> lista) {
        for (JTextField txtActual : lista) {
            txtActual.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    txtActual.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                }
            });
        }
    }

    public static void borrarTxt(List<JTextField> lista) {
        lista.forEach(txt -> {
            txt.setText("");
        });
    }

    public static void LimpiarTabla(DefaultTableModel modelo) {

        int numFilas = modelo.getRowCount();

        if (numFilas > 0) {
            for (int i = numFilas - 1; i > 0; i--) {
                modelo.removeRow(i);
            }
        }
    }
}
