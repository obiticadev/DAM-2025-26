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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/optimistic")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/upsert")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/conditional")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/valida-formato")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/historial")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/lanza-404")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/solo-cabeceras")
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
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/estado-activo")
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
        // TODO extra: recibe una lista de ItemDto y devuelve estatus 200 con la misma lista reflejada.
        return null;
    }

    /**
     * Reto Extra 10: Validación cruzada de identificadores de ruta y cuerpo.
     */
    // TODO extra: anota con @org.springframework.web.bind.annotation.PutMapping("/{id}/verificar-id")
    public org.springframework.http.ResponseEntity<?> actualizarValidandoIdCuerpo(
            @org.springframework.web.bind.annotation.PathVariable long id,
            @org.springframework.web.bind.annotation.RequestBody ItemDto cuerpo) {
        // TODO extra: si el id de la ruta no coincide con el id del cuerpo JSON,
        // devuelve estatus 400 (Bad Request) con cuerpo "Identificadores no coinciden".
        // Si coinciden, devuelve estatus 200 con el ItemDto.
        return null;
    }

}
