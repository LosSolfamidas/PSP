package Modelo;

import Vista.GUI_Jugador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Jugador extends Thread {

    private int puerto;
    private String URL;
    private Socket socket;
    public GUI_Jugador ventanaCliente;

    private int ID;
    private String nickCliente;
    private int puntuacionTotal;
    private boolean miTurno;

    /*Constructor*/
    public Jugador(int puerto, String url, int ID, String nick, GUI_Jugador ventanaCliente) {
        this.puerto = puerto;
        this.URL = url;
        this.ID = ID;
        this.nickCliente = nick;
        this.ventanaCliente = ventanaCliente;
        this.puntuacionTotal = 0;
        this.miTurno = false;
    }

    public GUI_Jugador getVentanaCliente() {
        return ventanaCliente;
    }

    public void setVentanaCliente(GUI_Jugador ventanaCliente) {
        this.ventanaCliente = ventanaCliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNickCliente() {
        return nickCliente;
    }

    public void setNickCliente(String nickCliente) {
        this.nickCliente = nickCliente;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public boolean isMiTurno() {
        return miTurno;
    }



    /*HILO*/
    @Override
    public void run() {
        try {
            socket = new Socket(URL, puerto);
            DataInputStream d_i_s = new DataInputStream(socket.getInputStream());
            enviarDatos_A_Servidor(Comandos.recibirNickUNO, nickCliente, puntuacionTotal);

            boolean start = true;//Debería poner el turno
            while (start) {
                int operacion = d_i_s.readInt();
                String puntuacion;
                switch (operacion) {

                    case Comandos.recibirNickUNO:
                        puntuacion = d_i_s.readUTF();
                        ventanaCliente.mostrarNuevoJugador(puntuacion);
                        break;

                    case Comandos.recibirPuntuacionDOS:
                        puntuacion = d_i_s.readUTF();
                        ventanaCliente.recibePuntuacion(puntuacion);
                        break;

                    case Comandos.desconectarTRES:
                        try {
                            int posicionCliente = d_i_s.readInt();
                            ventanaCliente.borrarJugador(posicionCliente);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case Comandos.recibirTurnoCUATRO:
                        this.miTurno = d_i_s.readBoolean();
                        ventanaCliente.compruebaTurno();
                        break;
                    
                    case Comandos.recibirfinCINCO:
                        ventanaCliente.adios();
                        break;
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(ventanaCliente, "No se pudo establecer la conexión");
        }
    }

    public synchronized void enviarPuntuaciones_A_Servidor(String mensaje) {
        enviarDatos_A_Servidor(Comandos.enviarPuntuacionDOS, mensaje, puntuacionTotal);
    }
    

    public synchronized void enviarDatos_A_Servidor(int operacion, String cadena, int puntuacion) {
        try {
            DataOutputStream d_o_s = new DataOutputStream(socket.getOutputStream());
            d_o_s.writeInt(operacion);
            d_o_s.writeUTF(cadena);
            d_o_s.writeInt(puntuacion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventanaCliente, "No se pudo enviar el mensaje");
        }
    }
    
    
}
