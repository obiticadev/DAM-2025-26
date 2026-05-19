package com.masterclass.api.b00_http;

/**
 * Ejercicio 004 · Semántica de los verbos HTTP (seguridad e idempotencia).
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.3).
 */
public final class Ej004HttpMethodsSemantics {

    private Ej004HttpMethodsSemantics() {
    }

    /**
     * Indica si un verbo es "seguro" (no modifica estado en el servidor).
     *
     * @param method verbo HTTP, sin distinguir mayúsculas (GET, post, ...)
     * @return true para verbos seguros (solo GET y HEAD en este bootcamp)
     * @throws IllegalArgumentException si el verbo no es uno de los 6 estándar
     */
    public static boolean isSafe(String method) {
        // TODO 1: si method es null, lanza IllegalArgumentException.
        // TODO 2: normaliza a mayúsculas y recorta espacios.
        // TODO 3: valida que sea uno de GET/HEAD/POST/PUT/PATCH/DELETE; si no, IllegalArgumentException.
        // TODO 4: devuelve true solo para GET y HEAD.
        // TODO 5: cualquier otro verbo estándar -> false (modifica estado).
        return false;
    }

    /**
     * Indica si un verbo es idempotente.
     *
     * @param method verbo HTTP
     * @return true para GET, HEAD, PUT, DELETE
     * @throws IllegalArgumentException si el verbo no es estándar
     */
    public static boolean isIdempotent(String method) {
        // TODO 6: reutiliza la normalización/validación (no dupliques): apóyate en isSafe
        //         o extrae un helper privado que normalice y valide.
        // TODO 7: GET y HEAD son idempotentes (y además seguros).
        // TODO 8: PUT es idempotente (reemplazo total).
        // TODO 9: DELETE es idempotente (borrar lo ya borrado deja el mismo estado).
        // TODO 10: POST y PATCH NO son idempotentes -> false.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("GET safe=" + isSafe("GET") + " idemp=" + isIdempotent("GET"));
        System.out.println("POST safe=" + isSafe("POST") + " idemp=" + isIdempotent("POST"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si method es null, lanza IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: normaliza a mayúsculas y recorta espacios.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: valida que sea uno de GET/HEAD/POST/PUT/PATCH/DELETE; si no, IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: devuelve true solo para GET y HEAD.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: cualquier otro verbo estándar -> false (modifica estado).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: reutiliza la normalización/validación (no dupliques): apóyate en isSafe
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: GET y HEAD son idempotentes (y además seguros).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: PUT es idempotente (reemplazo total).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: DELETE es idempotente (borrar lo ya borrado deja el mismo estado).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: POST y PATCH NO son idempotentes -> false.
    }

}
