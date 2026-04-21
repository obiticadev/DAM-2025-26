package Clase;

public class Objeto {
    private int entero;
    private double decimal;
    private String palabra;

    public Objeto(int entero, double decimal, String palabra) {
        this.entero = entero;
        this.decimal = decimal;
        this.palabra = palabra;
    }

    public int getEntero() {
        return entero;
    }

    public double getDecimal() {
        return decimal;
    }

    public String getPalabra() {
        return palabra;
    }

    @Override
    public String toString() {
        return "Objeto [entero=" + entero + ", decimal=" + decimal + ", palabra=" + palabra + "]";
    }

}
