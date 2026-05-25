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
        // TODO extra: Reto Extra 1: Creación clásica de hilos de ejecución.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 2: Obtención de resultados bloqueantes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 3: Coordinación de futuros paralelos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 4: Prevención de bloqueos con límites de tiempo (timeout).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 5: Recuperación y tolerancia a fallos asíncronos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 6: Pools de hilos acotados con ExecutorService.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarEnPoolAcotado");
    }

    /**
     * Reto Extra 7: Barrera de sincronización para múltiples futuros concurrentes.
     * Bloquea el hilo de ejecución actual hasta que todas las tareas provistas en la lista se hayan completado.
     *
     * @param futuros lista de futuros
     */
    public static void esperarQueTerminenTodos(List<CompletableFuture<?>> futuros) {
        // TODO extra: Reto Extra 7: Barrera de sincronización para múltiples futuros concurrentes.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 8: Carrera de futuros asíncronos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 9: Transformaciones no bloqueantes de resultados asíncronos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 10: Composición secuencial y encadenamiento asíncrono.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encadenarFuturosAsincronos");
    }

}
