package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.sql.DataSource;

/**
 * Ejercicio 102 · NamedParameterJdbcTemplate.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.4).
 *
 * <p>Parámetros con nombre (:clave) en vez de '?': más legible y mantenible.
 * Tabla EVENTO(id INT, tipo VARCHAR) (la crea el test).
 */
public final class Ej102NamedParameterJdbc {

    private final NamedParameterJdbcTemplate npjt;

    public Ej102NamedParameterJdbc(DataSource ds) {
        // TODO 1: crea el NamedParameterJdbcTemplate a partir del DataSource.
        this.npjt = null;
    }

    /**
     * Inserta un evento usando parámetros con nombre.
     *
     * @param id   id
     * @param tipo tipo de evento
     * @return filas insertadas
     */
    public int insertar(int id, String tipo) {
        // TODO 2: SQL "INSERT INTO EVENTO(id,tipo) VALUES (:id, :tipo)".
        // TODO 3: crea un MapSqlParameterSource.
        // TODO 4: addValue("id", id).
        // TODO 5: addValue("tipo", tipo).
        // TODO 6: npjt.update(sql, params) y devuelve el resultado.
        return -1;
    }

    /**
     * Cuenta eventos de un tipo.
     *
     * @param tipo tipo a contar
     * @return número de eventos de ese tipo
     */
    public int contarPorTipo(String tipo) {
        // TODO 7: SQL "SELECT COUNT(*) FROM EVENTO WHERE tipo = :tipo".
        // TODO 8: pasa el parámetro nombrado :tipo.
        // TODO 9: usa npjt.queryForObject(sql, params, Integer.class).
        // TODO 10: devuelve el conteo (no debe ser null para COUNT).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    /**
     * TODO extra 1: Retorna el SQL de inserción con parámetros nombrados.
     */
    public static String desafioObtenerSqlInsert() {
        return "INSERT INTO CLIENTE(id, nombre) VALUES (:id, :nombre)";
    }

    /**
     * TODO extra 2: Retorna el SQL de búsqueda con parámetros nombrados.
     */
    public static String desafioObtenerSqlSelect() {
        return "SELECT nombre FROM CLIENTE WHERE id = :id";
    }

    /**
     * TODO extra 3: Comprueba si un MapSqlParameterSource no es nulo.
     */
    public static boolean desafioParameterSourceActivo(org.springframework.jdbc.core.namedparam.MapSqlParameterSource ps) {
        return ps != null;
    }

    /**
     * TODO extra 4: Crea un MapSqlParameterSource y le asigna el parámetro :id.
     */
    public static org.springframework.jdbc.core.namedparam.MapSqlParameterSource desafioCrearSourceConId(int id) {
        return new org.springframework.jdbc.core.namedparam.MapSqlParameterSource("id", id);
    }

    /**
     * TODO extra 5: Añade el parámetro :nombre a un MapSqlParameterSource existente.
     */
    public static org.springframework.jdbc.core.namedparam.MapSqlParameterSource desafioAñadirNombre(org.springframework.jdbc.core.namedparam.MapSqlParameterSource source, String nombre) {
        return source.addValue("nombre", nombre);
    }

    /**
     * TODO extra 6: Comprueba si el template con parámetros nombrados está instanciado.
     */
    public static boolean desafioNamedTemplateActivo(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template) {
        return template != null;
    }

    /**
     * TODO extra 7: Lanza una excepción si el nombre es nulo o vacío en parámetros.
     */
    public static void desafioValidarNombreParam(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
    }

    /**
     * TODO extra 8: Comprueba si un mapa de parámetros contiene una clave específica.
     */
    public static boolean desafioContieneParametro(java.util.Map<String, ?> params, String clave) {
        return params.containsKey(clave);
    }

    /**
     * TODO extra 9: Crea un Map representativo con los valores :id y :nombre de forma simple.
     */
    public static java.util.Map<String, Object> desafioCrearMapParametros(int id, String nombre) {
        return java.util.Map.of("id", id, "nombre", nombre);
    }

    /**
     * TODO extra 10: Retorna verdadero si el total de parámetros ingresados en un source es correcto (por ejemplo, mayor que cero).
     */
    public static boolean desafioTieneParametros(org.springframework.jdbc.core.namedparam.MapSqlParameterSource source) {
        return source != null && source.getValues().size() > 0;
    }

}
