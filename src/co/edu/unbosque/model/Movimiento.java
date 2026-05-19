package co.edu.unbosque.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Movimiento {

    private int scriptX;
    private int scriptY;

    public Movimiento() {
        this.scriptX = 0;
        this.scriptY = 0;
    }

    public boolean mover(int deltaX, int deltaY, int filas, int columnas) {
        int nuevaX = scriptX + deltaX;
        int nuevaY = scriptY + deltaY;
        if (nuevaX >= 0 && nuevaX < filas && nuevaY >= 0 && nuevaY < columnas) {
            scriptX = nuevaX;
            scriptY = nuevaY;
            return true;
        }
        return false;
    }

    public void resetPosicion() {
        this.scriptX = 0;
        this.scriptY = 0;
    }

    public static Map<String, int[]> obtenerMovimientos() {
        Map<String, int[]> movimientos = new LinkedHashMap<>();
        movimientos.put("UP",    new int[]{-1,  0});
        movimientos.put("DOWN",  new int[]{ 1,  0});
        movimientos.put("LEFT",  new int[]{ 0, -1});
        movimientos.put("RIGHT", new int[]{ 0,  1});
        return movimientos;
    }

    public int getScriptX() {
        return scriptX;
    }

    public int getScriptY() {
        return scriptY;
    }
}