package com.masterclass.nivel2_default_static_methods;

/**
 * ============================================================================
 *  EJERCICIO 11 — DIAMOND PROBLEM (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Si una clase implementa DOS interfaces que tienen el MISMO
 * metodo default, el compilador te OBLIGA a resolver el conflicto.
 *
 *   interface A { default String id() { return "A"; } }
 *   interface B { default String id() { return "B"; } }
 *   class C implements A, B {
 *       @Override
 *       public String id() {
 *           return A.super.id();  // Eliges cual usar con NombreInterfaz.super
 *       }
 *   }
 *
 * Lee primero: teoria/02_Metodos_Default_y_Static.md
 */
public class Ejercicio11_DiamondProblem {

    public interface Trabajador {
        default String identificarse() {
            return "Soy un trabajador";
        }

        default int prioridad() {
            return 1;
        }
    }

    public interface Voluntario {
        default String identificarse() {
            return "Soy un voluntario";
        }

        default int prioridad() {
            return 2;
        }
    }

    /**
     * TODO: Crea un objeto que implemente AMBAS interfaces (Trabajador y Voluntario).
     * Debe resolver el conflicto de identificarse() asi:
     * - identificarse() -> "Soy un trabajador-voluntario: {nombre}"
     * - prioridad() -> debe devolver la suma de ambas prioridades (Trabajador.super + Voluntario.super)
     */
    public static Object crearTrabajadorVoluntario(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Recibe el resultado de crearTrabajadorVoluntario y llama a identificarse()
     * casteando al tipo adecuado.
     */
    public static String obtenerIdentificacion(Object trabajadorVoluntario) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Recibe el resultado de crearTrabajadorVoluntario y devuelve su prioridad.
     */
    public static int obtenerPrioridad(Object trabajadorVoluntario) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
