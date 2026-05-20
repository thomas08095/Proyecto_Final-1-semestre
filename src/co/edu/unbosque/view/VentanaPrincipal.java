package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

// Contenedor principal de la aplicación (La ventana del sistema operativo)
public class VentanaPrincipal extends JFrame {

	// Paneles que representan las diferentes "pantallas" del juego
	private MenuPrincipal menuPrincipal;
	private VistaJuego vistaJuego;

	// Contenedor dinámico y gestor de pantallas
	private JPanel contenedor;
	private CardLayout cardLayout; // Permite alternar entre menús y el juego como si fueran cartas

	// Constructor: Configura la ventana base
	public VentanaPrincipal() {
		setTitle("Cyber Infiltrator");
		setSize(1700, 850);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(new BorderLayout(10, 10));
		setResizable(false);
		setLocationRelativeTo(null); // Centra la ventana en el monitor
		inicializarComponentes();
		setVisible(true); // Hace visible la ventana al final de la carga
	}

	// Prepara las pantallas y las introduce en el gestor (CardLayout)
	public void inicializarComponentes() {
		cardLayout = new CardLayout();
		contenedor = new JPanel(cardLayout);

		// Instancia de la pantalla de inicio
		menuPrincipal = new MenuPrincipal();
		menuPrincipal.inicializarComponentes();

		// Instancia de la pantalla donde se desarrolla la partida
		vistaJuego = new VistaJuego();

		// Se añaden al contenedor con una etiqueta identificadora
		contenedor.add(menuPrincipal, "MENU");
		contenedor.add(vistaJuego, "JUEGO");

		add(contenedor, BorderLayout.CENTER);

		// Define cuál pantalla se mostrará primero por defecto al abrir el programa
		cardLayout.show(contenedor, "MENU");
	}

	// Métodos utilitarios para cambiar de pantalla durante la ejecución

	public void mostrarMenu() {
		cardLayout.show(contenedor, "MENU");
	}

	public void mostrarJuego() {
		cardLayout.show(contenedor, "JUEGO");
	}

	public MenuPrincipal getMenuPrincipal() {
		return menuPrincipal;
	}

	public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	public VistaJuego getVistaJuego() {
		return vistaJuego;
	}

	// Getters y Setters para que el controlador acceda a ambas vistas
	public void setVistaJuego(VistaJuego vistaJuego) {
		this.vistaJuego = vistaJuego;
	}

	public JPanel getContenedor() {
		return contenedor;
	}

	public void setContenedor(JPanel contenedor) {
		this.contenedor = contenedor;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
}