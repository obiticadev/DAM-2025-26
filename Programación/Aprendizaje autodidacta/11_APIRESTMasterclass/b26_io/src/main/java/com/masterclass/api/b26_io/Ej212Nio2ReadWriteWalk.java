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
     * @return número de bytes leídos tras escribir "abcdef" (== 6)
     */
    public static int readAllBytes() {
        // GUÍA: Files.writeString(p, "abcdef"); return Files.readAllBytes(p).length;  // 6.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para readAllBytes");
    }

    /**
     * Reto Extra 2: Files.lines expone las líneas como Stream (cerrar el stream).
     * @return número de líneas contadas con Files.lines (== 3 para 3 líneas)
     */
    public static long contarConFilesLines() {
        // GUÍA: escribe 3 líneas; try (Stream<String> s = Files.lines(p)) { return s.count(); }
        // OJO/CUIDADO: Files.lines abre el fichero; hay que cerrar el Stream (try-with-resources) o fugas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarConFilesLines");
    }

    /**
     * Reto Extra 3: walk con profundidad 1 no entra en subdirectorios.
     * @return número de entradas a profundidad 1 incluyendo el propio dir (depende del árbol creado)
     */
    public static long walkProfundidadUno() {
        // GUÍA: crea un dir con 2 ficheros y un subdir; try (Stream<Path> s = Files.walk(dir, 1)) { return s.count(); }
        // walk(dir, 1) devuelve el dir + sus hijos directos (no nietos). Crea EXACTAMENTE 2 ficheros y 1 subdir
        // y el test espera 4 (dir + 2 ficheros + 1 subdir).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para walkProfundidadUno");
    }

    /**
     * Reto Extra 4: Files.find filtra por un criterio (p.ej. extensión).
     * @return número de ficheros .txt encontrados entre varios de distintas extensiones (== 2)
     */
    public static long findPorExtension() {
        // GUÍA: crea "a.txt","b.txt","c.dat"; try (Stream<Path> s = Files.find(dir, 5,
        //   (path, attrs) -> path.toString().endsWith(".txt"))) { return s.count(); }  // 2.
        // CULTURA: find combina recorrido + filtro con acceso a los atributos en el predicado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para findPorExtension");
    }

    /**
     * Reto Extra 5: Files.list lista solo el contenido directo de un directorio.
     * @return número de entradas directas de un dir con 3 ficheros (== 3)
     */
    public static long listDirectorio() {
        // GUÍA: try (Stream<Path> s = Files.list(dir)) { return s.count(); }  (no recursivo).
        // CONTRASTE: list = solo hijos directos; walk = todo el subárbol.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listDirectorio");
    }

    /**
     * Reto Extra 6: BasicFileAttributes da tipo y tamaño en una sola lectura.
     * @return true si attrs.isRegularFile() y attrs.size() coinciden con un fichero de 4 bytes
     */
    public static boolean basicFileAttributes() {
        // GUÍA: Files.writeString(p, "abcd");
        //   BasicFileAttributes a = Files.readAttributes(p, BasicFileAttributes.class);
        //   return a.isRegularFile() && a.size() == 4;
        // CULTURA: readAttributes obtiene todos los metadatos de una vez (más eficiente que varias llamadas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para basicFileAttributes");
    }

    /**
     * Reto Extra 7: se puede consultar la fecha de última modificación.
     * @return true si Files.getLastModifiedTime devuelve un instante no nulo
     */
    public static boolean lastModifiedTimeNoNulo() {
        // GUÍA: return Files.getLastModifiedTime(p) != null;  (tras crear el fichero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lastModifiedTimeNoNulo");
    }

    /**
     * Reto Extra 8: Files.mismatch detecta el primer byte distinto (o -1 si son iguales).
     * @return true si Files.mismatch da -1 para ficheros iguales y &gt;= 0 para distintos
     */
    public static boolean mismatchDetectaDiferencia() {
        // GUÍA: escribe "hola" en p1 y p2 (iguales) -> Files.mismatch(p1,p2) == -1.
        //   escribe "holX" en p3 -> Files.mismatch(p1,p3) >= 0 (posición del primer byte distinto).
        //   return (igual == -1) && (distinto >= 0).
        // CULTURA: comparar ficheros sin leerlos enteros a mano (compárese con Ej207 reto 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismatchDetectaDiferencia");
    }

    /**
     * Reto Extra 9: escribir en modo APPEND añade líneas al final.
     * @return número total de líneas tras escribir 2 + 2 en append (== 4)
     */
    public static long writeAppendLineas() {
        // GUÍA: Files.write(p, List.of("a","b")); luego Files.write(p, List.of("c","d"),
        //   StandardOpenOption.APPEND); cuenta las líneas (readAllLines().size()).  // 4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para writeAppendLineas");
    }

    /**
     * Reto Extra 10: walk filtrando solo directorios.
     * @return número de directorios en un árbol con 1 dir raíz + 2 subdirs (== 3)
     */
    public static long walkSoloDirectorios() {
        // GUÍA: crea dir con 2 subdirectorios; try (Stream<Path> s = Files.walk(dir)) {
        //   return s.filter(Files::isDirectory).count(); }  // 3 (raíz + 2 subdirs).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para walkSoloDirectorios");
    }

    /**
     * Reto Extra 11: Files.newBufferedReader().lines() para leer perezosamente.
     * @return número de líneas leídas con un BufferedReader de NIO (== 3)
     */
    public static long newBufferedReaderLines() {
        // GUÍA: escribe 3 líneas; try (BufferedReader br = Files.newBufferedReader(p)) { return br.lines().count(); }
        // CULTURA: Files.newBufferedReader/newBufferedWriter usan UTF-8 por defecto y son la forma NIO de
        // obtener los Reader/Writer de 208.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para newBufferedReaderLines");
    }

    /**
     * Reto Extra 12: el atributo de fecha de creación está disponible.
     * @return true si BasicFileAttributes.creationTime() no es nulo
     */
    public static boolean creationTimeNoNulo() {
        // GUÍA: BasicFileAttributes a = Files.readAttributes(p, BasicFileAttributes.class);
        //   return a.creationTime() != null;
        // OJO: algunos sistemas de ficheros no rastrean creationTime y devuelven la de modificación,
        // pero el objeto FileTime nunca es null. Enlaza con java.time (b01·Ej020).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para creationTimeNoNulo");
    }
}
