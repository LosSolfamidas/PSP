package ModeloControlador;

public class EmpresaBolsa implements Comparable<EmpresaBolsa> {

    private String nombre;
    private String ultimoCambio;
    private String cambio;
    private String volumen;

    public EmpresaBolsa(String nombre, String ultimoCambio, String cambio, String volumen) {
        this.nombre = nombre;
        this.ultimoCambio = ultimoCambio;
        this.cambio = cambio;
        this.volumen = volumen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUltimoCambio() {
        return ultimoCambio;
    }

    public String getCambio() {
        return cambio;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUltimoCambio(String ultimoCambio) {
        this.ultimoCambio = ultimoCambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    @Override
    public int compareTo(EmpresaBolsa nombre) {
        if (this.nombre.charAt(0) < nombre.nombre.charAt(0)) {
            return -1;
        }
        if (this.nombre.charAt(0) == nombre.nombre.charAt(0)) {
            return 0;
        }
        return 1;
    }
}
