package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import co.edu.unbosque.controller.Controlador;
import co.edu.unbosque.model.Matriz;

/**
 * Panel contenedor principal del escenario interactivo del juego
 * Integra de forma organizada la matriz gráfica del tablero (MatrizPanel) y una barra lateral 
 * derecha (panelStats) que despliega en tiempo real las estadísticas del juego, el contador de movimientos, 
 * los estados de sigilo y los botones de instrucciones y salida. Además, configura los mapeos de teclado.
 */
public class PanelJuego extends JPanel {
	/** Panel lateral derecho agrupa los textos informativos y de estadísticas 
	 * de la sesión.
	 */
    private JPanel panelStats;
    /** título superior del bloque de "Datos Del Juego"
    */
    private JLabel lblTituloStats;
    /** Etiqueta indicadora de los nodos de energía recolectados o restantes en el nivel.
     *  */
    private JLabel lblNodos;
    /** Etiqueta que muestra la dificultad configurada para la partida actual.
    */
    private JLabel lblDificultad;
    /** Etiqueta indicadora de la cantidad de Firewalls estáticos generados en el mapa.
    */
    private JLabel lblFirewalls;
    /** Etiqueta que actualiza en tiempo real los movimientos restantes del jugado.
    */
    private JLabel lblMovimientos;
    /** Etiqueta que actualiza en tiempo real los puertos de enlace capturados.
     */
    private JLabel lblPuertos;
    /** Etiqueta que indica los escaneres presentes en el juego.
     */
    private JLabel lblEscaneres;
    /** Etiqueta que indica la presencia de antivirus activos en el juego.
     */
    private JLabel lblAntivirus;
    /** Etiqueta que muestra si el modo de Sigilo está activa o inactiva 
     */
    private JLabel lblSigilo;
    /** Botón interactivo para abrir el cuadro informativo de reglas e instrucciones 
    */
    private JButton btnInstrucciones;
    /** Botón para ejecutar la activación del sigilo. 
    */
    private JButton btnSigilo;
    /** Botón de escape rápido para abandonar la partida volver al menú principal.
    */
    private JButton btnExit;

    /** Panel encargado dibujar la cuadrícula bidimensional de la matriz de casillas. */
    private MatrizPanel matrizPanel;
    /** Referencia directa al Controlador
     * 
     */
    private Controlador controlador;
    
    /**
     *metodo constructor del PanelJuego.
     * se encarga de la distribucion del marco general de la ventana principal.
     */

    public PanelJuego() {
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(10, 20, 40));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Inicializa por completo el escenario de juego y los paneles informativos .
     * Limpia elemento de partidas previas, enlaza el controlador, asienta 
     * los mapeos de los KeyBindings para responder a las teclas físicas y fuerza el redibujado de la interfaz.
     * * @param controlador Instancia del controlador que coordina el patrón MVC.
     * @param matriz Objeto lógico del modelo que contiene la disposición física de las celdas.
     * @param dificultad Texto descriptivo de la dificultad seleccionada ("Facil", "Normal", "Dificil").
     * @param maxMovimientos Límite máximo inicial de movimientos permitidos para el tamaño de la cuadrícula.
     * @param cantFirewalls Cantidad de firewalls cargados en el nivel.
     */
    public void inicializar(Controlador controlador, Matriz matriz, String dificultad, int maxMovimientos, int cantFirewalls) {
        removeAll();
        this.controlador = controlador;

        construirPanelStats(dificultad, maxMovimientos, cantFirewalls);
        construirMatrizPanel(matriz);
        configurarTeclado();

        revalidate();
        repaint();
    }
    
    /**
     * Crea e integra el botón encargado de controlar el Sigilo.
     * Aplica la estética neon/cibernética y define su comando de acción para el controlador.
     * * @param panelNorth El panel contenedor de la zona superior donde se incrustará el botón.
     */
    private void crearBotonSigilo(JPanel panelNorth) {
        btnSigilo = new JButton("SIGILO");
        btnSigilo.setActionCommand("SIGILO");
        btnSigilo.setForeground(new Color(100, 200, 255));
        btnSigilo.setBackground(new Color(10, 20, 40));
        btnSigilo.setFont(new Font("Consolas", Font.BOLD, 16));
        btnSigilo.setFocusPainted(false);
        btnSigilo.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        btnSigilo.setPreferredSize(new Dimension(0, 40));

        panelNorth.add(btnSigilo, BorderLayout.SOUTH);
    }

    /**
     * Construye y da formato a la barra lateral derecha destinada a las estadísticas.
     * Agrega los botones superiores, inicializa los textos informativos y configura la acción del botón de salida
     * * @param dificultad Texto con el nombre de la dificultad activa.
     * @param maxMovimientos Número de movimientos máximos cargados originalmente.
     * @param cantFirewalls Conteo de obstáculos tipo Firewall en el mapa.
     */
    private void construirPanelStats(String dificultad, int maxMovimientos, int cantFirewalls) {
        panelStats = new JPanel(new BorderLayout());
        panelStats.setBackground(new Color(15, 30, 60));
        panelStats.setPreferredSize(new Dimension(250, 0));
        panelStats.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 200, 255), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel panelNorth = new JPanel(new BorderLayout(0, 20));
        panelNorth.setBackground(new Color(15, 30, 60));

        btnInstrucciones = new JButton("INSTRUCCIONES");
        btnInstrucciones.setActionCommand("INSTRUCCIONES");
        btnInstrucciones.setForeground(new Color(100, 200, 255));
        btnInstrucciones.setBackground(new Color(10, 20, 40));
        btnInstrucciones.setFont(new Font("Consolas", Font.BOLD, 16));
        btnInstrucciones.setFocusPainted(false);
        btnInstrucciones.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        btnInstrucciones.setPreferredSize(new Dimension(0, 40));

        panelNorth.add(btnInstrucciones, BorderLayout.NORTH);
        
        
        crearBotonSigilo(panelNorth);

        JPanel panelLabels = new JPanel(new GridLayout(9, 1, 0, 4));
        panelLabels.setBackground(new Color(15, 30, 60));

        lblTituloStats = new JLabel("Datos Del Juego", SwingConstants.LEFT);
        lblTituloStats.setForeground(new Color(100, 200, 255));
        lblTituloStats.setFont(new Font("Consolas", Font.BOLD, 20));

        lblNodos = crearLabelStat("Nodos: 0");
        lblDificultad = crearLabelStat("Dificultad: " + dificultad);
        lblFirewalls = crearLabelStat("Firewalls: " + cantFirewalls);
        lblMovimientos = crearLabelStat("Movimientos: " + maxMovimientos);
        lblPuertos = crearLabelStat("Puertos: 0");
        lblEscaneres = crearLabelStat("Escáneres: 0");
        lblAntivirus = crearLabelStat("Antivirus: 0");
        lblSigilo = crearLabelStat("Sigilo: INACTIVO");

        panelLabels.add(lblTituloStats);
        panelLabels.add(lblNodos);
        panelLabels.add(lblDificultad);
        panelLabels.add(lblFirewalls);
        panelLabels.add(lblMovimientos);
        panelLabels.add(lblPuertos);
        panelLabels.add(lblEscaneres);
        panelLabels.add(lblAntivirus);
        panelLabels.add(lblSigilo);

        panelNorth.add(panelLabels, BorderLayout.CENTER);
        panelStats.add(panelNorth, BorderLayout.NORTH);

        btnExit = new JButton("SALIR");
        btnExit.setForeground(new Color(100, 200, 255));
        btnExit.setBackground(new Color(10, 20, 40));
        btnExit.setFont(new Font("Consolas", Font.BOLD, 16));
        btnExit.setFocusPainted(false);
        btnExit.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        btnExit.setPreferredSize(new Dimension(0, 40));

        btnExit.addActionListener(evento -> {
            java.awt.Component comp = SwingUtilities.getWindowAncestor(this);
            if (comp instanceof VentanaPrincipal) {
                ((VentanaPrincipal) comp).mostrarMenu();
            }
        });

        panelStats.add(btnExit, BorderLayout.SOUTH);

        add(panelStats, BorderLayout.EAST);
    }
    /**
     * Helper utilitario interno encargado de instanciar, alinear y estilar los labels 
     * * @param texto Cadena de caracteres inicial a desplegar en el label.
     * @return El componente configurado bajo la estética de ciberseguridad.
     */
    private JLabel crearLabelStat(String texto) {
        JLabel label = new JLabel(texto, SwingConstants.LEFT);
        label.setForeground(new Color(100, 200, 255));
        label.setFont(new Font("Consolas", Font.PLAIN, 14));
        return label;
    }
    /**
     * Construye de manera interna el panel de visualización de la matriz y lo acopla en la zona central.
     * * @param matriz La matriz lógica que contiene el estado de ocupación de las casillas.
     */
    private void construirMatrizPanel(Matriz matriz) {
        matrizPanel = new MatrizPanel(matriz);
        matrizPanel.setBackground(new Color(10, 20, 40));
        matrizPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 200, 255), 2));
        add(matrizPanel, BorderLayout.CENTER);
    }
    /**
     * Registra los mapas de entradas (InputMap) y acciones (ActionMap) de la ventana a través de KeyBindings.
     * Permite capturar las pulsaciones de las flechas de dirección de manera asíncrona, fluida y 
     * sin problemas de pérdida de foco en ventanas anidadas.
     */
    private void configurarTeclado() {
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Subir");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Bajar");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Izquierda");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Derecha");

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "Subir");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "Bajar");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "Izquierda");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "Derecha");

        this.getActionMap().put("Subir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(-1, 0);
                }
            }
        });

        this.getActionMap().put("Bajar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(1, 0);
                }
            }
        });

        this.getActionMap().put("Izquierda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, -1);
                }
            }
        });

        this.getActionMap().put("Derecha", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evento) {
                if (controlador != null) {
                    controlador.solicitarMovimiento(0, 1);
                }
            }
        });
    }
    /**
     * Modifica el texto en pantalla correspondiente a los nodos de energía recolectados.
     * * @param cantidad Entero con el nuevo conteo de nodos de energía activos.
     */
    public void actualizarNodos(int cantidad) {
        lblNodos.setText("Nodos: " + cantidad);
    }
    /**
     * Modifica el texto en pantalla que refleja los pasos de movimiento restantes del jugador.
     * * @param restantes Movimientos disponibles restantes en la sesión.
     */
    public void actualizarMovimientos(int restantes) {
        lblMovimientos.setText("Movimientos: " + restantes);
    }
    /**
     * Modifica la visualización textual de los Firewalls vigentes en el mapa lateral.
     * * @param cantidad Conteo de firewalls remanentes o generados.
     */
    public void actualizarFirewalls(int cantidad) {
        lblFirewalls.setText("Firewalls: " + cantidad);
    }
    /**
     * Modifica la etiqueta de texto que indica la cantidad de puertos de enlace resueltos.
     * * @param cantidad Puertos capturados con éxito por el infiltrador.
     */
    public void actualizarPuertos(int cantidad) {
        lblPuertos.setText("Puertos: " + cantidad);
    }
    /**
     * Modifica el indicador de texto de la cantidad de Escáneres de Latencia presentes.
     * * @param cantidad Cantidad total de escáneres lógicos en el escenario.
     */
    public void actualizarEscaneres(int cantidad) {
        lblEscaneres.setText("Escáneres: " + cantidad);
    }
    /**
     * Modifica la visualización textual de la cantidad de Antivirus proactivos en el nivel.
     * * @param cantidad Cantidad de unidades de antivirus activas.
     */
    public void actualizarAntivirus(int cantidad) {
        lblAntivirus.setText("Antivirus: " + cantidad);
    }
    /**
     * Alterna de forma gráfica la etiqueta de la habilidad de sigilo entre los textos ACTIVO e INACTIVO.
     * * @param activo true si el modo de camuflaje de red está operando; false si es visible para los antivirus.
     */
    public void actualizarSigilo(boolean activo) {
        lblSigilo.setText("Sigilo: " + (activo ? "ACTIVO" : "INACTIVO"));
    }
    /**
     * Obtiene el componente del panel visual encargado de pintar las lineas/celdas de casillas.
     * * @return Instancia actual de MatrizPanel.
     */
    public MatrizPanel getMatrizPanel() {
        return matrizPanel;
    }
    /**
     * Desvincula por completo el panel de la cuadrícula anterior e inyecta un objeto 
     * MatrizPanel actualizado con el nuevo estado posicional del modelo, forzando un redibujado inmediato.
     * * @param matriz El nuevo objeto Matriz con los datos a renderizar.
     */
    public void actualizarMatriz(Matriz matriz) {
        if (matrizPanel != null) {
            remove(matrizPanel);
        }
        construirMatrizPanel(matriz);
        revalidate();
        repaint();
    }
    /**
     * Obtiene la referencia del botón de Instrucciones.
     * @return El objeto JButton de instrucciones.
     */
    public JButton getBtnInstrucciones() {
        return btnInstrucciones;
    }
    /**
     * Obtiene la referencia del botón de activación de Sigilo.
     * @return El objeto JButton de sigilo.
     */
    public JButton getBtnSigilo() {
        return btnSigilo;
    }
    /**
     * Obtiene la referencia del botón de Salida.
     * @return El objeto JButton de salida rápida.
     */
    public JButton getBtnExit() {
        return btnExit;
    }
}