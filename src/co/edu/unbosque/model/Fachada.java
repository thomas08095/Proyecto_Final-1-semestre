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

        if (dificultadSeleccionada.equalsIgnoreCase("Facil")) {
            antivirusP.RandomAntivirus(2, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(3, tablero.getNumeroCasillas());
            escanerL.RandomEscanerL(2, tablero.getNumeroCasillas());
            puertoE.RandomPuertoEnlace(2, tablero.getNumeroCasillas());
            firewall.RandomFirewall(2, tablero.getNumeroCasillas());
            
        } else if (dificultadSeleccionada.equalsIgnoreCase("Normal")) {
            antivirusP.RandomAntivirus(4, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(2, tablero.getNumeroCasillas());
            escanerL.RandomEscanerL(3, tablero.getNumeroCasillas());
            puertoE.RandomPuertoEnlace(3, tablero.getNumeroCasillas());
            firewall.RandomFirewall(2, tablero.getNumeroCasillas());

        } else if (dificultadSeleccionada.equalsIgnoreCase("Dificil")) {
            antivirusP.RandomAntivirus(6, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(1, tablero.getNumeroCasillas());
            escanerL.RandomEscanerL(4, tablero.getNumeroCasillas());
            puertoE.RandomPuertoEnlace(5, tablero.getNumeroCasillas());
            firewall.RandomFirewall(2, tablero.getNumeroCasillas());


        }

        movimiento.resetPosicion();

        Jugador jugador = new Jugador(0, 0, movimientos);
        jugador.setRutaImagen("src/imagenes/jugador.png");

        // Inicializamos el paquete en la posición inicial deseada (por ejemplo, 1, 1)
        paquete = new PaqueteDato(1, 1, movimientos);
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

    public void moverAntivirus() {
        antivirusP.moverAleatoriamente(tablero.getFilas(), tablero.getColumnas());
    }

    public void moverEscanerL() {
        escanerL.moverAleatoriamente(tablero.getFilas(), tablero.getColumnas());
    }

    public boolean detectarAntivirus() {
        for (int i = 0; i < antivirusP.getCantidad(); i++) {
            boolean enArriba = (movimiento.getInfiltradoX() == antivirusP.getFila()[i] - 1
                    && movimiento.getInfiltradoY() == antivirusP.getColumna()[i]);
            boolean enAbajo = (movimiento.getInfiltradoX() == antivirusP.getFila()[i] + 1
                    && movimiento.getInfiltradoY() == antivirusP.getColumna()[i]);
            boolean enIzquierda = (movimiento.getInfiltradoX() == antivirusP.getFila()[i]
                    && movimiento.getInfiltradoY() == antivirusP.getColumna()[i] - 1);
            boolean enDerecha = (movimiento.getInfiltradoX() == antivirusP.getFila()[i]
                    && movimiento.getInfiltradoY() == antivirusP.getColumna()[i] + 1);

            if (enArriba || enAbajo || enIzquierda || enDerecha) {
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

        // 2. ¿En esa casilla contigua está el paquete de datos?
        if (paquete != null && proximaFilaJugador == paquete.getFila() && proximaColumnaJugador == paquete.getColumna()) {

            // 3. Calculamos la casilla destino a la que se moverá el paquete
            int destinoFilaPaquete = paquete.getFila() + deltaX;
            int destinoColumnaPaquete = paquete.getColumna() + deltaY;

            // 4. Validamos que el paquete no se salga de los límites del tablero
            if (destinoFilaPaquete >= 0 && destinoFilaPaquete < tablero.getFilas() &&
                    destinoColumnaPaquete >= 0 && destinoColumnaPaquete < tablero.getColumnas()) {

                // El paquete se desplaza de forma válida en el modelo
                paquete.setFila(destinoFilaPaquete);
                paquete.setColumna(destinoColumnaPaquete);
            } else {
                // Si el paquete fuera a salirse de los servidores, bloqueamos todo el paso
                return false;
            }
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