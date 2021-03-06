package Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author LYNCHANIANO
 */
public class Ventana01 extends javax.swing.JDialog {

    /**
     * Creates new form Ventana01
     * @param parent
     * @param modal
     */
    public Ventana01(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setModal(false);
        setResizable(false);
        setTitle("Ventana 1");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResultado1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaErrores1 = new javax.swing.JTextArea();
        jLabelComandos1 = new javax.swing.JLabel();
        jLabelResultado1 = new javax.swing.JLabel();
        jLabelErrores1 = new javax.swing.JLabel();
        jTextFieldComando1 = new javax.swing.JTextField();
        jButtonOK1 = new javax.swing.JButton();
        jLabelTitulo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextAreaResultado1.setColumns(20);
        jTextAreaResultado1.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResultado1);

        jTextAreaErrores1.setColumns(20);
        jTextAreaErrores1.setRows(5);
        jScrollPane2.setViewportView(jTextAreaErrores1);

        jLabelComandos1.setText("Escribe aquí el comando a ejecutar");

        jLabelResultado1.setText("RESULTADO");

        jLabelErrores1.setText("ERRORES");

        jButtonOK1.setText("OK");
        jButtonOK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOK1ActionPerformed(evt);
            }
        });

        jLabelTitulo1.setText("VENTANA1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitulo1)
                    .addComponent(jLabelErrores1)
                    .addComponent(jLabelResultado1)
                    .addComponent(jLabelComandos1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldComando1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOK1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo1)
                .addGap(28, 28, 28)
                .addComponent(jLabelComandos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldComando1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOK1))
                .addGap(19, 19, 19)
                .addComponent(jLabelResultado1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabelErrores1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOK1ActionPerformed
        final Runtime cmd = Runtime.getRuntime();
        Thread threads = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Process procesoResultado = cmd.exec(jTextFieldComando1.getText());
                        String lineaResultado;

                        BufferedReader leerRespuesta = new BufferedReader(new InputStreamReader(procesoResultado.getInputStream(), StandardCharsets.UTF_8));

                        /*Limpiar jTextAreaResultado*/
                        jTextAreaErrores1.setText("");
                        jTextAreaResultado1.setText("");

                        /*Escribimos Resultado*/
                        while ((lineaResultado = leerRespuesta.readLine()) != null) {
                            jTextAreaResultado1.append(lineaResultado + "\n");
                        }
                    } catch (Exception e) {
                        jTextAreaErrores1.setText("");
                        jTextAreaResultado1.setText("");
                        jTextAreaErrores1.append(e.getMessage() + "\n");

                    }
                }
        });
        threads.start();
    }//GEN-LAST:event_jButtonOK1ActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ventana01 dialog = new Ventana01(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButtonOK1;
    private javax.swing.JLabel jLabelComandos1;
    private javax.swing.JLabel jLabelErrores1;
    private javax.swing.JLabel jLabelResultado1;
    private javax.swing.JLabel jLabelTitulo1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaErrores1;
    private javax.swing.JTextArea jTextAreaResultado1;
    private javax.swing.JTextField jTextFieldComando1;
    // End of variables declaration//GEN-END:variables
}
