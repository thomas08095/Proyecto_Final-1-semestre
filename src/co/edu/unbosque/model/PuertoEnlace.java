package co.edu.unbosque.model;

import java.util.Random;
/**
 * Clase que representa el componente lógico de los Puertos de Enlace dentro del modelo.
 * Gestiona el posicionamiento aleatorio de los puntos de acceso de red que el 
 * jugador debe capturar secuencialmente, permitiendo parametrizar la 
 * cantidad de objetivos y la inversión en el orden de recolección de los mismos.
 */
public class PuertoEnlace {
	/** Arreglo que almacena las coordenadas de las filas para cada puerto de enlace generado.
	*/
	private int[] fila;
	/** Arreglo que almacena las coordenadas de las columnas para cada puerto de enlace generado.
	 */
	private int[] columna;
	/** Cantidad total de puertos de enlace que se instanciarán en el escenario actual.
	 */
	private int cantidad;
	/** Generador de números aleatorios para determinar las posiciones en la matriz.
	 * */
	private Random rand;
	/** Número de casillas por lado que posee el tablero de juego.
	 */
	private int nCasillas;
	/** Arreglo encargado de guardar el orden numérico asignado a cada puerto para su captura.
	  */
	private int[] aorden;
	/** Ruta de ubicación del recurso gráfico o textura que identifica visualmente al puerto.
	 */
	private String rutaImagen;
	/** Bandera que determina si la secuencia de captura debe ser normal o inversa. 
	 */
	private boolean ordenInverso;
	/**
	 * Metodo constructor por defecto de PuertoEnlace.
	 * Inicializa los arreglos de coordenadas y orden con un tamaño de cero, 
	 * instancia el generador de números aleatorios y establece por defecto 
	 * la bandera de orden inverso en falso.
	 */
	public PuertoEnlace() {
		this.cantidad = 0;
		this.nCasillas = 0;
		fila = new int[0];
		columna = new int[0];
		aorden = new int[0];
		rand = new Random();
		this.ordenInverso = false;
	}
	/**
	 * Metodo constructor parametrizado para la inicialización instantánea de un único puerto de enlace.
	 * Asigna coordenadas fijas y empaqueta los datos en arreglos unitarios de tamaño uno.
	 * * @param fila Coordenada de la fila donde se ubicará el puerto.
	 * @param columna Coordenada de la columna donde se ubicará el puerto.
	 */
	public PuertoEnlace(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}
	/**
	 * Despliega y distribuye de forma aleatoria los puertos de enlace dentro del servidor.
	 * Los puertos se generan asegurando un margen de seguridad interno dentro de las dimensiones del tablero 
	 * y se indexan numéricamente de manera ascendente (1, 2, 3...) o descendente (...3, 2, 1) en función de 
	 * si la bandera 'ordenInverso' se encuentra activa al momento de la configuración de la partida.
	 * * @param cantidad Número total de puertos de enlace a ser distribuidos en la red.
	 * @param nCasillas Número total de casillas del tablero para delimitar los rangos de aleatoriedad.
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