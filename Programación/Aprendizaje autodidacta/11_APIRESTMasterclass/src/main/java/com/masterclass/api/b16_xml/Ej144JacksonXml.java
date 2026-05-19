package com.masterclass.api.b16_xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Ejercicio 144 · Jackson XML con {@code XmlMapper}.
 *
 * <p>Teoría: {@code teoria/16_XML_y_Ficheros.md} (sección 16.2).
 *
 * <p>{@code XmlMapper} comparte API con {@code ObjectMapper}: las mismas
 * anotaciones más {@code @JacksonXmlProperty}. Convierte POJO ↔ XML sin JAXB.
 */
public final class Ej144JacksonXml {

    private Ej144JacksonXml() {
    }

    /**
     * Serializa un {@link Cliente144} a XML usando {@code XmlMapper}.
     *
     * @param cliente instancia a serializar (no null)
     * @return el XML como String
     * @throws IllegalArgumentException si cliente es null
     * @throws RuntimeException         si falla la serialización
     */
    public static String aXml(Cliente144 cliente) {
        // TODO 1: si cliente es null -> IllegalArgumentException.
        // TODO 2: instancia un XmlMapper (com.fasterxml.jackson.dataformat.xml.XmlMapper).
        // TODO 3: opcionalmente activa SerializationFeature.INDENT_OUTPUT.
        // TODO 4: invoca mapper.writeValueAsString(cliente).
        // TODO 5: @JacksonXmlRootElement fija el nombre del elemento raíz.
        // TODO 6: @JacksonXmlProperty(isAttribute=true) mapea id como atributo.
        // TODO 7: captura JsonProcessingException y reenvíala como RuntimeException.
        // TODO 8: no añadas declaración XML manual: deja que Jackson la gestione.
        // TODO 9: el resultado no debe ser null ni vacío para un cliente válido.
        // TODO 10: devuelve la cadena XML.
        return null;
    }

    /**
     * Reconstruye un {@link Cliente144} desde XML usando {@code XmlMapper}.
     *
     * @param xml documento XML válido (no null ni en blanco)
     * @return el objeto reconstruido
     * @throws IllegalArgumentException si xml es null o en blanco
     * @throws RuntimeException         si falla la deserialización
     */
    public static Cliente144 desdeXml(String xml) {
        // TODO 1: si xml es null o isBlank() -> IllegalArgumentException.
        // TODO 2: instancia un XmlMapper.
        // TODO 3: invoca mapper.readValue(xml, Cliente144.class).
        // TODO 4: Cliente144 necesita constructor por defecto para Jackson.
        // TODO 5: las anotaciones guían el binding inverso (atributo vs elemento).
        // TODO 6: captura JsonProcessingException y reenvíala como RuntimeException.
        // TODO 7: un xml inválido debe fallar, no devolver objeto a medias.
        // TODO 8: verifica que el round-trip preserva id, nombre y vip.
        // TODO 9: no atrapes el error silenciosamente: propágalo.
        // TODO 10: devuelve el Cliente144 reconstruido.
        return null;
    }

    public static void main(String[] args) {
        Cliente144 c = new Cliente144(7, "Ada", true);
        String xml = aXml(c);
        System.out.println(xml);
        System.out.println(desdeXml(xml));
    }
}

/**
 * Modelo Jackson XML del ejercicio 144 (package-private, sufijado).
 */
@JacksonXmlRootElement(localName = "cliente")
final class Cliente144 {

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty
    private String nombre;

    @JacksonXmlProperty
    private boolean vip;

    Cliente144() {
    }

    Cliente144(int id, String nombre, boolean vip) {
        this.id = id;
        this.nombre = nombre;
        this.vip = vip;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        return "Cliente144{id=" + id + ", nombre=" + nombre + ", vip=" + vip + "}";
    }
}
