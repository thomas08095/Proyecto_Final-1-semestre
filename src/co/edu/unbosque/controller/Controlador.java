package co.edu.unbosque.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.model.Fachada;
import co.edu.unbosque.view.VentanaEmergente;
import co.edu.unbosque.view.VentanaPrincipal;

public class Controlador implements ActionListener {

    private VentanaPrincipal ventana;
    private VentanaEmergente ventanaE;
    private Fachada fachada;

    private int movimientosRealizados;
    private int nodosRecolectados;
    private int maxMovimientos;

    public Controlador() {
        fachada  = new Fachada();
        ventana  = new VentanaPrincipal();
        ventanaE = new VentanaEmergente();

        for (String elem : fachada.getDificultades()) {
            ventana.getMenuPrincipal().getCbxDificultades().addItem(elem);
        }
        for (String elem : fachada.getCasillas()) {
            ventana.getMenuPrincipal().getCbxCasillas().addItem(elem);
        }

        asignarOyentes();
    }

    private void actualizarVista() {
        int filas    = fachada.getFilas();
        int columnas = fachada.getColumnas();

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                ventana.getPanelJuego().limpiarCelda(i, j);
            }
        }

        ventana.getPanelJuego().setColorCelda(
                fachada.getScriptX(), fachada.getScriptY(), Color.GREEN);

        for (int i = 0; i < fachada.getCantidadAntivirus(); i++) {
            int fila = fachada.getFilasAntivirus()[i];
            int col  = fachada.getColumnasAntivirus()[i];
            if (fila != -1 && col != -1) {
                ventana.getPanelJuego().setColorCelda(fila, col, Color.RED);
            }
        }

        for (int i = 0; i < fachada.getCantidadNodo(); i++) {
            int fila = fachada.getFilasNodo()[i];
            int col  = fachada.getColumnasNodo()[i];
            if (fila != -1 && col != -1) {
                ventana.getPanelJuego().setColorCelda(fila, col, Color.YELLOW);
            }
        }

        int restantes = maxMovimientos - movimientosRealizados;
        ventana.getPanelJuego().actualizarMovimientos(restantes);
        ventana.getPanelJuego().actualizarNodos(nodosRecolectados);
        ventana.getPanelJuego().actualizarFirewalls(fachada.getCantidadAntivirus());
    }

    public void solicitarMovimiento(int deltaX, int deltaY) {
        boolean movioOk = fachada.solicitarMovimiento(deltaX, deltaY);
        if (!movioOk) return;

        movimientosRealizados++;
        int restantes = maxMovimientos - movimientosRealizados;

        actualizarVista();

        if (restantes <= 0) {
            ventanaE.mostrarInformacion("¡Te quedaste sin movimientos!\nPuntuación: "
                    + nodosRecolectados + " nodos recolectados.");
            reiniciarPartida();
            return;
        }

        if (fachada.detectarAntivirus()) {
            ventanaE.mostrarInformacion("¡Game Over! Chocaste con un Antivirus.\nPuntuación: "
                    + nodosRecolectados + " nodos recolectados.");
            reiniciarPartida();
            return;
        }

        if (fachada.detectarNodoEnergia()) {
            int bonus = (int) Math.ceil(restantes * 0.10);
            maxMovimientos += bonus;
            nodosRecolectados++;
            ventanaE.mostrarInformacion("¡Encontraste un Nodo de Energía!\n+"
                    + bonus + " movimientos extra.");
            actualizarVista();
        }
    }

    private void reiniciarPartida() {
        movimientosRealizados = 0;
        nodosRecolectados     = 0;
        maxMovimientos        = 0;
        ventana.mostrarMenu();
    }

    public void asignarOyentes() {
        ventana.getMenuPrincipal().getCbxDificultades().addActionListener(this);
        ventana.getMenuPrincipal().getCbxCasillas().addActionListener(this);
        ventana.getMenuPrincipal().getBtnJugar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("CASILLA") || command.equals("DIFICULTAD")) {
            ventana.getMenuPrincipal().getBtnJugar().setEnabled(true);

        } else if (command.equals("JUGAR")) {
            String cSeleccionada = ventana.getMenuPrincipal().getCbxCasillas().getSelectedItem().toString();
            String dSeleccionada = ventana.getMenuPrincipal().getCbxDificultades().getSelectedItem().toString();

            fachada.configurarTablero(dSeleccionada, cSeleccionada);
            movimientosRealizados = 0;
            nodosRecolectados     = 0;
            maxMovimientos        = fachada.numeroCasillas();

            ventana.getPanelJuego().inicializar(
                    this,
                    fachada.getFilas(),
                    fachada.getColumnas(),
                    dSeleccionada,
                    maxMovimientos,
                    fachada.getCantidadAntivirus()
            );

            actualizarVista();
            ventana.mostrarJuego();
        }
    }
}