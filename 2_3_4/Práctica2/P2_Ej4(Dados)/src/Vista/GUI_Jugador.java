package Vista;

import Modelo.Jugador;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Random;

public class GUI_Jugador extends javax.swing.JFrame {

    private Jugador jugador = null;

    private int ID;
    private ImageIcon gifDado = new ImageIcon("imagenes/gif.gif");
    private ImageIcon[] Dado;
    private ImageIcon resultadoDado;
    private int cont;
    private DefaultListModel modeloLista = new DefaultListModel();

    public GUI_Jugador() {
        initComponents();
        lblDado1.setVisible(false);
        lblDado2.setVisible(false);
        lblCabecera.setVisible(false);
        btnDesconectarCliente.setVisible(false);
        this.cont = 0;
    }

    public GUI_Jugador(int ID) {
        initComponents();
        this.ID = ID;
        lblDado1.setVisible(false);
        lblDado2.setVisible(false);
        lblCabecera.setVisible(false);
        btnDesconectarCliente.setVisible(false);
        this.cont = 0;
    }

    /*Geters/Seters*/
    public int getID() {
        return ID;
    }

    public void setID() {
        this.ID++;
    }

    public ImageIcon getGifDado() {
        return gifDado;
    }

    public void setGifDado(ImageIcon gifDado) {
        this.gifDado = gifDado;
    }

    public ImageIcon[] getDado() {
        return Dado;
    }

    public void setDado(ImageIcon[] Dado) {
        this.Dado = Dado;
    }

    public ImageIcon getResultadoDado() {
        return resultadoDado;
    }

    public void setResultadoDado(ImageIcon resultadoDado) {
        this.resultadoDado = resultadoDado;
    }

    public void setCont() {
        this.cont++;
    }

    private int resultadoAcumulado = 0;


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblPuerto = new javax.swing.JLabel();
        txtPuertoJugador = new javax.swing.JTextField();
        lblURL = new javax.swing.JLabel();
        txtURLJugador = new javax.swing.JTextField();
        btnConectarCliente = new javax.swing.JButton();
        txtNickJugador = new javax.swing.JTextField();
        btnDesconectarCliente = new javax.swing.JButton();
        lblDado1 = new javax.swing.JLabel();
        lblDado2 = new javax.swing.JLabel();
        btnTiraDados = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaPuntuacionesRecibidos = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaJugadores = new javax.swing.JList();
        lblCabecera = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        txtPuntuacion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPuerto.setText("Puerto");
        getContentPane().add(lblPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, -1, -1));

        txtPuertoJugador.setText("7878");
        getContentPane().add(txtPuertoJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 40, 60, -1));

        lblURL.setText("URL");
        getContentPane().add(lblURL, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, 20));

        txtURLJugador.setText("127.0.0.1");
        getContentPane().add(txtURLJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 146, -1));

        btnConectarCliente.setText("Conectar");
        btnConectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnConectarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 91, -1, -1));

        txtNickJugador.setText("Nick");
        getContentPane().add(txtNickJugador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, 107, -1));

        btnDesconectarCliente.setText("Desconectar");
        btnDesconectarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesconectarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDesconectarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, -1));

        lblDado1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADO 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12))); // NOI18N
        getContentPane().add(lblDado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 93, 90));

        lblDado2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADO 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12))); // NOI18N
        getContentPane().add(lblDado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 93, 90));

        btnTiraDados.setText("Tirar");
        btnTiraDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiraDadosActionPerformed(evt);
            }
        });
        getContentPane().add(btnTiraDados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 130, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 132, 498, 10));

        txtAreaPuntuacionesRecibidos.setEditable(false);
        txtAreaPuntuacionesRecibidos.setColumns(20);
        txtAreaPuntuacionesRecibidos.setRows(5);
        jScrollPane1.setViewportView(txtAreaPuntuacionesRecibidos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 220, 230));

        listaJugadores.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        listaJugadores.setForeground(new java.awt.Color(4, 4, 247));
        jScrollPane2.setViewportView(listaJugadores);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 70, 100));

        lblCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cabecera.jpg"))); // NOI18N
        getContentPane().add(lblCabecera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 130));

        txtTurno.setEditable(false);
        txtTurno.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        txtTurno.setForeground(new java.awt.Color(3, 146, 12));
        getContentPane().add(txtTurno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 220, -1));

        txtPuntuacion.setEditable(false);
        txtPuntuacion.setFont(new java.awt.Font("Dialog", 3, 11)); // NOI18N
        getContentPane().add(txtPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 190, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDesconectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesconectarClienteActionPerformed

        if (jugador != null) {
            jugador.enviarDatos_A_Servidor(3, "", 0);
            jugador.interrupt();
        }
        jugador.enviarPuntuaciones_A_Servidor("DESCONECTADO");
        jugador = null;

        btnConectarCliente.setEnabled(true);
        btnDesconectarCliente.setEnabled(false);

        modeloLista.removeAllElements();
        txtAreaPuntuacionesRecibidos.setText("DESCONECTADO");

    }//GEN-LAST:event_btnDesconectarClienteActionPerformed

    private void btnConectarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarClienteActionPerformed
        listaJugadores.setModel(modeloLista);
        try {
            int puerto = Integer.parseInt(txtPuertoJugador.getText());
            String url = txtURLJugador.getText();
            String nick = txtNickJugador.getText();

            if (jugador == null) {
                jugador = new Jugador(puerto, url, ID, nick, this);//this hace referencia a la ventana
                jugador.start();
            }

            btnConectarCliente.setVisible(false);
            txtNickJugador.setVisible(false);
            txtPuertoJugador.setVisible(false);
            txtURLJugador.setVisible(false);
            lblPuerto.setVisible(false);
            lblURL.setVisible(false);
            btnDesconectarCliente.setVisible(true);
            lblCabecera.setVisible(true);
            lblDado1.setVisible(true);
            lblDado2.setVisible(true);
            txtAreaPuntuacionesRecibidos.setVisible(true);
            txtPuntuacion.setText(jugador.getNickCliente());
            generaDado();
            compruebaTurno();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error datos " + e);
            jugador = null;
        }
    }//GEN-LAST:event_btnConectarClienteActionPerformed

    public int numAleatorio() {
        Random numAleatorio = new Random();
        int numero = numAleatorio.nextInt(6) + 1;
        return numero;
    }

    public void generaDado() {
        ImageIcon[] dado = new ImageIcon[7];
        for (int i = 1; i < dado.length; i++) {

            dado[i] = new ImageIcon("imagenes/pngs/" + i + ".png");
        }
        setDado(dado);
    }

    public void compruebaTurno() {

        if (jugador.isMiTurno()) {
            txtTurno.setText("TIRA LOS DADOS!!");
            btnTiraDados.setEnabled(true);
            InicioGif();
        } else {
            txtTurno.setText("NO ÉS TU TURNO");
            btnTiraDados.setEnabled(false);
        }
    }

    public int getResultadoAcumulado() {
        return resultadoAcumulado;
    }

    public void setResultadoAcumulado(int resultadoAcumulado) {
        this.resultadoAcumulado = resultadoAcumulado;
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (jugador != null) {
            jugador.enviarDatos_A_Servidor(3, "", 0);
            jugador.interrupt();
        }
        jugador = null;

        btnConectarCliente.setEnabled(true);
        btnDesconectarCliente.setEnabled(false);

        modeloLista.removeAllElements();
        txtAreaPuntuacionesRecibidos.setText("DESCONECTADO");
    }//GEN-LAST:event_formWindowClosed

    private void btnTiraDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiraDadosActionPerformed

        int numero;
        int resAux;
        ImageIcon diceResult;

        numero = numAleatorio();
        resAux = numero;

        calculaResultadoDado(numero);
        diceResult = getResultadoDado();
        lblDado1.setIcon(diceResult);

        numero = numAleatorio();
        resAux = resAux + numero;

        calculaResultadoDado(numero);
        diceResult = getResultadoDado();
        lblDado2.setIcon(diceResult);

        int acumulado = getResultadoAcumulado();
        setResultadoAcumulado(acumulado + resAux);

        String resultados = String.valueOf("Tirada Actúal: " + resAux + "\n "
                                        + "______________________"
                                        + "______________________");

        
        jugador.setPuntuacionTotal(getResultadoAcumulado());
        jugador.enviarPuntuaciones_A_Servidor(resultados);
        txtPuntuacion.setText(jugador.getNickCliente()+"  Acumulado: "+jugador.getPuntuacionTotal());


    }//GEN-LAST:event_btnTiraDadosActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("ID de la ventana: " + getID());
    }//GEN-LAST:event_formMouseClicked

    public void calculaResultadoDado(int numero) {
        ImageIcon[] dado = getDado();
        ImageIcon diceResult = dado[numero];
        setResultadoDado(diceResult);
    }

    public void recibePuntuacion(String mensaje) {
        //Se visualiza el mensaje en el txtArea de la ventana
        txtAreaPuntuacionesRecibidos.append(mensaje + "\n");
    }

    public void mostrarNuevoJugador(String nickCliente) {
        //Se visualiza el nombre del jugador en la Lista de jugadores de la ventana
        modeloLista.addElement(nickCliente.toUpperCase());
    }

    public void borrarJugador(int posicion) {
        //Se borra el nombre del jugador de la Lista de jugadores de la ventana
        modeloLista.remove(posicion);
    }

    public void InicioGif() {
        ImageIcon gif = getGifDado();
        lblDado1.setIcon(gif);
        lblDado2.setIcon(gif);
    }
    
    public void adios(){
        jugador.ventanaCliente.dispose();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConectarCliente;
    public javax.swing.JButton btnDesconectarCliente;
    public javax.swing.JButton btnTiraDados;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JLabel lblCabecera;
    public javax.swing.JLabel lblDado1;
    public javax.swing.JLabel lblDado2;
    public javax.swing.JLabel lblPuerto;
    public javax.swing.JLabel lblURL;
    public javax.swing.JList listaJugadores;
    public javax.swing.JTextArea txtAreaPuntuacionesRecibidos;
    public javax.swing.JTextField txtNickJugador;
    public javax.swing.JTextField txtPuertoJugador;
    public javax.swing.JTextField txtPuntuacion;
    public javax.swing.JTextField txtTurno;
    public javax.swing.JTextField txtURLJugador;
    // End of variables declaration//GEN-END:variables

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Jugador().setVisible(true);
        });
    }
}
/*
   Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {


                }
            });


         

 */
