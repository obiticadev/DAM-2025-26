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
     * Formaliza el comportamiento esperado de leer línea a línea con BufferedReader.readLine() dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param contenido texto de entrada que se escribe o relee
     * @return número de líneas leídas (igual a 3 para "a\nb\nc")
     */
    public static int contarLineas(String contenido) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineas");
    }

    /**
     * Reto Extra 2: leer con un charset distinto del de escritura corrompe el texto (mojibake).
     * Formaliza el comportamiento esperado de leer con un charset distinto del de escritura corrompe el
     * texto (mojibake) dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si escribir "ñ" en UTF-8 y leerlo en ISO-8859-1 NO devuelve "ñ"
     */
    public static boolean mojibakeAlCambiarCharset() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mojibakeAlCambiarCharset");
    }

    /**
     * Reto Extra 3: con el mismo charset (UTF-8) los acentos se conservan.
     * Formaliza el comportamiento esperado de con el mismo charset (UTF-8) los acentos se conservan dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return el texto releído con UTF-8, igual al original "ñandú café"
     */
    public static String utf8RespetaAcentos(String texto) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para utf8RespetaAcentos");
    }

    /**
     * Reto Extra 4: leer todo el contenido como un único String.
     * Formaliza el comportamiento esperado de leer todo el contenido como un único String dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param contenido texto de entrada que se escribe o relee
     * @return el contenido completo releído (igual al escrito)
     */
    public static String leerTodoComoString(String contenido) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerTodoComoString");
    }

    /**
     * Reto Extra 5: BufferedReader.lines() expone las líneas como Stream.
     * Formaliza el comportamiento esperado de bufferedReader.lines() expone las líneas como Stream dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @param contenido texto de entrada que se escribe o relee
     * @return número de líneas contadas con br.lines().count() (igual a 3 para "x\ny\nz")
     */
    public static long contarLineasConStream(String contenido) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineasConStream");
    }

    /**
     * Reto Extra 6: escribir líneas con PrintWriter.println y releerlas.
     * Formaliza el comportamiento esperado de escribir líneas con PrintWriter.println y releerlas dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @return número de líneas leídas tras escribir 2 con println (igual a 2)
     */
    public static int printWriterEscribeLineas() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para printWriterEscribeLineas");
    }

    /**
     * Reto Extra 7: el charset por defecto de la plataforma (JDK 18+) es UTF-8.
     * Formaliza el comportamiento esperado de el charset por defecto de la plataforma (JDK 18+) es UTF-8
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el nombre del charset por defecto (esperado: "UTF-8" en JDK moderno)
     */
    public static String charsetPorDefecto() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para charsetPorDefecto");
    }

    /**
     * Reto Extra 8: en ISO-8859-1 cada carácter ASCII ocupa exactamente 1 byte.
     * Formaliza el comportamiento esperado de en ISO-8859-1 cada carácter ASCII ocupa exactamente 1 byte
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return número de bytes de "abc" codificado en ISO-8859-1 (esperado: 3)
     */
    public static int bytesEnIsoLatin1(String texto) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bytesEnIsoLatin1");
    }

    /**
     * Reto Extra 9: escribir UTF-8 "normal" no añade un BOM al principio.
     * Formaliza el comportamiento esperado de escribir UTF-8 "normal" no añade un BOM al principio dentro
     * de una operación de E/S pequeña y verificable.
     *
     * @return true si el primer byte del fichero NO es el inicio del BOM UTF-8 (0xEF)
     */
    public static boolean utf8SinBom() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para utf8SinBom");
    }

    /**
     * Reto Extra 10: el separador de línea del sistema existe y no está vacío.
     * Formaliza el comportamiento esperado de el separador de línea del sistema existe y no está vacío
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return true si System.lineSeparator() no es nulo ni vacío
     */
    public static boolean separadorDeLineaNoVacio() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para separadorDeLineaNoVacio");
    }

    /**
     * Reto Extra 11: leer carácter a carácter con read() hasta -1.
     * Formaliza el comportamiento esperado de leer carácter a carácter con read() hasta -1 dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return número de caracteres leídos (igual a longitud del texto sin saltos)
     */
    public static int leerCaracterACaracter(String texto) {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerCaracterACaracter");
    }

    /**
     * Reto Extra 12: el round-trip con el mismo charset es idempotente.
     * Formaliza el comportamiento esperado de el round-trip con el mismo charset es idempotente dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return true si escribir y leer "测试 café ñ" en UTF-8 devuelve exactamente el original
     */
    public static boolean roundTripUtf8Idempotente() {
        // GUÍA: Mantén separadas las ideas de texto, bytes y codificación. El comportamiento correcto depende de usar el
        // charset adecuado y de conservar exactamente las líneas o caracteres observables.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para roundTripUtf8Idempotente");
    }
}
