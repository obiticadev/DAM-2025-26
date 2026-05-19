package com.masterclass.api.b03_core;

/**
 * Ejercicio 032 · @Qualifier / @Primary (resolución entre varios candidatos).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.2).
 *
 * <p>Versión sin Spring: cuando hay varias implementaciones, el "selector"
 * decide cuál usar por nombre, igual que hace @Qualifier.
 */
public final class Ej032QualifierAndPrimary {

    public interface Notificador {
        String enviar(String msg);
    }

    public static class EmailNotificador implements Notificador {
        public String enviar(String msg) {
            return "email:" + msg;
        }
    }

    public static class SmsNotificador implements Notificador {
        public String enviar(String msg) {
            return "sms:" + msg;
        }
    }

    private Ej032QualifierAndPrimary() {
    }

    /**
     * Resuelve la implementación por su "qualifier".
     *
     * @param qualifier "email" o "sms"
     * @return la implementación correspondiente
     * @throws IllegalArgumentException si el qualifier no se reconoce
     */
    public static Notificador resolver(String qualifier) {
        // TODO 1: si qualifier es null, lanza IllegalArgumentException.
        // TODO 2: normaliza el qualifier (trim + minúsculas) para comparar.
        // TODO 3: usa un switch sobre el valor normalizado.
        // TODO 4: caso "email" -> new EmailNotificador().
        // TODO 5: caso "sms" -> new SmsNotificador().
        // TODO 6: default -> IllegalArgumentException con mensaje indicando el valor inválido.
        return null;
    }

    /**
     * Devuelve la implementación marcada como primaria (la usada si no se cualifica).
     *
     * @return la implementación por defecto (EmailNotificador)
     */
    public static Notificador primario() {
        // TODO 7: @Primary significa "el elegido cuando no se especifica qualifier".
        // TODO 8: aquí el primario es EmailNotificador.
        // TODO 9: reutiliza resolver("email") para no duplicar la construcción.
        // TODO 10: devuelve esa instancia.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(resolver("sms").enviar("hi"));
        System.out.println(primario().enviar("hi"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si qualifier es null, lanza IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: normaliza el qualifier (trim + minúsculas) para comparar.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: usa un switch sobre el valor normalizado.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: caso "email" -> new EmailNotificador().
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: caso "sms" -> new SmsNotificador().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: default -> IllegalArgumentException con mensaje indicando el valor inválido.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: @Primary significa "el elegido cuando no se especifica qualifier".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: aquí el primario es EmailNotificador.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: reutiliza resolver("email") para no duplicar la construcción.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve esa instancia.
    }

}
