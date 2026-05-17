package co.edu.unbosque.model;

public class Dificultad {
	
	private String nombre;
	private String[] elementos;

	public Dificultad() {
		nombre = "";
		elementos = new String[4];
		elementos[0] = "";
		elementos[1] = "Facil";
		elementos[2] = "Normal";
		elementos[3] = "Dificil";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[] getElementos() {
		return elementos;
	}

	public void setElementos(String[] elementos) {
		this.elementos = elementos;
	}
}