package co.edu.unbosque.model;

public class Entidad {

    private int fila;
    private int columna;
    private String rutaImagen;

    public Entidad(int fila, int columna, String rutaImagen) {
        this.fila = fila;
        this.columna = columna;
        this.rutaImagen = rutaImagen;
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
