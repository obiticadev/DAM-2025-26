package com.masterclass.api.b01_java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Instant;
import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Ejercicio 020 · API java.time para APIs (fechas en JSON).
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.6).
 */
public final class Ej020DateTimeApi {

    private Ej020DateTimeApi() {
    }

    /**
     * Días entre dos fechas (puede ser negativo si fin &lt; inicio).
     *
     * @param inicio fecha inicial
     * @param fin    fecha final
     * @return número de días
     */
    public static long diasEntre(LocalDate inicio, LocalDate fin) {
        // TODO 1: usa ChronoUnit.DAYS.
        // TODO 2: aplica between(inicio, fin) (el orden importa: define el signo).
        // TODO 3: devuelve el resultado (negativo si fin < inicio).
        return Long.MIN_VALUE;
    }

    /**
     * Indica si un token emitido en {@code emitido} ya caducó respecto a {@code ahora}.
     *
     * @param emitido      instante de emisión
     * @param ahora        instante actual
     * @param minutosVida  validez en minutos
     * @return true si han pasado más de 'minutosVida' minutos
     */
    public static boolean haCaducado(LocalDateTime emitido, LocalDateTime ahora, long minutosVida) {
        // TODO 4: calcula el instante de expiración = emitido.plusMinutes(minutosVida).
        // TODO 5: compara 'ahora' con ese instante usando isAfter.
        // TODO 6: caducado = ahora está estrictamente después de la expiración.
        // TODO 7: devuelve ese booleano.
        return false;
    }

    /**
     * Formatea una fecha en ISO-8601 (yyyy-MM-dd).
     *
     * @param fecha fecha a formatear
     * @return representación ISO
     */
    public static String aIso(LocalDate fecha) {
        // TODO 8: LocalDate ya serializa en ISO-8601 con toString().
        // TODO 9: invoca fecha.toString() de forma explícita.
        // TODO 10: devuelve esa cadena (formato yyyy-MM-dd).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(diasEntre(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 11)));
    }

    /**
     * Reto Extra 1: Verificación de fin de semana.
     * Evalúa si una fecha corresponde a un día de fin de semana (sábado o domingo).
     *
     * @param fecha fecha a evaluar
     * @return true si es fin de semana
     */
    public static boolean esFinDeSemana(LocalDate fecha) {
        // GUÍA: obtén el día de la semana de la fecha y compáralo con los dos
        // días que el calendario considera fin de semana. Los enums se comparan
        // por identidad de forma segura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFinDeSemana");
    }

    /**
     * Reto Extra 2: Cálculo preciso de edad.
     * Calcula la edad exacta de una persona en años comparando su nacimiento con la fecha de referencia.
     *
     * @param fechaNacimiento fecha de nacimiento
     * @param ahora           fecha de referencia actual
     * @return edad en años
     */
    public static int calcularEdad(LocalDate fechaNacimiento, LocalDate ahora) {
        // GUÍA: calcula edad con reglas de calendario, no dividiendo días entre 365.
        // La fecha justo antes del cumpleaños es el caso que suele descubrir las
        // soluciones aproximadas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularEdad");
    }

    /**
     * Reto Extra 3: Alineación a UTC global.
     * Convierte una fecha y hora con zona horaria (ZonedDateTime) a un instante universal (Instant).
     *
     * @param zdt fecha y hora zonificada
     * @return instante equivalente en UTC
     */
    public static Instant convertirZonedDateTimeAInstant(ZonedDateTime zdt) {
        // GUÍA: un ZonedDateTime combina hora local y zona; un Instant representa
        // el punto absoluto en la línea temporal. Convierte conservando el instante,
        // no la hora de pared.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirZonedDateTimeAInstant");
    }

    /**
     * Reto Extra 4: Medición de latencia de red.
     * Calcula la diferencia en minutos entre dos marcas de tiempo universales (Instant).
     *
     * @param i1 instante inicial
     * @param i2 instante final
     * @return diferencia en minutos
     */
    public static long obtenerDiferenciaEnMinutos(Instant i1, Instant i2) {
        // GUÍA: entre dos Instants mides tiempo físico transcurrido. Usa una
        // herramienta de duración y expresa el resultado en minutos completos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDiferenciaEnMinutos");
    }

    /**
     * Reto Extra 5: Comparaciones cronológicas seguras.
     * Compara dos marcas temporales para determinar si el instante actual es estrictamente anterior al límite.
     *
     * @param actual instante actual
     * @param limite instante límite
     * @return true si actual es anterior a limite
     */
    public static boolean esFechaAnterior(Instant actual, Instant limite) {
        // GUÍA: compara instantes temporalmente, no por referencia de objeto.
        // La condición es estricta: igualdad con el límite no cuenta como anterior.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFechaAnterior");
    }

    /**
     * Reto Extra 6: Formateo personalizado ISO estándar de fecha.
     * Formatea un objeto LocalDate usando el formateador estándar ISO-8601 local.
     *
     * @param fecha fecha a formatear
     * @return representación textual "yyyy-MM-dd"
     */
    public static String formatearAFechaIsoStandard(LocalDate fecha) {
        // GUÍA: usa el formateo estándar ISO local para obtener yyyy-MM-dd.
        // La idea importante es separar el valor temporal de su representación textual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearAFechaIsoStandard");
    }

    /**
     * Reto Extra 7: Parsing robusto y libre de excepciones.
     * Intenta parsear una cadena ISO a LocalDate. Retorna un Optional vacío en lugar de lanzar ante formatos incorrectos.
     *
     * @param fechaIso cadena de fecha en formato ISO
     * @return Optional con LocalDate, o vacío si el formato es inválido
     */
    public static java.util.Optional<LocalDate> parsearFechaIso(String fechaIso) {
        // GUÍA: intenta interpretar la cadena como fecha ISO. Si el texto no tiene
        // ese formato, devuelve ausencia en vez de dejar escapar una excepción de parseo.
        // En una API, este fallo se traducirá más adelante en una respuesta controlada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearFechaIso");
    }

    /**
     * Reto Extra 8: Ajustes temporales personalizados (Día laboral).
     * Calcula el próximo día laborable a partir de la fecha dada (si el día siguiente es sábado o domingo, salta a lunes).
     *
     * @param fecha fecha inicial
     * @return fecha del próximo día laboral
     */
    public static LocalDate obtenerProximoDiaLaboral(LocalDate fecha) {
        // GUÍA: empieza mirando el día siguiente, no el mismo día. Si cae en fin
        // de semana, avanza hasta llegar a un laborable. Recuerda que LocalDate es
        // inmutable: cada suma de días produce otra fecha.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerProximoDiaLaboral");
    }

    /**
     * Reto Extra 9: Conversión y traducción entre zonas horarias.
     * Convierte una fecha y hora local interpretada en una zona origen a su equivalente en una zona destino.
     *
     * @param ldt         fecha y hora local
     * @param zonaOrigen  ID de la zona horaria origen (ej. "Europe/Madrid")
     * @param zonaDestino ID de la zona horaria destino (ej. "America/New_York")
     * @return fecha y hora local equivalente en la zona destino
     */
    public static LocalDateTime convertirEntreZonasHorarias(LocalDateTime ldt, String zonaOrigen, String zonaDestino) {
        // GUÍA: interpreta primero la hora local en la zona de origen. Después
        // traslada ese mismo instante a la zona destino y extrae su hora local allí.
        // No confundas "mismo instante" con "misma hora de pared".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirEntreZonasHorarias");
    }

    /**
     * Reto Extra 10: Determinación de año bisiesto.
     * Comprueba si el año indicado es bisiesto.
     *
     * @param anio año calendario
     * @return true si es bisiesto
     */
    public static boolean esBisiesto(int anio) {
        // GUÍA: puedes apoyarte en java.time o razonar la regla del calendario:
        // divisible por 4, salvo los siglos no divisibles por 400. Contrasta los
        // casos 1900 y 2000 para evitar la versión incompleta de la regla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBisiesto");
    }

}
