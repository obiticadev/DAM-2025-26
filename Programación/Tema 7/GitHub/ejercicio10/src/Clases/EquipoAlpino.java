package Clases;

public class EquipoAlpino extends DeporteInvierno {

    protected double tiempoSegundos;
    protected int penalizaciones;


    public EquipoAlpino(String nombre, int numParticipantes, double tiempoSegundos, int penalizaciones) {
        super(nombre, numParticipantes);
        this.tiempoSegundos = tiempoSegundos;
        this.penalizaciones = penalizaciones;
    }


    @Override
    protected double calcularPuntuacion() {
        double salida = (100 - tiempoSegundos) - (penalizaciones * 5);
        return salida;
    }

    

    
}
