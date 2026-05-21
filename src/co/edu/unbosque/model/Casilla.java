package co.edu.unbosque.model;

public class Casilla {

    private int fila;
    private int columna;
    private Entidad contenido;
    private boolean estaOcupada;

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.contenido = null;
        this.estaOcupada = false;
    }

    public int getFila() { return fila; }
    public void setFila(int fila) { this.fila = fila; }

    public int getColumna() { return columna; }
    public void setColumna(int columna) { this.columna = columna; }

    public Entidad getContenido() { return contenido; }
    public void setContenido(Entidad contenido) { this.contenido = contenido; }

    public boolean isEstaOcupada() { return estaOcupada; }
    public void setEstaOcupada(boolean estaOcupada) { this.estaOcupada = estaOcupada; }
}
