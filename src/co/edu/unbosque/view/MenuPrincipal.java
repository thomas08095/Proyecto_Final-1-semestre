package co.edu.unbosque.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Clase que representa la pantalla de inicio donde el jugador configura la partida
public class MenuPrincipal extends JPanel {

	// Componentes gráficos de la interfaz
	private JComboBox<String> cbxDificultades;
	private JComboBox<String> cbxCasillas;
	private JButton btnJugar;
	private JLabel lblTitulo;
	private JLabel lblSubTitulo;
	private JLabel lblDificultad;
	private JLabel lblImagen;

	// Constructor: Configura el layout principal del panel
	public MenuPrincipal() {
		setLayout(new GridBagLayout()); // Permite organizar los elementos centrados y en forma de cuadrícula flexible
		setOpaque(false);
	}

	// Método que inicializa, posiciona y añade cada componente a la pantalla
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

        ImageIcon icon = new ImageIcon("src/imagenes/BugBusterIcon.png");
        Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImagen = new JLabel(new ImageIcon(scaled));
        gbc.gridy = 3;
        add(lblImagen, gbc);

        lblDificultad = new JLabel("Selecciona el tablero y la dificultad:");
        gbc.gridy = 4;
        add(lblDificultad, gbc);
        
        cbxCasillas = new JComboBox<String>();
        cbxCasillas.setActionCommand("CASILLA");
        cbxCasillas.setPreferredSize(new Dimension(70, 30));
        cbxCasillas.setEnabled(true);
        gbc.gridy = 5;
        add(cbxCasillas, gbc);

        cbxDificultades = new JComboBox<String>();
        cbxDificultades.setActionCommand("DIFICULTAD");
        cbxDificultades.setPreferredSize(new Dimension(70, 30));
        cbxDificultades.setEnabled(true);
        gbc.gridy = 6;
        add(cbxDificultades, gbc);

        btnJugar = new JButton("Jugar");
        btnJugar.setActionCommand("JUGAR");
        btnJugar.setPreferredSize(new Dimension(80, 30));
        btnJugar.setEnabled(false);
        gbc.gridy = 7;
        add(btnJugar, gbc);
    }

	public JComboBox<String> getCbxDificultades() {
		return cbxDificultades;
	}

	public void setCbxDificultades(JComboBox<String> cbxDificultades) {
		this.cbxDificultades = cbxDificultades;
	}

	public JComboBox<String> getCbxCasillas() {
		return cbxCasillas;
	}

	public void setCbxCasillas(JComboBox<String> cbxCasillas) {
		this.cbxCasillas = cbxCasillas;
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

	public JLabel getLblSubTitulo() {
		return lblSubTitulo;
	}

	public void setLblSubTitulo(JLabel lblSubTitulo) {
		this.lblSubTitulo = lblSubTitulo;
	}

	public JLabel getLblDificultad() {
		return lblDificultad;
	}

	public void setLblDificultad(JLabel lblDificultad) {
		this.lblDificultad = lblDificultad;
	}

	public JLabel getLblImagen() {
		return lblImagen;
	}

	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}


}