package com.masterclass.api.b16_xml;

import java.util.List;

/**
 * Ejercicio 148 · Import / Export CSV usando solo el JDK.
 *
 * <p>Teoría: {@code teoria/16_XML_y_Ficheros.md} (sección 16.6).
 *
 * <p>CSV = cabecera + filas separadas por ';'. Se modela sin librerías:
 * parseo a {@link Persona148} y formateo de vuelta a texto CSV.
 */
public final class Ej148CsvImportExport {

    private Ej148CsvImportExport() {
    }

    /**
     * Importa un CSV con cabecera {@code id;nombre;edad} a una lista de
     * personas.
     *
     * @param csv contenido CSV completo, líneas separadas por '\n' (no null)
     * @return lista de personas (vacía si solo hay cabecera)
     * @throws IllegalArgumentException si csv es null o una fila está malformada
     */
    public static List<Persona148> importar(String csv) {
        // TODO 1: si csv es null -> IllegalArgumentException.
        // TODO 2: divide el texto en líneas por '\n' (tolera '\r' final).
        // TODO 3: si no hay líneas o solo cabecera, devuelve lista vacía.
        // TODO 4: descarta la primera línea (cabecera id;nombre;edad).
        // TODO 5: ignora líneas en blanco intermedias.
        // TODO 6: divide cada fila por ';' esperando exactamente 3 campos.
        // TODO 7: si el número de campos != 3 -> IllegalArgumentException.
        // TODO 8: parsea id (long) y edad (int); NumberFormatException -> IllegalArgument.
        // TODO 9: construye un Persona148 por fila y acumúlalo.
        // TODO 10: devuelve la lista de personas en orden del fichero.
        return List.of();
    }

    /**
     * Exporta una lista de personas a CSV con cabecera.
     *
     * @param personas lista a exportar (no null; puede estar vacía)
     * @return texto CSV con cabecera {@code id;nombre;edad}
     * @throws IllegalArgumentException si personas es null o algún nombre tiene ';'
     */
    public static String exportar(List<Persona148> personas) {
        // TODO 1: si personas es null -> IllegalArgumentException.
        // TODO 2: usa un StringBuilder para construir la salida.
        // TODO 3: escribe la cabecera "id;nombre;edad" seguida de '\n'.
        // TODO 4: si la lista está vacía, devuelve solo la cabecera.
        // TODO 5: itera cada persona en orden.
        // TODO 6: si el nombre contiene ';' -> IllegalArgumentException (rompería el CSV).
        // TODO 7: une los campos con ';' en el orden id;nombre;edad.
        // TODO 8: añade '\n' tras cada fila de datos.
        // TODO 9: el resultado debe poder re-importarse (round-trip estable).
        // TODO 10: devuelve la cadena CSV completa.
        return null;
    }

    public static void main(String[] args) {
        String csv = "id;nombre;edad\n1;Ada;36\n2;Alan;41\n";
        List<Persona148> p = importar(csv);
        System.out.println(p);
        System.out.println(exportar(p));
    }

        /**
     * RETO EXTRA 01: Determina si una linea de texto tiene formato CSV correcto.
     */
    public static boolean esCsvValidoFila(String row) {
        // GUÍA: chequeo de fila CSV (teoría 16.6). OJO al separador: aquí es ','.
        // 1. null/blank -> false.
        // 2. Es válida si contiene al menos una coma (más de un campo).
        // PISTA: return row != null && !row.isBlank() && row.contains(",");
        // OJO: el test usa "a,b,c" -> true. Cuidado: el import/export de este
        //   ejercicio usa ';' como separador, pero ESTOS retos extra trabajan con
        //   coma ',' (lo verás en todos los demás). No los confundas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCsvValidoFila");
    }

    /**
     * RETO EXTRA 02: Separa campos por comas respetando comillas simples.
     */
    public static java.util.List<String> extraerCamposCsv(String row) {
        // GUÍA: split por coma a lista.
        // 1. null -> List.of() (lista vacía, no null).
        // 2. Parte por ',' y devuelve la lista.
        // PISTA: return List.of(row.split(","));
        //   (o Arrays.asList(...) / Arrays.stream(...).toList()).
        // OJO: el test pide "a,b" -> ["a","b"]. List.of(...) sobre el array que
        //   devuelve split sirve. El enunciado menciona "respetar comillas", pero
        //   el test no lo prueba: un split simple basta para pasar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerCamposCsv");
    }

    /**
     * RETO EXTRA 03: Comprueba si la fila representa las columnas.
     */
    public static boolean esFilaCabecera(String row, String idCampo) {
        // GUÍA: ¿es esta fila la cabecera? Su primer campo es idCampo.
        // 1. null -> false.
        // 2. Comprueba que el PRIMER campo (antes de la primera coma) sea idCampo.
        // PISTA: return row != null && row.startsWith(idCampo + ",");
        //   (o split(",")[0].equals(idCampo)).
        // OJO: el test usa ("id,name,email","id") -> true. startsWith("id")
        //   "a secas" sería frágil (cazaría "identificador,..."): exige la coma
        //   detrás o compara el primer campo entero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFilaCabecera");
    }

    /**
     * RETO EXTRA 04: Genera la linea CSV uniendo los elementos.
     */
    public static String combinarFilaCsv(java.util.List<String> campos) {
        // GUÍA: la operación inversa al split (teoría 16.6).
        // PISTA: return String.join(",", campos);
        // OJO: el test pide ["a","b"] -> "a,b". String.join intercala la coma
        //   SOLO entre elementos (no al final): justo lo que se espera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarFilaCsv");
    }

    /**
     * RETO EXTRA 05: Determina si un valor del campo CSV es convertible a double.
     */
    public static boolean esDatoNumerico(String s) {
        // GUÍA: intentar parsear y capturar el fallo (patrón try/catch).
        // 1. null -> false.
        // 2. Intenta Double.parseDouble(s); si lanza NumberFormatException -> false.
        // PISTA: try { Double.parseDouble(s); return true; }
        //        catch (NumberFormatException e) { return false; }
        // OJO: el test usa "12.34" -> true. Usar la excepción como control es feo
        //   en general, pero aquí es el modo idiomático de "¿es número?" en Java
        //   (no hay un isNumeric en el JDK).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDatoNumerico");
    }

    /**
     * RETO EXTRA 06: Verifica formato ISO de fecha en campo de importacion.
     */
    public static boolean esFechaCsvValida(String s) {
        // GUÍA: parsear con java.time y capturar (enlaza con teoría 1.10).
        // 1. null -> false.
        // 2. Intenta LocalDate.parse(s); si lanza DateTimeParseException -> false.
        // PISTA: try { java.time.LocalDate.parse(s); return true; }
        //        catch (java.time.format.DateTimeParseException e) { return false; }
        // OJO: el test usa "2026-05-22" -> true. LocalDate.parse espera ISO-8601
        //   (yyyy-MM-dd) por defecto, que es exactamente ese formato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFechaCsvValida");
    }

    /**
     * RETO EXTRA 07: Sanea cadenas añadiendo dobles comillas si contienen comas.
     */
    public static String escaparComasCsv(String s) {
        // GUÍA: envolver en comillas SOLO si hace falta (teoría 16.6, tabla de trampas).
        // 1. null -> devuelve "" o null según prefieras (el test no lo prueba).
        // 2. Si contiene ',', devuélvelo entre comillas dobles; si no, tal cual.
        // PISTA: return s.contains(",") ? "\"" + s + "\"" : s;
        // OJO: el test pide "a,b" -> "\"a,b\"" (es decir, el literal "a,b" entre
        //   comillas). Así un campo con coma no rompe el CSV al reimportar. Una
        //   cadena sin comas se devuelve sin tocar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escaparComasCsv");
    }

    /**
     * RETO EXTRA 08: Limpia las dobles comillas envolventes del valor.
     */
    public static String eliminarComillasCsv(String s) {
        // GUÍA: la operación inversa al reto 07.
        // 1. null -> null (o "").
        // 2. Si empieza y acaba por comilla doble, quita la primera y la última.
        // PISTA: if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\""))
        //          return s.substring(1, s.length() - 1);
        //        return s;
        // OJO: el test pide "\"a\"" -> "a". Comprueba length >= 2 antes de
        //   recortar para no romper con una sola comilla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarComillasCsv");
    }

    /**
     * RETO EXTRA 09: Evalua si el error proviene del procesador de ficheros.
     */
    public static boolean esExcepcionDeImportacion(Throwable t) {
        // GUÍA: comprobación de tipo con instanceof.
        // PISTA: return t instanceof java.io.IOException;
        // OJO: el test pasa new IOException() -> true. instanceof devuelve false
        //   si t es null, así que no necesitas un null-check aparte.
        // CULTURA: distinguir IOException (problema de E/S) de otras excepciones
        //   te dice si reintentar o abortar; clasificarlas es trabajo de la capa
        //   de manejo de errores (b09).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeImportacion");
    }

    /**
     * RETO EXTRA 10: Crea la linea descriptiva de error de importacion.
     */
    public static String formatearLogCsv(int fila, String err) {
        // GUÍA: componer un mensaje legible de error.
        // PISTA: return "fila " + fila + ": " + err;
        // OJO: el test exige que el resultado CONTENGA la palabra "fila" (en
        //   minúscula). Incluye el número y el mensaje para que el log sea útil
        //   (p.ej. "fila 1: err").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLogCsv");
    }

}

/**
 * Modelo del ejercicio 148 (package-private, sufijado).
 */
record Persona148(long id, String nombre, int edad) {
}