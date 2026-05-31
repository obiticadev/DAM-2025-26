package com.masterclass.api.b03_core;

import java.util.function.Supplier;

/**
 * Ejercicio 038 · AOP conceptual: lógica transversal alrededor de una llamada.
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 *
 * <p>Un "around advice" mínimo: cuenta invocaciones y mide sin tocar la lógica.
 */
public class Ej038AopCrossCutting {

    private int invocaciones = 0;

    /**
     * Ejecuta la acción contando la invocación (advice "before") y devolviendo
     * su resultado (la acción no se modifica).
     *
     * @param accion lógica de negocio a envolver
     * @param <T>    tipo de retorno
     * @return el valor que devuelve la acción
     */
    public <T> T alrededor(Supplier<T> accion) {
        // TODO 1: valida que 'accion' no sea null.
        // TODO 2: el "advice before" se ejecuta ANTES de la lógica de negocio.
        // TODO 3: incrementa el contador 'invocaciones' como parte del advice before.
        // TODO 4: ejecuta la lógica real con accion.get() y guarda el resultado.
        // TODO 5: NO modifiques el resultado (un around transparente lo devuelve igual).
        // TODO 6: (conceptual) aquí iría un "advice after" si lo hubiera.
        // TODO 7: devuelve el resultado obtenido.
        // TODO 8: el contador debe reflejar el nº de veces invocado, no de éxitos.
        // TODO 9: ten presente que si 'accion' lanza, el contador ya se incrementó (before).
        // TODO 10: mantén el método genérico (<T>) para envolver cualquier tipo de retorno.
        return null;
    }

    /**
     * @return número de veces que se ha invocado {@link #alrededor}
     */
    public int invocaciones() {
        return invocaciones;
    }

    public static void main(String[] args) {
        var aop = new Ej038AopCrossCutting();
        aop.alrededor(() -> "a");
        aop.alrededor(() -> "b");
        System.out.println(aop.invocaciones());
    }    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    @java.lang.annotation.Target(java.lang.annotation.ElementType.METHOD)
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    public @interface Auditable {
    }

    public static class ServicioNegocio {
        public String saludar(String nombre) {
            return "Hola, " + nombre;
        }

        public int calcular(int a, int b) {
            return a + b;
        }

        @Auditable
        public String metodoAuditado() {
            return "auditado";
        }

        public void metodoConError() {
            throw new RuntimeException("Error simulado");
        }
    }

    /**
     * Reto Extra 1: Aspecto Before que cuenta inicios de llamada.
     */
    public static class AspectoAntesDeLlamada {
        // TODO extra: Reto Extra 1: Aspecto Before que cuenta inicios de llamada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 2: Aspecto AfterReturning que captura y guarda el valor devuelto.
     */
    public static class AspectoDespuesDeRetorno {
        // TODO extra: Reto Extra 2: Aspecto AfterReturning que captura y guarda el valor devuelto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 3: Aspecto AfterThrowing que registra excepciones lanzadas.
     */
    public static class AspectoEnCasoDeError {
        // TODO extra: Reto Extra 3: Aspecto AfterThrowing que registra excepciones lanzadas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 4: Aspecto Around que envuelve toda la ejecución.
     */
    public static class AspectoAlrededorAround {
        // TODO extra: Reto Extra 4: Aspecto Around que envuelve toda la ejecución.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 5: Aspecto que intercepta métodos anotados con @Auditable.
     */
    public static class PointcutPorAnotacionCustom {
        // TODO extra: Reto Extra 5: Aspecto que intercepta métodos anotados con @Auditable.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 6: Aspecto que mide la duración del método.
     */
    public static class AspectoConMedicionDeTiempo {
        // TODO extra: Reto Extra 6: Aspecto que mide la duración del método.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 7: Aspectos combinando múltiples expresiones en el pointcut.
     */
    public static class PointcutCombinadoConOperadores {
        // TODO extra: Reto Extra 7: Aspectos combinando múltiples expresiones en el pointcut.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 8: Acceder a los argumentos del método interceptado.
     */
    public static class AccederAParametrosDeMetodo {
        // TODO extra: Reto Extra 8: Acceder a los argumentos del método interceptado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 9: Modificación de argumentos en caliente.
     */
    public static class ModificarArgumentosDinamicos {
        // TODO extra: Reto Extra 9: Modificación de argumentos en caliente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

    /**
     * Reto Extra 10: Prioridad y orden entre múltiples aspectos usando @Order.
     */
    public static class AspectoOrdenadoConOrder {
        // TODO extra: Reto Extra 10: Prioridad y orden entre múltiples aspectos usando @Order.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para retoExtra");
    }

}
