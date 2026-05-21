package co.edu.unbosque.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Movimiento {

    private int infiltradoX;
    private int infiltradoY;

    public Movimiento() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
    }

    public boolean mover(int deltaX, int deltaY, int filas, int columnas) {
        int posicionX = infiltradoX + deltaX;
        int posicionY = infiltradoY + deltaY;
        if (posicionX >= 0 && posicionX < filas && posicionY >= 0 && posicionY < columnas) {
        	infiltradoX = posicionX;
        	infiltradoY = posicionY;
            return true;
        }
        return false;
    }

    public void resetPosicion() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
    }

	public int getInfiltradoX() {
		return infiltradoX;
	}

	public void setInfiltradoX(int infiltradoX) {
		this.infiltradoX = infiltradoX;
	}

	public int getInfiltradoY() {
		return infiltradoY;
	}

	public void setInfiltradoY(int infiltradoY) {
		this.infiltradoY = infiltradoY;
	}

}