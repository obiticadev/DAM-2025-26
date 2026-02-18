package Clases;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Eclipse extends EventoAstronomico {
    private TipoEclipse tipo;
    private List<String> zonasVisibilidad; // Cambiado a List para compatibilidad

    public Eclipse(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, String descripcion,
            TipoEclipse tipo, List<String> zonasVisibilidad) {
        super(fecha, horaInicio, horaFin, descripcion);
        this.tipo = tipo;
        this.zonasVisibilidad = zonasVisibilidad;
    }

    @Override
    public String toJson() {
        return String.format(
                "{\n  \"fecha\": \"%s\",\n  \"horaInicio\": \"%s\",\n  \"horaFin\": \"%s\",\n  \"descripcion\": \"%s\",\n  \"tipo\": \"%s\",\n  \"zonasVisibilidad\": %s\n}",
                getFecha(), getHoraInicio(), getHoraFin(), getDescripcion(), tipo, zonasVisibilidad.toString());
    }

    public boolean esVisibleEn(String zona) {
        return zonasVisibilidad.contains(zona);
    }

    public boolean esSolar() {
        return this.tipo.name().startsWith("SOLAR");
    }
}