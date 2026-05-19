package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * Ejercicio 100 · JdbcTemplate (adiós al boilerplate).
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.4).
 *
 * <p>Tabla TAREA(id INT, titulo VARCHAR) (la crea el test).
 */
public final class Ej100JdbcTemplate {

    private final JdbcTemplate jdbc;

    public Ej100JdbcTemplate(DataSource ds) {
        // TODO 1: crea el JdbcTemplate a partir del DataSource (new JdbcTemplate(ds)).
        this.jdbc = null;
    }

    /**
     * Inserta una tarea.
     *
     * @param id     id
     * @param titulo título
     * @return filas afectadas (1)
     */
    public int insertar(int id, String titulo) {
        // TODO 2: usa jdbc.update("INSERT INTO TAREA(id,titulo) VALUES (?,?)", id, titulo).
        // TODO 3: NO abras Connection ni PreparedStatement a mano (de eso se encarga).
        // TODO 4: devuelve el nº de filas que devuelve update().
        return -1;
    }

    /**
     * @return número total de tareas
     */
    public int contar() {
        // TODO 5: usa jdbc.queryForObject("SELECT COUNT(*) FROM TAREA", Integer.class).
        // TODO 6: maneja el posible null (no debería, COUNT siempre devuelve fila).
        // TODO 7: devuelve el entero.
        return -1;
    }

    /**
     * @param id id buscado
     * @return el título o null si no existe
     */
    public String tituloDe(int id) {
        // TODO 8: usa jdbc.query(...) o queryForObject con manejo de "no rows".
        // TODO 9: si no hay fila, devuelve null (captura EmptyResultDataAccessException).
        // TODO 10: si hay, devuelve el título.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }
}
