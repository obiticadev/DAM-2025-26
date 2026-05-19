package com.masterclass.api.b15_query;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 139 · Query by Example (probe → condiciones).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 *
 * <p>QBE: das un objeto "ejemplo"; los campos NO nulos se vuelven condiciones AND.
 */
public final class Ej139QueryByExample {

    /** Objeto-ejemplo: campos null = "no filtrar por este". */
    public record PersonaProbe(String nombre, String ciudad, Integer edad) {
    }

    private Ej139QueryByExample() {
    }

    /**
     * Construye el mapa de condiciones (campo → valor) a partir del probe.
     *
     * @param probe ejemplo con algunos campos a null
     * @return mapa con SOLO los campos no nulos, en orden nombre,ciudad,edad
     */
    public static Map<String, Object> condicionesDe(PersonaProbe probe) {
        Map<String, Object> cond = new LinkedHashMap<>();
        // TODO 1: si probe es null -> IllegalArgumentException.
        // TODO 2: si probe.nombre() != null, cond.put("nombre", probe.nombre()).
        // TODO 3: si probe.ciudad() != null, cond.put("ciudad", probe.ciudad()).
        // TODO 4: si probe.edad() != null, cond.put("edad", probe.edad()).
        // TODO 5: respeta el orden de inserción (LinkedHashMap).
        // TODO 6: un probe todo-null produce un mapa vacío (= traer todo).
        // TODO 7: devuelve el mapa.
        return cond;
    }

    /**
     * Filtra una lista en memoria aplicando las condiciones del probe (AND).
     *
     * @param datos lista de personas como Map (clave→valor)
     * @param probe ejemplo
     * @return filas que casan TODAS las condiciones activas
     */
    public static List<Map<String, Object>> filtrar(List<Map<String, Object>> datos, PersonaProbe probe) {
        // TODO 8: obtén las condiciones con condicionesDe(probe).
        // TODO 9: una fila pasa si para CADA (k,v) de condiciones, fila.get(k).equals(v).
        // TODO 10: devuelve las filas que cumplen todas (stream + filter).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(condicionesDe(new PersonaProbe("Ana", null, 30)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si probe es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si probe.nombre() != null, cond.put("nombre", probe.nombre()).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si probe.ciudad() != null, cond.put("ciudad", probe.ciudad()).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si probe.edad() != null, cond.put("edad", probe.edad()).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: respeta el orden de inserción (LinkedHashMap).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: un probe todo-null produce un mapa vacío (= traer todo).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: devuelve el mapa.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: obtén las condiciones con condicionesDe(probe).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: una fila pasa si para CADA (k,v) de condiciones, fila.get(k).equals(v).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve las filas que cumplen todas (stream + filter).
    }

}
