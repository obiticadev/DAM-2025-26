package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.List;

/**
 * Ejercicio 101 · RowMapper y ResultSetExtractor.
 *
 * <p>Teoría: {@code teoria/11_JDBC_Profundo.md} (sección 11.9).
 *
 * <p>Tabla LIBRO(id INT, titulo VARCHAR, paginas INT) (la crea el test). Los
 * retos extra reutilizan el modelo {@link Producto} y las clases de apoyo
 * {@link ProductoRowMapper} y {@link ProductoExtractor}.
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
        // GUÍA: teoría 11.9 — el SQL que alimenta al RowMapper de Producto.
        // Una línea: return "SELECT id, nombre, precio FROM PRODUCTO ORDER BY id";
        // OJO: el test compara con equals — fíjate en los espacios tras las comas
        //      (distinto del Ej095, que NO los lleva). Cópialo literal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerSqlSelect");
    }

    /**
     * TODO extra 2: Comprueba si un RowMapper está configurado de forma no nula.
     */
    public static boolean desafioRowMapperActivo(org.springframework.jdbc.core.RowMapper<Producto> mapper) {
        // GUÍA: una línea — return mapper != null;
        // OJO: el test pasa una lambda (rs, rowNum) -> null (true) y null (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioRowMapperActivo");
    }

    /**
     * TODO extra 3: Comprueba si un ResultSetExtractor está configurado de forma no nula.
     */
    public static boolean desafioResultSetExtractorActivo(org.springframework.jdbc.core.ResultSetExtractor<java.util.List<Producto>> extractor) {
        // GUÍA: una línea — return extractor != null;
        // OJO: el test pasa una lambda rs -> List.of() (true) y null (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioResultSetExtractorActivo");
    }

    /**
     * TODO extra 4: Mapea una fila individual utilizando el RowMapper pasado como argumento.
     */
    public static Producto desafioMapearFilaConMapper(org.springframework.jdbc.core.RowMapper<Producto> mapper, java.sql.ResultSet rs, int numFila) throws java.sql.SQLException {
        // GUÍA: teoría 11.9 — un RowMapper se invoca con mapRow(rs, rowNum).
        // Una línea: return mapper.mapRow(rs, numFila);
        // OJO: el test pasa un new ProductoRowMapper() sobre una fila con id=1 y espera p.id()=1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioMapearFilaConMapper");
    }

    /**
     * TODO extra 5: Extrae todos los productos utilizando el ResultSetExtractor.
     */
    public static java.util.List<Producto> desafioExtraerConExtractor(org.springframework.jdbc.core.ResultSetExtractor<java.util.List<Producto>> extractor, java.sql.ResultSet rs) throws java.sql.SQLException, org.springframework.dao.DataAccessException {
        // GUÍA: teoría 11.9 — un ResultSetExtractor recibe el ResultSet ENTERO.
        // Una línea: return extractor.extractData(rs);
        // OJO: el test pasa un new ProductoExtractor() sobre una tabla de 1 fila y espera list.size()=1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioExtraerConExtractor");
    }

    /**
     * TODO extra 6: Comprueba si una lista de productos contiene al menos un elemento con precio mayor que el valor indicado.
     */
    public static boolean desafioExisteProductoCaro(java.util.List<Producto> productos, double precioLimite) {
        // GUÍA: streams (teoría 1.3) — anyMatch con un predicado sobre el precio.
        // return productos.stream().anyMatch(p -> p.precio() > precioLimite);
        // OJO: el test con [Producto(precio 100)] espera true para límite 50 y false
        //      para límite 150 (es '>' ESTRICTO, no '>=').
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioExisteProductoCaro");
    }

    /**
     * TODO extra 7: Crea un Producto de prueba para testing.
     */
    public static Producto desafioCrearProductoDePrueba(int id, String nombre, double precio) {
        // GUÍA: teoría 1.1 (record). Una línea: return new Producto(id, nombre, precio);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioCrearProductoDePrueba");
    }

    /**
     * TODO extra 8: Suma el precio de todos los productos de la lista de forma acumulada.
     */
    public static double desafioCalcularSumaPrecios(java.util.List<Producto> productos) {
        // GUÍA: streams numéricos (teoría 1.3) — mapToDouble + sum.
        // return productos.stream().mapToDouble(Producto::precio).sum();
        // OJO: el test con precios 10.0 y 20.0 espera 30.0 (delta 0.001).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioCalcularSumaPrecios");
    }

    /**
     * TODO extra 9: Retorna una lista con los nombres de todos los productos.
     */
    public static java.util.List<String> desafioObtenerNombresDeProductos(java.util.List<Producto> productos) {
        // GUÍA: streams (teoría 1.3) — map + toList.
        // return productos.stream().map(Producto::nombre).toList();
        // OJO: el test con [Producto(nombre "A")] espera List.of("A").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioObtenerNombresDeProductos");
    }

    /**
     * TODO extra 10: Valida que la descripción de un producto cumpla con el formato adecuado.
     */
    public static boolean desafioNombreFormatoValido(String nombre) {
        // GUÍA: una línea — return nombre != null && nombre.length() >= 2;
        // OJO: el test manda "Ok" (length 2 → true) y "x" (length 1 → false): es '>=' 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desafioNombreFormatoValido");
    }

}
