package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Movimiento;

public class PanelJuego extends JPanel {

    private JPanel panelStats;
    private JLabel lblNodos;
    private JLabel lblDificultad;
    private JLabel lblFirewalls;
    private JLabel lblMovimientos;

    private JPanel[][] celdas;
    private JPanel panelGrid;

    private Controlador controlador;

    public PanelJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public void inicializar(Controlador controlador, int filas, int columnas,
                            String dificultad, int maxMovimientos, int cantFirewalls) {
        removeAll();
        this.controlador = controlador;

        construirPanelStats(dificultad, maxMovimientos, cantFirewalls);
        construirGrid(filas, columnas);
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

    private void construirGrid(int filas, int columnas) {
        panelGrid = new JPanel(new GridLayout(filas, columnas));
        panelGrid.setBackground(Color.BLACK);

        celdas = new JPanel[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = crearCelda();
                panelGrid.add(celdas[i][j]);
            }
        }

        add(panelGrid, BorderLayout.CENTER);
    }

    private JPanel crearCelda() {
        JPanel celda = new JPanel(new BorderLayout());
        celda.setBackground(Color.WHITE);
        celda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        celda.setPreferredSize(new Dimension(80, 80));

        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        celda.add(lbl, BorderLayout.CENTER);

        return celda;
    }

    public void setColorCelda(int fila, int col, Color color) {
        JPanel celda = celdas[fila][col];
        getLabelDeCelda(celda).setIcon(null);
        celda.setBackground(color);
        celda.repaint();
    }

    public void setImagenCelda(int fila, int col, String rutaImagen) {
        JPanel celda = celdas[fila][col];
        JLabel lbl = getLabelDeCelda(celda);
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image scaled = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(scaled));
        celda.repaint();
    }

    public void limpiarCelda(int fila, int col) {
        JPanel celda = celdas[fila][col];
        getLabelDeCelda(celda).setIcon(null);
        celda.setBackground(Color.WHITE);
        celda.repaint();
    }

    private JLabel getLabelDeCelda(JPanel celda) {
        return (JLabel) celda.getComponent(0);
    }

    private void configurarTeclado() {
        Map<String, int[]> movimientos = Movimiento.obtenerMovimientos();

        for (Map.Entry<String, int[]> entry : movimientos.entrySet()) {
            String tecla = entry.getKey();
            int[] delta = entry.getValue();

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(tecla), tecla);

            getActionMap().put(tecla, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (controlador != null) {
                        controlador.solicitarMovimiento(delta[0], delta[1]);
                    }
                }
            });
        }
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

    public int getFilasCeldas() {
        return celdas != null ? celdas.length : 0;
    }

    public int getColumnasCeldas() {
        return celdas != null ? celdas[0].length : 0;
    }
}