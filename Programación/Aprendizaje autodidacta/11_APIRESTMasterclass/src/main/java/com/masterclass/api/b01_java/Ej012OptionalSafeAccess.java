package com.masterclass.api.b01_java;

import java.util.List;
import java.util.Optional;

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
}
