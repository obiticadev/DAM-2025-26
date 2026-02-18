package Clases;

import java.time.LocalDate;
import java.time.LocalTime;

public class LluviaMeteoros extends EventoAstronomico {
    private String constelacionRadiante;
    private int meteorosPorHora, intensidadLimite;
    private boolean visibleSinLuna;

    public LluviaMeteoros(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String descripcion,
            String constelacionRadiante, int meteorosPorHora, boolean visibleSinLuna, int intensidadLimite) {
        super(fecha, horaInicio, horaFin, descripcion);
        this.constelacionRadiante = constelacionRadiante;
        this.meteorosPorHora = meteorosPorHora;
        this.visibleSinLuna = visibleSinLuna;
        this.intensidadLimite = intensidadLimite;
    }

    public boolean esIntensa() {
        return meteorosPorHora > intensidadLimite;
    }

    @Override
    public String toJson() {
        return String.format(
                "{\n  \"tipoEvento\": \"LLUVIA_METEOROS\",\n  \"fecha\": \"%s\",\n  \"horaInicio\": \"%s\",\n  \"horaFin\": \"%s\",\n  \"descripcion\": \"%s\",\n  \"constelacionRadiante\": \"%s\",\n  \"meteorosPorHora\": %d,\n  \"visibleSinLuna\": %b\n}",
                getFecha(), getHoraInicio(), getHoraFin(), getDescripcion(), constelacionRadiante, meteorosPorHora,
                visibleSinLuna);
    }
}