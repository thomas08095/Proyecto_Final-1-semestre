package co.edu.unbosque.controller;

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
		fachada = new Fachada();
		ventana = new VentanaPrincipal();
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
		fachada.reconstruirMatriz();
		ventana.getPanelJuego().actualizarMatriz(fachada.getMatriz());

		int restantes = maxMovimientos - movimientosRealizados;
		ventana.getPanelJuego().actualizarMovimientos(restantes);
		ventana.getPanelJuego().actualizarNodos(nodosRecolectados);
		ventana.getPanelJuego().actualizarFirewalls(fachada.getCantidadAntivirus());
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
			nodosRecolectados = 0;
			maxMovimientos = fachada.numeroCasillas();

			ventana.getPanelJuego().inicializar(this, fachada.getMatriz(), dSeleccionada, maxMovimientos,fachada.getCantidadAntivirus());

			actualizarVista();
			ventana.mostrarJuego();
		}
	}

	private void reiniciarPartida() {
		movimientosRealizados = 0;
		nodosRecolectados = 0;
		maxMovimientos = 0;
		ventana.mostrarMenu();
	}

	public void solicitarMovimiento(int deltaX, int deltaY) {
		boolean movioOk = fachada.solicitarMovimiento(deltaX, deltaY);
		if (!movioOk) {
			return;
		}

		movimientosRealizados++;
		int restantes = maxMovimientos - movimientosRealizados;

		fachada.moverAntivirus();
		fachada.moverEscanerL();
		actualizarVista();

		if (restantes <= 0) {
			ventanaE.mostrarInformacion("¡Te quedaste sin movimientos!");
			reiniciarPartida();
			return;
		}

		if (fachada.detectarAntivirus()) {
			ventanaE.mostrarInformacion("¡Game Over!");
			reiniciarPartida();
			return;
		}
		
		if (fachada.detectarEscanerL()) {
			ventanaE.mostrarInformacion("¡Encontraste un Escaner de Latencia!");
			return;
		}

		if (fachada.detectarNodoEnergia()) {
			int bonus = (int) (restantes * 0.10);
			maxMovimientos += bonus;
			nodosRecolectados++;
			ventanaE.mostrarInformacion("¡Encontraste un Nodo de Energía!\n+" + bonus + " movimientos extra.");
			actualizarVista();
		}
	}

}
