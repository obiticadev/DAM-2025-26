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
        // TODO extra: comprueba si el día de la semana es SATURDAY o SUNDAY
        return false;
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
        // TODO extra: usa Period.between() para calcular la diferencia y extrae los años
        return -1;
    }

    /**
     * Reto Extra 3: Alineación a UTC global.
     * Convierte una fecha y hora con zona horaria (ZonedDateTime) a un instante universal (Instant).
     *
     * @param zdt fecha y hora zonificada
     * @return instante equivalente en UTC
     */
    public static Instant convertirZonedDateTimeAInstant(ZonedDateTime zdt) {
        // TODO extra: convierte el ZonedDateTime a un objeto Instant nativo
        return null;
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
        // TODO extra: calcula la duración entre i1 e i2 y conviértela a minutos
        return Long.MIN_VALUE;
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
        // TODO extra: verifica si el instante actual ocurre antes que el límite usando isBefore()
        return false;
    }

    /**
     * Reto Extra 6: Formateo personalizado ISO estándar de fecha.
     * Formatea un objeto LocalDate usando el formateador estándar ISO-8601 local.
     *
     * @param fecha fecha a formatear
     * @return representación textual "yyyy-MM-dd"
     */
    public static String formatearAFechaIsoStandard(LocalDate fecha) {
        // TODO extra: utiliza DateTimeFormatter.ISO_LOCAL_DATE para formatear la fecha
        return "";
    }

    /**
     * Reto Extra 7: Parsing robusto y libre de excepciones.
     * Intenta parsear una cadena ISO a LocalDate. Retorna un Optional vacío en lugar de lanzar ante formatos incorrectos.
     *
     * @param fechaIso cadena de fecha en formato ISO
     * @return Optional con LocalDate, o vacío si el formato es inválido
     */
    public static java.util.Optional<LocalDate> parsearFechaIso(String fechaIso) {
        // TODO extra: parsea la cadena con LocalDate.parse, capturando DateTimeParseException para retornar Optional.empty()
        return java.util.Optional.empty();
    }

    /**
     * Reto Extra 8: Ajustes temporales personalizados (Día laboral).
     * Calcula el próximo día laborable a partir de la fecha dada (si el día siguiente es sábado o domingo, salta a lunes).
     *
     * @param fecha fecha inicial
     * @return fecha del próximo día laboral
     */
    public static LocalDate obtenerProximoDiaLaboral(LocalDate fecha) {
        // TODO extra: calcula el día siguiente e incrementa hasta un día laborable si cae en fin de semana
        return null;
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
        // TODO extra: asocia ldt a la zona origen, conviértela preservando el instante a la zona destino, y extrae su LocalDateTime
        return null;
    }

    /**
     * Reto Extra 10: Determinación de año bisiesto.
     * Comprueba si el año indicado es bisiesto.
     *
     * @param anio año calendario
     * @return true si es bisiesto
     */
    public static boolean esBisiesto(int anio) {
        // TODO extra: usa las capacidades de LocalDate para determinar si un año es bisiesto
        return false;
    }

}
