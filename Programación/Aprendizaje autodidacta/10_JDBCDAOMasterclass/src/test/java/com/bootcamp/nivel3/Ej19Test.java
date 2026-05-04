package com.bootcamp.nivel3;

import org.junit.jupiter.api.*;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida la estructura de la clase Videojuego definida dentro de Ej19_EntidadPOJO.
 * Usa reflection para inspeccionar sin acoplar el test a la implementación interna.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej19Test {

    private Class<?> videojuegoClass;

    @BeforeEach
    void resolverClase() throws ClassNotFoundException {
        // La clase Videojuego debe existir como clase interna de Ej19_EntidadPOJO
        videojuegoClass = Class.forName("com.bootcamp.nivel3_dao.Ej19_EntidadPOJO$Videojuego");
    }

    @Test
    @Order(1)
    @DisplayName("La clase Videojuego existe dentro de Ej19_EntidadPOJO")
    void claseExiste() {
        assertNotNull(videojuegoClass);
    }

    @Test
    @Order(2)
    @DisplayName("Tiene constructor sin id (4 parámetros: String, String, int, double)")
    void constructorSinId() {
        assertDoesNotThrow(() ->
            videojuegoClass.getDeclaredConstructor(String.class, String.class, int.class, double.class)
        , "Debe existir constructor Videojuego(String titulo, String genero, int anio, double precio)");
    }

    @Test
    @Order(3)
    @DisplayName("Tiene getter getTitulo()")
    void getterTitulo() {
        assertDoesNotThrow(() -> videojuegoClass.getMethod("getTitulo"));
    }

    @Test
    @Order(4)
    @DisplayName("Tiene getter getGenero()")
    void getterGenero() {
        assertDoesNotThrow(() -> videojuegoClass.getMethod("getGenero"));
    }

    @Test
    @Order(5)
    @DisplayName("Tiene getter getAnio() que devuelve int")
    void getterAnio() throws Exception {
        Method m = videojuegoClass.getMethod("getAnio");
        assertEquals(int.class, m.getReturnType());
    }

    @Test
    @Order(6)
    @DisplayName("Tiene getter getPrecio() que devuelve double")
    void getterPrecio() throws Exception {
        Method m = videojuegoClass.getMethod("getPrecio");
        assertEquals(double.class, m.getReturnType());
    }

    @Test
    @Order(7)
    @DisplayName("getTitulo() devuelve el valor pasado al constructor")
    void getTituloValorCorrecto() throws Exception {
        Constructor<?> ctor = videojuegoClass.getDeclaredConstructor(
                String.class, String.class, int.class, double.class);
        Object obj = ctor.newInstance("Zelda", "Aventura", 2023, 69.99);
        assertEquals("Zelda", videojuegoClass.getMethod("getTitulo").invoke(obj));
    }

    @Test
    @Order(8)
    @DisplayName("getPrecio() devuelve el valor pasado al constructor")
    void getPrecioValorCorrecto() throws Exception {
        Constructor<?> ctor = videojuegoClass.getDeclaredConstructor(
                String.class, String.class, int.class, double.class);
        Object obj = ctor.newInstance("Test", "RPG", 2020, 49.99);
        assertEquals(49.99, (double) videojuegoClass.getMethod("getPrecio").invoke(obj), 0.001);
    }

    @Test
    @Order(9)
    @DisplayName("toString() no lanza excepción y no devuelve null")
    void toStringOk() throws Exception {
        Constructor<?> ctor = videojuegoClass.getDeclaredConstructor(
                String.class, String.class, int.class, double.class);
        Object obj = ctor.newInstance("Test", "RPG", 2020, 19.99);
        String s = obj.toString();
        assertNotNull(s);
        assertFalse(s.isBlank());
    }
}
