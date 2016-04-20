
package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author katte
 */
public class GamePanel extends JPanel {
    private final Color BACKGROUND_COLOUR = Color.WHITE;
    private final Color BORDER_COLOR = Color.lightGray;
    private final int TOP_WIDTH = 4;
    private final int LEFT_WIDTH = 4;
    private final int RIGHT_WIDTH = 4;
    private final int BOTTOM_WIDTH = 4;
    private final Point punto[] = new Point[16];


    private Graphics2D g2d;
    private BufferedImage bufferedImage;

    public GamePanel(){
        setBorder(BorderFactory.createMatteBorder(TOP_WIDTH,LEFT_WIDTH,BOTTOM_WIDTH,RIGHT_WIDTH, BORDER_COLOR));
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(bufferedImage == null){
            createBufferedImage();
            drawGame();
        }
        g.drawImage(bufferedImage, 0, 0, null);
    }

    private void createBufferedImage(){
        bufferedImage = (BufferedImage)createImage(getWidth(), getHeight());
        g2d = (Graphics2D) bufferedImage.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(BACKGROUND_COLOUR);
        g2d.clearRect(0, 0, getWidth(), getHeight());
    }


    private void drawGame(){
        if(g2d != null){

            punto[0] = new Point(50,50);
            punto[1] = new Point(getWidth()/2, 50);
            punto[2] = new Point(getWidth() - 50, 50);
            punto[3] = new Point(getWidth()/2 - getWidth() / 4, getHeight()/2 - getHeight()/4);
            punto[4] = new Point(getWidth()/2 , punto[3].y);
            punto[5] = new Point(getWidth()/2 + getWidth() / 4, punto[3].y);
            punto[6] = new Point(punto[0].x, getHeight()/2);
            punto[7] = new Point(getWidth()/2 - getWidth()/4, getHeight()/2);
            punto[8] = new Point(getWidth()/2 + getWidth() / 4, getHeight()/2);
            punto[9]= new Point(punto[2].x, getHeight() / 2);
            punto[10] = new Point(punto[3].x, getHeight()/2 + getHeight()/ 4);
            punto[11] = new Point(punto[4].x ,punto[10].y);
            punto[12] = new Point(punto[5].x, punto[10].y);
            punto[13] = new Point(punto[6].x, getHeight() - 50);
            punto[14] = new Point(punto[11].x, punto[13].y);
            punto[15] = new Point(punto[2].x, punto[13].y);

            //Conectando los puntos
            //Punto 1 - 3
            g2d.drawLine(punto[0].x, punto[0].y, punto[2].x, punto[2].y);
            //Punto 1 - 14
            g2d.drawLine(punto[0].x, punto[0].y, punto[13].x, punto[13].y);
            //Punto 2 - 5
            g2d.drawLine(punto[1].x, punto[1].y, punto[4].x, punto[4].y);
            //Punto 3 - 16
            g2d.drawLine(punto[2].x, punto[2].y, punto[15].x, punto[15].y);
            //Punto 4 - 6
            g2d.drawLine(punto[3].x, punto[3].y, punto[5].x, punto[5].y);
            //Punto 4 - 11
            g2d.drawLine(punto[3].x, punto[3].y, punto[10].x, punto[10].y);
            //Punto 6 - 13
            g2d.drawLine(punto[5].x, punto[5].y, punto[12].x, punto[12].y);
            //Punto 7 - 8
            g2d.drawLine(punto[6].x, punto[6].y, punto[7].x, punto[7].y);
            //Punto 9 - 10
            g2d.drawLine(punto[8].x, punto[8].y, punto[9].x, punto[9].y);
            //Punto 11 - 13
            g2d.drawLine(punto[10].x, punto[10].y, punto[12].x, punto[12].y);
            //Punto 12 - 15
            g2d.drawLine(punto[11].x, punto[11].y, punto[14].x, punto[14].y);
            //Punto 14 - 16
            g2d.drawLine(punto[13].x, punto[13].y, punto[15].x, punto[15].y);


        }
    }


    public void clearPanel(){
        if(g2d != null){
            g2d.clearRect(0, 0, getWidth(), getHeight());
            repaint();
        }
    }




}

