package com.masterclass.api.b18_sec;

import javax.crypto.SecretKey;

/**
 * Ejercicio 159 · Emisión de JWT (jjwt 0.12.6, lógica pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.5).
 *
 * <p>Genera un JWT firmado con HMAC-SHA256 usando {@code io.jsonwebtoken}.
 * No requiere Spring: clave + claims -&gt; token compacto.
 */
public final class Ej159JwtIssue {

    private Ej159JwtIssue() {
    }

    /**
     * Construye una clave HMAC a partir de un secreto.
     *
     * @param secreto secreto en claro (no null, &gt;= 32 bytes para HS256)
     * @return SecretKey apta para firmar HS256
     * @throws IllegalArgumentException si el secreto es null o demasiado corto
     */
    public static SecretKey clave(String secreto) {
        // TODO 1: si secreto es null -> IllegalArgumentException.
        // TODO 2: pasa el secreto a bytes con UTF-8.
        // TODO 3: si tiene menos de 32 bytes -> IllegalArgumentException (HS256 exige >=256 bits).
        // TODO 4: usa io.jsonwebtoken.security.Keys.hmacShaKeyFor(bytes).
        // TODO 5: el secreto NUNCA debe ir en el código en producción (config/secret manager).
        // TODO 6: documenta que la misma clave firma y verifica (simétrica).
        // TODO 7: no loguees el secreto.
        // TODO 8: devuelve el SecretKey.
        // TODO 9: la clave debe ser estable entre emisión y validación.
        // TODO 10: cuidado: rotar la clave invalida los tokens antiguos.
        return null;
    }

    /**
     * Emite un JWT firmado para un usuario.
     *
     * @param clave           clave HMAC (no null)
     * @param subject         usuario (claim sub); no null/blank
     * @param rol             rol como claim custom "rol"
     * @param ahoraMillis     instante actual en epoch millis
     * @param duracionMillis  validez del token en millis (&gt; 0)
     * @return token JWT compacto (header.payload.signature)
     * @throws IllegalArgumentException si los argumentos son inválidos
     */
    public static String emitir(SecretKey clave, String subject, String rol,
                                long ahoraMillis, long duracionMillis) {
        // TODO 1: si clave es null -> IllegalArgumentException.
        // TODO 2: si subject es null o blank -> IllegalArgumentException.
        // TODO 3: si duracionMillis <= 0 -> IllegalArgumentException.
        // TODO 4: usa Jwts.builder().
        // TODO 5: fija el claim subject con .subject(subject).
        // TODO 6: añade el claim custom "rol" con .claim("rol", rol).
        // TODO 7: fija issuedAt = new Date(ahoraMillis).
        // TODO 8: fija expiration = new Date(ahoraMillis + duracionMillis).
        // TODO 9: firma con .signWith(clave) y serializa con .compact().
        // TODO 10: devuelve el token compacto resultante.
        return "";
    }

    public static void main(String[] args) {
        SecretKey k = clave("01234567890123456789012345678901");
        System.out.println(emitir(k, "ana", "ROLE_ADMIN", System.currentTimeMillis(), 60000));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si secreto es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: pasa el secreto a bytes con UTF-8.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: si tiene menos de 32 bytes -> IllegalArgumentException (HS256 exige >=256 bits).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: usa io.jsonwebtoken.security.Keys.hmacShaKeyFor(bytes).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: el secreto NUNCA debe ir en el código en producción (config/secret manager).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: documenta que la misma clave firma y verifica (simétrica).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: no loguees el secreto.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve el SecretKey.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: la clave debe ser estable entre emisión y validación.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: cuidado: rotar la clave invalida los tokens antiguos.
    }

}
