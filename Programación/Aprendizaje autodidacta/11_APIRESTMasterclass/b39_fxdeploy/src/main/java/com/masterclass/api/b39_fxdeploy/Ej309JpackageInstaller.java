package com.masterclass.api.b39_fxdeploy;

/**
 * Ejercicio 309 · Instaladores nativos con {@code jpackage} (msi / deb / dmg).
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 5).
 *
 * <p>{@code jlink} (Ej308) crea el runtime; {@code jpackage} da el último paso: lo empaqueta en un
 * INSTALADOR NATIVO del sistema operativo, el doble-clic que un usuario normal espera: {@code .msi}
 * en Windows, {@code .deb} en Linux Debian/Ubuntu, {@code .dmg} en macOS. Cada SO tiene su formato,
 * su icono y sus herramientas.
 *
 * <p>Como con {@code jlink}, {@code jpackage} es un COMANDO de terminal. El core testeable elige el
 * tipo de instalador por SO, calcula extensiones/iconos y CONSTRUYE el comando como cadena. Ejecutar
 * {@code jpackage} de verdad (requiere WiX en Windows, etc.) se documenta en la teoría y el README.
 */
public final class Ej309JpackageInstaller {

    private Ej309JpackageInstaller() {
    }

    /**
     * Tipo de instalador por defecto para un sistema operativo.
     *
     * @param so {@code "windows"}, {@code "linux"} o {@code "mac"} (sin distinguir mayúsculas)
     * @return {@code "msi"}, {@code "deb"}, {@code "dmg"}, o {@code "app-image"} si no se reconoce
     */
    public static String tipoInstaladorPara(String so) {
        // TODO 1: si so es null, devuelve "app-image" (un paquete genérico, sin instalador del SO).
        // TODO 2: normaliza con trim().toLowerCase().
        // TODO 3: "windows" -> "msi"; "linux" -> "deb"; "mac" o "macos" -> "dmg".
        // TODO 4: cualquier otro -> "app-image".
        //         (el test: "windows" -> "msi"; "linux" -> "deb"; "mac" -> "dmg"; "haiku" -> "app-image").
        return "";
    }

    /**
     * Extensión de fichero de un tipo de instalador.
     *
     * @param tipo {@code "msi"}, {@code "deb"}, {@code "dmg"}...
     * @return la extensión con punto ({@code ".msi"}), o {@code ""} si tipo es null/vacío
     */
    public static String extensionInstalador(String tipo) {
        // TODO 5: si tipo es null o en blanco, devuelve "".
        // TODO 6: si el tipo es "app-image", devuelve "" (es una carpeta, no un fichero con extensión).
        // TODO 7: en otro caso, devuelve "." + tipo.toLowerCase().
        //         (el test: "msi" -> ".msi"; "DEB" -> ".deb"; "app-image" -> "").
        return "";
    }

    /**
     * Comando {@code jpackage} básico: nombre, versión y tipo.
     *
     * @param app     nombre de la aplicación
     * @param version versión (formato SemVer)
     * @param tipo    tipo de instalador (msi/deb/dmg/app-image)
     * @return {@code "jpackage --name <app> --app-version <version> --type <tipo>"}
     */
    public static String construirComandoJpackage(String app, String version, String tipo) {
        // TODO 8: si app es null o vacía, usa "App"; si version es null o vacía, usa "1.0.0".
        // TODO 9: si tipo es null o vacío, usa "app-image".
        // TODO 10: devuelve "jpackage --name " + app + " --app-version " + version + " --type " + tipo.
        //          (el test: ("MiApp","2.0.0","msi") ->
        //           "jpackage --name MiApp --app-version 2.0.0 --type msi").
        return "";
    }

    public static void main(String[] args) {
        String tipo = tipoInstaladorPara("windows");
        System.out.println("Tipo en Windows: " + tipo + extensionInstalador(tipo));
        System.out.println(construirComandoJpackage("MiApp", "2.0.0", tipo));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Extensión del icono por SO.
     * Cada SO usa un formato de icono distinto: Windows {@code .ico}, Linux {@code .png}, mac {@code .icns}.
     */
    public static String extensionIcono(String so) {
        // GUÍA: teoría 5.3 (jpackage --icon necesita el formato nativo de cada plataforma).
        // 1. Normaliza so a minúsculas (null -> "").
        // 2. "windows" -> ".ico"; "mac"/"macos" -> ".icns"; cualquier otro (incl. linux) -> ".png".
        // OJO: el test: "windows" -> ".ico"; "mac" -> ".icns"; "linux" -> ".png".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionIcono");
    }

    /**
     * Reto Extra 2: ¿Es un nombre de app válido?
     * No vacío y sin caracteres problemáticos para una ruta ({@code / \\ : * ? " < > |}).
     */
    public static boolean nombreAppValido(String app) {
        // GUÍA: teoría 5.2 (el --name acaba siendo un nombre de fichero/carpeta del SO).
        // 1. Si app es null o en blanco, devuelve false.
        // 2. Devuelve false si contiene alguno de: / \ : * ? " < > |  (recórrelos y comprueba).
        // PISTA: for (char c : "/\\:*?\"<>|".toCharArray()) if (app.indexOf(c) >= 0) return false;
        // OJO: el test: "Mi App" -> true; "a/b" -> false; "" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreAppValido");
    }

    /**
     * Reto Extra 3: Argumento {@code --app-version}.
     * El flag de versión, validando que la versión sea SemVer (si no, usa 1.0.0).
     */
    public static String argAppVersion(String version) {
        // GUÍA: teoría 5.2 (jpackage exige una versión válida; reutiliza la validación de Ej305).
        // 1. Si version no pasa Ej305JavadocAndManifest.esVersionValida(version), usa "1.0.0".
        // 2. Devuelve "--app-version " + version.
        // PISTA: reutiliza el reto 10 de Ej305 (esVersionValida).
        // OJO: el test: "2.0.0" -> "--app-version 2.0.0"; "2.x" -> "--app-version 1.0.0".
        // CULTURA: reutilizar el validador de versión de Ej305 evita duplicar reglas -> DRY.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para argAppVersion");
    }

    /**
     * Reto Extra 4: Argumento {@code --vendor}.
     * El fabricante que aparece en el instalador. Si va con espacios, va entre comillas.
     */
    public static String argVendor(String vendor) {
        // GUÍA: teoría 5.2 (en un comando de shell, un valor con espacios DEBE ir entrecomillado).
        // 1. Si vendor es null o vacío, devuelve "".
        // 2. Si vendor contiene un espacio, devuelve --vendor "vendor" (entre comillas dobles).
        // 3. Si no, devuelve --vendor vendor (sin comillas).
        // PISTA: vendor.contains(" ") ? "--vendor \"" + vendor + "\"" : "--vendor " + vendor;
        // OJO: el test: "ACME" -> "--vendor ACME"; "ACME Corp" -> "--vendor \"ACME Corp\"".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para argVendor");
    }

    /**
     * Reto Extra 5: Nombre del fichero de instalador.
     * Combina nombre, versión y la extensión del tipo: {@code "MiApp-2.0.0.msi"}.
     */
    public static String nombrePaquete(String app, String version, String tipo) {
        // GUÍA: teoría 5.4 (el fichero que jpackage deja en la carpeta de salida).
        // 1. Si app es null o vacío, usa "App"; si version es null o vacía, usa "1.0.0".
        // 2. Devuelve app + "-" + version + extensionInstalador(tipo).
        // PISTA: reutiliza extensionInstalador(tipo).
        // OJO: el test: ("MiApp","2.0.0","msi") -> "MiApp-2.0.0.msi";
        //   con tipo "app-image" la extensión es "" -> "MiApp-2.0.0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombrePaquete");
    }

    /**
     * Reto Extra 6: ¿Necesita herramienta externa?
     * Crear un {@code .msi}/{@code .exe} en Windows requiere WiX Toolset instalado aparte.
     */
    public static boolean requiereHerramientaExterna(String tipo) {
        // GUÍA: teoría 5.5 (jpackage delega: msi/exe -> WiX; deb/rpm -> dpkg/rpmbuild).
        // 1. Si tipo es null, devuelve false.
        // 2. Devuelve true si tipo (minúsculas) es "msi" o "exe"; false en otro caso.
        // OJO: el test: "msi" -> true; "exe" -> true; "app-image" -> false; "dmg" -> false.
        // CULTURA: esta dependencia de un toolchain del SO es la razón de que 308/309 sean "guion":
        //   no se pueden ejecutar dentro de Maven/JUnit, igual que pasaba con Docker en b22.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereHerramientaExterna");
    }

    /**
     * Reto Extra 7: Argumento {@code --input}.
     * La carpeta donde están los jars de la app que jpackage empaquetará.
     */
    public static String argInput(String dir) {
        // GUÍA: teoría 5.2 (--input apunta a la carpeta con tu jar y sus dependencias).
        // 1. Si dir es null o vacío, usa "target".
        // 2. Devuelve "--input " + dir.
        // OJO: el test: "target/dist" -> "--input target/dist"; null -> "--input target".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para argInput");
    }

    /**
     * Reto Extra 8: Tipos disponibles por SO.
     * El número de formatos de instalador que jpackage ofrece en ese SO.
     */
    public static int numeroDeTiposPara(String so) {
        // GUÍA: teoría 5.1 (Windows: msi y exe; Linux: deb y rpm; mac: dmg y pkg; otro: solo app-image).
        // 1. Normaliza so a minúsculas (null -> "").
        // 2. "windows"/"linux"/"mac"/"macos" -> 2; cualquier otro -> 1 (solo app-image).
        // OJO: el test: "windows" -> 2; "linux" -> 2; "haiku" -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeTiposPara");
    }

    /**
     * Reto Extra 9: ¿El tipo es válido para ese SO?
     * Un {@code .msi} solo se puede generar en Windows, un {@code .deb} en Linux, etc.
     */
    public static boolean tipoValidoEn(String tipo, String so) {
        // GUÍA: teoría 5.1 (jpackage solo genera el instalador NATIVO del SO en el que corre).
        // 1. Si tipo o so son null, devuelve false; normaliza ambos a minúsculas.
        // 2. "app-image" vale en cualquier SO -> true.
        // 3. windows acepta msi/exe; linux acepta deb/rpm; mac/macos acepta dmg/pkg.
        // PISTA: un switch sobre el SO que comprueba si el tipo está en su conjunto permitido.
        // OJO: el test: ("msi","windows") -> true; ("deb","windows") -> false;
        //   ("app-image","mac") -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoValidoEn");
    }

    /**
     * Reto Extra 10: Comando jpackage completo y listo para pegar.
     * Encadena nombre, versión, tipo, input y main-jar en un solo comando multi-flag.
     */
    public static String comandoCompleto(String app, String version, String so, String mainJar) {
        // GUÍA: teoría 5.4 (el comando real que generarías en tu README de release).
        // 1. Calcula el tipo con tipoInstaladorPara(so).
        // 2. Parte de construirComandoJpackage(app, version, tipo).
        // 3. Añádele " " + argInput("target") + " --main-jar " + mainJar.
        // PISTA: reutiliza construirComandoJpackage, tipoInstaladorPara y argInput.
        // OJO: el test: ("MiApp","2.0.0","linux","app.jar") ->
        //   "jpackage --name MiApp --app-version 2.0.0 --type deb --input target --main-jar app.jar".
        // CULTURA: este comando es el equivalente cliente del `docker build`/`mvn deploy` del backend (b22/b23):
        //   el artefacto final que entregas al usuario.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoCompleto");
    }
}
