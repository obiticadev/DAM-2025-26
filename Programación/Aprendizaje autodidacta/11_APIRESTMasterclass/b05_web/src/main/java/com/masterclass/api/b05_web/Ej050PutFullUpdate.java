package com.masterclass.api.b05_web;

/**
 * Ejercicio 050 · PUT (reemplazo total, idempotente).
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code PUT /api/items/{id}} con body {@code {"nombre":"x"}}
 * devolviendo 200 y el item con ese id y nombre.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej050PutFullUpdate {

    public record ItemDto(long id, String nombre) {
    }

    public record ItemBody(String nombre) {
    }

    /**
     * Reemplaza por completo el recurso identificado por id.
     *
     * @param id     identificador en la ruta
     * @param cuerpo nueva representación completa
     * @return el item actualizado (status 200)
     */
    // TODO 3: anota el método con @PutMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable.
    // TODO 5: anota 'cuerpo' con @RequestBody.
    public ItemDto reemplazar(long id, ItemBody cuerpo) {
        // TODO 6: PUT reemplaza TODO el recurso (no parchea campos sueltos).
        // TODO 7: valida que cuerpo.nombre() no sea null (PUT exige representación completa).
        // TODO 8: construye un ItemDto con el id de la ruta y el nombre del cuerpo.
        // TODO 9: PUT es idempotente: repetir la misma petición da el mismo resultado.
        // TODO 10: devuelve el ItemDto (200 implícito).
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej050PutFullUpdate().reemplazar(7, new ItemBody("nuevo")));
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record ItemConVersion(long id, String nombre, int version) {}

    @org.springframework.web.bind.annotation.ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
    public static class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    // Historial para el reto 5
    public static final java.util.List<String> historialNombres = new java.util.ArrayList<>();

    /**
     * Reto Extra 1: PUT con control de concurrencia optimista (Optimistic Locking).
     */
    // GUÍA: teoría 5.3 — bloqueo optimista: el cliente manda la versión que cree
    //   tener; si no es la actual, alguien la cambió antes → 412 Precondition Failed.
    // 1. Compara cuerpo.version() con la versión actual simulada (5).
    //    - distinta → ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build().
    //    - igual    → 200 con un ItemConVersion de versión 6 (incrementada).
    //    PISTA: new ItemConVersion(id, cuerpo.nombre(), 6).
    // OJO: el test manda version 5 → 200 con $.version==6, y version 4 → 412.
    // CULTURA: esto es lo que hace @Version de JPA en el bloque 14, automatizado.
    // TODO extra: anota con @PutMapping("/{id}/optimistic").
    public org.springframework.http.ResponseEntity<ItemConVersion> actualizarConOptimisticLock(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemConVersion cuerpo) {
        // TODO extra: supón que la versión actual en base de datos para este 'id' es 5.
        // Si el cuerpo tiene una versión distinta a 5, devuelve ResponseEntity con status 412 (Precondition Failed).
        // Si coincide, devuelve ResponseEntity con status 200 y el objeto incrementando su versión a 6.
        return null;
    }

    /**
     * Reto Extra 2: PUT con comportamiento Upsert (Crear o Actualizar).
     */
    // GUÍA: teoría 5.3 — "upsert" = PUT que crea si no existe o actualiza si existe.
    //   El status distingue ambos casos: 201 Created vs 200 OK.
    // 1. Si id > 100 (recurso inexistente simulado) → 201 con ItemDto(id, nombre).
    // 2. Si id <= 100 (existente) → 200 con ItemDto(id, nombre).
    //    PISTA: ResponseEntity.status(HttpStatus.CREATED).body(...) vs ResponseEntity.ok(...).
    // OJO: el test usa id=150 → 201 con $.id==150 y id=50 → 200. El nombre sale
    //   del cuerpo, el id de la ruta.
    // TODO extra: anota con @PutMapping("/{id}/upsert").
    public org.springframework.http.ResponseEntity<ItemDto> actualizarOcrear(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: si el id es mayor que 100, se considera que el recurso no existe,
        // por lo que se "crea" devolviendo estatus 201 (Created) con el ItemDto.
        // Si el id es <= 100, se considera existente y se devuelve estatus 200 (OK) con el ItemDto.
        return null;
    }

    /**
     * Reto Extra 3: PUT condicional exigiendo cabecera If-Match.
     */
    // GUÍA: teoría 5.3 — actualización condicional con If-Match (la cara "escritura"
    //   del ETag): solo actualizo si el cliente tiene la versión que digo.
    // 1. En cascada sobre ifMatch:
    //    - null o vacío         → 428 Precondition Required (HttpStatus.PRECONDITION_REQUIRED).
    //    - distinto de "\"v1\"" → 412 Precondition Failed.
    //    - igual a "\"v1\""     → 200 con ItemDto(id, cuerpo.nombre()).
    //    PISTA: !StringUtils.hasText(ifMatch) para el primer caso.
    // OJO: el test cubre sin header → 428, If-Match "\"v2\"" → 412 y "\"v1\"" → 200
    //   con $.nombre. Las comillas forman parte del valor esperado.
    // TODO extra: anota con @PutMapping("/{id}/conditional").
    public org.springframework.http.ResponseEntity<ItemDto> actualizarConCabeceraConditional(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo,
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-Match", required = false) String ifMatch) {
        // TODO extra: si ifMatch es nulo o vacío, devuelve estatus 428 (Precondition Required).
        // Si ifMatch no coincide con "\"v1\"", devuelve estatus 412 (Precondition Failed).
        // Si coincide, devuelve estatus 200 con el ItemDto correspondiente.
        return null;
    }

    /**
     * Reto Extra 4: Validación de formato en campos de texto de entrada.
     */
    // GUÍA: teoría 5.3 — validación de formato del cuerpo antes de aceptar el PUT.
    // 1. Si cuerpo.nombre() contiene algún carácter que NO sea letra, dígito o
    //    espacio → 400 Bad Request; si no → 200 con ItemDto(id, nombre).
    //    PISTA: cuerpo.nombre().matches("[a-zA-Z0-9 ]+") como criterio de "válido".
    // OJO: el test "Texto Valido" → 200 y "Invalido!!!" (signos) → 400.
    // CULTURA: en el bloque 8 esto se hará declarativo con @Pattern de Bean Validation.
    // TODO extra: anota con @PutMapping("/{id}/valida-formato").
    public org.springframework.http.ResponseEntity<ItemDto> actualizarValidandoCampos(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: si el nombre en el cuerpo contiene algún carácter especial (cualquiera que no sea letra, número o espacio),
        // devuelve estatus 400 (Bad Request). De lo contrario, devuelve estatus 200 con el ItemDto.
        return null;
    }

    /**
     * Reto Extra 5: Registro de historial de modificaciones.
     */
    // GUÍA: teoría 5.3 — efecto colateral antes de responder (auditoría/historial).
    // 1. Añade a la lista estática 'historialNombres' (ya declarada arriba) el
    //    nombre anterior simulado "viejo-nombre".
    //    PISTA: historialNombres.add("viejo-nombre");
    // 2. return ResponseEntity.ok(new ItemDto(id, cuerpo.nombre()));
    // OJO: el test solo comprueba status 200; el historial es la "demostración".
    // TODO extra: anota con @PutMapping("/{id}/historial").
    public org.springframework.http.ResponseEntity<ItemDto> actualizarConHistorial(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: guarda en la lista estática `historialNombres` el nombre anterior (simulado como "viejo-nombre" para este id).
        // Luego devuelve estatus 200 con el nuevo ItemDto.
        return null;
    }

    /**
     * Reto Extra 6: Lanzar excepción de recurso no encontrado.
     */
    // GUÍA: teoría 5.3 — lanzar una excepción anotada con @ResponseStatus(NOT_FOUND)
    //   convierte el "no existe" en un 404 sin escribir ResponseEntity.
    // 1. Si id < 1 → throw new RecursoNoEncontradoException("ID no valido")
    //    (la clase, declarada arriba, ya lleva @ResponseStatus(HttpStatus.NOT_FOUND)).
    // 2. Si no → return new ItemDto(id, cuerpo.nombre());
    // OJO: el test pega a /api/items/0/lanza-404 y espera 404. El método devuelve
    //   ItemDto directo (no ResponseEntity): el status lo pone la excepción.
    // CULTURA: esta es la base del manejo central de errores del bloque 9.
    // TODO extra: anota con @PutMapping("/{id}/lanza-404").
    public ItemDto actualizarLanzaNoEncontrado(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: si id es menor que 1, lanza RecursoNoEncontradoException con el mensaje "ID no valido".
        // De lo contrario, devuelve el ItemDto con estatus 200.
        return null;
    }

    /**
     * Reto Extra 7: PUT con respuesta vacía utilizando únicamente cabeceras.
     */
    // GUÍA: teoría 5.3 — un PUT puede responder 204 (sin cuerpo) y comunicar datos
    //   solo por cabeceras.
    // 1. return ResponseEntity.noContent()
    //        .header("X-Updated-At", String.valueOf(System.currentTimeMillis()))
    //        .build();
    // OJO: el test exige 204 y que EXISTA la cabecera X-Updated-At (no compara su
    //   valor, así que un timestamp real vale aquí).
    // TODO extra: anota con @PutMapping("/{id}/solo-cabeceras").
    public org.springframework.http.ResponseEntity<Void> actualizarRetornandoSoloCabeceras(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: realiza la actualización del recurso (simulada) y devuelve una ResponseEntity
        // con estatus 204 (No Content) y la cabecera "X-Updated-At" con el valor actual en milisegundos (como String).
        return null;
    }

    /**
     * Reto Extra 8: Actualización de estado condicional (Conflict).
     */
    // GUÍA: teoría 5.3 — 409 Conflict = el estado actual del recurso impide la
    //   operación (aquí, un recurso "inactivo" no se puede actualizar).
    // 1. Si id == 99 (inactivo simulado) → 409 con body
    //    "Recurso inactivo no se puede actualizar".
    //    PISTA: ResponseEntity.status(HttpStatus.CONFLICT).body("...").
    // 2. Cualquier otro id → 200 con ItemDto(id, cuerpo.nombre()).
    // OJO: el test usa id=7 → 200 e id=99 → 409. El tipo es ResponseEntity<?>
    //   porque una rama devuelve String y otra ItemDto.
    // TODO extra: anota con @PutMapping("/{id}/estado-activo").
    public org.springframework.http.ResponseEntity<?> actualizarSoloActivos(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemBody cuerpo) {
        // TODO extra: si el id es 99, simulamos que el recurso está inactivo/deshabilitado.
        // Devuelve una respuesta con estatus 409 (Conflict) y cuerpo "Recurso inactivo no se puede actualizar".
        // Para cualquier otro id, devuelve estatus 200 con el ItemDto.
        return null;
    }

    /**
     * Reto Extra 9: Actualización en lote (Bulk PUT).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/bulk")
    public org.springframework.http.ResponseEntity<java.util.List<ItemDto>> actualizarBulk(
            @org.springframework.web.bind.annotation.RequestBody java.util.List<ItemDto> cuerpo) {
        // GUÍA: teoría 5.3 — PUT masivo: recibe y devuelve una lista completa.
        // 1. Una línea: return ResponseEntity.ok(cuerpo);
        //    (en un caso real persistirías cada elemento; aquí basta el eco).
        // OJO: el test manda [{id:1,nombre:"a"},{id:2,nombre:"b"}] y espera 200 con
        //   $[0].nombre=="a" y $[1].nombre=="b" (orden preservado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PutMapping");
    }

    /**
     * Reto Extra 10: Validación cruzada de identificadores de ruta y cuerpo.
     */
    // GUÍA: teoría 5.3 — coherencia entre el id de la RUTA y el id del CUERPO:
    //   si no coinciden, la petición es ambigua → 400.
    // 1. Si id (ruta) != cuerpo.id() → 400 con body "Identificadores no coinciden".
    // 2. Si coinciden → 200 con el ItemDto (cuerpo).
    //    PISTA: ResponseEntity.badRequest().body("Identificadores no coinciden").
    // OJO: el test usa ruta 7 + cuerpo id=8 → 400, y ruta 7 + cuerpo id=7 → 200.
    // TODO extra: anota con @PutMapping("/{id}/verificar-id").
    public org.springframework.http.ResponseEntity<?> actualizarValidandoIdCuerpo(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemDto cuerpo) {
        // TODO extra: si el id de la ruta no coincide con el id del cuerpo JSON,
        // devuelve estatus 400 (Bad Request) con cuerpo "Identificadores no coinciden".
        // Si coinciden, devuelve estatus 200 con el ItemDto.
        return null;
    }

}
