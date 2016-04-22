package Vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Juego extends JFrame  {
    public static int WIDTH = 600;
    public static int HEIGHT = 600;
    public Dimension dimension;
    private final Tablero tablero;

    public Juego() {
        super("Six Men\'s Morris");
        this.dimension = new Dimension(WIDTH, HEIGHT);
        this.tablero = new Tablero();
        this.setSize(this.dimension);
        this.setResizable(false);
        this.setLocationRelativeTo((Component)null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add("Center", this.tablero);
    }

    public static void main(String[] args) {
        new Juego();

    }
}
