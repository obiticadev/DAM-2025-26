import Objetos.Circulo;

public class App {
    public static void main(String[] args) throws Exception {
        Circulo[] forma = new Circulo[3];

        System.out.println("FIGURAS\n*******");
        for (int i = 0; i < forma.length; i++) {
            forma[i] = new Circulo(i + 2);
            System.out.println("El perímetro del círculo " + (i+1) + " es: " + forma[i].perimetro() + " unidades");
            System.out.println("El área del círculo " + (i+1) + " es: " + forma[i].area() + " unidades cuadradas");
            System.out.println();
        }
    }
}
