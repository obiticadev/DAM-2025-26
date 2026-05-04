package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Ej11 — INSERT que devuelve boolean con afectadas > 0
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: dominar el patrón completo de inserción con retorno de éxito.
 * Este patrón aparece en TODOS los DAOs — si lo sabes construir solo,
 * el resto es repetición.
 *
 * Además practicarás detectar cuándo un insert no afectó ninguna fila
 * (cosa que en SQLite normal no ocurre, pero hay que saber manejarlo).
 */
public class Ej11_InsertRetornaBoolean {

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej11.db";

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
     * Crea la tabla "Tareas" si no existe.
     * Columnas: id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL,
     *           prioridad INTEGER, completada INTEGER (0 = false, 1 = true en SQLite).
     *
     * @throws SQLException si la sentencia DDL falla
     */
    public static void crearTablaTareas() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Tareas (
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo     TEXT    NOT NULL,
                    prioridad  INTEGER,
                    completada INTEGER DEFAULT 0
                )""";
        try (Statement stmt = getConexion().createStatement()) {
            stmt.execute(sql);
        }
    }

    /**
     * Inserta una tarea nueva en la tabla.
     * El patrón exacto a implementar es:
     * prepareStatement → set* → executeUpdate → return afectadas > 0.
     *
     * @param titulo     texto descriptivo de la tarea
     * @param prioridad  nivel de prioridad (1 = alta, 2 = media, 3 = baja)
     * @return true si la tarea se insertó, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarTarea(String titulo, int prioridad) throws SQLException {
        String sql = "INSERT INTO Tareas (titulo, prioridad, completada) VALUES (?, ?, 0)";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setString para titulo.
        //         Posición 2 → setInt para prioridad.
        //         (completada ya va hardcodeado como 0 en el SQL)
        //         Guarda executeUpdate() en int afectadas.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Marca una tarea como completada actualizando la columna completada a 1.
     * Devuelve true solo si existía la tarea y fue actualizada.
     *
     * @param id identificador de la tarea a marcar
     * @return true si la tarea existía y se actualizó, false si no existía
     * @throws SQLException si la actualización falla
     */
    public static boolean completarTarea(int id) throws SQLException {
        String sql = "UPDATE Tareas SET completada = 1 WHERE id = ?";
        // TODO 2: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setInt para id.
        //         executeUpdate devuelve 0 si no existe esa id — úsalo para el boolean.
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Cuenta cuántas tareas hay en la tabla con el valor de completada indicado.
     *
     * @param completada 1 para contar completadas, 0 para contar pendientes
     * @return número de tareas que cumplen la condición
     * @throws SQLException si la consulta falla
     */
    public static int contarTareasPorEstado(int completada) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Tareas WHERE completada = ?";
        // TODO 3: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setInt para completada.
        //         Ejecuta executeQuery().
        //         Lee rs.getInt(1) y devuélvelo.
        return 0;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        crearTablaTareas();

        System.out.println("Insertar tarea 1: " + insertarTarea("Estudiar JDBC", 1));
        System.out.println("Insertar tarea 2: " + insertarTarea("Hacer ejercicios", 2));
        System.out.println("Insertar tarea 3: " + insertarTarea("Ver teoría DAO", 1));

        System.out.println("Pendientes: " + contarTareasPorEstado(0));

        System.out.println("Completar tarea id=1: " + completarTarea(1));
        System.out.println("Completar tarea id=99 (no existe): " + completarTarea(99));

        System.out.println("Completadas: " + contarTareasPorEstado(1));
        System.out.println("Pendientes: " + contarTareasPorEstado(0));
    }
}
