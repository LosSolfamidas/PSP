package ModeloControlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Cliente {

    private Socket socketCliente;
    private String URL;
    private int puerto;
    private ObjectInputStream o_i_s;
    private ObjectOutputStream o_o_s;
    private File directorioFicheros;
    //**************************************************************************
    private Rsa rsa;
    private BigInteger[] publicKey;
//**************************************************************************

//**************************************************************************
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private String simetricKey;
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private final int recargarListaUNO = 1;
    private final int recibirDos = 2;
    private final int enviarTres = 3;
    private final int eliminarCuatro = 4;
    private final int tamBuffer = 32;

    public Cliente(String URL, int puerto, File directorioFicheros) {
        this.URL = URL;
        this.puerto = puerto;
        this.directorioFicheros = directorioFicheros;
    }

    /**
     * Método que abre un socket y los stream de comunicación.
     */
    private void conectarseAlServidor() {
        try {
            socketCliente = new Socket(URL, puerto);
            o_o_s = new ObjectOutputStream(socketCliente.getOutputStream());
            o_i_s = new ObjectInputStream(socketCliente.getInputStream());

//******************************************************************************************************
            // Se intercambian las claves publicas RSA:
            rsa = new Rsa(512);
            o_o_s.writeObject(rsa.getPublicKey());
            publicKey = (BigInteger[]) o_i_s.readObject();
//******************************************************************************************************

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            // SE INTERCAMBIA LA CLAVE SIMETRICA CIFRADA:
            // Se lee del socket la clave simetrica cifrada del servidor: 
            BigInteger cifrado = (BigInteger) o_i_s.readObject();

            // Se descifra y desfirma la clave:
            BigInteger descifrado = rsa.decrypt(cifrado);
            BigInteger desfirmado = rsa.decrypt(descifrado, publicKey);

            // Se pasa a bytes:
            byte[] temp = desfirmado.toByteArray();

            // Array Char to String:
            simetricKey = new String(temp);
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexión" + ex);
            //ex.printStackTrace();
        }

    }

    /**
     * Método que cierra el socket y los stream.
     */
    private void desconectarseDelServidor() {
        try {
            socketCliente.close();
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
                    BufferedInputStream buffInStFichero = new BufferedInputStream(new FileInputStream(fichero))) {
                byte[] buffer = new byte[tamBuffer];
                int numBytesBuffer;





//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        
                /*
             * Igual que el servidor.
                 */
                while ((numBytesBuffer = buffInStFichero.read(buffer)) > 0) {
                    // 2. Buffer exacto:
                    byte[] temp = new byte[numBytesBuffer];
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = buffer[i];
                    }

                    // 3. Se alteran los bytes:
                    temp = Cesar.cypher(temp, simetricKey);

                    // 4 y 5 Se envia: 
                    o_o_s.write(temp);
                }
                o_o_s.flush();
                buffInStFichero.close();
                JOptionPane.showMessageDialog(null, "Fichero enviado");
            }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar el fichero del servidor" + e);
            //e.printStackTrace();
        } finally {

            desconectarseDelServidor();
        }

    }

    /*Método que solicita un fichero al servidor para su descarga:*/
    public void recibirFichero(String nombreFichero) throws ClassNotFoundException {
        conectarseAlServidor();//Conectamos al servidor

        try {

            o_o_s.writeInt(recibirDos);//El cliente envía un 2 para indicar que quiere un fichero.
            o_o_s.flush();

            // Se le envía el nombre del fichero
            o_o_s.writeObject(nombreFichero);
            o_o_s.flush();

            // Si el fichero no existe se avisa de ello, si existe se descarga
            if (!o_i_s.readBoolean()) {//si NO existe ese fichero devuelve un 0.
                JOptionPane.showMessageDialog(null, "El fichero solicitado no existe");

            } else {//Si existe devuelve un 1 y comienza la transmision:

                // Se recupera el fichero como un BufferedOutputStream:
                BufferedOutputStream buffOutStrFichero = new BufferedOutputStream(new FileOutputStream(new File(directorioFicheros, nombreFichero)));





//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
               byte[] buffer = new byte[tamBuffer];
                int numBytesBuffer;

                /*
                * Pasos para la transmision de datos por el socket con los datos alterados con la clave simetrica:
                * 1. Se leen los NUMBYTES exactos enviados por el servidor
                * 2. Se des-alteran los bytes con la clave simetrica.
                * 3. Se guardan los bytes en el fichero.
                 */
                while ((numBytesBuffer = o_i_s.read(buffer)) > 0) {
                    // 2. Se des-alteran los bytes:
//                    int j = 0;
//                    for (int i = 0; i < buffer.length; i++) {
//                        j = (j < simetricKey.length() - 1) ? j + 1 : 0;
//                        byte letra = (byte) simetricKey.charAt(j);
//                        buffer[i] = (byte) ((buffer[i] - letra) % 256);
//                    }
                    buffer = Cesar.decypher(buffer, simetricKey);

                    // 3. Guardado
                    buffOutStrFichero.write(buffer, 0, numBytesBuffer);
                }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@






                buffOutStrFichero.close();
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
