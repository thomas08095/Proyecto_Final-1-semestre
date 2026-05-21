package co.edu.unbosque.model;

public class Fachada {

    private Tablero tablero;
    private Dificultad dificultad;
    private Movimiento movimiento;
    private AntivirusProactivo antivirusP;
    private NodoEnergia nodoE;
    private Matriz matriz;
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
        
        int tamanoPersonalizado = 5; // Valor de respaldo por defecto
        
        // Convertimos el parámetro recibido en un número entero limpio
        try {
            tamanoPersonalizado = Integer.parseInt(casillaSeleccionada);
        } catch (NumberFormatException e) {
            // Si por alguna razón llega a fallar el parseo, se calcula con base en tus antiguos formatos
            if (casillaSeleccionada.equalsIgnoreCase("10x10")) tamanoPersonalizado = 10;
            else if (casillaSeleccionada.equalsIgnoreCase("15x15")) tamanoPersonalizado = 15;
            else if (casillaSeleccionada.equalsIgnoreCase("20x20")) tamanoPersonalizado = 20;
        }

        // Asignamos el tamaño al tablero físico y creamos la nueva instancia simétrica
        tablero.setNumeroCasillas(tamanoPersonalizado);
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

        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, listaAntivirus, null, listaNodos, null, null);
    }
    
    public int numeroCasillas() {
    	int n = tablero.getNumeroCasillas() * tablero.getNumeroCasillas();
    	return n;
    }

    public boolean detectarAntivirus() {
        int aX = getScriptX();
        int aY = getScriptY();
        for (int i = 0; i < antivirusP.getCantidad(); i++) {
            if (aX == antivirusP.getFilaA()[i] && aY == antivirusP.getColumnaA()[i]) {
                return true;
            }
        }
        return false;
    }
    
    public boolean detectarNodoEnergia() {
        int nX = getScriptX();
        int nY = getScriptY();
        for (int i = 0; i < nodoE.getCantidad(); i++) {
            if (nX == nodoE.getFilaNE()[i] && nY == nodoE.getColumnaNE()[i]) {
            	nodoE.getFilaNE()[i] = -1;
            	nodoE.getColumnaNE()[i] = -1;
                return true;
            }
        }
        return false;
    }

    public boolean solicitarMovimiento(int deltaX, int deltaY) {
        return movimiento.mover(deltaX, deltaY, tablero.getFilas(), tablero.getColumnas());
    }

    public int getScriptX() {
        return movimiento.getScriptX();
    }

    public int getScriptY() {
        return movimiento.getScriptY();
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
        Jugador jugador = new Jugador(movimiento.getScriptX(), movimiento.getScriptY(), movimientos);
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

        matriz = new Matriz(tablero.getFilas(), tablero.getColumnas(), jugador, listaAntivirus, null, listaNodos, null, null);
    }

	public Matriz getMatriz() {
		return matriz;
	}
}