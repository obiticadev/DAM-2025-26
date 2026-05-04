package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej17_NulosEnResultSet;
import com.bootcamp.nivel2_crud.Ej17_NulosEnResultSet.Persona;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej17Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej17_NulosEnResultSet.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("obtenerTodas() no devuelve null")
    void obtenerTodasNoNull() throws Exception {
        assertNotNull(Ej17_NulosEnResultSet.obtenerTodas());
    }

    @Test
    @Order(2)
    @DisplayName("obtenerTodas() devuelve al menos 3 personas")
    void obtenerTodasTieneDatos() throws Exception {
        assertTrue(Ej17_NulosEnResultSet.obtenerTodas().size() >= 3);
    }

    @Test
    @Order(3)
    @DisplayName("La persona 'Bob' tiene edad null en Java")
    void bobTieneEdadNull() throws Exception {
        List<Persona> lista = Ej17_NulosEnResultSet.obtenerTodas();
        Persona bob = lista.stream()
                .filter(p -> "Bob".equals(p.nombre))
                .findFirst()
                .orElse(null);
        assertNotNull(bob, "Bob debe existir en la tabla");
        assertNull(bob.edad, "La edad de Bob debe ser null (era NULL en BD)");
    }

    @Test
    @Order(4)
    @DisplayName("La persona 'Ana' tiene edad no nula")
    void anaTieneEdad() throws Exception {
        List<Persona> lista = Ej17_NulosEnResultSet.obtenerTodas();
        Persona ana = lista.stream()
                .filter(p -> "Ana".equals(p.nombre))
                .findFirst()
                .orElse(null);
        assertNotNull(ana);
        assertNotNull(ana.edad, "Ana tiene edad — no debe ser null");
    }

    @Test
    @Order(5)
    @DisplayName("insertarPersona() con null funciona sin excepción")
    void insertarConNullOk() {
        assertDoesNotThrow(() ->
            Ej17_NulosEnResultSet.insertarPersona("Test", null, null)
        );
    }

    @Test
    @Order(6)
    @DisplayName("Persona insertada con null aparece con null en la lista")
    void personaConNullSeMapea() throws Exception {
        Ej17_NulosEnResultSet.insertarPersona("Zara", null, null);
        List<Persona> lista = Ej17_NulosEnResultSet.obtenerTodas();
        Persona zara = lista.stream()
                .filter(p -> "Zara".equals(p.nombre))
                .findFirst()
                .orElse(null);
        assertNotNull(zara);
        assertNull(zara.edad, "La edad debe ser null");
        assertNull(zara.telefono, "El teléfono debe ser null");
    }
}
