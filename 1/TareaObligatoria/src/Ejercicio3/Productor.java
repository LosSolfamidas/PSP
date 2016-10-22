package Ejercicio3;

import java.util.Random;

public class Productor extends Thread {

    private Random random = new Random();
    private Buffer buffer;
    private int limiteFabricacion;
    private MiConsole MC = new MiConsole();//Para el color del texto que muestra la consola de Netbeans

    public Productor(Buffer buffer, int limiteFabricacion) {//Le pasaremos por parámetro el buffer a usar y el número de productos a fabricar (datos para añadir).

        this.buffer = buffer;
        this.limiteFabricacion = limiteFabricacion;

    }

    @Override
    public void run() {
        
        for (int i = 0; i < this.limiteFabricacion; i++) {
            
            String texto;//almacenará los textos que imprimirá en consola.
            
            /*El productor genera un número(idProducto/idDato)*/
            int aux = this.random.nextInt(100);//100 tipos de productos (por ejemplo).

            

            try {
                this.buffer.ponerDato(aux);//añade el dato (producto) al buffer.

                /*TODO ESTE CHURRO ES PARA LOS MENSAJES POR CONSOLA 
                 (El id del Productor(proceso) y el id del producto(dato) que ha fabricado(introducido en buffer))*/
                
                //SI el Productor es menor de 10
                if (i < 10 && aux < 10) {
                    texto = "El Productor 000" + (i + 1) + " Ha fabricado el Ítem nº: 100" + aux;
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i < 10 && aux >= 10 && aux < 100) {
                    texto = ("El Productor 000" + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i < 10 && aux > 100) {
                    texto = ("El Productor 000" + (i + 1) + " Ha fabricado el Ítem nº: 1" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } //SI el Productor es de 10 a 100
                else if (i >= 10 && i < 100 && aux < 10) {
                    texto = ("El Productor 00" + (i + 1) + " Ha fabricado el Ítem nº: 100" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i >= 10 && i < 100 && aux >= 10 && aux < 100) {
                    texto = ("El Productor 00" + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i >= 10 && i < 100 && aux > 100) {
                    texto = ("El Productor 00" + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } //SI el Productor es mayor a 100
                else if (i >= 100 && aux < 10) {
                    texto = ("El Productor 0" + (i + 1) + " Ha fabricado el Ítem nº: 100" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i >= 100 && aux >= 10 && aux < 100) {
                    texto = ("El Productor 0" + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else if (i >= 100 && aux > 100) {
                    texto = ("El Productor 0" + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    this.MC.println(this.MC.ANSI_PURPLE, texto);
                } else {

                    if (aux < 10) {
                        texto = ("El Productor " + (i + 1) + " Ha fabricado el Ítem nº: 100" + aux);
                    } else if (aux >= 10 && aux < 100) {
                        texto = ("El Productor " + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    } else if (aux > 100) {
                        texto = ("El Productor " + (i + 1) + " Ha fabricado el Ítem nº: 10" + aux);
                    }
                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }

}
