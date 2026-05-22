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

    /**
     * TODO extra 1: Retorna el SQL para insertar una tarea.
     */
    public static String desafioObtenerSqlInsert() {
        return "INSERT INTO TAREA(id, descripcion, completada) VALUES (?, ?, ?)";
    }

    /**
     * TODO extra 2: Retorna el SQL para buscar por ID.
     */
    public static String desafioObtenerSqlSelectById() {
        return "SELECT id, descripcion, completada FROM TAREA WHERE id = ?";
    }

    /**
     * TODO extra 3: Retorna el SQL para contar las tareas totales.
     */
    public static String desafioObtenerSqlCount() {
        return "SELECT COUNT(*) FROM TAREA";
    }

    /**
     * TODO extra 4: Inserta una tarea de forma segura con JdbcTemplate y sus parámetros.
     */
    public static int desafioInsertarConTemplate(org.springframework.jdbc.core.JdbcTemplate template, int id, String desc, boolean comp) {
        return template.update(desafioObtenerSqlInsert(), id, desc, comp);
    }

    /**
     * TODO extra 5: Comprueba si JdbcTemplate no es nulo.
     */
    public static boolean desafioTemplateActivo(org.springframework.jdbc.core.JdbcTemplate template) {
        return template != null;
    }

    /**
     * TODO extra 6: Cuenta las tareas de forma directa con queryForObject.
     */
    public static int desafioContarTareasConTemplate(org.springframework.jdbc.core.JdbcTemplate template) {
        Integer count = template.queryForObject(desafioObtenerSqlCount(), Integer.class);
        return count == null ? 0 : count;
    }

    /**
     * TODO extra 7: Lanza una excepción si el ID de tarea es inválido.
     */
    public static void desafioValidarId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID debe ser mayor que cero");
        }
    }

    /**
     * TODO extra 8: Comprueba si el conteo obtenido es positivo.
     */
    public static boolean desafioTieneTareas(int count) {
        return count > 0;
    }

    /**
     * TODO extra 9: Retorna un objeto descriptivo con la información básica de la tarea.
     */
    public static Object[] desafioPreconfigurarParametros(int id, String desc, boolean comp) {
        return new Object[] {id, desc, comp};
    }

    /**
     * TODO extra 10: Retorna verdadero si la tarea tiene una descripción no vacía.
     */
    public static boolean desafioDescripcionValida(String desc) {
        return desc != null && !desc.isBlank();
    }

}
