package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 247 · KeyStore: dónde se guardan claves y certificados de forma segura.
 *
 * <p>Las claves (privadas, secretas) y los certificados no se guardan en un .txt: van en un
 * <b>KeyStore</b>, un almacén cifrado y protegido por contraseña. Tipos habituales:
 * <ul>
 *   <li><b>PKCS12</b> (.p12/.pfx): estándar de la industria, guarda claves privadas + certificados.</li>
 *   <li><b>JCEKS</b>: formato propietario de Java que además guarda claves <i>secretas</i> (AES, HMAC).</li>
 * </ul>
 * Es lo que usan los servidores TLS (248) para custodiar su clave privada. Este módulo trae en
 * {@code src/main/resources} un {@code server.p12} autofirmado (alias {@code server}, clave
 * {@code changeit}) generado con {@code keytool}, que aquí cargas y exploras.
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.7).
 */
public final class Ej247KeyStore {

    private Ej247KeyStore() {
    }

    /** Contraseña del keystore de recursos (de juguete; en producción JAMÁS se hardcodea). */
    public static final char[] PASSWORD = "changeit".toCharArray();
    /** Nombre del recurso en el classpath (src/main/resources/server.p12). */
    public static final String RECURSO_KEYSTORE = "/server.p12";

    /**
     * Crea un KeyStore JCEKS en un fichero temporal, guarda una clave secreta AES, lo recarga
     * y comprueba que la clave recuperada es idéntica.
     *
     * @return true si la clave secreta sobrevive al guardado y la carga
     */
    public static boolean guardarYCargarClaveSecreta() {
        // TODO 1: genera una SecretKey AES (KeyGenerator "AES", 128) y recuerda sus bytes (getEncoded).
        // TODO 2: KeyStore ks = KeyStore.getInstance("JCEKS"); ks.load(null, PASSWORD) para inicializar uno vacío.
        // TODO 3: guarda la clave con ks.setKeyEntry("ai", clave, PASSWORD, null) (sin cadena de certs).
        // TODO 4: persiste a un fichero temporal: try (OutputStream os = Files.newOutputStream(tmp)) { ks.store(os, PASSWORD); }.
        // TODO 5: recarga: KeyStore ks2 = KeyStore.getInstance("JCEKS"); ks2.load(in, PASSWORD);
        //         recupera con ks2.getKey("ai", PASSWORD) y compara getEncoded() con el original (Arrays.equals).
        // TODO 6: borra el temporal y devuelve el resultado de la comparación (maneja las excepciones).
        return false;
    }

    /**
     * Carga el keystore de recursos y cuenta cuántos alias contiene.
     *
     * @return número de alias del keystore (será 1: "server"), o -1 si no se ha implementado
     */
    public static int numeroDeAliasDelKeystoreRecurso() {
        // TODO 7: KeyStore ks = KeyStore.getInstance("PKCS12").
        // TODO 8: carga desde el classpath: try (InputStream in = Ej247KeyStore.class.getResourceAsStream(RECURSO_KEYSTORE)) { ks.load(in, PASSWORD); }.
        // TODO 9: cuenta los alias con ks.size() (o recorriendo ks.aliases()).
        // TODO 10: devuelve ese número (maneja KeyStoreException/IOException/etc.).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("guardarYCargarClaveSecreta = " + guardarYCargarClaveSecreta());
        System.out.println("numeroDeAliasDelKeystoreRecurso = " + numeroDeAliasDelKeystoreRecurso());
    }

    /**
     * Reto Extra 1: el tipo PKCS12 se identifica como "PKCS12".
     * @return el tipo del KeyStore creado como PKCS12 (debe ser "PKCS12")
     */
    public static String tipoKeyStorePkcs12() {
        // GUÍA: return KeyStore.getInstance("PKCS12").getType();  // "PKCS12"
        // CULTURA: PKCS12 es interoperable (lo leen OpenSSL, Windows, navegadores); JKS (el viejo
        // formato Java) está deprecado en favor de PKCS12.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoKeyStorePkcs12");
    }

    /**
     * Reto Extra 2: el keystore de recursos se carga con la contraseña correcta.
     * @return true si ks.load del recurso con PASSWORD no lanza excepción
     */
    public static boolean cargaConPasswordCorrecta() {
        // GUÍA: carga /server.p12 con PASSWORD ("changeit"); si no lanza, return true.
        // PISTA: reutiliza la carga de numeroDeAliasDelKeystoreRecurso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargaConPasswordCorrecta");
    }

    /**
     * Reto Extra 3: cargar con la contraseña incorrecta falla.
     * @return true si ks.load del recurso con una contraseña errónea lanza IOException
     */
    public static boolean cargaConPasswordIncorrectaFalla() {
        // GUÍA: try { ks.load(in, "mala".toCharArray()); return false; }
        //   catch (IOException e) { return true; }
        // OJO: la integridad del keystore se valida con la contraseña; una mala da IOException
        // (con UnrecoverableKeyException como causa típica).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargaConPasswordIncorrectaFalla");
    }

    /**
     * Reto Extra 4: el keystore de recursos contiene el alias "server".
     * @return true si ks.containsAlias("server")
     */
    public static boolean contieneAliasServer() {
        // GUÍA: carga el recurso y return ks.containsAlias("server");
        // CULTURA: el alias es el "nombre" de una entrada; un keystore puede tener muchas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneAliasServer");
    }

    /**
     * Reto Extra 5: se puede recuperar el certificado del alias "server".
     * @return true si ks.getCertificate("server") no es null
     */
    public static boolean recuperarCertificado() {
        // GUÍA: Certificate cert = ks.getCertificate("server"); return cert != null;
        // El certificado contiene la clave PÚBLICA y los datos del titular (CN=localhost).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recuperarCertificado");
    }

    /**
     * Reto Extra 6: se puede recuperar la clave privada del alias "server".
     * @return true si ks.getKey("server", PASSWORD) es una PrivateKey
     */
    public static boolean recuperarClavePrivada() {
        // GUÍA: Key k = ks.getKey("server", PASSWORD); return k instanceof java.security.PrivateKey;
        // OJO: getKey pide la contraseña de la ENTRADA (aquí igual que la del store); por eso una
        // clave privada nunca está accesible sin contraseña.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recuperarClavePrivada");
    }

    /**
     * Reto Extra 7: listar los alias del keystore con la enumeración aliases().
     * @return true si la lista de alias contiene "server"
     */
    public static boolean listarAliasContieneServer() {
        // GUÍA: List<String> alias = java.util.Collections.list(ks.aliases()); return alias.contains("server");
        // PISTA: aliases() devuelve una Enumeration; Collections.list la pasa a List.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listarAliasContieneServer");
    }

    /**
     * Reto Extra 8: guardar un certificado en un keystore nuevo y recuperarlo.
     * @return true si, tras setCertificateEntry y recargar, el alias del certificado existe
     */
    public static boolean guardarCertificadoEnNuevoKeystore() {
        // GUÍA: saca el cert del recurso (getCertificate("server")). Crea un PKCS12 vacío (load(null,PASSWORD)),
        // ks2.setCertificateEntry("copia", cert), persiste a temporal, recárgalo y comprueba containsAlias("copia").
        // CULTURA: así se construye un truststore: un keystore que solo contiene certificados de confianza (248).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para guardarCertificadoEnNuevoKeystore");
    }

    /**
     * Reto Extra 9: borrar una entrada reduce el número de alias.
     * @return true si, tras deleteEntry, el tamaño del keystore disminuye en 1
     */
    public static boolean eliminarEntradaReduceTamano() {
        // GUÍA: carga el recurso a un keystore en memoria (no hace falta re-persistir). int antes = ks.size();
        //   ks.deleteEntry("server"); return ks.size() == antes - 1;
        // OJO: deleteEntry afecta a la copia en memoria; para que sea permanente habría que store() de nuevo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarEntradaReduceTamano");
    }

    /**
     * Reto Extra 10: JCEKS puede guardar una clave SECRETA (simétrica), no solo asimétricas.
     * @return true si una SecretKey AES guardada en JCEKS se recupera con los mismos bytes
     */
    public static boolean jceksGuardaClaveSecreta() {
        // GUÍA: es justo lo que hace guardarYCargarClaveSecreta; reutilízalo: return guardarYCargarClaveSecreta();
        // CULTURA: PKCS12 moderno también admite SecretKey, pero JCEKS fue históricamente EL sitio para
        // claves AES/HMAC. Las claves simétricas (243/246) también necesitan custodia segura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para jceksGuardaClaveSecreta");
    }
}
