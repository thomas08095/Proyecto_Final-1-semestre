package co.edu.unbosque.model;

public class Fachada {

    private Tablero tablero;
    private Dificultad dificultad;
    private Movimiento movimiento;
    private AntivirusProactivo antivirusP;
    private EscanerLatencia escanerL;
    private NodoEnergia nodoE;
    private PuertoEnlace puertoE;
    private Firewall firewall;
    private Matriz matriz;
    private PaqueteDato paquete;
    private int movimientos;
    
    // Variables para el control de los Puertos de Enlace
    private boolean ordenInverso = false;
    private int ultPuertoIncFila = -1;
    private int ultPuertoIncCol = -1;
    private boolean modoSigiloActivo = false;
    private HistorialPartida historial;

	// Variables para el control de los Puertos de Enlace
	private boolean ordenInverso = false;
	private int ultPuertoIncFila = -1;
	private int ultPuertoIncCol = -1;
	private boolean modoSigiloActivo = false;

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
		ordenInverso = false;
		ultPuertoIncFila = -1;
		ultPuertoIncCol = -1;
	}


	// Recibe el tamaño ingresado como String y lo parsea internamente

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

	public int numeroCasillas() {
		return tablero.getNumeroCasillas() * tablero.getNumeroCasillas();
	}

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

	// LÓGICA DE MOVIMIENTO 


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
		}

		return movimiento.mover(deltaX, deltaY, tablero.getFilas(), tablero.getColumnas());
	}  public void reconstruirMatriz() {
		// === RESPALDAR LOS RASTROS DE CASILLAS ANTES DE RECREAR EL OBJETO ===
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

		// === RESTAURAR LOS RASTROS DE CASILLAS PISADAS ANTERIORMENTE ===
		if (rastrosAnteriores != null) {
			for (int i = 0; i < tablero.getFilas(); i++) {
				for (int j = 0; j < tablero.getColumnas(); j++) {
					if (rastrosAnteriores[i][j]) {
						matriz.getCasillas()[i][j].setEsRastro(true);
					}
				}
			}
		}
		// === MARCA EL RASTRO ÚNICAMENTE EN LA UBICACIÓN EN CALIENTE DEL PAQUETE DE DATOS ===
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

	public void activarSigilo() {
		modoSigiloActivo = true;
	}

	public void desactivarSigilo() {
		modoSigiloActivo = false;
	}

	public boolean isModoSigiloActivo() {
		return modoSigiloActivo;
	}
}
