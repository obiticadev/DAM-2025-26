package Clases;

public abstract class Pico extends Herramienta {

    protected String lucidez;

    public Pico (String nombre, int durabilidad, String lucidez) {
        super(nombre, durabilidad);
        this.lucidez = lucidez;
    }

    public StringBuilder minar() {
        StringBuilder pico = new StringBuilder();
        pico.append("Soy un pico de ");
        return pico;
    }

}
