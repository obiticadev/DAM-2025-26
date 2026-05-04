package com.bootcamp.bossfinal;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite Boss Final — la más extensa del bootcamp.
 * Valida el sistema completo Categoria + Producto con dos DAOs completos.
 * Usa reflection porque las cinco clases son TODOs del alumno.
 *
 * Si todos estos tests pasan: el examen es sota, caballo y rey.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BossFinalTest {

    private static Class<?> catClass;
    private static Class<?> prodClass;
    private static Class<?> conexionClass;
    private static Class<?> daoCatClass;
    private static Class<?> daoProdClass;
    private static Object daoCat;
    private static Object daoProd;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.BossFinal_SistemaProductos$";
        catClass     = Class.forName(base + "Categoria");
        prodClass    = Class.forName(base + "Producto");
        conexionClass = Class.forName(base + "Conexion");
        daoCatClass  = Class.forName(base + "DAOCategoria");
        daoProdClass = Class.forName(base + "DAOProducto");
        daoCat  = daoCatClass.getDeclaredConstructor().newInstance();
        daoProd = daoProdClass.getDeclaredConstructor().newInstance();
        daoCatClass.getMethod("crearTabla").invoke(daoCat);
        daoProdClass.getMethod("crearTabla").invoke(daoProd);
        // Poblar datos base
        Object c1 = catClass.getDeclaredConstructor(String.class, String.class).newInstance("Electrónica", "Dispositivos");
        Object c2 = catClass.getDeclaredConstructor(String.class, String.class).newInstance("Ropa", "Prendas");
        daoCatClass.getMethod("insertar", catClass).invoke(daoCat, c1);
        daoCatClass.getMethod("insertar", catClass).invoke(daoCat, c2);
    }

    private Object nuevaCat(String nombre, String desc) throws Exception {
        return catClass.getDeclaredConstructor(String.class, String.class).newInstance(nombre, desc);
    }

    private Object nuevoProd(String nombre, double precio, int stock, int idCat) throws Exception {
        return prodClass.getDeclaredConstructor(String.class, double.class, int.class, int.class)
                .newInstance(nombre, precio, stock, idCat);
    }

    private int getId(Object obj) throws Exception {
        return (int) obj.getClass().getMethod("getId").invoke(obj);
    }

    // ── Estructura ────────────────────────────────────────────────────────

    @Test @Order(1) @DisplayName("Categoria existe con constructor sin id (2 params)")
    void categoriaExiste() {
        assertDoesNotThrow(() ->
            catClass.getDeclaredConstructor(String.class, String.class)
        );
    }

    @Test @Order(2) @DisplayName("Producto existe con constructor sin id (4 params)")
    void productoExiste() {
        assertDoesNotThrow(() ->
            prodClass.getDeclaredConstructor(String.class, double.class, int.class, int.class)
        );
    }

    @Test @Order(3) @DisplayName("Conexion.getConexion() no devuelve null")
    void getConexionNoNull() throws Exception {
        Object conn = conexionClass.getMethod("getConexion").invoke(null);
        assertNotNull(conn);
    }

    @Test @Order(4) @DisplayName("Ambos DAOs comparten la misma instancia de Conexion")
    void mismaConexion() throws Exception {
        Object c1 = conexionClass.getMethod("getConexion").invoke(null);
        Object c2 = conexionClass.getMethod("getConexion").invoke(null);
        assertSame(c1, c2, "getConexion() debe devolver siempre la misma instancia");
    }

    // ── DAOCategoria CRUD ─────────────────────────────────────────────────

    @Test @Order(5) @DisplayName("DAOCategoria.insertar() devuelve true")
    void catInsertarOk() throws Exception {
        boolean ok = (boolean) daoCatClass.getMethod("insertar", catClass)
                .invoke(daoCat, nuevaCat("Alimentación", "Comestibles"));
        assertTrue(ok);
    }

    @Test @Order(6) @DisplayName("DAOCategoria.obtenerTodos() devuelve lista no vacía")
    void catObtenerTodosOk() throws Exception {
        List<?> lista = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(7) @DisplayName("DAOCategoria.obtenerPorId() con id existente no devuelve null")
    void catObtenerPorIdOk() throws Exception {
        List<?> todos = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int id = getId(todos.get(0));
        Object cat = daoCatClass.getMethod("obtenerPorId", int.class).invoke(daoCat, id);
        assertNotNull(cat);
    }

    @Test @Order(8) @DisplayName("DAOCategoria.obtenerPorId() con id inexistente devuelve null")
    void catObtenerPorIdNull() throws Exception {
        Object res = daoCatClass.getMethod("obtenerPorId", int.class).invoke(daoCat, 99999);
        assertNull(res);
    }

    @Test @Order(9) @DisplayName("DAOCategoria.actualizar() devuelve true para id existente")
    void catActualizarOk() throws Exception {
        List<?> todos = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int id = getId(todos.get(0));
        Object actualizado = catClass.getDeclaredConstructor(int.class, String.class, String.class)
                .newInstance(id, "Nombre Modificado", "Desc Modificada");
        boolean ok = (boolean) daoCatClass.getMethod("actualizar", catClass).invoke(daoCat, actualizado);
        assertTrue(ok);
    }

    @Test @Order(10) @DisplayName("DAOCategoria.actualizar() devuelve false para id inexistente")
    void catActualizarFalso() throws Exception {
        Object fantasma = catClass.getDeclaredConstructor(int.class, String.class, String.class)
                .newInstance(99999, "X", "Y");
        boolean ok = (boolean) daoCatClass.getMethod("actualizar", catClass).invoke(daoCat, fantasma);
        assertFalse(ok);
    }

    @Test @Order(11) @DisplayName("DAOCategoria.eliminar() devuelve true para id existente")
    void catEliminarOk() throws Exception {
        daoCatClass.getMethod("insertar", catClass).invoke(daoCat, nuevaCat("Para Eliminar", "Temp"));
        List<?> todos = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int ultimoId = todos.stream().mapToInt(o -> { try { return getId(o); } catch (Exception e) { return 0; } }).max().orElse(-1);
        boolean ok = (boolean) daoCatClass.getMethod("eliminar", int.class).invoke(daoCat, ultimoId);
        assertTrue(ok);
    }

    @Test @Order(12) @DisplayName("DAOCategoria.eliminar() devuelve false para id inexistente")
    void catEliminarFalso() throws Exception {
        boolean ok = (boolean) daoCatClass.getMethod("eliminar", int.class).invoke(daoCat, 99999);
        assertFalse(ok);
    }

    // ── DAOProducto CRUD ──────────────────────────────────────────────────

    @Test @Order(13) @DisplayName("DAOProducto.insertar() devuelve true")
    void prodInsertarOk() throws Exception {
        List<?> cats = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int idCat = getId(cats.get(0));
        boolean ok = (boolean) daoProdClass.getMethod("insertar", prodClass)
                .invoke(daoProd, nuevoProd("Laptop Pro", 999.99, 5, idCat));
        assertTrue(ok);
    }

    @Test @Order(14) @DisplayName("DAOProducto.obtenerTodos() devuelve lista no vacía")
    void prodObtenerTodosOk() throws Exception {
        List<?> lista = (List<?>) daoProdClass.getMethod("obtenerTodos").invoke(daoProd);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(15) @DisplayName("DAOProducto.obtenerPorId() con id existente no devuelve null")
    void prodObtenerPorIdOk() throws Exception {
        List<?> todos = (List<?>) daoProdClass.getMethod("obtenerTodos").invoke(daoProd);
        int id = getId(todos.get(0));
        Object p = daoProdClass.getMethod("obtenerPorId", int.class).invoke(daoProd, id);
        assertNotNull(p);
    }

    @Test @Order(16) @DisplayName("DAOProducto.obtenerPorId() con id inexistente devuelve null")
    void prodObtenerPorIdNull() throws Exception {
        Object res = daoProdClass.getMethod("obtenerPorId", int.class).invoke(daoProd, 99999);
        assertNull(res);
    }

    @Test @Order(17) @DisplayName("DAOProducto.actualizar() devuelve true para id existente")
    void prodActualizarOk() throws Exception {
        List<?> todos = (List<?>) daoProdClass.getMethod("obtenerTodos").invoke(daoProd);
        int id = getId(todos.get(0));
        Object actualizado = prodClass
                .getDeclaredConstructor(int.class, String.class, double.class, int.class, int.class)
                .newInstance(id, "Producto Modificado", 1.0, 1, 1);
        boolean ok = (boolean) daoProdClass.getMethod("actualizar", prodClass).invoke(daoProd, actualizado);
        assertTrue(ok);
    }

    @Test @Order(18) @DisplayName("DAOProducto.actualizar() devuelve false para id inexistente")
    void prodActualizarFalso() throws Exception {
        Object fantasma = prodClass
                .getDeclaredConstructor(int.class, String.class, double.class, int.class, int.class)
                .newInstance(99999, "X", 1.0, 0, 1);
        boolean ok = (boolean) daoProdClass.getMethod("actualizar", prodClass).invoke(daoProd, fantasma);
        assertFalse(ok);
    }

    @Test @Order(19) @DisplayName("DAOProducto.eliminar() devuelve true para id existente")
    void prodEliminarOk() throws Exception {
        List<?> cats = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int idCat = getId(cats.get(0));
        daoProdClass.getMethod("insertar", prodClass).invoke(daoProd, nuevoProd("Para Eliminar", 1.0, 1, idCat));
        List<?> todos = (List<?>) daoProdClass.getMethod("obtenerTodos").invoke(daoProd);
        int ultimoId = todos.stream().mapToInt(o -> { try { return getId(o); } catch (Exception e) { return 0; } }).max().orElse(-1);
        boolean ok = (boolean) daoProdClass.getMethod("eliminar", int.class).invoke(daoProd, ultimoId);
        assertTrue(ok);
    }

    @Test @Order(20) @DisplayName("DAOProducto.eliminar() devuelve false para id inexistente")
    void prodEliminarFalso() throws Exception {
        boolean ok = (boolean) daoProdClass.getMethod("eliminar", int.class).invoke(daoProd, 99999);
        assertFalse(ok);
    }

    // ── Consultas especiales ──────────────────────────────────────────────

    @Test @Order(21) @DisplayName("obtenerPorCategoria() filtra correctamente")
    void obtenerPorCategoriaOk() throws Exception {
        List<?> cats = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int idCat = getId(cats.get(0));
        daoProdClass.getMethod("insertar", prodClass).invoke(daoProd, nuevoProd("Producto Cat Test", 50.0, 10, idCat));
        List<?> lista = (List<?>) daoProdClass.getMethod("obtenerPorCategoria", int.class).invoke(daoProd, idCat);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        // Todos los productos deben pertenecer a la categoría pedida
        for (Object p : lista) {
            int cat = (int) p.getClass().getMethod("getIdCategoria").invoke(p);
            assertEquals(idCat, cat);
        }
    }

    @Test @Order(22) @DisplayName("obtenerPorCategoria() con id inexistente devuelve lista vacía")
    void obtenerPorCategoriaVacio() throws Exception {
        List<?> lista = (List<?>) daoProdClass.getMethod("obtenerPorCategoria", int.class).invoke(daoProd, 99999);
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test @Order(23) @DisplayName("buscarPorNombre() encuentra coincidencias parciales")
    void buscarPorNombreOk() throws Exception {
        List<?> cats = (List<?>) daoCatClass.getMethod("obtenerTodos").invoke(daoCat);
        int idCat = getId(cats.get(0));
        daoProdClass.getMethod("insertar", prodClass).invoke(daoProd, nuevoProd("Auriculares Bluetooth Pro", 79.99, 20, idCat));
        List<?> lista = (List<?>) daoProdClass.getMethod("buscarPorNombre", String.class).invoke(daoProd, "Bluetooth");
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(24) @DisplayName("buscarPorNombre() sin coincidencias devuelve lista vacía")
    void buscarPorNombreVacio() throws Exception {
        List<?> lista = (List<?>) daoProdClass.getMethod("buscarPorNombre", String.class).invoke(daoProd, "zzz_inexistente");
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }
}
