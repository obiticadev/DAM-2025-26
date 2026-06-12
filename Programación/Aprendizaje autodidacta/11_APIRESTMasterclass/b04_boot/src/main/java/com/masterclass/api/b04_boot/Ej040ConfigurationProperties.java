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

    // === POJOs de los retos extra ===========================================
    // Son meros contenedores de datos (constructor + getters). El "reto" de cada
    // uno NO es la clase, sino el método pasoExtraNN que la POBLA a partir de un
    // mapa de propiedades. Por eso estas clases vienen completas: estudia sus
    // campos para saber qué tiene que rellenar tu binding.

    /** RETO EXTRA 01: configuración simple de un servidor (host + puerto). */
    public static class ServerConfig {
        private final String host;
        private final int port;

        // Constructor con nombres de parámetro 'host'/'port': imprescindible para
        // el constructor binding del Binder en pasoExtra05 (gracias a -parameters).
        public ServerConfig(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() { return host; }
        public int getPort() { return port; }
    }

    /** RETO EXTRA 02: igual que ServerConfig pero su binding (pasoExtra02) valida. */
    public static class ServerConfigConValidacion {
        private final String host;
        private final int port;

        public ServerConfigConValidacion(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() { return host; }
        public int getPort() { return port; }
    }

    /** RETO EXTRA 03: estructura jerárquica con un ServerConfig anidado. */
    public static class AppConfig {
        private final String name;
        private final ServerConfig server;

        public AppConfig(String name, ServerConfig server) {
            this.name = name;
            this.server = server;
        }

        public String getName() { return name; }
        public ServerConfig getServer() { return server; }
    }

    /** RETO EXTRA 04: configuración con una lista y un mapa. */
    public static class ServiceListConfig {
        private final List<String> urls;
        private final Map<String, String> metadata;

        public ServiceListConfig(List<String> urls, Map<String, String> metadata) {
            this.urls = urls;
            this.metadata = metadata;
        }

        public List<String> getUrls() { return urls; }
        public Map<String, String> getMetadata() { return metadata; }
    }

    /** RETO EXTRA 06: una sola propiedad en kebab-case (relaxed binding). */
    public static class KebabConfig {
        private final String externalServiceUrl;

        public KebabConfig(String externalServiceUrl) {
            this.externalServiceUrl = externalServiceUrl;
        }

        public String getExternalServiceUrl() { return externalServiceUrl; }
    }

    /** RETO EXTRA 07: configuración con valores por defecto. */
    public static class DefaultedConfig {
        private final String host;
        private final int port;

        public DefaultedConfig(String host, int port) {
            this.host = host;
            this.port = port;
        }

        public String getHost() { return host; }
        public int getPort() { return port; }
    }

    /** RETO EXTRA 09: configuración con un java.time.Duration. */
    public static class DurationConfig {
        private final Duration timeout;

        public DurationConfig(Duration timeout) {
            this.timeout = timeout;
        }

        public Duration getTimeout() { return timeout; }
    }

    // === Métodos de binding (LOS RETOS) =====================================

    /**
     * RETO EXTRA 01: Enlaza un mapa de propiedades planas en una clase ServerConfig.
     */
    public static ServerConfig pasoExtra01(Map<String, String> props) {
        // GUÍA: teoría 4.2 — binding manual de un bloque con prefijo "server.".
        // 1. Lee props.get("server.host") y props.get("server.port").
        // 2. Convierte el puerto con Integer.parseInt(...).
        // 3. Construye y devuelve: return new ServerConfig(host, port);
        // OJO: el test mete "server.host"="localhost" y "server.port"="8080" y
        //      espera getHost()=="localhost" y getPort()==8080.
        // CULTURA: esto es, en miniatura, lo que hace @ConfigurationProperties con
        //      prefix="server"; el reto 05 lo hará con el Binder REAL.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Realiza el binding y valida restricciones de JSR-380.
     * Debe lanzar una excepción de validación si no se cumplen.
     */
    public static ServerConfigConValidacion pasoExtra02(Map<String, String> props) {
        // GUÍA: teoría 4.2 — binding + validación.
        // 1. Enlaza host y port como en pasoExtra01.
        // 2. Valida el puerto y LANZA si no cumple (cualquier excepción vale: el
        //    test usa assertThrows(Exception.class, ...)).
        // OJO: el test acepta port 8080 y RECHAZA port 80. Una regla que cumple eso:
        //      puerto >= 1024 (los <1024 son puertos privilegiados). Si no cumple →
        //      throw new IllegalArgumentException("puerto no permitido: " + port).
        // PISTA (si tuvieras el starter de validación en el classpath): anotarías el
        //      POJO con @jakarta.validation.constraints.Min(1024) y usarías un
        //      Validator. Aquí, como el módulo no lo trae, valida a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Enlaza propiedades jerárquicas en una clase anidada AppConfig.
     */
    public static AppConfig pasoExtra03(Map<String, String> props) {
        // GUÍA: teoría 4.2 — binding jerárquico (un bloque dentro de otro).
        // 1. Lee "app.name".
        // 2. Construye el ServerConfig hijo desde "app.server.host" y
        //    "app.server.port" (reutiliza la idea de pasoExtra01).
        // 3. return new AppConfig(name, new ServerConfig(host, port));
        // OJO: el test espera getName()=="MyDemoApp", getServer().getHost()=="127.0.0.1"
        //      y getServer().getPort()==9000. Fíjate en el prefijo anidado
        //      "app.server.*": cada punto es un nivel de la jerarquía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * RETO EXTRA 04: Enlaza listas y mapas genéricos en un objeto ServiceListConfig.
     */
    public static ServiceListConfig pasoExtra04(Map<String, String> props) {
        // GUÍA: teoría 4.2 — enlace de colecciones indexadas y mapas.
        // 1. LISTA: las claves "services.urls[0]", "services.urls[1]"... son los
        //    elementos por índice. Recórrelas en orden de índice y mételas en una
        //    List<String>. (Puedes iterar i=0,1,... mientras exista la clave
        //    "services.urls[" + i + "]".)
        // 2. MAPA: las claves "services.metadata.X" → entrada (X → valor). Quédate
        //    con el sufijo tras "services.metadata." como clave del mapa.
        // PISTA: para el mapa, recorre props.entrySet(), filtra las que empiezan por
        //    "services.metadata." y usa key.substring(prefijo.length()).
        // OJO: el test espera getUrls().size()==2, getUrls().get(0)=="http://service-a"
        //      y getMetadata().get("env")=="production". El ORDEN de las urls importa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Realiza el enlazado programático usando Binder de Spring Boot.
     */
    public static <T> T pasoExtra05(Map<String, String> props, Class<T> targetClass, String prefix) {
        // GUÍA: teoría 4.2 — el Binder REAL (lo que usa Spring Boot por dentro).
        // 1. Envuelve el mapa como fuente de propiedades de configuración:
        //    var source = new org.springframework.boot.context.properties.source
        //                     .MapConfigurationPropertySource(props);
        // 2. Crea el Binder y enlaza por prefijo al tipo destino:
        //    return new org.springframework.boot.context.properties.bind.Binder(source)
        //               .bind(prefix, targetClass).get();
        // OJO: el test usa prefix "my-custom-prefix" y espera getHost()=="some-host",
        //      getPort()==8888. El Binder usa el CONSTRUCTOR de ServerConfig
        //      (constructor binding) emparejando los nombres de parámetro host/port
        //      con las claves: por eso el pom compila con -parameters.
        // CULTURA: este es el motor que materializa cualquier @ConfigurationProperties.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * RETO EXTRA 06: Comprueba el relaxed binding de kebab-case a camelCase.
     */
    public static KebabConfig pasoExtra06(Map<String, String> props) {
        // GUÍA: teoría 4.2 — relaxed binding: "external-service-url" (kebab) enlaza
        // al campo externalServiceUrl (camelCase).
        // 1. Lee props.get("external-service-url").
        // 2. return new KebabConfig(valor);
        // OJO: el test pasa la clave en kebab-case y espera
        //      getExternalServiceUrl()=="https://api.external.com". Aquí lo lees a
        //      mano, pero el Binder (reto 05) haría esa equivalencia él solo: kebab,
        //      camelCase y UPPER_SNAKE apuntan al mismo campo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * RETO EXTRA 07: Inicializa la configuración garantizando fallback a valores por defecto cuando no se proveen.
     */
    public static DefaultedConfig pasoExtra07(Map<String, String> props) {
        // GUÍA: teoría 4.2 — defaults cuando la propiedad falta.
        // 1. host  = props.getOrDefault("host", "localhost").
        // 2. port  = props.containsKey("port") ? Integer.parseInt(props.get("port")) : 8080.
        // 3. return new DefaultedConfig(host, port).
        // OJO: el test pasa un mapa VACÍO (Map.of()) y espera getHost()=="localhost",
        //      getPort()==8080. getOrDefault es el atajo idiomático para "valor o
        //      default" sobre un Map.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Comprobación de anotación @EnableConfigurationProperties.
     */
    public static boolean pasoExtra08(Class<?> targetConfig, Class<?> enableConfig) {
        // GUÍA: teoría 4.2 — leer una anotación por reflexión.
        // 1. Recupera la anotación de la clase enableConfig:
        //    var ann = enableConfig.getAnnotation(
        //        org.springframework.boot.context.properties.EnableConfigurationProperties.class);
        // 2. Si es null → false (no la lleva).
        // 3. ann.value() es un Class<?>[]; comprueba si CONTIENE targetConfig:
        //    return java.util.Arrays.asList(ann.value()).contains(targetConfig);
        // OJO: el test anota una clase con @EnableConfigurationProperties(ServerConfig.class)
        //      y pregunta si "registra" ServerConfig → debe dar true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * RETO EXTRA 09: Enlaza un valor de duración de tiempo (como "5s", "10m") a java.time.Duration.
     */
    public static DurationConfig pasoExtra09(Map<String, String> props) {
        // GUÍA: teoría 4.2 — Spring convierte "5s"/"10m" a Duration.
        // 1. Lee props.get("timeout") (un String como "5s").
        // 2. Conviértelo con el parser de Spring Boot:
        //    var d = org.springframework.boot.convert.DurationStyle.detectAndParse(valor);
        // 3. return new DurationConfig(d).
        // OJO: el test pasa "timeout"="5s" y espera getTimeout()==Duration.ofSeconds(5).
        //      NO inventes el parseo: "5s"→5 segundos, "10m"→10 minutos lo da
        //      DurationStyle. (java.time.Duration.parse exigiría "PT5S", otro formato.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Inspección y lectura reflexiva de propiedades en un Bean.
     */
    public static Map<String, Object> pasoExtra10(Object bean) {
        // GUÍA: teoría 4.2 — el inverso del binding: leer las props de un bean.
        // 1. Recorre los métodos getter del bean: los que empiezan por "get", no
        //    tienen parámetros y NO son getClass().
        // 2. Por cada uno, deriva el nombre de propiedad: "getHost" → "host"
        //    (quita "get" y pasa a minúscula la inicial) e invoca el método.
        // 3. Mete cada par (nombre, valor) en un Map<String,Object> y devuélvelo.
        // PISTA cómoda: usa el BeanWrapper de Spring en vez de reflexión cruda:
        //    var w = new org.springframework.beans.BeanWrapperImpl(bean);
        //    for (var pd : w.getPropertyDescriptors()) { ... w.getPropertyValue(name) ... }
        //    (descarta la propiedad "class").
        // OJO: el test pasa new ServerConfig("local-dns", 443) y espera
        //      result.get("host")=="local-dns" y result.get("port")==443 (Integer).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
