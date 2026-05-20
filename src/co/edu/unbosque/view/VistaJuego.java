package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent; // Necesario para la constante de enfoque
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import co.edu.unbosque.controller.Controlador;

public class VistaJuego extends JPanel {

    private Casilla[][] matrizCasillas; 
    private Controlador controlador;
    private JPanel contenedorPrincipal;
    private JPanel contenedorLabels;
    private JPanel tablero;
    
    private JLabel nodosRecolectados; 
    private JLabel dificultad;
    private JLabel cantFirewalls;
    private JLabel movimientosRestantes;

    public VistaJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }
    
    private void llenarPanelLabels() {
        contenedorLabels = new JPanel();
        contenedorLabels.setLayout(new GridLayout(1, 4));

        nodosRecolectados = new JLabel("Nodos: 0");
        contenedorLabels.add(nodosRecolectados);

        dificultad = new JLabel("Dificultad: Normal");
        contenedorLabels.add(dificultad);

        cantFirewalls = new JLabel("Firewalls: 2");
        contenedorLabels.add(cantFirewalls);

        movimientosRestantes = new JLabel("Movimientos: 30");
        contenedorLabels.add(movimientosRestantes);

        add(contenedorLabels, BorderLayout.NORTH);
    }

    private void inicializarMatrizVisual() {
        removeAll();
        llenarPanelLabels();
        tablero = new JPanel(new GridLayout(matrizCasillas.length, matrizCasillas[0].length));
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[0].length; j++) {
                tablero.add(matrizCasillas[i][j]);
            }
        }
        add(tablero, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }

    public void setMatriz(Casilla[][] matriz, Controlador controlador) {
        this.matrizCasillas = matriz;
        this.controlador = controlador;
        inicializarMatrizVisual();
        configurarMovimientoTeclado();
    }

    // --- SECCIÓN DE MOVIMIENTO TRADICIONAL Y DIRECTA ---
    private void configurarMovimientoTeclado() {
        // 1. Vinculamos cada flecha física de forma manual a su identificador de texto
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Arriba");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Abajo");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");

        // 2. Definimos de forma explícita qué coordenada (Delta) se envía para cada tecla
        getActionMap().put("Arriba", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(-1, 0); // Fila anterior, misma columna
                }
            }
        });

        getActionMap().put("Abajo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(1, 0);  // Siguiente fila, misma columna
                }
            }
        });

        getActionMap().put("Izquierda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, -1); // Misma fila, columna anterior
                }
            }
        });

        getActionMap().put("Derecha", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, 1);  // Misma fila, siguiente columna
                }
            }
        });
    }
}