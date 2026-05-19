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
}
