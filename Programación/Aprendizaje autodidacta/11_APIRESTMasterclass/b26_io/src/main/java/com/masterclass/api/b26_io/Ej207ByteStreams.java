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
     * @return número de bytes copiados (== datos.length)
     */
    public static long copiarDetectandoEof(byte[] datos) {
        // GUÍA: teoría 26.1. int b; while ((b = in.read()) != -1) { out.write(b); contador++; }
        // OJO: read() sin argumentos devuelve un int 0..255, o -1 en fin de stream (NUNCA un byte).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarDetectandoEof");
    }

    /**
     * Reto Extra 2: copiar usando un buffer de 1024 bytes.
     * @return número de bytes copiados con buffer (== datos.length)
     */
    public static long copiarConBuffer1024(byte[] datos) {
        // GUÍA: byte[] buf = new byte[1024]; int n; while ((n = in.read(buf)) != -1) out.write(buf, 0, n);
        // OJO/CUIDADO: escribe out.write(buf, 0, n), NO out.write(buf): el buffer puede ir medio lleno
        // en la última vuelta y escribirías basura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConBuffer1024");
    }

    /**
     * Reto Extra 3: volcar bytes a memoria con ByteArrayOutputStream.
     * @return número de bytes acumulados en memoria (== datos.length)
     */
    public static int volcarAMemoria(byte[] datos) {
        // GUÍA: ByteArrayOutputStream bos = new ByteArrayOutputStream(); bos.write(datos);
        //   return bos.toByteArray().length;  (o bos.size()).
        // CULTURA: BAOS es un OutputStream que escribe en un array que crece solo; útil para construir
        // bytes sin tocar disco (p.ej. serializar a memoria, ver Ej210/b29·Ej238).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volcarAMemoria");
    }

    /**
     * Reto Extra 4: el modo append añade al final en vez de sobrescribir.
     * @return tamaño del fichero tras escribir 'datos' DOS veces en modo append (== 2*datos.length)
     */
    public static long appendDuplicaTamano(byte[] datos) {
        // GUÍA: new FileOutputStream(tmp, true) abre en modo APPEND (el segundo arg). Escribe 'datos',
        // cierra, vuelve a abrir en append y escribe otra vez; el fichero mide 2*datos.length.
        // OJO: sin el 'true', el segundo FileOutputStream TRUNCA el fichero (lo deja en datos.length).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para appendDuplicaTamano");
    }

    /**
     * Reto Extra 5: dos ficheros con el mismo contenido son iguales byte a byte.
     * @return true si dos ficheros escritos con los mismos bytes tienen contenido idéntico
     */
    public static boolean dosFicherosIgualesPorContenido(byte[] datos) {
        // GUÍA: escribe 'datos' en dos temporales, léelos con readAllBytes y compara con Arrays.equals.
        // (En NIO existe Files.mismatch, ver Ej212; aquí hazlo con streams.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dosFicherosIgualesPorContenido");
    }

    /**
     * Reto Extra 6: escribir y leer un único byte.
     * @return el byte leído (como int 0..255), igual al escrito
     */
    public static int escribirYLeerUnByte(int valor) {
        // GUÍA: out.write(valor); ... int leido = in.read();  return leido;
        // OJO: write(int) escribe solo el byte bajo (valor & 0xff). El test usa 65 ('A') y espera 65.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerUnByte");
    }

    /**
     * Reto Extra 7: copiar un stream a otro con transferTo (JDK 9+).
     * @return número de bytes transferidos (== datos.length)
     */
    public static long copiarConTransferTo(byte[] datos) {
        // GUÍA: try (InputStream in = ...; OutputStream out = ...) { return in.transferTo(out); }
        // transferTo copia todo el stream y devuelve el número de bytes; sustituye al bucle manual.
        // CULTURA: es la forma moderna y concisa; por debajo usa un buffer interno.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConTransferTo");
    }

    /**
     * Reto Extra 8: un BufferedOutputStream/BufferedInputStream envuelve al stream crudo.
     * @return los bytes leídos a través de streams con buffer (iguales a datos)
     */
    public static byte[] copiarConBufferedStreams(byte[] datos) {
        // GUÍA: envuelve: new BufferedOutputStream(new FileOutputStream(tmp)) y al leer
        // new BufferedInputStream(new FileInputStream(tmp)). El buffer reduce llamadas al SO.
        // OJO: con BufferedOutputStream hay que cerrar (o flush) para que vacíe su buffer al fichero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarConBufferedStreams");
    }

    /**
     * Reto Extra 9: un fichero vacío tiene 0 bytes.
     * @return tamaño de un fichero al que no se escribe nada (debe ser 0)
     */
    public static long ficheroVacioTieneCeroBytes() {
        // GUÍA: crea un temporal, ábrelo y ciérralo sin escribir; return tmp.length();  // 0
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ficheroVacioTieneCeroBytes");
    }

    /**
     * Reto Extra 10: flush fuerza el envío del buffer antes de cerrar.
     * @return los bytes leídos tras hacer flush explícito (iguales a datos)
     */
    public static byte[] flushAseguraEscritura(byte[] datos) {
        // GUÍA: con un BufferedOutputStream, escribe 'datos', llama a os.flush() y LUEGO lee el fichero
        // (sin haber cerrado todavía). Sin flush, los bytes podrían seguir en el buffer en memoria.
        // CULTURA: close() hace flush implícito; flush() sirve cuando quieres asegurar a mitad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flushAseguraEscritura");
    }

    /**
     * Reto Extra 11: leer más allá del final devuelve -1 repetidamente.
     * @return true si, tras leer todo, una lectura extra con read() devuelve -1
     */
    public static boolean leerTrasEofDevuelveMenosUno(byte[] datos) {
        // GUÍA: lee todos los bytes; luego una llamada más a in.read() debe devolver -1 (sigue en EOF).
        // OJO: -1 es la señal universal de fin de stream en java.io; no es un byte válido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerTrasEofDevuelveMenosUno");
    }

    /**
     * Reto Extra 12: try-with-resources cierra el stream automáticamente.
     * @return true si, tras el bloque try-with-resources, el stream quedó cerrado
     */
    public static boolean tryWithResourcesCierraStream(byte[] datos) {
        // GUÍA: usa una subclase para observarlo, o un FileInputStream is; tras try-with-resources,
        // intentar is.read() lanza IOException ("Stream Closed") → captúrala y return true.
        // PISTA: try (FileInputStream is = new FileInputStream(tmp)) { ref = is; }  luego ref.read().
        // CULTURA: try-with-resources llama a close() siempre (incluso con excepción); evita fugas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tryWithResourcesCierraStream");
    }
}
