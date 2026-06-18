package com.masterclass.api.b27_concur;

/**
 * Ejercicio 222 · Coordinadores de {@code java.util.concurrent}:
 * {@code Semaphore}, {@code CountDownLatch}, {@code CyclicBarrier}.
 *
 * <p>Tres herramientas de sincronización de alto nivel:
 * un semáforo limita cuántos hilos hacen algo a la vez; un latch espera a que ocurran
 * N sucesos (de un solo uso); una barrera reúne a N hilos en un punto (reutilizable).
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.8).
 */
public final class Ej222Semaphores {

    private Ej222Semaphores() {
    }

    /**
     * Un {@code Semaphore} de 'permisos' permisos limita la concurrencia: mide el máximo
     * de hilos que estuvieron dentro de la sección a la vez.
     *
     * @param permisos número de permisos del semáforo
     * @param nTareas  número de tareas que compiten
     * @return máximo de tareas simultáneas observado (nunca debe superar 'permisos')
     */
    public static int semaforoLimitaConcurrencia(int permisos, int nTareas) {
        // TODO 1: crea un Semaphore(permisos), un AtomicInteger 'actual' y otro 'max'.
        // TODO 2: cada tarea hace sem.acquire(); incrementa 'actual'; actualiza 'max' al valor mayor visto.
        // TODO 3: cede un instante (no es obligatorio) y luego decrementa 'actual' y sem.release().
        // TODO 4: lanza nTareas en hilos (o un pool) y espera a todas.
        // TODO 5: devuelve max.get() (será <= permisos).
        return -1;
    }

    /**
     * Un {@code CountDownLatch} permite al hilo principal esperar a que N trabajos terminen.
     *
     * @param n número de trabajos
     * @return true si el latch llega a 0 y await() retorna
     */
    public static boolean latchEsperaATodos(int n) {
        // TODO 6: crea un CountDownLatch(n).
        // TODO 7: lanza n hilos; cada uno hace su "trabajo" y al final latch.countDown().
        // TODO 8: en el hilo principal llama a latch.await() (maneja InterruptedException).
        // TODO 9: (piensa) await() no retorna hasta que la cuenta es 0.
        // TODO 10: devuelve true tras pasar el await.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("semaforoLimitaConcurrencia(3, 50) = " + semaforoLimitaConcurrencia(3, 50));
        System.out.println("latchEsperaATodos(10) = " + latchEsperaATodos(10));
    }

    /**
     * Reto Extra 1: Semáforo de 1 permiso == mutex.
     * @return contador exacto usando un Semaphore(1) como exclusión mutua
     */
    public static int semaforoComoMutex(int nHilos, int iteraciones) {
        // GUÍA: Semaphore mutex = new Semaphore(1);
        //   cada incremento: mutex.acquire(); try { c[0]++; } finally { mutex.release(); }
        //   Resultado exacto == nHilos*iteraciones.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para semaforoComoMutex");
    }

    /**
     * Reto Extra 2: El latch es de un solo uso.
     * @return true si tras llegar a 0 el await() siguiente retorna de inmediato
     */
    public static boolean latchEsDeUnSoloUso() {
        // GUÍA: CountDownLatch l = new CountDownLatch(1); l.countDown();
        //   l.await();   // retorna ya; el latch no se "rearma"
        //   l.await();   // sigue retornando al instante
        //   return l.getCount() == 0;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para latchEsDeUnSoloUso");
    }

    /**
     * Reto Extra 3: {@code CyclicBarrier} reúne a N hilos.
     * @return número de hilos que cruzaron la barrera (== nParties)
     */
    public static int cyclicBarrierTodosLlegan(int nParties) {
        // GUÍA: AtomicInteger cruzaron = new AtomicInteger();
        //   CyclicBarrier b = new CyclicBarrier(nParties);
        //   lanza nParties hilos; cada uno: b.await(); cruzaron.incrementAndGet();
        //   join a todos; return cruzaron.get();
        // b.await() no libera a NADIE hasta que han llegado los nParties.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cyclicBarrierTodosLlegan");
    }

    /**
     * Reto Extra 4: {@code tryAcquire} sin permisos disponibles.
     * @return true si tryAcquire() sobre un Semaphore(0) devuelve false
     */
    public static boolean tryAcquireSinPermisos() {
        // GUÍA: Semaphore s = new Semaphore(0); return !s.tryAcquire();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tryAcquireSinPermisos");
    }

    /**
     * Reto Extra 5: La barrera tiene una acción al completarse.
     * @return el número de veces que se ejecutó la barrierAction (== 1 para una sola ronda)
     */
    public static int barrierAction(int nParties) {
        // GUÍA: el 2º argumento de CyclicBarrier es un Runnable que corre UNA vez cuando llegan todos:
        //   AtomicInteger veces = new AtomicInteger();
        //   CyclicBarrier b = new CyclicBarrier(nParties, veces::incrementAndGet);
        //   nParties hilos hacen b.await(); join; return veces.get();  // 1
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para barrierAction");
    }

    /**
     * Reto Extra 6: Latch como "pistola de salida".
     * @return true si N hilos arrancan solo tras un único countDown() de salida
     */
    public static boolean latchComoPistolaDeSalida() {
        // GUÍA: CountDownLatch salida = new CountDownLatch(1);
        //   N hilos: salida.await(); luego registran su arranque (AtomicInteger arrancados).
        //   principal: salida.countDown();  // todos salen "a la vez"
        //   join; return arrancados == N;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para latchComoPistolaDeSalida");
    }

    /**
     * Reto Extra 7: La {@code CyclicBarrier} es reutilizable.
     * @return número total de cruces en 'rondas' rondas (== rondas*nParties)
     */
    public static int cyclicBarrierReutilizable(int rondas, int nParties) {
        // GUÍA: a diferencia del latch, la barrera se rearma sola tras cada disparo. nParties hilos
        // ejecutan un bucle de 'rondas' iteraciones, haciendo b.await() en cada una. Cuenta los cruces.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cyclicBarrierReutilizable");
    }

    /**
     * Reto Extra 8: {@code availablePermits()} refleja los permisos restantes.
     * @return permisos disponibles tras adquirir 2 de 'permisos' (== permisos-2)
     */
    public static int availablePermitsTrasAdquirir(int permisos) {
        // GUÍA: Semaphore s = new Semaphore(permisos);
        //   s.acquire(2);
        //   return s.availablePermits();   // permisos-2
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para availablePermitsTrasAdquirir");
    }

    /**
     * Reto Extra 9: {@code latch.await(timeout)} expira si no se completa.
     * @return true si await con timeout corto devuelve false cuando la cuenta no llega a 0
     */
    public static boolean latchAwaitConTimeoutExpira() {
        // GUÍA: CountDownLatch l = new CountDownLatch(1);   // nadie hace countDown
        //   return l.await(50, TimeUnit.MILLISECONDS) == false;   // expira -> false -> devolvemos true
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para latchAwaitConTimeoutExpira");
    }

    /**
     * Reto Extra 10: Semáforo justo ({@code fair}).
     * @return contador exacto usando new Semaphore(1, true)
     */
    public static int semaforoFair(int nHilos, int iteraciones) {
        // GUÍA: new Semaphore(1, true) concede permisos en orden FIFO. El contador sigue siendo exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para semaforoFair");
    }
}
