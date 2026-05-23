package co.edu.unbosque.model;

import java.util.Random;
/**
 * Representa y gestiona las amenazas móviles de tipo Escáner de Latencia dentro del mapa.
 * Esta clase administra una colección indexada de coordenadas cartesianas correspondiente 
 * a múltiples instancias de escáneres activos. Se encarga de su posicionamiento inicial 
 * pseudoaleatorio en la topología de la red y expone la lógica computacional para el 
 * desplazamiento ortogonal automatizado y caótico por turnos.
 */
public class EscanerLatencia {
	/** Arreglo indexado que almacena la coordenada de la fila actual para cada escáner activo.
	 */
	private int[] fila;
	/** Arreglo indexado que almacena la coordenada de la columna actual para cada escáner activo.
	  */
	private int[] columna;
	/** Cantidad total de unidades de Escáner de Latencia desplegadas de acuerdo a la dificultad.
	 */
	private int cantidad;
	/** Generador de números aleatorios para resolver el posicionamiento y los vectores de movimiento. 
	 */
	private Random rand;
	/** Dimensión física de la matriz del entorno. 
	 */
	private int nCasillas;
	/** Ruta del recurso en disco de la imagen o ícono representativo para el dibujo gráfico.
	  */
	private String rutaImagen;
	/**
	 * Metodo constructor por defecto de la clase EscanerLatencia.
	 * Inicializa los marcadores numéricos de cantidad y límites en cero, parametriza las colecciones 
	 * cartesianas con vectores vacíos e instancia el objeto generador de aleatoriedad.
	 */

	public EscanerLatencia() {
		this.cantidad = 0;
		this.nCasillas = 0;
		this.fila = new int[0];
		this.columna = new int[0];
		this.rand = new Random();
	}
	/**
	 * Constructor parametrizado utilizado para instanciar una amenaza individual en una coordenada explícita.
	 * @param fila Índice inicial de la fila para el elemento.
	 * @param columna Índice inicial de la columna para el elemento.
	 */
	public EscanerLatencia(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}
	/**
	 * Genera posiciones de inicio aleatorias para un volumen determinado de amenazas en el tablero.
	 * la matriz se determina a partir de un valor numérico que el usuario ha proporcionado, para luego
	 * distribuir  
	 * Envía adicionalmente un reporte de traza hacia la consola estándar para auditoría técnica.
	 * @param cantidad Número de escáneres que se deben spawnear en la partida.
	 * @param nCasillas Tamaño total del eje de la cuadrícula del mapa.
	 */
	public void RandomEscanerL(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		this.nCasillas = nCasillas;
		this.fila = new int[cantidad];
		this.columna = new int[cantidad];

		for (int i = 0; i < cantidad; i++) {
			fila[i] = rand.nextInt(nCasillas);
			columna[i] = rand.nextInt(nCasillas);
			System.out.println("ESCANER DE LATENCIA " + (i + 1) + ": " + fila[i] + "," + columna[i]);
		}
	}
	/**
	 * Ejecuta el ciclo de desplazamiento automático para cada escáner en el tablero.
	 * El algoritmo implementa un subbucle de resiliencia limitado a un máximo de 4 intentos;
	 * si la dirección calculada colisiona con las 
	 * fronteras físicas de la matriz (filas o columnas), se descarta el vector y se 
	 * evalúa una nueva ruta para asegurar el movimiento efectivo.
	 * @param filas Altura límite de la matriz bidimensional del juego.
	 * @param columnas Anchura límite de la matriz bidimensional del juego.
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
				case 0:
					nuevaFila--;
					break;
				case 1:
					nuevaFila++;
					break;
				case 2:
					nuevaCol--;
					break;
				case 3:
					nuevaCol++;
					break;
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

	public int[] getFila() {
		return fila;
	}

	public void setFila(int[] fila) {
		this.fila = fila;
	}

	public int[] getColumna() {
		return columna;
	}

	public void setColumnaE(int[] columna) {
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