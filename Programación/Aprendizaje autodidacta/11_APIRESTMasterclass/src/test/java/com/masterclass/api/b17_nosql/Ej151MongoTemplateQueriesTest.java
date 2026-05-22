package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej151MongoTemplateQueriesTest {

    private final List<Pedido149> col = List.of(
            new Pedido149("a", "ana", 120),
            new Pedido149("b", "ana", 50),
            new Pedido149("c", "leo", 200));

    @Test
    void filtraPorClienteYTotalMinimo() {
        List<Pedido149> r = Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio("ana", 100));
        assertEquals(1, r.size());
        assertEquals("a", r.get(0).id());
    }

    @Test
    void clienteNullNoFiltraPorCliente() {
        List<Pedido149> r = Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio(null, 100));
        assertEquals(2, r.size());
    }

    @Test
    void sinCoincidenciasListaVacia() {
        assertTrue(Ej151MongoTemplateQueries.find(col,
                Ej151MongoTemplateQueries.criterio("leo", 999)).isEmpty());
    }

    @Test
    void argumentosNullFallan() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej151MongoTemplateQueries.find(null,
                        Ej151MongoTemplateQueries.criterio("ana", 1)));
        assertThrows(IllegalArgumentException.class,
                () -> Ej151MongoTemplateQueries.find(col, null));
    }

    @Test
    void testRetoExtra01_esComandoActualizacionValido() {
        // Determina si la query contiene algun operador de modificacion ($set, $push).
        assertTrue(Ej151MongoTemplateQueries.esComandoActualizacionValido("{\"$set\":{}}"));
    }

    @Test
    void testRetoExtra02_esFiltroDocumentId() {
        // Determina si es una busqueda directa por identificador primario.
        assertTrue(Ej151MongoTemplateQueries.esFiltroDocumentId("{\"_id\":1}"));
    }

    @Test
    void testRetoExtra03_combinarActualizaciones() {
        // Une dos asignaciones BSON en un unico bloque de actualizacion.
        assertEquals("u1,u2", Ej151MongoTemplateQueries.combinarActualizaciones("u1", "u2"));
    }

    @Test
    void testRetoExtra04_esExcepcionEscrituraMongo() {
        // Determina si la excepcion corresponde a un error de escritura (DuplicateKey, etc).
        assertTrue(Ej151MongoTemplateQueries.esExcepcionEscrituraMongo(new IllegalArgumentException("write")));
    }

    @Test
    void testRetoExtra05_crearUpsertComando() {
        // Genera un comando de guardado incremental unificado.
        assertTrue(Ej151MongoTemplateQueries.crearUpsertComando("f", "u").contains("f"));
    }

    @Test
    void testRetoExtra06_esLimiteSeguroEscritura() {
        // Valida que los paquetes de insercion no saturen la red.
        assertTrue(Ej151MongoTemplateQueries.esLimiteSeguroEscritura(500));
    }

    @Test
    void testRetoExtra07_extraerTotalAfectados() {
        // Extrae la cantidad de registros alterados de un log resumido.
        assertEquals(5, Ej151MongoTemplateQueries.extraerTotalAfectados("Modified: 5"));
    }

    @Test
    void testRetoExtra08_esOperacionMultiDocumento() {
        // Indica si la edicion afectara a multiples registros.
        assertTrue(Ej151MongoTemplateQueries.esOperacionMultiDocumento("multi:true"));
    }

    @Test
    void testRetoExtra09_esMulti() {
        // Indica si la edicion afectara a multiples registros.
        assertTrue(Ej151MongoTemplateQueries.esMulti("multi"));
    }

    @Test
    void testRetoExtra10_esTransaccionActiva() {
        // Determina si hay una transaccion abierta en la sesion.
        assertTrue(Ej151MongoTemplateQueries.esTransaccionActiva("ACTIVE"));
    }

    @Test
    void testRetoExtra11_esIdAutogenerado() {
        // Indica si el id fue dejado a cargo del driver de Mongo.
        assertTrue(Ej151MongoTemplateQueries.esIdAutogenerado(null));
    }

}