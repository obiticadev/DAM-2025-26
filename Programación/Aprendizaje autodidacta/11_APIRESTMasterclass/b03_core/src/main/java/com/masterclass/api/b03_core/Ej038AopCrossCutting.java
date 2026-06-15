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

    // NOTA: estos aspectos se registran como @Bean en el test (ConfigAop) con
    // @EnableAspectJAutoProxy. Para que el TEJIDO funcione tendrás que añadir a cada clase la
    // anotación @org.aspectj.lang.annotation.Aspect y el advice indicado en su GUÍA. Tal cual
    // están (sin @Aspect ni advice) compilan y el contexto arranca, pero los aspectos aún no
    // actúan: ese es justo tu trabajo. El estado estático y los getters ya están listos para que
    // el advice escriba en ellos.

    /**
     * Reto Extra 1: Aspecto Before que cuenta inicios de llamada.
     */
    public static class AspectoAntesDeLlamada {
        private static int contadorBefore = 0;

        // GUÍA: teoría 3.10 (advice @Before: corre ANTES del método interceptado).
        // 1. Anota la clase con @org.aspectj.lang.annotation.Aspect.
        // 2. Añade el advice:
        //    @org.aspectj.lang.annotation.Before(
        //        "execution(* com.masterclass.api.b03_core.Ej038AopCrossCutting.ServicioNegocio.*(..))")
        //    public void antes() { contadorBefore++; }
        // OJO: el test llama servicio.saludar("Juan") una vez y espera getContadorBefore()==1.
        //   La cuenta es de INICIOS de llamada, no de éxitos.
        public static int getContadorBefore() { return contadorBefore; }
        public static void limpiar() { contadorBefore = 0; }
    }

    /**
     * Reto Extra 2: Aspecto AfterReturning que captura y guarda el valor devuelto.
     */
    public static class AspectoDespuesDeRetorno {
        private static int ultimoResultado = 0;

        // GUÍA: teoría 3.10 (advice @AfterReturning: corre tras volver con éxito y recibe el
        // valor devuelto vía el atributo 'returning').
        //    @org.aspectj.lang.annotation.AfterReturning(
        //        pointcut = "execution(* ...ServicioNegocio.calcular(..))", returning = "resultado")
        //    public void capturar(Object resultado) { ultimoResultado = (int) resultado; }
        // OJO: el test hace calcular(10,20) (devuelve 30) y espera getUltimoResultado()==30.
        public static int getUltimoResultado() { return ultimoResultado; }
        public static void limpiar() { ultimoResultado = 0; }
    }

    /**
     * Reto Extra 3: Aspecto AfterThrowing que registra excepciones lanzadas.
     */
    public static class AspectoEnCasoDeError {
        private static String mensajeError = null;

        // GUÍA: teoría 3.10 (advice @AfterThrowing: corre si el método LANZA; recibe la excepción
        // vía 'throwing'). El advice NO traga la excepción: se relanza igual al llamador.
        //    @org.aspectj.lang.annotation.AfterThrowing(
        //        pointcut = "execution(* ...ServicioNegocio.*(..))", throwing = "ex")
        //    public void enError(Throwable ex) { mensajeError = ex.getMessage(); }
        // OJO: el test invoca metodoConError() (lanza RuntimeException("Error simulado")),
        //   comprueba que la excepción SIGUE propagándose (assertThrows) y que
        //   getMensajeError()=="Error simulado".
        public static String getMensajeError() { return mensajeError; }
        public static void limpiar() { mensajeError = null; }
    }

    /**
     * Reto Extra 4: Aspecto Around que envuelve toda la ejecución.
     */
    public static class AspectoAlrededorAround {
        private static boolean aroundEjecutado = false;

        // GUÍA: teoría 3.10 (advice @Around: el más potente; envuelve la llamada y DEBE invocar
        // proceed() y DEVOLVER su resultado, o "te comes" el valor del método).
        //    @org.aspectj.lang.annotation.Around("execution(* ...ServicioNegocio.saludar(..))")
        //    public Object alrededor(org.aspectj.lang.ProceedingJoinPoint jp) throws Throwable {
        //        aroundEjecutado = true;
        //        return jp.proceed();          // ← ejecuta el método real y devuelve su resultado
        //    }
        // OJO: el test espera saludar("Pepe")=="Hola, Pepe" (intacto) Y isAroundEjecutado()==true.
        //   Si olvidas el return jp.proceed(), saludar devolvería null y romperías otros tests.
        public static boolean isAroundEjecutado() { return aroundEjecutado; }
        public static void limpiar() { aroundEjecutado = false; }
    }

    /**
     * Reto Extra 5: Aspecto que intercepta métodos anotados con @Auditable.
     */
    public static class PointcutPorAnotacionCustom {
        private static boolean auditadoEjecutado = false;

        // GUÍA: teoría 3.10 (pointcut por ANOTACIÓN: intercepta los métodos marcados con
        // @Auditable, sin importar su nombre).
        //    @org.aspectj.lang.annotation.Before(
        //        "@annotation(com.masterclass.api.b03_core.Ej038AopCrossCutting.Auditable)")
        //    public void alAuditar() { auditadoEjecutado = true; }
        // OJO: el test llama metodoAuditado() (lleva @Auditable) y espera
        //   isAuditadoEjecutado()==true. saludar/calcular NO deben dispararlo (no están anotados).
        public static boolean isAuditadoEjecutado() { return auditadoEjecutado; }
        public static void limpiar() { auditadoEjecutado = false; }
    }

    /**
     * Reto Extra 6: Aspecto que mide la duración del método.
     */
    public static class AspectoConMedicionDeTiempo {
        private static long duracionUltimoMetodoNs = 0;

        // GUÍA: teoría 3.10 (medir tiempo con @Around: t0 antes, proceed(), t1 después).
        //    @org.aspectj.lang.annotation.Around("execution(* ...ServicioNegocio.*(..))")
        //    public Object medir(org.aspectj.lang.ProceedingJoinPoint jp) throws Throwable {
        //        long t0 = System.nanoTime();
        //        try { return jp.proceed(); }
        //        finally { duracionUltimoMetodoNs = System.nanoTime() - t0; }
        //    }
        // OJO: el test solo exige getDuracionUltimoMetodoNs() >= 0 tras saludar("Carlos").
        //   Devuelve la diferencia de nanos (siempre >= 0); usa finally para registrarla aunque lance.
        public static long getDuracionUltimoMetodoNs() { return duracionUltimoMetodoNs; }
        public static void limpiar() { duracionUltimoMetodoNs = 0; }
    }

    /**
     * Reto Extra 7: Aspectos combinando múltiples expresiones en el pointcut.
     */
    public static class PointcutCombinadoConOperadores {
        private static boolean pointcutCombinadoEjecutado = false;

        // GUÍA: teoría 3.10 (combinar expresiones de pointcut con && / || / !).
        //    @org.aspectj.lang.annotation.Before(
        //        "execution(* ...ServicioNegocio.*(..)) && " +
        //        "@annotation(com.masterclass.api.b03_core.Ej038AopCrossCutting.Auditable)")
        //    public void combinado() { pointcutCombinadoEjecutado = true; }
        // OJO: el test llama metodoAuditado() (de ServicioNegocio Y con @Auditable: cumple AMBAS
        //   condiciones) y espera isPointcutCombinadoEjecutado()==true.
        public static boolean isPointcutCombinadoEjecutado() { return pointcutCombinadoEjecutado; }
        public static void limpiar() { pointcutCombinadoEjecutado = false; }
    }

    /**
     * Reto Extra 8: Acceder a los argumentos del método interceptado.
     */
    public static class AccederAParametrosDeMetodo {
        private static Object[] ultimosArgumentos = new Object[0];

        // GUÍA: teoría 3.10 (un JoinPoint da acceso a los argumentos con jp.getArgs()).
        //    @org.aspectj.lang.annotation.Before("execution(* ...ServicioNegocio.saludar(..))")
        //    public void capturarArgs(org.aspectj.lang.JoinPoint jp) { ultimosArgumentos = jp.getArgs(); }
        // OJO: el test llama saludar("Ana") y espera
        //   assertArrayEquals(new Object[]{"Ana"}, getUltimosArgumentos()).
        public static Object[] getUltimosArgumentos() { return ultimosArgumentos; }
        public static void limpiar() { ultimosArgumentos = new Object[0]; }
    }

    /**
     * Reto Extra 9: Modificación de argumentos en caliente.
     */
    public static class ModificarArgumentosDinamicos {
        // GUÍA: teoría 3.10 (con @Around puedes ALTERAR los argumentos antes de proceed():
        // jp.proceed(new Object[]{ nuevoArg })).
        //    @org.aspectj.lang.annotation.Around("execution(* ...ServicioNegocio.saludar(..))")
        //    public Object modificar(org.aspectj.lang.ProceedingJoinPoint jp) throws Throwable {
        //        return jp.proceed(jp.getArgs());   // aquí podrías sustituir args
        //    }
        // ⚠ CUIDADO: el test reto9 espera saludar("Ana")=="Hola, Ana" SIN cambios. Si de verdad
        //   mutas el argumento, ese test fallaría; mantén el paso transparente (proceed con los
        //   mismos args) salvo que quieras demostrar el cambio en otra prueba propia.
    }

    /**
     * Reto Extra 10: Prioridad y orden entre múltiples aspectos usando @Order.
     */
    public static class AspectoOrdenadoConOrder {
        private static final java.util.List<String> ordenAspectos = new java.util.ArrayList<>();

        public static java.util.List<String> getOrdenAspectos() { return ordenAspectos; }
        public static void limpiar() { ordenAspectos.clear(); }

        // GUÍA: teoría 3.10 (cuando varios aspectos afectan al mismo método, @Order fija su
        // precedencia: menor valor = se ejecuta ANTES). Cada sub-aspecto, al entrar, añade su
        // nombre a 'ordenAspectos'; así verificas el orden.
        @org.springframework.core.annotation.Order(1)
        public static class AspectoPrimero {
            // GUÍA: anota con @Aspect y añade:
            //    @org.aspectj.lang.annotation.Before("execution(* ...ServicioNegocio.*(..))")
            //    public void marcar() { ordenAspectos.add("AspectoPrimero"); }
        }

        @org.springframework.core.annotation.Order(2)
        public static class AspectoSegundo {
            // GUÍA: anota con @Aspect y añade:
            //    @org.aspectj.lang.annotation.Before("execution(* ...ServicioNegocio.*(..))")
            //    public void marcar() { ordenAspectos.add("AspectoSegundo"); }
        }
        // OJO: el test acepta lista vacía O que el primer elemento sea "AspectoPrimero" (el de
        //   @Order(1)). Con @Order(1) antes que @Order(2), "AspectoPrimero" entra primero.
    }

}
