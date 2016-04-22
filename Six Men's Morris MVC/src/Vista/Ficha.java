
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
    protected int tipo;
    File ruta = new File("src\\Imagenes");
    boolean isSelected = false;

    public Ficha() {
        this.setOpaque(true);
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
}