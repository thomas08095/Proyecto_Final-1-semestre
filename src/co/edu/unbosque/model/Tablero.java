package co.edu.unbosque.model;

public class Tablero {
        int[][] tablero = new int[10][10];
        public Tablero(int filas, int columnas) {
            this.tablero = new int[filas][columnas];
        }

        public int[][] getMatriz() {
            return tablero;
        }       
	public void imprimrMatriz() {
        for (int i = 0; i<10;i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
	}
}
        


