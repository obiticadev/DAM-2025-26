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

    /**
     * RETO EXTRA 01: Valida que una imagen base de Docker tenga un formato correcto con tag.
     * 
     * @param imagen la imagen base a validar
     * @return true si tiene formato 'nombre:tag', false en caso contrario
     */
    public static boolean validarImagenBase(String imagen) {
        // GUÍA: teoría 22.1 (una imagen es "nombre:tag"; sin tag es no-determinista).
        // 1. null -> false.
        // 2. Debe tener formato "nombre:tag": parte por los dos puntos y exige
        //    que AMBAS partes existan y NO estén vacías.
        // PISTA: int i = imagen.indexOf(':'); válido si i > 0 && i < imagen.length()-1.
        //        (o imagen.split(":") con length==2 y ninguna parte blank).
        // OJO: el test exige false para "maven" (sin ':') y para "maven:" (tag
        //      vacío); true solo para "maven:3.9-eclipse-temurin-21".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarImagenBase");
    }

    /**
     * RETO EXTRA 02: Parsea el puerto de una directiva EXPOSE.
     * 
     * @param lineaExpose la línea que contiene EXPOSE
     * @return el número de puerto, o -1 si no es válido
     */
    public static int parsearPuerto(String lineaExpose) {
        // GUÍA: teoría 22.1 (EXPOSE documenta el puerto).
        // 1. null -> -1.
        // 2. trim() la línea (el test manda "  EXPOSE 80  " con espacios sobrantes).
        // 3. Si NO empieza por "EXPOSE" -> -1 ("RUN apt-get update" debe dar -1).
        // 4. Toma lo que sigue a EXPOSE y conviértelo a int; si no es número -> -1.
        // PISTA: String[] p = linea.trim().split("\\s+"); p[0].equals("EXPOSE").
        //        Envuelve Integer.parseInt(p[1]) en try/catch (NumberFormatException -> -1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearPuerto");
    }

    /**
     * RETO EXTRA 03: Extrae los nombres de los stages (alias) definidos con 'AS' en el Dockerfile.
     * 
     * @param lineas las líneas del Dockerfile
     * @return lista de nombres de stages encontrados
     */
    public static List<String> extraerStageNames(List<String> lineas) {
        // GUÍA: teoría 22.1 ("FROM imagen AS alias" nombra un stage).
        // 1. null -> lista vacía (List.of()).
        // 2. Recorre las líneas; quédate con las que contienen " AS " (un FROM
        //    con alias). El alias es la PALABRA que sigue al AS.
        // 3. Devuelve los alias EN ORDEN de aparición.
        // PISTA: split("\\s+") y coge el token siguiente al que sea "AS"
        //        (ignorando mayúsc/minúsc con equalsIgnoreCase). O un stream
        //        .filter(...).map(...).toList().
        // OJO: el test espera exactamente ["build","runtime"] de dos FROM con AS;
        //      "WORKDIR /app" (sin AS) no aporta nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerStageNames");
    }

    /**
     * RETO EXTRA 04: Cuenta la frecuencia de una determinada instrucción en el Dockerfile.
     * 
     * @param lineas las líneas del Dockerfile
     * @param instruccion la instrucción a buscar (ej. "RUN", "COPY")
     * @return el número de ocurrencias
     */
    public static long contarInstrucciones(List<String> lineas, String instruccion) {
        // GUÍA: teoría 22.1. Devuelve un long (cuenta de ocurrencias).
        // 1. Si lineas o instruccion son null -> 0.
        // 2. Cuenta las líneas cuya instrucción (primer token) sea la pedida.
        // PISTA: lineas.stream().filter(l -> l.trim().startsWith(instruccion + " "))
        //        .count();  (o l.trim().split("\\s+")[0].equals(instruccion)).
        // OJO: el test pide 2 para "RUN", 1 para "COPY", 0 para "EXPOSE" (que no
        //      aparece) -> contar lo ausente debe dar 0, no fallar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarInstrucciones");
    }

    /**
     * RETO EXTRA 05: Detecta comandos potencialmente inseguros o desaconsejados en las líneas RUN.
     * 
     * @param lineas las líneas del Dockerfile
     * @return lista de líneas que contienen comandos sospechosos
     */
    public static List<String> detectarInstruccionesSospechosas(List<String> lineas) {
        // GUÍA: teoría 22.1 (principio de menor privilegio).
        // 1. null -> lista vacía.
        // 2. Devuelve las líneas que contengan un comando sospechoso. Para este
        //    test bastan dos marcadores: "sudo" y "chmod 777".
        // PISTA: .filter(l -> l.contains("sudo") || l.contains("chmod 777")).toList();
        // OJO: el test espera 2 resultados y comprueba que el primero contiene
        //      "sudo" y el segundo "chmod 777" -> respeta el ORDEN de las líneas.
        //      "RUN apt-get update" (limpio) NO debe colarse.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarInstruccionesSospechosas");
    }

    /**
     * RETO EXTRA 06: Genera una directiva COPY optimizada y segura indicando el origen.
     * 
     * @param fromStage nombre del stage origen
     * @param origen ruta origen en el stage
     * @param destino ruta destino final
     * @return la directiva COPY generada
     */
    public static String generarDirectivaCopy(String fromStage, String origen, String destino) {
        // GUÍA: teoría 22.1 (COPY --from=stage reaprovecha el artefacto del builder).
        // 1. Si fromStage, origen o destino son null/blank -> IllegalArgumentException.
        // 2. Formato EXACTO: "COPY --from=" + fromStage + " " + origen + " " + destino.
        // PISTA: return "COPY --from=%s %s %s".formatted(fromStage, origen, destino);
        // OJO: el test compara con equals "COPY --from=build /app/target/app.jar app.jar"
        //      y exige IllegalArgumentException cuando fromStage es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarDirectivaCopy");
    }

    /**
     * RETO EXTRA 07: Verifica si un usuario configurado en la directiva USER es seguro (no root/0).
     * 
     * @param lineaUser la línea de USER
     * @return true si es seguro, false de lo contrario
     */
    public static boolean esUsuarioSeguro(String lineaUser) {
        // GUÍA: teoría 22.1 (correr como root es el antipatrón nº 2 del bloque).
        // 1. null -> false.
        // 2. Debe ser una línea USER: si NO empieza por "USER" -> false
        //    ("RUN adduser" debe dar false aunque mencione un usuario).
        // 3. Extrae el usuario (segundo token). Es seguro si NO es "root" ni "0".
        // PISTA: String[] p = lineaUser.trim().split("\\s+");
        //        p[0].equals("USER") && !p[1].equals("root") && !p[1].equals("0").
        // OJO: el test exige false tanto para "USER root" como para "USER 0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioSeguro");
    }

    /**
     * RETO EXTRA 08: Formatea una etiqueta metadata (LABEL) en formato estándar.
     * 
     * @param clave la clave de la etiqueta
     * @param valor el valor de la etiqueta
     * @return la línea del Dockerfile formateada
     */
    public static String formatearEtiqueta(String clave, String valor) {
        // GUÍA: teoría 22.1 (LABEL añade metadatos a la imagen).
        // 1. Si clave o valor son null/blank -> IllegalArgumentException
        //    (el test manda clave " " con un espacio: usa isBlank(), no isEmpty()).
        // 2. Formato: LABEL clave="valor" (el valor SIEMPRE entre comillas dobles).
        // PISTA: return "LABEL " + clave + "=\"" + valor + "\"";
        // OJO: el test compara con equals LABEL version="1.0.0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEtiqueta");
    }

    /**
     * RETO EXTRA 09: Valida si una línea de comando (ENTRYPOINT/CMD) está en la forma 'exec' (JSON array).
     * 
     * @param linea la línea a verificar
     * @return true si está en forma exec, false de lo contrario
     */
    public static boolean esExecFormEntrypoint(String linea) {
        // GUÍA: teoría 22.5 (exec form = array JSON; propaga SIGTERM a la JVM).
        // 1. null -> false.
        // 2. Debe ser una línea ENTRYPOINT cuyo argumento empiece por '['.
        // 3. Comprueba dos cosas: que empieza por "ENTRYPOINT" y que tras él hay
        //    un '[' (forma exec). Lo más simple: tras quitar "ENTRYPOINT" y
        //    trim, el resto empieza por '['.
        // PISTA: linea.trim().startsWith("ENTRYPOINT")
        //        && linea.substring(linea.indexOf("ENTRYPOINT")+10).trim().startsWith("[").
        // OJO: el test pide false para "RUN [\"java\"]" (es array pero NO es
        //      ENTRYPOINT) y para "ENTRYPOINT java -jar app.jar" (no es array).
        //      Hermano del reto 03 de Ej193 (esEntrypointFormaExec).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExecFormEntrypoint");
    }

    /**
     * RETO EXTRA 10: Simplifica un Dockerfile eliminando comentarios y líneas en blanco para optimizar lectura.
     * 
     * @param lineas el Dockerfile original
     * @return el Dockerfile optimizado y compacto
     */
    public static List<String> simplificarDockerfile(List<String> lineas) {
        // GUÍA: teoría 22.1. Filtra fuera comentarios y líneas en blanco.
        // 1. null -> lista vacía.
        // 2. Descarta: líneas en blanco (tras trim) y comentarios (trim empieza por '#').
        // PISTA: lineas.stream()
        //        .filter(l -> !l.isBlank() && !l.trim().startsWith("#")).toList();
        // OJO: la línea "   " (solo espacios) cuenta como blanca -> usa isBlank()
        //      sobre el trim. El test espera exactamente ["FROM base","RUN echo hello"].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para simplificarDockerfile");
    }

}
