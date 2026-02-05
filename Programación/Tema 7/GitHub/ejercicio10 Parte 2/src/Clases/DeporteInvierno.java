package Clases;

import Interfaz.Evaluable;

public abstract class DeporteInvierno implements Evaluable {
    protected String nombre;
    protected int numParticipantes;

    public DeporteInvierno(String nombre, int numParticipantes) {
        this.nombre = nombre;
        this.numParticipantes = numParticipantes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumParticipantes() {
        return numParticipantes;
    }

    
    
}
