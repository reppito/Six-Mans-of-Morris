package Modelo;

import java.awt.*;

/**
 * Created by Raul on 4/19/2016.
 */
public class Jugador2 {
    private FichaBlanca Fichas[];

    public Jugador2(Image img)
    {
        for (int i=0; i< Fichas.length;i++)
            if(i==0)
                Fichas[i]= new FichaBlanca(new Point(50,50),99999,img);
            else
                Fichas[i]= new FichaBlanca(new Point(50,Fichas[i-1].getLugar().y+60),9999,img);
    }

    public FichaBlanca getFicha(int i)
    {
        return Fichas[i];

    }
    public  FichaBlanca [] getFichas()
    {
        return Fichas;
    }

}
