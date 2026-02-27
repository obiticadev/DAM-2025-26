package DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import Clases.GestionReservas;
import Clases.Libros;
import Clases.Reservas;

public class GesionReservasDAO {
    private GestionReservas dao;;

    public GesionReservasDAO() {
        this.dao = new GestionReservas();
        cargarDatos();
    }

    private void cargarDatos() {
        Reservas r1 = new Reservas("Oliver", LocalDate.of(2025, 12, 5), new Libros("12", "Harry Potter", "Stuart"));
        Reservas r2 = new Reservas("Ruben", LocalDate.of(2025, 5, 12), new Libros("23", "Luisito", "Marta"));
        Reservas r3 = new Reservas("Martín", LocalDate.of(2015, 11, 8), new Libros("56", "Merlín", "HBO"));

        dao.getListaReservas().add(r1);
        dao.getListaReservas().add(r2);
        dao.getListaReservas().add(r3);
    }

    public ArrayList<Reservas> devolverDatos() {
        return new ArrayList<>(dao.getListaReservas());
    }

}
