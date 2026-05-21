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
    private PaqueteDato paquete; // <- Atributo global para el paquete de datos
    private int movimientos;

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
    }

    // ACTUALIZADO: Ahora recibe el tamaño ingresado como String y lo parsea internamente
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
            firewall.RandomFirewall(2, nCasillas);

        } else if (dificultadSeleccionada.equalsIgnoreCase("Normal")) {
            antivirusP.RandomAntivirus(4, nCasillas);
            nodoE.RandomNodoEnergia(2, nCasillas);
            escanerL.RandomEscanerL(3, nCasillas);
            puertoE.RandomPuertoEnlace(3, nCasillas);
            firewall.RandomFirewall(2, nCasillas);

        } else if (dificultadSeleccionada.equalsIgnoreCase("Dificil")) {
            antivirusP.RandomAntivirus(6, nCasillas);
            nodoE.RandomNodoEnergia(1, nCasillas);
            escanerL.RandomEscanerL(4, nCasillas);
            puertoE.RandomPuertoEnlace(5, nCasillas);
            firewall.RandomFirewall(2, nCasillas);

        }

        movimiento.resetPosicion();

        Jugador jugador = new Jugador(0, 0, movimientos);
        jugador.setRutaImagen("src/imagenes/jugador.png");

        // Inicializamos el paquete en la posición inicial deseada (por ejemplo, 1, 1)
        int centro = nCasillas / 2;
        paquete = new PaqueteDato(centro, centro, movimientos);
        paquete.setRutaImagen("src/imagenes/paquete_datos.png");

        // CORRECCIÓN: Se usa AntivirusProactivo en lugar de Antivirus
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
            listaEscanerLatencia[i].setRutaImagen("src/imagenes/escaner_latencia.png"); // Recuerda poner la ruta correcta
        }
        PuertoEnlace[] listaPuertoEnlace = new PuertoEnlace[puertoE.getCantidad()];
        for (int i = 0; i < puertoE.getCantidad(); i++) {
        	listaPuertoEnlace[i] = new PuertoEnlace(puertoE.getFila()[i], puertoE.getColumna()[i]);
        	listaPuertoEnlace[i].setRutaImagen("src/imagenes/puerto_enlace.png"); // Recuerda poner la ruta correcta
        }
        Firewall[] listaFirewall = new Firewall[firewall.getCantidad()];
        for (int i = 0; i < firewall.getCantidad(); i++) {
        	listaFirewall[i] = new Firewall(firewall.getFila()[i], firewall.getColumna()[i]);
        	listaFirewall[i].setRutaImagen("src/imagenes/firewall.png"); // Recuerda poner la ruta correcta
        }

        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, listaEscanerLatencia,
                listaNodos, listaPuertoEnlace, listaFirewall);
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

    public boolean detectarPuertoEnlace() {
        for (int i = 0; i < puertoE.getCantidad(); i++) {
            if (paquete.getFila() == puertoE.getFila()[i]
                    && paquete.getColumna() == puertoE.getColumna()[i]) {
                puertoE.getFila()[i] = -1;
                puertoE.getColumna()[i] = -1;
                return true;
            }
        }
        return false;
    }

    // LÓGICA DE MOVIMIENTO INTEGRADA Y CORREGIDA (Mecánica Sokoban)
    public boolean solicitarMovimiento(int deltaX, int deltaY) {
        // 1. Calculamos a dónde quiere ir el Script
        int proximaFilaJugador = movimiento.getInfiltradoX() + deltaX;
        int proximaColumnaJugador = movimiento.getInfiltradoY() + deltaY;

        // 2. ¿La celda destino está bloqueada por un obstáculo?
        if (celdaOcupada(proximaFilaJugador, proximaColumnaJugador)) {
            return false;
        }

        // 3. ¿En esa casilla contigua está el paquete de datos?
        if (paquete != null && proximaFilaJugador == paquete.getFila() && proximaColumnaJugador == paquete.getColumna()) {

            // 3. Calculamos la casilla destino a la que se moverá el paquete
            int destinoFilaPaquete = paquete.getFila() + deltaX;
            int destinoColumnaPaquete = paquete.getColumna() + deltaY;

            // 4. Validamos que el paquete no se salga ni quede en una banda
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

        // 5. El jugador realiza su movimiento físico si es válido
        return movimiento.mover(deltaX, deltaY, tablero.getFilas(), tablero.getColumnas());
    }

    public void reconstruirMatriz() {
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
            listaEscanerLatencia[i].setRutaImagen("src/imagenes/escaner_latencia.png"); // Recuerda poner la ruta correcta
        }
        PuertoEnlace[] listaPuertoEnlace = new PuertoEnlace[puertoE.getCantidad()];
        for (int i = 0; i < puertoE.getCantidad(); i++) {
        	listaPuertoEnlace[i] = new PuertoEnlace(puertoE.getFila()[i], puertoE.getColumna()[i]);
        	listaPuertoEnlace[i].setRutaImagen("src/imagenes/puerto_enlace.png"); // Recuerda poner la ruta correcta
        }
        Firewall[] listaFirewall = new Firewall[firewall.getCantidad()];
        for (int i = 0; i < firewall.getCantidad(); i++) {
        	listaFirewall[i] = new Firewall(firewall.getFila()[i], firewall.getColumna()[i]);
        	listaFirewall[i].setRutaImagen("src/imagenes/firewall.png"); // Recuerda poner la ruta correcta
        }

        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, listaEscanerLatencia,
                listaNodos, listaPuertoEnlace, listaFirewall);
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

	public void setPuertoE(PuertoEnlace puertoE) {
		this.puertoE = puertoE;
	}


}
