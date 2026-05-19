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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si repo es null -> IllegalArgumentException (error de programación).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si body es null -> respuesta 400 (Bad Request), sin tocar el repo.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: valida que body.cliente() no sea null ni vacío -> 400 si lo es.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: valida que body.total() sea >= 0 -> 400 si es negativo.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: genera un id (p.ej. java.util.UUID.randomUUID().toString()).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: construye un Pedido149 con id generado + datos del body.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: persístelo con repo.save(...).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: REST: crear con éxito devuelve 201 Created.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el cuerpo de la respuesta 201 es el pedido persistido.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna new Respuesta154(201, pedidoGuardado) (o 400/null en errores).
    }

}

/** DTO de entrada del endpoint de creación. */
record NuevoPedido154(String cliente, double total) {
}

/** Respuesta REST simulada (código HTTP + cuerpo). */
record Respuesta154(int status, Pedido149 cuerpo) {
}
