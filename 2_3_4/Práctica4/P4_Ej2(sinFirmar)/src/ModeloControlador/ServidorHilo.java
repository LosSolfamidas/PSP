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
import java.util.Random;
import javax.swing.JOptionPane;

public class ServidorHilo extends Thread {

    private Socket socket;
    private ObjectInputStream o_i_s;
    private ObjectOutputStream o_o_s;
    private File directorioFicheros = new File("FicherosServidor");
    private File[] misFicheros = null;
    private String nombreFichero;
    private File fichero;
    private Tiempo tiempo;
 
//**************************************************************************
    private Rsa rsa;
    private BigInteger[] publicKey;
//**************************************************************************
    private String simetricKey;
   //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

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
            rsa = new Rsa(512);
            publicKey = (BigInteger[]) o_i_s.readObject();
            o_o_s.writeObject(rsa.getPublicKey());
//******************************************************************************************************           



//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            // Se elige una clave simétrica para la transacción:
            simetricKey = getSimetricKey(16);
            System.out.println("SERVIDOR: " + simetricKey);

            // Se firma y cifra:
            tiempo = new Tiempo();    // Mido el tiempo que se tarda en cifrar
            BigInteger datos = new BigInteger(simetricKey.getBytes());
            
            
            
            
            BigInteger cifrado = rsa.encrypt(datos, publicKey);
            tiempo.fin();    // Fin del cifrado
            
            System.out.println("Se ha tardado " + tiempo.getTiempo() + "ms en cifrar los datos");
            // Se envia la clave:
            o_o_s.writeObject(cifrado);
            printStats(cifrado.toByteArray().length);
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
        }
    }
    
    private void printStats(int bytesSent){
        tiempo.fin();
        System.out.println("Se han enviado: " + bytesSent + " bytes en un total de " + tiempo.getTiempo() + "ms");
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




//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                        /*
                         * Pasos para la transmision de datos por el socket con los datos alterados con la clave simetrica:
                         * 1. Se el NUMBYTES del fichero
                         * 2. Se pasa a un array del tama�o del buffer.
                         * 3. Se alteran los bytes mediante la clave simetrica
                         * 4. Se envian los bytes alterados por el socket.
                         * 5. Se hace un flush() del socket
                         */
                        int numBytes = 0;
                        tiempo.reiniciar();
                        while ((numBytesBuffer = buffInStrFichero.read(buffer)) > 0) {
                            // 2. Buffer:
                            byte[] temp = new byte[numBytesBuffer];
                            for (int i = 0; i < temp.length; i++) {
                                temp[i] = buffer[i];
                            }

                            // 3. Se alteran los bytes:
                            temp = Cesar.cypher(temp, simetricKey);
                            
                            // 4 y 5 Se envia: 
                            o_o_s.write(temp);
                            numBytes += temp.length;
                        }
                        printStats(numBytes);
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@




                            o_o_s.flush();
                            buffInStrFichero.close();
                    }
                    break;

                case recibirTRES:
                    nombreFichero = (String) o_i_s.readObject();
                    fichero = new File(directorioFicheros, nombreFichero);

                    BufferedOutputStream buffOutStrFichero = new BufferedOutputStream(new FileOutputStream(fichero));
                    
                    
                    
                    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    byte[] buffer = new byte[tamBuffer];
                    int numBytesBuffer;

                    /*
                    * Igual que el cliente.
                     */
                    while ((numBytesBuffer = o_i_s.read(buffer)) > 0) {
                        // 2. Se des-alteran los bytes:
                        buffer = Cesar.decypher(buffer, simetricKey);

                        // 3. Guardado
                        buffOutStrFichero.write(buffer, 0, numBytesBuffer);
                    }
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                    
                    
                    
                   
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
 
    


//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  
    /**

     * @return
     */
    private String getSimetricKey(int key_size) {
        char key[] = new char[key_size];
        Random r = new Random();
        int n = 126 - 32 + 1;
        for (int i = 0; i < key.length -1; i++){
            key[i] = (char) (32 + r.nextInt(n));
        }
        
        return String.copyValueOf(key);
    }
    
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
}
