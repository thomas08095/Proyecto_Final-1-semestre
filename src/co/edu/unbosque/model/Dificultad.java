package co.edu.unbosque.model;

public class Dificultad {
	
	private String nombre;
	private String[] elementos;

	public Dificultad() {
		nombre = "";
		elementos = new String[3];
		elementos[0] = "Facil";
		elementos[1] = "Normal";
		elementos[2] = "Dificil";
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