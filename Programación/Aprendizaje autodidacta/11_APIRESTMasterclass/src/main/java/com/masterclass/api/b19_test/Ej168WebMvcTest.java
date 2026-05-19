package com.masterclass.api.b19_test;

/**
 * Ejercicio 168 · {@code @WebMvcTest} + MockMvc (controller como función pura).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.5).
 *
 * <p>Un {@code @WebMvcTest} arrancaría solo la capa web y un MockMvc
 * simularía peticiones HTTP. Aquí modelamos el controlador como una
 * función pura {@code request -> response}: {@link Respuesta168}.
 */
public final class Ej168WebMvcTest {

    private Ej168WebMvcTest() {
    }

    /**
     * Maneja {@code GET /saludo?nombre=X} como función pura.
     *
     * @param metodo método HTTP (no null)
     * @param ruta   ruta solicitada (no null)
     * @param nombre query param "nombre" (puede ser null/blank)
     * @return {@link Respuesta168} con status y cuerpo JSON
     * @throws IllegalArgumentException si metodo o ruta son null
     */
    public static Respuesta168 handle(String metodo, String ruta, String nombre) {
        // TODO 1: si metodo o ruta son null -> IllegalArgumentException.
        // TODO 2: si la ruta no es "/saludo" -> Respuesta168(404, "{}").
        // TODO 3: si el metodo no es "GET" -> Respuesta168(405, "{}").
        // TODO 4: si nombre es null o blank -> Respuesta168(400, cuerpo de error).
        // TODO 5: el cuerpo de error debe ser JSON: {"error":"nombre requerido"}.
        // TODO 6: caso OK -> status 200.
        // TODO 7: cuerpo OK -> JSON {"saludo":"Hola, <nombre>"}.
        // TODO 8: escapa correctamente el JSON (comillas dobles internas).
        // TODO 9: la respuesta es determinista (mismo request -> misma response).
        // TODO 10: devuelve la Respuesta168 construida.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(handle("GET", "/saludo", "Ada"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si metodo o ruta son null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si la ruta no es "/saludo" -> Respuesta168(404, "{}").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si el metodo no es "GET" -> Respuesta168(405, "{}").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si nombre es null o blank -> Respuesta168(400, cuerpo de error).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: el cuerpo de error debe ser JSON: {"error":"nombre requerido"}.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: caso OK -> status 200.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: cuerpo OK -> JSON {"saludo":"Hola, <nombre>"}.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: escapa correctamente el JSON (comillas dobles internas).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: la respuesta es determinista (mismo request -> misma response).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la Respuesta168 construida.
    }

}

/** Respuesta HTTP simulada (lo que MockMvc inspeccionaría). */
final class Respuesta168 {
    final int status;
    final String body;

    Respuesta168(int status, String body) {
        this.status = status;
        this.body = body;
    }

    @Override
    public String toString() {
        return status + " " + body;
    }
}
