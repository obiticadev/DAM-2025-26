package com.masterclass.api.b05_web;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 052 · DELETE y 204 No Content.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test espera {@code DELETE /api/items/{id}}: 204 si id &gt; 0,
 * 404 si id &lt;= 0 (recurso inexistente simulado).
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej052DeleteResource {

    /**
     * Borra el recurso identificado por id.
     *
     * @param id identificador (válido si &gt; 0 en esta simulación)
     * @return 204 si se borró; 404 si el recurso no existía
     */
    // TODO 3: anota el método con @DeleteMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable.
    public ResponseEntity<Void> borrar(long id) {
        // TODO 5: simula la existencia: el recurso "existe" si id > 0.
        // TODO 6: si id <= 0, devuelve ResponseEntity.notFound().build() (404).
        // TODO 7: si existe, ejecuta el borrado (aquí no-op simulado).
        // TODO 8: una respuesta 204 NO lleva body (ResponseEntity<Void>).
        // TODO 9: usa ResponseEntity.noContent().build() para el 204.
        // TODO 10: devuelve la ResponseEntity adecuada según el caso.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej052DeleteResource().borrar(5));
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record ItemDto(long id, String nombre, boolean activo) {}

    /**
     * Reto Extra 1: DELETE para borrado físico (retorna 204 No Content).
     */
    // GUÍA: teoría 5.3 — borrado físico = se elimina de verdad → 204 sin cuerpo.
    // 1. Una línea: return ResponseEntity.noContent().build();
    // OJO: el test pega a /api/items/5/fisico y espera 204.
    // CULTURA: lo opuesto es el borrado lógico del reto 2 (marcar inactivo en vez
    //   de borrar), habitual cuando hay que conservar el histórico.
    // TODO extra: anota con @DeleteMapping("/{id}/fisico").
    public ResponseEntity<Void> borrarFisico(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el borrado físico de la base de datos y retorna estatus 204.
        return null;
    }

    /**
     * Reto Extra 2: DELETE para borrado lógico (retorna 200 OK con el DTO modificado).
     */
    // GUÍA: teoría 5.3 — borrado lógico = no se elimina, se marca activo=false →
    //   200 con el recurso modificado (no 204, porque SÍ hay cuerpo).
    // 1. return ResponseEntity.ok(new ItemDto(id, "item-" + id, false));
    // OJO: el test pega a /api/items/5/logico y espera 200 con $.activo==false.
    // TODO extra: anota con @DeleteMapping("/{id}/logico").
    public ResponseEntity<ItemDto> borrarLogico(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: en lugar de eliminar, cambia el estado de 'activo' a false y devuelve estatus 200 con el DTO.
        return null;
    }

    /**
     * Reto Extra 3: DELETE con filtro por Query Parameter (borrado masivo selectivo).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("")
    public ResponseEntity<java.util.Map<String, Object>> borrarConFiltro(
            @org.springframework.web.bind.annotation.RequestParam("categoria") String categoria) {
        // GUÍA: teoría 5.3 — DELETE sobre la colección (sin id) filtrando por query.
        // 1. Devuelve 200 con un Map: "categoria" = categoria y "borrados" = 5
        //    (cantidad simulada de elementos eliminados).
        //    PISTA: Map.of("categoria", categoria, "borrados", 5).
        // OJO: el test pega a /api/items?categoria=libros y espera $.categoria==
        //   "libros" y $.borrados==5. La anotación @DeleteMapping("") mapea la raíz
        //   de @RequestMapping("/api/items").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para DeleteMapping");
    }

    /**
     * Reto Extra 4: DELETE protegido con autorización basada en token (simulado).
     */
    // GUÍA: teoría 5.3 — autorización casera por cabecera (la real llega en el b18).
    // 1. Si token NO es igual a "admin-token" → 403 Forbidden con body "No autorizado".
    //    PISTA: ResponseEntity.status(HttpStatus.FORBIDDEN).body("No autorizado").
    // 2. Si es correcto → 204 No Content (ResponseEntity.noContent().build()).
    // OJO: el test manda Authorization "wrong-token" → 403 y "admin-token" → 204.
    // TODO extra: anota con @DeleteMapping("/{id}/protegido").
    public ResponseEntity<?> borrarConProteccionAdmin(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestHeader("Authorization") String token) {
        // TODO extra: si el token no es igual a "admin-token", devuelve estatus 403 (Forbidden) con cuerpo "No autorizado".
        // Si es correcto, devuelve estatus 204.
        return null;
    }

    /**
     * Reto Extra 5: DELETE condicionado por relaciones de dependencia (cascade).
     */
    // GUÍA: teoría 5.3 — borrar algo con dependencias da 409 salvo que pidas cascada.
    // 1. Si cascade == false → 409 Conflict con body
    //    "No se puede borrar el recurso porque tiene dependencias. Usa cascade=true".
    // 2. Si cascade == true → 204 No Content.
    // OJO: el test usa ?cascade=false → 409 y ?cascade=true → 204. El parámetro
    //   booleano ya tiene defaultValue="false".
    // TODO extra: anota con @DeleteMapping("/{id}/dependencias").
    public ResponseEntity<?> borrarConDependencias(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestParam(value = "cascade", defaultValue = "false") boolean cascade) {
        // TODO extra: si 'cascade' es false, simula que el recurso tiene dependencias y devuelve estatus 409 (Conflict)
        // con cuerpo "No se puede borrar el recurso porque tiene dependencias. Usa cascade=true".
        // Si 'cascade' es true, realiza el borrado recursivo simulado y devuelve estatus 204.
        return null;
    }

    /**
     * Reto Extra 6: DELETE que retorna una copia de seguridad (backup) del recurso borrado.
     */
    // GUÍA: teoría 5.3 — DELETE que devuelve el recurso borrado (por si hay que
    //   restaurarlo) → 200 con el cuerpo, en vez de 204.
    // 1. return ResponseEntity.ok(new ItemDto(id, "item-" + id, true));
    // OJO: el test pega a /api/items/5/backup y espera 200 con $.id==5.
    // TODO extra: anota con @DeleteMapping("/{id}/backup").
    public ResponseEntity<ItemDto> borrarRetornandoBackup(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el borrado pero retorna estatus 200 con el ItemDto del recurso antes de ser destruido.
        return null;
    }

    /**
     * Reto Extra 7: DELETE sobre un recurso bloqueado contra borrado (Locked).
     */
    // GUÍA: teoría 5.3 — 423 Locked: el recurso está protegido contra borrado.
    // 1. Si id == 99 → 423 (HttpStatus.LOCKED) con body
    //    "Recurso protegido contra borrado".
    // 2. Cualquier otro id → 204 No Content.
    // OJO: el test usa id=99 → 423 e id=5 → 204.
    // TODO extra: anota con @DeleteMapping("/{id}/seguro").
    public ResponseEntity<?> borrarSeguro(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: si el id es 99, simulamos que el recurso está bloqueado.
        // Devuelve estatus 423 (Locked, HttpStatus.LOCKED) con cuerpo "Recurso protegido contra borrado".
        // Para cualquier otro id, devuelve estatus 204.
        return null;
    }

    /**
     * Reto Extra 8: DELETE con verificación de precondición temporal (Cabecera de fecha).
     */
    // GUÍA: teoría 5.3 — precondición por cabecera personalizada (X-Delete-Allowed-On).
    // 1. Si la cabecera es null o NO es igual a "HOY" → 412 Precondition Failed con
    //    body "Fecha de precondicion invalida".
    //    PISTA: !"HOY".equals(fechaStr).
    // 2. Si es "HOY" → 204 No Content.
    // OJO: el test manda "AYER" → 412 y "HOY" → 204.
    // TODO extra: anota con @DeleteMapping("/{id}/precondicion-fecha").
    public ResponseEntity<?> borrarConPrecondicionFecha(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestHeader(value = "X-Delete-Allowed-On", required = false) String fechaStr) {
        // TODO extra: si la cabecera 'X-Delete-Allowed-On' no está presente o no coincide con "HOY",
        // devuelve estatus 412 (Precondition Failed) con cuerpo "Fecha de precondicion invalida".
        // Si coincide, devuelve estatus 204.
        return null;
    }

    /**
     * Reto Extra 9: DELETE en lote pasando lista de IDs en el cuerpo (Bulk DELETE).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/batch")
    public ResponseEntity<java.util.Map<String, Object>> borrarMultiples(
            @org.springframework.web.bind.annotation.RequestBody java.util.List<Long> ids) {
        // GUÍA: teoría 5.3 — borrado masivo: la lista de ids viaja en el CUERPO
        //   (DELETE con body es poco ortodoxo pero habitual en APIs de lote).
        // 1. Devuelve 200 con un Map "borrados" = ids (la lista recibida).
        //    PISTA: Map.of("borrados", ids).
        // OJO: el test manda [1,2,3] y espera $.borrados[0]==1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para DeleteMapping");
    }

    /**
     * Reto Extra 10: Demostración de idempotencia en DELETE con estados de respuesta alternativos.
     */
    // GUÍA: teoría 5.3 — DELETE es IDEMPOTENTE: repetirlo deja el sistema igual.
    //   Aquí se simula con el ETag "borrado".
    // 1. Si el cliente manda If-None-Match == "\"borrado\"" (ya estaba borrado) →
    //    404 Not Found.
    // 2. Si no → 204 No Content con la cabecera ETag "\"borrado\"".
    //    PISTA: ResponseEntity.noContent().eTag("\"borrado\"").build();
    // OJO: el test primero borra (→ 204 con ETag "\"borrado\"") y luego repite con
    //   If-None-Match "\"borrado\"" (→ 404). Las comillas forman parte del valor.
    // TODO extra: anota con @DeleteMapping("/{id}/idempotencia").
    public ResponseEntity<Void> borrarVerificarCabeceraNoModified(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        // TODO extra: si el cliente envía If-None-Match con el valor "\"borrado\"", simulamos que ya fue borrado y retorna 404 (Not Found).
        // Si no, simula el borrado y retorna 204 (No Content) con la cabecera ETag: "\"borrado\"".
        return null;
    }

}
