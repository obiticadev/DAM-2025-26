package bloque6b;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.*;

/**
 * EJERCICIO 59 — Serializar Objetos a XML con JAXB
 * 📋 ENTRA EN EXAMEN — Tema 08 (Marshaller, @XmlRootElement)
 * Teoria: teoria/06B_JSON_XML.md (secciones 7-8)
 *
 * Contexto: Un sistema contable necesita exportar facturas en formato XML
 * para cumplir con la normativa fiscal.
 */
public class Ej59_SerializarXML {

    /**
     * Serializa una FacturaXML a un fichero .xml.
     */
    public static void serializarFactura(String ruta, FacturaXML factura) throws JAXBException {
        // TODO 1: Crear JAXBContext con JAXBContext.newInstance(FacturaXML.class).
        //         Crear Marshaller con context.createMarshaller().
        //         Configurar formato bonito: marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true).
        //         marshal(factura, new File(ruta)).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Serializa una FacturaXML a un String XML.
     */
    public static String facturaAXmlString(FacturaXML factura) throws JAXBException {
        // TODO 2: Crear JAXBContext y Marshaller. Crear StringWriter.
        //         marshal(factura, stringWriter). Devolver stringWriter.toString().
        return "";
    }

    /**
     * Serializa una factura sin formato bonito (en una sola linea).
     */
    public static String serializarCompacto(FacturaXML factura) throws JAXBException {
        // TODO 3: Igual que TODO 2 pero SIN la propiedad JAXB_FORMATTED_OUTPUT.
        return "";
    }

    /**
     * Serializa con formato bonito y la devuelve como String.
     */
    public static String serializarBonito(FacturaXML factura) throws JAXBException {
        // TODO 4: Igual que TODO 2 PERO con JAXB_FORMATTED_OUTPUT = true.
        return "";
    }

    /**
     * Verifica si la clase FacturaXML tiene la anotacion @XmlRootElement.
     */
    public static boolean tieneAnotacion() {
        // TODO 5: Usar FacturaXML.class.isAnnotationPresent(jakarta.xml.bind.annotation.XmlRootElement.class).
        return false;
    }

    /**
     * Devuelve true si el fichero XML no esta vacio.
     */
    public static boolean ficheroNoVacio(String ruta) {
        // TODO 6: Crear File. Si existe y length() > 0, true.
        return false;
    }

    /**
     * Devuelve el tamano del fichero XML en bytes.
     */
    public static long tamanoFichero(String ruta) {
        // TODO 7: Crear File. Si existe, length(). Si no, -1.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 59: Serializar XML ===\n");

        String dir = "temp/bloque6b";
        new File(dir).mkdirs();

        FacturaXML f = new FacturaXML(1, 150.75);
        serializarFactura(dir + "/factura.xml", f);
        System.out.println("XML:\n" + serializarBonito(f));
        System.out.println("Tamano: " + tamanoFichero(dir + "/factura.xml") + " bytes");
        System.out.println("Tiene @XmlRootElement: " + tieneAnotacion());
    }
}
