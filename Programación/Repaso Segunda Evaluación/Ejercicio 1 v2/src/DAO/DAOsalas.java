package DAO;

import java.util.ArrayList;
import java.util.List;

import Clases.SesionSala;

public class DAOsalas {
    List<SesionSala> listaSalas;

    public DAOsalas() {
        listaSalas = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() {
        this.listaSalas.add(new SesionSala(1, 15, 7.50, 1));
        this.listaSalas.add(new SesionSala(12, 18, 8.50, 2));
        this.listaSalas.add(new SesionSala(8, 12, 5.00, 3));
        this.listaSalas.add(new SesionSala(15, 20, 12.00, 4));
        this.listaSalas.add(new SesionSala(10, 10, 6.50, 5));
    }

    public List<SesionSala> getListaSalas() {
        return listaSalas;
    }

    public SesionSala buscarPorSesion(int numSesion) {
        for (SesionSala sesionSala : listaSalas) {
            if (sesionSala.getNumSesion() == numSesion) {
                return sesionSala;
            }
        }
        return null;
    }

    public boolean liberarAsientoGlobal(int numSesion) {
        for (SesionSala sesionSala : listaSalas) {
            if (sesionSala.liberarAsiento(numSesion)) {
                return true;
            }
        }
        return false;
    }

}
