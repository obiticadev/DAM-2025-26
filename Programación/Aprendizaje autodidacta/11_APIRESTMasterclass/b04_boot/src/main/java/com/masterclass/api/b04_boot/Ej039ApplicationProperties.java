package com.masterclass.api.b04_boot;

import java.util.Map;

/**
 * Ejercicio 039 · Resolución de propiedades con valor por defecto.
 *
 * <p>Teoría: {@code teoria/04_Spring_Boot_Config.md} (sección 4.1).
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
        // GUÍA: teoría 4.1 (tabla del Environment). Esto es lo que hace @Value por dentro.
        // 1. (Defensa) si env o clave son null, devuelve null.
        // 2. Lee directamente del almacén de propiedades de Spring:
        // PISTA: return env.getProperty(clave);
        // OJO: el test mete "app.nombre"="Antigravity" con env.setProperty(...) y
        //      espera exactamente "Antigravity". getProperty devuelve null si la
        //      clave no existe (NO lanza); eso lo aprovechan los retos siguientes.
        // CULTURA: MockEnvironment es el Environment de juguete de spring-test; el
        //      real (cargado de application.yml + env vars) tiene la MISMA API.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverPropiedadSpring");
    }

    /**
     * Reto Extra 2: Recuperación de propiedad con fallback de contingencia dinámico en Spring.
     */
    public static String resolverConDefaultSpring(org.springframework.core.env.Environment env, String clave, String defaultVal) {
        // GUÍA: teoría 4.1 — la sobrecarga con default de getProperty.
        // 1. Usa la versión de dos argumentos:
        // PISTA: return env.getProperty(clave, defaultVal);
        // OJO: el test pide la clave "inexistente" (no existe) y espera "Fallback".
        //      Spring devuelve el default SOLO si la clave falta; si existiera, el
        //      valor real ganaría (igual que ${clave:default} de la teoría).
        // Reutiliza la idea de resolverPropiedadSpring (reto 1): es el mismo método
        //      con una sobrecarga distinta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverConDefaultSpring");
    }

    /**
     * Reto Extra 3: Recuperación con conversión directa a tipo Integer.
     */
    public static Integer resolverComoInteger(org.springframework.core.env.Environment env, String clave) {
        // GUÍA: teoría 4.1 — Environment convierte tipos por ti.
        // 1. Pide la propiedad ya convertida al tipo destino:
        // PISTA: return env.getProperty(clave, Integer.class);
        // OJO: el test guarda "app.timeout"="60" (un String) y espera
        //      Integer.valueOf(60). NO uses Integer.parseInt a mano: la gracia es
        //      que Spring tiene un servicio de conversión integrado (el mismo que
        //      usará @ConfigurationProperties en el Ej040).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverComoInteger");
    }

    /**
     * Reto Extra 4: Recuperación con conversión directa a tipo Boolean.
     */
    public static Boolean resolverComoBoolean(org.springframework.core.env.Environment env, String clave) {
        // GUÍA: idéntico al reto 3 pero con Boolean.class.
        // PISTA: return env.getProperty(clave, Boolean.class);
        // OJO: "app.activo"="true" (String) debe volver como Boolean.TRUE. La
        //      conversión String→Boolean es la misma que aplica Spring Boot al leer
        //      flags del yml.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverComoBoolean");
    }

    /**
     * Reto Extra 5: Conversión de propiedades con delimitadores (comas) a listas de Strings.
     */
    public static java.util.List<String> resolverComoLista(org.springframework.core.env.Environment env, String clave) {
        // GUÍA: teoría 4.1 — una propiedad "a,b,c" se enlaza a List.
        // 1. Pide el tipo List directamente:
        // PISTA: return env.getProperty(clave, java.util.List.class);
        //      (puedes castear o usar @SuppressWarnings por el genérico crudo).
        // OJO: "app.usuarios"="admin,user,guest" → List.of("admin","user","guest"),
        //      SIN espacios, en ese orden. Spring parte por comas él solo: no hagas
        //      split manual a menos que quieras (entonces split(",") y trim).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverComoLista");
    }

    /**
     * Reto Extra 6: Componente con valor inyectado por defecto usando @Value.
     */
    public static class BeanConValueDefault {
        // GUÍA: teoría 4.1 — @Value con default sobre un campo de un bean Spring.
        // El test crea el contexto SIN definir "app.nombre", así que debe ganar el
        // default. Para que getNombre() devuelva "Anonimo", anota el campo:
        //   @org.springframework.beans.factory.annotation.Value("${app.nombre:Anonimo}")
        //   private String nombre;
        // (descomenta la anotación de arriba sobre el campo). El ":Anonimo" es el
        // valor por defecto EXACTO que comprueba el test.
        // CULTURA: así es como un @Component/@Service real recibe su config; aquí el
        // bean se registra a mano en el @Configuration del test.
        private String nombre;

        public String getNombre() {
            return nombre;
        }
    }

    /**
     * Reto Extra 7: Componente con inyección de colecciones mediante expresiones SpEL.
     */
    public static class BeanConValueList {
        // GUÍA: teoría 4.1/4.2 — @Value puede inyectar una lista.
        // El test espera getAdmins() == List.of("admin") sin definir la propiedad,
        // así que de nuevo manda el default. Anota el campo:
        //   @org.springframework.beans.factory.annotation.Value("${app.admins:admin}")
        //   private java.util.List<String> admins;
        // Spring convierte el String "admin" del default en una lista de un elemento.
        // PISTA SpEL (alternativa): "#{'${app.admins:admin}'.split(',')}" parte una
        // cadena CSV en lista vía SpEL; para este test basta el default simple.
        private java.util.List<String> admins;

        public java.util.List<String> getAdmins() {
            return admins;
        }
    }

    /**
     * Reto Extra 8: Comprobación estricta de la presencia de una propiedad en el Environment.
     */
    public static boolean verificarPropiedadDefinida(org.springframework.core.env.Environment env, String clave) {
        // GUÍA: teoría 4.1 — containsProperty responde sí/no SIN traer el valor.
        // PISTA: return env.containsProperty(clave);
        // OJO: el test comprueba false ANTES de setear "app.url" y true DESPUÉS.
        //      No uses getProperty(...)!=null si lo que quieres es "¿está definida?":
        //      containsProperty es la respuesta semántica correcta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarPropiedadDefinida");
    }

    /**
     * Reto Extra 9: Resolución recursiva de expresiones y marcadores homónimos anidados.
     */
    public static String resolverPropiedadesHomonimasSpring(org.springframework.core.env.Environment env, String expresion) {
        // GUÍA: teoría 4.1 — los ${...} pueden anidarse como default unos de otros.
        // 1. Deja que Spring resuelva la expresión COMPLETA por ti (incluida la
        //    anidación), en vez de parsearla a mano:
        // PISTA: return env.resolvePlaceholders(expresion);
        // OJO: el test pasa "${app.env:${app.default-env:prod}}" con app.env="dev"
        //      definido, y espera "dev" (gana la clave externa, no el default
        //      anidado). resolvePlaceholders entiende la anidación: si app.env
        //      faltara, intentaría app.default-env, y si esa faltara, "prod".
        // CULTURA: es exactamente el algoritmo de la sección 4.1, pero Spring ya lo
        //      trae hecho en PropertySourcesPlaceholderConfigurer.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverPropiedadesHomonimasSpring");
    }

    /**
     * Reto Extra 10: Carga dinámica de fuentes de propiedades en caliente sobre el Environment.
     */
    public static void registrarPropertySourceManual(org.springframework.core.env.ConfigurableEnvironment env, String nombreSource, java.util.Map<String, Object> mapa) {
        // GUÍA: teoría 4.4 — el Environment es una LISTA de PropertySource manipulable.
        // 1. Accede a la lista: env.getPropertySources().
        // 2. Añade una fuente nueva basada en el mapa, con prioridad:
        // PISTA: env.getPropertySources()
        //           .addFirst(new org.springframework.core.env.MapPropertySource(nombreSource, mapa));
        // OJO: el test mete {"app.puerto": 8080} (un Integer) y luego espera
        //      env.getProperty("app.puerto") == "8080" (String). getProperty
        //      convierte a String al leer: tú solo registras la fuente.
        // CULTURA: esto es lo que hace Spring Cloud Config al inyectar config remota
        //      "en caliente"; lo profundizas en el Ej042.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarPropertySourceManual");
    }

}
