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

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController y @RequestMapping("/api").
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota el método con @GetMapping("/download").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: define el contenido del fichero: "hola mundo".
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: conviértelo a byte[] (getBytes, UTF-8).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: parte de ResponseEntity.ok().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: añade header "Content-Disposition" = "attachment; filename=\"datos.txt\"".
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: añade header "Content-Type" = "text/plain" (o usa contentType()).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: añade "Content-Length" con la longitud del array (buena práctica).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: pon el byte[] como body.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la ResponseEntity.
    }

}
