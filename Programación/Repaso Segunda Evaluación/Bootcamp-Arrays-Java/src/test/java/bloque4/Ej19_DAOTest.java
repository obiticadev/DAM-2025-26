package bloque4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej19 - DAO Basico")
class Ej19_DAOTest {

    private Ej19_DAO dao;

    @BeforeEach
    void setUp() {
        dao = new Ej19_DAO();
    }

    @Test @DisplayName("Constructor: lista vacia")
    void constructor() { assertEquals(0, dao.size()); }

    @Test @DisplayName("cargarDatos: agrega productos")
    void cargarDatos() { dao.cargarDatos(); assertTrue(dao.size() >= 3); }

    @Test @DisplayName("agregar: id unico")
    void agregar() {
        Ej19_Producto p = new Ej19_Producto(1, "Test", 10, 2, 2);
        assertTrue(dao.agregar(p));
        assertFalse(dao.agregar(p)); // duplicado
    }

    @Test @DisplayName("buscarPorId: encuentra existente")
    void buscar() {
        dao.agregar(new Ej19_Producto(1, "Test", 10, 2, 2));
        assertNotNull(dao.buscarPorId(1));
        assertNull(dao.buscarPorId(99));
    }

    @Test @DisplayName("eliminar: elimina y devuelve true")
    void eliminar() {
        dao.agregar(new Ej19_Producto(1, "Test", 10, 2, 2));
        assertTrue(dao.eliminar(1));
        assertNull(dao.buscarPorId(1));
        assertFalse(dao.eliminar(1));
    }

    @Test @DisplayName("listar: devuelve copia")
    void listar() {
        dao.agregar(new Ej19_Producto(1, "A", 10, 2, 2));
        var lista = dao.listar();
        lista.clear();
        assertEquals(1, dao.size());
    }

    @Test @DisplayName("valorTotalInventario")
    void valorTotal() {
        Ej19_Producto p = new Ej19_Producto(1, "Test", 10, 2, 2);
        p.almacenar(0, 0, 5);
        dao.agregar(p);
        assertEquals(50.0, dao.valorTotalInventario(), 0.01);
    }
}
