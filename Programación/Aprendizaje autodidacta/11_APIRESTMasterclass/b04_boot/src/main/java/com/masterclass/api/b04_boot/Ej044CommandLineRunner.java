package com.masterclass.api.b04_boot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ejercicio 044 · Bootstrap de arranque (CommandLineRunner).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.6).
 *
 * <p>Un runner ejecuta tareas justo tras arrancar el contexto (seed de datos, etc.).
 */
public class Ej044CommandLineRunner {

    private final List<String> log = new ArrayList<>();
    private boolean ejecutado = false;

    /**
     * Ejecuta el bootstrap procesando los argumentos de arranque.
     *
     * @param args argumentos de línea de comandos (formato "--clave=valor")
     */
    public void run(String... args) {
        // TODO 1: el runner debe ejecutarse UNA sola vez (idempotencia de arranque).
        // TODO 2: si 'ejecutado' ya es true, no repitas el trabajo (return temprano).
        // TODO 3: si args es null, trátalo como array vacío.
        // TODO 4: recorre cada argumento.
        // TODO 5: procesa solo los que empiecen por "--" (ignora el resto).
        // TODO 6: quita el prefijo "--" y divide por el primer '=' en clave/valor.
        // TODO 7: si no hay '=', registra la clave con valor "".
        // TODO 8: añade al 'log' una entrada "clave=valor" por argumento válido.
        // TODO 9: marca 'ejecutado = true' al terminar.
        // TODO 10: el orden del log debe respetar el orden de los argumentos.
    }

    /**
     * @return registro de lo procesado en el arranque
     */
    public List<String> log() {
        return log;
    }

    /**
     * @return true si el runner ya se ejecutó
     */
    public boolean ejecutado() {
        return ejecutado;
    }

    public static void main(String[] args) {
        var r = new Ej044CommandLineRunner();
        r.run("--seed=true", "--region=eu");
        System.out.println(r.log());
    }

    // === Runners y generadores (LOS RETOS) ==================================

    /**
     * RETO EXTRA 01: Runner estándar utilizando CommandLineRunner.
     */
    public static class CustomCommandLineRunner implements org.springframework.boot.CommandLineRunner {
        // GUÍA: teoría 4.6 — un CommandLineRunner real recibe los args crudos.
        // El test (pasoExtra01) comprueba isRunExecuted()==false ANTES de ejecutarlo
        // y que es instanceof CommandLineRunner. Tu trabajo:
        //   - en run(...), marca runExecuted = true (y haz el trabajo de arranque).
        // OJO: el flag arranca en false; el test NO llama a run(), solo verifica el
        //   estado inicial y el tipo.
        private boolean runExecuted = false;

        @Override
        public void run(String... args) {
            // GUÍA: aquí va la tarea de arranque; al hacerla, runExecuted = true;
            throw new UnsupportedOperationException("TODO: implementar run() y poner runExecuted = true");
        }

        public boolean isRunExecuted() {
            return runExecuted;
        }
    }

    /**
     * RETO EXTRA 02: Runner avanzado utilizando ApplicationArguments.
     */
    public static class CustomApplicationRunner implements org.springframework.boot.ApplicationRunner {
        // GUÍA: teoría 4.6 — ApplicationRunner recibe los args YA PARSEADOS.
        // El test (pasoExtra02) solo comprueba que es instanceof ApplicationRunner.
        // En run(args) tienes args.getOptionNames(), args.getOptionValues("x"),
        // args.getNonOptionArgs() (la API que explotas en los retos 06-08).
        @Override
        public void run(org.springframework.boot.ApplicationArguments args) {
            throw new UnsupportedOperationException("TODO: implementar run(ApplicationArguments)");
        }
    }

    /**
     * RETO EXTRA 03: Runner con alta prioridad.
     */
    // GUÍA: para que pasoExtra03 lea 1, AÑADE sobre la clase la anotación:
    //   @org.springframework.core.annotation.Order(1)
    public static class HighPriorityRunner {
        // (sin miembros: la información del reto está en la anotación @Order)
    }

    /**
     * RETO EXTRA 04: Runner con baja prioridad.
     */
    // GUÍA: para que pasoExtra04 lea 2, AÑADE sobre la clase:
    //   @org.springframework.core.annotation.Order(2)
    public static class LowPriorityRunner {
        // (sin miembros: la información del reto está en la anotación @Order)
    }

    /**
     * RETO EXTRA 05: Runner que lee configuraciones inyectadas.
     */
    public static class ConfiguredRunner {
        // GUÍA: teoría 4.1/4.6 — un runner suele leer un flag de config para decidir
        // si actúa. El test (pasoExtra05) espera isSeedEnabled()==false.
        // Para realismo, anota el campo con el default false:
        //   @org.springframework.beans.factory.annotation.Value("${app.seed.enabled:false}")
        //   private boolean seedEnabled;
        // (el default false ya satisface al test sin necesidad de contexto Spring).
        private boolean seedEnabled;

        public boolean isSeedEnabled() {
            return seedEnabled;
        }
    }

    /**
     * RETO EXTRA 09: Generador de códigos de salida del sistema.
     */
    public static class CustomExitCodeGenerator implements org.springframework.boot.ExitCodeGenerator {
        // GUÍA: teoría 4.6 — un ExitCodeGenerator decide con qué código termina la JVM.
        // El test (pasoExtra09) crea uno con código 42 y comprueba que es instanceof
        // ExitCodeGenerator. Guarda el código en el constructor y devuélvelo:
        private final int exitCode;

        public CustomExitCodeGenerator(int exitCode) {
            this.exitCode = exitCode;
        }

        @Override
        public int getExitCode() {
            return exitCode;
        }
    }

    // === Métodos de los retos ===============================================

    /**
     * RETO EXTRA 01: Retorna una nueva instancia del CommandLineRunner personalizado.
     */
    public static CustomCommandLineRunner pasoExtra01() {
        // GUÍA: una línea — return new CustomCommandLineRunner();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Retorna una nueva instancia del ApplicationRunner personalizado.
     */
    public static CustomApplicationRunner pasoExtra02() {
        // GUÍA: una línea — return new CustomApplicationRunner();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Inspecciona y retorna el valor de la anotación @Order en el runner de alta prioridad.
     */
    public static int pasoExtra03(Object runner) {
        // GUÍA: teoría 4.6 — leer @Order por reflexión.
        // PISTA: var ann = org.springframework.core.annotation.AnnotationUtils
        //            .findAnnotation(runner.getClass(),
        //                            org.springframework.core.annotation.Order.class);
        //        return ann.value();
        // OJO: el test pasa un HighPriorityRunner y espera 1 (asegúrate de haber
        //   puesto @Order(1) sobre esa clase). Si la anotación faltara, ann sería
        //   null y esto reventaría: ese NPE es justo la señal de que olvidaste anotar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * RETO EXTRA 04: Inspecciona y retorna el valor de la anotación @Order en el runner de baja prioridad.
     */
    public static int pasoExtra04(Object runner) {
        // GUÍA: idéntico a pasoExtra03 — REUTILIZA su lógica (o llama a pasoExtra03).
        // OJO: el test pasa LowPriorityRunner y espera 2 (anota @Order(2) en la clase).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Retorna el runner que lee configuraciones inyectadas.
     */
    public static ConfiguredRunner pasoExtra05() {
        // GUÍA: una línea — return new ConfiguredRunner();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * RETO EXTRA 06: Recupera los argumentos sin opción (non-option) desde ApplicationArguments.
     */
    public static List<String> pasoExtra06(org.springframework.boot.ApplicationArguments args) {
        // GUÍA: teoría 4.6 — los "non-option" son los que NO empiezan por "--".
        // PISTA: return args.getNonOptionArgs();
        // OJO: el test pasa ("--optionA=val1", "nonOptionA") y espera que la lista
        //   contenga "nonOptionA" (pero NO "optionA", que es una opción).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * RETO EXTRA 07: Recupera el listado completo de nombres de opciones de arranque desde ApplicationArguments.
     */
    public static Set<String> pasoExtra07(org.springframework.boot.ApplicationArguments args) {
        // GUÍA: teoría 4.6 — los nombres de las "--opciones" (sin el -- ni el valor).
        // PISTA: return args.getOptionNames();
        // OJO: el test pasa ("--optionA=val1", "--optionB") y espera que el set
        //   contenga "optionA" y "optionB" (una opción sin valor también cuenta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Recupera los valores asociados a una opción particular de arranque.
     */
    public static List<String> pasoExtra08(org.springframework.boot.ApplicationArguments args, String optionName) {
        // GUÍA: teoría 4.6 — una misma opción puede repetirse y acumular valores.
        // PISTA: return args.getOptionValues(optionName);
        // OJO: el test pasa ("--optionA=val1", "--optionA=val2") y espera que los
        //   valores de "optionA" contengan "val1" Y "val2": getOptionValues
        //   devuelve la LISTA de todos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * RETO EXTRA 09: Retorna una instancia del generador de códigos de salida con un código determinado.
     */
    public static CustomExitCodeGenerator pasoExtra09(int exitCode) {
        // GUÍA: una línea — return new CustomExitCodeGenerator(exitCode);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Ejecuta el CommandLineRunner capturando cualquier excepción para encapsularla de forma segura.
     */
    public static void pasoExtra10(org.springframework.boot.CommandLineRunner runner, String... args) throws Exception {
        // GUÍA: teoría 1.9 (excepciones, encadena la causa) + 4.6.
        // 1. Ejecuta runner.run(args) dentro de un try.
        // 2. Captura la Exception (checked) y RELÁNZALA envuelta, conservando la causa:
        // PISTA: try { runner.run(args); }
        //        catch (Exception e) { throw new RuntimeException("fallo en el runner", e); }
        // OJO: el test pasa un runner que lanza Exception("Boot failed") y espera
        //   assertThrows(RuntimeException.class, ...). Por eso conviertes la checked
        //   en unchecked SIN perder la original (el segundo argumento 'e' = causa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
