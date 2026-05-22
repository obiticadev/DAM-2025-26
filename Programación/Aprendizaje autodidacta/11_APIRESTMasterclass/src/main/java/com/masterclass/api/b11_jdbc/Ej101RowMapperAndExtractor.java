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

    /**
     * TODO extra 1: Retorna la query SQL de selección para mapear con RowMapper.
     */
    public static String desafioObtenerSqlSelect() {
        return "SELECT id, nombre, precio FROM PRODUCTO ORDER BY id";
    }

    /**
     * TODO extra 2: Comprueba si un RowMapper está configurado de forma no nula.
     */
    public static boolean desafioRowMapperActivo(org.springframework.jdbc.core.RowMapper<Producto> mapper) {
        return mapper != null;
    }

    /**
     * TODO extra 3: Comprueba si un ResultSetExtractor está configurado de forma no nula.
     */
    public static boolean desafioResultSetExtractorActivo(org.springframework.jdbc.core.ResultSetExtractor<java.util.List<Producto>> extractor) {
        return extractor != null;
    }

    /**
     * TODO extra 4: Mapea una fila individual utilizando el RowMapper pasado como argumento.
     */
    public static Producto desafioMapearFilaConMapper(org.springframework.jdbc.core.RowMapper<Producto> mapper, java.sql.ResultSet rs, int numFila) throws java.sql.SQLException {
        return mapper.mapRow(rs, numFila);
    }

    /**
     * TODO extra 5: Extrae todos los productos utilizando el ResultSetExtractor.
     */
    public static java.util.List<Producto> desafioExtraerConExtractor(org.springframework.jdbc.core.ResultSetExtractor<java.util.List<Producto>> extractor, java.sql.ResultSet rs) throws java.sql.SQLException, org.springframework.dao.DataAccessException {
        return extractor.extractData(rs);
    }

    /**
     * TODO extra 6: Comprueba si una lista de productos contiene al menos un elemento con precio mayor que el valor indicado.
     */
    public static boolean desafioExisteProductoCaro(java.util.List<Producto> productos, double precioLimite) {
        return productos.stream().anyMatch(p -> p.precio() > precioLimite);
    }

    /**
     * TODO extra 7: Crea un Producto de prueba para testing.
     */
    public static Producto desafioCrearProductoDePrueba(int id, String nombre, double precio) {
        return new Producto(id, nombre, precio);
    }

    /**
     * TODO extra 8: Suma el precio de todos los productos de la lista de forma acumulada.
     */
    public static double desafioCalcularSumaPrecios(java.util.List<Producto> productos) {
        return productos.stream().mapToDouble(Producto::precio).sum();
    }

    /**
     * TODO extra 9: Retorna una lista con los nombres de todos los productos.
     */
    public static java.util.List<String> desafioObtenerNombresDeProductos(java.util.List<Producto> productos) {
        return productos.stream().map(Producto::nombre).toList();
    }

    /**
     * TODO extra 10: Valida que la descripción de un producto cumpla con el formato adecuado.
     */
    public static boolean desafioNombreFormatoValido(String nombre) {
        return nombre != null && nombre.length() >= 2;
    }

}
