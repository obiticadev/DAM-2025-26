package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Ej18 — Loop de inserts múltiples + conteo de éxitos
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar la inserción de una colección de objetos en bucle,
 * contando cuántos inserts tuvieron éxito. Es el patrón que usarás
 * para poblar tablas con datos de prueba o en lotes de importación.
 *
 * Después de este ejercicio tienes dominados INSERT, SELECT, UPDATE y DELETE.
 * Estás listo para encapsularlo en un DAO.
 */
public class Ej18_MultiplesInserts {

    public record Pelicula(String titulo, String director, int anio) {}

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej18.db";

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
     * Crea la tabla "Peliculas" si no existe.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaPeliculas() throws SQLException {
        try (Statement s = getConexion().createStatement()) {
            s.execute("""
                    CREATE TABLE IF NOT EXISTS Peliculas (
                        id       INTEGER PRIMARY KEY AUTOINCREMENT,
                        titulo   TEXT NOT NULL,
                        director TEXT,
                        anio     INTEGER
                    )""");
        }
    }

    /**
     * Inserta una sola película en la tabla.
     *
     * @param pelicula objeto Pelicula con los datos a insertar
     * @return true si la película fue insertada correctamente, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarPelicula(Pelicula pelicula) throws SQLException {
        String sql = "INSERT INTO Peliculas (titulo, director, anio) VALUES (?, ?, ?)";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setString para pelicula.titulo()
        //         Posición 2 → setString para pelicula.director()
        //         Posición 3 → setInt para pelicula.anio()
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Inserta una lista de películas en bucle y devuelve cuántas se insertaron con éxito.
     *
     * @param peliculas lista de películas a insertar
     * @return número de inserciones exitosas (aquellas donde insertarPelicula devolvió true)
     * @throws SQLException si alguna inserción falla
     */
    public static int insertarLote(List<Pelicula> peliculas) throws SQLException {
        // TODO 2: Recorre la lista con un for-each.
        //         Por cada película llama a insertarPelicula(p).
        //         Acumula en un contador los true devueltos.
        //         Devuelve el contador.
        return 0;
    }

    /**
     * Devuelve el número total de películas en la tabla.
     *
     * @return cantidad de filas en la tabla Peliculas
     * @throws SQLException si la consulta falla
     */
    public static int contarPeliculas() throws SQLException {
        // TODO 3: Usa Statement con SELECT COUNT(*) FROM Peliculas.
        //         Devuelve rs.getInt(1).
        return 0;
    }

    /**
     * Devuelve el número de películas de un director concreto.
     *
     * @param director nombre del director a filtrar
     * @return cantidad de películas de ese director en la tabla
     * @throws SQLException si la consulta falla
     */
    public static int contarPorDirector(String director) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Peliculas WHERE director = ?";
        // TODO 4: Usa PreparedStatement con try-with-resources.
        //         setString para director en posición 1.
        //         Devuelve rs.getInt(1).
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        crearTablaPeliculas();

        List<Pelicula> lote = List.of(
            new Pelicula("El Padrino", "Francis Ford Coppola", 1972),
            new Pelicula("Pulp Fiction", "Quentin Tarantino", 1994),
            new Pelicula("Matrix", "Lana Wachowski", 1999),
            new Pelicula("Reservoir Dogs", "Quentin Tarantino", 1992),
            new Pelicula("Interstellar", "Christopher Nolan", 2014)
        );

        int insertadas = insertarLote(lote);
        System.out.println("Insertadas: " + insertadas + " de " + lote.size());
        System.out.println("Total en tabla: " + contarPeliculas());
        System.out.println("Películas de Tarantino: " + contarPorDirector("Quentin Tarantino"));
    }
}
