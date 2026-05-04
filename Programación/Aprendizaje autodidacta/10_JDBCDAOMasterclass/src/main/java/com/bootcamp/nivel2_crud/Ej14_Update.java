package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej14 — UPDATE con PreparedStatement → boolean
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: practicar el patrón de actualización.
 * UPDATE es igual que INSERT en estructura, pero el SQL cambia y
 * el boolean devuelto indica si el registro existía y fue modificado.
 * Si executeUpdate() devuelve 0, el WHERE no encontró ninguna fila.
 */
public class Ej14_Update {

    public record Empleado(int id, String nombre, String departamento, double salario) {
        @Override
        public String toString() {
            return id + " | " + nombre + " | " + departamento + " | " + salario;
        }
    }

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej14.db";

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
     * Prepara la tabla Empleados con datos de ejemplo.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS Empleados (
                        id           INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre       TEXT NOT NULL,
                        departamento TEXT,
                        salario      REAL
                    )""");
        }
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Empleados")) {
            if (rs.next() && rs.getInt(1) == 0) {
                String ins = "INSERT INTO Empleados (nombre, departamento, salario) VALUES (?, ?, ?)";
                Object[][] d = {
                    {"Ana García", "Desarrollo", 32000.0},
                    {"Luis Pérez", "Marketing", 28000.0},
                    {"Sara López", "Desarrollo", 35000.0}
                };
                for (Object[] row : d) {
                    try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                        pst.setString(1, (String) row[0]);
                        pst.setString(2, (String) row[1]);
                        pst.setDouble(3, (Double) row[2]);
                        pst.executeUpdate();
                    }
                }
            }
        }
    }

    /**
     * Actualiza el salario de un empleado identificado por su id.
     *
     * @param id        id del empleado a actualizar
     * @param nuevoSalario nuevo valor del salario
     * @return true si el empleado existía y fue actualizado, false si no existía
     * @throws SQLException si la operación falla
     */
    public static boolean actualizarSalario(int id, double nuevoSalario) throws SQLException {
        String sql = "UPDATE Empleados SET salario = ? WHERE id = ?";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setDouble para nuevoSalario.
        //         Posición 2 → setInt para id.
        //         Guarda executeUpdate() en int afectadas.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Actualiza el departamento de un empleado.
     *
     * @param id              id del empleado a actualizar
     * @param nuevoDepartamento nuevo nombre de departamento
     * @return true si el empleado existía y fue actualizado, false si no existía
     * @throws SQLException si la operación falla
     */
    public static boolean actualizarDepartamento(int id, String nuevoDepartamento) throws SQLException {
        String sql = "UPDATE Empleados SET departamento = ? WHERE id = ?";
        // TODO 2: Mismo patrón que actualizarSalario().
        //         Posición 1 → setString para nuevoDepartamento.
        //         Posición 2 → setInt para id.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Devuelve el salario actual de un empleado, o -1 si no existe.
     *
     * @param id id del empleado a consultar
     * @return salario del empleado, o -1 si no existe ninguno con ese id
     * @throws SQLException si la consulta falla
     */
    public static double obtenerSalario(int id) throws SQLException {
        String sql = "SELECT salario FROM Empleados WHERE id = ?";
        // TODO 3: Abre PreparedStatement con try-with-resources.
        //         setInt para id en posición 1.
        //         Ejecuta executeQuery().
        //         Si rs.next() devuelve true: devuelve rs.getDouble("salario").
        //         Si no: devuelve -1 (empleado no encontrado).
        return -1;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("Salario inicial id=1: " + obtenerSalario(1));
        System.out.println("Actualizar salario id=1: " + actualizarSalario(1, 38000.0));
        System.out.println("Salario actualizado id=1: " + obtenerSalario(1));

        System.out.println("Actualizar salario id=99 (no existe): " + actualizarSalario(99, 50000.0));

        System.out.println("Actualizar dpto id=2: " + actualizarDepartamento(2, "Recursos Humanos"));
    }
}
