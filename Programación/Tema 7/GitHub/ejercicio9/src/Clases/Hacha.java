package Clases;

public class Hacha extends Herramienta{

    protected float eficiencia;
    
    public Hacha(String nombre, int durabilidad, float eficiencia) {
        super(nombre, durabilidad);
        this.eficiencia = eficiencia;
    }
    
}
