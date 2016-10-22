package Modelo;

import Vista.GUI_Cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente extends Thread {

    private int puerto;
    private String URL;
    private Socket socketCliente;
    private boolean estaConectado;
    private GUI_Cliente ventanaCliente;
    private String nickCliente;
    

    public Cliente(int puerto, String url, String nick, GUI_Cliente ventanaCliente) {
        this.puerto = puerto;
        this.URL = url;
        this.ventanaCliente = ventanaCliente;
        this.nickCliente = nick;
    }

    @Override
    public void run() {
        try {
            socketCliente = new Socket(URL, puerto);
            DataInputStream d_i_s = new DataInputStream(socketCliente.getInputStream());
            enviarCadena(Operaciones.enviarNickUNO, nickCliente);
            estaConectado = true;
            while (estaConectado) {
                int operacion = d_i_s.readInt();
                String mensaje = d_i_s.readUTF();
                switch (operacion) {
                    
                    case Operaciones.recibirNickUNO:
                        ventanaCliente.mostrarNuevoCliente(mensaje);
                        break;
                        
                    case Operaciones.recibirMensajeDOS:
                        ventanaCliente.recibeMensage(mensaje);
                        break;
                   
                    case Operaciones.desconectarTRES:
                        try {
                            int posicionCliente = Integer.parseInt(mensaje);
                            ventanaCliente.borrarCliente(posicionCliente);
                        } catch (Exception e) {
                        }
                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventanaCliente, "No se pudo establecer la conexión");
        }
    }

    public synchronized void enviarMensaje(String mensaje) {
        enviarCadena(2, mensaje);
    }

    public synchronized void enviarCadena(int operacion, String cadena) {
        try {
            DataOutputStream d_o_s = new DataOutputStream(socketCliente.getOutputStream());
            d_o_s.writeInt(operacion);
            d_o_s.writeUTF(cadena);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventanaCliente, "No se pudo enviar el mensaje");
        }

    }

}
