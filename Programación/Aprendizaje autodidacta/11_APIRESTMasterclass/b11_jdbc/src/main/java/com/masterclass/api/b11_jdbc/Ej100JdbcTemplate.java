package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * Ejercicio 100 · JdbcTemplate (adiós al boilerplate).
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.8).
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
        // GUÍA: teoría 11.8 — JdbcTemplate.update usa ? igual que un PreparedStatement.
        // Una línea: return "INSERT INTO TAREA(id, descripcion, completada) VALUES (?, ?, ?)";
        // OJO: el test compara con equals — tres columnas (id, descripcion, completada),
        //      espacios tras las comas y VALUES (?, ?, ?). Cópialo exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlInsert");
    }

    /**
     * TODO extra 2: Retorna el SQL para buscar por ID.
     */
    public static String desafioObtenerSqlSelectById() {
        // GUÍA: teoría 11.8. Una línea:
        // return "SELECT id, descripcion, completada FROM TAREA WHERE id = ?";
        // OJO: respeta espacios tras comas y alrededor del '=' ("WHERE id = ?").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlSelectById");
    }

    /**
     * TODO extra 3: Retorna el SQL para contar las tareas totales.
     */
    public static String desafioObtenerSqlCount() {
        // GUÍA: teoría 11.8. Una línea: return "SELECT COUNT(*) FROM TAREA";
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlCount");
    }

    /**
     * TODO extra 4: Inserta una tarea de forma segura con JdbcTemplate y sus parámetros.
     */
    public static int desafioInsertarConTemplate(org.springframework.jdbc.core.JdbcTemplate template, int id, String desc, boolean comp) {
        // GUÍA: teoría 11.8 — update(sql, args...) recibe los parámetros como varargs.
        // return template.update(desafioObtenerSqlInsert(), id, desc, comp);
        // OJO: reutiliza el reto 1 para el SQL; el test prepara la tabla TAREA(id,
        //      descripcion, completada BOOLEAN) y espera 1 fila insertada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioInsertarConTemplate");
    }

    /**
     * TODO extra 5: Comprueba si JdbcTemplate no es nulo.
     */
    public static boolean desafioTemplateActivo(org.springframework.jdbc.core.JdbcTemplate template) {
        // GUÍA: una línea — return template != null;
        // OJO: el test pasa un new JdbcTemplate() (true) y null (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioTemplateActivo");
    }

    /**
     * TODO extra 6: Cuenta las tareas de forma directa con queryForObject.
     */
    public static int desafioContarTareasConTemplate(org.springframework.jdbc.core.JdbcTemplate template) {
        // GUÍA: teoría 11.8 — queryForObject para un agregado (COUNT).
        // 1. Integer count = template.queryForObject(desafioObtenerSqlCount(), Integer.class);
        // 2. return count == null ? 0 : count;
        // OJO: el test inserta 1 fila y espera 1. El null-check es defensa (COUNT siempre da fila).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioContarTareasConTemplate");
    }

    /**
     * TODO extra 7: Lanza una excepción si el ID de tarea es inválido.
     */
    public static void desafioValidarId(int id) {
        // GUÍA: validación defensiva (teoría 1.9).
        // if (id <= 0) throw new IllegalArgumentException("ID debe ser mayor que cero");
        // OJO: el test espera la excepción con id=0 y NINGUNA con id=1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioValidarId");
    }

    /**
     * TODO extra 8: Comprueba si el conteo obtenido es positivo.
     */
    public static boolean desafioTieneTareas(int count) {
        // GUÍA: una línea — return count > 0;
        // OJO: el test manda 5 (true) y 0 (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioTieneTareas");
    }

    /**
     * TODO extra 9: Retorna un objeto descriptivo con la información básica de la tarea.
     */
    public static Object[] desafioPreconfigurarParametros(int id, String desc, boolean comp) {
        // GUÍA: empaqueta los args de update en un array (teoría 11.8: update acepta Object...).
        // Una línea: return new Object[] {id, desc, comp};
        // OJO: el test compara con assertArrayEquals(new Object[]{1, "d", true}, ...):
        //      el orden id, desc, comp es el que evalúa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioPreconfigurarParametros");
    }

    /**
     * TODO extra 10: Retorna verdadero si la tarea tiene una descripción no vacía.
     */
    public static boolean desafioDescripcionValida(String desc) {
        // GUÍA: una línea — return desc != null && !desc.isBlank();
        // OJO: el test manda "a" (true) y " " (un espacio → false): isBlank() también
        //      detecta cadenas solo-espacios, a diferencia de isEmpty().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioDescripcionValida");
    }

}
