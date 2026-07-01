package com.masterclass.api.b26_io;

import java.util.List;

/**
 * Ejercicio 212 · NIO.2 a fondo: lectura/escritura de líneas, recorrido de árboles y atributos.
 *
 * <p>Continúa con {@code Files}: leer/escribir colecciones de líneas de golpe
 * ({@code readAllLines}/{@code write}), recorrer árboles de directorios ({@code walk}, {@code find},
 * {@code list}) integrándolos con los Streams de {@code b01}, y consultar metadatos
 * ({@code BasicFileAttributes}: tamaño, fechas, tipo). Cubre AD RA1.c (recuperar info) de forma moderna.
 *
 * <p>Los métodos crean un árbol temporal y lo borran al final.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.6).
 */
public final class Ej212Nio2ReadWriteWalk {

    private Ej212Nio2ReadWriteWalk() {
    }

    /**
     * Escribe una lista de líneas con {@code Files.write} y la relee con {@code Files.readAllLines}.
     *
     * @param lineas líneas a escribir
     * @return las líneas releídas (== lineas), o lista vacía si no se ha implementado
     */
    public static List<String> escribirYLeerLineas(List<String> lineas) {
        // TODO 1: crea un Path temporal.
        // TODO 2: escribe con Files.write(path, lineas) (escribe cada elemento como una línea, UTF-8).
        // TODO 3: relee con Files.readAllLines(path).
        // TODO 4: borra el Path con Files.deleteIfExists.
        // TODO 5: devuelve la lista releída (maneja IOException).
        return List.of();
    }

    /**
     * Crea n ficheros en un directorio temporal y cuenta los ficheros regulares con {@code Files.walk}.
     *
     * @param n número de ficheros a crear
     * @return número de ficheros regulares encontrados (== n), o -1 si no se ha implementado
     */
    public static long contarFicherosEnArbol(int n) {
        // TODO 6: crea un directorio temporal con Files.createTempDirectory.
        // TODO 7: crea n ficheros dentro (dir.resolve("f"+i+".txt") + Files.writeString o Files.createFile).
        // TODO 8: recorre con try (Stream<Path> s = Files.walk(dir)) { ... } y filtra Files::isRegularFile.
        // TODO 9: cuenta con .count().
        // TODO 10: borra el árbol (walk + sorted(reverseOrder) + delete, o borra ficheros y dir) y devuelve el conteo.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("escribirYLeerLineas = " + escribirYLeerLineas(List.of("a", "b", "c")));
        System.out.println("contarFicherosEnArbol(5) = " + contarFicherosEnArbol(5));
    }

    /**
     * Reto Extra 1: leer todos los bytes de un fichero con Files.readAllBytes.
     * Formaliza el comportamiento esperado de leer todos los bytes de un fichero con Files.readAllBytes
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return número de bytes leídos tras escribir "abcdef" (igual a 6)
     */
    public static int readAllBytes() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para readAllBytes");
    }

    /**
     * Reto Extra 2: Files.lines expone las líneas como Stream (cerrar el stream).
     * Formaliza el comportamiento esperado de files.lines expone las líneas como Stream (cerrar el stream)
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return número de líneas contadas con Files.lines (igual a 3 para 3 líneas)
     */
    public static long contarConFilesLines() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConFilesLines");
    }

    /**
     * Reto Extra 3: walk con profundidad 1 no entra en subdirectorios.
     * Formaliza el comportamiento esperado de walk con profundidad 1 no entra en subdirectorios dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return número de entradas a profundidad 1 incluyendo el propio dir (depende del árbol creado)
     */
    public static long walkProfundidadUno() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para walkProfundidadUno");
    }

    /**
     * Reto Extra 4: Files.find filtra por un criterio (p.ej. extensión).
     * Formaliza el comportamiento esperado de files.find filtra por un criterio (p.ej. extensión) dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @return número de ficheros .txt encontrados entre varios de distintas extensiones (igual a 2)
     */
    public static long findPorExtension() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para findPorExtension");
    }

    /**
     * Reto Extra 5: Files.list lista solo el contenido directo de un directorio.
     * Formaliza el comportamiento esperado de files.list lista solo el contenido directo de un directorio
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return número de entradas directas de un dir con 3 ficheros (igual a 3)
     */
    public static long listDirectorio() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listDirectorio");
    }

    /**
     * Reto Extra 6: BasicFileAttributes da tipo y tamaño en una sola lectura.
     * Formaliza el comportamiento esperado de basicFileAttributes da tipo y tamaño en una sola lectura
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si attrs.isRegularFile() y attrs.size() coinciden con un fichero de 4 bytes
     */
    public static boolean basicFileAttributes() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para basicFileAttributes");
    }

    /**
     * Reto Extra 7: se puede consultar la fecha de última modificación.
     * Formaliza el comportamiento esperado de se puede consultar la fecha de última modificación dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si Files.getLastModifiedTime devuelve un instante no nulo
     */
    public static boolean lastModifiedTimeNoNulo() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lastModifiedTimeNoNulo");
    }

    /**
     * Reto Extra 8: Files.mismatch detecta el primer byte distinto (o -1 si son iguales).
     * Formaliza el comportamiento esperado de files.mismatch detecta el primer byte distinto (o -1 si son
     * iguales) dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si Files.mismatch da -1 para ficheros iguales y &gt;= 0 para distintos
     */
    public static boolean mismatchDetectaDiferencia() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismatchDetectaDiferencia");
    }

    /**
     * Reto Extra 9: escribir en modo APPEND añade líneas al final.
     * Formaliza el comportamiento esperado de escribir en modo APPEND añade líneas al final dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @return número total de líneas tras escribir 2 + 2 en append (igual a 4)
     */
    public static long writeAppendLineas() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para writeAppendLineas");
    }

    /**
     * Reto Extra 10: walk filtrando solo directorios.
     * Formaliza el comportamiento esperado de walk filtrando solo directorios dentro de una operación de
     * E/S pequeña y verificable.
     *
     * @return número de directorios en un árbol con 1 dir raíz + 2 subdirs (igual a 3)
     */
    public static long walkSoloDirectorios() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para walkSoloDirectorios");
    }

    /**
     * Reto Extra 11: Files.newBufferedReader().lines() para leer perezosamente.
     * Formaliza el comportamiento esperado de files.newBufferedReader().lines() para leer perezosamente
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return número de líneas leídas con un BufferedReader de NIO (igual a 3)
     */
    public static long newBufferedReaderLines() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para newBufferedReaderLines");
    }

    /**
     * Reto Extra 12: el atributo de fecha de creación está disponible.
     * Formaliza el comportamiento esperado de el atributo de fecha de creación está disponible dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si BasicFileAttributes.creationTime() no es nulo
     */
    public static boolean creationTimeNoNulo() {
        // GUÍA: Distingue entre lectura completa, flujos perezosos y recorridos de árbol. Cuando una operación abre un
        // Stream de NIO, su cierre forma parte del contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para creationTimeNoNulo");
    }
}
