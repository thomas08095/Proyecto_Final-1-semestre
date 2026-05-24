package co.edu.unbosque.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de registrar y exportar el historial completo de una partida.
 * Almacena cada movimiento realizado por el jugador junto con los eventos ocurridos
 * en ese turno y genera un archivo de texto en la carpeta src/logs al finalizar la partida.
 */
public class HistorialPartida {

    /**
     * Lista que acumula cada línea del historial, una por turno jugado.
     */
    private List<String> movimientos;

    /**
     * Nombre de la dificultad seleccionada para la partida (Facil, Normal, Dificil).
     */
    private String dificultad;

    /**
     * Tamaño del tablero en número de casillas por lado (10, 15 o 20).
     */
    private int tamanoTablero;

    /**
     * Fecha y hora exacta en que comenzó la partida, usada para nombrar el archivo exportado.
     */
    private LocalDateTime fechaInicio;

    /**
     * Constructor de HistorialPartida. Inicializa la lista de movimientos y registra
     * la fecha de inicio de la partida.
     * @param dificultad Dificultad seleccionada para la partida.
     * @param tamanoTablero Tamaño del tablero en casillas por lado.
     */
    public HistorialPartida(String dificultad, int tamanoTablero) {
        this.dificultad = dificultad;
        this.tamanoTablero = tamanoTablero;
        this.movimientos = new ArrayList<>();
        this.fechaInicio = LocalDateTime.now();
    }

    /**
     * Registra un turno en el historial con la posición del jugador y el evento ocurrido.
     * Si no ocurrió ningún evento especial, el campo evento puede ir vacío.
     * @param turno Número del turno jugado.
     * @param fila Fila en la que quedó el jugador tras el movimiento.
     * @param columna Columna en la que quedó el jugador tras el movimiento.
     * @param evento Descripción del evento ocurrido en ese turno (puede ser vacío).
     */
    public void registrarMovimiento(int turno, int fila, int columna, String evento) {
        String linea = "Turno " + turno + ": Jugador se mueve a (" + fila + "," + columna + ")";
        if (evento != null && !evento.isEmpty()) {
            linea += " — " + evento;
        }
        movimientos.add(linea);
    }

    /**
     * Exporta el historial completo a un archivo de texto en la carpeta src/logs.
     * El nombre del archivo incluye la fecha y hora de inicio de la partida.
     * Retorna la ruta del archivo generado, o null si ocurrió un error al escribir.
     * @param resultado Texto que describe el resultado final (VICTORIA, DERROTA — Antivirus, etc.).
     * @param movimientosUsados Total de movimientos realizados durante la partida.
     * @param nodosRecolectados Cantidad de nodos de energía recolectados durante la partida.
     * @return Ruta relativa del archivo generado, o null si falló la exportación.
     */
    public String exportar(String resultado, int movimientosUsados, int nodosRecolectados) {
        DateTimeFormatter formatterArchivo = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        DateTimeFormatter formatterLegible = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        new File("src/logs").mkdirs();
        String nombreArchivo = "src/logs/historial_" + fechaInicio.format(formatterArchivo) + ".txt";

        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            escritor.write("========================================\n");
            escritor.write("       CYBER INFILTRATOR — HISTORIAL    \n");
            escritor.write("========================================\n");
            escritor.write("Fecha:       " + fechaInicio.format(formatterLegible) + "\n");
            escritor.write("Dificultad:  " + dificultad + "\n");
            escritor.write("Tablero:     " + tamanoTablero + "x" + tamanoTablero + "\n");
            escritor.write("----------------------------------------\n\n");

            for (String linea : movimientos) {
                escritor.write(linea + "\n");
            }

            escritor.write("\n----------------------------------------\n");
            escritor.write("RESULTADO:           " + resultado + "\n");
            escritor.write("Movimientos usados:  " + movimientosUsados + "\n");
            escritor.write("Nodos recolectados:  " + nodosRecolectados + "\n");
            escritor.write("========================================\n");

            System.out.println("Historial exportado: " + nombreArchivo);

        } catch (IOException excepcion) {
            System.out.println("Error al exportar historial: " + excepcion.getMessage());
            return null;
        }
        return nombreArchivo;
    }
}
