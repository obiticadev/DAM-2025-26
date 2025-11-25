import Clases.Rectangulo;

public class App {
    public static void main(String[] args) throws Exception {
        Rectangulo plano1 = new Rectangulo(0, 0, 5, 5);
        Rectangulo plano2 = new Rectangulo(7, 9, 2, 3);

        System.out.println("Área del del rectángulo 1: " + plano1.area());
        System.out.println("Área del del rectángulo 2: " + plano2.area());

        System.out.println("Perímetro del rectángulo 1: " + plano1.perimetro());
        System.out.println("Perímetro del rectángulo 2: " + plano2.perimetro());

        System.out.println(plano1.devolverCuadradoPintado());
        System.out.println(plano2.devolverCuadradoPintado());
    }
}
