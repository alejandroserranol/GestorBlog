/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alejandro Serrano Loredo
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    Gestionar_SAX gesSAX = new Gestionar_SAX();
    Gestionar_XPath gesXPath = new Gestionar_XPath();
    Gestionar_JAXB gesJAXB = new Gestionar_JAXB();

    File ficheroXML = null;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonConectarSAX = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButtonMostrarSAX = new javax.swing.JButton();
        jLabelMensajeConexion = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSalidaSAX = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jButtonMostrarPost = new javax.swing.JButton();
        jComboBoxId = new javax.swing.JComboBox<>();
        jButtonLike = new javax.swing.JButton();
        jLabelMensajeRevision = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaRevision = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagenes/blog.png")).getImage()
        );

        jButtonConectarSAX.setText("Conectar con base de datos");
        jButtonConectarSAX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConectarSAXActionPerformed(evt);
            }
        });

        jButtonMostrarSAX.setText("Mostrar");
        jButtonMostrarSAX.setEnabled(false);
        jButtonMostrarSAX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarSAXActionPerformed(evt);
            }
        });

        jTextAreaSalidaSAX.setColumns(20);
        jTextAreaSalidaSAX.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSalidaSAX);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonMostrarSAX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelMensajeConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonMostrarSAX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMensajeConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Home", jPanel1);

        jButtonMostrarPost.setText("Mostrar Post");
        jButtonMostrarPost.setEnabled(false);
        jButtonMostrarPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarPostActionPerformed(evt);
            }
        });

        jComboBoxId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        jButtonLike.setText("Me gusta");
        jButtonLike.setEnabled(false);
        jButtonLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLikeActionPerformed(evt);
            }
        });

        jTextAreaRevision.setColumns(20);
        jTextAreaRevision.setRows(5);
        jScrollPane2.setViewportView(jTextAreaRevision);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonMostrarPost)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLike)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelMensajeRevision, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMensajeRevision, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonMostrarPost)
                        .addComponent(jComboBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLike)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Revisión", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(394, 394, 394)
                        .addComponent(jButtonConectarSAX)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonConectarSAX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConectarSAXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConectarSAXActionPerformed

        ficheroXML = dialogoSeleccionarFichero();
        if (ficheroXML == null) {
            this.jLabelMensajeConexion.setText("Error en conexión con base de datos.");
            this.jLabelMensajeRevision.setText("Error en conexión con base de datos.");
            this.jButtonMostrarSAX.setEnabled(false);
            this.jButtonMostrarPost.setEnabled(false);
            this.jButtonLike.setEnabled(false);
        } else {
            this.jLabelMensajeConexion.setText("¡Conexión establecida correctamente!");
            this.jLabelMensajeRevision.setText("¡Conexión establecida correctamente!");
            this.jButtonMostrarSAX.setEnabled(true);
            this.jButtonMostrarPost.setEnabled(true);
            this.jButtonLike.setEnabled(true);
        }


    }//GEN-LAST:event_jButtonConectarSAXActionPerformed

    private void jButtonMostrarSAXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarSAXActionPerformed

        if (gesSAX.preparar_SAX(ficheroXML) == -1) {
            this.jLabelMensajeConexion.setText("Error en acceso a datos.");
        } else {
            this.jTextAreaSalidaSAX.setText(gesSAX.recorrerSAX());
        }        

    }//GEN-LAST:event_jButtonMostrarSAXActionPerformed

    private void jButtonMostrarPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarPostActionPerformed
        
        if (gesXPath.preparar_XPath(ficheroXML) == -1) {
            this.jLabelMensajeRevision.setText("Error en acceso a datos.");
        } else {
            String consultaPost = "/Blog/Post[./@id=" + String.valueOf(jComboBoxId.getSelectedItem()) + "]";
            this.jTextAreaRevision.setText(gesXPath.ejecutar_XPath(consultaPost));
        }        

    }//GEN-LAST:event_jButtonMostrarPostActionPerformed

    private void jButtonLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLikeActionPerformed
        
        if(gesJAXB.preparar_JAXB(ficheroXML)==-1){
            this.jLabelMensajeRevision.setText("Error en acceso a datos.");
        } else {
            if(gesJAXB.annadirLike(String.valueOf(jComboBoxId.getSelectedItem()), ficheroXML) == -1){
                this.jLabelMensajeRevision.setText("Error en dar like.");
            } else {
                this.jLabelMensajeRevision.setText("¡Me gusta!");
            }
        }
        
    }//GEN-LAST:event_jButtonLikeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConectarSAX;
    private javax.swing.JButton jButtonLike;
    private javax.swing.JButton jButtonMostrarPost;
    private javax.swing.JButton jButtonMostrarSAX;
    private javax.swing.JComboBox<String> jComboBoxId;
    private javax.swing.JLabel jLabelMensajeConexion;
    private javax.swing.JLabel jLabelMensajeRevision;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaRevision;
    private javax.swing.JTextArea jTextAreaSalidaSAX;
    // End of variables declaration//GEN-END:variables

    private File dialogoSeleccionarFichero() {

        File fichero = null;

        try {
            JFileChooser fco = new JFileChooser();

            //Busca evitar abrir ficheros binarios
            fco.setFileFilter(new FileNameExtensionFilter("Archivos xml", "xml"));

            fco.setMultiSelectionEnabled(false);
            fco.setDialogType(JFileChooser.OPEN_DIALOG);

            //Si no se produce ningún error seleccion será 0
            int seleccion = fco.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                fichero = fco.getSelectedFile();
                String nombre = fichero.getName();
                String extension = nombre.substring(nombre.lastIndexOf('.') + 1, nombre.length());

                if (!extension.equalsIgnoreCase("xml")) {

                    JOptionPane.showConfirmDialog(null, "Estensión seleccionada no válida.", "", JOptionPane.PLAIN_MESSAGE);

                } else {
                    return fichero;
                }
            }
        } catch (Exception ex) {
            System.out.println("Fichero no seleccionado.");
        }

        return null;
    }
}
