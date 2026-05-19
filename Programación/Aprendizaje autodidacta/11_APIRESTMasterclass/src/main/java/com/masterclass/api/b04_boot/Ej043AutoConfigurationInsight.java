package com.masterclass.api.b04_boot;

import java.util.Set;

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
}
