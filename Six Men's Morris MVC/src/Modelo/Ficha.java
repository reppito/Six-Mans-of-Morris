package Modelo;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * Created by Raul on 4/19/2016.
 */
abstract class Ficha {
    Point lugar;
    int posicion;
    Image imagenFicha;
    boolean disponible=true;

    public Ficha(){
        lugar= new Point(0,0);
        posicion=0;
        imagenFicha= new Image() {
            @Override
            public int getWidth(ImageObserver observer) {
                return 0;
            }

            @Override
            public int getHeight(ImageObserver observer) {
                return 0;
            }

            @Override
            public ImageProducer getSource() {
                return null;
            }

            @Override
            public Graphics getGraphics() {
                return null;
            }

            @Override
            public Object getProperty(String name, ImageObserver observer) {
                return null;
            }
        };

    }

    public  Ficha(Point p, int pos, Image img ){
        lugar=p;
        posicion=pos;
        imagenFicha=img;
    }
    public void setLugar(Point p)
    {
        lugar=p;
    }
    public  void setPos(int i)
    {
        posicion=i;
    }
    public void setImg(Image img)
    {
        imagenFicha= img;
    }

    public Point getLugar()
    {
        return lugar;
    }
    public  int getPos()
    {
        return posicion;
    }
    public Image setImg()
    {
        return imagenFicha;
    }

    public void eliminada(){
        disponible=false;
    }
    public boolean disponible(){
        return disponible;
    }

}
