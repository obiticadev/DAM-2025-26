package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 244 · Cifrado asimétrico con RSA: dos claves, pública y privada.
 *
 * <p>El problema del simétrico (243) es repartir la clave secreta: ¿cómo se la haces llegar al
 * otro sin que la intercepten? El cifrado <b>asimétrico</b> lo resuelve con un PAR de claves
 * matemáticamente ligadas: lo que cifra la <b>pública</b> solo lo descifra la <b>privada</b>
 * (confidencialidad), y lo que firma la privada lo verifica la pública (autenticidad, ver 245).
 * La pública se reparte libremente; la privada no sale nunca de su dueño.
 *
 * <p>RSA es lento y solo cifra datos pequeños (menos que el tamaño de clave). En la práctica se
 * usa para intercambiar una clave AES y luego cifrar el grueso con AES (cifrado <b>híbrido</b>,
 * que es lo que hace TLS, ver 248).
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.4).
 */
public final class Ej244AsymmetricRsa {

    private Ej244AsymmetricRsa() {
    }

    /**
     * Genera un par RSA, cifra con la pública y descifra con la privada (round-trip).
     *
     * @param texto texto en claro (pequeño)
     * @return el texto descifrado (== texto), o {@code null} si no se ha implementado
     */
    public static String rsaRoundTrip(String texto) {
        // TODO 1: KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); kpg.initialize(2048); KeyPair kp = kpg.generateKeyPair().
        // TODO 2: Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding"); (OAEP, el padding seguro).
        // TODO 3: c.init(ENCRYPT_MODE, kp.getPublic()); byte[] ct = c.doFinal(texto.getBytes(UTF_8)).
        // TODO 4: Cipher d = Cipher.getInstance(mismo); d.init(DECRYPT_MODE, kp.getPrivate()).
        // TODO 5: byte[] claro = d.doFinal(ct); devuelve new String(claro, StandardCharsets.UTF_8).
        // TODO 6: maneja GeneralSecurityException como RuntimeException.
        return null;
    }

    /**
     * Devuelve el tamaño en bits del módulo de una clave pública RSA de 2048 bits.
     *
     * @return 2048 (el tamaño de clave), o -1 si no se ha implementado
     */
    public static int tamanoModuloEnBits() {
        // TODO 7: genera un par RSA de 2048 bits.
        // TODO 8: castea la pública a RSAPublicKey y obtén su módulo con getModulus() (un BigInteger).
        // TODO 9: el tamaño en bits es modulus.bitLength().
        // TODO 10: devuelve ese valor (será 2048).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("rsaRoundTrip = " + rsaRoundTrip("hola RSA"));
        System.out.println("tamanoModuloEnBits = " + tamanoModuloEnBits());
    }

    /**
     * Reto Extra 1: cifrar con la pública, descifrar con la privada (round-trip).
     * @return true si el texto descifrado coincide con el original
     */
    public static boolean cifrarPublicaDescifrarPrivada() {
        // GUÍA: return "confidencial".equals(rsaRoundTrip("confidencial"));
        // CULTURA: este es el sentido "confidencialidad": cualquiera puede cifrarte con tu pública,
        // solo tú descifras con tu privada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cifrarPublicaDescifrarPrivada");
    }

    /**
     * Reto Extra 2: la clave pública y la privada son distintas.
     * @return true si los bytes codificados de pública y privada difieren
     */
    public static boolean clavePublicaYPrivadaDistintas() {
        // GUÍA: KeyPair kp = ...; return !Arrays.equals(kp.getPublic().getEncoded(), kp.getPrivate().getEncoded());
        // (obvio, pero asienta que son dos claves ligadas, no la misma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clavePublicaYPrivadaDistintas");
    }

    /**
     * Reto Extra 3: la clave pública se serializa en formato X.509.
     * @return el formato de la clave pública (debe ser "X.509")
     */
    public static String formatoClavePublica() {
        // GUÍA: return kp.getPublic().getFormat();  // "X.509" (SubjectPublicKeyInfo)
        // CULTURA: es el formato estándar para distribuir claves públicas y certificados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoClavePublica");
    }

    /**
     * Reto Extra 4: la clave privada se serializa en formato PKCS#8.
     * @return el formato de la clave privada (debe ser "PKCS#8")
     */
    public static String formatoClavePrivada() {
        // GUÍA: return kp.getPrivate().getFormat();  // "PKCS#8"
        // OJO: nunca guardes una privada en claro; va cifrada en un KeyStore (Ej247).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoClavePrivada");
    }

    /**
     * Reto Extra 5: el algoritmo de la clave es RSA.
     * @return el algoritmo de la clave pública (debe ser "RSA")
     */
    public static String algoritmoDeLaClave() {
        // GUÍA: return kp.getPublic().getAlgorithm();  // "RSA"
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para algoritmoDeLaClave");
    }

    /**
     * Reto Extra 6: RSA no cifra datos más grandes que (tamaño de clave - padding).
     * @return true si cifrar un bloque demasiado grande lanza una excepción
     */
    public static boolean rsaNoCifraDatosGrandes() {
        // GUÍA: con RSA-2048 y OAEP-SHA256 solo caben ~190 bytes. Intenta cifrar new byte[300]:
        //   try { c.doFinal(new byte[300]); return false; }
        //   catch (javax.crypto.IllegalBlockSizeException | javax.crypto.BadPaddingException e) { return true; }
        // CULTURA: por esto RSA NO se usa para cifrar ficheros, sino para cifrar una clave AES (híbrido/TLS).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rsaNoCifraDatosGrandes");
    }

    /**
     * Reto Extra 7: con padding OAEP, cifrar dos veces el mismo texto da cifrados distintos.
     * @return true si dos cifrados del mismo texto con la misma pública difieren
     */
    public static boolean cifradoNoDeterministaPorPadding() {
        // GUÍA: OAEP añade aleatoriedad al padding, así que ct1 != ct2 aunque el texto y la clave sean
        // iguales (igual que el IV en AES). Cifra "x" dos veces y compara los byte[].
        // CULTURA: el padding aleatorio impide deducir si dos mensajes cifrados son iguales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cifradoNoDeterministaPorPadding");
    }

    /**
     * Reto Extra 8: reconstruir la clave pública desde sus bytes X.509.
     * @return true si la clave reconstruida con KeyFactory equals a la original
     */
    public static boolean reconstruirPublicaDesdeBytes() {
        // GUÍA: byte[] enc = kp.getPublic().getEncoded();
        //   KeyFactory kf = KeyFactory.getInstance("RSA");
        //   PublicKey pub2 = kf.generatePublic(new X509EncodedKeySpec(enc));
        //   return pub2.equals(kp.getPublic());
        // CULTURA: así envías una clave pública por la red (bytes) y el receptor la reconstruye.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconstruirPublicaDesdeBytes");
    }

    /**
     * Reto Extra 9: descifrar con OTRA privada (de otro par) falla.
     * @return true si usar la privada equivocada lanza una excepción al descifrar
     */
    public static boolean descifrarConOtraPrivadaFalla() {
        // GUÍA: cifra con la pública del par A; intenta descifrar con la privada del par B:
        //   try { d.doFinal(ct); return false; } catch (GeneralSecurityException e) { return true; }
        // CULTURA: solo la privada PAREJA de la pública que cifró puede descifrar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para descifrarConOtraPrivadaFalla");
    }

    /**
     * Reto Extra 10: round-trip de bytes crudos por RSA.
     * @return los bytes descifrados, iguales a los originales {7,7,7}
     */
    public static byte[] roundTripBytes(byte[] datos) {
        // GUÍA: cifra 'datos' con la pública y descífralo con la privada; devuelve el byte[].
        // OJO: mantén 'datos' pequeño (cabe en un bloque RSA). El test usa {7,7,7}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para roundTripBytes");
    }
}
