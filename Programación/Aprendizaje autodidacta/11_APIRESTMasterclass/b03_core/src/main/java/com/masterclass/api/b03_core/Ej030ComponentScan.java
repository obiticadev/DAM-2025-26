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
        // GUÍA: una línea —
        // return ctx.getBean(nombre);   (la sobrecarga por String de getBean, teoría 3.2).
        // OJO: el test registra Saludador (nombre por defecto "saludador") y espera que
        //   obtenerBeanPorNombre(ctx, "saludador") devuelva una instancia de Saludador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanPorNombre");
    }

    /**
     * Reto Extra 2: Comprueba programáticamente si un bean registrado en el contexto es de alcance Singleton.
     */
    public static boolean esSingleton(AnnotationConfigApplicationContext ctx, String nombreBean) {
        // GUÍA: teoría 3.2 (los metadatos viven en el BeanDefinition).
        // 1. return ctx.getBeanFactory().getBeanDefinition(nombreBean).isSingleton();
        //    (o, más corto, ctx.isSingleton(nombreBean)).
        // OJO: el test espera true para "saludador" (singleton por defecto) y false para
        //   "ej030ComponentScanTest.MiPrototypeBean" (lleva @Scope("prototype")). Fíjate en el
        //   nombre con prefijo de la clase externa para los beans anidados del test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSingleton");
    }

    /**
     * Reto Extra 3: Crea un AnnotationConfigApplicationContext que escanea el paquete pero excluye una clase concreta.
     */
    public static AnnotationConfigApplicationContext crearContextoFiltradoConExclusion(Class<?> claseAExcluir) {
        // GUÍA: teoría 3.2 (component scan con filtro de exclusión, como excludeFilters de
        // @ComponentScan). Lo haces a mano con un ClassPathBeanDefinitionScanner.
        // 1. var ctx = new AnnotationConfigApplicationContext();
        // 2. var scanner = new ClassPathBeanDefinitionScanner(ctx);
        // 3. scanner.addExcludeFilter(new org.springframework.core.type.filter.AssignableTypeFilter(claseAExcluir));
        // 4. scanner.scan("com.masterclass.api.b03_core");   // el paquete de este bloque
        // 5. ctx.refresh();  y  return ctx;   (NO lo cierres: el test lo usa y lo cierra él).
        // OJO: el test espera containsBeanDefinition("saludador") == true (Saludador debe estar
        //   anotado con @Component, TODO 1 del ejercicio base) y containsBean(nombre de
        //   MiPrototypeBean) == false (es la clase excluida). CULTURA: esto es lo que hace
        //   Spring Boot al excluir autoconfiguraciones que no quieres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearContextoFiltradoConExclusion");
    }

    /**
     * Reto Extra 4: Devuelve el recuento de definiciones de beans registradas en el contexto.
     */
    public static int contarBeansDefinidos(AnnotationConfigApplicationContext ctx) {
        // GUÍA: una línea —
        // return ctx.getBeanDefinitionCount();
        // OJO: el test registra 2 beans y espera count >= 2 (Spring añade definiciones internas
        //   de infraestructura, por eso es >= y no == 2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarBeansDefinidos");
    }

    /**
     * Reto Extra 5: Registra en caliente una instancia singleton pre-construida en un contexto de Spring ya refrescado.
     */
    public static void registrarBeanEnCaliente(AnnotationConfigApplicationContext ctx, String nombre, Object instancia) {
        // GUÍA: una línea —
        // ctx.getBeanFactory().registerSingleton(nombre, instancia);
        // (registra una instancia YA construida en un contexto ya refrescado).
        // OJO: el test registra "miManual" -> "Instancia manual" y luego espera
        //   ctx.getBean("miManual") == esa misma cadena. Es el equivalente Spring del reto 2
        //   de Ej029 (registerSingletonInstance).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarBeanEnCaliente");
    }

    /**
     * Reto Extra 6: Comprueba en los metadatos si la definición del bean está configurada con scope Prototype.
     */
    public static boolean esBeanPrototypeDefinido(AnnotationConfigApplicationContext ctx, String nombre) {
        // GUÍA: una línea —
        // return ctx.getBeanFactory().getBeanDefinition(nombre).isPrototype();
        // OJO: es el espejo del reto 2 (esSingleton). El test espera true para
        //   "ej030ComponentScanTest.MiPrototypeBean" y false para "saludador".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBeanPrototypeDefinido");
    }

    /**
     * Reto Extra 7: Encuentra y devuelve todos los beans que tengan presente una determinada anotación en su clase.
     */
    public static java.util.Map<String, Object> obtenerBeansPorAnotacion(AnnotationConfigApplicationContext ctx, Class<? extends java.lang.annotation.Annotation> anotacion) {
        // GUÍA: una línea —
        // return ctx.getBeansWithAnnotation(anotacion);
        // (devuelve Map<nombreBean, bean> de los beans cuya CLASE lleva esa anotación).
        // OJO: el test registra BeanConAnotacion (anotado con @CustomAnnotation, que es
        //   @Retention(RUNTIME)) y espera un mapa de tamaño 1 cuyo valor es un BeanConAnotacion.
        //   La anotación DEBE ser RUNTIME o no se ve por reflexión.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeansPorAnotacion");
    }

    /**
     * Reto Extra 8: Devuelve todos los nombres de beans del contexto ordenados alfabéticamente.
     */
    public static java.util.List<String> obtenerNombresDeBeans(AnnotationConfigApplicationContext ctx) {
        // GUÍA: teoría 1.3/1.4 (stream + sorted) sobre los nombres del contexto.
        // 1. return java.util.Arrays.stream(ctx.getBeanDefinitionNames()).sorted().toList();
        // OJO: el test recorre la lista y comprueba que cada nombre es <= al siguiente
        //   (orden ascendente). getBeanDefinitionNames() NO viene ordenado; tú lo ordenas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombresDeBeans");
    }

    /**
     * Reto Extra 9: Cierra de forma segura el contexto de Spring y comprueba si ha dejado de estar activo.
     */
    public static boolean cerrarYVerificarActivo(AnnotationConfigApplicationContext ctx) {
        // GUÍA: teoría 3.2 (ciclo del contexto: tras close() queda inactivo).
        // 1. ctx.close();
        // 2. return !ctx.isActive();   // true = se cerró correctamente.
        // OJO: el test verifica isActive()==true ANTES y, tras llamar a tu método, espera que
        //   devuelvas true y que ctx.isActive() sea false. No uses try-with-resources aquí: el
        //   cierre es justo lo que estás comprobando.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cerrarYVerificarActivo");
    }

    /**
     * Reto Extra 10: Comprueba si la definición del bean está configurada con inicialización perezosa (@Lazy).
     */
    public static boolean esDefinicionLazy(AnnotationConfigApplicationContext ctx, String nombre) {
        // GUÍA: una línea —
        // return ctx.getBeanFactory().getBeanDefinition(nombre).isLazyInit();
        // OJO: el test espera true para "ej030ComponentScanTest.MiLazyBean" (lleva @Lazy) y
        //   false para "saludador". Mismo patrón que retos 2 y 6: todo está en el BeanDefinition.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDefinicionLazy");
    }

}
