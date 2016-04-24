package Vista;

import Modelo.Consultas;
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
import java.util.ArrayList;
import javax.swing.*;

public class Tablero extends JPanel {

    private final Color BACKGROUND_COLOUR;
    private final Color BORDER_COLOR;
    private Graphics2D g2d;
    private BufferedImage bufferedImage;

    private static final Point[] punto = new Point[16];

    private ArrayList<Ficha> fichas_negras;
    private ArrayList<Ficha> fichas_Rojas;

    private ListaPosiciones posiciones;
    private Consultas consultas;

    private int SFNegras;
    private int SFRojas;

    boolean turno = false;
    boolean segundoClick = false;

    int cantidad_movimientos = 0;


    public Tablero() {
        SFNegras = 0;
        SFRojas = 0;

        fichas_negras= new ArrayList<Ficha>();
        fichas_Rojas= new ArrayList<Ficha>();

        consultas = new Consultas();

        this.BACKGROUND_COLOUR = Color.WHITE;
        this.BORDER_COLOR = Color.lightGray;

        this.setLayout((LayoutManager) null);
        this.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, this.BORDER_COLOR));

        posiciones = new ListaPosiciones();



        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posicion = atraparPunto(e);


                if(segundoClick == false)
                    //Posicionando fichas
                    earlyGame(posicion);
                    //Movimiento de fichas

                else {
                    eliminarFicha(posicion);
                }




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


        for(int i = 0;i < 6; i++)
        {
            fichas_negras.add(new FichaNegra());
            fichas_Rojas.add(new FichaRoja());
            

            this.add(fichas_Rojas.get(i));
            this.add(fichas_negras.get(i));
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

    private int atraparPunto(MouseEvent e){

        int posClickX = e.getX();
        int posClickY = e.getY();

        for (int i = 0; i < punto.length; i++)
            if (Math.abs(punto[i].getX() - posClickX) < 40 && Math.abs(punto[i].getY() - posClickY) < 40)
                return i;

        return -1;
    }

    private void earlyGame(int posicion){

        if(posiciones.getElementInt(posicion) == 0 && cantidad_movimientos < 12){
            if(!turno){
                fichas_Rojas.get(SFRojas).setPosicion(posicion);
                fichas_Rojas.get(SFRojas).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                posiciones.setInt(posicion, fichas_Rojas.get(SFRojas).getTipo());

                SFRojas++;

                if(verificarTresEnLinea(posicion)){
                    segundoClick = true;
                }
                else
                    this.turno = true;

            }
            else{
                fichas_negras.get(SFNegras).setPosicion(posicion);
                fichas_negras.get(SFNegras).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                posiciones.setInt(posicion, fichas_negras.get(SFNegras).getTipo());
                SFNegras++;

                if(verificarTresEnLinea(posicion)){
                    segundoClick = true;
                }
                else
                    this.turno = false;
            }
            System.out.println(posiciones.toStringInt());
            cantidad_movimientos++;
        }
    }

    private boolean verificarTresEnLinea(int posicion){
        return consultas.tresEnLinea(posicion);
    }

    private void eliminarFicha(int posicion){
        if(turno){
            for (int i = 0; i < fichas_Rojas.size(); i++)
                //
                if(fichas_Rojas.get(i).posicion == posicion) {

                    System.out.println(posicion);
                    System.out.println(i);
                    this.remove(fichas_Rojas.get(i));
                    fichas_Rojas.remove(i);
                    posiciones.setInt(posicion, 0);

                    turno = false;
                    segundoClick = false;

                    SFRojas--;



                }

        }
        else{
            for (int i = 0; i < fichas_negras.size(); i++){
                if(fichas_negras.get(i).posicion == posicion) {
                    System.out.println(posicion);
                    System.out.println(i);
                    this.remove(fichas_negras.get(i));
                    fichas_negras.remove(i);
                    posiciones.setInt(posicion, 0);
                    turno= true;
                    segundoClick = false;
                    SFNegras--;

                }
            }
        }
        System.out.println(posiciones.toStringInt());
        repaint();
    }

}


