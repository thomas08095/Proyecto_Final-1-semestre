package co.edu.unbosque.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import co.edu.unbosque.model.Fachada;
import co.edu.unbosque.view.Casilla;
import co.edu.unbosque.view.VentanaEmergente;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controlador implements ActionListener {

    private VentanaPrincipal ventana;
    private VentanaEmergente ventanaE;
    private Fachada fachada;
    private Casilla[][] matrizCasillas;
    private int movimiento;

    public Controlador() {
        fachada = new Fachada();
        ventana = new VentanaPrincipal();
        ventanaE = new VentanaEmergente();

        for (String elem : fachada.getDificultades()) {
            ventana.getVentanaBoton().getCbxDificultades().addItem(elem);
        }
        for (String elem : fachada.getCasillas()) {
            ventana.getVentanaBoton().getCbxCasillas().addItem(elem);
        }
        asignarOyentes();
    }
    
    

    private void actualizarVista() {
        int filas = fachada.getFilas();
        int columnas = fachada.getColumnas();

        matrizCasillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizCasillas[i][j] = new Casilla();
            }
        }
        
        System.out.println(fachada.getScriptX());
        System.out.println(fachada.getScriptY());

        matrizCasillas[fachada.getScriptX()][fachada.getScriptY()].setColor(Color.GREEN);

        for (int i = 0; i < fachada.getCantidadAntivirus(); i++) {
            int fila = fachada.getFilasAntivirus()[i];
            int col = fachada.getColumnasAntivirus()[i];
            matrizCasillas[fila][col].setColor(Color.RED);
        }

        for (int i = 0; i < fachada.getCantidadNodo(); i++) {
            int fila = fachada.getFilasNodo()[i];
            int col = fachada.getColumnasNodo()[i];
            matrizCasillas[fila][col].setColor(Color.YELLOW);
        }

        ventana.getVistaJuego().setMatriz(matrizCasillas, this);
    }

    public void solicitarMovimiento(int deltaX, int deltaY) {
        int anteriorX = fachada.getScriptX();
        int anteriorY = fachada.getScriptY();

        boolean movioOk = fachada.solicitarMovimiento(deltaX, deltaY);

        if (movioOk) {
            matrizCasillas[anteriorX][anteriorY].limpiar();
            matrizCasillas[fachada.getScriptX()][fachada.getScriptY()].setColor(Color.GREEN);
            movimiento = movimiento + 1;
            System.out.println(movimiento);

            if (movimiento == fachada.numeroCasillas()) {
                ventanaE.mostrarInformacion("Te quedaste sin movimientos!");
                movimiento = 0;
                ventana.mostrarMenu();
            }

            if (fachada.detectarAntivirus()) {
                ventanaE.mostrarInformacion("Game Over");
                movimiento = 0;
                ventana.mostrarMenu();
            }

            if (fachada.detectarNodoEnergia()) {
                ventanaE.mostrarInformacion("¡Encontraste un Nodo De Energía!");
                matrizCasillas[fachada.getScriptX()][fachada.getScriptY()].setColor(Color.GREEN);
            }
        }
    }

    public void asignarOyentes() {
        ventana.getVentanaBoton().getCbxDificultades().addActionListener(this);
        ventana.getVentanaBoton().getCbxCasillas().addActionListener(this);
        ventana.getVentanaBoton().getBtnJugar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("CASILLA") || command.equals("DIFICULTAD")) {
            ventana.getVentanaBoton().getBtnJugar().setEnabled(true);

        } else if (command.equals("JUGAR")) {
            String cSeleccionada = ventana.getVentanaBoton().getCbxCasillas().getSelectedItem().toString();
            String dSeleccionada = ventana.getVentanaBoton().getCbxDificultades().getSelectedItem().toString();
            fachada.configurarTablero(dSeleccionada, cSeleccionada);
            movimiento = 0;
            ventana.revalidate();
            actualizarVista();
            ventana.mostrarJuego();
        }
    }
}