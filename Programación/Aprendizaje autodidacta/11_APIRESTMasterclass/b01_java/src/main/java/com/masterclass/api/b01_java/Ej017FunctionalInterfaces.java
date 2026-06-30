package com.masterclass.api.b01_java;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Consumer;

/**
 * Ejercicio 017 · Interfaces funcionales: Function, Predicate, Supplier.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej017FunctionalInterfaces {

    private Ej017FunctionalInterfaces() {
    }

    /**
     * Aplica una transformación a cada elemento.
     *
     * @param entrada lista origen
     * @param f       función de mapeo
     * @param <T>     tipo de entrada
     * @param <R>     tipo de salida
     * @return lista transformada
     */
    public static <T, R> List<R> transformar(List<T> entrada, Function<T, R> f) {
        // TODO 1: abre stream sobre 'entrada'.
        // TODO 2: aplica .map(f) para transformar cada elemento.
        // TODO 3: recoge a List preservando el orden y devuélvela.
        return List.of();
    }

    /**
     * Filtra por un predicado.
     *
     * @param entrada lista origen
     * @param p       condición
     * @return elementos que cumplen p
     */
    public static <T> List<T> filtrar(List<T> entrada, Predicate<T> p) {
        // TODO 4: abre stream sobre 'entrada'.
        // TODO 5: aplica .filter(p).
        // TODO 6: recoge a List y devuélvela.
        return List.of();
    }

    /**
     * Devuelve el valor del supplier, o un fallback si lanza excepción.
     *
     * @param s        proveedor que puede fallar
     * @param fallback valor por defecto
     * @return s.get() o fallback si s lanza
     */
    public static <T> T seguroOrElse(Supplier<T> s, T fallback) {
        // TODO 7: abre un bloque try alrededor de s.get().
        // TODO 8: si tiene éxito, devuelve ese valor.
        // TODO 9: captura cualquier RuntimeException sin propagarla.
        // TODO 10: en el catch, devuelve 'fallback'.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(transformar(List.of(1, 2, 3), x -> x * x));
    }

    /**
     * Reto Extra 1: Composición lógica AND de predicados.
     * Retorna un nuevo Predicate que representa la composición lógica AND de p1 y p2.
     *
     * @param p1  primer predicado
     * @param p2  segundo predicado
     * @param <T> tipo de los elementos evaluados
     * @return predicado combinado (AND)
     */
    public static <T> Predicate<T> combinarPredicadosAND(Predicate<T> p1, Predicate<T> p2) {
        // GUÍA: no calcules un boolean ahora; devuelve una nueva condición.
        // Esa condición solo debe aprobar un valor cuando las dos condiciones
        // originales lo aprueban también.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarPredicadosAND");
    }

    /**
     * Reto Extra 2: Composición lógica OR de predicados.
     * Retorna un nuevo Predicate que representa la composición lógica OR de p1 y p2.
     *
     * @param p1  primer predicado
     * @param p2  segundo predicado
     * @param <T> tipo de los elementos evaluados
     * @return predicado combinado (OR)
     */
    public static <T> Predicate<T> combinarPredicadosOR(Predicate<T> p1, Predicate<T> p2) {
        // GUÍA: la condición combinada debe aceptar un valor si al menos una de
        // las dos condiciones lo acepta. Repasa la tabla de verdad antes de codificar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarPredicadosOR");
    }

    /**
     * Reto Extra 3: Negación lógica funcional.
     * Retorna un nuevo Predicate que representa la negación de p.
     *
     * @param p   predicado original
     * @param <T> tipo de los elementos evaluados
     * @return predicado negado
     */
    public static <T> Predicate<T> negarPredicado(Predicate<T> p) {
        // GUÍA: devuelve una condición que invierta el resultado del predicado
        // original para cualquier valor recibido. Esta idea se usa mucho para
        // expresar filtros negativos de forma legible.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para negarPredicado");
    }

    /**
     * Reto Extra 4: Composición secuencial de funciones (andThen).
     * Compone dos funciones secuencialmente (f1 aplicada primero, f2 aplicada al resultado de f1).
     *
     * @param f1  primera función (A -> B)
     * @param f2  segunda función (B -> C)
     * @param <A> tipo de entrada de f1
     * @param <B> tipo intermedio de salida de f1 / entrada de f2
     * @param <C> tipo de salida final de f2
     * @return función compuesta A -> C
     */
    public static <A, B, C> Function<A, C> componerFunciones(Function<A, B> f1, Function<B, C> f2) {
        // GUÍA: sigue las flechas de tipos: la primera función produce justo lo
        // que la segunda necesita recibir. El orden es parte del contrato; si lo
        // inviertes, los tipos o el resultado dejarán de cuadrar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para componerFunciones");
    }

    /**
     * Reto Extra 5: Auditoría temporal de tareas.
     * Ejecuta una tarea Runnable y retorna el tiempo total empleado en milisegundos.
     *
     * @param tarea tarea a ejecutar y medir
     * @return tiempo total transcurrido en milisegundos
     */
    public static long ejecutarYMedirTiempo(Runnable tarea) {
        // GUÍA: mide duración, no hora de calendario. Toma una marca antes de
        // ejecutar la tarea y otra después, y convierte la diferencia a milisegundos.
        // Para medir intervalos, prefiere un reloj monotónico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarYMedirTiempo");
    }

    /**
     * Reto Extra 6: Iteración funcional controlada con Consumer.
     * Aplica el consumidor indicado sobre todos los elementos de la lista.
     *
     * @param lista      lista de elementos
     * @param consumidor acción a realizar
     * @param <T>        tipo de los elementos
     */
    public static <T> void consumirLista(List<T> lista, Consumer<T> consumidor) {
        // GUÍA: el Consumer representa una acción sobre cada elemento, no una
        // transformación con resultado. Aplica la acción en orden y decide si una
        // lista null debe ignorarse de forma segura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para consumirLista");
    }

    /**
     * Reto Extra 7: Factorías funcionales diferidas con Supplier.
     * Retorna una instancia creada a partir del Supplier provisto.
     *
     * @param creador proveedor de instancias
     * @param <T>     tipo del elemento retornado
     * @return instancia de tipo T
     */
    public static <T> T crearConSupplier(Supplier<T> creador) {
        // GUÍA: el Supplier encapsula una creación diferida. Tu método debe pedir
        // el valor en el momento adecuado y devolverlo sin conocer cómo se fabrica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearConSupplier");
    }

    /**
     * Reto Extra 8: Acción secuencial múltiple con Consumers.
     * Combina dos consumidores para que se ejecuten de forma consecutiva (c1 seguido de c2).
     *
     * @param c1  primer consumidor
     * @param c2  segundo consumidor
     * @param <T> tipo de los elementos
     * @return consumidor compuesto
     */
    public static <T> Consumer<T> encadenarConsumidores(Consumer<T> c1, Consumer<T> c2) {
        // GUÍA: devuelve una acción compuesta. Cuando reciba un valor, debe ejecutar
        // primero la primera acción y después la segunda con ese mismo valor.
        // El orden observable importa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encadenarConsumidores");
    }

    /**
     * Reto Extra 9: Canalizaciones personalizadas.
     * Filtra la lista por el predicado y transforma los elementos que coincidan usando la función dada.
     *
     * @param lista lista de elementos
     * @param p     filtro a aplicar
     * @param f     función de transformación
     * @param <T>   tipo de entrada
     * @param <R>   tipo de salida
     * @return lista resultante con elementos filtrados y transformados
     */
    public static <T, R> List<R> filtrarYTransformar(List<T> lista, Predicate<T> p, Function<T, R> f) {
        // GUÍA: combina una selección y una transformación. Filtrar antes evita
        // transformar elementos que no van a formar parte del resultado. Mantén
        // el orden de los elementos supervivientes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarYTransformar");
    }

    /**
     * Reto Extra 10: Tolerancia a fallos encadenada con Suppliers.
     * Obtiene el valor del Supplier principal. Si éste arroja una excepción, obtiene el valor del Supplier fallback.
     *
     * @param principal proveedor principal
     * @param fallback  proveedor secundario de contingencia
     * @param <T>       tipo del valor retornado
     * @return el resultado de principal, o el resultado de fallback ante errores de principal
     */
    public static <T> T obtenerSeguroConSupplier(Supplier<T> principal, Supplier<T> fallback) {
        // GUÍA: intenta obtener el valor principal. Solo si esa obtención falla
        // debe ejecutarse el proveedor de respaldo. El fallback también es perezoso:
        // no lo calcules antes de saber si hará falta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSeguroConSupplier");
    }

}
