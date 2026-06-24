package com.masterclass.api.b42_mobile;

import java.util.Locale;

/**
 * Ejercicio 330 · Firma del APK, stores y distribución (guion).
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 6).
 *
 * <p>Publicar una app tiene reglas duras: cada versión que subes a Google Play debe llevar un
 * <strong>versionCode</strong> (entero) ESTRICTAMENTE mayor que el anterior y un
 * <strong>versionName</strong> legible (SemVer {@code 1.0.0}); el artefacto ({@code .apk} o el
 * moderno {@code .aab}) debe ir <strong>firmado</strong> con tu keystore (sin firma, Play lo
 * rechaza). Este ejercicio es "guion": no construye un APK real, pero modela y valida esas reglas y
 * los comandos como cadenas, igual que {@code b39} hizo con {@code jpackage}. Reutiliza el SemVer de
 * {@code b39} y la idea de firma de {@code b30} (criptografía).
 */
public final class Ej330PublishDistribution {

    private Ej330PublishDistribution() {
    }

    /**
     * ¿Es válido un {@code versionName} con formato SemVer {@code MAJOR.MINOR.PATCH}?
     *
     * @param versionName cadena de versión visible al usuario (p.ej. {@code "1.4.2"})
     * @return {@code true} si tiene exactamente tres números enteros &ge; 0 separados por puntos
     */
    public static boolean esVersionNameValida(String versionName) {
        // TODO 1: si versionName es null o blank -> false.
        // TODO 2: parte por '.' usando split("\\.", -1) (el -1 conserva trozos vacíos como "1..0").
        // TODO 3: debe haber EXACTAMENTE 3 partes (MAJOR, MINOR, PATCH).
        // TODO 4: cada parte debe ser no vacía y compuesta SOLO por dígitos (sin signos ni espacios).
        // TODO 5: devuelve true solo si las tres partes cumplen; cualquier fallo -> false.
        return false;
    }

    /**
     * Calcula el siguiente {@code versionCode} (Google Play exige que crezca en cada publicación).
     *
     * @param actual versionCode actual (entero &ge; 0)
     * @return {@code actual + 1}
     * @throws IllegalArgumentException si {@code actual} es negativo
     * @throws IllegalStateException    si {@code actual} ya es {@code Integer.MAX_VALUE} (no puede crecer)
     */
    public static int siguienteVersionCode(int actual) {
        // TODO 6: el versionCode es un entero interno; un valor negativo no tiene sentido -> IllegalArgumentException.
        // TODO 7: regla de Play: cada subida DEBE incrementar el versionCode (monótono creciente).
        // TODO 8: si actual == Integer.MAX_VALUE no se puede incrementar sin desbordar -> IllegalStateException.
        // TODO 9: en el caso normal, el siguiente es actual + 1.
        // TODO 10: devuelve actual + 1.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("'1.4.2' válida? " + esVersionNameValida("1.4.2"));
        System.out.println("versionCode 7 -> " + siguienteVersionCode(7));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Incrementa el PATCH de un versionName SemVer ({@code 1.0.0} -> {@code 1.0.1}).
     */
    public static String incrementarPatch(String versionName) {
        // GUÍA: teoría 6.2 (subir el patch = corrección sin cambios de API; reutiliza el SemVer de b39).
        // 1. Si !esVersionNameValida(versionName) -> IllegalArgumentException.
        // 2. parte por '.', suma 1 al tercer número y recompón "MAJOR.MINOR.(PATCH+1)".
        // PISTA: String[] p = versionName.split("\\."); ... (Integer.parseInt(p[2]) + 1).
        // OJO: el test: "1.0.0" -> "1.0.1"; "2.9.9" -> "2.9.10" (no hay acarreo, solo el patch sube).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarPatch");
    }

    /**
     * Reto Extra 2: Nombre del artefacto generado: {@code <app>-<version>-<tipo>.apk}.
     */
    public static String nombreArtefacto(String app, String version, String tipo) {
        // GUÍA: teoría 6.3 (un nombre con versión y build type evita confundir builds al distribuir).
        // 1. Si app, version o tipo son null/blank -> IllegalArgumentException.
        // 2. Formato: app + "-" + version + "-" + tipo + ".apk".
        // PISTA: return "%s-%s-%s.apk".formatted(app, version, tipo);
        // OJO: el test: ("miapp","1.0.0","release") -> "miapp-1.0.0-release.apk".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreArtefacto");
    }

    /**
     * Reto Extra 3: ¿Es un formato de publicación soportado? ({@code apk} o {@code aab}).
     */
    public static boolean esFormatoPublicacion(String formato) {
        // GUÍA: teoría 6.4 (Play recomienda AAB —Android App Bundle—; APK sigue valiendo para sideload).
        // 1. null -> false.
        // 2. true si (ignorando mayúsculas) es "apk" o "aab".
        // PISTA: "apk".equalsIgnoreCase(formato) || "aab".equalsIgnoreCase(formato).
        // OJO: el test: "aab" -> true, "AAB" -> true, "exe" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFormatoPublicacion");
    }

    /**
     * Reto Extra 4: Extensión de fichero (con punto) para un formato de publicación.
     */
    public static String extensionPorFormato(String formato) {
        // GUÍA: teoría 6.4 (cada formato tiene su extensión; valídalo primero).
        // 1. Si !esFormatoPublicacion(formato) -> "".
        // 2. "apk" -> ".apk"; "aab" -> ".aab" (normaliza a minúsculas).
        // PISTA: "." + formato.toLowerCase().
        // OJO: el test: "AAB" -> ".aab"; "zip" -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionPorFormato");
    }

    /**
     * Reto Extra 5: ¿Es válido el alias de la clave de firma? (no vacío, sin espacios).
     */
    public static boolean aliasValido(String alias) {
        // GUÍA: teoría 6.5 (el alias identifica tu clave dentro del keystore; entra en el comando de firma).
        // 1. null/blank -> false.
        // 2. false si contiene algún espacio en blanco.
        // PISTA: !alias.isBlank() && !alias.contains(" ").
        // OJO: el test: "miclave" -> true, "mi clave" -> false, "" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aliasValido");
    }

    /**
     * Reto Extra 6: Construye el comando de firma con {@code apksigner} (guion).
     */
    public static String comandoFirma(String keystore, String alias) {
        // GUÍA: teoría 6.5 (apksigner firma el APK con tu clave; sin firma, Play lo rechaza).
        // 1. Si keystore es null/blank o !aliasValido(alias) -> IllegalArgumentException.
        // 2. Formato: "apksigner sign --ks " + keystore + " --ks-key-alias " + alias + " app.apk".
        // PISTA: usa String.format/formatted con esos cuatro tokens fijos.
        // OJO: el test compara con equals "apksigner sign --ks mi.jks --ks-key-alias clave app.apk".
        // CULTURA: firmar = aplicar tu clave privada (b30, criptografía): el APK lleva una firma digital
        //   que Play verifica con tu certificado. Es el mismo principio que firmar un JWT o un mensaje.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoFirma");
    }

    /**
     * Reto Extra 7: ¿Está la build LISTA para publicar como release?
     */
    public static boolean esReleaseListo(boolean firmado, boolean minificado, boolean debuggable) {
        // GUÍA: teoría 6.6 (una release debe ir firmada, ofuscada/minificada y NUNCA debuggable).
        // 1. devuelve firmado && minificado && !debuggable.
        // OJO: el test: (true,true,false) -> true; (true,true,true) -> false (debuggable lo invalida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esReleaseListo");
    }

    /**
     * Reto Extra 8: Tamaño legible del artefacto (B / KB / MB), con punto decimal ({@code Locale.US}).
     */
    public static String tamanoLegible(long bytes) {
        // GUÍA: teoría 6.7 (Play muestra el tamaño de descarga; conviene reportarlo legible).
        // 1. Si bytes < 0 -> IllegalArgumentException.
        // 2. < 1024 -> "<bytes> B"; < 1024*1024 -> "%.1f KB"; en otro caso -> "%.1f MB".
        // PISTA: String.format(Locale.US, "%.1f MB", bytes / (1024.0*1024.0)). El Locale.US fuerza el PUNTO.
        // OJO: el test: 512 -> "512 B"; 1048576 -> "1.0 MB" (con punto, no coma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoLegible");
    }

    /**
     * Reto Extra 9: ¿Es una categoría de store reconocida?
     */
    public static boolean categoriaStoreValida(String categoria) {
        // GUÍA: teoría 6.8 (la ficha de Play exige una categoría de una lista cerrada).
        // 1. null -> false.
        // 2. true si (ignorando mayúsculas) es una de: GAME, SOCIAL, TOOLS, EDUCATION, PRODUCTIVITY.
        // PISTA: List.of("GAME","SOCIAL","TOOLS","EDUCATION","PRODUCTIVITY").contains(categoria.toUpperCase()).
        // OJO: el test: "tools" -> true (case-insensitive), "comida" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para categoriaStoreValida");
    }

    /**
     * Reto Extra 10: Comando Gradle para generar el bundle de release ({@code .aab}).
     */
    public static String comandoBundleRelease() {
        // GUÍA: teoría 6.9 ("./gradlew bundleRelease" produce el .aab firmado que se sube a Play).
        // 1. devuelve exactamente "./gradlew bundleRelease".
        // OJO: el test compara con equals; sin argumentos ni rutas.
        // CULTURA: es el "mvn package" de Android para publicar. Versionado (b39 SemVer), firma (b30) y
        //   build (Gradle ≈ Maven b04) confluyen aquí: este es el último paso antes de la store.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoBundleRelease");
    }
}
