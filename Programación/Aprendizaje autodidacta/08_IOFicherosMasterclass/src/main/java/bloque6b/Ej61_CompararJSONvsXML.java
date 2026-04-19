package bloque6b;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.*;

/**
 * EJERCICIO 61 — Comparar JSON vs XML
 * 📋 ENTRA EN EXAMEN — Tema 08 (comparacion de formatos)
 * Teoria: teoria/06B_JSON_XML.md (seccion 10)
 *
 * Contexto: El equipo de arquitectura necesita decidir entre JSON y XML
 * para un nuevo servicio. Vamos a comparar ambos formatos con datos reales.
 */
public class Ej61_CompararJSONvsXML {

    /**
     * Guarda un ProductoJSON en formato JSON y devuelve el tamano en bytes.
     */
    public static long guardarEnJSON(String ruta, ProductoJSON producto) throws IOException {
        // TODO 1: ObjectMapper -> writeValue. Devolver new File(ruta).length().
        return 0;
    }

    /**
     * Guarda una FacturaXML en formato XML y devuelve el tamano en bytes.
     */
    public static long guardarEnXML(String ruta, FacturaXML factura) throws JAXBException {
        // TODO 2: JAXBContext -> Marshaller -> marshal. Devolver new File(ruta).length().
        return 0;
    }

    /**
     * Devuelve el contenido JSON como String.
     */
    public static String contenidoJSON(ProductoJSON producto) throws IOException {
        // TODO 3: ObjectMapper -> writeValueAsString.
        return "";
    }

    /**
     * Devuelve el contenido XML como String.
     */
    public static String contenidoXML(FacturaXML factura) throws JAXBException {
        // TODO 4: JAXBContext -> Marshaller -> marshal a StringWriter.
        //         Devolver stringWriter.toString().
        return "";
    }

    /**
     * Compara tamanos y devuelve "JSON" si JSON es mas pequeno,
     * "XML" si XML es mas pequeno, "IGUALES" si iguales.
     */
    public static String formatoMasPequeno(String rutaJSON, String rutaXML) {
        // TODO 5: Obtener tamano de ambos ficheros con File.length().
        //         Comparar y devolver el formato ganador.
        return "";
    }

    /**
     * Devuelve la diferencia de tamano en bytes: tamanoXML - tamanoJSON.
     * Positivo = XML es mas grande. Negativo = JSON es mas grande.
     */
    public static long diferenciaBytes(String rutaJSON, String rutaXML) {
        // TODO 6: Calcular y devolver la diferencia.
        return 0;
    }

    /**
     * Devuelve un resumen del analisis comparativo como String.
     * Formato: "JSON: X bytes | XML: Y bytes | Diferencia: Z bytes | Ganador: JSON/XML"
     */
    public static String resumenComparativo(String rutaJSON, String rutaXML) {
        // TODO 7: Obtener tamanos de ambos. Calcular diferencia.
        //         Formatear y devolver el String resumen.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 61: Comparar JSON vs XML ===\n");

        String dir = "temp/bloque6b";
        new File(dir).mkdirs();

        ProductoJSON pj = new ProductoJSON("Laptop", 1200.99);
        FacturaXML fx = new FacturaXML(1, 1200.99);

        long tamJSON = guardarEnJSON(dir + "/comp.json", pj);
        long tamXML = guardarEnXML(dir + "/comp.xml", fx);

        System.out.println("JSON: " + tamJSON + " bytes");
        System.out.println("XML:  " + tamXML + " bytes");
        System.out.println("Mas pequeno: " + formatoMasPequeno(dir + "/comp.json", dir + "/comp.xml"));
        System.out.println(resumenComparativo(dir + "/comp.json", dir + "/comp.xml"));
    }
}
