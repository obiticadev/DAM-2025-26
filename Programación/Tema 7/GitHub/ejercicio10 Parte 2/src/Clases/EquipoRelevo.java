package Clases;

import java.util.ArrayList;

import Interfaz.Evaluable;

public class EquipoRelevo implements Evaluable{
    private ArrayList<Jugador> jugador = new ArrayList<>();
    private String nombre;

    public EquipoRelevo(String nombre, ArrayList<Jugador> jugador) {
        this.jugador = jugador;
        this.nombre = nombre;
    }

    @Override
    public double calcularPuntuacion() {
        double puntuacion = 0;
        for (Jugador j : jugador) {
            puntuacion += j.calcularPuntuacion();
        }
        return puntuacion;
    }

    @Override
    public StringBuilder mostrarInfo() {
        StringBuilder salida = new StringBuilder();

        salida.append("Nombre de equipo: ").append(nombre).append("\n");

        for (Jugador j : jugador) {
            salida.append("Jugador: ").append(j.getNombre()).append("\n");
        }

        salida.append("Puntuación de equipo: ").append(calcularPuntuacion()).append("\n");

        return salida;
        
    }



    
}
