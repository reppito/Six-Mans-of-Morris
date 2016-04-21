package Vista;

/**
 * Created by katte on 21/04/2016.
 */

import javax.swing.ImageIcon;

public class FichaRoja extends Ficha {
    public FichaRoja() {
        this.tipo = 1;
        this.color_ficha = new ImageIcon(this.ruta.getAbsolutePath() + "\\fichaRoja.jpg");
        this.setIcon(this.color_ficha);
    }
}
