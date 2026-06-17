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

    /**
     * Reto Extra 1: Comprueba si el nombre de migración tiene formato valido.
     */
    public static boolean esFormatoValido(String fichero) {
        // GUÍA: teoría 14.10. Formato válido = V + uno o más dígitos + "__" + algo.
        // 1. Lo más limpio es una expresión regular:
        //    return fichero != null && fichero.matches("V\\d+__.*");
        //    (\\d+ exige AL MENOS un dígito; por eso "V__init.sql" NO casa).
        // 2. Alternativa: intenta versionDe en try/catch y devuelve false si lanza.
        // OJO: el test pide true para "V1__init.sql" y false para "V__init.sql"
        //      (sin número de versión).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFormatoValido");
    }

    /**
     * Reto Extra 2: Extrae la descripcion de un fichero de migracion.
     */
    public static String descripcionDe(String fichero) {
        // GUÍA: la descripción es lo que va tras "__", sin la extensión y con los
        // guiones bajos convertidos en espacios.
        // 1. Quédate con lo de después de "__": fichero.split("__", 2)[1] →
        //    "add_email.sql".
        // 2. Quita ".sql": .replace(".sql", "").
        // 3. Cambia '_' por ' ': .replace('_', ' ').
        // PISTA: split("__", 2) limita a 2 trozos (por si la descripción tuviera "__").
        // OJO: "V2__add_email.sql" → "add email" (con espacio, sin .sql).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para descripcionDe");
    }

    /**
     * Reto Extra 3: Comprueba si es una migracion repetible (empieza por 'R').
     */
    public static boolean esMigracionRepetible(String fichero) {
        // GUÍA: teoría 14.10. Las repetibles empiezan por 'R'.
        // 1. Una línea: return fichero != null && fichero.startsWith("R");
        // OJO: el test pide true para "R__view.sql" y false para "V1__init.sql".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMigracionRepetible");
    }

    /**
     * Reto Extra 4: Comprueba si es una migracion versionada.
     */
    public static boolean esMigracionVersionada(String fichero) {
        // GUÍA: las versionadas empiezan por 'V'. Espejo del reto 3.
        // 1. Una línea: return fichero != null && fichero.startsWith("V");
        // OJO: el test pide true para "V1__init.sql" y false para "R__view.sql".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMigracionVersionada");
    }

    /**
     * Reto Extra 5: Filtra y devuelve solo las versiones de las migraciones validas.
     */
    public static java.util.List<Integer> obtenerVersiones(java.util.List<String> ficheros) {
        // GUÍA: mapea cada fichero a su versión con un stream (reutiliza versionDe).
        // 1. return ficheros.stream().map(Ej132FlywayMigrations::versionDe).toList();
        // OJO: el test pasa ["V1__init.sql","V2__x.sql"] y espera List.of(1, 2)
        //      (en ese mismo orden). Conecta con los streams de la teoría 1.3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerVersiones");
    }

    /**
     * Reto Extra 6: Comprueba si hay versiones duplicadas en una lista de ficheros.
     */
    public static boolean tieneDuplicados(java.util.List<String> ficheros) {
        // GUÍA: hay duplicados si al pasar las versiones a un Set se pierde tamaño.
        // 1. Obtén las versiones (reutiliza obtenerVersiones del reto 5).
        // 2. Compara: return new HashSet<>(versiones).size() != versiones.size();
        //    (un Set descarta repetidos; si encoge, había duplicados).
        // OJO: el test pasa ["V1__init.sql","V1__dup.sql"] (ambas versión 1) y
        //      espera true. CULTURA: Flyway aborta el arranque si encuentra dos
        //      migraciones con la misma versión.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneDuplicados");
    }

    /**
     * Reto Extra 7: Retorna el prefijo del fichero de migracion.
     */
    public static String prefijoDe(String fichero) {
        // GUÍA: el prefijo es la primera letra (V/R/U) como String.
        // 1. Una línea: return fichero.substring(0, 1);
        //    (o String.valueOf(fichero.charAt(0)) — pero charAt devuelve char, no String).
        // OJO: el test pasa "V1__init.sql" y espera "V" (un String, no un char).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prefijoDe");
    }

    /**
     * Reto Extra 8: Comprueba si el fichero de migracion es de tipo rollback (empieza por 'U').
     */
    public static boolean esRollback(String fichero) {
        // GUÍA: teoría 14.10. Los undo/rollback empiezan por 'U'.
        // 1. Una línea: return fichero != null && fichero.startsWith("U");
        // OJO: el test pide true para "U1__undo.sql" y false para "V1__init.sql".
        // Mismo patrón que los retos 3 y 4 (reutiliza prefijoDe si quieres).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRollback");
    }

    /**
     * Reto Extra 9: Compara dos migraciones por su version.
     */
    public static int compararVersiones(String f1, String f2) {
        // GUÍA: compara las versiones numéricas (reutiliza versionDe).
        // 1. return Integer.compare(versionDe(f1), versionDe(f2));
        // PISTA: Integer.compare devuelve <0, 0 o >0 (ideal para Comparator/sort).
        // OJO: el test comprueba que comparar V1 con V2 da un valor < 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compararVersiones");
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto estructurada.
     */
    public static String formatearMigracion(String fichero) {
        // GUÍA: combina versionDe y descripcionDe (retos base y 2).
        // 1. return "Migracion[V=" + versionDe(fichero) + ", Desc=" + descripcionDe(fichero) + "]";
        // OJO: el test pasa "V1__init.sql" y espera "Migracion[V=1, Desc=init]".
        //      Como "init" no tiene guiones bajos, descripcionDe lo devuelve tal cual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearMigracion");
    }



}
