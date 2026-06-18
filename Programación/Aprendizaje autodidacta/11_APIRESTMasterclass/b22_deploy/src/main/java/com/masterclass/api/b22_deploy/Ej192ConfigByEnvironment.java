package com.masterclass.api.b22_deploy;

public final class Ej192ConfigByEnvironment {
    private Ej192ConfigByEnvironment() {}
    public static boolean ejecutar() {
        // TODO 1: evita guardar credenciales en el application.yml (evitar leaks en Git).
        // TODO 2: usa ${DB_URL:jdbc:postgresql://localhost:5432/app} como fallback local.
        // TODO 3: en Docker Compose, pasa DB_URL inyectando 'jdbc:postgresql://db:5432/app'.
        // TODO 4: inyecta JWT_SECRET como variable de entorno segura.
        // TODO 5: en Kubernetes, este secreto vendría de un ConfigMap o Secret.
        // TODO 6: inyecta un perfil activo con SPRING_PROFILES_ACTIVE=prod.
        // TODO 7: valida si el entorno local sobreescribe variables críticas.
        // TODO 8: usa @ConfigurationProperties(prefix = "app") y relájate sobre el formato camelCase vs ENV_VAR.
        // TODO 9: lanza un IllegalStateException en arranque si falta JWT_SECRET.
        // TODO 10: retorna el estado simulado de carga exitosa.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    /**
     * RETO EXTRA 01: Verifica si el nombre de una propiedad de configuración contiene términos sensibles.
     * 
     * @param clave el nombre de la propiedad a analizar
     * @return true si es una propiedad que requiere ocultación de secretos
     */
    public static boolean esPropiedadMarcadaComoSecreta(String clave) {
        // GUÍA: teoría 22.4 (detectar secretos para enmascararlos en logs).
        // 1. null -> false.
        // 2. Es secreta si su nombre contiene alguno de los marcadores típicos:
        //    "password", "secret", "token", "key" (compara en minúsculas).
        // PISTA: String k = clave.toLowerCase();
        //        return k.contains("password") || k.contains("secret")
        //            || k.contains("token") || k.contains("key");
        // OJO: el test marca como secretas "spring.datasource.password" y
        //      "app.jwt-secret"; NO marca "spring.application.name".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropiedadMarcadaComoSecreta");
    }

    /**
     * RETO EXTRA 02: Parsea el valor por defecto (fallback) en una expresión de Spring Boot.
     * Ejemplo: "${DB_URL:jdbc:postgresql://localhost:5432/app}" -> "jdbc:postgresql://localhost:5432/app"
     * 
     * @param expresion expresion de Spring Boot
     * @return el valor por defecto, o la propia cadena si no tiene fallback
     */
    public static String parsearFallbackValor(String expresion) {
        // GUÍA: teoría 22.4 (sintaxis ${VAR:default}).
        // 1. Si NO empieza por "${" o no contiene ':' -> devuelve la propia cadena
        //    ("direct-value" se devuelve tal cual: no es un placeholder).
        // 2. Si lo es: el fallback va entre el PRIMER ':' y el '}' final.
        // PISTA: si expresion.startsWith("${") y tiene ':':
        //        int c = expresion.indexOf(':');
        //        return expresion.substring(c + 1, expresion.length() - 1);
        // OJO: la URL del default contiene MÁS ':' ("jdbc:postgresql://...:5432");
        //      usa indexOf(':') (el PRIMERO, el que separa VAR del default), no
        //      split(":"). Recorta el '}' final con length()-1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearFallbackValor");
    }

    /**
     * RETO EXTRA 03: Valida que una URL de conexión comience con el protocolo JDBC apropiado para Postgres.
     * 
     * @param url la URL a validar
     * @return true si comienza por 'jdbc:postgresql://'
     */
    public static boolean esUrlBaseDatosValida(String url) {
        // GUÍA: teoría 22.4. Debe ser una URL JDBC de Postgres.
        // 1. null -> false.
        // 2. Debe empezar por "jdbc:postgresql://".
        // PISTA: return url != null && url.startsWith("jdbc:postgresql://");
        // OJO: "jdbc:mysql://..." da false (es MySQL, no Postgres).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUrlBaseDatosValida");
    }

    /**
     * RETO EXTRA 04: Convierte una variable de entorno en formato SNAKE_CASE a relaxed binding camelCase.
     * Ejemplo: "SPRING_DATASOURCE_USERNAME" -> "springDatasourceUsername"
     * 
     * @param envVar nombre de la variable de entorno
     * @return el nombre en camelCase
     */
    public static String convertirEnvVarToCamelCase(String envVar) {
        // GUÍA: teoría 22.4 (relaxed binding: ENV_VAR -> camelCase).
        // 1. Pasa todo a minúsculas y parte por '_'.
        // 2. El PRIMER trozo queda tal cual; cada trozo siguiente con su inicial
        //    en mayúscula. Concaténalos.
        // PISTA: split("_"); primer token en minúsculas; resto:
        //        Character.toUpperCase(t.charAt(0)) + t.substring(1).
        // OJO: "SPRING_DATASOURCE_USERNAME" -> "springDatasourceUsername"
        //      (la 's' inicial en minúscula, no "Spring..."). Pista: baja TODO a
        //      lowercase primero y luego sube solo la inicial de los trozos 2..n.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirEnvVarToCamelCase");
    }

    /**
     * RETO EXTRA 05: Comprueba si el secreto de firma JWT cumple la longitud mínima recomendada (256 bits / 32 bytes).
     * 
     * @param secreto el secreto en texto plano
     * @return true si tiene al menos 32 caracteres
     */
    public static boolean validarJwtSecretLargo(String secreto) {
        // GUÍA: teoría 22.4 (HMAC-256 necesita >= 256 bits = 32 bytes/caracteres).
        // 1. null -> false.
        // 2. Válido si tiene al menos 32 caracteres.
        // PISTA: return secreto != null && secreto.length() >= 32;
        // OJO: "short" (5) da false; el de 39 caracteres da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para validarJwtSecretLargo");
    }

    /**
     * RETO EXTRA 06: Verifica si un perfil determinado está activo dada la propiedad comas-separada de Spring.
     * 
     * @param perfilesActivos valor de SPRING_PROFILES_ACTIVE (ej. "prod,security")
     * @param perfilBuscado perfil a buscar (ej. "prod")
     * @return true si está activo
     */
    public static boolean esPerfilActivo(String perfilesActivos, String perfilBuscado) {
        // GUÍA: teoría 22.4 (SPRING_PROFILES_ACTIVE=prod,security separado por comas).
        // 1. Si perfilesActivos o perfilBuscado son null -> false.
        // 2. Parte por ',' y comprueba si alguno coincide con el buscado.
        // PISTA: Arrays.asList(perfilesActivos.split(",")).contains(perfilBuscado);
        //        (o un stream con anyMatch). Conviene trim() por si hay espacios.
        // OJO: el test busca "test" dentro de "dev,test,prod" (en medio) -> no
        //      vale con startsWith/endsWith; hay que partir y comparar exacto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPerfilActivo");
    }

    /**
     * RETO EXTRA 07: Comprueba si un valor de configuración contiene algún placeholder no resuelto (${...}).
     * 
     * @param valor el valor a verificar
     * @return true si contiene un placeholder
     */
    public static boolean contienePlaceholders(String valor) {
        // GUÍA: teoría 22.4. Detecta un placeholder ${...} sin resolver.
        // 1. null -> false.
        // 2. Contiene "${" y, después, un '}'.
        // PISTA: return valor != null && valor.contains("${") && valor.contains("}");
        //        (más estricto con regex: valor.matches(".*\\$\\{.+}.*")).
        // OJO: "hello ${user.name}" da true; "hello world" da false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contienePlaceholders");
    }

    /**
     * RETO EXTRA 08: Genera la sintaxis Spring del placeholder para una propiedad con su valor fallback.
     * 
     * @param clave la propiedad
     * @param fallback el valor por defecto
     * @return la expresión formateada
     */
    public static String generarStringConFallback(String clave, String fallback) {
        // GUÍA: teoría 22.4 (inverso del reto 02: construir ${CLAVE:fallback}).
        // 1. Si clave o fallback son null -> IllegalArgumentException.
        // 2. Formato: "${" + clave + ":" + fallback + "}".
        // PISTA: return "${" + clave + ":" + fallback + "}";
        // OJO: el test compara con equals "${DB_PASSWORD:root}" y exige
        //      IllegalArgumentException con clave null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarStringConFallback");
    }

    /**
     * RETO EXTRA 09: Valida que el nombre de una variable de entorno POSIX estándar sea correcto.
     * Debe ser todo mayúsculas, dígitos y guiones bajos.
     * 
     * @param nombre variable a validar
     * @return true si es válido
     */
    public static boolean esVariableDeEntornoValida(String nombre) {
        // GUÍA: teoría 22.4 (variable POSIX: MAYÚSCULAS, dígitos y guion bajo).
        // 1. null -> false.
        // 2. Solo letras MAYÚSCULAS, dígitos y '_'.
        // PISTA: return nombre != null && nombre.matches("[A-Z0-9_]+");
        // OJO: rechaza "spring_datasource_url" (minúsculas) y
        //      "SPRING-DATASOURCE-URL" (guion medio); acepta "SPRING_DATASOURCE_URL".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVariableDeEntornoValida");
    }

    /**
     * RETO EXTRA 10: Sobrescribe programáticamente una propiedad en un mapa de configuración.
     * 
     * @param original mapa original
     * @param clave clave a establecer
     * @param nuevoValor valor a asignar
     * @return un nuevo mapa con la propiedad modificada
     */
    public static java.util.Map<String, String> sobreescribirPropiedad(java.util.Map<String, String> original, String clave, String nuevoValor) {
        // GUÍA: teoría 22.4 (sobreescritura de config SIN mutar el original).
        // 1. Crea una COPIA del mapa original (no lo modifiques in situ).
        // 2. Pon la clave/valor en la copia y devuélvela.
        // PISTA: Map<String,String> copia = new HashMap<>(original);
        //        copia.put(clave, nuevoValor); return copia;
        // OJO/CUIDADO: el test verifica que original.size() sigue siendo 1 tras
        //      la llamada -> si haces original.put(...) MUTAS el original y falla.
        //      Es el patrón inmutable que viste con los records en b01.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sobreescribirPropiedad");
    }

}
