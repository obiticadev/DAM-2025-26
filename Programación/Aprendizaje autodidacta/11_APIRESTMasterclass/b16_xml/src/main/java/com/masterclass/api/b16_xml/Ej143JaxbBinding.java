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

    /**
     * RETO EXTRA 01: Valida que el ISBN no sea nulo y tenga formato básico.
     */
    public static boolean extra01ValidarFormatoIsbn(String isbn) {
        // GUÍA: teoría 16.1 (validación de datos antes del binding).
        // 1. Si isbn es null -> lanza IllegalArgumentException (el test lo exige
        //    con assertThrows; NO devuelvas false en ese caso).
        // 2. Si no es null, comprueba el formato "dígitos-dígitos".
        // PISTA: return isbn.matches("\\d+-\\d+");  (o "[0-9-]+").
        // OJO: el test manda "978-84" -> true e "invalid-isbn" -> false. El
        //   segundo tiene letras, así que un patrón que solo admita dígitos y
        //   guion lo rechaza. null va por la rama de excepción, no por matches.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra01ValidarFormatoIsbn");
    }

    /**
     * RETO EXTRA 02: Remueve espacios extra y tabulaciones para compactar el XML.
     */
    public static String extra02CompactarXml(String xml) {
        // GUÍA: manipulación de String, no JAXB. Dos pasos en cadena.
        // 1. Colapsa toda secuencia de espacios en blanco en UN solo espacio.
        // 2. Recorta los extremos.
        // PISTA: return xml.replaceAll("\\s+", " ").trim();
        // OJO: el test manda "  <libro>   </libro> " y espera EXACTAMENTE
        //   "<libro> </libro>". Verifícalo mentalmente: replaceAll deja
        //   " <libro> </libro> " y trim() quita los extremos. El espacio
        //   interno se conserva (era \s+ -> " "), no desaparece.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra02CompactarXml");
    }

    /**
     * RETO EXTRA 03: Extrae el año de un libro en XML usando JAXB.
     */
    public static int extra03ExtraerAnioJAXB(String xml) {
        // GUÍA: reutiliza el unmarshalling que ya tienes (teoría 16.1).
        // 1. No reimplementes el parseo: llama a desdeXml(xml) del propio fichero.
        // 2. Devuelve el año del libro reconstruido con getAnio().
        // PISTA: return desdeXml(xml).getAnio();
        // OJO: el test serializa con aXml(...) un libro de 2008 y espera 2008.
        //   Es el round-trip de la sección 16.1 aplicado a un solo campo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra03ExtraerAnioJAXB");
    }

    /**
     * RETO EXTRA 04: Marshall a XML sin cabecera/declaración XML.
     */
    public static String extra04MarshallFragmento(Libro143 libro) {
        // GUÍA: teoría 16.1, tabla de propiedades del Marshaller (JAXB_FRAGMENT).
        // 1. Monta el JAXBContext y el Marshaller como en aXml().
        // 2. Activa el modo fragmento para que NO escriba la declaración <?xml?>:
        //    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        // 3. Marshalла a un StringWriter y devuelve su toString().
        // 4. Captura JAXBException y reenvíala como RuntimeException.
        // OJO: el test exige fragment.contains("<libro") pero
        //   !fragment.contains("<?xml"). Sin JAXB_FRAGMENT, JAXB mete la
        //   cabecera y el assertFalse falla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra04MarshallFragmento");
    }

    /**
     * RETO EXTRA 05: Convierte un Libro143 a un mapa de datos simple.
     */
    public static java.util.Map<String, Object> extra05LibroAMap(Libro143 libro) {
        // GUÍA: construcción manual de un Map (no necesita XML).
        // 1. Crea un Map<String,Object>, p.ej. new LinkedHashMap<>() para
        //    conservar el orden isbn/titulo/anio.
        // 2. Mete las tres entradas: "isbn"->getIsbn(), "titulo"->getTitulo(),
        //    "anio"->getAnio().
        // 3. Devuelve el mapa.
        // OJO: el test compara map.get("anio") con 2008 (un int). getAnio()
        //   devuelve int y se autoboxea a Integer(2008); equals con 2008 pasa.
        // CULTURA: convertir entidad a Map es justo lo que hace Jackson por
        //   dentro al serializar (ver Ej144 extra05ClienteAMap).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra05LibroAMap");
    }

    /**
     * RETO EXTRA 06: Crea un Libro143 con valores por defecto.
     */
    public static Libro143 extra06CrearLibroDefault() {
        // GUÍA: factoría trivial.
        // 1. Devuelve un new Libro143(...) con valores por defecto.
        // PISTA: el isbn DEBE ser exactamente "000-00" (el test lo compara con
        //   getIsbn()). Los otros dos campos son libres (p.ej. "Sin título", 0).
        // PISTA: return new Libro143("000-00", "Sin título", 0);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra06CrearLibroDefault");
    }

    /**
     * RETO EXTRA 07: Verifica si una cadena contiene un XML válido de libro JAXB.
     */
    public static boolean extra07EsXmlDeLibro(String xml) {
        // GUÍA: heurística simple por contenido (no hace falta parsear).
        // 1. Defiende el null: si xml es null -> false (el test no pasa null,
        //    pero evita NPE).
        // 2. Comprueba que contenga la etiqueta raíz de libro.
        // PISTA: return xml != null && xml.contains("<libro");
        // OJO: el test da true a "<libro isbn=\"123\">...</libro>" y false a
        //   "<revista></revista>". Usa "<libro" (sin cerrar el ">") para que
        //   valga tanto <libro> como <libro isbn=...>.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra07EsXmlDeLibro");
    }

    /**
     * RETO EXTRA 08: Serializa usando una codificación específica (ej. ISO-8859-1).
     */
    public static String extra08SerializarIso8859(Libro143 libro) {
        // GUÍA: teoría 16.1, propiedad JAXB_ENCODING del Marshaller.
        // 1. Monta context y marshaller como en aXml().
        // 2. Fija la codificación: marshaller.setProperty(Marshaller.JAXB_ENCODING,
        //    "ISO-8859-1");
        // 3. Marshalла a StringWriter y devuelve toString(); captura JAXBException.
        // OJO: el test exige que el resultado contenga encoding="ISO-8859-1".
        //   Esa cadena aparece en la declaración <?xml ... ?> SOLO si NO activas
        //   JAXB_FRAGMENT (al revés que el reto 04): aquí SÍ quieres la cabecera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra08SerializarIso8859");
    }

    /**
     * RETO EXTRA 09: Verifica si un libro fue publicado después de un año de corte.
     */
    public static boolean extra09EsPublicacionReciente(Libro143 libro, int anioCorte) {
        // GUÍA: comparación numérica simple.
        // 1. Devuelve si el año del libro es ESTRICTAMENTE mayor que anioCorte.
        // PISTA: return libro.getAnio() > anioCorte;
        // OJO: el test usa 2008 y comprueba (2000)->true, (2015)->false. Con
        //   estricto > basta; un año igual al corte (>=) no se pone a prueba aquí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra09EsPublicacionReciente");
    }

    /**
     * RETO EXTRA 10: Duplica un libro realizando una serialización y deserialización completa.
     */
    public static Libro143 extra10ClonarLibro(Libro143 libro) {
        // GUÍA: clon por round-trip (teoría 16.1). Reutiliza tus dos métodos base.
        // 1. Serializa el libro a XML con aXml(libro)...
        // 2. ...y reconstrúyelo con desdeXml(...): obtienes un objeto NUEVO con
        //    los mismos datos.
        // PISTA: return desdeXml(aXml(libro));
        // OJO: el test exige assertNotSame(original, clon) (objeto distinto) pero
        //   mismos isbn/titulo. El round-trip da exactamente eso: copia profunda.
        // CULTURA: serializar-y-deserializar es el truco clásico de "deep clone"
        //   sin escribir un copy-constructor campo a campo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra10ClonarLibro");
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
