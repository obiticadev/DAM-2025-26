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
}
