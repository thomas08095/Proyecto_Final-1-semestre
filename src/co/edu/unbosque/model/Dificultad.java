package co.edu.unbosque.model;

/**
 * Clase que representa las opciones de dificultad disponibles en el juego.
 * Provee el arreglo de niveles que se carga en el combo box del menú principal.
 */
public class Dificultad {

	/** Arreglo con los niveles de dificultad disponibles: vacío, Facil, Normal y Dificil. */
	private String[] elementos;

	/**
	 * Constructor de Dificultad. Inicializa el arreglo con las tres opciones de dificultad
	 * más una entrada vacía en la posición cero para el estado inicial del combo box.
	 */
	public Dificultad() {
		elementos = new String[4];
		elementos[0] = "";
		elementos[1] = "Facil";
		elementos[2] = "Normal";
		elementos[3] = "Dificil";
	}

	public String[] getElementos() { return elementos; }
	public void setElementos(String[] elementos) { this.elementos = elementos; }
}
