package co.edu.unbosque.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.model.Dificultad;
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
		}
	public void asignarOyentes() {
		ventana.getVentanaBoton().getCbxDificultades().addActionListener(this);
		ventana.getVentanaBoton().getBtnJugar().addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println(command);
		
		
		if (command.equals("DIFICULTAD")) {
	    	ventana.getVentanaBoton().getBtnJugar().setEnabled(true);
	    	

		    int posDificultad = ventana.getVentanaBoton().getCbxDificultades().getSelectedIndex();
		    String dificultadSeleccionada = ventana.getVentanaBoton().getCbxDificultades().getSelectedItem().toString();
		    
		   }else if (command.equals("JUGAR")) {
	    		ventana.getVentanaBoton().getLblDificultad().setVisible(false);
	    		ventana.getVentanaBoton().getLblTitulo().setVisible(false);
	    		ventana.getVentanaBoton().getBtnJugar().setVisible(false);
		    	ventana.getVentanaBoton().getCbxDificultades().setVisible(false);
	    	}
		}
		 
}
