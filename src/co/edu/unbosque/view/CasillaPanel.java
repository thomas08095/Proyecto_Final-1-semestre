package co.edu.unbosque.view;

import co.edu.unbosque.model.Casilla;
import javax.swing.*;
import java.awt.*;
/**
 * Representación visual individual (JPanel) de una celda del tablero en la interfaz de usuario.
 * Se encarga de gestionar dinámicamente el dibujado en tiempo real del fondo (cambiando a verde 
 * para marcar el rastro histórico del paquete de datos) y de superponer de forma escalada las 
 * imágenes de las entidades del juego (Jugador, Antivirus, Nodos, etc.) cuando la casilla se encuentra ocupada.
 */
public class CasillaPanel extends JPanel {
	/** Instancia lógica de la Casilla del modelo asociada
	 *  directamente a este panel visual.
    */
	private Casilla casilla;

	/**
	 * Metodo constructor de la clase CasillaPanel.
	 * Configura el tamaño preferido estandarizado de la celda en 60x60 píxeles, establece un borde 
	 * de estilo tecnológico azul neón, define el color de fondo oscuro por defecto e invoca 
	 * el método de refresco de imagen y fondo.
	 * * @param casilla Objeto de tipo Casilla que contiene los datos lógicos y estados de la celda.
	 */
	public CasillaPanel(Casilla casilla) {
		this.casilla = casilla;
		setPreferredSize(new Dimension(60, 60));
		setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255)));
		setBackground(new Color(10, 20, 40));
		setLayout(new BorderLayout());
		actualizarImagen();
	}
	/**
	 * Dibuja y actualiza los componentes visuales de la casilla de acuerdo a el estado del modelo.
	 * Limpia los componentes previos y valida: si está ocupada por el paquete de datos, tiñe el fondo 
	 * de verde y activa su rastro; si tiene otra entidad, dibuja su icono escalado de forma suavizada 
	 * (60x60). Si está vacía, determina si debe mantener el color verde por rastro histórico o volver 
	 * al fondo oscuro estándar del sistema.
	 */
	private void actualizarImagen() {
	    removeAll();

	    if (casilla.isEstaOcupada() && casilla.getContenido() != null) {
	    
	        if (casilla.getContenido().getRutaImagen().contains("paquete")) { 
	            setBackground(new Color(128,0 ,255)); 
	            casilla.setEsRastro(true); 
	        }

	        String rutaImagen = casilla.getContenido().getRutaImagen();
	        if (rutaImagen != null && !rutaImagen.isEmpty()) {
	            ImageIcon icon = new ImageIcon(
	                    new ImageIcon(rutaImagen).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
	            add(new JLabel(icon), BorderLayout.CENTER);
	        }
	    } 
	    else {
	        if (casilla.isEsRastro()) {
	            setBackground(new Color(128,0 ,255));
	        } else {
	            setBackground(new Color(10, 20, 40));
	        }
	    }

	    revalidate();
	    repaint();
	}
	
	public Casilla getCasilla() {
		return casilla;
	}
	
	public void setCasilla(Casilla casilla) {
		this.casilla = casilla;
		actualizarImagen(); 
	}
}