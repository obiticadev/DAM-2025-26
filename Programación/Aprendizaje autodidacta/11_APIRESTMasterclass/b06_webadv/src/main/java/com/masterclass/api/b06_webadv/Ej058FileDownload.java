package com.masterclass.api.b06_webadv;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 058 · Descarga de ficheros (Content-Disposition).
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.3).
 *
 * <p>El test pide {@code GET /api/download} y espera 200, cabecera
 * {@code Content-Disposition: attachment; filename="datos.txt"} y body "hola mundo".
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej058FileDownload {

    /**
     * Devuelve un fichero de texto como descarga.
     *
     * @return ResponseEntity con bytes, Content-Disposition y Content-Type
     */
    // TODO 2: anota el método con @GetMapping("/download").
    public ResponseEntity<byte[]> descargar() {
        // TODO 3: define el contenido del fichero: "hola mundo".
        // TODO 4: conviértelo a byte[] (getBytes, UTF-8).
        // TODO 5: parte de ResponseEntity.ok().
        // TODO 6: añade header "Content-Disposition" = "attachment; filename=\"datos.txt\"".
        // TODO 7: añade header "Content-Type" = "text/plain" (o usa contentType()).
        // TODO 8: añade "Content-Length" con la longitud del array (buena práctica).
        // TODO 9: pon el byte[] como body.
        // TODO 10: devuelve la ResponseEntity.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej058FileDownload().descargar());
    }

    /**
     * Reto Extra 1: Saneamiento de nombre de archivo para descarga.
     * Reemplaza caracteres inválidos del sistema de archivos (ej: \ / : * ? " < > |) por guiones bajos
     * para asegurar una descarga limpia.
     */
    public static String pasoExtra01(String filename) {
        // GUÍA: teoría 6.4. Reemplaza los caracteres ilegales de un nombre de
        // fichero (\ / : * ? " < > |) por '_'.
        // 1. Si filename es null -> decide (null o ""); el test no lo cubre.
        // 2. Una sola línea con regex: replaceAll de una clase de caracteres.
        //    return filename.replaceAll("[\\\\/:*?\"<>|]", "_");
        //    (en la clase [...] esos metacaracteres no necesitan casi escape, pero
        //     la barra invertida sí: "\\\\" en el String = "\\" en la regex).
        // OJO: el test: "archivo/limpio.txt" -> "archivo_limpio.txt" y
        //      "test:archivo.png" -> "test_archivo.png". El punto de la extensión
        //      NO se toca (no está en la lista de prohibidos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Cabecera Content-Disposition con soporte RFC 5987 (caracteres no ASCII).
     * Genera el valor de la cabecera Content-Disposition (ej: "attachment; filename*=UTF-8''canci%C3%B3n.mp3")
     * codificando adecuadamente los caracteres especiales en UTF-8.
     */
    public static String pasoExtra02(String filename) {
        // GUÍA: teoría 6.4 (RFC 5987 para nombres no ASCII). Formato objetivo:
        //   attachment; filename*=UTF-8''<nombre-percent-encoded>
        // 1. Codifica filename con URLEncoder en UTF-8:
        //    String enc = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        // 2. Devuelve "attachment; filename*=UTF-8''" + enc.
        // OJO/CUIDADO: URLEncoder codifica el espacio como '+', pero RFC 5987
        //      exige '%20'. El test usa "canción.mp3" (sin espacios) y espera
        //      EXACTAMENTE "attachment; filename*=UTF-8''canci%C3%B3n.mp3".
        //      La 'ó' en UTF-8 son dos bytes -> %C3%B3. Como no hay espacios, el
        //      URLEncoder vale tal cual; si los hubiera, harías
        //      enc.replace("+", "%20"). El '.' no se codifica.
        // CULTURA: por eso descargas con tildes/acentos a veces salen con nombres
        //          raros: clientes viejos que ignoran filename*.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Inyección de cabecera Content-Length.
     * Inyecta de forma segura el tamaño exacto en bytes ('content') dentro del builder de la ResponseEntity.
     */
    public static ResponseEntity.BodyBuilder pasoExtra03(ResponseEntity.BodyBuilder builder, byte[] content) {
        // GUÍA: el builder de Spring tiene un método para esto.
        // return builder.contentLength(content.length);
        // contentLength(long) añade la cabecera Content-Length y DEVUELVE el
        // propio builder (API fluida), así que puedes devolverlo directamente.
        // OJO: el test solo comprueba assertNotNull sobre el resultado; basta con
        //      no devolver null. (Defensa: si content es null podrías usar 0.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Mapeador de extensiones a tipos MIME.
     * Mapea extensiones comunes (ej: "csv" -> "text/csv", "pdf" -> "application/pdf") a su tipo MIME correspondiente.
     * Para extensiones desconocidas, devuelve "application/octet-stream".
     */
    public static String pasoExtra04(String extension) {
        // GUÍA: teoría 6.4 (mapeo extensión->MIME). Gemelo de Ej055.pasoExtra05.
        // 1. Si extension es null -> "application/octet-stream".
        // 2. switch (en minúsculas) con al menos: csv->text/csv, pdf->application/pdf
        //    y default -> "application/octet-stream".
        // PISTA: return switch (extension.toLowerCase()) {
        //            case "csv" -> "text/csv";
        //            case "pdf" -> "application/pdf";
        //            ... (añade json, png, txt, etc. si quieres)
        //            default -> "application/octet-stream";
        //        };
        // OJO: el test exige csv->text/csv, pdf->application/pdf,
        //      unknown->application/octet-stream.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Selector de visualización Inline vs Descarga (Attachment).
     * Decide si un archivo debe ser visualizado en el navegador ("inline") o descargado obligatoriamente ("attachment")
     * según su extensión (ej: pdf e imágenes inline, zip y exe attachment).
     */
    public static String pasoExtra05(String extension) {
        // GUÍA: teoría 6.4 (inline vs attachment). PDF e imágenes se muestran
        // (inline); el resto se descarga (attachment).
        // 1. Si extension es null -> "attachment" (lo seguro por defecto).
        // 2. Define el conjunto de "visualizables": pdf, png, jpg, jpeg, gif.
        //    Si la extensión (en minúsculas) está -> "inline"; si no -> "attachment".
        // PISTA: List.of("pdf","png","jpg","jpeg","gif").contains(ext) ? "inline" : "attachment";
        // OJO: el test: pdf->inline, png->inline, zip->attachment.
        // CULTURA: forzar attachment en zip/exe evita que el navegador "abra"
        //          (ejecute) contenido peligroso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Generador de ETags para descargas.
     * Genera un ETag fuerte en formato hexadecimal para un contenido en bytes dado, encerrándolo en comillas dobles.
     */
    public static String pasoExtra06(byte[] content) {
        // GUÍA: teoría 6.6 (ETag = hash del contenido, ENTRE COMILLAS).
        // 1. Si content es null -> decide (""); el test usa "test".getBytes().
        // 2. Calcula un hash (MD5/SHA-256, como en Ej057.pasoExtra05) en hex y
        //    enciérralo en comillas dobles:  "\"" + hex + "\"".
        //    Atajo válido para el test: Integer.toHexString(Arrays.hashCode(content)).
        // OJO: el test solo pide assertNotNull -> cualquier ETag no nulo pasa,
        //      pero hazlo bien (hash + comillas) porque lo reutilizas en
        //      pasoExtra10 y enlaza con Ej060.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Conversor de InputStream a byte array.
     * Lee todo el flujo de un 'inputStream' y lo convierte a un array de bytes de forma segura,
     * cerrando el flujo adecuadamente.
     */
    public static byte[] pasoExtra07(java.io.InputStream inputStream) {
        // GUÍA: el JDK moderno ya lo hace en una línea (teoría 1.9, recursos).
        // 1. Si inputStream es null -> new byte[0].
        // 2. try (inputStream) { return inputStream.readAllBytes(); }
        //    readAllBytes() (Java 9+) lee TODO el flujo; el try-with-resources lo
        //    cierra solo. Como readAllBytes lanza IOException, propágala o
        //    captúrala devolviendo new byte[0].
        // OJO: el test mete "hello stream".getBytes() y exige assertArrayEquals
        //      con la entrada -> no transformes los bytes, devuélvelos tal cual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Compresión GZIP al vuelo.
     * Comprime una cadena de texto en formato GZIP devolviendo los bytes del resultado comprimido.
     */
    public static byte[] pasoExtra08(String content) {
        // GUÍA: comprime un texto con GZIP usando las clases del JDK.
        // 1. Si content es null -> new byte[0].
        // 2. var bos = new ByteArrayOutputStream();
        //    try (var gz = new GZIPOutputStream(bos)) {
        //        gz.write(content.getBytes(StandardCharsets.UTF_8));
        //    }   // IMPORTANTE: el cierre del GZIP vacía (flush) el buffer
        //    return bos.toByteArray();
        // OJO/CUIDADO: si llamas a bos.toByteArray() ANTES de cerrar el
        //      GZIPOutputStream, sale incompleto o vacío. Cierra el gz primero
        //      (el try-with-resources lo hace al salir del bloque) y LUEGO lees
        //      bos. El test solo pide assertNotNull, pero este es EL fallo típico.
        // CULTURA: es lo que hace Spring cuando el cliente manda Accept-Encoding:
        //          gzip (ver Ej059.pasoExtra08).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Validación de seguridad de descargas (Sandboxing).
     * Comprueba si la ruta destino de la descarga ('targetPath') se encuentra contenida de forma segura
     * dentro del directorio sandbox de pruebas ('sandboxDirectory') para evitar descargas arbitrarias de ficheros del sistema.
     */
    public static boolean pasoExtra09(String targetPath, String sandboxDirectory) {
        // GUÍA: teoría 6.3/6.4 (sandboxing: la ruta destino debe estar DENTRO del
        // directorio permitido). Normaliza antes de comparar.
        // 1. Si algún argumento es null -> false.
        // 2. Normaliza ambas rutas para resolver '..' y separadores:
        //    Path target = Paths.get(targetPath).normalize();
        //    Path sandbox = Paths.get(sandboxDirectory).normalize();
        // 3. return target.startsWith(sandbox);  // startsWith de Path, no de String.
        // OJO: el test: "C:/app/sandbox/file.txt" dentro de "C:/app/sandbox" -> true;
        //      "C:/app/other/file.txt" -> false.
        // CUIDADO: usa Path.startsWith (compara por SEGMENTOS), no String.startsWith,
        //          que daría falsos positivos con "C:/app/sandbox-evil".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Construcción completa de ResponseEntity de descarga.
     * Crea una ResponseEntity<byte[]> configurada con estatus 200, tipo MIME "application/octet-stream",
     * Content-Length correspondiente y Content-Disposition de descarga (attachment) con el nombre dado.
     */
    public static ResponseEntity<byte[]> pasoExtra10(String filename, byte[] content) {
        // GUÍA: teoría 6.4. Junta todo lo anterior en UNA ResponseEntity de
        // descarga: 200 + octet-stream + Content-Length + Content-Disposition.
        // 1. byte[] body = (content != null) ? content : new byte[0];
        // 2. return ResponseEntity.ok()
        //        .header("Content-Type", "application/octet-stream")
        //        .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
        //        .contentLength(body.length)
        //        .body(body);
        //    (puedes reutilizar pasoExtra01 para sanear filename antes de meterlo).
        // OJO: el test solo comprueba que la respuesta no es null y que el status
        //      es 200 (response.getStatusCode().value() == 200). Cumple eso y
        //      añade el resto de cabeceras por buena práctica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
