package Modelo;

import java.awt.*;

/**
 * Created by Raul on 4/19/2016.
 */
public class FichaBlanca extends Ficha {

        int jugar=2;


        public FichaBlanca(){
            super();
        }
        public FichaBlanca(Point p, int pos, Image img ){
            super(p,pos,img);
        }

        public int Jugar(){
            return jugar;
        }


}
