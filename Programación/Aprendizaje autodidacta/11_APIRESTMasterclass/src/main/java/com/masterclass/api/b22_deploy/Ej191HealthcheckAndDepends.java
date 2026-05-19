package com.masterclass.api.b22_deploy;

public final class Ej191HealthcheckAndDepends {
    private Ej191HealthcheckAndDepends() {}
    public static boolean ejecutar() {
        // TODO 1: añade un bloque 'healthcheck' al servicio postgres.
        // TODO 2: usa 'pg_isready -U postgres' como test.
        // TODO 3: configura el interval (ej. 5s) y timeout (ej. 3s).
        // TODO 4: configura los retries para declarar el servicio 'unhealthy'.
        // TODO 5: en la API, usa 'depends_on' -> 'db'.
        // TODO 6: el depende_on debe usar 'condition: service_healthy'.
        // TODO 7: entiende por qué un ping TCP al puerto 5432 no basta.
        // TODO 8: añade un healthcheck a la propia API vía Actuator (/actuator/health).
        // TODO 9: testea qué pasa si la BD crashea y Compose intenta reiniciar la API.
        // TODO 10: retorna la validacion estricta del nodo YAML de depends_on.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: añade un bloque 'healthcheck' al servicio postgres.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa 'pg_isready -U postgres' como test.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: configura el interval (ej. 5s) y timeout (ej. 3s).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: configura los retries para declarar el servicio 'unhealthy'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: en la API, usa 'depends_on' -> 'db'.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: el depende_on debe usar 'condition: service_healthy'.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: entiende por qué un ping TCP al puerto 5432 no basta.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: añade un healthcheck a la propia API vía Actuator (/actuator/health).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: testea qué pasa si la BD crashea y Compose intenta reiniciar la API.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna la validacion estricta del nodo YAML de depends_on.
    }

}
