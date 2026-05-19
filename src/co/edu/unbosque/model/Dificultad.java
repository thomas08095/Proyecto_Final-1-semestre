package co.edu.unbosque.model;

public class Dificultad {
	
	private String[] elementos;

	public Dificultad() {
		elementos = new String[4];
		elementos[0] = "";
		elementos[1] = "Facil";
		elementos[2] = "Normal";
		elementos[3] = "Dificil";
	}

	public String[] getElementos() {
		return elementos;
	}

	public void setElementos(String[] elementos) {
		this.elementos = elementos;
	}
}