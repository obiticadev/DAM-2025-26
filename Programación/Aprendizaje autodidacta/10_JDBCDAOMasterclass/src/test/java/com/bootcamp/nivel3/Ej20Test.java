package com.bootcamp.nivel3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida que la interfaz IVideojuegoDAO existe dentro de Ej20_InterfazDAO
 * y declara los 6 métodos del contrato CRUD.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej20Test {

    private Class<?> interfazClass;
    private Class<?> videojuegoClass;

    @BeforeEach
    void resolverClases() throws ClassNotFoundException {
        interfazClass    = Class.forName("com.bootcamp.nivel3_dao.Ej20_InterfazDAO$IVideojuegoDAO");
        videojuegoClass  = Class.forName("com.bootcamp.nivel3_dao.Ej20_InterfazDAO$Videojuego");
    }

    @Test
    @Order(1)
    @DisplayName("IVideojuegoDAO es una interfaz")
    void esInterfaz() {
        assertTrue(interfazClass.isInterface(), "IVideojuegoDAO debe ser una interface");
    }

    @Test
    @Order(2)
    @DisplayName("Declara el método crearTabla()")
    void metodoCrearTabla() {
        assertDoesNotThrow(() -> interfazClass.getMethod("crearTabla"));
    }

    @Test
    @Order(3)
    @DisplayName("Declara el método insertar(Videojuego)")
    void metodoInsertar() {
        assertDoesNotThrow(() -> interfazClass.getMethod("insertar", videojuegoClass));
    }

    @Test
    @Order(4)
    @DisplayName("Declara el método obtenerTodos()")
    void metodoObtenerTodos() {
        assertDoesNotThrow(() -> interfazClass.getMethod("obtenerTodos"));
    }

    @Test
    @Order(5)
    @DisplayName("Declara el método obtenerPorId(int)")
    void metodoObtenerPorId() {
        assertDoesNotThrow(() -> interfazClass.getMethod("obtenerPorId", int.class));
    }

    @Test
    @Order(6)
    @DisplayName("Declara el método actualizar(Videojuego)")
    void metodoActualizar() {
        assertDoesNotThrow(() -> interfazClass.getMethod("actualizar", videojuegoClass));
    }

    @Test
    @Order(7)
    @DisplayName("Declara el método eliminar(int)")
    void metodoEliminar() {
        assertDoesNotThrow(() -> interfazClass.getMethod("eliminar", int.class));
    }

    @Test
    @Order(8)
    @DisplayName("La interfaz tiene exactamente 6 métodos abstractos")
    void exactamenteSeisMétodos() {
        long abstractos = java.util.Arrays.stream(interfazClass.getMethods())
                .filter(m -> java.lang.reflect.Modifier.isAbstract(m.getModifiers()))
                .count();
        assertEquals(6, abstractos, "La interfaz debe declarar exactamente 6 métodos");
    }
}
