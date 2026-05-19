package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 029 · Mini-contenedor IoC didáctico.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.1).
 *
 * <p>Recrea, en pequeño, lo que hace Spring: registrar fábricas de beans y
 * resolverlos como singletons (la misma instancia en cada {@code getBean}).
 */
public class Ej029ManualIoCContainer {

    /**
     * Registra una fábrica para un tipo. El bean se crea de forma perezosa
     * la primera vez que se pide y luego se cachea (singleton).
     *
     * @param tipo    clase que actúa como clave
     * @param fabrica proveedor que construye la instancia
     * @param <T>     tipo del bean
     */
    public <T> void register(Class<T> tipo, Supplier<T> fabrica) {
        // TODO 1: necesitas una estructura interna para fábricas (Map<Class,Supplier>).
        // TODO 2: y otra para instancias ya creadas (caché de singletons).
        // TODO 3: valida que 'tipo' y 'fabrica' no sean null.
        // TODO 4: guarda la fábrica indexada por 'tipo'.
        throw new UnsupportedOperationException("TODO no implementado");
    }

    /**
     * Devuelve el bean del tipo pedido, creándolo la primera vez (singleton).
     *
     * @param tipo clase pedida
     * @param <T>  tipo del bean
     * @return la instancia (siempre la misma para el mismo tipo)
     * @throws IllegalStateException si el tipo no fue registrado
     */
    public <T> T getBean(Class<T> tipo) {
        // TODO 5: si no hay fábrica registrada para 'tipo' -> IllegalStateException.
        // TODO 6: si ya existe instancia cacheada, devuélvela (mismo objeto).
        // TODO 7: si no, invoca la fábrica para crear la instancia.
        // TODO 8: guarda la instancia en la caché de singletons.
        // TODO 9: castea con tipo.cast(...) para devolver el tipo correcto sin warnings.
        // TODO 10: devuelve la instancia (futuras llamadas devolverán la misma).
        return null;
    }

    public static void main(String[] args) {
        var c = new Ej029ManualIoCContainer();
        c.register(StringBuilder.class, StringBuilder::new);
        System.out.println(c.getBean(StringBuilder.class) == c.getBean(StringBuilder.class));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: necesitas una estructura interna para fábricas (Map<Class,Supplier>).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: y otra para instancias ya creadas (caché de singletons).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: valida que 'tipo' y 'fabrica' no sean null.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: guarda la fábrica indexada por 'tipo'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si no hay fábrica registrada para 'tipo' -> IllegalStateException.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si ya existe instancia cacheada, devuélvela (mismo objeto).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si no, invoca la fábrica para crear la instancia.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: guarda la instancia en la caché de singletons.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: castea con tipo.cast(...) para devolver el tipo correcto sin warnings.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la instancia (futuras llamadas devolverán la misma).
    }

}
