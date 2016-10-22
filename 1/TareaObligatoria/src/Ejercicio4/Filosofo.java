package Ejercicio4;

/**
 *
 * @author LYNCHANIANO
 */
import static java.lang.Thread.*;
import java.util.Random;
import java.util.concurrent.*;

public class Filosofo extends Thread {

    private int idFilosofo;//El id del proceso.
    private Tenedor tenedorIzquierdo, tenedorDerecho;//semáforos binarios
    private Semaphore semaforoContador;//semáforo general
    private  Random random = new Random();//para las esperas (sleeps).
    private int valorAleatorio;
    private int numeroOperaciones = 6;//número de veces que se ejecutará run() (lo que durará el programa ejecutando vaya).
    private MiConsole MC = new MiConsole();//Para el color del texto que se visualiza por consola.

    Filosofo(int idFilosofo, Semaphore semaforoContador, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
        this.idFilosofo = idFilosofo;
        this.semaforoContador = semaforoContador;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
    }

    public void run() {
        
        //Textos que aparecerán por consola.
        String piensa, intenta, cogeDerecho, cogeIzquierdo, come, dejaDerecho, dejaIzquierdo, termina;
        

        for (int i = 0; i < this.numeroOperaciones; i++) {
            try {
                piensa = ("El Filósofo número " + (this.idFilosofo + 1) + " está PENSANDO.");
                this.MC.println(this.MC.ANSI_BLACK, piensa);
                
                this.valorAleatorio = (int) (random.nextDouble() * 3000 + 1);
                sleep(valorAleatorio);//se les para un tiempo aleatorio a cada uno.
                                      //(para que piensen con la calma y... para comprobar que el ejercicio funciona).

                
                
                this.semaforoContador.acquire();/* el método acquire() obtiene el semáforo, si el semaforo es mayor que cero,
                                           de lo contrario se espera hasta que se incremente y reduce el semaforo,
                                           después el thread que obtiene el semáforo ejecuta el recurso*/ 
                
                intenta = ("El Filósofo número " + (this.idFilosofo + 1) + " INTENTA coger cubiertos para poder COMER.");
                this.MC.println(this.MC.ANSI_BLUE, intenta);

                this.tenedorDerecho.acquire();//comprueba si está disponible el tenedor(semáforo a 1);
                                              //Si lo está, lo "coge" y lo pone en rojo (reduce a 0).
                                              //Sino... espera a que lo esté.
                
                cogeDerecho = ("El Filósofo número " + (this.idFilosofo + 1) + " COGE el Tenedor Derecho: " + (this.tenedorDerecho.getId()+1));
                this.MC.println(this.MC.ANSI_CYAN, cogeDerecho);

                this.tenedorIzquierdo.acquire();//comprueba si está disponible el tenedor(semáforo a 1);
                                                //Si lo está, lo "coge" y lo pone en rojo (reduce a 0).
                                                //Sino... espera a que lo esté.
                
                cogeIzquierdo = ("El Filósofo número " + (this.idFilosofo + 1) + " COGE el Tenedor Izquierdo: " + (this.tenedorIzquierdo.getId()+1));
                this.MC.println(this.MC.ANSI_GREEN, cogeIzquierdo);

                /*COMO EL FILÓSOFO(proceso) YA TIENE EN SU PODER LOS DOS TENEDORES(semáforos), CONSIDERAMOS QUE ESTÁ COMIENDO*/
                come = ("El Filósofo número " + (this.idFilosofo + 1) + " está COMIENDO " + "Tenedor Izquierdo: " + (this.tenedorIzquierdo.getId()+1) + " Tenedor Derecho: " + (this.tenedorDerecho.getId()+1));
                this.MC.println(this.MC.ANSI_PURPLE, come);
                
                this.valorAleatorio = (int) (random.nextDouble() * 3000 + 1);
                sleep(valorAleatorio);//dejamos al FILÓSOFO (proceso) COMIENDO (durmiendo) un tiempo aleatorio.
                
                this.tenedorDerecho.release();//libera el tenedor (pone el semáforo en verde(en 1))
                
                dejaDerecho = ("El Filósofo número " + (this.idFilosofo + 1) + " DEJA el Tenedor Derecho: " + (this.tenedorDerecho.getId()+1));
                this.MC.println(this.MC.ANSI_RED, dejaDerecho);

                this.tenedorIzquierdo.release();//libera el tenedor (pone el semáforo en verde(en 1))
                
                dejaIzquierdo = ("El Filósofo número " + (this.idFilosofo + 1) + " DEJA el Tenedor Izquierdo: " + (this.tenedorIzquierdo.getId()+1));
                this.MC.println(this.MC.ANSI_RESET, dejaIzquierdo);

                
                this.semaforoContador.release();/* pone el semaforo a 1 (da paso a otro filósofo (proceso)).*/
                
                /*MOSTRAMOS QUE EL FILÓSOFO (proceso) HA ACABADO*/
                termina = ("El Filósofo número " + (this.idFilosofo + 1) + " Ha TERMINADO de comer y se dispone a volver a PENSAR ");
                this.MC.println(this.MC.ANSI_YELLOW, termina);
                
            } catch (InterruptedException e) {
            }
        }
    }
}
