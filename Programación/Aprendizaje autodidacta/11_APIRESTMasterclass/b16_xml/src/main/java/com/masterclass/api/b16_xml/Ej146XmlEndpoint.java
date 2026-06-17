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

    /**
     * RETO EXTRA 01: Valida que la lista de productos no sea nula.
     */
    public static boolean extra01ValidarLista(List<Producto146> productos) {
        // GUÍA: precondición pura (patrón de defensa de 16.4).
        // 1. Si productos es null -> lanza IllegalArgumentException.
        // 2. Si no, devuelve true (una lista vacía SÍ es válida).
        // PISTA: if (productos == null) throw new IllegalArgumentException("..."); return true;
        // OJO: el test da true a List.of() (lista vacía válida) y espera EXCEPCIÓN
        //   con null. No devuelvas false para null: el test usa assertThrows.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra01ValidarLista");
    }

    /**
     * RETO EXTRA 02: Crea un StringBuilder pre-dimensionado para optimizar rendimiento.
     */
    public static StringBuilder extra02CrearStringBuilder(int capacidad) {
        // GUÍA: validar ANTES de construir.
        // 1. Si capacidad < 0 -> IllegalArgumentException.
        // 2. return new StringBuilder(capacidad);
        // OJO: el test pide (50)->no null y (-5)->IllegalArgumentException. Si
        //   pasas un negativo directo al constructor, lanza
        //   NegativeArraySizeException (NO es la que espera el test): valídalo tú
        //   primero y lanza la IllegalArgumentException a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra02CrearStringBuilder");
    }

    /**
     * RETO EXTRA 03: Genera una declaración XML con una codificación personalizada.
     */
    public static String extra03DeclaracionPersonalizada(String encoding) {
        // GUÍA: construcción de cadena con validación.
        // 1. Si encoding es null o en blanco -> IllegalArgumentException.
        // 2. Devuelve la declaración interpolando el encoding.
        // PISTA: return "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>";
        // OJO: el resultado debe ser EXACTO (equals):
        //   <?xml version="1.0" encoding="ISO-8859-1"?>
        //   Cuida las comillas escapadas y NO metas espacios de más. "" -> excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra03DeclaracionPersonalizada");
    }

    /**
     * RETO EXTRA 04: Genera la etiqueta de apertura de un elemento con atributos.
     */
    public static String extra04AperturaConAtributos(String tag, java.util.Map<String, String> atributos) {
        // GUÍA: StringBuilder recorriendo el mapa.
        // 1. Si tag es null/blank (o atributos null) -> IllegalArgumentException.
        // 2. Empieza con "<" + tag.
        // 3. Por cada entrada del mapa añade  " clave=\"valor\""  (espacio delante).
        // 4. Cierra con ">".
        // PISTA: for (var e : atributos.entrySet())
        //          sb.append(' ').append(e.getKey()).append("=\"")
        //            .append(e.getValue()).append('"');
        // OJO: el test comprueba startsWith("<item"), contains("key=\"val\"") y
        //   endsWith(">"). Con un solo atributo el orden no importa; no olvides
        //   el espacio entre el tag y el primer atributo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra04AperturaConAtributos");
    }

    /**
     * RETO EXTRA 05: Genera una etiqueta vacía autocerrada.
     */
    public static String extra05TagAutoCerrado(String tag) {
        // GUÍA: una línea con validación.
        // 1. Si tag es null/blank -> IllegalArgumentException.
        // 2. return "<" + tag + "/>";
        // OJO: el test pide "br" -> "<br/>" (sin espacio antes de la barra) y
        //   null -> excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra05TagAutoCerrado");
    }

    /**
     * RETO EXTRA 06: Formatea un único producto a su representación XML simple.
     */
    public static String extra06FormatearProducto(Producto146 producto) {
        // GUÍA: REUTILIZA escaparXml (el método base de este ejercicio).
        // 1. Si producto es null -> IllegalArgumentException.
        // 2. Monta "<producto id=\"" + id + "\">" + escaparXml(nombre) + "</producto>".
        // PISTA: return "<producto id=\"" + producto.id() + "\">"
        //          + escaparXml(producto.nombre()) + "</producto>";
        // OJO: el test usa nombre "A & B" y espera
        //   <producto id="42">A &amp; B</producto>. El '&' DEBE quedar escapado:
        //   por eso pasas el nombre por escaparXml, no lo concatenes crudo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra06FormatearProducto");
    }

    /**
     * RETO EXTRA 07: Escapa exclusivamente los ampersands de una cadena.
     */
    public static String extra07EscaparSoloAmpersand(String texto) {
        // GUÍA: escape parcial (solo '&').
        // 1. Si texto es null -> IllegalArgumentException.
        // 2. return texto.replace("&", "&amp;");
        // OJO: el test pide "A & B < C" -> "A &amp; B < C": el '<' se queda TAL
        //   CUAL, solo se toca el '&'. Es la diferencia con escaparXml (que
        //   escapa los cinco). null -> excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra07EscaparSoloAmpersand");
    }

    /**
     * RETO EXTRA 08: Retorna la etiqueta de cierre correspondiente.
     */
    public static String extra08EtiquetaCierre(String tag) {
        // GUÍA: una línea con validación.
        // 1. Si tag es null/blank -> IllegalArgumentException.
        // 2. return "</" + tag + ">";
        // OJO: el test pide "productos" -> "</productos>" y " " (espacio) ->
        //   excepción. Por eso valida con isBlank(), no solo con == null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra08EtiquetaCierre");
    }

    /**
     * RETO EXTRA 09: Cuenta la cantidad de caracteres que componen el XML resultante de la lista.
     */
    public static int extra09LongitudResultado(List<Producto146> productos) {
        // GUÍA: longitud del documento producido por serializar().
        // 1. Llama a serializar(productos) y devuelve la longitud del resultado,
        //    protegiéndote del null.
        // PISTA: String r = serializar(productos); return r == null ? 0 : r.length();
        // ⚠ CUIDADO: el test pasa List.of() y espera 0. Eso solo se cumple
        //   MIENTRAS serializar siga sin resolver (devuelve null -> 0). En cuanto
        //   implementes serializar de verdad, serializar(List.of()) devolverá el
        //   documento <productos></productos> (longitud > 0) y ESTE test fallará.
        //   Es una dependencia frágil del enunciado: si resuelves serializar,
        //   tendrás que reajustar la expectativa de este test (o el reto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra09LongitudResultado");
    }

    /**
     * RETO EXTRA 10: Concatenación directa de dos productos en un único fragmento XML.
     */
    public static String extra10ConcatenarDosProductos(Producto146 p1, Producto146 p2) {
        // GUÍA: REUTILIZA extra06FormatearProducto (reto 06).
        // 1. Si p1 o p2 es null -> IllegalArgumentException.
        // 2. return extra06FormatearProducto(p1) + extra06FormatearProducto(p2);
        // OJO: el test construye lo esperado llamando a extra06 sobre cada uno y
        //   concatenando: por eso DEBES delegar en extra06 (mismo formato/escape)
        //   en lugar de reconstruir el XML a mano. null en cualquiera -> excepción
        //   (la lanza extra06 al validar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra10ConcatenarDosProductos");
    }

}

/**
 * Modelo del ejercicio 146 (package-private, sufijado).
 */
record Producto146(int id, String nombre) {
}
