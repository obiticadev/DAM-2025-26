package com.masterclass.api.b38_fxreports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ejercicio 304 · Exportar a PDF/XLSX/CSV y comparar formatos.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 6).
 *
 * <p>El mismo {@code JasperPrint} se puede exportar a varios formatos con distintos
 * {@code Exporter}: {@code JRPdfExporter} (PDF), {@code JRXlsxExporter} (Excel),
 * {@code JRCsvExporter} (texto separado). Cada formato deja una huella distinta en sus primeros
 * bytes —su <i>magic number</i>—: un PDF empieza por {@code %PDF-}, un XLSX (que por dentro es un
 * ZIP) empieza por {@code PK}, y un CSV es texto plano sin firma. Lo testeable es
 * <b>reconocer el formato</b> por esos bytes y <b>construir el CSV</b> a mano (escapando bien las
 * celdas), que es el único de los tres que se genera sin librerías. El export real de Jasper se
 * enseña en la teoría.
 */
public final class Ej304ExportFormats {

    private Ej304ExportFormats() {
    }

    /**
     * <i>Magic number</i> esperado de cada formato: los bytes con los que empieza el fichero.
     *
     * @param formato {@code "pdf"}, {@code "xlsx"} o {@code "csv"} (sin distinguir mayúsculas)
     * @return la firma: PDF {@code %PDF-}, XLSX {@code PK 03 04}, CSV {@code byte[0]} (sin firma);
     *         {@code null} sin implementar
     */
    public static byte[] magicDe(String formato) {
        // TODO 1: si formato es null, devuelve null.
        // TODO 2: normaliza con formato.toLowerCase() y compara:
        //         "pdf"  -> new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D}  (%PDF-).
        // TODO 3: "xlsx" -> new byte[]{0x50, 0x4B, 0x03, 0x04}        (PK..; un XLSX es un ZIP).
        // TODO 4: "csv"  -> new byte[0] (texto plano, no tiene firma); cualquier otro -> null.
        //         (el test compara las firmas de pdf y xlsx, y que csv es de longitud 0).
        return null;
    }

    /**
     * Detecta el formato de unos bytes mirando su firma.
     *
     * @param datos bytes del fichero (puede ser null)
     * @return {@code "pdf"}, {@code "xlsx"} o {@code "desconocido"}
     */
    public static String detectarFormato(byte[] datos) {
        // TODO 5: si datos es null, devuelve "desconocido".
        // TODO 6: si empieza por la firma de pdf -> "pdf"; si empieza por la de xlsx -> "xlsx".
        //         (puedes reutilizar Ej300.empiezaPor(datos, magicDe("pdf")) cuando lo implementes,
        //          o comparar los primeros bytes a mano).
        // TODO 7: en cualquier otro caso, devuelve "desconocido".
        //         (el test: bytes "%PDF-1.7" -> "pdf"; bytes "PK..." -> "xlsx"; texto -> "desconocido").
        return "";
    }

    /**
     * Convierte una tabla (lista de filas, cada fila un array de celdas) en texto CSV: las celdas
     * se separan con {@code ;} y las filas con {@code \n}. Las celdas se escapan según RFC 4180
     * (ver {@code celdaCsv}).
     *
     * @param filas filas de la tabla (puede ser null)
     * @return el CSV completo; {@code ""} sin implementar
     */
    public static String aCsv(List<String[]> filas) {
        // TODO 8: si filas es null o vacía, devuelve "".
        // TODO 9: por cada fila, une sus celdas con ";" usando celdaCsv(celda) en cada una.
        // TODO 10: une las filas con "\n" (sin \n final) y devuelve el resultado.
        //          (el test: [{"a","b"},{"c","d"}] -> "a;b\nc;d").
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Magic pdf: " + Arrays.toString(magicDe("pdf")));
        System.out.println("Formato de %PDF: " + detectarFormato("%PDF-1.7".getBytes()));
        System.out.println("CSV: " + aCsv(List.of(new String[]{"a", "b"}, new String[]{"c", "d"})));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Extensión de fichero del formato.
     * "pdf" -> ".pdf", "xlsx" -> ".xlsx", "csv" -> ".csv".
     */
    public static String extensionDeFormato(String formato) {
        // GUÍA: teoría 6.1 (cada exporter escribe un fichero con su extensión).
        // 1. Si formato es null, devuelve "".
        // 2. Devuelve "." + formato.toLowerCase().
        // OJO: el test: "PDF" -> ".pdf"; null -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionDeFormato");
    }

    /**
     * Reto Extra 2: Tipo MIME del formato.
     * El Content-Type con que una API REST devolvería el fichero.
     */
    public static String mimeDe(String formato) {
        // GUÍA: teoría 6.2 (al descargar el informe por HTTP, el navegador necesita el MIME correcto).
        // 1. "pdf"  -> "application/pdf".
        // 2. "xlsx" -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".
        // 3. "csv"  -> "text/csv". Cualquier otro o null -> "application/octet-stream".
        // OJO: el test comprueba los tres MIME y que un formato raro cae en octet-stream.
        // CULTURA: este Content-Type es justo la cabecera que pusiste al devolver el PDF en b05/b25.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mimeDe");
    }

    /**
     * Reto Extra 3: ¿Es un formato binario?
     * PDF y XLSX son binarios; CSV es texto.
     */
    public static boolean esBinario(String formato) {
        // GUÍA: teoría 6.2 (binario -> escribes byte[]; texto -> escribes String con su charset).
        // 1. Devuelve true si formato (en minúsculas) es "pdf" o "xlsx"; false en otro caso (incluido csv).
        // OJO: el test: "pdf" -> true; "xlsx" -> true; "csv" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBinario");
    }

    /**
     * Reto Extra 4: Escapar una celda CSV (RFC 4180).
     * Si la celda contiene ';', '"' o salto de línea, va entre comillas y las comillas internas se duplican.
     */
    public static String celdaCsv(String valor) {
        // GUÍA: teoría 6.3 (sin escapar, un ';' dentro de un dato rompe las columnas).
        // 1. Si valor es null, devuelve "" (celda vacía).
        // 2. Si NO contiene ';' ni '"' ni '\n', devuelve valor tal cual.
        // 3. Si lo contiene: duplica las comillas (valor.replace("\"","\"\"")) y envuélvelo en comillas.
        // PISTA: return "\"" + valor.replace("\"", "\"\"") + "\"";
        // OJO: el test: "ana" -> "ana"; "a;b" -> "\"a;b\""; "di \"hola\"" -> "\"di \"\"hola\"\"\"".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para celdaCsv");
    }

    /**
     * Reto Extra 5: Una fila CSV.
     * Une las celdas de una fila con ';', escapando cada una.
     */
    public static String filaCsv(String[] celdas) {
        // GUÍA: teoría 6.3 (una fila = celdas escapadas unidas por el separador).
        // 1. Si celdas es null, devuelve "".
        // 2. Aplica celdaCsv a cada celda y únelas con ";".
        // PISTA: usa un StringBuilder o String.join(";", ...) sobre las celdas ya escapadas.
        // OJO: el test: {"a","b;c"} -> "a;\"b;c\"".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filaCsv");
    }

    /**
     * Reto Extra 6: CSV con fila de cabecera.
     * Antepone una fila de nombres de columna al CSV de datos.
     */
    public static String aCsvConCabecera(String[] columnas, List<String[]> filas) {
        // GUÍA: teoría 6.4 (un CSV "de verdad" lleva la cabecera de columnas en la primera línea).
        // 1. Construye la línea de cabecera con filaCsv(columnas).
        // 2. Construye el cuerpo con aCsv(filas).
        // 3. Si el cuerpo está vacío, devuelve solo la cabecera; si no, cabecera + "\n" + cuerpo.
        // OJO: el test: columnas {"id","nombre"}, filas [{"1","Ana"}] -> "id;nombre\n1;Ana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aCsvConCabecera");
    }

    /**
     * Reto Extra 7: Contar columnas de una línea CSV simple.
     * Cuántas celdas hay (separadas por ';'), en una línea SIN comillas.
     */
    public static int contarColumnas(String lineaCsv) {
        // GUÍA: teoría 6.5 (validar que todas las filas tienen el mismo nº de columnas).
        // 1. Si lineaCsv es null o vacía, devuelve 0.
        // 2. Devuelve lineaCsv.split(";", -1).length (el -1 conserva las celdas vacías del final).
        // PISTA: split(";", -1) -> "a;;b" da 3, "a;b;" da 3 (no descarta la última vacía).
        // OJO: el test: "a;b;c" -> 3; "a;;b" -> 3; "" -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarColumnas");
    }

    /**
     * Reto Extra 8: Formatos disponibles.
     * La lista de formatos a los que se puede exportar, en orden.
     */
    public static List<String> formatosDisponibles() {
        // GUÍA: teoría 6.1 (los items del menú "Exportar como...").
        // 1. Devuelve una lista con "pdf", "xlsx", "csv" en ese orden.
        // PISTA: List.of("pdf", "xlsx", "csv").
        // OJO: el test comprueba tamaño 3 y que el primero es "pdf".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatosDisponibles");
    }

    /**
     * Reto Extra 9: Nombre de fichero de salida.
     * Combina un nombre base con la extensión del formato ("factura" + "pdf" -> "factura.pdf").
     */
    public static String nombreSalida(String base, String formato) {
        // GUÍA: teoría 6.1 (el nombre que propone el diálogo "Guardar como").
        // 1. Si base es null o vacío, usa "informe".
        // 2. Devuelve base + extensionDeFormato(formato).
        // PISTA: reutiliza extensionDeFormato(formato).
        // OJO: el test: ("factura","pdf") -> "factura.pdf"; (null,"csv") -> "informe.csv".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreSalida");
    }

    /**
     * Reto Extra 10: ¿Coinciden los bytes con el formato declarado?
     * Verifica que un byte[] realmente es del formato que dice ser (defensa anti-corrupción).
     */
    public static boolean coincideFormato(byte[] datos, String formato) {
        // GUÍA: teoría 6.6 (antes de fiarte de la extensión, comprueba la firma real del fichero).
        // 1. Para "csv" no hay firma: considera válido si datos no es null (cualquier texto vale).
        // 2. Para "pdf"/"xlsx": detectarFormato(datos) debe coincidir con formato.toLowerCase().
        // PISTA: reutiliza detectarFormato(datos) y compáralo con el formato esperado.
        // OJO: el test: ("%PDF-..".bytes, "pdf") -> true; (esos mismos bytes, "xlsx") -> false;
        //   (cualquier texto, "csv") -> true.
        // CULTURA: este chequeo de magic number es el mismo que harías al subir un fichero por la API
        //   (b05) para no fiarte de la extensión que manda el cliente -> seguridad (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coincideFormato");
    }

    /** Helper de demostración: arma una lista mutable de filas. */
    static List<String[]> filas(String[]... fs) {
        return new ArrayList<>(List.of(fs));
    }
}
