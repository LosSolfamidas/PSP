package ModeloControlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Servidor extends Thread {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;

        File fichero;
        File[] arrayFicheros = null;
        File directorioFicheros = new File("FicherosServidor");
        ObjectInputStream o_i_s;
        ObjectOutputStream o_o_s;
        String nombreFichero = null;

        final int recargarListaUNO = 1;
        final int enviarDOS = 2;
        final int recibirTRES = 3;
        final int eliminarCUATRO = 4;
        final int tamBuffer = 32;

        try {
            // Se inicia el servicio con el puerto indicado
            serverSocket = new ServerSocket(puerto);
            JOptionPane.showMessageDialog(null, "Puerto: " + puerto + " Abierto");

            // El servicio no termina de ejecutarse hasta que se para forzosamente
            while (true) {
                // Cuando un cliente se conecte se abrira el Socket.
                socket = serverSocket.accept();

                //espera 1 segundo antes de iniciar
                socket.setSoLinger(true, 1);

                // Se crean los ObjectStreams para enviar y recibir datos a través del socket
                o_o_s = new ObjectOutputStream(socket.getOutputStream());
                o_i_s = new ObjectInputStream(socket.getInputStream());

                // Almacenamos los ficheros del servidor en un Array de Files
                if (directorioFicheros.isDirectory()) {
                    arrayFicheros = directorioFicheros.listFiles();
                }

                // Recogemos el int con la operación a realizar con el fichero
                int operacion = o_i_s.readInt();

                switch (operacion) {
                    case recargarListaUNO:
                        o_o_s.writeObject(getNombresFicheros(arrayFicheros));
                        o_o_s.flush();//enviar sin cerrar
                        break;

                    case enviarDOS:

                        // Recogemos el nombre del fichero
                        nombreFichero = (String) o_i_s.readObject();

                        // Buscamos el fichero por su nombre y lo recogemos
                        fichero = buscaFichero(arrayFicheros, nombreFichero);

                        // Si el fichero no se obtuvo se devuelve FALSE, en caso contrario TRUE 
                        o_o_s.writeBoolean((fichero != null));
                        //a continuación se envía
                        o_o_s.flush();

                        // Se procede a enviar el fichero
                        if (fichero != null) {
                            BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(fichero));
                            byte[] buffer = new byte[tamBuffer];
                            int numBytesLeidos;

                            while ((numBytesLeidos = buffInputStream.read(buffer)) > 0) {
                                o_o_s.write(buffer, 0, numBytesLeidos);
                            }
                            o_o_s.flush();
                            buffInputStream.close();
                        }
                        break;

                    case recibirTRES:

                        nombreFichero = (String) o_i_s.readObject();
                        fichero = new File(directorioFicheros, nombreFichero);

                        BufferedOutputStream buffOutStream = new BufferedOutputStream(new FileOutputStream(fichero));
                        byte[] buffer = new byte[tamBuffer];
                        int numBytesBuffer;

                        while ((numBytesBuffer = o_i_s.read(buffer)) > 0) {
                            buffOutStream.write(buffer, 0, numBytesBuffer);
                        }
                        buffOutStream.close();

                        break;

                    case eliminarCUATRO:

                        // Recogemos el nombre del fichero
                        nombreFichero = (String) o_i_s.readObject();

                        // Buscamos el fichero por su nombre y lo recogemos
                        fichero = buscaFichero(arrayFicheros, nombreFichero);
                        // Si el fichero no se obtuvo se devuelve FALSE, en caso contrario TRUE
                        o_o_s.writeObject((fichero != null));
                        //a continuación se envía
                        o_o_s.flush();

                        // Se procede a eliminar el fichero
                        if (fichero != null) {
                            fichero.delete();
                        } else {
                            JOptionPane.showMessageDialog(null, "Fichero no existe");
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "La opción \"" + operacion + "\" no existe");
                        break;
                }

                socket.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e);
            JOptionPane.showMessageDialog(null, "El puerto: " + puerto + " ya está en uso");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * Método con el que a partir de un array de ficheros creamos y devolvemos un array con los nombres de los ficheros.
     */
    private static String[] getNombresFicheros(File[] ficheros) {
        String[] nombresFicheros = new String[ficheros.length];

        int posicion = 0;
        for (File fichero : ficheros) {
            nombresFicheros[posicion] = fichero.getName();
            posicion++;
        }

        return nombresFicheros;
    }

    /*
     * Método que busca por nombre Y devuelve el fichero solicitado buscando en array que contiene todos los ficheros.
     */
    private static File buscaFichero(File[] ficheros, String ficheroSolicitado) {
        for (File fichero : ficheros) {
            if (fichero.getName().equals(ficheroSolicitado)) {
                return fichero;
            }
        }

        return null;
    }
}
