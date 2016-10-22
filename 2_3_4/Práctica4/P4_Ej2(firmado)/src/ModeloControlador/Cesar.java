package ModeloControlador;


public class Cesar {
    /**
     * Cyphers a byte array with the given key
     * @param plain_text
     * @param key
     * @return 
     */
    public static byte[] cypher(byte[] plain_text, String key){
        byte[] tmp = new byte[plain_text.length];
        
        int pos = 0;
        while (pos < plain_text.length){
            for (int i = 0; i < key.length() -1 ; i++){
                tmp[pos] = (byte) (plain_text[pos] + key.charAt(i) % 256);
                pos ++;
                if (pos >= plain_text.length) return tmp;
            }
        }
        return tmp;
    }
    
    /**
     * Decyphers a byte array with the given key
     * @param cyphertext
     * @param key
     * @return 
     */
    public static byte[] decypher(byte[] cyphertext, String key){
        byte[] tmp = new byte[cyphertext.length];
        
        int pos = 0;
        while (pos < cyphertext.length){
            for (int i = 0; i < key.length() - 1; i++){
                tmp[pos] = (byte) (cyphertext[pos] - key.charAt(i) % 256);
                pos ++;
                if (pos >= cyphertext.length) return tmp;
            }
        }
        return tmp;
    }
}
