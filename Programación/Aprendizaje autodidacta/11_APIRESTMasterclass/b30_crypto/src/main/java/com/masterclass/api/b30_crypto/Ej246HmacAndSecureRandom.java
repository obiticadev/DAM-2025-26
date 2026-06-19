package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 246 · HMAC (integridad con clave) y SecureRandom (aleatoriedad segura).
 *
 * <p>Un hash (241) prueba que los datos no cambiaron, pero cualquiera puede recalcularlo. Un
 * <b>HMAC</b> añade una <b>clave secreta</b>: solo quien la tiene puede generar o verificar el
 * código. Garantiza integridad <i>y</i> autenticidad sin claves asimétricas. Es lo que firma un
 * JWT con algoritmo HS256 (b18) y lo que valida webhooks (GitHub, Stripe...).
 *
 * <p>La segunda mitad es la <b>aleatoriedad</b>: todo lo cripto (salts, IVs, claves, tokens,
 * nonces) necesita números impredecibles. {@code java.util.Random} es predecible y NO sirve;
 * hay que usar {@code SecureRandom} (un CSPRNG). Generar un token de sesión con `Random` es un
 * fallo de seguridad clásico.
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.6).
 */
public final class Ej246HmacAndSecureRandom {

    private Ej246HmacAndSecureRandom() {
    }

    /**
     * Calcula el HMAC-SHA256 de un mensaje con una clave y lo devuelve en hexadecimal.
     *
     * @param mensaje mensaje a autenticar
     * @param clave   clave secreta compartida
     * @return los 64 caracteres hex del HMAC, o {@code null} si no se ha implementado
     */
    public static String hmacSha256Hex(String mensaje, String clave) {
        // TODO 1: crea la clave: SecretKeySpec k = new SecretKeySpec(clave.getBytes(UTF_8), "HmacSHA256").
        // TODO 2: Mac mac = Mac.getInstance("HmacSHA256"); mac.init(k).
        // TODO 3: byte[] resultado = mac.doFinal(mensaje.getBytes(StandardCharsets.UTF_8)).
        // TODO 4: convierte a hex con HexFormat.of().formatHex(resultado).
        // TODO 5: devuelve la cadena hex (64 caracteres para SHA-256).
        // TODO 6: maneja NoSuchAlgorithmException/InvalidKeyException como RuntimeException.
        return null;
    }

    /**
     * Verifica un HMAC esperado contra el recalculado, en tiempo constante.
     *
     * @param mensaje          mensaje
     * @param clave            clave secreta
     * @param hmacHexEsperado  HMAC esperado en hex
     * @return true si el HMAC recalculado coincide con el esperado
     */
    public static boolean hmacVerifica(String mensaje, String clave, String hmacHexEsperado) {
        // TODO 7: recalcula el HMAC de (mensaje, clave) en bytes.
        // TODO 8: decodifica hmacHexEsperado a bytes con HexFormat.of().parseHex(...).
        // TODO 9: compara con MessageDigest.isEqual(recalculado, esperado) — comparación en tiempo constante.
        // TODO 10: si algún parámetro es null o el hex es inválido, devuelve false (sin lanzar excepción).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("hmacSha256Hex = " + hmacSha256Hex("mensaje", "clave-secreta"));
    }

    /**
     * Reto Extra 1: con la misma clave y mensaje, el HMAC es el mismo (determinista).
     * @return true si dos cálculos de HMAC con idénticos parámetros coinciden
     */
    public static boolean mismaClaveMismoHmac() {
        // GUÍA: return hmacSha256Hex("m","k").equals(hmacSha256Hex("m","k"));
        // CULTURA: el determinismo es lo que permite al receptor recalcular y comparar (como en 241).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismaClaveMismoHmac");
    }

    /**
     * Reto Extra 2: cambiar la clave cambia el HMAC (aunque el mensaje sea el mismo).
     * @return true si hmac(m, k1) != hmac(m, k2) con claves distintas
     */
    public static boolean distintaClaveDistintoHmac() {
        // GUÍA: return !hmacSha256Hex("m","k1").equals(hmacSha256Hex("m","k2"));
        // CULTURA: por eso el HMAC autentica: sin la clave correcta no puedes producir el mismo código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distintaClaveDistintoHmac");
    }

    /**
     * Reto Extra 3: SecureRandom genera un salt/token de 16 bytes.
     * @return longitud del array generado con SecureRandom.nextBytes (debe ser 16)
     */
    public static int secureRandomGenera16Bytes() {
        // GUÍA: byte[] b = new byte[16]; new SecureRandom().nextBytes(b); return b.length;
        // OJO: SecureRandom, NO new Random() — Random es predecible y rompe la seguridad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para secureRandomGenera16Bytes");
    }

    /**
     * Reto Extra 4: dos tokens generados con SecureRandom son distintos.
     * @return true si dos tokens aleatorios de 32 bytes difieren
     */
    public static boolean dosTokensDistintos() {
        // GUÍA: genera dos byte[32] con SecureRandom y return !Arrays.equals(t1, t2).
        // CULTURA: la base de tokens de sesión, CSRF, API keys... su seguridad es su impredecibilidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosTokensDistintos");
    }

    /**
     * Reto Extra 5: un token aleatorio se codifica en Base64 URL-safe sin padding.
     * @return true si el token codificado no está vacío y no contiene '=' (sin padding)
     */
    public static boolean tokenBase64UrlSinPadding() {
        // GUÍA: byte[] t = ...(SecureRandom, 24 bytes);
        //   String s = Base64.getUrlEncoder().withoutPadding().encodeToString(t);
        //   return !s.isEmpty() && !s.contains("=");
        // CULTURA: URL-safe (-/_ en vez de +/) y sin padding para meterlo en URLs/cookies sin escapar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tokenBase64UrlSinPadding");
    }

    /**
     * Reto Extra 6: el HMAC detecta manipulación del mensaje.
     * @return true si el HMAC de "original" no verifica contra el mensaje "alterado"
     */
    public static boolean hmacDetectaManipulacion() {
        // GUÍA: calcula mac = hmacSha256Hex("original","k"); luego comprueba que
        // hmacVerifica("alterado","k", mac) devuelve false. return !hmacVerifica(...).
        // CULTURA: así valida un webhook que el cuerpo no se ha tocado por el camino.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hmacDetectaManipulacion");
    }

    /**
     * Reto Extra 7: comparar HMACs con MessageDigest.isEqual (tiempo constante).
     * @return true si isEqual confirma que dos HMACs iguales coinciden
     */
    public static boolean comparacionEnTiempoConstante() {
        // GUÍA: byte[] a = mac.doFinal(...); byte[] b = mac2.doFinal(mismo);
        //   return MessageDigest.isEqual(a, b);
        // OJO/CUIDADO: NUNCA compares HMACs con Arrays.equals o String.equals que cortocircuitan;
        // un atacante podría medir el tiempo y adivinar el código byte a byte (timing attack).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comparacionEnTiempoConstante");
    }

    /**
     * Reto Extra 8: el HMAC-SHA256 crudo ocupa 32 bytes.
     * @return longitud en bytes del HMAC-SHA256 (debe ser 32)
     */
    public static int hmacSha256Tiene32Bytes() {
        // GUÍA: byte[] m = mac.doFinal(...); return m.length;  // SHA-256 => 32 bytes (64 hex).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hmacSha256Tiene32Bytes");
    }

    /**
     * Reto Extra 9: en un lote grande de nonces de SecureRandom no hay repetidos.
     * @return true si 1000 nonces aleatorios de 16 bytes son todos distintos
     */
    public static boolean noncesUnicosEnLote() {
        // GUÍA: genera 1000 tokens (SecureRandom, 16 bytes), guárdalos en un Set<String> (hex/Base64)
        // y comprueba que el tamaño del Set es 1000 (ninguna colisión).
        // CULTURA: la unicidad práctica es lo que se exige a IVs/nonces (ver Ej243): nunca repetir.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noncesUnicosEnLote");
    }

    /**
     * Reto Extra 10: existe una fuente de aleatoriedad "fuerte" del sistema.
     * @return true si SecureRandom.getInstanceStrong() devuelve una instancia utilizable
     */
    public static boolean secureRandomStrongDisponible() {
        // GUÍA: SecureRandom sr = SecureRandom.getInstanceStrong(); sr.nextBytes(new byte[8]); return sr != null;
        // CULTURA: getInstanceStrong() pide al SO su mejor fuente de entropía; úsala para material de
        // larga vida (claves maestras). Maneja NoSuchAlgorithmException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para secureRandomStrongDisponible");
    }
}
