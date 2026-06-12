package com.masterclass.api.b06_webadv;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * Reto Extra 1: Validación del formato por extensión.
     * Comprueba si el archivo ('file') tiene una extensión permitida (ej: "png", "jpg"),
     * ignorando mayúsculas y minúsculas.
     */
    public static boolean pasoExtra01(MultipartFile file, List<String> allowedExtensions) {
        // GUÍA: teoría 6.3 (la extensión es una pista del cliente; valídala).
        // 1. Si file es null o getOriginalFilename() es null -> false.
        // 2. Saca la extensión: lo que va tras el ÚLTIMO punto.
        //    int p = nombre.lastIndexOf('.'); ext = nombre.substring(p + 1).
        // 3. Compara ignorando mayúsculas contra cada extensión permitida.
        // PISTA: allowedExtensions.stream().anyMatch(e -> e.equalsIgnoreCase(ext));
        // OJO: el test sube "image.PNG" (extensión en MAYÚSCULAS) con la lista
        //      ["png","jpg"] y espera true -> la comparación DEBE ignorar el caso.
        //      Con ["pdf","zip"] -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra01");
    }

    /**
     * Reto Extra 2: Validación estricta de tamaño máximo.
     * Comprueba si el tamaño del archivo no supera el límite de bytes especificado ('maxSizeBytes').
     */
    public static boolean pasoExtra02(MultipartFile file, long maxSizeBytes) {
        // GUÍA: teoría 6.3 (acota SIEMPRE el tamaño de subida).
        // 1. Si file es null -> false.
        // 2. return file.getSize() <= maxSizeBytes;  // getSize() es long.
        // OJO: el test usa un fichero de 100 bytes: con max=200 -> true, con
        //      max=50 -> false. El límite es "menor o IGUAL".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra02");
    }

    /**
     * Reto Extra 3: Validación del tipo MIME del archivo.
     * Comprueba si el tipo de contenido ('getContentType') del archivo coincide con alguno
     * de los tipos MIME permitidos (ej: "image/jpeg", "application/pdf").
     */
    public static boolean pasoExtra03(MultipartFile file, List<String> allowedMimeTypes) {
        // GUÍA: teoría 6.3. getContentType() es el MIME declarado por el cliente.
        // 1. Si file o file.getContentType() son null -> false.
        // 2. return allowedMimeTypes.contains(file.getContentType());
        //    (aquí el test usa MIME exactos; contains directo basta).
        // OJO: el test sube un "doc.pdf" con content-type "application/pdf"; con
        //      la lista ["application/pdf","image/png"] -> true, con
        //      ["text/plain"] -> false.
        // CUIDADO: en producción este MIME lo pone el cliente y puede mentir; es
        //          una primera barrera, no una garantía (combínala con la 01 y 07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra03");
    }

    /**
     * Reto Extra 4: Saneamiento de nombre contra Directory Traversal.
     * Limpia el nombre del archivo original para evitar que contenga rutas relativas (ej: "../../etc/passwd")
     * devolviendo únicamente el nombre del archivo (ej: "passwd"), o "desconocido" si no se puede determinar.
     */
    public static String pasoExtra04(MultipartFile file) {
        // GUÍA: teoría 6.3, riesgo nº 1 (directory traversal). Quédate SOLO con el
        // nombre de fichero, descartando cualquier ruta.
        // 1. Si file o getOriginalFilename() son null/blank -> "desconocido".
        // 2. Usa el JDK para extraer el último segmento:
        //    Paths.get(nombre).getFileName().toString()
        //    (import java.nio.file.Paths). "../../etc/passwd" -> "passwd".
        // OJO: el test exige "../../../etc/passwd" -> "passwd" y un nombre limpio
        //      "clean.txt" -> "clean.txt".
        // CUIDADO: una alternativa con split("/") falla con separadores Windows
        //          "\\"; Paths.get(...).getFileName() es lo robusto. Reutilízalo
        //          en pasoExtra09.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra04");
    }

    /**
     * Reto Extra 5: Cálculo del hash MD5 de verificación.
     * Calcula y devuelve el hash MD5 (como cadena hexadecimal) del contenido en bytes del archivo,
     * útil para verificar la integridad de la subida. Si el archivo es nulo, devuelve "".
     */
    public static String pasoExtra05(MultipartFile file) {
        // GUÍA: MD5 del contenido en hexadecimal (verificación de integridad).
        // 1. Si file es null -> "".
        // 2. byte[] datos = file.getBytes();  // declara throws/try IOException.
        // 3. MessageDigest md = MessageDigest.getInstance("MD5");
        //    byte[] hash = md.digest(datos);
        // 4. Convierte cada byte a 2 dígitos hex en minúscula. El patrón canónico:
        //    StringBuilder sb; for (byte b : hash) sb.append(String.format("%02x", b));
        // OJO: el test conoce el MD5 de "hello": debe salir EXACTAMENTE
        //      "5d41402abc4b2a76b9719d911017c592" (minúsculas, 32 chars).
        //      Si usas %02X (mayúsculas) el assertEquals falla.
        // CULTURA: MD5 sirve para integridad (¿llegó intacto?), NO para seguridad
        //          (está roto criptográficamente). Para ETags fuertes usarás
        //          SHA-256 en Ej060.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra05");
    }

    /**
     * Reto Extra 6: Suma de tamaños de múltiples archivos.
     * Calcula la suma total en bytes de todos los archivos de la lista recibida.
     */
    public static long pasoExtra06(List<MultipartFile> files) {
        // GUÍA: teoría 1.3 (stream + reducción numérica) aplicada a uploads.
        // 1. Si files es null -> 0.
        // 2. return files.stream().filter(Objects::nonNull)
        //              .mapToLong(MultipartFile::getSize).sum();
        //    mapToLong porque getSize() es long y la suma puede ser grande.
        // OJO: el test suma byte[15] + byte[25] = 40L (long, no int).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra06");
    }

    /**
     * Reto Extra 7: Detección elemental de imágenes.
     * Comprueba si el archivo es una imagen basándose tanto en su Content-Type ("image/*")
     * como en que su nombre original termine en una extensión típica de imagen (.jpg, .jpeg, .png, .gif).
     */
    public static boolean pasoExtra07(MultipartFile file) {
        // GUÍA: doble comprobación (MIME Y extensión) para reducir falsos.
        // 1. Si file es null -> false.
        // 2. ctype = file.getContentType(); empieza por "image/"?
        // 3. nombre = file.getOriginalFilename() en minúsculas; ¿termina en
        //    .jpg, .jpeg, .png o .gif?
        // 4. Devuelve true solo si AMBAS condiciones se cumplen.
        // PISTA: ctype != null && ctype.startsWith("image/")
        //        && (n.endsWith(".jpg") || n.endsWith(".jpeg") ...)
        // OJO: el test: photo.jpg + image/jpeg -> true; doc.pdf + application/pdf
        //      -> false. Reutiliza la idea de extensión del reto 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra07");
    }

    /**
     * Reto Extra 8: Extracción de cabeceras de columnas CSV.
     * Lee de forma eficiente la primera línea del archivo subido (simulando que es un archivo de texto CSV)
     * y devuelve una lista con las cabeceras de las columnas separadas por comas.
     */
    public static List<String> pasoExtra08(MultipartFile file) {
        // GUÍA: leer SOLO la primera línea de un CSV (las cabeceras de columna).
        // 1. Si file es null/vacío -> lista vacía.
        // 2. Lee la primera línea sin cargar todo: con getInputStream() envuelto
        //    en un BufferedReader y readLine(), o getBytes()->new String(...).
        //    Recomendado y eficiente (teoría 1.9, try-with-resources):
        //    try (var br = new BufferedReader(new InputStreamReader(
        //             file.getInputStream(), StandardCharsets.UTF_8))) {
        //        String primera = br.readLine();
        //        ...
        //    }
        // 3. Si primera es null -> lista vacía; si no, split(",") y trim de cada
        //    columna -> List.
        // OJO: el contenido es "id,nombre,edad\n1,Juan,20"; el test espera 3
        //      cabeceras: "id","nombre","edad" (NO la segunda fila de datos).
        // PISTA: Arrays.stream(primera.split(",")).map(String::trim).toList();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra08");
    }

    /**
     * Reto Extra 9: Simulación de almacenamiento físico.
     * Resuelve y devuelve la ruta absoluta final donde se guardaría el archivo dentro de un
     * directorio destino ('destinationDir') usando el nombre saneado del archivo.
     */
    public static String pasoExtra09(MultipartFile file, String destinationDir) {
        // GUÍA: teoría 6.3. Construye la ruta destino con el nombre SANEADO
        // (reutiliza pasoExtra04, no vuelvas a saner a mano).
        // 1. String nombre = pasoExtra04(file);  // ya quita traversal.
        // 2. Une el directorio y el nombre con Paths:
        //    return Paths.get(destinationDir, nombre).toString();
        //    Paths se encarga del separador correcto del SO.
        // OJO: el test pasa dir "C:/uploads" y "photo.png" y comprueba que la ruta
        //      (tras normalizar '\'->'/') TERMINE en "uploads/photo.png". No
        //      concatenes con '/' a mano: deja que Paths ponga el separador.
        // CULTURA: combinar saneo + Paths.get es el patrón seguro de guardado en
        //          un servicio de almacenamiento real (lo verás en proyectos con S3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra09");
    }

    /**
     * Reto Extra 10: Comprobación defensiva de archivos vacíos.
     * Determina si el objeto 'file' es nulo, está vacío (isEmpty) o su tamaño en bytes es menor o igual a cero.
     */
    public static boolean pasoExtra10(MultipartFile file) {
        // GUÍA: una expresión. "Está vacío" = null O isEmpty() O tamaño <= 0.
        // return file == null || file.isEmpty() || file.getSize() <= 0;
        // OJO: el orden importa por cortocircuito: comprueba null PRIMERO para no
        //      llamar a isEmpty() sobre null. El test exige true para byte[0] y
        //      para null, y false para byte[10].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasoExtra10");
    }

}
