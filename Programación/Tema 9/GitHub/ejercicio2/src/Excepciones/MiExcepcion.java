package Excepciones;

import java.time.LocalTime;

public class MiExcepcion extends Exception {
    private String matricula;
    private LocalTime horaEntrada;

    public MiExcepcion(String matricula, LocalTime horaEntrada) {
        this.matricula = matricula;
        this.horaEntrada = horaEntrada;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

}
