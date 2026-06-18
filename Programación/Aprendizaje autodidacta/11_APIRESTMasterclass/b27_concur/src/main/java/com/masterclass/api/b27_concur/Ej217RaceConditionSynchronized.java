package com.masterclass.api.b27_concur;

/**
 * Ejercicio 217 · Condición de carrera y {@code synchronized}.
 *
 * <p>El pecado capital de la concurrencia: {@code i++} NO es atómico (lee, suma, escribe).
 * Si dos hilos lo hacen a la vez, se pierden actualizaciones. Aquí reproduces el bug
 * y lo arreglas con exclusión mutua (el "monitor" de un objeto).
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.3).
 */
public final class Ej217RaceConditionSynchronized {

    private Ej217RaceConditionSynchronized() {
    }

    // Campo de apoyo para el reto de 'volatile' (debe ser de instancia/estático; no hay locales volatile).
    private static volatile int contadorVolatil;
    // Campo y monitor de apoyo para el reto del método synchronized.
    private static int contadorMetodo;

    private static synchronized void incrementarMetodo() {
        contadorMetodo++;
    }

    /**
     * Incrementa un contador compartido SIN sincronizar: demuestra la condición de carrera.
     *
     * @param nHilos      número de hilos
     * @param iteraciones incrementos por hilo
     * @return valor final (será &lt;= nHilos*iteraciones por culpa de las actualizaciones perdidas)
     */
    public static int contadorSinSync(int nHilos, int iteraciones) {
        // TODO 1: crea un contenedor compartido int[] cont = {0}.
        // TODO 2: crea nHilos Thread; cada uno hace 'iteraciones' veces cont[0]++ (SIN protección).
        // TODO 3: arranca TODOS los hilos.
        // TODO 4: haz join() a todos (maneja InterruptedException).
        // TODO 5: devuelve cont[0] (normalmente menor que el total esperado).
        return -1;
    }

    /**
     * Igual que el anterior pero protegiendo el incremento con un bloque {@code synchronized}.
     *
     * @param nHilos      número de hilos
     * @param iteraciones incrementos por hilo
     * @return valor final EXACTO (== nHilos*iteraciones)
     */
    public static int contadorConSync(int nHilos, int iteraciones) {
        // TODO 6: crea el contenedor int[] cont = {0} y un Object 'cerrojo' = new Object().
        // TODO 7: en cada hilo, el incremento va dentro de synchronized (cerrojo) { cont[0]++; }.
        // TODO 8: arranca todos los hilos.
        // TODO 9: haz join() a todos.
        // TODO 10: devuelve cont[0]; ahora sí es exacto porque el monitor serializa el acceso.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("sinSync(4,100000) = " + contadorSinSync(4, 100_000) + " (esperado 400000)");
        System.out.println("conSync(4,100000) = " + contadorConSync(4, 100_000));
    }

    private static int lanzarYContar(int nHilos, Runnable porHilo) {
        // helper de apoyo opcional; los retos pueden usarlo o crear sus hilos a mano.
        Thread[] ts = new Thread[nHilos];
        for (int i = 0; i < nHilos; i++) {
            ts[i] = new Thread(porHilo);
            ts[i].start();
        }
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
        return nHilos;
    }

    /**
     * Reto Extra 1: Método {@code synchronized}.
     * @return contador exacto usando un método estático synchronized
     */
    public static int conSynchronizedMetodo(int nHilos, int iteraciones) {
        // GUÍA: un método synchronized bloquea el monitor de la clase (estático) o del objeto.
        //   contadorMetodo = 0;
        //   lanzarYContar(nHilos, () -> { for (int i=0;i<iteraciones;i++) incrementarMetodo(); });
        //   return contadorMetodo;
        // 'incrementarMetodo' ya está declarado synchronized arriba.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conSynchronizedMetodo");
    }

    /**
     * Reto Extra 2: {@code AtomicInteger}.
     * @return contador exacto usando incrementAndGet()
     */
    public static int conAtomicInteger(int nHilos, int iteraciones) {
        // GUÍA: AtomicInteger c = new AtomicInteger();
        //   lanzarYContar(nHilos, () -> { for (int i=0;i<iteraciones;i++) c.incrementAndGet(); });
        //   return c.get();
        // Atómico = sin lock explícito, usando instrucciones CAS del procesador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conAtomicInteger");
    }

    /**
     * Reto Extra 3: {@code ReentrantLock}.
     * @return contador exacto usando lock()/unlock() en try-finally
     */
    public static int conReentrantLock(int nHilos, int iteraciones) {
        // GUÍA: ReentrantLock lock = new ReentrantLock(); int[] c = {0};
        //   lanzarYContar(nHilos, () -> { for (int i=0;i<iteraciones;i++){ lock.lock(); try { c[0]++; } finally { lock.unlock(); } } });
        //   return c[0];
        // SIEMPRE unlock() en finally: si saltara una excepción, el lock quedaría tomado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conReentrantLock");
    }

    /**
     * Reto Extra 4: Sincronizar lo mínimo (incremento local + suma única).
     * @return contador exacto, reduciendo la contención
     */
    public static int sincronizarLoMinimo(int nHilos, int iteraciones) {
        // GUÍA: cada hilo cuenta en una variable LOCAL (sin contención) y solo al final
        // suma su parcial bajo el cerrojo UNA vez:
        //   int[] total = {0}; Object cerrojo = new Object();
        //   lanzarYContar(nHilos, () -> {
        //       int local = 0; for (int i=0;i<iteraciones;i++) local++;
        //       synchronized (cerrojo) { total[0] += local; }
        //   });
        //   return total[0];
        // Misma exactitud, muchísima menos sincronización.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sincronizarLoMinimo");
    }

    /**
     * Reto Extra 5: {@code volatile} NO basta para incrementar.
     * volatile garantiza VISIBILIDAD pero no ATOMICIDAD del compuesto i++.
     * @return valor final (será &lt;= nHilos*iteraciones, demostrando que volatile no protege i++)
     */
    public static int volatileNoBastaParaIncrementar(int nHilos, int iteraciones) {
        // GUÍA: contadorVolatil = 0;
        //   lanzarYContar(nHilos, () -> { for (int i=0;i<iteraciones;i++) contadorVolatil++; });
        //   return contadorVolatil;   // suele perder actualizaciones igual que sinSync
        // volatile sirve para una bandera (leer/escribir), NO para read-modify-write.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volatileNoBastaParaIncrementar");
    }

    /**
     * Reto Extra 6: Suma particionada de un array con acumulador protegido.
     * @return suma de todos los valores (exacta)
     */
    public static long sumaParticionadaSync(long[] valores, int nHilos) {
        // GUÍA: reparte el array en nHilos tramos; cada hilo suma SU tramo en local y
        // añade el parcial al total bajo un cerrojo:
        //   long[] total = {0}; Object cerrojo = new Object();
        //   crea nHilos hilos con índices [desde,hasta) repartidos; cada uno:
        //       long parcial = 0; for (int k=desde;k<hasta;k++) parcial += valores[k];
        //       synchronized (cerrojo) { total[0] += parcial; }
        //   join a todos; return total[0];
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaParticionadaSync");
    }

    /**
     * Reto Extra 7: Dos cerrojos distintos NO ofrecen exclusión.
     * @return valor final (&lt;= 2*iteraciones): cada hilo usa su PROPIO monitor, así que no se excluyen
     */
    public static int locksDistintosNoProtegen(int iteraciones) {
        // GUÍA: int[] c = {0}; Object lockA = new Object(); Object lockB = new Object();
        //   Thread a = new Thread(() -> { for (int i=0;i<iteraciones;i++) synchronized(lockA){ c[0]++; } });
        //   Thread b = new Thread(() -> { for (int i=0;i<iteraciones;i++) synchronized(lockB){ c[0]++; } });
        //   a.start(); b.start(); a.join(); b.join();
        //   return c[0];
        // ERROR clásico: para proteger un dato compartido, TODOS deben usar EL MISMO cerrojo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para locksDistintosNoProtegen");
    }

    /**
     * Reto Extra 8: Bloque {@code synchronized} sobre objeto dedicado.
     * @return contador exacto usando synchronized(cerrojo) con un Object privado
     */
    public static int conObjetoDedicado(int nHilos, int iteraciones) {
        // GUÍA: es buena práctica bloquear sobre un Object 'private final' dedicado en vez de
        // sobre 'this' (evita que código externo bloquee tu monitor):
        //   final Object cerrojo = new Object(); int[] c = {0};
        //   lanzarYContar(nHilos, () -> { for (int i=0;i<iteraciones;i++) synchronized(cerrojo){ c[0]++; } });
        //   return c[0];
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conObjetoDedicado");
    }

    /**
     * Reto Extra 9: Propiedad de cota del caso sin sincronizar.
     * Ejecuta la versión sin sincronizar y verifica que nunca SUPERA el total esperado.
     * @return true si el resultado sin sync es &lt;= nHilos*iteraciones
     */
    public static boolean sinSyncNuncaSuperaElTotal(int nHilos, int iteraciones) {
        // GUÍA: las carreras PIERDEN incrementos, nunca los inventan. Por tanto el resultado
        // siempre cumple resultado <= total:
        //   int r = contadorSinSync(nHilos, iteraciones);
        //   return r <= (long) nHilos * iteraciones;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sinSyncNuncaSuperaElTotal");
    }

    /**
     * Reto Extra 10: Solución canónica recomendada.
     * @return contador exacto con AtomicInteger (la respuesta que darías en producción)
     */
    public static int solucionCanonica(int nHilos, int iteraciones) {
        // GUÍA: para un contador, AtomicInteger es la elección por defecto: correcto y sin locks.
        //   (idéntico a conAtomicInteger; este reto fija la "respuesta modelo").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para solucionCanonica");
    }
}
