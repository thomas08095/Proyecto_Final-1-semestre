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
import javax.swing.JTextField; // Agregado para capturar la dimensión

public class MenuPrincipal extends JPanel {

    private JComboBox<String> cbxDificultades;
    private JTextField txtTamanoTablero; // Reemplaza al JComboBox de casillas
    private JButton btnJugar;
    private JLabel lblTitulo;
    private JLabel lblSubTitulo;
    private JLabel lblDificultad;
    private JLabel lblImagen;

    public MenuPrincipal() {
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

        ImageIcon icon = new ImageIcon("src/imagenes/BugBusterIcon.png");
        Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImagen = new JLabel(new ImageIcon(scaled));
        gbc.gridy = 3;
        add(lblImagen, gbc);

        lblDificultad = new JLabel("Ingresa el tamaño del tablero y selecciona dificultad:");
        gbc.gridy = 4;
        add(lblDificultad, gbc);
        
        // Configuración del campo de texto numérico
        txtTamanoTablero = new JTextField();
        txtTamanoTablero.setPreferredSize(new Dimension(80, 30));
        txtTamanoTablero.setToolTipText("Ej: 10 para un tablero de 10x10");
        gbc.gridy = 5;
        add(txtTamanoTablero, gbc);

        cbxDificultades = new JComboBox<String>();
        cbxDificultades.setActionCommand("DIFICULTAD");
        cbxDificultades.setPreferredSize(new Dimension(100, 30));
        cbxDificultades.setEnabled(true);
        gbc.gridy = 6;
        add(cbxDificultades, gbc);

        btnJugar = new JButton("Jugar");
        btnJugar.setActionCommand("JUGAR");
        btnJugar.setPreferredSize(new Dimension(80, 30));
        btnJugar.setEnabled(true); // Se deja activo por defecto para validar al dar click
        gbc.gridy = 7;
        add(btnJugar, gbc);
    }

    // Getter para obtener el campo de texto en el controlador
    public JTextField getTxtTamanoTablero() {
        return txtTamanoTablero;
    }

    public JComboBox<String> getCbxDificultades() {
        return cbxDificultades;
    }

    public JButton getBtnJugar() {
        return btnJugar;
    }
}