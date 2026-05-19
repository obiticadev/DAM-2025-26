package com.masterclass.api.b01_java;

/**
 * Ejercicio 019 · Excepciones y try-with-resources.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.5).
 */
public final class Ej019ExceptionsAndTryWith {

    private Ej019ExceptionsAndTryWith() {
    }

    /** Excepción de negocio no comprobada. */
    public static class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String msg) {
            super(msg);
        }
    }

    /** Recurso simulado que registra si fue cerrado. */
    public static class Conexion implements AutoCloseable {
        public boolean cerrada = false;
        public String leer() {
            return "datos";
        }
        @Override
        public void close() {
            this.cerrada = true;
        }
    }

    /**
     * Busca un recurso por id; lanza si no existe.
     *
     * @param id identificador (válido si es &gt; 0)
     * @return "recurso-{id}"
     * @throws RecursoNoEncontradoException si id &lt;= 0, con mensaje "no existe: {id}"
     */
    public static String buscar(int id) {
        // TODO 1: comprueba la precondición id > 0.
        // TODO 2: si id <= 0, construye el mensaje "no existe: " + id.
        // TODO 3: lanza RecursoNoEncontradoException con ese mensaje.
        // TODO 4: si la precondición se cumple, construye "recurso-" + id.
        // TODO 5: devuelve esa cadena.
        return "";
    }

    /**
     * Usa la conexión dentro de un try-with-resources y devuelve lo leído.
     *
     * @param c conexión a usar y cerrar automáticamente
     * @return el resultado de c.leer()
     */
    public static String usarYCerrar(Conexion c) {
        // TODO 6: abre un try-with-resources declarando el recurso (try (var r = c)).
        // TODO 7: dentro del try, invoca r.leer() y guarda el resultado.
        // TODO 8: el cierre debe ser automático al salir del try (no llames close() a mano).
        // TODO 9: garantiza que se cierra incluso si hubiera excepción (lo hace try-with-resources).
        // TODO 10: devuelve el valor leído.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(buscar(5));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: comprueba la precondición id > 0.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si id <= 0, construye el mensaje "no existe: " + id.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: lanza RecursoNoEncontradoException con ese mensaje.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si la precondición se cumple, construye "recurso-" + id.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: devuelve esa cadena.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: abre un try-with-resources declarando el recurso (try (var r = c)).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: dentro del try, invoca r.leer() y guarda el resultado.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el cierre debe ser automático al salir del try (no llames close() a mano).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: garantiza que se cierra incluso si hubiera excepción (lo hace try-with-resources).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el valor leído.
    }

}
