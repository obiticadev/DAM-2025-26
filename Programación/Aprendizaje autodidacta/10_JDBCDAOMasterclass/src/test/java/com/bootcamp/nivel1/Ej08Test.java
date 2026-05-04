package com.bootcamp.nivel1;

import com.bootcamp.nivel1_conexion.Ej08_EliminarYRecrear;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej08Test {

    @BeforeEach
    void limpiar() throws SQLException {
        Ej08_EliminarYRecrear.eliminarTabla("Productos");
    }

    @Test
    @Order(1)
    @DisplayName("tablaExiste() devuelve false cuando la tabla no existe")
    void tablaNoExiste() throws SQLException {
        assertFalse(Ej08_EliminarYRecrear.tablaExiste("Productos"));
    }

    @Test
    @Order(2)
    @DisplayName("crearTablaProductos() crea la tabla correctamente")
    void crearTablaProductosOk() throws SQLException {
        Ej08_EliminarYRecrear.crearTablaProductos();
        assertTrue(Ej08_EliminarYRecrear.tablaExiste("Productos"),
                "Tras crearTablaProductos() la tabla debe existir");
    }

    @Test
    @Order(3)
    @DisplayName("eliminarTabla() elimina la tabla existente")
    void eliminarTablaExistente() throws SQLException {
        Ej08_EliminarYRecrear.crearTablaProductos();
        Ej08_EliminarYRecrear.eliminarTabla("Productos");
        assertFalse(Ej08_EliminarYRecrear.tablaExiste("Productos"),
                "Tras eliminarTabla() la tabla no debe existir");
    }

    @Test
    @Order(4)
    @DisplayName("eliminarTabla() no lanza excepción si la tabla no existe (IF EXISTS)")
    void eliminarTablaInexistenteNoExplota() {
        assertDoesNotThrow(() -> Ej08_EliminarYRecrear.eliminarTabla("TablaQueNoExiste"),
                "DROP TABLE IF EXISTS no debe lanzar excepción si la tabla no existe");
    }

    @Test
    @Order(5)
    @DisplayName("resetTablaProductos() deja la tabla existente y vacía")
    void resetTablaProductosOk() throws SQLException {
        Ej08_EliminarYRecrear.crearTablaProductos();
        Ej08_EliminarYRecrear.resetTablaProductos();
        assertTrue(Ej08_EliminarYRecrear.tablaExiste("Productos"),
                "Tras reset, la tabla debe seguir existiendo");
    }

    @Test
    @Order(6)
    @DisplayName("resetTablaProductos() funciona aunque la tabla no existiera")
    void resetSinTablaPrevia() {
        assertDoesNotThrow(() -> Ej08_EliminarYRecrear.resetTablaProductos(),
                "reset debe funcionar aunque no hubiera tabla previa");
    }
}
