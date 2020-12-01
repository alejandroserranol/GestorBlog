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
 * @author aleja
 */
public class VentanaPrincipal extends javax.swing.JFrame {
    
    Gestionar_SAX gesSAX = new Gestionar_SAX(); 

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaSalida = new javax.swing.JTextArea();
        jButtonMostrarSax = new javax.swing.JButton();
        jLabelMensaje = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemConectar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaSalida.setColumns(20);
        jTextAreaSalida.setRows(5);
        jScrollPane1.setViewportView(jTextAreaSalida);

        jButtonMostrarSax.setText("Mostrar");
        jButtonMostrarSax.setEnabled(false);
        jButtonMostrarSax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarSaxActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItemConectar.setText("Conectar BBDD");
        jMenuItemConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConectarActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemConectar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMostrarSax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonMostrarSax, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConectarActionPerformed
        
        File ficheroXML = null;
        ficheroXML = dialogoSeleccionarFichero();
        
        if(ficheroXML == null){
            this.jLabelMensaje.setText("Fichero no seleccionado.");
        } else {
            if(gesSAX.abrir_XML_SAX(ficheroXML)==-1){
                this.jLabelMensaje.setText("Error al crear el objeto SAX.");
                this.jButtonMostrarSax.setEnabled(false);
            } else {
                this.jLabelMensaje.setText("¡Objeto JAXB creado!");
                this.jButtonMostrarSax.setEnabled(true);
            }
        }
        
    }//GEN-LAST:event_jMenuItemConectarActionPerformed

    private void jButtonMostrarSaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarSaxActionPerformed
        
        this.jTextAreaSalida.setText(gesSAX.recorrerSAX());
        
    }//GEN-LAST:event_jButtonMostrarSaxActionPerformed

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
    private javax.swing.JButton jButtonMostrarSax;
    private javax.swing.JLabel jLabelMensaje;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemConectar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaSalida;
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
