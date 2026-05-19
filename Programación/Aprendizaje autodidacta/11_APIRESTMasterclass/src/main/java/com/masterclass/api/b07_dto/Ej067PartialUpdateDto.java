package com.masterclass.api.b07_dto;

import java.util.Optional;

/**
 * Ejercicio 067 · DTO de actualización parcial (PATCH).
 *
 * <p>Teoría: {@code teoria/07_DTOs_y_Mapeo.md} (sección 7.2).
 *
 * <p>Un PatchDto usa Optional para distinguir "no enviado" de "enviado como null".
 */
public final class Ej067PartialUpdateDto {

    public record Usuario(String nombre, String email, boolean activo) {
    }

    /** Campos opcionales: ausente = no tocar. */
    public record PatchDto(Optional<String> nombre, Optional<String> email, Optional<Boolean> activo) {
    }

    private Ej067PartialUpdateDto() {
    }

    /**
     * Aplica el parche: solo los Optional presentes modifican el usuario.
     *
     * @param actual estado actual
     * @param patch  cambios opcionales
     * @return nuevo Usuario con los cambios aplicados
     * @throws IllegalArgumentException si actual o patch son null
     */
    public static Usuario aplicar(Usuario actual, PatchDto patch) {
        // TODO 1: si actual o patch son null -> IllegalArgumentException.
        // TODO 2: parte de los valores actuales como base.
        // TODO 3: si patch.nombre() está presente, usa su valor; si no, conserva el actual.
        // TODO 4: aplica la misma regla para email.
        // TODO 5: aplica la misma regla para activo.
        // TODO 6: usa Optional.orElse(valorActual) para resolver cada campo.
        // TODO 7: NO trates Optional.empty() como "poner a null": significa "no tocar".
        // TODO 8: el record es inmutable: crea uno nuevo (no muta 'actual').
        // TODO 9: construye el nuevo Usuario con los 3 campos resueltos.
        // TODO 10: devuélvelo.
        return null;
    }

    public static void main(String[] args) {
        var u = new Usuario("Ana", "a@b.com", true);
        System.out.println(aplicar(u, new PatchDto(Optional.of("Bea"), Optional.empty(), Optional.empty())));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si actual o patch son null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: parte de los valores actuales como base.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si patch.nombre() está presente, usa su valor; si no, conserva el actual.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: aplica la misma regla para email.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: aplica la misma regla para activo.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: usa Optional.orElse(valorActual) para resolver cada campo.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: NO trates Optional.empty() como "poner a null": significa "no tocar".
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: el record es inmutable: crea uno nuevo (no muta 'actual').
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: construye el nuevo Usuario con los 3 campos resueltos.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuélvelo.
    }

}
