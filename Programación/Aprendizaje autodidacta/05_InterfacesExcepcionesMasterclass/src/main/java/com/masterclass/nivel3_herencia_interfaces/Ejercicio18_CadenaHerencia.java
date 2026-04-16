package com.masterclass.nivel3_herencia_interfaces;

/**
 * ============================================================================
 *  EJERCICIO 18 — CADENA DE HERENCIA DE INTERFACES (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Las interfaces pueden formar cadenas de herencia profundas.
 * Cada nivel anade nuevos contratos. La clase que implementa la interfaz
 * mas profunda debe cumplir TODOS los contratos de la cadena.
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio18_CadenaHerencia {

    public interface Identificable {
        String getId();
    }

    public interface Nombrable extends Identificable {
        String getNombre();
    }

    public interface Describible extends Nombrable {
        String getDescripcion();
    }

    public interface Presentable extends Describible {
        default String presentar() {
            return "[" + getId() + "] " + getNombre() + " - " + getDescripcion();
        }
    }

    /**
     * TODO: Crea un Presentable con los datos dados.
     * Debe implementar getId(), getNombre(), getDescripcion().
     * El default presentar() funcionara automaticamente.
     */
    public static Presentable crearProducto(String id, String nombre, String descripcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Comprueba si un Identificable es tambien Describible.
     */
    public static boolean esDescribible(Identificable entidad) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
