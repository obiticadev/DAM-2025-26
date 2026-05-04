package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej15 — DELETE con PreparedStatement → boolean
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar el patrón de eliminación.
 * DELETE tiene exactamente la misma estructura que UPDATE —
 * prepareStatement → set → executeUpdate → afectadas > 0.
 * Si devuelve 0, el id no existía.
 */
public class Ej15_Delete {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej15.db";

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
     * Prepara la tabla Notas con datos de ejemplo.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Notas (
                        id        INTEGER PRIMARY KEY AUTOINCREMENT,
                        contenido TEXT NOT NULL,
                        categoria TEXT
                    )""");
        }
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Notas")) {
            if (rs.next() && rs.getInt(1) == 0) {
                String ins = "INSERT INTO Notas (contenido, categoria) VALUES (?, ?)";
                Object[][] d = {
                    {"Repasar JDBC", "Estudio"},
                    {"Comprar leche", "Personal"},
                    {"Hacer ejercicios DAO", "Estudio"},
                    {"Llamar al médico", "Personal"}
                };
                for (Object[] row : d) {
                    try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                        pst.setString(1, (String) row[0]);
                        pst.setString(2, (String) row[1]);
                        pst.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Elimina la nota con el id indicado.
     *
     * @param id identificador de la nota a eliminar
     * @return true si la nota existía y fue eliminada, false si no existía
     * @throws SQLException si la operación falla
     */
    public static boolean eliminarPorId(int id) throws SQLException {
        String sql = "DELETE FROM Notas WHERE id = ?";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setInt para id.
        //         Guarda executeUpdate() en afectadas.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Elimina todas las notas de una categoría concreta.
     *
     * @param categoria categoría de notas a eliminar
     * @return número de notas eliminadas (puede ser 0 si no había ninguna)
     * @throws SQLException si la operación falla
     */
    public static int eliminarPorCategoria(String categoria) throws SQLException {
        String sql = "DELETE FROM Notas WHERE categoria = ?";
        // TODO 2: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setString para categoria.
        //         Devuelve directamente el int de executeUpdate()
        //         (no boolean — queremos saber cuántas se eliminaron).
        return 0;
    }

    /**
     * Cuenta cuántas notas quedan en la tabla.
     *
     * @return número de notas actualmente en la tabla
     * @throws SQLException si la consulta falla
     */
    public static int contarNotas() throws SQLException {
        // TODO 3: Usa Statement con SELECT COUNT(*) FROM Notas.
        //         Ejecuta executeQuery() y devuelve rs.getInt(1).
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("Notas iniciales: " + contarNotas());

        System.out.println("Eliminar id=1: " + eliminarPorId(1));
        System.out.println("Eliminar id=99 (no existe): " + eliminarPorId(99));
        System.out.println("Notas tras eliminar id=1: " + contarNotas());

        System.out.println("Eliminar categoría 'Personal': " + eliminarPorCategoria("Personal") + " eliminadas");
        System.out.println("Notas finales: " + contarNotas());
    }
}
