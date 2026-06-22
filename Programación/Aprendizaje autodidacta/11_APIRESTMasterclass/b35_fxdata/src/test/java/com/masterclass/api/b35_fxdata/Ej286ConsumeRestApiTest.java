package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.sun.net.httpserver.HttpServer;

import javafx.concurrent.Task;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(15)
class Ej286ConsumeRestApiTest {

    private static final String JSON_CLIENTES =
            "[{\"id\":1,\"nombre\":\"Ana\",\"email\":\"a@a.com\"},"
                    + "{\"id\":2,\"nombre\":\"Berta\",\"email\":\"b@b.com\"}]";

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    /** Levanta un servidor HTTP local en un puerto efímero que responde siempre (status, body). */
    private static HttpServer servidor(int status, String body) throws IOException {
        HttpServer servidor = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        servidor.createContext("/", intercambio -> {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            intercambio.sendResponseHeaders(status, bytes.length);
            try (OutputStream salida = intercambio.getResponseBody()) {
                salida.write(bytes);
            }
        });
        servidor.start();
        return servidor;
    }

    private static String url(HttpServer servidor) {
        return "http://localhost:" + servidor.getAddress().getPort() + "/";
    }

    @Test
    void parsearClientes() {
        List<ClienteDto> clientes = Ej286ConsumeRestApi.parsearClientes(JSON_CLIENTES);
        assertEquals(2, clientes.size());
        assertEquals("Ana", clientes.get(0).nombre());
        assertEquals(List.of(), Ej286ConsumeRestApi.parsearClientes("[]")); // caso límite: array vacío
    }

    @Test
    void cargarEnSegundoPlano() throws Exception {
        HttpServer servidor = servidor(200, JSON_CLIENTES);
        try {
            Task<List<ClienteDto>> tarea = Ej286ConsumeRestApi.cargarEnSegundoPlano(url(servidor));
            tarea.run();
            List<ClienteDto> clientes = tarea.get();
            assertEquals(2, clientes.size());
            assertEquals("Berta", clientes.get(1).nombre());
        } finally {
            servidor.stop(0);
        }
    }

    @Test
    void retoExtra01_parsearUno() {
        assertEquals("Ana", Ej286ConsumeRestApi.parsearUno("{\"id\":1,\"nombre\":\"Ana\",\"email\":\"a@a.com\"}").nombre());
    }

    @Test
    void retoExtra02_contarClientes() {
        assertEquals(2, Ej286ConsumeRestApi.contarClientes(JSON_CLIENTES));
        assertEquals(0, Ej286ConsumeRestApi.contarClientes("[]")); // caso límite
    }

    @Test
    void retoExtra03_nombresDe() {
        List<ClienteDto> clientes = List.of(
                new ClienteDto(1L, "Ana", "a@a.com"),
                new ClienteDto(2L, "Berta", "b@b.com"));
        assertEquals(List.of("Ana", "Berta"), Ej286ConsumeRestApi.nombresDe(clientes));
    }

    @Test
    void retoExtra04_esExito() {
        assertTrue(Ej286ConsumeRestApi.esExito(200));
        assertTrue(Ej286ConsumeRestApi.esExito(201));
        assertFalse(Ej286ConsumeRestApi.esExito(404));
        assertFalse(Ej286ConsumeRestApi.esExito(500));
    }

    @Test
    void retoExtra05_urlPaginada() {
        assertEquals("http://host/api/clientes?page=2&size=10",
                Ej286ConsumeRestApi.urlPaginada("http://host/api/clientes", 2, 10));
    }

    @Test
    void retoExtra06_peticionGet() {
        HttpRequest req = Ej286ConsumeRestApi.peticionGet("http://host/api/clientes");
        assertEquals("GET", req.method());
        assertEquals("http://host/api/clientes", req.uri().toString());
    }

    @Test
    void retoExtra07_peticionConTimeout() {
        HttpRequest req = Ej286ConsumeRestApi.peticionConTimeout("http://host/api", 3);
        assertTrue(req.timeout().isPresent());
        assertEquals(Duration.ofSeconds(3), req.timeout().get());
    }

    @Test
    void retoExtra08_tareaDescargaTexto() throws Exception {
        HttpServer servidor = servidor(200, "hola mundo");
        try {
            Task<String> tarea = Ej286ConsumeRestApi.tareaDescargaTexto(url(servidor));
            tarea.run();
            assertEquals("hola mundo", tarea.get());
        } finally {
            servidor.stop(0);
        }
    }

    @Test
    void retoExtra09_tareaClientes() throws Exception {
        HttpServer servidor = servidor(200, JSON_CLIENTES);
        try {
            Task<List<ClienteDto>> tarea = Ej286ConsumeRestApi.tareaClientes(url(servidor));
            tarea.run();
            assertEquals(2, tarea.get().size());
        } finally {
            servidor.stop(0);
        }
    }

    @Test
    void retoExtra10_tareaConManejoError() throws Exception {
        HttpServer servidor = servidor(404, "no encontrado");
        try {
            Task<List<ClienteDto>> tarea = Ej286ConsumeRestApi.tareaConManejoError(url(servidor));
            tarea.run();
            ExecutionException ex = assertThrows(ExecutionException.class, tarea::get);
            assertTrue(ex.getCause().getMessage().contains("404"));
        } finally {
            servidor.stop(0);
        }
    }
}
