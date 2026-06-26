package com.masterclass.api.b47_pruebas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Ejercicio 359 · Volumen/carga: medir throughput y generar carga simulada.
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.3).
 *
 * <p>NOTA: toda la carga es simulada con {@link Runnable}/{@link Supplier} locales,
 * sin red real. Los tests son completamente deterministas.
 */
public final class Ej359LoadVolumeTest {

    private Ej359LoadVolumeTest() {
    }

    // ---- Tipos de datos auxiliares -----------------------------------------------

    public record Peticion(String endpoint, String metodo) {
    }

    public record ResultadoCarga(int peticionesEnviadas, int errores, long tiempoTotalMs) {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Mide el throughput de una tarea ejecutándola {@code nIteraciones} veces.
     *
     * <p>Descarta las primeras {@code warmup} iteraciones (no se cuentan en el tiempo).
     * Retorna operaciones/segundo = {@code (nIteraciones - warmup) / (tiempoMs / 1000.0)}.
     * Usa {@link System#nanoTime()} para precisión.
     *
     * @param tarea       tarea a medir; no puede ser null
     * @param nIteraciones número total de ejecuciones (warmup incluido); debe ser > 0
     * @param warmup      iteraciones de calentamiento a descartar; debe ser >= 0 y < nIteraciones
     * @return operaciones/segundo o {@code -1} si los parámetros son inválidos
     */
    public static double medirThroughput(Runnable tarea, int nIteraciones, int warmup) {
        // TODO 1: valida tarea != null, nIteraciones > 0, warmup >= 0, warmup < nIteraciones.
        //   Retorna -1 si alguna condición falla.
        // TODO 2: ejecuta el warmup (warmup iteraciones sin medir).
        // TODO 3: registra nanoTime de inicio.
        // TODO 4: ejecuta (nIteraciones - warmup) iteraciones midiendo tiempo.
        // TODO 5: registra nanoTime de fin; calcula tiempoMs = (fin - inicio) / 1_000_000.0.
        // TODO 6: si tiempoMs == 0 (tareas instantáneas en test), retorna (nIteraciones - warmup) * 1000.0.
        // TODO 7: calcula ops/seg = (nIteraciones - warmup) / (tiempoMs / 1000.0).
        // TODO 8: retorna el resultado (double).
        return -1;
    }

    /**
     * Genera una lista de {@link Peticion} creadas con el {@code generador} dado.
     *
     * <p>Ejecuta el generador {@code n} veces, acumulando las peticiones.
     * Si el generador retorna {@code null} en alguna llamada, la omite (no añade null).
     *
     * @param generador fuente de peticiones; no puede ser null
     * @param n         número de peticiones a generar; debe ser > 0
     * @return lista con las peticiones generadas (sin nulls) o {@code List.of()} si parámetros inválidos
     */
    public static List<Peticion> generarCarga(Supplier<Peticion> generador, int n) {
        // TODO 9: valida generador != null y n > 0; retorna List.of() si no.
        // TODO 10: genera n peticiones en un bucle, omite las null, retorna List.copyOf(acumuladas).
        return List.of();
    }

    public static void main(String[] args) {
        // Tarea simulada: suma pequeña (no tiene dead-code elimination en JVM por el consumo de resultado).
        int[] contador = {0};
        Runnable tarea = () -> contador[0] += 1;

        double ops = medirThroughput(tarea, 1000, 100);
        System.out.printf("Throughput: %.0f ops/seg%n", ops);

        Supplier<Peticion> gen = () -> new Peticion("/api/users", "GET");
        List<Peticion> carga = generarCarga(gen, 500);
        System.out.println("Peticiones generadas: " + carga.size());
    }

    // ---- 10 RETOS EXTRA ----------------------------------------------------------

    /**
     * Reto Extra 1: ramp-up gradual de carga.
     * Dado un número inicial y un incremento, devuelve la lista de "niveles de carga"
     * (usuarios concurrentes) para cada paso. Genera {@code pasos} niveles.
     */
    public static List<Integer> rampUp(int inicio, int incremento, int pasos) {
        // GUÍA: teoría 3.1 (ramp-up: aumentar la carga gradualmente para encontrar el punto de quiebre).
        // 1. Valida inicio > 0, incremento > 0, pasos > 0; devuelve List.of() si no.
        // 2. Genera la lista: inicio, inicio+incremento, inicio+2*incremento, …
        // PISTA: IntStream.range(0, pasos).mapToObj(i -> inicio + i * incremento).toList()
        // OJO: el test verifica el primer y último elemento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rampUp");
    }

    /**
     * Reto Extra 2: carga sostenida vs carga pico.
     * Clasifica un nivel de carga: si carga <= base → "SOSTENIDA"; si carga <= base*2 → "PICO";
     * si carga > base*2 → "SOBRECARGA".
     */
    public static String clasificarCarga(int carga, int base) {
        // GUÍA: teoría 3.2 (diferencia entre carga sostenida, pico y sobrecarga).
        // 1. base <= 0 → "INVALIDO".
        // 2. carga <= base → "SOSTENIDA".
        // 3. carga <= base*2 → "PICO".
        // 4. carga > base*2 → "SOBRECARGA".
        // PISTA: serie de if/else if sencilla.
        // OJO: el test verifica exactamente en base*2 → "PICO" (no "SOBRECARGA").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clasificarCarga");
    }

    /**
     * Reto Extra 3: concurrencia con ExecutorService (enlace b27).
     * Ejecuta {@code nHilos} tareas en paralelo usando un fixed thread pool,
     * espera a que terminen y devuelve cuántas completaron sin excepción.
     */
    public static int ejecutarEnParalelo(List<Runnable> tareas, int nHilos) {
        // GUÍA: teoría 3.3 (prueba de carga concurrente: múltiples hilos → b27 ExecutorService).
        // 1. Crea ExecutorService.newFixedThreadPool(nHilos).
        // 2. Envía cada tarea con submit() y guarda los Future.
        // 3. Llama a get() en cada Future; cuenta los que no lanzaron excepción.
        // 4. Llama a shutdown() y awaitTermination(5, SECONDS).
        // PISTA: var futures = tareas.stream().map(es::submit).toList();
        // OJO: cierra siempre el executor (try/finally o try-with-resources con shutdownNow en catch).
        // CULTURA: b27 (concurrencia) enseña los hilos; aquí los usas para simular usuarios concurrentes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarEnParalelo");
    }

    /**
     * Reto Extra 4: back-pressure — limitar la cola de trabajo.
     * Dado un límite de cola y una lista de tareas, devuelve cuántas se aceptaron
     * (las primeras {@code limitecola}) y cuántas fueron rechazadas.
     */
    public static int[] aplicarBackPressure(List<Runnable> tareas, int limiteCola) {
        // GUÍA: teoría 3.4 (back-pressure: el sistema resiste rechazando trabajo excesivo).
        // 1. Acepta min(tareas.size(), limiteCola) tareas.
        // 2. Devuelve int[] { aceptadas, rechazadas }.
        // PISTA: int aceptadas = Math.min(tareas.size(), limiteCola);
        //   return new int[]{aceptadas, tareas.size() - aceptadas};
        // OJO: tareas null → {0, 0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarBackPressure");
    }

    /**
     * Reto Extra 5: volumen de datos (datasets grandes).
     * Genera una lista de {@code n} elementos de prueba usando un índice.
     * Cada elemento es "item_" + índice.
     */
    public static List<String> generarDataset(int n) {
        // GUÍA: teoría 3.5 (pruebas de volumen: el sistema bajo grandes cantidades de datos, no solo peticiones).
        // 1. n <= 0 → List.of().
        // 2. Genera la lista con IntStream.range(0, n).mapToObj(i -> "item_" + i).toList().
        // PISTA: IntStream.range(0, n).mapToObj(i -> "item_" + i).toList()
        // OJO: el test pasa n=1_000_000 → la implementación debe ser O(n) sin OutOfMemoryError en condiciones normales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarDataset");
    }

    /**
     * Reto Extra 6: throughput vs latencia — dado un throughput (ops/seg) y una latencia media (ms),
     * calcula el número de operaciones en vuelo (Little's Law: N = λ × W).
     */
    public static double operacionesEnVuelo(double throughputOpsSeg, double latenciaMs) {
        // GUÍA: teoría 3.6 (Ley de Little: N = λ × W; enlaza throughput y latencia).
        // 1. throughput <= 0 o latencia < 0 → -1.
        // 2. N = throughputOpsSeg * (latenciaMs / 1000.0).
        // PISTA: return throughputOpsSeg * latenciaMs / 1000.0;
        // OJO: el test pasa latencia 0 → resultado 0 (no -1, pues 0 >= 0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para operacionesEnVuelo");
    }

    /**
     * Reto Extra 7: JMH como guion — indica los pasos para crear un benchmark JMH.
     * Devuelve la lista de pasos en orden: ["@BenchmarkMode", "@Warmup", "@Measurement", "@Fork", "@Benchmark"].
     */
    public static List<String> pasosJMH() {
        // GUÍA: teoría 3.7 (JMH: el framework estándar de microbenchmarking en Java).
        // 1. Simplemente devuelve la lista fija de anotaciones en el orden correcto.
        // No hay lógica dinámica: es una referencia de los pasos mínimos.
        // PISTA: List.of("@BenchmarkMode", "@Warmup", "@Measurement", "@Fork", "@Benchmark")
        // OJO: el test verifica el orden exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasosJMH");
    }

    /**
     * Reto Extra 8: medir GC durante la carga.
     * Dado el número de colecciones GC antes y después y el tiempo transcurrido (ms),
     * calcula las colecciones GC por segundo.
     */
    public static double gcPorSegundo(long gcAntes, long gcDespues, long tiempoMs) {
        // GUÍA: teoría 3.8 (GC pressure: alta presión GC indica fuga de objetos bajo carga).
        // 1. tiempoMs <= 0 → -1.
        // 2. gcDelta = gcDespues - gcAntes (si < 0, tratar como 0).
        // 3. Retorna gcDelta / (tiempoMs / 1000.0).
        // PISTA: Math.max(0, gcDespues - gcAntes) / (tiempoMs / 1000.0)
        // OJO: tiempoMs = 500 y delta = 3 → 6.0 colecciones/seg.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gcPorSegundo");
    }

    /**
     * Reto Extra 9: steady state — calcula si el throughput se ha estabilizado.
     * Una lista de muestras de throughput está en steady state si la desviación estándar
     * de las últimas {@code ventana} muestras es menor que {@code umbralPct} % de la media.
     */
    public static boolean enSteadyState(List<Double> muestras, int ventana, double umbralPct) {
        // GUÍA: teoría 3.9 (steady state: el sistema ha "calentado" y su rendimiento es estable).
        // 1. Si muestras tiene menos de ventana elementos → false.
        // 2. Toma las últimas ventana muestras.
        // 3. Calcula media y desviación estándar.
        // 4. Retorna true si desv/media*100 < umbralPct.
        // PISTA: usa subList(size-ventana, size), luego stream().mapToDouble().average() y variance.
        // OJO: media == 0 → false (evita división por cero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enSteadyState");
    }

    /**
     * Reto Extra 10: enlace con b21 (rendimiento) y b29 (servidor bajo carga).
     * Combina throughput y disponibilidad: dado un throughput objetivo y el real,
     * y una disponibilidad real (fracción 0..1), calcula el "score" de servicio
     * como (realThroughput / objetivoThroughput) * disponibilidad * 100.
     * Devuelve -1 si objetivo <= 0 o disponibilidad fuera de [0,1].
     */
    public static double scoreServicio(double objetivoThroughput, double realThroughput, double disponibilidad) {
        // GUÍA: teoría 3.10 (métricas combinadas: rendimiento × disponibilidad = calidad de servicio).
        // 1. Valida objetivoThroughput > 0 y disponibilidad en [0,1]; devuelve -1 si no.
        // 2. score = (realThroughput / objetivoThroughput) * disponibilidad * 100.
        // PISTA: clamp el resultado a [0, 100] con Math.min/max si realThroughput > objetivo.
        // OJO: el test pasa realThroughput > objetivo (sobrecumplimiento) → score puede ser > 100 sin clamp.
        //   Decide si clampar o no; el test verifica tu decisión.
        // CULTURA: en b21 (resiliencia) aprendiste circuit breaker y rate limiting; el score aquí mide
        //   si el sistema cumple el SLA bajo carga real → conecta la prueba con el objetivo de negocio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para scoreServicio");
    }
}
