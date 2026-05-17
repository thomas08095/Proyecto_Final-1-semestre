package co.edu.unbosque.view;

import java.awt.*;

import javax.swing.*;

public class Casilla extends JPanel{
    private ImageIcon imagen;
    private JLabel label;
    private String tipo;
    
	public Casilla() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        setPreferredSize(new Dimension(80, 80));
        
        label = new JLabel();
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
	}
	
	public void setImagen(String rutaImagen) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        Image scaled = icon.getImage().getScaledInstance(
            getPreferredSize().width - 10,
            getPreferredSize().height - 10,
            Image.SCALE_SMOOTH
        );
        this.imagen = new ImageIcon(scaled);
        label.setIcon(this.imagen);
        repaint();
    }
	
    public void limpiar() {
        this.imagen = null;
        label.setIcon(null);
        repaint();
    }
}
