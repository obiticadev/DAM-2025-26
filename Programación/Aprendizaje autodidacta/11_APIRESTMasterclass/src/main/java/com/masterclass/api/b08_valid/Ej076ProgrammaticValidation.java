package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Ejercicio 076 · Validación programática con Validator.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.1).
 *
 * <p>A veces validas a mano (fuera de un controller). Aquí obtienes los mensajes
 * de error legibles para construir una respuesta.
 */
public final class Ej076ProgrammaticValidation {

    public static class ComentarioDto {
        @NotBlank(message = "el texto es obligatorio")
        public String texto;

        public ComentarioDto(String texto) {
            this.texto = texto;
        }
    }

    private Ej076ProgrammaticValidation() {
    }

    /**
     * Valida programáticamente y devuelve los mensajes de error ordenados.
     *
     * @param dto comentario
     * @return lista de mensajes (vacía si es válido)
     */
    public static List<String> mensajesDeError(ComentarioDto dto) {
        // TODO 1: construye un ValidatorFactory con Validation.buildDefaultValidatorFactory().
        // TODO 2: obtén el Validator con factory.getValidator().
        // TODO 3: ejecuta validator.validate(dto) para obtener las violaciones.
        // TODO 4: si no hay violaciones, devuelve una lista vacía.
        // TODO 5: mapea cada ConstraintViolation a getMessage().
        // TODO 6: ordena los mensajes alfabéticamente (salida determinista para el test).
        // TODO 7: recoge a List.
        // TODO 8: cierra/usa el factory de forma responsable (try-with-resources si procede).
        // TODO 9: este enfoque sirve fuera de Spring (servicios, batch, etc.).
        // TODO 10: devuelve la lista de mensajes.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(mensajesDeError(new ComentarioDto("")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: construye un ValidatorFactory con Validation.buildDefaultValidatorFactory().
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: obtén el Validator con factory.getValidator().
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: ejecuta validator.validate(dto) para obtener las violaciones.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si no hay violaciones, devuelve una lista vacía.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: mapea cada ConstraintViolation a getMessage().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: ordena los mensajes alfabéticamente (salida determinista para el test).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: recoge a List.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: cierra/usa el factory de forma responsable (try-with-resources si procede).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: este enfoque sirve fuera de Spring (servicios, batch, etc.).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la lista de mensajes.
    }

}
