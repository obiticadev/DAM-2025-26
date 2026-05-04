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
 * Ej24 — DAO impl: obtenerPorId(int) → Entidad o null
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar obtenerPorId() en el DAO.
 * La diferencia con obtenerTodos() es el if en vez del while:
 * solo puede haber una fila con ese id, y si no existe devolvemos null.
 *
 * Patrón exacto:
 *   WHERE id = ? → setInt → executeQuery → if(rs.next()) → new Entidad → else null
 */
public class Ej24_DAO_ObtenerPorId {

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
    private static final String URL = "jdbc:sqlite:bootcamp_ej24.db";

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

        /**
         * Busca el videojuego con el id indicado.
         *
         * @param id clave primaria del videojuego a buscar
         * @return el Videojuego encontrado, o null si no existe ninguno con ese id
         * @throws SQLException si la consulta falla
         */
        public Videojuego obtenerPorId(int id) throws SQLException {
            String sql = "SELECT id, titulo, genero, anio, precio FROM Videojuegos WHERE id = ?";
            // TODO 1: Abre PreparedStatement con try-with-resources.
            //         setInt(1, id).
            //         Ejecuta executeQuery().
            //         Usa if(rs.next()) — NO while.
            //         Si entra: construye y devuelve new Videojuego con el constructor de 5 parámetros.
            //         Si no entra: devuelve null.
            return null;
        }

        /**
         * Comprueba si existe un videojuego con el id indicado.
         *
         * @param id clave primaria a verificar
         * @return true si existe, false si no
         * @throws SQLException si la consulta falla
         */
        public boolean existe(int id) throws SQLException {
            // TODO 2: Llama a obtenerPorId(id).
            //         Devuelve true si no es null.
            return false;
        }

        // Stubs para compilar
        public boolean actualizar(Videojuego v) throws SQLException { return false; }
        public boolean eliminar(int id) throws SQLException { return false; }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOVideojuego dao = new DAOVideojuego();
        dao.crearTabla();
        dao.insertar(new Videojuego("Zelda: TOTK", "Aventura", 2023, 69.99));
        dao.insertar(new Videojuego("Elden Ring", "RPG", 2022, 59.99));

        System.out.println("id=1: " + dao.obtenerPorId(1));
        System.out.println("id=99: " + dao.obtenerPorId(99));
        System.out.println("¿existe id=1? " + dao.existe(1));
        System.out.println("¿existe id=99? " + dao.existe(99));
    }
}
