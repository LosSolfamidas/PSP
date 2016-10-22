package Controlador;

import Modelo.Operaciones;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class HiloConexion extends Thread {

    private Socket socket;
    private DataInputStream d_i_s;
    private DataOutputStream d_o_s;
    private String nickCliente;

    

    public HiloConexion(Socket socket) {
        try {
            this.socket = socket;
            d_i_s = new DataInputStream(socket.getInputStream());
            d_o_s = new DataOutputStream(socket.getOutputStream());
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
                String mensaje = d_i_s.readUTF();

                switch (operacion) {
                    case Operaciones.enviarNickUNO:
                        nickCliente = mensaje;
                        GestorConexiones.getInstance().enviarCadena_A_Conectados(operacion, mensaje);
                        break;

                    case Operaciones.enviarMensajeDOS:
                        mensaje = "#" + nickCliente + "# : " + mensaje;
                        GestorConexiones.getInstance().enviarCadena_A_Conectados(operacion, mensaje);
                        break;

                    case Operaciones.desconectarTRES:
                        GestorConexiones.getInstance().desconecta(this);
                        break;

                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR1: " + e);
            }
        }

    }

    public String getNick() {
        return this.nickCliente;
    }

    public synchronized void enviarCadena(int operacion, String cadena) {
        try {
            d_o_s.writeInt(operacion);
            d_o_s.writeUTF(cadena);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR3: " + e);
            e.printStackTrace();
        }
    }
}
