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

    /**
     * Reto Extra 1: Obtiene el nombre de un producto de forma segura.
     */
    public static String obtenerNombre(Prod139 p) {
        // TODO extra: Reto Extra 1: Obtiene el nombre de un producto de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Prod139 p) {
        // TODO extra: Reto Extra 2: Obtiene la categoria de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCategoria");
    }

    /**
     * Reto Extra 3: Comprueba si el producto tiene campos asignados para Example.
     */
    public static boolean tieneCamposDeEjemplo(Prod139 p) {
        // TODO extra: Reto Extra 3: Comprueba si el producto tiene campos asignados para Example.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCamposDeEjemplo");
    }

    /**
     * Reto Extra 4: Crea un nuevo producto de ejemplo.
     */
    public static Prod139 crearEjemplo(String nombre, String categoria) {
        // TODO extra: Reto Extra 4: Crea un nuevo producto de ejemplo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearEjemplo");
    }

    /**
     * Reto Extra 5: Comprueba si el precio de ejemplo esta asignado (mayor que 0).
     */
    public static boolean tienePrecioAsignado(Prod139 p) {
        // TODO extra: Reto Extra 5: Comprueba si el precio de ejemplo esta asignado (mayor que 0).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePrecioAsignado");
    }

    /**
     * Reto Extra 6: Obtiene el ID del producto de ejemplo.
     */
    public static Long obtenerId(Prod139 p) {
        // TODO extra: Reto Extra 6: Obtiene el ID del producto de ejemplo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        // TODO extra: Reto Extra 7: Normaliza el texto de los campos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 8: Comprueba si el ejemplo es nuevo (ID nulo).
     */
    public static boolean esNuevo(Prod139 p) {
        // TODO extra: Reto Extra 8: Comprueba si el ejemplo es nuevo (ID nulo).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 9: Valida el producto de ejemplo.
     */
    public static boolean esValido(Prod139 p) {
        // TODO extra: Reto Extra 9: Valida el producto de ejemplo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * Reto Extra 10: Retorna formato del ejemplo.
     */
    public static String formatearEjemplo(Prod139 p) {
        // TODO extra: Reto Extra 10: Retorna formato del ejemplo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEjemplo");
    }



}
