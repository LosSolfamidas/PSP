package Ejercicio2;

import java.util.Random;
import java.util.Scanner;

public class MultMatrizThreads {

    public static int[][] matriz1;
    public static int[][] matriz2;
    public static long[][] resultado;
    public static int numMatriz = 1;
    public static int inicio;
    public static int fin;
    public static int filasColumnas;
    public static Random random;
    //public static int num_threads = 4;

    public static void main(String[] args) throws InterruptedException {

        inicio();

        llenaMatriz(matriz1);
        llenaMatriz(matriz2);
        
        MuestraMatriz(matriz1);
        MuestraMatriz(matriz2);

        long comienza = System.currentTimeMillis();//Aquí empieza la medición del tiempo, justo antes de la creación de los Threads.
        
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        Thread4 thread4 = new Thread4();
        
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        
       
        /*EL CÓDIGO COMENTADO ES DE UNA PRUEBA FALLIDA DE INTRODUCIR LOS THREADS CON UNA FUNCIÓN (que también está comentada al final)*/
        /*
        for (int i =1; i< num_threads; i++){
            CreaThread t = new CreaThread(i, num_threads);
            t.start();
            t.join();
        }
        */
        
        muestraResultado(resultado);

        
        long tiempo = System.currentTimeMillis() - comienza;//Aquí termina la medición del tiempo.

        System.out.println("\n\nTIEMPO NECESARIO PARA REALIZAR OPERACIÓN : " + tiempo + " milisegundos");

    }
    /*FUNCIÓN QUE DEFINE E INICIALIZA LAS MATRICES QUE USAMOS EN EL EJERCICIO*/
    public static void inicio() {
        Scanner scanner = new Scanner(System.in);
        random = new Random();
        System.out.print("Esta aplicación realizará una multiplicación de matrices CUADRADAS (mismo número de filas y columnas)\n\n\n"
                + "Cuantas filas y columnas tendrán las matrices? : ");
        filasColumnas = scanner.nextInt();

        matriz1 = new int[filasColumnas][filasColumnas];
        matriz2 = new int[filasColumnas][filasColumnas];
        resultado = new long[matriz1.length][matriz2[0].length];

        System.out.print("\n\nEl rango de los números que conponen las matrices irá del 1 al : ");
        inicio = 1;
        fin = scanner.nextInt();

    }
    /*FUNCIÓN USADA PARA RELLENAR LAS DOS MATRICES A MULTIPLICAR*/
    public static int[][] llenaMatriz(int[][] matriz) {

        int valores;

        for (int i = 0; i < filasColumnas; i++) {
            for (int j = 0; j < filasColumnas; j++) {
                valores = (int) (random.nextDouble() * fin + inicio);
                matriz[i][j] = valores;
            }
        }
        return matriz;
    }
     /*FUNCIÓN USADA PARA MOSTRAR LAS DOS MATRICES A MULTIPLICAR*/
    public static void MuestraMatriz(int[][] matriz) {

        System.out.println("\n\t\tMATRIZ " + numMatriz);
        for (int i = 0; i < filasColumnas; i++) {
            System.out.print("[ ");
            for (int j = 0; j < filasColumnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.print("]");
            System.out.println("");
        }
        numMatriz++;
    }
     /*FUNCIÓN USADA PARA MOSTRAR LA MATRIZ QUE ALMACENA LOS RESULTADOS DE LA MULTIPLICACIÓN*/
    public static void muestraResultado(long[][] resultado) {
        System.out.println("\n\t\tRESULTADOS");
        for (int i = 0; i < resultado.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < resultado[0].length; j++) {
                System.out.print(resultado[i][j] + "\t");
            }
            System.out.print("]");
            System.out.println("");
        }
    }
    /*HE CREADO 4 FUNCIONES DISTINTAS, 1 PARA CADA UNO DE LOS 4 THREAD QUE SE USARÁN*/
    
    /*Este Thread se ocupa del primer cuarto de la operación*/
    public static class Thread1 extends Thread {

        @Override
        public void run() {
            int filas2 = matriz2.length;
            int columnas2 = matriz2[0].length;
            int cuarto1 = matriz1.length / 4; //se ocupará del primer cuarto.

            for (int i = 0; i <= cuarto1; i++) {
                for (int j = 0; j < columnas2; j++) {
                    for (int k = 0; k < filas2; k++) {
                        resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                    }
                }
            }
        }
    }
    /*Este Thread se ocupa del segundo cuarto de la operación*/
    public static class Thread2 extends Thread {

        @Override
        public void run() {
            int filas2 = matriz2.length;
            int columnas2 = matriz2[0].length;
            int cuarto2 = matriz1.length / 4 + 1; //desde el segundo cuarto
            int cuarto3 = matriz1.length * 2 / 4;//hasta el tercer cuarto

            for (int i = cuarto2; i <= cuarto3; i++) {
                for (int j = 0; j < columnas2; j++) {
                    for (int k = 0; k < filas2; k++) {
                        resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                    }
                }
            }
        }
    }
    /*Este Thread se ocupa del tercer cuarto de la operación*/
    public static class Thread3 extends Thread {

        @Override
        public void run() {
            int filas2 = matriz2.length;
            int columnas2 = matriz2[0].length;
            int cuarto3 = matriz1.length * 2 / 4 + 1; //desde el tercer cuarto
            int cuarto4 = matriz1.length * 3 / 4;//hasta el cuarto cuarto

            for (int i = cuarto3; i <= cuarto4; i++) {
                for (int j = 0; j < columnas2; j++) {
                    for (int k = 0; k < filas2; k++) {
                        resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                    }
                }
            }
        }
    }
    /*Este Thread se ocupa del último cuarto de la operación*/
    public static class Thread4 extends Thread {

        @Override
        public void run() {
            int filas2 = matriz2.length;
            int columnas2 = matriz2[0].length;
            int cuarto4 = matriz1.length * 3 / 4 + 1; //el cuarto cuarto

            for (int i = cuarto4; i < columnas2; i++) {
                for (int j = 0; j < columnas2; j++) {
                    for (int k = 0; k < filas2; k++) {
                        resultado[i][j] = resultado[i][j] + matriz1[i][k] * matriz2[k][j];
                    }
                }
            }

        }
    }

    /*public static class CreaThread extends Thread {

        int filas2 = matriz2.length;
        int columnas2 = matriz2[0].length;
        int start; // = matriz1.length*3/4+1; //el cuarto cuarto
        int end;

        public CreaThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < columnas2; j++) {
                    for (int k = 0; k < filas2; k++) {
                        resultado[i][j] = resultado[i][j] + matriz1[i][k] * matriz2[k][j];
                    }
                }
            }
        }
    }*/
}
