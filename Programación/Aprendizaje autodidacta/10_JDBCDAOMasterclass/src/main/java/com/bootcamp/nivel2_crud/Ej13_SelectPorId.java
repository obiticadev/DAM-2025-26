package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej13 — SELECT por id → objeto o null
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: dominar el patrón de búsqueda por clave primaria:
 * WHERE id = ? → executeQuery → if(rs.next()) → objeto, else null.
 * La diferencia con obtenerTodos() es el if en vez del while
 * (solo puede haber una fila con ese id).
 */
public class Ej13_SelectPorId {

    public static class Libro {
        public final int id;
        public final String titulo;
        public final String autor;
        public final int anio;

        public Libro(int id, String titulo, String autor, int anio) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.anio = anio;
        }

        @Override
        public String toString() {
            return id + " | " + titulo + " | " + autor + " | " + anio;
        }
    }

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej13.db";

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
     * Prepara la tabla Libros con datos de ejemplo para practicar.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Libros (
                        id     INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo TEXT NOT NULL,
                        autor  TEXT,
                        anio   INTEGER
                    )""");
        }
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Libros")) {
            if (rs.next() && rs.getInt(1) == 0) {
                String ins = "INSERT INTO Libros (titulo, autor, anio) VALUES (?, ?, ?)";
                Object[][] d = {
                    {"Clean Code", "Robert C. Martin", 2008},
                    {"The Pragmatic Programmer", "Andy Hunt", 1999},
                    {"Effective Java", "Joshua Bloch", 2018}
                };
                for (Object[] row : d) {
                    try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                        pst.setString(1, (String) row[0]);
                        pst.setString(2, (String) row[1]);
                        pst.setInt(3, (Integer) row[2]);
                        pst.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Busca un libro por su id.
     * Patrón: WHERE id = ? → if(rs.next()) → return new Libro(…) → else return null.
     *
     * @param id identificador del libro a buscar
     * @return el Libro encontrado, o null si no existe ningún libro con ese id
     * @throws SQLException si la consulta falla
     */
    public static Libro obtenerPorId(int id) throws SQLException {
        String sql = "SELECT id, titulo, autor, anio FROM Libros WHERE id = ?";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setInt para id.
        //         Ejecuta executeQuery().
        //         Usa if(rs.next()) — NO while, solo puede haber una fila.
        //         Si entra en el if: construye y devuelve new Libro(rs.getInt("id"), ...).
        //         Si no entra: devuelve null (libro no encontrado).
        return null;
    }

    /**
     * Comprueba si existe un libro con el id indicado.
     * No devuelve el objeto — solo confirma su existencia.
     *
     * @param id identificador a buscar
     * @return true si existe un libro con ese id, false si no
     * @throws SQLException si la consulta falla
     */
    public static boolean existe(int id) throws SQLException {
        // TODO 2: Llama a obtenerPorId(id).
        //         Devuelve true si el resultado no es null, false si es null.
        return false;
    }

    /**
     * Busca el primer libro cuyo título contenga el fragmento indicado (búsqueda parcial).
     * Usa LIKE con % a ambos lados del fragmento.
     *
     * @param fragmento texto a buscar dentro del título
     * @return el primer Libro cuyo título contiene el fragmento, o null si no hay coincidencia
     * @throws SQLException si la consulta falla
     */
    public static Libro buscarPorTitulo(String fragmento) throws SQLException {
        String sql = "SELECT id, titulo, autor, anio FROM Libros WHERE titulo LIKE ?";
        // TODO 3: Abre PreparedStatement con try-with-resources.
        //         El valor del parámetro debe ser "%" + fragmento + "%"
        //         para buscar el fragmento en cualquier posición del título.
        //         Usa if(rs.next()) para devolver el primer resultado o null.
        return null;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("Libro id=1: " + obtenerPorId(1));
        System.out.println("Libro id=99: " + obtenerPorId(99));

        System.out.println("¿Existe id=2? " + existe(2));
        System.out.println("¿Existe id=99? " + existe(99));

        System.out.println("Búsqueda 'Java': " + buscarPorTitulo("Java"));
        System.out.println("Búsqueda 'xyz': " + buscarPorTitulo("xyz"));
    }
}
