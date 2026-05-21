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

        // CORREGIDO: Ahora se usa getMenuPrincipal() o el método equivalente que tengas en VentanaPrincipal
        for (String elem : fachada.getDificultades()) {
            ventana.getMenuPrincipal().getCbxDificultades().addItem(elem);
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
        // CORREGIDO: Oyentes apuntando a MenuPrincipal
        ventana.getMenuPrincipal().getCbxDificultades().addActionListener(this);
        ventana.getMenuPrincipal().getBtnJugar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("DIFICULTAD")) {
            // Manejo de eventos de dificultad si se requiere
        } else if (command.equals("JUGAR")) {
            
            // CORREGIDO: Captura de datos desde getMenuPrincipal()
            String textoTamano = ventana.getMenuPrincipal().getTxtTamanoTablero().getText().trim();
            String dSeleccionada = ventana.getMenuPrincipal().getCbxDificultades().getSelectedItem().toString();
            
            if (textoTamano.isEmpty()) {
                ventanaE.mostrarInformacion("Por favor, ingresa un tamaño para el tablero.");
                return;
            }
            
            int tamanoNum = 0;
            try {
                tamanoNum = Integer.parseInt(textoTamano);
                if (tamanoNum <= 5 || tamanoNum > 21) {
                    ventanaE.mostrarInformacion("El tamaño debe estar entre 5 y 20.");
                    return;
                }
            } catch (NumberFormatException nfe) {
                ventanaE.mostrarInformacion("Error: Debes ingresar únicamente números enteros.");
                return;
            }

            fachada.configurarTablero(dSeleccionada, String.valueOf(tamanoNum));
            
            movimiento = 0;
            ventana.revalidate();
            actualizarVista();
            ventana.mostrarJuego();
        }
    }
}