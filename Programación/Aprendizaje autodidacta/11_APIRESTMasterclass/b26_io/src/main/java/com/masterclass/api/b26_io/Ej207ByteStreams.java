package com.masterclass.api.b26_io;

/**
 * Ejercicio 207 · Flujos de bytes: {@code InputStream} / {@code OutputStream}.
 *
 * <p>La base de TODA la E/S en Java es el byte. Un {@code OutputStream} escribe bytes a un
 * destino (fichero, red, memoria) y un {@code InputStream} los lee de una fuente. Da igual el
 * destino: la API es la misma (polimorfismo). Aquí trabajas con ficheros
 * ({@code FileInputStream}/{@code FileOutputStream}) y memoria ({@code ByteArrayOutputStream}),
 * con buffers y {@code try-with-resources}. Es la materia prima de AD RA1 (acceso a ficheros).
 *
 * <p>Para ser autocontenidos y deterministas, los métodos usan ficheros temporales que crean y
 * borran ellos mismos.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.1).
 */
public final class Ej207ByteStreams {

    private Ej207ByteStreams() {
    }

    /**
     * Escribe unos bytes a un fichero temporal y los vuelve a leer (round-trip binario).
     *
     * @param datos bytes a escribir
     * @return los bytes leídos del fichero (iguales a datos), o {@code null} si no se ha implementado
     */
    public static byte[] copiarBytes(byte[] datos) {
        // TODO 1: crea un fichero temporal con File.createTempFile("ej207", ".bin") (recuerda borrarlo al final).
        // TODO 2: escribe con try (OutputStream os = new FileOutputStream(tmp)) { os.write(datos); }.
        // TODO 3: lee con try (InputStream is = new FileInputStream(tmp)) { ... }.
        // TODO 4: dentro, usa is.readAllBytes() (JDK 9+) para obtener todos los bytes de golpe.
        // TODO 5: borra el temporal (tmp.delete()) y devuelve los bytes leídos (maneja IOException).
        return null;
    }

    /**
     * Cuenta cuántos bytes contiene un fichero leyéndolo con un buffer.
     *
     * @param datos bytes a escribir y luego contar
     * @return número de bytes leídos (== datos.length), o -1 si no se ha implementado
     */
    public static long contarBytes(byte[] datos) {
        // TODO 6: escribe 'datos' a un fichero temporal (como arriba).
        // TODO 7: abre un FileInputStream y prepara un byte[] buffer (p.ej. 8192) y un contador long.
        // TODO 8: bucle: int n; while ((n = is.read(buffer)) != -1) { total += n; }  (read devuelve -1 en EOF).
        // TODO 9: borra el temporal.
        // TODO 10: devuelve el total contado.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("copiarBytes = " + java.util.Arrays.toString(copiarBytes(new byte[]{1, 2, 3})));
        System.out.println("contarBytes = " + contarBytes(new byte[100]));
    }

    /**
     * Reto Extra 1: copiar de un stream a otro byte a byte detectando el EOF (-1).
     * Formaliza el comportamiento esperado de copiar de un stream a otro byte a byte detectando el EOF
     * (-1) dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes copiados (igual a datos.length)
     */
    public static long copiarDetectandoEof(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarDetectandoEof");
    }

    /**
     * Reto Extra 2: copiar usando un buffer de 1024 bytes.
     * Formaliza el comportamiento esperado de copiar usando un buffer de 1024 bytes dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes copiados con buffer (igual a datos.length)
     */
    public static long copiarConBuffer1024(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConBuffer1024");
    }

    /**
     * Reto Extra 3: volcar bytes a memoria con ByteArrayOutputStream.
     * Formaliza el comportamiento esperado de volcar bytes a memoria con ByteArrayOutputStream dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes acumulados en memoria (igual a datos.length)
     */
    public static int volcarAMemoria(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volcarAMemoria");
    }

    /**
     * Reto Extra 4: el modo append añade al final en vez de sobrescribir.
     * Formaliza el comportamiento esperado de el modo append añade al final en vez de sobrescribir dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return tamaño del fichero tras escribir 'datos' DOS veces en modo append (igual a 2*datos.length)
     */
    public static long appendDuplicaTamano(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para appendDuplicaTamano");
    }

    /**
     * Reto Extra 5: dos ficheros con el mismo contenido son iguales byte a byte.
     * Formaliza el comportamiento esperado de dos ficheros con el mismo contenido son iguales byte a byte
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si dos ficheros escritos con los mismos bytes tienen contenido idéntico
     */
    public static boolean dosFicherosIgualesPorContenido(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosFicherosIgualesPorContenido");
    }

    /**
     * Reto Extra 6: escribir y leer un único byte.
     * Formaliza el comportamiento esperado de escribir y leer un único byte dentro de una operación de E/S
     * pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el byte leído (como int 0..255), igual al escrito
     */
    public static int escribirYLeerUnByte(int valor) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerUnByte");
    }

    /**
     * Reto Extra 7: copiar un stream a otro con transferTo (JDK 9+).
     * Formaliza el comportamiento esperado de copiar un stream a otro con transferTo (JDK 9+) dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes transferidos (igual a datos.length)
     */
    public static long copiarConTransferTo(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConTransferTo");
    }

    /**
     * Reto Extra 8: un BufferedOutputStream/BufferedInputStream envuelve al stream crudo.
     * Formaliza el comportamiento esperado de un BufferedOutputStream/BufferedInputStream envuelve al
     * stream crudo dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos a través de streams con buffer (iguales a datos)
     */
    public static byte[] copiarConBufferedStreams(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConBufferedStreams");
    }

    /**
     * Reto Extra 9: un fichero vacío tiene 0 bytes.
     * Formaliza el comportamiento esperado de un fichero vacío tiene 0 bytes dentro de una operación de
     * E/S pequeña y verificable.
     *
     * @return tamaño de un fichero al que no se escribe nada (esperado: 0)
     */
    public static long ficheroVacioTieneCeroBytes() {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ficheroVacioTieneCeroBytes");
    }

    /**
     * Reto Extra 10: flush fuerza el envío del buffer antes de cerrar.
     * Formaliza el comportamiento esperado de flush fuerza el envío del buffer antes de cerrar dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos tras hacer flush explícito (iguales a datos)
     */
    public static byte[] flushAseguraEscritura(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flushAseguraEscritura");
    }

    /**
     * Reto Extra 11: leer más allá del final devuelve -1 repetidamente.
     * Formaliza el comportamiento esperado de leer más allá del final devuelve -1 repetidamente dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si, tras leer todo, una lectura extra con read() devuelve -1
     */
    public static boolean leerTrasEofDevuelveMenosUno(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerTrasEofDevuelveMenosUno");
    }

    /**
     * Reto Extra 12: try-with-resources cierra el stream automáticamente.
     * Formaliza el comportamiento esperado de try-with-resources cierra el stream automáticamente dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si, tras el bloque try-with-resources, el stream quedó cerrado
     */
    public static boolean tryWithResourcesCierraStream(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream, escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tryWithResourcesCierraStream");
    }
}
