package Clases;

import java.util.ArrayList;

public class GestionReservas {
    private ArrayList<Reservas> listaReservas;

    public GestionReservas() {
        listaReservas = new ArrayList<>();
    }

    public boolean registrarReserva(Reservas r) {
        if (!listaReservas.contains(r)) {
            return listaReservas.add(r);
        }
        return false;
    }

    public Reservas buscarReserva(int numeroReserva) {
        for (Reservas r : listaReservas) {
            if (r.getNumReserva() == numeroReserva) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LISTA DE RESERVAS\n");
        for (Reservas r : listaReservas) {
            sb.append(r.toString()).append("\n");
            sb.append(r.getCodigoLibro().toString()).append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Reservas> getListaReservas() {
        return listaReservas;
    }

}
