package com.masterclass.api.b16_xml;

import java.util.List;

/**
 * Ejercicio 145 · Parseo DOM vs SAX.
 *
 * <p>Teoría: {@code teoria/16_XML_y_Ficheros.md} (sección 16.3).
 *
 * <p>DOM carga el árbol completo en memoria (acceso aleatorio). SAX recorre el
 * documento en streaming disparando eventos (bajo consumo de RAM).
 */
public final class Ej145DomSaxParsing {

    private Ej145DomSaxParsing() {
    }

    /**
     * Cuenta, vía DOM, cuántos elementos con el nombre dado contiene el XML.
     *
     * @param xml      documento XML válido (no null ni en blanco)
     * @param etiqueta nombre del elemento a contar (no null ni en blanco)
     * @return número de apariciones del elemento
     * @throws IllegalArgumentException si algún argumento es inválido
     * @throws RuntimeException         si falla el parseo
     */
    public static int contarConDom(String xml, String etiqueta) {
        // TODO 1: si xml es null/blank o etiqueta null/blank -> IllegalArgumentException.
        // TODO 2: obtén un DocumentBuilderFactory.newInstance().
        // TODO 3: endurece el parser: setFeature disallow-doctype-decl (evita XXE).
        // TODO 4: crea un DocumentBuilder con factory.newDocumentBuilder().
        // TODO 5: envuelve el xml en un InputSource sobre un StringReader.
        // TODO 6: parsea a Document con builder.parse(inputSource).
        // TODO 7: invoca document.getElementsByTagName(etiqueta).
        // TODO 8: la longitud de la NodeList es el conteo buscado.
        // TODO 9: captura las excepciones (Parser/SAX/IO) como RuntimeException.
        // TODO 10: devuelve el número de nodos encontrados.
        return 0;
    }

    /**
     * Extrae, vía SAX en streaming, el texto de todos los elementos con el
     * nombre dado, en orden de aparición.
     *
     * @param xml      documento XML válido (no null ni en blanco)
     * @param etiqueta nombre del elemento cuyo texto recolectar
     * @return lista con el contenido textual de cada elemento coincidente
     * @throws IllegalArgumentException si algún argumento es inválido
     * @throws RuntimeException         si falla el parseo
     */
    public static List<String> textosConSax(String xml, String etiqueta) {
        // TODO 1: si xml es null/blank o etiqueta null/blank -> IllegalArgumentException.
        // TODO 2: obtén un SAXParserFactory.newInstance() y crea un SAXParser.
        // TODO 3: prepara una List<String> acumuladora del resultado.
        // TODO 4: implementa un DefaultHandler con un flag "dentro de etiqueta".
        // TODO 5: en startElement activa el flag si el qName coincide.
        // TODO 6: en characters, si el flag está activo, acumula el fragmento.
        // TODO 7: en endElement añade el texto a la lista y baja el flag.
        // TODO 8: invoca parser.parse(InputSource sobre StringReader, handler).
        // TODO 9: captura SAX/IO como RuntimeException; SAX no permite reposicionar.
        // TODO 10: devuelve la lista de textos en orden documental.
        return List.of();
    }

    public static void main(String[] args) {
        String xml = "<libros><libro>A</libro><libro>B</libro></libros>";
        System.out.println(contarConDom(xml, "libro"));
        System.out.println(textosConSax(xml, "libro"));
    }

    /**
     * RETO EXTRA 01: Comprueba si un XML básico está bien formado a nivel de corchetes.
     */
    public static boolean extra01EsXmlBienFormado(String xml) {
        // GUÍA: chequeo léxico ligero (NO parsees con DOM aquí).
        // 1. Defiende null -> false.
        // 2. Cuenta los '<' y los '>' y comprueba que coincidan (y que haya
        //    al menos uno).
        // PISTA: cuenta con chars().filter(c -> c == '<').count() para cada uno;
        //   return abre == cierra && abre > 0;
        // OJO: el test da true a "<tag></tag>" (2 y 2) y false a "<tag" (1 y 0).
        //   No es validación XML real, solo "los corchetes cuadran".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra01EsXmlBienFormado");
    }

    /**
     * RETO EXTRA 02: Convierte los nombres de etiqueta de un XML simple a mayúsculas usando regex.
     */
    public static String extra02ConvertirEtiquetasAMayusculas(String xml) {
        // GUÍA: regex con reemplazo dinámico (Pattern + Matcher).
        // 1. Patrón que case una etiqueta de apertura o cierre: "</?\\w+>".
        // 2. Recorre con Matcher.find() y reconstruye con appendReplacement,
        //    poniendo m.group().toUpperCase() en cada coincidencia; cierra con
        //    appendTail.
        // PISTA:
        //    Matcher m = Pattern.compile("</?\\w+>").matcher(xml);
        //    StringBuilder sb = new StringBuilder();
        //    while (m.find()) m.appendReplacement(sb, m.group().toUpperCase());
        //    m.appendTail(sb); return sb.toString();
        // OJO: el test espera "<libro>A</libro>" -> "<LIBRO>A</LIBRO>": solo las
        //   ETIQUETAS suben a mayúsculas, el contenido "A" no se toca. Un atajo
        //   xml.toUpperCase() pasaría ESTE test por casualidad (la "A" ya está en
        //   mayúscula), pero rompería con contenido en minúsculas: hazlo con regex.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra02ConvertirEtiquetasAMayusculas");
    }

    /**
     * RETO EXTRA 03: Obtiene el valor de un atributo específico en el primer nodo encontrado usando DOM.
     */
    public static String extra03ObtenerAtributoPorDom(String xml, String etiqueta, String atributo) {
        // GUÍA: teoría 16.3 (DOM). Parsea como en contarConDom y lee el atributo.
        // 1. Parsea el xml a Document (recuerda desactivar DOCTYPE, anti-XXE).
        // 2. NodeList nodos = doc.getElementsByTagName(etiqueta);
        // 3. Element primero = (Element) nodos.item(0);
        // 4. return primero.getAttribute(atributo);
        // PISTA: castea el Node a org.w3c.dom.Element para tener getAttribute().
        // OJO: el test usa "<libro id=\"123\">A</libro>" y espera "123". Si el
        //   atributo no existe, getAttribute devuelve "" (cadena vacía), no null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra03ObtenerAtributoPorDom");
    }

    /**
     * RETO EXTRA 04: Obtiene el primer texto del elemento coincidente usando DOM.
     */
    public static String extra04ObtenerPrimerTextoPorDom(String xml, String etiqueta) {
        // GUÍA: teoría 16.3 (DOM). Igual que el reto 03 pero leyendo el texto.
        // 1. Parsea a Document.
        // 2. doc.getElementsByTagName(etiqueta).item(0).getTextContent();
        // PISTA: getTextContent() devuelve el texto del nodo (y sus hijos).
        // OJO: el test usa el XML de tres <libro> A/B/C y espera "A" (el PRIMERO).
        //   item(0) es justamente el primero en orden documental.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra04ObtenerPrimerTextoPorDom");
    }

    /**
     * RETO EXTRA 05: Contar ocurrencias de múltiples etiquetas.
     */
    public static int extra05ContarMultiplesEtiquetas(String xml, List<String> etiquetas) {
        // GUÍA: REUTILIZA contarConDom (el método base de este ejercicio).
        // 1. Recorre la lista de etiquetas sumando contarConDom(xml, etiqueta).
        // PISTA: int total = 0; for (String e : etiquetas) total += contarConDom(xml, e);
        //   return total;  (o un stream con mapToInt(e -> contarConDom(xml, e)).sum()).
        // OJO: el test pide contar ["libro","libros"] en el XML de 3 libros y
        //   espera 4 (3 + 1). No reparsees a mano: apóyate en lo ya hecho.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra05ContarMultiplesEtiquetas");
    }

    /**
     * RETO EXTRA 06: Comprueba si un XML simple contiene la declaración de DOCTYPE (potencial XXE).
     */
    public static boolean extra06ContieneDoctype(String xml) {
        // GUÍA: detección léxica (teoría 16.3, recuadro XXE).
        // PISTA: return xml != null && xml.contains("<!DOCTYPE");
        // OJO: el test da true a "<!DOCTYPE html>..." y false a "<root></root>".
        // CULTURA: detectar DOCTYPE es el primer paso de la defensa anti-XXE;
        //   en parsers reales lo bloqueas con disallow-doctype-decl (lo verás en
        //   seguridad, b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra06ContieneDoctype");
    }

    /**
     * RETO EXTRA 07: Comprueba si el tag hijo tiene como padre directo a un tag específico usando DOM.
     */
    public static boolean extra07EsPadreDirecto(String xml, String tagPadre, String tagHijo) {
        // GUÍA: teoría 16.3 (DOM, navegación de árbol).
        // 1. Parsea a Document.
        // 2. Coge los nodos con nombre tagHijo: doc.getElementsByTagName(tagHijo).
        // 3. Para alguno de ellos, mira si su padre es tagPadre:
        //    nodo.getParentNode().getNodeName().equals(tagPadre).
        // PISTA: recorre la NodeList con un for y devuelve true en cuanto
        //   encuentres uno cuyo padre coincida; si ninguno, false.
        // OJO: el test da true a (padre="libros", hijo="libro") y false a
        //   (padre="libro", hijo="libros"): el orden importa, es padre->hijo
        //   DIRECTO (getParentNode, no un ancestro cualquiera).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra07EsPadreDirecto");
    }

    /**
     * RETO EXTRA 08: Obtiene todo el contenido textual plano del documento XML usando DOM.
     */
    public static String extra08ExtraerTodoTextoDom(String xml) {
        // GUÍA: teoría 16.3 (DOM). getTextContent sobre la raíz concatena TODO.
        // 1. Parsea a Document.
        // 2. return doc.getDocumentElement().getTextContent();
        // OJO: el test usa el XML de A/B/C y espera "ABC" (textos concatenados,
        //   sin las etiquetas). getTextContent aplana recursivamente el árbol.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra08ExtraerTodoTextoDom");
    }

    /**
     * RETO EXTRA 09: Verifica si la etiqueta raíz del XML coincide con la esperada.
     */
    public static boolean extra09ValidarEtiquetaRaiz(String xml, String raizEsperada) {
        // GUÍA: teoría 16.3 (DOM). El elemento raíz es getDocumentElement().
        // 1. Parsea a Document.
        // 2. return doc.getDocumentElement().getNodeName().equals(raizEsperada);
        // OJO: el test da true a (raíz "libros") y false a (raíz "libro"). La raíz
        //   del XML de ejemplo es <libros>, no <libro>.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra09ValidarEtiquetaRaiz");
    }

    /**
     * RETO EXTRA 10: Retorna el número total de nodos de tipo ELEMENT_NODE en el documento.
     */
    public static int extra10ContarNodosElemento(String xml) {
        // GUÍA: teoría 16.3 (DOM). El comodín "*" selecciona TODOS los elementos.
        // 1. Parsea a Document.
        // 2. return doc.getElementsByTagName("*").getLength();
        // OJO: el test usa <libros> con 3 <libro> y espera 4 (la raíz cuenta).
        //   "*" incluye la raíz y todos los descendientes de tipo elemento; los
        //   nodos de texto (A, B, C) NO son ELEMENT_NODE, así que no suman.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extra10ContarNodosElemento");
    }

}
