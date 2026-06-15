package com.masterclass.api.b09_err;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 080 · Payload de errores de validación agrupados.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.2).
 *
 * <p>Cuando varias reglas fallan, el cliente quiere un mapa campo→[mensajes],
 * no un texto plano.
 */
public final class Ej080ValidationErrorPayload {

    /** Un error de campo individual (lo que produce el validador). */
    public record FieldError(String campo, String mensaje) {
    }

    private Ej080ValidationErrorPayload() {
    }

    /**
     * Agrupa una lista de errores por campo.
     *
     * @param errores lista de errores individuales (puede repetir campo)
     * @return mapa campo → lista de mensajes (orden de aparición preservado)
     */
    public static Map<String, List<String>> agrupar(List<FieldError> errores) {
        // TODO 1: si errores es null -> IllegalArgumentException.
        // TODO 2: usa un LinkedHashMap para conservar el orden de primera aparición.
        // TODO 3: recorre cada FieldError.
        // TODO 4: si el campo no está en el mapa, crea una lista nueva (computeIfAbsent).
        // TODO 5: añade el mensaje a la lista de ese campo.
        // TODO 6: un mismo campo puede acumular varios mensajes (p.ej. NotBlank + Size).
        // TODO 7: no descartes duplicados salvo que sean idénticos (decisión: conservarlos).
        // TODO 8: el resultado debe ser estable y reproducible.
        // TODO 9: si la lista está vacía, devuelve un mapa vacío (no null).
        // TODO 10: devuelve el mapa agrupado.
        return Map.of();
    }

    public static void main(String[] args) {
        System.out.println(agrupar(List.of(
                new FieldError("email", "obligatorio"),
                new FieldError("email", "formato inválido"))));
    }

        /**
     * RETO EXTRA 01: Valida nomenclatura estandar en formato camelCase.
     */
    public static boolean esNombreCampoValido(String fieldName) {
        // GUÍA: valida que el nombre de campo esté en camelCase (empieza en
        // minúscula, solo letras y dígitos).
        // 1. null o vacío → false.
        // 2. Un regex lo resuelve de un golpe:
        //       return fieldName != null && fieldName.matches("[a-z][a-zA-Z0-9]*");
        // PISTA: String.matches comprueba la cadena ENTERA contra el patrón.
        // OJO: el test pasa "nombreUsuario" → true. Empieza por minúscula; un
        // "_" o un "!" lo invalidarían (eso lo mira el reto 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreCampoValido");
    }

    /**
     * RETO EXTRA 02: Construye la representacion leible de una violacion.
     */
    public static String formatearMensajeValidacion(String campo, String error) {
        // GUÍA: redacta un mensaje legible "El campo '<campo>' <error>".
        // PISTA: return "El campo '" + campo + "' " + error;
        // OJO: el test pasa ("edad", "es obligatorio") y espera EXACTAMENTE
        // "El campo 'edad' es obligatorio". Fíjate en las comillas simples
        // alrededor del campo y en el espacio antes del error — todo cuenta
        // (equals literal).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearMensajeValidacion");
    }

    /**
     * RETO EXTRA 03: Determina si el error es global del objeto y no de un campo concreto.
     */
    public static boolean esErrorGlobal(String campo) {
        // GUÍA: un error "global" (del objeto entero, no de un campo) se
        // representa con campo vacío o null.
        // PISTA: return campo == null || campo.isBlank();
        // OJO: el test pasa "" → true. En Bean Validation esto es la diferencia
        // entre un FieldError (campo concreto) y un ObjectError/global (validación
        // cruzada entre campos, como la que verás en b08 con @ScriptAssert).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorGlobal");
    }

    /**
     * RETO EXTRA 04: Genera un String formateado del campo y su valor.
     */
    public static String crearParClaveValor(String k, String v) {
        // GUÍA: una línea — return k + ":" + v;
        // OJO: separador ":" SIN espacios. El test espera "k:v" (compara con
        // equals). No confundas con el ": " de combinarDetalles (Ej078) — aquí
        // es par clave:valor compacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearParClaveValor");
    }

    /**
     * RETO EXTRA 05: Busca signos extraños no permitidos en nombres de campos.
     */
    public static boolean tieneCaracteresEspeciales(String s) {
        // GUÍA: detecta si hay algún carácter que NO sea letra o dígito.
        // 1. null → false (o el criterio que prefieras).
        // 2. Un regex con "contiene algo no alfanumérico":
        //       return s != null && !s.matches("[a-zA-Z0-9]*");
        //    (si NO encaja con "solo alfanuméricos", es que tiene rarezas.)
        // PISTA alternativa: s.chars().anyMatch(c -> !Character.isLetterOrDigit(c)).
        // OJO: el test pasa "user_id!" → true: tanto "_" como "!" cuentan como
        // especiales. Es el complemento del camelCase del reto 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneCaracteresEspeciales");
    }

    /**
     * RETO EXTRA 06: Comprueba limites inclusivos de longitud.
     */
    public static boolean esLargoValido(String text, int min, int max) {
        // GUÍA: comprueba que la longitud esté en [min, max] (ambos inclusive).
        // 1. text null → false.
        // 2. return text != null && text.length() >= min && text.length() <= max;
        // OJO: el test pasa ("abc", 2, 5): length 3, entre 2 y 5 → true. Límites
        // INCLUSIVOS (es la semántica de @Size de Bean Validation, b08).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esLargoValido");
    }

    /**
     * RETO EXTRA 07: Comprueba limites inclusivos para campos numericos.
     */
    public static boolean esNumeroRangoValido(double n, double min, double max) {
        // GUÍA: una línea — return n >= min && n <= max;
        // OJO: el test pasa (5.0, 1.0, 10.0) → true. Límites inclusivos, igual que
        // el reto 6 pero con double (es @Min/@Max de Bean Validation, b08).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNumeroRangoValido");
    }

    /**
     * RETO EXTRA 08: Extrae la propiedad hoja de una ruta anidada.
     */
    public static String extraerUltimoSegmentoCampo(String path) {
        // GUÍA: devuelve el último segmento de una ruta separada por puntos.
        // 1. null o vacío → "" (defensa).
        // 2. Coge lo que hay tras el último ".":
        //       return path.substring(path.lastIndexOf('.') + 1);
        //    (si no hay punto, lastIndexOf devuelve -1 y +1 = 0 → la cadena
        //     entera, que es lo correcto.)
        // PISTA alternativa: var p = path.split("\\."); return p[p.length - 1];
        //    OJO: en split el punto hay que escaparlo ("\\."), es regex.
        // OJO: el test pasa "cliente.perfil.nombre" → "nombre" (la hoja). Estas
        // rutas anidadas son las de los DTO con objetos dentro (b07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerUltimoSegmentoCampo");
    }

    /**
     * RETO EXTRA 09: Verifica si la coleccion tiene strings vacios o nulos.
     */
    public static boolean contieneErroresInvalidos(java.util.List<String> errors) {
        // GUÍA: ¿hay algún elemento null o en blanco en la lista? (teoría 1.3,
        // anyMatch).
        // 1. errors null o vacía → false.
        // 2. return errors != null
        //        && errors.stream().anyMatch(e -> e == null || e.isBlank());
        // OJO: el test pasa List.of("") → true (la cadena vacía cuenta como
        // inválida). isBlank() cubre vacíos y solo-espacios; pon e == null PRIMERO
        // para no hacer NPE al llamar isBlank sobre un null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneErroresInvalidos");
    }

    /**
     * RETO EXTRA 10: Determina si el error reportado indica valores ya en uso.
     */
    public static boolean esErrorDuplicidad(String error) {
        // GUÍA: detecta un error de duplicidad por el mensaje.
        // 1. null → false.
        // 2. return error != null && error.toLowerCase().contains("ya existe");
        // OJO: el test pasa "el correo ya existe" → true. Filtra por "ya existe"
        // (en minúsculas para tolerar mayúsculas). Esto es lo que mapea a 409
        // Conflict (9.5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorDuplicidad");
    }

}