package com.bootcamp.nivel3_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Ej21 — DAO impl: crearTabla()
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: implementar el primer método de un DAO real: crearTabla().
 * Construirás el Singleton de Conexion y la clase DAOVideojuego completa,
 * pero solo implementarás crearTabla() — los demás métodos tienen stubs.
 *
 * A partir de este ejercicio la entidad Videojuego ya viene dada
 * para que te concentres en la capa DAO.
 */
public class Ej21_DAO_CrearTabla {

    // ── Entidad (ya resuelta — céntrate en el DAO) ────────────────────────
    public static class Videojuego {
        private final int id;
        private final String titulo;
        private final String genero;
        private final int anio;
        private final double precio;

        public Videojuego(String titulo, String genero, int anio, double precio) {
            this(0, titulo, genero, anio, precio);
        }

        public Videojuego(int id, String titulo, String genero, int anio, double precio) {
            this.id = id; this.titulo = titulo; this.genero = genero;
            this.anio = anio; this.precio = precio;
        }

        public int getId()       { return id; }
        public String getTitulo(){ return titulo; }
        public String getGenero(){ return genero; }
        public int getAnio()     { return anio; }
        public double getPrecio(){ return precio; }

        @Override
        public String toString() {
            return id + " | " + titulo + " | " + genero + " | " + anio + " | " + precio;
        }
    }

    // ── Singleton de Conexion ─────────────────────────────────────────────

    // TODO 1: Crea aquí la clase estática "Conexion" con el patrón Singleton completo:
    //         - private static Connection instance = null
    //         - private static final String URL = "jdbc:sqlite:bootcamp_ej21.db"
    //         - Constructor privado vacío
    //         - public static Connection getConexion() con el guard null||isClosed
    //         - public static void cerrarConexion()
    //         Ya lo has hecho varias veces — en el examen tardas 2 minutos.

    // ── DAO ───────────────────────────────────────────────────────────────

    // TODO 2: Crea aquí la clase estática "DAOVideojuego" con:
    //         - public DAOVideojuego() { } (constructor público vacío)
    //
    //         - Implementa crearTabla() throws SQLException:
    //             Tabla "Videojuegos": id INTEGER PRIMARY KEY AUTOINCREMENT,
    //                                  titulo TEXT NOT NULL, genero TEXT,
    //                                  anio INTEGER, precio REAL
    //             Usa try-with-resources con Statement y Conexion.getConexion().
    //             Imprime "Tabla Videojuegos lista."
    //
    //         - Stub los demás métodos (para que compile):
    //             public boolean insertar(Videojuego v) throws SQLException { return false; }
    //             public List<Videojuego> obtenerTodos() throws SQLException { return new java.util.ArrayList<>(); }
    //             public Videojuego obtenerPorId(int id) throws SQLException { return null; }
    //             public boolean actualizar(Videojuego v) throws SQLException { return false; }
    //             public boolean eliminar(int id) throws SQLException { return false; }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    // Descomenta este bloque cuando hayas implementado Conexion y DAOVideojuego:
    //
    // public static void main(String[] args) throws SQLException {
    //     DAOVideojuego dao = new DAOVideojuego();
    //     dao.crearTabla();
    //     String check = "SELECT name FROM sqlite_master WHERE type='table' AND name='Videojuegos'";
    //     try (Statement s = Conexion.getConexion().createStatement();
    //          ResultSet rs = s.executeQuery(check)) {
    //         System.out.println("¿Tabla existe? " + rs.next());
    //     }
    //     dao.crearTabla();
    //     System.out.println("Segunda llamada OK.");
    //     Conexion.cerrarConexion();
    // }
    public static void main(String[] args) {
        System.out.println("Implementa las clases Conexion y DAOVideojuego (TODO 1 y 2).");
        System.out.println("Cuando compilen, descomenta el bloque del main para probar.");
    }
}
