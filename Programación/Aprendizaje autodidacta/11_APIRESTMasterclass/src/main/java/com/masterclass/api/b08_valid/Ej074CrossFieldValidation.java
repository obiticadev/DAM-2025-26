package com.masterclass.api.b08_valid;

/**
 * Ejercicio 074 · Validación entre campos.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>Algunas reglas relacionan varios campos (fechaFin &gt;= fechaInicio,
 * password == confirmación). Aquí se valida programáticamente.
 */
public final class Ej074CrossFieldValidation {

    public record RangoFechas(java.time.LocalDate inicio, java.time.LocalDate fin) {
    }

    public record Passwords(String password, String confirmacion) {
    }

    private Ej074CrossFieldValidation() {
    }

    /**
     * @param r rango de fechas
     * @return true si el rango es coherente (fin no anterior a inicio)
     * @throws IllegalArgumentException si inicio o fin son null
     */
    public static boolean rangoValido(RangoFechas r) {
        // TODO 1: si r es null -> IllegalArgumentException.
        // TODO 2: si r.inicio() o r.fin() son null -> IllegalArgumentException.
        // TODO 3: el rango es válido si fin NO es anterior a inicio (fin >= inicio).
        // TODO 4: usa isBefore para comprobar la relación entre los dos campos.
        // TODO 5: devuelve el booleano resultante.
        return false;
    }

    /**
     * @param p par password/confirmación
     * @return true si coinciden y cumplen longitud mínima 8
     */
    public static boolean passwordsCoinciden(Passwords p) {
        // TODO 6: si p es null -> IllegalArgumentException.
        // TODO 7: si password es null -> false (no válido).
        // TODO 8: comprueba longitud mínima 8 de password.
        // TODO 9: comprueba que password.equals(confirmacion) (regla entre 2 campos).
        // TODO 10: devuelve true solo si AMBAS condiciones se cumplen.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(passwordsCoinciden(new Passwords("12345678", "12345678")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si r es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si r.inicio() o r.fin() son null -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: el rango es válido si fin NO es anterior a inicio (fin >= inicio).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: usa isBefore para comprobar la relación entre los dos campos.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: devuelve el booleano resultante.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si p es null -> IllegalArgumentException.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si password es null -> false (no válido).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: comprueba longitud mínima 8 de password.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: comprueba que password.equals(confirmacion) (regla entre 2 campos).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve true solo si AMBAS condiciones se cumplen.
    }

}
