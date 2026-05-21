package co.edu.unbosque.model;

import java.util.Random;

public class AntivirusProactivo {
	private int[] fila;
	private int[] columna;
	private int cantidad;
	private Random rand;
	private int nCasillas;
	private String rutaImagen;

	// Constructor vacío (Actúa como Gestor para generar posiciones aleatorias)
	public AntivirusProactivo() {
		this.cantidad = 0;
		this.nCasillas = 0;
		this.fila = new int[0];
		this.columna= new int[0];
		this.rand = new Random();
	}

	// Constructor con parámetros (Actúa como un objeto individual para la Matriz)
	public AntivirusProactivo(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}

	public void RandomAntivirus(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		this.nCasillas = nCasillas;
		this.fila = new int[cantidad];
		this.columna = new int[cantidad];

		for (int i = 0; i < cantidad; i++) {
			fila[i] = rand.nextInt(nCasillas);
			columna[i] = rand.nextInt(nCasillas);
			System.out.println("ANTIVIRUS " + (i + 1) + ": " + fila[i] + "," + columna[i]);
		}
	}

	public void moverAleatoriamente(int filas, int columnas) {
		for (int i = 0; i < cantidad; i++) {
			boolean movido = false;
			int intentos = 0;

			while (!movido && intentos < 4) {
				int direccion = rand.nextInt(4);
				int nuevaFila = fila[i];
				int nuevaCol = columna[i];

				switch (direccion) {
				case 0:
					nuevaFila--;
					break;
				case 1:
					nuevaFila++;
					break;
				case 2:
					nuevaCol--;
					break;
				case 3:
					nuevaCol++;
					break;
				}

				if (nuevaFila >= 0 && nuevaFila < filas && nuevaCol >= 0 && nuevaCol < columnas) {
					fila[i] = nuevaFila;
					columna[i] = nuevaCol;
					movido = true;
				}
				intentos++;
			}
		}
	}

	public int[] getFila() {
		return fila;
	}

	public void setFila(int[] fila) {
		this.fila = fila;
	}

	public int[] getColumna() {
		return columna;
	}

	public void setColumna(int[] columna) {
		this.columna = columna;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public int getnCasillas() {
		return nCasillas;
	}

	public void setnCasillas(int nCasillas) {
		this.nCasillas = nCasillas;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	


}