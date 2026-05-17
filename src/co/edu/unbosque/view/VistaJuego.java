package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class VistaJuego {
JFrame frame;
JLabel label;
	Action arriba;
	Action abajo;
	Action izquierda;
	Action derecha;
	
	
	public VistaJuego() {
		
				
		label = new JLabel();
		label.setBackground(Color.MAGENTA);
		label.setBounds(100,100,100,100);
		label.setOpaque(true);
		
		
		
		label.getInputMap().put(KeyStroke.getKeyStroke("UP"), "Arriba");
		label.getActionMap().put("Arriba", new Arriba());
		
		label.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "Abajo");
		label.getActionMap().put("moverAbajo", new Abajo());
		
		label.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");
		label.getActionMap().put("Izquierda", new Izquierda());
		
		label.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");
		label.getActionMap().put("moverArriba", new Derecha());
		
	}
	
	public class Arriba extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setLocation(label.getX(), label.getY()-10);
			}
	}
		public class Abajo extends AbstractAction{
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setLocation(label.getX(), label.getY()+10);							
			}		
	}
	public class Izquierda extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setLocation(label.getX()-10, label.getY());		
		}
	}
	public class Derecha extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setLocation(label.getX()+10, label.getY());					
		}
	}
	}


