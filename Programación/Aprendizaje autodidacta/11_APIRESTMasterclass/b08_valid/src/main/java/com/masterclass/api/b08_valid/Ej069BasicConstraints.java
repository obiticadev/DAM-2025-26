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
        // GUÍA: teoría 8.1 (un objeto es válido si el Set de violaciones está vacío).
        // 1. Reutiliza camposInvalidos(dto): es válido cuando ese Set está vacío.
        // 2. Una línea: return camposInvalidos(dto).isEmpty();
        //    (o directamente VALIDATOR.validate(dto).isEmpty()).
        // PISTA: return camposInvalidos(dto).isEmpty();
        // OJO: el test pasa un dto VÁLIDO y espera assertFalse → es una aserción
        //      placeholder, NO la espec real. Implementa la regla correcta (un dto
        //      válido debe dar true); el test placeholder quedará en rojo a propósito.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRegistroValido");
    }

    /**
     * RETO EXTRA 2: Obtener los mensajes de error legibles.
     *
     * @param dto registro
     * @return lista de mensajes de error de las violaciones
     */
    public static java.util.List<String> obtenerMensajesError(RegistroDto dto) {
        // GUÍA: teoría 8.1 y 8.8 (de cada ConstraintViolation sacas getMessage()).
        // 1. Valida: VALIDATOR.validate(dto) devuelve Set<ConstraintViolation<RegistroDto>>.
        // 2. Mapea cada violación a su mensaje y recoge en lista:
        //    .stream().map(ConstraintViolation::getMessage).sorted().toList().
        // 3. Si no hay violaciones devuelve una lista VACÍA (no null).
        // PISTA: return VALIDATOR.validate(dto).stream()
        //            .map(v -> v.getMessage()).sorted().collect(Collectors.toList());
        // OJO: el test usa assertNull → aserción placeholder. La espec real (Javadoc)
        //      pide una LISTA de mensajes; impleméntala así aunque el test quede rojo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerMensajesError");
    }

    /**
     * RETO EXTRA 3: Crear un RegistroDto de demostración pre-validado y correcto.
     *
     * @return RegistroDto válido
     */
    public static RegistroDto crearRegistroPorDefecto() {
        // GUÍA: una línea — construye un dto que cumpla TODAS las constraints de 8.1.
        // 1. nombre con 2..50 chars, email con formato válido, edad en [18,120],
        //    telefono de 9 dígitos.
        // PISTA: return new RegistroDto("Ana", "ana@b.com", 30, "600123456");
        // COMPRUEBA: esRegistroValido(crearRegistroPorDefecto()) debería dar true.
        // OJO: el test usa assertNull (placeholder); la espec pide un dto VÁLIDO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRegistroPorDefecto");
    }

    /**
     * RETO EXTRA 4: Comprobar si una dirección de email es válida usando la constraint Email.
     *
     * @param email correo
     * @return true si cumple con el patrón de email estándar
     */
    public static boolean esEmailValido(String email) {
        // GUÍA: dos enfoques (teoría 8.1). El "de libro": validar SOLO ese campo.
        // 1. Camino Bean Validation: crea un dto con ese email y el resto válido y
        //    comprueba que "email" NO aparece en camposInvalidos.
        // 2. Camino simple (suficiente aquí): regex de email básica.
        // PISTA: return email != null && email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
        // OJO: el test manda "ana@b.com" (válido) y espera assertFalse → placeholder.
        //      Implementa la regla real: un email bien formado debe dar true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmailValido");
    }

    /**
     * RETO EXTRA 5: Comprobar si un número de teléfono tiene exactamente 9 dígitos.
     *
     * @param telefono teléfono
     * @return true si cumple la condición
     */
    public static boolean esTelefonoValido(String telefono) {
        // GUÍA: una línea con el mismo patrón que la constraint del DTO (teoría 8.2).
        // 1. null → false (no lances; es un test booleano).
        // 2. Reutiliza la regex "\\d{9}" de la anotación @Pattern del campo telefono.
        // PISTA: return telefono != null && telefono.matches("\\d{9}");
        // OJO: matches ancla a TODA la cadena, así que "1234567890" (10 dígitos) da
        //      false. El test manda "600123456" (9 dígitos, válido) y espera
        //      assertFalse → placeholder; tu implementación correcta dará true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTelefonoValido");
    }

    /**
     * RETO EXTRA 6: Validar si la edad está en el rango permitido (18 a 120).
     *
     * @param edad edad a comprobar
     * @return true si está en rango
     */
    public static boolean edadEnRango(Integer edad) {
        // GUÍA: una línea (equivale a @Min(18) + @Max(120) del DTO, teoría 8.1).
        // 1. null → false (Integer puede venir nulo).
        // 2. Rango cerrado [18, 120]: ambos extremos incluidos.
        // PISTA: return edad != null && edad >= 18 && edad <= 120;
        // OJO: el test manda 30 (en rango) y espera assertFalse → placeholder.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para edadEnRango");
    }

    /**
     * RETO EXTRA 7: Normalizar el nombre de un registro (quitar espacios sobrantes).
     *
     * @param nombre nombre original
     * @return nombre limpio, o vacío si es nulo
     */
    public static String normalizarNombre(String nombre) {
        // GUÍA: una línea — el Javadoc pide "nombre limpio, o vacío si es nulo".
        // 1. Si nombre es null → devuelve "" (cadena vacía).
        // 2. Si no, quita espacios de los extremos con trim().
        // PISTA: return nombre == null ? "" : nombre.trim();
        // OJO: el test manda " Ana " y espera "" — aserción placeholder. La espec
        //      real produciría "Ana" (trim, no vaciado). Implementa el trim correcto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarNombre");
    }

    /**
     * RETO EXTRA 8: Determinar si el registro tiene errores concretamente en el campo 'email'.
     *
     * @param dto registro
     * @return true si el campo 'email' es inválido
     */
    public static boolean tieneErroresDeEmail(RegistroDto dto) {
        // GUÍA: teoría 8.1 — filtrar el resultado del validador por nombre de campo.
        // 1. Reutiliza camposInvalidos(dto) (devuelve las rutas inválidas).
        // 2. Comprueba si ese Set contiene "email".
        // PISTA: return camposInvalidos(dto).contains("email");
        // OJO: el test manda email "malo" (inválido) y espera assertFalse →
        //      placeholder; tu implementación correcta dará true para ese caso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneErroresDeEmail");
    }

    /**
     * RETO EXTRA 9: Formatear todos los errores de un registro en una sola línea de texto.
     *
     * @param dto registro
     * @return cadena con todos los mensajes de error separados por comas
     */
    public static String formatearErrores(RegistroDto dto) {
        // GUÍA: teoría 8.8 — reutiliza obtenerMensajesError (reto 2) y une con comas.
        // 1. Obtén la lista de mensajes y únelos con ", " usando Collectors.joining.
        // PISTA: return String.join(", ", obtenerMensajesError(dto));
        //    (o ...stream().collect(Collectors.joining(", "))).
        // OJO: el test espera "" — placeholder; con un dto inválido la espec real
        //      devolvería los mensajes concatenados, no cadena vacía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearErrores");
    }

    /**
     * RETO EXTRA 10: Validar y corregir un RegistroDto (si el nombre es nulo/vacío, le asigna "Usuario Genérico").
     *
     * @param dto original
     * @return nuevo RegistroDto corregido si procede
     */
    public static RegistroDto validarYCorregir(RegistroDto dto) {
        // GUÍA: "withers" de records/inmutables (teoría 1.1) aplicado a un DTO.
        // 1. Si dto es null → devuelve null (o lanza, según prefieras; el test no lo cubre).
        // 2. Comprueba si el nombre es null o está en blanco (isBlank()).
        // 3. Si lo está, crea un NUEVO RegistroDto copiando email/edad/telefono pero
        //    con nombre "Usuario Genérico"; si no, devuelve el dto tal cual.
        // PISTA: boolean malo = dto.nombre == null || dto.nombre.isBlank();
        //        return malo ? new RegistroDto("Usuario Genérico", dto.email,
        //                                      dto.edad, dto.telefono) : dto;
        // OJO: el test manda nombre "" y espera assertNull → placeholder; la espec
        //      real devuelve un dto corregido, nunca null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarYCorregir");
    }

}
