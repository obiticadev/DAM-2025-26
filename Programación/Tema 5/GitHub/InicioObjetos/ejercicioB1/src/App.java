import Clases.Punto;

public class App {
    public static void main(String[] args) throws Exception {
        Punto punto1 = new Punto(5, 0);
        Punto punto2 = new Punto(10, 10);
        Punto punto3 = new Punto(-1, 7);

        System.out.println("Las coordenadas de los tres puntos son: ");
        System.out.println(punto1.getX() + ", " + punto1.getY());
        System.out.println(punto2.getX() + ", " + punto2.getY());
        System.out.println(punto3.getX() + ", " + punto3.getY());

        punto1.setX(punto1.getX()+3);
        punto2.setX(punto2.getX()+3);
        punto3.setX(punto3.getX()+3);


    }
}
