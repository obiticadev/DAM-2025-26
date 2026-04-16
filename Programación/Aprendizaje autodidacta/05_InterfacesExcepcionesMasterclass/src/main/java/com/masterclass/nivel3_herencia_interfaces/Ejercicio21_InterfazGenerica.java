package com.masterclass.nivel3_herencia_interfaces;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 21 — INTERFAZ GENERICA (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Las interfaces pueden usar GENERICOS para ser reutilizables
 * con cualquier tipo. El tipo se especifica al implementar.
 *
 *   interface Transformable<T> { T transformar(T entrada); }
 *   class Duplicador implements Transformable<String> {
 *       public String transformar(String e) { return e + e; }
 *   }
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio21_InterfazGenerica {

    /**
     * Interfaz generica: convierte un tipo E (entrada) a tipo S (salida).
     */
    public interface Convertidor<E, S> {
        S convertir(E entrada);
    }

    /**
     * TODO: Crea un Convertidor<String, Integer> que devuelva la longitud del String.
     */
    public static Convertidor<String, Integer> crearContadorCaracteres() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Convertidor<Integer, String> que convierta un entero a su
     * representacion binaria (usa Integer.toBinaryString).
     */
    public static Convertidor<Integer, String> crearConvertidorBinario() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Convertidor<String, String> que convierta a mayusculas.
     */
    public static Convertidor<String, String> crearConvertidorMayusculas() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Aplica un Convertidor a toda una lista y devuelve la lista transformada.
     */
    public static <E, S> List<S> convertirLista(List<E> lista, Convertidor<E, S> convertidor) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
