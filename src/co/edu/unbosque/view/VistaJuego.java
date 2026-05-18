package co.edu.unbosque.view;
	import java.awt.Color;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import javax.swing.AbstractAction;
	import javax.swing.JComponent;
	import javax.swing.JPanel;
	import javax.swing.KeyStroke;
	import co.edu.unbosque.controller.Controlador; 

	public class VistaJuego extends JPanel {
	    private JPanel[][] matrizCasillas; 
	    private Controlador controlador; 
	    public VistaJuego() {   
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
	        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Arriba");
	        this.getActionMap().put("Arriba", new AccionMover(-1, 0)); 
	        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Abajo");
	        this.getActionMap().put("Abajo", new AccionMover(1, 0));  
	        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");
	        this.getActionMap().put("Izquierda", new AccionMover(0, -1)); 
	        this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");
	        this.getActionMap().put("Derecha", new AccionMover(0, 1));  
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