package com.masterclass.api.b06_webadv;

/**
 * Ejercicio 061 · Estrategias de versionado de API.
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.4).
 *
 * <p>Lógica pura: resolver la versión efectiva combinando ruta y cabecera.
 */
public final class Ej061VersioningStrategies {

    private Ej061VersioningStrategies() {
    }

    /**
     * Resuelve la versión a usar.
     *
     * <p>Precedencia: la cabecera {@code X-API-Version} pisa la versión de la ruta.
     * Si ninguna está, la versión por defecto es 1.
     *
     * @param path          ruta solicitada, p.ej. "/api/v2/users" o "/api/users"
     * @param headerVersion valor de X-API-Version (puede ser null)
     * @return número de versión efectivo (&gt;= 1)
     * @throws IllegalArgumentException si la versión es no numérica o &lt; 1
     */
    public static int resolveVersion(String path, String headerVersion) {
        // TODO 1: si headerVersion no es null/blank, tiene prioridad.
        // TODO 2: parsea headerVersion a int (try/catch -> IllegalArgumentException).
        // TODO 3: si la versión del header es < 1 -> IllegalArgumentException.
        // TODO 4: si hay header válido, devuélvelo (ignora la ruta).
        // TODO 5: si no hay header, intenta extraer "/v{n}/" de la ruta.
        // TODO 6: localiza el segmento que empiece por 'v' seguido de dígitos.
        // TODO 7: si lo encuentras, parsea el número tras la 'v'.
        // TODO 8: valida que sea >= 1 (si no, IllegalArgumentException).
        // TODO 9: si la ruta no tiene versión, usa la versión por defecto 1.
        // TODO 10: devuelve la versión resuelta.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(resolveVersion("/api/v2/users", null));
        System.out.println(resolveVersion("/api/users", "3"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si headerVersion no es null/blank, tiene prioridad.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: parsea headerVersion a int (try/catch -> IllegalArgumentException).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si la versión del header es < 1 -> IllegalArgumentException.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si hay header válido, devuélvelo (ignora la ruta).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si no hay header, intenta extraer "/v{n}/" de la ruta.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: localiza el segmento que empiece por 'v' seguido de dígitos.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si lo encuentras, parsea el número tras la 'v'.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: valida que sea >= 1 (si no, IllegalArgumentException).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si la ruta no tiene versión, usa la versión por defecto 1.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la versión resuelta.
    }

}
