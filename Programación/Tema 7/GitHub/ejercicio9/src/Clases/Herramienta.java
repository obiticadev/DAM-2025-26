package Clases;

public abstract class Herramienta {
    protected String nombre;
    protected int durabilidad;
    protected static int contador = 0;


    public Herramienta(String nombre, int durabilidad) {
        this.nombre = nombre;
        this.durabilidad = durabilidad;
        Herramienta.contador ++;
    }

    public void modificarDurabilidad(int nuevaDurabilidad){
        this.durabilidad = nuevaDurabilidad;
    }

    public static int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    

}


