package co.edu.unbosque.model;
/**
 * Clase que representa el componente lógico de la Matriz dentro del modelo del juego.
 * Se encarga de centralizar e indexar todas las entidades dinámicas y estáticas del juego 
 * (Jugador, Paquete de Datos, Antivirus, Cortafuegos, Escáneres, Nodos de Energía y Puertos) 
 * dentro de una cuadrícula bidimensional de objetos de tipo,gestionando su poblamiento inicial 
 * mediante la lectura selectiva de coordenadas.
 */
public class Matriz {
	/** Cantidad de filas que componen la dimensión vertical de la cuadrícula de red.
	 */
	private int filas;
	/** Cantidad de columnas que componen la dimensión horizontal de la cuadrícula de red.
	 */
	private int columnas;
	/** Arreglo bidimensional de casillas que consolida el estado físico y contenido de la matriz.
	  */
	private Casilla[][] casillas;
	/** Instancia lógica del Jugador activo dentro de la simulación. 
	 */
	private Jugador jugador;
	/** Instancia del Paquete de Datos que recorre el sistema de archivos de manera histórica.
	 */
	private PaqueteDato paquete;
	/** Colección de hilos o unidades enemigas de Antivirus Proactivos presentes en el mapa. 
	 */
	private AntivirusProactivo[] listaAntivirus;
	/** Colección de obstáculos estáticos infranqueables de tipo Firewall generados en la red.
	  */
	private Firewall[] listaFirewall;
	/** Colección de amenazas pasivas o Escáneres de Latencia distribuidos en el servidor. 
	*/
	private EscanerLatencia[] listaEscaners;
	/** Colección de fuentes de recarga indexadas como Nodos de Energía en la sesión. 
	 */
	private NodoEnergia[] listaNodos;
	/** Colección de puntos de control obligatorios o Puertos de Enlace a hackear en orden.
	 */
	private PuertoEnlace[] listaPuertosEnlace;
	/**
	 * Metodo constructor parametrizado de la clase Matriz.
	 * Instancia la cuadrícula bidimensional de casillas y ejecuta un algoritmo de barrido 
	 * secuencial por celdas (i, j). En cada posición, evalúa de forma estratificada la presencia 
	 * de las entidades mediante la comparación de sus coordenadas actuales y, en caso de coincidencia, 
	 * encapsula su ruta gráfica en una nueva para ocupar la casilla.
	 * @param filas Número de filas asignadas al tablero.
	 * @param columnas Número de columnas asignadas al tablero.
	 * @param jugador Instancia del jugador para su posicionamiento.
	 * @param paquete Instancia del paquete de datos para registrar su punto de partida.
	 * @param listaAntivirus Arreglo con los antivirus enemigos calculados para el nivel.
	 * @param listaEscaners Arreglo con los escáneres de latencia asignados.
	 * @param listaNodos Arreglo con los nodos de energía disponibles para recarga.
	 * @param listaPuertosEnlace Arreglo con la secuencia de puertos de enlace a capturar.
	 * @param listaFirewall Arreglo con los cortafuegos inamovibles de la partida.
	 */
	public Matriz(int filas, int columnas, Jugador jugador, PaqueteDato paquete, AntivirusProactivo[] listaAntivirus,
			EscanerLatencia[] listaEscaners, NodoEnergia[] listaNodos, PuertoEnlace[] listaPuertosEnlace,
			Firewall[] listaFirewall) {
		this.filas = filas;
		this.columnas = columnas;
		this.jugador = jugador;
		this.paquete = paquete;
		this.listaAntivirus = listaAntivirus;
		this.listaEscaners = listaEscaners;
		this.listaNodos = listaNodos;
		this.listaPuertosEnlace = listaPuertosEnlace;
		this.listaFirewall = listaFirewall;
		this.casillas = new Casilla[filas][columnas];
	

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla(i, j);    
				// 1. Jugador
				if (jugador != null && jugador.getFila() == i && jugador.getColumna() == j) {
					casillas[i][j].setContenido(new Entidad(i, j, jugador.getRutaImagen()));
					casillas[i][j].setEstaOcupada(true);
				}

				// 2. Paquete
				if (paquete != null && paquete.getFila() == i && paquete.getColumna() == j) {
					casillas[i][j].setContenido(new Entidad(i, j, paquete.getRutaImagen()));
					casillas[i][j].setEstaOcupada(true);
				}

				// 3. AntivirusProactivo
				if (listaAntivirus != null) {
					for (AntivirusProactivo antivirus : listaAntivirus) {
						if (antivirus.getFila()[0] == i && antivirus.getColumna()[0] == j) {
							casillas[i][j].setContenido(new Entidad(i, j, antivirus.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}

				// 4. EscanerLatencia
				if (listaEscaners != null) {
					for (EscanerLatencia escaner : listaEscaners) {
						if (escaner.getFila()[0] == i && escaner.getColumna()[0] == j) {
							casillas[i][j].setContenido(new Entidad(i, j, escaner.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}

				// 5. NodoEnergia
				if (listaNodos != null) {
					for (NodoEnergia nodo : listaNodos) {
						if (nodo.getFila() == i && nodo.getColumna() == j) {
							casillas[i][j].setContenido(new Entidad(i, j, nodo.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}

				// 6. PuertoEnlace
				if (listaPuertosEnlace != null) {
					for (PuertoEnlace puerto : listaPuertosEnlace) {
						if (puerto.getFila()[0] == i && puerto.getColumna()[0] == j) {
							casillas[i][j].setContenido(new Entidad(i, j, puerto.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}

				// 7. Firewall
				if (listaFirewall != null) {
					for (Firewall firewall : listaFirewall) {
						if (firewall.getFila()[0] == i && firewall.getColumna()[0] == j) {
							casillas[i][j].setContenido(new Entidad(i, j, firewall.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}
			}
		}
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public PaqueteDato getPaquete() {
		return paquete;
	}

	public void setPaquete(PaqueteDato paquete) {
		this.paquete = paquete;
	}

	public AntivirusProactivo[] getListaAntivirus() {
		return listaAntivirus;
	}

	public void setListaAntivirus(AntivirusProactivo[] listaAntivirus) {
		this.listaAntivirus = listaAntivirus;
	}

	public Firewall[] getListaFirewall() {
		return listaFirewall;
	}

	public void setListaFirewall(Firewall[] listaFirewall) {
		this.listaFirewall = listaFirewall;
	}

	public EscanerLatencia[] getListaEscaners() {
		return listaEscaners;
	}

	public void setListaEscaners(EscanerLatencia[] listaEscaners) {
		this.listaEscaners = listaEscaners;
	}

	public NodoEnergia[] getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(NodoEnergia[] listaNodos) {
		this.listaNodos = listaNodos;
	}

	public PuertoEnlace[] getListaPuertosEnlace() {
		return listaPuertosEnlace;
	}

	public void setListaPuertosEnlace(PuertoEnlace[] listaPuertosEnlace) {
		this.listaPuertosEnlace = listaPuertosEnlace;
	}
}