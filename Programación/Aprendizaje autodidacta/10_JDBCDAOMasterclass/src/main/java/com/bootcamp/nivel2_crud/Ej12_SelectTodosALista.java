package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej12 — SELECT todos → List usando ResultSet
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: dominar el patrón de lectura masiva:
 * executeQuery → while(rs.next()) → construir objeto → añadir a lista → return lista.
 * Este es el ObtenerTodos() que aparece en todos los DAOs.
 */
public class Ej12_SelectTodosALista {

    // Entidad interna para este ejercicio
    public static class Ciudad {
        public final int id;
        public final String nombre;
        public final String pais;
        public final int poblacion;

        public Ciudad(int id, String nombre, String pais, int poblacion) {
            this.id = id;
            this.nombre = nombre;
            this.pais = pais;
            this.poblacion = poblacion;
        }

        @Override
        public String toString() {
            return id + " | " + nombre + " | " + pais + " | " + poblacion;
        }
    }

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej12.db";

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
     * Crea la tabla "Ciudades" e inserta datos de ejemplo.
     * Llamar dos veces no falla gracias a IF NOT EXISTS.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        String create = """
                CREATE TABLE IF NOT EXISTS Ciudades (
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre     TEXT NOT NULL,
                    pais       TEXT NOT NULL,
                    poblacion  INTEGER
                )""";
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute(create);
        }
        // Insertar solo si la tabla está vacía
        if (contarCiudades() == 0) {
            String ins = "INSERT INTO Ciudades (nombre, pais, poblacion) VALUES (?, ?, ?)";
            Object[][] datos = {
                {"Madrid", "España", 3300000},
                {"Barcelona", "España", 1600000},
                {"Tokio", "Japón", 13960000},
                {"París", "Francia", 2140000}
            };
            for (Object[] d : datos) {
                try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                    pst.setString(1, (String) d[0]);
                    pst.setString(2, (String) d[1]);
                    pst.setInt(3, (Integer) d[2]);
                    pst.executeUpdate();
                }
            }
        }
    }

    private static int contarCiudades() throws SQLException {
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Ciudades")) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * Devuelve todas las ciudades de la tabla como lista.
     * Patrón: executeQuery → while(rs.next()) → new Ciudad(…) → lista.add → return lista.
     *
     * @return lista con todas las ciudades; lista vacía si no hay ninguna
     * @throws SQLException si la consulta falla
     */
    public static List<Ciudad> obtenerTodas() throws SQLException {
        String sql = "SELECT id, nombre, pais, poblacion FROM Ciudades";
        List<Ciudad> lista = new ArrayList<>();
        // TODO 1: Abre PreparedStatement (o Statement, no hay parámetros) con try-with-resources.
        //         Ejecuta executeQuery().
        //         En un bucle while(rs.next()):
        //           - Lee rs.getInt("id"), rs.getString("nombre"), rs.getString("pais"), rs.getInt("poblacion")
        //           - Crea un objeto Ciudad con esos valores.
        //           - Añádelo a lista.
        //         Devuelve lista (puede estar vacía, pero nunca null).
        return lista;
    }

    /**
     * Devuelve solo las ciudades del país indicado.
     * Usa PreparedStatement con WHERE pais = ?.
     *
     * @param pais nombre del país por el que filtrar
     * @return lista de ciudades de ese país; vacía si no hay ninguna
     * @throws SQLException si la consulta falla
     */
    public static List<Ciudad> obtenerPorPais(String pais) throws SQLException {
        String sql = "SELECT id, nombre, pais, poblacion FROM Ciudades WHERE pais = ?";
        List<Ciudad> lista = new ArrayList<>();
        // TODO 2: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setString para pais.
        //         Aplica el mismo bucle while(rs.next()) que en obtenerTodas().
        //         Devuelve lista.
        return lista;
    }

    /**
     * Devuelve la ciudad con mayor población de la tabla, o null si está vacía.
     *
     * @return la Ciudad con más habitantes, o null si no hay datos
     * @throws SQLException si la consulta falla
     */
    public static Ciudad obtenerMasPoblada() throws SQLException {
        String sql = "SELECT id, nombre, pais, poblacion FROM Ciudades ORDER BY poblacion DESC LIMIT 1";
        // TODO 3: Ejecuta la query. Usa if(rs.next()) — solo habrá una fila o ninguna.
        //         Si hay fila, construye y devuelve el objeto Ciudad.
        //         Si no hay fila, devuelve null.
        return null;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("=== Todas las ciudades ===");
        obtenerTodas().forEach(System.out::println);

        System.out.println("\n=== Ciudades de España ===");
        obtenerPorPais("España").forEach(System.out::println);

        System.out.println("\n=== Ciudad más poblada ===");
        System.out.println(obtenerMasPoblada());
    }
}
