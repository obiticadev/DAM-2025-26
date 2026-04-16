package Clases;

public abstract class DeporteInvierno {
    protected String nombre;
    protected int numParticipantes;

    public DeporteInvierno(String nombre, int numParticipantes) {
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
    }

    public abstract double calcularPuntuacion();

    public abstract StringBuilder mostrarInfo();
    
}
