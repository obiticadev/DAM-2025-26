package com.masterclass.api.b27_concur;

/**
 * Ejercicio 216 · Estados de un hilo, {@code sleep} e interrupción.
 *
 * <p>Un hilo recorre los estados {@code NEW → RUNNABLE → (BLOCKED/WAITING/TIMED_WAITING) → TERMINATED}.
 * Aquí los observas con {@link Thread#getState()} y aprendes que la interrupción es
 * cooperativa: una señal que el hilo debe atender, no una muerte forzosa.
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.2).
 */
public final class Ej216ThreadStates {

    private Ej216ThreadStates() {
    }

    /**
     * Arranca un hilo trivial, lo espera y devuelve su estado final.
     *
     * @return estado del hilo tras terminar (debe ser {@link Thread.State#TERMINATED})
     */
    public static Thread.State estadoTrasJoin() {
        // TODO 1: crea un Thread con un Runnable que no haga nada (() -> {}).
        // TODO 2: arráncalo con start().
        // TODO 3: espera con join() (maneja InterruptedException restaurando el flag).
        // TODO 4: tras join, el hilo ya terminó.
        // TODO 5: devuelve t.getState().
        return null;
    }

    /**
     * Lanza un hilo que duerme {@code ms} y comprueba que tarda al menos ese tiempo.
     *
     * @param ms milisegundos a dormir
     * @return true si el tiempo medido fue &gt;= ms
     */
    public static boolean sleepDuraAlMenos(long ms) {
        // TODO 6: guarda el instante inicial con System.nanoTime().
        // TODO 7: crea un Thread cuyo Runnable haga Thread.sleep(ms) (envuelve la InterruptedException).
        // TODO 8: arráncalo y espera con join().
        // TODO 9: calcula el tiempo transcurrido en milisegundos ((nanoTime()-inicio)/1_000_000).
        // TODO 10: devuelve (transcurrido >= ms).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("estadoTrasJoin() = " + estadoTrasJoin());
        System.out.println("sleepDuraAlMenos(50) = " + sleepDuraAlMenos(50));
    }

    /**
     * Reto Extra 1: Estado NEW.
     * @return estado de un hilo recién creado pero NO arrancado (NEW)
     */
    public static Thread.State estadoNew() {
        // GUÍA: new Thread(() -> {}).getState() es NEW. Aún no existe en el SO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoNew");
    }

    /**
     * Reto Extra 2: Estado RUNNABLE del hilo actual.
     * @return estado del hilo que ejecuta este método (RUNNABLE)
     */
    public static Thread.State estadoRunnableDelMain() {
        // GUÍA: Thread.currentThread().getState() -> RUNNABLE (se está ejecutando ahora mismo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoRunnableDelMain");
    }

    /**
     * Reto Extra 3: Estado TIMED_WAITING.
     * Un hilo dormido con {@code sleep(long)} está en TIMED_WAITING.
     * @return TIMED_WAITING
     */
    public static Thread.State estadoTimedWaiting() {
        // GUÍA: lanza un hilo que duerma p.ej. 2000 ms y haz spin-wait hasta verlo:
        //   Thread t = new Thread(() -> { try { Thread.sleep(2000); } catch (InterruptedException e) {} });
        //   t.start();
        //   while (t.getState() != Thread.State.TIMED_WAITING) Thread.onSpinWait();
        //   Thread.State s = t.getState();
        //   t.interrupt();   // lo despertamos para no esperar 2s
        //   return s;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoTimedWaiting");
    }

    /**
     * Reto Extra 4: Estado WAITING.
     * Un hilo bloqueado en {@code latch.await()} (sin timeout) está en WAITING.
     * @return WAITING
     */
    public static Thread.State estadoWaiting() {
        // GUÍA: CountDownLatch puerta = new CountDownLatch(1);
        //   Thread t = new Thread(() -> { try { puerta.await(); } catch (InterruptedException e) {} });
        //   t.start();
        //   while (t.getState() != Thread.State.WAITING) Thread.onSpinWait();
        //   Thread.State s = t.getState();
        //   puerta.countDown();   // lo liberamos
        //   return s;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoWaiting");
    }

    /**
     * Reto Extra 5: Interrupción durante sleep.
     * @return true si interrumpir un hilo dormido provoca InterruptedException
     */
    public static boolean interrupcionDuranteSleep() {
        // GUÍA: AtomicBoolean saltó = new AtomicBoolean(false);
        //   Thread t = new Thread(() -> {
        //       try { Thread.sleep(10_000); } catch (InterruptedException e) { saltó.set(true); }
        //   });
        //   t.start();
        //   while (t.getState() != Thread.State.TIMED_WAITING) Thread.onSpinWait();
        //   t.interrupt(); t.join();
        //   return saltó.get();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interrupcionDuranteSleep");
    }

    /**
     * Reto Extra 6: El flag de interrupción se limpia al lanzar InterruptedException.
     * @return true si tras capturar InterruptedException el flag isInterrupted() es false
     */
    public static boolean flagSeLimpiaTrasInterruptedException() {
        // GUÍA: cuando sleep/wait/join lanzan InterruptedException, RESETEAN el flag a false.
        //   boolean[] flag = {true};
        //   Thread t = new Thread(() -> {
        //       try { Thread.sleep(10_000); }
        //       catch (InterruptedException e) { flag[0] = Thread.currentThread().isInterrupted(); }
        //   });
        //   t.start();
        //   while (t.getState() != Thread.State.TIMED_WAITING) Thread.onSpinWait();
        //   t.interrupt(); t.join();
        //   return !flag[0];   // el flag quedó en false -> devolvemos true
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flagSeLimpiaTrasInterruptedException");
    }

    /**
     * Reto Extra 7: Interrumpir ANTES de dormir.
     * @return true si llamar interrupt() antes de sleep hace que sleep lance de inmediato
     */
    public static boolean interrumpirAntesDeSleepLanzaInmediato() {
        // GUÍA: el flag persiste hasta que una operación bloqueante lo consume.
        //   boolean[] saltó = {false};
        //   Thread t = new Thread(() -> {
        //       Thread.currentThread().interrupt();           // marcamos el flag
        //       try { Thread.sleep(10_000); } catch (InterruptedException e) { saltó[0] = true; }
        //   });
        //   t.start(); t.join();
        //   return saltó[0];   // sleep ve el flag ya puesto y lanza al instante
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interrumpirAntesDeSleepLanzaInmediato");
    }

    /**
     * Reto Extra 8: {@code Thread.yield()} no falla.
     * @return true tras invocar yield() sin excepción (pista al planificador, no garantía)
     */
    public static boolean yieldNoLanza() {
        // GUÍA: Thread.yield(); return true;  // yield sugiere ceder la CPU; rara vez se usa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para yieldNoLanza");
    }

    /**
     * Reto Extra 9: {@code join(timeout)} retorna aunque el hilo siga vivo.
     * @return true si tras join(50) sobre un hilo bloqueado, este sigue vivo
     */
    public static boolean joinConTimeoutRetornaAunqueVivo() {
        // GUÍA: CountDownLatch puerta = new CountDownLatch(1);
        //   Thread t = new Thread(() -> { try { puerta.await(); } catch (InterruptedException e) {} });
        //   t.start();
        //   t.join(50);                 // espera como mucho 50 ms y vuelve
        //   boolean vivo = t.isAlive(); // sigue bloqueado -> true
        //   puerta.countDown(); t.join();
        //   return vivo;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para joinConTimeoutRetornaAunqueVivo");
    }

    /**
     * Reto Extra 10: Un hilo no se puede reiniciar.
     * @return true si llamar start() dos veces lanza IllegalThreadStateException
     */
    public static boolean reiniciarHiloLanzaIllegalThreadState() {
        // GUÍA: Thread t = new Thread(() -> {});
        //   t.start(); t.join();
        //   try { t.start(); return false; }            // segundo start sobre hilo TERMINATED
        //   catch (IllegalThreadStateException e) { return true; }
        // Un Thread es de un solo uso: para "reutilizar" hilos se usa un pool (Ej219).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reiniciarHiloLanzaIllegalThreadState");
    }
}
