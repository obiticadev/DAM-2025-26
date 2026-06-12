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

    // === Beans/servicios condicionados por perfil (LOS RETOS) ================
    // El test NO levanta un contexto Spring para estos: los instancia a mano vía
    // pasoExtraNN y comprueba un método. Las anotaciones @Profile que se piden son
    // "de realismo" (documentan cuándo existirían en una app real); tu trabajo es
    // crear cada clase con su método devolviendo el literal EXACTO que pide el test.

    /**
     * RETO EXTRA 03: Configuración cargada únicamente bajo el perfil "dev".
     */
    // GUÍA: teoría 4.3. Añade, para realismo, las anotaciones:
    //   @org.springframework.context.annotation.Configuration
    //   @org.springframework.context.annotation.Profile("dev")
    public static class DevConfig {
        // GUÍA: el test (pasoExtra03) espera message() == "Dev Environment".
        // Implementa: public String message() { return "Dev Environment"; }
        // (el literal debe coincidir EXACTAMENTE, lo compara con equals).
        public String message() {
            throw new UnsupportedOperationException("TODO: Implementar message() devolviendo \"Dev Environment\"");
        }
    }

    /**
     * RETO EXTRA 04: Componente que se carga cuando no estamos en producción.
     */
    // GUÍA: teoría 4.3. Para realismo añade @org.springframework.context.annotation.Profile("!prod")
    //   ("!prod" = en TODO menos en prod).
    public static class NonProdService {
        // GUÍA: el test (pasoExtra04) espera getStatus() == "Safe for testing".
        public String getStatus() {
            throw new UnsupportedOperationException("TODO: Implementar getStatus() devolviendo \"Safe for testing\"");
        }
    }

    /**
     * RETO EXTRA 06: Componente condicionado a que los perfiles "dev" y "cloud" estén activos simultáneamente.
     */
    // GUÍA: teoría 4.3. La expresión "y" es @Profile("dev & cloud") (¡no la coma,
    //   que significa OR!). Añádela para realismo.
    public static class CloudDevService {
        // GUÍA: el test (pasoExtra06) espera getProvider() == "Local Cloud Runner".
        public String getProvider() {
            throw new UnsupportedOperationException("TODO: Implementar getProvider() devolviendo \"Local Cloud Runner\"");
        }
    }

    /**
     * RETO EXTRA 08: Interfaz de servicio de base de datos para simular inyecciones selectivas.
     */
    public interface DbService {
        // GUÍA: las dos implementaciones de abajo ya hacen @Override de getUrl(),
        // así que la interfaz DEBE declarar ese método (una sola línea):
        String getUrl();
    }

    // GUÍA: para realismo, @org.springframework.context.annotation.Profile("dev")
    public static class DevDbService implements DbService {
        @Override
        public String getUrl() { return "jdbc:h2:mem"; }
    }

    // GUÍA: para realismo, @org.springframework.context.annotation.Profile("prod")
    public static class ProdDbService implements DbService {
        @Override
        public String getUrl() { return "jdbc:postgresql://db"; }
    }

    /**
     * RETO EXTRA 10: Configuración condicionada a expresiones complejas como "dev & !prod".
     */
    // GUÍA: teoría 4.3. Para realismo, @org.springframework.context.annotation.Profile("dev & !prod").
    public static class ComplexProfileConfig {
        // GUÍA: el test (pasoExtra10) espera getMode() == "Complex Mode".
        public String getMode() {
            throw new UnsupportedOperationException("TODO: Implementar getMode() devolviendo \"Complex Mode\"");
        }
    }

    // === Métodos sobre el Environment (LOS RETOS) ===========================

    /**
     * RETO EXTRA 01: Comprobación programática de la activación de un perfil.
     */
    public static boolean pasoExtra01(Environment env, String perfil) {
        // GUÍA: teoría 4.3 (tabla del Environment).
        // PISTA: return env.acceptsProfiles(org.springframework.core.env.Profiles.of(perfil));
        //   (alternativa: java.util.Arrays.asList(env.getActiveProfiles()).contains(perfil)).
        // OJO: el test activa "dev" y espera true para "dev" y false para "prod".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Activación programática de perfiles en caliente en el entorno configurable.
     */
    public static void pasoExtra02(ConfigurableEnvironment env, String... perfiles) {
        // GUÍA: teoría 4.3 — solo ConfigurableEnvironment puede CAMBIAR perfiles.
        // PISTA: env.setActiveProfiles(perfiles);   // varargs, se pasan tal cual
        // OJO: tras llamarlo, el test comprueba que acceptsProfiles(Profiles.of("dev"))
        //   y ("cloud") son true. setActiveProfiles REEMPLAZA los activos por estos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Retorna la instancia de DevConfig.
     */
    public static DevConfig pasoExtra03() {
        // GUÍA: una línea — return new DevConfig();
        // (el test llamará a config.message(); asegúrate de haber implementado ese
        // método en DevConfig, arriba).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * RETO EXTRA 04: Retorna la instancia de NonProdService.
     */
    public static NonProdService pasoExtra04() {
        // GUÍA: una línea — return new NonProdService();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Obtiene una lista combinada de perfiles activos, o los perfiles por defecto si no hay activos.
     */
    public static List<String> pasoExtra05(Environment env) {
        // GUÍA: teoría 4.3 — la regla "activos, o por defecto si no hay activos".
        // 1. String[] activos = env.getActiveProfiles();
        // 2. String[] elegidos = (activos.length > 0) ? activos : env.getDefaultProfiles();
        // 3. return java.util.Arrays.asList(elegidos);   (o List.of(elegidos))
        // OJO: el test 1 activa "dev,cloud" → la lista debe contener "dev" y "cloud".
        //   El test 2 NO activa nada pero hace setDefaultProfiles("default-profile")
        //   → la lista debe contener "default-profile". Ese es justo el fallback de
        //   Spring: sin perfiles activos, manda getDefaultProfiles().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * RETO EXTRA 06: Retorna la instancia de CloudDevService.
     */
    public static CloudDevService pasoExtra06() {
        // GUÍA: una línea — return new CloudDevService();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * RETO EXTRA 07: Determina si el entorno está utilizando los perfiles por defecto por no haberse configurado ningún perfil activo.
     */
    public static boolean pasoExtra07(Environment env) {
        // GUÍA: teoría 4.3 — "usa los por defecto" ⇔ no hay perfiles activos.
        // PISTA: return env.getActiveProfiles().length == 0;
        // OJO: con "dev" activo el test espera false; sin activar nada, true.
        //   Reutiliza la idea de pasoExtra05 (mismo concepto, otra pregunta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Filtra y retorna el servicio de base de datos adecuado de la lista según el tipo devuelto.
     */
    public static DbService pasoExtra08(List<DbService> services, Class<? extends DbService> targetClass) {
        // GUÍA: teoría 4.3 + streams del b01 — elegir el bean por su tipo concreto.
        // PISTA: return services.stream()
        //                       .filter(targetClass::isInstance)
        //                       .findFirst()
        //                       .orElse(null);
        // OJO: el test pasa [DevDbService, ProdDbService] y pide ProdDbService.class;
        //   debe volver la instancia ProdDbService (y getUrl()=="jdbc:postgresql://db").
        // CULTURA: esto es, en esencia, lo que hace Spring al inyectar "el bean de
        //   ese tipo" entre varios candidatos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * RETO EXTRA 09: Resolutor dinámico de perfiles según la región del sistema.
     */
    public static String[] pasoExtra09(String systemRegion) {
        // GUÍA: implementa la regla que codifica el test (mapeo región → perfiles):
        //   "EU"   -> {"prod", "eu"}   (región europea: prod + perfil regional en MINÚSCULA)
        //   "ASIA" -> {"dev"}          (cualquier otra: solo dev)
        // PISTA: if ("EU".equalsIgnoreCase(systemRegion))
        //            return new String[]{"prod", systemRegion.toLowerCase()};
        //        return new String[]{"dev"};
        // OJO: el test usa assertArrayEquals, así que el ORDEN y el número de
        //   elementos deben ser exactos; "EU" baja a "eu" en el segundo hueco.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Retorna la instancia de ComplexProfileConfig.
     */
    public static ComplexProfileConfig pasoExtra10() {
        // GUÍA: una línea — return new ComplexProfileConfig();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
