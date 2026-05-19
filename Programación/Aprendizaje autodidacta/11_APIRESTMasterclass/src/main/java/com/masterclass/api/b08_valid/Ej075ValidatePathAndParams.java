package com.masterclass.api.b08_valid;

/**
 * Ejercicio 075 · Validación de path variables y query params.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>No solo los bodies se validan: id de ruta debe ser positivo, page &gt;= 0,
 * size en [1, 100]. Aquí se valida manualmente la "constraint" de cada parámetro.
 */
public final class Ej075ValidatePathAndParams {

    private Ej075ValidatePathAndParams() {
    }

    /**
     * Valida un id de path (equivalente a @Positive sobre @PathVariable).
     *
     * @param id identificador recibido en la ruta
     * @throws IllegalArgumentException si id &lt;= 0, mensaje "id debe ser positivo"
     */
    public static void validarId(long id) {
        // TODO 1: un id de recurso siempre debe ser > 0.
        // TODO 2: si id <= 0, lanza IllegalArgumentException("id debe ser positivo").
        // TODO 3: si es válido, simplemente retorna (sin valor).
    }

    /**
     * Normaliza/valida los parámetros de paginación.
     *
     * @param page índice de página (debe ser &gt;= 0)
     * @param size tamaño de página (debe estar en [1, 100])
     * @return un array {pageValido, sizeValido} ya saneado
     * @throws IllegalArgumentException si los valores están fuera de rango
     */
    public static int[] validarPaginacion(int page, int size) {
        // TODO 4: si page < 0 -> IllegalArgumentException("page >= 0").
        // TODO 5: si size < 1 -> IllegalArgumentException("size >= 1").
        // TODO 6: si size > 100 -> IllegalArgumentException("size <= 100").
        // TODO 7: estas son las constraints típicas de @RequestParam paginado.
        // TODO 8: si todo es válido, construye int[]{page, size}.
        // TODO 9: NO satures silenciosamente fuera de rango: el contrato es fallar.
        // TODO 10: devuelve el array validado.
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        validarId(5);
        System.out.println(java.util.Arrays.toString(validarPaginacion(0, 20)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: un id de recurso siempre debe ser > 0.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si id <= 0, lanza IllegalArgumentException("id debe ser positivo").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si es válido, simplemente retorna (sin valor).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si page < 0 -> IllegalArgumentException("page >= 0").
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si size < 1 -> IllegalArgumentException("size >= 1").
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si size > 100 -> IllegalArgumentException("size <= 100").
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: estas son las constraints típicas de @RequestParam paginado.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si todo es válido, construye int[]{page, size}.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: NO satures silenciosamente fuera de rango: el contrato es fallar.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el array validado.
    }

}
