package com.masterclass.api.b26_io;

/**
 * Ejercicio 213 · Ficheros temporales, {@code FileChannel} y {@code ByteBuffer}.
 *
 * <p>NIO (el "new I/O" original) trabaja con <b>canales</b> y <b>buffers</b> en vez de streams.
 * Un {@code FileChannel} lee/escribe bloques contra un {@code ByteBuffer} (un array de bytes con
 * posición/límite/capacidad). Es más cercano al hardware y permite trucos como mapear un fichero
 * a memoria. También ves los ficheros temporales, que ya usabas, como ciudadanos de primera clase.
 *
 * <p>La trampa estrella de los buffers: {@code flip()} antes de leer lo que acabas de escribir.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.7).
 */
public final class Ej213TempFilesAndChannels {

    private Ej213TempFilesAndChannels() {
    }

    /**
     * Escribe unos bytes con un {@code FileChannel} y los relee con otro (round-trip por canal).
     *
     * @param datos bytes a escribir
     * @return los bytes leídos (iguales a datos), o {@code null} si no se ha implementado
     */
    public static byte[] escribirConChannel(byte[] datos) {
        // TODO 1: crea un Path temporal.
        // TODO 2: escribe con try (FileChannel ch = FileChannel.open(p, WRITE)) { ch.write(ByteBuffer.wrap(datos)); }.
        // TODO 3: para leer, abre try (FileChannel ch = FileChannel.open(p, READ)) y un ByteBuffer.allocate((int) ch.size()).
        // TODO 4: ch.read(buf); luego buf.flip() para pasar de modo escritura a lectura; extrae los bytes (buf.get o buf.array()).
        // TODO 5: borra el Path y devuelve los bytes (maneja IOException).
        return null;
    }

    /**
     * Devuelve el tamaño del fichero consultando el {@code FileChannel}.
     *
     * @param datos bytes a escribir
     * @return tamaño del fichero (== datos.length), o -1 si no se ha implementado
     */
    public static long tamanoViaChannel(byte[] datos) {
        // TODO 6: escribe 'datos' a un Path temporal con un FileChannel.
        // TODO 7: abre un FileChannel en modo READ.
        // TODO 8: obtén el tamaño con ch.size().
        // TODO 9: borra el Path.
        // TODO 10: devuelve el tamaño.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("escribirConChannel = " + java.util.Arrays.toString(escribirConChannel(new byte[]{1, 2, 3})));
        System.out.println("tamanoViaChannel = " + tamanoViaChannel(new byte[42]));
    }

    /**
     * Reto Extra 1: un fichero temporal se crea y existe.
     * @return true si Files.createTempFile produce un fichero existente
     */
    public static boolean ficheroTemporalSeCrea() {
        // GUÍA: Path t = Files.createTempFile("ej213", ".tmp"); boolean ok = Files.exists(t);
        //   Files.deleteIfExists(t); return ok;
        // CULTURA: los temporales van a java.io.tmpdir; útiles para datos intermedios que no deben perdurar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ficheroTemporalSeCrea");
    }

    /**
     * Reto Extra 2: ByteBuffer.allocate reserva una capacidad fija.
     * @return la capacidad de un ByteBuffer.allocate(10) (debe ser 10)
     */
    public static int byteBufferCapacidad() {
        // GUÍA: return ByteBuffer.allocate(10).capacity();  // 10.
        // CULTURA: capacidad = tamaño total; posición y límite se mueven dentro de ella.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para byteBufferCapacidad");
    }

    /**
     * Reto Extra 3: flip() prepara el buffer para leer (posición a 0).
     * @return la posición del buffer tras hacer put de 3 bytes y flip() (debe ser 0)
     */
    public static int flipPonePosicionACero() {
        // GUÍA: ByteBuffer b = ByteBuffer.allocate(8); b.put((byte)1).put((byte)2).put((byte)3);
        //   b.flip(); return b.position();  // 0 (y el límite queda en 3).
        // OJO/CUIDADO: olvidar flip() es EL error de NIO: lees desde donde quedó la escritura y no ves nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flipPonePosicionACero");
    }

    /**
     * Reto Extra 4: escribir y leer por canal recupera los bytes (round-trip).
     * @return los bytes leídos del canal (iguales a datos) — reutiliza escribirConChannel
     */
    public static byte[] channelRoundTrip(byte[] datos) {
        // GUÍA: return escribirConChannel(datos);  // valida el flujo write->flip->read.
        // El test pasa {7,8,9}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para channelRoundTrip");
    }

    /**
     * Reto Extra 5: ByteBuffer.wrap envuelve un array existente sin copiarlo.
     * @return los bytes del array respaldado por el buffer (iguales a datos)
     */
    public static byte[] byteBufferWrap(byte[] datos) {
        // GUÍA: ByteBuffer b = ByteBuffer.wrap(datos); return b.array();  // mismo array de respaldo.
        // OJO: wrap NO copia; modificar el buffer modifica el array original (y viceversa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para byteBufferWrap");
    }

    /**
     * Reto Extra 6: la posición del canal avanza al escribir.
     * @return la posición del FileChannel tras escribir 'datos' (== datos.length)
     */
    public static long posicionDelChannelTrasEscribir(byte[] datos) {
        // GUÍA: try (FileChannel ch = FileChannel.open(p, WRITE)) { ch.write(ByteBuffer.wrap(datos)); return ch.position(); }
        // CULTURA: como RandomAccessFile, el canal tiene una posición; también puedes hacer ch.position(n) (seek).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para posicionDelChannelTrasEscribir");
    }

    /**
     * Reto Extra 7: remaining() indica cuántos bytes quedan por procesar.
     * @return los bytes restantes en un buffer de capacidad 10 tras poner 4 (debe ser 6)
     */
    public static int remainingTrasPut() {
        // GUÍA: ByteBuffer b = ByteBuffer.allocate(10); b.put(new byte[4]); return b.remaining();  // 10-4 = 6.
        // CULTURA: remaining() = limit - position; clave en bucles de lectura/escritura por canal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para remainingTrasPut");
    }

    /**
     * Reto Extra 8: mapear un fichero a memoria con FileChannel.map y leer un byte.
     * @return el primer byte del fichero leído del MappedByteBuffer (== datos[0])
     */
    public static int mapearFicheroAMemoria(byte[] datos) {
        // GUÍA: escribe 'datos'; try (FileChannel ch = FileChannel.open(p, READ)) {
        //   MappedByteBuffer m = ch.map(FileChannel.MapMode.READ_ONLY, 0, ch.size()); return m.get(0) & 0xff; }
        // CULTURA: memory-mapped I/O deja al SO traer las páginas del fichero a memoria bajo demanda;
        // brutal para ficheros enormes de acceso aleatorio (BD, índices). El test pasa {55,...} y espera 55.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearFicheroAMemoria");
    }

    /**
     * Reto Extra 9: truncar un canal reduce el tamaño del fichero.
     * @return el tamaño tras escribir 10 bytes y truncar a 4 (debe ser 4)
     */
    public static long truncarConChannel() {
        // GUÍA: escribe 10 bytes; try (FileChannel ch = FileChannel.open(p, WRITE)) { ch.truncate(4); }
        //   return Files.size(p);  // 4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para truncarConChannel");
    }

    /**
     * Reto Extra 10: transferir de un canal a otro sin pasar por un buffer manual.
     * @return número de bytes transferidos entre dos canales (== datos.length)
     */
    public static long transferirEntreChannels(byte[] datos) {
        // GUÍA: escribe 'datos' en origen; abre canal origen (READ) y destino (WRITE);
        //   long n = origen.transferTo(0, origen.size(), destino); return n;
        // CULTURA: transferTo/transferFrom pueden usar "zero-copy" del SO (sendfile); muy eficiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transferirEntreChannels");
    }

    /**
     * Reto Extra 11: clear() reinicia el buffer para reutilizarlo.
     * @return true si tras clear() la posición es 0 y el límite es la capacidad
     */
    public static boolean clearReseteaBuffer() {
        // GUÍA: ByteBuffer b = ByteBuffer.allocate(8); b.put(new byte[3]); b.clear();
        //   return b.position() == 0 && b.limit() == b.capacity();
        // OJO: clear() NO borra los datos, solo reposiciona los marcadores para sobrescribir.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clearReseteaBuffer");
    }

    /**
     * Reto Extra 12: un directorio temporal se crea y es un directorio.
     * @return true si Files.createTempDirectory produce un directorio existente
     */
    public static boolean directorioTemporalSeCrea() {
        // GUÍA: Path d = Files.createTempDirectory("ej213dir"); boolean ok = Files.isDirectory(d);
        //   Files.deleteIfExists(d); return ok;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para directorioTemporalSeCrea");
    }
}
