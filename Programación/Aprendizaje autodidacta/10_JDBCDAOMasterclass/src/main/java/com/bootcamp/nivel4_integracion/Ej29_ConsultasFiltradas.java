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
 * Ej29 — Consultas filtradas con WHERE campo LIKE ?
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: aprender a usar el parámetro ? con LIKE para búsquedas parciales.
 * La clave: el comodín % va dentro del valor que pasas a setString, NO en el SQL.
 *
 * Patrón:
 *   String sql = "SELECT ... FROM Libros WHERE titulo LIKE ?";
 *   pst.setString(1, "%" + fragmento + "%");
 */
public class Ej29_ConsultasFiltradas {

    // ── Entidad ───────────────────────────────────────────────────────────

    public static class Libro {
        private final int id;
        private final String titulo;
        private final String autor;
        private final String genero;

        public Libro(String titulo, String autor, String genero) {
            this(0, titulo, autor, genero);
        }

        public Libro(int id, String titulo, String autor, String genero) {
            this.id = id; this.titulo = titulo;
            this.autor = autor; this.genero = genero;
        }

        public int getId()       { return id; }
        public String getTitulo(){ return titulo; }
        public String getAutor() { return autor; }
        public String getGenero(){ return genero; }

        @Override
        public String toString() { return id + " | " + titulo + " | " + autor + " | " + genero; }
    }

    // ── Singleton ─────────────────────────────────────────────────────────

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej29.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAO ───────────────────────────────────────────────────────────────

    public static class DAOLibro {

        /**
         * Crea la tabla Libros si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Libros (
                            id     INTEGER PRIMARY KEY AUTOINCREMENT,
                            titulo TEXT NOT NULL,
                            autor  TEXT,
                            genero TEXT
                        )""");
            }
        }

        /**
         * Inserta un libro en la tabla.
         *
         * @param libro libro a insertar
         * @return true si fue insertado, false si no
         * @throws SQLException si la inserción falla
         */
        public boolean insertar(Libro libro) throws SQLException {
            String sql = "INSERT INTO Libros (titulo, autor, genero) VALUES (?, ?, ?)";
            try (PreparedStatement pst = getConexion().prepareStatement(sql)) {
                pst.setString(1, libro.getTitulo());
                pst.setString(2, libro.getAutor());
                pst.setString(3, libro.getGenero());
                return pst.executeUpdate() > 0;
            }
        }

        /**
         * Devuelve todos los libros cuyo título contiene el fragmento dado.
         * Usa {@code LIKE '%fragmento%'} con PreparedStatement.
         *
         * @param fragmento texto parcial a buscar en el título
         * @return lista de libros coincidentes; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Libro> buscarPorTitulo(String fragmento) throws SQLException {
            String sql = "SELECT id, titulo, autor, genero FROM Libros WHERE titulo LIKE ?";
            List<Libro> lista = new ArrayList<>();
            // TODO 1: PreparedStatement con try-with-resources.
            //         setString(1, "%" + fragmento + "%").
            //         executeQuery() → while(rs.next()) → new Libro(id, titulo, autor, genero) → lista.add.
            //         Devuelve lista.
            return lista;
        }

        /**
         * Devuelve todos los libros de un género concreto (coincidencia exacta).
         *
         * @param genero género por el que filtrar
         * @return lista de libros de ese género; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Libro> filtrarPorGenero(String genero) throws SQLException {
            String sql = "SELECT id, titulo, autor, genero FROM Libros WHERE genero = ?";
            List<Libro> lista = new ArrayList<>();
            // TODO 2: PreparedStatement con try-with-resources.
            //         setString(1, genero).
            //         executeQuery() → while(rs.next()) → new Libro(id, titulo, autor, genero) → lista.add.
            //         Devuelve lista.
            return lista;
        }

        /**
         * Devuelve todos los libros cuyo autor contiene el fragmento dado.
         *
         * @param fragmento texto parcial a buscar en el autor
         * @return lista de libros coincidentes; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Libro> buscarPorAutor(String fragmento) throws SQLException {
            String sql = "SELECT id, titulo, autor, genero FROM Libros WHERE autor LIKE ?";
            List<Libro> lista = new ArrayList<>();
            // TODO 3: Igual que buscarPorTitulo pero filtrando por autor.
            //         setString(1, "%" + fragmento + "%").
            return lista;
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOLibro dao = new DAOLibro();
        dao.crearTabla();

        dao.insertar(new Libro("El Quijote",          "Cervantes",    "Clásico"));
        dao.insertar(new Libro("Quijote Moderno",      "Anónimo",     "Ficción"));
        dao.insertar(new Libro("Cien años de soledad", "García Márquez", "Realismo mágico"));
        dao.insertar(new Libro("El amor en los tiempos del cólera", "García Márquez", "Romance"));

        System.out.println("=== Libros con 'Quijote' en el título ===");
        dao.buscarPorTitulo("Quijote").forEach(System.out::println);

        System.out.println("\n=== Libros de García Márquez ===");
        dao.buscarPorAutor("García Márquez").forEach(System.out::println);

        System.out.println("\n=== Libros de género Clásico ===");
        dao.filtrarPorGenero("Clásico").forEach(System.out::println);
    }
}
