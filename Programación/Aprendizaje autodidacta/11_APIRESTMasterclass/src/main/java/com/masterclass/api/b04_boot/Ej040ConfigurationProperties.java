package com.masterclass.api.b04_boot;

import java.util.Map;
import java.util.List;
import java.time.Duration;

/**
 * Ejercicio 040 · Enlace de un bloque tipado (@ConfigurationProperties).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.2).
 *
 * <p>Construye un objeto tipado a partir de propiedades con prefijo, validando.
 */
public final class Ej040ConfigurationProperties {

    /** Bloque de configuración con prefijo "app". */
    public record AppProps(String region, int timeout) {
    }

    private Ej040ConfigurationProperties() {
    }

    /**
     * Enlaza las propiedades con prefijo "app." a un AppProps.
     *
     * @param props mapa plano de propiedades (p.ej. "app.region", "app.timeout")
     * @return AppProps poblado
     * @throws IllegalArgumentException si falta una propiedad obligatoria o el timeout no es número
     */
    public static AppProps bind(Map<String, String> props) {
        // TODO 1: si props es null, lanza IllegalArgumentException.
        // TODO 2: lee la clave "app.region".
        // TODO 3: si "app.region" falta, lanza IllegalArgumentException (obligatoria).
        // TODO 4: lee la clave "app.timeout" como String.
        // TODO 5: si "app.timeout" falta, usa el valor por defecto 30.
        // TODO 6: si está presente, parséalo con Integer.parseInt.
        // TODO 7: captura NumberFormatException y relánzala como IllegalArgumentException.
        // TODO 8: valida que timeout sea > 0 (un timeout no positivo no tiene sentido).
        // TODO 9: construye el record AppProps con los valores resueltos.
        // TODO 10: devuelve el AppProps.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(bind(Map.of("app.region", "eu", "app.timeout", "45")));
    }

    /**
     * RETO EXTRA 01: Clase de configuración simple para representar un servidor.
     */
    public static class ServerConfig {
        // TODO extra: Define atributos (host, port) y métodos accesores.
        private String host;
        private int port;

        public ServerConfig() {}

        public ServerConfig(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
    }

    /**
     * RETO EXTRA 02: Configuración con validación JSR-380.
     */
    public static class ServerConfigConValidacion {
        // TODO extra: Añade anotación @NotBlank
        private String host;

        // TODO extra: Añade anotación @Min(1024) y @Max(65535)
        private int port;

        public ServerConfigConValidacion() {}

        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
    }

    /**
     * RETO EXTRA 03: Estructura jerárquica con configuración de servidor anidada.
     */
    public static class AppConfig {
        private String name;
        // TODO extra: Atributo para configuración anidada del servidor (ServerConfig).
        private ServerConfig server;

        public AppConfig() {}

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public ServerConfig getServer() { return server; }
        public void setServer(ServerConfig server) { this.server = server; }
    }

    /**
     * RETO EXTRA 04: Configuración con colecciones y mapas.
     */
    public static class ServiceListConfig {
        // TODO extra: Lista de URLs de servicios
        private List<String> urls;
        // TODO extra: Mapa de metadatos genéricos
        private Map<String, String> metadata;

        public ServiceListConfig() {}

        public List<String> getUrls() { return urls; }
        public void setUrls(List<String> urls) { this.urls = urls; }
        public Map<String, String> getMetadata() { return metadata; }
        public void setMetadata(Map<String, String> metadata) { this.metadata = metadata; }
    }

    /**
     * RETO EXTRA 06: Configuración para validar relaxed binding (kebab-case a camelCase).
     */
    public static class KebabConfig {
        // TODO extra: Atributo que recibirá "external-service-url" en camelCase
        private String externalServiceUrl;

        public KebabConfig() {}

        public String getExternalServiceUrl() { return externalServiceUrl; }
        public void setExternalServiceUrl(String externalServiceUrl) { this.externalServiceUrl = externalServiceUrl; }
    }

    /**
     * RETO EXTRA 07: Configuración con valores por defecto predefinidos.
     */
    public static class DefaultedConfig {
        // TODO extra: Asigna un valor predeterminado como "localhost" al atributo host
        private String host;
        // TODO extra: Asigna un valor predeterminado como 8080 al atributo port
        private int port;

        public DefaultedConfig() {}

        public String getHost() { return host; }
        public void setHost(String host) { this.host = host; }
        public int getPort() { return port; }
        public void setPort(int port) { this.port = port; }
    }

    /**
     * RETO EXTRA 09: Configuración con uso de java.time.Duration.
     */
    public static class DurationConfig {
        // TODO extra: Atributo timeout de tipo Duration
        private Duration timeout;

        public DurationConfig() {}

        public Duration getTimeout() { return timeout; }
        public void setTimeout(Duration timeout) { this.timeout = timeout; }
    }

    /**
     * RETO EXTRA 01: Enlaza un mapa de propiedades planas en una clase ServerConfig.
     */
    public static ServerConfig pasoExtra01(Map<String, String> props) {
        // TODO extra: Implementa el enlazado manual o dinámico del prefijo "server" a ServerConfig.
        return null;
    }

    /**
     * RETO EXTRA 02: Realiza el binding y valida restricciones de JSR-380.
     * Debe lanzar una excepción de validación si no se cumplen.
     */
    public static ServerConfigConValidacion pasoExtra02(Map<String, String> props) {
        // TODO extra: Enlaza a ServerConfigConValidacion y ejecuta la validación de sus campos.
        return null;
    }

    /**
     * RETO EXTRA 03: Enlaza propiedades jerárquicas en una clase anidada AppConfig.
     */
    public static AppConfig pasoExtra03(Map<String, String> props) {
        // TODO extra: Realiza el binding a AppConfig incluyendo el objeto anidado ServerConfig.
        return null;
    }

    /**
     * RETO EXTRA 04: Enlaza listas y mapas genéricos en un objeto ServiceListConfig.
     */
    public static ServiceListConfig pasoExtra04(Map<String, String> props) {
        // TODO extra: Realiza el binding de colecciones (ej. "services.urls[0]", "services.metadata.key").
        return null;
    }

    /**
     * RETO EXTRA 05: Realiza el enlazado programático usando Binder de Spring Boot.
     */
    public static <T> T pasoExtra05(Map<String, String> props, Class<T> targetClass, String prefix) {
        // TODO extra: Usa org.springframework.boot.context.properties.bind.Binder para enlazar el mapa al targetClass con el prefijo dado.
        return null;
    }

    /**
     * RETO EXTRA 06: Comprueba el relaxed binding de kebab-case a camelCase.
     */
    public static KebabConfig pasoExtra06(Map<String, String> props) {
        // TODO extra: Enlaza propiedades kebab-case como "external-service-url" a camelCase en KebabConfig.
        return null;
    }

    /**
     * RETO EXTRA 07: Inicializa la configuración garantizando fallback a valores por defecto cuando no se proveen.
     */
    public static DefaultedConfig pasoExtra07(Map<String, String> props) {
        // TODO extra: Enlaza a DefaultedConfig y verifica que los valores por defecto se mantengan si no vienen en props.
        return null;
    }

    /**
     * RETO EXTRA 08: Comprobación de anotación @EnableConfigurationProperties.
     */
    public static boolean pasoExtra08(Class<?> targetConfig, Class<?> enableConfig) {
        // TODO extra: Comprueba mediante reflexión si 'enableConfig' tiene @EnableConfigurationProperties registrando 'targetConfig'.
        return false;
    }

    /**
     * RETO EXTRA 09: Enlaza un valor de duración de tiempo (como "5s", "10m") a java.time.Duration.
     */
    public static DurationConfig pasoExtra09(Map<String, String> props) {
        // TODO extra: Enlaza a DurationConfig permitiendo formatos de tiempo de Spring Boot (ej. "10s").
        return null;
    }

    /**
     * RETO EXTRA 10: Inspección y lectura reflexiva de propiedades en un Bean.
     */
    public static Map<String, Object> pasoExtra10(Object bean) {
        // TODO extra: Extrae reflexivamente todos los atributos y sus valores del bean dado en un mapa.
        return null;
    }

}
