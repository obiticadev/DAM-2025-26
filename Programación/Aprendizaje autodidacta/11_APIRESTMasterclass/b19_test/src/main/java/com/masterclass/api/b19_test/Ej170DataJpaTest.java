package com.masterclass.api.b19_test;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 170 · {@code @DataJpaTest} (repositorio en memoria como doble).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.7).
 *
 * <p>{@code @DataJpaTest} arrancaría JPA + H2 con rollback por test. Aquí
 * modelamos la PIEZA bajo prueba: un repositorio en memoria
 * ({@link RepoMem170}) y una consulta derivada {@code findByEdadMayorQue}
 * implementada como filtro puro, validable sin Spring.
 */
public final class Ej170DataJpaTest {

    private Ej170DataJpaTest() {
    }

    /**
     * Simula {@code findByEdadGreaterThan} sobre un repositorio en memoria.
     *
     * @param repo repositorio en memoria (no null)
     * @param edad umbral exclusivo
     * @return nombres cuya edad &gt; umbral, ordenados ascendentemente por nombre
     * @throws IllegalArgumentException si repo es null
     */
    public static List<String> findByEdadMayorQue(RepoMem170 repo, int edad) {
        // TODO 1: si repo es null -> IllegalArgumentException.
        // TODO 2: obtén todas las entidades con repo.findAll().
        // TODO 3: filtra las entradas cuya edad sea estrictamente > edad.
        // TODO 4: queda con la clave (nombre) de cada entrada que pasa el filtro.
        // TODO 5: ordena los nombres ascendentemente (consulta JPA con OrderBy).
        // TODO 6: recoge a List.
        // TODO 7: no mutes el mapa interno del repositorio (aislamiento del test).
        // TODO 8: si nadie supera el umbral -> devuelve lista vacía, no null.
        // TODO 9: este filtro modela la query derivada que JPA generaría.
        // TODO 10: devuelve la lista resultante.
        return List.of();
    }

    public static void main(String[] args) {
        RepoMem170 repo = () -> Map.of("Ada", 30, "Bob", 18);
        System.out.println(findByEdadMayorQue(repo, 20));
    }

        /**
     * RETO EXTRA 01: Cuenta total de entidades en el repo.
     */
    public static int contarEntidades(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — equivalente a repo.count() de Spring Data.
        // return repo.findAll().size();
        // El test (repo con 2 entradas) espera 2. findAll() devuelve el mapa
        // nombre→edad; size() cuenta sus entradas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEntidades");
    }

    /**
     * RETO EXTRA 02: Obtiene edad de una persona.
     */
    public static int obtenerEdadDe(RepoMem170 repo, String nombre) {
        // GUÍA: teoría 19.7 — busca por clave (como un findById).
        // return repo.findAll().get(nombre);
        // El test ({Ada:25}, "Ada") espera 25. Map.get devuelve el valor (la
        // edad) o null si la clave no existe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEdadDe");
    }

    /**
     * RETO EXTRA 03: Comprueba si la lista esta vacia.
     */
    public static boolean esListaVacia(java.util.List<String> list) {
        // GUÍA: teoría 19.7 — una línea: return list.isEmpty();
        // El test (List.of()) espera true. isEmpty() es más expresivo que
        // size() == 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esListaVacia");
    }

    /**
     * RETO EXTRA 04: Verifica existencia del nombre.
     */
    public static boolean contieneNombre(RepoMem170 repo, String nombre) {
        // GUÍA: teoría 19.7 — equivalente a repo.existsById(nombre).
        // return repo.findAll().containsKey(nombre);
        // El test ({Ada:25}, "Ada") espera true. containsKey comprueba la
        // existencia de la clave sin traer el valor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneNombre");
    }

    /**
     * RETO EXTRA 05: Obtiene todas las edades.
     */
    public static java.util.List<Integer> obtenerEdades(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — proyecta solo los valores (las edades).
        // return new ArrayList<>(repo.findAll().values());
        // El test ({A:10}) espera size()==1. Map.values() devuelve los valores;
        // envuélvelos en una lista NUEVA (no expongas la colección interna del
        // mapa — error común nº 7 del bloque, aislamiento).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEdades");
    }

    /**
     * RETO EXTRA 06: Calcula la edad maxima registrada.
     */
    public static int edadMaxima(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — agregación MAX (como un SELECT MAX(edad)).
        // return repo.findAll().values().stream().mapToInt(Integer::intValue).max().getAsInt();
        // El test ({Ada:30, Bob:18}) espera 30. mapToInt convierte a IntStream
        // (su max() devuelve OptionalInt → getAsInt). Estas agregaciones son lo
        // que en JPA harías con @Query("SELECT MAX(p.edad) ...").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadMaxima");
    }

    /**
     * RETO EXTRA 07: Calcula la edad minima registrada.
     */
    public static int edadMinima(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — agregación MIN, simétrica al reto 6.
        // return repo.findAll().values().stream().mapToInt(Integer::intValue).min().getAsInt();
        // El test ({Ada:30, Bob:18}) espera 18.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadMinima");
    }

    /**
     * RETO EXTRA 08: Verifica si el repo esta vacio.
     */
    public static boolean estaVacio(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — una línea: return repo.findAll().isEmpty();
        // El test (Map::of, repo vacío) espera true. No confundas con
        // esListaVacia (reto 3): aquí preguntas al REPO, no a una lista dada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacio");
    }

    /**
     * RETO EXTRA 09: Calcula la edad promedio de las personas.
     */
    public static double edadPromedio(RepoMem170 repo) {
        // GUÍA: teoría 19.7 — agregación AVG.
        // return repo.findAll().values().stream().mapToInt(Integer::intValue).average().getAsDouble();
        // El test ({Ada:30, Bob:18}) espera 24.0 = (30+18)/2. average() devuelve
        // OptionalDouble (vacío si no hay elementos) → getAsDouble.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadPromedio");
    }

    /**
     * RETO EXTRA 10: Nombres que superan la edad dada.
     */
    public static java.util.List<String> nombresMayoresQue(RepoMem170 repo, int edad) {
        // GUÍA: teoría 19.7 — es findByEdadMayorQue SIN el sorted final.
        // repo.findAll().entrySet().stream()
        //     .filter(e -> e.getValue() > edad)
        //     .map(Map.Entry::getKey)
        //     .toList();
        // El test ({Ada:30, Bob:18}, 20) espera size()==1 (solo Ada > 20).
        // Reutiliza el patrón del ejercicio base; aquí el test solo mira el
        // tamaño, no el orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresMayoresQue");
    }

}

/** Repositorio en memoria (doble de @DataJpaTest): nombre -> edad. */
@FunctionalInterface
interface RepoMem170 {
    /** @return mapa nombre->edad de todas las entidades */
    Map<String, Integer> findAll();
}
