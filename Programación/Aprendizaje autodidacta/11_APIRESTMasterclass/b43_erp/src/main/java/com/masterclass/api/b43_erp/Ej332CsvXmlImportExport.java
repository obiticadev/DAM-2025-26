package com.masterclass.api.b43_erp;

import java.util.List;

/**
 * Ejercicio 332 · Importar/exportar maestros (clientes/productos) en CSV y XML.
 *
 * <p>Teoría: {@code teoria/43_SGE_Integracion.md} (sección 2).
 *
 * <p>La forma más vieja y más usada de integrar con un ERP es el <em>fichero plano</em>: el
 * cliente te manda un CSV con sus clientes, o exportas un XML para que otro sistema lo coma.
 * Parsear CSV/XML ya lo dominas (b16); aquí lo aplicas al caso ERP: convertir filas en
 * {@link ClienteErp} y de vuelta, con <em>round-trip</em> estable (exportar→importar = original).
 */
public final class Ej332CsvXmlImportExport {

    /** Cabecera canónica del CSV de clientes de este bloque. */
    public static final String CABECERA = "idExterno;nombre;email;pais";

    private Ej332CsvXmlImportExport() {
    }

    /**
     * Importa un CSV de clientes (cabecera {@code idExterno;nombre;email;pais}).
     *
     * @param csv contenido CSV completo, líneas separadas por '\n' (no null)
     * @return lista de clientes (vacía si solo hay cabecera)
     * @throws IllegalArgumentException si csv es null o una fila no tiene 4 campos
     */
    public static List<ClienteErp> importarClientesCsv(String csv) {
        // TODO 1: si csv es null -> IllegalArgumentException.
        // TODO 2: divide en líneas por '\n' y quita un posible '\r' final de cada una.
        // TODO 3: si no hay líneas o solo hay cabecera -> devuelve List.of().
        // TODO 4: descarta la primera línea (la cabecera).
        // TODO 5: ignora líneas en blanco intermedias.
        // TODO 6: parte cada fila por ';' con límite -1 (para conservar campos vacíos al final).
        // TODO 7: si el número de campos != 4 -> IllegalArgumentException.
        // TODO 8: construye un ClienteErp con (idExterno, nombre, email, pais) ya troceados.
        // TODO 9: acumula cada cliente en una lista mutable.
        // TODO 10: devuelve la lista en el orden del fichero.
        return List.of();
    }

    /**
     * Exporta una lista de clientes a CSV con cabecera.
     *
     * @param clientes lista a exportar (no null; puede ir vacía)
     * @return texto CSV con cabecera; re-importable sin pérdida
     * @throws IllegalArgumentException si clientes es null o algún campo contiene ';'
     */
    public static String exportarClientesCsv(List<ClienteErp> clientes) {
        // TODO 1: si clientes es null -> IllegalArgumentException.
        // TODO 2: usa un StringBuilder y empieza por CABECERA + '\n'.
        // TODO 3: si la lista está vacía, devuelve solo la cabecera + '\n'.
        // TODO 4: recorre cada cliente en orden.
        // TODO 5: si algún campo (nombre, email...) contiene ';' -> IllegalArgumentException.
        // TODO 6: une los 4 campos con ';' en el orden de la cabecera.
        // TODO 7: añade '\n' tras cada fila de datos.
        // TODO 8: el resultado debe ser round-trip estable con importarClientesCsv.
        // TODO 9: devuelve la cadena completa.
        return null;
    }

    public static void main(String[] args) {
        String csv = CABECERA + "\nCLI-001;Acme SA;info@acme.es;ES\nCLI-002;Globex;hi@globex.com;US\n";
        List<ClienteErp> c = importarClientesCsv(csv);
        System.out.println(c);
        System.out.println(exportarClientesCsv(c));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Comprueba que la primera línea de un CSV es la cabecera esperada.
     */
    public static boolean cabeceraCsvValida(String csv) {
        // GUÍA: teoría 2 (un CSV de integración debe traer la cabecera pactada).
        // 1. null -> false.
        // 2. true si el CSV empieza por CABECERA (la constante de esta clase).
        // PISTA: return csv != null && csv.startsWith(CABECERA);
        // OJO: el test pasa un CSV que arranca por "idExterno;nombre;email;pais" -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeceraCsvValida");
    }

    /**
     * Reto Extra 2: Cuenta los registros de datos de un CSV (sin contar la cabecera).
     */
    public static int contarRegistrosCsv(String csv) {
        // GUÍA: teoría 2 (nº de filas = nº de líneas no vacías menos la cabecera).
        // 1. null/blank -> 0.
        // 2. parte por '\n', filtra líneas en blanco, resta 1 (la cabecera).
        // PISTA: long n = csv.lines().filter(l -> !l.isBlank()).count(); return (int) Math.max(0, n - 1);
        // OJO: el test pasa cabecera + 2 filas -> 2. Usa Math.max(0,...) para que un CSV
        //   con solo cabecera dé 0, no -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarRegistrosCsv");
    }

    /**
     * Reto Extra 3: Valida con una regla simple que un email tiene forma {@code algo@algo.algo}.
     */
    public static boolean emailValido(String email) {
        // GUÍA: teoría 2 (validación de maestros antes de cargarlos en el ERP).
        // 1. null/blank -> false.
        // 2. comprueba contra una regex sencilla: ^[^@\s]+@[^@\s]+\.[^@\s]+$.
        // PISTA: return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
        // OJO: el test exige "info@acme.es" -> true y "acme.es" (sin @) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para emailValido");
    }

    /**
     * Reto Extra 4: Escapa los caracteres reservados de XML en un texto.
     */
    public static String escaparXml(String texto) {
        // GUÍA: teoría 2 (al construir XML a mano hay que escapar & < > o el documento rompe).
        // 1. null -> "".
        // 2. reemplaza, EN ESTE ORDEN: '&'->"&amp;", '<'->"&lt;", '>'->"&gt;".
        // PISTA: texto.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
        // OJO: el '&' va PRIMERO. Si escapas '<' antes que '&', luego el '&' de "&lt;"
        //   se volvería a escapar a "&amp;lt;" (doble escape). El orden es la trampa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escaparXml");
    }

    /**
     * Reto Extra 5: Serializa un cliente como elemento XML {@code <cliente>...</cliente>}.
     */
    public static String clienteAXml(ClienteErp c) {
        // GUÍA: teoría 2 (exportar un maestro a XML). Reutiliza escaparXml en cada valor.
        // 1. null -> "".
        // 2. construye: <cliente id="ID"><nombre>...</nombre><email>...</email><pais>...</pais></cliente>
        //    escapando nombre y email con escaparXml.
        // PISTA: "<cliente id=\"" + c.idExterno() + "\"><nombre>" + escaparXml(c.nombre()) + "</nombre>..."
        // OJO: el test usa un nombre con '&' ("Tom & Jerry") y espera "&amp;" dentro del XML.
        //   Si no escapas, el XML sería inválido.
        // CULTURA: esto es lo que hace JAXB/Jackson-XML por ti en b16; hacerlo a mano una vez
        //   te enseña por qué el escape importa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clienteAXml");
    }

    /**
     * Reto Extra 6: Extrae el contenido de la primera etiqueta {@code <tag>...</tag>} de un XML.
     */
    public static String extraerTag(String xml, String tag) {
        // GUÍA: teoría 2 (lectura rápida de XML simple sin parser completo).
        // 1. si xml es null o no contiene <tag> y </tag> -> "".
        // 2. localiza el índice tras "<tag>" y el índice de "</tag>" y devuelve lo de en medio.
        // PISTA: int i = xml.indexOf("<"+tag+">"); if (i<0) return "";
        //        int ini = i + tag.length() + 2; int fin = xml.indexOf("</"+tag+">", ini);
        //        return fin < 0 ? "" : xml.substring(ini, fin);
        // OJO: el test extrae "pais" de "<pais>ES</pais>" -> "ES". Cuidado con sumar bien
        //   la longitud de "<tag>" (es tag.length()+2 por el '<' y el '>').
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerTag");
    }

    /**
     * Reto Extra 7: Deshace el escape XML (la inversa del reto 4).
     */
    public static String desescaparXml(String texto) {
        // GUÍA: teoría 2 (al LEER XML hay que deshacer las entidades).
        // 1. null -> "".
        // 2. reemplaza, EN ORDEN INVERSO al escape: "&lt;"->"<", "&gt;"->">", "&amp;"->"&".
        // PISTA: texto.replace("&lt;","<").replace("&gt;",">").replace("&amp;","&");
        // OJO: aquí el "&amp;" va EL ÚLTIMO (inverso del reto 4). El test comprueba que
        //   desescaparXml(escaparXml(s)) == s (round-trip): el orden es lo que lo garantiza.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desescaparXml");
    }

    /**
     * Reto Extra 8: Elimina clientes duplicados conservando el primero de cada {@code idExterno}.
     */
    public static List<ClienteErp> deduplicarPorId(List<ClienteErp> clientes) {
        // GUÍA: teoría 2 (los ficheros de integración traen duplicados; nos quedamos con el 1º).
        // 1. null -> List.of().
        // 2. recorre en orden; añade cada cliente solo si su idExterno no se vio antes.
        // PISTA: usa un java.util.Set<String> vistos y un List resultado; if (vistos.add(id)) añade.
        //   (Set.add devuelve false si ya estaba: úsalo como guarda.)
        // OJO: el test pasa [CLI-1(A), CLI-1(B), CLI-2] -> 2 elementos, y conserva el PRIMER
        //   CLI-1 (el "A"), no el último.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deduplicarPorId");
    }

    /**
     * Reto Extra 9: Detecta el formato de un contenido ("csv", "xml" o "desconocido").
     */
    public static String detectarFormato(String contenido) {
        // GUÍA: teoría 2 (un endpoint de importación recibe ficheros de varios formatos).
        // 1. null/blank -> "desconocido".
        // 2. si tras trim empieza por '<' -> "xml".
        // 3. si contiene ';' o ',' en la primera línea -> "csv".
        // 4. en otro caso -> "desconocido".
        // PISTA: String t = contenido.trim(); if (t.startsWith("<")) return "xml"; ...
        // OJO: comprueba XML ANTES que CSV: un XML podría contener comas dentro y darías
        //   "csv" por error. El '<' inicial es la señal fuerte.
        // CULTURA: esto es lo mismo que detectar por "magic number" en b26/b40, pero a nivel
        //   de texto en vez de bytes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarFormato");
    }

    /**
     * Reto Extra 10: Envuelve una lista de clientes en un documento XML completo.
     */
    public static String clientesAXml(List<ClienteErp> clientes) {
        // GUÍA: teoría 2 (exportar el maestro entero). Reutiliza clienteAXml en cada elemento.
        // 1. null -> "".
        // 2. construye <clientes> + (cada cliente como XML, concatenado) + </clientes>.
        // PISTA: StringBuilder con "<clientes>"; for cliente -> sb.append(clienteAXml(c)); "</clientes>".
        // OJO: el test cuenta que el resultado contiene tantos "<cliente " como elementos y
        //   empieza por "<clientes>". Lista vacía -> "<clientes></clientes>".
        // CULTURA: round-trip y composición — el documento se forma de piezas reutilizables,
        //   igual que un informe se compone de bandas (b38) o un JSON de objetos (b02).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clientesAXml");
    }
}
