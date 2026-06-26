package com.masterclass.api.b47_pruebas;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;

/**
 * Ejercicio 361 · Uso de recursos: memoria, CPU, hilos y detección de fugas.
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.5).
 */
public final class Ej361ResourceUsageTest {

    private Ej361ResourceUsageTest() {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Comprueba que el consumo de memoria no supera el presupuesto.
     *
     * @param memoriaMB  bytes usados antes de la operación
     * @param memoriaMB2 bytes usados después de la operación
     * @param limiteMB   límite máximo de incremento en bytes
     * @return {@code true} si {@code (memoriaMB2 - memoriaMB) <= limiteMB}; {@code false} en otro caso
     */
    public static boolean presupuestoMemoria(long memoriaAntesBytes, long memoriaDesuesBytes, long limiteBytes) {
        // TODO 1: valida limiteBytes >= 0; retorna false si es negativo.
        // TODO 2: calcula delta = memoriaDesuesBytes - memoriaAntesBytes.
        // TODO 3: si delta < 0 (el GC libero memoria entre mediciones), usa delta = 0.
        // TODO 4: retorna delta <= limiteBytes.
        return false;
    }

    /**
     * Detecta si se han creado hilos que no se han cerrado.
     *
     * <p>Hay fuga de hilos si {@code hilosDespues > hilosAntes + tolerancia}.
     *
     * @param hilosAntes   número de hilos activos antes de la operación
     * @param hilosDespues número de hilos activos después de la operación
     * @param tolerancia   número de hilos extra permitidos sin considerarse fuga
     * @return {@code true} si hay fuga, {@code false} si no
     */
    public static boolean detectarFugaHilos(int hilosAntes, int hilosDespues, int tolerancia) {
        // TODO 5: valida tolerancia >= 0; retorna true (fuga conservadora) si es negativo.
        // TODO 6: retorna hilosDespues > hilosAntes + tolerancia.
        return false;
    }

    /**
     * Obtiene el número de hilos activos usando {@link ThreadMXBean}.
     * Se usa en los tests como utilidad de medición real.
     */
    public static int hilosActivos() {
        // TODO 7: obtiene ManagementFactory.getThreadMXBean().getThreadCount().
        // TODO 8: retorna el conteo.
        return -1;
    }

    /**
     * Obtiene la memoria usada actual en bytes: totalMemory - freeMemory.
     * Útil para capturar el estado "antes" y "después" en los tests.
     */
    public static long memoriaUsadaBytes() {
        // TODO 9: obtiene Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory().
        // TODO 10: retorna el valor.
        return -1;
    }

    public static void main(String[] args) {
        long antes = memoriaUsadaBytes();
        int[] big = new int[1_000_000]; // crea presión de memoria
        long despues = memoriaUsadaBytes();
        System.out.println("¿Dentro de presupuesto 10 MB? " + presupuestoMemoria(antes, despues, 10_000_000));

        int hilosAntes = hilosActivos();
        Thread t = new Thread(() -> {
            try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        t.start();
        int hilosDurante = hilosActivos();
        System.out.println("¿Fuga de hilos (tolerancia 2)? " + detectarFugaHilos(hilosAntes, hilosDurante, 2));
    }

    // ---- 10 RETOS EXTRA ----------------------------------------------------------

    /**
     * Reto Extra 1: heap diff tras N operaciones.
     * Dado el uso de heap antes y después de N operaciones, calcula los bytes por operación.
     */
    public static double bytesPorOperacion(long heapAntes, long heapDespues, int nOperaciones) {
        // GUÍA: teoría 5.1 (heap diff: cuánta memoria consume cada operación en promedio).
        // 1. nOperaciones <= 0 → -1.
        // 2. delta = max(heapDespues - heapAntes, 0).
        // 3. Retorna delta / (double) nOperaciones.
        // PISTA: Math.max(0, heapDespues - heapAntes) / (double) nOperaciones
        // OJO: heapDespues < heapAntes (GC corrió) → delta = 0 → resultado 0.0 (no negativo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bytesPorOperacion");
    }

    /**
     * Reto Extra 2: memory leak clásico (colección que crece).
     * Simula una colección que acumula objetos sin limpiar. Añade {@code n} cadenas y
     * devuelve el tamaño final de la colección. No limpia nada (el "leak" está en no borrar).
     */
    public static int simularMemoryLeak(int n) {
        // GUÍA: teoría 5.2 (memory leak: causa más común = colección estática que acumula sin límite).
        // 1. Crea una lista local, añade n cadenas "item_i".
        // 2. Devuelve el tamaño (NO la limpia → simula el leak lógicamente).
        // PISTA: List<String> lista = new ArrayList<>(); for(int i=0;i<n;i++) lista.add("item_"+i); return lista.size();
        // OJO: n <= 0 → 0.
        // CULTURA: en b26_io (NIO.2) usaste try-with-resources (b01 b19) para cerrar streams;
        //   el mismo patrón evita fugas de descriptores de fichero (reto 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para simularMemoryLeak");
    }

    /**
     * Reto Extra 3: try-with-resources y cierre (enlace b01/b26).
     * Dado un AutoCloseable que lanza excepción al cerrar, verifica que la excepción
     * se propaga correctamente. Devuelve true si se captura la excepción al cerrar.
     */
    public static boolean cierreConExcepcion(AutoCloseable recurso) {
        // GUÍA: teoría 5.3 (try-with-resources garantiza cierre incluso si hay excepción → b01, b26).
        // 1. Intenta cerrar recurso con try { recurso.close(); return false; } catch(Exception e) { return true; }
        // PISTA: la excepción al cerrar es "suprimida" en try-with-resources; aquí la capturamos manualmente.
        // OJO: recurso null → return false (no se puede cerrar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cierreConExcepcion");
    }

    /**
     * Reto Extra 4: thread pool que no se apaga.
     * Comprueba que un ExecutorService creado fuera del scope de un método se termina.
     * Devuelve true si el executor se terminó correctamente en el tiempo dado.
     */
    public static boolean ejecutorTerminado(java.util.concurrent.ExecutorService executor, long timeoutMs) {
        // GUÍA: teoría 5.4 (thread pool leak: un executor que no se apaga mantiene hilos vivos → OOM).
        // 1. executor null → false.
        // 2. Llama a executor.shutdown().
        // 3. Intenta executor.awaitTermination(timeoutMs, MILLISECONDS); retorna true si termina.
        // PISTA: try { return executor.awaitTermination(timeoutMs, java.util.concurrent.TimeUnit.MILLISECONDS); }
        //   catch(InterruptedException e) { Thread.currentThread().interrupt(); return false; }
        // OJO: el test pasa un executor ya terminado → awaitTermination devuelve true de inmediato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutorTerminado");
    }

    /**
     * Reto Extra 5: descriptores de fichero — cuenta cuántos ficheros están abiertos (simula).
     * Dado un número "simulado" de ficheros abiertos y un límite del sistema,
     * devuelve el porcentaje de uso de descriptores.
     */
    public static double pctDescriptoresUsados(int abiertos, int limiteMax) {
        // GUÍA: teoría 5.5 (file descriptor exhaustion: too many open files → java.io.IOException).
        // 1. limiteMax <= 0 → -1.
        // 2. Retorna abiertos / (double) limiteMax * 100.
        // OJO: abiertos > limiteMax → puede superar 100 %; no clampar (el valor muestra la violación).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pctDescriptoresUsados");
    }

    /**
     * Reto Extra 6: off-heap — calcula si la memoria directa (off-heap) supera el límite.
     * Dado el uso actual en MB y el límite configurado con -XX:MaxDirectMemorySize,
     * devuelve true si supera el 80 % del límite.
     */
    public static boolean alertaOffHeap(long usadoMB, long limiteMB) {
        // GUÍA: teoría 5.6 (off-heap / direct memory: ByteBuffer.allocateDirect() usa fuera del heap Java).
        // 1. limiteMB <= 0 → false.
        // 2. Devuelve usadoMB > limiteMB * 0.80.
        // OJO: exactamente el 80 % → false (usa > no >=).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para alertaOffHeap");
    }

    /**
     * Reto Extra 7: perfilado básico — calcula el consumo de stack por hilo.
     * Dado el tamaño de stack (-Xss) en KB y el número de hilos, estima el total de stack en MB.
     */
    public static double stackTotalMB(int xssKB, int numHilos) {
        // GUÍA: teoría 5.7 (perfilado: -Xss controla el stack por hilo; muchos hilos = mucho stack).
        // 1. xssKB <= 0 o numHilos <= 0 → -1.
        // 2. Retorna xssKB * numHilos / 1024.0.
        // OJO: resultado en MB (no KB): divide entre 1024.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stackTotalMB");
    }

    /**
     * Reto Extra 8: presupuesto de CPU — dada la carga media de CPU (0..1) y el número de núcleos,
     * calcula el número de núcleos "en uso efectivo" (carga * núcleos).
     */
    public static double nucleosEnUso(double cargaCpu, int nucleos) {
        // GUÍA: teoría 5.8 (presupuesto de CPU: asignar un presupuesto de CPU a cada servicio).
        // 1. cargaCpu fuera de [0,1] o nucleos <= 0 → -1.
        // 2. Retorna cargaCpu * nucleos.
        // OJO: cargaCpu = 0.0 y nucleos = 8 → 0.0 núcleos en uso (válido, no -1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nucleosEnUso");
    }

    /**
     * Reto Extra 9: soak test (larga duración) — dado un historial de muestras de memoria (MB)
     * tomadas cada minuto, detecta si hay tendencia creciente (fuga lenta).
     * Hay fuga si la última muestra supera la primera en más de {@code umbralMB}.
     */
    public static boolean fugaLenta(List<Long> muestrasMemoriaMB, long umbralMB) {
        // GUÍA: teoría 5.9 (soak test: ejecutar 24h–72h para descubrir fugas que tardan en manifestarse).
        // 1. muestras null o size < 2 → false (no hay suficientes datos).
        // 2. Devuelve (ultima - primera) > umbralMB.
        // PISTA: muestras.get(muestras.size()-1) - muestras.get(0) > umbralMB
        // OJO: el test pasa última = primera (sin crecimiento) → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fugaLenta");
    }

    /**
     * Reto Extra 10: enlace con b27 (hilos) y b21 (recursos).
     * Clasifica el estado de salud del sistema en función de memoria y hilos:
     * "OK" si memoria OK y sin fuga de hilos, "MEMORIA" si solo memoria excede,
     * "HILOS" si solo hilos fugan, "CRITICO" si ambos problemas.
     */
    public static String estadoSalud(boolean memoriaExcede, boolean fugaHilos) {
        // GUÍA: teoría 5.10 (health check compuesto: combina múltiples métricas → b21 Actuator /health).
        // 1. Usa un if/else if/else sencillo con las cuatro combinaciones.
        // PISTA: if (!memoriaExcede && !fugaHilos) return "OK"; if (memoriaExcede && fugaHilos) return "CRITICO"; ...
        // OJO: el test cubre los cuatro casos.
        // CULTURA: Spring Boot Actuator (/actuator/health) en b20 hace exactamente esto: agrega múltiples
        //   HealthIndicator y devuelve UP/DOWN/DEGRADED según la combinación → b21 extiende eso con métricas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoSalud");
    }
}
