/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import BaseDeDatos.Bd;
import java.util.HashMap;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Berlis
 */
public class Reportes {

    private static JasperReport reporte;
    private static JasperPrint imprimir;
    private static JasperViewer viewer;
    private static JDialog contenedorReporte;

    public static void reporteEntregaA4(int idCobrador, long fecha, JDialog contenedorPadre) {
        try {
            String ruta = ".\\MisReportes\\miPrimerReporte.jrxml";
            //String ruta = "D:\\Escritorio nuevo\\misReportes\\miPrimerReporte.jrxml";
            HashMap parametros = new HashMap();

            parametros.put("id", idCobrador);
            parametros.put("fecha", new java.sql.Date(fecha));

            reporte = JasperCompileManager.compileReport(ruta);
            imprimir = JasperFillManager.fillReport(reporte, parametros, Bd.getConexion());

            if (!imprimir.getPages().isEmpty()) {
                viewer = new JasperViewer(imprimir, false);
                anadirReporteAContenedor(viewer, contenedorPadre);
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos.");
            }

            Bd.cerrarRecursos();

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

    public static void reporteListaDeCobroA4(int idCobrador, long fecha, JDialog contenedorPadre) {
        try {
            String ruta = ".\\MisReportes\\ListaDeCobro.jrxml";
            //String ruta = "C:\\Users\\Berlis\\JaspersoftWorkspace\\MyReports\\ListaDeCobro.jrxml";
            HashMap parametros = new HashMap();

            parametros.put("id", idCobrador);
            parametros.put("fecha", new java.sql.Date(fecha));

            reporte = JasperCompileManager.compileReport(ruta);
            imprimir = JasperFillManager.fillReport(reporte, parametros, Bd.getConexion());

            if (!imprimir.getPages().isEmpty()) {
                viewer = new JasperViewer(imprimir, false);
                anadirReporteAContenedor(viewer, contenedorPadre);
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos.");
            }

            Bd.cerrarRecursos();

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void reporteAdelantosA4(int idCobrador, long fecha, JDialog contenedorPadre) {
        try {
            String ruta = ".\\MisReportes\\Adelantos.jrxml";
            //String ruta = "C:\\Users\\Berlis\\JaspersoftWorkspace\\MyReports\\Adelantos.jrxml";
            HashMap parametros = new HashMap();

            parametros.put("id", idCobrador);
            parametros.put("fecha", new java.sql.Date(fecha));

            reporte = JasperCompileManager.compileReport(ruta);
            imprimir = JasperFillManager.fillReport(reporte, parametros, Bd.getConexion());

            if (!imprimir.getPages().isEmpty()) {
                viewer = new JasperViewer(imprimir, false);
                anadirReporteAContenedor(viewer, contenedorPadre);
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos.");
            }

            Bd.cerrarRecursos();

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    public static void reporteRecibosMatricial(HashMap parametros, JDialog contenedorPadre) {
        String ruta = ".\\MisReportes\\Recibos.jrxml";
        //String ruta = "C:\\Users\\Berlis\\JaspersoftWorkspace\\MyReports\\Recibos.jrxml";
        generarReportes( parametros, contenedorPadre , ruta);
    }

    private static void generarReportes(HashMap parametros, JDialog contenedorPadre,String ruta) {
        try {
            
            reporte = (JasperReport) JasperCompileManager.compileReport(ruta);//JRLoader.loadObjectFromFile(ruta);//
            imprimir = JasperFillManager.fillReport(reporte, parametros, Bd.getConexion());

            if (!imprimir.getPages().isEmpty()) {
                viewer = new JasperViewer(imprimir, false);
                anadirReporteAContenedor(viewer, contenedorPadre);
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos.");
            }

            Bd.cerrarRecursos();

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    private static void anadirReporteAContenedor(JasperViewer viewer, JDialog padre) {
        contenedorReporte = new JDialog(padre);
        contenedorReporte.setContentPane(viewer.getContentPane());
        contenedorReporte.setSize(viewer.getSize());
        contenedorReporte.setLocationRelativeTo(null);
        contenedorReporte.setVisible(true);
    }
    
    

}
