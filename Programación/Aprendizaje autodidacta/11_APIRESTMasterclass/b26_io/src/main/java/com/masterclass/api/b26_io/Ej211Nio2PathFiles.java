package com.masterclass.api.b26_io;

/**
 * Ejercicio 211 · NIO.2: {@code Path} y {@code Files}.
 *
 * <p>{@code java.nio.file} (NIO.2, desde Java 7) es la API moderna de ficheros, mucho más
 * cómoda que {@code java.io.File}. Un {@code Path} representa una ruta (no el fichero) y la
 * clase de utilidades {@code Files} hace casi todo en una línea: crear, copiar, mover, borrar,
 * comprobar existencia, leer/escribir de golpe. Es lo que usarías hoy en producción (AD RA1.a/c/d).
 *
 * <p>Los métodos usan ficheros/directorios temporales ({@code Files.createTempFile/createTempDirectory})
 * que se borran al terminar.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.5).
 */
public final class Ej211Nio2PathFiles {

    private Ej211Nio2PathFiles() {
    }

    /**
     * Escribe un texto en un fichero con {@code Files.writeString} y lo relee con {@code Files.readString}.
     *
     * @param contenido texto a escribir
     * @return el texto releído (== contenido), o {@code null} si no se ha implementado
     */
    public static String crearYLeerTexto(String contenido) {
        // TODO 1: crea un Path temporal con Files.createTempFile("ej211", ".txt").
        // TODO 2: escribe con Files.writeString(path, contenido) (UTF-8 por defecto).
        // TODO 3: relee con Files.readString(path).
        // TODO 4: borra con Files.deleteIfExists(path).
        // TODO 5: devuelve el texto releído (maneja IOException).
        return null;
    }

    /**
     * Escribe un texto, copia el fichero a otro Path y devuelve el tamaño del copiado.
     *
     * @param contenido texto a escribir
     * @return número de bytes del fichero copiado (== contenido.length() en ASCII), o -1 si no se ha implementado
     */
    public static long copiarYContar(String contenido) {
        // TODO 6: crea un Path origen y escribe 'contenido' (Files.writeString).
        // TODO 7: crea un Path destino (Files.createTempFile) y copia con
        //         Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING).
        // TODO 8: obtén el tamaño con Files.size(destino).
        // TODO 9: borra ambos Path.
        // TODO 10: devuelve el tamaño (en ASCII, == contenido.length()).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("crearYLeerTexto = " + crearYLeerTexto("hola nio"));
        System.out.println("copiarYContar = " + copiarYContar("12345"));
    }

    /**
     * Reto Extra 1: Files.exists es true tras crear el fichero.
     * Formaliza el comportamiento esperado de files.exists es true tras crear el fichero dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return true si Files.exists(path) tras crearlo
     */
    public static boolean existsTrasCrear() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existsTrasCrear");
    }

    /**
     * Reto Extra 2: tras borrar, el fichero ya no existe.
     * Formaliza el comportamiento esperado de tras borrar, el fichero ya no existe dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @return true si Files.exists es false tras Files.deleteIfExists
     */
    public static boolean noExisteTrasBorrar() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noExisteTrasBorrar");
    }

    /**
     * Reto Extra 3: mover un fichero lo quita del origen y lo pone en el destino.
     * Formaliza el comportamiento esperado de mover un fichero lo quita del origen y lo pone en el destino
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si tras Files.move el origen no existe y el destino sí
     */
    public static boolean moverFichero() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moverFichero");
    }

    /**
     * Reto Extra 4: copiar con REPLACE_EXISTING sobrescribe el destino.
     * Formaliza el comportamiento esperado de copiar con REPLACE_EXISTING sobrescribe el destino dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return el contenido del destino tras copiar encima de un fichero existente (igual a "nuevo")
     */
    public static String copiarReplaceExisting() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarReplaceExisting");
    }

    /**
     * Reto Extra 5: crear un directorio y comprobar que lo es.
     * Formaliza el comportamiento esperado de crear un directorio y comprobar que lo es dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return true si Files.isDirectory tras Files.createDirectory
     */
    public static boolean crearDirectorio() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDirectorio");
    }

    /**
     * Reto Extra 6: Files.size devuelve el tamaño en bytes.
     * Formaliza el comportamiento esperado de files.size devuelve el tamaño en bytes dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return tamaño del fichero tras escribir "abcdef" en UTF-8 (igual a 6)
     */
    public static long tamanoFichero() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoFichero");
    }

    /**
     * Reto Extra 7: un fichero normal es "regular file".
     * Formaliza el comportamiento esperado de un fichero normal es "regular file" dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @return true si Files.isRegularFile(path) para un fichero creado
     */
    public static boolean esRegularFile() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRegularFile");
    }

    /**
     * Reto Extra 8: resolve construye una ruta hija a partir de un directorio.
     * Formaliza el comportamiento esperado de resolve construye una ruta hija a partir de un directorio
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si dir.resolve("datos.txt") termina en "datos.txt"
     */
    public static boolean resolveConstruyeRuta() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolveConstruyeRuta");
    }

    /**
     * Reto Extra 9: getFileName devuelve el nombre del fichero sin la ruta.
     * Formaliza el comportamiento esperado de getFileName devuelve el nombre del fichero sin la ruta
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el nombre del fichero de un Path "carpeta/informe.pdf" (igual a "informe.pdf")
     */
    public static String getFileName() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getFileName");
    }

    /**
     * Reto Extra 10: escribir y leer texto con acentos (UTF-8) con Files.
     * Formaliza el comportamiento esperado de escribir y leer texto con acentos (UTF-8) con Files dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return el texto releído con Files.readString, igual al original "ñandú café"
     */
    public static String writeReadStringUtf8(String texto) {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para writeReadStringUtf8");
    }

    /**
     * Reto Extra 11: Files.notExists es true para una ruta inventada.
     * Formaliza el comportamiento esperado de files.notExists es true para una ruta inventada dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si Files.notExists para un Path que nunca se creó
     */
    public static boolean notExistsParaRutaInventada() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notExistsParaRutaInventada");
    }

    /**
     * Reto Extra 12: crear una jerarquía de directorios anidados de una vez.
     * Formaliza el comportamiento esperado de crear una jerarquía de directorios anidados de una vez
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si Files.createDirectories(a/b/c) crea toda la cadena y existe
     */
    public static boolean crearDirectoriosAnidados() {
        // GUÍA: Usa Path para expresar rutas y Files para ejecutar operaciones atómicas de creación, copia, movimiento,
        // lectura o borrado, limpiando siempre los recursos temporales creados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDirectoriosAnidados");
    }
}
