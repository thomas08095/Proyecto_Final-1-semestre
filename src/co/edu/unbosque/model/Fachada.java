package co.edu.unbosque.model;

public class Fachada {

    private Tablero tablero;
    private Dificultad dificultad;
    private Movimiento movimiento;
    private AntivirusProactivo antivirusP;
    private NodoEnergia nodoE;
    private int movimientos;

    public Fachada() {
        dificultad = new Dificultad();
        tablero = new Tablero(5, 5);
        movimiento = new Movimiento();
        antivirusP = new AntivirusProactivo();
        nodoE = new NodoEnergia();
        movimientos = 0;
    }

    public void configurarTablero(String dificultadSeleccionada, String casillaSeleccionada) {
        if (casillaSeleccionada.equalsIgnoreCase("10x10")) {
            tablero.setNumeroCasillas(10);
            setMovimientos(movimientos = tablero.getNumeroCasillas() *tablero.getNumeroCasillas());
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
        movimiento.resetPosicion();
    }
    public int numeroCasillas() {
    	int n= tablero.getNumeroCasillas()*tablero.getNumeroCasillas();
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
    
    
}