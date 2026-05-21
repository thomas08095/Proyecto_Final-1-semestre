package co.edu.unbosque.model;

import java.util.Random;

public class Firewall {

	private int[] fila;
	private int[] columna;
	private int cantidad;
	private Random rand;
	private int nCasillas;
	private String rutaImagen;

	public Firewall() {
		this.cantidad = 0;
		this.nCasillas = 0;
		this.fila = new int[0];
		this.columna= new int[0];
		this.rand = new Random();
	}

	// Constructor con parámetros (Actúa como un objeto individual para la Matriz)
	public Firewall(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}

	public void RandomFirewall(int cantidad, int nCasillas) {
		int columnaFija = 1 + rand.nextInt(nCasillas - 2);
		int filaInicio = 1 + rand.nextInt(nCasillas - cantidad - 1);
		this.cantidad = cantidad;
		this.nCasillas = nCasillas;
		this.fila = new int[cantidad];
		this.columna = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			fila[i] = filaInicio + i;
			columna[i] = columnaFija;
			System.out.println("FIREWALL " + (i + 1) + ": " + fila[i] + "," + columna[i]);
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
