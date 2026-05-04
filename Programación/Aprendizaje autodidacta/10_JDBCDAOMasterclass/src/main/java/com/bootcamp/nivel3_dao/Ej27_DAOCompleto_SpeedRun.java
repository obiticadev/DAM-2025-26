package com.bootcamp.nivel3_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej27 — SpeedRun: DAO completo desde cero
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: construir sin andamiaje previo, desde cero y de memoria:
 *   1. Entidad POJO (Cancion)
 *   2. Singleton de Conexion
 *   3. DAOCancion con los 6 métodos completos
 *
 * Este ejercicio simula exactamente la presión del examen.
 * Entidad nueva, sin plantilla, sin pistas de implementación.
 * Los tests validan que cada método funciona correctamente.
 *
 * Entidad: Cancion
 *   - id (int) — asignado por la BD
 *   - titulo (String)
 *   - artista (String)
 *   - duracionSegundos (int)
 *   - anio (int)
 */
public class Ej27_DAOCompleto_SpeedRun {

    // ─────────────────────────────────────────────────────────────────────
    // TODO 1 — ENTIDAD
    // Crea la clase estática pública "Cancion" con:
    //   - Atributos privados: id, titulo, artista, duracionSegundos, anio
    //   - Constructor SIN id: Cancion(titulo, artista, duracionSegundos, anio)
    //   - Constructor CON id: Cancion(id, titulo, artista, duracionSegundos, anio)
    //   - Getters para todos los campos
    //   - toString() legible con todos los campos
    // ─────────────────────────────────────────────────────────────────────

    // ─────────────────────────────────────────────────────────────────────
    // TODO 2 — SINGLETON
    // Crea la clase estática pública "Conexion" con:
    //   - private static Connection instance = null
    //   - private static final String URL = "jdbc:sqlite:bootcamp_ej27.db"
    //   - Constructor privado vacío
    //   - public static Connection getConexion() con guard null||isClosed
    //   - public static void cerrarConexion()
    // ─────────────────────────────────────────────────────────────────────

    // ─────────────────────────────────────────────────────────────────────
    // TODO 3 — DAO
    // Crea la clase estática pública "DAOCancion" con los 6 métodos:
    //
    //   void crearTabla() throws SQLException
    //     Tabla "Canciones": id INTEGER PRIMARY KEY AUTOINCREMENT,
    //                        titulo TEXT NOT NULL, artista TEXT,
    //                        duracion_segundos INTEGER, anio INTEGER
    //
    //   boolean insertar(Cancion c) throws SQLException
    //     INSERT en orden: titulo, artista, duracion_segundos, anio
    //     Devuelve executeUpdate() > 0
    //
    //   List<Cancion> obtenerTodos() throws SQLException
    //     SELECT id, titulo, artista, duracion_segundos, anio FROM Canciones
    //     while(rs.next()) → new Cancion(id, ...) → lista
    //
    //   Cancion obtenerPorId(int id) throws SQLException
    //     WHERE id=? → if(rs.next()) → Cancion o null
    //
    //   boolean actualizar(Cancion c) throws SQLException
    //     UPDATE ... SET titulo=?, artista=?, duracion_segundos=?, anio=? WHERE id=?
    //     El id del objeto va en la posición 5 (el WHERE)
    //
    //   boolean eliminar(int id) throws SQLException
    //     DELETE FROM Canciones WHERE id=?
    //     Devuelve executeUpdate() > 0
    // ─────────────────────────────────────────────────────────────────────

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    // Descomenta este bloque cuando hayas implementado Cancion, Conexion y DAOCancion:
    //
    // public static void main(String[] args) throws SQLException {
    //     DAOCancion dao = new DAOCancion();
    //     dao.crearTabla();
    //
    //     dao.insertar(new Cancion("Bohemian Rhapsody", "Queen", 354, 1975));
    //     dao.insertar(new Cancion("Stairway to Heaven", "Led Zeppelin", 482, 1971));
    //     dao.insertar(new Cancion("Hotel California", "Eagles", 391, 1977));
    //
    //     System.out.println("=== Todas las canciones ===");
    //     dao.obtenerTodos().forEach(System.out::println);
    //
    //     System.out.println("\nBuscar id=2: " + dao.obtenerPorId(2));
    //
    //     Cancion actualizada = new Cancion(1, "Bohemian Rhapsody (Live)", "Queen", 400, 1985);
    //     System.out.println("Actualizar id=1: " + dao.actualizar(actualizada));
    //
    //     System.out.println("Eliminar id=3: " + dao.eliminar(3));
    //     dao.obtenerTodos().forEach(System.out::println);
    //     Conexion.cerrarConexion();
    // }
    public static void main(String[] args) {
        System.out.println("Implementa Cancion, Conexion y DAOCancion (TODO 1, 2 y 3).");
        System.out.println("Cuando compilen, descomenta el bloque del main para probar.");
    }
}
