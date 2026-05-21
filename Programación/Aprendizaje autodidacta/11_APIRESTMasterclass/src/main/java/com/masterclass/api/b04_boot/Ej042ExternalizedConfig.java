package com.masterclass.api.b04_boot;

import java.util.Map;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Ejercicio 042 · Config externalizada y precedencia (12-factor).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.1).
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
        // TODO extra: Obtén la propiedad desde el Environment de Spring.
        return "";
    }

    /**
     * RETO EXTRA 02: Incorporación manual de un PropertySource prioritario sobre la cola del Environment.
     */
    public static void pasoExtra02(ConfigurableEnvironment env, String nombreSource, Map<String, Object> propiedades) {
        // TODO extra: Registra un MapPropertySource al inicio de la lista de PropertySources de env para que tenga máxima prioridad.
    }

    /**
     * RETO EXTRA 03: Incorporación de un PropertySource de baja prioridad (secundario/fallback).
     */
    public static void pasoExtra03(ConfigurableEnvironment env, String nombreSource, Map<String, Object> propiedades) {
        // TODO extra: Registra un MapPropertySource al final de la lista de PropertySources de env para fallback de baja prioridad.
    }

    /**
     * RETO EXTRA 04: Traduce una clave yml (camelCase o kebab-case con puntos) al formato estándar de variable de entorno de SO.
     * Ejemplo: "spring.datasource.connection-timeout" -> "SPRING_DATASOURCE_CONNECTION_TIMEOUT"
     */
    public static String pasoExtra04(String ymlKey) {
        // TODO extra: Implementa las reglas de traducción de relajación (relaxed binding) de variables de entorno de Spring Boot.
        return "";
    }

    /**
     * RETO EXTRA 05: Detecta cuál es el PropertySource exacto que provee el valor activo para una propiedad dada.
     */
    public static String pasoExtra05(ConfigurableEnvironment env, String clave) {
        // TODO extra: Recorre todos los PropertySources del Environment configurable y retorna el nombre del primero que contenga la clave.
        return null;
    }

    /**
     * RETO EXTRA 06: Remueve un PropertySource registrado en el entorno por su nombre.
     */
    public static boolean pasoExtra06(ConfigurableEnvironment env, String nombreSource) {
        // TODO extra: Elimina de forma segura el PropertySource indicado por nombre y devuelve true si se pudo eliminar.
        return false;
    }

    /**
     * RETO EXTRA 07: Define propiedades predeterminadas a nivel de aplicación utilizando SpringApplication.
     */
    public static void pasoExtra07(org.springframework.boot.SpringApplication application, Map<String, Object> defaultProperties) {
        // TODO extra: Añade el mapa de defaultProperties como propiedades por defecto de la aplicación SpringApplication antes de arrancar.
    }

    /**
     * RETO EXTRA 08: Carga y añade dinámicamente un conjunto de propiedades formateadas como String "clave=valor" al entorno.
     */
    public static void pasoExtra08(ConfigurableEnvironment env, String propertySourceString) {
        // TODO extra: Parsea el String (ej. "server.port=9090\napp.env=prod") y regístralo como un PropertySource.
    }

    /**
     * RETO EXTRA 09: Verifica si una clave está definida en las variables del sistema de la JVM o del Sistema Operativo de forma directa.
     */
    public static boolean pasoExtra09(String clave) {
        // TODO extra: Comprueba si la clave existe en System.getProperty() o System.getenv().
        return false;
    }

    /**
     * RETO EXTRA 10: Retorna los nombres de todos los PropertySources del entorno respetando el orden jerárquico real de precedencia.
     */
    public static List<String> pasoExtra10(ConfigurableEnvironment env) {
        // TODO extra: Obtén la lista completa de nombres de PropertySources registrados en el ConfigurableEnvironment en orden de prioridad.
        return null;
    }

}
