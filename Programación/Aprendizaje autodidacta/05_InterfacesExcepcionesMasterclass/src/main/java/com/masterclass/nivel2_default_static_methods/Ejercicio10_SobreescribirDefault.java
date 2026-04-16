package com.masterclass.nivel2_default_static_methods;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 10 — SOBREESCRIBIR UN METODO DEFAULT (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Aunque un metodo default ya tiene implementacion, una clase
 * puede SOBREESCRIBIRLO con @Override para personalizar el comportamiento.
 *
 * Esto es exactamente como sobreescribir un metodo heredado de una superclase,
 * pero aplicado a interfaces.
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio10_SobreescribirDefault {

    /**
     * Interfaz con un metodo default que crea un saludo estandar.
     */
    public interface Saludador {
        String getNombre();

        default String saludar() {
            return "Hola, soy " + getNombre();
        }
    }

    /**
     * TODO: Crea un Saludador con el nombre dado que USE el saludo default.
     * No sobreescribas saludar().
     */
    public static Saludador crearSaludadorNormal(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Saludador con el nombre dado que SOBREESCRIBA saludar()
     * para devolver: "Buenos dias! Me llamo {nombre} y soy VIP"
     */
    public static Saludador crearSaludadorVIP(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Recibe una lista de Saludadores y devuelve una lista con
     * el resultado de llamar a saludar() en cada uno.
     */
    public static List<String> obtenerSaludos(List<Saludador> saludadores) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
