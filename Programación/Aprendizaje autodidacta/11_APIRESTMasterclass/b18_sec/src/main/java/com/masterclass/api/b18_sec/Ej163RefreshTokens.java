package com.masterclass.api.b18_sec;

import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 163 · Refresh tokens (rotación, lógica pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.9).
 *
 * <p>El access token es corto; el refresh token es largo y se canjea por uno
 * nuevo. Modelamos el almacén de refresh tokens y la rotación sin Spring.
 */
public final class Ej163RefreshTokens {

    private Ej163RefreshTokens() {
    }

    /**
     * Verifica que un refresh token es válido y no ha sido revocado/caducado.
     *
     * @param almacen      mapa refreshToken -&gt; {@link RefreshInfo163} (no null)
     * @param refreshToken token presentado por el cliente (no null/blank)
     * @param ahoraMillis  instante actual epoch millis
     * @return Optional con la info si es válido (existe, no revocado, no caducado)
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static Optional<RefreshInfo163> verificar(Map<String, RefreshInfo163> almacen,
                                                     String refreshToken, long ahoraMillis) {
        // TODO 1: si almacen es null -> IllegalArgumentException.
        // TODO 2: si refreshToken es null o blank -> IllegalArgumentException.
        // TODO 3: busca la info en el almacén por el token.
        // TODO 4: si no existe -> Optional.empty() (token desconocido o ya rotado).
        // TODO 5: si info.revoked == true -> Optional.empty().
        // TODO 6: si info.expiraMillis <= ahoraMillis -> Optional.empty() (caducado).
        // TODO 7: NO loguees el token completo.
        // TODO 8: documenta que detectar reuso de un token rotado = posible robo.
        // TODO 9: envuelve la info válida en Optional.
        // TODO 10: devuelve el Optional.
        return Optional.empty();
    }

    /**
     * Rota el refresh token: revoca el antiguo y registra el nuevo.
     *
     * @param almacen        almacén mutable (no null)
     * @param tokenAntiguo   token consumido (debe existir)
     * @param tokenNuevo     token recién emitido (no null/blank)
     * @param subject        usuario dueño
     * @param expiraMillis   caducidad del nuevo token (epoch millis)
     * @return el tokenNuevo registrado
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static String rotar(Map<String, RefreshInfo163> almacen, String tokenAntiguo,
                               String tokenNuevo, String subject, long expiraMillis) {
        // TODO 1: si almacen es null -> IllegalArgumentException.
        // TODO 2: si tokenAntiguo es null o blank -> IllegalArgumentException.
        // TODO 3: si tokenNuevo es null o blank -> IllegalArgumentException.
        // TODO 4: marca el tokenAntiguo como revoked en el almacén (si existe).
        // TODO 5: rotación = single-use: el antiguo nunca vuelve a valer.
        // TODO 6: crea un RefreshInfo163 para el nuevo (subject, expiraMillis, revoked=false).
        // TODO 7: registra el tokenNuevo en el almacén.
        // TODO 8: si el antiguo no existía, considera no emitir (posible ataque) o documentar.
        // TODO 9: documenta el patrón "refresh token rotation" de OWASP.
        // TODO 10: devuelve el tokenNuevo.
        return "";
    }

    public static void main(String[] args) {
        System.out.println("refresh token rotation playground");
    }

        /**
     * RETO EXTRA 01: Comprueba longitud y aleatoriedad segura (e.g. UUID completo sin guiones).
     */
    public static boolean esRefreshTokenValido(String token) {
        // GUÍA: el refresh token debe existir y tener longitud mínima.
        // 1. Si token es null/blank -> false.
        // PISTA: return token != null && !token.isBlank() && token.length() >= 4;
        // OJO: el test pasa "uuid" (4 chars) y espera true, así que el umbral debe
        // dejarlo pasar. En producción exigirías mucho más (un UUID son 32 hex);
        // aquí basta con un mínimo razonable que cubra el caso del test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRefreshTokenValido");
    }

    /**
     * RETO EXTRA 02: Genera un token de refresco aleatorio fuerte.
     */
    public static String crearRefreshTokenNuevo() {
        // GUÍA: token aleatorio fuerte (no predecible).
        // PISTA: return java.util.UUID.randomUUID().toString().replace("-", "");
        //        (o SecureRandom + Base64url para más entropía).
        // OJO: el test solo exige assertNotNull. CUIDADO: nunca uses un contador ni
        // Math.random; un refresh token predecible se puede robar por fuerza bruta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRefreshTokenNuevo");
    }

    /**
     * RETO EXTRA 03: Determina si el token de refresco ya vencio.
     */
    public static boolean esTokenExpirado(java.time.Instant exp) {
        // GUÍA: comparar un Instant con el ahora (java.time, teoría 1.10).
        // 1. Si exp es null -> decide (false = "sin caducidad" razonable).
        // PISTA: return exp != null && exp.isBefore(java.time.Instant.now());
        // OJO: el test pasa Instant.now().minusSeconds(1) y espera true. Instant es
        // inmutable; isBefore compara puntos en la línea temporal (UTC).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTokenExpirado");
    }

    /**
     * RETO EXTRA 04: Determina si la excepcion apunta a token de refresco inexistente o caducado.
     */
    public static boolean esExcepcionDeRefresco(Throwable t) {
        // GUÍA: fallo de refresco por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("refresh");
        // OJO: el test pasa IllegalArgumentException("refresh") y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeRefresco");
    }

    /**
     * RETO EXTRA 05: Genera el JSON standard de retorno con ambos tokens.
     */
    public static String generarRespuestaToken(String access, String refresh) {
        // GUÍA: JSON con el par de tokens (formato OAuth2-ish).
        // PISTA: return String.format(
        //        "{\"access_token\":\"%s\",\"refresh_token\":\"%s\"}", access, refresh);
        // OJO: el test pasa ("a","r") y exige .contains("access"); usa una clave que
        // contenga "access" (access_token o accessToken). Formato libre por lo demás.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarRespuestaToken");
    }

    /**
     * RETO EXTRA 06: Determina si no se supero el limite de intentos fallidos de refresco.
     */
    public static boolean esIntentoRefrescoPermitido(int fallidos, int max) {
        // GUÍA: una línea — control de intentos (como Ej158 reto 08).
        // return fallidos < max;
        // OJO: el test pasa (1, 3) y espera true. Limitar intentos de refresco
        // mitiga el robo de tokens por fuerza bruta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIntentoRefrescoPermitido");
    }

    /**
     * RETO EXTRA 07: Valida que la peticion de refresco provenga de la misma IP original para evitar secuestros.
     */
    public static boolean esIpRefrescoSegura(String originalIp, String currentIp) {
        // GUÍA: una línea — la IP del refresco debe coincidir con la original.
        // return originalIp != null && originalIp.equals(currentIp);
        // OJO: el test pasa ("1.1.1.1","1.1.1.1") y espera true. Comparar la IP es
        // una defensa extra contra el secuestro de tokens (binding al cliente);
        // ojo en la práctica con NAT/proxies que comparten IP.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIpRefrescoSegura");
    }

    /**
     * RETO EXTRA 08: Obtiene los segundos de vida del refresh token.
     */
    public static long extraerTiempoExpiracion(String tokenJson) {
        // GUÍA: extraer el número de "exp" del JSON (regex de captura numérica).
        // 1. Si tokenJson es null -> 0 (o el default que prefieras).
        // 2. Captura los dígitos tras "exp": y parséalos a long.
        // PISTA: var m = java.util.regex.Pattern
        //        .compile("\"exp\"\\s*:\\s*(\\d+)").matcher(tokenJson);
        //        return m.find() ? Long.parseLong(m.group(1)) : 0L;
        // OJO: el test pasa {"exp":3600} y espera 3600L. Aquí el valor NO va entre
        // comillas (es un número), por eso capturas \\d+ y no [^"]+ (cf. Ej159-06).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerTiempoExpiracion");
    }

    /**
     * RETO EXTRA 09: Determina si el error ocurrio al consultar el almacen de tokens en persistencia.
     */
    public static boolean esFalloBaseDatosRefresco(Throwable t) {
        // GUÍA: fallo de persistencia = excepción de BD.
        // 1. Si t es null -> false.
        // PISTA: return t instanceof java.sql.SQLException;
        // OJO: el test pasa new SQLException() y espera true. Un fallo del almacén
        // de tokens es un 500 (infraestructura), no un 401 (token inválido).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloBaseDatosRefresco");
    }

    /**
     * RETO EXTRA 10: Resuelve la accion correctiva (ROTO o REUSE).
     */
    public static String determinarEstrategiaRefresco(int expDays) {
        // GUÍA: estrategia según los días de validez.
        // 1. Con caducidad positiva, la estrategia es rotar ("ROTO"); si no, "REUSE".
        // PISTA: return expDays > 0 ? "ROTO" : "REUSE";
        // OJO: el test pasa 30 y espera EXACTAMENTE "ROTO". CULTURA: rotar es lo
        // recomendado por OWASP (single-use, teoría 18.9); "REUSE" sería el modo
        // inseguro de reaprovechar el mismo refresh.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para determinarEstrategiaRefresco");
    }

}

/**
 * Info de un refresh token almacenado.
 *
 * @param subject usuario dueño
 * @param expiraMillis caducidad epoch millis
 * @param revoked true si fue revocado/rotado
 */
record RefreshInfo163(String subject, long expiraMillis, boolean revoked) {
}