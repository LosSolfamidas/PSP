package Controlador;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class GestorConexiones {

    private static GestorConexiones singleton = new GestorConexiones();

    public static GestorConexiones getInstance() {
        return singleton;
    }

    /*Array que almacenará todas las conexiones de los clientes*/
    private LinkedList<HiloConexion> arrayConexiones = new LinkedList<>();

    public synchronized void conectaNuevoCliente(HiloConexion nuevoHiloCliente) {

        //Primero adjudica el Turno
        if (arrayConexiones.isEmpty()) {
            nuevoHiloCliente.setIdConexion(0);
            nuevoHiloCliente.setTeToca(true);
        } else {
            nuevoHiloCliente.setIdConexion(arrayConexiones.getLast().getIdConexion() + 1);
            nuevoHiloCliente.setTeToca(false);
        }

        //Envía el Nick a la conexión
        arrayConexiones.stream().forEach((conexion) -> {
            nuevoHiloCliente.enviarNick_A_Cliente(conexion.getNick());
        });

        //Añade la nueva conexión al Array
        arrayConexiones.add(nuevoHiloCliente);

    }

    public synchronized void enviarCadena_A_Conectados(String cadena) {

        //Envía el mensaje y puntuación a todas las conexiones
        arrayConexiones.stream().forEach((conexion) -> {
            conexion.enviarPuntuacion_A_Cliente(cadena);
        });
    }

    public synchronized void enviarNick_A_Conectados(String cadena) {
        //Envía el Nick a todas las conexiones
        arrayConexiones.forEach((conexion) -> {
            conexion.enviarNick_A_Cliente(cadena);
        });
    }

    public synchronized void pasaRondaTurno(HiloConexion jugadorActual) {

        jugadorActual.sumaRonda();
        if (jugadorActual.getRonda() == 5) {
            jugadorActual.setFin(true);
            if (verificaFin()) {
                JOptionPane.showMessageDialog(null, mensajeGanador());
                fin();
                return;
            }

        }

        //Adjudica el Turno al siguiente jugador
        for (int i = 0; i < arrayConexiones.size(); i++) {
            HiloConexion jugadorSiguiente = arrayConexiones.get(i);
            if (jugadorSiguiente.getIdConexion() > jugadorActual.getIdConexion()) {
                jugadorSiguiente.setTeToca(true);

                return;
            }
        }

        arrayConexiones.get(0).setTeToca(true);

    }

    public synchronized boolean verificaFin() {

        for (int i = 1; i < arrayConexiones.size(); i++) {
            if (!arrayConexiones.get(i).isFin()) {
                return false;
            }
        }
        return true;
    }

    public synchronized String mensajeGanador() {
        String mensajeGanador;
        HiloConexion aux = new HiloConexion();
        int puntos = 0;
        for (int i = 1; i < arrayConexiones.size(); i++) {

            HiloConexion aux2 = arrayConexiones.get(i);
            if (puntos < aux2.getPuntuacionTotal()) {
                puntos = aux2.getPuntuacionTotal();
                aux = aux2;

            }
        }
        mensajeGanador = "El Ganador és: " + aux.getNick() + "\nPuntuación Total: " + aux.getPuntuacionTotal();
        return mensajeGanador;
    }

    /*Método que eliminará la conexión de un cliente del Array y también su nombre a la lista de clientes conectados*/
    public synchronized void desconecta(HiloConexion cliente_A_Desconectar) {

        arrayConexiones.stream().forEach((conexion) -> {
            if (cliente_A_Desconectar.getIdConexion() != conexion.getIdConexion()) {
                conexion.enviarDesconectado_A_Cliente(cliente_A_Desconectar.getIdConexion());
            }
        });
        arrayConexiones.remove(cliente_A_Desconectar);
    }

    public synchronized void fin() {

        arrayConexiones.stream().forEach((conexion) -> {
            conexion.acabarTodo();
            //arrayConexiones.remove(conexion);
        });
        arrayConexiones.remove();
    }

}
