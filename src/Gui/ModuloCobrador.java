/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import BaseDeDatos.Bd;
import Pojos.Cobrador;
import Pojos.Zona;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author berli
 */
public class ModuloCobrador extends javax.swing.JDialog {

    private DefaultTableModel modelo = new DefaultTableModel();
    private List<JTextField> listaa;
    private Cobrador cobradorSeleccionado;
    private Zona zonaSeleccionada;
    private boolean voyAGuardarUnCobrador = true;
    private static boolean habilitarListenerDeFocus = true;

    /**
     * Creates new form ModuloCobrador
     */
    public ModuloCobrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        listaa = ListaDeTxt();
        formatoTabla();
        metodosGenerales.camposDeTextoMayuscula(listaa);
        metodosGenerales.limitarCaracteresParaConjunto(listaa, 50);
        metodosGenerales.unCampoDeTextoMayuscula(txtBuscador);
        tabla.getTableHeader().setFont(new java.awt.Font("Century Gothic", 0, 13));
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtTelefon = new javax.swing.JTextField();
        txtZona = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        etiquetaDeZona = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblRegistro = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modulo de Cobradores");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N

        txtTelefon.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N

        txtZona.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtZonaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtZonaKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel4.setText("Telefono");

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel5.setText("Zona");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnNuevo.setBackground(new java.awt.Color(0, 24, 171));
        btnNuevo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setOpaque(true);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel4.add(btnNuevo);

        btnGuardar.setBackground(new java.awt.Color(0, 24, 171));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setOpaque(true);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar);

        btnEditar.setBackground(new java.awt.Color(0, 24, 171));
        btnEditar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.setContentAreaFilled(false);
        btnEditar.setEnabled(false);
        btnEditar.setOpaque(true);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel4.add(btnEditar);

        btnCancelar.setBackground(new java.awt.Color(0, 24, 171));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setOpaque(true);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancelar);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jLabel7.setText("Codigo");

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtCodigo.setText("Cod");

        etiquetaDeZona.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addComponent(txtApellido)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(etiquetaDeZona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtZona)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(etiquetaDeZona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtCodigo.setFocusable(false);

        jTabbedPane1.addTab("Cobrador", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        txtBuscador.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });

        tabla.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tabla.setForeground(new java.awt.Color(51, 51, 51));
        tabla.setModel(modelo);
        tabla.setGridColor(new java.awt.Color(255, 255, 255));
        tabla.setRowHeight(20);
        tabla.setSelectionBackground(new java.awt.Color(102, 102, 255));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        lblRegistro.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        lblRegistro.setText("Registros : 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                    .addComponent(txtBuscador, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblRegistro)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRegistro)
                .addContainerGap())
        );

        jTabbedPane1.addTab(" Buscar", jPanel3);

        jPanel5.setBackground(new java.awt.Color(0, 24, 171));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Modulo de Cobradores");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        funcionalidadDeBotonNuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        funcionalidadDeBotonGuardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        funcioalidadBotonEditar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        funcionalidadDeBotonCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtZonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZonaKeyReleased
        buscarZonaPorCodigo();
    }//GEN-LAST:event_txtZonaKeyReleased

    private void txtZonaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtZonaKeyTyped
        metodosGenerales.formatoNumericoTextField(evt, txtZona, 3);
    }//GEN-LAST:event_txtZonaKeyTyped

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            rellenarCampos();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tabla.getSelectedRow() > -1) {
            rellenarCampos();
        }
    }//GEN-LAST:event_tablaKeyPressed

    private void txtBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscadorKeyReleased
        buscarPorCriterioConfiguracionoPrestamos(txtBuscador.getText());
    }//GEN-LAST:event_txtBuscadorKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Windows (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModuloCobrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloCobrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloCobrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloCobrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModuloCobrador dialog = new ModuloCobrador(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel etiquetaDeZona;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefon;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables

    private List<JTextField> ListaDeTxt() {
        return Arrays.asList(new JTextField[]{txtNombre, txtApellido, txtTelefon, txtZona});
    }

    private void habilitarCampos(boolean valor) {
        listaa.forEach(txt -> {
            txt.setFocusable(valor);
        });
        btnEditar.setEnabled(!valor);
        btnGuardar.setEnabled(valor);
    }

    private void formatoTabla() {
        tabla.setDefaultEditor(Object.class, null);
        String[] titulos = {"Id", "Nombre", "Apellido", "Id", "Zona"};
        int[] tamanos = {50, 150, 150, 50, 100};
        metodosGenerales.FormatoTablas(tabla, modelo, titulos, tamanos);
        titulos = null;
        tamanos = null;
    }

    private void funcionalidadDeBotonNuevo() {
        metodosGenerales.borrarTxt(ListaDeTxt());
        habilitarCampos(true);
        voyAGuardarUnCobrador = true;
    }

    private void funcionalidadDeBotonGuardar() {

        boolean hayCampoVacio = metodosGenerales.hayUnCampoVacio(listaa);

        String id = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefon.getText();
        int idUsuario = ModuloPrincipal.usuario.getIdUsuario();

        if (voyAGuardarUnCobrador) {

            if (!hayCampoVacio) {

                if (zonaSeleccionada != null) {

                    Bd.insertarCobrador(new Cobrador(1, nombre, apellido, telefono, zonaSeleccionada, idUsuario));

                    metodosGenerales.borrarTxt(listaa);
                    etiquetaDeZona.setText("");
                    txtNombre.grabFocus();
                    btnEditar.setEnabled(false);

                } else {
                    JOptionPane.showMessageDialog(this, "Debes de ingrear una zona valida.");
                }

            }

        } else {

            if (!hayCampoVacio) {

                if (zonaSeleccionada != null) {

                    Bd.editarCobrador(new Cobrador(Integer.parseInt(id), nombre, apellido, telefono, zonaSeleccionada, idUsuario));

                    metodosGenerales.borrarTxt(listaa);
                    etiquetaDeZona.setText("");
                    txtNombre.grabFocus();
                    voyAGuardarUnCobrador = true;

                } else {
                    JOptionPane.showMessageDialog(this, "Debes de ingrear una zona valida.");
                }
            }
        }

        if (habilitarListenerDeFocus == true) {
            metodosGenerales.focusPerdidoTxt(listaa);
            habilitarListenerDeFocus = false;
        }
    }

    private void funcioalidadBotonEditar() {
        voyAGuardarUnCobrador = false;
        habilitarCampos(true);
    }

    private void funcionalidadDeBotonCancelar() {
        if (metodosGenerales.hayAlmenosUnCampoLleno(listaa)) {
            metodosGenerales.borrarTxt(listaa);
            habilitarCampos(true);
            voyAGuardarUnCobrador = true;
        } else {
            dispose();
        }
    }

    private void buscarPorCriterioConfiguracionoPrestamos(String criterio) {
        metodosGenerales.LimpiarTabla(modelo);
        int configuracion = ModuloPrincipal.conf.getIdConf();
        List<Cobrador> lista = Bd.buscarCobradorDeTuConfiguracion(criterio, configuracion);
        int numeroDeRegistros = lista.size();
        modelo.setRowCount(numeroDeRegistros);
        lblRegistro.setText("Registros : " + numeroDeRegistros);
        Cobrador c;

        for (int i = 0; i < lista.size(); i++) {
            c = lista.get(i);
            modelo.setValueAt(c.getIdCobrador(), i, 0);
            modelo.setValueAt(c, i, 1);
            modelo.setValueAt(c.getApellido(), i, 2);
            modelo.setValueAt(c.getZona().getIdZona(), i, 3);
            modelo.setValueAt(c.getZona(), i, 4);
        }
        lista = null;
    }

    public void rellenarCampos() {

        int filaSeleccionada = tabla.getSelectedRow();
        cobradorSeleccionado = (Cobrador) tabla.getValueAt(filaSeleccionada, 1);
        zonaSeleccionada = (Zona) tabla.getValueAt(filaSeleccionada, 4);

        txtCodigo.setText(String.valueOf(cobradorSeleccionado.getIdCobrador()));
        txtNombre.setText(cobradorSeleccionado.getNombre());
        txtApellido.setText(cobradorSeleccionado.getApellido());
        txtTelefon.setText(cobradorSeleccionado.getTelefono());
        txtZona.setText(String.valueOf(zonaSeleccionada.getIdZona()));
        etiquetaDeZona.setText(zonaSeleccionada.getNombre());

        metodosGenerales.LimpiarTabla(modelo);//anadido despues
        habilitarCampos(false);
        jTabbedPane1.setSelectedIndex(0);
    }

    private void buscarZonaPorCodigo() {
        String criterio = txtZona.getText();
        int configuracion = ModuloPrincipal.conf.getIdConf();

        if (criterio.length() == 3) {
            List<Zona> lista = Bd.buscarZonaDeTuConfiguracion(criterio, configuracion);
            if (!lista.isEmpty()) {
                zonaSeleccionada = lista.get(0);
                etiquetaDeZona.setText(zonaSeleccionada.getNombre());
                //  jTextField5.setText(jTextField5.getText()+" ->    "+lista.get(0).getNombre());
            } else {
                etiquetaDeZona.setText("No existe.");
                zonaSeleccionada = null;
            }
            lista = null;
        } else {
            etiquetaDeZona.setText("");
            zonaSeleccionada = null;
        }
        criterio = null;
    }

}