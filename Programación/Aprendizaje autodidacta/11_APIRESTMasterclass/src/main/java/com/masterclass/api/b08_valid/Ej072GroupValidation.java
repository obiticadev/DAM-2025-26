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
}
