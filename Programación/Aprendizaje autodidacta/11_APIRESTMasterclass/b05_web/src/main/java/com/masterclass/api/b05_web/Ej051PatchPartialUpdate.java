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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 1: PATCH utilizando DTO parcial.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/dto")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 2: PATCH para modificar una colección anidada (tags).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/tags")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 3: PATCH implícito para incrementar un contador.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/incrementar")
    public org.springframework.http.ResponseEntity<java.util.Map<String, Object>> patchIncrementarContador(
            @org.springframework.web.bind.annotation.PathVariable long id) {
        // TODO extra: simula el incremento de un contador de visitas para el recurso.
        // Devuelve estatus 200 con un Map conteniendo la clave "id" y "visitas" (incrementado a 11).
        return null;
    }

    /**
     * Reto Extra 4: Simulador simple de JSON Patch (RFC 6902).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 4: Simulador simple de JSON Patch (RFC 6902).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/rfc6902")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 5: Validación estricta del tipo de datos de entrada.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/valida-tipos")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 6: Protección contra modificación de campos de solo lectura (como ID).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/readonly")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 7: PATCH con cabecera de auditoría del usuario que modifica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/auditoria")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 8: Validación cruzada de campos en actualización parcial.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/estado-exclusivo")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 9: Control de conflictos y recursos bloqueados (Conflict).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/bloqueado")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PatchMapping("/{
        // TODO extra: Reto Extra 10: PATCH condicional condicionado a la cabecera If-Match.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para PatchMapping");
    }/conditional")
    public org.springframework.http.ResponseEntity<?> patchCondicionalEtag(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody java.util.Map<String, Object> cambios,
            @org.springframework.web.bind.annotation.RequestHeader(value = "If-Match", required = false) String ifMatch) {
        // TODO extra: si la cabecera If-Match no está presente, devuelve estatus 428 (Precondition Required).
        // Si no coincide con el valor esperado "\"v1\"", devuelve estatus 412 (Precondition Failed).
        return null;
    }

}
