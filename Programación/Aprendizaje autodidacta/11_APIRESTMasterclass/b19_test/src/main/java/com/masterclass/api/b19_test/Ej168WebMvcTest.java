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

        /**
     * RETO EXTRA 01: Crea una respuesta exitosa.
     */
    public static Respuesta168 crearRespuestaOK(String c) {
        // GUÍA: teoría 19.5 — fabrica una Respuesta168 con status 200.
        // return new Respuesta168(200, c);   // el body puede ser el propio c
        // El test solo comprueba crearRespuestaOK("c").status == 200, así que lo
        // único obligatorio es el 200; el cuerpo es libre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaOK");
    }

    /**
     * RETO EXTRA 02: Crea una respuesta de error.
     */
    public static Respuesta168 crearRespuestaError(int code, String err) {
        // GUÍA: teoría 19.5 — Respuesta168 con el código y un cuerpo de error.
        // return new Respuesta168(code, "{\"error\":\"" + err + "\"}");
        // El test (crearRespuestaError(400, "err")) solo verifica .status == 400.
        // Reutiliza esto desde handle() para los casos 400/404/405.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaError");
    }

    /**
     * RETO EXTRA 03: Comprueba si la respuesta es de exito.
     */
    public static boolean esRespuestaExitosa(Respuesta168 r) {
        // GUÍA: teoría 19.5 — éxito = familia 2xx.
        // return r.status >= 200 && r.status < 300;
        // El test pasa new Respuesta168(200, "") y espera true. Modela el rango
        // 2xx completo (no solo == 200) para que valga también con 201, 204…
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRespuestaExitosa");
    }

    /**
     * RETO EXTRA 04: Extrae el cuerpo de la respuesta.
     */
    public static String obtenerCuerpo(Respuesta168 r) {
        // GUÍA: teoría 19.5 — una línea: return r.body;
        // El test (new Respuesta168(200, "x")) espera "x". Es el accesor del
        // campo body que MockMvc inspeccionaría con .andReturn().getResponse().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCuerpo");
    }

    /**
     * RETO EXTRA 05: Comprueba si es un error de servidor.
     */
    public static boolean esErrorServidor(Respuesta168 r) {
        // GUÍA: teoría 19.5 — error de servidor = familia 5xx.
        // return r.status >= 500 && r.status < 600;
        // El test (new Respuesta168(500, "")) espera true. 5xx = "culpa del
        // servidor"; contrasta con esErrorCliente (4xx) del reto 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorServidor");
    }

    /**
     * RETO EXTRA 06: Valida si es la ruta del saludo.
     */
    public static boolean esRutaValida(String ruta) {
        // GUÍA: teoría 19.5 — una línea: return "/saludo".equals(ruta);
        // El test ("/saludo") espera true. PISTA: invoca equals sobre el literal
        // para que ruta == null devuelva false sin NPE. Es la comprobación que
        // usa handle() para decidir 404.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRutaValida");
    }

    /**
     * RETO EXTRA 07: Valida si el metodo es soportado.
     */
    public static boolean esMetodoSoportado(String metodo) {
        // GUÍA: teoría 19.5 — una línea: return "GET".equals(metodo);
        // El test ("GET") espera true. Este endpoint solo soporta GET; cualquier
        // otro método daría 405 en handle().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMetodoSoportado");
    }

    /**
     * RETO EXTRA 08: Extrae el codigo de estado.
     */
    public static int obtenerStatus(Respuesta168 r) {
        // GUÍA: teoría 19.5 — una línea: return r.status;
        // El test (new Respuesta168(200, "")) espera 200. Accesor simétrico a
        // obtenerCuerpo (reto 4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerStatus");
    }

    /**
     * RETO EXTRA 09: Genera el JSON del saludo.
     */
    public static String generarJsonSaludo(String nombre) {
        // GUÍA: teoría 19.5 — monta el cuerpo JSON del saludo.
        // return "{\"saludo\":\"Hola, " + nombre + "\"}";
        // El test solo exige generarJsonSaludo("Ada").contains("Ada"), así que
        // basta con que el nombre aparezca. PISTA: escapa las comillas dobles
        // internas con \" — es justo el cuerpo que handle() devuelve en el 200.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarJsonSaludo");
    }

    /**
     * RETO EXTRA 10: Comprueba si es un error del cliente.
     */
    public static boolean esErrorCliente(Respuesta168 r) {
        // GUÍA: teoría 19.5 — error del cliente = familia 4xx.
        // return r.status >= 400 && r.status < 500;
        // El test (new Respuesta168(400, "")) espera true. Completa el trío con
        // esRespuestaExitosa (2xx) y esErrorServidor (5xx).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorCliente");
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
