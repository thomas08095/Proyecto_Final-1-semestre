package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Movimiento;

public class VistaJuego extends JPanel {

    private JPanel[][] matrizCasillas;
    private Controlador controlador;
    private JPanel contenedorPrincipal;
    private JPanel contenedorLabels;
    private JPanel tablero;
    private LabelActualizable nodosRecolectados; // diff - nodos - firewall - movimientos
    private LabelActualizable dificultad;
    private LabelActualizable cantFirewalls;
    private LabelActualizable movimientosRestantes;

    public VistaJuego() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }
    
    private void llenarPanelLabels() {
        contenedorLabels = new JPanel();
        contenedorLabels.setLayout(new GridLayout(1, 4));

        nodosRecolectados = new LabelActualizable("0");
        contenedorLabels.add(nodosRecolectados);

        dificultad = new LabelActualizable("Normal");
        contenedorLabels.add(dificultad);

        cantFirewalls = new LabelActualizable("2");
        contenedorLabels.add(cantFirewalls);

        movimientosRestantes = new LabelActualizable("30");
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
    }

    public void setMatriz(JPanel[][] matriz, Controlador controlador) {
        this.matrizCasillas = matriz;
        this.controlador = controlador;
        inicializarMatrizVisual();
        configurarMovimientoTeclado();
    }

    private void configurarMovimientoTeclado() {
        Map<String, int[]> movimientos = Movimiento.obtenerMovimientos();
        for (Map.Entry<String, int[]> entry : movimientos.entrySet()) {
            String tecla = entry.getKey();
            int[] delta = entry.getValue();
            getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
            getActionMap().put(tecla, new AccionMover(delta[0], delta[1]));
        }
    }

    private class AccionMover extends AbstractAction {

        private int deltaX;
        private int deltaY;

        public AccionMover(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (controlador != null) {
                controlador.solicitarMovimiento(deltaX, deltaY);
            }
        }
    }
}