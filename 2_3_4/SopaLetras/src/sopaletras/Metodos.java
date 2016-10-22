package sopaletras;

/**
 *
 * @author LYNCHANIANO
 */
import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class Metodos {

    private JLabel[][] tabla;//La matriz de Label(la sopa de letras).
    //El número de filas y columnas de la matriz ( que serán 10/10)
    private int filas;
    private int columnas;

    private List listaPalabras;//Almacenará la lista de palabras que contenga el fichero.
    private ArrayList< char[][]> arrayLetras;
    private final int ceroX = 0;
    private final int unoY = 1;
    //Variables para identificar las posiciones que rodean las letras analizadas.
    //private int[] arrayPosiciones = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int arriba = 0;
    private final int arribaDerecha = 1;
    private final int derecha = 2;
    private final int abajoDerecha = 3;
    private final int abajo = 4;
    private final int abajoIzquierda = 5;
    private final int izquierda = 6;
    private final int arribaIzquierda = 7;

    private File ficheroGanador = new File("ficheros//ganador.txt");//El fichero con mensaje ganador

    public Metodos(JLabel[][] tabla, List listaPalabras) {
        this.tabla = tabla;
        filas = tabla.length;
        columnas = tabla[0].length;
        this.listaPalabras = listaPalabras;

        //creamos una tabla vacía en un ArrayList de letras (<char>) con el mismo tamaño que la tabla de la sopa.
        arrayLetras = new ArrayList< char[][]>();

        for (int i = 0; i < listaPalabras.getItemCount(); i++) {
            char[][] aux = new char[filas][columnas];
            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas; y++) {
                    aux[x][y] = ' ';
                }
            }
            arrayLetras.add(aux);
        }
    }

    /**
     * *******************************Comprueba si la palabra parametrizada
     * puede ser colocada en la tabla***************************
     * **********************************y usa el método "colocaLetras()" para
     * introducirla en la sopa*******************************
     */
    public boolean anyadePalabra(String palabra, int numeroLetra) {

        //Se calcula la coordenada de inicio de forma aleatoria.
        //La coordenada de inicio debe estar vacía.
        int x, y;
        do {
            x = (int) (Math.random() * filas);
            y = (int) (Math.random() * columnas);
        } while (!tabla[x][y].getText().equals(" "));

        int[] coordenadaInicio = {x, y};//Se crea la coordenada de inicio.
        int[] coordenadaFin = null;//Se inicializa la coordenada final.

        int totalLetrasPalabra = palabra.length() - 1;//La longitud de la palabra a colocar en el tablero.

        boolean[] direccionesPosibles = {false, false, false, false, false, false, false, false};//Para comprobar si ya se ha probado esa dirección.

        int numeroDeIntentos = 0;
        boolean palabraColocada = false;//Indica si la palabra se ha colocado o no en la tabla

        while (numeroDeIntentos <= 7 && !palabraColocada) {

            int direccion = (int) (Math.random() * 8);//Se escogerá una de las direcciones posibles a probar de forma aleatoria.

            /*A través de una serie de if controlamos todas las posibles condiciones^ dependiendo de la dirección.
             ^si es esa dirección, si se ha probado esa dirección, si hay espacio suficiente en esa dirección/es
             */
            if (direccion == arriba //Si el número random es arriba (0)...
                    && !direccionesPosibles[arriba]//Si aún no se ha probado esta posible dirección (false)
                    && coordenadaInicio[ceroX] - totalLetrasPalabra >= 0) {//Si hay suficientes casillas para colocar la palabra en esta dirección (arriba)
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] - totalLetrasPalabra, coordenadaInicio[unoY]//Se crea la coordenada de la última letra de la palabra a colocar.
                };
            } else if (direccion == arribaDerecha
                    && !direccionesPosibles[arribaDerecha]
                    && coordenadaInicio[ceroX] - totalLetrasPalabra >= 0
                    && coordenadaInicio[unoY] + totalLetrasPalabra <= columnas - 1) {//Si hay suficientes casillas para colocar la palabra en esta dirección (arribaDerecha)
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] - totalLetrasPalabra, coordenadaInicio[unoY] + totalLetrasPalabra
                };
            } else if (direccion == derecha
                    && !direccionesPosibles[derecha]
                    && coordenadaInicio[unoY] + totalLetrasPalabra <= columnas - 1) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX], coordenadaInicio[unoY] + totalLetrasPalabra
                };
            } else if (direccion == abajoDerecha
                    && !direccionesPosibles[abajoDerecha]
                    && coordenadaInicio[ceroX] + totalLetrasPalabra <= filas - 1
                    && coordenadaInicio[unoY] + totalLetrasPalabra <= columnas - 1) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] + totalLetrasPalabra, coordenadaInicio[unoY] + totalLetrasPalabra
                };
            } else if (direccion == abajo
                    && !direccionesPosibles[abajo]
                    && coordenadaInicio[ceroX] + totalLetrasPalabra <= filas - 1) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] + totalLetrasPalabra, coordenadaInicio[unoY]
                };
            } else if (direccion == abajoIzquierda
                    && !direccionesPosibles[abajoIzquierda]
                    && coordenadaInicio[ceroX] + totalLetrasPalabra <= filas - 1
                    && coordenadaInicio[unoY] - totalLetrasPalabra >= 0) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] + totalLetrasPalabra, coordenadaInicio[unoY] - totalLetrasPalabra
                };
            } else if (direccion == izquierda
                    && !direccionesPosibles[izquierda]
                    && coordenadaInicio[unoY] - totalLetrasPalabra >= 0) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX], coordenadaInicio[unoY] - totalLetrasPalabra
                };
            } else if (direccion == arribaIzquierda
                    && !direccionesPosibles[arribaIzquierda]
                    && coordenadaInicio[ceroX] - totalLetrasPalabra >= 0
                    && coordenadaInicio[unoY] - totalLetrasPalabra >= 0) {
                coordenadaFin = new int[]{
                    coordenadaInicio[ceroX] - totalLetrasPalabra, coordenadaInicio[unoY] - totalLetrasPalabra
                };
            }

            if (coordenadaFin != null) {//Si se ha creado la coordenada final... Se colocan las letras de toda la palabra.
                palabraColocada = colocaLetras(coordenadaInicio, coordenadaFin, direccion, palabra, numeroLetra, false);
            }
            coordenadaFin = null;//Se reinicia a null la coordenada.

            if (!direccionesPosibles[direccion]) {
                numeroDeIntentos++;
            }
            direccionesPosibles[direccion] = true;
        }
        return palabraColocada;//true/false
    }

    /**
     * ********************************Coloca las letras en la tabla de una de
     * las palabras del fichero.********************************* *********Para
     * ello recibe las coordenadas de inicio y fin, la dirección en la que se
     * colocará, la palabra a colocar*****************
     * ***************************************y el número de esa palabra en la
     * lista(fichero).*********************************************
     */
    private boolean colocaLetras(int[] coordenadaInicio, int[] coordenadaFinal, int coordenadaAleatoria, String palabra, int numeroPalabra, boolean OK) {
        int posX = coordenadaInicio[ceroX];
        int posY = coordenadaInicio[unoY];
        int totalLetrasPalabra = palabra.length();

        int letrasColocadas;

        for (letrasColocadas = 0; letrasColocadas < totalLetrasPalabra; letrasColocadas++) {
            String letraDeTabla = tabla[posX][posY].getText();//Variable con el contenido existente en la posición de la tabla que vamos a introducir letra.
            char letra_A_Colocar = palabra.charAt(letrasColocadas);//Variable con la letra que vamos a intentar colocar.

            // intentamos colocar la letra en la tabla
            if (letraDeTabla.equals(" ") || letraDeTabla.equals(letra_A_Colocar)) {
                if (OK) {
                    tabla[posX][posY].setText(letra_A_Colocar + " ");//es un char

                    char[][] aux = arrayLetras.get(numeroPalabra);
                    aux[posX][posY] = letra_A_Colocar;

                    arrayLetras.set(numeroPalabra, aux);
                    //System.out.println("numeroPalabra: "+numeroPalabra+ "\naux: "+aux[posX][posY]);
                }
            } else {
                return false;//Palabra no colocada
            }
            // Indicamos la dirección en que se deben introducir las letras moviendo las coordenadas.
            if (coordenadaAleatoria == arriba) {
                posX--;
            } else if (coordenadaAleatoria == arribaDerecha) {
                posX--;
                posY++;
            } else if (coordenadaAleatoria == derecha) {
                posY++;
            } else if (coordenadaAleatoria == abajoDerecha) {
                posX++;
                posY++;
            } else if (coordenadaAleatoria == abajo) {
                posX++;
            } else if (coordenadaAleatoria == abajoIzquierda) {
                posX++;
                posY--;
            } else if (coordenadaAleatoria == izquierda) {
                posY--;
            } else if (coordenadaAleatoria == arribaIzquierda) {
                posX--;
                posY--;
            }
        }
        // Si hay hueco para todos los caracteres se llama anyadePalabra (recursivo) para que esta vez los coloque:
        if (letrasColocadas == totalLetrasPalabra && !OK) {
            return colocaLetras(coordenadaInicio, coordenadaFinal, coordenadaAleatoria, palabra, numeroPalabra, true);
        } else if (OK) {
            return true;//Palabra colocada
        } else {
            return false;//Palabra no colocada
        }
    }

    /**
     * ** Comprueba si hemos marcado todas las letras de una palabra y si es así
     * las pinta de verde y la elimina del List.****
         *************************************************************************************************************************
     */
    public void validaLetrasPalabras() {
        int contPalabras = 0;
        // Analizamos las casillas de la tabla
        for (int numPalabra = 0; numPalabra < arrayLetras.size(); numPalabra++) {
            char[][] label = arrayLetras.get(numPalabra);
            // identificamos letra y su color
            int contador = 0;
            for (int x = 0; x < filas; x++) {
                for (int y = 0; y < columnas; y++) {
                    char letra = tabla[x][y].getText().charAt(0);
                    Color color = tabla[x][y].getForeground();
                    //Si la letra está marcada (Color MAGENTA)... Sumamos acierto al contador.
                    if (color.equals(Color.MAGENTA) && label[x][y] == letra) {
                        contador++;
                        //System.out.println(letra);
                    }
                }
            }
            // Se compara el tamaño de la palabra con el número de aciertos, si es el mismo número, pintamos todas las letras(la palabra) de Verde.
            if (contador == listaPalabras.getItem(numPalabra).length()) {
                for (int x = 0; x < filas; x++) {
                    for (int y = 0; y < columnas; y++) {
                        char letra = tabla[x][y].getText().charAt(0);
                        Color color = tabla[x][y].getForeground();

                        if (color.equals(Color.MAGENTA) && label[x][y] == letra) {
                            tabla[x][y].setForeground(Color.GREEN);
                        }
                    }
                }
                // Borramos la palabra de la List y del ArrayList
                listaPalabras.remove(numPalabra);
                arrayLetras.remove(numPalabra);
                contPalabras++;
                break;
            }
        }

        //Si se solucionan todas las palabras saldrá mensaje indicando que has ganado la partida.
        if (contPalabras == listaPalabras.getItemCount() + 1) {
            MensajeGanador();
        }

    }

    public void MensajeGanador() {
        listaPalabras.removeAll();//borrado de la lista

        try {
            try (BufferedReader buffer = new BufferedReader(new FileReader(ficheroGanador))) {
                String palabra;

                while ((palabra = buffer.readLine()) != null) {
                    listaPalabras.add(palabra);
                }
            }
        } catch (FileNotFoundException e) {
            listaPalabras.add("NO SE ENCUENTRA FICHERO");
        } catch (IOException e) {
            listaPalabras.add("ALGO ESTÁ FALLANDO");
        }
    }
}