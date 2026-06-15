package com.masterclass.api.b05_web;

import java.util.Map;

/**
 * Ejercicio 051 · PATCH (modificación parcial).
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.3).
 *
 * <p>El test parte de un item {id=1, nombre="viejo", activo=true} y envía
 * {@code PATCH /api/items/1} con {@code {"nombre":"nuevo"}} esperando que solo
 * cambie 'nombre'.
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api/items").
public class Ej051PatchPartialUpdate {

    public record ItemDto(long id, String nombre, boolean activo) {
    }

    /**
     * Aplica un parche parcial: solo los campos presentes en el mapa cambian.
     *
     * @param id     identificador del recurso
     * @param cambios mapa con los campos a modificar ("nombre" y/o "activo")
     * @return el item resultante tras aplicar solo los cambios indicados
     */
    // TODO 3: anota el método con @PatchMapping("/{id}").
    // TODO 4: anota 'id' con @PathVariable y 'cambios' con @RequestBody.
    public ItemDto patch(long id, Map<String, Object> cambios) {
        // TODO 5: parte de un estado base: nombre="viejo", activo=true (simulado en memoria).
        // TODO 6: si 'cambios' contiene la clave "nombre", actualiza solo ese campo.
        // TODO 7: si 'cambios' contiene "activo", actualiza solo ese campo (cast a boolean).
        // TODO 8: los campos NO presentes en 'cambios' deben conservar su valor base.
        // TODO 9: a diferencia de PUT, PATCH NO exige el recurso completo.
        // TODO 10: construye y devuelve el ItemDto con id + estado resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej051PatchPartialUpdate().patch(1, Map.of("nombre", "nuevo")));
    }

    // --- MÉTODOS Y DTOs DE RETOS EXTRA ---

    public record ItemPatchDto(String nombre, Boolean activo) {}

    public record ItemConTagsDto(long id, String nombre, boolean activo, java.util.List<String> tags) {}

    /**
     * Reto Extra 1: PATCH utilizando DTO parcial.
     */
    // GUÍA: teoría 5.3 — un DTO parcial (ItemPatchDto) usa campos WRAPPER (String,
    //   Boolean) para que "ausente" sea null y "presente" sea su valor.
    // 1. Parte del estado base (nombre="viejo", activo=true). Si cambios.nombre()
    //    != null actualiza el nombre; si cambios.activo() != null actualiza activo.
    // 2. return ResponseEntity.ok(new ItemDto(id, nombreFinal, activoFinal));
    // OJO: el test manda {"nombre":"nuevo-dto"} (sin activo) y espera $.nombre==
    //   "nuevo-dto"; activo no se toca. null = "no enviado", clave del PATCH parcial.
    // TODO extra: anota con @PatchMapping("/{id}/dto").
    public org.springframework.http.ResponseEntity<ItemDto> patchConDto(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemPatchDto cambios) {
        // TODO extra: si cambios.nombre() no es nulo, actualiza el nombre.
        // Si cambios.activo() no es nulo, actualiza el estado.
        // Devuelve estatus 200 con el ItemDto modificado.
        return null;
    }

    /**
     * Reto Extra 2: PATCH para modificar una colección anidada (tags).
     */
    // GUÍA: teoría 5.3 — PATCH sobre una colección con operaciones "add/remove".
    // 1. Parte de una lista MUTABLE base: new ArrayList<>(List.of("java","spring")).
    // 2. Si cambios contiene "addTag" → tags.add((String) cambios.get("addTag")).
    //    Si contiene "removeTag" → tags.remove((String) cambios.get("removeTag")).
    // 3. return ResponseEntity.ok(new ItemConTagsDto(id, "viejo", true, tags));
    // OJO: el test add "docker" espera $.tags[2]=="docker" (tercer elemento, tras
    //   java y spring); remove "spring" espera $.tags[0]=="java". Usa ArrayList
    //   mutable: List.of(...) lanzaría UnsupportedOperationException al modificar.
    // TODO extra: anota con @PatchMapping("/{id}/tags").
    public org.springframework.http.ResponseEntity<ItemConTagsDto> patchColeccion(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: partiendo de tags base = ["java", "spring"].
        // Si cambios contiene "addTag", añade el valor a la lista.
        // Si cambios contiene "removeTag", elimina el valor de la lista.
        // Devuelve estatus 200 con ItemConTagsDto.
        return null;
    }

    /**
     * Reto Extra 3: PATCH implícito para incrementar un contador.
     */
    // GUÍA: teoría 5.3 — PATCH sin cuerpo que solo "muta" un campo (incrementar).
    // 1. Construye un Map<String,Object> con "id" = id y "visitas" = 11 (el valor
    //    base 10 + 1) y devuélvelo con ResponseEntity.ok(...).
    //    PISTA: un LinkedHashMap con dos put, o Map.of("id", id, "visitas", 11).
    // OJO: el test (sin cuerpo) espera $.visitas == 11.
    // TODO extra: anota con @PatchMapping("/{id}/incrementar").
    public org.springframework.http.ResponseEntity<java.util.Map<String, Object>> patchIncrementarContador(
            @org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el incremento de un contador de visitas para el recurso.
        // Devuelve estatus 200 con un Map conteniendo la clave "id" y "visitas" (incrementado a 11).
        return null;
    }

    /**
     * Reto Extra 4: Simulador simple de JSON Patch (RFC 6902).
     */
    // GUÍA: teoría 5.3 — JSON Patch (RFC 6902) es una LISTA de operaciones
    //   {op, path, value} que el servidor aplica en orden.
    // 1. Parte de nombre="viejo", activo=true. Recorre 'operaciones'; para cada una
    //    si op=="replace" y path=="/nombre" → nombre = (String) op.get("value").
    //    (con extenderlo a /activo basta para el patrón).
    // 2. return ResponseEntity.ok(new ItemDto(id, nombre, activo));
    // OJO: el test manda [{"op":"replace","path":"/nombre","value":"cambiado"}] y
    //   espera $.nombre=="cambiado". Cada operación es un Map<String,Object>.
    // CULTURA: librerías como json-patch hacen esto completo (add/remove/move/test).
    // TODO extra: anota con @PatchMapping("/{id}/rfc6902").
    public org.springframework.http.ResponseEntity<ItemDto> patchConJsonPatch(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.List<java.util.Map<String, Object>> operaciones) {
        // TODO extra: recorre la lista de operaciones. Cada operación tiene "op" (p.ej. "replace"), "path" (p.ej. "/nombre"), y "value" (p.ej. "nuevo").
        // Aplica las operaciones al recurso base (nombre="viejo", activo=true) y devuelve estatus 200 con el ItemDto modificado.
        return null;
    }

    /**
     * Reto Extra 5: Validación estricta del tipo de datos de entrada.
     */
    // GUÍA: teoría 5.3 — al recibir Map<String,Object> tú validas los tipos a mano
    //   (Jackson no sabe que "activo" debe ser boolean).
    // 1. Si cambios contiene "activo" y su valor NO es Boolean → 400 con body
    //    "Tipo de dato invalido para activo".
    //    PISTA: !(cambios.get("activo") instanceof Boolean).
    // 2. Si pasa → 200 (con el ItemDto o un ok vacío).
    // OJO: el test manda {"activo":"no-booleano"} → 400 y {"activo":false} → 200.
    //   En JSON, false llega como Boolean; "no-booleano" como String.
    // TODO extra: anota con @PatchMapping("/{id}/valida-tipos").
    public org.springframework.http.ResponseEntity<?> patchValidarTipos(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: si el mapa contiene la clave "activo" y el valor no es un Boolean (o no es true/false),
        // devuelve estatus 400 (Bad Request) con cuerpo "Tipo de dato invalido para activo".
        return null;
    }

    /**
     * Reto Extra 6: Protección contra modificación de campos de solo lectura (como ID).
     */
    // GUÍA: teoría 5.3 — hay campos de solo lectura (el id) que el PATCH no debe tocar.
    // 1. Si cambios.containsKey("id") → 400 con body "No esta permitido modificar el ID".
    // 2. Si no → 200 (con el ItemDto o un ok).
    // OJO: el test manda {"id":2,...} → 400 y {"nombre":"nuevo"} (sin id) → 200.
    //   El mensaje debe ser EXACTO (sin tilde en "esta", tal como lo pide el test).
    // TODO extra: anota con @PatchMapping("/{id}/readonly").
    public org.springframework.http.ResponseEntity<?> patchNoPermitido(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: si el mapa de cambios contiene la clave "id", devuelve estatus 400 (Bad Request)
        // con cuerpo "No esta permitido modificar el ID".
        return null;
    }

    /**
     * Reto Extra 7: PATCH con cabecera de auditoría del usuario que modifica.
     */
    // GUÍA: teoría 5.2/5.3 — combina @RequestBody + @RequestHeader y devuelve quién
    //   hizo el cambio (auditoría).
    // 1. Construye un Map con el recurso actualizado (p.ej. "id", "nombre") y añade
    //    "modificadoPor" = user (valor de la cabecera X-User).
    // 2. return ResponseEntity.ok(mapa);
    // OJO: el test manda header X-User=obitica y espera $.modificadoPor=="obitica".
    // TODO extra: anota con @PatchMapping("/{id}/auditoria").
    public org.springframework.http.ResponseEntity<java.util.Map<String, Object>> patchAuditoria(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios,
            @org.springframework.web.bind.annotation.RequestHeader("X-User") String user) {
        // TODO extra: devuelve estatus 200 y un Map con las claves del recurso actualizado y la clave "modificadoPor" con el valor de la cabecera X-User.
        return null;
    }

    /**
     * Reto Extra 8: Validación cruzada de campos en actualización parcial.
     */
    // GUÍA: teoría 5.3 — validación CRUZADA: dos campos que, combinados, son inválidos.
    // 1. Si en cambios viene "activo" == false Y además viene "nombre" → 422 con
    //    body "No se puede renombrar un recurso que se desactiva".
    //    PISTA: Boolean.FALSE.equals(cambios.get("activo")) && cambios.containsKey("nombre").
    // 2. Si no → 200.
    // OJO: el test manda {"activo":false,"nombre":"nuevo"} → 422 y
    //   {"activo":true,"nombre":"nuevo"} → 200.
    // TODO extra: anota con @PatchMapping("/{id}/estado-exclusivo").
    public org.springframework.http.ResponseEntity<?> patchEstadoExclusivo(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: si en la misma petición se intenta cambiar "activo" a false y también se proporciona un nuevo "nombre",
        // devuelve estatus 422 (Unprocessable Entity) con cuerpo "No se puede renombrar un recurso que se desactiva".
        return null;
    }

    /**
     * Reto Extra 9: Control de conflictos y recursos bloqueados (Conflict).
     */
    // GUÍA: teoría 5.3 — 409 Conflict para un recurso "bloqueado".
    // 1. Si id == 99 → 409 con body "Recurso bloqueado contra modificaciones".
    // 2. Cualquier otro id → 200.
    // OJO: el test usa id=99 → 409 e id=1 → 200. Mismo patrón que Ej050 reto 8 pero
    //   con PATCH.
    // TODO extra: anota con @PatchMapping("/{id}/bloqueado").
    public org.springframework.http.ResponseEntity<?> patchLanzaConflicto(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios) {
        // TODO extra: si el id es 99, simulamos que el recurso está bloqueado.
        // Devuelve estatus 409 (Conflict) con cuerpo "Recurso bloqueado contra modificaciones".
        return null;
    }

    /**
     * Reto Extra 10: PATCH condicional condicionado a la cabecera If-Match.
     */
    // GUÍA: teoría 5.3 — mismo patrón If-Match que Ej050 reto 3, ahora con PATCH.
    // 1. En cascada sobre ifMatch:
    //    - null/vacío           → 428 Precondition Required.
    //    - distinto de "\"v1\"" → 412 Precondition Failed.
    //    - igual a "\"v1\""     → 200.
    // OJO: el test cubre sin header → 428, "\"v2\"" → 412 y "\"v1\"" → 200.
    //   Reutiliza la lógica que ya razonaste en Ej050 reto 3.
    // TODO extra: anota con @PatchMapping("/{id}/conditional").
    public org.springframework.http.ResponseEntity<?> patchCondicionalEtag(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios,
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-Match", required = false) String ifMatch) {
        // TODO extra: si la cabecera If-Match no está presente, devuelve estatus 428 (Precondition Required).
        // Si no coincide con el valor esperado "\"v1\"", devuelve estatus 412 (Precondition Failed).
        return null;
    }

}
