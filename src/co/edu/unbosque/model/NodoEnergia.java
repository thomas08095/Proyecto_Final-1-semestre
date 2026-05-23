package co.edu.unbosque.model;

import java.util.Random;
/**
 * Clase que representa el componente lógico de los Nodos de Energía dentro del modelo.
 * Gestiona el posicionamiento aleatorio de las fuentes de recarga distribuidas 
 * en el servidor, las cuales permiten al jugador recuperar o extender 
 * la cantidad de movimientos disponibles durante la partida.
  */
public class NodoEnergia {
	/** Coordenada de fila individual utilizada para una instancia específica de un nodo.
	 */
	private int fila;
	/** Coordenada de columna individual utilizada para una instancia específica de un nodo.
	 */
	private int columna;
	/** Ruta de almacenamiento local del recurso de imagen que identifica al nodo de energía. 
	 */
	private String rutaImagen;
	/** Arreglo que almacena las coordenadas de las filas para todos los nodos de energía generados.
	 */
	private int[] filaNE;
	/** Arreglo que almacena las coordenadas de las columnas para todos los nodos de energía generados.
	 */
	private int[] columnaNE;
	/** Cantidad total de nodos de energía que se instanciarán en el escenario de juego. 
	 */
	private int cantidad;
	/** Generador de números aleatorios para determinar las posiciones en la matriz.
    */
	private Random rand;
	/** Número de casillas por lado que posee el tablero de juego.
	 */
	private int nCasillas;
	/**
	 * Metodo constructor por defecto de NodoEnergia.
	 * Inicializa los arreglos de coordenadas con un tamaño de cero e instancia 
	 * el generador de números aleatorios para el cálculo de posiciones.
	 */
	public NodoEnergia() {
		this.cantidad = 0;
		this.nCasillas = 0;
		filaNE = new int[0];
		columnaNE = new int[0];
		rand = new Random();
	}
	/**
	 * Metodo constructor parametrizado para la inicialización instantánea de un único nodo de energía.
	 * Asigna una posición fija en la cuadrícula y prepara el objeto generador de aleatoriedad.
	 *@param fila Coordenada de la fila donde se ubicará el nodo.
	 * @param columna Coordenada de la columna donde se ubicará el nodo.
	 */
	public NodoEnergia(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
		rand = new Random();
	}
	/**
	 * Despliega y distribuye de forma aleatoria los nodos de energía en el mapa del servidor.
	 * Calcula las posiciones basándose en las dimensiones máximas del tablero y almacena las 
	 * coordenadas resultantes en sus respectivos arreglos para su nuevo dibujo.
	 * @param cantidad Número total de nodos de energía a ser distribuidos en la red.
	 * @param nCasillas Número total de casillas del tablero para delimitar los rangos de aleatoriedad.
	 */
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
