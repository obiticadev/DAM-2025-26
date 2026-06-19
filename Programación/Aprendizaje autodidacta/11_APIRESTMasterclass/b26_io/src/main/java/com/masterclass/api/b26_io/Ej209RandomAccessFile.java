package com.masterclass.api.b26_io;

/**
 * Ejercicio 209 · Acceso aleatorio: {@code RandomAccessFile}.
 *
 * <p>Los streams (207/208) son <b>secuenciales</b>: lees/escribes de principio a fin. Un
 * {@code RandomAccessFile} permite <b>saltar</b> a cualquier posición con {@code seek(offset)}
 * y leer/escribir ahí, como un array en disco. Es la base de los ficheros de registros de
 * tamaño fijo, los índices y las bases de datos (AD RA1.b, "acceso secuencial vs aleatorio").
 *
 * <p>Mantiene un "puntero de fichero" que avanza al leer/escribir; {@code seek} lo mueve a mano,
 * {@code length()} da el tamaño y {@code setLength()} trunca o extiende.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.3).
 */
public final class Ej209RandomAccessFile {

    private Ej209RandomAccessFile() {
    }

    /**
     * Escribe unos bytes y lee el que está en un offset concreto (acceso aleatorio).
     *
     * @param datos  bytes a escribir
     * @param offset posición a leer (0-based)
     * @return el byte en esa posición (0..255), o -1 si no se ha implementado
     */
    public static int leerEnOffset(byte[] datos, int offset) {
        // TODO 1: crea un fichero temporal y ábrelo con new RandomAccessFile(tmp, "rw").
        // TODO 2: escribe todos los bytes con raf.write(datos).
        // TODO 3: salta a la posición con raf.seek(offset).
        // TODO 4: lee un byte con raf.read() (devuelve 0..255 o -1 en EOF).
        // TODO 5: cierra, borra el temporal y devuelve el byte leído (maneja IOException).
        return -1;
    }

    /**
     * Actualiza un byte EN SITIO (sin reescribir el resto) y devuelve el fichero completo.
     *
     * @param datos  bytes iniciales
     * @param offset posición a modificar
     * @param nuevo  nuevo valor del byte en esa posición
     * @return el contenido completo tras la modificación, o {@code null} si no se ha implementado
     */
    public static byte[] actualizarEnSitio(byte[] datos, int offset, int nuevo) {
        // TODO 6: escribe 'datos' en un RandomAccessFile "rw".
        // TODO 7: raf.seek(offset); raf.write(nuevo);  (sobrescribe solo ese byte, sin tocar los demás).
        // TODO 8: raf.seek(0) para volver al principio.
        // TODO 9: lee todo el contenido (byte[] buf = new byte[(int) raf.length()]; raf.readFully(buf)).
        // TODO 10: cierra, borra y devuelve buf.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("leerEnOffset = " + leerEnOffset(new byte[]{10, 20, 30, 40}, 2));
        System.out.println("actualizarEnSitio = " + java.util.Arrays.toString(actualizarEnSitio(new byte[]{1, 2, 3}, 1, 9)));
    }

    /**
     * Reto Extra 1: length() devuelve el tamaño del fichero.
     * @return tamaño en bytes tras escribir 'datos' (== datos.length)
     */
    public static long longitudDelFichero(byte[] datos) {
        // GUÍA: escribe 'datos' y return raf.length();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudDelFichero");
    }

    /**
     * Reto Extra 2: seek más allá del final + write EXTIENDE el fichero (deja un hueco de ceros).
     * @return tamaño del fichero tras seek(10) y escribir 1 byte (debe ser 11)
     */
    public static long seekMasAllaExtiende() {
        // GUÍA: RandomAccessFile vacío; raf.seek(10); raf.write(0xFF); return raf.length();  // 11.
        // CULTURA: los bytes entre 0 y 10 quedan a cero (un "agujero"); útil para reservar espacio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seekMasAllaExtiende");
    }

    /**
     * Reto Extra 3: setLength() trunca el fichero a un tamaño menor.
     * @return tamaño tras escribir 10 bytes y hacer setLength(4) (debe ser 4)
     */
    public static long setLengthTrunca() {
        // GUÍA: escribe new byte[10]; raf.setLength(4); return raf.length();  // 4 (se descartan 6 bytes).
        // CULTURA: setLength también puede EXTENDER (rellenando con ceros).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para setLengthTrunca");
    }

    /**
     * Reto Extra 4: writeInt/readInt escriben y leen un entero (4 bytes) en cualquier posición.
     * @return el entero leído tras escribirlo (== valor)
     */
    public static int escribirYLeerInt(int valor) {
        // GUÍA: raf.writeInt(valor); raf.seek(0); return raf.readInt();
        // OJO: writeInt escribe 4 bytes en big-endian; por eso un int siempre ocupa lo mismo (registro fijo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerInt");
    }

    /**
     * Reto Extra 5: writeUTF/readUTF guardan y recuperan una cadena con su longitud.
     * @return la cadena leída tras escribirla (== texto)
     */
    public static String escribirYLeerUTF(String texto) {
        // GUÍA: raf.writeUTF(texto); raf.seek(0); return raf.readUTF();
        // CULTURA: writeUTF antepone 2 bytes con la longitud (formato "UTF modificado"); así readUTF
        // sabe cuántos bytes leer. Es un mini-protocolo de longitud-prefijada (compárese con b29).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerUTF");
    }

    /**
     * Reto Extra 6: leer un fichero en orden INVERSO usando seek.
     * @return los bytes leídos del último al primero (inverso de 'datos')
     */
    public static byte[] leerInverso(byte[] datos) {
        // GUÍA: for (long p = raf.length()-1; p >= 0; p--) { raf.seek(p); out[i++] = (byte) raf.read(); }
        // Demuestra el acceso aleatorio: imposible de hacer así con un InputStream secuencial.
        // OJO: el test pasa {1,2,3} y espera {3,2,1}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerInverso");
    }

    /**
     * Reto Extra 7: el puntero de fichero avanza al leer.
     * @return la posición del puntero tras leer 3 bytes (getFilePointer == 3)
     */
    public static long punteroAvanzaAlLeer() {
        // GUÍA: escribe >=3 bytes, seek(0), lee 3 bytes (read() x3 o readFully(new byte[3]));
        //   return raf.getFilePointer();  // 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para punteroAvanzaAlLeer");
    }

    /**
     * Reto Extra 8: registros de tamaño fijo — saltar al k-ésimo con seek(k*tam).
     * @return el primer byte del registro de índice 2 con registros de 4 bytes
     */
    public static int leerRegistroFijo() {
        // GUÍA: escribe 3 registros de 4 bytes; el registro 0 empieza en byte 0, el 1 en el 4, el 2 en el 8.
        // Marca el primer byte de cada registro (p.ej. 100,101,102). raf.seek(2*4); return raf.read();  // 102.
        // CULTURA: así funcionan los ficheros indexados y muchas BD: posición = índice * tamañoRegistro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerRegistroFijo");
    }

    /**
     * Reto Extra 9: abrir en modo "r" (solo lectura) impide escribir.
     * @return true si intentar write() sobre un RandomAccessFile abierto en "r" lanza IOException
     */
    public static boolean modoSoloLecturaNoEscribe() {
        // GUÍA: crea el fichero, ábrelo en modo "r"; try { raf.write(1); return false; }
        //   catch (IOException e) { return true; }
        // CULTURA: el modo "r"/"rw"/"rws"/"rwd" controla permisos y durabilidad de la escritura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modoSoloLecturaNoEscribe");
    }

    /**
     * Reto Extra 10: writeDouble/readDouble round-trip de un número en coma flotante.
     * @return el double leído tras escribirlo (== valor)
     */
    public static double escribirYLeerDouble(double valor) {
        // GUÍA: raf.writeDouble(valor); raf.seek(0); return raf.readDouble();  // 8 bytes.
        // OJO: el test compara con delta 0.0 (round-trip exacto: los mismos bits IEEE-754).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerDouble");
    }

    /**
     * Reto Extra 11: seek(0) vuelve al inicio para releer.
     * @return el primer byte leído dos veces tras seek(0) entre medias (== datos[0])
     */
    public static int seekCeroRelee(byte[] datos) {
        // GUÍA: escribe 'datos', seek(0), lee el primer byte; seek(0) otra vez, vuelve a leer el primero.
        // Demuestra que seek(0) "rebobina". El test pasa {7,...} y espera 7.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seekCeroRelee");
    }

    /**
     * Reto Extra 12: acceso aleatorio puro — leer un offset sin leer lo anterior.
     * @return el byte en el offset 5 de un fichero de 10 bytes, leído directamente
     */
    public static int accesoAleatorioDirecto() {
        // GUÍA: escribe 10 bytes con valores conocidos (p.ej. byte i = i*10); raf.seek(5); return raf.read();
        // CONTRASTE con 207: con un InputStream tendrías que leer (y descartar) los 5 primeros bytes;
        // con seek saltas directo. El test espera 50 (si datos[i] = i*10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para accesoAleatorioDirecto");
    }
}
