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
        // TODO extra: Retorna el primer elemento envuelto en un Optional, defendiendo contra nulos
        return Optional.empty();
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
        // TODO extra: Intercambia los elementos en las posiciones i y j de manera segura
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
        // TODO extra: Busca por id en el mapa de forma segura
        return Optional.empty();
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
        // TODO extra: Filtra los elementos de la lista usando stream y el predicado dado
        return List.of();
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
        // TODO extra: Transforma la lista a un mapa usando stream y keyExtractor
        return Map.of();
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
        // TODO extra: Usa compareTo para comparar de forma segura (defensa contra nulos)
        return false;
    }

    /**
     * Reto Extra 7: Modelado de pares genéricos inmutables.
     */
    public record Tupla<K, V>(K clave, V valor) {}

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
        // TODO extra: Instancia y retorna una Tupla con los valores provistos
        return null;
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
        // TODO extra: Retorna valor si no es nulo, de lo contrario valorPorDefecto
        return null;
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
        // TODO extra: Recorre el array y cuenta cuántos elementos coinciden de forma segura
        return 0;
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
        // TODO extra: Revierte la lista de forma no destructiva y retórnala
        return List.of();
    }

}
