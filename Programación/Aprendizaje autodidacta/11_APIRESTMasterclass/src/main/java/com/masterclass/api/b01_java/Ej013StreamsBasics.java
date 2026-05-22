package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 013 · Streams básicos: filter/map/collect.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej013StreamsBasics {

    private Ej013StreamsBasics() {
    }

    /**
     * Devuelve los nombres en mayúsculas de los productos cuyo precio supera un umbral.
     *
     * @param precios lista de pares [nombre, precio] ya emparejados como entradas
     * @param umbral  precio mínimo estricto
     * @return lista de nombres en mayúsculas, en el mismo orden
     */
    public static List<String> nombresCarosEnMayus(List<java.util.Map.Entry<String, Double>> precios, double umbral) {
        // TODO 1: abre un stream sobre 'precios'.
        // TODO 2: filtra las entradas cuyo getValue() sea > umbral (estricto).
        // TODO 3: mapea cada entrada a su getKey().
        // TODO 4: transforma cada clave a mayúsculas.
        // TODO 5: recoge a List preservando el orden y devuélvela.
        return List.of();
    }

    /**
     * Suma total de una lista de importes.
     *
     * @param importes valores a sumar
     * @return suma; 0.0 si la lista está vacía
     */
    public static double total(List<Double> importes) {
        // TODO 6: usa mapToDouble(Double::doubleValue) sobre el stream.
        // TODO 7: aplica sum() (el caso lista vacía debe dar 0.0 de forma natural).
        return -1;
    }

    /**
     * Cuenta cuántos elementos cumplen ser pares.
     *
     * @param numeros lista de enteros
     * @return cantidad de pares
     */
    public static long contarPares(List<Integer> numeros) {
        // TODO 8: abre stream sobre 'numeros'.
        // TODO 9: filtra los que cumplan n % 2 == 0.
        // TODO 10: devuelve count().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(total(List.of(10.0, 20.0, 5.5)));
        System.out.println(contarPares(List.of(1, 2, 3, 4)));
    }

    /**
     * Reto Extra 1: Filtrado numérico simple.
     * Filtra los números para obtener sólo los mayores de edad (mayor o igual a 18).
     *
     * @param edades lista de edades
     * @return lista de mayores de edad
     */
    public static List<Integer> filtrarMayoresDeEdad(List<Integer> edades) {
        // TODO extra: Reto Extra 1: Filtrado numérico simple.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarMayoresDeEdad");
    }

    /**
     * Reto Extra 2: Mapeo de cadenas de texto.
     * Convierte palabras a mayúsculas, ignorando nulos y vacíos.
     *
     * @param palabras lista de palabras
     * @return lista de palabras en mayúsculas filtradas
     */
    public static List<String> convertirAMayusculas(List<String> palabras) {
        // TODO extra: Reto Extra 2: Mapeo de cadenas de texto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirAMayusculas");
    }

    /**
     * Reto Extra 3: Reducción básica.
     * Calcula la suma de los cuadrados de los números de la lista.
     *
     * @param numeros lista de números
     * @return suma de los cuadrados
     */
    public static int calcularSumaCuadrados(List<Integer> numeros) {
        // TODO extra: Reto Extra 3: Reducción básica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularSumaCuadrados");
    }

    /**
     * Reto Extra 4: Operación de conteo terminal.
     * Cuenta cuántas cadenas en la lista son vacías (o contienen sólo espacios en blanco).
     *
     * @param cadenas lista de cadenas
     * @return cantidad de cadenas vacías
     */
    public static long contarCadenasVacias(List<String> cadenas) {
        // TODO extra: Reto Extra 4: Operación de conteo terminal.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCadenasVacias");
    }

    /**
     * Reto Extra 5: Colector Collectors.joining.
     * Une las palabras con coma y espacio, y las envuelve en corchetes "[palabra1, palabra2]".
     *
     * @param cadenas lista de palabras
     * @return cadena con el formato especificado
     */
    public static String unirConComas(List<String> cadenas) {
        // TODO extra: Reto Extra 5: Colector Collectors.joining.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unirConComas");
    }

    /**
     * Reto Extra 6: Deduplicación y ordenación.
     * Retorna una lista con los elementos únicos (sin duplicados) ordenados alfabéticamente.
     *
     * @param lista lista de palabras
     * @return lista sin duplicados y ordenada
     */
    public static List<String> obtenerElementosUnicos(List<String> lista) {
        // TODO extra: Reto Extra 6: Deduplicación y ordenación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerElementosUnicos");
    }

    /**
     * Reto Extra 7: Paginación con skip y limit.
     * Omite los primeros 'skip' elementos y toma como máximo 'limit' elementos.
     *
     * @param lista lista de palabras
     * @param skip  elementos a omitir
     * @param limit cantidad máxima a retornar
     * @return sublista paginada
     */
    public static List<String> limitarYDescartar(List<String> lista, int skip, int limit) {
        // TODO extra: Reto Extra 7: Paginación con skip y limit.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limitarYDescartar");
    }

    /**
     * Reto Extra 8: Búsqueda con anyMatch.
     * Comprueba si al menos una palabra de la lista empieza por el prefijo indicado.
     *
     * @param palabras lista de palabras
     * @param prefijo  prefijo a buscar (insensible a mayúsculas/minúsculas)
     * @return true si alguna coincide, false de lo contrario
     */
    public static boolean algunoEmpiezaCon(List<String> palabras, String prefijo) {
        // TODO extra: Reto Extra 8: Búsqueda con anyMatch.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para algunoEmpiezaCon");
    }

    /**
     * Reto Extra 9: Validación universal con allMatch.
     * Comprueba si todos los números de la lista son estrictamente positivos (> 0).
     *
     * @param numeros lista de números
     * @return true si todos son positivos o si la lista está vacía, false de lo contrario
     */
    public static boolean todosSonPositivos(List<Integer> numeros) {
        // TODO extra: Reto Extra 9: Validación universal con allMatch.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para todosSonPositivos");
    }

    /**
     * Reto Extra 10: Reducción con max.
     * Encuentra el número máximo de la lista.
     *
     * @param numeros lista de números
     * @return Optional con el máximo, o vacío si la lista no contiene números
     */
    public static Optional<Integer> obtenerMaximo(List<Integer> numeros) {
        // TODO extra: Reto Extra 10: Reducción con max.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMaximo");
    }

}
