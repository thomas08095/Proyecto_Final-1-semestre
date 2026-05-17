package co.edu.unbosque.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.model.Dificultad;
import co.edu.unbosque.view.Casilla;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controlador implements ActionListener {
	
	private VentanaPrincipal ventana;

	
	public Controlador() {
		ventana = new VentanaPrincipal();
		Dificultad dificultad = new Dificultad();
		for (String elem : dificultad.getElementos()) {
			ventana.getVentanaBoton().getCbxDificultades().addItem(elem);
		}
		asignarOyentes();
	    ventana.getVistaJuego().setMatriz(poblarMatriz());

	}
	
	private Casilla[][] poblarMatriz() {
        Casilla[][] matrizCasillas = new Casilla[15][15];
		
        for(int i = 0; i < matrizCasillas.length; i++) {
        	for(int j = 0; j < matrizCasillas[0].length; j++) {
        		matrizCasillas[i][j] = new Casilla(); 
        		Casilla casilla = matrizCasillas[i][j];
        		if(i==14&&j==7) {
        			casilla.setImagen("src/imagenes/tomoe-prueba.jpeg");
        		}
        	}
        }
        
        return matrizCasillas;
	}
	
	public void asignarOyentes() {
		ventana.getVentanaBoton().getCbxDificultades().addActionListener(this);
		ventana.getVentanaBoton().getBtnJugar().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		int posDificultad = ventana.getVentanaBoton().getCbxDificultades().getSelectedIndex();
		String dificultadSeleccionada = ventana.getVentanaBoton().getCbxDificultades().getSelectedItem().toString();	
		
		if (command.equals("DIFICULTAD")) {
	    	ventana.getVentanaBoton().getBtnJugar().setEnabled(true);	    	
		}
		else if (command.equals("JUGAR")) {
	    		ventana.mostrarJuego();
	    }
		if (dificultadSeleccionada.equals("Facil")) {
			
	    } 
		else if (dificultadSeleccionada.equals("Normal")) {

	    } 
	    else if (dificultadSeleccionada.equals("Dificil")) {
	    }
		
	}
		 
}
