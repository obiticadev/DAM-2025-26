package Clases;

public class PatinajeArtistico extends DeporteInvierno {

    protected int dificultad;
    protected int ejecucion;
    
    
    public PatinajeArtistico(String nombre, int numParticipantes, int dificultad, int ejecucion) {
        super(nombre, numParticipantes);
        this.dificultad = dificultad;
        this.ejecucion = ejecucion;
    }


    @Override
    protected double calcularPuntuacion() {
        double salida = (dificultad * 0.6) + (ejecucion * 0.4);
        return salida;
    }


    

    
    
    

}
