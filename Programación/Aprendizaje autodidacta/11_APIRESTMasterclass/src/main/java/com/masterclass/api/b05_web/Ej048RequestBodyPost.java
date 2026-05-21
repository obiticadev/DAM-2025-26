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
        // TODO extra: convierte cada ItemIn a ItemOut con IDs secuenciales 1, 2, etc.
        // Devuelve un Map con claves "total" (tamaño) y "items" (lista de ItemOut), y estatus 201.
        return null;
    }

    /**
     * Reto Extra 2: Validación manual y estatus 422 Unprocessable Entity.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/validar")
    public org.springframework.http.ResponseEntity<ItemOut> crearConValidacion(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // TODO extra: si el nombre es menor a 3 caracteres, devuelve ResponseEntity con status 422 (UNPROCESSABLE_ENTITY).
        // Si es correcto, devuelve status 201 y un ItemOut con id=1.
        return null;
    }

    /**
     * Reto Extra 3: Deserialización de tipos de datos complejos y anidados.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/metadatos")
    public org.springframework.http.ResponseEntity<ItemConMetadatos> crearConMetadatos(
            @org.springframework.web.bind.annotation.RequestBody ItemConMetadatos entrada) {
        // TODO extra: devuelve estatus 201 y el mismo objeto recibido en el cuerpo.
        return null;
    }

    /**
     * Reto Extra 4: Restricción del tipo de contenido consumido (consumes).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping(value = "/json-only", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public org.springframework.http.ResponseEntity<ItemOut> crearConTipoContent(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // TODO extra: devuelve estatus 201 y un ItemOut con id=1 y el nombre de la entrada.
        return null;
    }

    /**
     * Reto Extra 5: Vinculación combinada de cabecera y cuerpo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/audit")
    public org.springframework.http.ResponseEntity<String> crearConCabeceraAudit(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada,
            @org.springframework.web.bind.annotation.RequestHeader("X-Created-By") String creador) {
        // TODO extra: devuelve un ResponseEntity con el texto "Item " + entrada.nombre() + " creado por " + creador, y estatus 201.
        return null;
    }

    /**
     * Reto Extra 6: Validación de cuerpo nulo/vacío defensivo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/defensivo")
    public org.springframework.http.ResponseEntity<String> crearSinNombre(
            @org.springframework.web.bind.annotation.RequestBody(required = false) ItemIn entrada) {
        // TODO extra: si entrada es null o entrada.nombre() es nulo/vacío, devuelve status 400 (Bad Request) con cuerpo "cuerpo invalido".
        // Si no, devuelve status 200 con cuerpo "ok".
        return null;
    }

    /**
     * Reto Extra 7: Creación con id específico y detección de conflicto (409).
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/especifico")
    public org.springframework.http.ResponseEntity<ItemOut> crearConIdEspecifico(
            @org.springframework.web.bind.annotation.RequestBody ItemOut entrada) {
        // TODO extra: si el ID es menor o igual a 0, devuelve 400.
        // Si el ID es exactamente 99, simula conflicto y devuelve 409 Conflict.
        // Si no, devuelve 201 y la misma entrada.
        return null;
    }

    /**
     * Reto Extra 8: Construcción de Location con URL absoluta del servidor.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/absoluto")
    public org.springframework.http.ResponseEntity<ItemOut> crearRetornandoLocationAbsoluta(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada,
            jakarta.servlet.http.HttpServletRequest request) {
        // TODO extra: genera la Location absoluta utilizando org.springframework.web.servlet.support.ServletUriComponentsBuilder
        // a partir del contexto del request, apuntando a "/api/items/123".
        // Devuelve ResponseEntity con Location, status 201 y cuerpo conteniendo un ItemOut con id=123.
        return null;
    }

    /**
     * Reto Extra 9: Respuesta simplificada devolviendo únicamente el ID.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/solo-id")
    public org.springframework.http.ResponseEntity<IdOut> crearRetornandoSoloId(
            @org.springframework.web.bind.annotation.RequestBody ItemIn entrada) {
        // TODO extra: devuelve estatus 201 y un nuevo IdOut con id=999L.
        return null;
    }

    /**
     * Reto Extra 10: Deserialización de LocalDate en RequestBody.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PostMapping("/fecha")
    public org.springframework.http.ResponseEntity<ItemConFecha> crearConLocalDate(
            @org.springframework.web.bind.annotation.RequestBody ItemConFecha entrada) {
        // TODO extra: devuelve estatus 201 y el mismo objeto recibido.
        return null;
    }

}
