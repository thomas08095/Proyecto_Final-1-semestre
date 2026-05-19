package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Movimiento;

public class VistaJuego extends JPanel {

    private JPanel[][] matrizCasillas;
    private Controlador controlador;

    public VistaJuego() {
        setBackground(Color.WHITE);
    }

    private void inicializarMatrizVisual() {
        removeAll();
        for (int i = 0; i < matrizCasillas.length; i++) {
            for (int j = 0; j < matrizCasillas[0].length; j++) {
                add(matrizCasillas[i][j]);
            }
        }
        revalidate();
        repaint();
    }

    public void setMatriz(JPanel[][] matriz, Controlador controlador) {
        this.matrizCasillas = matriz;
        this.controlador = controlador;
        setLayout(new GridLayout(matriz.length, matriz[0].length));
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