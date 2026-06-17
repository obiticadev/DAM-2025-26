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

        /**
     * RETO EXTRA 01: Comprueba estructura basica de 3 partes separadas por puntos (header.payload.signature).
     */
    public static boolean esJwtValidoFormato(String token) {
        // GUÍA: teoría 18.5 (un JWT son 3 partes: header.payload.signature).
        // 1. Si token es null/blank -> false.
        // 2. Parte por el punto literal y cuenta los trozos.
        // PISTA: return token != null && token.split("\\.").length == 3;
        // OJO: el test pasa "a.b.c" y espera true. El punto es metacarácter en
        // regex, así que se escapa "\\."; esto NO valida la firma, solo la forma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJwtValidoFormato");
    }

    /**
     * RETO EXTRA 02: Verifica presencia de algoritmo HS256 y tipo JWT.
     */
    public static boolean esHeaderJwtCorrecto(String headerJson) {
        // GUÍA: inspeccionar el JSON del header (teoría 18.5).
        // 1. Si headerJson es null -> false.
        // 2. Comprueba que menciona el algoritmo HS256.
        // PISTA: return headerJson != null && headerJson.contains("HS256");
        // OJO: el test pasa {"alg":"HS256"} y espera true. Aquí basta con buscar
        // la cadena; en código real parsearías el JSON con Jackson (b02).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHeaderJwtCorrecto");
    }

    /**
     * RETO EXTRA 03: Determina si la excepcion apunta a firma corrupta o alterada.
     */
    public static boolean esExcepcionFirmaJwt(Throwable t) {
        // GUÍA: detectar fallo de firma por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // 2. En jjwt sería SignatureException; aquí va por mensaje.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("signature");
        // OJO: el test pasa IllegalArgumentException("signature") y espera true.
        // Firma inválida = token NO confiable (teoría 18.6): se rechaza siempre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionFirmaJwt");
    }

    /**
     * RETO EXTRA 04: Genera una cadena simulada JWT.
     */
    public static String generarJwtSimple(String sub, long expMs) {
        // GUÍA: fabricar una cadena con forma de JWT (3 partes con puntos).
        // 1. Codifica un header y un payload mínimos en base64url y únelos con ".".
        // PISTA: var enc = java.util.Base64.getUrlEncoder().withoutPadding();
        //        return enc.encodeToString("{\"alg\":\"none\"}".getBytes())
        //             + "." + enc.encodeToString(("{\"sub\":\""+sub+"\",\"exp\":"+expMs+"}").getBytes())
        //             + ".sig";
        // OJO: el test solo exige que el resultado .contains(".") — basta con que
        // haya al menos un punto separando partes. Esto es DIDÁCTICO; en serio se
        // usa Jwts.builder() (método emitir), nunca alg "none".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarJwtSimple");
    }

    /**
     * RETO EXTRA 05: Determina si la fecha de expiracion en segundos ya paso.
     */
    public static boolean esJwtExpirado(long expTimestamp) {
        // GUÍA: comparar exp (en SEGUNDOS epoch) con el ahora.
        // 1. Está expirado si expTimestamp es anterior al instante actual.
        // PISTA: return expTimestamp < java.time.Instant.now().getEpochSecond();
        // OJO: el test pasa 1000L (≈ enero de 1970) y espera true, claramente
        // pasado. CUIDADO con las unidades: el claim exp del JWT va en SEGUNDOS,
        // no en milisegundos (a diferencia de ahoraMillis en emitir/validar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJwtExpirado");
    }

    /**
     * RETO EXTRA 06: Obtiene el campo 'sub' del payload.
     */
    public static String extraerSujeto(String payloadJson) {
        // GUÍA: extraer el valor de "sub" del JSON con una regex de captura.
        // 1. Si payloadJson es null -> null.
        // 2. Busca "sub":"<valor>" y devuelve el grupo capturado.
        // PISTA: var m = java.util.regex.Pattern
        //        .compile("\"sub\"\\s*:\\s*\"([^\"]+)\"").matcher(payloadJson);
        //        return m.find() ? m.group(1) : null;
        // OJO: el test pasa {"sub":"ada"} y espera EXACTAMENTE "ada". El grupo
        // ([^"]+) captura todo hasta la siguiente comilla. (Con Jackson sería
        // node.get("sub").asText(), pero aquí practicas regex.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerSujeto");
    }

    /**
     * RETO EXTRA 07: Valida el campo de audiencia 'aud'.
     */
    public static boolean esReceptorValido(String aud, String expectedAud) {
        // GUÍA: una línea — la audiencia debe coincidir con la esperada.
        // return aud != null && aud.equals(expectedAud);
        // OJO: el test pasa ("app","app") y espera true. El claim 'aud' identifica
        // PARA QUÉ servicio se emitió el token; validarlo evita reutilizar un token
        // de otra app. Comparación exacta (equals), no contains.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esReceptorValido");
    }

    /**
     * RETO EXTRA 08: Extrae la lista de roles del JSON del payload.
     */
    public static java.util.List<String> extraerRoles(String payloadJson) {
        // GUÍA: sacar los valores del array "roles":[...] (regex + stream).
        // 1. Si payloadJson es null -> List.of().
        // 2. Recorre todas las cadenas entrecomilladas DENTRO del array roles.
        // PISTA: aísla el contenido de roles:[ ... ] y luego, sobre ese trozo,
        //   var m = Pattern.compile("\"([^\"]+)\"").matcher(trozoRoles);
        //   recoge cada m.group(1) en una List.
        // OJO: el test pasa {"roles":["USER"]} y espera List.of("USER"). Asegúrate
        // de no capturar la propia clave "roles": céntrate en el contenido [...].
        // (Reutiliza la idea de captura del reto 06.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerRoles");
    }

    /**
     * RETO EXTRA 09: Verifica longitud y codificacion base64url segura.
     */
    public static boolean longitudFirmaCorrecta(String signature) {
        // GUÍA: la firma debe existir y tener una longitud mínima razonable.
        // 1. Si signature es null -> false.
        // 2. Exige una longitud mínima (una firma HS256 real es larga).
        // PISTA: return signature != null && signature.length() >= 8;
        // OJO: el test pasa "signature" (9 caracteres) y espera true; un umbral de
        // 8 lo deja pasar. Opcionalmente valida también que sean chars base64url
        // ([A-Za-z0-9_-]) con matches.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudFirmaCorrecta");
    }

    /**
     * RETO EXTRA 10: Valida el emisor del token 'iss'.
     */
    public static boolean esEmisorValido(String iss, String expectedIss) {
        // GUÍA: una línea — el emisor 'iss' debe coincidir con el esperado.
        // return iss != null && iss.equals(expectedIss);
        // OJO: el test pasa ("iss","iss") y espera true. Igual que 'aud' (reto 07),
        // validar 'iss' garantiza que el token lo emitió TU servidor de auth y no
        // un tercero. Comparación exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmisorValido");
    }

}