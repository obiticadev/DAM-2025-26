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
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/fisico")
    public ResponseEntity<Void> borrarFisico(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el borrado físico de la base de datos y retorna estatus 204.
        return null;
    }

    /**
     * Reto Extra 2: DELETE para borrado lógico (retorna 200 OK con el DTO modificado).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/logico")
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
        // TODO extra: simula el borrado de recursos de una categoría específica y devuelve 200 OK con un Map
        // conteniendo la clave "categoria" y la clave "borrados" con valor 5 (número simulado de filas afectadas).
        return null;
    }

    /**
     * Reto Extra 4: DELETE protegido con autorización basada en token (simulado).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/protegido")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/dependencias")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/backup")
    public ResponseEntity<ItemDto> borrarRetornandoBackup(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el borrado pero retorna estatus 200 con el ItemDto del recurso antes de ser destruido.
        return null;
    }

    /**
     * Reto Extra 7: DELETE sobre un recurso bloqueado contra borrado (Locked).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/seguro")
    public ResponseEntity<?> borrarSeguro(@org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: si el id es 99, simulamos que el recurso está bloqueado.
        // Devuelve estatus 423 (Locked, HttpStatus.LOCKED) con cuerpo "Recurso protegido contra borrado".
        // Para cualquier otro id, devuelve estatus 204.
        return null;
    }

    /**
     * Reto Extra 8: DELETE con verificación de precondición temporal (Cabecera de fecha).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/precondicion-fecha")
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
        // TODO extra: recibe la lista de IDs a borrar y devuelve estatus 200 con un Map conteniendo
        // la clave "borrados" y la lista de IDs procesados.
        return null;
    }

    /**
     * Reto Extra 10: Demostración de idempotencia en DELETE con estados de respuesta alternativos.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.DeleteMapping("/{id}/idempotencia")
    public ResponseEntity<Void> borrarVerificarCabeceraNoModified(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-None-Match", required = false) String ifNoneMatch) {
        // TODO extra: si el cliente envía If-None-Match con el valor "\"borrado\"", simulamos que ya fue borrado y retorna 404 (Not Found).
        // Si no, simula el borrado y retorna 204 (No Content) con la cabecera ETag: "\"borrado\"".
        return null;
    }

}
