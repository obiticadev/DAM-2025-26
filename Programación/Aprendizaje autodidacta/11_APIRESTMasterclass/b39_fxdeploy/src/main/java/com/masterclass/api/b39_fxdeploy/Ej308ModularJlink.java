package com.masterclass.api.b39_fxdeploy;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 308 · Modularización ({@code module-info.java}) y runtime mínimo con {@code jlink}.
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 4).
 *
 * <p>Para distribuir una app sin pedirle al usuario que instale Java, se crea un RUNTIME a medida:
 * un mini-JDK que solo lleva los módulos que tu app usa. La herramienta es {@code jlink}, y para que
 * funcione tu proyecto debe ser MODULAR, es decir, declarar un {@code module-info.java} con qué
 * módulos {@code requires} y qué paquetes {@code exports}.
 *
 * <p>{@code jlink} es un COMANDO de terminal, no una API Java: igual que con Docker/CI (b22/b23),
 * aquí el core testeable PARSEA el {@code module-info.java} (texto) y CONSTRUYE el comando
 * {@code jlink} como cadena. El comando real para ejecutarlo va en la teoría y en el README del bloque.
 */
public final class Ej308ModularJlink {

    private Ej308ModularJlink() {
    }

    /**
     * Nombre del módulo declarado en un {@code module-info.java}.
     *
     * @param lineas líneas del fichero (puede ser null)
     * @return el nombre tras la palabra {@code module}; {@code ""} si no hay declaración
     */
    public static String nombreModulo(List<String> lineas) {
        // TODO 1: si lineas es null, devuelve "".
        // TODO 2: recorre las líneas y quédate con la primera que (tras trim) empiece por "module ".
        // TODO 3: quita el prefijo "module ", y corta en el primer "{" o ";" que aparezca (indexOf).
        // TODO 4: devuelve el nombre con trim(); si ninguna línea declara módulo, devuelve "".
        //         (el test: "module com.app {" -> "com.app").
        return "";
    }

    /**
     * Lista de módulos que el proyecto {@code requires}.
     *
     * @param lineas líneas del {@code module-info.java} (puede ser null)
     * @return nombres de los módulos requeridos, en orden de aparición
     */
    public static List<String> requires(List<String> lineas) {
        // TODO 5: si lineas es null, devuelve List.of(); crea una lista de resultado.
        // TODO 6: por cada línea (tras trim) que empiece por "requires ": quita "requires ",
        //         quita un posible "transitive " (substring) y quita el ";" final.
        // TODO 7: añade el nombre con trim() a la lista y devuélvela.
        //         (el test: "requires javafx.controls;" y "requires transitive java.sql;"
        //          -> ["javafx.controls","java.sql"]).
        return List.of();
    }

    /**
     * Lista de paquetes que el módulo {@code exports} (su API pública).
     *
     * @param lineas líneas del {@code module-info.java} (puede ser null)
     * @return paquetes exportados, en orden
     */
    public static List<String> exports(List<String> lineas) {
        // TODO 8: si lineas es null, devuelve List.of(); crea la lista de resultado.
        // TODO 9: por cada línea (tras trim) que empiece por "exports ": quita "exports " y el ";" final.
        //         (ignora la parte " to modulo" si la hubiera: corta en " to " si aparece).
        // TODO 10: añade el paquete con trim() y devuelve la lista.
        //          (el test: "exports com.app.api;" -> ["com.app.api"]).
        return List.of();
    }

    public static void main(String[] args) {
        List<String> mi = List.of(
                "module com.masterclass.app {",
                "    requires javafx.controls;",
                "    requires transitive java.sql;",
                "    exports com.masterclass.app.api;",
                "}");
        System.out.println("Módulo: " + nombreModulo(mi));
        System.out.println("requires: " + requires(mi));
        System.out.println("exports: " + exports(mi));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Número de módulos requeridos.
     * Cuántos {@code requires} declara el module-info.
     */
    public static int numeroDeRequires(List<String> lineas) {
        // GUÍA: teoría 4.2 (más requires = runtime más grande; conviene controlarlo).
        // 1. Reutiliza requires(lineas) y devuelve su size().
        // OJO: el test: un module-info con 2 requires -> 2; null -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeRequires");
    }

    /**
     * Reto Extra 2: ¿Requiere ese módulo?
     * true si el module-info tiene un {@code requires} de ese módulo.
     */
    public static boolean tieneRequires(List<String> lineas, String modulo) {
        // GUÍA: teoría 4.2 (comprobar dependencias del módulo).
        // 1. Si modulo es null, devuelve false.
        // 2. Devuelve requires(lineas).contains(modulo).
        // OJO: el test: tieneRequires(mi, "javafx.controls") -> true; "java.net.http" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneRequires");
    }

    /**
     * Reto Extra 3: Solo los {@code requires transitive}.
     * Los módulos marcados como transitive (los re-exporta a quien dependa de ti).
     */
    public static List<String> requiresTransitive(List<String> lineas) {
        // GUÍA: teoría 4.2 (transitive: quien use tu módulo "hereda" ese requires sin declararlo).
        // 1. Si lineas es null, devuelve List.of().
        // 2. Quédate con las líneas (trim) que empiecen por "requires transitive ".
        // 3. Quita ese prefijo y el ";" final, trim, y añádelo a la lista.
        // PISTA: filtra por startsWith("requires transitive ") ANTES de quitar el prefijo.
        // OJO: el test: con "requires transitive java.sql;" -> ["java.sql"];
        //   un "requires javafx.controls;" (sin transitive) NO debe aparecer.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiresTransitive");
    }

    /**
     * Reto Extra 4: Número de paquetes exportados.
     * Cuántos {@code exports} declara el módulo.
     */
    public static int numeroDeExports(List<String> lineas) {
        // GUÍA: teoría 4.3 (la superficie pública de tu módulo).
        // 1. Reutiliza exports(lineas) y devuelve su size().
        // OJO: el test: un module-info con 1 export -> 1; null -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeExports");
    }

    /**
     * Reto Extra 5: ¿Es un nombre de módulo válido?
     * Identificadores separados por puntos, sin guiones ni dobles puntos.
     */
    public static boolean nombreModuloValido(String nombre) {
        // GUÍA: teoría 4.1 (los nombres de módulo siguen las reglas de un nombre de paquete Java).
        // 1. Si nombre es null o vacío, devuelve false.
        // 2. Devuelve nombre.matches("[a-zA-Z]\\w*(\\.[a-zA-Z]\\w*)*").
        //    (cada segmento empieza por letra y solo lleva letras/dígitos/_; sin guiones).
        // PISTA: "com.app" -> true; "com.app-ui" -> false (el guion no es válido en módulos).
        // OJO: el test: "com.masterclass.app" -> true; "com.app-ui" -> false; "2cosas" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreModuloValido");
    }

    /**
     * Reto Extra 6: Argumento {@code --add-modules}.
     * jlink necesita la lista de módulos raíz separados por COMAS.
     */
    public static String argAddModules(List<String> modulos) {
        // GUÍA: teoría 4.4 (jlink --add-modules m1,m2,... define el grafo a incluir).
        // 1. Si modulos es null o vacía, devuelve "".
        // 2. Devuelve "--add-modules " + los módulos unidos por "," (String.join(",", ...)).
        // PISTA: String.join(",", modulos), sin espacios entre comas.
        // OJO: el test: ["java.base","javafx.controls"] -> "--add-modules java.base,javafx.controls".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para argAddModules");
    }

    /**
     * Reto Extra 7: Comando jlink completo.
     * Construye {@code jlink --add-modules <m> --output <dir>}.
     */
    public static String comandoJlink(List<String> modulos, String salida) {
        // GUÍA: teoría 4.4 (el comando que genera el runtime mínimo en la carpeta de salida).
        // 1. Si salida es null o vacía, usa "runtime".
        // 2. Devuelve "jlink " + argAddModules(modulos) + " --output " + salida.
        // PISTA: reutiliza argAddModules(modulos) del reto anterior.
        // OJO: el test: (["java.base"],"runtime") -> "jlink --add-modules java.base --output runtime".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoJlink");
    }

    /**
     * Reto Extra 8: Nombre de módulo automático desde un artefacto.
     * Un jar sin module-info recibe un "automatic module name" derivado de su nombre.
     */
    public static String moduloAutomatico(String artifactId) {
        // GUÍA: teoría 4.5 (módulos automáticos: el nombre se deriva cambiando '-' por '.').
        // 1. Si artifactId es null o vacío, devuelve "".
        // 2. Devuelve artifactId.toLowerCase() con los '-' cambiados por '.' y los '_' por '.'.
        // PISTA: artifactId.toLowerCase().replace('-', '.').replace('_', '.').
        // OJO: el test: "b39_fxdeploy" -> "b39.fxdeploy"; "my-lib" -> "my.lib".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moduloAutomatico");
    }

    /**
     * Reto Extra 9: ¿Es un módulo del JDK?
     * Los módulos de la plataforma empiezan por {@code java.} o {@code jdk.}.
     */
    public static boolean esModuloJdk(String modulo) {
        // GUÍA: teoría 4.2 (separar tus dependencias de las de la plataforma al planificar el runtime).
        // 1. Si modulo es null, devuelve false.
        // 2. Devuelve true si empieza por "java." o por "jdk.".
        // OJO: el test: "java.base" -> true; "jdk.crypto.ec" -> true; "javafx.controls" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esModuloJdk");
    }

    /**
     * Reto Extra 10: Solo los módulos de TERCEROS.
     * De una lista de requires, devuelve los que NO son del JDK (tus dependencias externas).
     */
    public static List<String> modulosDeTerceros(List<String> lineas) {
        // GUÍA: teoría 4.5 (jlink clásico no acepta módulos automáticos; estos son los que dan guerra).
        // 1. Toma requires(lineas).
        // 2. Filtra los que NO esModuloJdk(...).
        // PISTA: requires(lineas).stream().filter(m -> !esModuloJdk(m)).toList().
        // OJO: el test: con requires [java.sql, javafx.controls] -> ["javafx.controls"].
        // CULTURA: distinguir "plataforma" de "terceros" es justo lo que hace el classpath/módulos de
        //   un Dockerfile multi-stage (b22): copias solo lo tuyo sobre una base que ya trae la plataforma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modulosDeTerceros");
    }

    /** Helper de demostración: arma una lista mutable de líneas. */
    static List<String> lineas(String... ls) {
        return new ArrayList<>(List.of(ls));
    }
}
