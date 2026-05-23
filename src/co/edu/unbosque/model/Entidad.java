package co.edu.unbosque.model;

/**
 * Clase base que representa cualquier entidad visible dentro de la matriz del juego.
 * Almacena la posición en el tablero y la ruta de la imagen que la representa visualmente.
 * Es usada como contenido de cada Casilla para el renderizado gráfico.
 */
public class Entidad {

	/** Fila en la que se encuentra la entidad dentro de la matriz. */
	private int fila;

	/** Columna en la que se encuentra la entidad dentro de la matriz. */
	private int columna;

	/** Ruta relativa al archivo de imagen que representa visualmente esta entidad. */
	private String rutaImagen;

	/**
	 * Constructor de Entidad. Asigna posición e imagen a la entidad.
	 * @param fila Fila de la entidad en la matriz.
	 * @param columna Columna de la entidad en la matriz.
	 * @param rutaImagen Ruta relativa a la imagen de la entidad.
	 */
	public Entidad(int fila, int columna, String rutaImagen) {
		this.fila = fila;
		this.columna = columna;
		this.rutaImagen = rutaImagen;
	}

	public int getFila() { return fila; }
	public void setFila(int fila) { this.fila = fila; }
	public int getColumna() { return columna; }
	public void setColumna(int columna) { this.columna = columna; }
	public String getRutaImagen() { return rutaImagen; }
	public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
