package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Ejercicio 012 · Acceso seguro con Optional.
 *
 * <p>
 * Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.2).
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
        // TODO 5: usa findFirst() para quedarte con el primero y devuélvelo (ya es
        // Optional).
        if (nombres == null) {
            return Optional.empty();
        }
        return nombres.stream()
                .filter(Objects::nonNull)
                .filter(a -> a.length() > minLen)
                .findFirst();
    }

    /**
     * Devuelve el nombre en mayúsculas o un valor por defecto si no existe.
     *
     * @param nombre     posible valor (puede ser null)
     * @param porDefecto valor a usar si nombre es null
     * @return nombre.toUpperCase() o porDefecto
     */
    public static String enMayusOrDefault(String nombre, String porDefecto) {
        // TODO 6: envuelve 'nombre' con Optional.ofNullable (sin if explícito).
        // TODO 7: aplica map(String::toUpperCase) sobre el Optional.
        // TODO 8: resuelve con orElse(porDefecto).

        return Optional.ofNullable(nombre)
                .map(String::toUpperCase)
                .orElse(porDefecto);
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
        // TODO 10: resuelve con orElseThrow(() -> new
        // IllegalStateException("ausente")).
        return Optional.ofNullable(valor)
                .orElseThrow(() -> new IllegalStateException("ausente"));
    }

    public static void main(String[] args) {
        System.out.println(primeroLargo(List.of("an", "pedro", "li"), 3));
        System.out.println(enMayusOrDefault(null, "N/A"));
    }

    /**
     * Reto Extra 1: Mapeo seguro con map.
     * Mapea un Optional de String a un Optional de Integer con la longitud del
     * String.
     *
     * @param opt Optional que contiene una cadena o está vacío
     * @return Optional con la longitud de la cadena, o vacío
     */
    public static Optional<Integer> obtenerLargoSiPresente(Optional<String> opt) {
        // GUÍA: teoría 1.2 — map en estado puro.
        // Una línea: return opt.map(String::length);
        // map transforma SI hay valor y propaga el empty si no: exactamente lo
        // que piden los dos tests. Nada de isPresent()/get().
        // (Defensa opcional: si opt fuera null → Optional.empty().)
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para obtenerLargoSiPresente");
    }

    /**
     * Reto Extra 2: Aplanado de colecciones de Optionals.
     * Recibe una lista de Optionals y retorna el primer valor String no vacío que
     * encuentre.
     *
     * @param optList Lista de Optionals
     * @return Optional con el primer valor String presente, o vacío
     */
    public static Optional<String> obtenerPrimeroValido(List<Optional<String>> optList) {
        // GUÍA: combinar Stream + Optional (teoría 1.2 y 1.4).
        // 1. null → Optional.empty().
        // 2. Camino elegante: optList.stream()
        // .flatMap(Optional::stream) // descarta vacíos y desenvuelve
        // .findFirst();
        // Optional::stream convierte cada Optional en un stream de 0 o 1
        // elementos; flatMap los aplana → quedan solo los valores presentes.
        // ALTERNATIVA legible: .filter(Optional::isPresent).map(Optional::get)
        // .findFirst() — válida, pero conoce la primera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrimeroValido");
    }

    /**
     * Reto Extra 3: Filtrado declarativo con filter.
     * Filtra el Optional si el String contenido tiene una longitud mayor o igual al
     * mínimo.
     *
     * @param opt    Optional con la cadena
     * @param minLen Longitud mínima
     * @return Optional filtrado
     */
    public static Optional<String> filtrarPorLongitud(Optional<String> opt, int minLen) {
        // GUÍA: una línea — return opt.filter(s -> s.length() >= minLen);
        // filter mantiene el valor si cumple y devuelve empty si no (o si ya
        // estaba vacío). OJO: aquí es >= (los tests: "Java" con minLen 3 pasa,
        // con minLen 5 no), a diferencia del > estricto de primeroLargo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarPorLongitud");
    }

    /**
     * Reto Extra 4: Lazy Fallbacks con orElseGet.
     * Devuelve el valor del Optional o ejecuta la función proveedora costosa si
     * está vacío.
     *
     * @param opt Optional con la cadena
     * @param s   Proveedor de fallback costoso (lazy)
     * @return El valor contenido o el resultado del proveedor
     */
    public static String obtenerConLazyFallback(Optional<String> opt, Supplier<String> s) {
        // GUÍA: una línea — return opt.orElseGet(s);
        // EL MATIZ QUE EVALÚA EL TEST: con orElse(s.get()) el supplier se
        // ejecutaría SIEMPRE (los argumentos se evalúan antes de llamar);
        // con orElseGet(s) solo se ejecuta si el Optional está vacío. El test
        // cuenta las invocaciones y exige 0 cuando hay valor. Es la pregunta
        // de entrevista clásica sobre Optional (teoría 1.2, tabla).
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para obtenerConLazyFallback");
    }

    /**
     * Reto Extra 5: Lanzar excepción de negocio personalizada.
     * Retorna el valor o lanza una excepción personalizada de negocio
     * (IllegalArgumentException) si está vacío.
     *
     * @param opt Optional con la cadena
     * @return El valor contenido
     * @throws IllegalArgumentException si está vacío con mensaje "Valor requerido
     *                                  ausente"
     */
    public static String lanzarExcepcionPersonalizada(Optional<String> opt) {
        // GUÍA: una línea —
        // return opt.orElseThrow(() -> new IllegalArgumentException("Valor requerido
        // ausente"));
        // El mensaje debe ser EXACTO (el test lo compara con equals).
        // Este es el patrón "si no está → 404" de la teoría 1.2: en Spring la
        // excepción será NotFoundException y un handler la convertirá en HTTP.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para lanzarExcepcionPersonalizada");
    }

    /**
     * Reto Extra 6: Stream sobre Optional.
     * Convierte un Optional a un Stream de Java y filtra los elementos vacíos y en
     * minúsculas.
     * Retorna una lista con la cadena en mayúsculas si estaba presente y cumplía la
     * condición.
     *
     * @param opt Optional con la cadena
     * @return Lista con el elemento transformado si existía y era válido
     */
    public static List<String> convertirAStreamYFiltrar(Optional<String> opt) {
        // GUÍA: descifra los tests antes de codificar:
        // Optional.of("hello") → ["HELLO"] (estaba en minúsculas → pasa)
        // Optional.of("HELLO") → [] (NO estaba en minúsculas → fuera)
        // Optional.empty() → []
        // Es decir: conserva solo si s.equals(s.toLowerCase()), y devuelve la
        // versión en MAYÚSCULAS.
        // Pipeline: opt.stream() // 0 o 1 elementos
        // .filter(s -> s.equals(s.toLowerCase()))
        // .map(String::toUpperCase)
        // .toList();
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para convertirAStreamYFiltrar");
    }

    /**
     * Reto Extra 7: Control condicional con ifPresentOrElse.
     * Ejecuta una acción sobre el valor si está presente, o ejecuta una acción
     * alternativa si está vacío.
     *
     * @param opt      Optional con la cadena
     * @param siEsta   Acción a ejecutar si el valor está presente
     * @param siNoEsta Acción a ejecutar si el valor está vacío
     */
    public static void ejecutarAccionCondicional(Optional<String> opt, Consumer<String> siEsta, Runnable siNoEsta) {
        // GUÍA: una línea — opt.ifPresentOrElse(siEsta, siNoEsta);
        // (Java 9+). El método YA existe en Optional con esta firma exacta:
        // Consumer para el valor, Runnable para el caso vacío. Fíjate en que
        // este método devuelve void: es para EFECTOS (log, métricas), no para
        // transformar — para transformar siempre map/orElse.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para ejecutarAccionCondicional");
    }

    /**
     * Reto Extra 8: Reducción de Optionals anidados con flatMap.
     * Recibe un Optional que contiene otro Optional de String, y lo aplana de forma
     * segura.
     *
     * @param opt Optional anidado
     * @return Optional aplanado
     */
    public static Optional<String> mapearConFlatMap(Optional<Optional<String>> opt) {
        // GUÍA: una línea — return opt.flatMap(inner -> inner);
        // (o el equivalente: opt.flatMap(Function.identity())).
        // POR QUÉ: con map obtendrías Optional<Optional<String>> otra vez;
        // flatMap espera que la función devuelva YA un Optional y no lo
        // re-envuelve. Es la diferencia map/flatMap de la teoría 1.2 destilada
        // al caso mínimo. Los Optional<Optional<...>> reales aparecen al
        // encadenar dos búsquedas: usuario → su dirección → su ciudad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearConFlatMap");
    }

    /**
     * Reto Extra 9: Transformación y validaciones complejas.
     * Recibe un Optional de String. Si está presente, limpia los espacios en blanco
     * extremos (trim).
     * Si tras limpiarlo queda vacío, o si ya estaba vacío inicialmente, retorna
     * Optional.empty().
     * En caso contrario, retorna el String en mayúsculas.
     *
     * @param opt Optional con la cadena
     * @return Optional con el valor normalizado o vacío
     */
    public static Optional<String> reemplazarPorVacioSiInvalido(Optional<String> opt) {
        // GUÍA: la cadena completa map → filter → map, el patrón estrella de 1.2:
        // return opt.map(String::trim) // limpia espacios
        // .filter(s -> !s.isEmpty()) // si quedó vacío → empty
        // .map(String::toUpperCase); // normaliza
        // Sigue los tests: " hello " → "HELLO"; " " → empty; empty → empty.
        // Date cuenta de que NO hay ni un if: el Optional propaga el vacío solo.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para reemplazarPorVacioSiInvalido");
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
        // GUÍA: una línea — return opt1.or(() -> opt2);
        // Optional.or (Java 9+) recibe un Supplier<Optional<T>>: si opt1 tiene
        // valor, gana; si no, evalúa el supplier. Es el "plan B" declarativo:
        // buscar en caché .or(buscar en BD) .or(valor por defecto).
        // No confundir con orElse (devuelve T) — or devuelve OTRO Optional.
        throw new UnsupportedOperationException(
                "TODO: Implementar la lógica del reto extra para obtenerPrimeroDeVarios");
    }

}
