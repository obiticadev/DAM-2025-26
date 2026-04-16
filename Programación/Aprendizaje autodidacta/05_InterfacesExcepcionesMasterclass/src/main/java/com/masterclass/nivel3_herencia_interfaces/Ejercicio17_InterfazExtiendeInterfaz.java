package com.masterclass.nivel3_herencia_interfaces;

/**
 * ============================================================================
 *  EJERCICIO 17 — INTERFAZ QUE EXTIENDE OTRA INTERFAZ (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una interfaz puede extender (heredar de) otra interfaz.
 * La interfaz hija hereda todos los metodos de la padre y puede anadir mas.
 *
 *   interface Leible { String leer(); }
 *   interface LeibleConFormato extends Leible {
 *       String leerFormateado(String formato);
 *   }
 *
 * Quien implemente LeibleConFormato debe implementar AMBOS: leer() y leerFormateado().
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio17_InterfazExtiendeInterfaz {

    public interface Identificable {
        String getId();
    }

    public interface Nombrable extends Identificable {
        String getNombre();
    }

    /**
     * TODO: Crea un Nombrable con el id y nombre dados.
     * Debe implementar AMBOS metodos: getId() y getNombre().
     */
    public static Nombrable crearEntidad(String id, String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado un Identificable, devuelve su id en formato: "ID: {id}".
     * Nota: acepta Identificable, asi que tanto Identificable como Nombrable sirven.
     */
    public static String formatearId(Identificable entidad) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
