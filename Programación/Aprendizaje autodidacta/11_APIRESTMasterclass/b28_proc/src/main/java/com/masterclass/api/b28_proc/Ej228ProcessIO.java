package com.masterclass.api.b28_proc;

/**
 * Ejercicio 228 · E/S del proceso: leer su salida y su error.
 *
 * <p>Un proceso hijo tiene tres flujos estándar: <b>stdin</b> (entrada), <b>stdout</b> (salida)
 * y <b>stderr</b> (errores). Desde el padre, {@code Process.getInputStream()} lee lo que el hijo
 * escribe por stdout, {@code getErrorStream()} lo de stderr y {@code getOutputStream()} escribe
 * en su stdin. Capturar la salida de un proceso es la operación más común de PSP RA1.
 *
 * <p>OJO de portabilidad: solo se intercambia texto ASCII entre procesos para no depender del
 * encoding de consola de cada SO.
 *
 * <p>Teoría: {@code teoria/28_Multiproceso_IPC.md} (sección 28.2).
 */
public final class Ej228ProcessIO {

    private Ej228ProcessIO() {
    }

    /**
     * Lanza el hijo con "echo &lt;texto&gt;" y devuelve lo que escribe por stdout.
     *
     * @param texto texto a imprimir por el hijo
     * @return la línea leída de stdout (== texto), o {@code null} si no se ha implementado
     */
    public static String ejecutarYLeerSalida(String texto) {
        // TODO 1: ProcessBuilder pb = new ProcessBuilder(ProcesoHijo.comando("echo", texto)).
        // TODO 2: arranca con Process p = pb.start().
        // TODO 3: lee su stdout: try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), UTF_8))) { ... }.
        // TODO 4: String linea = br.readLine();  (la única línea que imprime el hijo).
        // TODO 5: espera con p.waitFor() y devuelve la línea (maneja IOException/InterruptedException).
        return null;
    }

    /**
     * Lanza el hijo con "err &lt;texto&gt;" y devuelve lo que escribe por stderr.
     *
     * @param texto texto a imprimir por stderr
     * @return la línea leída de stderr (== texto), o {@code null} si no se ha implementado
     */
    public static String leerStderr(String texto) {
        // TODO 6: lanza ProcesoHijo.comando("err", texto).
        // TODO 7: lee del flujo de ERROR: p.getErrorStream() (no getInputStream).
        // TODO 8: envuélvelo en BufferedReader y lee la línea.
        // TODO 9: espera con waitFor().
        // TODO 10: devuelve la línea de stderr.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("ejecutarYLeerSalida(\"hola\") = " + ejecutarYLeerSalida("hola"));
        System.out.println("leerStderr(\"fallo\") = " + leerStderr("fallo"));
    }

    /**
     * Reto Extra 1: capturar el resultado de un cálculo del hijo.
     * @return la salida de "suma 2 3" (debe ser "5")
     */
    public static String capturarSuma() {
        // GUÍA: lanza ProcesoHijo.comando("suma","2","3"), lee stdout: "5".
        // CULTURA: stdout es el "canal de retorno" clásico de un proceso (devuelve datos por texto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capturarSuma");
    }

    /**
     * Reto Extra 2: stdout y stderr son flujos SEPARADOS.
     * @return true si "outerr" da "OUT" por stdout y "ERR" por stderr
     */
    public static boolean stdoutYStderrSeparados() {
        // GUÍA: lanza ProcesoHijo.comando("outerr"); lee p.getInputStream() ("OUT") y p.getErrorStream() ("ERR").
        //   return out.equals("OUT") && err.equals("ERR").
        // OJO: lee ambos flujos (idealmente en hilos o uno tras otro); si el hijo escribiera mucho en
        // stderr sin que nadie lo lea, podría bloquearse (buffer lleno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stdoutYStderrSeparados");
    }

    /**
     * Reto Extra 3: redirectErrorStream(true) mezcla stderr dentro de stdout.
     * @return número de líneas leídas de stdout cuando "outerr" se mezcla (debe ser 2)
     */
    public static int redirectErrorStreamMezcla() {
        // GUÍA: pb.redirectErrorStream(true); ahora "OUT" y "ERR" llegan AMBOS por getInputStream().
        //   cuenta las líneas: 2.
        // CULTURA: útil para logs unificados; evita además el riesgo de bloqueo por leer dos flujos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para redirectErrorStreamMezcla");
    }

    /**
     * Reto Extra 4: leer toda la salida con readAllBytes.
     * @return true si la salida de "echo hola" leída con readAllBytes contiene "hola"
     */
    public static boolean leerConReadAllBytes() {
        // GUÍA: byte[] bs = p.getInputStream().readAllBytes(); String s = new String(bs, UTF_8);
        //   return s.contains("hola");  (incluye el salto de línea final).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerConReadAllBytes");
    }

    /**
     * Reto Extra 5: si el hijo solo escribe en stderr, su stdout está vacío.
     * @return true si tras "err algo" la lectura de stdout devuelve fin de stream (línea null)
     */
    public static boolean stdoutVacioSiSoloStderr() {
        // GUÍA: lanza ProcesoHijo.comando("err","algo"); lee p.getInputStream().readLine() -> null (vacío).
        //   return br.readLine() == null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stdoutVacioSiSoloStderr");
    }

    /**
     * Reto Extra 6: inheritIO conecta los flujos del hijo a los del padre sin colgar.
     * @return true si un proceso con inheritIO() termina con código 0
     */
    public static boolean inheritIONoCuelga() {
        // GUÍA: pb.inheritIO(); Process p = pb.start(); return p.waitFor() == 0;
        // CULTURA: inheritIO vuelca la salida del hijo directamente a la consola del padre (no la capturas);
        // cómodo para procesos interactivos o cuando no te interesa leer la salida en código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inheritIONoCuelga");
    }

    /**
     * Reto Extra 7: leer la salida con un BufferedReader sobre el pipe.
     * @return la primera línea de "echo pipe" leída con BufferedReader (== "pipe")
     */
    public static String pipeConBufferedReader() {
        // GUÍA: el getInputStream() del proceso ES un pipe del SO; se lee como cualquier stream (b26).
        //   reutiliza ejecutarYLeerSalida("pipe") si quieres: return ejecutarYLeerSalida("pipe").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pipeConBufferedReader");
    }

    /**
     * Reto Extra 8: leer la salida y LUEGO esperar el final.
     * @return el código de salida tras consumir el stdout de "echo x" (debe ser 0)
     */
    public static int leerSalidaYLuegoWaitFor() {
        // GUÍA: lee primero todo el stdout y DESPUÉS llama a waitFor(); return el código (0).
        // OJO/CUIDADO: lee la salida ANTES de waitFor() (o en paralelo); si el hijo llena el buffer del
        // pipe y nadie lee, se bloquea y waitFor() no vuelve nunca (interbloqueo clásico de procesos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerSalidaYLuegoWaitFor");
    }

    /**
     * Reto Extra 9: redirigir la salida del hijo a un fichero.
     * @return true si tras redirectOutput(fichero), el fichero contiene "redir"
     */
    public static boolean redirectOutputAFichero() {
        // GUÍA: Path tmp = Files.createTempFile(...); pb.redirectOutput(tmp.toFile());
        //   lanza ProcesoHijo.comando("echo","redir"); waitFor(); String s = Files.readString(tmp);
        //   return s.contains("redir");  (borra el temporal).
        // CULTURA: redirectOutput/redirectInput/redirectError conectan flujos a ficheros sin leerlos en código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para redirectOutputAFichero");
    }

    /**
     * Reto Extra 10: leer la salida con InputStreamReader y un charset explícito.
     * @return la salida de "echo dato" leída con InputStreamReader UTF-8 (== "dato")
     */
    public static String lecturaConInputStreamReaderUtf8() {
        // GUÍA: new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8)).readLine().
        // CULTURA: igual que en ficheros (b26·208), conviene fijar el charset al leer la salida de un proceso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lecturaConInputStreamReaderUtf8");
    }
}
