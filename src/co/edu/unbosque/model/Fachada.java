package co.edu.unbosque.model;

public class Fachada {

    private Tablero tablero;
    private Dificultad dificultad;
    private Movimiento movimiento;
    private AntivirusProactivo antivirusP;
    private NodoEnergia nodoE;

    public Fachada() {
        dificultad = new Dificultad();
        movimiento = new Movimiento();
        antivirusP = new AntivirusProactivo();
        nodoE = new NodoEnergia();
    }

    public void configurarTablero(String dificultadSeleccionada) {
        if (dificultadSeleccionada.equalsIgnoreCase("Facil")) {
            tablero = new Tablero(5, 5);
            antivirusP.RandomAntivirus(2);
        } else if (dificultadSeleccionada.equalsIgnoreCase("Normal")) {
            tablero = new Tablero(10, 10);
            antivirusP.RandomAntivirus(4);
        } else if (dificultadSeleccionada.equalsIgnoreCase("Dificil")) {
            tablero = new Tablero(15, 15);
            antivirusP.RandomAntivirus(6);
        }
        movimiento.resetPosicion();
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

    public int getCantidadAntivirus() {
        return antivirusP.getCantidad();
    }

    public int[] getFilasAntivirus() {
        return antivirusP.getFilaA();
    }

    public int[] getColumnasAntivirus() {
        return antivirusP.getColumnaA();
    }

    public Tablero getTablero() {
        return tablero;
    }
    
}