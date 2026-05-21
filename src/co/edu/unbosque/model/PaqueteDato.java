package co.edu.unbosque.model;

public class PaqueteDato {

    private int fila;
    private int columna;
    private String rutaImagen;

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