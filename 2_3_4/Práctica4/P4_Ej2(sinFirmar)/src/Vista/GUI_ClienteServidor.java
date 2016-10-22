package Vista;

import ModeloControlador.Cliente;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GUI_ClienteServidor extends javax.swing.JFrame {

    private Cliente metodoCliente;
    private String url;
    private int puerto;
    private File directorioFicheros = new File("FicherosCliente");
    public static ArrayList< File> arrayCliente = new ArrayList<>();

    public GUI_ClienteServidor() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        modeloCliente = new DefaultListModel();
        listCliente = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        modeloServidor = new DefaultListModel();
        listServidor = new javax.swing.JList<>();
        btnEnviarClientes = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnEliminarServidor = new javax.swing.JButton();
        btnCargarListas = new javax.swing.JButton();
        jLabelCabeceraServidor = new javax.swing.JLabel();
        jLabelCabeceraCliente = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnRecibirServidor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtURLCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPuertoCliente = new javax.swing.JTextField();
        btnConectarCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GESTIÓN FICHEROS");

        listCliente.setModel(modeloCliente);
        jScrollPane1.setViewportView(listCliente);

        listServidor.setModel(modeloServidor);
        jScrollPane2.setViewportView(listServidor);

        btnEnviarClientes.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEnviarClientes.setText("Enviar");
        btnEnviarClientes.setEnabled(false);
        btnEnviarClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnviarClientesMouseClicked(evt);
            }
        });

        btnEliminarCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.setEnabled(false);
        btnEliminarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseClicked(evt);
            }
        });

        btnEliminarServidor.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEliminarServidor.setText("Eliminar");
        btnEliminarServidor.setEnabled(false);
        btnEliminarServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarServidorMouseClicked(evt);
            }
        });

        btnCargarListas.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnCargarListas.setText("Recargar Listas");
        btnCargarListas.setEnabled(false);
        btnCargarListas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCargarListasMouseClicked(evt);
            }
        });

        jLabelCabeceraServidor.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabelCabeceraServidor.setText("Ficheros Servidor");

        jLabelCabeceraCliente.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabelCabeceraCliente.setText("Ficheros Cliente");

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        jLabel1.setText("P4_Ej2(sinFirmar)");

        btnRecibirServidor.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnRecibirServidor.setText("Enviar");
        btnRecibirServidor.setEnabled(false);
        btnRecibirServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRecibirServidorMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("IP:");

        txtURLCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtURLCliente.setText("localhost");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Puerto:");

        txtPuertoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPuertoCliente.setText("7878");

        btnConectarCliente.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        btnConectarCliente.setText("Conectar Cliente");
        btnConectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnConectarCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtURLCliente))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPuertoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCargarListas)
                        .addGap(19, 19, 19)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabelCabeceraCliente)
                        .addGap(161, 161, 161)
                        .addComponent(jLabelCabeceraServidor))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEnviarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRecibirServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(161, 161, 161))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCabeceraCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCabeceraServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEnviarClientes)
                                    .addComponent(btnEliminarCliente)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnEliminarServidor)
                                    .addComponent(btnRecibirServidor))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtURLCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPuertoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnConectarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarListas)
                        .addGap(55, 55, 55))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarListasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCargarListasMouseClicked
        cargarListaFicherosCliente();
        cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());
    }//GEN-LAST:event_btnCargarListasMouseClicked

    private void btnEnviarClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnviarClientesMouseClicked
        int index = listCliente.getSelectedIndex();
        if (index != -1) {

            metodoCliente.enviarFichero(arrayCliente.get(index));
            cargarListaFicherosCliente();
            cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());
        }
    }//GEN-LAST:event_btnEnviarClientesMouseClicked

    private void btnEliminarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseClicked
        eliminarFicheroCliente();
        cargarListaFicherosCliente();
            cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());
    }//GEN-LAST:event_btnEliminarClienteMouseClicked

    private void btnEliminarServidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarServidorMouseClicked
        if (listServidor.getSelectedIndex() != -1) {
            metodoCliente.eliminarFicheroServidor(listServidor.getSelectedValue());
            cargarListaFicherosCliente();
            cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());
        }
    }//GEN-LAST:event_btnEliminarServidorMouseClicked

    private void btnRecibirServidorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecibirServidorMouseClicked
        int index = listServidor.getSelectedIndex();
        if (index != -1) {
            
            
            
            try {
                metodoCliente.recibirFichero(listServidor.getSelectedValue());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI_ClienteServidor.class.getName()).log(Level.SEVERE, null, ex);
            
            
            
            }
            cargarListaFicherosCliente();
            cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());
        }
    }//GEN-LAST:event_btnRecibirServidorMouseClicked

    private void btnConectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarClienteActionPerformed
        metodoCliente = new Cliente(url, puerto, directorioFicheros);
        try {
            url = txtURLCliente.getText();
            puerto = Integer.parseInt(txtPuertoCliente.getText());

            metodoCliente = new Cliente(url, puerto, directorioFicheros);

            btnConectarCliente.setEnabled(false);
            txtURLCliente.setEnabled(false);
            txtPuertoCliente.setEnabled(false);
            btnEnviarClientes.setEnabled(true);
            btnEliminarCliente.setEnabled(true);
            btnRecibirServidor.setEnabled(true);
            btnEliminarServidor.setEnabled(true);
            btnCargarListas.setEnabled(true);

            //Cargamos y visualizamos las listas de ficheros
            cargarListaFicherosCliente();
            cargarListaFicherosServidor(metodoCliente.cargarListaFicherosServidor());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e);
        }
    }//GEN-LAST:event_btnConectarClienteActionPerformed

    private void cargarListaFicherosCliente() {
        modeloCliente.removeAllElements();
        arrayCliente.clear();

        if (directorioFicheros.isDirectory()) {
            File[] ficheros = directorioFicheros.listFiles();

            for (int i = 0; i < ficheros.length; i++) {
                File fichero = ficheros[i];
                modeloCliente.add(i, fichero.getName());
                arrayCliente.add(fichero);
            }
        }
    }

    private void cargarListaFicherosServidor(String[] ficheros) {
        modeloServidor.removeAllElements();

        for (int i = 0; i < ficheros.length; i++) {
            String fichero = ficheros[i];
            modeloServidor.add(i, fichero);
        }

    }

    /**
     * El siguiente metodo se encarga de borrar el fichero del cliente.
     */
    public void eliminarFicheroCliente() {

        int index = listCliente.getSelectedIndex();
        if (index != -1) {
            arrayCliente.get(index).delete();
            arrayCliente.remove(index);
            modeloCliente.remove(index);
            JOptionPane.showMessageDialog(this, "Fichero Borrado en el Cliente");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarListas;
    private javax.swing.JButton btnConectarCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarServidor;
    private javax.swing.JButton btnEnviarClientes;
    private javax.swing.JButton btnRecibirServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelCabeceraCliente;
    private javax.swing.JLabel jLabelCabeceraServidor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JList<String> listCliente;
    public static DefaultListModel modeloCliente;
    public static javax.swing.JList<String> listServidor;
    public static DefaultListModel modeloServidor;
    private javax.swing.JTextField txtPuertoCliente;
    private javax.swing.JTextField txtURLCliente;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_ClienteServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the GUI_ClienteServidor */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_ClienteServidor().setVisible(true);
            }
        });
    }
}
