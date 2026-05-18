package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaBoton extends JPanel {

	private JComboBox<String> cbxDificultades;
	private JButton btnJugar;
	private JLabel lblTitulo;
	private JLabel lblSubTitulo;
	private JLabel lblDificultad;

	public VentanaBoton() {
		setLayout(new GridBagLayout());
		setOpaque(false);
	}

	public void inicializarComponentes() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;

		lblTitulo = new JLabel("Cyber Infiltrator");
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 80));
		gbc.gridy = 1;
		add(lblTitulo, gbc);

		lblSubTitulo = new JLabel("By ©BugBusters");
		lblSubTitulo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		gbc.gridy = 2;
		add(lblSubTitulo, gbc);

		lblDificultad = new JLabel("Selecciona la dificultad:");
		gbc.gridy = 3;
		add(lblDificultad, gbc);

		btnJugar = new JButton("Jugar");
		btnJugar.setActionCommand("JUGAR");
		btnJugar.setPreferredSize(new Dimension(80, 30));
		btnJugar.setEnabled(false);
		gbc.gridy = 5;
		add(btnJugar, gbc);

		cbxDificultades = new JComboBox<String>();
		cbxDificultades.setActionCommand("DIFICULTAD");
		cbxDificultades.setPreferredSize(new Dimension(70, 30));
		cbxDificultades.setEnabled(true);
		gbc.gridy = 4;
		add(cbxDificultades, gbc);
	}

	public JComboBox<String> getCbxDificultades() {
		return cbxDificultades;
	}

	public void setCbxDificultades(JComboBox<String> cbxDificultades) {
		this.cbxDificultades = cbxDificultades;
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}

	public void setBtnJugar(JButton btnJugar) {
		this.btnJugar = btnJugar;
	}

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	public JLabel getLblDificultad() {
		return lblDificultad;
	}

	public void setLblDificultad(JLabel lblDificultad) {
		this.lblDificultad = lblDificultad;
	}

	public JLabel getLblSubTitulo() {
		return lblSubTitulo;
	}

	public void setLblSubTitulo(JLabel lblSubTitulo) {
		this.lblSubTitulo = lblSubTitulo;
	}
}
