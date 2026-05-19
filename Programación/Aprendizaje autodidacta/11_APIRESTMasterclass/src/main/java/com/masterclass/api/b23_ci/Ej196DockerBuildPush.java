package com.masterclass.api.b23_ci;

public final class Ej196DockerBuildPush {
    private Ej196DockerBuildPush() {}
    public static boolean ejecutar() {
        // TODO 1: define un job 'docker' que depenga (needs) de 'build-and-test'.
        // TODO 2: usa docker/setup-buildx-action para builds avanzados.
        // TODO 3: usa docker/login-action pasando secretos de GitHub (DOCKER_USERNAME/DOCKER_TOKEN).
        // TODO 4: extrae metadata de tags usando docker/metadata-action.
        // TODO 5: ejecuta docker/build-push-action apuntando a tu Dockerfile (Ej189).
        // TODO 6: etiqueta la imagen con 'latest' y con el hash del commit (github.sha).
        // TODO 7: haz push de la imagen al registro.
        // TODO 8: documenta el uso de OIDC (OpenID Connect) para AWS/GCP en lugar de tokens a largo plazo.
        // TODO 9: si el push en main tiene un tag 'vX.Y.Z', genera el release asociado.
        // TODO 10: devuelve validacion booleana del step push.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: define un job 'docker' que depenga (needs) de 'build-and-test'.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: usa docker/setup-buildx-action para builds avanzados.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: usa docker/login-action pasando secretos de GitHub (DOCKER_USERNAME/DOCKER_TOKEN).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: extrae metadata de tags usando docker/metadata-action.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: ejecuta docker/build-push-action apuntando a tu Dockerfile (Ej189).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: etiqueta la imagen con 'latest' y con el hash del commit (github.sha).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: haz push de la imagen al registro.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: documenta el uso de OIDC (OpenID Connect) para AWS/GCP en lugar de tokens a largo plazo.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: si el push en main tiene un tag 'vX.Y.Z', genera el release asociado.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve validacion booleana del step push.
    }

}
