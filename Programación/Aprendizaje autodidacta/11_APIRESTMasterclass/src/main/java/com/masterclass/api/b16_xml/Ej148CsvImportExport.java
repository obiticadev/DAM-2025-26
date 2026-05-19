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
}

/**
 * Modelo del ejercicio 148 (package-private, sufijado).
 */
record Persona148(long id, String nombre, int edad) {
}
