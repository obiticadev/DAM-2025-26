package com.masterclass.api.b38_fxreports;

import java.nio.charset.StandardCharsets;

/**
 * Ejercicio 300 · Compilar {@code .jrxml}, {@code fillReport} y exportar a PDF.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 2).
 *
 * <p>El pipeline de Jasper tiene tres pasos: <b>compilar</b> la plantilla {@code factura.jrxml}
 * (diseño XML) a un {@code factura.jasper} (binario), <b>rellenarla</b> ({@code fillReport}) con la
 * fuente de datos de Ej299 produciendo un {@code JasperPrint}, y <b>exportar</b> ese print a un
 * PDF ({@code JasperExportManager.exportReportToPdf} -> {@code byte[]}). El código real está en la
 * teoría y en las GUÍAS (necesita el motor Jasper). Lo que SÍ se prueba aquí, determinista y sin
 * dependencias, es <b>reconocer y validar el resultado</b>: todo PDF empieza por la firma
 * {@code %PDF-} (su <i>magic number</i>), y los nombres de fichero del pipeline siguen un patrón.
 * Un byte se compara como entero; {@code "%PDF-"} en ASCII es {@code 25 50 44 46 2D} (hex).
 */
public final class Ej300JasperFillExport {

    private Ej300JasperFillExport() {
    }

    /**
     * La firma con la que arranca TODO fichero PDF: los 5 bytes de {@code "%PDF-"} en ASCII. Un
     * visor decide si un fichero es PDF mirando justo estos bytes.
     *
     * @return {@code byte[]} de 5 elementos {@code {0x25,0x50,0x44,0x46,0x2D}}; {@code null} sin implementar
     */
    public static byte[] cabeceraPdf() {
        // TODO 1: en ASCII, '%'=0x25, 'P'=0x50, 'D'=0x44, 'F'=0x46, '-'=0x2D.
        // TODO 2: crea y devuelve new byte[]{0x25, 0x50, 0x44, 0x46, 0x2D}.
        //         (truco: "%PDF-".getBytes(java.nio.charset.StandardCharsets.US_ASCII) da lo mismo).
        // TODO 3: el test compara estos 5 bytes uno a uno.
        return null;
    }

    /**
     * Comprueba si un array de bytes es un PDF: ¿empieza por la cabecera {@code %PDF-}?
     *
     * @param datos bytes del documento (puede ser null)
     * @return {@code true} solo si los 5 primeros bytes son {@code %PDF-}
     */
    public static boolean esPdf(byte[] datos) {
        // TODO 4: si datos es null o tiene menos de 5 bytes, devuelve false (no cabe la firma).
        // TODO 5: obtén la cabecera esperada con cabeceraPdf().
        // TODO 6: compara datos[i] con cabecera[i] para i = 0..4; si alguno difiere, devuelve false.
        // TODO 7: si los 5 coinciden, devuelve true.
        return false;
    }

    /**
     * Nombre del fichero compilado: al compilar {@code factura.jrxml} (diseño) Jasper genera
     * {@code factura.jasper} (binario que se rellena). Solo cambia la extensión.
     *
     * @param jrxml nombre de la plantilla, p.ej. {@code "factura.jrxml"}
     * @return el mismo nombre con extensión {@code .jasper}; {@code ""} sin implementar
     */
    public static String nombreCompilado(String jrxml) {
        // TODO 8: si jrxml es null o vacío, devuelve "".
        // TODO 9: localiza el último punto (lastIndexOf('.')) y quédate con la parte previa (base).
        //         Si no hay punto, la base es el nombre entero.
        // TODO 10: devuelve base + ".jasper" (el test prueba "factura.jrxml" -> "factura.jasper").
        return "";
    }

    public static void main(String[] args) {
        byte[] fakePdf = "%PDF-1.7\n...".getBytes(StandardCharsets.US_ASCII);
        System.out.println("Cabecera: " + java.util.Arrays.toString(cabeceraPdf()));
        System.out.println("¿Es PDF?: " + esPdf(fakePdf));
        System.out.println("Compilado: " + nombreCompilado("factura.jrxml"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Extensión de un fichero.
     * Devuelve lo que va tras el último punto, en minúsculas (p.ej. "PDF" -> "pdf").
     */
    public static String extensionDe(String nombre) {
        // GUÍA: teoría 2.1 (cada paso del pipeline cambia la extensión: .jrxml -> .jasper -> .pdf).
        // 1. Si nombre es null o no contiene '.', devuelve "".
        // 2. Quédate con la subcadena tras el último punto y pásala a minúsculas.
        // PISTA: nombre.substring(nombre.lastIndexOf('.') + 1).toLowerCase().
        // OJO: el test prueba "factura.PDF" -> "pdf"; "sinpunto" -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionDe");
    }

    /**
     * Reto Extra 2: ¿No está vacío?
     * Un PDF generado nunca debe ser de 0 bytes; comprueba que tiene contenido.
     */
    public static boolean tieneContenido(byte[] datos) {
        // GUÍA: teoría 2.4 (el primer assert de un test de export: "el byte[] no está vacío").
        // 1. Devuelve true solo si datos no es null y datos.length > 0.
        // OJO: el test prueba un array de 10 bytes -> true; null -> false; new byte[0] -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneContenido");
    }

    /**
     * Reto Extra 3: Versión del PDF.
     * La cabecera real es "%PDF-1.7": extrae la versión ("1.7") de los primeros bytes.
     */
    public static String versionPdf(byte[] datos) {
        // GUÍA: teoría 2.4 (tras "%PDF-" viene la versión del estándar; útil para depurar).
        // 1. Si !esPdf(datos), devuelve "".
        // 2. Lee desde el byte 5 los caracteres mientras sean dígito o '.': esa es la versión.
        // PISTA: recorre desde i=5; para cada byte, char c=(char)datos[i]; corta cuando
        //   !Character.isDigit(c) && c != '.'.
        // OJO: el test usa "%PDF-1.7\n..." -> "1.7" (se para en el salto de línea).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para versionPdf");
    }

    /**
     * Reto Extra 4: Tamaño en KB (redondeado hacia abajo).
     * Cuántos kilobytes ocupa el documento (1 KB = 1024 bytes).
     */
    public static int tamanoKb(byte[] datos) {
        // GUÍA: teoría 2.5 (los visores y las APIs muestran el peso del PDF en KB).
        // 1. Si datos es null, devuelve 0.
        // 2. Devuelve datos.length / 1024 (división entera = redondeo hacia abajo).
        // OJO: el test prueba 2048 bytes -> 2; 1500 bytes -> 1 (no llega a 2 KB).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoKb");
    }

    /**
     * Reto Extra 5: ¿Es un .jasper ya compilado?
     * Distingue la plantilla compilada del diseño fuente por su extensión.
     */
    public static boolean esJasperCompilado(String nombre) {
        // GUÍA: teoría 2.2 (no rellenes un .jrxml; rellenas el .jasper que sale de compilarlo).
        // 1. Devuelve true solo si nombre no es null y termina en ".jasper".
        // PISTA: reutiliza extensionDe(nombre) y compáralo con "jasper".
        // OJO: el test prueba "factura.jasper" -> true; "factura.jrxml" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJasperCompilado");
    }

    /**
     * Reto Extra 6: Ruta de la plantilla dentro de resources.
     * Construye la ruta del classpath donde vive el diseño: "reports/<base>.jrxml".
     */
    public static String rutaPlantilla(String base) {
        // GUÍA: teoría 2.3 (las .jrxml viven en src/main/resources/reports y se cargan por classpath).
        // 1. Si base es null o vacío, devuelve "".
        // 2. Devuelve "reports/" + base + ".jrxml".
        // OJO: el test prueba "factura" -> "reports/factura.jrxml".
        // CULTURA: cargar un recurso del classpath es getClass().getResourceAsStream("/reports/..."),
        //   el mismo mecanismo que usaste con las plantillas Thymeleaf de b25.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaPlantilla");
    }

    /**
     * Reto Extra 7: ¿Coincide la firma (magic number) genérica?
     * ¿Empieza 'datos' por TODOS los bytes de 'firma'? (sirve para PDF, ZIP, PNG...).
     */
    public static boolean empiezaPor(byte[] datos, byte[] firma) {
        // GUÍA: teoría 2.6 (detectar cualquier formato = comparar sus primeros bytes con su magic).
        // 1. Si datos o firma son null, o datos es más corto que firma, devuelve false.
        // 2. Compara datos[i] con firma[i] para i = 0..firma.length-1; a la primera diferencia false.
        // 3. Si todos coinciden, true.
        // PISTA: esto es esPdf generalizado a cualquier firma -> reutilízalo en Ej304.
        // OJO: el test usa datos={1,2,3,4} firma={1,2} -> true; firma={1,9} -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empiezaPor");
    }

    /**
     * Reto Extra 8: Cabecera como texto.
     * Devuelve la firma del PDF leída como cadena ASCII ("%PDF-").
     */
    public static String cabeceraComoTexto() {
        // GUÍA: teoría 2.4 (los bytes de la firma son ASCII imprimible; se leen como String).
        // 1. Toma cabeceraPdf() y conviértela a String con new String(bytes, US_ASCII).
        // PISTA: new String(cabeceraPdf(), java.nio.charset.StandardCharsets.US_ASCII).
        // OJO: el test espera exactamente "%PDF-" (5 caracteres).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeceraComoTexto");
    }

    /**
     * Reto Extra 9: Round-trip de bytes.
     * Comprueba que dos arrays de bytes son idénticos (lo que escribiste = lo que leíste del disco).
     */
    public static boolean mismosBytes(byte[] a, byte[] b) {
        // GUÍA: teoría 2.7 (guardar el byte[] del PDF y releerlo debe dar EXACTAMENTE lo mismo).
        // 1. Usa java.util.Arrays.equals(a, b) (gestiona nulls y longitudes por ti).
        // 2. Devuelve ese resultado.
        // OJO: el test compara {1,2,3} con {1,2,3} -> true; con {1,2} -> false.
        // CULTURA: este round-trip (escribir/leer y comparar) es el patrón de los tests de IO de b26.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismosBytes");
    }

    /**
     * Reto Extra 10: Resumen del fichero exportado.
     * Devuelve una etiqueta tipo "factura.pdf (2 KB)" para mostrar en la UI tras exportar.
     */
    public static String resumenExport(String nombre, byte[] datos) {
        // GUÍA: teoría 2.5 (el mensaje "Informe guardado: factura.pdf (2 KB)" de la barra de estado).
        // 1. Si nombre es null, usa "" ; calcula los KB con tamanoKb(datos).
        // 2. Devuelve nombre + " (" + kb + " KB)".
        // PISTA: reutiliza tamanoKb(datos); concatena con el formato exacto del OJO.
        // OJO: el test prueba "factura.pdf" + 2048 bytes -> "factura.pdf (2 KB)" (espacio antes del '(').
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resumenExport");
    }
}
