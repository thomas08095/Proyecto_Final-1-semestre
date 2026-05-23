package co.edu.unbosque.model;
/**
 * Clase que representa el componente lógico Tablero dentro del juego.
 * Se encarga de almacenar las dimensiones básicas de la cuadrícula, gestionar una matriz primitiva 
 * de enteros y define las dimensiones de la matriz presentadas en texto al usuario
  */
public class Tablero {
	/** Cantidad de filas calculadas para el escenario de juego. 
	 */
    private int filas;
    /** Cantidad de columnas calculadas para el escenario de juego.
     */
    private int columnas;
    /** Matriz bidimensional de enteros que sirve como mapa de respaldo lógico.
      */
    private int[][] matriz;
    /** Número de casillas por lado (ej. 10 si el tablero es de 10x10).
     */
    private int numeroCasillas;
    /** Arreglo de cadenas de texto que contiene las opciones de tamaño disponibles para los combos de la vista.
     */
    private String[] elementos;
    /**
     * Metodo constructor de la clase Tablero.
     * Inicializa las dimensiones de filas y columnas, instancia el arreglo bidimensional de enteros 
     * con base en dichas medidas y precarga las opciones textuales de dimensionamiento de la matriz 
     * ("10x10", "15x15", "20x20") con una primera casilla vacía de control.
     * * @param filas Cantidad inicial de filas para el tablero.
     * @param columnas Cantidad inicial de columnas para el tablero.
     */
    public Tablero(int filas, int columnas) {
    
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int[filas][columnas];
        this.numeroCasillas = filas;
        elementos = new String[4];
        elementos[0] = "";
        elementos[1] = "10x10";
        elementos[2] = "15x15";
        elementos[3] = "20x20";
    }
    
	public int getFilas() {
		return filas;
	}
	
	public int getColumnas() {
		return columnas;
	}
	
	public int[][] getMatriz() {
		return matriz;
	}

    public int getNumeroCasillas() {
        return numeroCasillas;
    }

    public void setNumeroCasillas(int numeroCasillas) {
        this.numeroCasillas = numeroCasillas;
    }

    public String[] getElementos() {
        return elementos;
    }

    public void setElementos(String[] elementos) {
        this.elementos = elementos;
    }
}
