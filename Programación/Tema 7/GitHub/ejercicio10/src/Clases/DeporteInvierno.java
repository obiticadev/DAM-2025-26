package Clases;

public abstract class DeporteInvierno {
    protected String nombre;
    protected int numParticipantes;

    public DeporteInvierno(String nombre, int numParticipantes) {
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
    }

    protected abstract double calcularPuntuacion();

    public StringBuilder mostrarInfo(){
        StringBuilder salida = new StringBuilder();
        
        return salida;
    }
    
}
