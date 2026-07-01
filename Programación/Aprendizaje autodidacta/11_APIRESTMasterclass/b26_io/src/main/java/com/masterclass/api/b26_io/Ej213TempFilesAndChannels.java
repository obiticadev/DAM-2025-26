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
     * Formaliza el comportamiento esperado de un fichero temporal se crea y existe dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @return true si Files.createTempFile produce un fichero existente
     */
    public static boolean ficheroTemporalSeCrea() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ficheroTemporalSeCrea");
    }

    /**
     * Reto Extra 2: ByteBuffer.allocate reserva una capacidad fija.
     * Formaliza el comportamiento esperado de byteBuffer.allocate reserva una capacidad fija dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return la capacidad de un ByteBuffer.allocate(10) (esperado: 10)
     */
    public static int byteBufferCapacidad() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para byteBufferCapacidad");
    }

    /**
     * Reto Extra 3: flip() prepara el buffer para leer (posición a 0).
     * Formaliza el comportamiento esperado de flip() prepara el buffer para leer (posición a 0) dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return la posición del buffer tras hacer put de 3 bytes y flip() (esperado: 0)
     */
    public static int flipPonePosicionACero() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flipPonePosicionACero");
    }

    /**
     * Reto Extra 4: escribir y leer por canal recupera los bytes (round-trip).
     * Formaliza el comportamiento esperado de escribir y leer por canal recupera los bytes (round-trip)
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos del canal (iguales a datos) — reutiliza escribirConChannel
     */
    public static byte[] channelRoundTrip(byte[] datos) {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para channelRoundTrip");
    }

    /**
     * Reto Extra 5: ByteBuffer.wrap envuelve un array existente sin copiarlo.
     * Formaliza el comportamiento esperado de byteBuffer.wrap envuelve un array existente sin copiarlo
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes del array respaldado por el buffer (iguales a datos)
     */
    public static byte[] byteBufferWrap(byte[] datos) {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para byteBufferWrap");
    }

    /**
     * Reto Extra 6: la posición del canal avanza al escribir.
     * Formaliza el comportamiento esperado de la posición del canal avanza al escribir dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return la posición del FileChannel tras escribir 'datos' (igual a datos.length)
     */
    public static long posicionDelChannelTrasEscribir(byte[] datos) {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para posicionDelChannelTrasEscribir");
    }

    /**
     * Reto Extra 7: remaining() indica cuántos bytes quedan por procesar.
     * Formaliza el comportamiento esperado de remaining() indica cuántos bytes quedan por procesar dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @return los bytes restantes en un buffer de capacidad 10 tras poner 4 (esperado: 6)
     */
    public static int remainingTrasPut() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para remainingTrasPut");
    }

    /**
     * Reto Extra 8: mapear un fichero a memoria con FileChannel.map y leer un byte.
     * Formaliza el comportamiento esperado de mapear un fichero a memoria con FileChannel.map y leer un
     * byte dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return el primer byte del fichero leído del MappedByteBuffer (igual a datos[0])
     */
    public static int mapearFicheroAMemoria(byte[] datos) {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearFicheroAMemoria");
    }

    /**
     * Reto Extra 9: truncar un canal reduce el tamaño del fichero.
     * Formaliza el comportamiento esperado de truncar un canal reduce el tamaño del fichero dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return el tamaño tras escribir 10 bytes y truncar a 4 (esperado: 4)
     */
    public static long truncarConChannel() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para truncarConChannel");
    }

    /**
     * Reto Extra 10: transferir de un canal a otro sin pasar por un buffer manual.
     * Formaliza el comportamiento esperado de transferir de un canal a otro sin pasar por un buffer manual
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes transferidos entre dos canales (igual a datos.length)
     */
    public static long transferirEntreChannels(byte[] datos) {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transferirEntreChannels");
    }

    /**
     * Reto Extra 11: clear() reinicia el buffer para reutilizarlo.
     * Formaliza el comportamiento esperado de clear() reinicia el buffer para reutilizarlo dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return true si tras clear() la posición es 0 y el límite es la capacidad
     */
    public static boolean clearReseteaBuffer() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clearReseteaBuffer");
    }

    /**
     * Reto Extra 12: un directorio temporal se crea y es un directorio.
     * Formaliza el comportamiento esperado de un directorio temporal se crea y es un directorio dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si Files.createTempDirectory produce un directorio existente
     */
    public static boolean directorioTemporalSeCrea() {
        // GUÍA: Distingue capacidad, posición y límite en los buffers, y trata el canal como una vista posicionada del
        // fichero. El cierre y la limpieza del temporal siguen siendo parte del ejercicio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para directorioTemporalSeCrea");
    }
}
