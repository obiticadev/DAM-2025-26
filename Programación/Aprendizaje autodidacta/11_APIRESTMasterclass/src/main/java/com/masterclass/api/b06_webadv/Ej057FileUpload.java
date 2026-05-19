package com.masterclass.api.b06_webadv;

import org.springframework.web.multipart.MultipartFile;

/**
 * Ejercicio 057 · Subida de ficheros (MultipartFile).
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.3).
 *
 * <p>El test sube un fichero a {@code POST /api/upload} (multipart) y espera
 * un resumen "nombre:tamaño".
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej057FileUpload {

    /**
     * Recibe un fichero y devuelve un resumen.
     *
     * @param file fichero subido en la parte "file"
     * @return "nombreOriginal:tamañoEnBytes"
     */
    // TODO 2: anota el método con @PostMapping("/upload").
    // TODO 3: anota 'file' con @RequestParam("file").
    public String subir(MultipartFile file) {
        // TODO 4: si file es null o file.isEmpty(), devuelve "vacio" (caso límite).
        // TODO 5: obtén el nombre original con file.getOriginalFilename().
        // TODO 6: si el nombre es null, usa "desconocido".
        // TODO 7: obtén el tamaño con file.getSize().
        // TODO 8: (conceptual) aquí persistirías el contenido (no en este test).
        // TODO 9: construye "nombre:tamaño".
        // TODO 10: devuelve esa cadena.
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Endpoint de subida listo");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: anota la clase con @RestController y @RequestMapping("/api").
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: anota el método con @PostMapping("/upload").
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: anota 'file' con @RequestParam("file").
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: si file es null o file.isEmpty(), devuelve "vacio" (caso límite).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: obtén el nombre original con file.getOriginalFilename().
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si el nombre es null, usa "desconocido".
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: obtén el tamaño con file.getSize().
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: (conceptual) aquí persistirías el contenido (no en este test).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: construye "nombre:tamaño".
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve esa cadena.
    }

}
