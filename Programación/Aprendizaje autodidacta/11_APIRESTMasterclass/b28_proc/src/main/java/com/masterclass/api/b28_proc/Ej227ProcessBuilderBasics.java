package com.masterclass.api.b28_proc;

/**
 * Ejercicio 227 · Lanzar procesos con {@code ProcessBuilder}.
 *
 * <p>Un <b>proceso</b> es un programa en ejecución con su propia memoria, gestionado por el SO
 * (a diferencia de un hilo, b27, que comparte memoria dentro de un proceso). Java lanza procesos
 * con {@code ProcessBuilder}: defines el comando, {@code start()} crea el proceso y devuelve un
 * {@code Process}, {@code waitFor()} espera a que termine y da su <b>código de salida</b>
 * (0 = éxito por convención). Es PSP RA1 (procesos y su ejecución por el SO).
 *
 * <p>Para tests deterministas se lanza el proceso Java hijo {@link ProcesoHijo} (ver
 * {@link ProcesoHijo#comando(String...)}), no comandos del SO.
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.1).
 */
public final class Ej227ProcessBuilderBasics {

    private Ej227ProcessBuilderBasics() {
    }

    /**
     * Lanza el hijo con "exit N" y devuelve su código de salida.
     *
     * @param codigo código con el que el hijo debe terminar
     * @return el código de salida observado (== codigo), o -1 si no se ha implementado
     */
    public static int ejecutarYObtenerCodigo(int codigo) {
        // TODO 1: construye el comando con ProcesoHijo.comando("exit", String.valueOf(codigo)).
        // TODO 2: crea ProcessBuilder pb = new ProcessBuilder(comando).
        // TODO 3: arranca con Process p = pb.start() (lanza IOException si el ejecutable no existe).
        // TODO 4: espera el final con int code = p.waitFor() (lanza InterruptedException).
        // TODO 5: devuelve code (maneja IOException/InterruptedException).
        return -1;
    }

    /**
     * Lanza un proceso corto y comprueba que ya no está vivo tras esperarlo.
     *
     * @return true si, tras waitFor(), el proceso ha terminado (no isAlive)
     */
    public static boolean procesoYaNoEstaVivoTrasWaitFor() {
        // TODO 6: lanza ProcesoHijo.comando("ok") (imprime "HIJO-OK" y termina).
        // TODO 7: arranca el proceso.
        // TODO 8: espera con p.waitFor().
        // TODO 9: consulta p.isAlive() (debe ser false una vez terminado).
        // TODO 10: devuelve !p.isAlive().
        return false;
    }

    public static void main(String[] args) {
        System.out.println("ejecutarYObtenerCodigo(3) = " + ejecutarYObtenerCodigo(3));
        System.out.println("procesoYaNoEstaVivoTrasWaitFor = " + procesoYaNoEstaVivoTrasWaitFor());
    }

    /**
     * Reto Extra 1: el código de salida 0 significa éxito.
     * @return true si un proceso "exit 0" devuelve código 0
     */
    public static boolean exitCeroEsExito() {
        // GUÍA: teoría 28.1. return ejecutarYObtenerCodigo(0) == 0.
        // CULTURA: por convención Unix, 0 = OK y !=0 = error; los scripts y CI dependen de esto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exitCeroEsExito");
    }

    /**
     * Reto Extra 2: un código de salida distinto de 0 se propaga al padre.
     * @return el código de salida de un proceso "exit 5" (== 5)
     */
    public static int exitCincoSePropaga() {
        // GUÍA: return ejecutarYObtenerCodigo(5);  // 5.
        // CULTURA: así sabe el padre si el hijo falló y por qué (códigos de error convenidos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exitCincoSePropaga");
    }

    /**
     * Reto Extra 3: cada proceso tiene un PID positivo asignado por el SO.
     * @return true si Process.pid() es mayor que 0
     */
    public static boolean pidEsPositivo() {
        // GUÍA: Process p = new ProcessBuilder(ProcesoHijo.comando("sleep","300")).start();
        //   long pid = p.pid(); p.waitFor(); return pid > 0;
        // CULTURA: el PID identifica al proceso en el SO (lo ves en el Administrador de tareas / ps).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pidEsPositivo");
    }

    /**
     * Reto Extra 4: isAlive() es true mientras el proceso no ha terminado.
     * @return true si un proceso que duerme está vivo justo tras arrancar
     */
    public static boolean isAliveTrasArrancar() {
        // GUÍA: lanza ProcesoHijo.comando("sleep","500"); justo después p.isAlive() es true.
        //   guarda el booleano, luego p.waitFor() para no dejar el proceso colgado, y devuélvelo.
        // OJO: no olvides esperar/destruir el proceso para no dejar zombies en la suite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para isAliveTrasArrancar");
    }

    /**
     * Reto Extra 5: lanzar un ejecutable inexistente lanza IOException.
     * @return true si new ProcessBuilder("no-existe-xyz").start() lanza IOException
     */
    public static boolean comandoInexistenteLanzaIOException() {
        // GUÍA: try { new ProcessBuilder("comando-que-no-existe-xyz123").start(); return false; }
        //   catch (IOException e) { return true; }
        // OJO: el fallo ocurre en start() (no en waitFor): el SO no encuentra el ejecutable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoInexistenteLanzaIOException");
    }

    /**
     * Reto Extra 6: ProcessBuilder.command() devuelve el comando configurado.
     * @return true si pb.command() contiene el nombre de la clase del proceso hijo
     */
    public static boolean commandDevuelveElComando() {
        // GUÍA: ProcessBuilder pb = new ProcessBuilder(ProcesoHijo.comando("ok"));
        //   return pb.command().contains(ProcesoHijo.class.getName());
        // (no hace falta arrancar el proceso; command() es solo la configuración).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para commandDevuelveElComando");
    }

    /**
     * Reto Extra 7: exitValue() sobre un proceso aún vivo lanza IllegalThreadStateException.
     * @return true si consultar exitValue() antes de que termine lanza IllegalThreadStateException
     */
    public static boolean exitValueEnProcesoVivoLanza() {
        // GUÍA: lanza ProcesoHijo.comando("sleep","500"); SIN esperar, llama a p.exitValue():
        //   try { p.exitValue(); ... return false; } catch (IllegalThreadStateException e) { resultado = true; }
        //   luego p.waitFor() para limpiar. CONTRASTE: waitFor() bloquea hasta el final; exitValue() no espera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exitValueEnProcesoVivoLanza");
    }

    /**
     * Reto Extra 8: dos procesos lanzados son independientes (ambos terminan con éxito).
     * @return true si dos procesos "exit 0" lanzados por separado devuelven ambos 0
     */
    public static boolean dosProcesosIndependientes() {
        // GUÍA: lanza dos procesos "exit 0" (uno tras otro o a la vez) y comprueba que ambos
        // waitFor() devuelven 0. return c1 == 0 && c2 == 0.
        // CULTURA: cada proceso tiene su propia memoria; no comparten estado como los hilos (b27).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosProcesosIndependientes");
    }

    /**
     * Reto Extra 9: el ejecutable java existe en el sistema.
     * @return true si el fichero devuelto por ProcesoHijo.javaBin() existe
     */
    public static boolean javaBinExiste() {
        // GUÍA: return new java.io.File(ProcesoHijo.javaBin()).exists();
        // CULTURA: lanzar el MISMO java que corre la JVM actual (java.home) es lo robusto y portable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para javaBinExiste");
    }

    /**
     * Reto Extra 10: waitFor devuelve exactamente el código con el que terminó el hijo.
     * @return el código devuelto por waitFor para un hijo "exit 7" (== 7)
     */
    public static int waitForDevuelveElCodigo() {
        // GUÍA: return ejecutarYObtenerCodigo(7);  // demuestra que waitFor() == exitValue().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para waitForDevuelveElCodigo");
    }
}
