package com.masterclass.api.b39_fxdeploy;

import java.util.List;

/**
 * Ejercicio 310 · Versionado semántico (SemVer) y comprobación de actualizaciones.
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 6).
 *
 * <p>Una vez distribuida la app, ¿cómo sabe el cliente que hay una versión nueva? Comparando su
 * versión con la última publicada. Para que esa comparación sea fiable, las versiones siguen el
 * VERSIONADO SEMÁNTICO: {@code MAJOR.MINOR.PATCH} (p.ej. {@code 2.4.1}). Subes PATCH si arreglas un
 * bug, MINOR si añades algo compatible, MAJOR si rompes la compatibilidad.
 *
 * <p>Todo esto es <b>aritmética sobre cadenas</b>: parsear las tres cifras, compararlas en orden y
 * decidir si {@code hayActualizacion}. Es el cierre del módulo DI: tu app ya se documenta, ayuda,
 * recuerda ajustes, se empaqueta y ahora sabe actualizarse.
 */
public final class Ej310VersioningAndUpdate {

    private Ej310VersioningAndUpdate() {
    }

    /**
     * Compara dos versiones SemVer.
     *
     * @param a primera versión ({@code "MAJOR.MINOR.PATCH"})
     * @param b segunda versión
     * @return {@code -1} si a&lt;b, {@code 0} si iguales, {@code 1} si a&gt;b
     */
    public static int compararVersiones(String a, String b) {
        // TODO 1: trata null como "0.0.0" (si a es null, a = "0.0.0"; igual con b).
        // TODO 2: parsea cada versión a 3 enteros con parsearVersion(a) y parsearVersion(b).
        // TODO 3: compara primero el MAJOR (índice 0): si difieren, devuelve -1 o 1 según cuál es mayor.
        // TODO 4: si el MAJOR es igual, compara el MINOR (índice 1) igual; luego el PATCH (índice 2).
        // TODO 5: si las tres cifras coinciden, devuelve 0.
        //         (el test: ("1.0.0","1.0.1") -> -1; ("2.0.0","1.9.9") -> 1; ("1.2.3","1.2.3") -> 0).
        return 0;
    }

    /**
     * ¿Hay una versión más nueva que la instalada?
     *
     * @param actual versión instalada
     * @param ultima última versión publicada
     * @return true si {@code ultima} es estrictamente mayor que {@code actual}
     */
    public static boolean hayActualizacion(String actual, String ultima) {
        // TODO 6: reutiliza compararVersiones(actual, ultima).
        // TODO 7: hay actualización si el resultado es < 0 (actual es ANTERIOR a ultima).
        // TODO 8: devuelve compararVersiones(actual, ultima) < 0.
        //         (el test: ("1.0.0","1.2.0") -> true; ("2.0.0","1.9.0") -> false; iguales -> false).
        return false;
    }

    /**
     * ¿Es una cadena SemVer válida ({@code MAJOR.MINOR.PATCH} con tres números)?
     *
     * @param version la versión a validar
     * @return true si tiene exactamente tres trozos numéricos separados por puntos
     */
    public static boolean esSemverValida(String version) {
        // TODO 9: si version es null o vacía, devuelve false; trocea por "\\." (exige 3 trozos).
        // TODO 10: cada trozo debe ser solo dígitos (matches("\\d+")); devuelve true solo si los 3 lo son.
        //          (el test: "1.2.3" -> true; "1.2" -> false; "1.2.beta" -> false).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("¿Actualizar de 1.0.0 a 1.2.0? " + hayActualizacion("1.0.0", "1.2.0"));
        System.out.println("Comparar 2.0.0 vs 1.9.9: " + compararVersiones("2.0.0", "1.9.9"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Parsear una versión a [major, minor, patch].
     * Trocea {@code "2.4.1"} en {@code {2, 4, 1}}. Lo que falte se rellena con 0.
     */
    public static int[] parsearVersion(String version) {
        // GUÍA: teoría 6.2 (la base de toda comparación: convertir "x.y.z" a 3 números).
        // 1. Crea int[] r = {0,0,0}.
        // 2. Si version es null o vacía, devuelve r.
        // 3. Quita una posible "v" inicial, ignora un posible "-beta" (corta en "-"), trocea por "\\.".
        // 4. Para i de 0 a min(3, trozos.length)-1: r[i] = Integer.parseInt(trozos[i]) si es numérico.
        // PISTA: envuelve el parseInt en try/catch; un trozo no numérico deja ese hueco en 0.
        // OJO: el test: "2.4.1" -> {2,4,1}; "1.2" -> {1,2,0} (patch a 0); "" -> {0,0,0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearVersion");
    }

    /**
     * Reto Extra 2: Número MAJOR.
     * La primera cifra de la versión.
     */
    public static int major(String version) {
        // GUÍA: teoría 6.1 (subir el MAJOR significa "rompo compatibilidad").
        // 1. Devuelve parsearVersion(version)[0].
        // OJO: el test: "2.4.1" -> 2; null -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para major");
    }

    /**
     * Reto Extra 3: Incrementar PATCH.
     * {@code "1.2.3"} -> {@code "1.2.4"} (un arreglo de bug).
     */
    public static String incrementarPatch(String version) {
        // GUÍA: teoría 6.1 (PATCH sube al corregir un bug sin cambiar funcionalidad).
        // 1. Parsea la versión a [M,m,p].
        // 2. Devuelve M + "." + m + "." + (p+1).
        // PISTA: reutiliza parsearVersion(version).
        // OJO: el test: "1.2.3" -> "1.2.4"; "1.2.9" -> "1.2.10" (NO hay acarreo, solo suma 1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarPatch");
    }

    /**
     * Reto Extra 4: Incrementar MINOR.
     * Sube MINOR y REINICIA patch a 0: {@code "1.2.3"} -> {@code "1.3.0"}.
     */
    public static String incrementarMinor(String version) {
        // GUÍA: teoría 6.1 (MINOR sube al añadir algo compatible; el patch vuelve a 0).
        // 1. Parsea la versión a [M,m,p].
        // 2. Devuelve M + "." + (m+1) + ".0".
        // OJO: el test: "1.2.3" -> "1.3.0" (patch a 0, no a 3+1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarMinor");
    }

    /**
     * Reto Extra 5: Incrementar MAJOR.
     * Sube MAJOR y REINICIA minor y patch a 0: {@code "1.2.3"} -> {@code "2.0.0"}.
     */
    public static String incrementarMajor(String version) {
        // GUÍA: teoría 6.1 (MAJOR sube al romper compatibilidad; minor y patch vuelven a 0).
        // 1. Parsea la versión a [M,m,p].
        // 2. Devuelve (M+1) + ".0.0".
        // OJO: el test: "1.2.3" -> "2.0.0"; "0.9.9" -> "1.0.0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarMajor");
    }

    /**
     * Reto Extra 6: Formato con prefijo {@code v}.
     * La convención de las etiquetas de git: {@code "1.2.3"} -> {@code "v1.2.3"}.
     */
    public static String formatoConV(String version) {
        // GUÍA: teoría 6.3 (los tags de git suelen ser v1.2.3; ya lo usaste en el "Acerca de" de Ej306).
        // 1. Si version es null o vacía, devuelve "".
        // 2. Si ya empieza por "v", devuélvela tal cual; si no, antepón "v".
        // OJO: el test: "1.2.3" -> "v1.2.3"; "v1.2.3" -> "v1.2.3" (no duplica la v).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoConV");
    }

    /**
     * Reto Extra 7: Quitar el prefijo {@code v}.
     * La inversa: {@code "v1.2.3"} -> {@code "1.2.3"}.
     */
    public static String quitarV(String version) {
        // GUÍA: teoría 6.3 (normalizar antes de comparar: el comparador trabaja con números, no con la "v").
        // 1. Si version es null, devuelve "".
        // 2. Si empieza por "v" o "V", devuelve la subcadena desde el índice 1; si no, devuélvela igual.
        // OJO: el test: "v1.2.3" -> "1.2.3"; "1.2.3" -> "1.2.3".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para quitarV");
    }

    /**
     * Reto Extra 8: La versión más alta de una lista.
     * Recorre las versiones publicadas y devuelve la mayor (SemVer).
     */
    public static String ultimaVersion(List<String> versiones) {
        // GUÍA: teoría 6.4 (el servidor de actualizaciones devuelve una lista; eliges la más alta).
        // 1. Si versiones es null o vacía, devuelve "0.0.0".
        // 2. Recorre quedándote con la mayor según compararVersiones (empieza por la primera).
        // PISTA: String mejor = versiones.get(0); por cada v, si compararVersiones(v,mejor)>0 -> mejor=v.
        // OJO: el test: ["1.0.0","2.1.0","1.9.9"] -> "2.1.0" (2.1.0 gana a 1.9.9 por el MAJOR).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ultimaVersion");
    }

    /**
     * Reto Extra 9: ¿Es una pre-release?
     * Las versiones de prueba llevan un sufijo tras un guion: {@code "1.2.0-beta"}.
     */
    public static boolean esPrerelease(String version) {
        // GUÍA: teoría 6.5 (SemVer marca las versiones inestables con -alpha/-beta/-rc).
        // 1. Si version es null, devuelve false.
        // 2. Devuelve version.contains("-").
        // OJO: el test: "1.2.0-beta" -> true; "1.2.0" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrerelease");
    }

    /**
     * Reto Extra 10: Tipo de cambio entre dos versiones.
     * Compara dónde está la diferencia: {@code "major"}, {@code "minor"}, {@code "patch"} o {@code "ninguno"}.
     */
    public static String tipoDeCambio(String desde, String hasta) {
        // GUÍA: teoría 6.1 (clasificar un salto de versión para avisar al usuario de su gravedad).
        // 1. Parsea ambas a [M,m,p].
        // 2. Si difieren los MAJOR -> "major"; si no, si difieren los MINOR -> "minor";
        //    si no, si difieren los PATCH -> "patch"; si todo igual -> "ninguno".
        // PISTA: compara índice a índice de mayor a menor peso (0, luego 1, luego 2).
        // OJO: el test: ("1.0.0","2.0.0") -> "major"; ("1.0.0","1.1.0") -> "minor";
        //   ("1.0.0","1.0.1") -> "patch"; ("1.0.0","1.0.0") -> "ninguno".
        // CULTURA: esta clasificación es la que usan los gestores de dependencias (npm ^/~, Maven ranges)
        //   para decidir qué actualizaciones aplicar automáticamente y cuáles avisarte que revises.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoDeCambio");
    }
}
