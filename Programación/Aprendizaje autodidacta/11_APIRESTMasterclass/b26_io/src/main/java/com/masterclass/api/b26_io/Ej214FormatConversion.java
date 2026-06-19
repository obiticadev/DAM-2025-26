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
     * @return número de bytes de "ñ" en UTF-8 (debe ser 2)
     */
    public static int numeroDeBytesUtf8(String texto) {
        // GUÍA: return texto.getBytes(StandardCharsets.UTF_8).length;  // "ñ" -> 2 bytes (C3 B1).
        // CONTRASTE con la longitud en caracteres: "ñ".length() == 1 pero ocupa 2 bytes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeBytesUtf8");
    }

    /**
     * Reto Extra 2: convertir un Properties a líneas CSV "clave,valor".
     * @return número de líneas CSV generadas a partir de 2 propiedades (debe ser 2)
     */
    public static int propertiesACsv() {
        // GUÍA: Properties p con 2 claves; recorre p.stringPropertyNames() y genera "clave,valor" por cada una;
        // return numeroDeLineas.  (Une con '\n' si quieres una sola cadena; aquí basta contar.)
        // CULTURA: conversión de formato real (config -> tabla). Enlaza con b16·Ej148 (CSV) si existe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesACsv");
    }

    /**
     * Reto Extra 3: parsear CSV "clave,valor" a un Properties y leer una clave.
     * @return el valor de la clave "a" tras parsear "a,1\nb,2" (debe ser "1")
     */
    public static String csvAProperties() {
        // GUÍA: por cada línea, String[] campos = linea.split(",", 2); props.setProperty(campos[0], campos[1]).
        //   return props.getProperty("a");  // "1".
        // OJO: split(",", 2) por si el valor contiene comas (como en b29·Ej236 con los espacios).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvAProperties");
    }

    /**
     * Reto Extra 4: convertir un int a 4 bytes y reconstruirlo.
     * @return el entero reconstruido desde sus bytes (== valor)
     */
    public static int intABytesYVuelta(int valor) {
        // GUÍA: byte[] b = ByteBuffer.allocate(4).putInt(valor).array();
        //   int v = ByteBuffer.wrap(b).getInt(); return v;
        // CULTURA: así se serializa un número a binario de tamaño fijo (big-endian); base de formatos binarios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intABytesYVuelta");
    }

    /**
     * Reto Extra 5: round-trip de texto por Base64.
     * @return el texto reconstruido tras codificar y decodificar en Base64 (== texto)
     */
    public static String base64RoundTrip(String texto) {
        // GUÍA: String s = Base64.getEncoder().encodeToString(texto.getBytes(UTF_8));
        //   String vuelta = new String(Base64.getDecoder().decode(s), UTF_8); return vuelta.
        // OJO: Base64 NO cifra ni comprime; solo convierte bytes a texto ASCII seguro (b30 lo recuerda).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para base64RoundTrip");
    }

    /**
     * Reto Extra 6: separar una línea CSV en campos.
     * @return número de campos de "uno,dos,tres" (debe ser 3)
     */
    public static int csvParsearCampos(String linea) {
        // GUÍA: return linea.split(",").length;  // "uno,dos,tres" -> 3.
        // OJO/CUIDADO: split simple NO maneja comas dentro de comillas ("a,\"b,c\""); para CSV real haría
        // falta un parser; para el examen, el split básico suele bastar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvParsearCampos");
    }

    /**
     * Reto Extra 7: unir una lista en una línea CSV.
     * @return "a,b,c" a partir de la lista ["a","b","c"]
     */
    public static String csvJoin(List<String> campos) {
        // GUÍA: return String.join(",", campos);  // ["a","b","c"] -> "a,b,c".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para csvJoin");
    }

    /**
     * Reto Extra 8: round-trip de bytes por hexadecimal.
     * @return los bytes reconstruidos tras pasar a hex y volver (iguales a datos)
     */
    public static byte[] hexRoundTrip(byte[] datos) {
        // GUÍA: String hex = HexFormat.of().formatHex(datos);
        //   byte[] vuelta = HexFormat.of().parseHex(hex); return vuelta.
        // CULTURA: hex es otra codificación texto-de-bytes (2 chars por byte); usada en hashes (b30).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hexRoundTrip");
    }

    /**
     * Reto Extra 9: listar las claves de un Properties.
     * @return número de claves de un Properties con 2 entradas (debe ser 2)
     */
    public static int propertiesListarClaves() {
        // GUÍA: Properties p con 2 claves; return p.stringPropertyNames().size();  // 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesListarClaves");
    }

    /**
     * Reto Extra 10: getProperty con valor por defecto para una clave inexistente.
     * @return el valor por defecto al pedir una clave que no existe (debe ser "N/A")
     */
    public static String propertiesValorPorDefecto() {
        // GUÍA: Properties p = new Properties(); return p.getProperty("no-existe", "N/A");  // "N/A".
        // CULTURA: patrón típico de config (enlaza con b04 @Value con default).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propertiesValorPorDefecto");
    }

    /**
     * Reto Extra 11: round-trip de un double por 8 bytes.
     * @return el double reconstruido desde sus bytes (== valor)
     */
    public static double doubleABytesYVuelta(double valor) {
        // GUÍA: byte[] b = ByteBuffer.allocate(8).putDouble(valor).array();
        //   return ByteBuffer.wrap(b).getDouble();
        // OJO: el test compara con delta 0.0 (los mismos bits IEEE-754 vuelven exactos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para doubleABytesYVuelta");
    }

    /**
     * Reto Extra 12: texto multilínea -> lista de líneas -> texto, sin perder líneas.
     * @return número de líneas tras split("\n") de "a\nb\nc\nd" (debe ser 4)
     */
    public static int textoALineasYVuelta(String texto) {
        // GUÍA: String[] lineas = texto.split("\n"); ... String unido = String.join("\n", lineas);
        //   return lineas.length;  // "a\nb\nc\nd" -> 4.
        // CULTURA: convertir entre "blob de texto" y "lista de registros" es el día a día de procesar ficheros.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoALineasYVuelta");
    }
}
