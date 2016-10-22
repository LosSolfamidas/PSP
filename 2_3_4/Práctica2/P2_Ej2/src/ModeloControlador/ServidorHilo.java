package ModeloControlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ServidorHilo extends Thread {

    private Socket socket;
    private ObjectInputStream o_i_s;
    private ObjectOutputStream o_o_s;
    private File directorioFicheros = new File("FicherosServidor");
    private File[] misFicheros = null;
    private String nombreFichero;
    private File fichero;

    private final int recargarListaUNO = 1;
    private final int enviarDOS = 2;
    private final int recibirTRES = 3;
    private final int eliminarCUATRO = 4;
    private final int tamBuffer = 32;

    public ServidorHilo(Socket miSocketServidor) {
        this.socket = miSocketServidor;

        try {
            // Se crean los ObjectStreams para enviar y recibir datos a través del socket
            o_i_s = new ObjectInputStream(miSocketServidor.getInputStream());
            o_o_s = new ObjectOutputStream(miSocketServidor.getOutputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }

    /**
     * Iniciamos el Hilo.
     */
    @Override
    public void run() {
        // Comienza el servicio
        try {
            // Almacenamos los ficheros del servidor en un Array de Files
            if (directorioFicheros.isDirectory()) {
                misFicheros = directorioFicheros.listFiles();
            }

            // Recogemos el int con la operación a realizar con el fichero
            int operacion = o_i_s.readInt();

            switch (operacion) {
                case recargarListaUNO:
                    o_o_s.writeObject(getNombresFicheros(misFicheros));
                    o_o_s.flush();
                    break;

                case enviarDOS:
                    // Recogemos el nombre del fichero
                    nombreFichero = (String) o_i_s.readObject();

                    // Buscamos el fichero por su nombre y lo recogemos
                    fichero = buscaFichero(misFicheros, nombreFichero);

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
                    fichero = buscaFichero(misFicheros, nombreFichero);
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
                    JOptionPane.showMessageDialog(null, "La operación \"" + operacion + "\" no existe");
                    break;
            }

        } catch (ClassNotFoundException | IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e);

            }
        }

    }

    /*
     * Método con el que a partir de un array de ficheros creamos y devolvemos un array con los nombres de los ficheros.
     */
    private static String[] getNombresFicheros(File[] ficheros) {
        String[] nombresFicheros = new String[ficheros.length];

        int index = 0;
        for (File fichero : ficheros) {
            nombresFicheros[index] = fichero.getName();
            index++;
        }

        return nombresFicheros;
    }

    /*
     * Método que devuelve el fichero solicitado buscando en array que contiene todos los ficheros.
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
