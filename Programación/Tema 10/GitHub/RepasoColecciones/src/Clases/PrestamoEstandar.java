package Clases;

import java.time.LocalDate;

public class PrestamoEstandar extends Prestamo {
    private String sala;

    public PrestamoEstandar(LocalDate fechaPrestamo, LocalDate fechaDevolucion, String idSocio, String sala) {
        super(fechaPrestamo, fechaDevolucion, idSocio);
        this.sala = sala;
    }

    @Override
    public String mostrarDetalles() {
        return "Préstamo Estandar, desde " + getFechaPrestamo() + " hasta " + getFechaDevolucion() + " socio: "
                + getIdSocio() + " Sala: " + this.sala;
    }

}
