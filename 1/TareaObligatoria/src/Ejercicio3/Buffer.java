package Ejercicio3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author LYNCHANIANO
 */

/**/
public class Buffer {
    
    /*Hay 3 semáforos:
        exclusionMutua: Para evitar que el buffer sea modificado al mismo tiempo por Productores o Consumidores.
        hayDatos:Indica si el buffer no está vacío (primer parámetro es 0 porqué está vacío)
        hayEspacio:
        */

    private MiConsole MC = new MiConsole();
    private int buffer[];
    private int i = 0;//la posición del siguiente producto que fabricará el productor.
    private int j = 0;//la posición del siguiente producto que comprará el consumidor.
    
    private Semaphore exclusionMutua = new Semaphore(1, true);//Semáforo binario para evitar bloqueos entre Productores y Consumidores.
   
    private Semaphore hayDatos = new Semaphore(0, true);//Semáforo binario que comprueba si hay algún dato en el buffer.
                                                        //Se inicializa en 0(rojo)por qué aún no hay datos.
    
    private Semaphore hayEspacio;//Semáforo con el tamaño de permisos igual al tamaño del buffer que indica si el buffer no está lleno. 
                                 //Si el buffer estuviera lleno el acquire() dejaría al proceso esperando a que un "consumidor" retire algún dato del buffer.
    private String texto;
    
    
    public Buffer(int tamanyoBuffer) {

        this.buffer = new int[tamanyoBuffer];//Se inicializa el buffer[] dándole el tamaño pasado por parámetro.

        this.hayEspacio = new Semaphore(this.buffer.length, true);//El buffer tendrá el mismo espacio que el array
    }
    
    
    

    public void ponerDato(int dato) throws InterruptedException {
        
        this.hayEspacio.acquire();//si no hay espacio se espera.
        
        this.exclusionMutua.acquire();//Exclusión mutua entre procesos(para poder modificar el buffer).
        
        this.buffer[this.i] = dato;//añado el dato.
        
        if(dato<10){
            this.texto = ("Están fabricando el Ítem nº 100" + dato);
        }else if(dato>=10&&dato<100){
            this.texto = ("Están fabricando el Ítem nº 10" + dato);
        }else if(dato>100){
            this.texto = ("Están fabricando el Ítem nº 1" + dato);
        }else{
            this.texto = ("Están fabricando el Ítem nº " + dato);
        }
        MC.println(MC.ANSI_BLUE,texto);
        
        this.i = (this.i + 1) % this.buffer.length;//La i también está protegida por la exclusionMutua.
        //El módulo es porqué hay que tener en cuenta la posibilidad de que puedo darle la vuelta
        //Si tengo 5 posiciones: estamos en la 4, le sumamos 1 y vamos a la 5, cómo necesito la 5 voy al 0.

        this.exclusionMutua.release();//libero las variables de la exclusión
        
        this.hayDatos.release();//indicamos que hay datos en el buffer.

    }

    public int sacarDato() throws InterruptedException {
        
        this.hayDatos.acquire();//Si no hay datos se espera.
        
        this.exclusionMutua.acquire();//Exclusión mutua entre variables (para poder modificar el buffer).
        
        int aux = this.j;//aux tiene el valor de la posición de la que puedo extraer. Que es la primera disponible.
        
        this.j = (this.j + 1) % this.buffer.length;
        
        this.exclusionMutua.release();//libero las variables de la exclusión
        
        this.hayEspacio.release();//indicamos que hay espacio en el buffer.
        
        return this.buffer[aux];//retornamos el valor que hemos consumido(eliminado).
    }
}
