package DAO;

import java.util.ArrayList;
import java.util.List;

import Clases.SesionSala;

public class DAOsalas {
    private List<SesionSala> listaSalas;

    public DAOsalas() {
        this.listaSalas = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        listaSalas.add(new SesionSala(10, 10, 5.25, 1));
        listaSalas.add(new SesionSala(10, 10, 6.25, 2));
        listaSalas.add(new SesionSala(10, 10, 3.25, 3));
    }

    public List<SesionSala> getListaSalas() {
        return listaSalas;
    }

    public SesionSala buscarPorSesion(int numSesion) {
        for (SesionSala sala : listaSalas) {
            if (sala.getNumSesion() == numSesion) {
                return sala;
            }
        }
        return null;
    }

    public boolean liberarAsientoGlobal(int idReserva) {
        for (SesionSala sala : listaSalas) {
            if (sala.liberarAsiento(idReserva)) {
                return true;
            }
        }
        return false;
    }

}
