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

public class ServidorHilo extends Thread {

    private Socket socket;
    private ObjectInputStream o_i_s;
    private ObjectOutputStream o_o_s;
    private File directorioFicheros = new File("FicherosServidor");
    private File[] misFicheros = null;
    private String nombreFichero;
    private File fichero;
    private Rsa rsa;
    private BigInteger[] publicKey;

    private final int recargarListaUNO = 1;
    private final int enviarDOS = 2;
    private final int recibirTRES = 3;
    private final int eliminarCUATRO = 4;
    private final int tamBuffer = 32;

    public ServidorHilo(Socket socketServidor) {
        this.socket = socketServidor;

        try {
            // Se crean los ObjectStreams para enviar y recibir datos a través del socket
            o_i_s = new ObjectInputStream(socketServidor.getInputStream());
            o_o_s = new ObjectOutputStream(socketServidor.getOutputStream());



//******************************************************************************************************           
            // Se intercambian las claves publicas RSA:
            rsa = MyRSA.getRsa();
            publicKey = (BigInteger[]) o_i_s.readObject();
            o_o_s.writeObject(rsa.getPublicKey());
//******************************************************************************************************           
    



        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
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
                        BufferedInputStream buffInStrFichero = new BufferedInputStream(new FileInputStream(fichero));
                        byte[] buffer = new byte[tamBuffer];
                        int numBytesBuffer;




//******************************************************************************************************************************
                        /*
                         * Pasos para la transmision de datos por el socket con los datos firmados y cifrados:
                         * 1. Se lee del fichero hasta un maximo de TAMBUFFER bytes.
                         * 2. Se traspasa el buffer a otro array con el tama�o exacto+1 de bytes leidos, el primer componente siempre sera 1 y el resto a los bytes leidos.
                         * 3. Se pasa el nuevo array de bytes a BigInteger
                         * 4. Se cifra
                         * 5. Se le avisa al cliente de que debe seguir leyendo.
                         * 6. Se envia el BigInteger con datos por el socket.
                         */
                        while ((numBytesBuffer = buffInStrFichero.read(buffer)) > 0) {
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
                        
                        
                        o_o_s.writeBoolean(false); // Fin de lecturas del cliente.
//******************************************************************************************************************************




                            o_o_s.flush();
                            buffInStrFichero.close();
                    }
                    break;

                case recibirTRES:
                    nombreFichero = (String) o_i_s.readObject();
                    fichero = new File(directorioFicheros, nombreFichero);

                    BufferedOutputStream buffOutStrFichero = new BufferedOutputStream(new FileOutputStream(fichero));
                    
                    
                    
                    
//****************************************************************************************************************************
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
//****************************************************************************************************************************

                    
                    
                    
                   
                    buffOutStrFichero.close();
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
