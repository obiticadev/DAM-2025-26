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
        // GUÍA: una línea — equivale a @NotBlank (teoría 8.1). null/""/"   " → false.
        // PISTA: return calle != null && !calle.isBlank();
        // OJO: el test manda "Calle Mayor" (válida) y espera assertFalse → placeholder.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCalleValida");
    }

    /**
     * RETO EXTRA 2: Comprobar si un código postal es válido (5 dígitos).
     *
     * @param cp código postal
     * @return true si cumple el patrón de 5 dígitos
     */
    public static boolean esCpValido(String cp) {
        // GUÍA: misma regex que la constraint del campo cp (teoría 8.2): "\\d{5}".
        // PISTA: return cp != null && cp.matches("\\d{5}");
        // OJO: el test manda "28001" (válido) y espera assertFalse → placeholder.
        //      "2800" (4) o "abcde" deben dar false: matches ancla a toda la cadena.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCpValido");
    }

    /**
     * RETO EXTRA 3: Comprobar si un nombre de cliente es válido.
     *
     * @param nombre nombre del cliente
     * @return true si es válido
     */
    public static boolean esNombreValido(String nombre) {
        // GUÍA: una línea — equivale a @NotBlank (teoría 8.1). Reutiliza esCalleValida.
        // PISTA: return nombre != null && !nombre.isBlank();
        // OJO: el test manda "Juan" (válido) y espera assertFalse → placeholder.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreValido");
    }

    /**
     * RETO EXTRA 4: Comprobar si el cliente tiene dirección establecida.
     *
     * @param c cliente
     * @return true si la dirección no es nula
     */
    public static boolean tieneDireccion(Cliente c) {
        // GUÍA: una línea — comprueba que el campo anidado no es null (teoría 8.3).
        // 1. Protege también c null para no romper.
        // PISTA: return c != null && c.direccion != null;
        // OJO: el test manda un Cliente con direccion null y espera assertFalse →
        //      ahí casualmente coincide con la espec; pero un cliente CON dirección
        //      debe dar true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDireccion");
    }

    /**
     * RETO EXTRA 5: Validar una dirección de forma directa e independiente.
     *
     * @param d dirección
     * @return conjunto de propiedades inválidas de la dirección
     */
    public static Set<String> validarDireccionDirecta(Direccion d) {
        // GUÍA: el validador puede validar CUALQUIER objeto, no solo el raíz (8.1).
        // 1. Necesitas un Validator: instáncialo igual que el VALIDATOR del ejercicio
        //    (Validation.buildDefaultValidatorFactory().getValidator()) o reutilízalo.
        // 2. validator.validate(d) sobre la Direccion suelta y mapea las rutas.
        // PISTA: return VALIDATOR.validate(d).stream()
        //            .map(v -> v.getPropertyPath().toString()).collect(Collectors.toSet());
        //    (el campo VALIDATOR es private static; puedes usarlo aquí dentro).
        // OJO: el test usa assertNull → placeholder; la espec pide un Set de rutas
        //      inválidas ("calle","cp" para una Direccion mala).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarDireccionDirecta");
    }

    /**
     * RETO EXTRA 6: Determinar si la dirección está completamente libre de errores de validación.
     *
     * @param d dirección
     * @return true si es válida
     */
    public static boolean esDireccionCompleta(Direccion d) {
        // GUÍA: reutiliza validarDireccionDirecta (reto 5): válida = Set vacío.
        // PISTA: return validarDireccionDirecta(d).isEmpty();
        // OJO: el test manda una Direccion válida y espera assertFalse → placeholder;
        //      la espec real da true para una dirección correcta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDireccionCompleta");
    }

    /**
     * RETO EXTRA 7: Validar programáticamente si el cliente y su dirección anidada son correctos.
     *
     * @param c cliente
     * @return true si pasa todas las validaciones
     */
    public static boolean esClienteValido(Cliente c) {
        // GUÍA: gracias a @Valid en el campo direccion, validar el Cliente valida
        //       TAMBIÉN la dirección en cascada (teoría 8.3). Reutiliza rutasInvalidas.
        // PISTA: return rutasInvalidas(c).isEmpty();
        // OJO: el test manda un Cliente válido y espera assertFalse → placeholder;
        //      la espec real da true. CUIDADO: si en el ejercicio base olvidaste el
        //      @Valid sobre direccion, una dirección mala pasaría desapercibida.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esClienteValido");
    }

    /**
     * RETO EXTRA 8: Obtener únicamente los errores procedentes del objeto anidado dirección.
     *
     * @param c cliente
     * @return conjunto de rutas que empiezan por "direccion."
     */
    public static Set<String> obtenerErroresDeDireccion(Cliente c) {
        // GUÍA: filtra las rutas en cascada por su prefijo (teoría 8.3).
        // 1. Reutiliza rutasInvalidas(c) y quédate con las que empiezan por "direccion.".
        // PISTA: return rutasInvalidas(c).stream()
        //            .filter(r -> r.startsWith("direccion.")).collect(Collectors.toSet());
        // OJO: el test usa assertNull → placeholder; la espec pide el Set de rutas
        //      anidadas ("direccion.calle","direccion.cp"). Fíjate en el punto: solo
        //      aparecen así si el campo lleva @Valid.
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
        // GUÍA: el prefijo provincial son los 2 primeros dígitos del CP.
        // 1. Reutiliza esCpValido(cp) (reto 2) para asegurarte de que es un CP real.
        // 2. Comprueba cp.startsWith(prefijoProvincia).
        // PISTA: return esCpValido(cp) && cp.startsWith(prefijoProvincia);
        // OJO: el test ("28001","28") espera assertFalse → placeholder; la espec real
        //      da true (Madrid empieza por 28).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cpPerteneceAProvincia");
    }

    /**
     * RETO EXTRA 10: Limpiar y normalizar los espacios en blanco del nombre del cliente y calle.
     *
     * @param c cliente
     * @return copia de Cliente con strings limpios
     */
    public static Cliente clonarYLimpiarCliente(Cliente c) {
        // GUÍA: copia profunda con trim de los strings (patrón inmutable, teoría 1.1).
        // 1. Si c es null → null. Cuida también c.direccion null.
        // 2. Crea una NUEVA Direccion con calle/cp trim, y un NUEVO Cliente con el
        //    nombre trim y esa dirección limpia.
        // PISTA: var d = c.direccion == null ? null
        //            : new Direccion(c.direccion.calle.trim(), c.direccion.cp.trim());
        //        return new Cliente(c.nombre.trim(), d);
        // OJO: el test usa assertNull → placeholder; la espec real devuelve el cliente
        //      con "  Juan  " → "Juan" y "  Calle Mayor  " → "Calle Mayor".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clonarYLimpiarCliente");
    }

}
