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
	/**
	 * Obtiene el componente desplegable de las dificultades.
	 * @return El JComboBox encargado de listar las dificultades del sistema.
	 */
	public JComboBox<String> getCbxDificultades() {
		return cbxDificultades;
	}
	/**
	 * Asigna un componente personalizado para el combo de dificultades.
	 * @param cbxDificultades Instancia de JComboBox a establecer.
	 */
	public void setCbxDificultades(JComboBox<String> cbxDificultades) {
		this.cbxDificultades = cbxDificultades;
	}
	/**
	 * Obtiene el componente desplegable de los tamaños del tablero.
	 * @return El JComboBox encargado de listar las dimensiones de las casillas.
	 */
	public JComboBox<String> getCbxCasillas() {
		return cbxCasillas;
	}
	/**
	 * Asigna un componente personalizado para el combo del tamaño del tablero.
	 * @param cbxCasillas Instancia de JComboBox a establecer.
	 */
	public void setCbxCasillas(JComboBox<String> cbxCasillas) {
		this.cbxCasillas = cbxCasillas;
	}
	/**
	 * Obtiene el botón de acción para iniciar el juego.
	 * @return El objeto JButton que ejecuta la acción "JUGAR".
	 */
	public JButton getBtnJugar() {
		return btnJugar;
	}
	/**
	 * Asigna o altera el estado del botón principal de juego.
	 * @param btnJugar Instancia del botón de juego a establecer.
	 */
	public void setBtnJugar(JButton btnJugar) {
		this.btnJugar = btnJugar;
	}
	/**
	 * Obtiene la etiqueta del título principal.
	 * @return El JLabel del título del juego.
	 */
	public JLabel getLblTitulo() {
		return lblTitulo;
	}
	/**
	 * Asigna una etiqueta de texto personalizada para el título.
	 * @param lblTitulo Instancia de JLabel a establecer.
	 */
	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}
	/**
	 * Obtiene la etiqueta del subtítulo de la consola.
	 * @return El JLabel correspondiente al subtítulo.
	 */
	public JLabel getLblSubTitulo() {
		return lblSubTitulo;
	}
	/**
	 * Asigna una etiqueta de texto personalizada para el subtítulo.
	 * @param lblSubTitulo Instancia de JLabel a establecer.
	 */
	public void setLblSubTitulo(JLabel lblSubTitulo) {
		this.lblSubTitulo = lblSubTitulo;
	}
	/**
	 * Obtiene la etiqueta indicadora de la zona de dificultad.
	 * @return El JLabel de la sección de dificultades.
	 */
	public JLabel getLblDificultad() {
		return lblDificultad;
	}
	/**
	 * Asigna una etiqueta de texto personalizada para el texto de dificultad.
	 * @param lblDificultad Instancia de JLabel a establecer.
	 */
	public void setLblDificultad(JLabel lblDificultad) {
		this.lblDificultad = lblDificultad;
	}
	/**
	 * Obtiene el contenedor de la imagen del menú.
	 * @return El JLabel que almacena el icono gráfico escalado.
	 */
	public JLabel getLblImagen() {
		return lblImagen;
	}
	/**
	 * Asigna una imagen personalizada al menú principal.
	 * @param lblImagen Instancia de JLabel con el icono a establecer.
	 */
	public void setLblImagen(JLabel lblImagen) {
		this.lblImagen = lblImagen;
	}
	/**
	 * Obtiene el botón encargado de alternar la secuencia de los Puertos de Enlace.
	 * @return El objeto JButton con el comando de acción "ORDEN_INVERSO".
	 */
	public JButton getBtnOrdenInverso() {
		return btnOrdenInverso;
	}
	/**
	 * Asigna un botón de control personalizado para la mecánica de inversión del orden.
	 * @param btnOrdenInverso Instancia de JButton a establecer.
	 */
	public void setBtnOrdenInverso(JButton btnOrdenInverso) {
		this.btnOrdenInverso = btnOrdenInverso;
	}
	
}