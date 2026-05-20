package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import co.edu.unbosque.controller.Controlador;

// Contenedor principal de la pantalla de juego en ejecución
public class VistaJuego extends JPanel {
    
    // Panel central que contendrá la cuadrícula jugable
    private PanelTablero panelTablero; 
    
    // Panel lateral/superior con la información y estadísticas de la partida
    private JPanel contenedorLabels;
    private LabelActualizable nodosRecolectados;
    private LabelActualizable dificultad;
    private LabelActualizable cantFirewalls;
    private LabelActualizable movimientosRestantes;

    // Constructor: Prepara el esqueleto general de la interfaz
    public VistaJuego() {
        setLayout(new BorderLayout()); // Layout ideal para dividir el tablero en el centro y datos a los costados
        setBackground(Color.WHITE);
        panelTablero = new PanelTablero(); 
    }
    
    // Construye la zona de estadísticas (Score, movimientos, dificultad)
    private void llenarPanelLabels() {
        if (contenedorLabels != null) {
            remove(contenedorLabels); // Evita duplicados al reiniciar la partida
        }
        contenedorLabels = new JPanel();
        contenedorLabels.setLayout(new GridLayout(1, 4)); // 1 fila, 4 columnas para los labels

        // Inicializa las etiquetas con valores por defecto
        nodosRecolectados = new LabelActualizable("0");
        contenedorLabels.add(nodosRecolectados);

        dificultad = new LabelActualizable("Normal");
        contenedorLabels.add(dificultad);

        cantFirewalls = new LabelActualizable("2");
        contenedorLabels.add(cantFirewalls);

        movimientosRestantes = new LabelActualizable("30");
        contenedorLabels.add(movimientosRestantes);
        
        // Coloca la barra de información en el borde derecho de la pantalla
        add(contenedorLabels, BorderLayout.EAST); 
    }

    // Método invocado por el controlador al presionar el botón "Jugar"
    public void inicializarVistaTablero(Controlador controlador, int filas, int columnas) {
        removeAll();
        llenarPanelLabels(); // Dibuja la zona de estadísticas
        
        // Le indica al panel especialista que fabrique la cuadrícula interactiva
        panelTablero.inicializarTablero(filas, columnas, controlador);
        
        // Añade el mapa de casillas al centro de la pantalla
        add(panelTablero, BorderLayout.CENTER);
        
        // Fuerza el refresco gráfico
        revalidate();
        repaint();
    }

    // Permite al controlador obtener acceso indirecto a la matriz visual
    public PanelTablero getPanelTablero() {
        return panelTablero;
    }
}