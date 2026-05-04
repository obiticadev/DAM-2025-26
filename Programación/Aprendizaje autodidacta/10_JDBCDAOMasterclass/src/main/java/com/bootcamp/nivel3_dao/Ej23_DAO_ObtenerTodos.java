package com.bootcamp.nivel3_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej23 — DAO impl: obtenerTodos() → List&lt;Entidad&gt;
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar obtenerTodos() en el DAO.
 * El método ejecuta un SELECT sin filtros y convierte cada fila
 * del ResultSet en un objeto Videojuego usando sus getters inversos (rs.get*).
 *
 * Patrón exacto:
 *   executeQuery() → while(rs.next()) → new Entidad(rs.get*…) → lista.add → return lista
 */
public class Ej23_DAO_ObtenerTodos {

    public static class Videojuego {
        private final int id;
        private final String titulo;
        private final String genero;
        private final int anio;
        private final double precio;

        public Videojuego(String titulo, String genero, int anio, double precio) {
            this(0, titulo, genero, anio, precio);
        }

        public Videojuego(int id, String titulo, String genero, int anio, double precio) {
            this.id = id; this.titulo = titulo; this.genero = genero;
            this.anio = anio; this.precio = precio;
        }

        public int getId()        { return id; }
        public String getTitulo() { return titulo; }
        public String getGenero() { return genero; }
        public int getAnio()      { return anio; }
        public double getPrecio() { return precio; }

        @Override
        public String toString() {
            return id + " | " + titulo + " | " + genero + " | " + anio + " | " + precio;
        }
    }

    // ── Singleton ─────────────────────────────────────────────────────────
    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej23.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAO ───────────────────────────────────────────────────────────────
    public static class DAOVideojuego {

        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Videojuegos (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            titulo TEXT NOT NULL, genero TEXT,
                            anio INTEGER, precio REAL)""");
            }
        }

        public boolean insertar(Videojuego v) throws SQLException {
            String sql = "INSERT INTO Videojuegos (titulo, genero, anio, precio) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setString(1, v.getTitulo()); pst.setString(2, v.getGenero());
                pst.setInt(3, v.getAnio());      pst.setDouble(4, v.getPrecio());
                return pst.executeUpdate() > 0;
            }
        }

        /**
         * Devuelve todos los videojuegos de la tabla como lista.
         * Construye cada objeto leyendo las columnas por nombre del ResultSet.
         *
         * @return lista con todos los videojuegos; vacía si no hay datos
         * @throws SQLException si la consulta falla
         */
        public List<Videojuego> obtenerTodos() throws SQLException {
            String sql = "SELECT id, titulo, genero, anio, precio FROM Videojuegos";
            List<Videojuego> lista = new ArrayList<>();
            // TODO 1: Usa try-with-resources con PreparedStatement (o Statement, sin parámetros).
            //         En el while(rs.next()):
            //           Lee rs.getInt("id"), rs.getString("titulo"), rs.getString("genero"),
            //               rs.getInt("anio"), rs.getDouble("precio")
            //           Crea new Videojuego(id, titulo, genero, anio, precio) — usa el constructor CON id.
            //           Añade el objeto a lista.
            //         Devuelve lista.
            return lista;
        }

        /**
         * Devuelve solo los videojuegos del género indicado.
         *
         * @param genero género por el que filtrar
         * @return lista de videojuegos de ese género; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Videojuego> obtenerPorGenero(String genero) throws SQLException {
            String sql = "SELECT id, titulo, genero, anio, precio FROM Videojuegos WHERE genero = ?";
            List<Videojuego> lista = new ArrayList<>();
            // TODO 2: Igual que obtenerTodos() pero con PreparedStatement y setString(1, genero).
            //         Devuelve lista.
            return lista;
        }

        // Stubs para compilar
        public Videojuego obtenerPorId(int id) throws SQLException { return null; }
        public boolean actualizar(Videojuego v) throws SQLException { return false; }
        public boolean eliminar(int id) throws SQLException { return false; }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOVideojuego dao = new DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
        dao.insertar(new Videojuego("God of War", "Aventura", 2022, 49.99));

        System.out.println("=== Todos ===");
        dao.obtenerTodos().forEach(System.out::println);

        System.out.println("\n=== Género Aventura ===");
        dao.obtenerPorGenero("Aventura").forEach(System.out::println);
    }
}
