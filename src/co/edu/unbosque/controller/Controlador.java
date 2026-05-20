package co.edu.unbosque.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import co.edu.unbosque.model.Fachada;
import co.edu.unbosque.view.Casilla;
import co.edu.unbosque.view.VentanaEmergente;
import co.edu.unbosque.view.VentanaPrincipal;

// Clase encargada de coordinar la interacción entre la interfaz de usuario y la lógica matemática
public class Controlador implements ActionListener {

    private VentanaPrincipal ventana;
    private VentanaEmergente ventanaE;
    private Fachada fachada;
    private Casilla[][] matrizCasillas;
    private int movimiento;

    // Constructor: Configura las instancias base e inicializa los selectores del menú
    public Controlador() {
        fachada = new Fachada();
        ventana = new VentanaPrincipal();
        ventanaE = new VentanaEmergente();

        // Llena las opciones de los JComboBox con los datos provenientes del modelo
        for (String elem : fachada.getDificultades()) {
            ventana.getMenuPrincipal().getCbxDificultades().addItem(elem);
        }
        for (String elem : fachada.getCasillas()) {
            ventana.getMenuPrincipal().getCbxCasillas().addItem(elem);
        }
        
        asignarOyentes();
    }

    // Método encargado de refrescar el estado de los componentes visuales existentes
    private void actualizarVista() {
        int filas = fachada.getFilas();
        int columnas = fachada.getColumnas();

        // 1. Limpieza total de los colores anteriores en las casillas físicas de la pantalla
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matrizCasillas != null && matrizCasillas[i][j] != null) {
                    matrizCasillas[i][j].limpiar();
                }
            }
        }
        
        // 2. Colorea de verde la celda donde está posicionado el jugador
        matrizCasillas[fachada.getScriptX()][fachada.getScriptY()].setColor(Color.GREEN);

        // 3. Colorea de rojo las celdas asignadas a los Antivirus enemigos
        for (int i = 0; i < fachada.getCantidadAntivirus(); i++) {
            int fila = fachada.getFilasAntivirus()[i];
            int col = fachada.getColumnasAntivirus()[i];
            
            if (fila != -1 && col != -1) {
                matrizCasillas[fila][col].setColor(Color.RED);
            }
        }

        // 4. Colorea de amarillo las celdas correspondientes a los Nodos de Energía activos
        for (int i = 0; i < fachada.getCantidadNodo(); i++) {
            int fila = fachada.getFilasNodo()[i];
            int col = fachada.getColumnasNodo()[i];
            
            // Validación para evitar pintar nodos que ya han sido consumidos (-1)
            if (fila != -1 && col != -1) {
                matrizCasillas[fila][col].setColor(Color.YELLOW);
            }
        }
    }

    // Gestiona las solicitudes de movimiento enviadas desde los KeyBindings del panel
    public void solicitarMovimiento(int deltaX, int deltaY) {
        // Valida el movimiento dentro de las reglas lógicas del modelo
        boolean movioOk = fachada.solicitarMovimiento(deltaX, deltaY);

        if (movioOk) {
            // Incrementa el contador tras realizar un paso válido
            movimiento = movimiento + 1;
            int movimientoR = fachada.numeroCasillas()-movimiento;
            System.out.println("Movimientos realizados: " + movimiento);

            // Redibuja el nuevo mapa en la pantalla con las posiciones actualizadas
            actualizarVista();

            // Evalúa si el contador alcanzó la cantidad máxima permitida por el mapa
            if (movimientoR ==0) {
                ventanaE.mostrarInformacion("¡Te quedaste sin movimientos!");
                movimiento = 0;
                ventana.mostrarMenu();
                return;
            }

            // Evalúa colisiones letales contra los antivirus
            if (fachada.detectarAntivirus()) {
                ventanaE.mostrarInformacion("Game Over");
                movimiento = 0;
                ventana.mostrarMenu();
                return;
            }

            // Evalúa la recolección de recompensas de energía
            if (fachada.detectarNodoEnergia()) {
            	int nodo = (int) (movimientoR*0.10);
                movimientoR= (int) (movimientoR+(movimientoR*0.10));
                ventanaE.mostrarInformacion("¡Encontraste un Nodo De Energía!"+
                "\n Te dieron un total de "+nodo+" movimientos extra" );
                actualizarVista(); // Refresca de inmediato para hacer desaparecer el nodo recolectado
            }
        }
    }

    // Asocia los disparadores de eventos con los componentes interactivos del menú de inicio
    public void asignarOyentes() {
        ventana.getMenuPrincipal().getCbxDificultades().addActionListener(this);
        ventana.getMenuPrincipal().getCbxCasillas().addActionListener(this);
        ventana.getMenuPrincipal().getBtnJugar().addActionListener(this);
    }

    // Captura los clics y ejecuciones provenientes del menú de configuraciones
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("CASILLA") || command.equals("DIFICULTAD")) {
            ventana.getMenuPrincipal().getBtnJugar().setEnabled(true);

        } else if (command.equals("JUGAR")) {
            String cSeleccionada = ventana.getMenuPrincipal().getCbxCasillas().getSelectedItem().toString();
            String dSeleccionada = ventana.getMenuPrincipal().getCbxDificultades().getSelectedItem().toString();
            
            // Establece los parámetros matemáticos internos del mapa
            fachada.configurarTablero(dSeleccionada, cSeleccionada);
            movimiento = 0;
            
            // Inicializa la estructura visual del grid una sola vez al empezar la partida
            ventana.getVistaJuego().inicializarVistaTablero(this, fachada.getFilas(), fachada.getColumnas());
            
            // Vincula la referencia local con el contenedor de componentes gráficos reales de la pantalla
            matrizCasillas = ventana.getVistaJuego().getPanelTablero().getMatrizCasillas();
            
            // Aplica los colores iniciales de las entidades
            actualizarVista();
            
            // Transiciona la pantalla hacia la interfaz de juego activa
            ventana.mostrarJuego();
        }
    }
}