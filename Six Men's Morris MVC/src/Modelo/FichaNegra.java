package Modelo;

import java.awt.*;

/**
 * Created by Raul on 4/19/2016.
 */
public class FichaNegra extends Ficha {
    int jugar=2;


    public FichaNegra(){
        super();
    }
    public FichaNegra(Point p, int pos, Image img ){
        super(p,pos,img);
    }

    public int Jugar(){
        return jugar;
    }

}
