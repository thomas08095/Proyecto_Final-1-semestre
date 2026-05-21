package co.edu.unbosque.model;

public class Casilla {

    private int fila;
    private int columna;
    private Entidad contenido;
    private boolean estaOcupada;
    private boolean tieneRastro; 

    public Casilla(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estaOcupada = false;
        this.tieneRastro = false;
    }

    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    
    public Entidad getContenido() { return contenido; }
    public void setContenido(Entidad contenido) { this.contenido = contenido; }
    
    public boolean isEstaOcupada() { return estaOcupada; }
    public void setEstaOcupada(boolean estaOcupada) { this.estaOcupada = estaOcupada; }

    // GETTER Y SETTER NUEVOS
    public boolean isTieneRastro() { return tieneRastro; }
    public void setTieneRastro(boolean tieneRastro) { this.tieneRastro = tieneRastro; }
}