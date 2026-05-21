package co.edu.unbosque.model;

public class Movimiento {

    private int infiltradoX;
    private int infiltradoY;
    private boolean[][] rastro; // <- Matriz para recordar el camino recorrido

    public Movimiento() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
        this.rastro = new boolean[20][20]; 
        this.rastro[0][0] = true; 
    }

    public void inicializarRastro(int filas, int columnas) {
        this.rastro = new boolean[filas][columnas];
        resetPosicion();
    }

    public boolean mover(int deltaX, int deltaY, int filas, int columnas) {
        int posicionX = infiltradoX + deltaX;
        int posicionY = infiltradoY + deltaY;
        
        if (posicionX >= 0 && posicionX < filas && posicionY >= 0 && posicionY < columnas) {
            infiltradoX = posicionX;
            infiltradoY = posicionY;

            rastro[infiltradoX][infiltradoY] = true; 
            return true;
        }
        return false;
    }

    public void resetPosicion() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
        for (int i = 0; i < rastro.length; i++) {
            for (int j = 0; j < rastro[i].length; j++) {
                rastro[i][j] = false;
            }
        }
        this.rastro[0][0] = true; 
    }

    public boolean esCasillaVisitada(int fila, int columna) {
        if (fila >= 0 && fila < rastro.length && columna >= 0 && columna < rastro[0].length) {
            return rastro[fila][columna];
        }
        return false;
    }

    public int getInfiltradoX() {
        return infiltradoX;
    }

    public void setInfiltradoX(int infiltradoX) {
        this.infiltradoX = infiltradoX;
    }

    public int getInfiltradoY() {
        return infiltradoY;
    }

    public void setInfiltradoY(int infiltradoY) {
        this.infiltradoY = infiltradoY;
    }
}