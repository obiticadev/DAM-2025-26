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
public class Ej029ManualIoCContainer implements AutoCloseable {

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

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Registra una fábrica de alcance PROTOTYPE.
     * Cada vez que se invoque getBean(tipo), debe retornarse una nueva instancia ejecutando esta fábrica.
     */
    public <T> void registerPrototype(Class<T> tipo, Supplier<T> fabrica) {
        // TODO extra (Reto 1): Registra una fábrica prototype.
    }

    /**
     * Reto Extra 2: Registra una instancia pre-construida de un bean asociada a un nombre identificativo.
     */
    public void registerSingletonInstance(String nombre, Object instancia) {
        // TODO extra (Reto 2): Registra una instancia singleton ya creada indexada por su nombre.
    }

    /**
     * Reto Extra 3: Recupera un bean registrado a partir de su nombre identificativo.
     */
    public Object getBeanByName(String nombre) {
        // TODO extra (Reto 3): Busca y devuelve la instancia singleton asociada a dicho nombre.
        // Lanza IllegalStateException si no existe.
        return null;
    }

    /**
     * Reto Extra 4: Comprueba si existe algún registro del bean en el contenedor.
     */
    public boolean hasBean(Class<?> tipo) {
        // TODO extra (Reto 4): Retorna true si el tipo tiene asociada fábrica o instancia cacheada.
        return false;
    }

    /**
     * Reto Extra 5: Vacía y limpia por completo todas las estructuras de registro y cachés del contenedor.
     */
    public void clear() {
        // TODO extra (Reto 5): Restablece el contenedor a su estado inicial vacío.
    }

    /**
     * Reto Extra 6: Devuelve el recuento total de tipos o nombres registrados en el contenedor.
     */
    public int getBeanCount() {
        // TODO extra (Reto 6): Devuelve el número total de beans definidos/registrados.
        return 0;
    }

    /**
     * Reto Extra 7: Registra un bean cuya fábrica depende dinámicamente de la resolución de otro bean registrado.
     */
    public <T> void registerWithDependency(Class<T> tipo, Class<?> dep, java.util.function.Function<Object, T> fabrica) {
        // TODO extra (Reto 7): Define una fábrica que se invoque pasando como argumento el bean 'dep' obtenido del contenedor.
    }

    /**
     * Reto Extra 8: Resuelve y devuelve todas las instancias singleton que hereden o implementen el tipo indicado.
     */
    public <T> java.util.Map<String, T> getBeansOfType(Class<T> tipo) {
        // TODO extra (Reto 8): Recorre las instancias singleton cacheadas y devuelve un mapa indexado por nombre/clase con las que coincidan.
        return java.util.Map.of();
    }

    /**
     * Reto Extra 9: Registra un alias alternativo que apunte al nombre de un bean ya existente.
     */
    public void registerAlias(String originalName, String alias) {
        // TODO extra (Reto 9): Permite que getBeanByName resuelva el bean original usando el alias.
    }

    /**
     * Reto Extra 10: Callback de destrucción ordenada para liberar recursos de los singletons.
     */
    @Override
    public void close() throws Exception {
        // TODO extra (Reto 10): Recorre todas las instancias registradas y si implementan AutoCloseable, invoca su close().
    }

}
