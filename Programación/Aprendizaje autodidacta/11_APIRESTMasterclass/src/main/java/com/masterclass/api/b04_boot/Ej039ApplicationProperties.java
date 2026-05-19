package com.masterclass.api.b04_boot;

import java.util.Map;

/**
 * Ejercicio 039 · Resolución de propiedades con valor por defecto.
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.2).
 *
 * <p>Replica la sintaxis {@code ${clave:default}} de {@code @Value} sobre un
 * mapa que simula el {@code application.yml}.
 */
public final class Ej039ApplicationProperties {

    private Ej039ApplicationProperties() {
    }

    /**
     * Resuelve una expresión tipo {@code "${app.timeout:30}"}.
     *
     * @param props      mapa de propiedades cargadas
     * @param expression expresión con o sin valor por defecto tras ':'
     * @return el valor encontrado, el default si no existe, o "" si no hay default
     */
    public static String resolve(Map<String, String> props, String expression) {
        // TODO 1: si expression es null/vacía, devuelve "".
        // TODO 2: valida que empiece por "${" y termine por "}"; si no, devuelve "" (formato inválido).
        // TODO 3: recorta los delimitadores "${" y "}" para quedarte con el interior.
        // TODO 4: localiza el primer ':' (separa clave de valor por defecto).
        // TODO 5: si NO hay ':', la clave es todo el interior y no hay default.
        // TODO 6: si HAY ':', la clave es lo anterior y el default lo posterior.
        // TODO 7: busca la clave en 'props'.
        // TODO 8: si existe, devuelve su valor (la config externa gana).
        // TODO 9: si no existe pero hay default, devuelve el default.
        // TODO 10: si no existe y no hay default, devuelve "".
        return "";
    }

    public static void main(String[] args) {
        var props = Map.of("app.region", "eu-west-1");
        System.out.println(resolve(props, "${app.region:us}"));
        System.out.println(resolve(props, "${app.timeout:30}"));
    }
}
