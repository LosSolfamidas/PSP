
package ModeloControlador;


public class MyRSA {
    private static Rsa rsa;

    public static Rsa getRsa() {
        if (rsa == null){
            rsa = new Rsa(512);
        }
        
        return rsa;
    }
}
