package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Ejercicio 012 · Acceso seguro con Optional.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.2).
 */
public final class Ej012OptionalSafeAccess {

    private Ej012OptionalSafeAccess() {
    }

    /**
     * Busca el primer nombre cuya longitud supere un mínimo.
     *
     * @param nombres lista de candidatos (puede contener null, ignóralos)
     * @param minLen  longitud mínima estricta
     * @return Optional con el primero que cumpla, o vacío
     */
    public static Optional<String> primeroLargo(List<String> nombres, int minLen) {
        // TODO 1: si 'nombres' es null, devuelve Optional.empty() (defensa).
        // TODO 2: abre un stream sobre la lista.
        // TODO 3: filtra los elementos null (no deben provocar NPE).
        // TODO 4: filtra los que tengan length() > minLen (mínimo ESTRICTO).
        // TODO 5: usa findFirst() para quedarte con el primero y devuélvelo (ya es Optional).
        return Optional.empty();
    }

    /**
     * Devuelve el nombre en mayúsculas o un valor por defecto si no existe.
     *
     * @param nombre   posible valor (puede ser null)
     * @param porDefecto valor a usar si nombre es null
     * @return nombre.toUpperCase() o porDefecto
     */
    public static String enMayusOrDefault(String nombre, String porDefecto) {
        // TODO 6: envuelve 'nombre' con Optional.ofNullable (sin if explícito).
        // TODO 7: aplica map(String::toUpperCase) sobre el Optional.
        // TODO 8: resuelve con orElse(porDefecto).
        return "";
    }

    /**
     * Lanza una excepción si el valor no está presente.
     *
     * @param valor posible valor
     * @return el valor si existe
     * @throws IllegalStateException si valor es null, con mensaje "ausente"
     */
    public static String requerido(String valor) {
        // TODO 9: envuelve 'valor' con Optional.ofNullable.
        // TODO 10: resuelve con orElseThrow(() -> new IllegalStateException("ausente")).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(primeroLargo(List.of("an", "pedro", "li"), 3));
        System.out.println(enMayusOrDefault(null, "N/A"));
    }

    /**
     * Reto Extra 1: Mapeo seguro con map.
     * Mapea un Optional de String a un Optional de Integer con la longitud del String.
     *
     * @param opt Optional que contiene una cadena o está vacío
     * @return Optional con la longitud de la cadena, o vacío
     */
    public static Optional<Integer> obtenerLargoSiPresente(Optional<String> opt) {
        // TODO extra: Mapea la longitud del String de forma segura si está presente
        return Optional.empty();
    }

    /**
     * Reto Extra 2: Aplanado de colecciones de Optionals.
     * Recibe una lista de Optionals y retorna el primer valor String no vacío que encuentre.
     *
     * @param optList Lista de Optionals
     * @return Optional con el primer valor String presente, o vacío
     */
    public static Optional<String> obtenerPrimeroValido(List<Optional<String>> optList) {
        // TODO extra: Filtra la lista de optionals para quedarte con el primero que esté presente, desempaquetándolo
        return Optional.empty();
    }

    /**
     * Reto Extra 3: Filtrado declarativo con filter.
     * Filtra el Optional si el String contenido tiene una longitud mayor o igual al mínimo.
     *
     * @param opt    Optional con la cadena
     * @param minLen Longitud mínima
     * @return Optional filtrado
     */
    public static Optional<String> filtrarPorLongitud(Optional<String> opt, int minLen) {
        // TODO extra: Filtra el valor si su longitud es mayor o igual a minLen
        return Optional.empty();
    }

    /**
     * Reto Extra 4: Lazy Fallbacks con orElseGet.
     * Devuelve el valor del Optional o ejecuta la función proveedora costosa si está vacío.
     *
     * @param opt Optional con la cadena
     * @param s   Proveedor de fallback costoso (lazy)
     * @return El valor contenido o el resultado del proveedor
     */
    public static String obtenerConLazyFallback(Optional<String> opt, Supplier<String> s) {
        // TODO extra: Retorna el valor o ejecuta el Supplier de manera lazy
        return "";
    }

    /**
     * Reto Extra 5: Lanzar excepción de negocio personalizada.
     * Retorna el valor o lanza una excepción personalizada de negocio (IllegalArgumentException) si está vacío.
     *
     * @param opt Optional con la cadena
     * @return El valor contenido
     * @throws IllegalArgumentException si está vacío con mensaje "Valor requerido ausente"
     */
    public static String lanzarExcepcionPersonalizada(Optional<String> opt) {
        // TODO extra: Obtén el valor o lanza IllegalArgumentException con el mensaje correspondiente
        return "";
    }

    /**
     * Reto Extra 6: Stream sobre Optional.
     * Convierte un Optional a un Stream de Java y filtra los elementos vacíos y en minúsculas.
     * Retorna una lista con la cadena en mayúsculas si estaba presente y cumplía la condición.
     *
     * @param opt Optional con la cadena
     * @return Lista con el elemento transformado si existía y era válido
     */
    public static List<String> convertirAStreamYFiltrar(Optional<String> opt) {
        // TODO extra: Usa opt.stream() para aplanar el optional, filtrando e interactuando con streams
        return List.of();
    }

    /**
     * Reto Extra 7: Control condicional con ifPresentOrElse.
     * Ejecuta una acción sobre el valor si está presente, o ejecuta una acción alternativa si está vacío.
     *
     * @param opt       Optional con la cadena
     * @param siEsta    Acción a ejecutar si el valor está presente
     * @param siNoEsta  Acción a ejecutar si el valor está vacío
     */
    public static void ejecutarAccionCondicional(Optional<String> opt, Consumer<String> siEsta, Runnable siNoEsta) {
        // TODO extra: Ejecuta ifPresentOrElse sobre el Optional con las acciones provistas
    }

    /**
     * Reto Extra 8: Reducción de Optionals anidados con flatMap.
     * Recibe un Optional que contiene otro Optional de String, y lo aplana de forma segura.
     *
     * @param opt Optional anidado
     * @return Optional aplanado
     */
    public static Optional<String> mapearConFlatMap(Optional<Optional<String>> opt) {
        // TODO extra: Aplana el Optional anidado usando flatMap
        return Optional.empty();
    }

    /**
     * Reto Extra 9: Transformación y validaciones complejas.
     * Recibe un Optional de String. Si está presente, limpia los espacios en blanco extremos (trim).
     * Si tras limpiarlo queda vacío, o si ya estaba vacío inicialmente, retorna Optional.empty().
     * En caso contrario, retorna el String en mayúsculas.
     *
     * @param opt Optional con la cadena
     * @return Optional con el valor normalizado o vacío
     */
    public static Optional<String> reemplazarPorVacioSiInvalido(Optional<String> opt) {
        // TODO extra: Mapea a trim(), filtra los que queden vacíos, y mapea a toUpperCase()
        return Optional.empty();
    }

    /**
     * Reto Extra 10: Concatenación y encadenamiento seguro con or.
     * Retorna el primer Optional que no esté vacío entre opt1 y opt2.
     *
     * @param opt1 Primer Optional candidado
     * @param opt2 Segundo Optional fallback
     * @return El primer Optional con valor presente
     */
    public static Optional<String> obtenerPrimeroDeVarios(Optional<String> opt1, Optional<String> opt2) {
        // TODO extra: Utiliza el método or() para encadenar las dos opciones de Optional
        return Optional.empty();
    }

}
