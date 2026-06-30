package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Consumer;

/**
 * Ejercicio 016 · Comodines y varianza: {@code ? extends} / {@code ? super}.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.4).
 */
public final class Ej016WildcardsVariance {

    private Ej016WildcardsVariance() {
    }

    /**
     * Suma cualquier lista de números (Integer, Double, Long...).
     *
     * @param numeros lista productora; usa {@code ? extends Number}
     * @return suma como double
     */
    public static double sumar(List<? extends Number> numeros) {
        // TODO 1: declara un acumulador double a 0.
        // TODO 2: recorre la lista (es PRODUCTORA: solo lees, ? extends Number).
        // TODO 3: para cada elemento usa doubleValue() para obtener su valor.
        // TODO 4: acumula en el total.
        // TODO 5: devuelve el acumulado.
        return -1;
    }

    /**
     * Añade los enteros 1, 2 y 3 a una lista consumidora.
     *
     * @param destino lista consumidora; usa {@code ? super Integer}
     */
    public static void rellenar(List<? super Integer> destino) {
        // TODO 6: 'destino' es CONSUMIDORA (? super Integer): puedes añadir Integers.
        // TODO 7: añade los valores 1, 2 y 3 en ese orden.
    }

    /**
     * Cuenta cuántos elementos son instancia de la clase dada.
     *
     * @param items lista heterogénea
     * @param tipo  clase a contar
     * @return número de coincidencias
     */
    public static long contarDeTipo(List<?> items, Class<?> tipo) {
        // TODO 8: abre stream sobre 'items' (comodín sin límites: solo Object).
        // TODO 9: filtra con tipo.isInstance(x) (comprobación de tipo en runtime).
        // TODO 10: devuelve count().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(sumar(List.of(1, 2.5, 3L)));
    }

    /**
     * Reto Extra 1: Copia segura respetando PECS (Producer Extends, Consumer Super).
     * Copia todos los elementos de la lista de origen a la lista de destino.
     *
     * @param origen  lista productora de elementos de tipo T o subtipos
     * @param destino lista consumidora de elementos de tipo T o supertipos
     * @param <T>     tipo base de los elementos
     */
    public static <T> void copiarElementos(List<? extends T> origen, List<? super T> destino) {
        // GUÍA: aplica PECS: el origen produce valores que puedes leer como T y
        // el destino consume valores T. La llamada debe funcionar aunque origen
        // y destino no tengan exactamente el mismo tipo concreto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarElementos");
    }

    /**
     * Reto Extra 2: Lectura de tipos numéricos (Covarianza).
     * Calcula la suma de todos los elementos numéricos de una colección.
     *
     * @param lista colección productora de números
     * @return suma de los números como double
     */
    public static double calcularSumaColeccion(Collection<? extends Number> lista) {
        // GUÍA: la colección es productora de Number o subtipos. No necesitas
        // saber si cada valor es Integer, Double o Long; todos pueden aportar
        // su valor numérico como double.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularSumaColeccion");
    }

    /**
     * Reto Extra 3: Escritura contravariante segura (Contravarianza).
     * Agrega números enteros correlativos desde el 1 hasta el límite indicado en la lista de destino.
     *
     * @param lista  lista consumidora de enteros
     * @param limite límite inclusivo de los enteros a agregar
     */
    public static void agregarNumeros(Collection<? super Integer> lista, int limite) {
        // GUÍA: la colección está pensada para recibir enteros. Añade la secuencia
        // desde 1 hasta el límite incluido. Recuerda que con ? super Integer puedes
        // escribir Integers, pero no leer de vuelta como Integer con seguridad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agregarNumeros");
    }

    /**
     * Reto Extra 4: Integración funcional covariante.
     * Filtra los elementos de una lista de solo lectura usando un predicado compatible.
     *
     * @param lista  lista de solo lectura (? extends T)
     * @param filtro predicado que acepta elementos de tipo T o supertipos
     * @param <T>    tipo base de los elementos
     * @return lista resultante con los elementos que pasaron el filtro
     */
    public static <T> List<T> filtrarListaSoloLectura(List<? extends T> lista, Predicate<? super T> filtro) {
        // GUÍA: la lista produce elementos compatibles con T y el predicado puede
        // aceptar T o tipos más generales. Devuelve una lista nueva con los valores
        // que superen la condición, sin intentar escribir en la lista de entrada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarListaSoloLectura");
    }

    /**
     * Reto Extra 5: Búsqueda acotada compleja (PECS avanzado).
     * Encuentra el valor máximo en una lista de elementos comparables.
     *
     * @param lista lista productora de elementos comparables
     * @param <T>   tipo de los elementos
     * @return Optional con el valor máximo o vacío si la lista es nula o vacía
     */
    public static <T extends Comparable<? super T>> Optional<T> encontrarMaximoCovariante(List<? extends T> lista) {
        // GUÍA: si no hay datos, no inventes un máximo. Si los hay, compara los
        // elementos respetando la cota de la firma: T puede estar definido como
        // comparable consigo mismo o con un supertipo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encontrarMaximoCovariante");
    }

    /**
     * Reto Extra 6: Combinación segura de colecciones compatibles.
     * Crea una nueva lista conteniendo la combinación de elementos de dos colecciones covariantes.
     *
     * @param c1  primera colección productora
     * @param c2  segunda colección productora
     * @param <T> tipo base común resultante
     * @return lista combinada de elementos de tipo T
     */
    public static <T> List<T> combinarColeccionesCovariantes(Collection<? extends T> c1, Collection<? extends T> c2) {
        // GUÍA: ambas colecciones producen valores compatibles con un tipo común T.
        // Crea una colección resultado y añade los elementos de ambas, manteniendo
        // el orden relativo de entrada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarColeccionesCovariantes");
    }

    /**
     * Reto Extra 7: Lectura pura sin tipo (Unbounded Wildcard).
     * Comprueba si un determinado elemento está presente en la colección sin importar el tipo de ésta.
     *
     * @param coleccion colección de cualquier tipo (?)
     * @param elemento  elemento a buscar
     * @return true si está presente, false de lo contrario
     */
    public static boolean esElementoPresente(Collection<?> coleccion, Object elemento) {
        // GUÍA: no necesitas conocer el tipo interno de la colección porque solo
        // vas a consultar presencia. Protege el caso de colección null y delega
        // la igualdad al comportamiento normal de la colección.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esElementoPresente");
    }

    /**
     * Reto Extra 8: Lectura y formato universal.
     * Retorna una representación formateada de la colección como String.
     *
     * @param coleccion colección de cualquier tipo
     * @return String formateado como "items: [elem1, elem2, ...]"
     */
    public static String imprimirColeccionComodin(Collection<?> coleccion) {
        // GUÍA: el contrato pide un prefijo fijo y después la representación de
        // los elementos. Comprueba el caso de colección vacía para no producir
        // separadores extra ni formato inconsistente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para imprimirColeccionComodin");
    }

    /**
     * Reto Extra 9: Modificación selectiva segura.
     * Reemplaza todas las apariciones de un valor original por un valor nuevo en una lista compatible.
     *
     * @param lista         lista consumidora (? super T)
     * @param valorOriginal valor a ser reemplazado
     * @param valorNuevo    nuevo valor a colocar
     * @param <T>           tipo de los valores
     */
    public static <T> void reemplazarValores(List<? super T> lista, T valorOriginal, T valorNuevo) {
        // GUÍA: necesitas leer para comparar y escribir para reemplazar, así que
        // conviene recorrer posiciones. En una lista ? super T puedes colocar el
        // nuevo valor T, pero al leer obtendrás una visión más general.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reemplazarValores");
    }

    /**
     * Reto Extra 10: Consumo seguro PECS.
     * Extrae los elementos de la lista productora y los aplica sobre el consumidor provisto.
     *
     * @param origen     lista productora (? extends T)
     * @param consumidor consumidor compatible (? super T)
     * @param <T>        tipo base de los elementos
     */
    public static <T> void extraerYConsumir(List<? extends T> origen, Consumer<? super T> consumidor) {
        // GUÍA: el origen produce elementos y el Consumer los consume. Aplica la
        // acción a cada elemento respetando el orden de la lista y sin intentar
        // insertar nada en el origen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerYConsumir");
    }

}
