import Clases.Articulo;

public class App {
    public static void main(String[] args) throws Exception {
        Articulo articulo1 = new Articulo("1", "Pijama", 37, 5);

        System.out.println(articulo1.devolverDatos());
    }
}
