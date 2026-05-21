package com.masterclass.api.b04_boot;

import java.util.Map;

/**
 * Ejercicio 039 · Resolución de propiedades con valor por defecto.
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.2).
 *
 * <p>Replica la sintaxis {@code ${clave:default}} de {@code @Value} sobre un
 * mapa que simula el {@code application.yml}.
 */
public final class Ej039ApplicationProperties {

    private Ej039ApplicationProperties() {
    }

    /**
     * Resuelve una expresión tipo {@code "${app.timeout:30}"}.
     *
     * @param props      mapa de propiedades cargadas
     * @param expression expresión con o sin valor por defecto tras ':'
     * @return el valor encontrado, el default si no existe, o "" si no hay default
     */
    public static String resolve(Map<String, String> props, String expression) {
        // TODO 1: si expression es null/vacía, devuelve "".
        // TODO 2: valida que empiece por "${" y termine por "}"; si no, devuelve "" (formato inválido).
        // TODO 3: recorta los delimitadores "${" y "}" para quedarte con el interior.
        // TODO 4: localiza el primer ':' (separa clave de valor por defecto).
        // TODO 5: si NO hay ':', la clave es todo el interior y no hay default.
        // TODO 6: si HAY ':', la clave es lo anterior y el default lo posterior.
        // TODO 7: busca la clave en 'props'.
        // TODO 8: si existe, devuelve su valor (la config externa gana).
        // TODO 9: si no existe pero hay default, devuelve el default.
        // TODO 10: si no existe y no hay default, devuelve "".
        return "";
    }

    public static void main(String[] args) {
        var props = Map.of("app.region", "eu-west-1");
        System.out.println(resolve(props, "${app.region:us}"));
        System.out.println(resolve(props, "${app.timeout:30}"));
    }

    // --- MÉTODOS Y CLASES DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Obtención de propiedades desde el Environment de Spring.
     */
    public static String resolverPropiedadSpring(org.springframework.core.env.Environment env, String clave) {
        // TODO extra (Reto 1): Usa env.getProperty(clave) para recuperar el valor.
        return null;
    }

    /**
     * Reto Extra 2: Recuperación de propiedad con fallback de contingencia dinámico en Spring.
     */
    public static String resolverConDefaultSpring(org.springframework.core.env.Environment env, String clave, String defaultVal) {
        // TODO extra (Reto 2): Usa env.getProperty(clave, defaultVal) para recuperar el valor o el fallback.
        return null;
    }

    /**
     * Reto Extra 3: Recuperación con conversión directa a tipo Integer.
     */
    public static Integer resolverComoInteger(org.springframework.core.env.Environment env, String clave) {
        // TODO extra (Reto 3): Usa env.getProperty(clave, Integer.class) para conversión automática de tipo.
        return null;
    }

    /**
     * Reto Extra 4: Recuperación con conversión directa a tipo Boolean.
     */
    public static Boolean resolverComoBoolean(org.springframework.core.env.Environment env, String clave) {
        // TODO extra (Reto 4): Usa env.getProperty(clave, Boolean.class) para conversión automática de tipo.
        return null;
    }

    /**
     * Reto Extra 5: Conversión de propiedades con delimitadores (comas) a listas de Strings.
     */
    public static java.util.List<String> resolverComoLista(org.springframework.core.env.Environment env, String clave) {
        // TODO extra (Reto 5): Usa env.getProperty(clave) y parsea el valor separado por comas a un List<String>, o usa otra facilidad de Spring.
        return null;
    }

    /**
     * Reto Extra 6: Componente con valor inyectado por defecto usando @Value.
     */
    public static class BeanConValueDefault {
        // TODO extra (Reto 6): Anota este campo con @Value("${app.nombre:Anonimo}") para la inyección por defecto.
        private String nombre;

        public String getNombre() {
            return nombre;
        }
    }

    /**
     * Reto Extra 7: Componente con inyección de colecciones mediante expresiones SpEL.
     */
    public static class BeanConValueList {
        // TODO extra (Reto 7): Anota este campo con @Value("#{'${app.admins:admin}'.split(',')}") para inyectar una lista.
        private java.util.List<String> admins;

        public java.util.List<String> getAdmins() {
            return admins;
        }
    }

    /**
     * Reto Extra 8: Comprobación estricta de la presencia de una propiedad en el Environment.
     */
    public static boolean verificarPropiedadDefinida(org.springframework.core.env.Environment env, String clave) {
        // TODO extra (Reto 8): Usa env.containsProperty(clave) para validar su presencia física.
        return false;
    }

    /**
     * Reto Extra 9: Resolución recursiva de expresiones y marcadores homónimos anidados.
     */
    public static String resolverPropiedadesHomonimasSpring(org.springframework.core.env.Environment env, String expresion) {
        // TODO extra (Reto 9): Usa env.resolveRequiredPlaceholders(expresion) o resolvePlaceholders(expresion) para resolver marcadores anidados.
        return null;
    }

    /**
     * Reto Extra 10: Carga dinámica de fuentes de propiedades en caliente sobre el Environment.
     */
    public static void registrarPropertySourceManual(org.springframework.core.env.ConfigurableEnvironment env, String nombreSource, java.util.Map<String, Object> mapa) {
        // TODO extra (Reto 10): Crea un MapPropertySource y agrégalo al environment configurable mediante env.getPropertySources().addFirst(...).
    }

}
