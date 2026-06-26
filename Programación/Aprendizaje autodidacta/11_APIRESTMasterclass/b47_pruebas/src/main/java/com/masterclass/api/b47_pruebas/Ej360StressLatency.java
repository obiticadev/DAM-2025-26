package com.masterclass.api.b47_pruebas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ejercicio 360 · Estrés: percentiles de latencia y punto de saturación.
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.4).
 */
public final class Ej360StressLatency {

    private Ej360StressLatency() {
    }

    // ---- Tipos de datos auxiliares -----------------------------------------------

    /** Un punto de la curva carga/latencia: {@code carga} usuarios y {@code latenciaMs} observada. */
    public record Medida(int carga, long latenciaMs) {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Calcula el percentil {@code p} de una lista de latencias (en milisegundos).
     *
     * <p>Algoritmo: ordena una copia, índice {@code (int) Math.ceil(p / 100.0 * size) - 1}
     * (limitado a [0, size-1]).
     *
     * @param latencias lista de latencias; no puede ser null
     * @param p         percentil a calcular [0, 100]
     * @return latencia en el percentil p, o {@code -1} si la lista es null, vacía o p está fuera de rango
     */
    public static long percentil(List<Long> latencias, int p) {
        // TODO 1: valida latencias != null y !vacía, y p en [0, 100]; retorna -1 si no.
        // TODO 2: crea una copia mutable de la lista.
        // TODO 3: ordena la copia en orden ascendente.
        // TODO 4: calcula el índice: (int) Math.ceil(p / 100.0 * size) - 1.
        // TODO 5: limita el índice al rango [0, size - 1].
        // TODO 6: retorna latencias_ordenadas.get(índice).
        return -1;
    }

    /**
     * Detecta el punto de saturación en una curva carga/latencia.
     *
     * <p>El punto de saturación es la primera {@link Medida} donde la latencia supera
     * el doble de la latencia de la primera medida de la curva.
     * Si ninguna medida supera ese umbral, retorna {@code null}.
     *
     * @param curva lista de medidas ordenadas por carga ascendente; no puede ser null ni vacía
     * @return primera {@link Medida} que supera el umbral, o {@code null} si no hay saturación
     */
    public static Medida puntoSaturacion(List<Medida> curva) {
        // TODO 7: valida curva != null y size >= 2; retorna null si no.
        // TODO 8: obtiene la latencia base = curva.get(0).latenciaMs().
        // TODO 9: calcula umbral = base * 2.
        // TODO 10: recorre la curva desde la posición 1; retorna la primera medida con latenciaMs > umbral.
        //   Si no se encuentra ninguna, retorna null.
        return null;
    }

    public static void main(String[] args) {
        List<Long> latencias = List.of(50L, 52L, 55L, 60L, 70L, 80L, 200L, 250L, 300L, 500L);
        System.out.println("p50 = " + percentil(latencias, 50) + " ms");
        System.out.println("p95 = " + percentil(latencias, 95) + " ms");
        System.out.println("p99 = " + percentil(latencias, 99) + " ms");

        List<Medida> curva = List.of(
                new Medida(10, 50), new Medida(50, 60), new Medida(100, 80),
                new Medida(200, 120), new Medida(500, 300), new Medida(1000, 600)
        );
        System.out.println("Saturación en: " + puntoSaturacion(curva));
    }

    // ---- 10 RETOS EXTRA ----------------------------------------------------------

    /**
     * Reto Extra 1: tail latency — explica el p99.
     * Dado p50, p95 y p99, indica si hay "cola larga": se considera cola larga si
     * p99 > p95 * 1.5 Y p99 > p50 * 3.
     */
    public static boolean tieneColaLarga(long p50, long p95, long p99) {
        // GUÍA: teoría 4.1 (tail latency: el p99 afecta a 1 de cada 100 peticiones — en alta carga son miles).
        // 1. Devuelve true si p99 > p95 * 1.5 && p99 > p50 * 3.
        // PISTA: return p99 > p95 * 1.5 && p99 > p50 * 3;
        // OJO: el test usa valores en los que una sola condición se cumple pero no la otra → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneColaLarga");
    }

    /**
     * Reto Extra 2: coordinated omission — ajustar la latencia medida.
     * En coordinated omission, si el sistema está lento, el cliente espera antes de
     * enviar la siguiente petición. Para corregirlo: latenciaCorregida = max(latenciaReal, intervalo).
     * Aplica la corrección a toda una lista.
     */
    public static List<Long> corregirCoordinatedOmission(List<Long> latenciasReales, long intervaloMs) {
        // GUÍA: teoría 4.2 (coordinated omission: sesgo que hace el sistema parecer más rápido de lo que es).
        // 1. Por cada latencia, devuelve max(latenciaReal, intervaloMs).
        // 2. Lista null o vacía → List.of().
        // PISTA: latenciasReales.stream().map(l -> Math.max(l, intervaloMs)).toList()
        // OJO: el test pasa intervaloMs = 0 → resultado igual a las latencias originales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para corregirCoordinatedOmission");
    }

    /**
     * Reto Extra 3: histograma de latencias.
     * Divide el rango [0, max] en {@code cubos} intervalos iguales y cuenta cuántas latencias
     * caen en cada intervalo. Devuelve la lista de conteos.
     */
    public static List<Integer> histograma(List<Long> latencias, int cubos) {
        // GUÍA: teoría 4.3 (histograma: distribución completa vs. un solo percentil).
        // 1. Valida latencias no vacías y cubos > 0; devuelve List.of() si no.
        // 2. Calcula max; tamaño de cubo = max / cubos (si max=0, todos en cubo 0).
        // 3. Por cada latencia, índice = min((int)(lat / tamCubo), cubos - 1).
        // 4. Devuelve la lista de conteos.
        // PISTA: usa un int[] de tamaño cubos, luego convierte a List<Integer>.
        // OJO: el test verifica que la suma de conteos == latencias.size().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para histograma");
    }

    /**
     * Reto Extra 4: SLA — verifica si el sistema cumple el SLA.
     * El SLA dice: "p95 < maxP95ms Y disponibilidad >= minDisponibilidad".
     */
    public static boolean cumpleSLA(long p95Ms, long maxP95Ms, double disponibilidad, double minDisponibilidad) {
        // GUÍA: teoría 4.4 (SLA: Service Level Agreement — el contrato de rendimiento con el cliente).
        // 1. Devuelve p95Ms < maxP95Ms && disponibilidad >= minDisponibilidad.
        // PISTA: return p95Ms < maxP95Ms && disponibilidad >= minDisponibilidad;
        // OJO: el test usa exactamente p95Ms == maxP95Ms → false (el SLA exige estrictamente menor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cumpleSLA");
    }

    /**
     * Reto Extra 5: degradación graceful (enlace b21 circuit breaker).
     * Si la latencia supera el umbral, activa el modo degradado.
     * Devuelve "NORMAL", "DEGRADADO" o "CORTADO" según si está por debajo del umbral,
     * entre umbral y umbral*2, o por encima de umbral*2.
     */
    public static String modoServicio(long latenciaMs, long umbralMs) {
        // GUÍA: teoría 4.5 (degradación graceful y circuit breaker → b21).
        // 1. latenciaMs <= umbralMs → "NORMAL".
        // 2. latenciaMs <= umbralMs * 2 → "DEGRADADO".
        // 3. latenciaMs > umbralMs * 2 → "CORTADO".
        // PISTA: serie de if/else if.
        // OJO: el test verifica exactamente en umbralMs → "NORMAL" (usa <=).
        // CULTURA: en b21 usaste @CircuitBreaker de Resilience4j; aquí implementas la lógica de
        //   decisión que ese componente hace internamente. El circuit breaker "ABIERTO" = "CORTADO" aquí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modoServicio");
    }

    /**
     * Reto Extra 6: load shedding — rechaza peticiones bajo alta carga.
     * Dado el porcentaje de uso de la cola (0..100), decide si rechazar la petición:
     * rechaza si uso >= umbralRechazoPct.
     */
    public static boolean rechazarPeticion(int usoCola, int umbralRechazoPct) {
        // GUÍA: teoría 4.6 (load shedding: mejor responder rápido con error que saturar todo el sistema).
        // 1. Devuelve usoCola >= umbralRechazoPct.
        // PISTA: return usoCola >= umbralRechazoPct;
        // OJO: el test usa usoCola = umbralRechazoPct → true (rechaza en el límite exacto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rechazarPeticion");
    }

    /**
     * Reto Extra 7: timeout bajo estrés.
     * Dado el tiempo de respuesta y el timeout configurado, devuelve true si la petición
     * superó el timeout (tiempoRespuestaMs > timeoutMs).
     */
    public static boolean superaTimeout(long tiempoRespuestaMs, long timeoutMs) {
        // GUÍA: teoría 4.7 (timeout: el contrato de tiempo máximo que el cliente acepta esperar).
        // 1. Devuelve tiempoRespuestaMs > timeoutMs.
        // OJO: exactamente igual → false (estrictamente mayor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para superaTimeout");
    }

    /**
     * Reto Extra 8: recuperación tras pico de carga.
     * Dado el tiempo del pico (ms) y el tiempo hasta volver a latencia normal (ms),
     * calcula el tiempo de recuperación = (tiempoRecuperacionMs - tiempoNormal) / tiempoNormal * 100 (%).
     * Retorna -1 si tiempoNormal <= 0.
     */
    public static double tiempoRecuperacionPct(long tiempoNormal, long tiempoRecuperacionMs) {
        // GUÍA: teoría 4.8 (recuperación tras pico: ¿cuánto tarda el sistema en volver al baseline?).
        // 1. tiempoNormal <= 0 → -1.
        // 2. Calcula (tiempoRecuperacionMs - tiempoNormal) / (double) tiempoNormal * 100.
        // OJO: tiempoRecuperacionMs <= tiempoNormal → resultado <= 0 (recuperación inmediata o instantánea).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoRecuperacionPct");
    }

    /**
     * Reto Extra 9: spike test — detecta si un conjunto de latencias tiene un spike.
     * Un spike es una latencia que supera la media * {@code factorSpike}.
     * Devuelve las latencias que constituyen un spike.
     */
    public static List<Long> detectarSpikes(List<Long> latencias, double factorSpike) {
        // GUÍA: teoría 4.9 (spike test: tráfico repentino y breve a nivel extremo).
        // 1. Calcula la media de la lista.
        // 2. Filtra las latencias > media * factorSpike.
        // PISTA: double media = latencias.stream().mapToLong(Long::longValue).average().orElse(0);
        // OJO: lista vacía → List.of().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarSpikes");
    }

    /**
     * Reto Extra 10: enlace con b186 (resiliencia).
     * Dado un historial de latencias por segundo, calcula cuántos segundos el p99 supera
     * el SLA. Devuelve el porcentaje de segundos en violación de SLA.
     */
    public static double pctSegundosViolaSLA(List<List<Long>> historialPorSegundo, long slaP99Ms) {
        // GUÍA: teoría 4.10 (SLO compliance: qué fracción del tiempo se viola el SLA → conecta con b21).
        // 1. Para cada segundo (lista de latencias), calcula su p99.
        // 2. Cuenta los segundos donde p99 > slaP99Ms.
        // 3. Devuelve (conteo / total) * 100.
        // 4. Lista vacía → 0.0.
        // PISTA: usa el método percentil() de este mismo ejercicio.
        // OJO: si todos los segundos cumplen el SLA → 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pctSegundosViolaSLA");
    }
}
