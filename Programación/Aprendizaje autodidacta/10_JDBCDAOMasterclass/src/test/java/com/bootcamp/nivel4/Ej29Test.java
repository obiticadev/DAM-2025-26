package com.bootcamp.nivel4;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida los tres TODOs de Ej29_ConsultasFiltradas:
 * buscarPorTitulo, filtrarPorGenero y buscarPorAutor.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej29Test {

    private static Class<?> libroClass;
    private static Class<?> daoClass;
    private static Object dao;

    @BeforeAll
    static void resolverClases() throws Exception {
        String base = "com.bootcamp.nivel4_integracion.Ej29_ConsultasFiltradas$";
        libroClass = Class.forName(base + "Libro");
        daoClass   = Class.forName(base + "DAOLibro");
        dao = daoClass.getDeclaredConstructor().newInstance();
        daoClass.getMethod("crearTabla").invoke(dao);
        // Poblar datos de prueba
        Object l1 = libroClass.getDeclaredConstructor(String.class, String.class, String.class)
                .newInstance("El Quijote", "Cervantes", "Clásico");
        Object l2 = libroClass.getDeclaredConstructor(String.class, String.class, String.class)
                .newInstance("Don Quijote Moderno", "Anónimo", "Ficción");
        Object l3 = libroClass.getDeclaredConstructor(String.class, String.class, String.class)
                .newInstance("Cien años de soledad", "García Márquez", "Realismo mágico");
        daoClass.getMethod("insertar", libroClass).invoke(dao, l1);
        daoClass.getMethod("insertar", libroClass).invoke(dao, l2);
        daoClass.getMethod("insertar", libroClass).invoke(dao, l3);
    }

    @Test @Order(1) @DisplayName("buscarPorTitulo() devuelve coincidencias parciales")
    void buscarPorTituloOk() throws Exception {
        List<?> res = (List<?>) daoClass.getMethod("buscarPorTitulo", String.class).invoke(dao, "Quijote");
        assertNotNull(res);
        assertEquals(2, res.size(), "Deben encontrarse 2 libros con 'Quijote' en el título");
    }

    @Test @Order(2) @DisplayName("buscarPorTitulo() sin coincidencias devuelve lista vacía")
    void buscarPorTituloSinResultados() throws Exception {
        List<?> res = (List<?>) daoClass.getMethod("buscarPorTitulo", String.class).invoke(dao, "zzz_inexistente");
        assertNotNull(res);
        assertTrue(res.isEmpty());
    }

    @Test @Order(3) @DisplayName("filtrarPorGenero() devuelve libros del género exacto")
    void filtrarPorGeneroOk() throws Exception {
        List<?> res = (List<?>) daoClass.getMethod("filtrarPorGenero", String.class).invoke(dao, "Clásico");
        assertNotNull(res);
        assertEquals(1, res.size());
    }

    @Test @Order(4) @DisplayName("filtrarPorGenero() con género inexistente devuelve lista vacía")
    void filtrarPorGeneroVacio() throws Exception {
        List<?> res = (List<?>) daoClass.getMethod("filtrarPorGenero", String.class).invoke(dao, "GeneroFalso");
        assertNotNull(res);
        assertTrue(res.isEmpty());
    }

    @Test @Order(5) @DisplayName("buscarPorAutor() devuelve coincidencias parciales")
    void buscarPorAutorOk() throws Exception {
        List<?> res = (List<?>) daoClass.getMethod("buscarPorAutor", String.class).invoke(dao, "García");
        assertNotNull(res);
        assertFalse(res.isEmpty());
    }
}
