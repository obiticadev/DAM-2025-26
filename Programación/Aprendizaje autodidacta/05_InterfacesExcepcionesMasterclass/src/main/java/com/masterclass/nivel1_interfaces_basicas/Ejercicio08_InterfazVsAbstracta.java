package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 *  EJERCICIO 08 — INTERFAZ VS CLASE ABSTRACTA (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Interfaz y clase abstracta NO son lo mismo.
 *
 * INTERFAZ = "puede hacer esto" (capacidad)
 *   - Una clase puede implementar MUCHAS interfaces
 *   - No tiene estado (sin atributos de instancia)
 *   - No tiene constructores
 *
 * CLASE ABSTRACTA = "es un tipo de" (esencia)
 *   - Una clase solo puede extender UNA clase abstracta
 *   - Puede tener estado (atributos) y constructores
 *   - Puede tener metodos con implementacion completa
 *
 * Ejemplo: Un Pato es un Animal (extends Animal) que puede nadar Y volar
 * (implements Nadador, Volador).
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio08_InterfazVsAbstracta {

    /**
     * Interfaz: capacidad de volar.
     */
    public interface Volador {
        String volar();
    }

    /**
     * Interfaz: capacidad de nadar.
     */
    public interface Nadador {
        String nadar();
    }

    /**
     * Clase abstracta: esencia de Animal. Tiene ESTADO (nombre, tipo).
     * NO puedes instanciarla directamente. Debes crear una subclase.
     */
    public static abstract class Animal {
        private final String nombre;
        private final String tipo;

        protected Animal(String nombre, String tipo) {
            this.nombre = nombre;
            this.tipo = tipo;
        }

        public String getNombre() { return nombre; }
        public String getTipo() { return tipo; }

        public abstract String hacerSonido();

        public String presentarse() {
            return "Soy " + nombre + ", un " + tipo;
        }
    }

    /**
     * TODO: Crea un Animal de tipo "Pato" que TAMBIEN sea Volador y Nadador.
     * - hacerSonido() -> "Cuack!"
     * - volar() -> "{nombre} vuela por el cielo"
     * - nadar() -> "{nombre} nada en el lago"
     *
     * PISTA: Necesitas una clase que haga:
     *   extends Animal implements Volador, Nadador
     */
    public static Animal crearPato(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea un Animal de tipo "Pez" que solo sea Nadador (no puede volar).
     * - hacerSonido() -> "Blub!"
     * - nadar() -> "{nombre} nada en el oceano"
     */
    public static Animal crearPez(String nombre) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Comprueba si un Animal puede volar (es instanceof Volador).
     */
    public static boolean puedeVolar(Animal animal) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Comprueba si un Animal puede nadar (es instanceof Nadador).
     */
    public static boolean puedeNadar(Animal animal) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
