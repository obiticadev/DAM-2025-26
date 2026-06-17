package com.masterclass.api.b17_nosql;

import java.util.List;

/**
 * Ejercicio 154 · API REST sobre Mongo.
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.6).
 *
 * <p>El controller no cambia por usar Mongo: recibe un DTO, valida, delega en
 * el "repositorio" ({@link RepoEnMemoria150}) y devuelve una respuesta con
 * código HTTP. Simulamos la respuesta con {@link Respuesta154} para poder
 * testearlo sin levantar Spring.
 */
public final class Ej154MongoRestEndpoint {

    private Ej154MongoRestEndpoint() {
    }

    /**
     * POST /pedidos — crea un pedido en Mongo.
     *
     * @param repo repositorio destino (no null)
     * @param body DTO de entrada (cliente + total)
     * @return 201 con el pedido creado, o 400 si el body es inválido
     */
    public static Respuesta154 crear(RepoEnMemoria150 repo, NuevoPedido154 body) {
        // TODO 1: si repo es null -> IllegalArgumentException (error de programación).
        // TODO 2: si body es null -> respuesta 400 (Bad Request), sin tocar el repo.
        // TODO 3: valida que body.cliente() no sea null ni vacío -> 400 si lo es.
        // TODO 4: valida que body.total() sea >= 0 -> 400 si es negativo.
        // TODO 5: genera un id (p.ej. java.util.UUID.randomUUID().toString()).
        // TODO 6: construye un Pedido149 con id generado + datos del body.
        // TODO 7: persístelo con repo.save(...).
        // TODO 8: REST: crear con éxito devuelve 201 Created.
        // TODO 9: el cuerpo de la respuesta 201 es el pedido persistido.
        // TODO 10: retorna new Respuesta154(201, pedidoGuardado) (o 400/null en errores).
        return new Respuesta154(400, null);
    }

    /**
     * GET /pedidos/{id} — recupera un pedido por id.
     *
     * @param repo repositorio fuente (no null)
     * @param id   id del pedido
     * @return 200 con el pedido, o 404 si no existe
     */
    public static Respuesta154 obtener(RepoEnMemoria150 repo, String id) {
        // TODO 1: si repo es null -> IllegalArgumentException.
        // TODO 2: si id es null o vacío -> respuesta 400.
        // TODO 3: consulta repo.findById(id) (devuelve Optional).
        // TODO 4: si el Optional está vacío -> 404 Not Found, cuerpo null.
        // TODO 5: si está presente -> 200 OK.
        // TODO 6: el cuerpo del 200 es el pedido encontrado.
        // TODO 7: no lances excepción por id inexistente (es 404, no 500).
        // TODO 8: el controller solo orquesta; la lógica de acceso vive en el repo.
        // TODO 9: usa Optional.map/orElse para elegir 200 vs 404 limpiamente.
        // TODO 10: retorna la Respuesta154 adecuada.
        return new Respuesta154(404, null);
    }

    public static void main(String[] args) {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        Respuesta154 r = crear(repo, new NuevoPedido154("ana", 30));
        System.out.println(r);
        System.out.println(obtener(repo, r.cuerpo() == null ? "x" : r.cuerpo().id()));
    }

        /**
     * RETO EXTRA 01: Valida rutas validas bajo el api rest de mongo.
     */
    public static boolean esPeticionMongoValida(String method, String path) {
        // GUÍA: teoría 17.6 (valida método HTTP + ruta del API).
        // 1. Si method o path son null -> false.
        // 2. El método debe ser uno válido: GET, POST, PUT, PATCH, DELETE.
        // 3. La ruta debe empezar por "/" (y aquí, por "/api/mongo").
        // PISTA: Set.of("GET","POST","PUT","PATCH","DELETE").contains(method)
        //    && path.startsWith("/").
        // OJO: el test manda ("GET", "/api/mongo") y espera true.
        // CULTURA: el verbo + la ruta son la firma de un endpoint REST (b05).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPeticionMongoValida");
    }

    /**
     * RETO EXTRA 02: Verifica si el contentType de entrada corresponde a JSON o BSON.
     */
    public static boolean esCabeceraJson(String contentType) {
        // GUÍA: comprobar que el Content-Type sea JSON (o BSON).
        // 1. Si contentType es null -> false.
        // 2. Debe contener "json" (o "bson"), ignorando mayúsculas.
        // PISTA: contentType.toLowerCase().contains("json").
        // OJO: el test manda "application/json" y espera true; usa contains, no
        //      equals, porque puede venir "application/json; charset=utf-8".
        // CULTURA: es la cabecera que mira Spring para elegir el HttpMessageConverter (b02).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCabeceraJson");
    }

    /**
     * RETO EXTRA 03: Genera el JSON standard de retorno de endpoints.
     */
    public static String crearRespuestaRestMongo(String id, String data) {
        // GUÍA: construir un JSON de respuesta con id y data.
        // 1. Forma sugerida: {"id":"<id>","data":"<data>"}.
        // PISTA: return "{\"id\":\"" + id + "\",\"data\":\"" + data + "\"}";
        // OJO: el test (id="1", data="data") solo exige que el resultado
        //      .contains("data"); con incluir el valor data ya cuadra.
        // CULTURA: en real esto lo serializa Jackson desde un record DTO (b07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaRestMongo");
    }

    /**
     * RETO EXTRA 04: Identifica metodos HTTP que pueden mutar o eliminar colecciones enteras (DELETE).
     */
    public static boolean esAccionCritica(String method) {
        // GUÍA: marcar como crítico el método que destruye datos (DELETE).
        // 1. Si method es null -> false.
        // 2. Devuelve true para "DELETE" (puedes incluir también PUT si quieres
        //    ser más estricto, pero el test solo exige DELETE).
        // PISTA: return "DELETE".equalsIgnoreCase(method);
        // OJO: el test manda "DELETE" y espera true.
        // CULTURA: GET es seguro/idempotente; DELETE no se debe exponer sin control (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAccionCritica");
    }

    /**
     * RETO EXTRA 05: Filtra endpoints reservados para administración y gestion de indices.
     */
    public static boolean esRutaExclusivaAdmin(String path) {
        // GUÍA: detectar rutas de administración (contienen "/admin").
        // 1. Si path es null -> false.
        // 2. Devuelve true si la ruta contiene "/admin".
        // PISTA: return path != null && path.contains("/admin");
        // OJO: el test manda "/api/mongo/admin" y espera true.
        // CULTURA: las rutas /admin se protegen con un rol distinto (b18, Security).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaExclusivaAdmin");
    }

    /**
     * RETO EXTRA 06: Determina si la excepcion proviene de fallos de serializacion de red.
     */
    public static boolean esExcepcionApiRest(Throwable t) {
        // GUÍA: clasificar una excepción del API REST (por mensaje).
        // 1. Si t es null -> false.
        // 2. Criterio: el mensaje contiene "rest" (ignorando case).
        // PISTA: msg != null && msg.toLowerCase().contains("rest");
        //    mismo patrón que esExcepcionMongo (Ej149 reto 8).
        // OJO: el test pasa new IllegalArgumentException("rest") y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionApiRest");
    }

    /**
     * RETO EXTRA 07: Crea la explicacion de error del endpoint rest.
     */
    public static String generarMensajeFalloMongo(String operation, String err) {
        // GUÍA: una línea — formatea un mensaje de error con plantilla fija.
        // PISTA: return "Mongo error on " + operation + ": " + err;
        // OJO: el test (operation="OP", err="err") compara EXACTO con
        //      "Mongo error on OP: err". Respeta espacios y los dos puntos al pie
        //      de la letra: "Mongo error on " + ... + ": " + ...
        // CULTURA: mensajes consistentes facilitan el grep en logs (b20, Observabilidad).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarMensajeFalloMongo");
    }

    /**
     * RETO EXTRA 08: Verifica que el offset no sea negativo.
     */
    public static boolean esLimiteOffsetValido(int offset) {
        // GUÍA: un offset de paginación no puede ser negativo.
        // PISTA: return offset >= 0;
        // OJO: el test manda 0 y espera true (0 es válido: primera página).
        //      Cuidado: la condición es >= 0, NO > 0 (si no, 0 fallaría).
        // CULTURA: el offset es el .skip(n) de Mongo (conecta con b15, paginación).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esLimiteOffsetValido");
    }

    /**
     * RETO EXTRA 09: Comprueba que venga algun cabezal basico de autorizacion.
     */
    public static boolean contieneTokenSeguridad(String authorizationHeader) {
        // GUÍA: detectar una cabecera Authorization con token (Bearer).
        // 1. Si authorizationHeader es null o vacío -> false.
        // 2. Debe empezar por "Bearer " (o contener un token).
        // PISTA: authorizationHeader.startsWith("Bearer ").
        // OJO: el test manda "Bearer tok" y espera true.
        // CULTURA: "Bearer <jwt>" es el esquema estándar de OAuth2/JWT (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneTokenSeguridad");
    }

    /**
     * RETO EXTRA 10: Determina si la consulta a Mongo se resolvio en tiempo optimo (menos de 500ms).
     */
    public static boolean tiempoRespuestaOptimo(long ms) {
        // GUÍA: una consulta es "óptima" si tardó menos de 500 ms.
        // 1. ms no debería ser negativo (defensivo), pero el foco es el umbral.
        // PISTA: return ms >= 0 && ms < 500;
        // OJO: el test manda 100 y espera true (100 < 500). El límite es ESTRICTO:
        //      con < 500, justo 500 ms NO sería óptimo.
        // CULTURA: medir latencia por petición es la base de las métricas (b20).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tiempoRespuestaOptimo");
    }

}

/** DTO de entrada del endpoint de creación. */
record NuevoPedido154(String cliente, double total) {
}

/** Respuesta REST simulada (código HTTP + cuerpo). */
record Respuesta154(int status, Pedido149 cuerpo) {
}