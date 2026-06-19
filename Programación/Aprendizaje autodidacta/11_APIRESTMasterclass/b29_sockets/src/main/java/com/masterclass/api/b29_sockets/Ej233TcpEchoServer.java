package com.masterclass.api.b29_sockets;

import java.util.List;

/**
 * Ejercicio 233 · Servidor de eco TCP: el "hola mundo" de los sockets.
 *
 * <p>Un {@code ServerSocket} escucha en un puerto y, en {@code accept()}, se bloquea hasta
 * que llega un cliente; entonces devuelve un {@code Socket} ya conectado con ese cliente.
 * Sobre los streams de ese socket lees y escribes como si fuera un fichero. El servidor de
 * eco se limita a devolver lo que recibe: es el esqueleto sobre el que se construye TODO
 * (un endpoint REST no es más que esto + HTTP + Spring por encima, ver {@code b00} y {@code b05}).
 *
 * <p>Para que el ejercicio sea autocontenido y testeable, cada método core levanta el
 * servidor en un puerto efímero ({@code new ServerSocket(0)}) dentro de un hilo y conecta
 * un cliente en el mismo método. En producción el servidor vive en su propio proceso.
 *
 * <p>Teoría: {@code teoria/29_Sockets_Red.md} (sección 29.1).
 */
public final class Ej233TcpEchoServer {

    private Ej233TcpEchoServer() {
    }

    /**
     * Levanta un servidor de eco, le envía un mensaje y devuelve lo que el servidor responde.
     *
     * @param mensaje texto a enviar (una línea, sin saltos internos)
     * @return el eco devuelto por el servidor (== mensaje)
     */
    public static String ecoDeUnMensaje(String mensaje) {
        // TODO 1: abre un ServerSocket en puerto efímero: try (ServerSocket server = new ServerSocket(0)).
        // TODO 2: guarda el puerto real con server.getLocalPort() (lo necesita el cliente).
        // TODO 3: lanza un hilo "servidor": dentro, Socket s = server.accept(); envuelve s.getInputStream()
        //         en BufferedReader (UTF-8) y s.getOutputStream() en PrintWriter(autoFlush=true);
        //         lee una línea con readLine() y escríbela de vuelta con println(); cierra el socket.
        // TODO 4: arranca el hilo con start().
        // TODO 5: en el hilo actual abre el cliente: try (Socket cliente = new Socket("localhost", puerto));
        //         envía mensaje con out.println(mensaje) y lee la respuesta con in.readLine().
        // TODO 6: devuelve la respuesta leída (maneja IOException; junta el hilo con join() si quieres).
        return null;
    }

    /**
     * Envía varias líneas por una ÚNICA conexión y recoge el eco de cada una, en orden.
     *
     * @param lineas mensajes a enviar uno tras otro
     * @return lista con el eco de cada línea, en el mismo orden
     */
    public static List<String> ecoDeVariasLineas(List<String> lineas) {
        // TODO 7: levanta el servidor; el hilo servidor debe hacer un BUCLE while ((l = in.readLine()) != null)
        //         que reescriba cada línea con out.println(l) (así atiende varias en la misma conexión).
        // TODO 8: en el cliente, recorre 'lineas' y por cada una out.println(x) y luego in.readLine().
        // TODO 9: acumula cada respuesta en una List<String> result.
        // TODO 10: cierra el lado de escritura del cliente (o el socket) para que el servidor vea EOF;
        //          junta el hilo y devuelve result.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("ecoDeUnMensaje(\"hola\") = " + ecoDeUnMensaje("hola"));
        System.out.println("ecoDeVariasLineas([a,b,c]) = " + ecoDeVariasLineas(List.of("a", "b", "c")));
    }

    /**
     * Reto Extra 1: servidor que devuelve el eco EN MAYÚSCULAS.
     * @return el mensaje en mayúsculas tal y como lo devuelve el servidor
     */
    public static String ecoEnMayusculas(String mensaje) {
        // GUÍA: teoría 29.1. Calca ecoDeUnMensaje, pero en el hilo servidor transforma antes de
        // responder: out.println(linea.toUpperCase()).
        // PISTA: el test manda "abc" y espera EXACTAMENTE "ABC" (Locale por defecto basta aquí).
        // CULTURA: el servidor decide la lógica; el cliente solo habla el protocolo. Eso es un endpoint.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoEnMayusculas");
    }

    /**
     * Reto Extra 2: el servidor responde la LONGITUD del mensaje recibido.
     * @return número de caracteres del mensaje (lo calcula el servidor y lo manda como texto)
     */
    public static int longitudDelMensaje(String mensaje) {
        // GUÍA: el servidor hace out.println(String.valueOf(linea.length())); el cliente lee la línea
        // y la convierte con Integer.parseInt(in.readLine()).
        // OJO: el test manda "hola" y espera 4; "" (cadena vacía) espera 0.
        // PISTA: reutiliza la estructura de ecoDeUnMensaje y cambia solo lo que el servidor escribe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudDelMensaje");
    }

    /**
     * Reto Extra 3: el servidor antepone un prefijo fijo al eco.
     * @return prefijo + mensaje, según lo arma el servidor
     */
    public static String ecoConPrefijo(String mensaje, String prefijo) {
        // GUÍA: en el servidor out.println(prefijo + linea). El prefijo debe viajar al hilo servidor:
        // como es una lambda, captura una variable final (prefijo ya lo es por ser parámetro).
        // OJO: el test usa prefijo "ECO:" y mensaje "hola" → espera "ECO:hola" (sin espacio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoConPrefijo");
    }

    /**
     * Reto Extra 4: el servidor atiende N conexiones secuenciales (una tras otra) y las cuenta.
     * @return número de conexiones atendidas (== n)
     */
    public static int cuentaConexionesAtendidas(int n) {
        // GUÍA: teoría 29.1 + 29.3. El hilo servidor hace un bucle: for (int i=0;i<n;i++){ accept();
        // atiende un mensaje; contador++ }. Cada vuelta acepta UN cliente nuevo.
        // 2. En el hilo actual abre n clientes de uno en uno (cada uno: new Socket, envía, cierra).
        // PISTA: un AtomicInteger no hace falta si el bucle servidor es secuencial, pero úsalo si
        // lees el contador desde otro hilo; el test solo comprueba el return == n.
        // OJO: el test llama cuentaConexionesAtendidas(5) y espera 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cuentaConexionesAtendidas");
    }

    /**
     * Reto Extra 5: el cliente detecta el fin de stream cuando el servidor cierra la conexión.
     * @return true si, tras cerrar el servidor, la lectura del cliente devuelve fin de stream
     */
    public static boolean clienteDetectaFinDeStream() {
        // GUÍA: teoría 29.1 ("EOF"). El servidor acepta y CIERRA el socket sin escribir nada.
        // En el cliente, in.readLine() devolverá null (fin de stream) o inputStream.read() devolverá -1.
        // return (in.readLine() == null);
        // CULTURA: -1 / null es como el peer te dice "he colgado"; gestionarlo bien es media RA3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clienteDetectaFinDeStream");
    }

    /**
     * Reto Extra 6: el servidor devuelve el eco con la cadena invertida.
     * @return el mensaje invertido tal y como lo responde el servidor
     */
    public static String ecoInvirtiendo(String mensaje) {
        // GUÍA: en el servidor out.println(new StringBuilder(linea).reverse().toString()).
        // OJO: el test manda "abc" y espera "cba".
        // PISTA: si reutilizaste un método privado para "levantar servidor con transformación",
        // aquí solo cambias la función de transformación (eso es lo que harías con un Function<String,String>).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoInvirtiendo");
    }

    /**
     * Reto Extra 7: comprobar que el puerto efímero asignado es válido.
     * @return true si new ServerSocket(0) recibe un puerto &gt; 0 del sistema operativo
     */
    public static boolean puertoEfimeroEsValido() {
        // GUÍA: una línea conceptual —
        // try (ServerSocket s = new ServerSocket(0)) { return s.getLocalPort() > 0; }
        // CULTURA: el puerto 0 significa "que el SO me asigne uno libre"; imprescindible en tests
        // para no chocar con puertos ocupados ni entre ejecuciones paralelas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puertoEfimeroEsValido");
    }

    /**
     * Reto Extra 8: eco respetando acentos y ñ con codificación UTF-8 explícita.
     * @return el eco del mensaje con caracteres no ASCII intactos
     */
    public static String ecoConUtf8(String mensaje) {
        // GUÍA: teoría 29.1 ("encoding sobre el socket"). El bug clásico: usar new InputStreamReader(in)
        // sin charset usa el del sistema y rompe la ñ (mojibake). Fuerza UTF-8 en AMBOS lados:
        // new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8) y el OutputStreamWriter igual.
        // OJO: el test manda "ñandú café" y espera esa MISMA cadena.
        // CULTURA: enlaza con b26 (encodings/Charset) — un socket es un stream de bytes, igual que un fichero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoConUtf8");
    }

    /**
     * Reto Extra 9: el servidor cuenta cuántas líneas recibe en una conexión antes del EOF.
     * @return número de líneas recibidas (== lineas.size())
     */
    public static int contarLineasRecibidas(List<String> lineas) {
        // GUÍA: el servidor hace while (in.readLine() != null) contador++; cuando el cliente CIERRA
        // su salida (o el socket), readLine() devuelve null y sale del bucle.
        // OJO/CUIDADO: si el cliente no cierra la escritura, el servidor se queda colgado esperando
        // más líneas → el @Timeout del test lo mataría. Cierra con cliente.shutdownOutput() o cliente.close().
        // PISTA: para devolver el contador al hilo principal usa un int[1] o AtomicInteger capturado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineasRecibidas");
    }

    /**
     * Reto Extra 10: un mensaje vacío produce un eco vacío (no null).
     * @return el eco de la cadena vacía, que debe ser ""
     */
    public static String ecoMensajeVacio() {
        // GUÍA: envía out.println("") (una línea vacía) y lee in.readLine(): debe devolver "" (cadena
        // vacía), NO null. null solo aparece en fin de stream; una línea vacía sigue siendo una línea.
        // OJO: el test espera exactamente "" con assertEquals("", ...).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ecoMensajeVacio");
    }
}
