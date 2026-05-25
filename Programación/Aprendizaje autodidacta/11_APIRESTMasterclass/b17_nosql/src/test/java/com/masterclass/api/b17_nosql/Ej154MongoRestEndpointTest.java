package com.masterclass.api.b17_nosql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej154MongoRestEndpointTest {

    @Test
    void crearDevuelve201YPersiste() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        Respuesta154 r = Ej154MongoRestEndpoint.crear(repo, new NuevoPedido154("ana", 30));
        assertEquals(201, r.status());
        assertNotNull(r.cuerpo());
        assertEquals("ana", r.cuerpo().cliente());
        assertEquals(1, repo.findAll().size());
    }

    @Test
    void crearConBodyInvalidoDevuelve400() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        assertEquals(400, Ej154MongoRestEndpoint.crear(repo, null).status());
        assertEquals(400, Ej154MongoRestEndpoint.crear(repo, new NuevoPedido154("", 10)).status());
        assertEquals(400, Ej154MongoRestEndpoint.crear(repo, new NuevoPedido154("ana", -5)).status());
        assertTrue(repo.findAll().isEmpty());
    }

    @Test
    void obtenerExistenteDevuelve200() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        Respuesta154 creado = Ej154MongoRestEndpoint.crear(repo, new NuevoPedido154("leo", 80));
        Respuesta154 r = Ej154MongoRestEndpoint.obtener(repo, creado.cuerpo().id());
        assertEquals(200, r.status());
        assertEquals("leo", r.cuerpo().cliente());
    }

    @Test
    void obtenerInexistenteDevuelve404() {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        assertEquals(404, Ej154MongoRestEndpoint.obtener(repo, "nope").status());
    }

    @Test
    void testRetoExtra01_esPeticionMongoValida() {
        // Valida rutas validas bajo el api rest de mongo.
        assertTrue(Ej154MongoRestEndpoint.esPeticionMongoValida("GET", "/api/mongo"));
    }

    @Test
    void testRetoExtra02_esCabeceraJson() {
        // Verifica si el contentType de entrada corresponde a JSON o BSON.
        assertTrue(Ej154MongoRestEndpoint.esCabeceraJson("application/json"));
    }

    @Test
    void testRetoExtra03_crearRespuestaRestMongo() {
        // Genera el JSON standard de retorno de endpoints.
        assertTrue(Ej154MongoRestEndpoint.crearRespuestaRestMongo("1", "data").contains("data"));
    }

    @Test
    void testRetoExtra04_esAccionCritica() {
        // Identifica metodos HTTP que pueden mutar o eliminar colecciones enteras (DELETE).
        assertTrue(Ej154MongoRestEndpoint.esAccionCritica("DELETE"));
    }

    @Test
    void testRetoExtra05_esRutaExclusivaAdmin() {
        // Filtra endpoints reservados para administración y gestion de indices.
        assertTrue(Ej154MongoRestEndpoint.esRutaExclusivaAdmin("/api/mongo/admin"));
    }

    @Test
    void testRetoExtra06_esExcepcionApiRest() {
        // Determina si la excepcion proviene de fallos de serializacion de red.
        assertTrue(Ej154MongoRestEndpoint.esExcepcionApiRest(new IllegalArgumentException("rest")));
    }

    @Test
    void testRetoExtra07_generarMensajeFalloMongo() {
        // Crea la explicacion de error del endpoint rest.
        assertEquals("Mongo error on OP: err", Ej154MongoRestEndpoint.generarMensajeFalloMongo("OP", "err"));
    }

    @Test
    void testRetoExtra08_esLimiteOffsetValido() {
        // Verifica que el offset no sea negativo.
        assertTrue(Ej154MongoRestEndpoint.esLimiteOffsetValido(0));
    }

    @Test
    void testRetoExtra09_contieneTokenSeguridad() {
        // Comprueba que venga algun cabezal basico de autorizacion.
        assertTrue(Ej154MongoRestEndpoint.contieneTokenSeguridad("Bearer tok"));
    }

    @Test
    void testRetoExtra10_tiempoRespuestaOptimo() {
        // Determina si la consulta a Mongo se resolvio en tiempo optimo (menos de 500ms).
        assertTrue(Ej154MongoRestEndpoint.tiempoRespuestaOptimo(100L));
    }

}