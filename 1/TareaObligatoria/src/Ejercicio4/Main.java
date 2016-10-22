package Ejercicio4;

import java.util.concurrent.Semaphore;

/**
 *
 * @author LYNCHANIANO
 */
public class Main {

    public static void main(String args[]) {
        
        /*SE INICIALIZAN TODOS LOS COMPONENTES DEL PROGRAMA*/
        
        int totalFilosofos = 5;// número de filósofos.
        
        Filosofo filosofo[] = new Filosofo[totalFilosofos]; //se crea un array con espacio para 5 filósofos(procesos)
        
        Tenedor[] tenedor = new Tenedor[totalFilosofos]; //se crea un array de tenedores (semáforos binarios)
                                                         //habrá tantos tenedores cómo filósofos
        
        Semaphore semaforo = new Semaphore(totalFilosofos - 1); //se crea una mesa para cuatro filosofos (semáforo).Si hubieran 5 de vez podrían bloquearse.

        for (int i = 0; i < totalFilosofos; i++) // se inicializan 5 tenedores
        {
            tenedor[i] = new Tenedor(i, 1);//se inicializa el semáforo dándole su id y poniendolos en verde(en 1).
        }

        for (int i = 0; i < totalFilosofos; i++) // se inicializan los 5 filosofos
        {
            filosofo[i] = new Filosofo(i, semaforo, tenedor[i], tenedor[(i + 1) % totalFilosofos]);//el módulo al tenedor derecho:
                                                                                                   //asegura que se le asigna el correcto.
                                                                                                   //(por si el izquierdo es el tenedor 5)
            filosofo[i].start();//Inicializamos el Hilo.
        }
        
        for (int i = 0; i < totalFilosofos; i++) { // se les añade el join() a los filósofos.
            try {
                filosofo[i].join();//se le añade el join para que espere su turno.
            } catch (Exception e) {
            }
        }
    }
}
