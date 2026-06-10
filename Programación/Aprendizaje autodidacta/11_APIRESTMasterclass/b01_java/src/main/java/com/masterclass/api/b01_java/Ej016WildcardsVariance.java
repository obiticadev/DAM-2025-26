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
        // GUÍA: PECS de manual (teoría 1.6) — esta firma ES Collections.copy.
        // for (T elemento : origen) { destino.add(elemento); }
        // (o destino.addAll(origen)).
        // POR QUÉ COMPILA: de origen (? extends T) LEES como T; en destino
        // (? super T) ESCRIBES T. El test copia List<Integer> → List<Number>:
        // sin los wildcards esa llamada ni compilaría. Esa es toda la magia.
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
        // GUÍA: igual que sumar() de arriba pero con Collection y streams:
        // lista.stream().mapToDouble(Number::doubleValue).sum();
        // Number::doubleValue es el puente: cualquier subtipo (Integer, Double,
        // Long — el test mezcla los tres) sabe darse como double.
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
        // GUÍA: bucle clásico sobre lista consumidora (? super Integer):
        // for (int i = 1; i <= limite; i++) { lista.add(i); }
        // El test pasa una List<Number> y añades Integers sin problema: un
        // Integer SIEMPRE cabe donde caben Numbers. Eso es la contravarianza.
        // Prueba mental: ¿podrías LEER un Integer de esa lista? No — solo
        // Object. Consumer Super: se escribe, no se lee.
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
        // GUÍA:
        // List<T> resultado = new ArrayList<>();
        // for (T elem : lista) { if (filtro.test(elem)) resultado.add(elem); }
        // return resultado;
        // LA FIRMA ES LA LECCIÓN: Predicate<? super T> permite que el test
        // filtre List<Integer> con un Predicate<Number> — un filtro "más
        // general" sirve para un tipo más concreto. Mira la firma real de
        // Stream.filter en el JDK: usa exactamente este wildcard.
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
        // GUÍA:
        // 1. lista null o vacía → Optional.empty().
        // 2. Recorre guardando el mayor: if (elem.compareTo(max) > 0) max = elem;
        //    (o streams: lista.stream().max(Comparator.naturalOrder()) — pero
        //    necesitarás castear o copiar a List<T>; el bucle es más directo aquí).
        // LA FIRMA <T extends Comparable<? super T>> es la del JDK real
        // (Collections.max): permite tipos cuya comparación está definida en
        // un PADRE. No la memorices: entiende que es "T comparable consigo
        // mismo o con un supertipo" y reconócela cuando la veas.
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
        // GUÍA:
        // List<T> resultado = new ArrayList<>(c1);
        // resultado.addAll(c2);
        // return resultado;
        // El test combina List<Integer> y List<Double> en List<Number>: el
        // compilador INFIERE T = Number como el ancestro común de ambas. Dos
        // wildcards covariantes convergiendo en un T: PECS compuesto.
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
        // GUÍA: una línea — return coleccion != null && coleccion.contains(elemento);
        // Collection<?> ("de lo que sea") basta porque contains recibe Object:
        // solo COMPARAS, no insertas. Regla práctica: si tu método solo lee
        // y no le importa el tipo, usa <?> — es más honesto que <Object>.
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
        // GUÍA: el toString de las colecciones YA produce "[a, b]":
        // return "items: " + coleccion;
        // (Si quieres hacerlo "a mano": stream + map(String::valueOf) +
        // Collectors.joining(", ", "items: [", "]") — repaso de Ej013 reto 5.)
        // Tests: ["a","b"] → "items: [a, b]"; vacía → "items: []".
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
        // GUÍA: recorre POR ÍNDICE (vas a escribir con set, no solo leer):
        // for (int i = 0; i < lista.size(); i++) {
        //     if (Objects.equals(lista.get(i), valorOriginal)) {
        //         lista.set(i, valorNuevo);
        //     }
        // }
        // En una List<? super T>: get(i) te da Object (suficiente para
        // comparar con equals) y set(i, T) acepta tu T. Lectura degradada,
        // escritura exacta: contravarianza en acción.
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
        // GUÍA: una línea — origen.forEach(consumidor);
        // (o el bucle: for (T e : origen) consumidor.accept(e);)
        // CIERRE DEL EJERCICIO: esta firma — List<? extends T> +
        // Consumer<? super T> — es LITERALMENTE la de Iterable.forEach del
        // JDK. Si entiendes por qué cada wildcard va donde va, PECS es tuyo:
        // el origen PRODUCE elementos (extends), el consumidor los CONSUME (super).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerYConsumir");
    }

}
