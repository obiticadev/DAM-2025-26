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
        // GUÍA: el enum DayOfWeek (ya importado) hace todo el trabajo:
        // DayOfWeek dia = fecha.getDayOfWeek();
        // return dia == DayOfWeek.SATURDAY || dia == DayOfWeek.SUNDAY;
        // (Los enums se comparan con == sin miedo: son singletons.)
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
        // GUÍA: Period es la clase de "cantidades de calendario" (teoría 1.10):
        // return Period.between(fechaNacimiento, ahora).getYears();
        // El segundo test es la trampa que Period resuelve solo: un día ANTES
        // del cumpleaños → 30, no 31. Si lo calcularas con días/365 fallaría
        // por los bisiestos. NO reinventes el calendario.
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
        // GUÍA: una línea — return zdt.toInstant();
        // EL CONCEPTO vale más que la línea: "12:00 en Madrid" (ZonedDateTime)
        // y "10:00 UTC" (Instant) son EL MISMO punto en la línea del tiempo.
        // toInstant() tira la información de zona y deja el punto absoluto.
        // Regla de la teoría 1.10: tu BD y tu JSON guardan Instant; la zona
        // se aplica al MOSTRAR.
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
        // GUÍA: Duration para tiempo físico (Period era para calendario):
        // return Duration.between(i1, i2).toMinutes();
        // (Equivale a ChronoUnit.MINUTES.between(i1, i2) — conoce ambas.)
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
        // GUÍA: una línea — return actual.isBefore(limite);
        // Toda la familia java.time trae isBefore / isAfter / isEqual:
        // SIEMPRE preferibles a compareTo() < 0 por legibilidad. Nunca
        // compares fechas con == (compara referencias, no momentos).
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
        // GUÍA: esta vez con el formateador EXPLÍCITO (en aIso usaste toString):
        // return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
        // Mismo resultado, pero conoce el mecanismo general: format(formatter)
        // sirve para CUALQUIER patrón — ofPattern("dd/MM/yyyy") cuando el
        // cliente lo pida. ISO_LOCAL_DATE es la constante para "yyyy-MM-dd".
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
        // GUÍA: el patrón defensivo que ya practicaste en Ej005 reto 9:
        // try { return Optional.of(LocalDate.parse(fechaIso)); }
        // catch (DateTimeParseException | NullPointerException e) {
        //     return Optional.empty();
        // }
        // El test mete "21/05/2026" (formato español, no ISO) → empty, no
        // explosión. CONTEXTO API: una fecha mal formateada en un query param
        // debe acabar en 400 Bad Request, jamás en un 500 por excepción suelta.
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
        // GUÍA: avanza un día y, si caes en finde, sigue avanzando:
        // LocalDate siguiente = fecha.plusDays(1);
        // while (esFinDeSemana(siguiente)) {      // ¡reutiliza el reto 1!
        //     siguiente = siguiente.plusDays(1);
        // }
        // return siguiente;
        // RECUERDA el error común nº3: plusDays DEVUELVE una fecha nueva;
        // sin la reasignación, el bucle sería infinito. Los tres tests:
        // jueves→viernes, viernes→lunes, sábado→lunes.
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
        // GUÍA: la coreografía completa de zonas, en tres pasos:
        // 1. INTERPRETA la hora local en su zona origen:
        //    ZonedDateTime enOrigen = ldt.atZone(ZoneId.of(zonaOrigen));
        // 2. TRASLADA el mismo instante a la zona destino:
        //    ZonedDateTime enDestino = enOrigen.withZoneSameInstant(ZoneId.of(zonaDestino));
        // 3. Quédate con la hora local resultante: enDestino.toLocalDateTime();
        // El test: 12:00 Madrid (UTC+2 en mayo) → 06:00 Nueva York (UTC-4).
        // OJO: withZoneSameInstant (mismo momento, otra pared) ≠
        // withZoneSameLocal (misma pared, otro momento). Aquí quieres Instant.
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
        // GUÍA: el JDK ya sabe la regla — java.time.Year.isLeap(anio).
        // (También vale LocalDate.of(anio, 1, 1).isLeapYear().)
        // Si quieres implementarla a mano para entenderla:
        // divisible por 4 Y (no divisible por 100 O divisible por 400):
        // 2024 ✔ (÷4), 2026 ✘, 1900 ✘ (÷100 pero no ÷400), 2000 ✔ (÷400).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBisiesto");
    }

}
