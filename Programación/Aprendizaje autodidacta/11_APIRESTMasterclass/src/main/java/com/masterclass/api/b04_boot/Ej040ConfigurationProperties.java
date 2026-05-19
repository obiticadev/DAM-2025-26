package com.masterclass.api.b04_boot;

import java.util.Map;

/**
 * Ejercicio 040 · Enlace de un bloque tipado (@ConfigurationProperties).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.2).
 *
 * <p>Construye un objeto tipado a partir de propiedades con prefijo, validando.
 */
public final class Ej040ConfigurationProperties {

    /** Bloque de configuración con prefijo "app". */
    public record AppProps(String region, int timeout) {
    }

    private Ej040ConfigurationProperties() {
    }

    /**
     * Enlaza las propiedades con prefijo "app." a un AppProps.
     *
     * @param props mapa plano de propiedades (p.ej. "app.region", "app.timeout")
     * @return AppProps poblado
     * @throws IllegalArgumentException si falta una propiedad obligatoria o el timeout no es número
     */
    public static AppProps bind(Map<String, String> props) {
        // TODO 1: si props es null, lanza IllegalArgumentException.
        // TODO 2: lee la clave "app.region".
        // TODO 3: si "app.region" falta, lanza IllegalArgumentException (obligatoria).
        // TODO 4: lee la clave "app.timeout" como String.
        // TODO 5: si "app.timeout" falta, usa el valor por defecto 30.
        // TODO 6: si está presente, parséalo con Integer.parseInt.
        // TODO 7: captura NumberFormatException y relánzala como IllegalArgumentException.
        // TODO 8: valida que timeout sea > 0 (un timeout no positivo no tiene sentido).
        // TODO 9: construye el record AppProps con los valores resueltos.
        // TODO 10: devuelve el AppProps.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(bind(Map.of("app.region", "eu", "app.timeout", "45")));
    }
}
