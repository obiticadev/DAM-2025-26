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

        /**
     * RETO EXTRA 01: Verifica clave.
     */
    public static boolean contienePropiedad(java.util.Map<String, String> props, String k) {
        // GUÍA: teoría 19.12 — una línea: return props.containsKey(k);
        // El test ({a:b}, "a") espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contienePropiedad");
    }

    /**
     * RETO EXTRA 02: Obtiene valor.
     */
    public static String obtenerPropiedad(java.util.Map<String, String> props, String k) {
        // GUÍA: teoría 19.12 — una línea: return props.get(k);
        // El test ({a:b}, "a") espera "b". Map.get devuelve null si no existe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPropiedad");
    }

    /**
     * RETO EXTRA 03: Valida si es perfil test.
     */
    public static boolean esPerfilTest(String perfil) {
        // GUÍA: teoría 19.12 — una línea: return "test".equals(perfil);
        // El test ("test") espera true. Invoca equals sobre el literal para
        // evitar NPE si perfil es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPerfilTest");
    }

    /**
     * RETO EXTRA 04: Formatea clave de perfil.
     */
    public static String obtenerClavePerfil(String perfil, String clave) {
        // GUÍA: teoría 19.12 — una línea: return perfil + "." + clave;
        // El test ("test", "url") espera "test.url". Es justo la clave compuesta
        // que construye resolver() para buscar la sobreescritura del perfil.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerClavePerfil");
    }

    /**
     * RETO EXTRA 05: Obtiene total de propiedades.
     */
    public static int obtenerTamanioProps(java.util.Map<String, String> props) {
        // GUÍA: teoría 19.12 — una línea: return props.size();
        // El test ({a:b}) espera 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTamanioProps");
    }

    /**
     * RETO EXTRA 06: Valida si esta vacio.
     */
    public static boolean esMapaVacio(java.util.Map<String, String> props) {
        // GUÍA: teoría 19.12 — una línea: return props.isEmpty();
        // El test (Map.of() vacío) espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMapaVacio");
    }

    /**
     * RETO EXTRA 07: Resuelve con fallback por defecto.
     */
    public static String resolverConDefault(java.util.Map<String, String> props, String perf, String k, String def) {
        // GUÍA: teoría 19.12 — reutiliza resolver() y añade un default final.
        // String v = resolver(props, perf, k);
        // return v != null ? v : def;
        // El test ({} vacío, "test", "url", "def") espera "def": como no hay ni
        // clave de perfil ni base, resolver devuelve null → cae al default.
        // Es la precedencia perfil > base > default (lo que da @Value con :default).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverConDefault");
    }

    /**
     * RETO EXTRA 08: Verifica valor efectivo.
     */
    public static boolean esPropiedadActiva(java.util.Map<String, String> props, String perf, String k, String val) {
        // GUÍA: teoría 19.12 — ¿el valor efectivo resuelto coincide con 'val'?
        // return val.equals(resolver(props, perf, k));
        // El test ({test.url:h2}, "test", "url", "h2") espera true: resolver da
        // "h2" (gana la clave de perfil) y coincide con val. Reutiliza resolver
        // del ejercicio base; invoca equals sobre val por seguridad ante null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropiedadActiva");
    }

    /**
     * RETO EXTRA 09: Crea una copia mutable.
     */
    public static java.util.Map<String, String> limpiarPropiedades(java.util.Map<String, String> props) {
        // GUÍA: teoría 19.12 — copia defensiva en un mapa mutable.
        // return new HashMap<>(props);
        // El test ({a:b}) espera size()==1. El constructor copia las entradas a
        // un HashMap nuevo y modificable; así puedes mutar la copia sin tocar el
        // original (que con Map.of además sería inmutable).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarPropiedades");
    }

    /**
     * RETO EXTRA 10: Combina propiedades.
     */
    public static java.util.Map<String, String> fusionarPropiedades(java.util.Map<String, String> p1, java.util.Map<String, String> p2) {
        // GUÍA: teoría 19.12 — fusiona dos mapas (p2 sobreescribe a p1).
        // Map<String, String> r = new HashMap<>(p1);
        // r.putAll(p2);
        // return r;
        // El test ({a:1}, {b:2}) espera size()==2. ESTO es la fusión base+perfil
        // de Spring: putAll del perfil pisa las claves coincidentes de la base
        // (precedencia del perfil). Crea un mapa nuevo, no mutes p1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para fusionarPropiedades");
    }

}
