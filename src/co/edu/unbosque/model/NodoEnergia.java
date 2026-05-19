package co.edu.unbosque.model;

import java.util.Random;

public class NodoEnergia {
	private int[] filaNE;
	private int[] columnaNE;
	private int cantidad;
	private Random rand;

	public NodoEnergia() {
		this.cantidad = 0;
		filaNE = new int[0];
		columnaNE = new int[0];
		rand = new Random();
	}

	public void RandomNodoEnergia(int cantidad) {
		this.cantidad = cantidad;
		filaNE = new int[cantidad];
		columnaNE = new int[cantidad];
		for (int i = 0; i < cantidad; i++) {
			filaNE[i] = rand.nextInt(10);
			columnaNE[i] = rand.nextInt(10);
			System.out.println("NODO DE ENERGÍA " + (i + 1) + ": " + filaNE[i] + "," + columnaNE[i]);
		}
	}

	public int[] getFilaNE() {
		return filaNE;
	}

	public void setFilaNE(int[] filaNE) {
		this.filaNE = filaNE;
	}

	public int[] getColumnaNE() {
		return columnaNE;
	}

	public void setColumnaNE(int[] columnaNE) {
		this.columnaNE = columnaNE;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}
	
}
