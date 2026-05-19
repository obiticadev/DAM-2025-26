package com.masterclass.api.b03_core;

import java.time.Clock;

/**
 * Ejercicio 035 · Configuración por Java: @Configuration / @Bean.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 */
public final class Ej035JavaConfigBeans {

    /**
     * Clase de configuración.
     */
    // TODO 1: anota esta clase con @org.springframework.context.annotation.Configuration.
    public static class AppConfig {
        // TODO 2: anota este método con @org.springframework.context.annotation.Bean.
        // TODO 3: el método debe devolver Clock.systemUTC() (no null).
        public Clock clock() {
            return null;
        }
    }

    private Ej035JavaConfigBeans() {
    }

    /**
     * Arranca un contexto con AppConfig y devuelve el bean Clock.
     *
     * @return el Clock gestionado por Spring (no null si la config es correcta)
     */
    public static Clock obtenerClock() {
        // TODO 4: crea un AnnotationConfigApplicationContext pasando AppConfig.class.
        // TODO 5: el contexto procesará @Configuration y registrará el @Bean.
        // TODO 6: recupera el bean con ctx.getBean(Clock.class).
        // TODO 7: cierra el contexto cuando termines (try-with-resources).
        // TODO 8: devuelve la instancia obtenida del contenedor.
        // TODO 9: si el @Bean devolviera null, getBean fallaría: por eso TODO 3 importa.
        // TODO 10: NO devuelvas Clock.systemUTC() directamente: debe venir del contexto.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(obtenerClock());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota esta clase con @org.springframework.context.annotation.Configuration.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota este método con @org.springframework.context.annotation.Bean.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: el método debe devolver Clock.systemUTC() (no null).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: crea un AnnotationConfigApplicationContext pasando AppConfig.class.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: el contexto procesará @Configuration y registrará el @Bean.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: recupera el bean con ctx.getBean(Clock.class).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: cierra el contexto cuando termines (try-with-resources).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve la instancia obtenida del contenedor.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si el @Bean devolviera null, getBean fallaría: por eso TODO 3 importa.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: NO devuelvas Clock.systemUTC() directamente: debe venir del contexto.
    }

}
