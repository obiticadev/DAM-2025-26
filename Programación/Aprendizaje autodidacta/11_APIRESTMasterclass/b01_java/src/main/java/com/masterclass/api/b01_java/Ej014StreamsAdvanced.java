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
        // GUÍA: decide qué propiedad de cada palabra será la clave del grupo.
        // Las palabras con la misma clave deben quedar juntas y conservar su
        // orden relativo dentro de cada lista.
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
        // GUÍA: una partición binaria siempre tiene dos lados: los que cumplen
        // la condición y los que no. Aunque un lado quede vacío, el mapa debería
        // poder consultarse sin sorpresas.
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
        // GUÍA: hay dos niveles que pueden contener basura: la sublista completa
        // y cada cadena individual. Primero evita entrar en sublistas nulas y,
        // después, descarta elementos nulos o vacíos al aplanar.
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
        // GUÍA: cada palabra debe convertirse en una clave y el valor asociado
        // no es la lista de apariciones, sino la cantidad de veces que aparece.
        // Piensa en esto como un histograma textual.
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
        // GUÍA: primero agrupa por longitud y luego decide cómo resumir cada
        // grupo. En este caso el resumen no es una lista ni un conteo: es una
        // cadena con los elementos del grupo unidos en orden.
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
        // GUÍA: cada categoría forma un grupo y dentro de cada grupo debes elegir
        // el producto con mayor precio. La firma usa Optional porque una reducción
        // de máximo representa una respuesta que podría no existir.
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
        // GUÍA: no promedies todos los productos, solo los de la categoría pedida.
        // Si no hay ninguno, el contrato define 0.0 como valor neutro de respuesta.
        // Distingue entre tiempo de filtrado y tiempo de cálculo del promedio.
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
        // GUÍA: el nombre es la clave del mapa, pero puede repetirse. Define una
        // regla clara para resolver colisiones: entre productos con igual nombre,
        // debe sobrevivir el de mayor precio.
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
        // GUÍA: no calcules cada métrica en recorridos separados. El JDK tiene
        // una estructura preparada para resumir valores double con count, suma,
        // mínimo, máximo y media en una sola pasada.
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
        // GUÍA: además de agrupar, el mapa resultante debe tener claves ordenadas.
        // El tipo concreto de mapa importa porque un mapa hash no promete orden
        // al recorrer sus claves.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agruparYOrdenarResultados");
    }

}
