package co.edu.unbosque.model;

public class Tablero {
    private int filas;
    private int columnas;
    private int[][] matriz;
    private int numeroCasillas;
    private String[] elementos;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int[filas][columnas];
        this.numeroCasillas = filas;
        elementos = new String[4];
        elementos[0] = "";
        elementos[1] = "10x10";
        elementos[2] = "15x15";
        elementos[3] = "20x20";
    }

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int[][] getMatriz() {
		return matriz;
	}

    public int getNumeroCasillas() {
        return numeroCasillas;
    }

    public void setNumeroCasillas(int numeroCasillas) {
        this.numeroCasillas = numeroCasillas;
    }

    public String[] getElementos() {
        return elementos;
    }

    public void setElementos(String[] elementos) {
        this.elementos = elementos;
    }
}
