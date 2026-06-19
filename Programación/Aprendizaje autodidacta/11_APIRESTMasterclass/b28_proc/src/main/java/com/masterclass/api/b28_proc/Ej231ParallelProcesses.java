package com.masterclass.api.b28_proc;

import java.util.List;

/**
 * Ejercicio 231 · Varios procesos en paralelo.
 *
 * <p>Lanzar varios procesos a la vez es como tener varios programas trabajando en paralelo
 * (PSP RA1.h). La clave es <b>arrancarlos todos primero</b> ({@code start()} no bloquea) y
 * <b>esperarlos después</b> ({@code waitFor()}): así corren simultáneamente. Si arrancas y
 * esperas uno a uno, los serializas y pierdes el paralelismo. Luego se recogen sus códigos de
 * salida para saber cuáles fueron bien.
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.5).
 */
public final class Ej231ParallelProcesses {

    private Ej231ParallelProcesses() {
    }

    /**
     * Lanza un proceso por cada código pedido (cada uno "exit c") y suma los códigos de salida.
     *
     * @param codigos códigos con los que debe terminar cada proceso
     * @return la suma de los códigos de salida observados, o -1 si no se ha implementado
     */
    public static int sumarCodigosDeSalida(List<Integer> codigos) {
        // TODO 1: crea una lista List<Process> procesos.
        // TODO 2: por cada código c, arranca new ProcessBuilder(ProcesoHijo.comando("exit", String.valueOf(c))).start()
        //         y guárdalo en la lista (ARRANCA TODOS primero, sin esperar).
        // TODO 3: recorre los procesos y acumula int suma += p.waitFor() de cada uno.
        // TODO 4: maneja IOException/InterruptedException.
        // TODO 5: devuelve la suma.
        return -1;
    }

    /**
     * Lanza n procesos en paralelo que terminan con éxito (exit 0) y comprueba que todos van bien.
     *
     * @param n número de procesos
     * @return true si los n procesos terminan con código 0, false si no se ha implementado
     */
    public static boolean todosTerminanConExito(int n) {
        // TODO 6: arranca n procesos ProcesoHijo.comando("exit","0") guardándolos en una lista.
        // TODO 7: espera todos con waitFor() y comprueba que cada código es 0.
        // TODO 8: usa una bandera que se ponga a false si algún código != 0.
        // TODO 9: maneja excepciones.
        // TODO 10: devuelve true solo si todos terminaron con 0.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("sumarCodigosDeSalida([1,2,3]) = " + sumarCodigosDeSalida(List.of(1, 2, 3)));
        System.out.println("todosTerminanConExito(4) = " + todosTerminanConExito(4));
    }

    /**
     * Reto Extra 1: lanzar tres procesos y contar los que terminan.
     * @return número de procesos terminados (== 3)
     */
    public static int lanzarTresEnParalelo() {
        // GUÍA: arranca 3 procesos "exit 0", espera a los 3 y cuenta cuántos terminaron (3).
        // PISTA: reutiliza el patrón de todosTerminanConExito.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lanzarTresEnParalelo");
    }

    /**
     * Reto Extra 2: tres procesos lanzados están todos vivos al arrancar.
     * @return true si 3 procesos "sleep" están vivos nada más arrancar (luego se destruyen)
     */
    public static boolean todosVivosTrasArrancar() {
        // GUÍA: arranca 3 "sleep 2000"; comprueba que los 3 isAlive() son true; destruye todos
        // (destroyForcibly) y devuelve el resultado. Demuestra que corren a la vez.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para todosVivosTrasArrancar");
    }

    /**
     * Reto Extra 3: entre varios procesos, uno falla y el resto va bien.
     * @return true si de los códigos [0,0,5] exactamente uno es distinto de 0
     */
    public static boolean unoFallaRestoOk() {
        // GUÍA: lanza procesos con códigos 0,0,5; cuenta cuántos waitFor() != 0; return cuenta == 1.
        // CULTURA: así un orquestador detecta qué subtarea concreta falló sin abortar las demás.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unoFallaRestoOk");
    }

    /**
     * Reto Extra 4: la suma de los códigos [0,1,2] es 3.
     * @return suma de códigos de salida de tres procesos exit 0/1/2 (== 3)
     */
    public static int sumaDeCodigosCeroUnoDos() {
        // GUÍA: return sumarCodigosDeSalida(List.of(0,1,2));  // 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaDeCodigosCeroUnoDos");
    }

    /**
     * Reto Extra 5: esperar a todos los procesos con waitFor.
     * @return true si, tras esperar a 3 procesos, ninguno sigue vivo
     */
    public static boolean esperarTodos() {
        // GUÍA: arranca 3 "exit 0"; waitFor() en cada uno; comprueba que ninguno isAlive(); return true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esperarTodos");
    }

    /**
     * Reto Extra 6: procesos distintos tienen PIDs distintos.
     * @return true si tres procesos lanzados tienen tres PIDs diferentes
     */
    public static boolean pidsDistintos() {
        // GUÍA: arranca 3 "sleep 1000"; mete sus pid() en un Set<Long>; comprueba que el Set tiene 3;
        //   destruye todos. return set.size() == 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pidsDistintos");
    }

    /**
     * Reto Extra 7: arrancar todos antes de esperar (paralelo de verdad).
     * @return true si, arrancando 3 y esperando después, todos terminan con 0
     */
    public static boolean arrancarTodosLuegoEsperar() {
        // GUÍA: primero un bucle que start() los 3 y los guarda; LUEGO otro bucle que waitFor() los 3.
        // CONTRASTE: arrancar-y-esperar dentro del mismo bucle los serializaría (no habría paralelismo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para arrancarTodosLuegoEsperar");
    }

    /**
     * Reto Extra 8: la lista de procesos arrancados tiene el tamaño esperado.
     * @return número de procesos en la lista tras arrancar 5 (== 5)
     */
    public static int listaProcesosTamano() {
        // GUÍA: arranca 5 procesos "exit 0", guárdalos en una List<Process>, espera a todos y
        //   return lista.size();  // 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listaProcesosTamano");
    }

    /**
     * Reto Extra 9: si todos terminan con 0, la suma de códigos es 0.
     * @return suma de códigos de tres procesos exit 0 (== 0)
     */
    public static int sumaCodigosTodoCero() {
        // GUÍA: return sumarCodigosDeSalida(List.of(0,0,0));  // 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaCodigosTodoCero");
    }

    /**
     * Reto Extra 10: contar cuántos procesos terminaron con éxito (código 0).
     * @return número de éxitos entre los códigos [0,0,5] (== 2)
     */
    public static int contarExitosos() {
        // GUÍA: lanza procesos con códigos 0,0,5; cuenta cuántos waitFor() == 0; return 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarExitosos");
    }
}
