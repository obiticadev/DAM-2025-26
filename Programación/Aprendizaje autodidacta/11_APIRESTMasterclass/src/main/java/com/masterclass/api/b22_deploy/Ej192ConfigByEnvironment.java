package com.masterclass.api.b22_deploy;

public final class Ej192ConfigByEnvironment {
    private Ej192ConfigByEnvironment() {}
    public static boolean ejecutar() {
        // TODO 1: evita guardar credenciales en el application.yml (evitar leaks en Git).
        // TODO 2: usa ${DB_URL:jdbc:postgresql://localhost:5432/app} como fallback local.
        // TODO 3: en Docker Compose, pasa DB_URL inyectando 'jdbc:postgresql://db:5432/app'.
        // TODO 4: inyecta JWT_SECRET como variable de entorno segura.
        // TODO 5: en Kubernetes, este secreto vendría de un ConfigMap o Secret.
        // TODO 6: inyecta un perfil activo con SPRING_PROFILES_ACTIVE=prod.
        // TODO 7: valida si el entorno local sobreescribe variables críticas.
        // TODO 8: usa @ConfigurationProperties(prefix = "app") y relájate sobre el formato camelCase vs ENV_VAR.
        // TODO 9: lanza un IllegalStateException en arranque si falta JWT_SECRET.
        // TODO 10: retorna el estado simulado de carga exitosa.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: evita guardar credenciales en el application.yml (evitar leaks en Git).
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa ${DB_URL:jdbc:postgresql://localhost:5432/app} como fallback local.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: en Docker Compose, pasa DB_URL inyectando 'jdbc:postgresql://db:5432/app'.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: inyecta JWT_SECRET como variable de entorno segura.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: en Kubernetes, este secreto vendría de un ConfigMap o Secret.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: inyecta un perfil activo con SPRING_PROFILES_ACTIVE=prod.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: valida si el entorno local sobreescribe variables críticas.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: usa @ConfigurationProperties(prefix = "app") y relájate sobre el formato camelCase vs ENV_VAR.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: lanza un IllegalStateException en arranque si falta JWT_SECRET.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna el estado simulado de carga exitosa.
    }

}
