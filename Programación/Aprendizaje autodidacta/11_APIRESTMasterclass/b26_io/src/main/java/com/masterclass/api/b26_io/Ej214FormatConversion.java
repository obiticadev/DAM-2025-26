package com.masterclass.api.b26_io;

import java.util.List;

/**
 * Ejercicio 214 · Conversión entre formatos: texto ↔ binario, properties ↔ CSV.
 *
 * <p>Cierre del bloque y de AD RA1.e ("conversiones de formato"). Los datos viven en muchos
 * formatos: bytes crudos, texto, {@code .properties} (clave=valor), CSV (valores separados por
 * comas). Convertir entre ellos es pan de cada día: leer un formato, transformar en memoria y
 * escribir otro. Aquí practicas las conversiones más típicas con las herramientas del JDK
 * ({@code String.getBytes}, {@code ByteBuffer}, {@code Properties}, {@code split}/{@code join},
 * {@code Base64}, {@code HexFormat}).
 *
 * <p>Teoría: {@code teoria/26_IO_Ficheros_NIO2.md} (sección 26.8).
 */
public final class Ej214FormatConversion {

    private Ej214FormatConversion() {
    }

    /**
     * Convierte un texto a bytes (UTF-8), los escribe a fichero y reconstruye el texto al leerlos.
     *
     * @param texto texto a convertir
     * @return el texto reconstruido (== texto), o {@code null} si no se ha implementado
     */
    public static String textoABinarioYVuelta(String texto) {
        // TODO 1: convierte a bytes con texto.getBytes(StandardCharsets.UTF_8).
        // TODO 2: escribe esos bytes a un fichero temporal (Files.write o un OutputStream).
        // TODO 3: relee los bytes (Files.readAllBytes).
        // TODO 4: reconstruye con new String(bytes, StandardCharsets.UTF_8).
        // TODO 5: borra el temporal y devuelve el texto (maneja IOException).
        return null;
    }

    /**
     * Guarda una propiedad en un fichero {@code .properties} y la recupera.
     *
     * @param clave clave de la propiedad
     * @param valor valor de la propiedad
     * @return el valor recuperado (== valor), o {@code null} si no se ha implementado
     */
    public static String propertiesRoundTrip(String clave, String valor) {
        // TODO 6: crea Properties props = new Properties(); props.setProperty(clave, valor).
        // TODO 7: guarda con try (OutputStream os = new FileOutputStream(tmp)) { props.store(os, null); }.
        // TODO 8: carga en otro Properties: try (InputStream is = new FileInputStream(tmp)) { props2.load(is); }.
        // TODO 9: recupera con props2.getProperty(clave).
        // TODO 10: borra el temporal y devuelve el valor.
        return null;
    }

    public static void main(String[] args) {
        System.out.println("textoABinarioYVuelta = " + textoABinarioYVuelta("hola"));
        System.out.println("propertiesRoundTrip = " + propertiesRoundTrip("nombre", "ana"));
    }

    /**
     * Reto Extra 1: cuántos bytes ocupa un texto con acentos en UTF-8.
     * Formaliza el comportamiento esperado de cuántos bytes ocupa un texto con acentos en UTF-8 dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return número de bytes de "ñ" en UTF-8 (esperado: 2)
     */
    public static int numeroDeBytesUtf8(String texto) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeBytesUtf8");
    }

    /**
     * Reto Extra 2: convertir un Properties a líneas CSV "clave,valor".
     * Formaliza el comportamiento esperado de convertir un Properties a líneas CSV "clave,valor" dentro de
     * una operación de E/S pequeña y verificable.
     *
     * @return número de líneas CSV generadas a partir de 2 propiedades (esperado: 2)
     */
    public static int propertiesACsv() {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesACsv");
    }

    /**
     * Reto Extra 3: parsear CSV "clave,valor" a un Properties y leer una clave.
     * Formaliza el comportamiento esperado de parsear CSV "clave,valor" a un Properties y leer una clave
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el valor de la clave "a" tras parsear "a,1\nb,2" (esperado: "1")
     */
    public static String csvAProperties() {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvAProperties");
    }

    /**
     * Reto Extra 4: convertir un int a 4 bytes y reconstruirlo.
     * Formaliza el comportamiento esperado de convertir un int a 4 bytes y reconstruirlo dentro de una
     * operación de E/S pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el entero reconstruido desde sus bytes (igual a valor)
     */
    public static int intABytesYVuelta(int valor) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intABytesYVuelta");
    }

    /**
     * Reto Extra 5: round-trip de texto por Base64.
     * Formaliza el comportamiento esperado de round-trip de texto por Base64 dentro de una operación de
     * E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return el texto reconstruido tras codificar y decodificar en Base64 (igual a texto)
     */
    public static String base64RoundTrip(String texto) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para base64RoundTrip");
    }

    /**
     * Reto Extra 6: separar una línea CSV en campos.
     * Formaliza el comportamiento esperado de separar una línea CSV en campos dentro de una operación de
     * E/S pequeña y verificable.
     *
     * @param linea línea de texto que se va a separar
     * @return número de campos de "uno,dos,tres" (esperado: 3)
     */
    public static int csvParsearCampos(String linea) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvParsearCampos");
    }

    /**
     * Reto Extra 7: unir una lista en una línea CSV.
     * Formaliza el comportamiento esperado de unir una lista en una línea CSV dentro de una operación de
     * E/S pequeña y verificable.
     *
     * @param campos campos que se deben unir en formato CSV
     * @return "a,b,c" a partir de la lista ["a","b","c"]
     */
    public static String csvJoin(List<String> campos) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvJoin");
    }

    /**
     * Reto Extra 8: round-trip de bytes por hexadecimal.
     * Formaliza el comportamiento esperado de round-trip de bytes por hexadecimal dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @param datos bytes de entrada del escenario
     * @return los bytes reconstruidos tras pasar a hex y volver (iguales a datos)
     */
    public static byte[] hexRoundTrip(byte[] datos) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hexRoundTrip");
    }

    /**
     * Reto Extra 9: listar las claves de un Properties.
     * Formaliza el comportamiento esperado de listar las claves de un Properties dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @return número de claves de un Properties con 2 entradas (esperado: 2)
     */
    public static int propertiesListarClaves() {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesListarClaves");
    }

    /**
     * Reto Extra 10: getProperty con valor por defecto para una clave inexistente.
     * Formaliza el comportamiento esperado de getProperty con valor por defecto para una clave inexistente
     * dentro de una operación de E/S pequeña y verificable.
     *
     * @return el valor por defecto al pedir una clave que no existe (esperado: "N/A")
     */
    public static String propertiesValorPorDefecto() {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesValorPorDefecto");
    }

    /**
     * Reto Extra 11: round-trip de un double por 8 bytes.
     * Formaliza el comportamiento esperado de round-trip de un double por 8 bytes dentro de una operación
     * de E/S pequeña y verificable.
     *
     * @param valor valor numérico que se convierte o persiste
     * @return el double reconstruido desde sus bytes (igual a valor)
     */
    public static double doubleABytesYVuelta(double valor) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para doubleABytesYVuelta");
    }

    /**
     * Reto Extra 12: texto multilínea a lista de líneas a texto, sin perder líneas.
     * Formaliza el comportamiento esperado de texto multilínea a lista de líneas a texto, sin perder
     * líneas dentro de una operación de E/S pequeña y verificable.
     *
     * @param texto texto de entrada del escenario
     * @return número de líneas tras split("\n") de "a\nb\nc\nd" (esperado: 4)
     */
    public static int textoALineasYVuelta(String texto) {
        // GUÍA: Separa representación de dominio, texto y bytes. Cada conversión debe ser reversible o medible según el
        // contrato, sin confundir formato con significado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoALineasYVuelta");
    }
}
