package co.edu.unbosque.model;

public class Tablero {

	private int filas;
	private int columnas;
	private int[][] matriz;

	public Tablero(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.matriz = new int[filas][columnas];
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

	public void imprimirMatriz() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}
}
