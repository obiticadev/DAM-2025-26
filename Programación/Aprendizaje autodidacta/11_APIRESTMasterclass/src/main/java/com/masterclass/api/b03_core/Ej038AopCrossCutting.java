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
        private static int contadorBefore = 0;

        public static int getContadorBefore() {
            return contadorBefore;
        }

        public static void limpiar() {
            contadorBefore = 0;
        }

        // TODO extra (Reto 1): Anota esta clase con @org.aspectj.lang.annotation.Aspect y crea un consejo @org.aspectj.lang.annotation.Before para interceptar ServicioNegocio.saludar.
    }

    /**
     * Reto Extra 2: Aspecto AfterReturning que captura y guarda el valor devuelto.
     */
    public static class AspectoDespuesDeRetorno {
        private static Object ultimoResultado;

        public static Object getUltimoResultado() {
            return ultimoResultado;
        }

        public static void limpiar() {
            ultimoResultado = null;
        }

        // TODO extra (Reto 2): Crea un consejo @org.aspectj.lang.annotation.AfterReturning que intercepte ServicioNegocio.calcular y asigne su resultado a 'ultimoResultado'.
    }

    /**
     * Reto Extra 3: Aspecto AfterThrowing que registra excepciones lanzadas.
     */
    public static class AspectoEnCasoDeError {
        private static String mensajeError;

        public static String getMensajeError() {
            return mensajeError;
        }

        public static void limpiar() {
            mensajeError = null;
        }

        // TODO extra (Reto 3): Crea un consejo @org.aspectj.lang.annotation.AfterThrowing que intercepte ServicioNegocio.metodoConError y guarde el mensaje de la excepción en 'mensajeError'.
    }

    /**
     * Reto Extra 4: Aspecto Around que envuelve toda la ejecución.
     */
    public static class AspectoAlrededorAround {
        private static boolean aroundEjecutado = false;

        public static boolean isAroundEjecutado() {
            return aroundEjecutado;
        }

        public static void limpiar() {
            aroundEjecutado = false;
        }

        // TODO extra (Reto 4): Crea un consejo @org.aspectj.lang.annotation.Around que intercepte ServicioNegocio.saludar, registre su ejecución en 'aroundEjecutado' y proceda con la llamada.
    }

    /**
     * Reto Extra 5: Aspecto que intercepta métodos anotados con @Auditable.
     */
    public static class PointcutPorAnotacionCustom {
        private static boolean auditadoEjecutado = false;

        public static boolean isAuditadoEjecutado() {
            return auditadoEjecutado;
        }

        public static void limpiar() {
            auditadoEjecutado = false;
        }

        // TODO extra (Reto 5): Crea un consejo @org.aspectj.lang.annotation.Before o @org.aspectj.lang.annotation.Around usando un pointcut que filtre por la presencia de @Auditable.
    }

    /**
     * Reto Extra 6: Aspecto que mide la duración del método.
     */
    public static class AspectoConMedicionDeTiempo {
        private static long duracionUltimoMetodoNs = -1;

        public static long getDuracionUltimoMetodoNs() {
            return duracionUltimoMetodoNs;
        }

        public static void limpiar() {
            duracionUltimoMetodoNs = -1;
        }

        // TODO extra (Reto 6): Usa un consejo @org.aspectj.lang.annotation.Around para medir el tiempo transcurrido en nanosegundos (System.nanoTime()) y guárdalo en 'duracionUltimoMetodoNs'.
    }

    /**
     * Reto Extra 7: Aspectos combinando múltiples expresiones en el pointcut.
     */
    public static class PointcutCombinadoConOperadores {
        private static boolean pointcutCombinadoEjecutado = false;

        public static boolean isPointcutCombinadoEjecutado() {
            return pointcutCombinadoEjecutado;
        }

        public static void limpiar() {
            pointcutCombinadoEjecutado = false;
        }

        // TODO extra (Reto 7): Crea un pointcut usando operadores lógicos (&&, ||, !) para interceptar sólo métodos de ServicioNegocio anotados con @Auditable.
    }

    /**
     * Reto Extra 8: Acceder a los argumentos del método interceptado.
     */
    public static class AccederAParametrosDeMetodo {
        private static Object[] ultimosArgumentos;

        public static Object[] getUltimosArgumentos() {
            return ultimosArgumentos;
        }

        public static void limpiar() {
            ultimosArgumentos = null;
        }

        // TODO extra (Reto 8): Usa JoinPoint en un consejo @org.aspectj.lang.annotation.Before para extraer los argumentos de la llamada y guardarlos en 'ultimosArgumentos'.
    }

    /**
     * Reto Extra 9: Modificación de argumentos en caliente.
     */
    public static class ModificarArgumentosDinamicos {
        // TODO extra (Reto 9): Usa ProceedingJoinPoint en un consejo @org.aspectj.lang.annotation.Around para alterar los argumentos originales antes de llamar a proceed().
    }

    /**
     * Reto Extra 10: Prioridad y orden entre múltiples aspectos usando @Order.
     */
    public static class AspectoOrdenadoConOrder {
        private static final java.util.List<String> ordenAspectos = new java.util.ArrayList<>();

        public static java.util.List<String> getOrdenAspectos() {
            return ordenAspectos;
        }

        public static void limpiar() {
            ordenAspectos.clear();
        }

        // TODO extra (Reto 10.1): Anota con @org.springframework.core.annotation.Order(1) y registra su ejecución.
        public static class AspectoPrimero {
        }

        // TODO extra (Reto 10.2): Anota con @org.springframework.core.annotation.Order(2) y registra su ejecución.
        public static class AspectoSegundo {
        }
    }

}
