package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida los tres TODOs de Ej30_ConsultasOrdenadas:
 * obtenerOrdenadosPorPrecioAsc, obtenerOrdenadosPorPrecioDesc
 * y obtenerPorCategoriaOrdenados.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej30Test {

    private static Class<?> prodClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.Ej30_ConsultasOrdenadas$";
        prodClass = Class.forName(base + "Producto");
        daoClass  = Class.forName(base + "DAOProducto");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
        // Poblar
        Object p1 = prodClass.getDeclaredConstructor(String.class, String.class, double.class)
                .newInstance("Teclado",    "Periférico", 49.99);
        Object p2 = prodClass.getDeclaredConstructor(String.class, String.class, double.class)
                .newInstance("Monitor",    "Pantalla", 299.0);
        Object p3 = prodClass.getDeclaredConstructor(String.class, String.class, double.class)
                .newInstance("Ratón",      "Periférico", 19.99);
        Object p4 = prodClass.getDeclaredConstructor(String.class, String.class, double.class)
                .newInstance("Auriculares","Periférico", 89.0);
        daoClass.getMethod("insertar", prodClass).invoke(dao, p1);
        daoClass.getMethod("insertar", prodClass).invoke(dao, p2);
        daoClass.getMethod("insertar", prodClass).invoke(dao, p3);
        daoClass.getMethod("insertar", prodClass).invoke(dao, p4);
    }

    private double getPrecio(Object prod) throws Exception {
        return (double) prod.getClass().getMethod("getPrecio").invoke(prod);
    }

    @Test @Order(1) @DisplayName("obtenerOrdenadosPorPrecioAsc() devuelve lista no vacía")
    void asc_noVacia() throws Exception {
        List<?> lista = (List<?>) daoClass.getMethod("obtenerOrdenadosPorPrecioAsc").invoke(dao);
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test @Order(2) @DisplayName("obtenerOrdenadosPorPrecioAsc() está ordenado de menor a mayor")
    void asc_ordenado() throws Exception {
        List<?> lista = (List<?>) daoClass.getMethod("obtenerOrdenadosPorPrecioAsc").invoke(dao);
        for (int i = 0; i < lista.size() - 1; i++) {
            double actual   = getPrecio(lista.get(i));
            double siguiente = getPrecio(lista.get(i + 1));
            assertTrue(actual <= siguiente,
                    "El precio " + actual + " debe ser <= " + siguiente);
        }
    }

    @Test @Order(3) @DisplayName("obtenerOrdenadosPorPrecioDesc() está ordenado de mayor a menor")
    void desc_ordenado() throws Exception {
        List<?> lista = (List<?>) daoClass.getMethod("obtenerOrdenadosPorPrecioDesc").invoke(dao);
        assertFalse(lista.isEmpty());
        for (int i = 0; i < lista.size() - 1; i++) {
            double actual    = getPrecio(lista.get(i));
            double siguiente = getPrecio(lista.get(i + 1));
            assertTrue(actual >= siguiente,
                    "El precio " + actual + " debe ser >= " + siguiente);
        }
    }

    @Test @Order(4) @DisplayName("obtenerPorCategoriaOrdenados() filtra por categoría")
    void porCategoria_filtra() throws Exception {
        List<?> lista = (List<?>) daoClass
                .getMethod("obtenerPorCategoriaOrdenados", String.class)
                .invoke(dao, "Periférico");
        assertNotNull(lista);
        assertEquals(3, lista.size(), "Deben ser 3 periféricos");
    }

    @Test @Order(5) @DisplayName("obtenerPorCategoriaOrdenados() devuelve lista vacía para categoría inexistente")
    void porCategoria_vacio() throws Exception {
        List<?> lista = (List<?>) daoClass
                .getMethod("obtenerPorCategoriaOrdenados", String.class)
                .invoke(dao, "CategoríaFalsa");
        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }
}
