package Vista;


public class GUI_Principal extends javax.swing.JFrame {
    
    public GUI_Principal() {
        initComponents();
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        menuAbrirServidor = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VENTANA PRINCIPAL");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imagen.png"))); // NOI18N

        jMenu3.setText("Opciones");

        menuAbrirServidor.setText("Abrir Puerto");
        menuAbrirServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirServidorActionPerformed(evt);
            }
        });
        jMenu3.add(menuAbrirServidor);

        jMenuItem2.setText("Gestionar Ficheros");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 314, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuAbrirServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirServidorActionPerformed
        (new GUI_AbrePuertoServidor()).setVisible(true);
    }//GEN-LAST:event_menuAbrirServidorActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        (new GUI_ClienteServidor()).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Principal().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem menuAbrirServidor;
    // End of variables declaration//GEN-END:variables
    
}
