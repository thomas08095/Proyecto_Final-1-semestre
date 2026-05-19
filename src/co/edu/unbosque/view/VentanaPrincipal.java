package co.edu.unbosque.view;
import javax.swing.*;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class VentanaPrincipal extends JFrame {

    private VentanaBoton ventanaBoton;
    private VistaJuego vistaJuego;
    private JPanel contenedor;
    private CardLayout cardLayout;

    public VentanaPrincipal() {
        setTitle("Cyber Infiltrator");
        setSize(1700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(new BorderLayout(10, 10));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        ventanaBoton = new VentanaBoton();
        ventanaBoton.inicializarComponentes();
        vistaJuego = new VistaJuego();

        contenedor.add(ventanaBoton, "MENU");
        contenedor.add(vistaJuego, "JUEGO");

        add(contenedor, BorderLayout.CENTER);

        cardLayout.show(contenedor, "MENU"); 
    }

    public void mostrarMenu() {
        cardLayout.show(contenedor, "MENU");
    }
    
    public void mostrarJuego() {
        cardLayout.show(contenedor, "JUEGO");
    }

    public VentanaBoton getVentanaBoton() {
        return ventanaBoton;
    }

    public void setVentanaBoton(VentanaBoton ventanaBoton) {
        this.ventanaBoton = ventanaBoton;
    }
    
    public VistaJuego getVistaJuego() {
    	return vistaJuego;
    }
    
    public void setVistaJuego(VistaJuego vistajuego) {
    	this.vistaJuego = vistaJuego;
    }
}
