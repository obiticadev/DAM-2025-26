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
 * Ej22 — DAO impl: insertar(Entidad) → boolean
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar insertar() en el DAO.
 * El método recibe un objeto Videojuego, extrae sus campos con getters
 * y los bind al PreparedStatement. El id NO se inserta (lo asigna la BD).
 *
 * Patrón exacto:
 *   prepareStatement(sql) → set*(1, v.getCampo()) × N → executeUpdate() → afectadas > 0
 */
public class Ej22_DAO_Insertar {

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
    private static final String URL = "jdbc:sqlite:bootcamp_ej22.db";

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
                            id     INTEGER PRIMARY KEY AUTOINCREMENT,
                            titulo TEXT NOT NULL,
                            genero TEXT,
                            anio   INTEGER,
                            precio REAL
                        )""");
            }
        }

        /**
         * Inserta un nuevo videojuego en la tabla.
         * El id del objeto se ignora — la BD asigna uno con AUTOINCREMENT.
         *
         * @param v objeto Videojuego con los datos a insertar
         * @return true si la fila fue insertada, false si no
         * @throws SQLException si la inserción falla
         */
        public boolean insertar(Videojuego v) throws SQLException {
            String sql = "INSERT INTO Videojuegos (titulo, genero, anio, precio) VALUES (?, ?, ?, ?)";
            // TODO 1: Abre PreparedStatement con try-with-resources.
            //         Posición 1 → setString(1, v.getTitulo())
            //         Posición 2 → setString(2, v.getGenero())
            //         Posición 3 → setInt(3, v.getAnio())
            //         Posición 4 → setDouble(4, v.getPrecio())
            //         Guarda executeUpdate() en int afectadas.
            //         Devuelve afectadas > 0.
            return false;
        }

        /**
         * Cuenta cuántos videojuegos hay en la tabla.
         *
         * @return número de filas en la tabla Videojuegos
         * @throws SQLException si la consulta falla
         */
        public int contar() throws SQLException {
            // TODO 2: SELECT COUNT(*) FROM Videojuegos con Statement.
            //         Devuelve rs.getInt(1).
            return 0;
        }

        // Stubs para compilar — se implementarán en ejercicios siguientes
        public List<Videojuego> obtenerTodos() throws SQLException { return new ArrayList<>(); }
        public Videojuego obtenerPorId(int id) throws SQLException { return null; }
        public boolean actualizar(Videojuego v) throws SQLException { return false; }
        public boolean eliminar(int id) throws SQLException { return false; }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOVideojuego dao = new DAOVideojuego();
        dao.crearTabla();

        System.out.println("Antes: " + dao.contar());
        System.out.println("Insertar Zelda: " + dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99)));
        System.out.println("Insertar Elden Ring: " + dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99)));
        System.out.println("Después: " + dao.contar());
    }
}
