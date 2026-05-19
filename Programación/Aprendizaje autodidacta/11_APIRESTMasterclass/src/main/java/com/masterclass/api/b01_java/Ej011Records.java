package com.masterclass.api.b01_java;

/**
 * Ejercicio 011 · Records como DTO inmutable.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.1).
 *
 * <p>Los componentes del record son su <em>contrato</em> (firma), por eso vienen
 * declarados. Lo que debes implementar es la validación y la copia inmutable.
 */
public final class Ej011Records {

    /** DTO inmutable de un producto. */
    public record ProductoDto(Long id, String nombre, double precio) {

        /**
         * Constructor compacto de validación.
         *
         * @throws IllegalArgumentException si el precio es negativo o el nombre está vacío
         */
        public ProductoDto {
            // TODO 1: si precio < 0, lanza IllegalArgumentException (un precio no puede ser negativo).
            // TODO 2: si nombre es null, lanza IllegalArgumentException.
            // TODO 3: si nombre está en blanco (solo espacios), lanza IllegalArgumentException.
            // TODO 4: opcional defensivo: normaliza 'nombre' con trim antes de asignarlo.
        }

        /**
         * Devuelve una copia con el precio incrementado un porcentaje.
         *
         * @param porcentaje p.ej. 21.0 para +21 %
         * @return nuevo ProductoDto con el precio ajustado (el record es inmutable)
         */
        public ProductoDto conIva(double porcentaje) {
            // TODO 5: calcula el factor multiplicador (1 + porcentaje/100).
            // TODO 6: calcula el nuevo precio aplicando ese factor al precio actual.
            // TODO 7: NO mutes este record (son inmutables): crea uno nuevo.
            // TODO 8: conserva id y nombre originales en la copia.
            // TODO 9: devuelve la nueva instancia.
            return null;
        }

        /**
         * @return true si el producto es "caro" (precio &gt;= 100).
         */
        public boolean esCaro() {
            // TODO 10: devuelve si el precio es mayor o igual a 100.
            return false;
        }
    }

    public static void main(String[] args) {
        ProductoDto p = new ProductoDto(1L, "Teclado", 100.0);
        System.out.println(p + " -> " + p.conIva(21) + " caro=" + p.esCaro());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si precio < 0, lanza IllegalArgumentException (un precio no puede ser negativo).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si nombre es null, lanza IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si nombre está en blanco (solo espacios), lanza IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: opcional defensivo: normaliza 'nombre' con trim antes de asignarlo.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: calcula el factor multiplicador (1 + porcentaje/100).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: calcula el nuevo precio aplicando ese factor al precio actual.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: NO mutes este record (son inmutables): crea uno nuevo.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: conserva id y nombre originales en la copia.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve la nueva instancia.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve si el precio es mayor o igual a 100.
    }

}
