import Clases.Articulo;

public class App {
    public static void main(String[] args) throws Exception {
        Articulo articulo1 = new Articulo("Pijama", 10, 5, 2);

        System.out.println(articulo1.resumenDeEstado());
    }
}
