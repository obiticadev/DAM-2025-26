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
 * Ej16 — Navegar ResultSet con todos los tipos de get*()
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar la lectura de columnas con getString(), getInt() y getDouble(),
 * accediendo tanto por nombre de columna como por índice.
 * Una tabla con cuatro tipos distintos obliga a usar el método correcto en cada caso.
 */
public class Ej16_NavegacionResultSet {

    public static class Sensor {
        public final int id;
        public final String nombre;
        public final double valor;
        public final int activo;   // SQLite no tiene BOOLEAN; usa 0/1

        public Sensor(int id, String nombre, double valor, int activo) {
            this.id = id;
            this.nombre = nombre;
            this.valor = valor;
            this.activo = activo;
        }

        @Override
        public String toString() {
            return id + " | " + nombre + " | " + valor + " | " + (activo == 1 ? "ON" : "OFF");
        }
    }

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej16.db";

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
     * Prepara la tabla Sensores con datos de ejemplo.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        try (Statement s = getConexion().createStatement()) {
            s.execute("""
                    CREATE TABLE IF NOT EXISTS Sensores (
                        id     INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre TEXT NOT NULL,
                        valor  REAL,
                        activo INTEGER DEFAULT 1
                    )""");
        }
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Sensores")) {
            if (rs.next() && rs.getInt(1) == 0) {
                String ins = "INSERT INTO Sensores (nombre, valor, activo) VALUES (?, ?, ?)";
                Object[][] d = {
                    {"Temperatura", 23.7, 1},
                    {"Humedad", 65.2, 1},
                    {"Presión", 1013.5, 0},
                    {"CO2", 412.0, 1}
                };
                for (Object[] row : d) {
                    try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                        pst.setString(1, (String) row[0]);
                        pst.setDouble(2, (Double) row[1]);
                        pst.setInt(3, (Integer) row[2]);
                        pst.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Lee todos los sensores usando los nombres de columna en los get*().
     * Usa rs.getInt("id"), rs.getString("nombre"), rs.getDouble("valor"), rs.getInt("activo").
     *
     * @return lista de todos los sensores leídos por nombre de columna
     * @throws SQLException si la consulta falla
     */
    public static List<Sensor> leerPorNombreColumna() throws SQLException {
        String sql = "SELECT id, nombre, valor, activo FROM Sensores";
        List<Sensor> lista = new ArrayList<>();
        // TODO 1: Ejecuta la query con try-with-resources.
        //         En el while(rs.next()) lee cada campo POR NOMBRE DE COLUMNA:
        //           int id       = rs.getInt("id")
        //           String nom   = rs.getString("nombre")
        //           double val   = rs.getDouble("valor")
        //           int act      = rs.getInt("activo")
        //         Crea new Sensor(id, nom, val, act) y añádelo a lista.
        //         Devuelve lista.
        return lista;
    }

    /**
     * Lee todos los sensores usando índices numéricos en los get*() (1-based).
     * Columnas en el SELECT: 1=id, 2=nombre, 3=valor, 4=activo.
     *
     * @return lista de todos los sensores leídos por índice
     * @throws SQLException si la consulta falla
     */
    public static List<Sensor> leerPorIndice() throws SQLException {
        String sql = "SELECT id, nombre, valor, activo FROM Sensores";
        List<Sensor> lista = new ArrayList<>();
        // TODO 2: Igual que leerPorNombreColumna(), pero usa índices numéricos:
        //           rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)
        //         Devuelve lista.
        return lista;
    }

    /**
     * Devuelve el valor promedio de todos los sensores activos (activo = 1).
     *
     * @return promedio de la columna valor de los sensores activos, o 0 si no hay ninguno
     * @throws SQLException si la consulta falla
     */
    public static double promedioSensoresActivos() throws SQLException {
        String sql = "SELECT AVG(valor) FROM Sensores WHERE activo = 1";
        // TODO 3: Ejecuta con Statement y try-with-resources.
        //         Lee rs.getDouble(1) — AVG devuelve una sola fila.
        //         Si rs.next() es false (tabla vacía), devuelve 0.
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("=== Por nombre de columna ===");
        leerPorNombreColumna().forEach(System.out::println);

        System.out.println("\n=== Por índice ===");
        leerPorIndice().forEach(System.out::println);

        System.out.println("\nPromedio sensores activos: " + promedioSensoresActivos());
    }
}
