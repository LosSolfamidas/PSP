package Modelo;

import Controlador.GestorConexiones;
import Controlador.HiloConexion;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Servidor extends Thread {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            // Se inicia el servicio con el puerto indicado
            serverSocket = new ServerSocket(puerto);
            JOptionPane.showMessageDialog(null, "Puerto: " + puerto + " Abierto");

            // El servicio nunca termina de ejecutarse hasta que se para forzosamente
            while (true) {
                // Cada vez que un cliente se conecte se creara un nuevo hilo para manejar su petición
                Socket socket = serverSocket.accept();
                //espera 3 segundos antes de iniciar
                socket.setSoLinger(true, 3);
                GestorConexiones.getInstance().conectaNuevoCliente(new HiloConexion(socket));
            }
        } catch (IOException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "ERROR SERVIDOR: " + e);
        }
    }

}
