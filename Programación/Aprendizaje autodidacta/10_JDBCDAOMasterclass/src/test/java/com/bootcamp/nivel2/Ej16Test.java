package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej16_NavegacionResultSet;
import com.bootcamp.nivel2_crud.Ej16_NavegacionResultSet.Sensor;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej16Test {

    @BeforeAll
    static void setup() throws Exception {
        Ej16_NavegacionResultSet.inicializar();
    }

    @Test
    @Order(1)
    @DisplayName("leerPorNombreColumna() no devuelve null")
    void leerNombreNoNull() throws Exception {
        assertNotNull(Ej16_NavegacionResultSet.leerPorNombreColumna());
    }

    @Test
    @Order(2)
    @DisplayName("leerPorNombreColumna() devuelve al menos 4 sensores")
    void leerNombreTieneDatos() throws Exception {
        assertTrue(Ej16_NavegacionResultSet.leerPorNombreColumna().size() >= 4);
    }

    @Test
    @Order(3)
    @DisplayName("leerPorIndice() devuelve los mismos datos que leerPorNombreColumna()")
    void ambosMétodosDanMismoResultado() throws Exception {
        List<Sensor> porNombre = Ej16_NavegacionResultSet.leerPorNombreColumna();
        List<Sensor> porIndice = Ej16_NavegacionResultSet.leerPorIndice();
        assertEquals(porNombre.size(), porIndice.size(), "Ambos métodos deben devolver la misma cantidad");
        for (int i = 0; i < porNombre.size(); i++) {
            assertEquals(porNombre.get(i).id, porIndice.get(i).id);
            assertEquals(porNombre.get(i).nombre, porIndice.get(i).nombre);
            assertEquals(porNombre.get(i).valor, porIndice.get(i).valor, 0.001);
        }
    }

    @Test
    @Order(4)
    @DisplayName("leerPorNombreColumna() mapea los campos correctamente")
    void camposCorrectos() throws Exception {
        List<Sensor> lista = Ej16_NavegacionResultSet.leerPorNombreColumna();
        lista.forEach(s -> {
            assertTrue(s.id > 0);
            assertNotNull(s.nombre);
            assertFalse(s.nombre.isBlank());
        });
    }

    @Test
    @Order(5)
    @DisplayName("promedioSensoresActivos() es mayor que 0")
    void promedioMayorCero() throws Exception {
        assertTrue(Ej16_NavegacionResultSet.promedioSensoresActivos() > 0);
    }
}
