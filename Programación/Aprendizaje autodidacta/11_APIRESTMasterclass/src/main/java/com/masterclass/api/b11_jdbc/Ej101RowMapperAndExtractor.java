package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.List;

/**
 * Ejercicio 101 · RowMapper y ResultSetExtractor.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.4).
 *
 * <p>Tabla LIBRO(id INT, titulo VARCHAR, paginas INT) (la crea el test).
 */
public final class Ej101RowMapperAndExtractor {

    public record Libro(int id, String titulo, int paginas) {
    }

    private final JdbcTemplate jdbc;

    public Ej101RowMapperAndExtractor(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }

    /**
     * RowMapper que convierte una fila en Libro.
     *
     * @return el RowMapper&lt;Libro&gt;
     */
    public static RowMapper<Libro> libroMapper() {
        // TODO 1: devuelve una lambda (rs, rowNum) -> ...
        // TODO 2: lee rs.getInt("id").
        // TODO 3: lee rs.getString("titulo").
        // TODO 4: lee rs.getInt("paginas").
        // TODO 5: construye y devuelve un Libro con esos valores.
        return null;
    }

    /**
     * Lista todos los libros usando el RowMapper.
     *
     * @return lista de libros ordenada por id
     */
    public List<Libro> listar() {
        // TODO 6: usa jdbc.query("SELECT ... ORDER BY id", libroMapper()).
        // TODO 7: devuelve la lista resultante.
        return List.of();
    }

    /**
     * Suma total de páginas usando un extractor/agregado.
     *
     * @return suma de la columna paginas (0 si no hay filas)
     */
    public int totalPaginas() {
        // TODO 8: puedes usar SQL "SELECT COALESCE(SUM(paginas),0) FROM LIBRO".
        // TODO 9: queryForObject(..., Integer.class) para extraer el agregado.
        // TODO 10: devuelve el entero (COALESCE garantiza 0 si está vacío).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con H2 en memoria");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: devuelve una lambda (rs, rowNum) -> ...
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: lee rs.getInt("id").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: lee rs.getString("titulo").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: lee rs.getInt("paginas").
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: construye y devuelve un Libro con esos valores.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa jdbc.query("SELECT ... ORDER BY id", libroMapper()).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: devuelve la lista resultante.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: puedes usar SQL "SELECT COALESCE(SUM(paginas),0) FROM LIBRO".
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: queryForObject(..., Integer.class) para extraer el agregado.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el entero (COALESCE garantiza 0 si está vacío).
    }

}
