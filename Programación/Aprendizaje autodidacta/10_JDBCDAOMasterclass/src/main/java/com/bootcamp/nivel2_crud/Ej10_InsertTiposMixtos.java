package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej10 — INSERT con tipos mixtos (String, int, double)
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar el binding de distintos tipos Java a PreparedStatement.
 * Cada tipo tiene su propio método set*() — la clave es usar el correcto.
 */
public class Ej10_InsertTiposMixtos {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej10.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return instance;
    }

    /**
     * Crea la tabla "Productos" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL,
     *           precio REAL NOT NULL, stock INTEGER NOT NULL.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaProductos() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Productos (
                    id     INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT    NOT NULL,
                    precio REAL    NOT NULL,
                    stock  INTEGER NOT NULL
                )""";
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Inserta un producto en la tabla Productos.
     * Usa setString para nombre, setDouble para precio y setInt para stock.
     *
     * @param nombre nombre del producto
     * @param precio precio unitario (puede tener decimales)
     * @param stock  unidades disponibles
     * @return true si la fila fue insertada correctamente, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarProducto(String nombre, double precio, int stock) throws SQLException {
        String sql = "INSERT INTO Productos (nombre, precio, stock) VALUES (?, ?, ?)";
        // TODO 1: Abre un PreparedStatement con try-with-resources.
        //         Posición 1 → pst.setString para nombre.
        //         Posición 2 → pst.setDouble para precio.
        //         Posición 3 → pst.setInt para stock.
        //         Ejecuta executeUpdate() y devuelve afectadas > 0.
        return false;
    }

    /**
     * Inserta varios productos de golpe llamando a insertarProducto() en un bucle.
     * Recibe un array de Object[] donde cada elemento es {nombre, precio, stock}.
     *
     * @param productos array de arrays; cada elemento es [nombre, precio, stock]
     * @return número de inserciones que devolvieron true
     * @throws SQLException si alguna inserción falla
     */
    public static int insertarVariosProductos(Object[][] productos) throws SQLException {
        // TODO 2: Recorre el array productos.
        //         Por cada elemento extrae: (String) p[0], (Double) p[1], (Integer) p[2].
        //         Llama a insertarProducto() y acumula en un contador los true devueltos.
        //         Devuelve el contador al final.
        return 0;
    }

    /**
     * Devuelve el stock total sumando todos los productos de la tabla.
     *
     * @return suma de la columna stock de todos los registros
     * @throws SQLException si la consulta falla
     */
    public static int stockTotal() throws SQLException {
        String sql = "SELECT SUM(stock) FROM Productos";
        // TODO 3: Ejecuta la query con Statement y try-with-resources.
        //         Lee rs.getInt(1) — SUM devuelve una sola fila.
        //         Devuelve ese valor.
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        crearTablaProductos();

        System.out.println("Insertando Teclado: " + insertarProducto("Teclado", 49.99, 100));
        System.out.println("Insertando Monitor: " + insertarProducto("Monitor", 299.00, 25));

        Object[][] lote = {
            {"Ratón", 19.99, 200},
            {"Auriculares", 89.50, 50}
        };
        System.out.println("Insertados en lote: " + insertarVariosProductos(lote));
        System.out.println("Stock total: " + stockTotal());
    }
}
