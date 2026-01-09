package Objetos;

public class Circulo {

    private double radio;
    private final double PI = 3.14159265358979323846264338327950;

    
    public Circulo(double radio) {
        this.radio = radio;
    }

    public double perimetro(){

        double perimetro = 2 * PI * this.radio;
        
        return perimetro;
    }

    public double area(){

        double area = Math.pow(radio, 2) * PI;

        return area;
    }

    
}
