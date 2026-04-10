package Clases;

import Enum.Evento;
import Excepciones.DiaRepetidoOInferior;
import Excepciones.DistanciaNegativaException;

public class DiaMision {
    private static int ultimoDiaRegistrado = 0;

    private int numeroDia;
    private double distanciaRecorrida;
    private Evento evento;

    public DiaMision(int numeroDia, double distanciaRecorrida, Evento evento)
            throws DistanciaNegativaException, DiaRepetidoOInferior {

        if (distanciaRecorrida < 0) {
            throw new DistanciaNegativaException("ERROR: Distancia negativa: ", distanciaRecorrida);
        }

        if (numeroDia <= ultimoDiaRegistrado) {
            throw new DiaRepetidoOInferior("ERROR: Día repetido o anterior", numeroDia);
        }

        this.distanciaRecorrida = distanciaRecorrida;
        this.evento = evento;

        this.numeroDia = numeroDia;
        ultimoDiaRegistrado = numeroDia;
    }

    public static int getUltimoDiaRegistrado() {
        return ultimoDiaRegistrado;
    }

    public int getNumeroDia() {
        return numeroDia;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public Evento getEvento() {
        return evento;
    }

}
