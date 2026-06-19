package com.masterclass.api.b26_io;

import java.util.List;

/**
 * Ejercicio 208 · Flujos de caracteres: {@code Reader} / {@code Writer} y encodings.
 *
 * <p>Los bytes (207) no son texto: para pasar de bytes a caracteres hace falta una
 * <b>codificación</b> ({@code Charset}). Los flujos de caracteres ({@code Reader}/{@code Writer})
 * envuelven a los de bytes con un charset. El error nº1 del mundo real es el <b>mojibake</b>:
 * escribir en UTF-8 y leer en ISO-8859-1 (o al revés) destroza la ñ y los acentos. Regla:
 * <b>especifica SIEMPRE el charset</b> (UTF-8) en ambos lados.
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.2).
 */
public final class Ej208CharStreams {

    private Ej208CharStreams() {
    }

    /**
     * Escribe un texto con el charset indicado y lo relee como lista de líneas.
     *
     * @param contenido texto con líneas separadas por '\n'
     * @param charset   nombre del charset (p.ej. "UTF-8")
     * @return las líneas leídas, o lista vacía si no se ha implementado
     */
    public static List<String> leerLineas(String contenido, String charset) {
        // TODO 1: crea un fichero temporal y obtén el Charset con Charset.forName(charset).
        // TODO 2: escribe con try (Writer w = new OutputStreamWriter(new FileOutputStream(tmp), cs)) { w.write(contenido); }.
        // TODO 3: lee con try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tmp), cs))) {...}.
        // TODO 4: bucle String l; while ((l = br.readLine()) != null) result.add(l);  (readLine quita el '\n').
        // TODO 5: borra el temporal y devuelve la lista (maneja IOException).
        return List.of();
    }

    /**
     * Escribe un texto y cuenta cuántas palabras (separadas por espacios) tiene al releerlo.
     *
     * @param texto texto de una sola línea
     * @return número de palabras, o -1 si no se ha implementado
     */
    public static int escribirYContarPalabras(String texto) {
        // TODO 6: escribe 'texto' a un temporal en UTF-8 (OutputStreamWriter).
        // TODO 7: relee el contenido completo (BufferedReader, readLine o lines()).
        // TODO 8: separa por espacios con split("\\s+") (uno o más espacios).
        // TODO 9: cuenta los elementos no vacíos.
        // TODO 10: borra el temporal y devuelve el número de palabras.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("leerLineas = " + leerLineas("uno\ndos\ntres", "UTF-8"));
        System.out.println("contarPalabras = " + escribirYContarPalabras("hola mundo cruel"));
    }

    /**
     * Reto Extra 1: leer línea a línea con BufferedReader.readLine().
     * @return número de líneas leídas (== 3 para "a\nb\nc")
     */
    public static int contarLineas(String contenido) {
        // GUÍA: int n=0; while (br.readLine() != null) n++; return n;  (escribe 'contenido' primero).
        // OJO: el test usa "a\nb\nc" y espera 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineas");
    }

    /**
     * Reto Extra 2: leer con un charset distinto del de escritura corrompe el texto (mojibake).
     * @return true si escribir "ñ" en UTF-8 y leerlo en ISO-8859-1 NO devuelve "ñ"
     */
    public static boolean mojibakeAlCambiarCharset() {
        // GUÍA: teoría 26.2. Escribe "ñ" con UTF-8 (2 bytes: C3 B1) y léelo con ISO-8859-1: saldrán
        // 2 caracteres raros ("Ã±"), no "ñ". return !leido.equals("ñ").
        // CULTURA: este es EL bug de "los acentos salen mal"; la causa es siempre un charset mal puesto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mojibakeAlCambiarCharset");
    }

    /**
     * Reto Extra 3: con el mismo charset (UTF-8) los acentos se conservan.
     * @return el texto releído con UTF-8, igual al original "ñandú café"
     */
    public static String utf8RespetaAcentos(String texto) {
        // GUÍA: escribe y lee con StandardCharsets.UTF_8 en ambos lados; el texto vuelve intacto.
        // OJO: el test manda "ñandú café".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para utf8RespetaAcentos");
    }

    /**
     * Reto Extra 4: leer todo el contenido como un único String.
     * @return el contenido completo releído (igual al escrito)
     */
    public static String leerTodoComoString(String contenido) {
        // GUÍA: usa reader.readLine() en bucle reconstruyendo con '\n', o lee char a char con read()
        // hasta -1 acumulando en un StringBuilder. (Con NIO sería Files.readString, ver Ej212.)
        // OJO: el test usa "linea-unica" (sin saltos) y espera esa misma cadena.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerTodoComoString");
    }

    /**
     * Reto Extra 5: BufferedReader.lines() expone las líneas como Stream.
     * @return número de líneas contadas con br.lines().count() (== 3 para "x\ny\nz")
     */
    public static long contarLineasConStream(String contenido) {
        // GUÍA: try (BufferedReader br = ...) { return br.lines().count(); }
        // CULTURA: lines() conecta la E/S con los Streams de b01; ideal para filtrar/mapear líneas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineasConStream");
    }

    /**
     * Reto Extra 6: escribir líneas con PrintWriter.println y releerlas.
     * @return número de líneas leídas tras escribir 2 con println (== 2)
     */
    public static int printWriterEscribeLineas() {
        // GUÍA: try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, UTF_8))) { pw.println("a"); pw.println("b"); }
        //   luego cuenta las líneas releídas. println añade el separador de línea por ti.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para printWriterEscribeLineas");
    }

    /**
     * Reto Extra 7: el charset por defecto de la plataforma (JDK 18+) es UTF-8.
     * @return el nombre del charset por defecto (debe ser "UTF-8" en JDK moderno)
     */
    public static String charsetPorDefecto() {
        // GUÍA: return Charset.defaultCharset().name();  // desde JDK 18, "UTF-8" (JEP 400).
        // CULTURA: antes dependía del SO (Windows: windows-1252) y causaba mojibake entre máquinas;
        // por eso SIEMPRE conviene especificar el charset y no fiarse del default.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para charsetPorDefecto");
    }

    /**
     * Reto Extra 8: en ISO-8859-1 cada carácter ASCII ocupa exactamente 1 byte.
     * @return número de bytes de "abc" codificado en ISO-8859-1 (debe ser 3)
     */
    public static int bytesEnIsoLatin1(String texto) {
        // GUÍA: return texto.getBytes(StandardCharsets.ISO_8859_1).length;  // 1 byte por carácter.
        // CONTRASTE: "ñ" en UTF-8 son 2 bytes pero en ISO-8859-1 es 1 (por eso cambiar charset rompe).
        // OJO: el test usa "abc" y espera 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bytesEnIsoLatin1");
    }

    /**
     * Reto Extra 9: escribir UTF-8 "normal" NO añade un BOM al principio.
     * @return true si el primer byte del fichero NO es el inicio del BOM UTF-8 (0xEF)
     */
    public static boolean utf8SinBom() {
        // GUÍA: escribe "hola" con OutputStreamWriter UTF-8, relee los bytes crudos (FileInputStream)
        // y comprueba que bytes[0] != (byte)0xEF (el BOM sería EF BB BF). return bytes[0] != (byte)0xEF.
        // CULTURA: Java NO escribe BOM en UTF-8; algunos editores de Windows sí, y eso rompe parsers.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para utf8SinBom");
    }

    /**
     * Reto Extra 10: el separador de línea del sistema existe y no está vacío.
     * @return true si System.lineSeparator() no es nulo ni vacío
     */
    public static boolean separadorDeLineaNoVacio() {
        // GUÍA: String sep = System.lineSeparator(); return sep != null && !sep.isEmpty();
        // CULTURA: en Windows es "\r\n" y en *nix "\n"; por eso readLine() (que acepta ambos) es robusto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para separadorDeLineaNoVacio");
    }

    /**
     * Reto Extra 11: leer carácter a carácter con read() hasta -1.
     * @return número de caracteres leídos (== longitud del texto sin saltos)
     */
    public static int leerCaracterACaracter(String texto) {
        // GUÍA: int c; int n=0; while ((c = reader.read()) != -1) n++; return n;
        // OJO: read() de un Reader devuelve un char como int (0..65535), o -1 en EOF. El test usa
        // "abcde" y espera 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerCaracterACaracter");
    }

    /**
     * Reto Extra 12: el round-trip con el MISMO charset es idempotente.
     * @return true si escribir y leer "测试 café ñ" en UTF-8 devuelve exactamente el original
     */
    public static boolean roundTripUtf8Idempotente() {
        // GUÍA: UTF-8 codifica cualquier carácter Unicode (chino, emoji, acentos). Escribe y lee
        // "测试 café ñ" con UTF-8 y comprueba que vuelve idéntico.
        // CULTURA: por eso UTF-8 es el estándar universal: una sola codificación para todos los idiomas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para roundTripUtf8Idempotente");
    }
}
