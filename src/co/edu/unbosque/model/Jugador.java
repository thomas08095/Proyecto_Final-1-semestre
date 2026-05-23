package co.edu.unbosque.model;
/**
 * Clase que representa el componente lógico del Jugador dentro del modelo.
 * Modela el agente del usuario (Infiltrador) que navega por el servidor de archivos,
 * registrando su posición matricial (fila, columna), la ruta de su recurso visual 
 * y el contador de movimientos disponibles antes de ser detectado o desconectado.
 */
public class Jugador {
	/** Coordenada de fila correspondiente a la ubicación actual del jugador en la cuadrícula.
	 */
	private int fila;
	/** Coordenada de columna correspondiente a la ubicación actual del jugador en la cuadrícula.
	 */
	private int columna;
	/** Ruta de almacenamiento local del recurso de imagen o textura asignado al jugador. 
	 */
	private String rutaImagen;
	/** Cantidad de saltos o movimientos lógicos remanentes que posee el jugador en el nivel.
	  */
	private int movimientosRestantes;
	/**
	 * Constructor parametrizado de la clase Jugador.
	 * Inicializa las coordenadas de spawn o reaparición en el tablero y asienta 
	 * la cantidad inicial de movimientos según las reglas dictadas por la dificultad.
	 *  @param fila Fila inicial en la que se posicionará el personaje.
	 * @param columna Columna inicial en la que se posicionará el personaje.
	 * @param movimientosIniciales Límite máximo de movimientos cargados para la sesión.
	 */
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
