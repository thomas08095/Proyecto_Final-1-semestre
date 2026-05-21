package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.colorchooser.ColorChooserComponentFactory;
import javax.swing.text.AttributeSet.ColorAttribute;

import co.edu.unbosque.controller.Controlador;

// Contenedor principal de la pantalla de juego en ejecución
public class VistaJuego extends JPanel {

    // Panel central que contendrá la cuadrícula jugable
    private PanelTablero panelTablero;

    // Panel de estadísticas
    private JPanel contenedorLabels;

    // Labels informativos
    private JLabel nodosRecolectados;
    private JLabel dificultad;
    private JLabel cantFirewalls;
    private JLabel movimientosRestantes;

    // Matriz visual
    private Casilla[][] matrizCasillas;

    private Controlador controlador;

    // Panel del tablero
    private JPanel tablero;

    // Constructor
    public VistaJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F2EDFF"));

        panelTablero = new PanelTablero();
    }

    // Construye la zona de estadísticas
    private void llenarPanelLabels() {

        if (contenedorLabels != null) {
            remove(contenedorLabels);
        }

        contenedorLabels = new JPanel();
        contenedorLabels.setLayout(new GridLayout(1, 4));

        // Labels iniciales
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

    // Inicializa el tablero usando PanelTablero
    public void inicializarVistaTablero(Controlador controlador, int filas, int columnas) {

        removeAll();

        this.controlador = controlador;

        llenarPanelLabels();

        // Construye el tablero
        panelTablero.inicializarTablero(filas, columnas, controlador);

        // Agrega al centro
        add(panelTablero, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Inicializa usando matriz visual
    private void inicializarMatrizVisual() {

        removeAll();

        llenarPanelLabels();

        tablero = new JPanel(
                new GridLayout(matrizCasillas.length, matrizCasillas[0].length));

        for (int i = 0; i < matrizCasillas.length; i++) {

            for (int j = 0; j < matrizCasillas[0].length; j++) {

                tablero.add(matrizCasillas[i][j]);
            }
        }

        add(tablero, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    // Getter del panel tablero
    public PanelTablero getPanelTablero() {
        return panelTablero;
    }

    // Setter de matriz
    public void setMatriz(Casilla[][] matriz, Controlador controlador) {

        this.matrizCasillas = matriz;
        this.controlador = controlador;

        inicializarMatrizVisual();

        configurarMovimientoTeclado();
    }

    // Configuración de movimiento por teclado
    private void configurarMovimientoTeclado() {

        // Flechas
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("UP"), "Arriba");

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("DOWN"), "Abajo");

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");

        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");

        // Acción arriba
        getActionMap().put("Arriba", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (controlador != null) {
                    controlador.solicitarMovimiento(-1, 0);
                }
            }
        });

        // Acción abajo
        getActionMap().put("Abajo", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (controlador != null) {
                    controlador.solicitarMovimiento(1, 0);
                }
            }
        });

        // Acción izquierda
        getActionMap().put("Izquierda", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (controlador != null) {
                    controlador.solicitarMovimiento(0, -1);
                }
            }
        });

        // Acción derecha
        getActionMap().put("Derecha", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (controlador != null) {
                    controlador.solicitarMovimiento(0, 1);
                }
            }
        });
    }
}