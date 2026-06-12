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

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Recupera del contexto de Spring el bean que tiene prioridad @Primary.
     */
    public static <T> T obtenerBeanPrimary(org.springframework.context.ApplicationContext ctx, Class<T> tipo) {
        // GUÍA: una línea —
        // return ctx.getBean(tipo);
        // Cuando hay varios candidatos, getBean(tipo) devuelve el marcado con @Primary (teoría 3.4).
        // OJO: el test registra MiNotificadorPrimary (@Primary) y MiNotificadorSms; espera que
        //   obtenerBeanPrimary(ctx, Notificador.class).enviar("test") == "primary:test".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanPrimary");
    }

    /**
     * Reto Extra 2: Recupera un bean específico cualificado con @Qualifier desde el ApplicationContext.
     */
    public static <T> T obtenerBeanQualifier(org.springframework.context.ApplicationContext ctx, Class<T> tipo, String calificador) {
        // GUÍA: teoría 3.4 (resolver por el VALOR de @Qualifier, no por el nombre del bean).
        // 1. Necesitas el BeanFactory: var bf = ((org.springframework.context.ConfigurableApplicationContext) ctx).getBeanFactory();
        // 2. Resuelve por qualifier con la utilidad de Spring:
        //    return org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils
        //               .qualifiedBeanOfType(bf, tipo, calificador);
        // OJO: el bean MiNotificadorSms lleva @Qualifier("smsSpecial") pero su NOMBRE es
        //   "ej032...MiNotificadorSms"; por eso ctx.getBean("smsSpecial") fallaría. El test
        //   espera que obtenerBeanQualifier(ctx, Notificador.class, "smsSpecial").enviar("test")
        //   == "smsSpecial:test".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBeanQualifier");
    }

    /**
     * Reto Extra 3: Recupera todos los beans de un determinado tipo como un mapa indexado por nombre del bean.
     */
    public static <T> java.util.Map<String, T> obtenerTodosLosBeansComoMapa(org.springframework.context.ApplicationContext ctx, Class<T> tipo) {
        // GUÍA: una línea —
        // return ctx.getBeansOfType(tipo);   // Map<nombreBean, instancia> de TODOS los del tipo.
        // OJO: el test espera size 2 y que las CLAVES sean los nombres con prefijo de clase
        //   externa: "ej032QualifierAndPrimaryTest.MiNotificadorPrimary" y "...MiNotificadorSms".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTodosLosBeansComoMapa");
    }

    /**
     * Reto Extra 4: Servicio desambiguador que inyecta una colección de notificadores,
     * pero prioriza el que tenga una anotación @Primary si está disponible, o usa uno por defecto.
     */
    public static class ServicioDesambiguador {
        private final java.util.List<Notificador> notificadores;

        public ServicioDesambiguador(java.util.List<Notificador> notificadores) {
            this.notificadores = notificadores;
        }

        public Notificador resolverNotificador() {
            // GUÍA: teoría 3.4 (replicar la regla de @Primary sobre una lista inyectada).
            // 1. Busca en 'notificadores' el primero cuya CLASE tenga la anotación @Primary:
            //    .filter(n -> n.getClass().isAnnotationPresent(org.springframework.context.annotation.Primary.class))
            //    .findFirst()
            // 2. Si lo hay, devuélvelo; si no, devuelve el primero como fallback
            //    (.orElse(notificadores.get(0))).
            // OJO: el test inyecta List.of(s, p) donde p = MiNotificadorPrimary (@Primary) y
            //   espera que resolverNotificador() == p, AUNQUE p vaya en segunda posición. No
            //   devuelvas el primero sin más: prioriza el @Primary.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverNotificador");
        }
    }

    /**
     * Reto Extra 5: Servicio que inyecta dos notificadores homónimos usando calificadores explícitos.
     */
    public static class ServicioDualQualifier {
        private final Notificador email;
        private final Notificador sms;

        public ServicioDualQualifier(Notificador email, Notificador sms) {
            // En Spring real cada parámetro llevaría su @Qualifier("email") / @Qualifier("sms");
            // aquí los recibes ya resueltos por orden.
            this.email = email;
            this.sms = sms;
        }

        public String notificarAmbos(String msg) {
            // GUÍA: teoría 3.4 (inyectar DOS implementaciones distintas y usar ambas).
            // 1. Llama a email.enviar(msg) y a sms.enviar(msg).
            // 2. Únelos con " & " (espacio-ampersand-espacio).
            // PISTA: return email.enviar(msg) + " & " + sms.enviar(msg);
            // OJO: con EmailNotificador + SmsNotificador y msg="alerta" el test espera
            //   "email:alerta & sms:alerta" (en ese orden).
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notificarAmbos");
        }
    }

    /**
     * Reto Extra 6: Simula la resolución de beans en un contexto evaluando una anotación cualificadora personalizada.
     */
    public static Object resolucionConAnotacionCustom(org.springframework.context.ApplicationContext ctx, Class<?> tipo, Class<? extends java.lang.annotation.Annotation> customQualifier) {
        // GUÍA: teoría 3.4 (un @Qualifier puede ser una anotación propia; aquí la buscas por
        // reflexión sobre las clases de los beans).
        // 1. Recupera todos los del tipo: ctx.getBeansOfType(tipo).values().
        // 2. Quédate con el que su CLASE tenga la anotación:
        //    .stream().filter(b -> b.getClass().isAnnotationPresent(customQualifier)).findFirst()
        // 3. Devuelve ese bean (.orElse(null) o .orElseThrow()).
        // OJO: el test registra MiNotificadorPrimary y MiNotificadorConAnotacionCustom (lleva
        //   @MiCalificadorCustom, que es RUNTIME); espera que devuelvas el segundo
        //   (instanceof MiNotificadorConAnotacionCustom).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolucionConAnotacionCustom");
    }

    /**
     * Reto Extra 7: Consulta las definiciones del BeanFactory para saber si un determinado bean tiene activada la propiedad Primary.
     */
    public static boolean esBeanPrimaryProgramatico(org.springframework.context.support.GenericApplicationContext ctx, String nombreBean) {
        // GUÍA: una línea —
        // return ctx.getBeanDefinition(nombreBean).isPrimary();
        // (el flag @Primary vive en el BeanDefinition, teoría 3.4).
        // OJO: el test espera true para "ej032...MiNotificadorPrimary" y false para
        //   "ej032...MiNotificadorSms".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBeanPrimaryProgramatico");
    }

    /**
     * Reto Extra 8: Cuenta y lista cuántos beans en el ApplicationContext poseen una cualificación explícita de un tipo.
     */
    public static int contarBeansPorCalificador(org.springframework.context.support.GenericApplicationContext ctx, Class<?> tipo, String qualifierValue) {
        // GUÍA: teoría 3.4 (contar los beans cuyo @Qualifier vale lo pedido).
        // 1. Recorre ctx.getBeansOfType(tipo).values().
        // 2. Para cada bean lee la anotación de su clase:
        //    var q = b.getClass().getAnnotation(org.springframework.beans.factory.annotation.Qualifier.class);
        // 3. Cuenta los que q != null && q.value().equals(qualifierValue).
        // PISTA: filtra con ese predicado y usa .count() (cast a int).
        // OJO: el test registra Primary + Sms(@Qualifier("smsSpecial")) y espera count == 1
        //   para "smsSpecial".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarBeansPorCalificador");
    }

    /**
     * Reto Extra 9: Servicio resiliente de resolución. Intenta resolver un notificador cualificado;
     * si no existe en el ApplicationContext, captura la excepción y retorna un SmsNotificador por defecto.
     */
    public static class ServicioFallbackQualifier {
        private final org.springframework.context.ApplicationContext ctx;

        public ServicioFallbackQualifier(org.springframework.context.ApplicationContext ctx) {
            this.ctx = ctx;
        }

        public Notificador resolverConFallback(String calificador) {
            // GUÍA: teoría 1.9 + 3.4 (intentar resolver del contexto y, si no existe, fallback).
            // 1. try { return obtenerBeanQualifier(ctx, Notificador.class, calificador); }
            //    (reutiliza el método del reto 2).
            // 2. catch (org.springframework.beans.BeansException e) { return new SmsNotificador(); }
            // OJO: el test usa un contexto VACÍO y pide "smsSpecial" (que no existe) -> debe
            //   capturar la excepción y devolver un SmsNotificador (instanceof SmsNotificador).
            //   No dejes que la excepción se propague.
            throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverConFallback");
        }
    }

    /**
     * Reto Extra 10: Mutación programática en caliente de las definiciones de beans para marcar un bean como Primary en tiempo de ejecución.
     */
    public static void reemplazarPrimaryEnCaliente(org.springframework.context.support.GenericApplicationContext ctx, String nuevoPrimaryBean) {
        // GUÍA: teoría 3.4 (mutar el flag @Primary del BeanDefinition en caliente).
        // 1. Quita el primary a TODOS los demás del mismo tipo: recorre los nombres y pon
        //    bd.setPrimary(false) salvo al nuevo (si dejas dos primary, getBean vuelve a fallar).
        // 2. Marca el nuevo: ctx.getBeanDefinition(nuevoPrimaryBean).setPrimary(true).
        // OJO: el test, tras llamar con "ej032...MiNotificadorSms", espera que
        //   ctx.getBean(Notificador.class).enviar("test") == "smsSpecial:test" (antes daba
        //   "primary:test"). Para que getBean cambie de elegido, el viejo primary
        //   (MiNotificadorPrimary) debe dejar de serlo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reemplazarPrimaryEnCaliente");
    }

}
