package Clases;

import Interfaz.Evaluable;

public class PatinajeArtistico extends DeporteInvierno implements Evaluable {

    protected int dificultad;
    protected int ejecucion;

    public PatinajeArtistico(String nombre, int numParticipantes, int dificultad, int ejecucion) {
        super(nombre, numParticipantes);
        this.dificultad = dificultad;
        this.ejecucion = ejecucion;
    }

    @Override
    public double calcularPuntuacion() {
        double salida = (dificultad * 0.6) + (ejecucion * 0.4);
        return salida;
    }

    @Override
    public StringBuilder mostrarInfo() {

        StringBuilder salida = new StringBuilder();

        salida.append("Nombre: ").append(nombre).append("\n")
                .append("Número de participantes: ").append(numParticipantes).append("\n")
                .append("Dificultad: ").append(dificultad).append("\n")
                .append("Ejecución: ").append(ejecucion).append("\n")
                .append("\nPuntuación: ").append(calcularPuntuacion()).append("\n");
        return salida;
    }

}
