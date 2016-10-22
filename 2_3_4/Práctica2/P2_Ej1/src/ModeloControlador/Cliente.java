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

public class Cliente {

    private Socket socket;
    private String IP;
    private int puerto;
    private ObjectInputStream o_i_s;
    private ObjectOutputStream o_o_s;
    private File directorioFicheros;

    private final int recargarListaUNO = 1;
    private final int recibirDos = 2;
    private final int enviarTres = 3;
    private final int eliminarCuatro = 4;
    private final int tamBuffer = 32;

    public Cliente(String IP, int puerto, File directorioFicheros) {
        this.IP = IP;
        this.puerto = puerto;
        this.directorioFicheros = directorioFicheros;
    }

    /**
     * Método que abre un socket y los stream de comunicación.
     */
    private void conectarseAlServidor() {
        try {
            socket = new Socket(IP, puerto);
            o_o_s = new ObjectOutputStream(socket.getOutputStream());
            o_i_s = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
        }
    }

    /**
     * Método que cierra el socket y los stream.
     */
    private void desconectarseDelServidor() {
        try {
            socket.close();
            o_i_s.close();
            o_o_s.close();
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, "Error al intentar cerrar la conexión");
        }
    }

    /*
     * Método que solicita al servidor que le envíe los ficheros que tienen almacenados. 
     */
    public String[] cargarListaFicherosServidor() {
        conectarseAlServidor();
        String[] nombreFicheros = new String[0];

        try {
            o_o_s.writeInt(recargarListaUNO);//envía un 1 para indicarle al servidor que está solicitando los ficheros.

            o_o_s.flush();
            nombreFicheros = (String[]) o_i_s.readObject();//el cliente recibe un array de strings de 0 o más strings.

            if (nombreFicheros.length == 0) {
                JOptionPane.showMessageDialog(null, "No hay ficheros en el servidor");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en el objeto recibido");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los ficheros del servidor");
        } finally {
            desconectarseDelServidor();
        }
        return nombreFicheros;
    }

    /**
     * Método que envía ficheros al servidor.
     *
     * @param fichero
     */
    public void enviarFichero(File fichero) {
        conectarseAlServidor();

        try {
            // Se ordena el envío:
            o_o_s.writeInt(enviarTres);
            o_o_s.flush();

            // Se le envía el nombre del fichero
            o_o_s.writeObject(fichero.getName());
            o_o_s.flush();

            try ( // Se envía el fichero:
                    BufferedInputStream intFichero = new BufferedInputStream(new FileInputStream(fichero))) {
                byte[] buffer = new byte[tamBuffer];
                int numBytesBuffer;
                while ((numBytesBuffer = intFichero.read(buffer)) > 0) {
                    o_o_s.write(buffer, 0, numBytesBuffer);
                }
                o_o_s.flush();
                JOptionPane.showMessageDialog(null, "Fichero enviado");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el fichero del servidor");
        } finally {
            desconectarseDelServidor();
        }

    }

    /*Método que solicita un fichero al servidor para su descarga:*/
    public void recibirFichero(String nombreFichero) {
        conectarseAlServidor();//Conectamos al servidor

        try {

            o_o_s.writeInt(recibirDos);//El cliente envía un 2 para indicar que quiere un fichero.
            o_o_s.flush();

            // Se le envia el nombre del fichero
            o_o_s.writeObject(nombreFichero);//El cliente envía el nombre del fichero.
            o_o_s.flush();

            // Si el fichero no existe se avisa de ello, si existe se descarga:
            if (!o_i_s.readBoolean()) {//si NO existe ese fichero devuelve un 0.
                JOptionPane.showMessageDialog(null, "El fichero solicitado no existe");

            } else {//Si existe devuelve un 1 y comienza la transmision:

                // Se recupera el fichero como un BufferedOutputStream:
                BufferedOutputStream outFichero = new BufferedOutputStream(new FileOutputStream(new File(directorioFicheros, nombreFichero)));

                // Se crea un array de bytes para que funcione como buffer intermedio,
                //así en vez de leer del socket y escribir en el fichero de byte en byte se hace de 1024 bytes en 1024 bytes.
                byte[] buffer = new byte[tamBuffer];

                // numBytesLeidos se usa para controlar cuantos bytes se leen del socket y cuantos se han de escribir en el fichero.
                //Normalmente en la última lectura nunca tendremos 1024 bytes exactos para transferir.
                int numBytesLeidos;

                while ((numBytesLeidos = o_i_s.read(buffer)) > 0) {
                    outFichero.write(buffer, 0, numBytesLeidos);
                }
                outFichero.close();
                JOptionPane.showMessageDialog(null, "Fichero recibido");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el fichero del servidor");
        } finally {
            desconectarseDelServidor();//al acabar nos desconectamos del servidor.
        }

    }

    /**
     * El siguiente método se encarga de borrar el fichero del servidor.
     *
     * @param nombreFichero
     */
    public void eliminarFicheroServidor(String nombreFichero) {
        conectarseAlServidor();

        try {
            // Se indica que se quiere eliminar un fichero del servidor:
            o_o_s.writeInt(eliminarCuatro);//Primero se envía al servidor un 4 para indicar la operación.
            o_o_s.flush();

            // Se le envía el nombre del fichero a borrar
            o_o_s.writeObject(nombreFichero);
            o_o_s.flush();

            //  Para finalizar se recibe un booleando con el resultado de la operación.
            if ((Boolean) o_i_s.readObject()) {
                JOptionPane.showMessageDialog(null, "Fichero borrado en el Servidor");
            } else {
                JOptionPane.showMessageDialog(null, "El fichero no existe");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error en el objeto recibido");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el fichero del servidor");
        } finally {
            desconectarseDelServidor();
        }

    }

}
