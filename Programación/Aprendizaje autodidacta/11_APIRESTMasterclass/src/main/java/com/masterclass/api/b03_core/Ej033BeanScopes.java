package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 033 · Scopes: singleton vs prototype.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.4).
 */
public class Ej033BeanScopes<T> {

    private final Supplier<T> fabrica;
    private final boolean singleton;

    /**
     * @param fabrica   crea instancias del bean
     * @param singleton true = misma instancia siempre; false = una nueva por llamada
     */
    public Ej033BeanScopes(Supplier<T> fabrica, boolean singleton) {
        this.fabrica = fabrica;
        this.singleton = singleton;
    }

    /**
     * Obtiene una instancia respetando el scope configurado.
     *
     * @return la instancia (cacheada si es singleton; nueva si es prototype)
     */
    public T get() {
        // TODO 1: necesitas un campo interno para cachear la instancia singleton.
        // TODO 2: comprueba el flag 'singleton'.
        // TODO 3: si NO es singleton (prototype), devuelve siempre fabrica.get() (instancia nueva).
        // TODO 4: si es singleton, comprueba si ya hay instancia cacheada.
        // TODO 5: si la caché está vacía, créala con fabrica.get().
        // TODO 6: guárdala en el campo de caché.
        // TODO 7: en llamadas siguientes, NO vuelvas a invocar la fábrica.
        // TODO 8: devuelve siempre la MISMA instancia para singleton.
        // TODO 9: ten en cuenta el caso límite: fabrica.get() podría devolver null.
        // TODO 10: documenta mentalmente por qué prototype no cachea (estado independiente).
        return null;
    }

    public static void main(String[] args) {
        var s = new Ej033BeanScopes<>(Object::new, true);
        System.out.println(s.get() == s.get());
        var p = new Ej033BeanScopes<>(Object::new, false);
        System.out.println(p.get() == p.get());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Comprueba si dos solicitudes de obtención de un bean devuelven la misma instancia (Singleton) o distintas (Prototype).
     */
    public static boolean esMismaInstancia(org.springframework.context.ApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 1: Comprueba si dos solicitudes de obtención de un bean devuelven la misma instancia (Singleton) o distintas (Prototype).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMismaInstancia");
    }

    /**
     * Reto Extra 2: Un bean Prototype que mantiene un contador incremental interno.
     * Cada nueva instancia debe inicializar su contador en 0.
     */
    public static class BeanConContadorPrototype {
        // TODO extra: Reto Extra 2: Un bean Prototype que mantiene un contador incremental interno.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Soluciona el problema de inyectar un bean de alcance Prototype
     * en un Singleton utilizando ObjectFactory para obtener una nueva instancia del Prototype en cada invocación.
     */
    public static class SingletonConInyeccionPrototype {
        // TODO extra: Reto Extra 3: Soluciona el problema de inyectar un bean de alcance Prototype
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: Registra programáticamente un Scope personalizado en el contexto de Spring.
     */
    public static void registrarScopeCustom(org.springframework.context.support.GenericApplicationContext ctx, String scopeName, org.springframework.beans.factory.config.Scope scope) {
        // TODO extra: Reto Extra 4: Registra programáticamente un Scope personalizado en el contexto de Spring.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarScopeCustom");
    }

    /**
     * Reto Extra 5: Implementación sencilla de ThreadScope que aísla beans por hilo (ThreadLocal).
     */
    public static class ScopeThread implements org.springframework.beans.factory.config.Scope {
        // TODO extra: Reto Extra 5: Implementación sencilla de ThreadScope que aísla beans por hilo (ThreadLocal).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Comprueba en los metadatos si un bean está configurado explícitamente con ámbito prototype.
     */
    public static boolean esScopePrototypeDefinido(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 6: Comprueba en los metadatos si un bean está configurado explícitamente con ámbito prototype.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esScopePrototypeDefinido");
    }

    /**
     * Reto Extra 7: Crea un Scope personalizado que limite a un número máximo (ej. 3) las instancias concurrentes de un bean y reutilice la última en caso de superar el cupo.
     */
    public static org.springframework.beans.factory.config.Scope crearScopeLimitado(int maxInstancias) {
        // TODO extra: Reto Extra 7: Crea un Scope personalizado que limite a un número máximo (ej. 3) las instancias concurrentes de un bean y reutilice la última en caso de superar el cupo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearScopeLimitado");
    }

    /**
     * Reto Extra 8: Fuerza la destrucción de beans cacheados dentro de un determinado ámbito personalizado sin reiniciar el contexto de Spring.
     */
    public static void limpiarCachéScope(org.springframework.beans.factory.config.Scope scope) {
        // TODO extra: Reto Extra 8: Fuerza la destrucción de beans cacheados dentro de un determinado ámbito personalizado sin reiniciar el contexto de Spring.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarCachéScope");
    }

    /**
     * Reto Extra 9: DTO Singleton que inyecta dinámicamente un Proxy de un bean de ámbito Prototype utilizando ScopedProxyMode.
     */
    public static class SingletonConProxyPrototype {
        // TODO extra: Reto Extra 9: DTO Singleton que inyecta dinámicamente un Proxy de un bean de ámbito Prototype utilizando ScopedProxyMode.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Demuestra y verifica si Spring ejecuta el método anotado con @PreDestroy en beans de ámbito Prototype.
     */
    public static boolean evaluarCicloVidaPrototype(org.springframework.context.support.GenericApplicationContext ctx, String nombreBeanPrototype) {
        // TODO extra: Reto Extra 10: Demuestra y verifica si Spring ejecuta el método anotado con @PreDestroy en beans de ámbito Prototype.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarCicloVidaPrototype");
    }

}
