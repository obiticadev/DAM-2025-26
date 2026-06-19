package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 243 · Cifrado simétrico con AES: una clave para cifrar y descifrar.
 *
 * <p>En el cifrado <b>simétrico</b> las dos partes comparten la MISMA clave secreta. AES es el
 * estándar. Pero "AES" no basta: hay que elegir un <b>modo de operación</b> y un <b>IV</b>
 * (vector de inicialización):
 * <ul>
 *   <li><b>ECB</b>: inseguro, bloques iguales → cifrado igual (revela patrones). No usar.</li>
 *   <li><b>CBC</b>: encadena bloques, necesita IV aleatorio; protege confidencialidad pero no integridad.</li>
 *   <li><b>GCM</b>: <i>autenticado</i> (AEAD): cifra Y detecta manipulación. El recomendado hoy.</li>
 * </ul>
 * Regla de oro: el IV debe ser <b>único</b> por mensaje (aleatorio), y NO es secreto (viaja con
 * el cifrado). Reusar IV con la misma clave rompe la seguridad.
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.3).
 */
public final class Ej243SymmetricAes {

    private Ej243SymmetricAes() {
    }

    /**
     * Cifra y descifra un texto con AES/GCM (modo autenticado) usando clave e IV aleatorios.
     *
     * @param texto texto en claro
     * @return el texto descifrado (== texto), o {@code null} si no se ha implementado
     */
    public static String aesGcmRoundTrip(String texto) {
        // TODO 1: genera una clave con KeyGenerator kg = KeyGenerator.getInstance("AES"); kg.init(256); SecretKey k = kg.generateKey().
        // TODO 2: genera un IV/nonce de 12 bytes aleatorio (SecureRandom) y crea un GCMParameterSpec(128, iv).
        // TODO 3: Cipher c = Cipher.getInstance("AES/GCM/NoPadding"); c.init(ENCRYPT_MODE, k, gcmSpec); byte[] ct = c.doFinal(texto.getBytes(UTF_8)).
        // TODO 4: para descifrar, Cipher d = Cipher.getInstance("AES/GCM/NoPadding"); d.init(DECRYPT_MODE, k, gcmSpec).
        // TODO 5: byte[] claro = d.doFinal(ct); devuelve new String(claro, StandardCharsets.UTF_8).
        // TODO 6: maneja las GeneralSecurityException como RuntimeException.
        return null;
    }

    /**
     * Cifra y descifra un texto con AES/CBC/PKCS5Padding usando clave e IV aleatorios.
     *
     * @param texto texto en claro
     * @return el texto descifrado (== texto)
     */
    public static String aesCbcRoundTrip(String texto) {
        // TODO 7: genera clave AES (128 o 256) y un IV de 16 bytes aleatorio (IvParameterSpec).
        // TODO 8: Cipher.getInstance("AES/CBC/PKCS5Padding"); init en ENCRYPT_MODE con clave + IvParameterSpec; doFinal.
        // TODO 9: init OTRO Cipher en DECRYPT_MODE con la MISMA clave y el MISMO IV; doFinal sobre el cifrado.
        // TODO 10: devuelve el texto reconstruido (UTF-8). CBC necesita el IV para descifrar: guárdalo.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("aesGcmRoundTrip = " + aesGcmRoundTrip("mensaje secreto"));
        System.out.println("aesCbcRoundTrip = " + aesCbcRoundTrip("mensaje secreto"));
    }

    /**
     * Reto Extra 1: una clave AES-256 ocupa 32 bytes.
     * @return longitud en bytes de una clave AES-256 (debe ser 32)
     */
    public static int claveAes256TieneTreintaYDosBytes() {
        // GUÍA: kg.init(256); SecretKey k = kg.generateKey(); return k.getEncoded().length;  // 256/8 = 32
        // OJO: si el JDK no permitiera 256 (políticas antiguas), 128 daría 16; los JDK modernos ya
        // traen "unlimited strength" por defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claveAes256TieneTreintaYDosBytes");
    }

    /**
     * Reto Extra 2: dos IV generados con SecureRandom son distintos.
     * @return true si dos IV aleatorios de 12 bytes difieren
     */
    public static boolean dosIvAleatoriosDistintos() {
        // GUÍA: genera dos byte[12] con SecureRandom.nextBytes y return !Arrays.equals(iv1, iv2).
        // CULTURA: la unicidad del IV es crítica; con GCM, repetir (clave, IV) es catastrófico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosIvAleatoriosDistintos");
    }

    /**
     * Reto Extra 3: el mismo texto cifrado con IV distinto da cifrados distintos.
     * @return true si cifrar "igual" con la misma clave pero distinto IV produce ciphertext distinto
     */
    public static boolean mismoTextoIvDistintoCifradoDistinto() {
        // GUÍA: fija una clave; cifra "igual" con iv1 y luego con iv2 (≠ iv1). Compara los byte[] de
        // salida: distintos. Por eso un atacante no sabe si dos mensajes cifrados son el mismo texto.
        // OJO: compara solo el cuerpo cifrado; recuerda que el IV viaja aparte (no es secreto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismoTextoIvDistintoCifradoDistinto");
    }

    /**
     * Reto Extra 4: ECB es inseguro — dos bloques de texto idénticos dan cifrado idéntico.
     * @return true si, con AES/ECB, dos bloques de 16 bytes iguales producen cifrado igual
     */
    public static boolean ecbRevelaPatrones() {
        // GUÍA: cifra 32 bytes formados por DOS bloques de 16 bytes idénticos con "AES/ECB/NoPadding".
        // Los dos bloques de 16 bytes del cifrado serán IGUALES entre sí → el patrón se filtra.
        // Compara cifrado[0..16) con cifrado[16..32): iguales → return true.
        // CULTURA: el famoso "pingüino ECB" — nunca uses ECB. Este reto demuestra POR QUÉ.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecbRevelaPatrones");
    }

    /**
     * Reto Extra 5: GCM detecta manipulación del cifrado (autenticación AEAD).
     * @return true si alterar un byte del cifrado GCM hace fallar el descifrado con AEADBadTagException
     */
    public static boolean gcmDetectaManipulacion() {
        // GUÍA: cifra con GCM, altera un byte del ciphertext (ct[0] ^= 1) e intenta descifrar:
        //   try { d.doFinal(ctAlterado); return false; }
        //   catch (javax.crypto.AEADBadTagException e) { return true; }
        // CULTURA: esto es lo que CBC NO te da. GCM = confidencialidad + integridad + autenticidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gcmDetectaManipulacion");
    }

    /**
     * Reto Extra 6: cifrar/descifrar bytes crudos (no solo texto).
     * @return los bytes descifrados, iguales a los originales {1,2,3,4,5}
     */
    public static byte[] cifrarDescifrarBytes(byte[] datos) {
        // GUÍA: el Cipher trabaja con byte[]; no hace falta pasar por String. Cifra 'datos' con GCM o
        // CBC y descífralo; devuelve el byte[] resultante. El test compara con {1,2,3,4,5}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cifrarDescifrarBytes");
    }

    /**
     * Reto Extra 7: descifrar con la clave equivocada no recupera el texto.
     * @return true si descifrar con OTRA clave falla (excepción) o no devuelve el texto original
     */
    public static boolean claveIncorrectaNoDescifra() {
        // GUÍA: cifra con la clave A; intenta descifrar con la clave B (distinta). Con GCM saltará una
        // AEADBadTagException (la autenticación falla); captúrala y return true. Con CBC podrías obtener
        // basura o BadPaddingException; cualquiera de esos casos cuenta como "no descifra".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claveIncorrectaNoDescifra");
    }

    /**
     * Reto Extra 8: una clave AES-128 ocupa 16 bytes.
     * @return longitud en bytes de una clave AES-128 (debe ser 16)
     */
    public static int claveAes128TieneDieciseisBytes() {
        // GUÍA: kg.init(128); return generateKey().getEncoded().length;  // 128/8 = 16.
        // CULTURA: AES-128 ya es seguro hoy; AES-256 da margen frente a futuros (incl. cuántico parcial).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claveAes128TieneDieciseisBytes");
    }

    /**
     * Reto Extra 9: el cifrado se puede transportar como Base64 (texto) sin perder datos.
     * @return true si Base64(cifrado) -> decode -> descifrado recupera el texto original
     */
    public static boolean cifradoComoBase64RoundTrip() {
        // GUÍA: cifra "hola" → byte[] ct; String b64 = Base64.getEncoder().encodeToString(ct);
        //   byte[] vuelta = Base64.getDecoder().decode(b64); descífralo y comprueba que es "hola".
        // CULTURA: el cifrado son bytes binarios; para meterlos en JSON (b02) o una URL se codifican
        // en Base64. Base64 NO cifra, solo transporta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cifradoComoBase64RoundTrip");
    }

    /**
     * Reto Extra 10: AES/CBC con clave e IV FIJOS es determinista (mismo cifrado).
     * @return true si cifrar el mismo texto con la misma clave y el mismo IV da el mismo cifrado
     */
    public static boolean cbcDeterministaConClaveEIvFijos() {
        // GUÍA: usa una clave fija (new SecretKeySpec(bytes16/32, "AES")) y un IV fijo (16 bytes).
        // Cifra "repetible" dos veces con esos mismos parámetros y compara los byte[]: iguales.
        // OJO/CUIDADO: esto demuestra POR QUÉ el IV debe ser aleatorio en producción: con IV fijo,
        // un atacante detecta mensajes repetidos. Lo haces fijo aquí solo para verlo determinista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cbcDeterministaConClaveEIvFijos");
    }
}
