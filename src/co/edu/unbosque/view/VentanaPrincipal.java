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
    /**
     * Obtiene el componente gráfico del menú principal.
     * @return El objeto MenuPrincipal asociado actualmente a la ventana.
     */
    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
    /**
     * Asigna un componente gráfico personalizado para el menú principal.
     * @param menuPrincipal Instancia del menú principal a establecer.
     */
    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }
    /**
     * Obtiene el componente gráfico del área de juego interactiva.
     * @return El objeto PanelJuego encargado del renderizado de la matriz.
     */
    public PanelJuego getPanelJuego() {
        return panelJuego;
    }
    /**
     * Asigna un componente gráfico personalizado para el área de juego interactiva.
     * @param panelJuego Instancia de PanelJuego a establecer.
     */
    public void setPanelJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }
    /**
     * Obtiene el contenedor intermedio que centraliza los paneles mutables del juego.
     * @return El JPanel contenedor general controlado por el CardLayout.
     */
    public JPanel getContenedor() {
        return contenedor;
    }
    /**
     * @return El administrador CardLayout que orquesta el intercambio de pantallas.
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }
}