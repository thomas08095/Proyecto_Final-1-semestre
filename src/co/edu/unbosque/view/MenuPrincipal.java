package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Panel que representa la interfaz del Menú Principal del juego.
 * utiliza un diseño basado en gridLayout para posicionar de manera simétrica los componentes
 *  de personalización de la partida, como la selección de dificultad, la dimensión del tablero,
 * la inversión del orden de los puertos y el botón de inicio.
 */
public class MenuPrincipal extends JPanel {
	/** boton desplegable para las dificultades disponibles en el juego. 
	*/
	private JComboBox<String> cbxDificultades;
	/** boton desplegable  para las dimensiones del mapa 
    */
	private JComboBox<String> cbxCasillas;
	/** Botón de acción encargado de iniciar la partida
	*/
	private JButton btnJugar;
	/** Botón interactivo para alternar la regla del orden inverso de los Puertos de Enlace.
    */
	private JButton btnOrdenInverso;
	/** Etiqueta que muestra el título principal de la aplicación ("Cyber Infiltrator").
    */
	private JLabel lblTitulo;
	/** Etiqueta que presenta el nombre del equipo autor del juego.
	 */
	private JLabel lblSubTitulo;
	/** Etiqueta de texto que indica la zona de configuración de dificultad.
	 */
	private JLabel lblDificultad;
	/** logotipo o imagen representativa del juego
	 * */
	private JLabel lblImagen;
	/**
	 * Metodo constructor de la clase MenuPrincipal.
	 * Configura el administrador de diseño en GridLayout, establece un color de fondo 
	 * oscuro tecnológico azulado y activa la propiedad de opacidad para que se pinte.
	 */

	public MenuPrincipal() {
		setLayout(new GridBagLayout());
		setBackground(new Color(10, 20, 40));
		setOpaque(true);
	}
	/**
	 * Inicializa, estiliza y posiciona secuencialmente cada uno de los componentesdel menú.
	 * Configura las restricciones de alineación, define las fuentes de tipo Consolas con 
	 * estéticas neón, escala de forma suavizada el logotipo principal y restringe el 
	 * disparo del botón de inicio hasta que las propiedades requeridas sean leídas.
	 */

	public void inicializarComponentes() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;

		lblTitulo = new JLabel("Cyber Infiltrator");
		lblTitulo.setFont(new Font("Consolas", Font.BOLD, 80));
		lblTitulo.setForeground(new Color(100, 200, 255));
		gbc.gridy = 1;
		add(lblTitulo, gbc);

		lblSubTitulo = new JLabel("By ©BugBusters");
		lblSubTitulo.setFont(new Font("Consolas", Font.BOLD, 15));
		lblSubTitulo.setForeground(new Color(100, 200, 255));
		gbc.gridy = 2;
		add(lblSubTitulo, gbc);

		ImageIcon icon = new ImageIcon("src/imagenes/BugBusterIcon.png");
		Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		lblImagen = new JLabel(new ImageIcon(scaled));
		gbc.gridy = 3;
		add(lblImagen, gbc);

		lblDificultad = new JLabel("Selecciona el tablero y la dificultad:");
		lblDificultad.setFont(new Font("Consolas", Font.BOLD, 16));
		lblDificultad.setForeground(new Color(100, 200, 255));
		gbc.gridy = 4;
		add(lblDificultad, gbc);

		cbxCasillas = new JComboBox<String>();
		cbxCasillas.setActionCommand("CASILLA");
		cbxCasillas.setPreferredSize(new Dimension(100, 30));
		cbxCasillas.setBackground(new Color(15, 30, 60));
		cbxCasillas.setForeground(new Color(100, 200, 255));
		cbxCasillas.setFont(new Font("Consolas", Font.BOLD, 14));
		cbxCasillas.setEnabled(true);
		gbc.gridy = 5;
		add(cbxCasillas, gbc);

		cbxDificultades = new JComboBox<String>();
		cbxDificultades.setActionCommand("DIFICULTAD");
		cbxDificultades.setPreferredSize(new Dimension(100, 30));
		cbxDificultades.setBackground(new Color(15, 30, 60));
		cbxDificultades.setForeground(new Color(100, 200, 255));
		cbxDificultades.setFont(new Font("Consolas", Font.BOLD, 14));
		cbxDificultades.setEnabled(true);
		gbc.gridy = 6;
		add(cbxDificultades, gbc);
		
		btnOrdenInverso = new JButton("ORDEN INVERSO");
		btnOrdenInverso.setActionCommand("ORDENINVERSO");
		btnOrdenInverso.setPreferredSize(new Dimension(170, 40));
		btnOrdenInverso.setBackground(new Color(10, 20, 40));
		btnOrdenInverso.setForeground(new Color(100, 200, 255));
		btnOrdenInverso.setFont(new Font("Consolas", Font.BOLD, 16));
		btnOrdenInverso.setFocusPainted(false);
		btnOrdenInverso.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
		btnOrdenInverso.setEnabled(true);
		gbc.gridy = 7;
		add(btnOrdenInverso, gbc);

		btnJugar = new JButton("JUGAR");
		btnJugar.setActionCommand("JUGAR");
		btnJugar.setPreferredSize(new Dimension(100, 40));
		btnJugar.setBackground(new Color(10, 20, 40));
		btnJugar.setForeground(new Color(100, 200, 255));
		btnJugar.setFont(new Font("Consolas", Font.BOLD, 16));
		btnJugar.setFocusPainted(false);
		btnJugar.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
		btnJugar.setEnabled(false);
		gbc.gridy = 8;
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

	public JButton getBtnOrdenInverso() {
		return btnOrdenInverso;
	}
	
	public void setBtnOrdenInverso(JButton btnOrdenInverso) {
		this.btnOrdenInverso = btnOrdenInverso;
	}
	
}