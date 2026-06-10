package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 014 · Streams avanzados: reduce, flatMap, Collectors.groupingBy.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.3).
 */
public final class Ej014StreamsAdvanced {

    private Ej014StreamsAdvanced() {
    }

    /**
     * Aplana una lista de listas en una sola lista preservando el orden.
     *
     * @param listas lista de sublistas
     * @return todos los elementos concatenados
     */
    public static List<Integer> aplanar(List<List<Integer>> listas) {
        // TODO 1: abre stream sobre 'listas' (cada elemento es una sublista).
        // TODO 2: usa flatMap(List::stream) para fundir las sublistas en un solo flujo.
        // TODO 3: recoge a List respetando el orden de aparición y devuélvela.
        return List.of();
    }

    /**
     * Agrupa palabras por su primera letra.
     *
     * @param palabras lista de palabras no vacías
     * @return mapa letra→lista de palabras que empiezan por esa letra
     */
    public static Map<Character, List<String>> agruparPorInicial(List<String> palabras) {
        // TODO 4: abre stream sobre 'palabras'.
        // TODO 5: define la clave de agrupación: p -> p.charAt(0).
        // TODO 6: usa Collectors.groupingBy con esa función clave.
        // TODO 7: el value por defecto de groupingBy ya es una List que preserva orden: devuélvelo.
        return Map.of();
    }

    /**
     * Concatena nombres separados por coma usando reduce/joining.
     *
     * @param nombres lista de nombres
     * @return "a, b, c"; cadena vacía si la lista está vacía
     */
    public static String unirConComas(List<String> nombres) {
        // TODO 8: abre stream sobre 'nombres'.
        // TODO 9: usa Collectors.joining(", ") (el caso vacío produce "" naturalmente).
        // TODO 10: devuelve la cadena resultante.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(aplanar(List.of(List.of(1, 2), List.of(3))));
        System.out.println(unirConComas(List.of("a", "b", "c")));
    }

    public record Producto(String nombre, String categoria, double precio) {}

    /**
     * Reto Extra 1: Clasificación con groupingBy.
     * Agrupa las palabras por su longitud.
     *
     * @param palabras lista de palabras
     * @return mapa longitud -> lista de palabras
     */
    public static Map<Integer, List<String>> agruparPorLongitud(List<String> palabras) {
        // GUÍA: teoría 1.4, primer collector.
        // palabras.stream().collect(Collectors.groupingBy(String::length));
        // La función clasificadora (String::length) decide la CLAVE; los valores
        // con la misma clave caen en la misma List, en orden de aparición
        // (el test espera [bb, dd] para la clave 2, en ese orden).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparPorLongitud");
    }

    /**
     * Reto Extra 2: Particionado binario con partitioningBy.
     * Particiona los números enteros en pares (true) e impares (false).
     *
     * @param numeros lista de números
     * @return mapa booleano -> lista de números
     */
    public static Map<Boolean, List<Integer>> particionarParesImpares(List<Integer> numeros) {
        // GUÍA: numeros.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        // partitioningBy vs groupingBy con clave booleana: partitioningBy
        // GARANTIZA que existen ambas claves (true y false, aunque una lista
        // quede vacía); groupingBy solo crea las claves que aparecen. Esa
        // garantía evita nulls al hacer res.get(false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para particionarParesImpares");
    }

    /**
     * Reto Extra 3: Aplanamiento de jerarquías complejas.
     * Aplana una lista de listas de cadenas, ignorando elementos nulos o vacíos.
     *
     * @param listas lista de listas de cadenas
     * @return lista plana de cadenas válidas
     */
    public static List<String> aplanarListas(List<List<String>> listas) {
        // GUÍA: flatMap con minas: el test mete una SUBLISTA null y un "" dentro
        // de otra. Hay que filtrar a DOS niveles:
        // listas.stream()
        //       .filter(sub -> sub != null)            // nivel 1: sublistas null
        //       .flatMap(List::stream)                 // aplana
        //       .filter(s -> s != null && !s.isEmpty()) // nivel 2: elementos
        //       .toList();
        // Resultado esperado: [hello, world].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplanarListas");
    }

    /**
     * Reto Extra 4: Contar frecuencias con groupingBy y counting.
     * Cuenta cuántas veces aparece cada palabra en la lista.
     *
     * @param palabras lista de palabras
     * @return mapa palabra -> cantidad de ocurrencias
     */
    public static Map<String, Long> contarOcurrencias(List<String> palabras) {
        // GUÍA: primer groupingBy con DOWNSTREAM collector (teoría 1.4):
        // palabras.stream().collect(
        //     Collectors.groupingBy(p -> p, Collectors.counting()));
        // El segundo argumento sustituye la List por defecto: en vez de
        // acumular los elementos, los CUENTA (Long). Este "histograma en una
        // línea" es comodín en entrevistas y en informes reales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarOcurrencias");
    }

    /**
     * Reto Extra 5: Reducción secundaria de valores agrupados.
     * Agrupa las palabras por su longitud, y concatena los nombres de cada grupo separados por coma.
     *
     * @param palabras lista de palabras
     * @return mapa longitud -> nombres concatenados
     */
    public static Map<Integer, String> obtenerNombresConcatenadosPorGrupo(List<String> palabras) {
        // GUÍA: mismo esquema que el reto 4, cambiando el downstream:
        // Collectors.groupingBy(String::length, Collectors.joining(", "))
        // Cada grupo, en vez de List o conteo, se reduce a una cadena unida.
        // Test: clave 2 → "bb, dd". Date cuenta del patrón general:
        // groupingBy(clave, QUÉ-HACER-CON-CADA-GRUPO).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombresConcatenadosPorGrupo");
    }

    /**
     * Reto Extra 6: Colector avanzado maxBy por categoría.
     * Encuentra el producto más caro dentro de cada categoría.
     *
     * @param productos lista de productos
     * @return mapa categoria -> Optional con el producto más caro de esa categoría
     */
    public static Map<String, Optional<Producto>> encontrarProductoMasCaroPorCategoria(List<Producto> productos) {
        // GUÍA: groupingBy + maxBy:
        // productos.stream().collect(Collectors.groupingBy(
        //     Producto::categoria,
        //     Collectors.maxBy(Comparator.comparingDouble(Producto::precio))));
        // maxBy devuelve Optional<Producto> (un grupo podría quedar vacío en
        // teoría) — por eso la firma del método ya lo declara así.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encontrarProductoMasCaroPorCategoria");
    }

    /**
     * Reto Extra 7: Promedio continuo con averagingDouble.
     * Calcula el precio promedio de los productos pertenecientes a una categoría.
     *
     * @param productos lista de productos
     * @param categoria categoría seleccionada
     * @return precio promedio, o 0.0 si no hay productos en esa categoría
     */
    public static double calcularPromedioPrecio(List<Producto> productos, String categoria) {
        // GUÍA: dos opciones equivalentes; elige y entiende ambas:
        // A) filter + average:
        //    productos.stream().filter(p -> p.categoria().equals(categoria))
        //        .mapToDouble(Producto::precio).average().orElse(0.0);
        //    (average() devuelve OptionalDouble: vacío si no hubo elementos →
        //    orElse(0.0) cubre el caso "CatC" del test).
        // B) filter + collect(Collectors.averagingDouble(Producto::precio))
        //    — devuelve 0.0 directamente con stream vacío.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularPromedioPrecio");
    }

    /**
     * Reto Extra 8: Prevención de duplicados en toMap con merge.
     * Convierte la lista de productos en un mapa donde la clave es el nombre del producto y el valor es el producto.
     * En caso de duplicados de nombre, quédate con el producto de mayor precio.
     *
     * @param productos lista de productos
     * @return mapa nombre -> producto
     */
    public static Map<String, Producto> crearMapaDeValoresUnicos(List<Producto> productos) {
        // GUÍA: toMap de TRES argumentos — el tercero es la merge function:
        // productos.stream().collect(Collectors.toMap(
        //     Producto::nombre,                              // clave
        //     p -> p,                                        // valor
        //     (a, b) -> a.precio() >= b.precio() ? a : b));  // si chocan, gana el caro
        // SIN el tercer argumento, dos productos llamados "prod" lanzan
        // IllegalStateException: el test existe para que sufras (una vez) ese
        // detalle. toMap sin merge function solo es seguro con claves únicas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMapaDeValoresUnicos");
    }

    /**
     * Reto Extra 9: Estadísticas completas con summarizingDouble.
     * Genera un objeto DoubleSummaryStatistics con el resumen de precios de los productos.
     *
     * @param productos lista de productos
     * @return estadísticas de precios
     */
    public static java.util.DoubleSummaryStatistics generarEstadisticasPrecio(List<Producto> productos) {
        // GUÍA: una línea —
        // productos.stream().collect(Collectors.summarizingDouble(Producto::precio));
        // DoubleSummaryStatistics trae count, sum, min, max y average de UNA
        // pasada. Es el endpoint "/estadisticas" de una API resuelto en una
        // línea, en vez de recorrer la lista cuatro veces.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarEstadisticasPrecio");
    }

    /**
     * Reto Extra 10: Agrupamiento con mapa ordenado (TreeMap).
     * Agrupa los productos por su categoría, retornando un mapa ordenado alfabéticamente por la categoría.
     *
     * @param productos lista de productos
     * @return mapa ordenado categoria -> lista de productos
     */
    public static Map<String, List<Producto>> agruparYOrdenarResultados(List<Producto> productos) {
        // GUÍA: groupingBy de TRES argumentos — el del medio es la FÁBRICA del mapa:
        // productos.stream().collect(Collectors.groupingBy(
        //     Producto::categoria,
        //     TreeMap::new,                       // mapa ordenado por clave
        //     Collectors.toList()));
        // groupingBy normal devuelve HashMap (orden impredecible); el test
        // recorre keySet() y exige [CatA, CatB] → necesitas TreeMap.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparYOrdenarResultados");
    }

}
