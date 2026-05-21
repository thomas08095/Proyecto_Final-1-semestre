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
        int nuevaX = infiltradoX + deltaX;
        int nuevaY = infiltradoY + deltaY;
        if (nuevaX >= 0 && nuevaX < filas && nuevaY >= 0 && nuevaY < columnas) {
        	infiltradoX = nuevaX;
        	infiltradoY = nuevaY;
            return true;
        }
        return false;
    }

    public void resetPosicion() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
    }


    public int getScriptX() {
        return infiltradoX;
    }

    public int getScriptY() {
        return infiltradoY;
    }
}