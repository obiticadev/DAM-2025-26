package com.masterclass.api.b28_proc;

import java.util.List;

/**
 * Ejercicio 229 · IPC por pipes: hablar con el proceso hijo por stdin/stdout.
 *
 * <p>La comunicación entre procesos (IPC) más sencilla son los <b>pipes</b> de los flujos
 * estándar: el padre <b>escribe</b> en el stdin del hijo ({@code Process.getOutputStream()}) y
 * <b>lee</b> de su stdout ({@code getInputStream()}). Es un diálogo petición/respuesta, primo
 * del de sockets (b29) pero entre dos procesos de la misma máquina. PSP RA1.f/g
 * (compartir información con subprocesos y obtener su resultado).
 *
 * <p>Clave: tras escribir hay que <b>flush/cerrar</b> el stdin del hijo, o el hijo se queda
 * esperando más entrada y nadie avanza (interbloqueo).
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.3).
 */
public final class Ej229ProcessPipesIPC {

    private Ej229ProcessPipesIPC() {
    }

    /**
     * Envía un texto al stdin del hijo ("upper") y devuelve lo que el hijo responde en mayúsculas.
     *
     * @param texto texto a enviar
     * @return el texto en mayúsculas devuelto por el hijo, o {@code null} si no se ha implementado
     */
    public static String enviarYRecibir(String texto) {
        // TODO 1: lanza ProcesoHijo.comando("upper") (lee una línea de stdin y la devuelve en mayúsculas).
        // TODO 2: escribe en su stdin: try (Writer w = new OutputStreamWriter(p.getOutputStream(), UTF_8)) { w.write(texto); w.write("\n"); }
        //         (al cerrar el Writer se hace flush y se cierra el stdin → el hijo ve la línea y el EOF).
        // TODO 3: lee la respuesta de su stdout con un BufferedReader (readLine).
        // TODO 4: espera con p.waitFor().
        // TODO 5: devuelve la línea leída (maneja IOException/InterruptedException).
        return null;
    }

    /**
     * Envía varias líneas al hijo ("lineas") y devuelve cuántas dice haber recibido.
     *
     * @param lineas líneas a enviar
     * @return número de líneas que contó el hijo (== lineas.size()), o -1 si no se ha implementado
     */
    public static int contarLineasEnviadas(List<String> lineas) {
        // TODO 6: lanza ProcesoHijo.comando("lineas") (cuenta las líneas de stdin hasta EOF).
        // TODO 7: escribe cada línea en su stdin (w.write(linea); w.write("\n")) y CIERRA el writer (EOF).
        // TODO 8: lee la respuesta (el número) de su stdout.
        // TODO 9: espera con waitFor() y parsea Integer.parseInt(respuesta).
        // TODO 10: devuelve el número (maneja excepciones).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("enviarYRecibir(\"hola\") = " + enviarYRecibir("hola"));
        System.out.println("contarLineasEnviadas([a,b,c]) = " + contarLineasEnviadas(List.of("a", "b", "c")));
    }

    /**
     * Reto Extra 1: escribir en el stdin del hijo y recibir su transformación.
     * @return "ABC" tras enviar "abc" al proceso "upper"
     */
    public static String enviarLineaYRecibirMayusculas() {
        // GUÍA: return enviarYRecibir("abc");  // el hijo responde "ABC".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enviarLineaYRecibirMayusculas");
    }

    /**
     * Reto Extra 2: cerrar el stdin del hijo le señala el fin de entrada (EOF).
     * @return true si, al cerrar el stdin, el hijo "lineas" termina y devuelve su conteo
     */
    public static boolean cerrarStdinSenialaEof() {
        // GUÍA: envía 2 líneas a "lineas" y CIERRA el stdin; el hijo detecta EOF, cuenta 2 y termina.
        //   return contarLineasEnviadas(List.of("a","b")) == 2.
        // OJO/CUIDADO: sin cerrar el stdin, el hijo espera más líneas para siempre → cuelgue (timeout).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cerrarStdinSenialaEof");
    }

    /**
     * Reto Extra 3: escribir el stdin con un PrintWriter (autoFlush).
     * @return "DATO" enviando "dato" con PrintWriter al proceso "upper"
     */
    public static String ipcConPrintWriter() {
        // GUÍA: PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), UTF_8), true);
        //   pw.println("dato"); pw.close();  // close() cierra el stdin (EOF) tras el flush del autoFlush.
        //   lee la respuesta "DATO".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ipcConPrintWriter");
    }

    /**
     * Reto Extra 4: enviar cinco líneas y que el hijo cuente cinco.
     * @return número de líneas contadas por el hijo al enviar 5 (== 5)
     */
    public static int enviarCincoLineas() {
        // GUÍA: return contarLineasEnviadas(List.of("1","2","3","4","5"));  // 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enviarCincoLineas");
    }

    /**
     * Reto Extra 5: una línea vacía sigue siendo una línea.
     * @return número de líneas al enviar una sola línea vacía (== 1)
     */
    public static int lineaVaciaCuentaComoLinea() {
        // GUÍA: return contarLineasEnviadas(List.of(""));  // 1 (una línea vacía, no cero).
        // OJO: el EOF (cerrar stdin) NO es una línea; "" sí lo es.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lineaVaciaCuentaComoLinea");
    }

    /**
     * Reto Extra 6: round-trip mayúsculas con otro valor.
     * @return "TEST" enviando "test" al proceso "upper"
     */
    public static String roundTripUpper() {
        // GUÍA: return enviarYRecibir("test");  // "TEST".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para roundTripUpper");
    }

    /**
     * Reto Extra 7: escribir bytes crudos en el stdin del hijo.
     * @return "BYTES" enviando los bytes de "bytes\n" directamente al OutputStream
     */
    public static String escribirBytesEnStdin() {
        // GUÍA: p.getOutputStream().write("bytes\n".getBytes(UTF_8)); p.getOutputStream().close();
        //   el hijo "upper" lee la línea y responde "BYTES". El stdin es un OutputStream normal (b26).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirBytesEnStdin");
    }

    /**
     * Reto Extra 8: leer la respuesta y luego esperar el final con código 0.
     * @return el código de salida tras un intercambio completo con "upper" (debe ser 0)
     */
    public static int intercambioTerminaConCero() {
        // GUÍA: haz un enviarYRecibir completo pero devuelve el código de waitFor() en vez del texto.
        //   El proceso "upper" termina con 0 tras responder.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intercambioTerminaConCero");
    }

    /**
     * Reto Extra 9: el pipe transporta una línea larga completa.
     * @return la longitud de la respuesta al enviar una línea de 1000 caracteres a "upper" (== 1000)
     */
    public static int ipcLineaLarga() {
        // GUÍA: envía "a".repeat(1000) a "upper"; la respuesta "A".repeat(1000) mide 1000.
        //   return enviarYRecibir("a".repeat(1000)).length();
        // CULTURA: como en sockets (b29), el pipe es un flujo; readLine reensambla hasta el '\n'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ipcLineaLarga");
    }

    /**
     * Reto Extra 10: enviar tres líneas y recibir el conteo (reutiliza el core).
     * @return número de líneas al enviar ["x","y","z"] (== 3)
     */
    public static int contarTresLineas() {
        // GUÍA: return contarLineasEnviadas(List.of("x","y","z"));  // 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarTresLineas");
    }
}
