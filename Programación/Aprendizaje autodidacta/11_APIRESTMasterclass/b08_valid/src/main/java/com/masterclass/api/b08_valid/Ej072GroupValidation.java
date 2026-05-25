package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ejercicio 072 · Grupos de validación (Create vs Update).
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.3).
 *
 * <p>Al crear, el id debe ser null; al actualizar, obligatorio. Mismo DTO,
 * distintas reglas según el grupo.
 */
public final class Ej072GroupValidation {

    /** Marcadores de grupo (interfaces vacías). */
    public interface OnCreate {
    }

    public interface OnUpdate {
    }

    public static class RecursoDto {
        // TODO 1: 'id' con @Null(groups = OnCreate.class) — al crear NO debe venir id.
        // TODO 2: 'id' con @NotNull(groups = OnUpdate.class) — al actualizar es obligatorio.
        public Long id;

        // TODO 3: 'nombre' con @NotBlank aplicando a AMBOS grupos
        //         (groups = {OnCreate.class, OnUpdate.class}).
        public String nombre;

        public RecursoDto(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej072GroupValidation() {
    }

    /**
     * Valida bajo el grupo de creación.
     *
     * @param dto recurso
     * @return campos inválidos según OnCreate
     */
    public static Set<String> validarCreate(RecursoDto dto) {
        // TODO 4: invoca VALIDATOR.validate(dto, OnCreate.class).
        // TODO 5: mapea a nombres de propiedad y recoge a Set.
        return VALIDATOR.validate(dto, OnCreate.class).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    /**
     * Valida bajo el grupo de actualización.
     *
     * @param dto recurso
     * @return campos inválidos según OnUpdate
     */
    public static Set<String> validarUpdate(RecursoDto dto) {
        // TODO 6: invoca VALIDATOR.validate(dto, OnUpdate.class).
        // TODO 7: el método ya está hecho; lo que decide el resultado son los 'groups'.
        // TODO 8: con id=null en Create -> válido; en Update -> inválido.
        // TODO 9: con id!=null en Create -> inválido; en Update -> válido.
        // TODO 10: 'nombre' vacío es inválido en ambos grupos.
        return VALIDATOR.validate(dto, OnUpdate.class).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(validarCreate(new RecursoDto(5L, "x")));
    }

    /**
     * RETO EXTRA 1: Comprobar si el recurso es totalmente válido bajo el grupo OnCreate.
     *
     * @param dto recurso DTO
     * @return true si no incumple ninguna restricción de creación
     */
    public static boolean esValidoParaCrear(RecursoDto dto) {
        // TODO extra: RETO EXTRA 1: Comprobar si el recurso es totalmente válido bajo el grupo OnCreate.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidoParaCrear");
    }

    /**
     * RETO EXTRA 2: Comprobar si el recurso es totalmente válido bajo el grupo OnUpdate.
     *
     * @param dto recurso DTO
     * @return true si no incumple ninguna restricción de actualización
     */
    public static boolean esValidoParaActualizar(RecursoDto dto) {
        // TODO extra: RETO EXTRA 2: Comprobar si el recurso es totalmente válido bajo el grupo OnUpdate.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValidoParaActualizar");
    }

    /**
     * RETO EXTRA 3: Validar que un nombre no sea nulo ni vacío de forma genérica.
     *
     * @param nombre nombre a validar
     * @return true si es correcto
     */
    public static boolean esNombreCorrecto(String nombre) {
        // TODO extra: RETO EXTRA 3: Validar que un nombre no sea nulo ni vacío de forma genérica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreCorrecto");
    }

    /**
     * RETO EXTRA 4: Validar explícitamente la presencia de ID cuando se realiza una actualización.
     *
     * @param dto recurso DTO
     * @return true si tiene un ID no nulo
     */
    public static boolean tieneIdSiendoUpdate(RecursoDto dto) {
        // TODO extra: RETO EXTRA 4: Validar explícitamente la presencia de ID cuando se realiza una actualización.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneIdSiendoUpdate");
    }

    /**
     * RETO EXTRA 5: Validar explícitamente la ausencia de ID en un contexto de creación.
     *
     * @param dto recurso DTO
     * @return true si el ID es nulo
     */
    public static boolean careceDeIdSiendoCreate(RecursoDto dto) {
        // TODO extra: RETO EXTRA 5: Validar explícitamente la ausencia de ID en un contexto de creación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para careceDeIdSiendoCreate");
    }

    /**
     * RETO EXTRA 6: Validar el DTO usando el grupo por defecto (Default de Jakarta).
     *
     * @param dto recurso DTO
     * @return conjunto de propiedades inválidas bajo el grupo por defecto
     */
    public static Set<String> obtenerCamposInvalidosGlobal(RecursoDto dto) {
        // TODO extra: RETO EXTRA 6: Validar el DTO usando el grupo por defecto (Default de Jakarta).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCamposInvalidosGlobal");
    }

    /**
     * RETO EXTRA 7: Preparar un DTO para ser guardado forzando su ID a null.
     *
     * @param dto DTO de entrada
     * @return copia de DTO con ID nulo
     */
    public static RecursoDto prepararParaCrear(RecursoDto dto) {
        // TODO extra: RETO EXTRA 7: Preparar un DTO para ser guardado forzando su ID a null.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prepararParaCrear");
    }

    /**
     * RETO EXTRA 8: Asegurar que el DTO tenga un ID establecido para actualizarse, usando un valor por defecto si es nulo.
     *
     * @param dto DTO de entrada
     * @param idDefecto ID a asignar si es nulo
     * @return copia de DTO con ID asignado
     */
    public static RecursoDto prepararParaActualizar(RecursoDto dto, Long idDefecto) {
        // TODO extra: RETO EXTRA 8: Asegurar que el DTO tenga un ID establecido para actualizarse, usando un valor por defecto si es nulo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prepararParaActualizar");
    }

    /**
     * RETO EXTRA 9: Comparar si dos clases de grupo de validación representan el mismo grupo.
     *
     * @param g1 primer grupo
     * @param g2 segundo grupo
     * @return true si coinciden
     */
    public static boolean esIgualGrupo(Class<?> g1, Class<?> g2) {
        // TODO extra: RETO EXTRA 9: Comparar si dos clases de grupo de validación representan el mismo grupo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIgualGrupo");
    }

    /**
     * RETO EXTRA 10: Validar el recurso aplicando un conjunto variable de grupos de validación.
     *
     * @param dto recurso DTO
     * @param grupos varargs de clases de grupos
     * @return conjunto de propiedades inválidas
     */
    public static Set<String> validarConGruposMultiples(RecursoDto dto, Class<?>... grupos) {
        // TODO extra: RETO EXTRA 10: Validar el recurso aplicando un conjunto variable de grupos de validación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarConGruposMultiples");
    }

}
