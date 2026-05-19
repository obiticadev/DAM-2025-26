package com.masterclass.api.b05_web;

/**
 * Ejercicio 047 · @RequestParam con valor por defecto.
 *
 * <p>Teoría: {@code teoria/05_Controllers_REST.md} (sección 5.2).
 *
 * <p>El test espera {@code GET /api/sum?a=2&b=3} -> "5" y {@code GET /api/sum?a=2} -> "2"
 * (b por defecto 0).
 */
// TODO 1: anota la clase con @RestController.
// TODO 2: anota la clase con @RequestMapping("/api").
public class Ej047QueryParams {

    /**
     * Suma dos enteros recibidos por query string.
     *
     * @param a primer sumando (obligatorio)
     * @param b segundo sumando (opcional, por defecto 0)
     * @return la suma como texto
     */
    // TODO 3: anota el método con @GetMapping("/sum").
    // TODO 4: anota 'a' con @RequestParam (obligatorio por defecto).
    // TODO 5: anota 'b' con @RequestParam(defaultValue = "0") (opcional).
    public String sum(int a, int b) {
        // TODO 6: Spring convierte los query params a int automáticamente.
        // TODO 7: si falta 'a' Spring responde 400 (no lo gestionas tú aquí).
        // TODO 8: calcula a + b.
        // TODO 9: el cuerpo debe ser el número como String (usa String.valueOf).
        // TODO 10: devuelve esa cadena.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new Ej047QueryParams().sum(2, 3));
    }
}
