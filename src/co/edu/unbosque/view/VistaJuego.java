package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaJuego extends JPanel{
	Casilla[][] matrizCasillas;
	
	public VistaJuego() { 
	}


	private void InicializarMatriz() {
		for(int i = 0; i < matrizCasillas.length; i++) {
			for(int j = 0; j < matrizCasillas[0].length; j++) {
				add(matrizCasillas[i][j]);
			}
		}
	}
	
	public void setMatriz(Casilla[][] matriz) {
		matrizCasillas = matriz;
		setLayout(new GridLayout(matriz.length, matriz[0].length));
        InicializarMatriz();
	}
	
}
