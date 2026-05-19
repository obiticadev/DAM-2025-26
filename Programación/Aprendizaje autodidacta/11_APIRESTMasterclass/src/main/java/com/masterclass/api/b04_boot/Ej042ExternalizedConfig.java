package com.masterclass.api.b04_boot;

import java.util.Map;

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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que 'key' no sea null/vacía.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: traduce 'key' al formato de variable de entorno:
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: consulta primero 'env' con la clave traducida.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si está en env, ese valor GANA (mayor precedencia) -> devuélvelo.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si no está en env, consulta 'yml' con la clave original.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si está en yml, devuélvelo (precedencia media).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si no está en ninguna, devuelve 'defecto' (menor precedencia).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: trata env o yml null como mapas vacíos (defensa).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: no mezcles formatos: env usa MAYUS_, yml usa minus.con.puntos.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve siempre un String no null (defecto puede ser "").
    }

}
