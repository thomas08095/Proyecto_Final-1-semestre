package co.edu.unbosque.view;

import co.edu.unbosque.model.Casilla;
import javax.swing.*;
import java.awt.*;

public class CasillaPanel extends JPanel {

    private Casilla casilla;

    public CasillaPanel(Casilla casilla) {
        this.casilla = casilla;
        setPreferredSize(new Dimension(60, 60));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setLayout(new BorderLayout());
        actualizarImagen();
        
    }

    private void actualizarImagen() {
        removeAll();
        if (casilla.isEstaOcupada() && casilla.getContenido() != null) {
            String rutaImagen = casilla.getContenido().getRutaImagen();
            if (rutaImagen != null && !rutaImagen.isEmpty()) {
                ImageIcon icon = new ImageIcon(
                    new ImageIcon(rutaImagen).getImage()
                        .getScaledInstance(60, 60, Image.SCALE_SMOOTH)
                );
                add(new JLabel(icon), BorderLayout.CENTER);
            }
        }
    }

    public Casilla getCasilla() { return casilla; }
    public void setCasilla(Casilla casilla) { this.casilla = casilla; }
}
