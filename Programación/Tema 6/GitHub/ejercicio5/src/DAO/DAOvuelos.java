package DAO;

import java.time.LocalDate;
import java.time.LocalTime;

import Clases.Vuelo;
import Enum.Estado;

public class DAOvuelos {
    public static Vuelo[] listaVuelos(){
        /* public Vuelo(String numeroVuelo, String origen, String destino, LocalDate fecha, LocalTime hora, Estado estado) */
        Vuelo[] vuelo = {
            new Vuelo("123VAS", "China", "Japón", LocalDate.now().plusDays(7), LocalTime.now().plusHours(2), Estado.PROGRAMADO),
            new Vuelo("1245GF", "España", "Rusia", LocalDate.now().plusMonths(2), LocalTime.now().plusMinutes(15), Estado.PROGRAMADO),
            new Vuelo("678ERT", "EEUU", "Francia", LocalDate.now().plusYears(1), LocalTime.of(14, 0, 0), Estado.PROGRAMADO)
        };

        return vuelo;
    }
}
