package com.masterclass.api.b01_java;

/**
 * Ejercicio 018 · Jerarquía sellada + pattern matching en switch.
 *
 * <p>Teoría: {@code teoria/01_Java_Moderno_para_APIs.md} (sección 1.5).
 *
 * <p>Modela el resultado de una operación de API como una jerarquía cerrada.
 */
public final class Ej018SealedPatternMatching {

    /** Resultado sellado: solo puede ser Ok, NotFound o Fallo. */
    public sealed interface Resultado permits Ok, NotFound, Fallo {
    }

    public record Ok(String valor) implements Resultado {
    }

    public record NotFound(Object id) implements Resultado {
    }

    public record Fallo(String mensaje) implements Resultado {
    }

    private Ej018SealedPatternMatching() {
    }

    /**
     * Traduce un Resultado a un código HTTP usando switch con patrones.
     *
     * @param r resultado de dominio
     * @return 200 para Ok, 404 para NotFound, 500 para Fallo
     */
    public static int aHttpStatus(Resultado r) {
        // TODO 1: usa un switch de PATRONES sobre 'r' (switch expression).
        // TODO 2: case Ok ok -> 200.
        // TODO 3: case NotFound nf -> 404.
        // TODO 4: case Fallo f -> 500.
        // TODO 5: al ser 'sealed' el switch es exhaustivo: NO añadas 'default'.
        return -1;
    }

    /**
     * Devuelve un mensaje legible para el cliente.
     *
     * @param r resultado de dominio
     * @return texto descriptivo según el subtipo
     */
    public static String describir(Resultado r) {
        // TODO 6: switch de patrones devolviendo un String por caso.
        // TODO 7: para Ok, incluye el valor (p.ej. "OK: " + ok.valor()).
        // TODO 8: para NotFound, incluye el id buscado.
        // TODO 9: para Fallo, incluye el mensaje de error.
        // TODO 10: aprovecha el binding del patrón (ok/nf/f) para acceder a los campos.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(aHttpStatus(new Ok("hola")));
        System.out.println(describir(new NotFound(7)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: usa un switch de PATRONES sobre 'r' (switch expression).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: case Ok ok -> 200.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: case NotFound nf -> 404.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: case Fallo f -> 500.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: al ser 'sealed' el switch es exhaustivo: NO añadas 'default'.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: switch de patrones devolviendo un String por caso.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: para Ok, incluye el valor (p.ej. "OK: " + ok.valor()).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: para NotFound, incluye el id buscado.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: para Fallo, incluye el mensaje de error.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: aprovecha el binding del patrón (ok/nf/f) para acceder a los campos.
    }

}
