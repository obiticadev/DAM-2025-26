package com.masterclass.api.b09_err;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 080 · Payload de errores de validación agrupados.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.2).
 *
 * <p>Cuando varias reglas fallan, el cliente quiere un mapa campo→[mensajes],
 * no un texto plano.
 */
public final class Ej080ValidationErrorPayload {

    /** Un error de campo individual (lo que produce el validador). */
    public record FieldError(String campo, String mensaje) {
    }

    private Ej080ValidationErrorPayload() {
    }

    /**
     * Agrupa una lista de errores por campo.
     *
     * @param errores lista de errores individuales (puede repetir campo)
     * @return mapa campo → lista de mensajes (orden de aparición preservado)
     */
    public static Map<String, List<String>> agrupar(List<FieldError> errores) {
        // TODO 1: si errores es null -> IllegalArgumentException.
        // TODO 2: usa un LinkedHashMap para conservar el orden de primera aparición.
        // TODO 3: recorre cada FieldError.
        // TODO 4: si el campo no está en el mapa, crea una lista nueva (computeIfAbsent).
        // TODO 5: añade el mensaje a la lista de ese campo.
        // TODO 6: un mismo campo puede acumular varios mensajes (p.ej. NotBlank + Size).
        // TODO 7: no descartes duplicados salvo que sean idénticos (decisión: conservarlos).
        // TODO 8: el resultado debe ser estable y reproducible.
        // TODO 9: si la lista está vacía, devuelve un mapa vacío (no null).
        // TODO 10: devuelve el mapa agrupado.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(agrupar(List.of(
                new FieldError("email", "obligatorio"),
                new FieldError("email", "formato inválido"))));
    }
}
