package com.masterclass.api.b04_boot;

import org.springframework.core.env.Environment;
import org.springframework.core.env.ConfigurableEnvironment;
import java.util.List;

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

    /**
     * RETO EXTRA 03: Configuración cargada únicamente bajo el perfil "dev".
     */
    // TODO extra: Añade anotaciones @org.springframework.context.annotation.Configuration y @org.springframework.context.annotation.Profile("dev")
    public static class DevConfig {
        public String message() { return "Dev Environment"; }
    }

    /**
     * RETO EXTRA 04: Componente que se carga cuando no estamos en producción.
     */
    // TODO extra: Añade anotación @org.springframework.context.annotation.Profile("!prod")
    public static class NonProdService {
        public String getStatus() { return "Safe for testing"; }
    }

    /**
     * RETO EXTRA 06: Componente condicionado a que los perfiles "dev" y "cloud" estén activos simultáneamente.
     */
    // TODO extra: Añade anotaciones @org.springframework.context.annotation.Profile con expresión lógica.
    public static class CloudDevService {
        public String getProvider() { return "Local Cloud Runner"; }
    }

    /**
     * RETO EXTRA 08: Interfaz de servicio de base de datos para simular inyecciones selectivas.
     */
    public interface DbService {
        String getUrl();
    }

    // TODO extra: Añade @org.springframework.context.annotation.Profile("dev")
    public static class DevDbService implements DbService {
        @Override
        public String getUrl() { return "jdbc:h2:mem"; }
    }

    // TODO extra: Añade @org.springframework.context.annotation.Profile("prod")
    public static class ProdDbService implements DbService {
        @Override
        public String getUrl() { return "jdbc:postgresql://db"; }
    }

    /**
     * RETO EXTRA 10: Configuración condicionada a expresiones complejas como "dev & !prod".
     */
    // TODO extra: Añade anotación @org.springframework.context.annotation.Profile con expresión "dev & !prod"
    public static class ComplexProfileConfig {
        public String getMode() { return "Complex Mode"; }
    }

    /**
     * RETO EXTRA 01: Comprobación programática de la activación de un perfil.
     */
    public static boolean pasoExtra01(Environment env, String perfil) {
        // TODO extra: Comprueba si el perfil indicado está activo en el Environment de Spring.
        return false;
    }

    /**
     * RETO EXTRA 02: Activación programática de perfiles en caliente en el entorno configurable.
     */
    public static void pasoExtra02(ConfigurableEnvironment env, String... perfiles) {
        // TODO extra: Activa programáticamente los perfiles indicados en el ConfigurableEnvironment.
    }

    /**
     * RETO EXTRA 03: Retorna la instancia de DevConfig.
     */
    public static DevConfig pasoExtra03() {
        // TODO extra: Retorna una nueva instancia de la configuración de dev.
        return null;
    }

    /**
     * RETO EXTRA 04: Retorna la instancia de NonProdService.
     */
    public static NonProdService pasoExtra04() {
        // TODO extra: Retorna una nueva instancia del servicio de no-producción.
        return null;
    }

    /**
     * RETO EXTRA 05: Obtiene una lista combinada de perfiles activos, o los perfiles por defecto si no hay activos.
     */
    public static List<String> pasoExtra05(Environment env) {
        // TODO extra: Lee del Environment los perfiles activos y, si no hay ninguno, retorna los perfiles por defecto.
        return null;
    }

    /**
     * RETO EXTRA 06: Retorna la instancia de CloudDevService.
     */
    public static CloudDevService pasoExtra06() {
        // TODO extra: Retorna una nueva instancia del servicio CloudDev.
        return null;
    }

    /**
     * RETO EXTRA 07: Determina si el entorno está utilizando los perfiles por defecto por no haberse configurado ningún perfil activo.
     */
    public static boolean pasoExtra07(Environment env) {
        // TODO extra: Comprueba si no hay perfiles activos explícitamente y por ende se usan los por defecto.
        return false;
    }

    /**
     * RETO EXTRA 08: Filtra y retorna el servicio de base de datos adecuado de la lista según el tipo devuelto.
     */
    public static DbService pasoExtra08(List<DbService> services, Class<? extends DbService> targetClass) {
        // TODO extra: Busca y retorna la implementación de DbService que coincida con targetClass de entre la lista.
        return null;
    }

    /**
     * RETO EXTRA 09: Resolutor dinámico de perfiles según la región del sistema.
     */
    public static String[] pasoExtra09(String systemRegion) {
        // TODO extra: Si la región es "EU" o "US", retorna los perfiles {"prod", systemRegion.toLowerCase()}. De lo contrario, {"dev"}.
        return null;
    }

    /**
     * RETO EXTRA 10: Retorna la instancia de ComplexProfileConfig.
     */
    public static ComplexProfileConfig pasoExtra10() {
        // TODO extra: Retorna una nueva instancia de ComplexProfileConfig.
        return null;
    }

}
