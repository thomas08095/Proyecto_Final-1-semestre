package co.edu.unbosque.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialPartida {

    private List<String> movimientos;
    private String dificultad;
    private int tamanoTablero;
    private LocalDateTime fechaInicio;

    public HistorialPartida(String dificultad, int tamanoTablero) {
        this.dificultad = dificultad;
        this.tamanoTablero = tamanoTablero;
        this.movimientos = new ArrayList<>();
        this.fechaInicio = LocalDateTime.now();
    }

    public void registrarMovimiento(int turno, int fila, int columna, String evento) {
        String linea = "Turno " + turno + ": Jugador se mueve a (" + fila + "," + columna + ")";
        if (evento != null && !evento.isEmpty()) {
            linea += " — " + evento;
        }
        movimientos.add(linea);
    }

    public String exportar(String resultado, int movimientosUsados, int nodosRecolectados) {
        DateTimeFormatter formatterArchivo = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        DateTimeFormatter formatterLegible = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
