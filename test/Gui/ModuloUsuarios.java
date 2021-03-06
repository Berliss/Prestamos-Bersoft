/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Gui.*;
import BaseDeDatos.Bd;
import Pojos.ConfiguracionPrestamos;
import Pojos.Usuario;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author berli
 */
public class ModuloUsuarios extends javax.swing.JDialog {

    private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    private DefaultTableModel modelo = new DefaultTableModel();
    private List<JTextField> lista;
    private Usuario usuarioSeleccionado;
    private boolean voyAGuardarUnUsuario = true;
    private static boolean habilitarListenerDeFocus = true;

    /**
     * Creates new form ModuloUsuarios
     */
    public ModuloUsuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        lista = ListaDeTxt();
        formatoTabla();
        esconderTxt(false);
        setLocationRelativeTo(null);
        rellenarComboDeConfiguraciones();
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
        txtContrasena = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtConfirmarContrasena = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        comboConfig = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtContrasenaAnterior = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        txtBuscador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel14.setText("Contrasena");

        jLabel15.setText("Usuario");

        jLabel16.setText("Codigo");

        jLabel17.setText("Contrasena Anterior");

        txtCodigo.setText("Cod");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel5.add(btnNuevo);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuardar);

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancelar);

        jLabel12.setText("Confirmar Contrasena");

        comboConfig.setModel(modeloCombo);
        comboConfig.setEnabled(false);

        jLabel13.setText("Configuracion Prestamos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(txtContrasena)
                    .addComponent(txtConfirmarContrasena)
                    .addComponent(comboConfig, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtContrasenaAnterior))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasenaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtCodigo.setFocusable(false);

        jTabbedPane1.addTab("Usuarios", jPanel2);

        txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscadorKeyReleased(evt);
            }
        });

        tabla.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(txtBuscador))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Buscar", jPanel3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
        funcionalidadBotonEditar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        funcionalidadDeBotonCancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getClickCount() == 2) {
            rellenarCampos();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && tabla.getSelectedRow()> -1) {
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
            java.util.logging.Logger.getLogger(ModuloUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModuloUsuarios dialog = new ModuloUsuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<ConfiguracionPrestamos> comboConfig;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscador;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtConfirmarContrasena;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JPasswordField txtContrasenaAnterior;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    private List<JTextField> ListaDeTxt() {
        return Arrays.asList(new JTextField[]{txtUsuario, txtContrasena, txtConfirmarContrasena});
    }

    private void habilitarCampos(boolean valor) {
        lista.forEach(txt -> {
            txt.setFocusable(valor);
        });
        comboConfig.setEnabled(valor);
        btnEditar.setEnabled(!valor);
        btnGuardar.setEnabled(valor);
        txtUsuario.setEnabled(valor);

        if (valor) {
            esconderTxt(valor);
        } else {
            esconderTxt(valor);
        }

    }

    private void formatoTabla() {
        tabla.setDefaultEditor(Object.class, null);
        String[] titulos = {"Id", "Usuario", "Configuracion"};
        int[] tamanos = {50, 200, 300};
        metodosGenerales.FormatoTablas(tabla, modelo, titulos, tamanos);
        titulos = null;
        tamanos = null;
    }

    private void funcionalidadDeBotonNuevo() {
        metodosGenerales.borrarTxt(ListaDeTxt());
        comboConfig.setSelectedIndex(0);
        habilitarCampos(true);
        esconderTxt(false);
        voyAGuardarUnUsuario = true;
    }

    private void funcionalidadDeBotonGuardar() {

        boolean hayCampoVacio = metodosGenerales.hayUnCampoVacio(lista);
        String contrasena = new String(txtContrasena.getPassword());
        String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());

        if (voyAGuardarUnUsuario) {

            if (!hayCampoVacio) {

                if (contrasena.equals(confirmarContrasena)) {

                    Usuario u = new Usuario(1, txtUsuario.getText(), contrasena, (ConfiguracionPrestamos) comboConfig.getSelectedItem());

                    boolean miUsuarioSeInsertoCorrectamente = Bd.insertarUsuario(u);

                    if (miUsuarioSeInsertoCorrectamente) {
                        metodosGenerales.borrarTxt(lista);
                        comboConfig.setSelectedIndex(0);
                        txtUsuario.grabFocus();
                        btnEditar.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario ya existe.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden.");
                }
            }

        } else {
            if (!hayCampoVacio) {

                if (new String(txtContrasenaAnterior.getPassword()).equals(usuarioSeleccionado.getContrasena())) {

                    if (contrasena.equals(confirmarContrasena)) {
                        int id = usuarioSeleccionado.getIdUsuario();
                        String nombre = txtUsuario.getText();
                        ConfiguracionPrestamos c = (ConfiguracionPrestamos) comboConfig.getSelectedItem();

                        Bd.editarUsuario(new Usuario(id, nombre, contrasena, c));

                        metodosGenerales.borrarTxt(lista);
                        comboConfig.setSelectedIndex(0);
                        txtUsuario.grabFocus();
                        voyAGuardarUnUsuario = true;
                    } else {

                        JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden.");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "La contrasena anterior es incorrecta.");
                }
            }
        }

        if (habilitarListenerDeFocus == true) {
            metodosGenerales.focusPerdidoTxt(lista);
            habilitarListenerDeFocus = false;
        }

    }

    private void funcionalidadBotonEditar() {
        voyAGuardarUnUsuario = false;
        habilitarCampos(true);
        txtUsuario.setEnabled(false);
    }

    private void funcionalidadDeBotonCancelar() {
        if (metodosGenerales.hayAlmenosUnCampoLleno(lista)) {

            metodosGenerales.borrarTxt(lista);
            habilitarCampos(true);
            comboConfig.setSelectedIndex(0);
            voyAGuardarUnUsuario = true;
            esconderTxt(false);

        } else {
            dispose();
        }
    }

    private void buscarPorCriterioConfiguracionoPrestamos(String criterio) {
        metodosGenerales.LimpiarTabla(modelo);
        List<Usuario> lista = Bd.buscarUsuario(criterio);
        modelo.setRowCount(lista.size());
        Usuario u;

        for (int i = 0; i < lista.size(); i++) {
            u = lista.get(i);
            modelo.setValueAt(u.getIdUsuario(), i, 0);
            modelo.setValueAt(u, i, 1);
            modelo.setValueAt(u.getConfig().getNombreConfiguracion(), i, 2);
        }
        u = null;
        lista = null;
    }

    private void rellenarCampos() {

        int filaSeleccionada = tabla.getSelectedRow();
        usuarioSeleccionado = (Usuario) tabla.getValueAt(filaSeleccionada, 1);

        txtCodigo.setText(String.valueOf(usuarioSeleccionado.getIdUsuario()));
        txtUsuario.setText(usuarioSeleccionado.getUsuario());
        txtContrasena.setText("987654321");
        txtConfirmarContrasena.setText("asdfghjkl");

        for (int i = 0; i < modeloCombo.getSize(); i++) {
            if (comboConfig.getItemAt(i).getIdConf() == usuarioSeleccionado.getConfig().getIdConf()) {
                comboConfig.setSelectedIndex(i);
                break;
            }
        }

        metodosGenerales.LimpiarTabla(modelo);//anadido despues
        habilitarCampos(false);
        jTabbedPane1.setSelectedIndex(0);

    }

    private void rellenarComboDeConfiguraciones() {
        Bd.buscarConfiguracionPrestamos("").forEach(configuracion -> {
            modeloCombo.addElement(configuracion);
        });
    }

    private void esconderTxt(boolean valor) {
        jLabel17.setVisible(valor);
        txtContrasenaAnterior.setVisible(valor);
        txtContrasenaAnterior.setText(valor ? "" : "|-|-|-|Aa");
    }

}
