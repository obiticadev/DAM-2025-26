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
 * Ej32 — Manejo de errores en DAO: catch + mensaje + re-lanzar
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: saber cómo gestionar SQLException en los métodos DAO correctamente.
 * Cada método propaga SQLException. El DAO NO la silencia: imprime un mensaje
 * de diagnóstico y luego re-lanza para que el llamador también pueda actuar.
 *
 * Patrón:
 *   public boolean insertar(Entidad e) throws SQLException {
 *       try (PreparedStatement pst = ...) {
 *           // lógica
 *           return pst.executeUpdate() > 0;
 *       } catch (SQLException ex) {
 *           System.err.println("Error al insertar " + e.getNombre() + ": " + ex.getMessage());
 *           throw ex;
 *       }
 *   }
 */
public class Ej32_ManejoErrores {

    // ── Entidad ───────────────────────────────────────────────────────────

    public static class Tarea {
        private final int id;
        private final String descripcion;
        private final int prioridad;

        public Tarea(String descripcion, int prioridad) {
            this(0, descripcion, prioridad);
        }

        public Tarea(int id, String descripcion, int prioridad) {
            this.id = id; this.descripcion = descripcion; this.prioridad = prioridad;
        }

        public int getId()             { return id; }
        public String getDescripcion() { return descripcion; }
        public int getPrioridad()      { return prioridad; }

        @Override
        public String toString() { return id + " | " + descripcion + " | P" + prioridad; }
    }

    // ── Singleton ─────────────────────────────────────────────────────────

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej32.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAO ───────────────────────────────────────────────────────────────

    public static class DAOTarea {

        /**
         * Crea la tabla Tareas si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Tareas (
                            id          INTEGER PRIMARY KEY AUTOINCREMENT,
                            descripcion TEXT NOT NULL,
                            prioridad   INTEGER NOT NULL
                        )""");
            }
        }

        /**
         * Inserta una tarea en la tabla.
         * Si la inserción falla, imprime un mensaje de error con la descripción
         * de la tarea y el mensaje de la excepción, luego re-lanza la excepción.
         *
         * @param t tarea a insertar
         * @return true si fue insertada, false si no
         * @throws SQLException si la inserción falla (después de imprimir el diagnóstico)
         */
        public boolean insertar(Tarea t) throws SQLException {
            String sql = "INSERT INTO Tareas (descripcion, prioridad) VALUES (?, ?)";
            // TODO 1: PreparedStatement con try-with-resources.
            //         setString(1, t.getDescripcion()), setInt(2, t.getPrioridad()).
            //         Rodea la lógica con try/catch(SQLException ex):
            //           System.err.println("Error al insertar tarea '" + t.getDescripcion() + "': " + ex.getMessage());
            //           throw ex;
            //         Devuelve executeUpdate() > 0.
            return false;
        }

        /**
         * Devuelve todas las tareas de la tabla.
         * Si la consulta falla, imprime un mensaje de error y re-lanza la excepción.
         *
         * @return lista de tareas; vacía si no hay ninguna
         * @throws SQLException si la consulta falla (después de imprimir el diagnóstico)
         */
        public List<Tarea> obtenerTodas() throws SQLException {
            String sql = "SELECT id, descripcion, prioridad FROM Tareas ORDER BY prioridad ASC";
            List<Tarea> lista = new ArrayList<>();
            // TODO 2: Statement con try-with-resources.
            //         executeQuery(sql) → while(rs.next()) → new Tarea(id, descripcion, prioridad) → lista.add.
            //         Rodea la lógica con try/catch(SQLException ex):
            //           System.err.println("Error al obtener tareas: " + ex.getMessage());
            //           throw ex;
            //         Devuelve lista.
            return lista;
        }

        /**
         * Elimina una tarea por su id.
         * Si la eliminación falla, imprime un mensaje de error y re-lanza la excepción.
         *
         * @param id id de la tarea a eliminar
         * @return true si fue eliminada, false si el id no existía
         * @throws SQLException si la eliminación falla (después de imprimir el diagnóstico)
         */
        public boolean eliminar(int id) throws SQLException {
            String sql = "DELETE FROM Tareas WHERE id = ?";
            // TODO 3: PreparedStatement con try-with-resources.
            //         setInt(1, id).
            //         Rodea la lógica con try/catch(SQLException ex):
            //           System.err.println("Error al eliminar tarea id=" + id + ": " + ex.getMessage());
            //           throw ex;
            //         Devuelve executeUpdate() > 0.
            return false;
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAOTarea dao = new DAOTarea();
        dao.crearTabla();

        dao.insertar(new Tarea("Revisar pull requests", 1));
        dao.insertar(new Tarea("Actualizar documentación", 3));
        dao.insertar(new Tarea("Corregir bug crítico", 1));

        System.out.println("=== Tareas (ordenadas por prioridad) ===");
        dao.obtenerTodas().forEach(System.out::println);

        System.out.println("\nEliminando primera tarea...");
        boolean ok = dao.eliminar(1);
        System.out.println("Eliminada: " + ok);

        System.out.println("\n=== Tareas tras eliminar ===");
        dao.obtenerTodas().forEach(System.out::println);
    }
}
