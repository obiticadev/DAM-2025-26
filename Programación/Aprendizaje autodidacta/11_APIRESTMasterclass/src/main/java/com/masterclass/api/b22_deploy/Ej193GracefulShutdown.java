package com.masterclass.api.b22_deploy;

public final class Ej193GracefulShutdown {
    private Ej193GracefulShutdown() {}
    public static boolean ejecutar() {
        // TODO 1: en application.yml establece server.shutdown=graceful.
        // TODO 2: configura un timeout máximo (spring.lifecycle.timeout-per-shutdown-phase).
        // TODO 3: comprueba que el ENTRYPOINT en Docker está en exec form: ["java", "-jar"].
        // TODO 4: si estuviera como "java -jar" en shell form, Docker no pasaría el SIGTERM al JVM.
        // TODO 5: simula un cierre con SIGTERM y monitoriza los hilos de Tomcat.
        // TODO 6: implementa un endpoint simulado que tarde 5s para observar el cierre ordenado.
        // TODO 7: en ese lapso de 5s, el servidor rechazará peticiones nuevas con 503.
        // TODO 8: valida cómo Spring Data JPA cierra el connection pool (HikariCP).
        // TODO 9: entiende cómo interactúa esto con preStop hooks en Kubernetes.
        // TODO 10: retorna un comprobante booleano de que el cierre ordenado está activo.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: en application.yml establece server.shutdown=graceful.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: configura un timeout máximo (spring.lifecycle.timeout-per-shutdown-phase).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: comprueba que el ENTRYPOINT en Docker está en exec form: ["java", "-jar"].
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si estuviera como "java -jar" en shell form, Docker no pasaría el SIGTERM al JVM.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: simula un cierre con SIGTERM y monitoriza los hilos de Tomcat.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: implementa un endpoint simulado que tarde 5s para observar el cierre ordenado.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: en ese lapso de 5s, el servidor rechazará peticiones nuevas con 503.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: valida cómo Spring Data JPA cierra el connection pool (HikariCP).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: entiende cómo interactúa esto con preStop hooks en Kubernetes.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna un comprobante booleano de que el cierre ordenado está activo.
    }

}
