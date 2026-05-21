package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Matriz;

public class PanelJuego extends JPanel {

    private JPanel panelStats;
    private JLabel lblTituloStats;
    private JLabel lblNodos;
    private JLabel lblDificultad;
    private JLabel lblFirewalls;
    private JLabel lblMovimientos;
    private JLabel lblPuertos;
    private JLabel lblEscaneres;
    private JLabel lblAntivirus;
    private JButton btnExit;

    private MatrizPanel matrizPanel;
    private Controlador controlador;

    public PanelJuego() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(10, 20, 40));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void inicializar(Controlador controlador, Matriz matriz, String dificultad, int maxMovimientos, int cantFirewalls) {
        removeAll();
        this.controlador = controlador;

        construirPanelStats(dificultad, maxMovimientos, cantFirewalls);
        construirMatrizPanel(matriz);
        configurarTeclado();

        revalidate();
        repaint();
    }

    private void construirPanelStats(String dificultad, int maxMovimientos, int cantFirewalls) {
        panelStats = new JPanel(new BorderLayout());
        panelStats.setBackground(new Color(15, 30, 60));
        panelStats.setPreferredSize(new Dimension(250, 0));
        panelStats.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 200, 255), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel panelLabels = new JPanel(new GridLayout(8, 1, 0, 4));
        panelLabels.setBackground(new Color(15, 30, 60));

        lblTituloStats = new JLabel("INFORMACIÓN", SwingConstants.LEFT);
        lblTituloStats.setForeground(new Color(100, 200, 255));
        lblTituloStats.setFont(new Font("Consolas", Font.BOLD, 20));

        lblNodos = crearLabelStat("Nodos: 0");
        lblDificultad = crearLabelStat("Dificultad: " + dificultad);
        lblFirewalls = crearLabelStat("Firewalls: " + cantFirewalls);
        lblMovimientos = crearLabelStat("Movimientos: " + maxMovimientos);
        lblPuertos = crearLabelStat("Puertos: 0");
        lblEscaneres = crearLabelStat("Escáneres: 0");
        lblAntivirus = crearLabelStat("Antivirus: 0");

        panelLabels.add(lblTituloStats);
        panelLabels.add(lblNodos);
        panelLabels.add(lblDificultad);
        panelLabels.add(lblFirewalls);
        panelLabels.add(lblMovimientos);
        panelLabels.add(lblPuertos);
        panelLabels.add(lblEscaneres);
        panelLabels.add(lblAntivirus);

        panelStats.add(panelLabels, BorderLayout.NORTH);

        btnExit = new JButton("SALIR");
        btnExit.setForeground(new Color(100, 200, 255));
        btnExit.setBackground(new Color(10, 20, 40));
        btnExit.setFont(new Font("Consolas", Font.BOLD, 16));
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        btnExit.setPreferredSize(new Dimension(0, 40));

        btnExit.addActionListener(evento -> {
            java.awt.Component comp = SwingUtilities.getWindowAncestor(this);
            if (comp instanceof VentanaPrincipal) {
                ((VentanaPrincipal) comp).mostrarMenu();
            }
        });

        panelStats.add(btnExit, BorderLayout.SOUTH);

        add(panelStats, BorderLayout.EAST);
    }

    private JLabel crearLabelStat(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.LEFT);
        label.setForeground(new Color(100, 200, 255));
        label.setFont(new Font("Consolas", Font.PLAIN, 14));
        return label;
    }

    private void construirMatrizPanel(Matriz matriz) {
        matrizPanel = new MatrizPanel(matriz);
        matrizPanel.setBackground(new Color(10, 20, 40));
        matrizPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        add(matrizPanel, BorderLayout.CENTER);
    }

    private void configurarTeclado() {
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Subir");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Bajar");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "Subir");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "Bajar");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "Izquierda");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "Derecha");

        this.getActionMap().put("Subir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(-1, 0);
                }
            }
        });

        this.getActionMap().put("Bajar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(1, 0);
                }
            }
        });

        this.getActionMap().put("Izquierda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, -1);
                }
            }
        });

        this.getActionMap().put("Derecha", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, 1);
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

    public void actualizarPuertos(int cantidad) {
        lblPuertos.setText("Puertos: " + cantidad);
    }

    public void actualizarEscaneres(int cantidad) {
        lblEscaneres.setText("Escáneres: " + cantidad);
    }

    public void actualizarAntivirus(int cantidad) {
        lblAntivirus.setText("Antivirus: " + cantidad);
    }

    public MatrizPanel getMatrizPanel() {
        return matrizPanel;
    }

    public void actualizarMatriz(Matriz matriz) {
        if (matrizPanel != null) {
            remove(matrizPanel);
        }
        construirMatrizPanel(matriz);
        revalidate();
        repaint();
    }

    public JButton getBtnExit() {
        return btnExit;
    }
}