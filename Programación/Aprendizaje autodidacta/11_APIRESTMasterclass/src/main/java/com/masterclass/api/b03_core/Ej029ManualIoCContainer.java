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
        // TODO extra: Reto Extra 1: Registra una fábrica de alcance PROTOTYPE.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerPrototype");
    }

    /**
     * Reto Extra 2: Registra una instancia pre-construida de un bean asociada a un nombre identificativo.
     */
    public void registerSingletonInstance(String nombre, Object instancia) {
        // TODO extra: Reto Extra 2: Registra una instancia pre-construida de un bean asociada a un nombre identificativo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerSingletonInstance");
    }

    /**
     * Reto Extra 3: Recupera un bean registrado a partir de su nombre identificativo.
     */
    public Object getBeanByName(String nombre) {
        // TODO extra: Reto Extra 3: Recupera un bean registrado a partir de su nombre identificativo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeanByName");
    }

    /**
     * Reto Extra 4: Comprueba si existe algún registro del bean en el contenedor.
     */
    public boolean hasBean(Class<?> tipo) {
        // TODO extra: Reto Extra 4: Comprueba si existe algún registro del bean en el contenedor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hasBean");
    }

    /**
     * Reto Extra 5: Vacía y limpia por completo todas las estructuras de registro y cachés del contenedor.
     */
    public void clear() {
        // TODO extra: Reto Extra 5: Vacía y limpia por completo todas las estructuras de registro y cachés del contenedor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clear");
    }

    /**
     * Reto Extra 6: Devuelve el recuento total de tipos o nombres registrados en el contenedor.
     */
    public int getBeanCount() {
        // TODO extra: Reto Extra 6: Devuelve el recuento total de tipos o nombres registrados en el contenedor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeanCount");
    }

    /**
     * Reto Extra 7: Registra un bean cuya fábrica depende dinámicamente de la resolución de otro bean registrado.
     */
    public <T> void registerWithDependency(Class<T> tipo, Class<?> dep, java.util.function.Function<Object, T> fabrica) {
        // TODO extra: Reto Extra 7: Registra un bean cuya fábrica depende dinámicamente de la resolución de otro bean registrado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerWithDependency");
    }

    /**
     * Reto Extra 8: Resuelve y devuelve todas las instancias singleton que hereden o implementen el tipo indicado.
     */
    public <T> java.util.Map<String, T> getBeansOfType(Class<T> tipo) {
        // TODO extra: Reto Extra 8: Resuelve y devuelve todas las instancias singleton que hereden o implementen el tipo indicado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getBeansOfType");
    }

    /**
     * Reto Extra 9: Registra un alias alternativo que apunte al nombre de un bean ya existente.
     */
    public void registerAlias(String originalName, String alias) {
        // TODO extra: Reto Extra 9: Registra un alias alternativo que apunte al nombre de un bean ya existente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registerAlias");
    }

    /**
     * Reto Extra 10: Callback de destrucción ordenada para liberar recursos de los singletons.
     */
    @Override
    public void close() throws Exception {
        // TODO extra: Reto Extra 10: Callback de destrucción ordenada para liberar recursos de los singletons.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para close");
    }

}
