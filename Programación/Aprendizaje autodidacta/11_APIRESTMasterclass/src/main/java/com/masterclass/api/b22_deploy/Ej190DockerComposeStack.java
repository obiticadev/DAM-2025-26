package com.masterclass.api.b22_deploy;

public final class Ej190DockerComposeStack {
    private Ej190DockerComposeStack() {}
    public static boolean ejecutar() {
        // TODO 1: define version y services en docker-compose.yml.
        // TODO 2: crea un servicio 'db' basado en la imagen de postgres.
        // TODO 3: inyecta POSTGRES_USER, POSTGRES_PASSWORD y POSTGRES_DB.
        // TODO 4: configura volúmenes para persistir la BD localmente.
        // TODO 5: crea el servicio 'api' basado en tu imagen generada.
        // TODO 6: mapea el puerto 8080 del host al 8080 del contenedor.
        // TODO 7: define la variable de entorno SPRING_DATASOURCE_URL para la API.
        // TODO 8: asegura que ambos compartan una red bridge dedicada.
        // TODO 9: documenta comandos como 'docker compose up -d'.
        // TODO 10: retorna el archivo YML en string o valida el setup.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: define version y services en docker-compose.yml.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: crea un servicio 'db' basado en la imagen de postgres.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: inyecta POSTGRES_USER, POSTGRES_PASSWORD y POSTGRES_DB.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: configura volúmenes para persistir la BD localmente.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: crea el servicio 'api' basado en tu imagen generada.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: mapea el puerto 8080 del host al 8080 del contenedor.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: define la variable de entorno SPRING_DATASOURCE_URL para la API.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: asegura que ambos compartan una red bridge dedicada.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: documenta comandos como 'docker compose up -d'.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: retorna el archivo YML en string o valida el setup.
    }

}
