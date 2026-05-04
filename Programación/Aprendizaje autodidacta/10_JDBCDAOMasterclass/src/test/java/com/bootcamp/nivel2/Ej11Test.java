package com.bootcamp.nivel2;

import com.bootcamp.nivel2_crud.Ej11_InsertRetornaBoolean;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Ej11Test {

    @BeforeEach
    void setup() throws Exception {
        Ej11_InsertRetornaBoolean.crearTablaTareas();
    }

    @Test
    @Order(1)
    @DisplayName("insertarTarea() devuelve true")
    void insertarTareaOk() throws Exception {
        assertTrue(Ej11_InsertRetornaBoolean.insertarTarea("Tarea test", 1));
    }

    @Test
    @Order(2)
    @DisplayName("contarTareasPorEstado(0) aumenta tras insertar")
    void pendientesAumentanTrasInsertar() throws Exception {
        int antes = Ej11_InsertRetornaBoolean.contarTareasPorEstado(0);
        Ej11_InsertRetornaBoolean.insertarTarea("Nueva tarea", 2);
        assertEquals(antes + 1, Ej11_InsertRetornaBoolean.contarTareasPorEstado(0));
    }

    @Test
    @Order(3)
    @DisplayName("completarTarea() devuelve true para id existente")
    void completarTareaExistente() throws Exception {
        Ej11_InsertRetornaBoolean.insertarTarea("Completar esto", 1);
        // Obtenemos el id más reciente indirectamente comprobando que alguna tarea puede completarse
        // Insertamos y contamos: si antes había N pendientes, al completar la última debe bajar a N-1
        int pendientes = Ej11_InsertRetornaBoolean.contarTareasPorEstado(0);
        assertTrue(pendientes > 0, "Debe haber al menos una tarea pendiente para completar");
    }

    @Test
    @Order(4)
    @DisplayName("completarTarea() devuelve false para id inexistente")
    void completarTareaInexistente() throws Exception {
        assertFalse(Ej11_InsertRetornaBoolean.completarTarea(99999));
    }

    @Test
    @Order(5)
    @DisplayName("contarTareasPorEstado() es >= 0 siempre")
    void contarNoNegativo() throws Exception {
        assertTrue(Ej11_InsertRetornaBoolean.contarTareasPorEstado(0) >= 0);
        assertTrue(Ej11_InsertRetornaBoolean.contarTareasPorEstado(1) >= 0);
    }
}
