package com.masterclass.api.b04_boot;

import java.util.Map;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Ejercicio 042 · Config externalizada y precedencia (12-factor).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.4).
 *
 * <p>Las variables de entorno PISAN al application.yml. Implementa esa jerarquía.
 */
public final class Ej042ExternalizedConfig {

    private Ej042ExternalizedConfig() {
    }

    /**
     * Resuelve una propiedad respetando la precedencia: env &gt; yml &gt; default.
     *
     * @param env     mapa que simula variables de entorno (claves en MAYUS_CON_GUION_BAJO)
     * @param yml     mapa que simula application.yml (claves con puntos)
     * @param key     clave en formato yml, p.ej. "app.region"
     * @param defecto valor si no aparece en ninguna fuente
     * @return el valor según la precedencia
     */
    public static String resolve(Map<String, String> env, Map<String, String> yml, String key, String defecto) {
        // TODO 1: valida que 'key' no sea null/vacía.
        // TODO 2: traduce 'key' al formato de variable de entorno:
        //         "app.region" -> "APP_REGION" (punto->guion bajo, mayúsculas).
        // TODO 3: consulta primero 'env' con la clave traducida.
        // TODO 4: si está en env, ese valor GANA (mayor precedencia) -> devuélvelo.
        // TODO 5: si no está en env, consulta 'yml' con la clave original.
        // TODO 6: si está en yml, devuélvelo (precedencia media).
        // TODO 7: si no está en ninguna, devuelve 'defecto' (menor precedencia).
        // TODO 8: trata env o yml null como mapas vacíos (defensa).
        // TODO 9: no mezcles formatos: env usa MAYUS_, yml usa minus.con.puntos.
        // TODO 10: devuelve siempre un String no null (defecto puede ser "").
        return "";
    }

    public static void main(String[] args) {
        var env = Map.of("APP_REGION", "us-east-1");
        var yml = Map.of("app.region", "eu-west-1");
        System.out.println(resolve(env, yml, "app.region", "local"));
    }

    /**
     * RETO EXTRA 01: Resuelve una propiedad utilizando la API real de Environment de Spring.
     */
    public static String pasoExtra01(Environment env, String clave) {
        // GUÍA: teoría 4.4/4.1 — el Environment ya combina TODAS las fuentes en orden.
        // PISTA: return env.getProperty(clave);
        // OJO: el test define "custom.property"="hello" y espera "hello". Aquí no
        //   traduces formatos a mano: el Environment real ya tiene una fuente por
        //   las env vars (con su normalización) y otra por el yml.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Incorporación manual de un PropertySource prioritario sobre la cola del Environment.
     */
    public static void pasoExtra02(ConfigurableEnvironment env, String nombreSource, Map<String, Object> propiedades) {
        // GUÍA: teoría 4.4 — addFirst = máxima prioridad (pisa a las demás fuentes).
        // PISTA: env.getPropertySources()
        //           .addFirst(new org.springframework.core.env.MapPropertySource(nombreSource, propiedades));
        // OJO: ya existe my.key="original"; tras añadir tu fuente con my.key="winner"
        //   AL PRINCIPIO, getProperty("my.key") debe dar "winner". Ese es el sentido
        //   de la precedencia: la fuente de más arriba gana.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Incorporación de un PropertySource de baja prioridad (secundario/fallback).
     */
    public static void pasoExtra03(ConfigurableEnvironment env, String nombreSource, Map<String, Object> propiedades) {
        // GUÍA: teoría 4.4 — addLast = mínima prioridad (solo aporta lo que falte).
        // PISTA: env.getPropertySources()
        //           .addLast(new org.springframework.core.env.MapPropertySource(nombreSource, propiedades));
        // OJO: tu fuente trae my.key="loser" y new.key="fallback". Como va AL FINAL,
        //   my.key sigue siendo "original" (la fuente previa gana) pero new.key, que
        //   no existía, SÍ se resuelve a "fallback". Contrasta con pasoExtra02.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * RETO EXTRA 04: Traduce una clave yml (camelCase o kebab-case con puntos) al formato estándar de variable de entorno de SO.
     * Ejemplo: "spring.datasource.connection-timeout" -> "SPRING_DATASOURCE_CONNECTION_TIMEOUT"
     */
    public static String pasoExtra04(String ymlKey) {
        // GUÍA: teoría 4.4 (tabla de formatos) — la traducción yml → variable de SO.
        // 1. Sustituye '.' y '-' por '_'.
        // 2. Pasa todo a MAYÚSCULAS.
        // PISTA: return ymlKey.replace('.', '_').replace('-', '_').toUpperCase();
        // OJO: el test exige "spring.datasource.connection-timeout" →
        //   "SPRING_DATASOURCE_CONNECTION_TIMEOUT" y "app.region" → "APP_REGION".
        //   Es la misma regla que aplica Spring para leer config desde contenedores.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Detecta cuál es el PropertySource exacto que provee el valor activo para una propiedad dada.
     */
    public static String pasoExtra05(ConfigurableEnvironment env, String clave) {
        // GUÍA: teoría 4.4 — recorre las fuentes EN ORDEN y devuelve la primera que
        // tenga la clave (esa es la que "gana").
        // PISTA: for (var ps : env.getPropertySources())
        //            if (ps.containsProperty(clave)) return ps.getName();
        //        return null;
        // OJO: el test hace addFirst("source-a") y luego addFirst("source-b"); como
        //   source-b queda ARRIBA, el ganador para "my.key" es "source-b".
        // CULTURA: esto es lo que muestra el endpoint /actuator/env de Spring Boot.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * RETO EXTRA 06: Remueve un PropertySource registrado en el entorno por su nombre.
     */
    public static boolean pasoExtra06(ConfigurableEnvironment env, String nombreSource) {
        // GUÍA: teoría 4.4 — MutablePropertySources.remove(nombre) devuelve la fuente
        // quitada (o null si no existía).
        // PISTA: return env.getPropertySources().remove(nombreSource) != null;
        // OJO: el test añade "to-delete", llama y espera true, y luego comprueba que
        //   getPropertySources().contains("to-delete") es false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * RETO EXTRA 07: Define propiedades predeterminadas a nivel de aplicación utilizando SpringApplication.
     */
    public static void pasoExtra07(org.springframework.boot.SpringApplication application, Map<String, Object> defaultProperties) {
        // GUÍA: teoría 4.4 — SpringApplication permite fijar defaults globales (la
        // fuente de MENOR prioridad de toda la app).
        // PISTA: application.setDefaultProperties(defaultProperties);
        // OJO: el test solo verifica que no peta al llamarlo. Estas "default
        //   properties" son el último recurso: cualquier yml/env/arg las pisa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Carga y añade dinámicamente un conjunto de propiedades formateadas como String "clave=valor" al entorno.
     */
    public static void pasoExtra08(ConfigurableEnvironment env, String propertySourceString) {
        // GUÍA: teoría 4.4 — parsea un texto "k=v" multilínea y regístralo como fuente.
        // 1. Parte el texto por saltos de línea: propertySourceString.split("\n").
        // 2. Por cada línea, parte por el PRIMER '=' (split("=", 2)) en clave/valor.
        // 3. Acumula en un Map<String,Object> y añádelo con addFirst(MapPropertySource).
        // OJO: el test pasa "server.port=9090\napp.env=prod" y espera luego
        //   getProperty("server.port")=="9090" y getProperty("app.env")=="prod".
        // PISTA: split("=", 2) evita romper valores que contengan '=' (mismo cuidado
        //   que el parseo de --clave=valor del Ej044).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * RETO EXTRA 09: Verifica si una clave está definida en las variables del sistema de la JVM o del Sistema Operativo de forma directa.
     */
    public static boolean pasoExtra09(String clave) {
        // GUÍA: teoría 4.4 — hay DOS almacenes "del sistema" distintos:
        //   - propiedades de la JVM:  System.getProperties()  (p.ej. "java.home")
        //   - variables del SO:       System.getenv()         (p.ej. "PATH")
        // PISTA: return System.getProperties().containsKey(clave)
        //              || System.getenv().containsKey(clave);
        // OJO: el test comprueba pasoExtra09("java.home") || pasoExtra09("PATH"):
        //   "java.home" es propiedad de la JVM y "PATH" es variable del SO, así que
        //   debes mirar en AMBOS sitios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Retorna los nombres de todos los PropertySources del entorno respetando el orden jerárquico real de precedencia.
     */
    public static List<String> pasoExtra10(ConfigurableEnvironment env) {
        // GUÍA: teoría 4.4 — los nombres de las fuentes, en su orden de precedencia.
        // PISTA: var nombres = new java.util.ArrayList<String>();
        //        for (var ps : env.getPropertySources()) nombres.add(ps.getName());
        //        return nombres;
        //   (MutablePropertySources es Iterable y ya está en orden de prioridad).
        // OJO: el test solo exige que la lista NO esté vacía (un MockEnvironment ya
        //   trae al menos su fuente interna).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
