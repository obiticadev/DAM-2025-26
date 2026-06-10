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
        // GUÍA: teoría 1.7 (composición) — una línea: return p1.and(p2);
        // Predicate YA trae and() como default method. Entiende qué devuelve:
        // no un boolean, sino un NUEVO Predicate que, al hacer test(x),
        // evalúa p1.test(x) && p2.test(x) (con cortocircuito incluido).
        // Devolver funciones que combinan funciones: eso es composición.
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
        // GUÍA: una línea — return p1.or(p2);
        // Repasa la tabla de verdad con los tests: 6 (>5 y par) ✔, 4 (par) ✔,
        // 7 (>5) ✔, 3 (ni una ni otra) ✘.
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
        // GUÍA: una línea — return p.negate();
        // ÚSALO LUEGO en streams: .filter(Predicate.not(String::isBlank)) es
        // la forma idiomática de "filtra los NO vacíos" — Predicate.not es la
        // versión estática de este mismo negate.
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
        // GUÍA: una línea — return f1.andThen(f2);
        // SIGUE LOS TIPOS: f1 es A→B, f2 es B→C, el resultado A→C. Con el test:
        // 5 → (n*2) → 10 → ("res: "+n) → "res: 10".
        // OJO con el espejo: f1.andThen(f2) = "f1 primero"; f1.compose(f2) =
        // "f2 primero". Confundirlos compila y falla en runtime: dibuja la
        // flecha de tipos cuando dudes.
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
        // GUÍA: el patrón "cronómetro envolvente":
        // long inicio = System.nanoTime();   // nanoTime, NO currentTimeMillis
        // tarea.run();
        // return (System.nanoTime() - inicio) / 1_000_000;  // ns → ms
        // ¿Por qué nanoTime? currentTimeMillis es la HORA del sistema (puede
        // saltar por NTP); nanoTime es un cronómetro monotónico, lo correcto
        // para medir duraciones. Este envoltorio es la semilla de los
        // interceptores de métricas que verás en b20.
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
        // GUÍA: una línea — lista.forEach(consumidor);
        // El test pasa dest::add como Consumer: una referencia a método de
        // instancia sobre un objeto concreto (tercer tipo de :: de la teoría
        // 1.7). Defensa: lista null → no hagas nada.
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
        // GUÍA: una línea — return creador.get();
        // Parece trivial, pero la idea es grande: quien LLAMA decide CÓMO se
        // construye el objeto, y la construcción se DIFIERE hasta este get().
        // Spring usa Suppliers así para crear beans perezosos, y tú ya lo
        // usaste sin saberlo en orElseGet (Ej012 reto 4).
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
        // GUÍA: una línea — return c1.andThen(c2);
        // Consumer también compone: el combinado ejecuta c1 y LUEGO c2 con el
        // mismo argumento. El test verifica el ORDEN exacto (c1 antes que c2).
        // Caso real: consumer de log + consumer de métricas encadenados.
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
        // GUÍA: junta tus transformar() y filtrar() de arriba en un pipeline:
        // return lista.stream().filter(p).map(f).toList();
        // ORDEN: filtra ANTES de transformar (transformar lo que vas a
        // descartar es trabajo tirado). Acabas de escribir la firma genérica
        // del 80% de los métodos de servicio de una API (teoría 1.3).
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
        // GUÍA: tu seguroOrElse de arriba, con fallback PEREZOSO:
        // try { return principal.get(); }
        // catch (RuntimeException e) { return fallback.get(); }
        // Diferencia clave con seguroOrElse(s, valorFijo): aquí el plan B
        // también se calcula bajo demanda. Es el esqueleto del patrón
        // "circuit breaker con fallback" que verás en resiliencia (b21):
        // intenta el servicio, y si falla, sirve la caché.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerSeguroConSupplier");
    }

}
