import java.util.Random;

import Clases.Game;
import Utilidades.Funciones;

public class App {
    public static void main(String[] args) throws Exception {
        Random rd = new Random();
        Game gameplay = new Game(10, 10);

        gameplay.resetearMatriz();
        gameplay.dibujarMatriz();

    }

}
