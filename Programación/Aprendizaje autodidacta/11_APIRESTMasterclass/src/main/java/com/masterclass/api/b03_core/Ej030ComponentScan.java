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
        // TODO extra (Reto 1): Devuelve el bean asociado al nombre indicado.
        return null;
    }

    /**
     * Reto Extra 2: Comprueba programáticamente si un bean registrado en el contexto es de alcance Singleton.
     */
    public static boolean esSingleton(AnnotationConfigApplicationContext ctx, String nombreBean) {
        // TODO extra (Reto 2): Retorna true si el bean tiene alcance singleton en el contexto.
        return false;
    }

    /**
     * Reto Extra 3: Crea un AnnotationConfigApplicationContext que escanea el paquete pero excluye una clase concreta.
     */
    public static AnnotationConfigApplicationContext crearContextoFiltradoConExclusion(Class<?> claseAExcluir) {
        // TODO extra (Reto 3): Inicializa un contexto configurando un TypeFilter para excluir la clase indicada durante el escaneo.
        return null;
    }

    /**
     * Reto Extra 4: Devuelve el recuento de definiciones de beans registradas en el contexto.
     */
    public static int contarBeansDefinidos(AnnotationConfigApplicationContext ctx) {
        // TODO extra (Reto 4): Retorna la cantidad de definiciones de beans en el contexto.
        return 0;
    }

    /**
     * Reto Extra 5: Registra en caliente una instancia singleton pre-construida en un contexto de Spring ya refrescado.
     */
    public static void registrarBeanEnCaliente(AnnotationConfigApplicationContext ctx, String nombre, Object instancia) {
        // TODO extra (Reto 5): Registra la instancia manual en el BeanFactory del contexto.
    }

    /**
     * Reto Extra 6: Comprueba en los metadatos si la definición del bean está configurada con scope Prototype.
     */
    public static boolean esBeanPrototypeDefinido(AnnotationConfigApplicationContext ctx, String nombre) {
        // TODO extra (Reto 6): Consulta los BeanDefinition del BeanFactory para saber si el scope es prototype.
        return false;
    }

    /**
     * Reto Extra 7: Encuentra y devuelve todos los beans que tengan presente una determinada anotación en su clase.
     */
    public static java.util.Map<String, Object> obtenerBeansPorAnotacion(AnnotationConfigApplicationContext ctx, Class<? extends java.lang.annotation.Annotation> anotacion) {
        // TODO extra (Reto 7): Busca beans decorados con la anotación indicada.
        return java.util.Map.of();
    }

    /**
     * Reto Extra 8: Devuelve todos los nombres de beans del contexto ordenados alfabéticamente.
     */
    public static java.util.List<String> obtenerNombresDeBeans(AnnotationConfigApplicationContext ctx) {
        // TODO extra (Reto 8): Retorna la lista ordenada de identificadores de beans registrados.
        return java.util.List.of();
    }

    /**
     * Reto Extra 9: Cierra de forma segura el contexto de Spring y comprueba si ha dejado de estar activo.
     */
    public static boolean cerrarYVerificarActivo(AnnotationConfigApplicationContext ctx) {
        // TODO extra (Reto 9): Cierra el contexto y valida si isActive() retorna false.
        return false;
    }

    /**
     * Reto Extra 10: Comprueba si la definición del bean está configurada con inicialización perezosa (@Lazy).
     */
    public static boolean esDefinicionLazy(AnnotationConfigApplicationContext ctx, String nombre) {
        // TODO extra (Reto 10): Consulta los metadatos del BeanDefinition para comprobar si es lazy.
        return false;
    }

}
