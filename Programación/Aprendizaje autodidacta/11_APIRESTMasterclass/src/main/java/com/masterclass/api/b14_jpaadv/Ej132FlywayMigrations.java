package com.masterclass.api.b14_jpaadv;

import java.util.List;

/**
 * Ejercicio 132 · Migraciones versionadas (lógica de Flyway).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.4).
 *
 * <p>Flyway aplica ficheros {@code V<n>__desc.sql} en orden de versión.
 */
public final class Ej132FlywayMigrations {

    private Ej132FlywayMigrations() {
    }

    /**
     * Extrae el número de versión de un nombre de migración Flyway.
     *
     * @param fichero p.ej. "V2__add_email.sql"
     * @return el número (2 en el ejemplo)
     * @throws IllegalArgumentException si el formato no es válido
     */
    public static int versionDe(String fichero) {
        // TODO 1: si fichero es null/blank -> IllegalArgumentException.
        // TODO 2: debe empezar por 'V' (mayúscula).
        // TODO 3: debe contener el separador "__".
        // TODO 4: la versión es lo que va entre 'V' y "__".
        // TODO 5: parsea ese fragmento a int (try/catch -> IllegalArgumentException).
        // TODO 6: devuelve la versión.
        return -1;
    }

    /**
     * Ordena migraciones por versión ascendente y devuelve la siguiente versión a crear.
     *
     * @param ficheros lista de nombres de migración (orden arbitrario)
     * @return versión siguiente = (máxima versión existente) + 1; 1 si lista vacía
     */
    public static int siguienteVersion(List<String> ficheros) {
        // TODO 7: si la lista es null o vacía -> devuelve 1 (primera migración).
        // TODO 8: mapea cada fichero a su versión con versionDe (stream).
        // TODO 9: obtén el máximo (max()).
        // TODO 10: devuelve max + 1 (la siguiente migración a aplicar).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(siguienteVersion(List.of("V1__init.sql", "V3__x.sql", "V2__y.sql")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si fichero es null/blank -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: debe empezar por 'V' (mayúscula).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: debe contener el separador "__".
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: la versión es lo que va entre 'V' y "__".
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: parsea ese fragmento a int (try/catch -> IllegalArgumentException).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: devuelve la versión.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: si la lista es null o vacía -> devuelve 1 (primera migración).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: mapea cada fichero a su versión con versionDe (stream).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: obtén el máximo (max()).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve max + 1 (la siguiente migración a aplicar).
    }

}
