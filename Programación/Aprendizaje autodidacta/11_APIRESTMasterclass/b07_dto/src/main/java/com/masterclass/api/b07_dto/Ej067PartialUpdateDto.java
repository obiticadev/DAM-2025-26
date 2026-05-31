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

    /**
     * RETO EXTRA 1: Comprobar si el PatchDto está completamente vacío.
     *
     * @param patch parche DTO
     * @return true si todos los campos son Optional.empty()
     */
    public static boolean esVacio(PatchDto patch) {
        // TODO extra: RETO EXTRA 1: Comprobar si el PatchDto está completamente vacío.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVacio");
    }

    /**
     * RETO EXTRA 2: Comprobar si el PatchDto es completo (todos los campos presentes).
     *
     * @param patch parche DTO
     * @return true si todos los campos están presentes (of/ofNullable)
     */
    public static boolean esCompleto(PatchDto patch) {
        // TODO extra: RETO EXTRA 2: Comprobar si el PatchDto es completo (todos los campos presentes).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCompleto");
    }

    /**
     * RETO EXTRA 3: Validar el formato del email en PatchDto si está presente.
     *
     * @param patch parche DTO
     * @return true si no está presente o, si lo está, contiene '@'
     */
    public static boolean validarEmail(PatchDto patch) {
        // TODO extra: RETO EXTRA 3: Validar el formato del email en PatchDto si está presente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarEmail");
    }

    /**
     * RETO EXTRA 4: Crear un PatchDto que solo actualice el email.
     *
     * @param email nuevo email
     * @return PatchDto con solo email presente
     */
    public static PatchDto crearSoloEmail(String email) {
        // TODO extra: RETO EXTRA 4: Crear un PatchDto que solo actualice el email.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearSoloEmail");
    }

    /**
     * RETO EXTRA 5: Combinar dos PatchDto (el segundo sobrescribe al primero si tiene valores presentes).
     *
     * @param p1 primer parche (base)
     * @param p2 segundo parche (prioritario)
     * @return parche combinado
     */
    public static PatchDto combinarParches(PatchDto p1, PatchDto p2) {
        // TODO extra: RETO EXTRA 5: Combinar dos PatchDto (el segundo sobrescribe al primero si tiene valores presentes).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarParches");
    }

    /**
     * RETO EXTRA 6: Crear un PatchDto para desactivar explícitamente a un usuario.
     *
     * @return PatchDto con activo=false
     */
    public static PatchDto desactivarUsuario() {
        // TODO extra: RETO EXTRA 6: Crear un PatchDto para desactivar explícitamente a un usuario.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desactivarUsuario");
    }

    /**
     * RETO EXTRA 7: Contar cuántos campos solicita modificar el PatchDto.
     *
     * @param patch parche DTO
     * @return cantidad de campos presentes (0 a 3)
     */
    public static int contarCamposModificados(PatchDto patch) {
        // TODO extra: RETO EXTRA 7: Contar cuántos campos solicita modificar el PatchDto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarCamposModificados");
    }

    /**
     * RETO EXTRA 8: Determinar si el nombre está marcado para modificación.
     *
     * @param patch parche DTO
     * @return true si el nombre está presente
     */
    public static boolean esNombreModificado(PatchDto patch) {
        // TODO extra: RETO EXTRA 8: Determinar si el nombre está marcado para modificación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreModificado");
    }

    /**
     * RETO EXTRA 9: Crear un PatchDto a partir de valores que pueden ser nulos.
     *
     * @param nombre nombre nullable
     * @param email email nullable
     * @param activo activo nullable
     * @return PatchDto correspondiente (nulo mapea a empty)
     */
    public static PatchDto crearDesdeNullables(String nombre, String email, Boolean activo) {
        // TODO extra: RETO EXTRA 9: Crear un PatchDto a partir de valores que pueden ser nulos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDesdeNullables");
    }

    /**
     * RETO EXTRA 10: Aplicar el reverso de un parche (revertir cambios para auditoría).
     *
     * @param modificado usuario ya modificado
     * @param patch parche que fue aplicado
     * @return Usuario con los campos revertidos a su estado original (vacíos en el parche)
     */
    public static Usuario aplicarInverso(Usuario modificado, PatchDto patch) {
        // TODO extra: RETO EXTRA 10: Aplicar el reverso de un parche (revertir cambios para auditoría).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarInverso");
    }

}
