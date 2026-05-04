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
 * Boss Final — Sistema Productos + Categorías desde cero
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Este es el examen real simulado. Sin plantilla, sin código base.
 * Construyes dos entidades relacionadas + Singleton + dos DAOs completos.
 *
 * Si llegas aquí y lo resuelves: sota, caballo y rey.
 *
 * Entidades:
 *   Categoria (id, nombre, descripcion)
 *   Producto  (id, nombre, precio, stock, idCategoria) → referencia Categoria
 *
 * DAOCategoria: crearTabla, insertar, obtenerTodos, obtenerPorId, actualizar, eliminar
 * DAOProducto:  crearTabla, insertar, obtenerTodos, obtenerPorId, actualizar, eliminar,
 *               obtenerPorCategoria(int), buscarPorNombre(String)
 *
 * Conexion: Singleton compartido — ambos DAOs usan la misma instancia.
 */
public class BossFinal_SistemaProductos {

    // ── TODO 1: Entidad Categoria ─────────────────────────────────────────
    //
    //   Campos: int id, String nombre, String descripcion
    //
    //   Constructor sin id (2 params): nombre, descripcion
    //   Constructor con id (3 params): id, nombre, descripcion
    //
    //   Getters: getId, getNombre, getDescripcion
    //   toString: id + " | " + nombre + " | " + descripcion

    // ── TODO 2: Entidad Producto ──────────────────────────────────────────
    //
    //   Campos: int id, String nombre, double precio, int stock, int idCategoria
    //
    //   Constructor sin id (4 params): nombre, precio, stock, idCategoria
    //   Constructor con id (5 params): id, nombre, precio, stock, idCategoria
    //
    //   Getters: getId, getNombre, getPrecio, getStock, getIdCategoria
    //   toString: id + " | " + nombre + " | " + precio + " | stock=" + stock + " | cat=" + idCategoria

    // ── TODO 3: Singleton Conexion ────────────────────────────────────────
    //
    //   Clase estática "Conexion":
    //   - private static Connection instance = null
    //   - private static final String URL = "jdbc:sqlite:bootcamp_boss.db"
    //   - Constructor privado vacío
    //   - public static Connection getConexion() con guard null||isClosed
    //   - public static void cerrarConexion()

    // ── TODO 4: DAOCategoria ──────────────────────────────────────────────
    //
    //   Clase estática "DAOCategoria". Constructor público vacío.
    //
    //   Tabla "Categorias":
    //     id          INTEGER PRIMARY KEY AUTOINCREMENT
    //     nombre      TEXT NOT NULL
    //     descripcion TEXT
    //
    //   crearTabla() throws SQLException
    //   insertar(Categoria c) → boolean throws SQLException
    //   obtenerTodos() → List<Categoria> throws SQLException
    //   obtenerPorId(int id) → Categoria throws SQLException   (null si no existe)
    //   actualizar(Categoria c) → boolean throws SQLException
    //   eliminar(int id) → boolean throws SQLException

    // ── TODO 5: DAOProducto ───────────────────────────────────────────────
    //
    //   Clase estática "DAOProducto". Constructor público vacío.
    //
    //   Tabla "Productos":
    //     id           INTEGER PRIMARY KEY AUTOINCREMENT
    //     nombre       TEXT NOT NULL
    //     precio       REAL
    //     stock        INTEGER
    //     id_categoria INTEGER
    //
    //   crearTabla() throws SQLException
    //   insertar(Producto p) → boolean throws SQLException
    //   obtenerTodos() → List<Producto> throws SQLException
    //   obtenerPorId(int id) → Producto throws SQLException    (null si no existe)
    //   actualizar(Producto p) → boolean throws SQLException
    //   eliminar(int id) → boolean throws SQLException
    //
    //   obtenerPorCategoria(int idCategoria) → List<Producto> throws SQLException
    //     → SELECT ... WHERE id_categoria = ?
    //
    //   buscarPorNombre(String fragmento) → List<Producto> throws SQLException
    //     → SELECT ... WHERE nombre LIKE ?  → "%" + fragmento + "%"

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    // Descomenta cuando hayas implementado todos los TODOs:
    //
    // public static void main(String[] args) throws SQLException {
    //     DAOCategoria daoCat  = new DAOCategoria();
    //     DAOProducto  daoProd = new DAOProducto();
    //
    //     daoCat.crearTabla();
    //     daoProd.crearTabla();
    //
    //     // ── Verificar Singleton ──
    //     System.out.println("¿Misma conexión? " + (Conexion.getConexion() == Conexion.getConexion()));
    //
    //     // ── Insertar categorías ──
    //     daoCat.insertar(new Categoria("Electrónica",  "Dispositivos electrónicos"));
    //     daoCat.insertar(new Categoria("Ropa",         "Prendas de vestir"));
    //     daoCat.insertar(new Categoria("Alimentación", "Productos de consumo"));
    //
    //     // ── Insertar productos ──
    //     daoProd.insertar(new Producto("Laptop Pro",     999.99,  5, 1));
    //     daoProd.insertar(new Producto("Auriculares BT", 79.99,  20, 1));
    //     daoProd.insertar(new Producto("Camiseta M",     19.99, 100, 2));
    //     daoProd.insertar(new Producto("Zapatillas 42",  59.99,  30, 2));
    //     daoProd.insertar(new Producto("Café Arábica",    8.99, 200, 3));
    //
    //     // ── Listados ──
    //     System.out.println("\n=== Categorías ===");
    //     daoCat.obtenerTodos().forEach(System.out::println);
    //
    //     System.out.println("\n=== Todos los productos ===");
    //     daoProd.obtenerTodos().forEach(System.out::println);
    //
    //     System.out.println("\n=== Productos de Electrónica (id=1) ===");
    //     daoProd.obtenerPorCategoria(1).forEach(System.out::println);
    //
    //     System.out.println("\n=== Búsqueda por 'Pro' ===");
    //     daoProd.buscarPorNombre("Pro").forEach(System.out::println);
    //
    //     // ── Actualizar ──
    //     Producto lap = daoProd.obtenerPorId(1);
    //     System.out.println("\nAntes actualizar: " + lap);
    //     daoProd.actualizar(new Producto(lap.getId(), "Laptop Pro Max", lap.getPrecio(), 3, lap.getIdCategoria()));
    //     System.out.println("Después actualizar: " + daoProd.obtenerPorId(1));
    //
    //     // ── Eliminar ──
    //     System.out.println("\n=== Eliminar Café Arábica (id=5) ===");
    //     System.out.println("Eliminado: " + daoProd.eliminar(5));
    //
    //     System.out.println("\n=== Productos finales ===");
    //     daoProd.obtenerTodos().forEach(System.out::println);
    //
    //     Conexion.cerrarConexion();
    // }
    public static void main(String[] args) {
        System.out.println("Boss Final — implementa los 5 TODOs (Categoria, Producto, Conexion, DAOCategoria, DAOProducto).");
        System.out.println("Cuando compilen, descomenta el bloque del main para probar.");
        System.out.println("Si lo resuelves, el examen es sota, caballo y rey.");
    }
}
