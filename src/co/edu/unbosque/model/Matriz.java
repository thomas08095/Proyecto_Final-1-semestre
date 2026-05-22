package co.edu.unbosque.model;

public class Matriz {

	private int filas;
	private int columnas;
	private Casilla[][] casillas;
	private Jugador jugador;
	private PaqueteDato paquete;
	private AntivirusProactivo[] listaAntivirus;
	private Firewall[] listaFirewall;
	private EscanerLatencia[] listaEscaners;
	private NodoEnergia[] listaNodos;
	private PuertoEnlace[] listaPuertosEnlace;
	private Movimiento mov;

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
		this.mov = new Movimiento();

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla(i, j);

				if (jugador != null) {
				    if (i == mov.getInfiltradoX() && j == mov.getInfiltradoY()) {
				        casillas[i][j].setEsRastro(true);
				    }
				
				    
				}

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

	// (Tus Getters y Setters se mantienen exactamente iguales bajo esta línea...)
	public int getFilas() { return filas; }
	public void setFilas(int filas) { this.filas = filas; }
	public int getColumnas() { return columnas; }
	public void setColumnas(int columnas) { this.columnas = columnas; }
	public Casilla[][] getCasillas() { return casillas; }
	public void setCasillas(Casilla[][] casillas) { this.casillas = casillas; }
	public Jugador getJugador() { return jugador; }
	public void setJugador(Jugador jugador) { this.jugador = jugador; }
	public PaqueteDato getPaquete() { return paquete; }
	public void setPaquete(PaqueteDato paquete) { this.paquete = paquete; }
	public AntivirusProactivo[] getListaAntivirus() { return listaAntivirus; }
	public void setListaAntivirus(AntivirusProactivo[] listaAntivirus) { this.listaAntivirus = listaAntivirus; }
	public Firewall[] getListaFirewall() { return listaFirewall; }
	public void setListaFirewall(Firewall[] listaFirewall) { this.listaFirewall = listaFirewall; }
	public EscanerLatencia[] getListaEscaners() { return listaEscaners; }
	public void setListaEscaners(EscanerLatencia[] listaEscaners) { this.listaEscaners = listaEscaners; }
	public NodoEnergia[] getListaNodos() { return listaNodos; }
	public void setListaNodos(NodoEnergia[] listaNodos) { this.listaNodos = listaNodos; }
	public PuertoEnlace[] getListaPuertosEnlace() { return listaPuertosEnlace; }
	public void setListaPuertosEnlace(PuertoEnlace[] listaPuertosEnlace) { this.listaPuertosEnlace = listaPuertosEnlace; }
}