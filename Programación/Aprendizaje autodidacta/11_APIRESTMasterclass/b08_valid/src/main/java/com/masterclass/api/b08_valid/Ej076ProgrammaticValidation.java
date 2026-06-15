package com.masterclass.api.b08_valid;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Ejercicio 076 · Validación programática con Validator.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.1).
 *
 * <p>A veces validas a mano (fuera de un controller). Aquí obtienes los mensajes
 * de error legibles para construir una respuesta.
 */
public final class Ej076ProgrammaticValidation {

    public static class ComentarioDto {
        @NotBlank(message = "el texto es obligatorio")
        public String texto;

        public ComentarioDto(String texto) {
            this.texto = texto;
        }
    }

    private Ej076ProgrammaticValidation() {
    }

    /**
     * Valida programáticamente y devuelve los mensajes de error ordenados.
     *
     * @param dto comentario
     * @return lista de mensajes (vacía si es válido)
     */
    public static List<String> mensajesDeError(ComentarioDto dto) {
        // TODO 1: construye un ValidatorFactory con Validation.buildDefaultValidatorFactory().
        // TODO 2: obtén el Validator con factory.getValidator().
        // TODO 3: ejecuta validator.validate(dto) para obtener las violaciones.
        // TODO 4: si no hay violaciones, devuelve una lista vacía.
        // TODO 5: mapea cada ConstraintViolation a getMessage().
        // TODO 6: ordena los mensajes alfabéticamente (salida determinista para el test).
        // TODO 7: recoge a List.
        // TODO 8: cierra/usa el factory de forma responsable (try-with-resources si procede).
        // TODO 9: este enfoque sirve fuera de Spring (servicios, batch, etc.).
        // TODO 10: devuelve la lista de mensajes.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(mensajesDeError(new ComentarioDto("")));
    }

        /**
     * RETO EXTRA 01: Verifica si un usuario cumple los minimos de registro (username largo y mayor de edad).
     */
    public static boolean esUsuarioValido(String username, int edad) {
        // GUÍA: dos condiciones a la vez (teoría 8.1: @Size + @Min hechos a mano).
        // 1. username no null y con longitud suficiente (>= 5 cubre el caso del test).
        // 2. edad de mayoría de edad (>= 18).
        // PISTA: return username != null && username.length() >= 5 && edad >= 18;
        // OJO (test REAL): manda ("user123", 18) y espera assertTrue → "user123" tiene
        //      7 chars y 18 es el límite inclusivo. Aquí el test SÍ es la espec real.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioValido");
    }

    /**
     * RETO EXTRA 02: Determina si un comentario es apto para publicar (no nulo, no vacio y menor de 200 chars).
     */
    public static boolean validarComentario(String txt) {
        // GUÍA: tres reglas — no null, no en blanco, < 200 chars (teoría 8.1).
        // PISTA: return txt != null && !txt.isBlank() && txt.length() < 200;
        // OJO (test REAL): manda "buen curso" y espera assertTrue. El límite es < 200
        //      estricto (200 caracteres exactos NO valdrían).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarComentario");
    }

    /**
     * RETO EXTRA 03: Valida sintaxis basica de una direccion IPv4.
     */
    public static boolean esIpValida(String ip) {
        // GUÍA: 4 octetos 0-255 separados por puntos (@Pattern de 8.2 llevado al límite).
        // 1. null → false. Divide con ip.split("\\.") y comprueba que hay 4 partes.
        // 2. Cada parte debe ser un número entre 0 y 255 (Integer.parseInt + rango).
        //    Una regex "simple" admite 999; para hacerlo bien valida el rango numérico.
        // PISTA: String[] p = ip.split("\\.");
        //        if (p.length != 4) return false;
        //        for (String o : p) { int n = Integer.parseInt(o); if (n<0||n>255) return false; }
        //        return true;   // envuelve en try/catch NumberFormatException → false
        // OJO (test REAL): "192.168.1.1" debe dar true. CUIDADO: split("\\.") con
        //      backslash escapado, porque "." en regex significa "cualquier carácter".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIpValida");
    }

    /**
     * RETO EXTRA 04: Valida que un valor este estrictamente entre min y max.
     */
    public static boolean esRangoValido(int min, int max, int val) {
        // GUÍA: "estrictamente entre" → ambos extremos EXCLUIDOS.
        // PISTA: return val > min && val < max;
        // OJO (test REAL): (1, 10, 5) debe dar true. "Estrictamente" significa que
        //      val == min o val == max darían false (a diferencia de @Min/@Max, que
        //      son inclusivos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRangoValido");
    }

    /**
     * RETO EXTRA 05: Verifica que el color sea un codigo hexadecimal de 6 caracteres valido.
     */
    public static boolean esColorHexValido(String hex) {
        // GUÍA: '#' seguido de 6 dígitos hexadecimales (regex, teoría 8.2).
        // 1. null → false. Hex = [0-9a-fA-F].
        // PISTA: return hex != null && hex.matches("#[0-9a-fA-F]{6}");
        // OJO (test REAL): "#FFFFFF" debe dar true. La almohadilla cuenta: el test
        //      la incluye. "FFFFFF" sin # daría false con esta regex.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esColorHexValido");
    }

    /**
     * RETO EXTRA 06: Comprueba que empiece por http:// o https:// de manera segura.
     */
    public static boolean esUrlValida(String url) {
        // GUÍA: prefijo de protocolo. startsWith cubre los dos casos.
        // PISTA: return url != null && (url.startsWith("http://") || url.startsWith("https://"));
        //    (truco: comprobar solo startsWith("http://") NO basta, "https" no lo cumple;
        //     por eso listas ambos, o usas startsWith("http") de forma más laxa).
        // OJO (test REAL): "https://google.com" debe dar true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUrlValida");
    }

    /**
     * RETO EXTRA 07: Busca palabras ofensivas de una lista negra dentro del texto.
     */
    public static boolean tienePalabraProhibida(String texto, java.util.List<String> prohibidas) {
        // GUÍA: ¿alguna palabra prohibida aparece en el texto? (streams, teoría 1.3).
        // 1. Protege nulls (texto o lista null → false).
        // 2. prohibidas.stream().anyMatch(texto::contains) — true si alguna está dentro.
        //    (para ignorar mayúsculas, compara en lowercase ambos lados).
        // PISTA: return texto != null && prohibidas != null
        //          && prohibidas.stream().anyMatch(texto::contains);
        // OJO (test REAL): ("spam total", ["spam"]) debe dar true (contiene "spam").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePalabraProhibida");
    }

    /**
     * RETO EXTRA 08: Valida si el ID tiene patron alfanumerico con guiones.
     */
    public static boolean validarIdFormato(String id) {
        // GUÍA: solo letras, dígitos y guiones (regex, teoría 8.2).
        // 1. null → false. Conjunto permitido: [A-Za-z0-9-].
        // PISTA: return id != null && id.matches("[A-Za-z0-9-]+");
        // OJO (test REAL): "USR-481516" debe dar true (mayúsculas, dígitos y un guion).
        //      Un espacio o un '_' lo invalidarían.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarIdFormato");
    }

    /**
     * RETO EXTRA 09: Verifica si la extension del archivo esta entre las permitidas.
     */
    public static boolean esExtensionPermitida(String filename, java.util.List<String> permitidas) {
        // GUÍA: extrae la extensión (lo que va tras el último punto) y míralo en la lista.
        // 1. null → false. Localiza el último punto: filename.lastIndexOf('.').
        // 2. ext = filename.substring(idx + 1) (en minúsculas para comparar bien).
        // 3. permitidas.contains(ext).
        // PISTA: int i = filename.lastIndexOf('.');
        //        if (i < 0) return false;
        //        return permitidas.contains(filename.substring(i + 1).toLowerCase());
        // OJO (test REAL): ("foto.png", ["png","jpg"]) debe dar true. Usa lastIndexOf,
        //      no indexOf, para nombres como "foto.final.png".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExtensionPermitida");
    }

    /**
     * RETO EXTRA 10: Valida que el numero de tarjeta tenga 16 digitos sin espacios.
     */
    public static boolean esTarjetaCreditoValida(String tarjeta) {
        // GUÍA: exactamente 16 dígitos (regex, teoría 8.2). null → false.
        // PISTA: return tarjeta != null && tarjeta.matches("\\d{16}");
        // OJO (test REAL): "1234567812345678" (16 dígitos) debe dar true. matches ancla
        //      a toda la cadena, así que con espacios o 15/17 dígitos daría false.
        // CULTURA: validar el FORMATO no valida que la tarjeta sea real; eso es el
        //      algoritmo de Luhn, un escalón más que aquí no se pide.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTarjetaCreditoValida");
    }

}