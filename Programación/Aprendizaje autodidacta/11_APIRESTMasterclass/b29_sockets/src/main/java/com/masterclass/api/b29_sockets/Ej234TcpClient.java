package com.masterclass.api.b29_sockets;

import java.util.List;

/**
 * Ejercicio 234 · Cliente TCP: conectar, hablar y proteger la espera con timeouts.
 *
 * <p>El cliente es la otra mitad del 233. Aquí el foco está en el lado cliente: abrir un
 * {@code Socket} contra host:puerto, escribir y leer por sus streams, y sobre todo
 * <b>no quedarse colgado</b>: {@code setSoTimeout} corta una lectura que no llega, y
 * conectar a un puerto cerrado lanza {@code ConnectException}. Saber distinguir esos fallos
 * es la diferencia entre un cliente robusto y uno que cuelga la aplicación.
 *
 * <p>Para ser autocontenido, cada método core levanta un pequeño servidor de eco en un hilo
 * (puerto efímero) y actúa de cliente contra él.
 *
 * <p>Teoría: {@code teoria/29_Sockets_Red.md} (sección 29.2).
 */
public final class Ej234TcpClient {

    private Ej234TcpClient() {
    }

    /**
     * Conecta a un servidor de eco con un timeout de lectura y devuelve la respuesta.
     *
     * @param mensaje   texto a enviar
     * @param timeoutMs milisegundos máximos esperando respuesta (0 = sin límite)
     * @return el eco recibido (== mensaje)
     */
    public static String enviarYRecibirConTimeout(String mensaje, int timeoutMs) {
        // TODO 1: levanta un servidor de eco en puerto efímero dentro de un hilo (como en Ej233).
        // TODO 2: abre el cliente: Socket cliente = new Socket("localhost", puerto).
        // TODO 3: aplica cliente.setSoTimeout(timeoutMs) ANTES de leer (limita read()/readLine()).
        // TODO 4: envía con PrintWriter(autoFlush) out.println(mensaje).
        // TODO 5: lee la respuesta con BufferedReader in.readLine().
        // TODO 6: cierra cliente y devuelve la respuesta (maneja IOException).
        return null;
    }

    /**
     * Envía varios mensajes por la misma conexión y concatena los ecos sin separador.
     *
     * @param mensajes lista de mensajes
     * @return concatenación de las respuestas en orden (p.ej. ["a","b"] -&gt; "ab")
     */
    public static String enviarVariosYConcatenar(List<String> mensajes) {
        // TODO 7: levanta servidor de eco que atienda varias líneas (bucle while readLine != null).
        // TODO 8: en el cliente, por cada mensaje out.println(m) y luego in.readLine().
        // TODO 9: acumula las respuestas en un StringBuilder.
        // TODO 10: cierra la salida del cliente para liberar al servidor y devuelve sb.toString().
        return null;
    }

    public static void main(String[] args) {
        System.out.println("enviarYRecibirConTimeout(\"ping\",2000) = " + enviarYRecibirConTimeout("ping", 2000));
        System.out.println("enviarVariosYConcatenar([a,b,c]) = " + enviarVariosYConcatenar(List.of("a", "b", "c")));
    }

    /**
     * Reto Extra 1: una lectura que no recibe nada dentro del timeout lanza SocketTimeoutException.
     * @return true si setSoTimeout corto provoca SocketTimeoutException al leer
     */
    public static boolean timeoutLanzaSocketTimeout() {
        // GUÍA: teoría 29.2. Levanta un servidor que ACEPTA pero NO responde (se queda dormido).
        // En el cliente: setSoTimeout(200); try { in.readLine(); return false; }
        //   catch (SocketTimeoutException e) { return true; }
        // OJO: SocketTimeoutException es subclase de IOException; captúrala ANTES que IOException.
        // CULTURA: sin timeout, un peer mudo cuelga tu hilo para siempre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para timeoutLanzaSocketTimeout");
    }

    /**
     * Reto Extra 2: conectar a un puerto donde no escucha nadie lanza ConnectException.
     * @return true si new Socket a un puerto cerrado lanza ConnectException
     */
    public static boolean conexionRehusada() {
        // GUÍA: obtén un puerto libre y CIÉRRALO para garantizar que nadie escucha:
        //   int p; try (ServerSocket s = new ServerSocket(0)) { p = s.getLocalPort(); }
        //   try (Socket c = new Socket("localhost", p)) { return false; }
        //   catch (ConnectException e) { return true; }
        // OJO: ConnectException es subclase de IOException; no la tapes con un catch(IOException) genérico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conexionRehusada");
    }

    /**
     * Reto Extra 3: round-trip de un número entero como texto.
     * @return el número recibido de vuelta (== n)
     */
    public static int ecoNumeroEntero(int n) {
        // GUÍA: el cliente envía out.println(String.valueOf(n)); el servidor lo reescribe; el cliente
        // lee y hace Integer.parseInt(in.readLine()). Reutiliza enviarYRecibirConTimeout si quieres
        // y parsea su resultado: return Integer.parseInt(enviarYRecibirConTimeout(String.valueOf(n), 2000)).
        // OJO: el test prueba 42 y también -7 (números negativos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoNumeroEntero");
    }

    /**
     * Reto Extra 4: el cliente lee TODAS las líneas hasta el fin de stream y las cuenta.
     * @return número de líneas que envió el servidor antes de cerrar (== 3)
     */
    public static int leerTodasLasLineasHastaEof() {
        // GUÍA: el servidor escribe 3 líneas ("uno","dos","tres") y CIERRA. El cliente:
        //   int n=0; while (in.readLine() != null) n++; return n;
        // PISTA: el bucle termina solo cuando readLine() devuelve null (EOF). El servidor DEBE cerrar.
        // OJO: el test espera exactamente 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerTodasLasLineasHastaEof");
    }

    /**
     * Reto Extra 5: enviar usando BufferedWriter con flush explícito en vez de PrintWriter.
     * @return el eco recibido (== mensaje)
     */
    public static String enviarConBufferedWriter(String mensaje) {
        // GUÍA: BufferedWriter w = new BufferedWriter(new OutputStreamWriter(out, UTF_8));
        //   w.write(mensaje); w.newLine(); w.flush();   // ¡sin flush, el servidor no recibe nada!
        // OJO/CUIDADO: PrintWriter con autoFlush=true hace el flush por ti; BufferedWriter NO.
        // Olvidar flush() es la causa nº1 de "mi cliente se cuelga y no pasa nada".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enviarConBufferedWriter");
    }

    /**
     * Reto Extra 6: la dirección remota de la conexión es loopback (localhost).
     * @return true si cliente.getInetAddress().isLoopbackAddress()
     */
    public static boolean direccionRemotaEsLoopback() {
        // GUÍA: tras conectar al servidor local, cliente.getInetAddress() es la IP del peer.
        // return cliente.getInetAddress().isLoopbackAddress();  // 127.0.0.1 / ::1
        // CULTURA: en un servidor real, esto te da la IP del cliente (logging, rate-limit por IP).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para direccionRemotaEsLoopback");
    }

    /**
     * Reto Extra 7: setSoTimeout(0) significa "espera indefinida" y la respuesta normal llega igual.
     * @return el eco recibido con timeout 0 (== mensaje)
     */
    public static String soTimeoutCeroEsperaSiempre(String mensaje) {
        // GUÍA: reutiliza enviarYRecibirConTimeout(mensaje, 0). El valor 0 NO es "no esperes": es
        // "espera lo que haga falta". Como el servidor responde enseguida, recibes el eco normal.
        // OJO: el test manda "sin-limite" y espera ese mismo texto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para soTimeoutCeroEsperaSiempre");
    }

    /**
     * Reto Extra 8: enviar y recibir bytes crudos (sin capa de texto).
     * @return los bytes devueltos por el servidor (iguales a los enviados)
     */
    public static byte[] enviarBytesYRecibirBytes(byte[] datos) {
        // GUÍA: trabaja directamente sobre los streams binarios: cliente.getOutputStream().write(datos);
        //   cliente.getOutputStream().flush(); cliente.shutdownOutput();  // señala fin al servidor
        //   luego lee todo con inputStream.readAllBytes(). El servidor de eco copia bytes con un buffer.
        // PISTA: cliente.shutdownOutput() cierra solo la escritura; sigues pudiendo leer la respuesta.
        // OJO: el test compara con assertArrayEquals sobre {1,2,3,4,5}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enviarBytesYRecibirBytes");
    }

    /**
     * Reto Extra 9: el cliente se reconecta tras cerrar y el servidor lo atiende dos veces.
     * @return número de conexiones completadas con éxito (== 2)
     */
    public static int reconectarTrasCierre() {
        // GUÍA: el servidor hace un bucle accept() x2 (atiende, cierra, vuelve a accept()).
        // El cliente: conecta, envía/lee, cierra; vuelve a conectar al MISMO puerto, envía/lee, cierra.
        // Cuenta 2 round-trips correctos. Reutiliza la idea de cuentaConexionesAtendidas de Ej233.
        // OJO: el test espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reconectarTrasCierre");
    }

    /**
     * Reto Extra 10: un mensaje muy largo viaja completo (TCP es un flujo, no respeta "paquetes").
     * @return longitud del eco recibido (== mensaje.length())
     */
    public static int mensajeLargoLlegaCompleto() {
        // GUÍA: construye un mensaje de 10000 caracteres ("a".repeat(10000)), envíalo y lee el eco
        // con readLine(); return respuesta.length().
        // CULTURA: TCP es un STREAM de bytes; un write grande puede llegar troceado en varios read(),
        // pero readLine() reensambla hasta el '\n'. No asumas "1 write = 1 read".
        // OJO: el test espera 10000.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeLargoLlegaCompleto");
    }
}
