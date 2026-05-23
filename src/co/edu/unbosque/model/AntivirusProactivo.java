package co.edu.unbosque.model;

import java.util.Random;

/**
 * Representa a los antivirus proactivos que se mueven por el tablero como amenazas activas.
 * Opera en dos modos: como gestor que genera y almacena múltiples posiciones aleatorias
 * al iniciar la partida, y como instancia individual que representa un antivirus específico
 * dentro de la matriz para su renderizado.
 */
public class AntivirusProactivo {

	/** Arreglo con las filas de cada antivirus generado. */
	private int[] fila;

	/** Arreglo con las columnas de cada antivirus generado. */
	private int[] columna;

	/** Cantidad de antivirus activos en el tablero. */
	private int cantidad;

	/** Generador de números aleatorios para posicionamiento y movimiento. */
	private Random rand;

	/** Tamaño del tablero en casillas, usado para limitar el movimiento. */
	private int nCasillas;

	/** Ruta relativa a la imagen que representa visualmente al antivirus. */
	private String rutaImagen;

	/**
	 * Constructor vacío. Actúa como gestor para generar y almacenar
	 * las posiciones aleatorias de todos los antivirus de la partida.
	 */
	public AntivirusProactivo() {
		this.cantidad = 0;
		this.nCasillas = 0;
		this.fila = new int[0];
		this.columna = new int[0];
		this.rand = new Random();
	}

	/**
	 * Constructor con parámetros. Actúa como instancia individual
	 * para representar un antivirus específico dentro de la matriz.
	 * @param fila Fila del antivirus en la matriz.
	 * @param columna Columna del antivirus en la matriz.
	 */
	public AntivirusProactivo(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}

	/**
	 * Genera posiciones aleatorias para la cantidad indicada de antivirus
	 * dentro de los límites del tablero e imprime cada posición en consola.
	 * @param cantidad Número de antivirus a generar.
	 * @param nCasillas Tamaño del tablero en casillas por lado.
	 */
	public void RandomAntivirus(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		this.nCasillas = nCasillas;
		this.fila = new int[cantidad];
		this.columna = new int[cantidad];

		for (int i = 0; i < cantidad; i++) {
			fila[i] = rand.nextInt(nCasillas);
			columna[i] = rand.nextInt(nCasillas);
			System.out.println("ANTIVIRUS " + (i + 1) + ": " + fila[i] + "," + columna[i]);
		}
	}

	/**
	 * Mueve cada antivirus en una dirección aleatoria válida dentro del tablero.
	 * Intenta hasta 4 direcciones antes de quedarse en la posición actual.
	 * @param filas Número de filas del tablero.
	 * @param columnas Número de columnas del tablero.
	 */
	public void moverAleatoriamente(int filas, int columnas) {
		for (int i = 0; i < cantidad; i++) {
			boolean movido = false;
			int intentos = 0;

			while (!movido && intentos < 4) {
				int direccion = rand.nextInt(4);
				int nuevaFila = fila[i];
				int nuevaCol = columna[i];

				switch (direccion) {
				case 0: { nuevaFila--; break; }
				case 1: { nuevaFila++; break; }
				case 2: { nuevaCol--; break; }
				case 3: { nuevaCol++; break; }
				}

				if (nuevaFila >= 0 && nuevaFila < filas && nuevaCol >= 0 && nuevaCol < columnas) {
					fila[i] = nuevaFila;
					columna[i] = nuevaCol;
					movido = true;
				}
				intentos++;
			}
		}
	}

	public int[] getFila() { return fila; }
	public void setFila(int[] fila) { this.fila = fila; }
	public int[] getColumna() { return columna; }
	public void setColumna(int[] columna) { this.columna = columna; }
	public int getCantidad() { return cantidad; }
	public void setCantidad(int cantidad) { this.cantidad = cantidad; }
	public Random getRand() { return rand; }
	public void setRand(Random rand) { this.rand = rand; }
	public int getnCasillas() { return nCasillas; }
	public void setnCasillas(int nCasillas) { this.nCasillas = nCasillas; }
	public String getRutaImagen() { return rutaImagen; }
	public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
