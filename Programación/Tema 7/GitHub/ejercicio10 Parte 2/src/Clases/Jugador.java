package Clases;

import java.util.ArrayList;

import Interfaz.Evaluable;

public class Jugador implements Evaluable {
    private String nombre;
    private ArrayList<DeporteInvierno> deportes = new ArrayList<>();
    
    public Jugador(String nombre, ArrayList<DeporteInvierno> deportes) {
        this.nombre = nombre;
        this.deportes = deportes;
    }

    public StringBuilder mostrarInfo(){

        StringBuilder salida = new StringBuilder();
        salida.append("Nombre del jugador: ").append(nombre);
        for (DeporteInvierno d : deportes) {
            salida.append("Nombre del deporte: ").append(d.getNombre())
                .append("Número de participantes: ").append(d.getNumParticipantes());
            
        }
        salida.append("\nPuntuación total: ").append(calcularPuntuacion());
        return salida;
        
    }

    @Override
    public double calcularPuntuacion() {
        double puntuacion = 0;
        for (DeporteInvierno d : deportes) {
            puntuacion += d.calcularPuntuacion();
        }
        return puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<DeporteInvierno> getDeportes() {
        return deportes;
    }

    
    
}
