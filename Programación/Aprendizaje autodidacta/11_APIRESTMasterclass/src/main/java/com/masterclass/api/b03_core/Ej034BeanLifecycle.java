package com.masterclass.api.b03_core;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 034 · Ciclo de vida: init y destroy.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.3).
 *
 * <p>Simula @PostConstruct/@PreDestroy: registra el orden de las fases.
 */
public class Ej034BeanLifecycle {

    private final List<String> fases = new ArrayList<>();

    /** Llamado tras inyectar dependencias (equivale a @PostConstruct). */
    public void init() {
        // TODO 1: @PostConstruct se ejecuta UNA vez, tras construir e inyectar.
        // TODO 2: añade el literal "init" a la lista 'fases'.
        // TODO 3: no añadas "init" más de una vez por instancia (idealmente).
    }

    /** Lógica de negocio entre init y destroy. */
    public void usar() {
        // TODO 4: representa el uso normal del bean (entre init y destroy).
        // TODO 5: añade el literal "uso" a 'fases'.
        // TODO 6: 'usar' puede invocarse varias veces, pero aquí basta registrar el hito.
    }

    /** Llamado al cerrar el contenedor (equivale a @PreDestroy). */
    public void destroy() {
        // TODO 7: @PreDestroy se ejecuta al cerrar el contexto, antes de descartar el bean.
        // TODO 8: añade el literal "destroy" a 'fases'.
        // TODO 9: tras destroy no debería haber más uso (invariante del ciclo de vida).
    }

    /**
     * @return las fases registradas en orden de ejecución
     */
    public List<String> fases() {
        // TODO 10: devuelve la lista 'fases' (el orden esperado es init, uso, destroy).
        return fases;
    }

    public static void main(String[] args) {
        var b = new Ej034BeanLifecycle();
        b.init();
        b.usar();
        b.destroy();
        System.out.println(b.fases());
    }
}
