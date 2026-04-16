package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Excepciones.IdRepetido;

public class Mision {
    private static int posicion = 0;
    private double distanciaRecorrida;
    private String nombre;
    private double distanciaTotal;
    private List<DiaMision> coleccionDias;
    private Map<Integer, Astronauta> coleccionAstronauta;
    private boolean misionFinalizada;

    public Mision(String nombre, double distanciaTotal) {
        this.nombre = nombre;
        this.distanciaTotal = distanciaTotal;
        this.distanciaRecorrida = 0;
        this.coleccionDias = new ArrayList<>();
        this.coleccionAstronauta = new HashMap<>();
        this.misionFinalizada = false;
    }

    public void agregarAstronauta(Astronauta astronauta) throws IdRepetido {
        for (Map.Entry<Integer, Astronauta> tuplaEntry : coleccionAstronauta.entrySet()) {
            if (tuplaEntry.getValue().equals(astronauta)) {
                throw new IdRepetido("ERROR: Id repetido: ", astronauta.getId());
            }
        }
        coleccionAstronauta.put(++posicion, astronauta);
    }

    public void agregarDiaMision(DiaMision diaMision) {
        coleccionDias.add(diaMision);
        if ((distanciaTotal - distanciaRecorrida - diaMision.getDistanciaRecorrida()) < 0) {
            distanciaRecorrida = distanciaTotal;
            misionFinalizada = true;

        } else {
            distanciaRecorrida += diaMision.getDistanciaRecorrida();
        }
    }

    public boolean isMisionFinalizada() {
        return misionFinalizada;
    }

}
