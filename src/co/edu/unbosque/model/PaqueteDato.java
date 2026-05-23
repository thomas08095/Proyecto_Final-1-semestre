package co.edu.unbosque.model;
/**
 * Clase que representa el componente lógico del Paquete de Datos dentro del modelo.
  */
public class PaqueteDato {
	/** Coordenada correspondiente a la fila actual del paquete en la matriz de juego.
	 */
    private int fila;
    /** Coordenada correspondiente a la columna actual del paquete en la matriz de juego.
     */
    private int columna;
    /** Ruta de almacenamiento local del recurso de imagen que identifica al paquete de datos.
     */
    private String rutaImagen;
    /**
     * Metodo constructor parametrizado de la clase PaqueteDato.
     * Define la localización inicial del paquete dentro del mapa de red y carga 
     * por defecto la ruta del recurso gráfico correspondiente a su apariencia visual.
     * @param fila Fila inicial asignada dentro del escenario de juego.
     * @param columna Columna inicial asignada dentro del escenario de juego.
     * @param movimientos Cantidad de movimientos iniciales permitidos por la dificultad.
     */
    public PaqueteDato(int fila, int columna, int movimientos) {
        this.fila = fila;
        this.columna = columna;
        this.rutaImagen = "src/imagenes/paquete_datos.png"; 
    }

    public int getFila() { 
        return fila; 
    }
    
    public void setFila(int fila) { 
        this.fila = fila; 
    }

    public int getColumna() { 
        return columna; 
    }
    
    public void setColumna(int columna) { 
        this.columna = columna; 
    }

    public String getRutaImagen() { 
        return rutaImagen; 
    }
    
    public void setRutaImagen(String rutaImagen) { 
        this.rutaImagen = rutaImagen; 
    }
}