package sopaletras;

/**
 *
 * @author LYNCHANIANO
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;

public class Principal {

    private Metodos metodos;
    private JFrame FramePrincipal;
    private JLabel label, labelPalabrasEncontrar;
    private final int filas = 12;
    private final int columnas = 12;
    private JLabel[][] tablero = new JLabel[filas][columnas];
    private List listaPalabras;
    private File ficheroPalabras = new File("ficheros//palabras.txt");//El fichero con las palabras a resolver
    private File ficheroResolver = new File("ficheros//resolver.txt");//El fichero con mensaje al autorresolver
    private final int[] letras = new int[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /*Botones de opción*/
    private JMenuBar menuBar;
    private JMenu btnResolver, btnReiniciar, botonFinPartida;

    /*Para la colocación de los componentes de la interfaz*/
    private int posX = 0, posY = 0, ancho = 0, alto = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal ventana = new Principal();
                    ventana.FramePrincipal.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        inicioComponentes();
        llenaListaPalabras();
        metodos = new Metodos(tablero, listaPalabras);
        dibujaTablero();
    }

    /**
     * ****************************Iniciamos y damos funcionalidad a todos los
     * componentes de la interfaz.****************************
   ********************************************************************************************************************************
     */
    private void inicioComponentes() {

        FramePrincipal = new JFrame();
        FramePrincipal.setResizable(false);
        FramePrincipal.setTitle("Ejercicio Sopa de Letras");
        posX = 400;
        posY = 200;
        ancho = 820;
        alto = 650;
        FramePrincipal.setBounds(posX, posY, ancho, alto);
        FramePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FramePrincipal.getContentPane().setLayout(null);

//Creamos los label donde se alojarán las letras
        posX = 220;
        posY = 10;
        ancho = 25;
        alto = 25;
        javax.swing.border.Border border = LineBorder.createGrayLineBorder();
        for (int x = 1; x <= filas; x++) {
            for (int y = 1; y <= columnas; y++) {
                label = new JLabel(" ");
                label.setBounds(posX, posY, ancho, alto);
                label.setBorder(border);
                label.setFont(new Font("Tahoma", Font.ITALIC, 20));
                label.setForeground(Color.BLACK);

//Se le da función al click del mouse sobre los label
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        clickLabel(evt);
                    }
                });

                tablero[x - 1][y - 1] = label;
                FramePrincipal.getContentPane().add(label);//añadimos los label al Frame

                posX += 50;//(separamos los label)
            }
            posX = 220;//Reiniciamos la posición
            posY += 50;//(Saltamos linea)
        }

        labelPalabrasEncontrar = new JLabel("<HTML><U>¡¡ENCUÉNTRALAS!!</U></HTML>");
        labelPalabrasEncontrar.setFont(new Font("Tahoma", Font.TRUETYPE_FONT, 20));

        posX = 5;
        posY = 15;
        ancho = 190;
        alto = 26;
        labelPalabrasEncontrar.setBounds(posX, posY, ancho, alto);
        FramePrincipal.getContentPane().add(labelPalabrasEncontrar);

        listaPalabras = new List();
        listaPalabras.setFont(new Font("Tahoma", Font.ITALIC, 12));
        listaPalabras.setBackground(Color.decode("#C2FFEB"));
        /*Inhabilitamos la interacción con las palabras de la lista*/
        listaPalabras.setEnabled(false);
        listaPalabras.setFocusable(false);

        posX = 30;
        posY = 50;
        ancho = 120;
        alto = 210;
        listaPalabras.setBounds(posX, posY, ancho, alto);

        FramePrincipal.getContentPane().add(listaPalabras);

        menuBar = new JMenuBar();
        FramePrincipal.setJMenuBar(menuBar);

        btnResolver = new JMenu("Resolver");
        btnResolver.setEnabled(true);
        btnResolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                descubrePalabras(); // Boton Resolver
            }
        });
        menuBar.add(btnResolver);

        btnReiniciar = new JMenu("Reiniciar");
        btnReiniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                dibujaTablero(); // Boton Reiniciar
            }
        });
        menuBar.add(btnReiniciar);

        botonFinPartida = new JMenu("Fin");
        botonFinPartida.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0); // Boton fin
            }
        });
        menuBar.add(botonFinPartida);
    }

    /**
     * ****************************Genera el tablero cargando y colocando las
     * palabras del fichero.****************************
   ********************************************************************************************************************************
     */
    private void dibujaTablero() {

        // Se borra el contenido que pueda tener la tabla:
        for (int x = 0; x < tablero.length; x++) {
            for (int y = 0; y < tablero[0].length; y++) {
                tablero[x][y].setText(" ");
                tablero[x][y].setForeground(Color.BLACK);//Se vuelven a poner las letras negras (por si hay marcadas).
            }
        }

        // Se carga la lista de palabras:
        llenaListaPalabras();

        metodos = new Metodos(tablero, listaPalabras);

        // Se colocan las palabras del fichero en la tabla. Probando una a una.
        int numeroDePalabra = 0;
        while (numeroDePalabra < listaPalabras.getItemCount()) {
            String palabraActual = listaPalabras.getItem(numeroDePalabra);

            if (metodos.anyadePalabra(palabraActual, numeroDePalabra)) {//Si la palabra se ha colocado...
                numeroDePalabra++;
            }
        }
        // Se rellenan el resto de los label con letras aleatorias

        for (int x = 0; x < tablero.length; x++) {
            for (int y = 0; y < tablero[0].length; y++) {
                if (tablero[x][y].getText().equals(" ")) {
                    tablero[x][y].setText("" + (char) letras[(int) (Math.random() * 27)]);
                }
            }
        }

    }

    /**
     * ****************************Este método añade al label las palabras de
     * fichero.txt.****************************
   ********************************************************************************************************************************
     */
    private void llenaListaPalabras() {
        listaPalabras.removeAll();//reinicio la lista

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(ficheroPalabras));
            String palabra;

            while ((palabra = buffer.readLine()) != null) {
                listaPalabras.add(palabra.toUpperCase());
            }
            buffer.close();
        } catch (FileNotFoundException e) {
            listaPalabras.add("NO SE ENCUENTRA FICHERO");
        } catch (IOException e) {
            listaPalabras.add("ALGO ESTÁ FALLANDO");
        }
    }

    /**
     * ****************************Este metodo da funcionalidad a la acción de
     * click sobre una letra(label).****************************
   ********************************************************************************************************************************
     */

    private void clickLabel(MouseEvent evt) {
        JLabel letra = (JLabel) evt.getSource();
        //Al pulsar la letra el color cambia de negro a magenta.
        if (letra.getForeground().equals(Color.BLACK)) {
            letra.setForeground(Color.MAGENTA);
            //Comprueba si has marcado todas las letras de la palabra  
            metodos.validaLetrasPalabras();

            //Si es magenta volverá a negro.
        } else if (letra.getForeground().equals(Color.MAGENTA) || letra.getForeground().equals(Color.GREEN)) {
            letra.setForeground(Color.BLACK);
        }

    }

    /**
     * ***************Este metodo compara la primera letra de la palabra
     * a resolver con cada una de las letras de la
     * sopa.**************************** ******************************
     * Si coinciden, a través de "Autorresolver" se resuelve la palabra en cuestión
     * Y así con todas las palabras.
     * Las palabras se resolverán 1 a 1, con un hilo cada una.***************************************
     */
    private void descubrePalabras() {

        AutoResolver resuelve;

        for (int i = 0; i < listaPalabras.getItemCount(); i++) {
            for (int x = 0; x < tablero.length; x++) {
                for (int y = 0; y < tablero[0].length; y++) {
                    char letra = tablero[x][y].getText().charAt(0);
                    char primeraLetraPalabra = listaPalabras.getItem(i).charAt(0);
                    if (letra == primeraLetraPalabra) {
                        resuelve = new AutoResolver(listaPalabras.getItem(i), x, y, tablero);
                        resuelve.start();// Se inicia el Hilo.
                    }
                }
            }
        }
        MensajeResolver();//
    }
    
   
    
    
    /*Muestra mensaje en el caso de autorresolver la sopa*/
    public void MensajeResolver() {
        listaPalabras.removeAll();//borrado de la lista

        try {
            try (BufferedReader buffer = new BufferedReader(new FileReader(ficheroResolver))) {
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