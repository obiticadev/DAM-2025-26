package com.masterclass.api.b01_java;

import java.util.Objects;

/**
 * Ejercicio 022 · Contratos equals/hashCode (clave para Sets y JPA).
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.1).
 */
public final class Ej022EqualsHashCodeContracts {

    /** Entidad de negocio identificada por su 'codigo' (no por la referencia). */
    public static final class Articulo {
        private final String codigo;
        private final String nombre;

        public Articulo(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public String codigo() {
            return codigo;
        }

        public String nombre() {
            return nombre;
        }

        /**
         * Dos artículos son iguales si comparten 'codigo' (la identidad de negocio).
         *
         * @param o objeto a comparar
         * @return true si es un Articulo con el mismo codigo
         */
        @Override
        public boolean equals(Object o) {
            // TODO 1: si o == this, devuelve true (reflexividad, atajo).
            // TODO 2: si o es null, devuelve false.
            // TODO 3: si la clase de o no es Articulo, devuelve false.
            // TODO 4: castea o a Articulo.
            // TODO 5: compara ÚNICAMENTE el campo 'codigo' con Objects.equals.
            // TODO 6: devuelve el resultado de esa comparación.
            return false;
        }

        /**
         * Coherente con equals: basado solo en 'codigo'.
         *
         * @return hash consistente con equals
         */
        @Override
        public int hashCode() {
            // TODO 7: el hashCode DEBE basarse en los mismos campos que equals (solo 'codigo').
            // TODO 8: usa Objects.hash(codigo) (o codigo.hashCode() controlando null).
            // TODO 9: dos objetos equals() deben tener el MISMO hashCode (contrato).
            // TODO 10: devuelve ese valor.
            return 0;
        }
    }

    private Ej022EqualsHashCodeContracts() {
    }

    public static void main(String[] args) {
        var a = new Articulo("A1", "Tornillo");
        var b = new Articulo("A1", "Tornillo (rebautizado)");
        System.out.println("equals=" + a.equals(b) + " mismoHash=" + (a.hashCode() == b.hashCode()));
        System.out.println(Objects.equals(a, b));
    }
}
