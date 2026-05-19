package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: declara/usa una estructura interna Map<ID,T> como almacén.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: obtén la clave aplicando idExtractor.apply(entity).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: inserta en el mapa (put reemplaza si la clave ya existía -> upsert).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve la entidad guardada.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: recupera del mapa el valor asociado a 'id'.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: envuélvelo con Optional.ofNullable (ausente -> Optional.empty()).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: crea una nueva lista a partir de los values() del mapa.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve una copia (no expongas la colección interna mutable).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: usa map.remove(id) y observa si devolvió algo distinto de null.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve true si había una entidad con esa clave, false si no.
    }

}
