package com.masterclass.nivel3_herencia_interfaces;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 19 — INSTANCEOF CON INTERFACES (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: instanceof funciona con interfaces igual que con clases.
 * Puedes comprobar si un objeto implementa una interfaz especifica.
 *
 *   if (obj instanceof Serializable) { ... }
 *
 * Desde Java 16, puedes usar Pattern Matching:
 *   if (obj instanceof Leible leible) { leible.leer(); }
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio19_InstanceofConInterfaces {

    public interface Serializable { String serializar(); }
    public interface Cacheable { String getCacheKey(); }
    public interface Loggeable { String toLogString(); }

    /**
     * TODO: Crea un objeto que implemente SOLO Serializable.
     * serializar() debe devolver "JSON:{dato}"
     */
    public static Object crearSoloSerializable(String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un objeto que implemente Serializable Y Cacheable.
     * serializar() -> "JSON:{dato}", getCacheKey() -> "CACHE_{dato}"
     */
    public static Object crearSerializableYCacheable(String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un objeto que implemente las TRES interfaces.
     * serializar() -> "JSON:{dato}", getCacheKey() -> "CACHE_{dato}", toLogString() -> "LOG[{dato}]"
     */
    public static Object crearCompleto(String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Cuenta cuantos objetos de la lista implementan la interfaz Cacheable.
     */
    public static long contarCacheables(List<Object> objetos) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Intenta serializar el objeto. Si es Serializable, devuelve su serializar().
     * Si no, devuelve "No serializable".
     */
    public static String intentarSerializar(Object objeto) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
