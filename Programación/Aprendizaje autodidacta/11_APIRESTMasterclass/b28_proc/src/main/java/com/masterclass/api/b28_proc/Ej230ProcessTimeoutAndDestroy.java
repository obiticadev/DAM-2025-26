package com.masterclass.api.b28_proc;

import java.util.concurrent.TimeUnit;

/**
 * Ejercicio 230 · Timeouts y destrucción de procesos.
 *
 * <p>Un proceso hijo puede colgarse o tardar demasiado. {@code waitFor(timeout, unidad)} espera
 * con límite y devuelve {@code true} si terminó a tiempo o {@code false} si venció el plazo (sin
 * matarlo). Para terminarlo: {@code destroy()} (educado, SIGTERM) o {@code destroyForcibly()}
 * (a la fuerza, SIGKILL). {@code isAlive()} comprueba si sigue vivo. PSP RA1: controlar la
 * ejecución de subprocesos.
 *
 * <p>Regla: si {@code waitFor(timeout)} devuelve false, TÚ debes decidir (normalmente destruir),
 * o dejarás un proceso huérfano colgado.
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.4).
 */
public final class Ej230ProcessTimeoutAndDestroy {

    private Ej230ProcessTimeoutAndDestroy() {
    }

    /**
     * Lanza un hijo que duerme sleepMs y comprueba si termina dentro de timeoutMs.
     *
     * @param sleepMs   cuánto duerme el hijo
     * @param timeoutMs plazo máximo de espera
     * @return true si el proceso terminó dentro del plazo, false si no se ha implementado
     */
    public static boolean terminaDentroDelPlazo(long sleepMs, long timeoutMs) {
        // TODO 1: lanza ProcesoHijo.comando("sleep", String.valueOf(sleepMs)).
        // TODO 2: espera con boolean termino = p.waitFor(timeoutMs, TimeUnit.MILLISECONDS).
        // TODO 3: si NO terminó (false), destruye el proceso con p.destroy() para no dejarlo colgado.
        // TODO 4: (opcional) espera de nuevo para limpiar.
        // TODO 5: devuelve 'termino' (maneja IOException/InterruptedException).
        return false;
    }

    /**
     * Lanza un hijo "infinito" (duerme mucho) y lo mata; confirma que ya no está vivo.
     *
     * @return true si tras destroy() el proceso deja de estar vivo
     */
    public static boolean matarProcesoColgado() {
        // TODO 6: lanza ProcesoHijo.comando("sleep", "10000") (10 s, hace de "proceso colgado").
        // TODO 7: comprueba que isAlive() es true al principio (opcional).
        // TODO 8: mátalo con p.destroy().
        // TODO 9: espera su final con p.waitFor() (para que el SO lo recoja).
        // TODO 10: devuelve !p.isAlive().
        return false;
    }

    public static void main(String[] args) {
        System.out.println("terminaDentroDelPlazo(100,5000) = " + terminaDentroDelPlazo(100, 5000));
        System.out.println("matarProcesoColgado = " + matarProcesoColgado());
    }

    /**
     * Reto Extra 1: waitFor(timeout) devuelve false si el proceso no termina a tiempo.
     * @return true si esperar 200 ms a un proceso que duerme 10 s devuelve false (y luego se mata)
     */
    public static boolean waitForTimeoutFalseSiNoTermina() {
        // GUÍA: lanza "sleep 10000"; boolean t = p.waitFor(200, MILLISECONDS);  // false
        //   p.destroyForcibly(); return !t.
        // OJO/CUIDADO: destruye SIEMPRE el proceso tras un timeout, o quedará huérfano corriendo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para waitForTimeoutFalseSiNoTermina");
    }

    /**
     * Reto Extra 2: destroy() termina un proceso vivo.
     * @return true si tras destroy() y waitFor() el proceso ya no vive
     */
    public static boolean destroyTerminaProceso() {
        // GUÍA: return matarProcesoColgado();  // mismo patrón: destroy + waitFor + !isAlive.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para destroyTerminaProceso");
    }

    /**
     * Reto Extra 3: destroyForcibly() termina el proceso a la fuerza.
     * @return true si tras destroyForcibly() y waitFor() el proceso ya no vive
     */
    public static boolean destroyForciblyTermina() {
        // GUÍA: lanza "sleep 10000"; p.destroyForcibly(); p.waitFor(); return !p.isAlive().
        // CULTURA: destroy() pide terminar (SIGTERM, el proceso podría limpiar); destroyForcibly()
        // mata sin contemplaciones (SIGKILL). En Windows ambos suelen ser equivalentes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para destroyForciblyTermina");
    }

    /**
     * Reto Extra 4: un proceso destruido termina con un código distinto de 0.
     * @return true si exitValue() tras destruir el proceso no es 0
     */
    public static boolean destroyProduceCodigoNoCero() {
        // GUÍA: lanza "sleep 10000"; p.destroy(); p.waitFor(); return p.exitValue() != 0.
        // CULTURA: un proceso terminado por señal no sale con 0; así el padre sabe que fue abortado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para destroyProduceCodigoNoCero");
    }

    /**
     * Reto Extra 5: isAlive() es false tras terminar normalmente.
     * @return true si un proceso corto, tras waitFor(), no está vivo
     */
    public static boolean isAliveFalseTrasTerminar() {
        // GUÍA: lanza "ok"; p.waitFor(); return !p.isAlive().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isAliveFalseTrasTerminar");
    }

    /**
     * Reto Extra 6: waitFor con unidad de tiempo para un proceso corto devuelve true.
     * @return true si waitFor(5, SECONDS) de un proceso "ok" devuelve true
     */
    public static boolean waitForConUnidadTrue() {
        // GUÍA: lanza "ok"; return p.waitFor(5, TimeUnit.SECONDS);  // termina enseguida → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para waitForConUnidadTrue");
    }

    /**
     * Reto Extra 7: un proceso que duerme sigue vivo mientras tanto.
     * @return true si un proceso "sleep 5000" está vivo nada más arrancar (luego se destruye)
     */
    public static boolean procesoLargoSigueVivo() {
        // GUÍA: lanza "sleep 5000"; boolean vivo = p.isAlive(); p.destroyForcibly(); return vivo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesoLargoSigueVivo");
    }

    /**
     * Reto Extra 8: destroy() es idempotente (llamarlo dos veces no falla).
     * @return true si destruir dos veces el mismo proceso no lanza excepción
     */
    public static boolean destroyEsIdempotente() {
        // GUÍA: lanza "sleep 5000"; p.destroy(); p.destroy(); p.waitFor(); return !p.isAlive().
        // (la segunda llamada sobre un proceso ya muerto no hace nada).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para destroyEsIdempotente");
    }

    /**
     * Reto Extra 9: un timeout NO mata el proceso por sí solo (sigue vivo tras vencer).
     * @return true si, tras un waitFor(200ms) fallido sobre "sleep 5000", el proceso sigue vivo
     */
    public static boolean timeoutNoMataElProceso() {
        // GUÍA: lanza "sleep 5000"; boolean t = p.waitFor(200, MILLISECONDS);  // false
        //   boolean sigueVivo = p.isAlive();  // ¡true! el timeout solo deja de ESPERAR
        //   p.destroyForcibly(); return !t && sigueVivo.
        // CULTURA: waitFor(timeout) no es "matar tras X": solo deja de bloquear. Matar es decisión tuya.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para timeoutNoMataElProceso");
    }

    /**
     * Reto Extra 10: onExit() devuelve un CompletableFuture que completa al terminar el proceso.
     * @return true si p.onExit().get() devuelve el propio proceso ya terminado
     */
    public static boolean onExitCompletaAlTerminar() {
        // GUÍA: lanza "ok"; CompletableFuture<Process> f = p.onExit();
        //   Process terminado = f.get();  // bloquea hasta el final (b27·Ej225)
        //   return !terminado.isAlive();
        // CULTURA: onExit() integra los procesos con la programación asíncrona de CompletableFuture.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para onExitCompletaAlTerminar");
    }
}
