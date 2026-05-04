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
 * Ej25 — DAO impl: actualizar(Entidad) → boolean
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar actualizar() en el DAO.
 * El método recibe un objeto Videojuego con el id ya asignado
 * (se obtuvo previamente de la BD) y actualiza todos sus campos.
 * El id se usa en el WHERE para localizar la fila exacta.
 *
 * Patrón exacto:
 *   UPDATE tabla SET campo1=?, campo2=? … WHERE id=?
 *   → set*(posicion, v.getCampo()) → set*(última, v.getId()) → executeUpdate → afectadas > 0
 *
 * Atención: el id siempre va en la ÚLTIMA posición del PreparedStatement
 * porque es el que va en el WHERE, que es lo último del SQL.
 */
public class Ej25_DAO_Actualizar {

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
    private static final String URL = "jdbc:sqlite:bootcamp_ej25.db";

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

        /**
         * Actualiza todos los campos del videojuego en la base de datos.
         * La fila a actualizar se localiza por v.getId().
         * Si no existe ninguna fila con ese id, executeUpdate devuelve 0.
         *
         * @param v videojuego con el id y los nuevos valores de todos los campos
         * @return true si la fila existía y fue actualizada, false si no existía
         * @throws SQLException si la actualización falla
         */
        public boolean actualizar(Videojuego v) throws SQLException {
            String sql = "UPDATE Videojuegos SET titulo=?, genero=?, anio=?, precio=? WHERE id=?";
            // TODO 1: Abre PreparedStatement con try-with-resources.
            //         Posición 1 → setString(1, v.getTitulo())
            //         Posición 2 → setString(2, v.getGenero())
            //         Posición 3 → setInt(3, v.getAnio())
            //         Posición 4 → setDouble(4, v.getPrecio())
            //         Posición 5 → setInt(5, v.getId())   ← el id va al final, es el WHERE
            //         Devuelve executeUpdate() > 0.
            return false;
        }

        // Stub para compilar
        public boolean eliminar(int id) throws SQLException { return false; }
        public List<Videojuego> obtenerTodos() throws SQLException { return new ArrayList<>(); }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOVideojuego dao = new DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: BOTW", "Aventura", 2017, 59.99));

        System.out.println("Antes: " + dao.obtenerPorId(1));

        // Creamos un objeto actualizado con el mismo id pero nuevos valores
        Videojuego actualizado = new Videojuego(1, "Zelda: TOTK", "Aventura", 2023, 69.99);
        System.out.println("Actualizar id=1: " + dao.actualizar(actualizado));
        System.out.println("Después: " + dao.obtenerPorId(1));

        Videojuego noExiste = new Videojuego(999, "Falso", "X", 2000, 0.0);
        System.out.println("Actualizar id=999 (no existe): " + dao.actualizar(noExiste));
    }
}
