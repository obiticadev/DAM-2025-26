package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Ejercicio 015 · Repositorio genérico en memoria.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.4).
 *
 * <p>Un único repositorio reutilizable para cualquier entidad con id de tipo ID.
 */
public class Ej015GenericsRepository<T, ID> {

    private final Function<T, ID> idExtractor;

    /**
     * @param idExtractor función que, dada una entidad, devuelve su identificador
     */
    public Ej015GenericsRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    /**
     * Guarda (o reemplaza si ya existe ese id) una entidad.
     *
     * @param entity entidad a persistir
     * @return la propia entidad guardada
     */
    public T save(T entity) {
        // TODO 1: declara/usa una estructura interna Map<ID,T> como almacén.
        // TODO 2: obtén la clave aplicando idExtractor.apply(entity).
        // TODO 3: inserta en el mapa (put reemplaza si la clave ya existía -> upsert).
        // TODO 4: devuelve la entidad guardada.
        return null;
    }

    /**
     * Busca por identificador.
     *
     * @param id identificador
     * @return Optional con la entidad o vacío
     */
    public Optional<T> findById(ID id) {
        // TODO 5: recupera del mapa el valor asociado a 'id'.
        // TODO 6: envuélvelo con Optional.ofNullable (ausente -> Optional.empty()).
        return Optional.empty();
    }

    /**
     * @return todas las entidades guardadas (orden no garantizado)
     */
    public List<T> findAll() {
        // TODO 7: crea una nueva lista a partir de los values() del mapa.
        // TODO 8: devuelve una copia (no expongas la colección interna mutable).
        return List.of();
    }

    /**
     * Borra por id.
     *
     * @param id identificador
     * @return true si existía y se borró
     */
    public boolean deleteById(ID id) {
        // TODO 9: usa map.remove(id) y observa si devolvió algo distinto de null.
        // TODO 10: devuelve true si había una entidad con esa clave, false si no.
        return false;
    }

    public static void main(String[] args) {
        var repo = new Ej015GenericsRepository<String, Integer>(String::length);
        repo.save("hola");
        System.out.println(repo.findById(4));
    }

    /**
     * Reto Extra 1: Operaciones genéricas con arrays.
     * Retorna el primer elemento de un array genérico, o Optional.empty() si el array es nulo o vacío.
     *
     * @param elementos array genérico
     * @param <E>       tipo de los elementos
     * @return Optional con el primer elemento
     */
    public static <E> Optional<E> obtenerPrimero(E[] elementos) {
        // TODO extra: Reto Extra 1: Operaciones genéricas con arrays.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrimero");
    }

    /**
     * Reto Extra 2: Algoritmos sobre vectores genéricos.
     * Intercambia las posiciones de dos elementos en un array genérico.
     *
     * @param array array genérico
     * @param i     primer índice
     * @param j     segundo índice
     * @param <E>   tipo de los elementos
     * @throws IllegalArgumentException si los índices están fuera de rango o el array es nulo
     */
    public static <E> void intercambiarPosiciones(E[] array, int i, int j) {
        // TODO extra: Reto Extra 2: Algoritmos sobre vectores genéricos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intercambiarPosiciones");
    }

    /**
     * Reto Extra 3: Búsqueda genérica en Mapas.
     * Busca una clave en un mapa y devuelve un Optional con el valor correspondiente.
     *
     * @param mapa mapa genérico
     * @param id   clave a buscar
     * @param <K>  tipo de la clave
     * @param <V>  tipo del valor
     * @return Optional con el valor o vacío
     */
    public static <K, V> Optional<V> buscarPorIdEnMapa(Map<K, V> mapa, K id) {
        // TODO extra: Reto Extra 3: Búsqueda genérica en Mapas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorIdEnMapa");
    }

    /**
     * Reto Extra 4: Filtrado genérico funcional.
     * Filtra los elementos de una lista dada una condición funcional (Predicate).
     *
     * @param lista  lista original
     * @param filtro condición
     * @param <E>    tipo de los elementos
     * @return lista con los elementos filtrados
     */
    public static <E> List<E> filtrarElementosGenericos(List<E> lista, Predicate<E> filtro) {
        // TODO extra: Reto Extra 4: Filtrado genérico funcional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarElementosGenericos");
    }

    /**
     * Reto Extra 5: Transformador genérico de colecciones.
     * Convierte una lista de valores en un mapa asociando cada valor a su clave extraída.
     *
     * @param lista        lista de valores
     * @param keyExtractor función para extraer la clave de cada valor
     * @param <K>          tipo de la clave
     * @param <V>          tipo del valor
     * @return mapa resultante
     */
    public static <K, V> Map<K, V> convertirListaAMapa(List<V> lista, Function<V, K> keyExtractor) {
        // TODO extra: Reto Extra 5: Transformador genérico de colecciones.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirListaAMapa");
    }

    /**
     * Reto Extra 6: Genéricos acotados por comparación.
     * Comprueba si el primer elemento es estrictamente mayor que el límite.
     *
     * @param actual valor actual
     * @param limite valor límite
     * @param <E>    tipo comparable del elemento
     * @return true si es mayor, false de lo contrario
     */
    public static <E extends Comparable<E>> boolean esMayorQue(E actual, E limite) {
        // TODO extra: Reto Extra 6: Genéricos acotados por comparación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMayorQue");
    }

    /**
     * Reto Extra 7: Modelado de pares genéricos inmutables.
     */
    public record Tupla<K, V>(K clave, V valor) {
        // TODO extra: Reto Extra 7: Modelado de pares genéricos inmutables.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7 (método): Crea una tupla clave-valor inmutable.
     *
     * @param clave clave de la tupla
     * @param valor valor de la tupla
     * @param <K>   tipo de la clave
     * @param <V>   tipo del valor
     * @return nueva Tupla instanciada
     */
    public static <K, V> Tupla<K, V> crearTupla(K clave, V valor) {
        // TODO extra: Reto Extra 7 (método): Crea una tupla clave-valor inmutable.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTupla");
    }

    /**
     * Reto Extra 8: Manejo seguro de nulos genéricos.
     * Devuelve el valor provisto, o el valor por defecto si el provisto es nulo.
     *
     * @param valor            valor a evaluar
     * @param valorPorDefecto  valor fallback
     * @param <E>              tipo del elemento
     * @return el elemento o su valor por defecto
     */
    public static <E> E obtenerValorConDefecto(E valor, E valorPorDefecto) {
        // TODO extra: Reto Extra 8: Manejo seguro de nulos genéricos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerValorConDefecto");
    }

    /**
     * Reto Extra 9: Conteo universal sobre arrays.
     * Cuenta cuántas ocurrencias de un elemento dado existen en el array.
     *
     * @param array    array genérico
     * @param elemento elemento a contar
     * @param <E>      tipo de los elementos
     * @return cantidad de ocurrencias
     */
    public static <E> long contarOcurrenciasGenerico(E[] array, E elemento) {
        // TODO extra: Reto Extra 9: Conteo universal sobre arrays.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarOcurrenciasGenerico");
    }

    /**
     * Reto Extra 10: Reversión de colecciones genéricas.
     * Retorna una nueva lista que contiene los mismos elementos de la lista original pero en orden inverso.
     *
     * @param lista lista original
     * @param <E>   tipo de los elementos
     * @return nueva lista revertida
     */
    public static <E> List<E> revertirListaGenerica(List<E> lista) {
        // TODO extra: Reto Extra 10: Reversión de colecciones genéricas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para revertirListaGenerica");
    }

}
