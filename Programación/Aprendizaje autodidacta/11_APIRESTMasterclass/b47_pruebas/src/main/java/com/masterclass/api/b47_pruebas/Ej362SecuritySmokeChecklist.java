package com.masterclass.api.b47_pruebas;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 362 · Seguridad: checklist OWASP básico y smoke automatizado.
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.6).
 */
public final class Ej362SecuritySmokeChecklist {

    private Ej362SecuritySmokeChecklist() {
    }

    // ---- Tipos de datos auxiliares -----------------------------------------------

    public enum Severidad { CRITICO, ALTO, MEDIO, BAJO, INFO }

    public record ConfigApp(
            boolean httpsActivo,
            boolean hstsActivo,
            boolean cspActivo,
            boolean xframeActivo,
            String corsOrigin,
            boolean rateLimitActivo,
            int longitudMinPassword,
            boolean secretosEnCodigo,
            boolean logDatosSensibles
    ) {
    }

    public record Hallazgo(String descripcion, Severidad severidad) {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Ejecuta el checklist de seguridad sobre la configuración de la aplicación.
     *
     * <p>Comprueba en orden:
     * <ol>
     *   <li>HTTPS no activo → CRITICO "HTTPS no forzado"</li>
     *   <li>HSTS no activo → ALTO "Header HSTS ausente"</li>
     *   <li>CSP no activo → ALTO "Header CSP ausente"</li>
     *   <li>X-Frame no activo → MEDIO "Header X-Frame-Options ausente"</li>
     *   <li>corsOrigin es "*" → ALTO "CORS permite cualquier origen"</li>
     *   <li>Rate limit no activo → MEDIO "Sin rate limiting"</li>
     *   <li>longitudMinPassword < 8 → ALTO "Contraseña débil permitida"</li>
     *   <li>secretosEnCodigo = true → CRITICO "Secreto en claro en el código"</li>
     *   <li>logDatosSensibles = true → ALTO "Datos sensibles en logs"</li>
     * </ol>
     *
     * @param cfg configuración de la aplicación; no puede ser null
     * @return lista de hallazgos, ordenada por severidad (CRITICO primero), o List.of() si cfg es null
     */
    public static List<Hallazgo> checklistSeguridad(ConfigApp cfg) {
        // TODO 1: valida cfg != null; retorna List.of() si es null.
        // TODO 2: crea lista mutable de hallazgos y comprueba HTTPS → CRITICO si no activo.
        // TODO 3: comprueba HSTS → ALTO si no activo.
        // TODO 4: comprueba CSP → ALTO si no activo.
        // TODO 5: comprueba X-Frame → MEDIO si no activo.
        // TODO 6: comprueba CORS no es "*" → ALTO si es "*".
        // TODO 7: comprueba rate limit activo → MEDIO si no activo.
        // TODO 8: comprueba longitudMinPassword >= 8 → ALTO si < 8.
        // TODO 9: comprueba secretosEnCodigo no activo → CRITICO si activo.
        // TODO 10: comprueba logDatosSensibles no activo → ALTO si activo. Ordena por severidad y retorna.
        return List.of();
    }

    public static void main(String[] args) {
        ConfigApp cfg = new ConfigApp(
                true, true, false, false, "*", false, 6, false, true
        );
        List<Hallazgo> hallazgos = checklistSeguridad(cfg);
        hallazgos.forEach(h -> System.out.printf("[%s] %s%n", h.severidad(), h.descripcion()));
    }

    // ---- 10 RETOS EXTRA ----------------------------------------------------------

    /**
     * Reto Extra 1: inyección SQL — detecta si una cadena contiene patrones de inyección básicos.
     * Patrones a detectar (insensible a mayúsculas): "' OR", "DROP TABLE", "'; --", "UNION SELECT".
     */
    public static boolean contieneInyeccionSQL(String entrada) {
        // GUÍA: teoría 6.1 (inyección SQL: primera vuln OWASP Top 10; solución = PreparedStatement → b11).
        // 1. entrada null → false.
        // 2. Convierte a mayúsculas.
        // 3. Comprueba si contiene alguno de los cuatro patrones.
        // PISTA: entrada.toUpperCase().contains("' OR") || ...
        // OJO: el test usa minúsculas → asegúrate de normalizar con toUpperCase().
        // CULTURA: en b11 (JDBC) usaste PreparedStatement; nunca concatenes SQL con datos de usuario.
        //   Un fuzzer básico (reto 3) envía exactamente estos patrones para detectar la vulnerabilidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneInyeccionSQL");
    }

    /**
     * Reto Extra 2: validación de entrada (b08).
     * Dado un correo electrónico, valida que sigue el patrón básico usuario@dominio.ext.
     * Devuelve true si es válido.
     */
    public static boolean emailValido(String email) {
        // GUÍA: teoría 6.2 (validación de entrada: primer paso de defensa → b08 Bean Validation).
        // 1. email null o vacío → false.
        // 2. Comprueba con regex simple: ^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$
        // PISTA: return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
        // OJO: "a@b" (sin punto en el dominio) → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para emailValido");
    }

    /**
     * Reto Extra 3: fuzzing básico de un parser.
     * Dado un parser (Function<String,Object>) y una lista de entradas malformadas,
     * devuelve cuántas lanzaron excepción (el parser no las manejó correctamente).
     */
    public static int contarExcepcionesFuzzing(java.util.function.Function<String, Object> parser,
                                                List<String> entradas) {
        // GUÍA: teoría 6.3 (fuzzing: alimentar el parser con entradas inesperadas para encontrar crashes).
        // 1. parser null o entradas null → 0.
        // 2. Por cada entrada, intenta parser.apply(entrada); cuenta las que lanzan cualquier Throwable.
        // PISTA: try { parser.apply(e); } catch (Throwable t) { count++; }
        // OJO: el test usa un parser que solo falla con null → asegúrate de pasar null también en la lista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarExcepcionesFuzzing");
    }

    /**
     * Reto Extra 4: dependencias vulnerables — simula el resultado de OWASP Dependency-Check.
     * Dado un mapa de dependencia → CVE score (0–10), devuelve las dependencias con score >= 7.
     */
    public static List<String> dependenciasVulnerables(java.util.Map<String, Double> dependencias, double umbral) {
        // GUÍA: teoría 6.4 (OWASP Dependency-Check: detecta CVEs en las librerías de tu pom.xml).
        // 1. Filtra entradas con score >= umbral.
        // 2. Devuelve los nombres ordenados.
        // PISTA: dependencias.entrySet().stream().filter(e -> e.getValue() >= umbral).map(Map.Entry::getKey).sorted().toList()
        // OJO: dependencias null → List.of().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dependenciasVulnerables");
    }

    /**
     * Reto Extra 5: authz por roles (b18).
     * Dado un rol de usuario y un recurso, determina si el rol tiene acceso.
     * Mapa de permisos: ADMIN→todo, USER→solo "read", GUEST→nada.
     */
    public static boolean tienePermiso(String rol, String accion) {
        // GUÍA: teoría 6.5 (autorización por roles: RBAC → b18 Spring Security).
        // 1. rol o accion null → false.
        // 2. "ADMIN" → true siempre.
        // 3. "USER" → true solo si accion.equals("read").
        // 4. "GUEST" → false siempre.
        // 5. Otro rol → false.
        // PISTA: return switch(rol) { case "ADMIN" -> true; case "USER" -> "read".equals(accion); default -> false; };
        // OJO: el test pasa "user" (minúsculas) → false (los roles son case-sensitive).
        // CULTURA: en b18 usaste @PreAuthorize("hasRole('ADMIN')"); este método es la lógica que Spring
        //   ejecuta internamente para decidir si la petición pasa o devuelve 403.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePermiso");
    }

    /**
     * Reto Extra 6: JWT mal validado (b18).
     * Comprueba si un JWT "simulado" (cadena con 3 partes separadas por '.') tiene el algoritmo
     * esperado en el header (primera parte base64). Devuelve false si el algoritmo es "none".
     */
    public static boolean jwtAlgoritmoValido(String jwtSimulado, String algoritmoEsperado) {
        // GUÍA: teoría 6.6 (JWT alg=none: ataque donde el atacante elimina la firma declarando alg=none).
        // 1. jwtSimulado null o no tiene 3 partes → false.
        // 2. Decodifica el header (primera parte) con Base64.getDecoder().
        // 3. El header simulado tiene formato "alg=X" → extrae X.
        // 4. Retorna false si X es "none" O si X no es igual a algoritmoEsperado.
        // PISTA: String header = new String(java.util.Base64.getDecoder().decode(partes[0]));
        // OJO: el test pasa un header literal "alg=none" en base64 → devuelve false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para jwtAlgoritmoValido");
    }

    /**
     * Reto Extra 7: logging de datos sensibles (b30).
     * Dado un mensaje de log, detecta si contiene datos sensibles típicos:
     * "password", "token", "secret", "cvv", "ssn" (insensible a mayúsculas).
     */
    public static boolean logContieneDatosSensibles(String mensajeLog) {
        // GUÍA: teoría 6.7 (no loguear datos sensibles: viola GDPR y PCI-DSS → b30 logging).
        // 1. mensajeLog null → false.
        // 2. Normaliza a minúsculas y comprueba si contiene alguna de las cinco palabras.
        // PISTA: String lower = mensajeLog.toLowerCase(); lower.contains("password") || ...
        // OJO: el test usa "TOKEN" en mayúsculas → normaliza con toLowerCase().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para logContieneDatosSensibles");
    }

    /**
     * Reto Extra 8: CSRF — genera el token CSRF como UUID y verifica uno recibido.
     * Devuelve true si el token recibido tiene el formato de un UUID (8-4-4-4-12 hex).
     */
    public static boolean tokenCSRFValido(String token) {
        // GUÍA: teoría 6.8 (CSRF: Cross-Site Request Forgery → b18 Spring Security lo protege por defecto).
        // 1. token null → false.
        // 2. Comprueba con regex: ^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$
        // PISTA: return token != null && token.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        // OJO: UUID.randomUUID().toString() siempre pasa; "123" → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tokenCSRFValido");
    }

    /**
     * Reto Extra 9: checklist OWASP Top 10 como tabla.
     * Devuelve la lista de las 10 categorías OWASP Top 10 (2021) en el orden oficial.
     */
    public static List<String> owaspTop10() {
        // GUÍA: teoría 6.9 (OWASP Top 10 2021: las 10 categorías de vulnerabilidad más críticas).
        // 1. Devuelve la lista fija en el orden oficial A01–A10.
        // Categorías: A01 Broken Access Control, A02 Cryptographic Failures,
        //   A03 Injection, A04 Insecure Design, A05 Security Misconfiguration,
        //   A06 Vulnerable/Outdated Components, A07 Identification/Authentication Failures,
        //   A08 Software and Data Integrity Failures, A09 Security Logging Failures,
        //   A10 Server-Side Request Forgery.
        // PISTA: List.of("A01 Broken Access Control", "A02 Cryptographic Failures", ...)
        // OJO: el test verifica que la lista tiene exactamente 10 elementos y que el primero es "A01 Broken Access Control".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para owaspTop10");
    }

    /**
     * Reto Extra 10: enlace con b18 y b21.
     * Dado un informe de hallazgos de seguridad, determina si el sistema puede pasar
     * a producción: puede pasar si no hay hallazgos CRITICO y tiene a lo sumo 2 ALTO.
     */
    public static boolean aptoParaProduccion(List<Hallazgo> hallazgos) {
        // GUÍA: teoría 6.10 (security gate: condición mínima antes de desplegar a producción).
        // 1. hallazgos null o vacío → true (sin hallazgos, apto).
        // 2. Si alguno tiene severidad CRITICO → false.
        // 3. Si hay más de 2 con severidad ALTO → false.
        // 4. En otro caso → true.
        // PISTA: long criticos = hallazgos.stream().filter(h -> h.severidad() == Severidad.CRITICO).count();
        // OJO: el test pasa exactamente 2 ALTO y 0 CRITICO → true (el límite es "a lo sumo 2").
        // CULTURA: en b21 (resiliencia) aprendiste circuit breaker; en b18 (seguridad) aprendiste los controles.
        //   Este método es el "security circuit breaker": si hay CRITICO, el despliegue no pasa.
        //   En un pipeline CI real (b23) este check detendría el merge automáticamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aptoParaProduccion");
    }
}
