
package Vista;

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Ficha extends JLabel {


    protected final int NEGRA = 1;
    protected final int BLANCA = 2;
    protected ImageIcon color_ficha;
    protected ImageIcon ficha_seleccionada;

    protected boolean estaEliminada = false;
    protected boolean isSelected = false;

    protected int tipo;
    protected int posicion;

    File ruta = new File("src\\Imagenes");


    public Ficha() {
        this.setOpaque(true);
        posicion = -1;
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;

    }

    public int getTipo() {
        return this.tipo;

    }

    public boolean isSelected() {
        return this.isSelected;

    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}