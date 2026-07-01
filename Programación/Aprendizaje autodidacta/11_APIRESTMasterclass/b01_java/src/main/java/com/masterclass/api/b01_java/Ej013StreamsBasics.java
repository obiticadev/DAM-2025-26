package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Ejercicio 013 · Streams básicos: filter/map/collect.
 *
 * <p>
 * Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej013StreamsBasics {

    private Ej013StreamsBasics() {
    }

    /**
     * Devuelve los nombres en mayúsculas de los productos cuyo precio supera un
     * umbral.
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

        return precios.stream()
                .filter(a -> a.getValue() > umbral)
                .map(a -> a.getKey().toUpperCase())
                .toList();
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
        return importes.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
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
        return numeros.stream()
                .filter(a -> a % 2 == 0)
                .count();
    }

    public static void main(String[] args) {
        System.out.println(total(List.of(10.0, 20.0, 5.5)));
        System.out.println(contarPares(List.of(1, 2, 3, 4)));
    }

    /**
     * Reto Extra 1: Filtrado numérico simple.
     * Filtra los números para obtener sólo los mayores de edad (mayor o igual a
     * 18).
     *
     * @param edades lista de edades
     * @return lista de mayores de edad
     */
    public static List<Integer> filtrarMayoresDeEdad(List<Integer> edades) {
        // GUÍA: conserva solo las edades que cumplen el límite legal indicado.
        // El 18 entra; no lo trates como menor. Si decides proteger listas null,
        // devuelve una lista vacía en vez de propagar un fallo accidental.
        return edades.stream().filter(e -> e >= 18).toList();
    }

    /**
     * Reto Extra 2: Mapeo de cadenas de texto.
     * Convierte palabras a mayúsculas, ignorando nulos y vacíos.
     *
     * @param palabras lista de palabras
     * @return lista de palabras en mayúsculas filtradas
     */
    public static List<String> convertirAMayusculas(List<String> palabras) {
        // GUÍA: separa el problema en dos decisiones: qué entradas son válidas
        // y cómo se transforma cada una. El orden importa porque un null no puede
        // convertirse a mayúsculas. Recuerda que una lista de prueba sí puede traer
        // nulls.
        return palabras.stream()
                .filter(a -> a != null && !a.isEmpty())
                .map(String::toUpperCase)
                .toList();
    }

    /**
     * Reto Extra 3: Reducción básica.
     * Calcula la suma de los cuadrados de los números de la lista.
     *
     * @param numeros lista de números
     * @return suma de los cuadrados
     */
    public static int calcularSumaCuadrados(List<Integer> numeros) {
        // GUÍA: cada número aporta su cuadrado al total. Puedes pensarlo como
        // una transformación numérica seguida de una reducción. Para una lista
        // vacía, el total natural debe ser cero.
        if (numeros == null || numeros.isEmpty()) {
            return 0;
        }
        return numeros.stream()
                .mapToInt(a -> (int) Math.pow(a, 2))
                .sum();

    }

    /**
     * Reto Extra 4: Operación de conteo terminal.
     * Cuenta cuántas cadenas en la lista son vacías (o contienen sólo espacios en
     * blanco).
     *
     * @param cadenas lista de cadenas
     * @return cantidad de cadenas vacías
     */
    public static long contarCadenasVacias(List<String> cadenas) {
        // GUÍA: cuenta cadenas que no tienen contenido visible. No es lo mismo
        // una cadena vacía que una cadena con espacios, pero ambas deben tratarse
        // como vacías para este reto. Fíjate en que el retorno es long.
        return cadenas.stream()
                .map(String::trim)
                .filter(String::isEmpty)
                .count();
    }

    /**
     * Reto Extra 5: Colector Collectors.joining.
     * Une las palabras con coma y espacio, y las envuelve en corchetes "[palabra1,
     * palabra2]".
     *
     * @param cadenas lista de palabras
     * @return cadena con el formato especificado
     */
    public static String unirConComas(List<String> cadenas) {
        // GUÍA: el formato tiene tres partes: prefijo, separador entre elementos
        // y sufijo. Comprueba mentalmente el caso de lista vacía: no debe aparecer
        // una coma sobrante ni espacios internos innecesarios.
        return cadenas.stream()
                .collect(Collectors.joining(", ", "[", "]"));
    }

    /**
     * Reto Extra 6: Deduplicación y ordenación.
     * Retorna una lista con los elementos únicos (sin duplicados) ordenados
     * alfabéticamente.
     *
     * @param lista lista de palabras
     * @return lista sin duplicados y ordenada
     */
    public static List<String> obtenerElementosUnicos(List<String> lista) {
        // GUÍA: necesitas eliminar repeticiones y después presentar un orden
        // estable y predecible. Para String, el orden natural es alfabético.
        // Piensa si el orden original debe influir en el resultado final.
        return lista.stream()
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();
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
        // GUÍA: este es el núcleo de la paginación: descartar una cantidad inicial
        // y quedarse con una ventana de tamaño máximo. Si la ventana pide más
        // elementos de los disponibles, devuelve simplemente los que existan.
        return lista.stream()
                .skip(skip)
                .limit(limit)
                .toList();
    }

    /**
     * Reto Extra 8: Búsqueda con anyMatch.
     * Comprueba si al menos una palabra de la lista empieza por el prefijo
     * indicado.
     *
     * @param palabras lista de palabras
     * @param prefijo  prefijo a buscar (insensible a mayúsculas/minúsculas)
     * @return true si alguna coincide, false de lo contrario
     */
    public static boolean algunoEmpiezaCon(List<String> palabras, String prefijo) {
        // GUÍA: la comparación no debe depender de mayúsculas o minúsculas, así
        // que ambos lados deben evaluarse con el mismo criterio. Basta con que
        // una palabra cumpla; no necesitas recorrer lógicamente más tras encontrarla.
        if (palabras == null || palabras.isEmpty() || prefijo == null) {
            return false;
        }

        String newPrefijo = prefijo.toLowerCase();

        return palabras.stream()
                .filter(Objects::nonNull)
                .anyMatch(a -> a.toLowerCase().startsWith(newPrefijo));
    }

    /**
     * Reto Extra 9: Validación universal con allMatch.
     * Comprueba si todos los números de la lista son estrictamente positivos (> 0).
     *
     * @param numeros lista de números
     * @return true si todos son positivos o si la lista está vacía, false de lo
     *         contrario
     */
    public static boolean todosSonPositivos(List<Integer> numeros) {
        // GUÍA: esta es una condición universal: ningún elemento puede incumplirla.
        // La lista vacía se considera válida porque no contiene contraejemplos,
        // una idea lógica conocida como verdad vacua.
        if (numeros == null) {
            return false;
        }
        return numeros.stream()
                .allMatch(a -> a != null && a > 0);
    }

    /**
     * Reto Extra 10: Reducción con max.
     * Encuentra el número máximo de la lista.
     *
     * @param numeros lista de números
     * @return Optional con el máximo, o vacío si la lista no contiene números
     */
    public static Optional<Integer> obtenerMaximo(List<Integer> numeros) {
        // GUÍA: buscar un máximo requiere comparar elementos entre sí. Si no hay
        // elementos, no existe respuesta válida; por eso el contrato usa Optional
        // en vez de devolver null o un número inventado.
        if (numeros == null || numeros.isEmpty()) {
            return Optional.empty();
        }
        return numeros.stream()
                .filter(Objects::nonNull)
                .max(Integer::compareTo);
    }

}
