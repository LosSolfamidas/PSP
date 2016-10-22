package ModeloControlador;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;


public class Servidor extends Thread
{
    
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }
    
     @Override
     public void run() {
         ServerSocket serverSocket;
         
        
        try
        {
            // Se inicia el servicio con el puerto indicado
            serverSocket = new ServerSocket(puerto);
            JOptionPane.showMessageDialog(null, "Puerto: " + puerto + " Abierto");

            // El servicio nunca termina de ejecutarse hasta que se para forzosamente
            while( true )
            {
                // Cada vez que un cliente se conecte se creara un nuevo hilo para manejar su petición
                Socket socket = serverSocket.accept();
                //espera 1 segundo antes de iniciar
                socket.setSoLinger(true, 1);
                new ServidorHilo(socket).start();
            }
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog(null, "ERROR SERVIDOR: " + e);
        }
     }
}
