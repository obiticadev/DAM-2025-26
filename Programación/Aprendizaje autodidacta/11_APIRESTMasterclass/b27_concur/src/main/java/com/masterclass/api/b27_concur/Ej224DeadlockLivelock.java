package com.masterclass.api.b27_concur;

/**
 * Ejercicio 224 · Interbloqueo (deadlock) y cómo evitarlo.
 *
 * <p>El deadlock aparece cuando dos hilos retienen un recurso y esperan el del otro, en
 * orden inverso: nadie cede y ambos quedan bloqueados para siempre. La cura clásica es
 * adquirir SIEMPRE los cerrojos en el mismo orden global.
 *
 * <p>Para provocarlo de forma determinista se usan latches (cada hilo retiene su primer
 * cerrojo ANTES de pedir el segundo) e hilos {@code daemon} (para que no impidan terminar).
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.10).
 */
public final class Ej224DeadlockLivelock {

    private Ej224DeadlockLivelock() {
    }

    /**
     * Provoca un deadlock determinista y confirma que ocurre.
     *
     * @return true si ambos hilos quedan bloqueados (deadlock reproducido)
     */
    public static boolean provocarDeadlock() {
        // TODO 1: crea dos ReentrantLock a y b, y un CountDownLatch ambosListos(2).
        // TODO 2: hilo 1 (daemon): a.lock(); ambosListos.countDown(); ambosListos.await(); b.lock(); (b.unlock; a.unlock).
        // TODO 3: hilo 2 (daemon): b.lock(); ambosListos.countDown(); ambosListos.await(); a.lock(); (a.unlock; b.unlock).
        //         IMPORTANTE: ordenes INVERSOS (1: a->b ; 2: b->a) y setDaemon(true) en ambos.
        // TODO 4: arranca ambos y haz join(500) a cada uno (no esperes para siempre).
        // TODO 5: devuelve (t1.isAlive() && t2.isAlive()): siguen vivos == deadlock.
        return false;
    }

    /**
     * Misma situación pero adquiriendo los cerrojos en orden consistente: NO hay deadlock.
     *
     * @return true si ambos hilos terminan (no hubo interbloqueo)
     */
    public static boolean evitarDeadlockOrdenando() {
        // TODO 6: crea dos ReentrantLock a y b.
        // TODO 7: AMBOS hilos adquieren primero a y luego b (mismo orden global).
        // TODO 8: cada hilo: a.lock(); b.lock(); ...trabajo...; b.unlock(); a.unlock().
        // TODO 9: arranca ambos y haz join(2000) a cada uno.
        // TODO 10: devuelve (!t1.isAlive() && !t2.isAlive()): ambos terminaron.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("provocarDeadlock() = " + provocarDeadlock());
        System.out.println("evitarDeadlockOrdenando() = " + evitarDeadlockOrdenando());
    }

    /**
     * Reto Extra 1: Orden global evita el deadlock.
     * @return true si N hilos que adquieren dos cerrojos en orden consistente terminan todos
     */
    public static boolean ordenGlobalEvita() {
        // GUÍA: misma idea que evitarDeadlockOrdenando pero con N hilos: si TODOS piden a antes que b,
        // nunca hay ciclo de espera. Devuelve true si ninguno queda vivo tras join.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenGlobalEvita");
    }

    /**
     * Reto Extra 2: {@code tryLock} con retroceso evita el deadlock.
     * @return true si usando tryLock (y soltando si no se consigue el segundo) ambos terminan
     */
    public static boolean tryLockEvitaDeadlock() {
        // GUÍA: en vez de lock() bloqueante, cada hilo hace a.tryLock(); si consigue a, intenta b.tryLock();
        // si NO consigue b, suelta a y reintenta. Así nadie se queda atascado. Devuelve true al terminar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tryLockEvitaDeadlock");
    }

    /**
     * Reto Extra 3: Transferencias entre cuentas SIN deadlock (orden por id).
     * @return saldo total tras muchas transferencias (se conserva == saldo inicial total)
     */
    public static long transferenciasSinDeadlock(int nCuentas, int saldoInicial, int nTransfers) {
        // GUÍA: cada cuenta tiene su lock. Para transferir de X a Y, bloquea SIEMPRE primero la de menor
        // id y luego la de mayor id (orden global por identidad). Lanza varias transferencias en hilos.
        // El dinero ni se crea ni se destruye: total final == nCuentas*saldoInicial.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transferenciasSinDeadlock");
    }

    /**
     * Reto Extra 4: Un solo cerrojo no puede producir deadlock.
     * @return true si dos hilos compartiendo UN cerrojo terminan siempre
     */
    public static boolean unSoloLockNoProduceDeadlock() {
        // GUÍA: con un único cerrojo no hay espera circular posible. Dos hilos lo toman por turnos
        // y ambos terminan. Devuelve true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unSoloLockNoProduceDeadlock");
    }

    /**
     * Reto Extra 5: Detección con {@code ThreadMXBean}.
     * @return true si findDeadlockedThreads() detecta el interbloqueo provocado
     */
    public static boolean detectarConThreadMXBean() {
        // GUÍA: provoca un deadlock con hilos daemon (como en provocarDeadlock, sin join eterno);
        //   ThreadMXBean mx = ManagementFactory.getThreadMXBean();
        //   espera un momento controlado y haz long[] ids = mx.findDeadlockedThreads();
        //   return ids != null && ids.length >= 2;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarConThreadMXBean");
    }

    /**
     * Reto Extra 6: Ordenar cerrojos por {@code System.identityHashCode}.
     * @return true si ordenar dinámicamente dos cerrojos por hash evita el deadlock
     */
    public static boolean ordenarPorIdentityHashCode() {
        // GUÍA: cuando no hay un "id" natural, ordena los dos Object lock por System.identityHashCode
        // y bloquéalos en ese orden en ambos hilos. Devuelve true al terminar ambos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarPorIdentityHashCode");
    }

    /**
     * Reto Extra 7: Un livelock con retroceso acaba progresando.
     * @return true si, tras varios reintentos educados, una operación termina
     */
    public static boolean livelockTerminaConBackoff() {
        // GUÍA: en un livelock los hilos no se bloquean pero se ceden el paso eternamente. Con un
        // límite de reintentos + pequeño retroceso aleatorio, uno acaba avanzando. Modela un par de
        // reintentos acotados que finalmente completan y devuelve true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para livelockTerminaConBackoff");
    }

    /**
     * Reto Extra 8: {@code lockInterruptibly} permite romper un bloqueo desde fuera.
     * @return true si interrumpir a un hilo que espera un cerrojo lo libera
     */
    public static boolean lockInterruptiblyRompeEspera() {
        // GUÍA: A retiene 'a'; B hace a.lockInterruptibly() y espera; interrumpimos B; B captura
        // InterruptedException y abandona limpiamente. Devuelve true si B salió por interrupción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lockInterruptiblyRompeEspera");
    }

    /**
     * Reto Extra 9: synchronized anidado en el mismo orden no produce deadlock.
     * @return true si dos hilos con synchronized(a){ synchronized(b){...} } (mismo orden) terminan
     */
    public static boolean nestedSynchronizedMismoOrden() {
        // GUÍA: synchronized también puede deadlockear si se anida en ordenes inversos. En el MISMO
        // orden (a luego b en ambos) es seguro. Devuelve true al terminar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nestedSynchronizedMismoOrden");
    }

    /**
     * Reto Extra 10: El deadlock también ocurre con {@code synchronized}.
     * @return true si dos hilos con synchronized en orden inverso quedan bloqueados (daemon)
     */
    public static boolean deadlockConSynchronized() {
        // GUÍA: réplica de provocarDeadlock pero con synchronized(a){ synchronized(b){} } y
        // synchronized(b){ synchronized(a){} }, latches para asegurar el cruce e hilos daemon.
        // join(500) a ambos; return ambos siguen vivos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deadlockConSynchronized");
    }
}
