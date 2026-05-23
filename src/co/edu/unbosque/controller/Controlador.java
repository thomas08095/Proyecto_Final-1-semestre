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
	private int puertosRecolectados;
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
		ventana.getPanelJuego().actualizarNodos(fachada.getCantidadNodo() - nodosRecolectados);
		ventana.getPanelJuego().actualizarFirewalls(fachada.getMatriz().getListaFirewall().length);
		ventana.getPanelJuego().actualizarPuertos(fachada.getMatriz().getListaPuertosEnlace().length - puertosRecolectados);
		ventana.getPanelJuego().actualizarEscaneres(fachada.getMatriz().getListaEscaners().length);
		ventana.getPanelJuego().actualizarAntivirus(fachada.getMatriz().getListaAntivirus().length);
		ventana.getPanelJuego().actualizarSigilo(fachada.isModoSigiloActivo());
	}

	public void asignarOyentes() {
		ventana.getMenuPrincipal().getCbxDificultades().addActionListener(this);
		ventana.getMenuPrincipal().getCbxCasillas().addActionListener(this);
		ventana.getMenuPrincipal().getBtnJugar().addActionListener(this);
	}

	private void reiniciarPartida() {
		movimientosRealizados = 0;
		nodosRecolectados = 0;
		puertosRecolectados = 0;
		maxMovimientos = 0;
		fachada.setOrdenInverso(false);
		ventana.mostrarMenu();
	}

	public void solicitarMovimiento(int deltaX, int deltaY) {
		boolean movioOk = fachada.solicitarMovimiento(deltaX, deltaY);
		if (!movioOk) {
			return;
		}

		movimientosRealizados++;
		int restantes = maxMovimientos - movimientosRealizados;
		String eventoTurno = "";

		fachada.moverAntivirus();
		fachada.moverEscanerL();
		actualizarVista();

		if (restantes <= 0) {
			eventoTurno = "Sin movimientos — Derrota";
			fachada.registrarMovimiento(movimientosRealizados, fachada.getInfiltradoX(), fachada.getInfiltradoY(), eventoTurno);
			String rutaSinMov = fachada.exportarHistorial("DERROTA — Sin movimientos", movimientosRealizados, nodosRecolectados);
			ventanaE.mostrarInformacion("¡Te quedaste sin movimientos!\n\nHistorial guardado en:\n" + rutaSinMov);
			reiniciarPartida();
			return;
		}

		if (fachada.isModoSigiloActivo()) {
			eventoTurno = "Sigilo activo — amenazas ignoradas";
			fachada.desactivarSigilo();
		} else {
			if (fachada.detectarAntivirus()) {
				eventoTurno = "Contacto con Antivirus — Game Over";
				fachada.registrarMovimiento(movimientosRealizados, fachada.getInfiltradoX(), fachada.getInfiltradoY(), eventoTurno);
				String rutaAntivirus = fachada.exportarHistorial("DERROTA — Antivirus", movimientosRealizados, nodosRecolectados);
				ventanaE.mostrarInformacion("¡Game Over!\n\nHistorial guardado en:\n" + rutaAntivirus);
				reiniciarPartida();
				return;
			}
			if (fachada.detectarEscanerL()) {
				int penalizacion = (int) (restantes * 0.05);
				maxMovimientos -= penalizacion;
				eventoTurno = "Escáner de Latencia — -" + penalizacion + " movimientos";
				fachada.registrarMovimiento(movimientosRealizados, fachada.getInfiltradoX(), fachada.getInfiltradoY(), eventoTurno);
				ventanaE.mostrarInformacion("¡Encontraste un Escáner de Latencia!\n  -" + penalizacion + " movimientos menos.");
				return;
			}
		}

		if (fachada.detectarNodoEnergia()) {
			int bonus = (int) (restantes * 0.10);
			maxMovimientos += bonus;
			nodosRecolectados++;
			eventoTurno = "Nodo de Energía recolectado — +" + bonus + " movimientos";
			ventanaE.mostrarInformacion("¡Encontraste un Nodo de Energía!\n  +" + bonus + " movimientos extra.");
		}

		int estadoPuerto = fachada.detectarPuertoEnlace(puertosRecolectados);
		if (estadoPuerto == 1) {
			puertosRecolectados++;
			eventoTurno += (eventoTurno.isEmpty() ? "" : " | ") + "Puerto de Enlace entregado (" + puertosRecolectados + ")";
			ventanaE.mostrarInformacion("¡Llevaste un paquete al Puerto de Enlace!\n                               "
					+ puertosRecolectados + "/" + fachada.getMatriz().getListaPuertosEnlace().length);
			if (fachada.getMatriz().getListaPuertosEnlace().length == puertosRecolectados) {
				fachada.registrarMovimiento(movimientosRealizados, fachada.getInfiltradoX(), fachada.getInfiltradoY(), eventoTurno);
				String rutaVictoria = fachada.exportarHistorial("VICTORIA", movimientosRealizados, nodosRecolectados);
				ventanaE.mostrarInformacion("¡Encontraste Todos los Puertos de Enlace!\n\nHistorial guardado en:\n" + rutaVictoria);
				reiniciarPartida();
			}
			actualizarVista();
		} else if (estadoPuerto == -1) {
			ventanaE.mostrarInformacion("¡Este no es el orden correcto, sigue intentando!");
		}

		fachada.registrarMovimiento(movimientosRealizados, fachada.getInfiltradoX(), fachada.getInfiltradoY(), eventoTurno);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("CASILLA") || command.equals("DIFICULTAD")) {
			ventana.getMenuPrincipal().getBtnJugar().setEnabled(true);

		} else if (command.equals("JUGAR")) {
			ventanaE.mostrarInformacion("- Instrucciones del Juego -\r\n"
					+ "\r\n"
					+ "- Debes mover el paquete de datos por el servidor.\r\n"
					+ "- Recorre todos los puertos de enlace en el orden indicado.\r\n"
					+ "- Evita los Antivirus Proactivos y los Escáneres de Latencia.\r\n"
					+ "- Los Nodos de Energía restauran movimientos.\r\n"
					+ "- Los Firewalls generan penalizaciones.\r\n"
					+ "- Solo puedes moverte vertical y horizontalmente.\r\n"
					+ "\r\n"
					+ "- IMPORTANTE:\r\n"
					+ "  Consultar los Protocolos de Red durante la partida\r\n"
					+ "  descontará 10 movimientos automáticamente.\r\n"
					+ "\r\n"
					+ "¿Deseas continuar?");
			String cSeleccionada = ventana.getMenuPrincipal().getCbxCasillas().getSelectedItem().toString();
			String dSeleccionada = ventana.getMenuPrincipal().getCbxDificultades().getSelectedItem().toString();

			fachada.configurarTablero(dSeleccionada, cSeleccionada);
			movimientosRealizados = 0;
			nodosRecolectados = 0;
			maxMovimientos = fachada.numeroCasillas();

			ventana.getPanelJuego().inicializar(this, fachada.getMatriz(), dSeleccionada, maxMovimientos,fachada.getCantidadAntivirus());
			ventana.getPanelJuego().getBtnInstrucciones().addActionListener(this);
			ventana.getPanelJuego().getBtnSigilo().addActionListener(this);

			actualizarVista();
			ventana.mostrarJuego();
		}
		else if(command.equals("ORDENINVERSO")){
			fachada.setOrdenInverso(true);
		}
		else if(command.equals("INSTRUCCIONES")){
			ventanaE.mostrarInformacion("\r\n"
					+ "  Protocolos de Red\r\n"
					+ "\r\n"
					+ "• Antivirus Proactivo: elimina al jugador si está en una casilla contigua.\r\n"
					+ "• Escáner de Latencia: reduce el 5% de movimientos restantes.\r\n"
					+ "• Firewalls: generan penalizaciones al pasar entre ellos.\r\n"
					+ "• Nodos de Energía: recuperan movimientos.\r\n"
					+ "• Puertos de Enlace: deben recorrerse en orden.\r\n"
					+ "\r\n"
					+ "Has perdido 10 movimientos por consultar los protocolos.");
			maxMovimientos -= 10;
			actualizarVista();
		}
		else if(command.equals("SIGILO")){
			fachada.activarSigilo();
			ventanaE.mostrarInformacion("\r\n"
					+ "  Has activado el modo sigilo\r\n"
					+ "\r\n"
					+ "• Por el sigiente turno, ningun encuentro con amenaza te afectará.\r\n");
		}
	}
}