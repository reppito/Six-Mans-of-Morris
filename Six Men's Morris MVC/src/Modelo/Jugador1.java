package Modelo;

import java.awt.*;

/**
 * Created by Raul on 4/19/2016.
 */
public class Jugador1 {
    private FichaNegra Fichas[];

    public Jugador1(Image img)
    {
        for (int i=0; i< Fichas.length;i++)
            if(i==0)
                Fichas[i]= new FichaNegra(new Point(50,50),99999,img);
            else
                Fichas[i]= new FichaNegra(new Point(50,Fichas[i-1].getLugar().y+60),9999,img);
    }
    public FichaNegra getFicha(int i)
    {
        return Fichas[i];

    }
    public  FichaNegra [] getFichas()
    {
        return Fichas;
    }
}
