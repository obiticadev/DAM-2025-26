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
}
