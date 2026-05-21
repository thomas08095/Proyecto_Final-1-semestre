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

    private int filas;
    private int columnas;
    private Casilla[][] casillas;
    private Jugador jugador;
    private PaqueteDato paquete; 
    private Antivirus[] listaAntivirus;
    private Firewall[] listaFirewall;
    private EscanerLatencia[] listaEscaners;
    private NodoEnergia[] listaNodos;
    private PuertoEnlace[] listaPuertosEnlace;
    private Movimiento movimiento; 

    public Matriz(int filas, int columnas, Jugador jugador, PaqueteDato paquete, Antivirus[] listaAntivirus,
            EscanerLatencia[] listaEscaners, NodoEnergia[] listaNodos,
            PuertoEnlace[] listaPuertosEnlace, Firewall[] listaFirewall, Movimiento movimiento) {

        this.filas = filas;
        this.columnas = columnas;
        this.jugador = jugador;
        this.paquete = paquete; 
        this.listaAntivirus = listaAntivirus;
        this.listaEscaners = listaEscaners;
        this.listaNodos = listaNodos;
        this.listaPuertosEnlace = listaPuertosEnlace;
        this.listaFirewall = listaFirewall;
        this.movimiento = movimiento; 
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
						if (puerto.getFila() == i && puerto.getColumna() == j) {
							casillas[i][j].setContenido(new Entidad(i, j, puerto.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}

				// 7. Firewall
				if (listaFirewall != null) {
					for (Firewall firewall : listaFirewall) {
						if (firewall.getFila() == i && firewall.getColumna() == j) {
							casillas[i][j].setContenido(new Entidad(i, j, firewall.getRutaImagen()));
							casillas[i][j].setEstaOcupada(true);
						}
					}
				}
			}
		}
	}

	// Getters y Setters
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
                if (movimiento != null && movimiento.esCasillaVisitada(i, j)) {
                    casillas[i][j].setTieneRastro(true);
                }

                if (jugador != null && jugador.getFila() == i && jugador.getColumna() == j) {
                    casillas[i][j].setContenido(new Entidad(i, j, jugador.getRutaImagen()));
                    casillas[i][j].setEstaOcupada(true);
                }

                if (paquete != null && paquete.getFila() == i && paquete.getColumna() == j) {
                    casillas[i][j].setContenido(new Entidad(i, j, paquete.getRutaImagen()));
                    casillas[i][j].setEstaOcupada(true);
                }

                if (listaAntivirus != null) {
                    for (Antivirus antivirus : listaAntivirus) {
                        if (antivirus.getFila() == i && antivirus.getColumna() == j) {
                            casillas[i][j].setContenido(new Entidad(i, j, antivirus.getRutaImagen()));
                            casillas[i][j].setEstaOcupada(true);
                        }
                    }
                }

                if (listaEscaners != null) {
                    for (EscanerLatencia escaner : listaEscaners) {
                        if (escaner.getFila() == i && escaner.getColumna() == j) {
                            casillas[i][j].setContenido(new Entidad(i, j, escaner.getRutaImagen()));
                            casillas[i][j].setEstaOcupada(true);
                        }
                    }
                }

                if (listaNodos != null) {
                    for (NodoEnergia nodo : listaNodos) {
                        if (nodo.getFila() == i && nodo.getColumna() == j) {
                            casillas[i][j].setContenido(new Entidad(i, j, nodo.getRutaImagen()));
                            casillas[i][j].setEstaOcupada(true);
                        }
                    }
                }

                if (listaPuertosEnlace != null) {
                    for (PuertoEnlace puerto : listaPuertosEnlace) {
                        if (puerto.getFila() == i && puerto.getColumna() == j) {
                            casillas[i][j].setContenido(new Entidad(i, j, puerto.getRutaImagen()));
                            casillas[i][j].setEstaOcupada(true);
                        }
                    }
                }

                if (listaFirewall != null) {
                    for (Firewall firewall : listaFirewall) {
                        if (firewall.getFila() == i && firewall.getColumna() == j) {
                            casillas[i][j].setContenido(new Entidad(i, j, firewall.getRutaImagen()));
                            casillas[i][j].setEstaOcupada(true);
                        }
                    }
                }
            }
        }
    }

    public PaqueteDato getPaquete() { return paquete; }
    public void setPaquete(PaqueteDato paquete) { this.paquete = paquete; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public Casilla[][] getCasillas() { return casillas; }
    public Jugador getJugador() { return jugador; }
    public Antivirus[] getListaAntivirus() { return listaAntivirus; }
    public Firewall[] getListaFirewall() { return listaFirewall; }
    public EscanerLatencia[] getListaEscaners() { return listaEscaners; }
    public NodoEnergia[] getListaNodos() { return listaNodos; }
    public PuertoEnlace[] getListaPuertosEnlace() { return listaPuertosEnlace; }
}