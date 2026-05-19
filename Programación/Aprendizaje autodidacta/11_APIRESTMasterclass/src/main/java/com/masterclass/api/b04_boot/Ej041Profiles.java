package com.masterclass.api.b04_boot;

/**
 * Ejercicio 041 · Selección por perfil activo.
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.3).
 */
public final class Ej041Profiles {

    private Ej041Profiles() {
    }

    /**
     * Devuelve la URL de base de datos según el perfil activo.
     *
     * @param activeProfile valor de spring.profiles.active ("dev", "test", "prod")
     * @return URL JDBC apropiada para el perfil
     * @throws IllegalArgumentException si el perfil no se reconoce
     */
    public static String datasourceUrl(String activeProfile) {
        // TODO 1: si activeProfile es null o vacío, asume "dev" (perfil por defecto).
        // TODO 2: normaliza el perfil (trim + minúsculas).
        // TODO 3: "dev" -> "jdbc:h2:mem:devdb".
        // TODO 4: "test" -> "jdbc:h2:mem:testdb".
        // TODO 5: "prod" -> "jdbc:postgresql://db:5432/app".
        // TODO 6: cualquier otro perfil -> IllegalArgumentException con el valor recibido.
        // TODO 7: devuelve la URL resuelta.
        return "";
    }

    /**
     * Indica si en ese perfil deben mostrarse stack traces detallados.
     *
     * @param activeProfile perfil activo
     * @return true solo en dev y test (en prod se ocultan por seguridad)
     */
    public static boolean verboseErrors(String activeProfile) {
        // TODO 8: normaliza el perfil igual que en datasourceUrl (puedes extraer un helper).
        // TODO 9: dev y test -> true (queremos ver el detalle al desarrollar).
        // TODO 10: prod o cualquier otro -> false (no filtrar internals al cliente).
        return false;
    }

    public static void main(String[] args) {
        System.out.println(datasourceUrl("prod") + " verbose=" + verboseErrors("prod"));
    }
}
