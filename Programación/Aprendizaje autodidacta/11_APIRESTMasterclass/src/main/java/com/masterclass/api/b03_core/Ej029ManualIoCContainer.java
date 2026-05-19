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
}
