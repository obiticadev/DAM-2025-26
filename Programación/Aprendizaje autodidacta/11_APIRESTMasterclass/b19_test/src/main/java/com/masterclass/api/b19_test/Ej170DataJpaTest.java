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
        // TODO extra: RETO EXTRA 01: Cuenta total de entidades en el repo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEntidades");
    }

    /**
     * RETO EXTRA 02: Obtiene edad de una persona.
     */
    public static int obtenerEdadDe(RepoMem170 repo, String nombre) {
        // TODO extra: RETO EXTRA 02: Obtiene edad de una persona.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEdadDe");
    }

    /**
     * RETO EXTRA 03: Comprueba si la lista esta vacia.
     */
    public static boolean esListaVacia(java.util.List<String> list) {
        // TODO extra: RETO EXTRA 03: Comprueba si la lista esta vacia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esListaVacia");
    }

    /**
     * RETO EXTRA 04: Verifica existencia del nombre.
     */
    public static boolean contieneNombre(RepoMem170 repo, String nombre) {
        // TODO extra: RETO EXTRA 04: Verifica existencia del nombre.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneNombre");
    }

    /**
     * RETO EXTRA 05: Obtiene todas las edades.
     */
    public static java.util.List<Integer> obtenerEdades(RepoMem170 repo) {
        // TODO extra: RETO EXTRA 05: Obtiene todas las edades.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEdades");
    }

    /**
     * RETO EXTRA 06: Calcula la edad maxima registrada.
     */
    public static int edadMaxima(RepoMem170 repo) {
        // TODO extra: RETO EXTRA 06: Calcula la edad maxima registrada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadMaxima");
    }

    /**
     * RETO EXTRA 07: Calcula la edad minima registrada.
     */
    public static int edadMinima(RepoMem170 repo) {
        // TODO extra: RETO EXTRA 07: Calcula la edad minima registrada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadMinima");
    }

    /**
     * RETO EXTRA 08: Verifica si el repo esta vacio.
     */
    public static boolean estaVacio(RepoMem170 repo) {
        // TODO extra: RETO EXTRA 08: Verifica si el repo esta vacio.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacio");
    }

    /**
     * RETO EXTRA 09: Calcula la edad promedio de las personas.
     */
    public static double edadPromedio(RepoMem170 repo) {
        // TODO extra: RETO EXTRA 09: Calcula la edad promedio de las personas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadPromedio");
    }

    /**
     * RETO EXTRA 10: Nombres que superan la edad dada.
     */
    public static java.util.List<String> nombresMayoresQue(RepoMem170 repo, int edad) {
        // TODO extra: RETO EXTRA 10: Nombres que superan la edad dada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresMayoresQue");
    }

}

/** Repositorio en memoria (doble de @DataJpaTest): nombre -> edad. */
@FunctionalInterface
interface RepoMem170 {
    /** @return mapa nombre->edad de todas las entidades */
    Map<String, Integer> findAll();
}
