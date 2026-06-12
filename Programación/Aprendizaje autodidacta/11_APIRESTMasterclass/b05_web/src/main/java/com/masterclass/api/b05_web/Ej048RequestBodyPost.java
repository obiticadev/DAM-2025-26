package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 048 · @RequestBody y 201 Created.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code POST /api/items} con body JSON {@code {"nombre":"x"}}
 * devolviendo 201 y el mismo item con un id asignado.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej048RequestBodyPost {

    public record ItemIn(String nombre) {
    }

    public record ItemOut(long id, String nombre) {
    }

    /**
     * Crea un item a partir del cuerpo JSON.
     *
     * @param entrada cuerpo deserializado
     * @return 201 Created con el item resultante (id = 1 fijo para el test)
     */
    // TODO 3: anota el método con @PostMapping.
    // TODO 4: anota 'entrada' con @RequestBody para deserializar el JSON.
    public ResponseEntity<ItemOut> crear(ItemIn entrada) {
        // TODO 5: valida que entrada.nombre() no sea null/vacío (si lo es, 400 con ResponseEntity.badRequest()).
        // TODO 6: construye un ItemOut con id = 1L y el nombre recibido.
        // TODO 7: usa ResponseEntity.status(HttpStatus.CREATED) (201).
        // TODO 8: añade la cabecera Location "/api/items/1" con .location(URI.create(...)).
        // TODO 9: incluye el ItemOut en el body con .body(...).
        // TODO 10: devuelve esa ResponseEntity.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej048RequestBodyPost().crear(new ItemIn("café")));
    }

    // --- MÉTODOS Y RECORDs DE RETOS EXTRA ---

    public record ItemConMetadatos(String nombre, java.util.Map<String, String> tags) {}
    public record ItemConFecha(String nombre, java.time.LocalDate expiracion) {}
    public record IdOut(long id) {}

    /**
     * Reto Extra 1: Creación por lotes recibiendo una lista JSON.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/batch")
    public org.springframework.http.ResponseEntity<java.util.Map<String, Object>> crearConLista(
            @org.springframework.web.bind.annotation.RequestBody java.util.List<ItemIn> entradas) {
        // GUÍA: teoría 5.2/5.3 — @RequestBody deserializa un array JSON a List<ItemIn>.
        // 1. Recorre 'entradas' asignando ids correlativos desde 1 → crea un
        //    List<ItemOut> (índice i → id i+1, mismo nombre).
        // 2. Construye un Map<String,Object> con "total" = entradas.size() e
        //    "items" = esa lista; devuélvelo con ResponseEntity.status(CREATED).
        // OJO: el test espera 201, $.total==2, $.items[0].id==1/nombre=="te" y
        //   $.items[1].id==2/nombre=="cafe": los ids empiezan en 1 y siguen el orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 2: Validación manual y estatus 422 Unprocessable Entity.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/validar")
    public org.springframework.http.ResponseEntity<ItemOut> crearConValidacion(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // GUÍA: teoría 5.3 — 422 Unprocessable Entity = sintaxis válida pero regla
        //   de negocio incumplida (distinto del 400 de cuerpo malformado).
        // 1. Si el nombre es demasiado corto (longitud < 3), devuelve
        //    ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build().
        // 2. Si pasa, construye ItemOut(1, nombre) y devuelve 201 con .body(...).
        // OJO: el test manda "ok" (2 letras) → 422 y "cafe" (4) → 201 con id 1.
        //   El umbral que separa ambos casos es length < 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 3: Deserialización de tipos de datos complejos y anidados.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/metadatos")
    public org.springframework.http.ResponseEntity<ItemConMetadatos> crearConMetadatos(
            @org.springframework.web.bind.annotation.RequestBody ItemConMetadatos entrada) {
        // GUÍA: teoría 5.2 + JSON (bloque 2) — Jackson deserializa objetos ANIDADOS
        //   (aquí un Map<String,String> dentro del record) sin esfuerzo extra.
        // 1. Una línea: return ResponseEntity.status(HttpStatus.CREATED).body(entrada);
        //    (devolver el mismo objeto recibido = "eco" del recurso creado).
        // OJO: el test manda {"nombre":"taza","tags":{"color":"azul","material":"ceramica"}}
        //   y comprueba $.nombre, $.tags.color y $.tags.material: el Map sale como
        //   subobjeto JSON.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 4: Restricción del tipo de contenido consumido (consumes).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping(value = "/json-only", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public org.springframework.http.ResponseEntity<ItemOut> crearConTipoContent(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // GUÍA: teoría 5.2 — consumes = APPLICATION_JSON_VALUE (ya en la anotación)
        //   hace que Spring RECHACE con 415 cualquier Content-Type que no sea JSON.
        // 1. Una línea: return ResponseEntity.status(HttpStatus.CREATED).body(new ItemOut(1L, entrada.nombre()));
        // OJO: el test manda primero APPLICATION_XML → espera 415 (lo bloquea el
        //   propio consumes, ni entras al método) y luego JSON → 201 con $.nombre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 5: Vinculación combinada de cabecera y cuerpo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/audit")
    public org.springframework.http.ResponseEntity<String> crearConCabeceraAudit(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada,
            @org.springframework.web.bind.annotation.RequestHeader("X-Created-By") String creador) {
        // GUÍA: teoría 5.2 — un método puede combinar @RequestBody y @RequestHeader:
        //   datos por el cuerpo, metadatos por cabecera.
        // 1. Devuelve 201 con body String: "Item " + entrada.nombre() + " creado por " + creador.
        //    PISTA: ResponseEntity.status(HttpStatus.CREATED).body("Item ...").
        // OJO: el test manda body {"nombre":"mesa"} y header X-Created-By=Admin,
        //   y compara el body EXACTO "Item mesa creado por Admin".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 6: Validación de cuerpo nulo/vacío defensivo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/defensivo")
    public org.springframework.http.ResponseEntity<String> crearSinNombre(
            @org.springframework.web.bind.annotation.RequestBody(required = false) ItemIn entrada) {
        // GUÍA: teoría 5.3 — con required=false el cuerpo puede faltar (entrada==null)
        //   en vez de provocar un 400 automático: tú decides qué hacer.
        // 1. Si entrada es null O su nombre es null/vacío → ResponseEntity.badRequest()
        //    .body("cuerpo invalido"); si no → ResponseEntity.ok("ok").
        //    PISTA: usa org.springframework.util.StringUtils.hasText(entrada.nombre()).
        // OJO: el test cubre tres casos: nombre "" → 400 "cuerpo invalido",
        //   SIN body → 400 "cuerpo invalido", nombre "silla" → 200 "ok".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 7: Creación con id específico y detección de conflicto (409).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/especifico")
    public org.springframework.http.ResponseEntity<ItemOut> crearConIdEspecifico(
            @org.springframework.web.bind.annotation.RequestBody ItemOut entrada) {
        // GUÍA: teoría 5.3 — un POST puede devolver varios códigos según la lógica.
        // 1. Lee entrada.id() y decide en cascada:
        //    - id <= 0           → 400 Bad Request (id inválido).
        //    - id == 99          → 409 Conflict (simula que ya existe).
        //    - en otro caso      → 201 Created con .body(entrada).
        //    PISTA: ResponseEntity.status(HttpStatus.CONFLICT).build() para el 409.
        // OJO: el test cubre id=0 → 400, id=99 → 409, id=10 → 201 con $.id==10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 8: Construcción de Location con URL absoluta del servidor.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/absoluto")
    public org.springframework.http.ResponseEntity<ItemOut> crearRetornandoLocationAbsoluta(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada,
            jakarta.servlet.http.HttpServletRequest request) {
        // GUÍA: teoría 5.3 — la cabecera Location del 201 debe apuntar al recurso
        //   recién creado. La forma idiomática construye la URI desde la request.
        // 1. id fijo = 123. Construye la URI:
        //    URI loc = ServletUriComponentsBuilder.fromRequestUri(request)
        //                  .path("/{id}").buildAndExpand(123).toUri();
        //    (import org.springframework.web.servlet.support.ServletUriComponentsBuilder)
        // 2. return ResponseEntity.created(loc).body(new ItemOut(123L, entrada.nombre()));
        //    created(loc) ya pone status 201 y la cabecera Location.
        // OJO: el test solo exige que Location CONTENGA "/api/items/123" y $.id==123.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 9: Respuesta simplificada devolviendo únicamente el ID.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/solo-id")
    public org.springframework.http.ResponseEntity<IdOut> crearRetornandoSoloId(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // GUÍA: teoría 5.3 — a veces el cliente solo necesita el id generado, no el
        //   recurso completo: devuelve un DTO mínimo (IdOut).
        // 1. Una línea: return ResponseEntity.status(HttpStatus.CREATED).body(new IdOut(999L));
        // OJO: el test espera 201 y $.id==999 (valor fijo). El record IdOut(long id)
        //   ya está declarado arriba.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

    /**
     * Reto Extra 10: Deserialización de LocalDate en RequestBody.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/fecha")
    public org.springframework.http.ResponseEntity<ItemConFecha> crearConLocalDate(
            @org.springframework.web.bind.annotation.RequestBody ItemConFecha entrada) {
        // GUÍA: teoría 5.2 + java.time — con el módulo JavaTime de Jackson (lo trae
        //   Spring Boot) una fecha ISO "2026-05-21" deserializa a LocalDate y vuelve
        //   a serializar igual.
        // 1. Una línea: return ResponseEntity.status(HttpStatus.CREATED).body(entrada);
        // OJO: el test manda {"nombre":"fruta","expiracion":"2026-05-21"} y espera
        //   $.nombre=="fruta" y $.expiracion=="2026-05-21" (ISO, no un array [2026,5,21]).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PostMapping");
    }

}
