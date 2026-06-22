package com.masterclass.api.b39_fxdeploy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 305 · Javadoc del proyecto y metadatos del JAR ({@code MANIFEST.MF}).
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 1).
 *
 * <p>Cuando empaquetas tu aplicación en un {@code .jar}, dentro va un fichero de texto especial,
 * {@code META-INF/MANIFEST.MF}, con los METADATOS del programa: qué clase tiene el {@code main}, qué
 * versión es, quién la hizo. Es una lista de pares {@code Clave: Valor}, uno por línea. Documentar tu
 * proyecto empieza aquí: rellenar bien ese manifest y escribir el Javadoc de cada clase/paquete.
 *
 * <p>Lo testeable es <b>parsear</b> ese formato {@code Clave: Valor} a un {@link Map}, <b>leer</b>
 * atributos concretos (la versión, el {@code Main-Class}) y <b>medir</b> la cobertura de Javadoc. El
 * Javadoc real (las etiquetas {@code @param}, {@code @return}, {@code package-info.java}) se enseña
 * en la teoría; aquí trabajamos con su huella en texto.
 */
public final class Ej305JavadocAndManifest {

    private Ej305JavadocAndManifest() {
    }

    /**
     * Parsea las líneas de un {@code MANIFEST.MF} (formato {@code Clave: Valor}) a un mapa que
     * conserva el orden de aparición.
     *
     * @param lineas líneas del manifest (puede ser null)
     * @return mapa clave→valor (vacío si no hay líneas); las líneas en blanco o sin {@code ": "} se ignoran
     */
    public static Map<String, String> parsearManifest(List<String> lineas) {
        // TODO 1: si lineas es null o está vacía, devuelve Map.of().
        // TODO 2: crea un LinkedHashMap (conserva el orden) y recorre cada línea.
        // TODO 3: ignora la línea si está en blanco (isBlank) o no contiene ": ".
        // TODO 4: parte por el PRIMER ": " (indexOf), pon clave.trim() -> valor.trim() y devuelve el mapa.
        //         (el test: "Manifest-Version: 1.0" y "Main-Class: com.App" -> 2 entradas).
        return Map.of();
    }

    /**
     * Lee un atributo del manifest ya parseado.
     *
     * @param manifest mapa devuelto por {@link #parsearManifest(List)} (puede ser null)
     * @param clave    nombre del atributo
     * @return el valor, o {@code ""} si no existe / entradas nulas
     */
    public static String valorDe(Map<String, String> manifest, String clave) {
        // TODO 5: si manifest o clave son null, devuelve "".
        // TODO 6: devuelve manifest.getOrDefault(clave, "").
        //         (el test: leer "Main-Class" de un manifest -> "com.App"; clave inexistente -> "").
        return "";
    }

    /**
     * Versión de implementación declarada en el manifest ({@code Implementation-Version}), con un
     * valor de respaldo si falta.
     *
     * @param manifest mapa del manifest (puede ser null)
     * @return la versión declarada, o {@code "0.0.0"} si no aparece o está vacía
     */
    public static String versionImplementacion(Map<String, String> manifest) {
        // TODO 7: si manifest es null, devuelve "0.0.0".
        // TODO 8: lee el valor de "Implementation-Version" reutilizando valorDe(manifest, ...).
        // TODO 9: si ese valor es null o está en blanco (isBlank), devuelve "0.0.0".
        // TODO 10: en otro caso, devuelve el valor leído.
        //          (el test: manifest con Implementation-Version: 2.4.1 -> "2.4.1"; sin él -> "0.0.0").
        return "";
    }

    public static void main(String[] args) {
        List<String> lineas = List.of(
                "Manifest-Version: 1.0",
                "Main-Class: com.masterclass.api.App",
                "Implementation-Version: 2.4.1");
        Map<String, String> mf = parsearManifest(lineas);
        System.out.println("Main-Class: " + valorDe(mf, "Main-Class"));
        System.out.println("Versión: " + versionImplementacion(mf));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Título de implementación.
     * El nombre legible de la app, declarado en {@code Implementation-Title}.
     */
    public static String tituloImplementacion(Map<String, String> manifest) {
        // GUÍA: teoría 1.2 (los atributos Implementation-* identifican tu build).
        // 1. Reutiliza valorDe(manifest, "Implementation-Title").
        // OJO: el test lee "Mi App" de un manifest que lo trae; sin él -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tituloImplementacion");
    }

    /**
     * Reto Extra 2: Fabricante.
     * Quién publica la app, declarado en {@code Implementation-Vendor}.
     */
    public static String vendorImplementacion(Map<String, String> manifest) {
        // GUÍA: teoría 1.2 (Implementation-Vendor lo usa también jpackage como --vendor, ver Ej309).
        // 1. Reutiliza valorDe(manifest, "Implementation-Vendor").
        // OJO: el test: manifest con Implementation-Vendor: ACME -> "ACME".
        // CULTURA: este mismo dato es el --vendor del instalador nativo en Ej309.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vendorImplementacion");
    }

    /**
     * Reto Extra 3: Clase principal.
     * La clase con el {@code public static void main}, declarada en {@code Main-Class}.
     */
    public static String claseMain(Map<String, String> manifest) {
        // GUÍA: teoría 1.1 (sin Main-Class, `java -jar app.jar` no sabe por dónde arrancar).
        // 1. Reutiliza valorDe(manifest, "Main-Class").
        // OJO: el test: "com.masterclass.api.App"; sin el atributo -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseMain");
    }

    /**
     * Reto Extra 4: ¿Existe el atributo?
     * true si el manifest declara esa clave (aunque su valor fuera "").
     */
    public static boolean tieneAtributo(Map<String, String> manifest, String clave) {
        // GUÍA: teoría 1.2 (distinguir "no está" de "está vacío").
        // 1. Si manifest o clave son null, devuelve false.
        // 2. Devuelve manifest.containsKey(clave).
        // OJO: el test: contiene "Main-Class" -> true; "No-Existe" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAtributo");
    }

    /**
     * Reto Extra 5: Número de atributos.
     * Cuántos pares Clave: Valor tiene el manifest parseado.
     */
    public static int numeroDeAtributos(Map<String, String> manifest) {
        // GUÍA: teoría 1.2 (tamaño del mapa de metadatos).
        // 1. Si manifest es null, devuelve 0; si no, manifest.size().
        // OJO: el test: un manifest de 3 líneas válidas -> 3; null -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeAtributos");
    }

    /**
     * Reto Extra 6: Construir una línea de manifest.
     * Dados clave y valor, devuelve la línea en formato {@code "Clave: Valor"}.
     */
    public static String lineaManifest(String clave, String valor) {
        // GUÍA: teoría 1.1 (el formato es EXACTAMENTE "clave: valor", con un espacio tras los dos puntos).
        // 1. Si clave es null o vacía, devuelve "".
        // 2. Devuelve clave + ": " + (valor == null ? "" : valor).
        // OJO: el test: ("Main-Class","com.App") -> "Main-Class: com.App".
        //   El ": " (dos puntos + espacio) es obligatorio: es el separador que parsearManifest busca.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lineaManifest");
    }

    /**
     * Reto Extra 7: Class-Path como lista.
     * El atributo {@code Class-Path} es una lista de jars separados por ESPACIOS; devuélvela troceada.
     */
    public static List<String> classpath(Map<String, String> manifest) {
        // GUÍA: teoría 1.3 (Class-Path lista las dependencias relativas al jar).
        // 1. Lee valorDe(manifest, "Class-Path").
        // 2. Si está vacío, devuelve List.of(); si no, split por espacios (uno o más: " +") y devuélvelo.
        // PISTA: valor.trim().split(" +") y List.of(array).
        // OJO: el test: "lib/a.jar lib/b.jar" -> ["lib/a.jar","lib/b.jar"]; sin Class-Path -> lista vacía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para classpath");
    }

    /**
     * Reto Extra 8: Cobertura de Javadoc.
     * Porcentaje ENTERO de elementos documentados sobre el total (redondeo hacia abajo).
     */
    public static int porcentajeDocumentado(int documentados, int total) {
        // GUÍA: teoría 1.4 (una métrica de calidad: ¿qué % de tus clases/métodos públicos tienen Javadoc?).
        // 1. Si total <= 0, devuelve 0 (evita dividir por cero).
        // 2. Devuelve documentados * 100 / total (división entera -> trunca).
        // PISTA: ojo al orden: multiplica por 100 ANTES de dividir, o el entero te dará siempre 0.
        // OJO: el test: (7,10) -> 70; (1,3) -> 33 (no 33.3); (0,0) -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeDocumentado");
    }

    /**
     * Reto Extra 9: Nombre del JAR.
     * El fichero que produce Maven: {@code artifactId-version.jar}.
     */
    public static String nombreJar(String artifactId, String version) {
        // GUÍA: teoría 1.5 (la convención de nombres de artefacto de Maven).
        // 1. Si artifactId es null o vacío, usa "app".
        // 2. Si version es null o vacía, devuelve artifactId + ".jar".
        // 3. En otro caso, devuelve artifactId + "-" + version + ".jar".
        // OJO: el test: ("b39_fxdeploy","1.0.0") -> "b39_fxdeploy-1.0.0.jar"; (null,null) -> "app.jar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreJar");
    }

    /**
     * Reto Extra 10: ¿Es una versión semántica válida?
     * Comprueba el formato {@code MAJOR.MINOR.PATCH} con tres números separados por puntos.
     */
    public static boolean esVersionValida(String version) {
        // GUÍA: teoría 1.5 (Implementation-Version debería seguir SemVer; lo profundizas en Ej310).
        // 1. Si version es null o vacía, devuelve false.
        // 2. Trocea por "\\." y exige EXACTAMENTE 3 trozos.
        // 3. Cada trozo debe ser solo dígitos (matches("\\d+")).
        // PISTA: version.split("\\.") -> length == 3 y cada parte.matches("\\d+").
        // OJO: el test: "1.0.0" -> true; "1.0" -> false (faltan trozos); "1.x.0" -> false (no es número).
        // CULTURA: esta validación es la base del versionado semántico que comparas en Ej310 (hayActualizacion).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVersionValida");
    }

    /** Helper de demostración: arma un manifest mutable a partir de pares clave/valor. */
    static Map<String, String> manifestDe(String... pares) {
        Map<String, String> m = new LinkedHashMap<>();
        for (int i = 0; i + 1 < pares.length; i += 2) {
            m.put(pares[i], pares[i + 1]);
        }
        return m;
    }
}
