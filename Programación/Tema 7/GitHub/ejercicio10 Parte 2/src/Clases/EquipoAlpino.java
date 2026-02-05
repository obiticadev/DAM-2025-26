package Clases;

import Interfaz.Evaluable;

public class EquipoAlpino extends DeporteInvierno implements Evaluable{

    protected double tiempoSegundos;
    protected int penalizaciones;

    public EquipoAlpino(String nombre, int numParticipantes, double tiempoSegundos, int penalizaciones) {
        super(nombre, numParticipantes);
        this.tiempoSegundos = tiempoSegundos;
        this.penalizaciones = penalizaciones;
    }

    @Override
    public double calcularPuntuacion() {
        double salida = (100 - tiempoSegundos) - (penalizaciones * 5);
        return salida;
    }

    @Override
    public StringBuilder mostrarInfo() {

        StringBuilder salida = new StringBuilder();

        salida.append("Nombre: ").append(nombre).append("\n")
                .append("Número de participantes: ").append(numParticipantes).append("\n")
                .append("Tiempo en segundos: ").append(tiempoSegundos).append("s\n")
                .append("Penalizaciones: ").append(penalizaciones).append("\n")
                .append("\nPuntuación: ").append(calcularPuntuacion()).append("\n");
        return salida;

    }



}
