package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private MenuPrincipal menuPrincipal;
    private PanelJuego panelJuego;

    private JPanel contenedor;
    private CardLayout cardLayout;

    public VentanaPrincipal() {
        setTitle("Cyber Infiltrator");
        setSize(1700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(new BorderLayout(10, 10));
        setResizable(false);
        setLocationRelativeTo(null);
        inicializarComponentes();
        setVisible(true);
    }

    public void inicializarComponentes() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        menuPrincipal = new MenuPrincipal();
        menuPrincipal.inicializarComponentes();

        panelJuego = new PanelJuego();

        contenedor.add(menuPrincipal, "MENU");
        contenedor.add(panelJuego, "JUEGO");

        add(contenedor, BorderLayout.CENTER);

        cardLayout.show(contenedor, "MENU");
    }

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