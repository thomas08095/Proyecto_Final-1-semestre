package co.edu.unbosque.model;

public class Firewall {

    private int fila;
    private int columna;
    private String rutaImagen;
    private int longitud;
    private boolean esHorizontal;
    private Casilla[] casillasOcupadas;

    public Firewall(int fila, int columna, int longitud, boolean esHorizontal) {
        this.fila = fila;
        this.columna = columna;
        this.longitud = longitud;
        this.esHorizontal = esHorizontal;
        this.casillasOcupadas = new Casilla[longitud];
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }

    public int getLongitud() { return longitud; }
    public void setLongitud(int longitud) { this.longitud = longitud; }

    public boolean isEsHorizontal() { return esHorizontal; }
    public void setEsHorizontal(boolean esHorizontal) { this.esHorizontal = esHorizontal; }

    public Casilla[] getCasillasOcupadas() { return casillasOcupadas; }
    public void setCasillasOcupadas(Casilla[] casillasOcupadas) { this.casillasOcupadas = casillasOcupadas; }
}
