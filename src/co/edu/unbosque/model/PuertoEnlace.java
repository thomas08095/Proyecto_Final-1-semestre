package co.edu.unbosque.model;

import java.util.Random;

public class PuertoEnlace {

//Atributos
	
	private int[] fila;
	private int[] columna;
	private int cantidad;
	private Random rand;
	private int nCasillas;
	private int[] orden;
	private String rutaImagen;

//Clase Creadora
	public PuertoEnlace() {
		this.cantidad = 0;
		this.nCasillas = 0;
		fila = new int[0];
		columna = new int[0];
		orden = new int[0];
		rand = new Random();
	}

	public PuertoEnlace(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}

//Metodos
	public void RandomPuertoEnlace(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		fila = new int[cantidad];
		columna = new int[cantidad];
		orden = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			fila[i] = 1 + rand.nextInt(nCasillas - 2);
			columna[i] = 1 + rand.nextInt(nCasillas - 2);
			System.out.println("PUERTO ENLACE " + (i + 1) + ": " + fila[i] + "," + columna[i]);


		}
	}
//Getters && Setters
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

	public int[] getOrden() {
		return orden;
	}

	public void setOrden(int[] orden) {
		this.orden = orden;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

}
