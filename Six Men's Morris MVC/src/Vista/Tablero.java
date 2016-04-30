package Vista;

import Modelo.Consultas;
import Modelo.ListaPosiciones;

import java.awt.*;
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

    private JLabel turno_ficha = new JLabel("Turno");
    private JTextField color_ficha = new JTextField(20);

    private JLabel label_negras = new JLabel("Fichas negras:");
    private JLabel label_rojas = new JLabel("Fichas rojas:");

    private JTextField contador_negras = new JTextField(5);
    private JTextField contador_rojas = new JTextField(5);

    private JButton reiniciar = new JButton("Reiniciar");




    private static final Point[] punto = new Point[16];

    private ArrayList<Ficha> fichas_negras;
    private ArrayList<Ficha> fichas_Rojas;

    private ListaPosiciones posiciones;
    private Consultas consultas;

    private int SFNegras;
    private int SFRojas;

    private  boolean ganador=false;

    boolean turno = false;
    boolean tresEnLinea = false;

    int cantidad_movimientos = 0;


    public Tablero() {
        SFNegras = 0;
        SFRojas = 0;

        color_ficha.setEditable(false);
        color_ficha.setBackground(Color.RED);

        contador_negras.setEditable(false);
        contador_rojas.setEditable(false);



        add(turno_ficha);
        add(color_ficha);
        add(label_negras);
        add(contador_negras);
        add(label_rojas);
        add(contador_rojas);
        add(reiniciar);
        turno_ficha.setBounds(20,10,40,20);
        color_ficha.setBounds(60,10, 20, 20);

        label_negras.setBounds(200, 10, 100, 20);
        contador_negras.setBounds(290, 10, 20, 20);

        label_rojas.setBounds(320, 10, 100, 20);
        contador_rojas.setBounds(400,10, 20, 20);





        contador_negras.setText(SFNegras + "");
        contador_rojas.setText(SFRojas + "");

        fichas_negras = new ArrayList<Ficha>();
        fichas_Rojas = new ArrayList<Ficha>();

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

               //FIRST PART
                if(tresEnLinea == false && cantidad_movimientos < 12&&!ganador)
                    //Posicionando fichas
                    earlyGame(posicion);



                //SECOND PART
                if(cantidad_movimientos >= 12 && !tresEnLinea &&!ganador) {
                   if(posiciones.getElementInt(posicion) != 0)
                        seleccionarFicha(posicion);
                    else if (posiciones.getElementInt(posicion) == 0){
                        moverFicha(posicion);
                   }

                }
                //Si se hace tresEnLinea se elimina
                if (tresEnLinea && !ganador ){
                    eliminarFicha(posicion);
                }
                if(cantidad_movimientos>=12&&Trancado())
                {
                    if(!turno)
                        mostrarGanador("Ganaron las negras");
                    else
                        mostrarGanador("Ganaron las rojas");

                    ganador = true;
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
        if (bufferedImage == null) {
            createBufferedImage();
            inicializarPosiciones();
            posicionaFichas();
            drawGame();
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

        reiniciar.setBounds(getWidth()/2 - 50, getHeight()/2 + 90, 100, 30);

        reiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPanel();
                posicionaFichas();
                drawGame();
            }
        });


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
        if (g2d != null) {
            g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

            for (int i = 0; i < fichas_negras.size(); i++){
                fichas_negras.get(i).setBounds(- 10, -10, 10,10);
            }

            for (int i =0 ; i < fichas_Rojas.size(); i++){
                fichas_Rojas.get(i).setBounds(- 10, -10, 10,10);
            }

            fichas_negras.removeAll(fichas_negras);
            fichas_Rojas.removeAll(fichas_Rojas);

            color_ficha.setBackground(Color.RED);

            contador_negras.setText("0");
            contador_rojas.setText("0");


            posiciones.resetPosiciones();

            SFNegras = 0;
            SFRojas = 0;

            ganador = false;

            turno = false;
            tresEnLinea = false;

            cantidad_movimientos = 0;



            repaint();
        }

    }


    //De aqui en adelante es el Juego

    private int atraparPunto(MouseEvent e){

        int posClickX = e.getX();
        int posClickY = e.getY();

        for (int i = 0; i < punto.length; i++)
            if (Math.abs(punto[i].getX() - posClickX) < 60 && Math.abs(punto[i].getY() - posClickY) < 60)
                return i;

        return -1;
    }

    private void earlyGame(int posicion){

        if(posiciones.getElementInt(posicion) == 0 ){
            if(!turno){
                fichas_Rojas.get(SFRojas).setPosicion(posicion);
                fichas_Rojas.get(SFRojas).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                posiciones.setInt(posicion, fichas_Rojas.get(SFRojas).getTipo());

                SFRojas++;
                contador_rojas.setText(SFRojas + "");


                if(verificarTresEnLinea(posicion)){
                    tresEnLinea = true;
                }
                else{
                    this.turno = true;
                    color_ficha.setBackground(Color.BLACK);
                }


            }
            else{
                fichas_negras.get(SFNegras).setPosicion(posicion);
                fichas_negras.get(SFNegras).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                posiciones.setInt(posicion, fichas_negras.get(SFNegras).getTipo());
                SFNegras++;
                contador_negras.setText(SFNegras + "");


                if(verificarTresEnLinea(posicion)){
                    tresEnLinea = true;
                }
                else{
                    this.turno = false;
                    color_ficha.setBackground(Color.RED);
                }

            }
            //System.out.println(posiciones.toStringInt());
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


                    this.remove(fichas_Rojas.get(i));
                    fichas_Rojas.remove(i);
                    posiciones.setInt(posicion, 0);

                    turno = false;
                    tresEnLinea = false;

                    SFRojas--;
                    if(MenorATres(SFRojas)&&cantidad_movimientos==12) {
                        mostrarGanador("Ganaron las negras");
                        ganador=true;
                    }
                    contador_rojas.setText(SFRojas + "");
                    color_ficha.setBackground(Color.RED);

                }

        }
        else{
            for (int i = 0; i < fichas_negras.size(); i++){
                if(fichas_negras.get(i).posicion == posicion) {
                    this.remove(fichas_negras.get(i));
                    fichas_negras.remove(i);
                    posiciones.setInt(posicion, 0);
                    turno = true;
                    tresEnLinea = false;
                    SFNegras--;
                    if(MenorATres(SFNegras)&&cantidad_movimientos==12){
                        mostrarGanador("Ganaron las rojas");
                        ganador=true;
                    }
                    contador_negras.setText(SFNegras + "");
                    color_ficha.setBackground(Color.BLACK);

                }
            }
        }
//        System.out.println(posiciones.toStringInt());
        repaint();
    }

    private void seleccionarFicha(int posicion){
        //si es el turno de las rojas
        if(!turno){
            for (int i = 0; i < fichas_Rojas.size(); i++) {
                //
                if (fichas_Rojas.get(i).isSelected()) {
                    fichas_Rojas.get(i).setSelected(false);
                }
                if (fichas_Rojas.get(i).posicion == posicion) {
                    fichas_Rojas.get(i).setSelected(true);
                }
            }
        }
        //turno negras
        else{
            for (int i = 0; i < fichas_negras.size(); i++){

                    if (fichas_negras.get(i).isSelected()) {
                        fichas_negras.get(i).setSelected(false);
                    }
                    if (fichas_negras.get(i).posicion == posicion) {
                        fichas_negras.get(i).setSelected(true);
                    }


            }
        }
        //System.out.println(posiciones.toStringInt());
        repaint();

    }
    private void moverFicha(int posicion)
    {  ArrayList<Integer> PosiblesMov;
        if(!turno){
            for (int i = 0; i < fichas_Rojas.size(); i++) {
                //
                if (fichas_Rojas.get(i).isSelected()) {
                     PosiblesMov = consultas.movimientos(fichas_Rojas.get(i).posicion);

                    for(Integer pos: PosiblesMov)
                    {
                        if(pos == posicion)
                        {
                            posiciones.setInt(fichas_Rojas.get(i).posicion, 0);
                            posiciones.setInt(posicion,fichas_Rojas.get(i).getTipo());
                            fichas_Rojas.get(i).setPosicion(posicion);
                            fichas_Rojas.get(i).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                            fichas_Rojas.get(i).setSelected(false);
                            if(verificarTresEnLinea(posicion)){
                                tresEnLinea = true;
                            }

                            else{
                                turno = true;
                                color_ficha.setBackground(Color.BLACK);
                            }

                        }
                    }
                }

            }
        }
        //turno negras
        else{
            for (int i = 0; i < fichas_negras.size(); i++){

                if (fichas_negras.get(i).isSelected()) {
                    PosiblesMov = consultas.movimientos(fichas_negras.get(i).posicion);

                    for(Integer pos: PosiblesMov)
                    {
                        if(pos == posicion)
                        {
                            posiciones.setInt(fichas_negras.get(i).posicion, 0);
                            posiciones.setInt(posicion,fichas_negras.get(i).getTipo());
                            fichas_negras.get(i).setPosicion(posicion);

                            fichas_negras.get(i).setBounds(punto[posicion].x - 20, punto[posicion].y - 20, 40, 40);
                            fichas_negras.get(i).setSelected(false);
                            if(verificarTresEnLinea(posicion)){
                                tresEnLinea = true;
                            }

                            else{
                                turno = false;
                                color_ficha.setBackground(Color.RED);
                            }

                        }
                    }
                }



            }
        }
        repaint();
//        System.out.println(posiciones.toStringInt());
    }

    private boolean MenorATres(int cantidad){
        if(cantidad < 3)
            return true;
        return false;


    }
    private boolean Trancado()
    {
        //Si es el turno de las rojas
        if(turno)
        {
            for (int i= 0; i < fichas_negras.size(); i++)
            {
                if(consultas.MovimientoDisponible(fichas_negras.get(i).posicion)) {
                    return false;
                }

            }
        }
        else{
            for (int i = 0; i < fichas_Rojas.size(); i++)
            {
                if(consultas.MovimientoDisponible(fichas_Rojas.get(i).posicion)){
                    return false;
                }
            }
        }
        return true;
    }

    private void mostrarGanador(String ganador){
        g2d.setFont(new Font("Verdana", Font.BOLD, 16));
        g2d.drawString(ganador, getWidth()/2 - 70, getHeight()/2);
    }
}


