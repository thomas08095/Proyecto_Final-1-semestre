package co.edu.unbosque.model;

import java.util.Random;

public class PuertoEnlace {

//Atributos
	private int fila;
	private int columna;
	private String rutaImagen;

	private int[] filaP;
	private int[] columnaP;
	private int cantidad;
	private Random rand;
	private int nCasillas;
	private int[] orden;

//Clase Creadora
	public PuertoEnlace() {
		this.cantidad = 0;
		this.nCasillas = 0;
		filaP = new int[0];
		columnaP = new int[0];
		orden = new int[0];
		rand = new Random();
	}

	public PuertoEnlace(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		rand = new Random();
	}

//Getters && Setters
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

	public int[] getFilaP() {
		return filaP;
	}

	public void setFilaP(int[] filaP) {
		this.filaP = filaP;
	}

	public int[] getColumnaP() {
		return columnaP;
	}

	public void setColumnaP(int[] columnaP) {
		this.columnaP = columnaP;
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

//Metodos
	public void RandomPuertoEnlace(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		filaP = new int[cantidad];
		columnaP = new int[cantidad];
		orden = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			filaP[i] = rand.nextInt(nCasillas);
			columnaP[i] = rand.nextInt(nCasillas);
			orden[i] = i + 1;
		}
	}

	public void OrdenCorrecto() {

	}

}
