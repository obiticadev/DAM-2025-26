package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej09 — INSERT con parámetros String usando PreparedStatement
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar el patrón fundamental de inserción:
 * prepareStatement → setString → executeUpdate → return afectadas > 0.
 * La tabla usa solo columnas de tipo TEXT para centrarse en el binding de Strings.
 */
public class Ej09_InsertStringParams {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej09.db";

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
     * Crea la tabla "Paises" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL,
     *           capital TEXT, continente TEXT.
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaPaises() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Paises (
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre     TEXT NOT NULL,
                    capital    TEXT,
                    continente TEXT
                )""";
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Inserta un país en la tabla Paises usando PreparedStatement.
     * La query ya está preparada — tú debes hacer el binding y la ejecución.
     *
     * @param nombre     nombre del país
     * @param capital    capital del país
     * @param continente continente al que pertenece
     * @return true si la fila fue insertada correctamente, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarPais(String nombre, String capital, String continente) throws SQLException {
        String sql = "INSERT INTO Paises (nombre, capital, continente) VALUES (?, ?, ?)";
        // TODO 1: Abre un PreparedStatement con try-with-resources usando la query sql.
        //         Usa pst.setString(posicion, valor) para cada uno de los tres parámetros.
        //         Recuerda: los índices empiezan en 1, no en 0.
        //         Llama a pst.executeUpdate() y guarda el resultado en int afectadas.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Inserta un país cuyo continente es desconocido (null en base de datos).
     * Demuestra cómo pasar null a un PreparedStatement.
     *
     * @param nombre  nombre del país
     * @param capital capital del país
     * @return true si la fila fue insertada correctamente, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarPaisSinContinente(String nombre, String capital) throws SQLException {
        String sql = "INSERT INTO Paises (nombre, capital, continente) VALUES (?, ?, ?)";
        // TODO 2: Igual que insertarPais(), pero el tercer parámetro es null.
        //         Para pasar null usa: pst.setNull(3, java.sql.Types.VARCHAR)
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Cuenta cuántas filas hay actualmente en la tabla Paises.
     *
     * @return número de filas en la tabla
     * @throws SQLException si la consulta falla
     */
    public static int contarPaises() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Paises";
        // TODO 3: Usa Statement (no PreparedStatement, no hay parámetros).
        //         Ejecuta executeQuery(sql) con try-with-resources.
        //         Lee el resultado con rs.getInt(1) — COUNT devuelve una sola fila y columna.
        //         Devuelve ese valor.
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        crearTablaPaises();

        System.out.println("Insertando España: " + insertarPais("España", "Madrid", "Europa"));
        System.out.println("Insertando Japón: " + insertarPais("Japón", "Tokio", "Asia"));
        System.out.println("Insertando País sin continente: " + insertarPaisSinContinente("Atlántida", "Poseidonia"));

        System.out.println("Total países: " + contarPaises());
    }
}
