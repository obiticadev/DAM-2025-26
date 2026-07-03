package com.masterclass.api.b26_io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Ejercicio 207 · Flujos de bytes: {@code InputStream} / {@code OutputStream}.
 *
 * <p>
 * La base de TODA la E/S en Java es el byte. Un {@code OutputStream} escribe
 * bytes a un
 * destino (fichero, red, memoria) y un {@code InputStream} los lee de una
 * fuente. Da igual el
 * destino: la API es la misma (polimorfismo). Aquí trabajas con ficheros
 * ({@code FileInputStream}/{@code FileOutputStream}) y memoria
 * ({@code ByteArrayOutputStream}),
 * con buffers y {@code try-with-resources}. Es la materia prima de AD RA1
 * (acceso a ficheros).
 *
 * <p>
 * Para ser autocontenidos y deterministas, los métodos usan ficheros temporales
 * que crean y
 * borran ellos mismos.
 *
 * <p>
 * Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.1).
 */
public final class Ej207ByteStreams {

    private Ej207ByteStreams() {
    }

    /**
     * Escribe unos bytes a un fichero temporal y los vuelve a leer (round-trip
     * binario).
     *
     * @param datos bytes a escribir
     * @return los bytes leídos del fichero (iguales a datos), o {@code null} si no
     *         se ha implementado
     */
    public static byte[] copiarBytes(byte[] datos) {
        // TODO 1: crea un fichero temporal con File.createTempFile("ej207", ".bin")
        // (recuerda borrarlo al final).
        // TODO 2: escribe con try (OutputStream os = new FileOutputStream(tmp)) {
        // os.write(datos); }.
        // TODO 3: lee con try (InputStream is = new FileInputStream(tmp)) { ... }.
        // TODO 4: dentro, usa is.readAllBytes() (JDK 9+) para obtener todos los bytes
        // de golpe.
        // TODO 5: borra el temporal (tmp.delete()) y devuelve los bytes leídos (maneja
        // IOException).
        File tmp = null;
        byte[] byteFinal = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            try (OutputStream os = new FileOutputStream(tmp)) {
                os.write(datos);
            }
            try (InputStream is = new FileInputStream(tmp)) {
                byteFinal = is.readAllBytes();
            }
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
        return byteFinal;

    }

    /**
     * Cuenta cuántos bytes contiene un fichero leyéndolo con un buffer.
     *
     * @param datos bytes a escribir y luego contar
     * @return número de bytes leídos (== datos.length), o -1 si no se ha
     *         implementado
     */
    public static long contarBytes(byte[] datos) {
        // TODO 6: escribe 'datos' a un fichero temporal (como arriba).
        // TODO 7: abre un FileInputStream y prepara un byte[] buffer (p.ej. 8192) y un
        // contador long.
        // TODO 8: bucle: int n; while ((n = is.read(buffer)) != -1) { total += n; }
        // (read devuelve -1 en EOF).
        // TODO 9: borra el temporal.
        // TODO 10: devuelve el total contado.
        File tmp = null;
        long contador = 0;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            try (OutputStream os = new FileOutputStream(tmp)) {
                os.write(datos);
            }
            try (InputStream is = new FileInputStream(tmp)) {
                byte[] buffer = new byte[8192];
                int n;
                while ((n = is.read(buffer)) != -1) {
                    contador += n;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        System.out.println("copiarBytes = " + java.util.Arrays.toString(copiarBytes(new byte[] { 1, 2, 3 })));
        System.out.println("contarBytes = " + contarBytes(new byte[100]));
    }

    /**
     * Reto Extra 1: copiar de un stream a otro byte a byte detectando el EOF (-1).
     * Formaliza el comportamiento esperado de copiar de un stream a otro byte a
     * byte detectando el EOF
     * (-1) dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes copiados (igual a datos.length)
     */
    public static long copiarDetectandoEof(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        long bytesCopiados = 0;
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp.deleteOnExit();
            try (InputStream is = new ByteArrayInputStream(datos);
                    OutputStream os = new FileOutputStream(tmp)) {
                int byteLeido;
                while ((byteLeido = is.read()) != -1) {
                    os.write(byteLeido);
                    bytesCopiados++;
                }
                return bytesCopiados;
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
     * Reto Extra 2: copiar usando un buffer de 1024 bytes.
     * Formaliza el comportamiento esperado de copiar usando un buffer de 1024 bytes
     * dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes copiados con buffer (igual a datos.length)
     */
    public static long copiarConBuffer1024(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        long bytesLeidos = 0;
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp.deleteOnExit();
            try (InputStream is = new ByteArrayInputStream(datos);
                    OutputStream os = new FileOutputStream(tmp)) {
                byte[] bytesCapturados = new byte[1024];
                int cantidadLeida;
                while ((cantidadLeida = is.read(bytesCapturados)) != -1) {
                    os.write(bytesCapturados, 0, cantidadLeida);
                    bytesLeidos += cantidadLeida;
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
        return bytesLeidos;
    }

    /**
     * Reto Extra 3: volcar bytes a memoria con ByteArrayOutputStream.
     * Formaliza el comportamiento esperado de volcar bytes a memoria con
     * ByteArrayOutputStream dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes acumulados en memoria (igual a datos.length)
     */
    public static int volcarAMemoria(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        try {
            try (InputStream is = new ByteArrayInputStream(datos);
                    ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                int b;
                while ((b = is.read()) != -1) {
                    os.write(b);
                }
                return os.size();
            }
        } catch (IOException io) {
            // TODO: handle exception
            io.printStackTrace();
            return -1;
        }
    }

    /**
     * Reto Extra 4: el modo append añade al final en vez de sobrescribir.
     * Formaliza el comportamiento esperado de el modo append añade al final en vez
     * de sobrescribir dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return tamaño del fichero tras escribir 'datos' DOS veces en modo append
     *         (igual a 2*datos.length)
     */
    public static long appendDuplicaTamano(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", "bin");
            tmp.deleteOnExit();
            escribirFlujo(datos, tmp, false);
            escribirFlujo(datos, tmp, true);
            return tmp.length();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
    }

    public static void escribirFlujo(byte[] datos, File tmp, boolean append) throws IOException {
        try (InputStream is = new ByteArrayInputStream(datos);
                OutputStream os = new FileOutputStream(tmp, append)) {
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
    }

    /**
     * Reto Extra 5: dos ficheros con el mismo contenido son iguales byte a byte.
     * Formaliza el comportamiento esperado de dos ficheros con el mismo contenido
     * son iguales byte a byte
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si dos ficheros escritos con los mismos bytes tienen contenido
     *         idéntico
     */
    public static boolean dosFicherosIgualesPorContenido(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        File tmp2 = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp2 = File.createTempFile("ej207.5", ".bin");
            tmp.deleteOnExit();
            tmp2.deleteOnExit();
            escribirFlujo(datos, tmp, false);
            escribirFlujo(datos, tmp2, false);
            return compararContenido(tmp, tmp2);
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
            if (tmp2 != null && tmp2.exists()) {
                tmp2.delete();
            }
        }
    }

    public static boolean compararContenido(File tmp1, File tmp2) throws IOException {
        if (tmp1.length() != tmp2.length()) {
            return false;
        }
        try (InputStream is1 = new FileInputStream(tmp1);
                InputStream is2 = new FileInputStream(tmp2)) {
            int byte1;
            int byte2;

            while ((byte1 = is1.read()) != -1) {
                byte2 = is2.read();
                if (byte1 != byte2) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * Reto Extra 6: escribir y leer un único byte.
     * Formaliza el comportamiento esperado de escribir y leer un único byte dentro
     * de una operación de E/S
     * pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el byte leído (como int 0..255), igual al escrito
     */
    public static int escribirYLeerUnByte(int valor) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp.deleteOnExit();
            try (OutputStream os = new FileOutputStream(tmp)) {
                os.write(valor);
            }
            try (InputStream is = new FileInputStream(tmp)) {
                return is.read();
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
        return -1;
    }

    /**
     * Reto Extra 7: copiar un stream a otro con transferTo (JDK 9+).
     * Formaliza el comportamiento esperado de copiar un stream a otro con
     * transferTo (JDK 9+) dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return número de bytes transferidos (igual a datos.length)
     */
    public static long copiarConTransferTo(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp.deleteOnExit();
            try (InputStream is = new ByteArrayInputStream(datos);
                    FileOutputStream os = new FileOutputStream(tmp)) {
                return is.transferTo(os);
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
     * Reto Extra 8: un BufferedOutputStream/BufferedInputStream envuelve al stream
     * crudo.
     * Formaliza el comportamiento esperado de un
     * BufferedOutputStream/BufferedInputStream envuelve al
     * stream crudo dentro de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos a través de streams con buffer (iguales a datos)
     */
    public static byte[] copiarConBufferedStreams(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej207", ".bin");
            tmp.deleteOnExit();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmp))) {
                bos.write(datos);
            }
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp))) {
                return bis.readAllBytes();
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
     * Reto Extra 9: un fichero vacío tiene 0 bytes.
     * Formaliza el comportamiento esperado de un fichero vacío tiene 0 bytes dentro
     * de una operación de
     * E/S pequeña y verificable.
     *
     * @return tamaño de un fichero al que no se escribe nada (esperado: 0)
     */
    public static long ficheroVacioTieneCeroBytes() {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        try {
            tmp = File.createTempFile("ej208", ".bin");
            tmp.deleteOnExit();
            try (FileOutputStream fos = new FileOutputStream(tmp)) {
                fos.write(new byte[0]);
            }
            long tamano = tmp.length();

            tmp.delete();

            return tamano;

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
     * Reto Extra 10: flush fuerza el envío del buffer antes de cerrar.
     * Formaliza el comportamiento esperado de flush fuerza el envío del buffer
     * antes de cerrar dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes leídos tras hacer flush explícito (iguales a datos)
     */
    public static byte[] flushAseguraEscritura(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        File tmp = null;
        byte[] byteLeidos = null;
        try {
            tmp = File.createTempFile("ej208", ".bin");
            tmp.deleteOnExit();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmp))) {
                bos.write(datos);
                bos.flush();
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tmp))) {
                    byteLeidos = bis.readAllBytes();
                }
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (tmp != null && tmp.exists()) {
                tmp.delete();
            }
        }
        return byteLeidos;
    }

    /**
     * Reto Extra 11: leer más allá del final devuelve -1 repetidamente.
     * Formaliza el comportamiento esperado de leer más allá del final devuelve -1
     * repetidamente dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si, tras leer todo, una lectura extra con read() devuelve -1
     */
    public static boolean leerTrasEofDevuelveMenosUno(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.

        try {
            try (BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(datos))) {
                bis.readAllBytes();
                bis.read();
                return (bis.read() == -1);
            }
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reto Extra 12: try-with-resources cierra el stream automáticamente.
     * Formaliza el comportamiento esperado de try-with-resources cierra el stream
     * automáticamente dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return true si, tras el bloque try-with-resources, el stream quedó cerrado
     */
    public static boolean tryWithResourcesCierraStream(byte[] datos) {
        // GUÍA: Trabaja el contrato de los flujos binarios: leer hasta fin de stream,
        // escribir solo los bytes válidos y
        // cerrar los recursos que respaldan el fichero temporal.
        BufferedInputStream bis = null;
        try {
            try (BufferedInputStream binario = new BufferedInputStream(new ByteArrayInputStream(datos))) {
                bis = binario;
                binario.readAllBytes();
            }
            bis.read();

            return false;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
            return true;
        }
    }
}
