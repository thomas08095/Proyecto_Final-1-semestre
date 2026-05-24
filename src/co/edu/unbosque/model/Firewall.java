package co.edu.unbosque.model;

import java.util.Random;
/**
 * Clase que representa el componente lógico de los Firewall dentro del modelo.
 * Modela los obstáculos estáticos distribuidos en el servidor de archivos,
 * cuya función principal consiste en bloquear el paso del script de infiltración, obligando 
 * al jugador a recalcular sus rutas de movimiento por la red.
 */
public class Firewall {
	/** Arreglo que almacena las coordenadas de las filas para todos los cortafuegos generados. */
	private int[] fila;
	/** Arreglo que almacena las coordenadas de las columnas para todos los cortafuegos generados. */
	private int[] columna;
	/** Cantidad total de cortafuegos que se instanciarán de forma física en el nivel.
	  */
	private int cantidad;
	/** Generador de números aleatorios utilizado para calcular la dispersión en el tablero.
	 */
	private Random rand;
	/** Dimensión o número de casillas por lado que posee el tablero de juego.
	 */
	private int nCasillas;
	/** Ruta de almacenamiento local del recurso de imagen que identifica visualmente al firewall.
	  */
	private String rutaImagen;
	/**
	 * Metodo constructor por defecto de la clase Firewall.
	 * Inicializa los arreglos de coordenadas posicionales con una longitud de cero e 
	 * instancia el generador de números aleatorios para la posterior creación de obstáculos.
	 */
	public Firewall() {
		this.cantidad = 0;
		this.nCasillas = 0;
		this.fila = new int[0];
		this.columna= new int[0];
		this.rand = new Random();
	}
	/**
	 * Metodo constructor parametrizado de la clase Firewall.
	 * Actúa como un constructor especializado para crear una celda individual ocupada por un 
	 * firewall en la Matriz, envolviendo las coordenadas fijas provistas en arreglos unitarios.
	 * @param fila Coordenada de la fila fija para este obstáculo.
	 * @param columna Coordenada de la columna fija para este obstáculo.
	 */
		public Firewall(int fila, int columna) {
		this.fila = new int[] { fila };
		this.columna = new int[] { columna };
	}
		/**
		 * Distribuye de forma aleatoria los obstáculos Firewall a lo largo y ancho del servidor.
		 * Inicializa los arreglos con la cantidad especificada y calcula posiciones cartesianas uniformes 
		 * acotadas por el límite de casillas del mapa, imprimiendo las coordenadas en consola para auditoría.
		 * @param cantidad Número total de obstáculos de tipo cortafuegos a generar en la partida.
		 * @param nCasillas Cantidad total de casillas por lado en el tablero para delimitar la aleatoriedad.
		 */
	public void RandomFirewall(int cantidad, int nCasillas) {
		this.cantidad = cantidad;
		this.nCasillas = nCasillas;
		this.fila = new int[cantidad];
		this.columna = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			fila[i] = rand.nextInt(nCasillas);
			columna[i] = rand.nextInt(nCasillas);
			System.out.println("FIREWALL " + (i + 1) + ": " + fila[i] + "," + columna[i]);
		}
	}

	/**
	 * Verifica si la posición del Script de Infiltración (paquete) se encuentra entre dos
	 * Firewalls en la misma fila o en la misma columna.
	 * Si la casilla del paquete está en medio de dos firewalls (uno a cada lado, horizontal
	 * o verticalmente), se aplica una penalización equivalente a la suma de las coordenadas
	 * de la posición donde se encuentra el paquete (filaPaquete + columnaPaquete).
	 * @param filaPaquete    Fila actual del Script de Infiltración en el tablero.
	 * @param columnaPaquete Columna actual del Script de Infiltración en el tablero.
	 * @return La penalización calculada (filaPaquete + columnaPaquete) si el paquete está
	 *         entre dos Firewalls; 0 en caso contrario.
	 */
	public int calcularPenalizacionEntreFirewalls(int filaPaquete, int columnaPaquete) {
		boolean firewallArriba    = false;
		boolean firewallAbajo     = false;
		boolean firewallIzquierda = false;
		boolean firewallDerecha   = false;

		for (int i = 0; i < cantidad; i++) {
			// Firewalls en la misma columna (eje vertical)
			if (columna[i] == columnaPaquete) {
				if (fila[i] < filaPaquete) firewallArriba = true;
				if (fila[i] > filaPaquete) firewallAbajo  = true;
			}
			// Firewalls en la misma fila (eje horizontal)
			if (fila[i] == filaPaquete) {
				if (columna[i] < columnaPaquete) firewallIzquierda = true;
				if (columna[i] > columnaPaquete) firewallDerecha   = true;
			}
		}

		boolean entreVertical   = firewallArriba && firewallAbajo;
		boolean entreHorizontal = firewallIzquierda && firewallDerecha;

		if (entreVertical || entreHorizontal) {
			return filaPaquete + columnaPaquete;
		}
		return 0;
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

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

}