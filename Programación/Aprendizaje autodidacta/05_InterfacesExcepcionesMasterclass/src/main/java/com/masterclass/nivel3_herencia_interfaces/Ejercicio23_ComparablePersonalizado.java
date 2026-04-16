package com.masterclass.nivel3_herencia_interfaces;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 23 — COMPARABLE PERSONALIZADO (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Comparable<T> es una interfaz de Java que define el "orden natural"
 * de un tipo. Si tu clase implementa Comparable, puedes usar Collections.sort(),
 * TreeSet, etc. sin necesidad de un Comparator externo.
 *
 *   public class Producto implements Comparable<Producto> {
 *       public int compareTo(Producto o) {
 *           return Double.compare(this.precio, o.precio);
 *       }
 *   }
 *
 * compareTo devuelve:
 *   - negativo si this < other
 *   - 0 si this == other
 *   - positivo si this > other
 *
 * Lee primero: teoria/03_Herencia_de_Interfaces.md
 */
public class Ejercicio23_ComparablePersonalizado {

    /**
     * TODO: Completa esta clase para que implemente Comparable<Estudiante>.
     * El orden natural debe ser por nota DESCENDENTE (mayor nota primero).
     * Si empatan en nota, desempatar por nombre ASCENDENTE (A-Z).
     */
    public static class Estudiante implements Comparable<Estudiante> {
        private final String nombre;
        private final double nota;

        public Estudiante(String nombre, double nota) {
            this.nombre = nombre;
            this.nota = nota;
        }

        public String getNombre() { return nombre; }
        public double getNota() { return nota; }

        @Override
        public int compareTo(Estudiante o) {
            throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
        }

        @Override
        public String toString() { return nombre + "(" + nota + ")"; }
    }

    /**
     * TODO: Ordena una lista de Estudiantes usando su orden natural (Comparable).
     * Devuelve una nueva lista ordenada.
     */
    public static List<Estudiante> ordenarEstudiantes(List<Estudiante> estudiantes) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve el estudiante con mejor nota de la lista.
     * (Como el orden es por nota DESC, sera el primero despues de ordenar).
     * Devuelve null si la lista esta vacia.
     */
    public static Estudiante mejorEstudiante(List<Estudiante> estudiantes) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
