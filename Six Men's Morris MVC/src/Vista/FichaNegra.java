package Vista;

/**
 * Created by katte on 21/04/2016.
 */
import javax.swing.ImageIcon;

public class FichaNegra extends Ficha {

    public FichaNegra() {
        this.tipo = 1;
        this.color_ficha = new ImageIcon(this.ruta.getAbsolutePath()+"\\fichaNegra.jpg");
        this.setIcon(this.color_ficha);
    }
}
