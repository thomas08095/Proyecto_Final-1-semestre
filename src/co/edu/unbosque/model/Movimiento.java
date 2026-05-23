package co.edu.unbosque.model;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * Clase que representa el componente lógico de Movimiento dentro del modelo.
 * Se encarga de rastrear la ubicación cartesiana bidimensional del agente infiltrado 
 * en el servidor, validando que los desplazamientos solicitados no excedan los 
 * límites físicos perimetrales establecidos para el tablero de juego.
 */
public class Movimiento {
	/** Coordenada posicional actual del infiltrado en el eje X (filas de la matriz).
	 */
    private int infiltradoX;
    /** Coordenada posicional actual del infiltrado en el eje Y (columnas de la matriz).
     */
    private int infiltradoY;
    /**
     * Metodo constructor por defecto de la clase Movimiento.
     * Inicializa la ubicación lógica del agente infiltrado en el origen del 
     * sistema de coordenadas (casilla superior izquierda [0][0]).
     */
    public Movimiento() {
        this.infiltradoX = 0;
        this.infiltradoY = 0;
    }
    /**
     * Evalúa y ejecuta el desplazamiento relativo del agente en la cuadrícula de red.
     * Calcula la posición tentativa sumando los deltas provistos y verifica que se encuentre dentro del 
     * rango [0, límites). Si es una celda válida,consolida el movimiento actualizando las variables de coordenadas globales.
     * @param deltaX Cambio relativo en el eje de las filas (positivo hacia abajo, negativo hacia arriba).
     * @param deltaY Cambio relativo en el eje de las columnas (positivo a la derecha, negativo a la izquierda).
     * @param filas Cantidad total de filas del tablero para la validación perimetral.
     * @param columnas Cantidad total de columnas del tablero para la validación perimetral.
     * @return true si el movimiento fue válido y completado con éxito; false si excedía los límites.
     */
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
    /**
     * Restablece la localización lógica del agente infiltrado de vuelta a las 
     * coordenadas de origen [0][0] del sistema de ciberseguridad.
     */
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