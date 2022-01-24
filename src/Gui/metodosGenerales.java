/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author berli
 */
public class metodosGenerales {

    private static int xCord = 0;
    private static int yCord = 0;


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

    public static void limitarCaracteresParaConjunto(List<JTextField> lista, int digitosMaximo) {

        for (JTextField txt : lista) {
            txt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (txt.getText().length() >= digitosMaximo) {
                        e.consume();
                    }
                }
            });
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

    public static void camposDeTextoMayuscula(List<JTextField> listaCampos) {
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
    
     public static void unCampoDeTextoMayuscula(JTextField txt) {
 
            txt.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent evt) {
                    char charecter = evt.getKeyChar();
                    if (Character.isLowerCase(charecter)) {
                        evt.setKeyChar(Character.toUpperCase(charecter));
                    }
                }
            });
        
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

    public static void FormatoTablas(JTable Tabla, DefaultTableModel modeloTabla, List<String> nombreColumnas, List<Integer> tamanoColumna) {
        //En este metodo le pongo los nombre a las columnas y el tamano preferido

        for (int i = 0; i < nombreColumnas.size(); i++) {
            modeloTabla.addColumn(nombreColumnas.get(i));
        }

        for (int i = 0; i < tamanoColumna.size(); i++) {
            Tabla.getColumnModel().getColumn(i).setPreferredWidth(tamanoColumna.get(i));
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

    public static boolean hayUnCampoVacioEnLogin(List<JTextField> lista) {

        for (JTextField txtActual : lista) {
            if (txtActual.getText().trim().isEmpty()) {
                txtActual.grabFocus();
                ((JPanel) txtActual.getParent()).setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED, 2));
                return true;
            }
        }
        return false;
    }

    public static boolean hayUnCampoVacioConExcepciones(List<JTextField> listaTxt, List<Integer> txtExcepciones) {
        //Nota siempre poner los txt en el mismo orden que se ven el el formulario  IMPORTANTISIMO
        int tamanoDeMiLista = listaTxt.size();
        JTextField txtActual;
        for (int i = 0; i < tamanoDeMiLista; i++) {
            txtActual = listaTxt.get(i);
            if (!txtExcepciones.contains(i)) {
                if (txtActual.getText().trim().isEmpty()) {
                    txtActual.grabFocus();
                    txtActual.setBorder(BorderFactory.createEtchedBorder(Color.RED, null));
                    return true;
                }
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

    public static void focusPerdidoTxtEnLogin(List<JTextField> lista) {
        for (JTextField txtActual : lista) {
            txtActual.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    ((JPanel) txtActual.getParent()).setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 2));
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
            for (int i = numFilas - 1; i > -1; i--) {
                modelo.removeRow(i);
            }
        }
    }

    public static int[] mousePressedVentana(MouseEvent evt) {
        xCord = evt.getX();
        yCord = evt.getY();
        return new int[]{xCord, yCord};
    }

    public static void mouseDraggedVentana(MouseEvent evt, Window ventana) {
        ventana.setLocation(evt.getXOnScreen() - (xCord), evt.getYOnScreen() - (yCord));
    }

    public static void mouseDraggedVentana(MouseEvent evt, Window ventana, int x, int y) {
        ventana.setLocation(evt.getXOnScreen() - (xCord + x), evt.getYOnScreen() - (yCord + y));
    }

    public static String quitarFormatoDeMoneda(String stringConFormato) {

        String nuevaCadenaSinFormato = "";

        try {

            char[] cadenaDescompuesta = stringConFormato.toCharArray();

            for (int i = 0; i < cadenaDescompuesta.length; i++) {
                if (cadenaDescompuesta[i] == '$' || cadenaDescompuesta[i] == ',') {
                    continue;
                } else {
                    nuevaCadenaSinFormato += cadenaDescompuesta[i];
                }
            }

        } catch (NullPointerException e) {
            return "0.00";
        }

        return nuevaCadenaSinFormato;
    }

    public static Date sumarFechas(int dias, int semanas) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        c.add(Calendar.DAY_OF_YEAR, dias);
        c.add(Calendar.WEEK_OF_YEAR, semanas);

        return c.getTime();
    }

    public static Date sumarFechas(Date fechaInicial, int dias, int semanas) {

        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicial);

        c.add(Calendar.DAY_OF_YEAR, dias);
        c.add(Calendar.WEEK_OF_YEAR, semanas);

        return c.getTime();
    }

    public static Date fechaSinHora(Date fecha) {
        // solo puse los valores de fecha 2 en 0 pq es lo que necesito ahora lo normal es formatear ambas
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);

        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();

    }

    public static Date sumarFechaFormaModerna(int anos, int mes, int semana, int dia) {
        //no lo estoy utilizando pq debo de estudiarlo a fondo para saber como funciona
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        localDateTime = localDateTime.plusYears(anos).plusMonths(mes).plusWeeks(semana).plusDays(dia);

        Date nuevaFecha = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return nuevaFecha;
    }

    public static void personalizarToolTips(Color colorDeFondo, Color colorDeLetras) {
        UIManager.put("ToolTip.background", colorDeFondo);
        UIManager.put("ToolTip.foreground", colorDeLetras);
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 14));
        UIManager.put("ToolTip.border", new LineBorder(Color.RED));
    }

    public static void anadirCheckBoxATabla(int column, JTable table) {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }

    public static void removerPestanas(JTabbedPane panel, List<Integer> listaDePestanas) {

        Collections.reverse(listaDePestanas);

        listaDePestanas.forEach(indiceDePestanaARemover -> {
            panel.remove(indiceDePestanaARemover);
        });

    }

}
