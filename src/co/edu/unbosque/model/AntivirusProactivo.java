package co.edu.unbosque.model;

import java.util.Random;

public class AntivirusProactivo {

    private int[] filaA;
    private int[] columnaA;
    private int cantidad;
    private Random rand;
    private int nCasillas;

    public AntivirusProactivo() {
        this.cantidad = 0;
        this.nCasillas = 0;
        filaA = new int[0];
        columnaA = new int[0];
        rand = new Random();
    }

    public void RandomAntivirus(int cantidad,int nCasillas) {
        this.cantidad = cantidad;
        filaA = new int[cantidad];
        columnaA = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            filaA[i] = rand.nextInt(nCasillas);
            columnaA[i] = rand.nextInt(nCasillas);
            System.out.println("ANTIVIRUS " + (i+1) + ": " + filaA[i] + "," + columnaA[i]);
        }
    }

    public int[] getFilaA() {
        return filaA;
    }

    public void setFilaA(int[] filaA) {
        this.filaA = filaA;
    }

    public int[] getColumnaA() {
        return columnaA;
    }

    public void setColumnaA(int[] columnaA) {
        this.columnaA = columnaA;
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

	public int getnCasillas() {
		return nCasillas;
	}

	public void setnCasillas(int nCasillas) {
		this.nCasillas = nCasillas;
	}
    
}