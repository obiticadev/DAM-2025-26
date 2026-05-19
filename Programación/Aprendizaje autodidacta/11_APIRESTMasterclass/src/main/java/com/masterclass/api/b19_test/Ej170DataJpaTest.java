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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si repo es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: obtén todas las entidades con repo.findAll().
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: filtra las entradas cuya edad sea estrictamente > edad.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: queda con la clave (nombre) de cada entrada que pasa el filtro.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: ordena los nombres ascendentemente (consulta JPA con OrderBy).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: recoge a List.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: no mutes el mapa interno del repositorio (aislamiento del test).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si nadie supera el umbral -> devuelve lista vacía, no null.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: este filtro modela la query derivada que JPA generaría.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista resultante.
    }

}

/** Repositorio en memoria (doble de @DataJpaTest): nombre -> edad. */
@FunctionalInterface
interface RepoMem170 {
    /** @return mapa nombre->edad de todas las entidades */
    Map<String, Integer> findAll();
}
