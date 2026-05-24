package co.edu.unbosque.model;
/**
 * Clase que representa el componente lógico de la Fachada.
 * Centraliza e interconecta los subsistemas de juego, coordinando
 * la configuración del tablero por dificultades, las validaciones perimetrales, el ciclo 
 * de movimiento de las amenazas inteligentes (Antivirus y Escáneres), la interacción con 
 * los ítems y la exportación de auditorías mediante el historial de partida.
 */
public class Fachada {
	/** Instancia encargada de dimensionar el tamaño geométrico de la cuadrícula.
    */
    private Tablero tablero;
    /** Instancia que indexa los textos descriptivos de los niveles de dificultad.
      */
    private Dificultad dificultad;
    /** Gestor de las coordenadas cartesianas del agente infiltrado del usuario.
      */
    private Movimiento movimiento;
    /** Representa el pool y comportamiento de las amenazas móviles de Antivirus Proactivos.
      */
    private AntivirusProactivo antivirusP;
    /** Representa el pool y comportamiento de las amenazas de Escáner de Latencia. 
     */
    private EscanerLatencia escanerL;
    /** Componente lógico que administra la generación de fuentes de recarga de energía.
     */
    private NodoEnergia nodoE;
    /** Componente lógico que supervisa la secuencia de captura de los puntos de acceso. 
     */
    private PuertoEnlace puertoE;
    /** Obstáculo estático inamovible diseñado para bloquear celdas de la cuadrícula. 
     */
    private Firewall firewall;
    /** Matriz multicapa que unifica las entidades lógicas con sus representaciones visuales.
     */
    private Matriz matriz;
    /** Paquete de datos que el jugador debe empujar hacia los puertos de enlace.
     */
    private PaqueteDato paquete;
    /** Cantidad total de movimientos permitidos acumulados para la sesión activa. 
     */
    private int movimientos;
    
    // Variables para el control de los Puertos de Enlace
    /** Bandera que determina si se invierte el orden natural de recolección de los puertos.
     */
    private boolean ordenInverso = false;
    /** Almacena la última fila de un puerto donde se intentó una captura inválida. 
     */
    private int ultPuertoIncFila = -1;
    /** Almacena la última columna de un puerto donde se intentó una captura inválida. 
     */
    private int ultPuertoIncCol = -1;
    /** Estado del modificador que altera la visibilidad frente a los escáneres de red.
     */
    private boolean modoSigiloActivo = false;
    /** Indica si el modo sigilo ya fue utilizado en esta partida. Solo puede usarse una vez.
     */
    private boolean sigiloUsado = false;
    /** Registro estructurado interno encargado de transcribir los eventos del juego.
     */
    private HistorialPartida historial;
    /**
     * Metodo constructor por defecto de la clase Fachada.
     * Inicializa todas las instancias del subsistema del modelo y restablece las variables 
     * de control posicional y banderas de juego a sus estados primitivos y neutros.
     */

	public Fachada() {
		dificultad = new Dificultad();
		tablero = new Tablero(5, 5);
		movimiento = new Movimiento();
		antivirusP = new AntivirusProactivo();
		escanerL = new EscanerLatencia();
		nodoE = new NodoEnergia();
		puertoE = new PuertoEnlace();
		firewall = new Firewall();
		movimientos = 0;
		modoSigiloActivo = false;
		sigiloUsado = false;
		ordenInverso = false;
		ultPuertoIncFila = -1;
		ultPuertoIncCol = -1;
	}
	// Recibe el tamaño ingresado como String y lo parsea internamente
	
	/**
     * Configura y distribuye paramétricamente todos los elementos del mapa de juego.
     * Parsea las dimensiones de la cuadrícula, calcula el total de movimientos permitidos, 
     * esparce aleatoriamente las entidades según la dificultad seleccionada, inicializa 
     * el spawn de los personajes y fija los primeros rastros del paquete de datos en la matriz.
     * @param dificultadSeleccionada Nivel de complejidad ("Facil", "Normal", "Dificil").
     * @param casillaSeleccionada Formato de dimensión de la matriz ("10x10", "15x15", "20x20").
     */

	public void configurarTablero(String dificultadSeleccionada, String casillaSeleccionada) {

		if (casillaSeleccionada.equalsIgnoreCase("10x10")) {
			tablero.setNumeroCasillas(10);
			setMovimientos(movimientos = tablero.getNumeroCasillas() * tablero.getNumeroCasillas());
			tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
		} else if (casillaSeleccionada.equalsIgnoreCase("15x15")) {
			tablero.setNumeroCasillas(15);
			tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
		} else if (casillaSeleccionada.equalsIgnoreCase("20x20")) {
			tablero.setNumeroCasillas(20);
			tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
		}
		setMovimientos(movimientos = tablero.getNumeroCasillas() * tablero.getNumeroCasillas());
		tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());

		int nCasillas = tablero.getNumeroCasillas();

		if (dificultadSeleccionada.equalsIgnoreCase("Facil")) {
			antivirusP.RandomAntivirus(2, nCasillas);
			nodoE.RandomNodoEnergia(3, nCasillas);
			escanerL.RandomEscanerL(2, nCasillas);
			puertoE.RandomPuertoEnlace(2, nCasillas);
			firewall.RandomFirewall(4, nCasillas);

		} else if (dificultadSeleccionada.equalsIgnoreCase("Normal")) {
			antivirusP.RandomAntivirus(4, nCasillas);
			nodoE.RandomNodoEnergia(2, nCasillas);
			escanerL.RandomEscanerL(3, nCasillas);
			puertoE.RandomPuertoEnlace(3, nCasillas);
			firewall.RandomFirewall(6, nCasillas);

		} else if (dificultadSeleccionada.equalsIgnoreCase("Dificil")) {
			antivirusP.RandomAntivirus(6, nCasillas);
			nodoE.RandomNodoEnergia(1, nCasillas);
			escanerL.RandomEscanerL(4, nCasillas);
			puertoE.RandomPuertoEnlace(5, nCasillas);
			firewall.RandomFirewall(8, nCasillas);
		}

		movimiento.resetPosicion();
        historial = new HistorialPartida(dificultadSeleccionada, nCasillas);

		Jugador jugador = new Jugador(0, 0, movimientos);
		jugador.setRutaImagen("src/imagenes/jugador.png");

		// Inicializamos el paquete en la posición central del mapa

		int centro = nCasillas / 2;
		paquete = new PaqueteDato(centro, centro, movimientos);
		paquete.setRutaImagen("src/imagenes/paquete_datos.png");

		AntivirusProactivo[] listaAntivirus = new AntivirusProactivo[antivirusP.getCantidad()];
		for (int i = 0; i < antivirusP.getCantidad(); i++) {
			listaAntivirus[i] = new AntivirusProactivo(antivirusP.getFila()[i], antivirusP.getColumna()[i]);
			listaAntivirus[i].setRutaImagen("src/imagenes/antivirus.png");
		}

		NodoEnergia[] listaNodos = new NodoEnergia[nodoE.getCantidad()];
		for (int i = 0; i < nodoE.getCantidad(); i++) {
			listaNodos[i] = new NodoEnergia(nodoE.getFilaNE()[i], nodoE.getColumnaNE()[i]);
			listaNodos[i].setRutaImagen("src/imagenes/nodo_energia.png");
		}

		EscanerLatencia[] listaEscanerLatencia = new EscanerLatencia[escanerL.getCantidad()];
		for (int i = 0; i < escanerL.getCantidad(); i++) {
			listaEscanerLatencia[i] = new EscanerLatencia(escanerL.getFila()[i], escanerL.getColumna()[i]);
			listaEscanerLatencia[i].setRutaImagen("src/imagenes/escaner_latencia.png"); 
		}

		PuertoEnlace[] listaPuertoEnlace = new PuertoEnlace[puertoE.getCantidad()];
		for (int i = 0; i < puertoE.getCantidad(); i++) {
			listaPuertoEnlace[i] = new PuertoEnlace(puertoE.getFila()[i], puertoE.getColumna()[i]);
			listaPuertoEnlace[i].setRutaImagen("src/imagenes/puerto_enlace.png"); 
		}

		Firewall[] listaFirewall = new Firewall[firewall.getCantidad()];
		for (int i = 0; i < firewall.getCantidad(); i++) {
			listaFirewall[i] = new Firewall(firewall.getFila()[i], firewall.getColumna()[i]);
			listaFirewall[i].setRutaImagen("src/imagenes/firewall.png"); 
		}

		matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, listaEscanerLatencia,
				listaNodos, listaPuertoEnlace, listaFirewall);

		ordenInverso = false;
		ultPuertoIncFila = -1;
		ultPuertoIncCol = -1;

		//  FIJA EL RASTRO EN LA CASILLA INICIAL DEL PAQUETE AL EMPEZAR LA PARTIDA
		if (paquete != null) {
			matriz.getCasillas()[paquete.getFila()][paquete.getColumna()].setEsRastro(true);
		}
	}
	/**
     * Calcula el área geométrica total del tablero.
     * @return El número de casillas totales que conforman la cuadrícula de juego.
     */
	public int numeroCasillas() {
		return tablero.getNumeroCasillas() * tablero.getNumeroCasillas();
	}
	/**
     * Evalúa si una coordenada específica se encuentra bloqueada por un elemento restrictivo.
     * Analiza las colecciones de Firewalls, Antivirus y Escáneres para prevenir superposiciones.
     * @param fila Índice de la fila a evaluar.
     * @param columna Índice de la columna a evaluar.
     * @return true si la casilla está ocupada por un obstáculo o amenaza; false en caso contrario.
     */
	private boolean celdaOcupada(int fila, int columna) {
		for (int i = 0; i < firewall.getCantidad(); i++) {
			if (fila == firewall.getFila()[i] && columna == firewall.getColumna()[i]) {
				return true;
			}
		}
		for (int i = 0; i < antivirusP.getCantidad(); i++) {
			if (fila == antivirusP.getFila()[i] && columna == antivirusP.getColumna()[i]) {
				return true;
			}
		}
		for (int i = 0; i < escanerL.getCantidad(); i++) {
			if (fila == escanerL.getFila()[i] && columna == escanerL.getColumna()[i]) {
				return true;
			}
		}
		return false;
	}
	/**
     * Desplaza de forma aleatoria a cada una de las unidades de Antivirus Proactivo.
     * Cada antivirus selecciona una dirección al azar e intenta moverse a ella; la transición
     * se efectúa únicamente si la casilla destino está dentro del rango y libre de otros obstáculos.
     */
	public void moverAntivirus() {
		for (int i = 0; i < antivirusP.getCantidad(); i++) {
			boolean movido = false;
			int intentos = 0;
			while (!movido && intentos < 4) {
				int direccion = antivirusP.getRand().nextInt(4);
				int nuevaFila = antivirusP.getFila()[i];
				int nuevaCol = antivirusP.getColumna()[i];
				switch (direccion) {
				case 0: { nuevaFila--; break; }
				case 1: { nuevaFila++; break; }
				case 2: { nuevaCol--; break; }
				case 3: { nuevaCol++; break; }
				}
				if (nuevaFila >= 0 && nuevaFila < tablero.getFilas()
						&& nuevaCol >= 0 && nuevaCol < tablero.getColumnas()
						&& !celdaOcupada(nuevaFila, nuevaCol)) {
					antivirusP.getFila()[i] = nuevaFila;
					antivirusP.getColumna()[i] = nuevaCol;
					movido = true;
				}
				intentos++;
			}
		}
	}
	/**
     * Desplaza de forma aleatoria a cada uno de los Escáneres de Latencia.
     * Realiza un cálculo de vecindad de 4 direcciones de forma aleatoria, consolidando el
     * cambio posicional solo si respeta los perímetros y no genera colisiones con celdas ocupadas.
     */
	public void moverEscanerL() {
		for (int i = 0; i < escanerL.getCantidad(); i++) {
			boolean movido = false;
			int intentos = 0;
			while (!movido && intentos < 4) {
				int direccion = escanerL.getRand().nextInt(4);
				int nuevaFila = escanerL.getFila()[i];
				int nuevaCol = escanerL.getColumna()[i];
				switch (direccion) {
				case 0: { nuevaFila--; break; }
				case 1: { nuevaFila++; break; }
				case 2: { nuevaCol--; break; }
				case 3: { nuevaCol++; break; }
				}
				if (nuevaFila >= 0 && nuevaFila < tablero.getFilas()
						&& nuevaCol >= 0 && nuevaCol < tablero.getColumnas()
						&& !celdaOcupada(nuevaFila, nuevaCol)) {
					escanerL.getFila()[i] = nuevaFila;
					escanerL.getColumna()[i] = nuevaCol;
					movido = true;
				}
				intentos++;
			}
		}
	}
	/**
     * Verifica si el jugador ha ingresado al rango de intercepción de algún Antivirus.
     * La detección se dispara si el infiltrado ocupa la misma casilla o está adyacente a uno.
     * @return true si el jugador fue detectado en el perímetro de un antivirus; false de lo contrario.
     */
	public boolean detectarAntivirus() {
		for (int i = 0; i < antivirusP.getCantidad(); i++) {
			boolean mismaPosticion = (movimiento.getInfiltradoX() == antivirusP.getFila()[i]
					&& movimiento.getInfiltradoY() == antivirusP.getColumna()[i]);
			boolean enArriba = (movimiento.getInfiltradoX() == antivirusP.getFila()[i] - 1
					&& movimiento.getInfiltradoY() == antivirusP.getColumna()[i]);
			boolean enAbajo = (movimiento.getInfiltradoX() == antivirusP.getFila()[i] + 1
					&& movimiento.getInfiltradoY() == antivirusP.getColumna()[i]);
			boolean enIzquierda = (movimiento.getInfiltradoX() == antivirusP.getFila()[i]
					&& movimiento.getInfiltradoY() == antivirusP.getColumna()[i] - 1);
			boolean enDerecha = (movimiento.getInfiltradoX() == antivirusP.getFila()[i]
					&& movimiento.getInfiltradoY() == antivirusP.getColumna()[i] + 1);

			if (mismaPosticion || enArriba || enAbajo || enIzquierda || enDerecha) {
				return true;
			}
		}
		return false;
	}
	/**
     * Verifica si el jugador se encuentra en las proximidades directas de un Escáner de Latencia.
     * Si se detecta proximidad cruzada, se eliminan las coordenadas del escáner enviándolo fuera del mapa.
     * @return true si el jugador gatilló la alerta de proximidad de un escáner; false de lo contrario.
     */
	public boolean detectarEscanerL() {
		for (int i = 0; i < escanerL.getCantidad(); i++) {
			boolean enArriba = (movimiento.getInfiltradoX() == escanerL.getFila()[i] - 1
					&& movimiento.getInfiltradoY() == escanerL.getColumna()[i]);
			boolean enAbajo = (movimiento.getInfiltradoX() == escanerL.getFila()[i] + 1
					&& movimiento.getInfiltradoY() == escanerL.getColumna()[i]);
			boolean enIzquierda = (movimiento.getInfiltradoX() == escanerL.getFila()[i]
					&& movimiento.getInfiltradoY() == escanerL.getColumna()[i] - 1);
			boolean enDerecha = (movimiento.getInfiltradoX() == escanerL.getFila()[i]
					&& movimiento.getInfiltradoY() == escanerL.getColumna()[i] + 1);

			if (enArriba || enAbajo || enIzquierda || enDerecha) {
				escanerL.getFila()[i] = -1000;
				escanerL.getColumna()[i] = -100;
				return true;
			}
		}
		return false;
	}
	/**
     * Evalúa la colisión exacta entre el infiltrado y un Nodo de Energía.
     * Si coinciden en la misma celda, el nodo es consumido (coordenadas enviadas a -1).
     * @return true si se procesó la recolección exitosa de energía; false en caso contrario.
     */
	public boolean detectarNodoEnergia() {
		for (int i = 0; i < nodoE.getCantidad(); i++) {
			if (movimiento.getInfiltradoX() == nodoE.getFilaNE()[i]
					&& movimiento.getInfiltradoY() == nodoE.getColumnaNE()[i]) {
				nodoE.getFilaNE()[i] = -1;
				nodoE.getColumnaNE()[i] = -1;
				return true;
			}
		}
		return false;
	}
	/**
     * Controla la captura lógica y secuencial de los Puertos de Enlace por parte del Paquete de Datos.
     * Calcula el índice esperado basándose en la bandera 'ordenInverso' y valida si el puerto colisionado 
     * coincide con la secuencia. Si el orden es erróneo, maneja el reintento impidiendo ciclos infinitos de error.
     * @param puertosRecolectados Cantidad total de puertos asegurados con éxito hasta el momento.
     * @return 1 si el puerto fue capturado en el orden correcto; -1 si es incorrecto (primer intento); 
     * 0 si no hay colisión o ya se notificó el error en esa celda.
     */
	public int detectarPuertoEnlace(int puertosRecolectados) {
		int indexEsperado = ordenInverso ? (puertoE.getCantidad() - 1 - puertosRecolectados) : puertosRecolectados;

		for (int i = 0; i < puertoE.getCantidad(); i++) {
			if (paquete.getFila() == puertoE.getFila()[i] && paquete.getColumna() == puertoE.getColumna()[i]) {
				if (i == indexEsperado) {
					puertoE.getFila()[i] = -1;
					puertoE.getColumna()[i] = -1;
					ultPuertoIncFila = -1;
					ultPuertoIncCol = -1;
					return 1; 



				} else {
					if (paquete.getFila() != ultPuertoIncFila || paquete.getColumna() != ultPuertoIncCol) {
						ultPuertoIncFila = paquete.getFila();
						ultPuertoIncCol = paquete.getColumna();

						return -1; 
					}
					return 0;
				}
			}
		}

		ultPuertoIncFila = -1;
		ultPuertoIncCol = -1;     
		return 0;       
	}
	/**
     * Procesa la solicitud de movimiento del jugador y gestiona la física de empuje del paquete de datos.
     * Valida que el jugador no camine sobre celdas ocupadas. Si la celda de destino contiene el paquete de datos,
     * intenta desplazarlo en la misma dirección (delta), validando que este no quede fuera del mapa ni en las bandas perimetrales.
     * @param deltaX Desplazamiento en el eje de las filas.
     * @param deltaY Desplazamiento en el eje de las columnas.
     * @return true si el movimiento global pudo ser ejecutado con éxito; false si fue obstruido.
     */
	public boolean solicitarMovimiento(int deltaX, int deltaY) {
		int proximaFilaJugador = movimiento.getInfiltradoX() + deltaX;
		int proximaColumnaJugador = movimiento.getInfiltradoY() + deltaY;

		if (celdaOcupada(proximaFilaJugador, proximaColumnaJugador)) {
			return false;
		}

		if (paquete != null && proximaFilaJugador == paquete.getFila() && proximaColumnaJugador == paquete.getColumna()) {

			int destinoFilaPaquete = paquete.getFila() + deltaX;
			int destinoColumnaPaquete = paquete.getColumna() + deltaY;

			boolean fueraDelTablero = destinoFilaPaquete < 0 || destinoFilaPaquete >= tablero.getFilas()
					|| destinoColumnaPaquete < 0 || destinoColumnaPaquete >= tablero.getColumnas();
					boolean enBanda = destinoFilaPaquete == 0 || destinoFilaPaquete == tablero.getFilas() - 1
							|| destinoColumnaPaquete == 0 || destinoColumnaPaquete == tablero.getColumnas() - 1;

					if (fueraDelTablero || enBanda) {
						return false;
					}

					paquete.setFila(destinoFilaPaquete);
					paquete.setColumna(destinoColumnaPaquete);

					// Verificar penalización por pasar entre dos Firewalls
					int penalizacion = firewall.calcularPenalizacionEntreFirewalls(paquete.getFila(), paquete.getColumna());
					if (penalizacion > 0) {
						movimientos -= penalizacion;
						System.out.println("PENALIZACION entre Firewalls: -" + penalizacion + " movimientos. Fila=" + paquete.getFila() + " Col=" + paquete.getColumna());
					}
		}

		return movimiento.mover(deltaX, deltaY, tablero.getFilas(), tablero.getColumnas());
		
		/**
	     * Recrea desde cero la Matriz unificada del nivel para refrescar los gráficos tras cambios lógicos.
	     * Implementa un mecanismo de respaldo y restauración de las matrices booleanas de rastro 
	     * para asegurar que las casillas pisadas anteriormente por el paquete se mantengan pintadas.
	     */
	}  public void reconstruirMatriz() {
		boolean[][] rastrosAnteriores = null;
		if (matriz != null && matriz.getCasillas() != null) {
			rastrosAnteriores = new boolean[tablero.getFilas()][tablero.getColumnas()];
			for (int i = 0; i < tablero.getFilas(); i++) {
				for (int j = 0; j < tablero.getColumnas(); j++) {
					rastrosAnteriores[i][j] = matriz.getCasillas()[i][j].isEsRastro();
				}
			}
		}

		Jugador jugador = new Jugador(movimiento.getInfiltradoX(), movimiento.getInfiltradoY(), movimientos);
		jugador.setRutaImagen("src/imagenes/jugador.png");

		AntivirusProactivo[] listaAntivirus = new AntivirusProactivo[antivirusP.getCantidad()];
		for (int i = 0; i < antivirusP.getCantidad(); i++) {
			listaAntivirus[i] = new AntivirusProactivo(antivirusP.getFila()[i], antivirusP.getColumna()[i]);
			listaAntivirus[i].setRutaImagen("src/imagenes/antivirus.png");
		}

		NodoEnergia[] listaNodos = new NodoEnergia[nodoE.getCantidad()];
		for (int i = 0; i < nodoE.getCantidad(); i++) {
			listaNodos[i] = new NodoEnergia(nodoE.getFilaNE()[i], nodoE.getColumnaNE()[i]);
			listaNodos[i].setRutaImagen("src/imagenes/nodo_energia.png");
		}

		EscanerLatencia[] listaEscanerLatencia = new EscanerLatencia[escanerL.getCantidad()];
		for (int i = 0; i < escanerL.getCantidad(); i++) {
			listaEscanerLatencia[i] = new EscanerLatencia(escanerL.getFila()[i], escanerL.getColumna()[i]);
			listaEscanerLatencia[i].setRutaImagen("src/imagenes/escaner_latencia.png"); 
		}

		PuertoEnlace[] listaPuertoEnlace = new PuertoEnlace[puertoE.getCantidad()];
		for (int i = 0; i < puertoE.getCantidad(); i++) {
			listaPuertoEnlace[i] = new PuertoEnlace(puertoE.getFila()[i], puertoE.getColumna()[i]);
			listaPuertoEnlace[i].setRutaImagen("src/imagenes/puerto_enlace.png"); 
		}

		Firewall[] listaFirewall = new Firewall[firewall.getCantidad()];
		for (int i = 0; i < firewall.getCantidad(); i++) {
			listaFirewall[i] = new Firewall(firewall.getFila()[i], firewall.getColumna()[i]);
			listaFirewall[i].setRutaImagen("src/imagenes/firewall.png"); 
		}

		matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, listaEscanerLatencia,
				listaNodos, listaPuertoEnlace, listaFirewall);

		if (rastrosAnteriores != null) {
			for (int i = 0; i < tablero.getFilas(); i++) {
				for (int j = 0; j < tablero.getColumnas(); j++) {
					if (rastrosAnteriores[i][j]) {
						matriz.getCasillas()[i][j].setEsRastro(true);
					}
				}
			}
		}
   		if (paquete != null) {
			matriz.getCasillas()[paquete.getFila()][paquete.getColumna()].setEsRastro(true);
		}
	}

	public int getInfiltradoX() {
		return movimiento.getInfiltradoX();
	}

	public int getInfiltradoY() {
		return movimiento.getInfiltradoY();
	}

	public int getFilas() {
		return tablero.getFilas();
	}

	public int getColumnas() {
		return tablero.getColumnas();
	}

	public String[] getDificultades() {
		return dificultad.getElementos();
	}

	public String[] getCasillas() {
		return tablero.getElementos();
	}

	public int getCantidadAntivirus() {
		return antivirusP.getCantidad();
	}

	public int[] getFilasAntivirus() {
		return antivirusP.getFila();
	}

	public int[] getColumnasAntivirus() {
		return antivirusP.getColumna();
	}

	public int getCantidadNodo() {
		return nodoE.getCantidad();
	}

	public int[] getFilasNodo() {
		return nodoE.getFilaNE();
	}

	public int[] getColumnasNodo() {
		return nodoE.getColumnaNE();
	}

	public Tablero getTablero() {
		return tablero;
	}

	public int getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(int movimientos) {
		this.movimientos = movimientos;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public EscanerLatencia getEscanerL() {
		return escanerL;
	}

	public void setEscanerL(EscanerLatencia escanerL) {
		this.escanerL = escanerL;
	}

	public PuertoEnlace getPuertoE() {
		return puertoE;
	}


	public boolean isOrdenInverso() {
		return ordenInverso;
	}


	public void setOrdenInverso(boolean ordenInverso) {
		this.ordenInverso = ordenInverso;
	}


	public void setPuertoE(PuertoEnlace puertoE) {
		this.puertoE = puertoE;
	}


	public void registrarMovimiento(int turno, int fila, int columna, String evento) {
		if (historial != null) {
			historial.registrarMovimiento(turno, fila, columna, evento);
		}
	}

	public String exportarHistorial(String resultado, int movimientosUsados, int nodosRecolectados) {
		if (historial != null) {
			return historial.exportar(resultado, movimientosUsados, nodosRecolectados);
		}
		return null;
	}

	/**
	 * Intenta activar el modo sigilo. Solo puede usarse una vez por partida.
	 * @return true si el sigilo fue activado con éxito; false si ya fue usado anteriormente.
	 */
	public boolean activarSigilo() {
		if (sigiloUsado) {
			return false;
		}
		modoSigiloActivo = true;
		sigiloUsado = true;
		return true;
	}

	public void desactivarSigilo() {
		modoSigiloActivo = false;
	}

	public boolean isModoSigiloActivo() {
		return modoSigiloActivo;
	}
}