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
     * @return true si Files.exists(path) tras crearlo
     */
    public static boolean existsTrasCrear() {
        // GUÍA: Path p = Files.createTempFile(...); return Files.exists(p);  (bórralo después).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existsTrasCrear");
    }

    /**
     * Reto Extra 2: tras borrar, el fichero ya no existe.
     * @return true si Files.exists es false tras Files.deleteIfExists
     */
    public static boolean noExisteTrasBorrar() {
        // GUÍA: crea, Files.deleteIfExists(p), return !Files.exists(p).
        // OJO: deleteIfExists NO lanza si no existe; Files.delete SÍ lanza NoSuchFileException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noExisteTrasBorrar");
    }

    /**
     * Reto Extra 3: mover un fichero lo quita del origen y lo pone en el destino.
     * @return true si tras Files.move el origen no existe y el destino sí
     */
    public static boolean moverFichero() {
        // GUÍA: Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        //   return !Files.exists(origen) && Files.exists(destino);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moverFichero");
    }

    /**
     * Reto Extra 4: copiar con REPLACE_EXISTING sobrescribe el destino.
     * @return el contenido del destino tras copiar encima de un fichero existente (== "nuevo")
     */
    public static String copiarReplaceExisting() {
        // GUÍA: crea destino con "viejo"; crea origen con "nuevo"; Files.copy(origen, destino, REPLACE_EXISTING);
        //   return Files.readString(destino);  // "nuevo".
        // OJO: sin REPLACE_EXISTING, copiar sobre un fichero que ya existe lanza FileAlreadyExistsException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copiarReplaceExisting");
    }

    /**
     * Reto Extra 5: crear un directorio y comprobar que lo es.
     * @return true si Files.isDirectory tras Files.createDirectory
     */
    public static boolean crearDirectorio() {
        // GUÍA: Path d = Files.createTempDirectory("ej211dir"); return Files.isDirectory(d);  (bórralo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDirectorio");
    }

    /**
     * Reto Extra 6: Files.size devuelve el tamaño en bytes.
     * @return tamaño del fichero tras escribir "abcdef" en UTF-8 (== 6)
     */
    public static long tamanoFichero() {
        // GUÍA: Files.writeString(p, "abcdef"); return Files.size(p);  // 6 (ASCII = 1 byte/char).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoFichero");
    }

    /**
     * Reto Extra 7: un fichero normal es "regular file".
     * @return true si Files.isRegularFile(path) para un fichero creado
     */
    public static boolean esRegularFile() {
        // GUÍA: return Files.isRegularFile(Files.createTempFile(...));
        // CONTRASTE: un directorio NO es regular file (isDirectory sí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRegularFile");
    }

    /**
     * Reto Extra 8: resolve construye una ruta hija a partir de un directorio.
     * @return true si dir.resolve("datos.txt") termina en "datos.txt"
     */
    public static boolean resolveConstruyeRuta() {
        // GUÍA: Path d = Files.createTempDirectory(...); Path hijo = d.resolve("datos.txt");
        //   return hijo.endsWith("datos.txt");  // o hijo.getFileName().toString().equals("datos.txt").
        // CULTURA: resolve es la forma correcta de unir rutas (en vez de concatenar Strings con "/").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolveConstruyeRuta");
    }

    /**
     * Reto Extra 9: getFileName devuelve el nombre del fichero sin la ruta.
     * @return el nombre del fichero de un Path "carpeta/informe.pdf" (== "informe.pdf")
     */
    public static String getFileName() {
        // GUÍA: Path p = Path.of("carpeta", "informe.pdf"); return p.getFileName().toString();  // "informe.pdf".
        // (no necesita tocar disco; Path es solo la ruta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para getFileName");
    }

    /**
     * Reto Extra 10: escribir y leer texto con acentos (UTF-8) con Files.
     * @return el texto releído con Files.readString, igual al original "ñandú café"
     */
    public static String writeReadStringUtf8(String texto) {
        // GUÍA: Files.writeString(p, texto) y Files.readString(p) usan UTF-8 por defecto; el texto vuelve intacto.
        // (Puedes pasar un Charset explícito como tercer argumento para otros encodings).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para writeReadStringUtf8");
    }

    /**
     * Reto Extra 11: Files.notExists es true para una ruta inventada.
     * @return true si Files.notExists para un Path que nunca se creó
     */
    public static boolean notExistsParaRutaInventada() {
        // GUÍA: Path p = Path.of(System.getProperty("java.io.tmpdir"), "no-existe-" + System.nanoTime());
        //   return Files.notExists(p);
        // OJO: notExists NO es exactamente !exists (existe un tercer estado "desconocido" por permisos),
        // pero para una ruta claramente inexistente devuelve true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notExistsParaRutaInventada");
    }

    /**
     * Reto Extra 12: crear una jerarquía de directorios anidados de una vez.
     * @return true si Files.createDirectories(a/b/c) crea toda la cadena y existe
     */
    public static boolean crearDirectoriosAnidados() {
        // GUÍA: Path base = Files.createTempDirectory(...); Path anidado = base.resolve("a").resolve("b").resolve("c");
        //   Files.createDirectories(anidado); return Files.isDirectory(anidado);
        // OJO: createDirectories crea los intermedios que falten; createDirectory (singular) fallaría si "a"/"b" no existen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDirectoriosAnidados");
    }
}
