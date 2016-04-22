package Vista;

import Modelo.ListaPosiciones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.*;

public class Tablero extends JPanel {
    private final Color BACKGROUND_COLOUR;
    private final Color BORDER_COLOR;
    private final int TOP_WIDTH;
    private final int LEFT_WIDTH;
    private final int RIGHT_WIDTH;
    private final int BOTTOM_WIDTH;
    private static final Point[] punto = new Point[16];
    private Ficha[] fichas_negras;
    private Ficha[] fichas_Rojas;
    private Graphics2D g2d;
    private BufferedImage bufferedImage;
    private JButton JBprobar;
    private ListaPosiciones posiciones;

    public Tablero() {
        this.BACKGROUND_COLOUR = Color.WHITE;
        this.BORDER_COLOR = Color.lightGray;
        this.TOP_WIDTH = 4;
        this.LEFT_WIDTH = 4;
        this.RIGHT_WIDTH = 4;
        this.BOTTOM_WIDTH = 4;
        this.setLayout((LayoutManager) null);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, this.BORDER_COLOR));
        this.fichas_negras = new FichaNegra[6];
        this.fichas_Rojas = new FichaRoja[6];
        JBprobar = new JButton("probar");
        posiciones = new ListaPosiciones();
        JBprobar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //esto es solo para la prueba este metodo se llamara aparte
                PosicionarSegunTablero();
            }
        });
        JBprobar.setBounds(500, 500, 80, 50);
        this.add(JBprobar);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posClickX = e.getX();
                int posClickY = e.getY();

                for (int i = 0; i < punto.length; i++)
                    if (Math.abs(punto[i].getX() - posClickX) < 40 && Math.abs(punto[i].getY() - posClickY) < 40)
                        System.out.println("se presiono el punto " + punto[i].getX() + " " + punto[i].getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void PosicionarSegunTablero() {
        int negra = 0;
        int rojas = 0;
        for (int i = 0; i < posiciones.getSizeInt(); i++) {
            if (posiciones.getElementInt(i) == fichas_Rojas[rojas].getTipo())
                fichas_Rojas[rojas++].setBounds(punto[i].x - 20, punto[i].y - 20, 40, 40);
            else if (posiciones.getElementInt(i) == fichas_negras[negra].getTipo())
                fichas_negras[negra++].setBounds(punto[i].x - 20, punto[i].y - 20, 40, 40);

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.bufferedImage == null) {
            this.createBufferedImage();
            this.inicializarPosiciones();
            this.posicionaFichas();
            this.drawGame();
        }

        g.drawImage(this.bufferedImage, 0, 0, (ImageObserver) null);
    }

    private void createBufferedImage() {
        this.bufferedImage = (BufferedImage) this.createImage(Juego.WIDTH, Juego.HEIGHT);
        this.g2d = (Graphics2D) this.bufferedImage.getGraphics();
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


        for (int pos = 0; pos < this.fichas_negras.length; pos++) {
            this.fichas_negras[pos] = new FichaNegra();
            this.fichas_Rojas[pos] = new FichaRoja();
            this.fichas_negras[pos].setBounds(-20, 100 + (pos * 40), 40, 40);
            this.fichas_Rojas[pos].setBounds(this.getWidth() - 20, 100 + (pos * 40), 40, 40);
            this.add(this.fichas_negras[pos]);
            this.add(this.fichas_Rojas[pos]);

        }

    }

    private void drawGame() {
        if (this.g2d != null) {
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
        if (this.g2d != null) {
            this.g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
            this.repaint();
        }

    }

    //De aqui en adelante es el Juego

}


