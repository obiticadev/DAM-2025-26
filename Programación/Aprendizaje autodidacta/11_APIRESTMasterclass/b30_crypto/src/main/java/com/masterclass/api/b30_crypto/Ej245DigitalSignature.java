package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 245 · Firma digital: autenticidad, integridad y no repudio.
 *
 * <p>El cifrado oculta; la <b>firma</b> demuestra QUIÉN escribió algo y que NO ha cambiado.
 * Es el uso "inverso" del par asimétrico (244): se firma con la <b>privada</b> y se verifica
 * con la <b>pública</b>. Como solo el dueño tiene la privada, una firma válida prueba:
 * <ul>
 *   <li><b>Autenticidad</b>: lo firmó el dueño de la privada.</li>
 *   <li><b>Integridad</b>: el mensaje no se alteró (la firma incluye un hash del contenido).</li>
 *   <li><b>No repudio</b>: el firmante no puede negar haberlo firmado.</li>
 * </ul>
 * Por debajo, {@code "SHA256withRSA"} hashea el mensaje con SHA-256 y firma ese hash con RSA.
 * Es lo que hace un certificado o un JWT firmado (enlaza con b18).
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.5).
 */
public final class Ej245DigitalSignature {

    private Ej245DigitalSignature() {
    }

    /**
     * Firma un mensaje con una clave privada RSA y verifica la firma con la pública.
     *
     * @param mensaje texto a firmar
     * @return true si la firma se verifica correctamente
     */
    public static boolean firmarYVerificar(String mensaje) {
        // TODO 1: genera un par RSA de 2048 bits (KeyPairGenerator).
        // TODO 2: Signature firmador = Signature.getInstance("SHA256withRSA"); firmador.initSign(kp.getPrivate()).
        // TODO 3: firmador.update(mensaje.getBytes(UTF_8)); byte[] firma = firmador.sign().
        // TODO 4: Signature verificador = Signature.getInstance("SHA256withRSA"); verificador.initVerify(kp.getPublic()).
        // TODO 5: verificador.update(mensaje.getBytes(UTF_8)); devuelve verificador.verify(firma).
        // TODO 6: maneja GeneralSecurityException como RuntimeException.
        return false;
    }

    /**
     * Firma un mensaje y comprueba que la verificación FALLA si el mensaje se altera.
     *
     * @param original mensaje firmado
     * @param alterado  mensaje distinto contra el que se verifica la firma del original
     * @return true si la verificación del mensaje alterado da false (firma inválida)
     */
    public static boolean verificaFallaSiCambiaMensaje(String original, String alterado) {
        // TODO 7: firma 'original' con la privada (como arriba) y obtén la firma.
        // TODO 8: prepara el verificador con la pública y aliméntalo con 'alterado' (¡no el original!).
        // TODO 9: llama a verify(firma): debe devolver false porque el contenido no casa con la firma.
        // TODO 10: devuelve true cuando esa verificación da false (es decir, la firma protege la integridad).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("firmarYVerificar = " + firmarYVerificar("documento importante"));
        System.out.println("verificaFallaSiCambiaMensaje = " + verificaFallaSiCambiaMensaje("original", "alterado"));
    }

    /**
     * Reto Extra 1: se firma con la privada y se verifica con la pública.
     * @return true si firmar con la privada y verificar con la pública del MISMO par funciona
     */
    public static boolean firmaConPrivadaVerificaConPublica() {
        // GUÍA: return firmarYVerificar("contrato");  // el flujo canónico privada->pública.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para firmaConPrivadaVerificaConPublica");
    }

    /**
     * Reto Extra 2: verificar con la clave pública de OTRO par falla.
     * @return true si una firma del par A no se verifica con la pública del par B
     */
    public static boolean verificarConOtraClaveFalla() {
        // GUÍA: firma con la privada de A; verifica con la pública de B (otro par): verify() -> false.
        // return !verify;  // devuelve true cuando la verificación cruzada falla, como debe.
        // CULTURA: esto es lo que garantiza la AUTENTICIDAD: solo la pública pareja valida la firma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarConOtraClaveFalla");
    }

    /**
     * Reto Extra 3: la firma RSA-2048 ocupa 256 bytes.
     * @return longitud en bytes de la firma (debe ser 256)
     */
    public static int longitudFirmaRsa2048() {
        // GUÍA: firma cualquier mensaje con RSA-2048 y devuelve firma.length;  // 2048/8 = 256.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudFirmaRsa2048");
    }

    /**
     * Reto Extra 4: el algoritmo de firma se identifica como "SHA256withRSA".
     * @return el nombre del algoritmo del objeto Signature (debe ser "SHA256withRSA")
     */
    public static String algoritmoDeFirma() {
        // GUÍA: Signature s = Signature.getInstance("SHA256withRSA"); return s.getAlgorithm();
        // CULTURA: "SHA256withRSA" = primero hash SHA-256 del mensaje, luego firma RSA de ese hash.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para algoritmoDeFirma");
    }

    /**
     * Reto Extra 5: firmar y verificar bytes crudos.
     * @return true si la firma de un byte[] se verifica correctamente
     */
    public static boolean firmarYVerificarBytes(byte[] datos) {
        // GUÍA: mismo flujo pero update(datos) en vez de un String. El test pasa {1,2,3}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para firmarYVerificarBytes");
    }

    /**
     * Reto Extra 6: manipular la firma la invalida.
     * @return true si alterar un byte de la firma hace que verify() devuelva false
     */
    public static boolean manipularFirmaInvalida() {
        // GUÍA: firma "x", altera firma[0] ^= 1 y verifica: verify() -> false. return !verify.
        // OJO: verify() devuelve false; en algunos casos una firma corrupta puede lanzar SignatureException:
        // captúrala y trátala también como "inválida" (return true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para manipularFirmaInvalida");
    }

    /**
     * Reto Extra 7: la firma se puede transportar como Base64.
     * @return true si la firma codificada en Base64 no está vacía
     */
    public static boolean firmaBase64NoVacia() {
        // GUÍA: byte[] firma = ...; String b64 = Base64.getEncoder().encodeToString(firma);
        //   return !b64.isEmpty();
        // CULTURA: así viaja la firma en un JWT (b18) o en una cabecera HTTP: bytes -> Base64.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para firmaBase64NoVacia");
    }

    /**
     * Reto Extra 8: también se puede firmar con curva elíptica (ECDSA).
     * @return true si firmar/verificar con un par EC y "SHA256withECDSA" funciona
     */
    public static boolean ecdsaFirmaYVerifica() {
        // GUÍA: KeyPairGenerator.getInstance("EC"); kpg.initialize(256); par EC.
        //   Signature.getInstance("SHA256withECDSA"); mismo flujo sign/verify.
        // CULTURA: ECDSA da la misma seguridad que RSA con claves MUCho más cortas (256 bits EC ≈
        // 3072 bits RSA); por eso domina en TLS moderno y blockchain.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecdsaFirmaYVerifica");
    }

    /**
     * Reto Extra 9: SHA256withRSA (PKCS#1 v1.5) es determinista: misma clave y mensaje, misma firma.
     * @return true si firmar dos veces el mismo mensaje con la misma privada da firmas idénticas
     */
    public static boolean firmaRsaEsDeterminista() {
        // GUÍA: con un MISMO par de claves, firma "repetible" dos veces y compara los byte[]: iguales
        // (el padding PKCS#1 v1.5 de firma no es aleatorio). CONTRASTE con el cifrado OAEP (Ej244 reto7),
        // que SÍ es aleatorio. (Nota: ECDSA y RSA-PSS no serían deterministas.)
        // OJO: usa el mismo KeyPair para las dos firmas; con pares distintos saldrían firmas distintas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para firmaRsaEsDeterminista");
    }

    /**
     * Reto Extra 10: no repudio — una firma válida solo la pudo crear el dueño de la privada.
     * @return true si la firma de A verifica con la pública de A pero NO con la pública de B
     */
    public static boolean noRepudioSoloDuenoFirma() {
        // GUÍA: combina los retos 1 y 2: firma con privada de A; verifica con pública de A (true) y
        // con pública de B (false). return (verificaConA && !verificaConB).
        // CULTURA: el no repudio es jurídicamente potente: el firmante no puede negar su firma porque
        // nadie más tiene su privada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noRepudioSoloDueñoFirma");
    }
}
