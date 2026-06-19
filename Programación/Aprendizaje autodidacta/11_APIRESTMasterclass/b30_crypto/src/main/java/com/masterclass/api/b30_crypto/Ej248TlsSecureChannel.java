package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 248 · Canal seguro TLS: cripto aplicada de verdad sobre sockets.
 *
 * <p>Cierre del bloque: TLS (lo que pone la "s" en HTTPS) combina TODO lo anterior. En el
 * handshake, cliente y servidor usan criptografía <b>asimétrica</b> (244/245: el certificado
 * del servidor, su clave pública y la firma de una CA) para autenticarse y acordar una clave
 * <b>simétrica</b> de sesión (243), con la que cifran el resto del tráfico (rápido). Es el
 * cifrado <b>híbrido</b> en acción.
 *
 * <p>En Java un canal TLS es un {@code SSLSocket}/{@code SSLServerSocket}: se usan como los
 * sockets de {@code b29} pero con cifrado transparente. El servidor necesita su clave privada y
 * certificado (de un {@code KeyStore}, 247); el cliente necesita confiar en ese certificado (un
 * <b>truststore</b>). Este módulo trae {@code server.p12} (keystore) y {@code truststore.p12}
 * (con el certificado del servidor) en {@code src/main/resources}.
 *
 * <p>Puente con b29: un servidor de sockets + TLS = un servidor seguro; + HTTP = HTTPS.
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.8).
 */
public final class Ej248TlsSecureChannel {

    private Ej248TlsSecureChannel() {
    }

    public static final char[] PASSWORD = "changeit".toCharArray();
    public static final String KEYSTORE = "/server.p12";       // clave privada + cert del servidor
    public static final String TRUSTSTORE = "/truststore.p12"; // cert en que confía el cliente

    /**
     * Levanta un servidor TLS local, le envía un mensaje por un canal cifrado y devuelve el eco.
     *
     * @param mensaje texto a enviar por el túnel TLS
     * @return el eco recibido (== mensaje), o {@code null} si no se ha implementado
     */
    public static String ecoTlsRoundTrip(String mensaje) {
        // TODO 1: SERVIDOR — carga el KeyStore /server.p12 (PKCS12, PASSWORD); crea un
        //         KeyManagerFactory.getInstance("PKIX"/"SunX509"); kmf.init(ks, PASSWORD).
        // TODO 2: SSLContext sc = SSLContext.getInstance("TLS"); sc.init(kmf.getKeyManagers(), null, null);
        //         SSLServerSocket ss = (SSLServerSocket) sc.getServerSocketFactory().createServerSocket(0); guarda el puerto.
        // TODO 3: lanza el hilo servidor: SSLSocket s = (SSLSocket) ss.accept(); lee una línea y hace eco
        //         (BufferedReader/PrintWriter sobre los streams del SSLSocket, igual que en b29).
        // TODO 4: CLIENTE — carga /truststore.p12 a un KeyStore; TrustManagerFactory.getInstance("PKIX");
        //         tmf.init(trust); SSLContext sc2 = SSLContext.getInstance("TLS"); sc2.init(null, tmf.getTrustManagers(), null).
        // TODO 5: SSLSocket c = (SSLSocket) sc2.getSocketFactory().createSocket("localhost", puerto);
        //         envía 'mensaje' con println y lee la respuesta con readLine().
        // TODO 6: cierra todo y devuelve la respuesta (maneja IOException/GeneralSecurityException).
        return null;
    }

    /**
     * Indica si la plataforma soporta TLS 1.3.
     *
     * @return true si "TLSv1.3" está entre los protocolos soportados por defecto
     */
    public static boolean soportaTls13() {
        // TODO 7: SSLContext sc = SSLContext.getDefault().
        // TODO 8: obtén los protocolos soportados: sc.getSupportedSSLParameters().getProtocols().
        // TODO 9: comprueba si el array contiene "TLSv1.3" (Arrays.asList(...).contains("TLSv1.3")).
        // TODO 10: devuelve ese booleano (maneja NoSuchAlgorithmException).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("ecoTlsRoundTrip = " + ecoTlsRoundTrip("hola TLS"));
        System.out.println("soportaTls13 = " + soportaTls13());
    }

    /**
     * Reto Extra 1: tras el handshake, el protocolo negociado es de la familia TLS.
     * @return true si SSLSession.getProtocol() empieza por "TLS"
     */
    public static boolean handshakeNegociaTls() {
        // GUÍA: teoría 30.8. En el cliente, tras conectar, fuerza el handshake con c.startHandshake()
        // y consulta c.getSession().getProtocol(): "TLSv1.3" (o "TLSv1.2"). return protocolo.startsWith("TLS").
        // PISTA: reutiliza el montaje servidor+cliente de ecoTlsRoundTrip.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para handshakeNegociaTls");
    }

    /**
     * Reto Extra 2: el servidor presenta su certificado durante el handshake.
     * @return true si el cliente recibe al menos un certificado del servidor
     */
    public static boolean servidorPresentaCertificado() {
        // GUÍA: tras el handshake, c.getSession().getPeerCertificates() devuelve la cadena de certs del
        // servidor. return peer.length > 0;  (lanzaría SSLPeerUnverifiedException si no se autenticó).
        // CULTURA: así sabe el cliente que habla con quien dice (autenticación del servidor, 245).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para servidorPresentaCertificado");
    }

    /**
     * Reto Extra 3: se negocia una suite de cifrado concreta.
     * @return true si SSLSession.getCipherSuite() no es null ni vacío
     */
    public static boolean cipherSuiteNegociadaNoVacia() {
        // GUÍA: String suite = c.getSession().getCipherSuite();  // p.ej. "TLS_AES_256_GCM_SHA384"
        //   return suite != null && !suite.isEmpty();
        // CULTURA: la cipher suite dice qué algoritmos concretos se usan (intercambio de clave + cifrado
        // simétrico + hash); en TLS 1.3 son AEAD como AES-GCM (Ej243) o ChaCha20.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cipherSuiteNegociadaNoVacia");
    }

    /**
     * Reto Extra 4: un cliente que NO confía en el certificado rechaza la conexión.
     * @return true si conectar sin el truststore correcto lanza SSLHandshakeException
     */
    public static boolean clienteSinTruststoreRechaza() {
        // GUÍA: monta el cliente con un SSLContext por defecto (SSLContext.getDefault(), que NO confía
        // en nuestro cert autofirmado) e intenta el handshake contra el servidor:
        //   try { c.startHandshake(); return false; }
        //   catch (javax.net.ssl.SSLHandshakeException e) { return true; }
        // OJO/CUIDADO: este es EL motivo de que un cert autofirmado dé error en el navegador. Verificar
        // el certificado es lo que impide ataques man-in-the-middle. NUNCA lo desactives "para que funcione".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clienteSinTruststoreRechaza");
    }

    /**
     * Reto Extra 5: el canal TLS transporta UTF-8 intacto (acentos/ñ).
     * @return el eco de "ñandú café" por el túnel TLS
     */
    public static String ecoTlsUtf8(String mensaje) {
        // GUÍA: igual que ecoTlsRoundTrip pero con StandardCharsets.UTF_8 explícito en los streams.
        // OJO: el test manda "ñandú café"; el cifrado no cambia el problema del encoding (b26/b29).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoTlsUtf8");
    }

    /**
     * Reto Extra 6: la plataforma ofrece suites de cifrado soportadas.
     * @return true si SSLServerSocketFactory.getDefault().getSupportedCipherSuites() no está vacío
     */
    public static boolean suitesSoportadasNoVacias() {
        // GUÍA: var f = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        //   return f.getSupportedCipherSuites().length > 0;
        // No necesita handshake; solo inspecciona las capacidades del runtime.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para suitesSoportadasNoVacias");
    }

    /**
     * Reto Extra 7: se puede obtener un SSLContext para "TLS".
     * @return true si SSLContext.getInstance("TLS") devuelve una instancia
     */
    public static boolean sslContextTlsDisponible() {
        // GUÍA: return SSLContext.getInstance("TLS") != null;  // maneja NoSuchAlgorithmException.
        // CULTURA: el SSLContext es la "fábrica" central: combina KeyManagers (quién soy) y
        // TrustManagers (en quién confío) para producir sockets seguros.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sslContextTlsDisponible");
    }

    /**
     * Reto Extra 8: construir un KeyManager desde el keystore del servidor.
     * @return true si KeyManagerFactory inicializado con /server.p12 produce al menos un KeyManager
     */
    public static boolean keyManagerDesdeKeystore() {
        // GUÍA: carga /server.p12; KeyManagerFactory kmf = KeyManagerFactory.getInstance(
        //   KeyManagerFactory.getDefaultAlgorithm()); kmf.init(ks, PASSWORD);
        //   return kmf.getKeyManagers().length > 0;
        // CULTURA: el KeyManager es lo que presenta la identidad (clave privada + cert) del lado servidor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para keyManagerDesdeKeystore");
    }

    /**
     * Reto Extra 9: construir un TrustManager desde el truststore.
     * @return true si TrustManagerFactory inicializado con /truststore.p12 produce al menos un TrustManager
     */
    public static boolean trustManagerDesdeTruststore() {
        // GUÍA: carga /truststore.p12; TrustManagerFactory tmf = TrustManagerFactory.getInstance(
        //   TrustManagerFactory.getDefaultAlgorithm()); tmf.init(trust);
        //   return tmf.getTrustManagers().length > 0;
        // CULTURA: el TrustManager decide en qué certificados confía el cliente; con el cert del servidor
        // dentro, el handshake del reto 4 sí funcionaría (a diferencia del truststore por defecto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para trustManagerDesdeTruststore");
    }

    /**
     * Reto Extra 10: el canal TLS transporta bytes crudos intactos (round-trip).
     * @return los bytes recibidos de vuelta por el túnel, iguales a {9,8,7}
     */
    public static byte[] tlsRoundTripBytes(byte[] datos) {
        // GUÍA: envía 'datos' por el OutputStream del SSLSocket y léelos en el servidor (eco binario,
        // como en b29·Ej234). El cifrado es transparente: escribes/lees bytes normales y TLS los protege.
        // OJO: usa shutdownOutput() o un marcador para señalar fin, y readAllBytes/longitud conocida.
        // El test compara con {9,8,7}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tlsRoundTripBytes");
    }
}
