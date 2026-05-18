package co.edu.unbosque.view;
import javax.swing.JOptionPane;

public class VentanaEmergente {
	
	
	public VentanaEmergente() {
		}
		public void mostrarInformacion(String m) {
			JOptionPane.showMessageDialog(null, m);
		}
		
		public int leerInt(String m) {
			String aux = JOptionPane.showInputDialog(m);
			int dato = Integer.parseInt(aux);
			return dato;
		}
		
		public String leerString(String m) {
			String aux = JOptionPane.showInputDialog(m);
			return aux;
		}
		
		public long leerLong(String m) {
			String aux = JOptionPane.showInputDialog(m);
			long dato = Long.parseLong(aux);
			return dato;
		}

}
