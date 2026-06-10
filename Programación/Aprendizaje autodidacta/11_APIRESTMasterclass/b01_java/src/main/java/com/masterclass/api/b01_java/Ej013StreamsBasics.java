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
        // GUÍA: teoría 1.3, patrón 1 (filtrar).
        // edades.stream().filter(e -> e >= 18).toList();
        // OJO al test: 18 SÍ entra (>= , no >). Defensa: si edades es null,
        // devuelve List.of().
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
        // GUÍA: filtra ANTES de mapear (si mapeas un null → NPE):
        // palabras.stream()
        //         .filter(p -> p != null && !p.isBlank())  // fuera nulls y vacíos
        //         .map(String::toUpperCase)
        //         .toList();
        // El test mete null y "" en la lista a propósito (con Arrays.asList,
        // porque List.of no admite nulls — detalle que conviene saber).
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
        // GUÍA: map + reducción numérica (teoría 1.3, patrón 2):
        // numeros.stream().mapToInt(n -> n * n).sum();
        // mapToInt produce un IntStream, que trae sum() gratis (y para lista
        // vacía devuelve 0, como exige el test). Sin mapToInt tendrías que usar
        // reduce(0, Integer::sum) — también vale, compara ambas.
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
        // GUÍA: cadenas.stream().filter(String::isBlank).count();
        // isBlank() cubre "" Y "   " (solo espacios), justo los dos casos del
        // test. count() devuelve long — fíjate en el tipo de retorno del método.
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
        // GUÍA: joining tiene una sobrecarga con (separador, prefijo, sufijo):
        // cadenas.stream().collect(Collectors.joining(", ", "[", "]"));
        // Con lista vacía produce "[]" solo — exactamente el segundo test.
        // No concatenes los corchetes a mano: esta sobrecarga existe para eso.
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
        // GUÍA: dos intermedias encadenadas:
        // lista.stream().distinct().sorted().toList();
        // distinct() usa equals (dedup), sorted() sin argumentos usa el orden
        // natural (alfabético en String). El orden distinct→sorted o
        // sorted→distinct da igual aquí; piensa por qué.
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
        // GUÍA: lista.stream().skip(skip).limit(limit).toList();
        // ESTO ES LA PAGINACIÓN: ?page=2&size=10 de una API es skip(20).limit(10).
        // Cuando llegues a Pageable en Spring Data (b12) será esta misma idea.
        // skip/limit toleran pasarse del tamaño (segundo test: limit 5 sobre 2
        // elementos → devuelve los 2, sin excepción).
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
        // GUÍA: insensible a mayúsculas → normaliza AMBOS lados a minúsculas:
        // String pref = prefijo.toLowerCase();
        // return palabras.stream().anyMatch(p -> p.toLowerCase().startsWith(pref));
        // El test busca "sp" y debe encontrar "Spring". anyMatch cortocircuita:
        // en cuanto encuentra uno, deja de recorrer.
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
        // GUÍA: numeros.stream().allMatch(n -> n > 0);
        // DETALLE LÓGICO que comprueba el test: allMatch sobre lista VACÍA da
        // true ("verdad vacua": no hay ningún elemento que incumpla). Es el
        // comportamiento matemático correcto del cuantificador universal, y
        // sorprende la primera vez. anyMatch sobre vacía da false, simétricamente.
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
        // GUÍA: numeros.stream().max(Integer::compare);
        // max necesita un Comparator (también vale Comparator.naturalOrder())
        // y devuelve Optional<Integer> — vacío si la lista lo está. Fíjate:
        // streams y Optional se diseñaron juntos; toda reducción que puede
        // "no tener respuesta" (max, min, findFirst, reduce sin identidad)
        // devuelve Optional en vez de null. Eso ata las secciones 1.2 y 1.3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMaximo");
    }

}
