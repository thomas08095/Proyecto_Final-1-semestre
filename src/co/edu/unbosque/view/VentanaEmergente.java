package co.edu.unbosque.view;
import javax.swing.JOptionPane;
/**
 * Clase que usamos para la gestion de ventanas emergentes
 */
public class VentanaEmergente {
	/**metodo constructor de la clase ventana emergente
 */
	public VentanaEmergente() {
		}
	/**Despliega un cuadro de diálogo en la pantalla con un mensaje personalizado.
	 * @param m El mensaje de texto que se desea mostrar al usuario dentro de la ventana.
	 */
		public void mostrarInformacion(String m) {
			JOptionPane.showMessageDialog(null, m);
		}
		/**
		 * Solicita al usuario un valor numérico entero mediante una ventana emergente.
		 * * @param m El mensaje de solicitud del dato al usuario.
		 * @return El valor entero (int) ingresado por el usuario.
		 */
		
		public int leerInt(String m) {
			String aux = JOptionPane.showInputDialog(m);
			int dato = Integer.parseInt(aux);
			return dato;
		}
		/**
		 * Solicita al usuario la entrada de una cadena de texto mediante una ventana emergente.
		 * * @param m El mensaje o instrucción que solicita el dato al usuario.
		 * @return La cadena de texto (String) escrita por el usuario.
		 */
		public String leerString(String m) {
			String aux = JOptionPane.showInputDialog(m);
			return aux;
		}
	
}
