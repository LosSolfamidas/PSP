package ModeloControlador;


import java.math.BigInteger;
import java.security.SecureRandom;

public class Rsa {

    private BigInteger claveCompartida; // Clave compartida
    private BigInteger calvePrivada; // Clave privada (no se comparte)
    private BigInteger clavePublica; // Clave publica para la otra parte
    private final int Publica = 0, Privada = 0, Compartida = 1;

    /**
     * Constructor que genera las claves pública y privada de tamaño bitlen
     * indicado.
     *
     * @param bitlen
     */
    public Rsa(int bitlen) {
        // Calculamos los dos numeros primos p y q:
        SecureRandom secureRandom = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, secureRandom);
        BigInteger q = new BigInteger(bitlen / 2, 100, secureRandom);

        //n = p*q, v= (p-1)*(q-1)
        claveCompartida = p.multiply(q);
        BigInteger v = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        // Calculamos k como el numero impar mas peque�o relativamente primo con v
        clavePublica = new BigInteger("3");
        while (v.gcd(clavePublica).intValue() > 1) {
            clavePublica = clavePublica.add(new BigInteger("2"));
        }

        // Calculamos d de modo que (d * k) MOD V = 1
        calvePrivada = clavePublica.modInverse(v);
    }

    /**
     * Pasa el BigInteger normal a cifrado usando la clave publica.
     *
     * @param message
     * @return
     */
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(clavePublica, claveCompartida);
    }

    /**
     * Pasa el BigInteger normal a cifrado usando la clave publica recibida.
     *
     * @param message
     * @param key
     * @return
     */
    public BigInteger encrypt(BigInteger message, BigInteger[] key) {
        return message.modPow(key[Publica], key[Compartida]);
    }

    /**
     * Pasa el BigInteger de cifrado a normal usando la clave privada.
     *
     * @param message
     * @return
     */
    public BigInteger decrypt(BigInteger message) {
        return message.modPow(calvePrivada, claveCompartida);
    }

    /**
     * Pasa el BigInteger de cifrado a normal usando la clave privada recibida.
     *
     * @param message
     * @return
     */
    public BigInteger decrypt(BigInteger message, BigInteger[] key) {
        return message.modPow(key[Privada], key[Compartida]);
    }

    /**
     * Devuelve la clave publica.
     *
     * @return
     */
    public BigInteger[] getPublicKey() {
        return new BigInteger[]{clavePublica, claveCompartida};
    }

    /**
     * Devuelve la clave privada.
     *
     * @return
     */
    public BigInteger[] getPrivateKey() {
        return new BigInteger[]{calvePrivada, claveCompartida};
    }

}
