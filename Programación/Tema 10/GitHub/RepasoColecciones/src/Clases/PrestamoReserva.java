package Clases;

import java.time.LocalDate;

public class PrestamoReserva extends Prestamo {
    private Libro libro;

    // DUDA ----------------
    public PrestamoReserva(LocalDate fechaPrestamo, LocalDate fechaDevolucion, String idSocio, Libro libro) {
        super(fechaPrestamo, fechaDevolucion, idSocio);
        this.libro = libro;
    }

    @Override
    public String mostrarDetalles() {
        return "Préstamo Reserva, desde " + getFechaPrestamo() + " hasta " + getFechaDevolucion() + " socio: "
                + getIdSocio() + " " + libro.toString();
    }

}
