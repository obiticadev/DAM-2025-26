package com.masterclass.api.b09_err;

/**
 * Ejercicio 079 · Jerarquía de excepciones de dominio → status HTTP.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 */
public final class Ej079DomainExceptionHierarchy {

    /** Raíz de las excepciones de negocio. */
    public static class DominioException extends RuntimeException {
        public DominioException(String m) {
            super(m);
        }
    }

    public static class NoEncontradoException extends DominioException {
        public NoEncontradoException(String m) {
            super(m);
        }
    }

    public static class ConflictoException extends DominioException {
        public ConflictoException(String m) {
            super(m);
        }
    }

    public static class NoAutorizadoException extends DominioException {
        public NoAutorizadoException(String m) {
            super(m);
        }
    }

    private Ej079DomainExceptionHierarchy() {
    }

    /**
     * Mapea una excepción de dominio a su código HTTP.
     *
     * @param ex excepción lanzada por la capa de negocio
     * @return el status HTTP correspondiente
     */
    public static int aStatus(DominioException ex) {
        // TODO 1: si ex es null -> IllegalArgumentException.
        // TODO 2: usa instanceof / switch de patrones sobre el tipo concreto.
        // TODO 3: NoEncontradoException -> 404.
        // TODO 4: ConflictoException -> 409.
        // TODO 5: NoAutorizadoException -> 403.
        // TODO 6: cualquier otra DominioException (genérica) -> 400.
        // TODO 7: comprueba los subtipos ANTES que la clase base (orden importa).
        // TODO 8: no devuelvas 500 para errores de dominio (500 es fallo del servidor).
        // TODO 9: el mapeo debe ser exhaustivo para los 3 subtipos + base.
        // TODO 10: devuelve el entero del status.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(aStatus(new ConflictoException("dup")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si ex es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa instanceof / switch de patrones sobre el tipo concreto.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: NoEncontradoException -> 404.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: ConflictoException -> 409.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: NoAutorizadoException -> 403.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: cualquier otra DominioException (genérica) -> 400.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: comprueba los subtipos ANTES que la clase base (orden importa).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no devuelvas 500 para errores de dominio (500 es fallo del servidor).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el mapeo debe ser exhaustivo para los 3 subtipos + base.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el entero del status.
    }

}
