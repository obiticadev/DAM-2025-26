package com.masterclass.api.b16_xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Ejercicio 143 · JAXB binding objeto ↔ XML.
 *
 * <p>Teoría: {@code teoria/16_XML_y_Ficheros.md} (sección 16.1).
 *
 * <p>JAXB (`jakarta.xml.bind`) serializa un POJO anotado a XML con un
 * {@code Marshaller} y lo reconstruye con un {@code Unmarshaller}. El
 * <em>round-trip</em> (objeto → xml → objeto) debe preservar el estado.
 */
public final class Ej143JaxbBinding {

    private Ej143JaxbBinding() {
    }

    /**
     * Serializa un {@link Libro143} a su representación XML.
     *
     * @param libro instancia a serializar (no null)
     * @return el documento XML como String
     * @throws IllegalArgumentException si libro es null
     * @throws RuntimeException         si falla el marshalling
     */
    public static String aXml(Libro143 libro) {
        // TODO 1: si libro es null -> IllegalArgumentException.
        // TODO 2: crea un JAXBContext con JAXBContext.newInstance(Libro143.class).
        // TODO 3: obtén un Marshaller con context.createMarshaller().
        // TODO 4: activa Marshaller.JAXB_FORMATTED_OUTPUT a true para XML legible.
        // TODO 5: prepara un StringWriter como destino del marshal.
        // TODO 6: invoca marshaller.marshal(libro, writer).
        // TODO 7: captura JAXBException y reenvíala como RuntimeException.
        // TODO 8: el @XmlRootElement de Libro143 define el nombre del elemento raíz.
        // TODO 9: @XmlAttribute mapea isbn como atributo, no como elemento hijo.
        // TODO 10: devuelve writer.toString() (el XML resultante).
        return null;
    }

    /**
     * Reconstruye un {@link Libro143} desde su XML.
     *
     * @param xml documento XML válido (no null ni en blanco)
     * @return el objeto reconstruido
     * @throws IllegalArgumentException si xml es null o en blanco
     * @throws RuntimeException         si falla el unmarshalling
     */
    public static Libro143 desdeXml(String xml) {
        // TODO 1: si xml es null o isBlank() -> IllegalArgumentException.
        // TODO 2: crea un JAXBContext con JAXBContext.newInstance(Libro143.class).
        // TODO 3: obtén un Unmarshaller con context.createUnmarshaller().
        // TODO 4: envuelve el xml en un StringReader.
        // TODO 5: invoca unmarshaller.unmarshal(reader).
        // TODO 6: castea el resultado a Libro143.
        // TODO 7: captura JAXBException y reenvíala como RuntimeException.
        // TODO 8: un xml mal formado debe propagar el fallo, no devolver null.
        // TODO 9: el round-trip debe cumplir aXml(desdeXml(xml)) equivalente.
        // TODO 10: devuelve el Libro143 reconstruido.
        return null;
    }

    public static void main(String[] args) {
        Libro143 l = new Libro143("978-84", "Clean Code", 2008);
        String xml = aXml(l);
        System.out.println(xml);
        System.out.println(desdeXml(xml));
    }
}

/**
 * Modelo JAXB del ejercicio 143 (package-private, sufijado para evitar
 * colisiones). El constructor vacío es obligatorio para el unmarshalling.
 */
@XmlRootElement(name = "libro")
@XmlAccessorType(XmlAccessType.FIELD)
final class Libro143 {

    @XmlAttribute
    private String isbn;

    @XmlElement
    private String titulo;

    @XmlElement
    private int anio;

    Libro143() {
    }

    Libro143(String isbn, String titulo, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
    }

    String getIsbn() {
        return isbn;
    }

    String getTitulo() {
        return titulo;
    }

    int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return "Libro143{isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + "}";
    }
}
