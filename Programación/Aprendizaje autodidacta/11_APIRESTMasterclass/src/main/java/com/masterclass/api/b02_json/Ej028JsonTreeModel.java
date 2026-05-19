package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 028 · Árbol JSON (JsonNode) para lectura dinámica.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.4).
 */
public final class Ej028JsonTreeModel {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej028JsonTreeModel() {
    }

    /**
     * Extrae el id del primer elemento del array "datos".
     *
     * @param json texto tipo {@code {"datos":[{"id":7},{"id":9}]}}
     * @return el id (7 en el ejemplo); -1 si la ruta no existe
     */
    public static int primerIdDeDatos(String json) {
        // TODO 1: parsea el texto con MAPPER.readTree(json) (envuelve la checked).
        // TODO 2: obtén el nodo "datos" con root.get("datos").
        // TODO 3: si ese nodo es null o no es array, devuelve -1.
        // TODO 4: accede al primer elemento con get(0); si es null, -1.
        // TODO 5: obtén el nodo "id" de ese elemento; si es null, -1.
        // TODO 6: devuelve su valor con asInt().
        return Integer.MIN_VALUE;
    }

    /**
     * Cuenta cuántos elementos tiene el array "datos".
     *
     * @param json texto JSON
     * @return tamaño del array, o 0 si no existe o no es array
     */
    public static int tamanioDatos(String json) {
        // TODO 7: parsea con readTree (reutiliza el patrón anterior).
        // TODO 8: localiza el nodo "datos".
        // TODO 9: si es null o no isArray(), devuelve 0.
        // TODO 10: si es array, devuelve su size().
        return -1;
    }

    public static void main(String[] args) {
        String j = "{\"datos\":[{\"id\":7},{\"id\":9}]}";
        System.out.println(primerIdDeDatos(j) + " / " + tamanioDatos(j));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: parsea el texto con MAPPER.readTree(json) (envuelve la checked).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: obtén el nodo "datos" con root.get("datos").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si ese nodo es null o no es array, devuelve -1.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: accede al primer elemento con get(0); si es null, -1.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: obtén el nodo "id" de ese elemento; si es null, -1.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve su valor con asInt().
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: parsea con readTree (reutiliza el patrón anterior).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: localiza el nodo "datos".
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si es null o no isArray(), devuelve 0.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: si es array, devuelve su size().
    }

}
