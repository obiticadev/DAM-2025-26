package com.masterclass.api.b19_test;

import java.util.Map;

/**
 * Ejercicio 175 · Slices y perfil test (selección de config como función pura).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.12).
 *
 * <p>{@code @ActiveProfiles("test")} selecciona propiedades aisladas
 * (BD en memoria, secretos falsos). Modelamos esa resolución de
 * propiedades por perfil como función pura, validable sin Spring.
 */
public final class Ej175TestSlicesAndProfiles {

    private Ej175TestSlicesAndProfiles() {
    }

    /**
     * Resuelve el valor efectivo de una propiedad según el perfil activo.
     *
     * <p>Prioridad: una clave en el perfil activo (p.ej. {@code "test.url"})
     * gana sobre la clave base ({@code "url"}). Es lo que hace Spring al
     * fusionar {@code application.yml} con {@code application-test.yml}.
     *
     * @param props   propiedades base + sobreescrituras por perfil (no null)
     * @param perfil  perfil activo, p.ej. "test" (no null/blank)
     * @param clave   clave de propiedad a resolver (no null/blank)
     * @return valor del perfil si existe, si no el base, o null si no hay ninguno
     * @throws IllegalArgumentException si algún argumento es inválido
     */
    public static String resolver(Map<String, String> props, String perfil, String clave) {
        // TODO 1: si props es null -> IllegalArgumentException.
        // TODO 2: si perfil es null o blank -> IllegalArgumentException.
        // TODO 3: si clave es null o blank -> IllegalArgumentException.
        // TODO 4: construye la clave del perfil: perfil + "." + clave.
        // TODO 5: si props contiene la clave del perfil -> ese valor gana.
        // TODO 6: si no, intenta la clave base 'clave' (fallback).
        // TODO 7: si tampoco existe la base -> devuelve null (sin valor).
        // TODO 8: no mutes el mapa de propiedades de entrada.
        // TODO 9: la precedencia perfil>base modela la fusión de Spring.
        // TODO 10: devuelve el valor efectivo resuelto.
        return null;
    }

    public static void main(String[] args) {
        Map<String, String> p = Map.of("url", "prod-db", "test.url", "h2-mem");
        System.out.println(resolver(p, "test", "url"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si props es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si perfil es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si clave es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: construye la clave del perfil: perfil + "." + clave.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si props contiene la clave del perfil -> ese valor gana.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si no, intenta la clave base 'clave' (fallback).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si tampoco existe la base -> devuelve null (sin valor).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no mutes el mapa de propiedades de entrada.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: la precedencia perfil>base modela la fusión de Spring.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el valor efectivo resuelto.
    }

}
