package co.edu.unbosque.model;

public class Fachada {

    private Tablero tablero;
    private Dificultad dificultad;
    private Movimiento movimiento;
    private AntivirusProactivo antivirusP;
    private NodoEnergia nodoE;
    private Matriz matriz;
    private PaqueteDato paquete; // <- Atributo global para el paquete de datos
    private int movimientos;

    public Fachada() {
        dificultad = new Dificultad();
        tablero = new Tablero(5, 5); // Inicialización por defecto antes de configurar
        movimiento = new Movimiento();
        antivirusP = new AntivirusProactivo();
        nodoE = new NodoEnergia();
        movimientos = 0;
    }

    // ACTUALIZADO: Ahora recibe el tamaño ingresado como String y lo parsea internamente
    public void configurarTablero(String dificultadSeleccionada, String casillaSeleccionada) {
        
        if (casillaSeleccionada.equalsIgnoreCase("10x10")) {
            tablero.setNumeroCasillas(10);
            setMovimientos(movimientos = tablero.getNumeroCasillas() * tablero.getNumeroCasillas());
            tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
        }
        else if (casillaSeleccionada.equalsIgnoreCase("15x15")) {
            tablero.setNumeroCasillas(15);
            tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
        }
        else if (casillaSeleccionada.equalsIgnoreCase("20x20")) {
            tablero.setNumeroCasillas(20);
            tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
        }
        setMovimientos(movimientos = tablero.getNumeroCasillas() * tablero.getNumeroCasillas());
        tablero = new Tablero(tablero.getNumeroCasillas(), tablero.getNumeroCasillas());
        
        // Distribución proactiva de amenazas según la dificultad elegida
        if (dificultadSeleccionada.equalsIgnoreCase("Facil")) {
            antivirusP.RandomAntivirus(2, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(3, tablero.getNumeroCasillas());
        } else if (dificultadSeleccionada.equalsIgnoreCase("Normal")) {
            antivirusP.RandomAntivirus(4, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(2, tablero.getNumeroCasillas());
        } else if (dificultadSeleccionada.equalsIgnoreCase("Dificil")) {
            antivirusP.RandomAntivirus(6, tablero.getNumeroCasillas());
            nodoE.RandomNodoEnergia(1, tablero.getNumeroCasillas());
        }
        
        // Reiniciamos al Script en la esquina superior izquierda (0,0) para la nueva partida
        movimiento.resetPosicion();

        Jugador jugador = new Jugador(0, 0, movimientos);
        jugador.setRutaImagen("src/imagenes/jugador.png");
        
        // Inicializamos el paquete en la posición inicial deseada (por ejemplo, 1, 1)
        paquete = new PaqueteDato(1, 1, movimientos);
        paquete.setRutaImagen("src/imagenes/paquete_datos.png");
        

        Antivirus[] listaAntivirus = new Antivirus[antivirusP.getCantidad()];
        for (int i = 0; i < antivirusP.getCantidad(); i++) {
            listaAntivirus[i] = new Antivirus(antivirusP.getFilaA()[i], antivirusP.getColumnaA()[i]);
            listaAntivirus[i].setRutaImagen("src/imagenes/antivirus.png");
        }

        NodoEnergia[] listaNodos = new NodoEnergia[nodoE.getCantidad()];
        for (int i = 0; i < nodoE.getCantidad(); i++) {
            listaNodos[i] = new NodoEnergia(nodoE.getFilaNE()[i], nodoE.getColumnaNE()[i]);
            listaNodos[i].setRutaImagen("src/imagenes/nodo_energia.png");
        }

        // Se envía el objeto 'paquete' en la cuarta posición del constructor de Matriz
        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, null, listaNodos, null, null);
    }
    
    public int numeroCasillas() {
        int n = tablero.getNumeroCasillas() * tablero.getNumeroCasillas();
        return n;
    }

    public boolean detectarAntivirus() {
        for (int i = 0; i < antivirusP.getCantidad(); i++) {
            if (movimiento.getInfiltradoX() == antivirusP.getFilaA()[i] && movimiento.getInfiltradoY() == antivirusP.getColumnaA()[i]) {
                return true;
            }
        }
        return false;
    }
    
    public boolean detectarNodoEnergia() {
        for (int i = 0; i < nodoE.getCantidad(); i++) {
            if (movimiento.getInfiltradoX() == nodoE.getFilaNE()[i] && movimiento.getInfiltradoY() == nodoE.getColumnaNE()[i]) {
                nodoE.getFilaNE()[i] = -1;
                nodoE.getColumnaNE()[i] = -1;
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
        return antivirusP.getFilaA();
    }

    public int[] getColumnasAntivirus() {
        return antivirusP.getColumnaA();
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

    public void reconstruirMatriz() {
        Jugador jugador = new Jugador(movimiento.getInfiltradoX(), movimiento.getInfiltradoY(), movimientos);
        jugador.setRutaImagen("src/imagenes/jugador.png");

        Antivirus[] listaAntivirus = new Antivirus[antivirusP.getCantidad()];
        for (int i = 0; i < antivirusP.getCantidad(); i++) {
            listaAntivirus[i] = new Antivirus(antivirusP.getFilaA()[i], antivirusP.getColumnaA()[i]);
            listaAntivirus[i].setRutaImagen("src/imagenes/antivirus.png");
        }

        NodoEnergia[] listaNodos = new NodoEnergia[nodoE.getCantidad()];
        for (int i = 0; i < nodoE.getCantidad(); i++) {
            listaNodos[i] = new NodoEnergia(nodoE.getFilaNE()[i], nodoE.getColumnaNE()[i]);
            listaNodos[i].setRutaImagen("src/imagenes/nodo_energia.png");
        }

        // Se mantiene el paso del paquete actualizado al reconstruir la matriz
        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, paquete, listaAntivirus, null, listaNodos, null, null);
    }

    public Matriz getMatriz() {
        return matriz;
    }
}