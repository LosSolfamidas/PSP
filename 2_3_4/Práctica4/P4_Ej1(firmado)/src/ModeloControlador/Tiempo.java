package ModeloControlador;

public class Tiempo {
    private long start;
    private long end;
    
    public Tiempo() {
        reiniciar();
    }
    
    public void reiniciar() {
        start = System.currentTimeMillis();
    }
    
    public void fin(){
        end = System.currentTimeMillis();
    }
    
    public long getTiempo(){
        return end - start;
    }
}

