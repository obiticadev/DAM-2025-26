package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej150MongoRepositoryTest {

    @Test
    void saveYFindById() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        assertEquals("ana", repo.findById("p1").orElseThrow().cliente());
        assertTrue(repo.findById("zzz").isEmpty());
    }

    @Test
    void saveEsUpsert() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        repo.save(new Pedido149("p1", "ana", 99));
        assertEquals(1, repo.findAll().size());
        assertEquals(99, repo.findById("p1").orElseThrow().total());
    }

    @Test
    void deleteByIdIdempotente() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        assertTrue(repo.deleteById("p1"));
        assertFalse(repo.deleteById("p1"));
    }

    @Test
    void findByClienteDerivado() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        repo.save(new Pedido149("p2", "leo", 70));
        repo.save(new Pedido149("p3", "ana", 10));
        assertEquals(2, repo.findByCliente("ana").size());
        assertTrue(repo.findByCliente("nadie").isEmpty());
    }

    @Test
    void testRetoExtra01_esFiltroRegexValido() {
        // Verifica si el patron regex de busqueda es correcto.
        assertTrue(Ej150MongoRepository.esFiltroRegexValido("^Ada"));
    }

    @Test
    void testRetoExtra02_esRangoEdadValido() {
        // Valida limites de filtro de edad en queries.
        assertTrue(Ej150MongoRepository.esRangoEdadValido(18, 99));
    }

    @Test
    void testRetoExtra03_combinarFiltrosY() {
        // Une dos condiciones BSON en una operacion $and.
        assertEquals("{\"$and\":[f1,f2]}", Ej150MongoRepository.combinarFiltrosY("f1", "f2"));
    }

    @Test
    void testRetoExtra04_esFiltroVacio() {
        // Determina si la consulta no tiene restricciones (documento vacio).
        assertTrue(Ej150MongoRepository.esFiltroVacio("{}"));
    }

    @Test
    void testRetoExtra05_extraerCampoObjectId() {
        // Obtiene el valor del identificador dentro de un BSON string.
        assertEquals("123", Ej150MongoRepository.extraerCampoObjectId("{\"_id\":\"123\"}"));
    }

    @Test
    void testRetoExtra06_esConsultaPaginadaSegura() {
        // Verifica que el salto y limite cumplan margenes seguros de memoria.
        assertTrue(Ej150MongoRepository.esConsultaPaginadaSegura(0, 50));
    }

    @Test
    void testRetoExtra07_esExcepcionDeQueryMongo() {
        // Determina si el fallo ocurrio por sintaxis invalida en consulta.
        assertTrue(Ej150MongoRepository.esExcepcionDeQueryMongo(new IllegalArgumentException("query")));
    }

    @Test
    void testRetoExtra08_crearQueryGeospatial() {
        // Genera la query geo-JSON de cercania.
        assertTrue(Ej150MongoRepository.crearQueryGeospatial(0.0, 0.0, 100.0).contains("$near"));
    }

    @Test
    void testRetoExtra09_esOperadorMongoValido() {
        // Verifica si la cadena es un operador de consulta de Mongo valido.
        assertTrue(Ej150MongoRepository.esOperadorMongoValido("$gt"));
    }

    @Test
    void testRetoExtra10_extraerOrdenacionDeQuery() {
        // Resuelve la direccion del orden (1 asc, -1 desc).
        assertEquals("ASC", Ej150MongoRepository.extraerOrdenacionDeQuery("{\"edad\":1}"));
    }

}