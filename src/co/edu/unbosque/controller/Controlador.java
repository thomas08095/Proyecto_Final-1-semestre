package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import co.edu.unbosque.model.Dificultad;
import co.edu.unbosque.view.Casilla;
import co.edu.unbosque.view.VentanaEmergente;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controlador implements ActionListener {
    private VentanaPrincipal ventana;
    private VentanaEmergente ventanaE;
  
    private int scriptX;
    private int scriptY;
    private int limiteFilas;
    private int limiteColumnas;
    private Casilla[][] matrizCasillas;
    

    public Controlador() {
        ventana = new VentanaPrincipal();
        
        ventanaE = new VentanaEmergente();
  
        Dificultad dificultad = new Dificultad();
        for (String elem : dificultad.getElementos()) {
            ventana.getVentanaBoton().getCbxDificultades().addItem(elem);
        }
        
        asignarOyentes();
     
        configurarNuevoTablero(5, 5);
    }
    private void configurarNuevoTablero(int filas, int columnas) {
        this.limiteFilas = filas;
        this.limiteColumnas = columnas;
            
        this.scriptX = 0;
        this.scriptY = 0;
        
        this.matrizCasillas = poblarMatriz(filas, columnas);
        
      
        ventana.getVistaJuego().setMatriz(matrizCasillas, this);
    }
    
    private Casilla[][] poblarMatriz(int filas, int columnas) {
        Casilla[][] nuevaMatriz = new Casilla[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nuevaMatriz[i][j] = new Casilla(); 
                if (i == scriptX && j == scriptY) {
                    nuevaMatriz[i][j].setImagen("src/imagenes/tomoe-prueba.jpeg");
                }
            }
        }
        return nuevaMatriz;
    }
    
    public void solicitarMovimiento(int deltaX, int deltaY) {
        int nuevaX = scriptX + deltaX;
        int nuevaY = scriptY + deltaY;
      
        if (nuevaX >= 0 && nuevaX < limiteFilas && nuevaY >= 0 && nuevaY < limiteColumnas) {
            
             matrizCasillas[scriptX][scriptY].setImagen(null); 
            
            this.scriptX = nuevaX;
            this.scriptY = nuevaY;
            
            matrizCasillas[scriptX][scriptY].setImagen("src/imagenes/tomoe-prueba.jpeg");
            
            int x = scriptX;
            int y = scriptY;
            
            if (x==1&&y==1) {
            	ventanaE.mostrarInformacion("PERDISTE");
            	
            }
            
            System.out.print(scriptX+","+scriptY+"\n");

        }
    }
    
    public void asignarOyentes() {
        ventana.getVentanaBoton().getCbxDificultades().addActionListener(this);
        ventana.getVentanaBoton().getBtnJugar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String dSeleccionada = ventana.getVentanaBoton().getCbxDificultades().getSelectedItem().toString();    
        
        if (command.equals("DIFICULTAD")) {
            ventana.getVentanaBoton().getBtnJugar().setEnabled(true);
            
        } else if (command.equals("JUGAR")) {
            ventana.getVentanaBoton().getBtnJugar().setEnabled(true);      
    
            if (dSeleccionada.equalsIgnoreCase("Facil")) {
                configurarNuevoTablero(5, 5);
            } else if (dSeleccionada.equalsIgnoreCase("Normal") || dSeleccionada.equalsIgnoreCase("Medio")) {
                configurarNuevoTablero(10, 10);
            } else if (dSeleccionada.equalsIgnoreCase("Dificil")) {
                configurarNuevoTablero(15, 15);
            }
            ventana.mostrarJuego();
        }
    }
}