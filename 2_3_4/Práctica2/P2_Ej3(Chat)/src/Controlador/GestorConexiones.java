package Controlador;

import java.util.LinkedList;


public class GestorConexiones {

   

    private static GestorConexiones singleton = new GestorConexiones();

    public static GestorConexiones getInstance() {
        return singleton;
    }

    
     /*Array que almacenará todas las conexiones de los clientes*/
    private LinkedList<HiloConexion> arrayConexiones = new LinkedList<>();
    
    
    
    
    
    /*Método que envía el nick del nuevo cliente a la lista de clientes conectados y también añadirá ese cliente nuevo al Array*/
    public synchronized void conectaNuevoCliente(HiloConexion nuevoHiloCliente) {
        
        arrayConexiones.stream().forEach((conexion) -> {
            nuevoHiloCliente.enviarCadena(1, conexion.getNick());
        });
        arrayConexiones.add(nuevoHiloCliente);   
    }
    
    
    
    
    public synchronized void enviarCadena_A_Conectados(int operacion, String cadena) {
        
        arrayConexiones.stream().forEach((conexion) -> {
            conexion.enviarCadena(operacion, cadena);
        });
    }

    
    
    
    /*Método que eliminará la conexión de un cliente del Array y también su nombre a la lista de clientes conectados*/
    public synchronized void desconecta(HiloConexion cliente_A_Desconectar) {
        int posicion = -1;
        
        
        for (int iterPosicion = 0; iterPosicion < arrayConexiones.size(); iterPosicion++) {
            if (arrayConexiones.get(iterPosicion) == cliente_A_Desconectar) {
                posicion = iterPosicion;
            }
        }
        if (posicion != -1) {
            for (int iPosicion = 0; iPosicion < arrayConexiones.size(); iPosicion++) {
                if (iPosicion != posicion) {
                    arrayConexiones.get(iPosicion).enviarCadena(3, "" + posicion);
                }
            }
            arrayConexiones.remove(posicion);
        }
    }

    

}
