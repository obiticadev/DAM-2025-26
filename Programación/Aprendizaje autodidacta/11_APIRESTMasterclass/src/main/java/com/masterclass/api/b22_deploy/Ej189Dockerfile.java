package com.masterclass.api.b22_deploy;

import java.util.List;

/**
 * Ejercicio 189 · Dockerfile multi-stage (guion como lógica pura).
 *
 * <p>Teoría: {@code teoria/22_Docker_Despliegue.md} (sección 22.1).
 *
 * <p>Un Dockerfile multi-stage usa un stage "builder" (con Maven/JDK) para
 * compilar el JAR y un stage "runtime" mínimo (solo JRE) que copia el
 * artefacto. La imagen final NO arrastra el compilador ni el código fuente,
 * corre como usuario no-root y expone solo el puerto necesario.
 *
 * <p>Aquí modelamos esa generación como función pura: a partir de los
 * parámetros del build producimos las líneas del Dockerfile y validamos sus
 * invariantes de seguridad y tamaño.
 */
public final class Ej189Dockerfile {

    private Ej189Dockerfile() {
    }

    /**
     * Genera las líneas de un Dockerfile multi-stage.
     *
     * @param imagenBuilder imagen base del stage de build (p.ej. {@code maven:3.9-eclipse-temurin-21})
     * @param imagenRuntime imagen base del stage final (p.ej. {@code eclipse-temurin:21-jre})
     * @param nombreJar     nombre del artefacto generado (p.ej. {@code app.jar})
     * @param puerto        puerto que expone la app (&gt; 0 y &lt; 65536)
     * @return lista ordenada de líneas del Dockerfile
     * @throws IllegalArgumentException si algún parámetro es null/blank o el puerto es inválido
     */
    public static List<String> generar(String imagenBuilder, String imagenRuntime,
                                        String nombreJar, int puerto) {
        // TODO 1: valida que imagenBuilder, imagenRuntime y nombreJar no sean null ni blank -> IllegalArgumentException.
        // TODO 2: valida que el puerto esté en (0, 65536) -> IllegalArgumentException (rango TCP válido).
        // TODO 3: abre el stage builder: "FROM <imagenBuilder> AS build" (alias 'build' para referenciarlo luego).
        // TODO 4: en el builder, fija WORKDIR /app, copia el código y ejecuta el empaquetado (mvn -q -DskipTests package).
        // TODO 5: abre el stage runtime: "FROM <imagenRuntime>" (imagen mínima, solo JRE: sin compilador).
        // TODO 6: crea un usuario no-root y cámbiate a él (RUN adduser ... + USER appuser) por principio de menor privilegio.
        // TODO 7: fija WORKDIR /app y copia SOLO el jar desde el stage build: "COPY --from=build /app/target/<jar> app.jar".
        // TODO 8: declara EXPOSE <puerto> (documenta el puerto; no abre nada por sí solo).
        // TODO 9: define ENTRYPOINT en forma exec (JSON array) para que la JVM reciba señales: ["java","-jar","app.jar"].
        // TODO 10: devuelve la lista de líneas en orden; el stage final NO debe contener "mvn" ni el código fuente.
        return List.of();
    }

    /**
     * Verifica las invariantes de seguridad/tamaño de un Dockerfile ya generado.
     *
     * @param lineas líneas del Dockerfile
     * @return true si: hay 2 FROM (multi-stage), existe una directiva USER no-root,
     *         el stage final no usa la imagen builder y el ENTRYPOINT está en forma exec
     */
    public static boolean esSeguro(List<String> lineas) {
        // TODO 1: si lineas es null o vacía -> false.
        // TODO 2: cuenta las directivas FROM: debe haber al menos 2 (multi-stage real).
        // TODO 3: localiza una línea USER cuyo valor NO sea root/0 (corre sin privilegios).
        // TODO 4: comprueba que existe al menos un "COPY --from=" (se reaprovecha el artefacto del builder).
        // TODO 5: comprueba que el ENTRYPOINT está en forma exec (empieza por '[' = JSON array) para propagar SIGTERM.
        // TODO 6: comprueba que ninguna línea del stage final ejecute "mvn" (no se compila en runtime).
        // TODO 7: comprueba que el stage final NO arranca FROM la imagen builder (sería arrastrar el JDK).
        // TODO 8: si falta cualquiera de las invariantes anteriores -> false.
        // TODO 9: trata mayúsculas/minúsculas de instrucciones Docker de forma consistente.
        // TODO 10: devuelve true solo si TODAS las invariantes se cumplen.
        return false;
    }

    public static void main(String[] args) {
        List<String> df = generar("maven:3.9-eclipse-temurin-21", "eclipse-temurin:21-jre", "app.jar", 8080);
        df.forEach(System.out::println);
        System.out.println("seguro=" + esSeguro(df));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que imagenBuilder, imagenRuntime y nombreJar no sean null ni blank -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: valida que el puerto esté en (0, 65536) -> IllegalArgumentException (rango TCP válido).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: abre el stage builder: "FROM <imagenBuilder> AS build" (alias 'build' para referenciarlo luego).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: en el builder, fija WORKDIR /app, copia el código y ejecuta el empaquetado (mvn -q -DskipTests package).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: abre el stage runtime: "FROM <imagenRuntime>" (imagen mínima, solo JRE: sin compilador).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: crea un usuario no-root y cámbiate a él (RUN adduser ... + USER appuser) por principio de menor privilegio.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: fija WORKDIR /app y copia SOLO el jar desde el stage build: "COPY --from=build /app/target/<jar> app.jar".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: declara EXPOSE <puerto> (documenta el puerto; no abre nada por sí solo).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: define ENTRYPOINT en forma exec (JSON array) para que la JVM reciba señales: ["java","-jar","app.jar"].
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista de líneas en orden; el stage final NO debe contener "mvn" ni el código fuente.
    }

}
