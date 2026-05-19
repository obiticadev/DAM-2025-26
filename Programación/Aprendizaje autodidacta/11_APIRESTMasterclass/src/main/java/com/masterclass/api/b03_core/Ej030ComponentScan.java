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
}
