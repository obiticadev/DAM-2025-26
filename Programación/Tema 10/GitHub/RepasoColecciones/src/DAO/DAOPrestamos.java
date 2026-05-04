package DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Clases.Prestamo;

public class DAOPrestamos {

    private Set<Prestamo> prestamos;

    public DAOPrestamos() {
        prestamos = new HashSet<>();
    }

    public boolean agregarPrestamo(Prestamo prestamo) {
        return prestamos.add(prestamo);
    }

    public Prestamo buscarUltimoPrestamoPorSocio(String idSocio) {
        List<Prestamo> p = new ArrayList<>(prestamos);
        Collections.sort(p);
        for (Prestamo p1 : p) {
            if (p1.getIdSocio().equals(idSocio)) {
                return p1;
            }
        }
        return null;
    }
}