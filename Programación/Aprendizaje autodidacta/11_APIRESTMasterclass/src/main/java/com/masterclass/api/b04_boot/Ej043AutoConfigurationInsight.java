package com.masterclass.api.b04_boot;

import java.util.Set;
import java.util.List;

/**
 * Ejercicio 043 · Cómo decide la autoconfiguración (conditional on class).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.1).
 *
 * <p>Spring Boot activa una autoconfig si su clase "trigger" está en el classpath
 * y no hay ya un bean equivalente. Replica esa decisión.
 */
public final class Ej043AutoConfigurationInsight {

    private Ej043AutoConfigurationInsight() {
    }

    /**
     * Decide si una autoconfiguración debe activarse.
     *
     * @param classpath        clases presentes en el classpath
     * @param triggerClass     clase requerida por la autoconfig (@ConditionalOnClass)
     * @param userBeanPresent  true si el usuario ya definió un bean equivalente
     * @return true si la autoconfig debe aplicarse
     */
    public static boolean shouldActivate(Set<String> classpath, String triggerClass, boolean userBeanPresent) {
        // TODO 1: si classpath es null, trátalo como conjunto vacío.
        // TODO 2: si triggerClass es null/vacío, la condición no puede cumplirse -> false.
        // TODO 3: comprueba si 'triggerClass' está en el classpath (@ConditionalOnClass).
        // TODO 4: si NO está, la autoconfig no aplica -> false.
        // TODO 5: aplica @ConditionalOnMissingBean: si el usuario ya tiene su bean...
        // TODO 6: ...la autoconfig se "echa a un lado" -> false.
        // TODO 7: solo si la clase está Y no hay bean de usuario -> true.
        // TODO 8: el orden de evaluación importa: primero clase, luego missing bean.
        // TODO 9: no actives por defecto: ante duda, false (principio conservador).
        // TODO 10: devuelve el booleano final.
        return false;
    }

    public static void main(String[] args) {
        var cp = Set.of("javax.sql.DataSource", "org.h2.Driver");
        System.out.println(shouldActivate(cp, "org.h2.Driver", false));
        System.out.println(shouldActivate(cp, "org.h2.Driver", true));
    }

    /**
     * RETO EXTRA 02: Bean de contingencia autoconfigurado.
     */
    public static class AutoConfiguredService {
        public String getOrigin() { return "Autoconfigured"; }
    }

    /**
     * RETO EXTRA 03: Bean personalizado por el usuario.
     */
    public static class UserConfiguredService {
        public String getOrigin() { return "User Customized"; }
    }

    /**
     * RETO EXTRA 04: Componente condicionado a la existencia de un contexto web.
     */
    // TODO extra: Añade la anotación @org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
    public static class WebSpecificComponent {
        public String getContextType() { return "Web Context"; }
    }

    /**
     * RETO EXTRA 05: Componente condicionado a entornos que NO son web.
     */
    // TODO extra: Añade la anotación @org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication (o equivalente)
    public static class NonWebSpecificComponent {
        public String getContextType() { return "Non-Web Context"; }
    }

    /**
     * RETO EXTRA 07: Componente condicionado a que una propiedad esté habilitada.
     */
    // TODO extra: Añade @org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(name = "app.feature.enabled", havingValue = "true")
    public static class FeatureToggleComponent {
        public String status() { return "Active"; }
    }

    /**
     * RETO EXTRA 01: Comprueba si una clase de autoconfiguración está excluida en el cargador de la aplicación.
     */
    public static boolean pasoExtra01(Class<?> bootAppClass, String autoConfigClassName) {
        // TODO extra: Inspecciona reflexivamente si 'bootAppClass' tiene la anotación @SpringBootApplication o @EnableAutoConfiguration declarando exclusiones para autoConfigClassName.
        return false;
    }

    /**
     * RETO EXTRA 02: Retorna una nueva instancia del Bean autoconfigurado.
     */
    public static AutoConfiguredService pasoExtra02() {
        // TODO extra: Retorna una nueva instancia de AutoConfiguredService.
        return null;
    }

    /**
     * RETO EXTRA 03: Selecciona dinámicamente si se debe usar el Bean del usuario o el de contingencia de la autoconfiguración.
     */
    public static Object pasoExtra03(boolean hasUserBean) {
        // TODO extra: Si hasUserBean es true, retorna UserConfiguredService; en caso contrario, retorna AutoConfiguredService.
        return null;
    }

    /**
     * RETO EXTRA 04: Retorna una nueva instancia del componente específico web.
     */
    public static WebSpecificComponent pasoExtra04() {
        // TODO extra: Retorna una nueva instancia de WebSpecificComponent.
        return null;
    }

    /**
     * RETO EXTRA 05: Retorna una nueva instancia del componente específico no-web.
     */
    public static NonWebSpecificComponent pasoExtra05() {
        // TODO extra: Retorna una nueva instancia de NonWebSpecificComponent.
        return null;
    }

    /**
     * RETO EXTRA 06: Decisión de candidato único de autoconfiguración.
     */
    public static boolean pasoExtra06(List<String> beanNames, String primaryBean) {
        // TODO extra: Devuelve true si la lista de nombres de Beans contiene exactamente un elemento, o si contiene a primaryBean de forma explícita.
        return false;
    }

    /**
     * RETO EXTRA 07: Retorna el componente sujeto a feature toggling.
     */
    public static FeatureToggleComponent pasoExtra07() {
        // TODO extra: Retorna una nueva instancia de FeatureToggleComponent.
        return null;
    }

    /**
     * RETO EXTRA 08: Ordenación de precedencia de carga de autoconfiguraciones (simulación).
     */
    public static List<String> pasoExtra08(List<String> configs) {
        // TODO extra: Ordena la lista garantizando que "DataSourceAutoConfiguration" se cargue siempre antes que "HibernateJpaAutoConfiguration".
        return null;
    }

    /**
     * RETO EXTRA 09: Simula la validación de registro de una autoconfiguración dentro de spring.factories o imports.
     */
    public static boolean pasoExtra09(String resourceContent, String targetAutoConfigClass) {
        // TODO extra: Comprueba si el contenido del fichero de importación contiene la línea completa con targetAutoConfigClass.
        return false;
    }

    /**
     * RETO EXTRA 10: Lee y extrae el conjunto de autoconfiguraciones válidas a partir de un ConditionEvaluationReport.
     */
    public static Set<String> pasoExtra10(org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport report) {
        // TODO extra: Filtra y extrae del reporte las clases de configuración cuya condición de coincidencia (match) fue exitosa.
        return null;
    }

}
