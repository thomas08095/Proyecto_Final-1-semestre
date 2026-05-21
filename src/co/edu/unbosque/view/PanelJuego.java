package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Matriz;
import co.edu.unbosque.model.Movimiento;

public class PanelJuego extends JPanel {

    private JPanel panelStats;
    private JLabel lblNodos;
    private JLabel lblDificultad;
    private JLabel lblFirewalls;
    private JLabel lblMovimientos;

    private MatrizPanel matrizPanel;

    private Controlador controlador;

    public PanelJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public void inicializar(Controlador controlador, Matriz matriz,
                            String dificultad, int maxMovimientos, int cantFirewalls) {
        removeAll();
        this.controlador = controlador;

        construirPanelStats(dificultad, maxMovimientos, cantFirewalls);
        construirMatrizPanel(matriz);
        configurarTeclado();

        revalidate();
        repaint();
    }

    private void construirPanelStats(String dificultad, int maxMovimientos, int cantFirewalls) {
        panelStats = new JPanel(new GridLayout(4, 1, 0, 10));
        panelStats.setBackground(new Color(30, 30, 30));
        panelStats.setPreferredSize(new Dimension(200, 0));

        lblNodos       = crearLabelStat("Nodos: 0");
        lblDificultad  = crearLabelStat("Dificultad: " + dificultad);
        lblFirewalls   = crearLabelStat("Firewalls: " + cantFirewalls);
        lblMovimientos = crearLabelStat("Movimientos: " + maxMovimientos);

        panelStats.add(lblNodos);
        panelStats.add(lblDificultad);
        panelStats.add(lblFirewalls);
        panelStats.add(lblMovimientos);

        add(panelStats, BorderLayout.EAST);
    }

    private JLabel crearLabelStat(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    private void construirMatrizPanel(Matriz matriz) {
        matrizPanel = new MatrizPanel(matriz);
        add(matrizPanel, BorderLayout.CENTER);
    }

    public void actualizarMatriz(Matriz matriz) {
        if (matrizPanel != null) {
        	remove(matrizPanel);
        }
        matrizPanel = new MatrizPanel(matriz);
        add(matrizPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

      private void configurarTeclado() {
        // 1. Vinculamos cada flecha física
        this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke("UP"), "Subir");
        this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke("DOWN"), "Bajar");
        this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke("LEFT"), "Izquierda");
        this.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(javax.swing.KeyStroke.getKeyStroke("RIGHT"), "Derecha");

        // 2. Definimos de forma lineal e individual los vectores de movimiento para el controlador
        this.getActionMap().put("Subir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(-1, 0); // Sube una fila
                }
            }
        });
        this.getActionMap().put("Bajar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(1, 0);  // Baja una fila
                }
            }
        });

        this.getActionMap().put("Izquierda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, -1); // Retrocede una columna
                }
            }
        });

        this.getActionMap().put("Derecha", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, 1);  // Avanza una columna
                }
            }
        });
    }
    

    public void actualizarNodos(int cantidad) {
        lblNodos.setText("Nodos: " + cantidad);
    }

    public void actualizarMovimientos(int restantes) {
        lblMovimientos.setText("Movimientos: " + restantes);
    }

    public void actualizarFirewalls(int cantidad) {
        lblFirewalls.setText("Firewalls: " + cantidad);
    }

    public MatrizPanel getMatrizPanel() {
        return matrizPanel;
    }
}
