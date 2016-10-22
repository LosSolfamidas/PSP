package FTP;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPFile;
import com.enterprisedt.net.ftp.FileTransferClient;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GUI extends javax.swing.JFrame {

    FileTransferClient ftp;
    private String url;
    private int puerto;
    private String usuario;
    private String password;

    private String pathServidor = "/My Pictures";
    private File pathCliente = new File("FicherosCliente");
    private static ArrayList<File> arrayCliente = new ArrayList<>();
    private static ArrayList<FTPFile> arrayServidor = new ArrayList<>();

    public GUI() {
        initComponents();
        ftp = new FileTransferClient();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnEnviarClientes = new javax.swing.JButton();
        txtPuertoCliente = new javax.swing.JTextField();
        btnEliminarCliente = new javax.swing.JButton();
        btnConectarCliente = new javax.swing.JButton();
        btnEliminarServidor = new javax.swing.JButton();
        btnCargarListas = new javax.swing.JButton();
        jLabelCabeceraServidor = new javax.swing.JLabel();
        jLabelCabeceraCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modeloCliente = new DefaultListModel();
        listCliente = new javax.swing.JList<>();
        btnRecibirServidor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        modeloServidor = new DefaultListModel();
        listServidor = new javax.swing.JList<>();
        txtURLCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Puerto:");

        btnEnviarClientes.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEnviarClientes.setText("Enviar");
        btnEnviarClientes.setEnabled(false);
        btnEnviarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarClientesActionPerformed(evt);
            }
        });

        txtPuertoCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPuertoCliente.setText("21");

        btnEliminarCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.setEnabled(false);
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnConectarCliente.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        btnConectarCliente.setText("Conectar Cliente");
        btnConectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarClienteActionPerformed(evt);
            }
        });

        btnEliminarServidor.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnEliminarServidor.setText("Eliminar");
        btnEliminarServidor.setEnabled(false);
        btnEliminarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServidorActionPerformed(evt);
            }
        });

        btnCargarListas.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnCargarListas.setText("Recargar Listas");
        btnCargarListas.setEnabled(false);
        btnCargarListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarListasActionPerformed(evt);
            }
        });

        jLabelCabeceraServidor.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabelCabeceraServidor.setText("Ficheros Servidor");

        jLabelCabeceraCliente.setFont(new java.awt.Font("DialogInput", 3, 18)); // NOI18N
        jLabelCabeceraCliente.setText("Ficheros Cliente");

        listCliente.setModel(modeloCliente);
        jScrollPane1.setViewportView(listCliente);

        btnRecibirServidor.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        btnRecibirServidor.setText("Enviar");
        btnRecibirServidor.setEnabled(false);
        btnRecibirServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirServidorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("IP:");

        listServidor.setModel(modeloServidor);
        jScrollPane2.setViewportView(listServidor);

        txtURLCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        txtURLCliente.setText("ftp.drivehq.com");

        jLabel1.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        jLabel1.setText("P3_Ej2(FTP)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel4.setText("User");

        txtUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtUser.setText("lynchaniano");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel5.setText("password");

        txtPassword.setText("asdf1234");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtURLCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtPuertoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword)
                                    .addComponent(txtUser))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCargarListas)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnConectarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(btnEliminarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabelCabeceraCliente)
                        .addGap(161, 161, 161)
                        .addComponent(jLabelCabeceraServidor)))
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(235, 235, 235))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnConectarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargarListas)
                        .addGap(55, 55, 55))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarClienteActionPerformed

        try {
            url = txtURLCliente.getText();
            puerto = Integer.parseInt(txtPuertoCliente.getText());
            usuario = txtUser.getText();
            password = new String(txtPassword.getPassword());

            if (ftp.isConnected()) {
                ftp.disconnect(true);
            }

            ftp.setRemoteHost(url);
            ftp.setRemotePort(puerto);
            ftp.setUserName(usuario);
            ftp.setPassword(password);
            ftp.connect();

            synchronized (GUI.this) {
                cargarFicherosServidor();
            }
            cargarListaFicherosCliente();

            btnConectarCliente.setEnabled(false);
            txtURLCliente.setEnabled(false);
            txtPuertoCliente.setEnabled(false);
            btnCargarListas.setEnabled(true);
            enableButtons();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error " + e);
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnConectarClienteActionPerformed

    private void btnCargarListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarListasActionPerformed
        try {
            cargarFicherosServidor();
            cargarListaFicherosCliente();
        } catch (FTPException | IOException | ParseException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCargarListasActionPerformed

    private void btnEnviarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarClientesActionPerformed

        int index = listCliente.getSelectedIndex();
        if (index != -1) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dissableButtons();
                        ftp.uploadFile(arrayCliente.get(index).getAbsolutePath(), pathServidor + '/' + arrayCliente.get(index).getName());
                        cargarListaFicherosCliente();
                        synchronized (GUI.this) {     
                            cargarFicherosServidor();
                        }
                        enableButtons();
                    } catch (FTPException | IOException | ParseException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            t.start();
        }
    }//GEN-LAST:event_btnEnviarClientesActionPerformed

    private void btnEliminarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServidorActionPerformed
        final int index = listServidor.getSelectedIndex();
        if (index != -1) {
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dissableButtons();
                        ftp.deleteFile(arrayServidor.get(index).getPath() + '/' + arrayServidor.get(index).getName());
                        cargarListaFicherosCliente();
                        synchronized (GUI.this) {
                            cargarFicherosServidor();
                        }
                        enableButtons();
                    } catch (FTPException | IOException | ParseException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            t.start();
        }
    }//GEN-LAST:event_btnEliminarServidorActionPerformed

    private void btnRecibirServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirServidorActionPerformed
        final int index = listServidor.getSelectedIndex();
        final String remotePath = arrayServidor.get(index).getPath() + '/' + arrayServidor.get(index).getName();
        final String localPath = pathCliente.getAbsolutePath() + "/" + arrayServidor.get(index).getName();

        if (index != -1) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        dissableButtons();
                        ftp.downloadFile(localPath, remotePath);
                        cargarListaFicherosCliente();
                        synchronized (GUI.this) {
                            cargarFicherosServidor();
                        }
                        enableButtons();
                    } catch (FTPException | IOException | ParseException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t.start();
        }
    }//GEN-LAST:event_btnRecibirServidorActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        int index = listCliente.getSelectedIndex();
        if (index != -1) {
            arrayCliente.get(index).delete();
            arrayCliente.remove(index);
            modeloCliente.remove(index);
            JOptionPane.showMessageDialog(this, "Fichero Borrado en el Cliente");
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    public synchronized void cargarFicherosServidor() throws FTPException, IOException, ParseException {
        modeloServidor.removeAllElements();
        arrayServidor.clear();
        arrayServidor.addAll(Arrays.asList(ftp.directoryList(pathServidor)));

        for (int i = 0; i < arrayServidor.size(); i++) {
            if (arrayServidor.get(i).isFile()) {
                modeloServidor.add(i, arrayServidor.get(i).getName());
            }
        }
    }

    

    private synchronized void cargarListaFicherosCliente() {
        modeloCliente.removeAllElements();
        arrayCliente.clear();

        if (pathCliente.isDirectory()) {
            File[] ficheros = pathCliente.listFiles();

            for (int i = 0; i < ficheros.length; i++) {
                File fichero = ficheros[i];
                modeloCliente.add(i, fichero.getName());
                arrayCliente.add(fichero);
            }
        }
    }
    
    private synchronized void dissableButtons() {
        btnEliminarCliente.setEnabled(false);
        btnEnviarClientes.setEnabled(false);
        btnRecibirServidor.setEnabled(false);
        btnEliminarServidor.setEnabled(false);
        btnCargarListas.setEnabled(false);
    }

    private synchronized void enableButtons() {
        btnEliminarCliente.setEnabled(true);
        btnEnviarClientes.setEnabled(true);
        btnRecibirServidor.setEnabled(true);
        btnEliminarServidor.setEnabled(true);
        btnCargarListas.setEnabled(true);
    }


    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        ftp.disconnect(true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCabeceraCliente;
    private javax.swing.JLabel jLabelCabeceraServidor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JList<String> listCliente;
    public static DefaultListModel modeloCliente;
    public static javax.swing.JList<String> listServidor;
    public static DefaultListModel modeloServidor;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPuertoCliente;
    private javax.swing.JTextField txtURLCliente;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
