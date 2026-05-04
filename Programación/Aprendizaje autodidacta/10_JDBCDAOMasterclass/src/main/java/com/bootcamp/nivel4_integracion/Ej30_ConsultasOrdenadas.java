package com.bootcamp.nivel4_integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej30 — Consultas ordenadas con ORDER BY
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: saber cuándo y cómo usar ORDER BY en consultas JDBC.
 * La regla clave: ORDER BY va en el SQL estático, NUNCA como parámetro ?.
 * El ? solo sirve para valores de datos (WHERE campo = ?), no para palabras
 * clave SQL ni nombres de columna.
 *
 * Correcto:
 *   String sql = "SELECT * FROM Productos ORDER BY precio ASC";
 *
 * Incorrecto (no funciona):
 *   String sql = "SELECT * FROM Productos ORDER BY ?";
 */
public class Ej30_ConsultasOrdenadas {

    // ── Entidad ───────────────────────────────────────────────────────────

    public static class Producto {
        private final int id;
        private final String nombre;
        private final String categoria;
        private final double precio;

        public Producto(String nombre, String categoria, double precio) {
            this(0, nombre, categoria, precio);
        }

        public Producto(int id, String nombre, String categoria, double precio) {
            this.id = id; this.nombre = nombre;
            this.categoria = categoria; this.precio = precio;
        }

        public int getId()          { return id; }
        public String getNombre()   { return nombre; }
        public String getCategoria(){ return categoria; }
        public double getPrecio()   { return precio; }

        @Override
        public String toString() { return id + " | " + nombre + " | " + categoria + " | " + precio; }
    }

    // ── Singleton ─────────────────────────────────────────────────────────

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej30.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAO ───────────────────────────────────────────────────────────────

    public static class DAOProducto {

        /**
         * Crea la tabla Productos si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Productos (
                            id        INTEGER PRIMARY KEY AUTOINCREMENT,
                            nombre    TEXT NOT NULL,
                            categoria TEXT,
                            precio    REAL
                        )""");
            }
        }

        /**
         * Inserta un producto en la tabla.
         *
         * @param p producto a insertar
         * @return true si fue insertado, false si no
         * @throws SQLException si la inserción falla
         */
        public boolean insertar(Producto p) throws SQLException {
            String sql = "INSERT INTO Productos (nombre, categoria, precio) VALUES (?, ?, ?)";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setString(1, p.getNombre());
                pst.setString(2, p.getCategoria());
                pst.setDouble(3, p.getPrecio());
                return pst.executeUpdate() > 0;
            }
        }

        /**
         * Devuelve todos los productos ordenados por precio de menor a mayor.
         *
         * @return lista de productos ordenados por precio ASC; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Producto> obtenerOrdenadosPorPrecioAsc() throws SQLException {
            String sql = "SELECT id, nombre, categoria, precio FROM Productos ORDER BY precio ASC";
            List<Producto> lista = new ArrayList<>();
            // TODO 1: Statement con try-with-resources.
            //         executeQuery(sql) → while(rs.next()) → new Producto(id, nombre, categoria, precio) → lista.add.
            //         Devuelve lista.
            return lista;
        }

        /**
         * Devuelve todos los productos ordenados por precio de mayor a menor.
         *
         * @return lista de productos ordenados por precio DESC; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Producto> obtenerOrdenadosPorPrecioDesc() throws SQLException {
            String sql = "SELECT id, nombre, categoria, precio FROM Productos ORDER BY precio DESC";
            List<Producto> lista = new ArrayList<>();
            // TODO 2: Igual que TODO 1 pero con ORDER BY precio DESC.
            return lista;
        }

        /**
         * Devuelve los productos de una categoría dada, ordenados por nombre.
         * Combina WHERE (con ?) y ORDER BY (estático) en la misma query.
         *
         * @param categoria categoría por la que filtrar
         * @return lista de productos de esa categoría ordenados alfabéticamente; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Producto> obtenerPorCategoriaOrdenados(String categoria) throws SQLException {
            String sql = "SELECT id, nombre, categoria, precio FROM Productos WHERE categoria = ? ORDER BY nombre ASC";
            List<Producto> lista = new ArrayList<>();
            // TODO 3: PreparedStatement con try-with-resources.
            //         setString(1, categoria).
            //         executeQuery() → while(rs.next()) → new Producto(id, nombre, categoria, precio) → lista.add.
            //         Devuelve lista.
            return lista;
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOProducto dao = new DAOProducto();
        dao.crearTabla();

        dao.insertar(new Producto("Teclado",    "Periférico", 49.99));
        dao.insertar(new Producto("Monitor",    "Pantalla",   299.0));
        dao.insertar(new Producto("Ratón",      "Periférico",  19.99));
        dao.insertar(new Producto("Auriculares","Periférico",  89.0));
        dao.insertar(new Producto("Webcam",     "Periférico",  59.0));

        System.out.println("=== Productos baratos primero ===");
        dao.obtenerOrdenadosPorPrecioAsc().forEach(System.out::println);

        System.out.println("\n=== Productos caros primero ===");
        dao.obtenerOrdenadosPorPrecioDesc().forEach(System.out::println);

        System.out.println("\n=== Periféricos ordenados por nombre ===");
        dao.obtenerPorCategoriaOrdenados("Periférico").forEach(System.out::println);
    }
}
