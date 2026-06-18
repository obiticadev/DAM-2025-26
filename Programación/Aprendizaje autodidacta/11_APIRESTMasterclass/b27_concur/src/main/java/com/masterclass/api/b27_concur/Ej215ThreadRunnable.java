package com.masterclass.api.b27_concur;

import java.util.List;

/**
 * Ejercicio 215 · {@code Thread} vs {@code Runnable}: crear, arrancar y esperar hilos.
 *
 * <p>Primer contacto serio con la concurrencia: la diferencia entre {@code start()}
 * (lanza un hilo nuevo) y {@code run()} (ejecuta en el hilo llamador), y {@code join()}
 * para esperar a que termine.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.1).
 */
public final class Ej215ThreadRunnable {

    private Ej215ThreadRunnable() {
    }

    /**
     * Calcula la suma 1..n DENTRO de un hilo nuevo y la devuelve al hilo llamador.
     *
     * @param n límite superior (incluido); si n &lt;= 0 la suma es 0
     * @return suma de 1 hasta n
     */
    public static long sumarEnHilo(long n) {
        // TODO 1: crea un contenedor mutable para el resultado, p.ej. long[] holder = {0}.
        // TODO 2: define un Runnable (lambda) que recorra de 1 a n y acumule en holder[0].
        // TODO 3: crea un Thread con ese Runnable y arráncalo con start() (NO run()).
        // TODO 4: espera a que termine con join() (captura InterruptedException; si salta,
        //         restaura el flag con Thread.currentThread().interrupt() y lanza IllegalStateException).
        // TODO 5: devuelve holder[0].
        return -1;
    }

    /**
     * Lanza un hilo por cada tarea, espera a TODAS y devuelve cuántas se ejecutaron.
     *
     * @param tareas lista de tareas a ejecutar en paralelo
     * @return número de tareas completadas (== tareas.size())
     */
    public static int ejecutarTareas(List<Runnable> tareas) {
        // TODO 6: crea un AtomicInteger contador a 0.
        // TODO 7: por cada tarea, crea un Thread que ejecute la tarea y LUEGO haga contador.incrementAndGet();
        //         guarda los Thread en una lista y arráncalos todos.
        // TODO 8: recorre la lista de Thread y haz join() a cada uno (maneja InterruptedException).
        // TODO 9: (piensa) si hicieras join dentro del mismo bucle donde arrancas, ¿seguirían en paralelo? No.
        // TODO 10: devuelve contador.get().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("sumarEnHilo(100) = " + sumarEnHilo(100));
        System.out.println("ejecutarTareas(3 tareas) = "
                + ejecutarTareas(List.of(() -> {}, () -> {}, () -> {})));
    }

    /**
     * Reto Extra 1: Suma con holder de un solo elemento.
     * Igual que {@link #sumarEnHilo(long)} pero usando explícitamente un {@code long[]} de tamaño 1.
     *
     * @param n límite superior incluido
     * @return suma 1..n
     */
    public static long sumaConHolder(long n) {
        // GUÍA: un Runnable no devuelve valor, así que el "canal de retorno" clásico es
        // una variable capturada mutable. Las locales capturadas por una lambda deben ser
        // (efectivamente) finales, pero el CONTENIDO de un array sí se puede mutar:
        //   long[] h = {0}; Runnable r = () -> { for (long i=1;i<=n;i++) h[0]+=i; };
        //   Thread t = new Thread(r); t.start(); t.join(); return h[0];
        // El test espera 5050 para n=100.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaConHolder");
    }

    /**
     * Reto Extra 2: Nombrar un hilo.
     * Crea un hilo con el nombre dado, arráncalo y devuelve el nombre que el propio hilo reporta.
     *
     * @param nombre nombre a asignar
     * @return nombre del hilo (debe coincidir con el parámetro)
     */
    public static String nombreDelHilo(String nombre) {
        // GUÍA: String[] capt = new String[1];
        //   Thread t = new Thread(() -> capt[0] = Thread.currentThread().getName(), nombre);
        //   t.start(); t.join(); return capt[0];
        // new Thread(runnable, "nombre") asigna el nombre. Útil en logs/depuración.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreDelHilo");
    }

    /**
     * Reto Extra 3: {@code run()} NO crea un hilo.
     * Demuestra que invocar {@code run()} directamente ejecuta en el hilo llamador.
     *
     * @return true si el Runnable corrió en el hilo actual (main), no en uno nuevo
     */
    public static boolean runEjecutaEnHiloLlamador() {
        // GUÍA: el error nº1 del novato: llamar a run() en vez de start().
        //   Thread actual = Thread.currentThread();
        //   boolean[] mismo = {false};
        //   Thread t = new Thread(() -> mismo[0] = (Thread.currentThread() == actual));
        //   t.run();   // OJO: run(), no start() -> se ejecuta AQUÍ, no en otro hilo
        //   return mismo[0];   // true: corrió en el hilo llamador
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para runEjecutaEnHiloLlamador");
    }

    /**
     * Reto Extra 4: Varios hilos incrementando un contador atómico.
     * Lanza {@code nHilos} hilos, cada uno hace un único {@code incrementAndGet()}, y espera a todos.
     *
     * @param nHilos número de hilos a lanzar
     * @return valor final del contador (== nHilos)
     */
    public static int contarConVariosHilos(int nHilos) {
        // GUÍA: AtomicInteger evita la condición de carrera (eso se ve en Ej217/Ej223).
        //   AtomicInteger c = new AtomicInteger();
        //   List<Thread> ts = new ArrayList<>();
        //   for (int i=0;i<nHilos;i++){ Thread t=new Thread(c::incrementAndGet); ts.add(t); t.start(); }
        //   for (Thread t: ts) t.join();
        //   return c.get();
        // Arranca TODOS antes de hacer join a ninguno (si no, serializas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConVariosHilos");
    }

    /**
     * Reto Extra 5: Ciclo de vida con {@code isAlive()}.
     * Un hilo bloqueado en un latch está vivo; tras liberarlo y hacer join, ya no.
     *
     * @return true si isAlive() era true mientras corría y false tras terminar
     */
    public static boolean isAliveCicloDeVida() {
        // GUÍA: usa un CountDownLatch para CONTROLAR el momento, sin sleeps a ciegas:
        //   CountDownLatch puerta = new CountDownLatch(1);
        //   Thread t = new Thread(() -> { try { puerta.await(); } catch (InterruptedException e) {} });
        //   t.start();
        //   boolean vivoDurante = t.isAlive();   // está esperando -> true
        //   puerta.countDown();                  // lo dejamos terminar
        //   t.join();
        //   boolean vivoDespues = t.isAlive();   // terminado -> false
        //   return vivoDurante && !vivoDespues;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isAliveCicloDeVida");
    }

    /**
     * Reto Extra 6: Interrumpir un hilo en bucle.
     * Un hilo gira en un bucle hasta ser interrumpido; comprueba que se detiene.
     *
     * @return true si el hilo detectó la interrupción y paró
     */
    public static boolean interrumpirHilo() {
        // GUÍA: interrupt() NO mata el hilo: le levanta una bandera que el hilo debe consultar.
        //   AtomicBoolean paro = new AtomicBoolean(false);
        //   Thread t = new Thread(() -> {
        //       while (!Thread.currentThread().isInterrupted()) { /* girar */ }
        //       paro.set(true);
        //   });
        //   t.start();
        //   t.interrupt();   // pide parar
        //   t.join();
        //   return paro.get();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interrumpirHilo");
    }

    /**
     * Reto Extra 7: Prioridad de un hilo.
     * Crea un hilo, asígnale la prioridad indicada y devuelve la prioridad efectiva.
     *
     * @param prioridad valor entre Thread.MIN_PRIORITY (1) y Thread.MAX_PRIORITY (10)
     * @return prioridad del hilo
     */
    public static int prioridadDelHilo(int prioridad) {
        // GUÍA: Thread t = new Thread(() -> {});
        //   t.setPriority(prioridad);
        //   return t.getPriority();
        // La prioridad es una PISTA al planificador del SO, no una garantía. El test
        // solo comprueba que se almacena (5 -> 5). No hace falta start().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prioridadDelHilo");
    }

    /**
     * Reto Extra 8: Hilo daemon.
     * Crea un hilo daemon y confirma su condición.
     *
     * @return true si el hilo es daemon
     */
    public static boolean esDaemon() {
        // GUÍA: un hilo daemon NO impide que la JVM termine (p.ej. el GC).
        //   Thread t = new Thread(() -> {});
        //   t.setDaemon(true);   // hay que hacerlo ANTES de start()
        //   return t.isDaemon();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDaemon");
    }

    /**
     * Reto Extra 9: {@code join} esperando un resultado diferido.
     * Un hilo "calcula" tras un pequeño retardo controlado por latch; recoge su resultado.
     *
     * @return el valor producido por el hilo (42)
     */
    public static long joinConResultado() {
        // GUÍA: long[] r = {0};
        //   CountDownLatch listo = new CountDownLatch(1);
        //   Thread t = new Thread(() -> { r[0] = 42; listo.countDown(); });
        //   t.start();
        //   t.join();                 // tras join, r[0] ya está visible (join establece happens-before)
        //   return r[0];
        // CLAVE de memoria: join() garantiza que ves lo que el hilo escribió (visibilidad).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para joinConResultado");
    }

    /**
     * Reto Extra 10: Lanzar N hilos y esperar a todos.
     * Crea {@code n} hilos que suman 1 cada uno a un acumulador atómico; espera a todos.
     *
     * @param n número de hilos
     * @return suma total (== n)
     */
    public static int lanzarYEsperarTodos(int n) {
        // GUÍA: patrón fork-join manual (lo automatiza ExecutorService en Ej219):
        //   AtomicInteger acc = new AtomicInteger();
        //   Thread[] hilos = new Thread[n];
        //   for (int i=0;i<n;i++){ hilos[i]=new Thread(acc::incrementAndGet); hilos[i].start(); }
        //   for (Thread h: hilos) h.join();
        //   return acc.get();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lanzarYEsperarTodos");
    }
}
