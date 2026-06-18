package com.masterclass.api.b27_concur;

/**
 * Ejercicio 226 · Prioridades, {@code ThreadLocal}, daemon y contexto de ejecución.
 *
 * <p>Cierre del bloque: detalles del "entorno" de un hilo. La prioridad (una pista al
 * planificador), el {@code ThreadLocal} (una variable con un valor propio por hilo, clave en
 * frameworks web para guardar el usuario/transacción de la petición), los hilos daemon y el
 * manejador de excepciones no capturadas.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.12).
 */
public final class Ej226ThreadPriorityAndContext {

    private Ej226ThreadPriorityAndContext() {
    }

    /**
     * Asigna y recupera la prioridad de un hilo.
     *
     * @param prioridad valor entre 1 y 10
     * @return la prioridad efectiva del hilo
     */
    public static int prioridadSeAlmacena(int prioridad) {
        // TODO 1: crea un Thread con un Runnable vacío.
        // TODO 2: asígnale la prioridad con setPriority(prioridad).
        // TODO 3: recupera con getPriority().
        // TODO 4: (piensa) la prioridad es una PISTA al SO, no garantiza orden de ejecución.
        // TODO 5: devuelve la prioridad obtenida.
        return -1;
    }

    /**
     * Demuestra que un {@code ThreadLocal} tiene un valor independiente por hilo.
     *
     * @return true si dos hilos ven cada uno SU propio valor (no se pisan)
     */
    public static boolean threadLocalAisladoPorHilo() {
        // TODO 6: crea un ThreadLocal<Integer> tl.
        // TODO 7: hilo A: tl.set(1); ...; lee tl.get() y comprueba que es 1; guarda el resultado.
        // TODO 8: hilo B: tl.set(2); ...; lee tl.get() y comprueba que es 2; guarda el resultado.
        // TODO 9: arranca ambos y haz join (usa un pequeño retardo/latch para que se solapen si quieres).
        // TODO 10: devuelve (A vio 1 && B vio 2): cada hilo mantuvo su valor.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("prioridadSeAlmacena(7) = " + prioridadSeAlmacena(7));
        System.out.println("threadLocalAisladoPorHilo() = " + threadLocalAisladoPorHilo());
    }

    /**
     * Reto Extra 1: Hilo daemon configurable.
     * @return true si el hilo creado es daemon
     */
    public static boolean esDaemonConfigurable() {
        // GUÍA: Thread t = new Thread(() -> {}); t.setDaemon(true); return t.isDaemon();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDaemonConfigurable");
    }

    /**
     * Reto Extra 2: {@code ThreadLocal.withInitial}.
     * @return el valor inicial del ThreadLocal sin haber hecho set()
     */
    public static int threadLocalValorInicial(int inicial) {
        // GUÍA: ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> inicial);
        //   return tl.get();   // devuelve 'inicial' la primera vez, sin set previo
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para threadLocalValorInicial");
    }

    /**
     * Reto Extra 3: Nombre personalizado del hilo.
     * @return el nombre asignado, leído desde dentro del propio hilo
     */
    public static String nombrePersonalizado(String nombre) {
        // GUÍA: String[] capt = new String[1];
        //   Thread t = new Thread(() -> capt[0] = Thread.currentThread().getName(), nombre);
        //   t.start(); t.join(); return capt[0];
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombrePersonalizado");
    }

    /**
     * Reto Extra 4: La prioridad máxima es 10.
     * @return Thread.MAX_PRIORITY
     */
    public static int prioridadMaxEsDiez() {
        // GUÍA: return Thread.MAX_PRIORITY;   // constante == 10 (MIN_PRIORITY==1, NORM_PRIORITY==5)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prioridadMaxEsDiez");
    }

    /**
     * Reto Extra 5: {@code UncaughtExceptionHandler}.
     * @return true si el manejador se invoca cuando el hilo lanza una excepción no capturada
     */
    public static boolean uncaughtExceptionHandlerSeInvoca() {
        // GUÍA: AtomicBoolean invocado = new AtomicBoolean(false);
        //   Thread t = new Thread(() -> { throw new RuntimeException("boom"); });
        //   t.setUncaughtExceptionHandler((hilo, ex) -> invocado.set(true));
        //   t.start(); t.join();
        //   return invocado.get();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para uncaughtExceptionHandlerSeInvoca");
    }

    /**
     * Reto Extra 6: {@code InheritableThreadLocal} se hereda al hilo hijo.
     * @return el valor que el hilo hijo lee, heredado del padre
     */
    public static int inheritableThreadLocalHereda(int valor) {
        // GUÍA: InheritableThreadLocal<Integer> itl = new InheritableThreadLocal<>();
        //   itl.set(valor);                       // lo fija el hilo padre (este)
        //   int[] capt = {-1};
        //   Thread hijo = new Thread(() -> capt[0] = itl.get());   // el hijo lo hereda
        //   hijo.start(); hijo.join();
        //   return capt[0];
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inheritableThreadLocalHereda");
    }

    /**
     * Reto Extra 7: {@code ThreadLocal.remove} vuelve al valor inicial.
     * @return el valor inicial tras un set(otro) seguido de remove()
     */
    public static int threadLocalRemoveVuelveAInicial(int inicial) {
        // GUÍA: ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> inicial);
        //   tl.set(inicial + 100);
        //   tl.remove();          // olvida el valor puesto; vuelve a withInitial
        //   return tl.get();      // 'inicial'
        // remove() es CLAVE en pools: si no limpias el ThreadLocal, el siguiente uso del
        // hilo reciclado vería datos de la petición anterior (fuga de contexto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para threadLocalRemoveVuelveAInicial");
    }

    /**
     * Reto Extra 8: {@code ThreadFactory} personalizada.
     * @return true si la factoría crea hilos daemon con el prefijo de nombre esperado
     */
    public static boolean threadFactoryPersonalizada() {
        // GUÍA: ThreadFactory f = r -> { Thread t = new Thread(r, "mi-pool-1"); t.setDaemon(true); return t; };
        //   Thread t = f.newThread(() -> {});
        //   return t.isDaemon() && t.getName().startsWith("mi-pool");
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para threadFactoryPersonalizada");
    }

    /**
     * Reto Extra 9: El id de hilo es positivo.
     * @return el identificador del hilo actual (siempre &gt; 0)
     */
    public static long threadIdEsPositivo() {
        // GUÍA: return Thread.currentThread().threadId();   // (Java 19+) identificador único y positivo
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para threadIdEsPositivo");
    }

    /**
     * Reto Extra 10: Número de procesadores disponibles (contexto de ejecución).
     * @return Runtime.getRuntime().availableProcessors() (&gt;= 1)
     */
    public static int numeroDeProcesadores() {
        // GUÍA: return Runtime.getRuntime().availableProcessors();
        // Es el número que suele usarse para dimensionar un pool de hilos CPU-bound.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeProcesadores");
    }
}
