package com.masterclass.api.b22_deploy;

public final class Ej194ReverseProxyTraefik {
    private Ej194ReverseProxyTraefik() {}
    public static boolean ejecutar() {
        // TODO 1: añade el servicio 'traefik' al compose.
        // TODO 2: mapea los puertos 80 y 443 al proxy.
        // TODO 3: mapea el socket de Docker a Traefik para auto-descubrimiento.
        // TODO 4: en el servicio de la API añade etiquetas 'labels'.
        // TODO 5: traefik.enable=true en la API.
        // TODO 6: especifica el Host('api.mi-dominio.com') como router rule.
        // TODO 7: Traefik enrutará el tráfico automáticamente a contenedores que escuchen internamente.
        // TODO 8: no expongas el 8080 de la API al exterior (borra sections 'ports' del container API).
        // TODO 9: configura Let's Encrypt o resolver certificado simulado.
        // TODO 10: devuelve la configuración correcta del label para el routing.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: añade el servicio 'traefik' al compose.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: mapea los puertos 80 y 443 al proxy.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: mapea el socket de Docker a Traefik para auto-descubrimiento.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: en el servicio de la API añade etiquetas 'labels'.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: traefik.enable=true en la API.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: especifica el Host('api.mi-dominio.com') como router rule.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: Traefik enrutará el tráfico automáticamente a contenedores que escuchen internamente.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: no expongas el 8080 de la API al exterior (borra sections 'ports' del container API).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: configura Let's Encrypt o resolver certificado simulado.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la configuración correcta del label para el routing.
    }

}
