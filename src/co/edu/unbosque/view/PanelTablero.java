package co.edu.unbosque.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Movimiento;

// Componente visual responsable exclusivamente de organizar el mapa (matriz) y registrar las pulsaciones de teclado
public class PanelTablero extends JPanel {
    
    // Matriz gráfica que contiene los cuadros (casillas) visibles del juego
    private Casilla[][] matrizCasillas;
    // Referencia al controlador para notificarle cuando el jugador intente moverse
    private Controlador controlador;

    // Constructor
    public PanelTablero() {
        // Se deja vacío porque el tamaño del tablero se desconoce hasta que el jugador elige una opción
    }

    // Método orquestador que crea la estructura visual e inicializa los controles
    public void inicializarTablero(int filas, int columnas, Controlador controlador) {
        this.controlador = controlador;
        generarGrid(filas, columnas);
        configurarMovimientoTeclado();
    }

    // Construye la cuadrícula visual basándose en el número de filas y columnas del modelo
    private void generarGrid(int filas, int columnas) {
        this.removeAll(); // Se asegura de que no haya elementos basura de partidas anteriores
        this.setLayout(new GridLayout(filas, columnas)); // Divide el panel equitativamente
        
        matrizCasillas = new Casilla[filas][columnas];

        // Instancia cada cuadro blanco (Casilla) y lo añade a la pantalla
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizCasillas[i][j] = new Casilla();
                this.add(matrizCasillas[i][j]);
            }
        }
        
        // Actualiza el componente visual
        this.revalidate();
        this.repaint();
    }

    // Mapea las teclas de dirección del sistema operativo con una acción específica
    private void configurarMovimientoTeclado() {
        Map<String, int[]> movimientos = Movimiento.obtenerMovimientos(); // Obtiene las directrices del modelo
        
        for (Map.Entry<String, int[]> entry : movimientos.entrySet()) {
            String tecla = entry.getKey();
            int[] delta = entry.getValue(); // Cuántas posiciones sumar o restar en X/Y
            
            // Registra la tecla cuando la ventana actual esté enfocada
            this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
            // Asigna a dicha tecla el evento interno encargado de procesarla
            this.getActionMap().put(tecla, new AccionMover(delta[0], delta[1]));
        }
    }

    // Clase interna para manejar qué ocurre cuando el usuario pulsa una flecha direccional
    private class AccionMover extends AbstractAction {
        private int deltaX;
        private int deltaY;

        public AccionMover(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // Se dispara automáticamente al hundir la tecla configurada
        @Override
        public void actionPerformed(ActionEvent e) {
            if (controlador != null) {
                // Notifica al controlador el deseo de moverse en un eje específico
                controlador.solicitarMovimiento(deltaX, deltaY);
            }
        }
    }

    // Retorna la matriz gráfica para que el controlador pueda colorear las casillas
    public Casilla[][] getMatrizCasillas() {
        return matrizCasillas;
    }
}