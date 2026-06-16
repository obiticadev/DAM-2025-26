package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.sql.DataSource;

/**
 * Ejercicio 102 · NamedParameterJdbcTemplate.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.10).
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
        // GUÍA: teoría 11.10 — marcadores :clave en vez de ?.
        // Una línea: return "INSERT INTO CLIENTE(id, nombre) VALUES (:id, :nombre)";
        // OJO: el test compara con equals — los marcadores van con dos puntos (:id, :nombre)
        //      y hay espacios tras las comas. Cópialo literal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlInsert");
    }

    /**
     * TODO extra 2: Retorna el SQL de búsqueda con parámetros nombrados.
     */
    public static String desafioObtenerSqlSelect() {
        // GUÍA: teoría 11.10. Una línea:
        // return "SELECT nombre FROM CLIENTE WHERE id = :id";
        // OJO: el test exige "WHERE id = :id" con espacios alrededor del '='.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlSelect");
    }

    /**
     * TODO extra 3: Comprueba si un MapSqlParameterSource no es nulo.
     */
    public static boolean desafioParameterSourceActivo(org.springframework.jdbc.core.namedparam.MapSqlParameterSource ps) {
        // GUÍA: una línea — return ps != null;
        // OJO: el test pasa un new MapSqlParameterSource() (true) y null (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioParameterSourceActivo");
    }

    /**
     * TODO extra 4: Crea un MapSqlParameterSource y le asigna el parámetro :id.
     */
    public static org.springframework.jdbc.core.namedparam.MapSqlParameterSource desafioCrearSourceConId(int id) {
        // GUÍA: teoría 11.10 — el constructor (clave, valor) es un atajo de addValue.
        // Una línea: return new MapSqlParameterSource("id", id);
        // OJO: el test crea con id=10 y comprueba source.getValue("id") == 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioCrearSourceConId");
    }

    /**
     * TODO extra 5: Añade el parámetro :nombre a un MapSqlParameterSource existente.
     */
    public static org.springframework.jdbc.core.namedparam.MapSqlParameterSource desafioAñadirNombre(org.springframework.jdbc.core.namedparam.MapSqlParameterSource source, String nombre) {
        // GUÍA: teoría 11.10 — addValue DEVUELVE el propio source (encadenable, estilo builder).
        // Una línea: return source.addValue("nombre", nombre);
        // OJO: el test comprueba después source.getValue("nombre") == "Ana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioAñadirNombre");
    }

    /**
     * TODO extra 6: Comprueba si el template con parámetros nombrados está instanciado.
     */
    public static boolean desafioNamedTemplateActivo(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template) {
        // GUÍA: una línea — return template != null;
        // OJO: el test pasa un NamedParameterJdbcTemplate real (true) y null (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioNamedTemplateActivo");
    }

    /**
     * TODO extra 7: Lanza una excepción si el nombre es nulo o vacío en parámetros.
     */
    public static void desafioValidarNombreParam(String nombre) {
        // GUÍA: validación defensiva (teoría 1.9).
        // if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre inválido");
        // OJO: el test espera IllegalArgumentException con null y NINGUNA con "Ana".
        //      isBlank() también caza cadenas solo-espacios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarNombreParam");
    }

    /**
     * TODO extra 8: Comprueba si un mapa de parámetros contiene una clave específica.
     */
    public static boolean desafioContieneParametro(java.util.Map<String, ?> params, String clave) {
        // GUÍA: una línea — return params.containsKey(clave);
        // OJO: el test con Map.of("id", 1) espera true para "id" y false para "nombre".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioContieneParametro");
    }

    /**
     * TODO extra 9: Crea un Map representativo con los valores :id y :nombre de forma simple.
     */
    public static java.util.Map<String, Object> desafioCrearMapParametros(int id, String nombre) {
        // GUÍA: teoría 11.10 — alternativa a MapSqlParameterSource: un Map normal.
        // Una línea: return java.util.Map.of("id", id, "nombre", nombre);
        // OJO: el test comprueba map.get("id")==1 y map.get("nombre")=="Ana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioCrearMapParametros");
    }

    /**
     * TODO extra 10: Retorna verdadero si el total de parámetros ingresados en un source es correcto (por ejemplo, mayor que cero).
     */
    public static boolean desafioTieneParametros(org.springframework.jdbc.core.namedparam.MapSqlParameterSource source) {
        // GUÍA: teoría 11.10 — getValues() devuelve el Map interno de parámetros.
        // Una línea: return source != null && source.getValues().size() > 0;
        // OJO: el test crea un source con un parámetro y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioTieneParametros");
    }

}
