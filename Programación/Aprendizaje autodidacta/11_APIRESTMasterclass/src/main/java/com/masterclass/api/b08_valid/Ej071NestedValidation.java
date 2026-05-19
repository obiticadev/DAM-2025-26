package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Ejercicio 071 · Validación de objetos anidados (@Valid en cascada).
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 */
public final class Ej071NestedValidation {

    public static class Direccion {
        // TODO 1: 'calle' con @NotBlank.
        public String calle;
        // TODO 2: 'cp' con @Pattern(regexp = "\\d{5}").
        public String cp;

        public Direccion(String calle, String cp) {
            this.calle = calle;
            this.cp = cp;
        }
    }

    public static class Cliente {
        // TODO 3: 'nombre' con @NotBlank.
        public String nombre;

        // TODO 4: anota 'direccion' con @NotNull.
        // TODO 5: anota 'direccion' con @jakarta.validation.Valid para validar EN CASCADA.
        // TODO 6: sin @Valid las constraints internas de Direccion NO se evalúan.
        public Direccion direccion;

        public Cliente(String nombre, Direccion direccion) {
            this.nombre = nombre;
            this.direccion = direccion;
        }
    }

    private static final Validator VALIDATOR =
            Validation.buildDefaultValidatorFactory().getValidator();

    private Ej071NestedValidation() {
    }

    /**
     * @param c cliente con dirección anidada
     * @return rutas de propiedad inválidas (incluye "direccion.calle" si la cascada funciona)
     */
    public static Set<String> rutasInvalidas(Cliente c) {
        // TODO 7: método infraestructura ya hecho.
        // TODO 8: para que aparezca "direccion.calle" debe estar @Valid en el campo.
        // TODO 9: para que aparezca "direccion" (null) debe estar @NotNull.
        // TODO 10: el test comprobará rutas concretas: ajusta las anotaciones.
        return VALIDATOR.validate(c).stream()
                .map(v -> v.getPropertyPath().toString())
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        System.out.println(rutasInvalidas(new Cliente("Ana", new Direccion("", "abc"))));
    }
}
