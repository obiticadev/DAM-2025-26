package com.masterclass.api.b27_concur;

/**
 * Ejercicio 221 · {@code Lock} explícito: {@code ReentrantLock}, {@code tryLock},
 * {@code ReadWriteLock} y {@code Condition}.
 *
 * <p>{@code synchronized} es cómodo pero rígido. Los {@code Lock} de
 * {@code java.util.concurrent.locks} permiten intentar adquirir sin bloquear
 * ({@code tryLock}), con timeout, de forma interrumpible, separar lecturas de
 * escrituras y usar varias {@code Condition} sobre un mismo cerrojo.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.7).
 */
public final class Ej221Locks {

    private Ej221Locks() {
    }

    /**
     * Contador protegido con un {@code ReentrantLock} (lock/unlock en try-finally).
     *
     * @param nHilos      número de hilos
     * @param iteraciones incrementos por hilo
     * @return contador exacto (== nHilos*iteraciones)
     */
    public static int contadorConLock(int nHilos, int iteraciones) {
        // TODO 1: crea un ReentrantLock y un contenedor int[] c = {0}.
        // TODO 2: cada hilo, 'iteraciones' veces: lock.lock(); try { c[0]++; } finally { lock.unlock(); }
        // TODO 3: arranca los nHilos hilos.
        // TODO 4: haz join() a todos.
        // TODO 5: devuelve c[0].
        return -1;
    }

    /**
     * Demuestra que {@code tryLock()} no bloquea: si el cerrojo está tomado, devuelve false.
     *
     * @return true si un segundo hilo NO logra el lock mientras otro lo retiene
     */
    public static boolean tryLockEvitaBloqueo() {
        // TODO 6: crea un ReentrantLock y un CountDownLatch 'retenido'(1) y 'suelta'(1).
        // TODO 7: hilo A: lock.lock(); retenido.countDown(); suelta.await(); lock.unlock();
        // TODO 8: en el hilo principal espera 'retenido' y prueba boolean ok = lock.tryLock();
        //         (debe ser false porque A lo tiene). Si por error fuera true, haz unlock.
        // TODO 9: libera A con suelta.countDown() y haz join.
        // TODO 10: devuelve !ok (true cuando tryLock falló correctamente).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("contadorConLock(4,100000) = " + contadorConLock(4, 100_000));
        System.out.println("tryLockEvitaBloqueo() = " + tryLockEvitaBloqueo());
    }

    /**
     * Reto Extra 1: Reentrada.
     * @return true si el mismo hilo puede adquirir el lock que ya posee (reentrante)
     */
    public static boolean reentrante() {
        // GUÍA: ReentrantLock lock = new ReentrantLock();
        //   lock.lock(); lock.lock();   // el MISMO hilo entra dos veces sin bloquearse
        //   try { return lock.getHoldCount() == 2; } finally { lock.unlock(); lock.unlock(); }
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reentrante");
    }

    /**
     * Reto Extra 2: {@code ReadWriteLock} (muchos lectores, un escritor).
     * @return el valor escrito por el escritor y leído por los lectores
     */
    public static int readWriteLockLectoresYEscritor(int valor) {
        // GUÍA: ReadWriteLock rw = new ReentrantReadWriteLock(); int[] dato = {0};
        //   escritor: rw.writeLock().lock(); try { dato[0]=valor; } finally { rw.writeLock().unlock(); }
        //   lectores: rw.readLock().lock(); try { leer dato[0]; } finally { rw.readLock().unlock(); }
        //   Ejecuta primero el escritor (join), luego varios lectores; devuelve dato[0].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para readWriteLockLectoresYEscritor");
    }

    /**
     * Reto Extra 3: {@code tryLock(timeout)}.
     * @return true si tryLock con timeout corto falla mientras otro hilo retiene el cerrojo
     */
    public static boolean tryLockConTimeout() {
        // GUÍA: como tryLockEvitaBloqueo pero con boolean ok = lock.tryLock(50, TimeUnit.MILLISECONDS);
        //   (maneja InterruptedException). Debe dar false; devuelve !ok.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tryLockConTimeout");
    }

    /**
     * Reto Extra 4: Productor-consumidor con {@code Condition}.
     * @return elementos transferidos (== nElementos)
     */
    public static int conditionProductorConsumidor(int nElementos) {
        // GUÍA: un Lock con DOS Condition (noLleno, noVacio) es la versión "moderna" de wait/notify:
        //   lock.lock(); try { while(lleno) noLleno.await(); ... noVacio.signal(); } finally { unlock; }
        //   Implementa una cola de capacidad pequeña; un productor mete 1..n y un consumidor saca n.
        //   Devuelve cuántos consumió.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conditionProductorConsumidor");
    }

    /**
     * Reto Extra 5: {@code unlock()} en finally libera aunque el cuerpo lance.
     * @return true si tras una excepción dentro de la sección crítica el lock queda libre
     */
    public static boolean unlockEnFinally() {
        // GUÍA: ReentrantLock lock = new ReentrantLock();
        //   try { lock.lock(); try { throw new RuntimeException("boom"); } finally { lock.unlock(); } }
        //   catch (RuntimeException ignored) {}
        //   boolean libre = lock.tryLock();   // si finally hizo su trabajo, ahora se puede tomar
        //   if (libre) lock.unlock();
        //   return libre;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unlockEnFinally");
    }

    /**
     * Reto Extra 6: {@code isLocked()} refleja el estado.
     * @return true si isLocked() es true mientras otro hilo retiene el cerrojo y false al soltarlo
     */
    public static boolean isLockedRefleja() {
        // GUÍA: usa latches como en tryLockEvitaBloqueo; mientras A lo retiene, lock.isLocked()==true;
        //   tras soltar y join, ==false. Devuelve (vivoTrue && libreFalse).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isLockedRefleja");
    }

    /**
     * Reto Extra 7: Lock justo ({@code fair}).
     * @return contador exacto usando new ReentrantLock(true)
     */
    public static int fairLock(int nHilos, int iteraciones) {
        // GUÍA: new ReentrantLock(true) concede el lock por orden de llegada (menos inanición,
        // algo más lento). El resultado del contador sigue siendo exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fairLock");
    }

    /**
     * Reto Extra 8: {@code lockInterruptibly()} responde a la interrupción.
     * @return true si un hilo esperando en lockInterruptibly() sale al ser interrumpido
     */
    public static boolean lockInterruptibly() {
        // GUÍA: A retiene el lock; B hace lock.lockInterruptibly() y queda esperando; interrumpimos B;
        //   B captura InterruptedException -> marca un flag. Devuelve ese flag.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lockInterruptibly");
    }

    /**
     * Reto Extra 9: {@code getHoldCount()} cuenta la reentrada.
     * @return el número de veces que el hilo ha tomado el lock (== veces)
     */
    public static int getHoldCount(int veces) {
        // GUÍA: ReentrantLock lock = new ReentrantLock();
        //   for (int i=0;i<veces;i++) lock.lock();
        //   int n = lock.getHoldCount();
        //   for (int i=0;i<veces;i++) lock.unlock();
        //   return n;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getHoldCount");
    }

    /**
     * Reto Extra 10: {@code await}/{@code signal} con Condition transfiere el control.
     * @return true si un hilo en await() progresa tras el signal() de otro
     */
    public static boolean conditionAwaitSignal() {
        // GUÍA: Lock lock = new ReentrantLock(); Condition c = lock.newCondition();
        //   boolean[] avanzo = {false};
        //   hilo: lock.lock(); try { c.await(); avanzo[0]=true; } finally { lock.unlock(); }
        //   principal: espera a que el hilo esté en WAITING, luego lock.lock(); try { c.signal(); } finally { unlock; }
        //   join; return avanzo[0];
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conditionAwaitSignal");
    }
}
