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

    /**
     * RETO EXTRA 01: Valida que el nombre de cliente no esté vacío.
     */
    public static boolean extra01ValidarNombre(Cliente144 cliente) {
        // GUÍA: validación de campo (patrón "cadena con contenido").
        // 1. Saca el nombre con cliente.getNombre().
        // 2. Es válido si NO es null y NO está en blanco.
        // PISTA: return cliente.getNombre() != null && !cliente.getNombre().isBlank();
        // OJO: el test da "Bob"->true y ""->false. isBlank() (Java 11) cubre
        //   también el caso de solo espacios, mejor que isEmpty().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra01ValidarNombre");
    }

    /**
     * RETO EXTRA 02: Transforma el nombre del cliente a mayúsculas.
     */
    public static String extra02NombreMayusculas(Cliente144 cliente) {
        // GUÍA: una línea.
        // PISTA: return cliente.getNombre().toUpperCase();
        // OJO: el test manda "Bob" y espera "BOB". (Defensa opcional: podrías
        //   reutilizar extra01ValidarNombre antes, pero el test no lo exige.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra02NombreMayusculas");
    }

    /**
     * RETO EXTRA 03: Extrae el ID de un cliente en XML usando Jackson.
     */
    public static int extra03ExtraerIdJackson(String xml) {
        // GUÍA: reutiliza el deserializador del propio fichero (teoría 16.2).
        // 1. No parsees a mano: llama a desdeXml(xml).
        // 2. Devuelve su id con getId().
        // PISTA: return desdeXml(xml).getId();
        // OJO: el test serializa un cliente con id=15 y espera 15. Es el
        //   round-trip de 16.2 quedándote con un solo campo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra03ExtraerIdJackson");
    }

    /**
     * RETO EXTRA 04: Serializa a XML formateado de una forma específica.
     */
    public static String extra04JacksonFormateado(Cliente144 cliente) {
        // GUÍA: teoría 16.2 (INDENT_OUTPUT). Igual que aXml() pero indentado.
        // 1. XmlMapper mapper = new XmlMapper();
        // 2. mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //    (o mapper.writerWithDefaultPrettyPrinter()).
        // 3. return mapper.writeValueAsString(cliente); captura JsonProcessingException.
        // OJO: el test solo exige que el resultado contenga "\n" o "\r" (saltos
        //   de línea del indentado). Sin INDENT_OUTPUT el XML va en una sola línea
        //   y el assertTrue falla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra04JacksonFormateado");
    }

    /**
     * RETO EXTRA 05: Convierte un Cliente144 a mapa clave-valor.
     */
    public static java.util.Map<String, Object> extra05ClienteAMap(Cliente144 cliente) {
        // GUÍA: aquí Jackson SÍ ayuda — convertValue convierte POJO -> Map.
        // 1. XmlMapper mapper = new XmlMapper();
        // 2. return mapper.convertValue(cliente, Map.class);
        // ALTERNATIVA manual: monta tú un LinkedHashMap con id/nombre/vip.
        // OJO: el test compara map.get("id") con 1 (Integer), map.get("vip") con
        //   false (Boolean). convertValue respeta los tipos: int->Integer,
        //   boolean->Boolean. Si lo haces a mano, NO metas los números como String.
        // CULTURA: convertValue es el motor interno de Jackson; ningún XML/JSON
        //   se genera aquí, solo se reorganizan los datos en un Map.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra05ClienteAMap");
    }

    /**
     * RETO EXTRA 06: Crea un Cliente144 VIP por defecto.
     */
    public static Cliente144 extra06CrearClienteVip() {
        // GUÍA: factoría trivial.
        // PISTA: return new Cliente144(1, "VIP", true);
        // OJO: el test solo comprueba que no sea null y que isVip() sea true. El
        //   tercer argumento del constructor (vip) DEBE ser true; id y nombre son
        //   libres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra06CrearClienteVip");
    }

    /**
     * RETO EXTRA 07: Verifica si una cadena contiene un XML válido de cliente Jackson.
     */
    public static boolean extra07EsXmlDeCliente(String xml) {
        // GUÍA: heurística por contenido, gemelo del Ej143 extra07.
        // PISTA: return xml != null && xml.contains("<cliente");
        // OJO: el test da true a "<cliente id=\"1\">...</cliente>" y false a
        //   "<usuario></usuario>". Usa "<cliente" (sin cerrar) para admitir el
        //   atributo id.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra07EsXmlDeCliente");
    }

    /**
     * RETO EXTRA 08: Serializa forzando a escribir la cabecera XML.
     */
    public static String extra08SerializarConCabecera(Cliente144 cliente) {
        // GUÍA: teoría 16.2 (WRITE_XML_DECLARATION). Jackson NO escribe la
        // cabecera por defecto; hay que activarla.
        // 1. XmlMapper mapper = new XmlMapper();
        // 2. mapper.getFactory().getXMLOutputFactory()... NO; la forma directa es:
        //    mapper.configure(com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
        //         .Feature.WRITE_XML_DECLARATION, true);
        // 3. return mapper.writeValueAsString(cliente);
        // OJO: el test exige xml.startsWith("<?xml"). Sin esa Feature el XML
        //   empieza directamente por "<cliente" y el assertTrue falla.
        // ALTERNATIVA pragmática: anteponer manualmente
        //   "<?xml version=\"1.0\" ...?>" + aXml(cliente). Funciona, pero conocer
        //   la Feature es lo "Jackson".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra08SerializarConCabecera");
    }

    /**
     * RETO EXTRA 09: Verifica si el cliente califica para descuento especial (ser VIP y llamarse Ada).
     */
    public static boolean extra09TieneDescuentoEspecial(Cliente144 cliente) {
        // GUÍA: regla de negocio compuesta (dos condiciones con &&).
        // 1. Califica si es VIP Y se llama exactamente "Ada".
        // PISTA: return cliente.isVip() && "Ada".equals(cliente.getNombre());
        // OJO: el test da true a (Ada, vip=true) y false a (Ada, vip=false): las
        //   DOS condiciones deben cumplirse. Pon "Ada".equals(nombre) (constante
        //   a la izquierda) para no reventar si el nombre fuera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra09TieneDescuentoEspecial");
    }

    /**
     * RETO EXTRA 10: Duplica un cliente utilizando Jackson XML.
     */
    public static Cliente144 extra10ClonarCliente(Cliente144 cliente) {
        // GUÍA: clon por round-trip, gemelo del Ej143 extra10.
        // PISTA: return desdeXml(aXml(cliente));
        // OJO: el test exige assertNotSame(c, clon) pero mismos id y nombre.
        //   Serializar a XML y volver a leer crea un objeto independiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra10ClonarCliente");
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
