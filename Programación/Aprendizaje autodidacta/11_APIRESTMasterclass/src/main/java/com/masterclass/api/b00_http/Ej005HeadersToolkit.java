package com.masterclass.api.b00_http;

import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 005 · Caja de herramientas de cabeceras HTTP.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.2).
 *
 * <p>Las cabeceras HTTP son case-insensitive en el nombre. Implementa búsquedas
 * robustas que no dependan de mayúsculas/minúsculas.
 */
public final class Ej005HeadersToolkit {

    private Ej005HeadersToolkit() {
    }

    /**
     * Busca el valor de una cabecera ignorando mayúsculas/minúsculas en el nombre.
     *
     * @param headers mapa de cabeceras tal cual llegaron
     * @param name    nombre buscado (p.ej. "content-type")
     * @return Optional con el valor, o vacío si no existe
     */
    public static Optional<String> get(Map<String, String> headers, String name) {
        // TODO 1: si headers es null o name es null, devuelve Optional.empty().
        // TODO 2: recorre las entradas del mapa.
        // TODO 3: compara cada clave con 'name' usando equalsIgnoreCase.
        // TODO 4: al primer match, devuelve Optional.of(valor).
        // TODO 5: si ninguna clave coincide, devuelve Optional.empty().
        return Optional.empty();
    }

    /**
     * Indica si una cabecera está presente (case-insensitive).
     *
     * @param headers mapa de cabeceras
     * @param name    nombre buscado
     * @return true si existe
     */
    public static boolean has(Map<String, String> headers, String name) {
        // TODO 6: reutiliza get(...) y devuelve isPresent() (no reimplementes el bucle).
        return false;
    }

    /**
     * Extrae el token Bearer de la cabecera Authorization.
     *
     * @param headers mapa de cabeceras
     * @return el token sin el prefijo "Bearer ", o cadena vacía si no hay
     */
    public static String bearerToken(Map<String, String> headers) {
        // TODO 7: usa get(headers, "Authorization"); si está ausente devuelve "".
        // TODO 8: si el valor NO empieza por "Bearer " devuelve "" (esquema no soportado).
        // TODO 9: recorta el prefijo "Bearer " (7 caracteres) para quedarte con el token.
        // TODO 10: devuelve el token resultante sin espacios sobrantes.
        return "";
    }

    public static void main(String[] args) {
        var h = Map.of("Content-Type", "application/json", "Authorization", "Bearer abc.def");
        System.out.println(get(h, "content-type"));
        System.out.println(bearerToken(h));
    }
}
