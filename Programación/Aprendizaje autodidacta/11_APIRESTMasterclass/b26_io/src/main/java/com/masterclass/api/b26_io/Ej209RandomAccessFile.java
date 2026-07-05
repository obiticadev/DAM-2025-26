package com.masterclass.api.b26_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * Ejercicio 209 · Acceso aleatorio: {@code RandomAccessFile}.
 *
 * <p>
 * Los streams (207/208) son <b>secuenciales</b>: lees/escribes de principio a
 * fin. Un
 * {@code RandomAccessFile} permite <b>saltar</b> a cualquier posición con
 * {@code seek(offset)}
 * y leer/escribir ahí, como un array en disco. Es la base de los ficheros de
 * registros de
 * tamaño fijo, los índices y las bases de datos (AD RA1.b, "acceso secuencial
 * vs aleatorio").
 *
 * <p>
 * Mantiene un "puntero de fichero" que avanza al leer/escribir; {@code seek} lo
 * mueve a mano,
 * {@code length()} da el tamaño y {@code setLength()} trunca o extiende.
 *
 * <p>
 * Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.3).
 */
public final class Ej209RandomAccessFile {

    private Ej209RandomAccessFile() {
    }

    /**
     * Escribe unos bytes y lee el que está en un offset concreto (acceso
     * aleatorio).
     *
     * @param datos  bytes a escribir
     * @param offset posición a leer (0-based)
     * @return el byte en esa posición (0..255), o -1 si no se ha implementado
     */
    public static int leerEnOffset(byte[] datos, int offset) {
        // TODO 1: crea un fichero temporal y ábrelo con new RandomAccessFile(tmp,
        // "rw").
        // TODO 2: escribe todos los bytes con raf.write(datos).
        // TODO 3: salta a la posición con raf.seek(offset).
        // TODO 4: lee un byte con raf.read() (devuelve 0..255 o -1 en EOF).
        // TODO 5: cierra, borra el temporal y devuelve el byte leído (maneja
        // IOException).
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(datos);
                raf.seek(offset);
                int b = raf.read();
                return b;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Actualiza un byte EN SITIO (sin reescribir el resto) y devuelve el fichero
     * completo.
     *
     * @param datos  bytes iniciales
     * @param offset posición a modificar
     * @param nuevo  nuevo valor del byte en esa posición
     * @return el contenido completo tras la modificación, o {@code null} si no se
     *         ha implementado
     */
    public static byte[] actualizarEnSitio(byte[] datos, int offset, int nuevo) {
        // TODO 6: escribe 'datos' en un RandomAccessFile "rw".
        // TODO 7: raf.seek(offset); raf.write(nuevo); (sobrescribe solo ese byte, sin
        // tocar los demás).
        // TODO 8: raf.seek(0) para volver al principio.
        // TODO 9: lee todo el contenido (byte[] buf = new byte[(int) raf.length()];
        // raf.readFully(buf)).
        // TODO 10: cierra, borra y devuelve buf.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(datos);
                raf.seek(offset);
                raf.write(nuevo);
                raf.seek(0);
                byte[] buf = new byte[(int) raf.length()];
                raf.readFully(buf);
                return buf;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return new byte[0];
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("leerEnOffset = " + leerEnOffset(new byte[] { 10, 20, 30, 40 }, 2));
        System.out.println(
                "actualizarEnSitio = " + java.util.Arrays.toString(actualizarEnSitio(new byte[] { 1, 2, 3 }, 1, 9)));
    }

    /**
     * Reto Extra 1: length() devuelve el tamaño del fichero.
     * Formaliza el comportamiento esperado de length() devuelve el tamaño del
     * fichero dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return tamaño en bytes tras escribir 'datos' (igual a datos.length)
     */
    public static long longitudDelFichero(byte[] datos) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tmp)) {
                fos.write(datos);
                return tmp.length();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 2: seek más allá del final + write extiende el fichero (deja un
     * hueco de ceros).
     * Formaliza el comportamiento esperado de seek más allá del final + write
     * extiende el fichero (deja un
     * hueco de ceros) dentro de una operación de E/S pequeña y verificable.
     *
     * @return tamaño del fichero tras seek(10) y escribir 1 byte (esperado: 11)
     */
    public static long seekMasAllaExtiende() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.seek(10);
                raf.writeByte(1);
                return raf.length();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 3: setLength() trunca el fichero a un tamaño menor.
     * Formaliza el comportamiento esperado de setLength() trunca el fichero a un
     * tamaño menor dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return tamaño tras escribir 10 bytes y hacer setLength(4) (esperado: 4)
     */
    public static long setLengthTrunca() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(new byte[10]);
                raf.setLength(4);
                return raf.length();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 4: writeInt/readInt escriben y leen un entero (4 bytes) en
     * cualquier posición.
     * Formaliza el comportamiento esperado de writeInt/readInt escriben y leen un
     * entero (4 bytes) en
     * cualquier posición dentro de una operación de E/S pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el entero leído tras escribirlo (igual a valor)
     */
    public static int escribirYLeerInt(int valor) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.writeInt(valor);
                raf.seek(0);
                return raf.readInt();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 5: writeUTF/readUTF guardan y recuperan una cadena con su
     * longitud.
     * Formaliza el comportamiento esperado de writeUTF/readUTF guardan y recuperan
     * una cadena con su
     * longitud dentro de una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return la cadena leída tras escribirla (igual a texto)
     */
    public static String escribirYLeerUTF(String texto) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.writeUTF(texto);
                raf.seek(0);
                return raf.readUTF();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 6: leer un fichero en orden inverso usando seek.
     * Formaliza el comportamiento esperado de leer un fichero en orden inverso
     * usando seek dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos del último al primero (inverso de 'datos')
     */
    public static byte[] leerInverso(byte[] datos) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(datos);
                byte[] inverso = new byte[datos.length];
                for (int i = 0; i < datos.length; i++) {
                    raf.seek(datos.length - 1 - i);
                    inverso[i] = raf.readByte();
                }
                return inverso;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return new byte[0];
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 7: el puntero de fichero avanza al leer.
     * Formaliza el comportamiento esperado de el puntero de fichero avanza al leer
     * dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @return la posición del puntero tras leer 3 bytes (getFilePointer igual a 3)
     */
    public static long punteroAvanzaAlLeer() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(new byte[] { 0, 10, 20, 30, 40, 50 });
                raf.seek(0);
                raf.read();
                raf.read();
                raf.read();
                return raf.getFilePointer();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 8: registros de tamaño fijo — saltar al k-ésimo con seek(k*tam).
     * Formaliza el comportamiento esperado de registros de tamaño fijo — saltar al
     * k-ésimo con seek(k*tam)
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el primer byte del registro de índice 2 con registros de 4 bytes
     */
    public static int leerRegistroFijo() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(new byte[] {
                        100, 100, 100, 100,
                        101, 101, 101, 101,
                        102, 102, 102, 102,
                        103, 103, 103, 103
                });
                raf.seek(2 * 4);
                return raf.readByte();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 9: abrir en modo "r" (solo lectura) impide escribir.
     * Formaliza el comportamiento esperado de abrir en modo "r" (solo lectura)
     * impide escribir dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si intentar write() sobre un RandomAccessFile abierto en "r"
     *         lanza IOException
     */
    public static boolean modoSoloLecturaNoEscribe() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "r")) {
                raf.write(new byte[1]);
                return false;
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return true;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 10: writeDouble/readDouble round-trip de un número en coma
     * flotante.
     * Formaliza el comportamiento esperado de writeDouble/readDouble round-trip de
     * un número en coma
     * flotante dentro de una operación de E/S pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el double leído tras escribirlo (igual a valor)
     */
    public static double escribirYLeerDouble(double valor) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.writeDouble(valor);
                raf.seek(0);
                return raf.readDouble();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 11: seek(0) vuelve al inicio para releer.
     * Formaliza el comportamiento esperado de seek(0) vuelve al inicio para releer
     * dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return el primer byte leído dos veces tras seek(0) entre medias (igual a
     *         datos[0])
     */
    public static int seekCeroRelee(byte[] datos) {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(datos);
                raf.seek(0);
                raf.read();
                raf.seek(0);
                return raf.read();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    /**
     * Reto Extra 12: acceso aleatorio puro — leer un offset sin leer lo anterior.
     * Formaliza el comportamiento esperado de acceso aleatorio puro — leer un
     * offset sin leer lo anterior
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el byte en el offset 5 de un fichero de 10 bytes, leído directamente
     */
    public static int accesoAleatorioDirecto() {
        // GUÍA: Razona con el puntero de fichero como estado observable: cada lectura,
        // escritura o seek cambia desde
        // dónde se opera sin obligarte a recorrer el contenido previo.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej209", ".bin");
            tmp.deleteOnExit();
            try (RandomAccessFile raf = new RandomAccessFile(tmp, "rw")) {
                raf.write(new byte[] { 0, 10, 20, 30, 40, 50, 60, 70, 80, 90 });
                raf.seek(5);
                return raf.read();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }
}
