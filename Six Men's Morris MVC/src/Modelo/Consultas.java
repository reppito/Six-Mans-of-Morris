package Modelo;

import org.jpl7.Query;
import org.jpl7.Term;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Raul on 4/23/2016.
 */
public class Consultas {
    ListaPosiciones lista;
    Query q1;

    public Consultas(){
        lista= new ListaPosiciones();
        q1= new Query("consult('SMH.pl')");
        q1.oneSolution();
    }

    public boolean tresEnLinea(int i)
    {


        if((i==0||i==1||i==2)&&(lista.getElementInt(0)==lista.getElementInt(1)&&lista.getElementInt(2)==lista.getElementInt(0)) ) {
            System.out.print(1+"");
            return true;

        }
        else if((i==0||i==6||i==13)&&(lista.getElementInt(0)==lista.getElementInt(6)&&lista.getElementInt(13)==lista.getElementInt(0))) {
            System.out.print(2+"");
            return true;

        }
        else if((i==13||i==14||i==15)&&(lista.getElementInt(13)==lista.getElementInt(14)&&lista.getElementInt(14)==lista.getElementInt(15))){
            System.out.print(3+"");
            return true;

        }
        else if((i==2||i==9||i==15)&&(lista.getElementInt(2)==lista.getElementInt(9)&&lista.getElementInt(9)==lista.getElementInt(15))){
            System.out.print(4+"");
            return true;

        }
        else if((i==3||i==4||i==5)&&(lista.getElementInt(3)==lista.getElementInt(4)&&lista.getElementInt(4)==lista.getElementInt(5))){
            System.out.print(5+"");
            return true;

        }
        else if((i==3||i==7||i==10)&&(lista.getElementInt(3)==lista.getElementInt(7)&&lista.getElementInt(7)==lista.getElementInt(10))) {
            System.out.print(6+"");
            return true;

        }
        else if((i==10||i==11||i==12)&&(lista.getElementInt(10)==lista.getElementInt(11)&&lista.getElementInt(11)==lista.getElementInt(12))){
            System.out.print(7+"");
            return true;

        }
        else if((i==5||i==8||i==12)&&(lista.getElementInt(5)==lista.getElementInt(8)&&lista.getElementInt(8)==lista.getElementInt(12))){
            System.out.print(8+"");
            return true;

        }
        else return false;

    }
    public ArrayList<Integer> movimientos(int i)
    {  System.out.println(lista.getElementString(i));
        ArrayList<Integer> pos= new ArrayList<Integer>();
        Query movimientos= new Query("verificarMovimientos("+lista.toStringInt()+","+lista.getElementString(i)+",X)");
        Map<String, Term>[] soluciones= movimientos.allSolutions();
        for(int j=0;j<soluciones.length;j++)
            pos.add(Integer.parseInt(soluciones[j].get("X").toString()));
        return pos;
    }
}
