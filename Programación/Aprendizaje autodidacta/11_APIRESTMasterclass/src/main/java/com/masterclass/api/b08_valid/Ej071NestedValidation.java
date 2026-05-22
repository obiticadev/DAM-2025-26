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

    /**
     * RETO EXTRA 1: Comprobar si una calle es válida (no vacía y no nula).
     *
     * @param calle nombre de la calle
     * @return true si es correcta
     */
    public static boolean esCalleValida(String calle) {
        // TODO extra: RETO EXTRA 1: Comprobar si una calle es válida (no vacía y no nula).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCalleValida");
    }

    /**
     * RETO EXTRA 2: Comprobar si un código postal es válido (5 dígitos).
     *
     * @param cp código postal
     * @return true si cumple el patrón de 5 dígitos
     */
    public static boolean esCpValido(String cp) {
        // TODO extra: RETO EXTRA 2: Comprobar si un código postal es válido (5 dígitos).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCpValido");
    }

    /**
     * RETO EXTRA 3: Comprobar si un nombre de cliente es válido.
     *
     * @param nombre nombre del cliente
     * @return true si es válido
     */
    public static boolean esNombreValido(String nombre) {
        // TODO extra: RETO EXTRA 3: Comprobar si un nombre de cliente es válido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreValido");
    }

    /**
     * RETO EXTRA 4: Comprobar si el cliente tiene dirección establecida.
     *
     * @param c cliente
     * @return true si la dirección no es nula
     */
    public static boolean tieneDireccion(Cliente c) {
        // TODO extra: RETO EXTRA 4: Comprobar si el cliente tiene dirección establecida.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDireccion");
    }

    /**
     * RETO EXTRA 5: Validar una dirección de forma directa e independiente.
     *
     * @param d dirección
     * @return conjunto de propiedades inválidas de la dirección
     */
    public static Set<String> validarDireccionDirecta(Direccion d) {
        // TODO extra: RETO EXTRA 5: Validar una dirección de forma directa e independiente.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarDireccionDirecta");
    }

    /**
     * RETO EXTRA 6: Determinar si la dirección está completamente libre de errores de validación.
     *
     * @param d dirección
     * @return true si es válida
     */
    public static boolean esDireccionCompleta(Direccion d) {
        // TODO extra: RETO EXTRA 6: Determinar si la dirección está completamente libre de errores de validación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDireccionCompleta");
    }

    /**
     * RETO EXTRA 7: Validar programáticamente si el cliente y su dirección anidada son correctos.
     *
     * @param c cliente
     * @return true si pasa todas las validaciones
     */
    public static boolean esClienteValido(Cliente c) {
        // TODO extra: RETO EXTRA 7: Validar programáticamente si el cliente y su dirección anidada son correctos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClienteValido");
    }

    /**
     * RETO EXTRA 8: Obtener únicamente los errores procedentes del objeto anidado dirección.
     *
     * @param c cliente
     * @return conjunto de rutas que empiezan por "direccion."
     */
    public static Set<String> obtenerErroresDeDireccion(Cliente c) {
        // TODO extra: RETO EXTRA 8: Obtener únicamente los errores procedentes del objeto anidado dirección.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerErroresDeDireccion");
    }

    /**
     * RETO EXTRA 9: Comprobar si el código postal pertenece a una provincia concreta por su prefijo.
     *
     * @param cp código postal
     * @param prefijoProvincia prefijo de 2 dígitos (ej: "28" para Madrid)
     * @return true si pertenece
     */
    public static boolean cpPerteneceAProvincia(String cp, String prefijoProvincia) {
        // TODO extra: RETO EXTRA 9: Comprobar si el código postal pertenece a una provincia concreta por su prefijo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cpPerteneceAProvincia");
    }

    /**
     * RETO EXTRA 10: Limpiar y normalizar los espacios en blanco del nombre del cliente y calle.
     *
     * @param c cliente
     * @return copia de Cliente con strings limpios
     */
    public static Cliente clonarYLimpiarCliente(Cliente c) {
        // TODO extra: RETO EXTRA 10: Limpiar y normalizar los espacios en blanco del nombre del cliente y calle.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarYLimpiarCliente");
    }

}
