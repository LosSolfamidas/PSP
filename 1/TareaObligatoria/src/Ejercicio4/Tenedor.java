package Ejercicio4;

/**
 *
 * @author LYNCHANIANO
 */

import java.util.concurrent.Semaphore;
/*DEFINE LOS TENEDORES*/
public class Tenedor extends Semaphore {//los tenedores son semáforos binarios.

    private int id;

    Tenedor(int id, int valor) {
        super(valor);//se invoca al constructor de Semaphore.
        this.id = id;
    }

    int getId() {
        return id;
    }
}
