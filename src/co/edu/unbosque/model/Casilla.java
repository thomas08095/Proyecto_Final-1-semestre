package co.edu.unbosque.model;

/**
 * Representa una celda individual dentro de la matriz del tablero.
 * Cada casilla conoce su posición, si está ocupada por una entidad,
 * qué entidad contiene y si forma parte del rastro del paquete de datos.
 */
public class Casilla {

	/** Fila de esta casilla en la matriz. */
	private int fila;

	/** Columna de esta casilla en la matriz. */
	private int columna;

	/** Entidad que ocupa actualmente esta casilla, o null si está vacía. */
	private Entidad contenido;

	/** Indica si la casilla está ocupada por alguna entidad del juego. */
	private boolean estaOcupada;

	/** Indica si esta casilla forma parte del rastro dejado por el paquete de datos. */
	private boolean esRastro = false;

	/**
	 * Constructor de Casilla. Inicializa la posición y la deja vacía y libre.
	 * @param fila Fila de la casilla en la matriz.
	 * @param columna Columna de la casilla en la matriz.
	 */
	public Casilla(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		this.contenido = null;
		this.estaOcupada = false;
	}

	public boolean isEsRastro() { return esRastro; }
	public void setEsRastro(boolean esRastro) { this.esRastro = esRastro; }
	public int getFila() { return fila; }
	public void setFila(int fila) { this.fila = fila; }
	public int getColumna() { return columna; }
	public void setColumna(int columna) { this.columna = columna; }
	public Entidad getContenido() { return contenido; }
	public void setContenido(Entidad contenido) { this.contenido = contenido; }
	public boolean isEstaOcupada() { return estaOcupada; }
	public void setEstaOcupada(boolean estaOcupada) { this.estaOcupada = estaOcupada; }
}
