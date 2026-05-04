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
 * Ej33 — SpeedRun de examen: sistema completo desde cero
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: construir Entidad + Singleton + DAO completo en el mínimo tiempo posible.
 * Este ejercicio simula las condiciones reales del examen.
 *
 * Entidad: Pelicula (id, titulo, director, anio, duracionMin)
 * DAO a implementar: crearTabla, insertar, obtenerTodos, obtenerPorId, actualizar, eliminar
 *
 * Tiempo objetivo del examen: ≤ 25 minutos para el sistema completo.
 * Sigue el flujo mental del SpeedRun:
 *   1. Lee el enunciado → identifica entidad y campos
 *   2. Escribe el POJO (atributos + constructores + getters + toString)
 *   3. Escribe el Singleton (instance + URL + getConexion + cerrar)
 *   4. Escribe el DAO (crearTabla + CRUD completo)
 *   5. Main de prueba
 */
public class Ej33_SpeedRun_Examen {

    // ── TODO 1: Entidad Pelicula ─────────────────────────────────────────
    //
    //   Campos: int id, String titulo, String director, int anio, int duracionMin
    //
    //   Constructor sin id (4 params): titulo, director, anio, duracionMin
    //   Constructor con id (5 params): id, titulo, director, anio, duracionMin
    //
    //   Getters: getId, getTitulo, getDirector, getAnio, getDuracionMin
    //
    //   toString: id + " | " + titulo + " | " + director + " | " + anio + " | " + duracionMin + " min"

    // ── TODO 2: Singleton Conexion ────────────────────────────────────────
    //
    //   Clase estática "Conexion":
    //   - private static Connection instance = null
    //   - private static final String URL = "jdbc:sqlite:bootcamp_ej33.db"
    //   - Constructor privado vacío
    //   - public static Connection getConexion() con guard null||isClosed
    //   - public static void cerrarConexion()

    // ── TODO 3: DAOPelicula ───────────────────────────────────────────────
    //
    //   Clase estática "DAOPelicula":
    //   Constructor público vacío.
    //
    //   Tabla "Peliculas":
    //     id           INTEGER PRIMARY KEY AUTOINCREMENT
    //     titulo       TEXT NOT NULL
    //     director     TEXT
    //     anio         INTEGER
    //     duracion_min INTEGER
    //
    //   Métodos a implementar:
    //
    //   crearTabla() throws SQLException
    //     → Statement try-with-resources, CREATE TABLE IF NOT EXISTS
    //
    //   insertar(Pelicula p) → boolean throws SQLException
    //     → INSERT INTO Peliculas (titulo, director, anio, duracion_min) VALUES (?, ?, ?, ?)
    //     → setString(1), setString(2), setInt(3), setInt(4)
    //     → executeUpdate() > 0
    //
    //   obtenerTodos() → List<Pelicula> throws SQLException
    //     → SELECT id, titulo, director, anio, duracion_min FROM Peliculas
    //     → while(rs.next()) → new Pelicula(id, titulo, director, anio, durMin)
    //
    //   obtenerPorId(int id) → Pelicula throws SQLException
    //     → SELECT ... WHERE id = ?
    //     → if(rs.next()) return new Pelicula(...); return null
    //
    //   actualizar(Pelicula p) → boolean throws SQLException
    //     → UPDATE Peliculas SET titulo=?, director=?, anio=?, duracion_min=? WHERE id=?
    //     → posiciones: 1=titulo, 2=director, 3=anio, 4=durMin, 5=id
    //     → executeUpdate() > 0
    //
    //   eliminar(int id) → boolean throws SQLException
    //     → DELETE FROM Peliculas WHERE id = ?
    //     → executeUpdate() > 0

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    // Descomenta este bloque cuando hayas implementado los tres TODOs:
    //
    // public static void main(String[] args) throws SQLException {
    //     DAOPelicula dao = new DAOPelicula();
    //     dao.crearTabla();
    //
    //     dao.insertar(new Pelicula("El Padrino",      "Coppola", 1972, 175));
    //     dao.insertar(new Pelicula("Inception",       "Nolan",   2010, 148));
    //     dao.insertar(new Pelicula("Pulp Fiction",    "Tarantino",1994, 154));
    //
    //     System.out.println("=== Todas las películas ===");
    //     dao.obtenerTodos().forEach(System.out::println);
    //
    //     System.out.println("\n=== Buscar id=2 ===");
    //     System.out.println(dao.obtenerPorId(2));
    //
    //     System.out.println("\n=== Actualizar id=2 ===");
    //     boolean ok = dao.actualizar(new Pelicula(2, "Inception (Ed. Dir.)", "Nolan", 2010, 168));
    //     System.out.println("Actualizado: " + ok);
    //     System.out.println(dao.obtenerPorId(2));
    //
    //     System.out.println("\n=== Eliminar id=3 ===");
    //     System.out.println("Eliminado: " + dao.eliminar(3));
    //
    //     System.out.println("\n=== Lista final ===");
    //     dao.obtenerTodos().forEach(System.out::println);
    //
    //     Conexion.cerrarConexion();
    // }
    public static void main(String[] args) {
        System.out.println("Implementa los tres TODOs (Pelicula, Conexion, DAOPelicula).");
        System.out.println("Cuando compilen, descomenta el bloque del main para probar.");
    }
}
