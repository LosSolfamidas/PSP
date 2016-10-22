package Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 *
 * @author LYNCHANIANO
 */
public class FrameVentanaPrincipal extends javax.swing.JFrame implements Runnable {

    public int cont1 = 0;
    public int cont2 = 0;
    public int cont3 = 0;

    /**
     * Creates new form FrameVentanaPrincipal
     */
    public FrameVentanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);//Pantalla al centro
        setResizable(false);
        setTitle("Ventana Principal");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelVentanaPrincipal = new javax.swing.JPanel();
        jLabelComandos = new javax.swing.JLabel();
        jButtonOK = new javax.swing.JButton();
        jTextFieldComando = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaErrores = new javax.swing.JTextArea();
        jLabelTitulo = new javax.swing.JLabel();
        jButtonEjecutaVentana1 = new javax.swing.JButton();
        jLabelResultado = new javax.swing.JLabel();
        jLabelErrores = new javax.swing.JLabel();
        jButtonEjecutaVentana2 = new javax.swing.JButton();
        jButtonEjecutaVentana3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelComandos.setText("Escribe aquí el comando a ejecutar");

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jTextAreaResultado.setColumns(20);
        jTextAreaResultado.setRows(5);
        jScrollPane1.setViewportView(jTextAreaResultado);

        jTextAreaErrores.setColumns(20);
        jTextAreaErrores.setRows(5);
        jScrollPane2.setViewportView(jTextAreaErrores);

        jLabelTitulo.setText("PRÁCTICA PROCESOS");

        jButtonEjecutaVentana1.setText("Ventana 1");
        jButtonEjecutaVentana1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutaVentana1ActionPerformed(evt);
            }
        });

        jLabelResultado.setText("RESULTADO");

        jLabelErrores.setText("ERRORES");

        jButtonEjecutaVentana2.setText("Ventana 2");
        jButtonEjecutaVentana2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutaVentana2ActionPerformed(evt);
            }
        });

        jButtonEjecutaVentana3.setText("Ventana 3");
        jButtonEjecutaVentana3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutaVentana3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelVentanaPrincipalLayout = new javax.swing.GroupLayout(jPanelVentanaPrincipal);
        jPanelVentanaPrincipal.setLayout(jPanelVentanaPrincipalLayout);
        jPanelVentanaPrincipalLayout.setHorizontalGroup(
            jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabelComandos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                        .addGroup(jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResultado)
                            .addComponent(jLabelErrores))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelVentanaPrincipalLayout.createSequentialGroup()
                        .addGroup(jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jButtonEjecutaVentana1)
                                .addGap(152, 152, 152)
                                .addComponent(jButtonEjecutaVentana2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonEjecutaVentana3))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jTextFieldComando)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonOK)))
                        .addGap(36, 36, 36))))
        );
        jPanelVentanaPrincipalLayout.setVerticalGroup(
            jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVentanaPrincipalLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelComandos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOK)
                    .addComponent(jTextFieldComando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResultado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabelErrores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelVentanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEjecutaVentana1)
                    .addComponent(jButtonEjecutaVentana2)
                    .addComponent(jButtonEjecutaVentana3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVentanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelVentanaPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        
        
        final Runtime cmd = Runtime.getRuntime();
        Thread threads = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Process procesoResultado = cmd.exec(jTextFieldComando.getText());
                        String lineaResultado;

                        BufferedReader leerRespuesta = new BufferedReader(new InputStreamReader(procesoResultado.getInputStream(), StandardCharsets.UTF_8));

                        /*Limpiar jTextAreaResultado*/
                        jTextAreaErrores.setText("");
                        jTextAreaResultado.setText("");

                        /*Escribimos Resultado*/
                        while ((lineaResultado = leerRespuesta.readLine()) != null) {
                            jTextAreaResultado.append(lineaResultado + "\n");
                        }
                    } catch (Exception e) {
                        jTextAreaErrores.setText("");
                        jTextAreaResultado.setText("");
                        jTextAreaErrores.append(e.getMessage() + "\n");

                    }
                }
        });
        threads.start();

        

    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonEjecutaVentana1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutaVentana1ActionPerformed
        
        if(cont1<1){new Ventana01(this,true).setVisible(true); cont1++;}else{}
    }//GEN-LAST:event_jButtonEjecutaVentana1ActionPerformed

    private void jButtonEjecutaVentana2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutaVentana2ActionPerformed
         if(cont2<1){new Ventana02(this,true).setVisible(true);cont2++;}else{}
    }//GEN-LAST:event_jButtonEjecutaVentana2ActionPerformed

    private void jButtonEjecutaVentana3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutaVentana3ActionPerformed
         if(cont3<1){new Ventana03(this,true).setVisible(true);cont3++;}else{}
    }//GEN-LAST:event_jButtonEjecutaVentana3ActionPerformed

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
            java.util.logging.Logger.getLogger(FrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameVentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                new FrameVentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEjecutaVentana1;
    private javax.swing.JButton jButtonEjecutaVentana2;
    private javax.swing.JButton jButtonEjecutaVentana3;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JLabel jLabelComandos;
    private javax.swing.JLabel jLabelErrores;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelVentanaPrincipal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaErrores;
    private javax.swing.JTextArea jTextAreaResultado;
    private javax.swing.JTextField jTextFieldComando;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        /*aquí va el código que se ejecutará en el hilo*/
       
    }
}
