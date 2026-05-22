package co.edu.unbosque.model;

import java.util.Random;

public class PuertoEnlace {

	private int[] fila;
	private int[] columna;
	private int cantidad;
	private Random rand;
	private int nCasillas;
	private int[] aorden;
	private String rutaImagen;
	private boolean ordenInverso;

	public PuertoEnlace() {
		this.cantidad = 0;
		this.nCasillas = 0;
		fila = new int[0];
		columna = new int[0];
		aorden = new int[0];
		rand = new Random();
		this.ordenInverso = false;
	}

	public PuertoEnlace(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}

	/*
	 * Los puertos de enlace están dispuestos en el servidor en un orden determinado. El Script de Infiltración debe 
	 * recorrer los puertos en ese orden a menos que el usuario, al inicio del juego, elija recorrerlos en orden inverso
	 */
	public void RandomPuertoEnlace(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		fila = new int[cantidad];
		columna = new int[cantidad];
		aorden = new int[cantidad];

		for (int i = 0; i < cantidad; i++) {
			fila[i] = rand.nextInt(nCasillas - 2) + 1;
			columna[i] = rand.nextInt(nCasillas - 2) + 1;
			
			if (ordenInverso) {
				aorden[i] = cantidad - i;
			} else {
				aorden[i] = i + 1;
			}
			
			System.out.println("PUERTO ENLACE " + aorden[i] + ": " + fila[i] + "," + columna[i]);
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
	public int[] getAorden() {
		return aorden;
	}

	public void setAorden(int[] aorden) {
		this.aorden = aorden;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public boolean isOrdenInverso() {
		return ordenInverso;
	}

	public void setOrdenInverso(boolean ordenInverso) {
		this.ordenInverso = ordenInverso;
	}
}