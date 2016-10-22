package sopaletras;

/**
 *
 * @author LYNCHANIANO
 */
import java.awt.Color;

import javax.swing.JLabel;

public class AutoResolver extends Thread {

    private JLabel[][] tabla;
    private int filas, columnas;
    private String palabra;
    private int posX, posY;
    private final int arriba = 0;
    private final int arribaDerecha = 1;
    private final int derecha = 2;
    private final int abajoDerecha = 3;
    private final int abajo = 4;
    private final int abajoIzquierda = 5;
    private final int izquierda = 6;
    private final int arribaIzquierda = 7;

    public AutoResolver(String palabra, int posX, int posY, JLabel[][] tabla) {
        this.tabla = tabla;
        filas = tabla.length;
        columnas = tabla[0].length;
        this.palabra = palabra;
        this.posX = posX;
        this.posY = posY;
    }
    /*Crea un Hilo para resolver una de las palabras de la lista de forma automática*/
    @Override
    public void run() {
        try {
            JLabel[] labels = new JLabel[palabra.length()];

            // Se comprueban las 8 direcciones posibles a partir de la letra:
            for (int posicion = 0; posicion < 8; posicion++) {
                int contador = 0;
                int pX = posX;
                int pY = posY;
                
                //Se crea un array de label y se van añadiendo todas las letras de la palabra a resolver.
                //Se comprueba la coordenada pasada con la primera letra de la palabra a resolver y se añade al array
                //Se va modificando la coordenada y avanzando letras de la palabra y añadiendo al array
                //Cuando la palabra está completa en el array... se pinta de rojo.
                for (int numLabel = 0; numLabel < palabra.length(); numLabel++) {
                    
                    if (tabla[pX][pY].getText().charAt(0) == palabra.charAt(numLabel)) {
                        contador++;
                        labels[numLabel] = tabla[pX][pY];
                    }
                    // Indicamos la dirección en que se deben pintar(resolver) las letras moviendo las coordenadas.
                    if (posicion == arriba && pX > 0) {
                        pX--;
                    } else if (posicion == arribaDerecha && pX > 0 && pY < columnas - 1) {
                        pX--;
                        pY++;
                    } else if (posicion == derecha && pY < columnas - 1) {
                        pY++;
                    } else if (posicion == abajoDerecha && pX < filas - 1 && pY < columnas - 1) {
                        pX++;
                        pY++;
                    } else if (posicion == abajo && pX < filas - 1) {
                        pX++;
                    } else if (posicion == abajoIzquierda && pX < filas - 1 && pY > 0) {
                        pX++;
                        pY--;
                    } else if (posicion == izquierda && pY > 0) {
                        pY--;
                    } else if (posicion == arribaIzquierda && pX > 0 && pY > 0) {
                        pX--;
                        pY--;
                    } else {
                        break;
                    }
                }
                //Una vez lleno, recorremos el array pintando las letras de rojo
                if (contador == palabra.length()) {
                    for (int i = 0; i < labels.length; i++) {
                        labels[i].setForeground(Color.RED);
                    }
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
