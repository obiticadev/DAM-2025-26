package com.masterclass.api.b03_core;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Ejercicio 030 · @Component y resolución por el contenedor.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.1).
 */
public final class Ej030ComponentScan {

    /**
     * Componente gestionado por Spring.
     */
    // TODO 1: anota esta clase con @org.springframework.stereotype.Component
    //         para que el contenedor la reconozca como bean candidato.
    public static class Saludador {
        public String saludar() {
            // TODO 2: devuelve exactamente "hola" (contrato esperado por el test).
            return "";
        }
    }

    private Ej030ComponentScan() {
    }

    /**
     * Construye un contexto que registra {@link Saludador} y lo recupera.
     *
     * @return el bean Saludador gestionado por Spring
     */
    public static Saludador obtenerBean() {
        // TODO 3: crea un AnnotationConfigApplicationContext vacío (constructor sin args).
        // TODO 4: regístralo con ctx.register(Saludador.class).
        // TODO 5: invoca ctx.refresh() para que el contenedor procese el bean.
        // TODO 6: recupera el bean con ctx.getBean(Saludador.class).
        // TODO 7: usa try-with-resources o cierra el contexto tras obtener el bean.
        // TODO 8: devuelve la instancia gestionada (no un new manual).
        // TODO 9: el bean debe ser funcional (saludar() devuelve "hola").
        // TODO 10: NO instancies Saludador con 'new': el objetivo es que lo dé el contenedor.
        return null;
    }

    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext()) {
            System.out.println("Contexto listo: " + ctx);
        }
        System.out.println(obtenerBean());
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Recupera un bean a partir de su nombre identificador de cadena del contexto.
     */
    public static Object obtenerBeanPorNombre(AnnotationConfigApplicationContext ctx, String nombre) {
        // TODO extra: Reto Extra 1: Recupera un bean a partir de su nombre identificador de cadena del contexto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanPorNombre");
    }

    /**
     * Reto Extra 2: Comprueba programáticamente si un bean registrado en el contexto es de alcance Singleton.
     */
    public static boolean esSingleton(AnnotationConfigApplicationContext ctx, String nombreBean) {
        // TODO extra: Reto Extra 2: Comprueba programáticamente si un bean registrado en el contexto es de alcance Singleton.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSingleton");
    }

    /**
     * Reto Extra 3: Crea un AnnotationConfigApplicationContext que escanea el paquete pero excluye una clase concreta.
     */
    public static AnnotationConfigApplicationContext crearContextoFiltradoConExclusion(Class<?> claseAExcluir) {
        // TODO extra: Reto Extra 3: Crea un AnnotationConfigApplicationContext que escanea el paquete pero excluye una clase concreta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearContextoFiltradoConExclusion");
    }

    /**
     * Reto Extra 4: Devuelve el recuento de definiciones de beans registradas en el contexto.
     */
    public static int contarBeansDefinidos(AnnotationConfigApplicationContext ctx) {
        // TODO extra: Reto Extra 4: Devuelve el recuento de definiciones de beans registradas en el contexto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarBeansDefinidos");
    }

    /**
     * Reto Extra 5: Registra en caliente una instancia singleton pre-construida en un contexto de Spring ya refrescado.
     */
    public static void registrarBeanEnCaliente(AnnotationConfigApplicationContext ctx, String nombre, Object instancia) {
        // TODO extra: Reto Extra 5: Registra en caliente una instancia singleton pre-construida en un contexto de Spring ya refrescado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarBeanEnCaliente");
    }

    /**
     * Reto Extra 6: Comprueba en los metadatos si la definición del bean está configurada con scope Prototype.
     */
    public static boolean esBeanPrototypeDefinido(AnnotationConfigApplicationContext ctx, String nombre) {
        // TODO extra: Reto Extra 6: Comprueba en los metadatos si la definición del bean está configurada con scope Prototype.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBeanPrototypeDefinido");
    }

    /**
     * Reto Extra 7: Encuentra y devuelve todos los beans que tengan presente una determinada anotación en su clase.
     */
    public static java.util.Map<String, Object> obtenerBeansPorAnotacion(AnnotationConfigApplicationContext ctx, Class<? extends java.lang.annotation.Annotation> anotacion) {
        // TODO extra: Reto Extra 7: Encuentra y devuelve todos los beans que tengan presente una determinada anotación en su clase.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeansPorAnotacion");
    }

    /**
     * Reto Extra 8: Devuelve todos los nombres de beans del contexto ordenados alfabéticamente.
     */
    public static java.util.List<String> obtenerNombresDeBeans(AnnotationConfigApplicationContext ctx) {
        // TODO extra: Reto Extra 8: Devuelve todos los nombres de beans del contexto ordenados alfabéticamente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombresDeBeans");
    }

    /**
     * Reto Extra 9: Cierra de forma segura el contexto de Spring y comprueba si ha dejado de estar activo.
     */
    public static boolean cerrarYVerificarActivo(AnnotationConfigApplicationContext ctx) {
        // TODO extra: Reto Extra 9: Cierra de forma segura el contexto de Spring y comprueba si ha dejado de estar activo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cerrarYVerificarActivo");
    }

    /**
     * Reto Extra 10: Comprueba si la definición del bean está configurada con inicialización perezosa (@Lazy).
     */
    public static boolean esDefinicionLazy(AnnotationConfigApplicationContext ctx, String nombre) {
        // TODO extra: Reto Extra 10: Comprueba si la definición del bean está configurada con inicialización perezosa (@Lazy).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDefinicionLazy");
    }

}
