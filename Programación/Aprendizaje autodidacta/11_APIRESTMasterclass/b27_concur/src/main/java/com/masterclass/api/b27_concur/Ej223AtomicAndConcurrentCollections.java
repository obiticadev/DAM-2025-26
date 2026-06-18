package com.masterclass.api.b27_concur;

import java.util.List;

/**
 * Ejercicio 223 · Atómicos y colecciones concurrentes.
 *
 * <p>La forma idiomática de compartir estado sin {@code synchronized}: variables atómicas
 * ({@code AtomicInteger}, {@code LongAdder}, {@code AtomicReference}) y colecciones
 * thread-safe ({@code ConcurrentHashMap}, {@code BlockingQueue},
 * {@code CopyOnWriteArrayList}, {@code ConcurrentLinkedQueue}).
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.9).
 */
public final class Ej223AtomicAndConcurrentCollections {

    private Ej223AtomicAndConcurrentCollections() {
    }

    /**
     * Contador con {@code AtomicInteger.incrementAndGet()} (sin locks).
     *
     * @param nHilos      número de hilos
     * @param iteraciones incrementos por hilo
     * @return contador exacto (== nHilos*iteraciones)
     */
    public static int atomicIncrement(int nHilos, int iteraciones) {
        // TODO 1: crea un AtomicInteger.
        // TODO 2: cada hilo hace 'iteraciones' veces incrementAndGet().
        // TODO 3: arranca todos los hilos.
        // TODO 4: join a todos.
        // TODO 5: devuelve el valor con get().
        return -1;
    }

    /**
     * Conteo concurrente sobre una clave de un {@code ConcurrentHashMap} con merge.
     *
     * @param nHilos  número de hilos
     * @param porHilo incrementos por hilo sobre la misma clave
     * @return cuenta final asociada a la clave (== nHilos*porHilo)
     */
    public static int concurrentMapCuentaClave(int nHilos, int porHilo) {
        // TODO 6: crea un ConcurrentHashMap<String,Integer> y usa la clave "k".
        // TODO 7: cada hilo hace 'porHilo' veces map.merge("k", 1, Integer::sum).
        // TODO 8: arranca los hilos.
        // TODO 9: join a todos.
        // TODO 10: devuelve map.get("k").
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("atomicIncrement(4,100000) = " + atomicIncrement(4, 100_000));
        System.out.println("concurrentMapCuentaClave(4,100000) = " + concurrentMapCuentaClave(4, 100_000));
    }

    /**
     * Reto Extra 1: {@code LongAdder} (mejor que AtomicLong bajo alta contención).
     * @return suma exacta (== nHilos*iteraciones)
     */
    public static long longAdderSuma(int nHilos, int iteraciones) {
        // GUÍA: LongAdder a = new LongAdder();
        //   cada hilo: 'iteraciones' veces a.increment();  ->  a.sum()
        // LongAdder reparte el conteo en celdas internas: menos contención que AtomicLong.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longAdderSuma");
    }

    /**
     * Reto Extra 2: Bucle {@code compareAndSet} (CAS).
     * @return 'objetivo' alcanzado incrementando con un bucle CAS
     */
    public static int compareAndSetLoop(int objetivo) {
        // GUÍA: AtomicInteger v = new AtomicInteger(0);
        //   while (v.get() < objetivo) { int x = v.get(); v.compareAndSet(x, x+1); }
        //   return v.get();
        // compareAndSet(esperado, nuevo) solo escribe si el valor sigue siendo 'esperado'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compareAndSetLoop");
    }

    /**
     * Reto Extra 3: {@code updateAndGet} con una función.
     * @return base + delta usando updateAndGet
     */
    public static int updateAndGet(int base, int delta) {
        // GUÍA: AtomicInteger v = new AtomicInteger(base);
        //   return v.updateAndGet(x -> x + delta);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para updateAndGet");
    }

    /**
     * Reto Extra 4: {@code computeIfAbsent} con claves distintas desde varios hilos.
     * @return número de claves distintas en el mapa (== nClaves)
     */
    public static int computeIfAbsentClavesDistintas(int nClaves) {
        // GUÍA: ConcurrentHashMap<Integer,Integer> m = new ConcurrentHashMap<>();
        //   lanza nClaves hilos; el hilo i hace m.computeIfAbsent(i, k -> k*k);
        //   join; return m.size();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para computeIfAbsentClavesDistintas");
    }

    /**
     * Reto Extra 5: {@code BlockingQueue} (put/take) transfiere sin perder elementos.
     * @return elementos consumidos (== n)
     */
    public static int blockingQueuePutTake(int n) {
        // GUÍA: BlockingQueue<Integer> q = new ArrayBlockingQueue<>(16);
        //   productor: for i in 1..n q.put(i);   consumidor: for i in 1..n q.take(); cuenta++;
        //   put/take ya bloquean por ti (es el Ej218 ya resuelto por la librería). return cuenta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para blockingQueuePutTake");
    }

    /**
     * Reto Extra 6: {@code CopyOnWriteArrayList} desde varios hilos.
     * @return tamaño final de la lista (== nHilos*porHilo)
     */
    public static int copyOnWriteAddDesdeHilos(int nHilos, int porHilo) {
        // GUÍA: List<Integer> lista = new CopyOnWriteArrayList<>();
        //   cada hilo hace 'porHilo' veces lista.add(1);  join; return lista.size();
        // CopyOnWrite copia el array en cada escritura: ideal para muchas lecturas, pocas escrituras.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copyOnWriteAddDesdeHilos");
    }

    /**
     * Reto Extra 7: {@code AtomicReference.compareAndSet}.
     * @return true si se sustituye el valor esperado por uno nuevo con CAS
     */
    public static boolean atomicReferenceCAS() {
        // GUÍA: AtomicReference<String> ref = new AtomicReference<>("viejo");
        //   boolean ok = ref.compareAndSet("viejo", "nuevo");
        //   return ok && ref.get().equals("nuevo");
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para atomicReferenceCAS");
    }

    /**
     * Reto Extra 8: {@code ConcurrentLinkedQueue} (no bloqueante) offer/poll.
     * @return número de elementos extraídos (== n)
     */
    public static int concurrentLinkedQueueOfferPoll(int n) {
        // GUÍA: Queue<Integer> q = new ConcurrentLinkedQueue<>();
        //   for i in 1..n q.offer(i);   int c=0; while (q.poll()!=null) c++;   return c;
        // offer/poll no bloquean: devuelven de inmediato (poll da null si está vacía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concurrentLinkedQueueOfferPoll");
    }

    /**
     * Reto Extra 9: LongAdder y AtomicLong dan el mismo resultado.
     * @return el valor común (== nHilos*iteraciones)
     */
    public static long adderVsAtomicMismoResultado(int nHilos, int iteraciones) {
        // GUÍA: calcula el total con un AtomicLong y con un LongAdder en sendas pasadas;
        //   comprueba que coinciden y devuelve ese valor (si no coinciden, devuelve -1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para adderVsAtomicMismoResultado");
    }

    /**
     * Reto Extra 10: Conteo concurrente de palabras (map-reduce básico).
     * @return número de palabras DISTINTAS contadas en el ConcurrentHashMap
     */
    public static int cuentaPalabrasDistintas(List<String> palabras) {
        // GUÍA: ConcurrentHashMap<String,Integer> conteo = new ConcurrentHashMap<>();
        //   recorre 'palabras' en paralelo (p.ej. palabras.parallelStream().forEach o varios hilos)
        //   haciendo conteo.merge(p, 1, Integer::sum);   return conteo.size();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cuentaPalabrasDistintas");
    }
}
