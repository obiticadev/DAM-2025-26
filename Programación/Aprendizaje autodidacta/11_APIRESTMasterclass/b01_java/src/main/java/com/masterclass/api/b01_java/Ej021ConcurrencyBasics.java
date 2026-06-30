package com.masterclass.api.b01_java;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.BiFunction;

/**
 * Ejercicio 021 · Concurrencia mínima con CompletableFuture.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.6).
 */
public final class Ej021ConcurrencyBasics {

    private Ej021ConcurrencyBasics() {
    }

    /**
     * Ejecuta una tarea de forma asíncrona y devuelve el future.
     *
     * @param valor valor a producir
     * @return CompletableFuture que completará con 'valor' en mayúsculas
     */
    public static CompletableFuture<String> asincronoMayus(String valor) {
        // TODO 1: usa CompletableFuture.supplyAsync (ejecuta en otro hilo).
        // TODO 2: la lambda debe devolver valor.toUpperCase().
        // TODO 3: devuelve el future SIN bloquear (no llames join aquí).
        return null;
    }

    /**
     * Combina dos futures sumando sus resultados enteros.
     *
     * @param a primer future
     * @param b segundo future
     * @return future con la suma
     */
    public static CompletableFuture<Integer> sumar(CompletableFuture<Integer> a, CompletableFuture<Integer> b) {
        // TODO 4: combina ambos con a.thenCombine(b, ...).
        // TODO 5: la BiFunction debe sumar los dos enteros (Integer::sum).
        // TODO 6: devuelve el future combinado (sigue siendo asíncrono).
        return null;
    }

    /**
     * Espera todos los futures y devuelve sus resultados en orden.
     *
     * @param futures lista de futures de String
     * @return lista con los valores ya resueltos
     */
    public static List<String> esperarTodos(List<CompletableFuture<String>> futures) {
        // TODO 7: usa CompletableFuture.allOf(...) con el array de futures para esperar a todos.
        // TODO 8: invoca .join() sobre ese allOf para bloquear hasta que terminen.
        // TODO 9: recorre 'futures' en orden y haz join() de cada uno.
        // TODO 10: recoge los resultados a una List y devuélvela.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(asincronoMayus("hola").join());
    }

    /**
     * Reto Extra 1: Creación clásica de hilos de ejecución.
     * Crea e inicia un Thread para ejecutar de manera independiente el Runnable provisto.
     *
     * @param tarea tarea a ejecutar de forma asíncrona
     * @return instancia de Thread iniciada
     */
    public static Thread ejecutarEnHiloSeparado(Runnable tarea) {
        // GUÍA: crea un hilo para la tarea y arráncalo de verdad. No confundas
        // ejecutar el Runnable en el hilo actual con iniciar un hilo nuevo. Devuelve
        // la instancia para que el llamador pueda esperarla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarEnHiloSeparado");
    }

    /**
     * Reto Extra 2: Obtención de resultados bloqueantes.
     * Bloquea el hilo actual de ejecución hasta obtener y retornar el resultado del futuro.
     *
     * @param futuro objeto de futuro asíncrono
     * @param <T>    tipo del resultado esperado
     * @return valor resuelto
     */
    public static <T> T obtenerResultadoAsincrono(CompletableFuture<T> futuro) {
        // GUÍA: aquí sí quieres bloquear hasta tener el resultado, porque el
        // contrato devuelve un valor ya resuelto. En código real, bloquea solo en
        // los bordes; dentro de una cadena asíncrona se compone sin esperar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerResultadoAsincrono");
    }

    /**
     * Reto Extra 3: Coordinación de futuros paralelos.
     * Combina los resultados de dos futuros paralelos usando una función de combinación.
     *
     * @param futA       primer futuro
     * @param futB       segundo futuro
     * @param combinador función para mezclar ambos resultados
     * @param <A>        tipo del primer futuro
     * @param <B>        tipo del segundo futuro
     * @param <R>        tipo del resultado combinado
     * @return futuro con el valor resultante de la mezcla
     */
    public static <A, B, R> CompletableFuture<R> combinarDosResultadosAsincronos(
            CompletableFuture<A> futA, CompletableFuture<B> futB, BiFunction<A, B, R> combinador) {
        // GUÍA: no resuelvas un futuro antes que el otro. La combinación debe
        // esperar a que ambos estén disponibles y aplicar entonces la BiFunction
        // recibida para producir el resultado final.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarDosResultadosAsincronos");
    }

    /**
     * Reto Extra 4: Prevención de bloqueos con límites de tiempo (timeout).
     * Asegura que el futuro finalice devolviendo el valor por defecto si no concluye en el tiempo máximo indicado.
     *
     * @param futuro           futuro bajo supervisión
     * @param ms               tiempo límite en milisegundos
     * @param valorPorDefecto valor de contingencia
     * @param <T>              tipo de retorno del futuro
     * @return futuro protegido contra tiempos de espera excesivos
     */
    public static <T> CompletableFuture<T> ejecutarConTimeout(CompletableFuture<T> futuro, long ms, T valorPorDefecto) {
        // GUÍA: protege un futuro que podría no terminar nunca. Tras el tiempo
        // máximo, debe completarse con el valor de contingencia en vez de dejar
        // bloqueado al consumidor indefinidamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarConTimeout");
    }

    /**
     * Reto Extra 5: Recuperación y tolerancia a fallos asíncronos.
     * Retorna un futuro que, ante excepciones durante la ejecución asíncrona, se recupera devolviendo el valor de contingencia.
     *
     * @param futuro   futuro susceptible de fallar
     * @param fallback valor de recuperación
     * @param <T>      tipo de retorno del futuro
     * @return futuro tolerante a fallos
     */
    public static <T> CompletableFuture<T> recuperarAnteErrorAsincrono(CompletableFuture<T> futuro, T fallback) {
        // GUÍA: si el futuro termina bien, conserva su valor. Si termina con error,
        // transforma ese fallo en un valor de recuperación sin bloquear el hilo actual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recuperarAnteErrorAsincrono");
    }

    /**
     * Reto Extra 6: Pools de hilos acotados con ExecutorService.
     * Configura y arranca un pool de hilos acotado a nHilos, envía el proceso Runnable para su ejecución y lo apaga ordenadamente.
     *
     * @param tarea  proceso a ejecutar
     * @param nHilos cantidad de hilos concurrentes permitidos
     * @return ExecutorService configurado
     */
    public static ExecutorService ejecutarEnPoolAcotado(Runnable tarea, int nHilos) {
        // GUÍA: crea un pool con tamaño limitado, encarga la tarea y solicita un
        // apagado ordenado. Apagar ordenadamente no mata tareas en curso; evita
        // dejar hilos vivos sin control.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarEnPoolAcotado");
    }

    /**
     * Reto Extra 7: Barrera de sincronización para múltiples futuros concurrentes.
     * Bloquea el hilo de ejecución actual hasta que todas las tareas provistas en la lista se hayan completado.
     *
     * @param futuros lista de futuros
     */
    public static void esperarQueTerminenTodos(List<CompletableFuture<?>> futuros) {
        // GUÍA: construye una barrera: el método no debe continuar hasta que todos
        // los futuros hayan terminado. El orden de finalización no importa, solo
        // que no quede ninguno pendiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esperarQueTerminenTodos");
    }

    /**
     * Reto Extra 8: Carrera de futuros asíncronos.
     * Retorna un futuro que se resolverá con el valor del primer futuro de la carrera en terminar, sin importar cuál sea.
     *
     * @param fut1 primer futuro competidor
     * @param fut2 segundo futuro competidor
     * @param <T>  tipo de los futuros
     * @return futuro completado con el resultado del ganador
     */
    public static <T> CompletableFuture<T> ejecutarPrimeroQueTermine(CompletableFuture<T> fut1, CompletableFuture<T> fut2) {
        // GUÍA: compón una carrera entre dos futuros y conserva el valor del que
        // termine primero. No esperes al perdedor; podría no completarse nunca.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarPrimeroQueTermine");
    }

    /**
     * Reto Extra 9: Transformaciones no bloqueantes de resultados asíncronos.
     * Transforma el resultado de un futuro asíncronamente mediante la función provista, sin bloquear el hilo.
     *
     * @param futuro    futuro original
     * @param mapeador  función de transformación
     * @param <T>       tipo de entrada
     * @param <R>       tipo del resultado transformado
     * @return futuro con el valor transformado
     */
    public static <T, R> CompletableFuture<R> mapearResultadoAsincrono(CompletableFuture<T> futuro, Function<T, R> mapeador) {
        // GUÍA: transforma el valor cuando llegue, sin bloquear ahora para obtenerlo.
        // Es la misma idea mental que map en Optional o Stream, aplicada al mundo
        // asíncrono.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearResultadoAsincrono");
    }

    /**
     * Reto Extra 10: Composición secuencial y encadenamiento asíncrono.
     * Encadena dos futuros secuencialmente de manera no bloqueante, donde el segundo futuro depende del resultado del primero.
     *
     * @param futuro         primer futuro asíncrono
     * @param mapeadorFuturo función que genera el segundo futuro a partir del resultado del primero
     * @param <T>            tipo del primer futuro
     * @param <R>            tipo del segundo futuro secuencial
     * @return futuro compuesto
     */
    public static <T, R> CompletableFuture<R> encadenarFuturosAsincronos(
            CompletableFuture<T> futuro, Function<T, CompletableFuture<R>> mapeadorFuturo) {
        // GUÍA: la segunda operación asíncrona depende del resultado de la primera.
        // Como el mapeador ya devuelve otro futuro, la composición debe aplanar
        // esa dependencia para no acabar con un futuro anidado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encadenarFuturosAsincronos");
    }

}
