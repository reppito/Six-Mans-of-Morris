package Vista;
import javax.swing.*;
import java.awt.*;


import Modelo.ListaPosiciones;
import org.jpl7.Query;


/**
 * Created by katte on 17/04/2016.
 */
public class TheView extends JFrame {

    private static int WIDTH = 600;
    private static int HEIGHT = 600;
    private Dimension dimension = new Dimension(WIDTH, HEIGHT);

    private final GamePanel gamePanel =  new GamePanel();

    public TheView(){

        setSize(dimension);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, gamePanel);

    }




    public static void main(String[] args) {
        new TheView();




    }
}
