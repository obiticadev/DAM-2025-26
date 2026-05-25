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

    /**
     * RETO EXTRA 1: Comprobar si un RegistroDto es completamente válido.
     *
     * @param dto registro
     * @return true si no tiene ninguna violación de validación
     */
    public static boolean esRegistroValido(RegistroDto dto) {
        // TODO extra: RETO EXTRA 1: Comprobar si un RegistroDto es completamente válido.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRegistroValido");
    }

    /**
     * RETO EXTRA 2: Obtener los mensajes de error legibles.
     *
     * @param dto registro
     * @return lista de mensajes de error de las violaciones
     */
    public static java.util.List<String> obtenerMensajesError(RegistroDto dto) {
        // TODO extra: RETO EXTRA 2: Obtener los mensajes de error legibles.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMensajesError");
    }

    /**
     * RETO EXTRA 3: Crear un RegistroDto de demostración pre-validado y correcto.
     *
     * @return RegistroDto válido
     */
    public static RegistroDto crearRegistroPorDefecto() {
        // TODO extra: RETO EXTRA 3: Crear un RegistroDto de demostración pre-validado y correcto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRegistroPorDefecto");
    }

    /**
     * RETO EXTRA 4: Comprobar si una dirección de email es válida usando la constraint Email.
     *
     * @param email correo
     * @return true si cumple con el patrón de email estándar
     */
    public static boolean esEmailValido(String email) {
        // TODO extra: RETO EXTRA 4: Comprobar si una dirección de email es válida usando la constraint Email.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmailValido");
    }

    /**
     * RETO EXTRA 5: Comprobar si un número de teléfono tiene exactamente 9 dígitos.
     *
     * @param telefono teléfono
     * @return true si cumple la condición
     */
    public static boolean esTelefonoValido(String telefono) {
        // TODO extra: RETO EXTRA 5: Comprobar si un número de teléfono tiene exactamente 9 dígitos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTelefonoValido");
    }

    /**
     * RETO EXTRA 6: Validar si la edad está en el rango permitido (18 a 120).
     *
     * @param edad edad a comprobar
     * @return true si está en rango
     */
    public static boolean edadEnRango(Integer edad) {
        // TODO extra: RETO EXTRA 6: Validar si la edad está en el rango permitido (18 a 120).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadEnRango");
    }

    /**
     * RETO EXTRA 7: Normalizar el nombre de un registro (quitar espacios sobrantes).
     *
     * @param nombre nombre original
     * @return nombre limpio, o vacío si es nulo
     */
    public static String normalizarNombre(String nombre) {
        // TODO extra: RETO EXTRA 7: Normalizar el nombre de un registro (quitar espacios sobrantes).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarNombre");
    }

    /**
     * RETO EXTRA 8: Determinar si el registro tiene errores concretamente en el campo 'email'.
     *
     * @param dto registro
     * @return true si el campo 'email' es inválido
     */
    public static boolean tieneErroresDeEmail(RegistroDto dto) {
        // TODO extra: RETO EXTRA 8: Determinar si el registro tiene errores concretamente en el campo 'email'.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneErroresDeEmail");
    }

    /**
     * RETO EXTRA 9: Formatear todos los errores de un registro en una sola línea de texto.
     *
     * @param dto registro
     * @return cadena con todos los mensajes de error separados por comas
     */
    public static String formatearErrores(RegistroDto dto) {
        // TODO extra: RETO EXTRA 9: Formatear todos los errores de un registro en una sola línea de texto.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearErrores");
    }

    /**
     * RETO EXTRA 10: Validar y corregir un RegistroDto (si el nombre es nulo/vacío, le asigna "Usuario Genérico").
     *
     * @param dto original
     * @return nuevo RegistroDto corregido si procede
     */
    public static RegistroDto validarYCorregir(RegistroDto dto) {
        // TODO extra: RETO EXTRA 10: Validar y corregir un RegistroDto (si el nombre es nulo/vacío, le asigna "Usuario Genérico").
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarYCorregir");
    }

}
