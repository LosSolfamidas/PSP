package Ejercicio3;

public class Consumidor extends Thread {

    private Buffer buffer;
    private int limiteCompra;
    private MiConsole MC = new MiConsole();//Para el color del texto que muestra la consola de Netbeans

    public Consumidor(Buffer buffer, int limiteCompra) {//Le pasaremos por parámetro el buffer a usar y el número de productos a Comprar(Datos a eliminar).
        this.buffer = buffer;
        this.limiteCompra = limiteCompra;

    }

    @Override
    public void run() {

        for (int i = 0; i < this.limiteCompra; i++) {
            int aux;
            String texto;
            try {
                aux = this.buffer.sacarDato();//elimina el dato (producto) del buffer.
                
                
                /*TODO ESTE CHURRO ES PARA LOS MENSAJES POR CONSOLA 
                 (El id del Consumidor(proceso) y el id del producto(dato) que ha comprado(eliminado del buffer))*/
                if (i < 10) {
                    if (aux < 10) {
                        texto = ("El Consumidor 500" + (i + 1) + " ha comprado el Ítem nº: 100" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    } else if (aux >= 10 && aux < 100) {
                        texto = ("El Consumidor 500" + (i + 1) + " ha comprado el Ítem nº: 10" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);

                    } else if (aux > 100) {
                        texto = ("El Consumidor 500" + (i + 1) + " ha comprado el Ítem nº: 1" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    }

                } else if (i >= 10 && i < 100) {
                    if (aux < 10) {
                        texto = ("El Consumidor 50" + (i + 1) + " ha comprado el Ítem nº: 100" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    } else if (aux >= 10 && aux < 100) {
                        texto = ("El Consumidor 50" + (i + 1) + " ha comprado el Ítem nº: 10" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    } else if (aux > 100) {
                        texto = ("El Consumidor 50" + (i + 1) + " ha comprado el Ítem nº: 1" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    }

                } else if (i > 100) {
                    if (aux < 10) {
                        texto = ("El Consumidor 5" + (i + 1) + " ha comprado el Ítem nº: 100" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    } else if (aux >= 10 && aux < 100) {
                        texto = ("El Consumidor 5" + (i + 1) + " ha comprado el Ítem nº: 10" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    } else if (aux > 100) {
                        texto = ("El Consumidor 5" + (i + 1) + " ha comprado el Ítem nº: 1" + aux);
                        this.MC.println(this.MC.ANSI_GREEN, texto);
                    }

                }

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

}
