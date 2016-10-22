
package Ejercicio3;

import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) throws InterruptedException {
        
        System.out.print("Tamaño del buffer?: ");
        int tamanyoBuffer = scanner.nextInt();
        
        System.out.print("Cuantos productos quieres fabricar/vender?: ");
        int limiteProductos = scanner.nextInt();
        System.out.println("\n\n\n\n");
        
        
        
        Buffer buffer = new Buffer(tamanyoBuffer);
        Productor productor = new Productor(buffer,limiteProductos);
        Consumidor consumidor = new Consumidor(buffer, limiteProductos);
        productor.start();
        consumidor.start();
        
        consumidor.join();//el consumidor debe esperar a que se fabriquen los productos.
    }
    
}
