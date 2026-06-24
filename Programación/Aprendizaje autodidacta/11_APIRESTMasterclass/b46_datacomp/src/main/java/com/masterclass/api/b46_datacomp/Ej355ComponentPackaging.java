package com.masterclass.api.b46_datacomp;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Ejercicio 355 · Empaquetado del componente: JAR, {@code MANIFEST.MF}, SPI y versionado (guion + core mínimo).
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 5).
 *
 * <p>Un componente se ENTREGA como un JAR: un zip con tus {@code .class} y un {@code MANIFEST.MF}
 * que lo describe (título, versión, fabricante, módulo automático...). Aquí no se construye el JAR
 * a mano (eso lo hace Maven); se trabaja con su pieza testeable, el {@link Manifest}: leer sus
 * metadatos, validarlos y razonar sobre versionado semántico (SemVer) y descubrimiento por SPI.
 */
public final class Ej355ComponentPackaging {

    private Ej355ComponentPackaging() {
    }

    /**
     * Extrae los metadatos principales de un manifiesto a un mapa con claves
     * {@code "nombre"}, {@code "version"} y {@code "vendor"}.
     *
     * @param manifest el manifiesto del JAR
     * @return mapa con los tres metadatos (cada ausente como ""), o {@code Map.of()} si es null
     */
    public static Map<String, String> leerMetadatos(Manifest manifest) {
        // TODO 1: si manifest es null, devuelve Map.of().
        // TODO 2: obtén los atributos principales con manifest.getMainAttributes().
        // TODO 3: lee Implementation-Title (usa Attributes.Name.IMPLEMENTATION_TITLE); si es null usa "".
        // TODO 4: lee Implementation-Version (Attributes.Name.IMPLEMENTATION_VERSION); null -> "".
        // TODO 5: lee Implementation-Vendor (Attributes.Name.IMPLEMENTATION_VENDOR); null -> "".
        // TODO 6: mete los tres en un LinkedHashMap con claves "nombre","version","vendor" y devuélvelo.
        return Map.of();
    }

    /**
     * Valida que un manifiesto tenga al menos título y versión (lo mínimo de un componente serio).
     *
     * @param manifest manifiesto a validar
     * @return {@code true} si tiene Implementation-Title e Implementation-Version no vacíos
     */
    public static boolean validarManifiesto(Manifest manifest) {
        // TODO 7: si manifest es null, devuelve false.
        // TODO 8: reutiliza leerMetadatos(manifest) para obtener el mapa.
        // TODO 9: comprueba que "nombre" NO esté vacío (título presente).
        // TODO 10: comprueba que "version" NO esté vacío y devuelve el AND de ambas condiciones.
        return false;
    }

    public static void main(String[] args) {
        Manifest m = new Manifest();
        Attributes a = m.getMainAttributes();
        a.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        a.put(Attributes.Name.IMPLEMENTATION_TITLE, "b46-datacomp");
        a.put(Attributes.Name.IMPLEMENTATION_VERSION, "1.0.0");
        System.out.println(leerMetadatos(m));
        System.out.println(validarManifiesto(m));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: devuelve la versión del manifiesto o "" si no está.
     */
    public static String versionDesdeManifiesto(Manifest manifest) {
        // GUÍA: teoría 5 (Implementation-Version identifica la entrega).
        // 1. reutiliza leerMetadatos y devuelve su "version".
        // PISTA: return leerMetadatos(manifest).getOrDefault("version", "");
        // OJO: el test pone version "1.0.0" -> espera "1.0.0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para versionDesdeManifiesto");
    }

    /**
     * Reto Extra 2: devuelve el vendor o "Desconocido" si el manifiesto no lo trae.
     */
    public static String vendorODefecto(Manifest manifest) {
        // GUÍA: teoría 5 (no todo manifiesto declara fabricante; aplica un valor por defecto).
        // 1. lee el "vendor" de leerMetadatos.
        // 2. si es null o vacío, devuelve "Desconocido"; si no, el vendor.
        // PISTA: String v = leerMetadatos(manifest).get("vendor"); return (v == null || v.isEmpty()) ? "Desconocido" : v;
        // OJO: el test usa un manifiesto SIN vendor -> espera "Desconocido".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vendorODefecto");
    }

    /**
     * Reto Extra 3: lee el atributo {@code Automatic-Module-Name} del manifiesto.
     */
    public static String automaticModuleName(Manifest manifest) {
        // GUÍA: teoría 5 (un JAR clásico puede declarar su nombre de módulo para JPMS, b39).
        // 1. obtén getMainAttributes().getValue("Automatic-Module-Name").
        // 2. si es null, devuelve "".
        // PISTA: String v = manifest.getMainAttributes().getValue("Automatic-Module-Name");
        // OJO: el test pone "com.masterclass.datacomp" -> lo espera tal cual. Es un atributo
        //   "a medida": se lee por nombre String, no hay constante Attributes.Name para él.
        // CULTURA: este nombre es el que usará 'requires' cuando alguien modularice (b39).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para automaticModuleName");
    }

    /**
     * Reto Extra 4: valida que una cadena tenga forma SemVer {@code MAJOR.MINOR.PATCH}.
     */
    public static boolean esVersionSemver(String version) {
        // GUÍA: teoría 5 (versionado semántico: tres números separados por puntos).
        // 1. null -> false.
        // 2. comprueba con la regex ^\d+\.\d+\.\d+$.
        // PISTA: return version != null && version.matches("\\d+\\.\\d+\\.\\d+");
        // OJO: el test exige "1.0.0" -> true y "1.0" -> false (faltan partes). El '$' implícito
        //   de matches obliga a que sean EXACTAMENTE tres números.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVersionSemver");
    }

    /**
     * Reto Extra 5: compara dos versiones SemVer (-1, 0, 1).
     */
    public static int compararVersiones(String a, String b) {
        // GUÍA: teoría 5 (comparar versiones componente a componente, no como texto).
        // 1. parte cada versión por "." y compara major, luego minor, luego patch como enteros.
        // 2. devuelve -1 si a<b, 1 si a>b, 0 si iguales.
        // PISTA: String[] pa = a.split("\\."); compara Integer.parseInt(pa[i]) frente a pb[i].
        // OJO: el test comprueba compararVersiones("1.2.0","1.10.0") == -1. Como TEXTO "2" > "1",
        //   pero como NÚMERO 2 < 10: por eso hay que parsear a int, no comparar cadenas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compararVersiones");
    }

    /**
     * Reto Extra 6: ruta del fichero de proveedor de servicio (SPI) para una interfaz.
     */
    public static String rutaServiceProvider(Class<?> interfaz) {
        // GUÍA: teoría 5 (SPI/ServiceLoader: un JAR declara implementaciones en
        //   META-INF/services/<nombre.completo.de.la.Interfaz>).
        // 1. null -> "".
        // 2. devuelve "META-INF/services/" + interfaz.getName().
        // PISTA: return "META-INF/services/" + interfaz.getName();
        // OJO: el test usa Runnable.class -> "META-INF/services/java.lang.Runnable". Es getName()
        //   (con el paquete), no getSimpleName().
        // CULTURA: así descubre Java los drivers JDBC (b11) o los proveedores de logging sin
        //   que tú los nombres en el código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaServiceProvider");
    }

    /**
     * Reto Extra 7: contenido del fichero SPI (una clase de implementación por línea).
     */
    public static String contenidoServiceProvider(List<String> implementaciones) {
        // GUÍA: teoría 5 (dentro del fichero META-INF/services va la lista de implementaciones).
        // 1. null/empty -> "".
        // 2. une las clases con saltos de línea "\n".
        // PISTA: return String.join("\n", implementaciones);
        // OJO: el test pasa ["a.B","c.D"] -> "a.B\nc.D". Sin línea en blanco al final.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contenidoServiceProvider");
    }

    /**
     * Reto Extra 8: incrementa una parte de la versión SemVer ("major", "minor" o "patch").
     */
    public static String incrementarVersion(String version, String parte) {
        // GUÍA: teoría 5 (un cambio mayor sube MAJOR y resetea el resto; un patch solo sube PATCH).
        // 1. parte la versión en major.minor.patch (enteros).
        // 2. "major" -> major+1 y minor=patch=0; "minor" -> minor+1 y patch=0; "patch" -> patch+1.
        // 3. recompón "major.minor.patch".
        // PISTA: if (parte.equals("minor")) { minor++; patch = 0; }
        // OJO: el test comprueba incrementarVersion("1.2.3","minor") -> "1.3.0" (¡el patch se
        //   resetea a 0!). Subir minor NO conserva el patch anterior.
        // CULTURA: esto automatiza el "bump" de versión de un changelog/release.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarVersion");
    }

    /**
     * Reto Extra 9: ¿dos versiones son compatibles binariamente? (mismo MAJOR en SemVer).
     */
    public static boolean esCompatibleBinariamente(String base, String nueva) {
        // GUÍA: teoría 5 (en SemVer, romper compatibilidad obliga a subir el MAJOR; mismo major
        //   = compatible hacia atrás).
        // 1. extrae el major de cada versión (lo de antes del primer punto).
        // 2. compatibles si los majors coinciden.
        // PISTA: base.split("\\.")[0].equals(nueva.split("\\.")[0]);
        // OJO: el test comprueba ("1.2.0","1.9.9") -> true y ("1.2.0","2.0.0") -> false.
        // CULTURA: por esto pasar de la v2 a la v3 de una librería suele requerir tocar tu código,
        //   pero de la 2.1 a la 2.8 no: el MAJOR es el contrato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCompatibleBinariamente");
    }

    /**
     * Reto Extra 10: genera las líneas {@code requires} de un {@code module-info} (enlace b39).
     */
    public static String moduleInfoRequires(List<String> modulos) {
        // GUÍA: teoría 5 (cuando el componente se modulariza con JPMS, declara sus dependencias
        //   con 'requires <modulo>;' — enlaza con b39 jlink/jpackage).
        // 1. null/empty -> "".
        // 2. por cada módulo genera la línea "requires " + m + ";" y únelas con "\n".
        // PISTA: modulos.stream().map(m -> "requires " + m + ";").collect(Collectors.joining("\n"));
        // OJO: el test pasa ["java.sql","java.desktop"] -> "requires java.sql;\nrequires java.desktop;".
        //   Cada línea acaba en ';' y NO hay salto al final.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para moduleInfoRequires");
    }
}
