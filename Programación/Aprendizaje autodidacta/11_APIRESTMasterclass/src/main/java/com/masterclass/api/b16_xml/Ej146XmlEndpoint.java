package com.masterclass.api.b16_xml;

import java.util.List;

/**
 * Ejercicio 146 · Endpoint que produce XML (lógica pura, sin Spring).
 *
 * <p>Teoría: {@code teoria/16_XML_y_Ficheros.md} (sección 16.4).
 *
 * <p>Un controlador con {@code produces = APPLICATION_XML_VALUE} negocia
 * contenido. Aquí modelamos solo la serialización como función pura para poder
 * testearla sin levantar el servidor.
 */
public final class Ej146XmlEndpoint {

    private Ej146XmlEndpoint() {
    }

    /**
     * Serializa una lista de productos como un documento XML
     * {@code <productos>...<producto id="..">..</producto>..</productos>}.
     *
     * @param productos lista a serializar (no null; puede estar vacía)
     * @return el cuerpo XML que devolvería el endpoint
     * @throws IllegalArgumentException si productos es null
     */
    public static String serializar(List<Producto146> productos) {
        // TODO 1: si productos es null -> IllegalArgumentException.
        // TODO 2: usa un StringBuilder para construir el documento.
        // TODO 3: añade la declaración <?xml version="1.0" encoding="UTF-8"?>.
        // TODO 4: abre el elemento raíz <productos>.
        // TODO 5: si la lista está vacía, el raíz queda sin hijos (válido).
        // TODO 6: itera cada Producto146 generando <producto id="...">.
        // TODO 7: escapa caracteres XML del nombre (& < > " ') para no romper el doc.
        // TODO 8: cierra cada <producto> con su nombre como texto.
        // TODO 9: cierra el elemento raíz </productos>.
        // TODO 10: devuelve la cadena completa del StringBuilder.
        return null;
    }

    /**
     * Escapa los caracteres reservados de XML en texto de contenido.
     *
     * @param texto valor a escapar (no null)
     * @return el texto con &amp; &lt; &gt; etc. sustituidos
     * @throws IllegalArgumentException si texto es null
     */
    public static String escaparXml(String texto) {
        // TODO 1: si texto es null -> IllegalArgumentException.
        // TODO 2: reemplaza '&' por &amp; PRIMERO (orden importa).
        // TODO 3: reemplaza '<' por &lt;.
        // TODO 4: reemplaza '>' por &gt;.
        // TODO 5: reemplaza '"' por &quot;.
        // TODO 6: reemplaza '\'' por &apos;.
        // TODO 7: una cadena sin reservados debe volver intacta.
        // TODO 8: cadena vacía devuelve cadena vacía.
        // TODO 9: no uses regex con grupos: replace literal basta y es seguro.
        // TODO 10: devuelve el texto ya escapado.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(serializar(List.of(new Producto146(1, "Café & Té"))));
    }
}

/**
 * Modelo del ejercicio 146 (package-private, sufijado).
 */
record Producto146(int id, String nombre) {
}
