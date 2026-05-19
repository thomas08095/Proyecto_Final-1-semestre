package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class LabelActualizable extends JLabel {
	public LabelActualizable(String valorInicial) {
		setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(Color.BLUE);
        setText(valorInicial);
	}

	public void cambiarValor(String nuevoValor) {
		setText(nuevoValor);
	}
}
