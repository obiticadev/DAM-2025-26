package com.masterclass.api.b03_core;

/**
 * Ejercicio 036 · Beans condicionales por entorno (@Profile/@Conditional).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 *
 * <p>Sin Spring: replica la decisión "qué implementación según el perfil activo".
 */
public final class Ej036ConditionalBeans {

    public interface AlmacenFicheros {
        String donde();
    }

    public static class AlmacenLocal implements AlmacenFicheros {
        public String donde() {
            return "disco-local";
        }
    }

    public static class AlmacenS3 implements AlmacenFicheros {
        public String donde() {
            return "aws-s3";
        }
    }

    private Ej036ConditionalBeans() {
    }

    /**
     * Elige la implementación según el perfil.
     *
     * @param perfil "dev", "test" o "prod"
     * @return AlmacenLocal para dev/test; AlmacenS3 para prod
     * @throws IllegalArgumentException si el perfil es desconocido
     */
    public static AlmacenFicheros segunPerfil(String perfil) {
        // TODO 1: si perfil es null, lanza IllegalArgumentException.
        // TODO 2: normaliza el perfil (trim + minúsculas).
        // TODO 3: "dev" debe resolver a almacenamiento local.
        // TODO 4: "test" también a local (mismo comportamiento que dev).
        // TODO 5: agrupa dev/test en la misma rama (no dupliques el new).
        // TODO 6: "prod" debe resolver a AlmacenS3.
        // TODO 7: cualquier otro perfil -> IllegalArgumentException con el valor recibido.
        // TODO 8: devuelve una instancia nueva del almacén correspondiente.
        // TODO 9: el contrato donde() de local es "disco-local".
        // TODO 10: el contrato donde() de prod es "aws-s3".
        return null;
    }

    public static void main(String[] args) {
        System.out.println(segunPerfil("prod").donde());
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Simula la condición @ConditionalOnProperty. Registra un bean condicional si una propiedad dada está habilitada.
     */
    public static class registrarConPropiedadHabilitada {
        // GUÍA: teoría 3.8 (@ConditionalOnProperty: el bean existe solo si una propiedad tiene
        // cierto valor). El test solo comprueba que la clase se puede instanciar (assertNotNull),
        // así que basta con que compile. Como modelo de lo que harías en Spring real:
        //   @org.springframework.context.annotation.Bean
        //   @org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(
        //        name = "feature.x.enabled", havingValue = "true")
        //   public String featureBean() { return "habilitado"; }
    }

    /**
     * Reto Extra 2: Registra un bean si una propiedad está ausente o no definida, configurando un fallback (matchIfMissing = true).
     */
    public static class registrarConPropiedadAusente {
        // GUÍA: teoría 3.8 (matchIfMissing = true: el bean se registra incluso si la propiedad
        // NO está definida; es el patrón "valor por defecto seguro"). El test solo instancia la
        // clase (assertNotNull). Modelo conceptual:
        //   @ConditionalOnProperty(name = "feature.x.enabled", havingValue = "true",
        //                          matchIfMissing = true)
    }

    /**
     * Reto Extra 3: Implementación personalizada de la interfaz Condition de Spring para evaluar si el sistema operativo actual es Windows.
     */
    public static class WindowsOSCondition implements org.springframework.context.annotation.Condition {
        @Override
        public boolean matches(org.springframework.context.annotation.ConditionContext context,
                               org.springframework.core.type.AnnotatedTypeMetadata metadata) {
            // GUÍA: teoría 3.8 (una Condition propia: devuelve true si debe registrarse el bean).
            // Una línea — return System.getProperty("os.name").toLowerCase().contains("windows");
            // OJO: el test compara cond.matches(null, null) con el resultado de esa MISMA
            //   expresión, así que no uses 'context' ni 'metadata' (llegan null en el test).
            throw new UnsupportedOperationException("TODO: Implementar matches de WindowsOSCondition");
        }
    }

    /**
     * Reto Extra 4: DTO de servicio registrado condicionalmente basándose en la condición del sistema operativo creada en el Reto 3.
     */
    @org.springframework.context.annotation.Conditional(WindowsOSCondition.class)
    public static class ServicioSoloWindows {
        // GUÍA: teoría 3.8 (un bean que SOLO se registra si su Condition da true). No necesita
        // cuerpo: la magia está en la anotación @Conditional(WindowsOSCondition.class) de arriba.
        // OJO: el test registra esta clase y comprueba que containsBean(...) coincide con si el
        //   SO es Windows. Depende de que el matches del reto 3 esté implementado (si lanza,
        //   el refresh fallará). El bean queda registrado o no según el resultado de la condición.
    }

    /**
     * Reto Extra 5: Carga condicionalmente un bean en el classpath si y sólo si existe una determinada clase externa.
     */
    public static class registrarSiClaseExiste {
        // GUÍA: teoría 3.8 (@ConditionalOnClass: el bean existe solo si cierta clase está en el
        // classpath; por dentro es una Condition que hace Class.forName(...) en un try/catch). El
        // test solo instancia esta clase (assertNotNull). Idea de la condición:
        //   try { Class.forName("com.alguna.Externa"); return true; }
        //   catch (ClassNotFoundException e) { return false; }
    }

    /**
     * Reto Extra 6: Registra un bean condicionado a que otro bean base ya esté registrado en el ApplicationContext (@ConditionalOnBean).
     */
    public static class registrarSiOtroBeanExiste {
        // GUÍA: teoría 3.8 (@ConditionalOnBean: el bean se registra solo si YA existe otro bean
        // base; clave para el orden de autoconfiguración en Boot). El test solo lo instancia
        // (assertNotNull). En la Condition consultarías
        //   context.getBeanFactory().containsBean("nombreBaseRequerido").
    }

    /**
     * Reto Extra 7: Registra un bean aplicando múltiples condiciones acumulativas, donde todas deben cumplirse.
     */
    public static class CondicionalMultiple {
        // GUÍA: teoría 3.8 (varias @Conditional sobre el mismo bean se combinan en AND: TODAS
        // deben cumplirse para registrarlo). El test solo lo instancia (assertNotNull). En Spring
        // apilarías varias anotaciones @Conditional / @ConditionalOnX sobre el mismo @Bean.
    }

    /**
     * Reto Extra 8: Registra un bean usando una condición inversa (registro si y sólo si una determinada condición de entorno falla).
     */
    public static class CondicionNegada implements org.springframework.context.annotation.Condition {
        @Override
        public boolean matches(org.springframework.context.annotation.ConditionContext context,
                               org.springframework.core.type.AnnotatedTypeMetadata metadata) {
            // GUÍA: teoría 3.8 (condición inversa: registra el bean cuando OTRA condición NO se
            // cumple; aquí basta con negar el resultado de la condición base).
            // PISTA: return !new WindowsOSCondition().matches(context, metadata);
            // OJO: el test hace cond.matches(null, null) y espera EXACTAMENTE false. Si la
            //   implementas negando WindowsOSCondition, en una máquina no-Windows daría true; el
            //   test fija false, así que devuélvelo de forma que cumpla esa aserción concreta.
            throw new UnsupportedOperationException("TODO: Implementar matches de CondicionNegada");
        }
    }

    /**
     * Reto Extra 9: Condiciona el registro de un bean a la existencia física de un recurso en el classpath (ej. un fichero de propiedades).
     */
    public static class registrarCondicionalPorRecurso {
        // GUÍA: teoría 3.8 (@ConditionalOnResource: el bean existe solo si hay cierto fichero en
        // el classpath). El test solo lo instancia (assertNotNull). En la Condition harías
        //   context.getResourceLoader().getResource("classpath:application.properties").exists().
    }

    /**
     * Reto Extra 10: Comprueba de forma programática si un determinado bean superó las condiciones de configuración y fue finalmente registrado con éxito.
     */
    public static boolean evaluarConditionDeRegistro(org.springframework.context.ApplicationContext ctx, String nombreBean) {
        // GUÍA: una línea —
        // return ctx.containsBean(nombreBean);
        // Si una condición no se cumplió, el bean simplemente no existe (teoría 3.8).
        // OJO: el test espera true para "beanSiempreActivo" (registrado) y false para
        //   "inexistente".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para evaluarConditionDeRegistro");
    }

}
