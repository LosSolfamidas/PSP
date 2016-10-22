package Vista;

import Modelo.Cliente;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GUI_Cliente extends javax.swing.JFrame {

    private Cliente cliente = null;

    public GUI_Cliente() {
        initComponents();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPuertoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtURLCliente = new javax.swing.JTextField();
        btnConectarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaMensajesRecibidos = new javax.swing.JTextArea();
        txtEnviarMensaje = new javax.swing.JTextField();
        txtNickCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList();
        btnDesconectarCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Cliente");

        jLabel2.setText("Puerto");

        txtPuertoCliente.setText("7878");

        jLabel3.setText("URL");

        txtURLCliente.setText("127.0.0.1");

        btnConectarCliente.setText("Conectar");
        btnConectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarClienteActionPerformed(evt);
            }
        });

        txtAreaMensajesRecibidos.setEditable(false);
        txtAreaMensajesRecibidos.setColumns(20);
        txtAreaMensajesRecibidos.setRows(5);
        jScrollPane1.setViewportView(txtAreaMensajesRecibidos);

        txtEnviarMensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnviarMensajeKeyPressed(evt);
            }
        });

        txtNickCliente.setText("Nick");

        jScrollPane2.setViewportView(listaClientes);

        btnDesconectarCliente.setText("Desconectar");
        btnDesconectarCliente.setEnabled(false);
        btnDesconectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarClienteActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1)
                    .add(layout.createSequentialGroup()
                        .add(btnConectarCliente)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnDesconectarCliente))
                    .add(txtEnviarMensaje, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, txtNickCliente)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(jLabel2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(txtPuertoCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel3)))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtURLCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 146, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(txtPuertoCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(txtURLCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtNickCliente, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnConectarCliente)
                    .add(btnDesconectarCliente))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jScrollPane2)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtEnviarMensaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesconectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarClienteActionPerformed

        if (cliente != null) {
            cliente.enviarCadena(3, "");
            cliente.interrupt();
        }
        cliente = null;

        btnConectarCliente.setEnabled(true);
        btnDesconectarCliente.setEnabled(false);

        modeloLista.removeAllElements();
        txtAreaMensajesRecibidos.setText("");

    }//GEN-LAST:event_btnDesconectarClienteActionPerformed

    private void txtEnviarMensajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnviarMensajeKeyPressed
        if (evt.getKeyCode() == 10) {//Cuando se pulsa Intro
            cliente.enviarMensaje(txtEnviarMensaje.getText());
            txtEnviarMensaje.setText("");
            guardarHistorial();
        }
    }//GEN-LAST:event_txtEnviarMensajeKeyPressed
    DefaultListModel modeloLista = new DefaultListModel();
    private void btnConectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarClienteActionPerformed
        listaClientes.setModel(modeloLista);
        try {
            int puerto = Integer.parseInt(txtPuertoCliente.getText());
            String url = txtURLCliente.getText();
            String nick = txtNickCliente.getText();
            if (cliente == null) {
                cliente = new Cliente(puerto, url, nick, this);//this hace referencia a la ventana
                cliente.start();
            }

            btnConectarCliente.setEnabled(false);
            btnDesconectarCliente.setEnabled(true);

            recuperarHistorial();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error datos " + e);
            cliente = null;
        }
    }//GEN-LAST:event_btnConectarClienteActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (cliente != null) {
            cliente.enviarCadena(3, "");
            cliente.interrupt();
        }
        cliente = null;

        btnConectarCliente.setEnabled(true);
        btnDesconectarCliente.setEnabled(false);

        modeloLista.removeAllElements();
        txtAreaMensajesRecibidos.setText("");
    }//GEN-LAST:event_formWindowClosed

    public void recibeMensage(String mensaje) {
        txtAreaMensajesRecibidos.append(mensaje + "\n");
    }

    public void mostrarNuevoCliente(String nickCliente) {
        modeloLista.addElement(nickCliente);
    }

    public void borrarCliente(int posicion) {
        modeloLista.remove(posicion);
    }

    /*Método que guarda el Historial de conversaciones en un fichero*/
    public void guardarHistorial() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("Historial.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir Historial" + e);
            return;
        }

        //Escribimos
        try {
            fileWriter.write(txtAreaMensajesRecibidos.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar Historial" + e);
        }

        //cerramos el fichero
        try {
            fileWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar el fichero que almacena el Historial" + e);
        }
    }

    /*Método que recupera y visualiza un fichero que se encarga de guardar el Historial de conversaciones*/
    public void recuperarHistorial() {
        FileReader fileReader;

        //Abrimos el fichero para escribir
        try {
            fileReader = new FileReader("Historial.txt");
        } catch (IOException io) {
            System.out.println(io);
            return;
        }

        //Leemos
        char[] buffer = new char[256];
        int longitud;
        try {
            while ((longitud = fileReader.read(buffer)) != -1) {
                String s = new String(buffer, 0, longitud);
                txtAreaMensajesRecibidos.setText(txtAreaMensajesRecibidos.getText() + s);
            }
            //JOptionPane.showMessageDialog(null, "Historial leido");
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error al leer el Historial");
        }

        //cerramos el fichero
        try {
            fileReader.close();
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error al cerrar el fichero que almacena el Historial");
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConectarCliente;
    private javax.swing.JButton btnDesconectarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listaClientes;
    private javax.swing.JTextArea txtAreaMensajesRecibidos;
    private javax.swing.JTextField txtEnviarMensaje;
    private javax.swing.JTextField txtNickCliente;
    private javax.swing.JTextField txtPuertoCliente;
    private javax.swing.JTextField txtURLCliente;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Cliente().setVisible(true);
        });
    }
}
