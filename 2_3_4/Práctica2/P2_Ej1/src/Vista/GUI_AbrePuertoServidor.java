package Vista;

import ModeloControlador.Servidor;

public class GUI_AbrePuertoServidor extends javax.swing.JFrame {
    
    private Servidor servidor = null;
    
    public GUI_AbrePuertoServidor() {
        initComponents();
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPuerto = new javax.swing.JTextField();
        btnAbrirPuerto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SERVIDOR");
        setAlwaysOnTop(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Servidor");

        jLabel2.setText("Puerto:");

        txtPuerto.setText("7878");

        btnAbrirPuerto.setText("Abrir puerto");
        btnAbrirPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirPuertoActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(83, 83, 83)
                        .add(jLabel1)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)
                        .add(18, 18, 18)
                        .add(txtPuerto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 60, Short.MAX_VALUE)
                        .add(btnAbrirPuerto)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1)
                .add(28, 28, 28)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(txtPuerto, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnAbrirPuerto))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirPuertoActionPerformed
        if (servidor==null){
            int puerto = Integer.parseInt(txtPuerto.getText());
            servidor = new Servidor(puerto);
            servidor.start();
            dispose();
        }
    }//GEN-LAST:event_btnAbrirPuertoActionPerformed
    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_AbrePuertoServidor().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirPuerto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtPuerto;
    // End of variables declaration//GEN-END:variables
    
}
