package Ejercicio2;

import java.util.Random;
import java.util.Scanner;

public class MultMatrizSecuencial {

    public static int[][] matriz1;
    public static int[][] matriz2;
    public static long[][] resultado;
    public static int numMatriz = 1;
    public static int inicio;
    public static int fin;
    public static int filasColumnas;
    public static Random random;
    
    
    public static void main(String[] args) {

        inicio();
        

        llenaMatriz(matriz1);
        llenaMatriz(matriz2);
        MuestraMatriz(matriz1);
        MuestraMatriz(matriz2);
        long comienza = System.currentTimeMillis();//Aquí empieza la medición del tiempo, justo antes de la creación de los Threads.
        multiplica(matriz1, matriz2, resultado);
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
    /*FUNCIÓN QUE MULTIPLICA DOS MATRICES QUE RECIBE CÓMO PARAMETRO*/
    public static void multiplica(int[][] matriz1, int[][] matriz2, long[][] resultado) {

        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz2[0].length; j++) {
                for (int k = 0; k < matriz2[0].length; k++) {
                    resultado[i][j] += matriz1[i][k] * matriz2[k][j];
                }
            }
        }
    }
    /*FUNCIÓN USADA PARA MOSTRAR LA MATRIZ QUE ALMACENA LOS RESULTADOS DE LA MULTIPLICACIÓN*/
    public static void muestraResultado(long[][]resultado) {
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

}
