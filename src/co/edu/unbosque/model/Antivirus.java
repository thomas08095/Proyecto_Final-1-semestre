package co.edu.unbosque.model;

public class Antivirus {

    private int fila;
    private int columna;
    private String rutaImagen;

    public Antivirus(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
