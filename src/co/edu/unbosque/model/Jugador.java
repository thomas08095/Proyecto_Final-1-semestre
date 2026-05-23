package co.edu.unbosque.model;

public class Jugador {

	private int fila;
	private int columna;
	private String rutaImagen;
	private int movimientosRestantes;

	public Jugador(int fila, int columna, int movimientosIniciales) {
		this.fila = fila;
		this.columna = columna;
		this.movimientosRestantes = movimientosIniciales;
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

	public int getMovimientosRestantes() {
		return movimientosRestantes;
	}

	public void setMovimientosRestantes(int movimientosRestantes) {
		this.movimientosRestantes = movimientosRestantes;
	}
}
