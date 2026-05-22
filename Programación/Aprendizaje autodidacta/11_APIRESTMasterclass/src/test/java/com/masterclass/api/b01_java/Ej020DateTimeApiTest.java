package com.masterclass.api.b01_java;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class Ej020DateTimeApiTest {

    @Test
    void diasEntre() {
        assertEquals(10, Ej020DateTimeApi.diasEntre(
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 11)));
    }

    @Test
    void haCaducado() {
        var emit = LocalDateTime.of(2026, 1, 1, 10, 0);
        assertTrue(Ej020DateTimeApi.haCaducado(emit, emit.plusMinutes(31), 30));
        assertFalse(Ej020DateTimeApi.haCaducado(emit, emit.plusMinutes(10), 30));
    }

    @Test
    void aIso() {
        assertEquals("2026-05-18", Ej020DateTimeApi.aIso(LocalDate.of(2026, 5, 18)));
    }

    @Test
    void retoExtra01_esFinDeSemana() {
        assertTrue(Ej020DateTimeApi.esFinDeSemana(LocalDate.of(2026, 5, 23))); // Sábado
        assertTrue(Ej020DateTimeApi.esFinDeSemana(LocalDate.of(2026, 5, 24))); // Domingo
        assertFalse(Ej020DateTimeApi.esFinDeSemana(LocalDate.of(2026, 5, 22))); // Viernes
    }

    @Test
    void retoExtra02_calcularEdad() {
        LocalDate nac = LocalDate.of(1995, 5, 10);
        LocalDate ref = LocalDate.of(2026, 5, 10);
        assertEquals(31, Ej020DateTimeApi.calcularEdad(nac, ref));
        assertEquals(30, Ej020DateTimeApi.calcularEdad(nac, ref.minusDays(1)));
    }

    @Test
    void retoExtra03_convertirZonedDateTimeAInstant() {
        java.time.ZonedDateTime zdt = java.time.ZonedDateTime.of(2026, 5, 21, 12, 0, 0, 0, java.time.ZoneId.of("Europe/Madrid"));
        java.time.Instant instant = Ej020DateTimeApi.convertirZonedDateTimeAInstant(zdt);
        assertEquals(zdt.toInstant(), instant);
    }

    @Test
    void retoExtra04_obtenerDiferenciaEnMinutos() {
        java.time.Instant i1 = java.time.Instant.parse("2026-05-21T10:00:00Z");
        java.time.Instant i2 = java.time.Instant.parse("2026-05-21T10:35:00Z");
        assertEquals(35, Ej020DateTimeApi.obtenerDiferenciaEnMinutos(i1, i2));
    }

    @Test
    void retoExtra05_esFechaAnterior() {
        java.time.Instant i1 = java.time.Instant.parse("2026-05-21T10:00:00Z");
        java.time.Instant i2 = java.time.Instant.parse("2026-05-21T10:05:00Z");
        assertTrue(Ej020DateTimeApi.esFechaAnterior(i1, i2));
        assertFalse(Ej020DateTimeApi.esFechaAnterior(i2, i1));
    }

    @Test
    void retoExtra06_formatearAFechaIsoStandard() {
        assertEquals("2026-05-21", Ej020DateTimeApi.formatearAFechaIsoStandard(LocalDate.of(2026, 5, 21)));
    }

    @Test
    void retoExtra07_parsearFechaIso() {
        assertTrue(Ej020DateTimeApi.parsearFechaIso("2026-05-21").isPresent());
        assertEquals(LocalDate.of(2026, 5, 21), Ej020DateTimeApi.parsearFechaIso("2026-05-21").get());
        assertTrue(Ej020DateTimeApi.parsearFechaIso("21/05/2026").isEmpty());
    }

    @Test
    void retoExtra08_obtenerProximoDiaLaboral() {
        assertEquals(LocalDate.of(2026, 5, 22), Ej020DateTimeApi.obtenerProximoDiaLaboral(LocalDate.of(2026, 5, 21))); // Jueves -> Viernes
        assertEquals(LocalDate.of(2026, 5, 25), Ej020DateTimeApi.obtenerProximoDiaLaboral(LocalDate.of(2026, 5, 22))); // Viernes -> Lunes
        assertEquals(LocalDate.of(2026, 5, 25), Ej020DateTimeApi.obtenerProximoDiaLaboral(LocalDate.of(2026, 5, 23))); // Sábado -> Lunes
    }

    @Test
    void retoExtra09_convertirEntreZonasHorarias() {
        LocalDateTime ldt = LocalDateTime.of(2026, 5, 21, 12, 0); // 12:00 en Madrid
        LocalDateTime converted = Ej020DateTimeApi.convertirEntreZonasHorarias(ldt, "Europe/Madrid", "America/New_York");
        // Europe/Madrid (UTC+2 en Mayo) vs America/New_York (UTC-4 en Mayo) -> diferencia es -6 horas
        assertEquals(ldt.minusHours(6), converted);
    }

    @Test
    void retoExtra10_esBisiesto() {
        assertTrue(Ej020DateTimeApi.esBisiesto(2024));
        assertFalse(Ej020DateTimeApi.esBisiesto(2026));
    }
}
