package co.edu.unbosque.view;

import co.edu.unbosque.model.Matriz;
import javax.swing.*;
import java.awt.*;

public class MatrizPanel extends JPanel {

    private CasillaPanel[][] casillaPanels;
    private Matriz matriz;

    public MatrizPanel(Matriz matriz) {
        this.matriz = matriz;
        this.casillaPanels = new CasillaPanel[matriz.getFilas()][matriz.getColumnas()];
        setLayout(new GridLayout(matriz.getFilas(), matriz.getColumnas()));
        for (int i = 0; i < matriz.getFilas(); i++) {
            for (int j = 0; j < matriz.getColumnas(); j++) {
                casillaPanels[i][j] = new CasillaPanel(matriz.getCasillas()[i][j]);
                add(casillaPanels[i][j]);
            }
        }
    }

    public CasillaPanel[][] getCasillaPanels() { return casillaPanels; }
    public void setCasillaPanels(CasillaPanel[][] casillaPanels) { this.casillaPanels = casillaPanels; }

    public Matriz getMatriz() { return matriz; }
    public void setMatriz(Matriz matriz) { this.matriz = matriz; }
}
