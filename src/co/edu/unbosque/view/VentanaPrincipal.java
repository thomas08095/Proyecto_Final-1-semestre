package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;
/**
 * Esta es la ventana principal del juego, "Cyber Infiltrador", gestiona el intercambio de pantallas
 * principales utilizando un diseño basado en cardLayout
 */
public class VentanaPrincipal extends JFrame {
/**
 * Este es el panel que representa la pantalla de inicio y tiene la configuracion del menu principal 
 */
    private MenuPrincipal menuPrincipal;
    /**
     * En este panel desplegamos la matriz grafica del juego 
     */
    private PanelJuego panelJuego;
/**
 * 
 */
    private JPanel contenedor;
    /**
     * 
     */
    private CardLayout cardLayout;
    
    /** metodo constructor de la ventana principal
     * configura las dimensiones del marco del grafico, el color de fondo, restringe el cambio 
     * del tamaño y centra la ventana en la pantalla del jugador 
     */
    public VentanaPrincipal() {
        setTitle("Cyber Infiltrator");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(56, 22, 64));
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(null);
        inicializarComponentes();
        setVisible(true);
    }
    /** Inicializa la logica de diseño y los paneles principales de la interfaz 
     * crea e integra las instancias del menu y del area dentro del contenedor, 
     * establece la pantalla del menu como la pantalla inicial pro defecto
     */

    public void inicializarComponentes() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        contenedor.setBackground(new Color(56, 22, 64));

        menuPrincipal = new MenuPrincipal();
        menuPrincipal.inicializarComponentes();

        panelJuego = new PanelJuego();

        contenedor.add(menuPrincipal, "MENU");
        contenedor.add(panelJuego, "JUEGO");

        add(contenedor, BorderLayout.CENTER);

        cardLayout.show(contenedor, "MENU");
    }
    /**
     * Modifica el estado del CardLayout para traer la vista del menú principal.
     */
    public void mostrarMenu() {
        cardLayout.show(contenedor, "MENU");
    }
    /**
     * Modifica el estado del CardLayout para traer la interfaz  de juego.
     * Despliega la matriz cuando se elige el nivel de dificultad
     */
    public void mostrarJuego() {
        cardLayout.show(contenedor, "JUEGO");
    }
    
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
   
    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
    
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }
    
    public void setPanelJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }
   
    public JPanel getContenedor() {
        return contenedor;
    }
   
    public CardLayout getCardLayout() {
        return cardLayout;
    }
}