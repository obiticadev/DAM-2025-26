package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Ejercicio 015 · Repositorio genérico en memoria.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.4).
 *
 * <p>Un único repositorio reutilizable para cualquier entidad con id de tipo ID.
 */
public class Ej015GenericsRepository<T, ID> {

    private final Function<T, ID> idExtractor;

    /**
     * @param idExtractor función que, dada una entidad, devuelve su identificador
     */
    public Ej015GenericsRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    /**
     * Guarda (o reemplaza si ya existe ese id) una entidad.
     *
     * @param entity entidad a persistir
     * @return la propia entidad guardada
     */
    public T save(T entity) {
        // TODO 1: declara/usa una estructura interna Map<ID,T> como almacén.
        // TODO 2: obtén la clave aplicando idExtractor.apply(entity).
        // TODO 3: inserta en el mapa (put reemplaza si la clave ya existía -> upsert).
        // TODO 4: devuelve la entidad guardada.
        return null;
    }

    /**
     * Busca por identificador.
     *
     * @param id identificador
     * @return Optional con la entidad o vacío
     */
    public Optional<T> findById(ID id) {
        // TODO 5: recupera del mapa el valor asociado a 'id'.
        // TODO 6: envuélvelo con Optional.ofNullable (ausente -> Optional.empty()).
        return Optional.empty();
    }

    /**
     * @return todas las entidades guardadas (orden no garantizado)
     */
    public List<T> findAll() {
        // TODO 7: crea una nueva lista a partir de los values() del mapa.
        // TODO 8: devuelve una copia (no expongas la colección interna mutable).
        return List.of();
    }

    /**
     * Borra por id.
     *
     * @param id identificador
     * @return true si existía y se borró
     */
    public boolean deleteById(ID id) {
        // TODO 9: usa map.remove(id) y observa si devolvió algo distinto de null.
        // TODO 10: devuelve true si había una entidad con esa clave, false si no.
        return false;
    }

    public static void main(String[] args) {
        var repo = new Ej015GenericsRepository<String, Integer>(String::length);
        repo.save("hola");
        System.out.println(repo.findById(4));
    }

    /**
     * Reto Extra 1: Operaciones genéricas con arrays.
     * Retorna el primer elemento de un array genérico, o Optional.empty() si el array es nulo o vacío.
     *
     * @param elementos array genérico
     * @param <E>       tipo de los elementos
     * @return Optional con el primer elemento
     */
    public static <E> Optional<E> obtenerPrimero(E[] elementos) {
        // GUÍA: tu primer MÉTODO genérico (el <E> vive en el método, teoría 1.5).
        // if (elementos == null || elementos.length == 0) return Optional.empty();
        // return Optional.ofNullable(elementos[0]);
        // (ofNullable y no of: el primer elemento podría ser null.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrimero");
    }

    /**
     * Reto Extra 2: Algoritmos sobre vectores genéricos.
     * Intercambia las posiciones de dos elementos en un array genérico.
     *
     * @param array array genérico
     * @param i     primer índice
     * @param j     segundo índice
     * @param <E>   tipo de los elementos
     * @throws IllegalArgumentException si los índices están fuera de rango o el array es nulo
     */
    public static <E> void intercambiarPosiciones(E[] array, int i, int j) {
        // GUÍA:
        // 1. array null, o i/j fuera de [0, length) → IllegalArgumentException
        //    (el test prueba null y el índice -1).
        // 2. El intercambio clásico con temporal:
        //    E tmp = array[i]; array[i] = array[j]; array[j] = tmp;
        // Fíjate: el método es void y MUTA el array (los arrays no tienen
        // versión inmutable; por eso en el resto del bloque preferimos List).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intercambiarPosiciones");
    }

    /**
     * Reto Extra 3: Búsqueda genérica en Mapas.
     * Busca una clave en un mapa y devuelve un Optional con el valor correspondiente.
     *
     * @param mapa mapa genérico
     * @param id   clave a buscar
     * @param <K>  tipo de la clave
     * @param <V>  tipo del valor
     * @return Optional con el valor o vacío
     */
    public static <K, V> Optional<V> buscarPorIdEnMapa(Map<K, V> mapa, K id) {
        // GUÍA: una línea — return Optional.ofNullable(mapa.get(id));
        // Es EXACTAMENTE tu findById de arriba, generalizado a dos parámetros
        // de tipo <K, V>. map.get devuelve null si no está; ofNullable lo
        // convierte en empty. (Defensa: mapa null → empty.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorIdEnMapa");
    }

    /**
     * Reto Extra 4: Filtrado genérico funcional.
     * Filtra los elementos de una lista dada una condición funcional (Predicate).
     *
     * @param lista  lista original
     * @param filtro condición
     * @param <E>    tipo de los elementos
     * @return lista con los elementos filtrados
     */
    public static <E> List<E> filtrarElementosGenericos(List<E> lista, Predicate<E> filtro) {
        // GUÍA: genéricos + funcional, juntos:
        // return lista.stream().filter(filtro).toList();
        // El Predicate<E> entra como parámetro: acabas de escribir tu propio
        // "filter" reutilizable para CUALQUIER tipo. Así están implementadas
        // las tripas de la Streams API.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarElementosGenericos");
    }

    /**
     * Reto Extra 5: Transformador genérico de colecciones.
     * Convierte una lista de valores en un mapa asociando cada valor a su clave extraída.
     *
     * @param lista        lista de valores
     * @param keyExtractor función para extraer la clave de cada valor
     * @param <K>          tipo de la clave
     * @param <V>          tipo del valor
     * @return mapa resultante
     */
    public static <K, V> Map<K, V> convertirListaAMapa(List<V> lista, Function<V, K> keyExtractor) {
        // GUÍA:
        // return lista.stream().collect(Collectors.toMap(keyExtractor, v -> v));
        // (o un bucle con map.put(keyExtractor.apply(v), v) — compáralos).
        // ¿Te suena? Es la idea del constructor del repositorio de arriba:
        // una Function<V,K> que extrae la clave. Recuerda de Ej014 reto 8 que
        // toMap explota con claves duplicadas; aquí los tests no las traen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirListaAMapa");
    }

    /**
     * Reto Extra 6: Genéricos acotados por comparación.
     * Comprueba si el primer elemento es estrictamente mayor que el límite.
     *
     * @param actual valor actual
     * @param limite valor límite
     * @param <E>    tipo comparable del elemento
     * @return true si es mayor, false de lo contrario
     */
    public static <E extends Comparable<E>> boolean esMayorQue(E actual, E limite) {
        // GUÍA: una línea — return actual.compareTo(limite) > 0;
        // LA CLAVE ES LA FIRMA: <E extends Comparable<E>> (tipo acotado, teoría
        // 1.5). Sin esa cota, actual no tendría compareTo. Gracias a ella el
        // mismo método compara Integers ("10 > 5") y Strings ("apple" <
        // "banana", orden alfabético) — los dos casos del test.
        // compareTo: negativo = menor, 0 = igual, positivo = mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMayorQue");
    }

    /**
     * Reto Extra 7: Modelado de pares genéricos inmutables.
     */
    public record Tupla<K, V>(K clave, V valor) {
        // GUÍA: este record YA está completo — los componentes (clave, valor)
        // definen el constructor y los accesores. Un record genérico es la
        // forma idiomática de devolver "dos cosas a la vez" sin crear una
        // clase ad-hoc. Si quieres ir más allá, añade un constructor compacto
        // que rechace claves null.
    }

    /**
     * Reto Extra 7 (método): Crea una tupla clave-valor inmutable.
     *
     * @param clave clave de la tupla
     * @param valor valor de la tupla
     * @param <K>   tipo de la clave
     * @param <V>   tipo del valor
     * @return nueva Tupla instanciada
     */
    public static <K, V> Tupla<K, V> crearTupla(K clave, V valor) {
        // GUÍA: una línea — return new Tupla<>(clave, valor);
        // El diamante <> infiere K y V de los argumentos. Estos métodos
        // "factory" estáticos son idiomáticos (Map.entry, List.of hacen lo
        // mismo): leen mejor que el new y permiten cachear/validar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTupla");
    }

    /**
     * Reto Extra 8: Manejo seguro de nulos genéricos.
     * Devuelve el valor provisto, o el valor por defecto si el provisto es nulo.
     *
     * @param valor            valor a evaluar
     * @param valorPorDefecto  valor fallback
     * @param <E>              tipo del elemento
     * @return el elemento o su valor por defecto
     */
    public static <E> E obtenerValorConDefecto(E valor, E valorPorDefecto) {
        // GUÍA: una línea — return valor != null ? valor : valorPorDefecto;
        // (Equivale a Objects.requireNonNullElse(valor, valorPorDefecto),
        // que existe en el JDK justamente para esto — úsalo y ya conoces
        // una utilidad más de java.util.Objects.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerValorConDefecto");
    }

    /**
     * Reto Extra 9: Conteo universal sobre arrays.
     * Cuenta cuántas ocurrencias de un elemento dado existen en el array.
     *
     * @param array    array genérico
     * @param elemento elemento a contar
     * @param <E>      tipo de los elementos
     * @return cantidad de ocurrencias
     */
    public static <E> long contarOcurrenciasGenerico(E[] array, E elemento) {
        // GUÍA:
        // 1. array null → 0.
        // 2. Arrays.stream(array).filter(e -> Objects.equals(e, elemento)).count();
        // Objects.equals y no e.equals(elemento): tolera nulls en ambos lados
        // sin NPE. Memoriza ese helper: es la forma segura de comparar SIEMPRE.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarOcurrenciasGenerico");
    }

    /**
     * Reto Extra 10: Reversión de colecciones genéricas.
     * Retorna una nueva lista que contiene los mismos elementos de la lista original pero en orden inverso.
     *
     * @param lista lista original
     * @param <E>   tipo de los elementos
     * @return nueva lista revertida
     */
    public static <E> List<E> revertirListaGenerica(List<E> lista) {
        // GUÍA: el test exige que la ORIGINAL no cambie → copia primero:
        // List<E> copia = new ArrayList<>(lista);
        // Collections.reverse(copia);     // reverse muta, por eso la copia
        // return copia;
        // ALTERNATIVA Java 21: lista.reversed() devuelve una vista invertida
        // sin tocar la original (SequencedCollection). Ambas pasan el test.
        // TRAMPA evitada: Collections.reverse(lista) directo lanzaría
        // UnsupportedOperationException — List.of es inmutable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para revertirListaGenerica");
    }

}
