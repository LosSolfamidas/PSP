package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;
import Modelo.Comandos;

public class HiloConexion extends Thread {

    private Socket socket;
    private DataInputStream d_i_s;
    private DataOutputStream d_o_s;
    private String nickCliente;
    private Integer idConexion;
    private boolean teToca;
    private int ronda;
    private int puntuacionTotal;
    private boolean fin;

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public Integer getIdConexion() {
        return idConexion;
    }

    public synchronized void setIdConexion(Integer idConexion) {
        this.idConexion = idConexion;
    }

    public synchronized boolean isTeToca() {
        return teToca;
    }

    

    public int getRonda() {
        return ronda;
    }

    public void sumaRonda() {
        this.ronda++;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public HiloConexion(){}
    public HiloConexion(Socket socket) {
        try {
            this.socket = socket;
            d_i_s = new DataInputStream(socket.getInputStream());
            d_o_s = new DataOutputStream(socket.getOutputStream());
            this.ronda = 0;

            start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR SERVIDOR " + e);
        }

    }

    @Override
    public void run() {

        while (true) {

            try {
                int operacion = d_i_s.readInt();
                String cadena = d_i_s.readUTF().toUpperCase();
                int puntuacion = d_i_s.readInt();

                switch (operacion) {
                    case Comandos.enviarNickUNO:
                        nickCliente = cadena;
                        GestorConexiones.getInstance().enviarNick_A_Conectados(cadena);
                        break;
                    case Comandos.enviarPuntuacionDOS:

                        cadena = nickCliente +"===>"+ cadena;
                        GestorConexiones.getInstance().enviarCadena_A_Conectados(cadena);
                        this.setTeToca(false);
                        GestorConexiones.getInstance().pasaRondaTurno(this);
                        this.setPuntuacionTotal(puntuacion);
                        break;
                    case Comandos.desconectarTRES:
                        GestorConexiones.getInstance().desconecta(this);
                        break;

                    case Comandos.enviarfinCINCO:
                        GestorConexiones.getInstance().fin();
                        break;

                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR OPERACIÓN: " + e);
                e.printStackTrace();
            }

        }

    }

    public synchronized String getNick() {
        return this.nickCliente;
    }

    public synchronized void enviarPuntuacion_A_Cliente(String cadena) {
        try {
            d_o_s.writeInt(Comandos.enviarPuntuacionDOS);
            d_o_s.writeUTF(cadena);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR2: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void enviarNick_A_Cliente(String nick) {
        try {
            d_o_s.writeInt(Comandos.enviarNickUNO);
            d_o_s.writeUTF(nick);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR1: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void setTeToca(boolean teToca) {
        try {
            d_o_s.writeInt(Comandos.enviarTurnoCUATRO);
            d_o_s.writeBoolean(teToca);
            this.teToca = teToca;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public synchronized void enviarDesconectado_A_Cliente(int idCliente) {
        try {
            d_o_s.writeInt(Comandos.desconectarTRES);
            d_o_s.writeInt(idCliente);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public synchronized void acabarTodo() {
        try {
            d_o_s.writeInt(Comandos.recibirfinCINCO);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
