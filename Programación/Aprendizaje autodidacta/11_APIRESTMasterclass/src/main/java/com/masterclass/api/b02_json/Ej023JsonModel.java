package com.masterclass.api.b02_json;

/**
 * Ejercicio 023 · El modelo de tipos JSON.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.1).
 *
 * <p>Sin librerías: clasifica un literal JSON simple por su tipo según su sintaxis.
 */
public final class Ej023JsonModel {

    private Ej023JsonModel() {
    }

    /**
     * Determina el tipo JSON de un literal ya recortado (sin espacios sobrantes).
     *
     * @param literal texto como {@code "true"}, {@code "42"}, {@code "\"hola\""}, {@code "null"},
     *                {@code "{...}"} o {@code "[...]"}
     * @return uno de: "objeto", "array", "string", "numero", "booleano", "null", "desconocido"
     */
    public static String tipo(String literal) {
        // TODO 1: si literal es null o vacío -> "desconocido".
        // TODO 2: si empieza por '{' -> "objeto".
        // TODO 3: si empieza por '[' -> "array".
        // TODO 4: si empieza por '"' -> "string".
        // TODO 5: si es exactamente "true" o "false" -> "booleano".
        // TODO 6: si es exactamente "null" -> "null".
        // TODO 7: intenta parsear como número (Double.parseDouble en try/catch).
        // TODO 8: si el parseo tiene éxito -> "numero".
        // TODO 9: si lanza NumberFormatException, no es número.
        // TODO 10: si nada de lo anterior aplica -> "desconocido".
        return "";
    }

    public static void main(String[] args) {
        for (String s : new String[]{"{}", "[1]", "\"x\"", "42", "true", "null", "??"}) {
            System.out.println(s + " -> " + tipo(s));
        }
    }
}
