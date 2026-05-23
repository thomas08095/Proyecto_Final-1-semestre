package co.edu.unbosque.view;

import co.edu.unbosque.model.Matriz;
import javax.swing.*;
import java.awt.*;/**
 * Representa la matriz lógica bidimensional del juego 
 * Se encarga de construir la cuadrícula de casillas y posicionar a el (Jugador, 
 * Paquete, Antivirus, Firewalls, etc.) 
 * mapeando sus coordenadas actuales en cada actualización.
 */

public class MatrizPanel extends JPanel {
	/** Arreglo bidimensional de componentes CasillaPanel
	 *  que representan las celdas físicas en la interfaz.
    */
    private CasillaPanel[][] casillaPanels;
    /** Instancia lógica de la Matriz proveniente del modelo
     *  que dicta el tamaño y contenido de las celdas.
     */
    private Matriz matriz;
    /**
     * Metodo constructor de la clase MatrizPanel.
     * Toma las dimensiones de filas y columnas del objeto Matriz para configurar el Layout,
     * inicializa el mapa bidimensional de celdas gráficas e inserta cada una en el contenedor 
     * recorriendo de forma secuencial la cuadrícula lógica.
     * * @param matriz Objeto lógico Matriz que contiene la información estructural del escenario de juego.
     */

    public MatrizPanel(Matriz matriz) {
        this.matriz = matriz;
        this.casillaPanels = new CasillaPanel[matriz.getFilas()][matriz.getColumnas()];
        setLayout(new GridLayout(matriz.getFilas(), matriz.getColumnas()));
        for (int i = 0; i < matriz.getFilas(); i++) {
            for (int j = 0; j < matriz.getColumnas(); j++) {
                casillaPanels[i][j] = new CasillaPanel(matriz.getCasillas()[i][j]);
                add(casillaPanels[i][j]);
            }
        }
    }

   
    public CasillaPanel[][] getCasillaPanels() { return casillaPanels; }
  
    public void setCasillaPanels(CasillaPanel[][] casillaPanels) { this.casillaPanels = casillaPanels; }
   
    public Matriz getMatriz() { return matriz; }
   
    public void setMatriz(Matriz matriz) { this.matriz = matriz; }
}
