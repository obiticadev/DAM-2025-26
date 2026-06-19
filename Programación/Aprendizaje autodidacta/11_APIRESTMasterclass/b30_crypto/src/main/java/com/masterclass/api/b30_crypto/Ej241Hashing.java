package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 241 · Funciones hash: huella digital de los datos.
 *
 * <p>Un hash (resumen, digest) transforma cualquier dato en una huella de tamaño fijo
 * (SHA-256 → 32 bytes), de forma determinista y de un solo sentido: del hash no se puede
 * volver al dato. Sirve para <b>integridad</b> ("¿han cambiado estos bytes?"), no para
 * confidencialidad. Tres propiedades: determinista, irreversible y resistente a colisiones.
 *
 * <p>OJO conceptual (PSP RA5): un hash desnudo NO sirve para guardar contraseñas (es rápido
 * y sin sal → fuerza bruta). Eso se arregla en {@code Ej242} con salt + PBKDF2.
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.1).
 */
public final class Ej241Hashing {

    private Ej241Hashing() {
    }

    /**
     * Calcula el SHA-256 de un texto y lo devuelve en hexadecimal (minúsculas).
     *
     * @param texto texto de entrada (se codifica en UTF-8)
     * @return los 64 caracteres hex del hash, o {@code null} si no se ha implementado
     */
    public static String sha256Hex(String texto) {
        // TODO 1: obtén el algoritmo con MessageDigest md = MessageDigest.getInstance("SHA-256").
        // TODO 2: calcula los bytes del hash con md.digest(texto.getBytes(StandardCharsets.UTF_8)).
        // TODO 3: convierte el byte[] a hexadecimal. Lo más simple en JDK 17+: HexFormat.of().formatHex(bytes).
        // TODO 4: alternativa manual: StringBuilder + String.format("%02x", b & 0xff) por cada byte.
        // TODO 5: devuelve la cadena hex en minúsculas (HexFormat ya da minúsculas).
        // TODO 6: maneja NoSuchAlgorithmException (no debería ocurrir con "SHA-256", relánzala como RuntimeException).
        return null;
    }

    /**
     * Verifica que unos datos producen el hash hexadecimal esperado (control de integridad).
     *
     * @param datos            bytes a comprobar
     * @param hashHexEsperado  hash SHA-256 esperado en hex
     * @return true si el hash recalculado coincide con el esperado
     */
    public static boolean verificarIntegridad(byte[] datos, String hashHexEsperado) {
        // TODO 7: recalcula el SHA-256 de 'datos' (reutiliza la lógica de sha256Hex sobre bytes).
        // TODO 8: compara el hex recalculado con hashHexEsperado, ignorando mayúsculas/minúsculas.
        // TODO 9: devuelve true solo si coinciden.
        // TODO 10: si algún parámetro es null, devuelve false (no lances NullPointerException).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("sha256Hex(\"abc\") = " + sha256Hex("abc"));
        System.out.println("verificarIntegridad(abc) = "
                + verificarIntegridad("abc".getBytes(java.nio.charset.StandardCharsets.UTF_8),
                "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"));
    }

    /**
     * Reto Extra 1: el digest SHA-256 ocupa siempre 32 bytes (256 bits), sea cual sea la entrada.
     * @return número de bytes del digest (debe ser 32)
     */
    public static int sha256TieneTreintaYDosBytes() {
        // GUÍA: teoría 30.1. md.digest(...) devuelve un byte[]; su .length es 32 para SHA-256
        // tanto si la entrada es "" como si es un libro entero. Por eso es de "tamaño fijo".
        // PISTA: prueba con cualquier texto; el test solo mira que el length sea 32.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sha256TieneTreintaYDosBytes");
    }

    /**
     * Reto Extra 2: efecto avalancha — cambiar un solo carácter cambia el hash por completo.
     * @return true si el hash de "abc" es distinto del de "abd"
     */
    public static boolean efectoAvalancha() {
        // GUÍA: return !sha256Hex("abc").equals(sha256Hex("abd"));
        // CULTURA: un bit de diferencia en la entrada cambia ~la mitad de los bits del hash. Es lo que
        // hace inútil "adivinar" el dato a partir de hashes parecidos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para efectoAvalancha");
    }

    /**
     * Reto Extra 3: MD5 (roto, pero hay que conocerlo). Hash hex de "abc".
     * @return el MD5 de "abc" en hex (valor famoso: 900150983cd24fb0d6963f7d28e17f72)
     */
    public static String md5Hex(String texto) {
        // GUÍA: MessageDigest.getInstance("MD5"); mismo patrón que sha256Hex.
        // OJO/CUIDADO: MD5 está ROTO (se generan colisiones a voluntad). El test usa "abc" y espera
        // "900150983cd24fb0d6963f7d28e17f72". Inclúyelo solo para reconocerlo, NUNCA para seguridad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para md5Hex");
    }

    /**
     * Reto Extra 4: SHA-1 (también obsoleto). Hash hex de "abc".
     * @return el SHA-1 de "abc" en hex (a9993e364706816aba3e25717850c26c9cd0d89d)
     */
    public static String sha1Hex(String texto) {
        // GUÍA: MessageDigest.getInstance("SHA-1"). El test usa "abc" y espera
        // "a9993e364706816aba3e25717850c26c9cd0d89d".
        // CULTURA: SHA-1 está deprecado para firmas/certificados desde ~2017 (colisión SHAttered).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sha1Hex");
    }

    /**
     * Reto Extra 5: el hash es determinista — la misma entrada da siempre el mismo hash.
     * @return true si sha256Hex("repetible") devuelve lo mismo en dos llamadas
     */
    public static boolean mismaEntradaMismoHash() {
        // GUÍA: return sha256Hex("repetible").equals(sha256Hex("repetible"));
        // CULTURA: el determinismo es lo que permite verificar integridad (recalcular y comparar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismaEntradaMismoHash");
    }

    /**
     * Reto Extra 6: dos "ficheros" (byte[]) con idéntico contenido tienen idéntico hash.
     * @return true si dos arrays de bytes iguales producen el mismo hash
     */
    public static boolean ficherosIgualesMismoHash() {
        // GUÍA: hashea byte[]{1,2,3} y otro byte[]{1,2,3} (instancias distintas, mismo contenido)
        // y compara los hex. Es el fundamento del "deduplicado" y de comprobar descargas.
        // PISTA: añade una sobrecarga privada sha256Hex(byte[]) o reutiliza la lógica del core.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ficherosIgualesMismoHash");
    }

    /**
     * Reto Extra 7: el hash SHA-256 en hex mide 64 caracteres (2 hex por byte).
     * @return longitud de la cadena hex (debe ser 64)
     */
    public static int longitudHexSha256() {
        // GUÍA: return sha256Hex("lo que sea").length();  // 32 bytes * 2 = 64 caracteres hex.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudHexSha256");
    }

    /**
     * Reto Extra 8: el SHA-256 de la cadena vacía es un valor conocido.
     * @return el SHA-256 de "" en hex (e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855)
     */
    public static String hashCadenaVacia() {
        // GUÍA: return sha256Hex("");  // hashear "" es válido y da un valor fijo y famoso.
        // OJO: el test compara con e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hashCadenaVacia");
    }

    /**
     * Reto Extra 9: digest incremental — varios update() equivalen a hashear la concatenación.
     * @return true si update("ab")+update("c") da el mismo hash que digest("abc")
     */
    public static boolean digestIncrementalEquivale() {
        // GUÍA: MessageDigest md = ...; md.update("ab".getBytes(UTF_8)); md.update("c".getBytes(UTF_8));
        //   byte[] h1 = md.digest(); compáralo (HexFormat) con sha256Hex("abc").
        // CULTURA: el update incremental permite hashear ficheros enormes leyendo por bloques
        // (b26) sin cargarlos enteros en memoria.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para digestIncrementalEquivale");
    }

    /**
     * Reto Extra 10: comparar dos hashes en tiempo constante con MessageDigest.isEqual.
     * @return true si MessageDigest.isEqual confirma que dos hashes iguales coinciden
     */
    public static boolean compararEnTiempoConstante() {
        // GUÍA: byte[] a = md.digest("x".getBytes(UTF_8)); byte[] b = md2.digest("x".getBytes(UTF_8));
        //   return MessageDigest.isEqual(a, b);
        // CULTURA: isEqual compara sin "cortocircuito" temprano, evitando timing attacks (medir cuánto
        // tarda la comparación para deducir el valor). Úsalo siempre con secretos. Enlaza con Ej246.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compararEnTiempoConstante");
    }
}
