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
 * Ej26 — DAO impl: eliminar(int) → boolean
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar eliminar() en el DAO.
 * Es el método más corto del CRUD — solo un parámetro, solo un set*.
 *
 * Patrón exacto:
 *   DELETE FROM tabla WHERE id=? → setInt(1, id) → executeUpdate → afectadas > 0
 *
 * Con este ejercicio tienes los 6 métodos del DAO completos.
 * El siguiente es el SpeedRun: construyes todo desde cero.
 */
public class Ej26_DAO_Eliminar {

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
    private static final String URL = "jdbc:sqlite:bootcamp_ej26.db";

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

        public List<Videojuego> obtenerTodos() throws SQLException {
            List<Videojuego> lista = new ArrayList<>();
            try (Statement s = getConexion().createStatement();
                 ResultSet rs = s.executeQuery("SELECT id,titulo,genero,anio,precio FROM Videojuegos")) {
                while (rs.next())
                    lista.add(new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                            rs.getString("genero"), rs.getInt("anio"), rs.getDouble("precio")));
            }
            return lista;
        }

        public Videojuego obtenerPorId(int id) throws SQLException {
            String sql = "SELECT id,titulo,genero,anio,precio FROM Videojuegos WHERE id=?";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) return new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                            rs.getString("genero"), rs.getInt("anio"), rs.getDouble("precio"));
                }
            }
            return null;
        }

        public boolean actualizar(Videojuego v) throws SQLException {
            String sql = "UPDATE Videojuegos SET titulo=?, genero=?, anio=?, precio=? WHERE id=?";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setString(1, v.getTitulo()); pst.setString(2, v.getGenero());
                pst.setInt(3, v.getAnio());      pst.setDouble(4, v.getPrecio());
                pst.setInt(5, v.getId());
                return pst.executeUpdate() > 0;
            }
        }

        /**
         * Elimina el videojuego con el id indicado de la tabla.
         *
         * @param id clave primaria del videojuego a eliminar
         * @return true si el videojuego existía y fue eliminado, false si no existía
         * @throws SQLException si la operación falla
         */
        public boolean eliminar(int id) throws SQLException {
            String sql = "DELETE FROM Videojuegos WHERE id = ?";
            // TODO 1: Abre PreparedStatement con try-with-resources.
            //         setInt(1, id).
            //         Devuelve executeUpdate() > 0.
            //         Es el método más corto del DAO — una sola línea dentro del try.
            return false;
        }

        /**
         * Cuenta cuántos videojuegos hay actualmente en la tabla.
         *
         * @return número de filas en la tabla
         * @throws SQLException si la consulta falla
         */
        public int contar() throws SQLException {
            // TODO 2: SELECT COUNT(*) FROM Videojuegos con Statement.
            //         Devuelve rs.getInt(1).
            return 0;
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOVideojuego dao = new DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));
        dao.insertar(new Videojuego("Minecraft", "Sandbox", 2011, 26.99));

        System.out.println("Antes: " + dao.contar() + " videojuegos");
        System.out.println("Eliminar id=2: " + dao.eliminar(2));
        System.out.println("Después: " + dao.contar() + " videojuegos");
        System.out.println("Eliminar id=99 (no existe): " + dao.eliminar(99));
        System.out.println("Final: " + dao.contar() + " videojuegos");
    }
}
