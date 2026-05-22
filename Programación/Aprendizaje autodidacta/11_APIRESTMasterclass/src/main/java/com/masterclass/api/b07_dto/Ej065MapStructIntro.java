package com.masterclass.api.b07_dto;

import java.util.function.Function;

/**
 * Ejercicio 065 · Mapper "declarativo" (idea de MapStruct sin la librería).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.3).
 *
 * <p>MapStruct genera mappers en compilación. Aquí simulamos la idea: un
 * registro de funciones de mapeo reutilizables y componibles.
 */
public final class Ej065MapStructIntro {

    public record Origen(String a, int b) {
    }

    public record Destino(String a, int bDoble) {
    }

    private Ej065MapStructIntro() {
    }

    /**
     * Devuelve una función de mapeo declarativa Origen -> Destino.
     *
     * @return función que copia 'a' y duplica 'b' en 'bDoble'
     */
    public static Function<Origen, Destino> mapper() {
        // TODO 1: declara una lambda Function<Origen,Destino>.
        // TODO 2: en ella, valida que el origen no sea null.
        // TODO 3: mapea campo 'a' directamente (mismo nombre/tipo).
        // TODO 4: mapea 'b' a 'bDoble' aplicando *2 (transformación).
        // TODO 5: construye y devuelve el Destino dentro de la lambda.
        // TODO 6: devuelve la propia función (declarativa, reutilizable).
        return null;
    }

    /**
     * Compone el mapper con un post-procesado que pone 'a' en mayúsculas.
     *
     * @return función Origen -> Destino con 'a' en mayúsculas
     */
    public static Function<Origen, Destino> mapperMayus() {
        // TODO 7: parte de mapper() (no reescribas el mapeo base).
        // TODO 8: usa andThen para post-procesar el Destino.
        // TODO 9: en el post, crea un Destino con a.toUpperCase() y el mismo bDoble.
        // TODO 10: devuelve la función compuesta.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(mapper().apply(new Origen("x", 5)));
    }

    /**
     * RETO EXTRA 1: Mapear una lista de elementos Origen a Destino.
     *
     * @param lista lista de origen
     * @return lista de destino mapeada
     */
    public static java.util.List<Destino> mapearLista(java.util.List<Origen> lista) {
        // TODO extra: RETO EXTRA 1: Mapear una lista de elementos Origen a Destino.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearLista");
    }

    /**
     * RETO EXTRA 2: Mapear y filtrar elementos con valores negativos.
     *
     * @param lista lista de origen
     * @return lista de destino filtrada
     */
    public static java.util.List<Destino> mapearConFiltro(java.util.List<Origen> lista) {
        // TODO extra: RETO EXTRA 2: Mapear y filtrar elementos con valores negativos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConFiltro");
    }

    /**
     * RETO EXTRA 3: Mapeo inverso de Destino a Origen.
     *
     * @param d destino
     * @return origen mapeado
     */
    public static Origen mapearInverso(Destino d) {
        // TODO extra: RETO EXTRA 3: Mapeo inverso de Destino a Origen.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearInverso");
    }

    /**
     * RETO EXTRA 4: Mapear con valor por defecto.
     *
     * @param o origen
     * @param defecto valor por defecto para 'a'
     * @return destino con valor por defecto
     */
    public static Destino mapearConDefault(Origen o, String defecto) {
        // TODO extra: RETO EXTRA 4: Mapear con valor por defecto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConDefault");
    }

    /**
     * RETO EXTRA 5: Mapeo con prefijo en campo string.
     *
     * @param o origen
     * @param prefijo prefijo a añadir a 'a'
     * @return destino
     */
    public static Destino mapearConPrefijo(Origen o, String prefijo) {
        // TODO extra: RETO EXTRA 5: Mapeo con prefijo en campo string.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConPrefijo");
    }

    /**
     * RETO EXTRA 6: Comprobar equivalencia lógica entre Origen y Destino.
     *
     * @param o origen
     * @param d destino
     * @return true si coinciden en 'a' y d.bDoble == o.b * 2
     */
    public static boolean esEquivalente(Origen o, Destino d) {
        // TODO extra: RETO EXTRA 6: Comprobar equivalencia lógica entre Origen y Destino.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEquivalente");
    }

    /**
     * RETO EXTRA 7: Obtener el valor máximo de bDoble tras el mapeo.
     *
     * @param lista lista de origen
     * @return máximo bDoble, o 0 si la lista está vacía
     */
    public static int obtenerMaxBDoble(java.util.List<Origen> lista) {
        // TODO extra: RETO EXTRA 7: Obtener el valor máximo de bDoble tras el mapeo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMaxBDoble");
    }

    /**
     * RETO EXTRA 8: Mapear solo el campo numérico.
     *
     * @param o origen
     * @return destino con 'a' nulo
     */
    public static Destino mapearSoloB(Origen o) {
        // TODO extra: RETO EXTRA 8: Mapear solo el campo numérico.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearSoloB");
    }

    /**
     * RETO EXTRA 9: Mapeo seguro envolviendo en Optional.
     *
     * @param o origen
     * @return Optional con Destino si origen no es nulo
     */
    public static java.util.Optional<Destino> mapearConSafeCast(Origen o) {
        // TODO extra: RETO EXTRA 9: Mapeo seguro envolviendo en Optional.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConSafeCast");
    }

    /**
     * RETO EXTRA 10: Mapeo con sufijo en campo string.
     *
     * @param o origen
     * @param sufijo sufijo
     * @return destino
     */
    public static Destino mapearConSuffix(Origen o, String sufijo) {
        // TODO extra: RETO EXTRA 10: Mapeo con sufijo en campo string.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConSuffix");
    }

}
