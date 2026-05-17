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

    public VentanaPrincipal() {
        setTitle("Cyber Infiltrator");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        getContentPane().setLayout(new BorderLayout(10, 10));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        ventanaBoton = new VentanaBoton();
        ventanaBoton.inicializarComponentes();
        add(ventanaBoton, BorderLayout.CENTER);
    }

    public VentanaBoton getVentanaBoton() {
        return ventanaBoton;
    }

    public void setVentanaBoton(VentanaBoton ventanaBoton) {
        this.ventanaBoton = ventanaBoton;
    }
}
