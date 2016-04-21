package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Tablero extends JPanel {
    private final Color BACKGROUND_COLOUR;
    private final Color BORDER_COLOR;
    private final int TOP_WIDTH;
    private final int LEFT_WIDTH;
    private final int RIGHT_WIDTH;
    private final int BOTTOM_WIDTH;
    private static final Point[] punto = new Point[16];
    private Ficha[] fichas_negras;
    private Ficha[] fichas_blancas;
    private Graphics2D g2d;
    private BufferedImage bufferedImage;

    public Tablero() {
        this.BACKGROUND_COLOUR = Color.WHITE;
        this.BORDER_COLOR = Color.lightGray;
        this.TOP_WIDTH = 4;
        this.LEFT_WIDTH = 4;
        this.RIGHT_WIDTH = 4;
        this.BOTTOM_WIDTH = 4;
        this.setLayout((LayoutManager)null);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, this.BORDER_COLOR));
        this.fichas_negras = new FichaNegra[6];
        this.fichas_blancas = new FichaRoja[6];
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.bufferedImage == null) {
            this.createBufferedImage();
            this.inicializarPosiciones();
            this.posicionaFichas();
            this.drawGame();
        }

        g.drawImage(this.bufferedImage, 0, 0, (ImageObserver)null);
    }

    private void createBufferedImage() {
        this.bufferedImage = (BufferedImage)this.createImage(Juego.WIDTH, Juego.HEIGHT);
        this.g2d = (Graphics2D)this.bufferedImage.getGraphics();
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2d.setBackground(this.BACKGROUND_COLOUR);
        this.g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void inicializarPosiciones() {
        punto[0] = new Point(50, 50);
        punto[1] = new Point(this.getWidth() / 2, 50);
        punto[2] = new Point(this.getWidth() - 50, 50);
        punto[3] = new Point(this.getWidth() / 2 - this.getWidth() / 4, this.getHeight() / 2 - this.getHeight() / 4);
        punto[4] = new Point(this.getWidth() / 2, punto[3].y);
        punto[5] = new Point(this.getWidth() / 2 + this.getWidth() / 4, punto[3].y);
        punto[6] = new Point(punto[0].x, this.getHeight() / 2);
        punto[7] = new Point(this.getWidth() / 2 - this.getWidth() / 4, this.getHeight() / 2);
        punto[8] = new Point(this.getWidth() / 2 + this.getWidth() / 4, this.getHeight() / 2);
        punto[9] = new Point(punto[2].x, this.getHeight() / 2);
        punto[10] = new Point(punto[3].x, this.getHeight() / 2 + this.getHeight() / 4);
        punto[11] = new Point(punto[4].x, punto[10].y);
        punto[12] = new Point(punto[5].x, punto[10].y);
        punto[13] = new Point(punto[6].x, this.getHeight() - 50);
        punto[14] = new Point(punto[11].x, punto[13].y);
        punto[15] = new Point(punto[2].x, punto[13].y);
    }

    private void posicionaFichas() {
        boolean contador_posiciones = false;

        int pos;
        for(pos = 0; pos < this.fichas_negras.length; ++pos) {
            this.fichas_negras[pos] = new FichaNegra();
            this.fichas_blancas[pos] = new FichaRoja();
        }

        int var3;
        for(var3 = 0; var3 < this.fichas_negras.length; ++var3) {
            this.fichas_negras[var3].setBounds(punto[var3].x - 20, punto[var3].y - 20, 40, 40);
            this.add(this.fichas_negras[var3]);
        }

        for(pos = 0; pos < this.fichas_blancas.length; ++pos) {
            this.fichas_blancas[pos].setBounds(punto[var3].x - 20, punto[var3].y - 20, 40, 40);
            this.add(this.fichas_blancas[pos]);
            ++var3;
        }

    }

    private void drawGame() {
        if(this.g2d != null) {
            this.g2d.drawLine(punto[0].x, punto[0].y, punto[2].x, punto[2].y);
            this.g2d.drawLine(punto[0].x, punto[0].y, punto[13].x, punto[13].y);
            this.g2d.drawLine(punto[1].x, punto[1].y, punto[4].x, punto[4].y);
            this.g2d.drawLine(punto[2].x, punto[2].y, punto[15].x, punto[15].y);
            this.g2d.drawLine(punto[3].x, punto[3].y, punto[5].x, punto[5].y);
            this.g2d.drawLine(punto[3].x, punto[3].y, punto[10].x, punto[10].y);
            this.g2d.drawLine(punto[5].x, punto[5].y, punto[12].x, punto[12].y);
            this.g2d.drawLine(punto[6].x, punto[6].y, punto[7].x, punto[7].y);
            this.g2d.drawLine(punto[8].x, punto[8].y, punto[9].x, punto[9].y);
            this.g2d.drawLine(punto[10].x, punto[10].y, punto[12].x, punto[12].y);
            this.g2d.drawLine(punto[11].x, punto[11].y, punto[14].x, punto[14].y);
            this.g2d.drawLine(punto[13].x, punto[13].y, punto[15].x, punto[15].y);
        }

    }

    public void clearPanel() {
        if(this.g2d != null) {
            this.g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
            this.repaint();
        }

    }
}