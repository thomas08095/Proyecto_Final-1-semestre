package co.edu.unbosque.model;

import java.util.Random;

public class NodoEnergia {

	private int fila;
	private int columna;
	private String rutaImagen;

	private int[] filaNE;
	private int[] columnaNE;
	private int cantidad;
	private Random rand;
	private int nCasillas;

	public NodoEnergia() {
		this.cantidad = 0;
		this.nCasillas = 0;
		filaNE = new int[0];
		columnaNE = new int[0];
		rand = new Random();
	}

	public NodoEnergia(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		rand = new Random();
	}

	public void RandomNodoEnergia(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		filaNE = new int[cantidad];
		columnaNE = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			filaNE[i] = rand.nextInt(nCasillas);
			columnaNE[i] = rand.nextInt(nCasillas);
			System.out.println("NODO DE ENERGÍA " + (i + 1) + ": " + filaNE[i] + "," + columnaNE[i]);
		}
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public int[] getFilaNE() {
		return filaNE;
	}

	public void setFilaNE(int[] filaNE) {
		this.filaNE = filaNE;
	}

	public int[] getColumnaNE() {
		return columnaNE;
	}

	public void setColumnaNE(int[] columnaNE) {
		this.columnaNE = columnaNE;
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
}
