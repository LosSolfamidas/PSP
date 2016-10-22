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
    private Rsa rsa;
    private BigInteger[] publicKey;

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
            rsa = MyRSA.getRsa();
            o_o_s.writeObject(rsa.getPublicKey());
            publicKey = (BigInteger[]) o_i_s.readObject();
//******************************************************************************************************

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

//************************************************************************************************************  
                /*
                         * Pasos para la transmision de datos por el socket con los datos firmados y cifrados:
                         * 1. Se lee del fichero hasta un maximo de TAMBUFFER bytes.
                         * 2. Se traspasa el buffer a otro array con el tama�o exacto+1 de bytes leidos, el primer componente siempre sera 1 y el resto a los bytes leidos.
                         * 3. Se pasa el nuevo array de bytes a BigInteger
                         * 4. Se cifra
                         * 5. Se le avisa al cliente de que debe seguir leyendo.
                         * 6. Se envia el BigInteger con datos por el socket.
                 */
                while ((numBytesBuffer = buffInStFichero.read(buffer)) > 0) {

                    // 2. Buffer + 1
                    byte[] temp = new byte[numBytesBuffer + 1];
                    for (int i = 0; i < temp.length; i++) {
                        temp[i] = (i == 0) ? 1 : buffer[i - 1];
                    }

                    // 3. Datos:
                    BigInteger datos = new BigInteger(temp);

                    // 4. Cifrado:
                    BigInteger cifrado = rsa.encrypt(datos, publicKey);

                    // 5 y 6. Enviar:
                    o_o_s.writeBoolean(true);
                    o_o_s.writeObject(cifrado);
                }
                o_o_s.writeBoolean(false); // Fin de lecturas del servidor.
                o_o_s.flush();
                buffInStFichero.close();
                JOptionPane.showMessageDialog(null, "Fichero enviado");
            }
//************************************************************************************************************

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

//*********************************************************************************************************************************************
                /*
                * Pasos para la transmision de datos por el socket con los datos firmados y cifrados:
                * 1. Se lee del socket un objeto tipo boolean que indica si seguir leyendo datos del socket.
                * 2. Se pasa el nuevo array de bytes a BigInteger
                * 3. Se descifra
                * 4. Se pasa a un array de bytes
                * 5. Se guarda en el fichero el array de bytes (obciando el componente 0 con valor 1).
                 */
                while (o_i_s.readBoolean()) {
                    // 2. Datos cifrados:
                    BigInteger cifrado = (BigInteger) o_i_s.readObject();

                    // 3. Descifrado:
                    BigInteger descifrado = rsa.decrypt(cifrado);

                    // 4. Arrar descifrado:
                    byte[] array = descifrado.toByteArray();

                    // 5. Guardado
                    buffOutStrFichero.write(array, 1, array.length - 1);
                }
//*********************************************************************************************************************************************

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
