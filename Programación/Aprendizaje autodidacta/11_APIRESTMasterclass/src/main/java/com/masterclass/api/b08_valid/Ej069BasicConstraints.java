package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ejercicio 069 · Constraints básicas.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>El ejercicio consiste en ANOTAR los campos del DTO. El método {@code validar}
 * (infraestructura) ya está hecho: ejecuta el validador y devuelve los campos
 * que incumplen alguna regla.
 */
public final class Ej069BasicConstraints {

    /** DTO de registro. Debes añadir las constraints indicadas. */
    public static class RegistroDto {
        // TODO 1: anota 'nombre' con @jakarta.validation.constraints.NotBlank.
        // TODO 2: anota además 'nombre' con @Size(min=2, max=50).
        public String nombre;

        // TODO 3: anota 'email' con @NotBlank.
        // TODO 4: anota 'email' con @Email.
        public String email;

        // TODO 5: anota 'edad' con @NotNull.
        // TODO 6: anota 'edad' con @Min(18) (mayoría de edad).
        // TODO 7: anota 'edad' con @Max(120) (límite razonable).
        public Integer edad;

        // TODO 8: anota 'telefono' con @Pattern(regexp = "\\d{9}") (9 dígitos).
        public String telefono;

        public RegistroDto(String nombre, String email, Integer edad, String telefono) {
            this.nombre = nombre;
            this.email = email;
            this.edad = edad;
            this.telefono = telefono;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej069BasicConstraints() {
    }

    /**
     * Devuelve el conjunto de nombres de propiedad que incumplen alguna constraint.
     *
     * @param dto objeto a validar
     * @return nombres de campos inválidos (vacío si todo es válido)
     */
    public static Set<String> camposInvalidos(RegistroDto dto) {
        // TODO 9: este método ya está implementado (infraestructura de validación).
        // TODO 10: tu trabajo es que las ANOTACIONES de arriba hagan que este Set
        //          contenga exactamente los campos inválidos esperados por el test.
        return VALIDATOR.validate(dto).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(camposInvalidos(new RegistroDto("", "malo", 10, "abc")));
    }
}
