import java.time.LocalDate;

import Clases.Agenda;
import Clases.Cita;
import Clases.CitaInvalidaException;

public class Main {

    public static void main(String[] args) {

        Agenda agenda = new Agenda();

        try {
            Cita c1 = new Cita(LocalDate.now().plusDays(1), "Dentista");
            agenda.añadirCita(c1);
            Cita c2 = new Cita(LocalDate.now().minusDays(2), "Reunión");
            agenda.añadirCita(c2);
        } catch (CitaInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        agenda.mostrarCitas();
    }
}