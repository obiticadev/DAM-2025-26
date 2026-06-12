package com.masterclass.api.b04_boot;

import java.util.Set;
import java.util.List;

/**
 * Ejercicio 043 · Cómo decide la autoconfiguración (conditional on class).
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.5).
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

    // === Componentes "condicionados" (LOS RETOS) ============================
    // El test los instancia a mano (pasoExtraNN) y comprueba un método. Las
    // anotaciones @ConditionalOnXxx que se piden son de realismo: documentan
    // cuándo existirían en una app real. Tu trabajo: implementar el método con el
    // literal EXACTO que espera el test.

    /**
     * RETO EXTRA 02: Bean de contingencia autoconfigurado.
     */
    public static class AutoConfiguredService {
        // GUÍA: el test (pasoExtra02) espera getOrigin() == "Autoconfigured".
        public String getOrigin() {
            throw new UnsupportedOperationException("TODO: Implementar getOrigin() devolviendo \"Autoconfigured\"");
        }
    }

    /**
     * RETO EXTRA 03: Bean personalizado por el usuario.
     */
    public static class UserConfiguredService {
        // GUÍA: el test solo comprueba "instanceof UserConfiguredService" (pasoExtra03),
        // así que basta con que la clase exista. Representa el bean que el USUARIO
        // define y que, por @ConditionalOnMissingBean, desplaza al autoconfigurado.
    }

    /**
     * RETO EXTRA 04: Componente condicionado a la existencia de un contexto web.
     */
    // GUÍA: para realismo, @org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
    public static class WebSpecificComponent {
        // GUÍA: el test (pasoExtra04) espera getContextType() == "Web Context".
        public String getContextType() {
            throw new UnsupportedOperationException("TODO: Implementar getContextType() devolviendo \"Web Context\"");
        }
    }

    /**
     * RETO EXTRA 05: Componente condicionado a entornos que NO son web.
     */
    // GUÍA: para realismo, @ConditionalOnNotWebApplication (paquete .condition).
    public static class NonWebSpecificComponent {
        // GUÍA: el test (pasoExtra05) espera getContextType() == "Non-Web Context".
        public String getContextType() {
            throw new UnsupportedOperationException("TODO: Implementar getContextType() devolviendo \"Non-Web Context\"");
        }
    }

    /**
     * RETO EXTRA 07: Componente condicionado a que una propiedad esté habilitada.
     */
    // GUÍA: para realismo,
    //   @ConditionalOnProperty(name = "app.feature.enabled", havingValue = "true")
    public static class FeatureToggleComponent {
        // GUÍA: el test (pasoExtra07) espera status() == "Active".
        public String status() {
            throw new UnsupportedOperationException("TODO: Implementar status() devolviendo \"Active\"");
        }
    }

    // === Métodos de los retos ===============================================

    /**
     * RETO EXTRA 01: Comprueba si una clase de autoconfiguración está excluida en el cargador de la aplicación.
     */
    public static boolean pasoExtra01(Class<?> bootAppClass, String autoConfigClassName) {
        // GUÍA: teoría 4.5 — @SpringBootApplication(exclude = {...}) lista las
        // autoconfiguraciones desactivadas; léelo por reflexión.
        // 1. var ann = bootAppClass.getAnnotation(
        //        org.springframework.boot.autoconfigure.SpringBootApplication.class);
        // 2. ann.exclude() es un Class<?>[]; comprueba si ALGUNA tiene ese nombre:
        //    return java.util.Arrays.stream(ann.exclude())
        //               .anyMatch(c -> c.getName().equals(autoConfigClassName));
        // OJO: el test excluye DataSourceAutoConfiguration → true para esa clase y
        //   false para WebMvcAutoConfiguration (que no está excluida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * RETO EXTRA 02: Retorna una nueva instancia del Bean autoconfigurado.
     */
    public static AutoConfiguredService pasoExtra02() {
        // GUÍA: una línea — return new AutoConfiguredService();
        // (recuerda implementar getOrigin() en esa clase).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * RETO EXTRA 03: Selecciona dinámicamente si se debe usar el Bean del usuario o el de contingencia de la autoconfiguración.
     */
    public static Object pasoExtra03(boolean hasUserBean) {
        // GUÍA: teoría 4.5 — esta es la lógica de @ConditionalOnMissingBean en vivo.
        // PISTA: return hasUserBean ? new UserConfiguredService() : new AutoConfiguredService();
        // OJO: hasUserBean=true → instancia de UserConfiguredService (gana el del
        //   usuario); false → AutoConfiguredService (entra la contingencia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * RETO EXTRA 04: Retorna una nueva instancia del componente específico web.
     */
    public static WebSpecificComponent pasoExtra04() {
        // GUÍA: una línea — return new WebSpecificComponent();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * RETO EXTRA 05: Retorna una nueva instancia del componente específico no-web.
     */
    public static NonWebSpecificComponent pasoExtra05() {
        // GUÍA: una línea — return new NonWebSpecificComponent();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * RETO EXTRA 06: Decisión de candidato único de autoconfiguración.
     */
    public static boolean pasoExtra06(List<String> beanNames, String primaryBean) {
        // GUÍA: teoría 4.5 — es la lógica de @ConditionalOnSingleCandidate: hay un
        // único candidato, O bien entre varios hay uno marcado como primario.
        // PISTA: return beanNames.size() == 1 || beanNames.contains(primaryBean);
        // OJO con los tres casos del test:
        //   ["singleBean"], "somePrimary"          -> true  (un solo candidato)
        //   ["beanA","somePrimary"], "somePrimary" -> true  (varios, pero hay primario)
        //   ["beanA","beanB"], "somePrimary"       -> false (varios y ninguno primario)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * RETO EXTRA 07: Retorna el componente sujeto a feature toggling.
     */
    public static FeatureToggleComponent pasoExtra07() {
        // GUÍA: una línea — return new FeatureToggleComponent();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * RETO EXTRA 08: Ordenación de precedencia de carga de autoconfiguraciones (simulación).
     */
    public static List<String> pasoExtra08(List<String> configs) {
        // GUÍA: teoría 4.5 — algunas autoconfiguraciones deben cargarse ANTES que
        // otras (DataSource antes que JPA, porque JPA necesita el datasource). Es lo
        // que en Spring expresan @AutoConfigureBefore/@AutoConfigureAfter.
        // 1. Define una prioridad: "DataSourceAutoConfiguration" debe ir antes que
        //    "HibernateJpaAutoConfiguration".
        // PISTA: ordena con un comparador que dé índice 0 a DataSource y 1 a
        //    Hibernate (o usa un Map<String,Integer> de prioridades y ordena por él).
        //    Devuelve una NUEVA lista ordenada (no mutes la de entrada a ciegas).
        // OJO: el test espera sorted.get(0)=="DataSourceAutoConfiguration" y
        //   sorted.get(1)=="HibernateJpaAutoConfiguration".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * RETO EXTRA 09: Simula la validación de registro de una autoconfiguración dentro de spring.factories o imports.
     */
    public static boolean pasoExtra09(String resourceContent, String targetAutoConfigClass) {
        // GUÍA: teoría 4.5 — el fichero ...AutoConfiguration.imports lista una clase
        // por línea; "está registrada" ⇔ aparece como una de esas líneas.
        // PISTA: return resourceContent.lines()
        //                .map(String::trim)
        //                .anyMatch(linea -> linea.equals(targetAutoConfigClass));
        // OJO: el test da dos líneas (DataSource y WebMvc) y pide true para
        //   DataSource y false para SecurityAutoConfiguration (que no figura).
        //   Compara la línea COMPLETA, no con contains (evita falsos positivos por
        //   nombres que sean prefijo de otros).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * RETO EXTRA 10: Lee y extrae el conjunto de autoconfiguraciones válidas a partir de un ConditionEvaluationReport.
     */
    public static Set<String> pasoExtra10(org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport report) {
        // GUÍA: teoría 4.5 — el ConditionEvaluationReport es el informe REAL de qué
        // condiciones casaron (lo que ves con --debug al arrancar Spring Boot).
        // PISTA: return report.getConditionAndOutcomesBySource().entrySet().stream()
        //            .filter(e -> e.getValue().isFullMatch())   // solo las que casaron del todo
        //            .map(java.util.Map.Entry::getKey)
        //            .collect(java.util.stream.Collectors.toSet());
        // OJO: el test usa un contexto VACÍO, así que el informe puede no tener
        //   coincidencias: basta con devolver un Set NO null (aunque esté vacío).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
