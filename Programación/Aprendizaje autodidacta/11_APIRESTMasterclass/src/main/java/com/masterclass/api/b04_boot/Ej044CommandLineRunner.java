package com.masterclass.api.b04_boot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ejercicio 044 · Bootstrap de arranque (CommandLineRunner).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.1).
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

    /**
     * RETO EXTRA 01: Runner estándar utilizando CommandLineRunner.
     */
    // TODO extra: Implementa la interfaz org.springframework.boot.CommandLineRunner
    public static class CustomCommandLineRunner implements org.springframework.boot.CommandLineRunner {
        private boolean runExecuted = false;

        @Override
        public void run(String... args) throws Exception {
            // TODO extra: Marca runExecuted como true al ejecutarse.
        }

        public boolean isRunExecuted() { return runExecuted; }
    }

    /**
     * RETO EXTRA 02: Runner avanzado utilizando ApplicationArguments.
     */
    // TODO extra: Implementa la interfaz org.springframework.boot.ApplicationRunner
    public static class CustomApplicationRunner implements org.springframework.boot.ApplicationRunner {
        private final List<String> optionNames = new ArrayList<>();

        @Override
        public void run(org.springframework.boot.ApplicationArguments args) throws Exception {
            // TODO extra: Lee las opciones de arranque y añádelas todas a optionNames.
        }

        public List<String> getOptionNames() { return optionNames; }
    }

    /**
     * RETO EXTRA 03: Runner con alta prioridad.
     */
    // TODO extra: Añade anotación @org.springframework.core.annotation.Order(1)
    public static class HighPriorityRunner {
        public int getOrder() { return 1; }
    }

    /**
     * RETO EXTRA 04: Runner con baja prioridad.
     */
    // TODO extra: Añade anotación @org.springframework.core.annotation.Order(2)
    public static class LowPriorityRunner {
        public int getOrder() { return 2; }
    }

    /**
     * RETO EXTRA 05: Runner que lee configuraciones inyectadas.
     */
    public static class ConfiguredRunner {
        // TODO extra: Inyecta @org.springframework.beans.factory.annotation.Value("${app.seed.enabled:false}")
        private boolean seedEnabled;

        public boolean isSeedEnabled() { return seedEnabled; }
        public void setSeedEnabled(boolean seedEnabled) { this.seedEnabled = seedEnabled; }
    }

    /**
     * RETO EXTRA 09: Generador de códigos de salida del sistema.
     */
    // TODO extra: Implementa la interfaz org.springframework.boot.ExitCodeGenerator
    public static class CustomExitCodeGenerator implements org.springframework.boot.ExitCodeGenerator {
        private final int exitCode;

        public CustomExitCodeGenerator(int exitCode) {
            this.exitCode = exitCode;
        }

        @Override
        public int getExitCode() {
            // TODO extra: Devuelve el código de salida exitCode.
            return 0;
        }
    }

    /**
     * RETO EXTRA 01: Retorna una nueva instancia del CommandLineRunner personalizado.
     */
    public static CustomCommandLineRunner pasoExtra01() {
        // TODO extra: Retorna una instancia de CustomCommandLineRunner.
        return null;
    }

    /**
     * RETO EXTRA 02: Retorna una nueva instancia del ApplicationRunner personalizado.
     */
    public static CustomApplicationRunner pasoExtra02() {
        // TODO extra: Retorna una instancia de CustomApplicationRunner.
        return null;
    }

    /**
     * RETO EXTRA 03: Inspecciona y retorna el valor de la anotación @Order en el runner de alta prioridad.
     */
    public static int pasoExtra03(Object runner) {
        // TODO extra: Lee reflexivamente la anotación @Order y devuelve su valor numérico, o -1 si no está anotado.
        return -1;
    }

    /**
     * RETO EXTRA 04: Inspecciona y retorna el valor de la anotación @Order en el runner de baja prioridad.
     */
    public static int pasoExtra04(Object runner) {
        // TODO extra: Lee reflexivamente la anotación @Order y devuelve su valor numérico, o -1 si no está anotado.
        return -1;
    }

    /**
     * RETO EXTRA 05: Retorna el runner que lee configuraciones inyectadas.
     */
    public static ConfiguredRunner pasoExtra05() {
        // TODO extra: Retorna una instancia de ConfiguredRunner.
        return null;
    }

    /**
     * RETO EXTRA 06: Recupera los argumentos sin opción (non-option) desde ApplicationArguments.
     */
    public static List<String> pasoExtra06(org.springframework.boot.ApplicationArguments args) {
        // TODO extra: Devuelve la lista de argumentos sin opción del objeto args.
        return null;
    }

    /**
     * RETO EXTRA 07: Recupera el listado completo de nombres de opciones de arranque desde ApplicationArguments.
     */
    public static Set<String> pasoExtra07(org.springframework.boot.ApplicationArguments args) {
        // TODO extra: Devuelve los nombres de opciones (option names) del objeto args.
        return null;
    }

    /**
     * RETO EXTRA 08: Recupera los valores asociados a una opción particular de arranque.
     */
    public static List<String> pasoExtra08(org.springframework.boot.ApplicationArguments args, String optionName) {
        // TODO extra: Devuelve los valores de la opción optionName en args.
        return null;
    }

    /**
     * RETO EXTRA 09: Retorna una instancia del generador de códigos de salida con un código determinado.
     */
    public static CustomExitCodeGenerator pasoExtra09(int exitCode) {
        // TODO extra: Retorna una instancia de CustomExitCodeGenerator con el código indicado.
        return null;
    }

    /**
     * RETO EXTRA 10: Ejecuta el CommandLineRunner capturando cualquier excepción para encapsularla de forma segura.
     */
    public static void pasoExtra10(org.springframework.boot.CommandLineRunner runner, String... args) throws Exception {
        // TODO extra: Ejecuta runner.run(args) capturando cualquier excepción para relanzarla encapsulada en RuntimeException.
    }

}
