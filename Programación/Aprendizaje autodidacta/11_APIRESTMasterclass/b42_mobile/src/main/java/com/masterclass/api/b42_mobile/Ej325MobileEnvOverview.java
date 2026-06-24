package com.masterclass.api.b42_mobile;

import java.util.List;

/**
 * Ejercicio 325 · Entorno móvil, SDK/AVD y estructura de proyecto (guion).
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 1).
 *
 * <p>Antes de escribir una línea de UI, un proyecto Android se define por su <strong>configuración
 * de SDK</strong> (tres niveles de API: {@code minSdk} &le; {@code targetSdk} &le; {@code compileSdk}),
 * por su <strong>estructura de carpetas</strong> (el {@code AndroidManifest.xml}, los scripts de
 * Gradle, el código en {@code src/main/java} y los recursos en {@code res/}) y por un
 * <strong>AVD</strong> (Android Virtual Device, el emulador). Este ejercicio es "guion": Android
 * usa Gradle, no Maven, así que aquí modelamos esas reglas como funciones puras sobre cadenas y
 * números, igual que se hizo con el Dockerfile en {@code b22}. Cero pantalla, cero emulador.
 */
public final class Ej325MobileEnvOverview {

    private Ej325MobileEnvOverview() {
    }

    /**
     * ¿Es coherente una configuración de niveles de API de Android?
     *
     * @param minSdk     API mínima soportada (un dispositivo más antiguo no instala la app)
     * @param targetSdk  API para la que está probada/optimizada
     * @param compileSdk API contra la que se compila (debe ser la más alta o igual)
     * @return {@code true} solo si los tres son &ge; 1 y se cumple min &le; target &le; compile
     */
    public static boolean esConfiguracionSdkValida(int minSdk, int targetSdk, int compileSdk) {
        // TODO 1: si minSdk < 1 -> false (no existe API 0; la 1 fue Android 1.0).
        // TODO 2: si targetSdk < 1 || compileSdk < 1 -> false.
        // TODO 3: el orden obligatorio es minSdk <= targetSdk (no puedes apuntar más bajo que tu mínimo).
        // TODO 4: y targetSdk <= compileSdk (no compilas contra una API menor que la que apuntas).
        // TODO 5: devuelve true solo si TODAS las condiciones anteriores se cumplen.
        return false;
    }

    /**
     * Clasifica un fichero de un proyecto Android por su papel en la estructura.
     *
     * @param ruta ruta relativa del fichero dentro del proyecto
     * @return {@code "manifest"}, {@code "gradle"}, {@code "recurso"}, {@code "codigo"} o
     *         {@code "desconocido"} (nunca {@code null})
     */
    public static String clasificarFichero(String ruta) {
        // TODO 6: si ruta es null o blank -> "desconocido".
        // TODO 7: si termina en "AndroidManifest.xml" -> "manifest" (el descriptor de la app).
        // TODO 8: si termina en ".gradle" o ".gradle.kts" -> "gradle" (los scripts de build).
        // TODO 9: si contiene "/res/" -> "recurso" (layouts XML, drawables, strings, mipmaps...).
        // TODO 10: si termina en ".java" o ".kt" -> "codigo"; en cualquier otro caso -> "desconocido".
        return "";
    }

    public static void main(String[] args) {
        System.out.println("min21/target33/compile34 válido? " + esConfiguracionSdkValida(21, 33, 34));
        System.out.println("AndroidManifest.xml -> " + clasificarFichero("app/src/main/AndroidManifest.xml"));
        System.out.println("MainActivity.java -> " + clasificarFichero("app/src/main/java/MainActivity.java"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es válido un nombre de paquete (applicationId) tipo {@code com.empresa.app}?
     */
    public static boolean esNombrePaqueteValido(String paquete) {
        // GUÍA: teoría 1.3 (el applicationId identifica la app de forma única en Google Play).
        // 1. null o blank -> false.
        // 2. Debe tener AL MENOS dos segmentos separados por '.' (dominio invertido: com.empresa).
        // 3. Cada segmento: solo minúsculas/dígitos/_, y NO puede empezar por dígito.
        // PISTA: String[] seg = paquete.split("\\.", -1); exige seg.length >= 2 y valida
        //        cada uno con seg[i].matches("[a-z_][a-z0-9_]*").
        // OJO: el test exige true para "com.empresa.app", false para "app" (1 segmento),
        //   "Com.Empresa" (mayúsculas) y "com.2fast" (segmento que empieza por dígito).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombrePaqueteValido");
    }

    /**
     * Reto Extra 2: Construye la ruta de un recurso Android: {@code res/<tipo>/<nombre>}.
     */
    public static String rutaRecurso(String tipo, String nombre) {
        // GUÍA: teoría 1.4 (los recursos se agrupan por tipo: layout, drawable, values, mipmap...).
        // 1. Si tipo o nombre son null/blank -> IllegalArgumentException.
        // 2. Formato EXACTO: "res/" + tipo + "/" + nombre.
        // PISTA: return "res/%s/%s".formatted(tipo, nombre);
        // OJO: el test compara con equals "res/layout/activity_main.xml" y exige excepción si tipo es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaRecurso");
    }

    /**
     * Reto Extra 3: Genera el nombre de un AVD a partir de dispositivo y nivel de API.
     */
    public static String nombreAvd(String dispositivo, int api) {
        // GUÍA: teoría 1.5 (un AVD es un emulador con un perfil de hardware + una imagen de sistema).
        // 1. Si dispositivo es null/blank o api < 1 -> IllegalArgumentException.
        // 2. Reemplaza los espacios del dispositivo por '_' y añade "_API_<api>".
        // PISTA: return dispositivo.trim().replace(' ', '_') + "_API_" + api;
        // OJO: el test: ("Pixel 6", 33) -> "Pixel_6_API_33".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreAvd");
    }

    /**
     * Reto Extra 4: Nombre comercial de Android para un nivel de API (los que pide el test).
     */
    public static String nombreVersionAndroid(int api) {
        // GUÍA: teoría 1.2 (cada API level tiene una versión: 33=Android 13 "Tiramisu").
        // 1. Mapea: 31 -> "Android 12", 32 -> "Android 12L", 33 -> "Android 13", 34 -> "Android 14".
        // 2. Cualquier otro valor -> "Desconocido".
        // PISTA: un switch (switch expression) sobre 'api' es lo más limpio.
        // OJO: el test comprueba 33 -> "Android 13" y 99 -> "Desconocido".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreVersionAndroid");
    }

    /**
     * Reto Extra 5: Carpeta de recursos para una densidad de pantalla (bucket dpi).
     */
    public static String carpetaDensidad(String bucket) {
        // GUÍA: teoría 1.4 (Android sirve el drawable según la densidad: mdpi=160dpi base, hdpi=240...).
        // 1. null/blank -> "".
        // 2. Devuelve "drawable-" + bucket en minúsculas (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi).
        // PISTA: return "drawable-" + bucket.trim().toLowerCase();
        // OJO: el test: "XHDPI" -> "drawable-xhdpi" (normaliza a minúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para carpetaDensidad");
    }

    /**
     * Reto Extra 6: Cuenta cuántos ficheros de la lista caen en la categoría pedida.
     */
    public static long contarPorCategoria(List<String> ficheros, String categoria) {
        // GUÍA: teoría 1.4 (reutiliza clasificarFichero del core como clasificador).
        // 1. Si ficheros o categoria son null -> 0.
        // 2. Cuenta los ficheros cuya clasificarFichero(...) sea igual a 'categoria'.
        // PISTA: ficheros.stream().filter(f -> clasificarFichero(f).equals(categoria)).count();
        // OJO: el test mete 1 manifest + 2 .java + 1 .gradle y pide 2 para "codigo", 0 para "recurso".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPorCategoria");
    }

    /**
     * Reto Extra 7: Convierte el nombre corto de un permiso en su nombre completo.
     */
    public static String permisoCompleto(String corto) {
        // GUÍA: teoría 1.6 (los permisos se declaran como "android.permission.NOMBRE" en el manifest).
        // 1. null/blank -> IllegalArgumentException.
        // 2. Si ya empieza por "android.permission." devuélvelo tal cual (idempotente).
        // 3. Si no, antepón el prefijo y pon el nombre en MAYÚSCULAS.
        // PISTA: corto.startsWith("android.permission.") ? corto : "android.permission." + corto.toUpperCase();
        // OJO: el test: "camera" -> "android.permission.CAMERA"; y un valor ya completo no se duplica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para permisoCompleto");
    }

    /**
     * Reto Extra 8: ¿Es un build type válido? (los que reconoce Gradle de serie).
     */
    public static boolean esBuildTypeValido(String tipo) {
        // GUÍA: teoría 1.7 (Android trae dos build types por defecto: debug y release).
        // 1. null -> false.
        // 2. true solo si (ignorando mayúsculas) es "debug" o "release".
        // PISTA: "debug".equalsIgnoreCase(tipo) || "release".equalsIgnoreCase(tipo).
        // OJO: el test: "release" -> true, "staging" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esBuildTypeValido");
    }

    /**
     * Reto Extra 9: ¿La carpeta de recursos es para una orientación apaisada ({@code -land})?
     */
    public static boolean esRecursoApaisado(String carpeta) {
        // GUÍA: teoría 1.4 (los "resource qualifiers" como -land/-port/-night adaptan recursos).
        // 1. null -> false.
        // 2. true si la carpeta CONTIENE el qualifier "-land" (p.ej. "layout-land").
        // PISTA: carpeta.contains("-land").
        // OJO: el test: "layout-land" -> true, "layout" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRecursoApaisado");
    }

    /**
     * Reto Extra 10: Construye el comando Gradle (wrapper) para ejecutar una tarea.
     */
    public static String comandoGradle(String tarea) {
        // GUÍA: teoría 1.8 (Gradle es a Android lo que Maven a este proyecto: el "motor de build").
        // 1. Si tarea es null/blank -> IllegalArgumentException.
        // 2. Formato: "./gradlew " + tarea (el wrapper ./gradlew fija la versión de Gradle del repo).
        // PISTA: return "./gradlew " + tarea.trim();
        // OJO: el test: "assembleDebug" -> "./gradlew assembleDebug".
        // CULTURA: ./gradlew assembleRelease es el "mvn package" de Android. Maven lo viste en b04;
        //   Gradle resuelve dependencias y empaqueta igual, pero genera un .apk/.aab en vez de un .jar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoGradle");
    }
}
