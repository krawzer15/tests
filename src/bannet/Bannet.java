package bannet;

import Model.Usuario;
import View.vLogin;

/**
 *
 * @author krawz
 */
public class Bannet {
 public static Usuario usuarioActivo = new Usuario();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            vLogin.main(args);
    }

}
