package bloque6b;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;

/**
 * EJERCICIO 60 — Deserializar XML a Objetos con JAXB
 * 📋 ENTRA EN EXAMEN — Tema 08 (Unmarshaller, unmarshal)
 * Teoria: teoria/06B_JSON_XML.md (seccion 9)
 *
 * Contexto: El sistema contable necesita importar facturas XML recibidas
 * desde proveedores externos.
 */
public class Ej60_DeserializarXML {

    /**
     * Deserializa una FacturaXML desde un fichero .xml.
     */
    public static FacturaXML deserializarFactura(String ruta) throws JAXBException {
        // TODO 1: Crear JAXBContext con newInstance(FacturaXML.class).
        //         Crear Unmarshaller con createUnmarshaller().
        //         unmarshal(new File(ruta)) y hacer cast a FacturaXML.
        return null;
    }

    /**
     * Deserializa una FacturaXML desde un String XML.
     */
    public static FacturaXML desdeString(String xml) throws JAXBException {
        // TODO 2: Crear JAXBContext y Unmarshaller.
        //         Crear StringReader con el xml. unmarshal(new StringReader(xml)).
        //         Cast y devolver.
        return null;
    }

    /**
     * Verifica integridad: serializa a XML y luego deserializa.
     * Devuelve true si equals() da true.
     */
    public static boolean verificarIntegridad(String ruta, FacturaXML original) throws JAXBException {
        // TODO 3: Serializar la factura con Marshaller. Deserializar con Unmarshaller.
        //         Comparar con equals().
        return false;
    }

    /**
     * Intenta deserializar un fichero invalido. Devuelve el nombre
     * de la excepcion o "OK".
     */
    public static String intentarDeserializar(String ruta) {
        // TODO 4: try-catch. Si funciona, "OK". Si falla, e.getClass().getSimpleName().
        return "";
    }

    /**
     * Lee el ID de la factura deserializada.
     */
    public static int leerIdFactura(String ruta) throws JAXBException {
        // TODO 5: Deserializar. Devolver factura.getId().
        return 0;
    }

    /**
     * Lee el total de la factura deserializada.
     */
    public static double leerTotalFactura(String ruta) throws JAXBException {
        // TODO 6: Deserializar. Devolver factura.getTotal().
        return 0.0;
    }

    /**
     * Devuelve true si FacturaXML tiene constructor vacio.
     */
    public static boolean tieneConstructorVacio() {
        // TODO 7: Intentar crear new FacturaXML(). Si no lanza excepcion, true.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 60: Deserializar XML ===\n");

        String dir = "temp/bloque6b";

        FacturaXML f = deserializarFactura(dir + "/factura.xml");
        System.out.println("Deserializada: " + f);
        System.out.println("ID: " + leerIdFactura(dir + "/factura.xml"));
        System.out.println("Total: " + leerTotalFactura(dir + "/factura.xml"));
    }
}
