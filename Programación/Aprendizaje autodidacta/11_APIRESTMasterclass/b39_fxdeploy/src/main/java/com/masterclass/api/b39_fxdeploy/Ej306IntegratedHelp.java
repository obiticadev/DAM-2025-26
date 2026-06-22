package com.masterclass.api.b39_fxdeploy;

import java.util.List;

/**
 * Ejercicio 306 · Ayuda integrada en la app: "Acerca de", manual y enlaces a la documentación.
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 2).
 *
 * <p>Una app terminada lleva su propia AYUDA: un diálogo "Acerca de" (nombre, versión, autor,
 * copyright), un manual de usuario y enlaces ({@code Hyperlink}) que abren la documentación en el
 * navegador. Todo eso es <b>texto y enlaces</b>: perfectamente testeable sin abrir ventana. El nodo
 * {@code Hyperlink} y {@code HostServices.showDocument(url)} (cómo abrir el navegador desde JavaFX)
 * se enseñan en la teoría; aquí construimos y validamos las CADENAS que ese diálogo mostrará.
 */
public final class Ej306IntegratedHelp {

    /** Datos del diálogo "Acerca de". Inmutable: el patrón record que ves desde b01. */
    public record AcercaDe(String nombre, String version, int anio, String autor) {
    }

    private Ej306IntegratedHelp() {
    }

    /**
     * Texto del diálogo "Acerca de": nombre y versión en la primera línea, copyright en la segunda.
     *
     * @param info datos de la app (puede ser null)
     * @return {@code "<nombre> v<version>\n© <anio> <autor>"}; {@code ""} si info es null
     */
    public static String textoAcercaDe(AcercaDe info) {
        // TODO 1: si info es null, devuelve "".
        // TODO 2: primera línea = info.nombre() + " v" + info.version().
        // TODO 3: segunda línea = "© " + info.anio() + " " + info.autor().
        // TODO 4: une las dos líneas con "\n" y devuélvelo.
        //         (el test: AcercaDe("Mi App","1.0.0",2026,"Ana") -> "Mi App v1.0.0\n© 2026 Ana").
        return "";
    }

    /**
     * ¿Es una URL que el {@code Hyperlink} puede abrir? Solo aceptamos http/https.
     *
     * @param url la URL (puede ser null)
     * @return true si empieza por {@code http://} o {@code https://}
     */
    public static boolean esUrlValida(String url) {
        // TODO 5: si url es null o está en blanco (isBlank), devuelve false.
        // TODO 6: normaliza con trim().toLowerCase() para no fallar por mayúsculas/espacios.
        // TODO 7: devuelve true solo si empieza por "http://" o "https://".
        //         (el test: "https://docs.app" -> true; "ftp://x" -> false; null -> false).
        return false;
    }

    /**
     * Línea de copyright legal: el símbolo ©, el año y el autor.
     *
     * @param anio  año de publicación
     * @param autor titular del copyright (puede ser null o vacío)
     * @return {@code "© <anio> <autor>"}, o {@code "© <anio>"} si no hay autor
     */
    public static String lineaCopyright(int anio, String autor) {
        // TODO 8: empieza la cadena con "© " + anio.
        // TODO 9: si autor es null o está en blanco, devuelve solo "© " + anio (sin espacio sobrante).
        // TODO 10: si hay autor, devuelve "© " + anio + " " + autor.trim().
        //          (el test: (2026,"Ana") -> "© 2026 Ana"; (2026,"") -> "© 2026").
        return "";
    }

    public static void main(String[] args) {
        AcercaDe info = new AcercaDe("Masterclass App", "2.4.1", 2026, "DAM");
        System.out.println(textoAcercaDe(info));
        System.out.println("¿URL válida? " + esUrlValida("https://docs.app"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Título de la ventana de ayuda.
     * El texto de la barra de título: {@code "Acerca de — <nombre>"}.
     */
    public static String tituloVentana(AcercaDe info) {
        // GUÍA: teoría 2.1 (stage.setTitle del diálogo modal "Acerca de").
        // 1. Si info es null, devuelve "Acerca de".
        // 2. Devuelve "Acerca de — " + info.nombre() (la raya larga "—", no un guion).
        // OJO: el test: nombre "Mi App" -> "Acerca de — Mi App".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tituloVentana");
    }

    /**
     * Reto Extra 2: URL de la documentación de una versión.
     * Compone {@code <base>/<version>/index.html} (la doc suele estar versionada).
     */
    public static String urlDocsVersion(String base, String version) {
        // GUÍA: teoría 2.3 (el Hyperlink de "Manual online" apunta a la doc de TU versión).
        // 1. Si base es null o vacía, usa "https://docs.app".
        // 2. Quita una posible "/" final de base (endsWith) para no duplicarla.
        // 3. Devuelve base + "/" + version + "/index.html".
        // OJO: el test: ("https://docs.app","2.4.1") y ("https://docs.app/","2.4.1") dan lo MISMO:
        //   "https://docs.app/2.4.1/index.html" (la barra final no debe duplicar la "/").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para urlDocsVersion");
    }

    /**
     * Reto Extra 3: ¿Es un email válido (validación simple)?
     * Tiene una sola @ y al menos un punto DESPUÉS de la @.
     */
    public static boolean esEmailValido(String email) {
        // GUÍA: teoría 2.4 (el enlace "Contactar con soporte" usa mailto:).
        // 1. Si email es null o en blanco, false.
        // 2. Cuenta las '@': debe haber exactamente una (indexOf == lastIndexOf y != -1).
        // 3. Debe existir un '.' en la parte de después de la @ (substring tras la @ contiene ".").
        // PISTA: int at = email.indexOf('@'); luego email.substring(at).contains(".").
        // OJO: el test: "a@b.com" -> true; "a@@b.com" -> false; "ab.com" -> false (sin @).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEmailValido");
    }

    /**
     * Reto Extra 4: Secciones del manual.
     * La lista FIJA de apartados del manual de usuario, en orden.
     */
    public static List<String> seccionesManual() {
        // GUÍA: teoría 2.2 (el índice del manual que muestras en un ListView o TreeView).
        // 1. Devuelve List.of("Introducción", "Primeros pasos", "Funciones", "Atajos", "Soporte").
        // OJO: el test comprueba tamaño 5 y que el primero es "Introducción".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seccionesManual");
    }

    /**
     * Reto Extra 5: Buscar en el manual.
     * Devuelve las secciones cuyo título CONTIENE el término (sin distinguir mayúsculas).
     */
    public static List<String> buscarEnManual(List<String> secciones, String termino) {
        // GUÍA: teoría 2.2 (el cuadro de búsqueda de la ayuda filtra el índice).
        // 1. Si secciones es null o termino null/vacío, devuelve List.of().
        // 2. Filtra con stream(): la sección en minúsculas contains(termino en minúsculas).
        // PISTA: .filter(s -> s.toLowerCase().contains(termino.toLowerCase())).toList().
        // OJO: el test: buscar "paso" en el índice -> ["Primeros pasos"] (no distingue mayúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarEnManual");
    }

    /**
     * Reto Extra 6: Texto del atajo de teclado.
     * Formatea una tecla como pista visible: {@code "F1"} -> {@code "Pulsa [F1] para ayuda"}.
     */
    public static String pistaAtajo(String tecla) {
        // GUÍA: teoría 2.5 (los aceleradores de menú: F1 abre la ayuda, Ctrl+Q sale).
        // 1. Si tecla es null o vacía, devuelve "".
        // 2. Devuelve "Pulsa [" + tecla + "] para ayuda".
        // OJO: el test: "F1" -> "Pulsa [F1] para ayuda".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pistaAtajo");
    }

    /**
     * Reto Extra 7: Índice numerado.
     * Antepone "1. ", "2. ", ... a cada sección, para mostrar el manual como lista ordenada.
     */
    public static List<String> indiceNumerado(List<String> secciones) {
        // GUÍA: teoría 2.2 (presentar el índice numerado, como una tabla de contenidos).
        // 1. Si secciones es null o vacía, devuelve List.of().
        // 2. Recorre con índice i (0..n-1) y construye (i+1) + ". " + seccion.
        // PISTA: un bucle for con contador, o IntStream.range(0, secciones.size())...
        // OJO: el test: ["A","B"] -> ["1. A","2. B"] (la numeración empieza en 1, no en 0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceNumerado");
    }

    /**
     * Reto Extra 8: URL con ancla a una sección.
     * Convierte el título en un ancla: {@code base + "#" + slug}, con el slug en minúsculas y
     * los espacios cambiados por guiones.
     */
    public static String urlConAncla(String base, String seccion) {
        // GUÍA: teoría 2.3 (los enlaces a una sección concreta de la doc usan #ancla).
        // 1. Si base es null, usa "".
        // 2. Calcula el slug: seccion.trim().toLowerCase().replace(" ", "-").
        // 3. Devuelve base + "#" + slug.
        // PISTA: "Primeros pasos" -> slug "primeros-pasos".
        // OJO: el test: ("docs.html","Primeros pasos") -> "docs.html#primeros-pasos".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para urlConAncla");
    }

    /**
     * Reto Extra 9: ¿La app es "vieja" respecto a un año?
     * true si el año de copyright es ANTERIOR al año actual que se le pasa (recordatorio de actualizar).
     */
    public static boolean copyrightDesactualizado(AcercaDe info, int anioActual) {
        // GUÍA: teoría 2.1 (un © con un año viejo da mala imagen; conviene avisar al mantenedor).
        // 1. Si info es null, devuelve false.
        // 2. Devuelve info.anio() < anioActual.
        // OJO: el test: año 2024 con anioActual 2026 -> true; año 2026 con 2026 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para copyrightDesactualizado");
    }

    /**
     * Reto Extra 10: Pie del "Acerca de" con la versión legible.
     * Combina el texto de "Acerca de" con una última línea {@code "Versión v<version>"}.
     */
    public static String acercaDeCompleto(AcercaDe info) {
        // GUÍA: teoría 2.1 (componer el diálogo final reutilizando textoAcercaDe).
        // 1. Si info es null, devuelve "".
        // 2. Reutiliza textoAcercaDe(info) y añádele "\nVersión v" + info.version() al final.
        // PISTA: textoAcercaDe(info) + "\nVersión v" + info.version().
        // OJO: el test: AcercaDe("App","1.0.0",2026,"Ana") ->
        //   "App v1.0.0\n© 2026 Ana\nVersión v1.0.0" (tres líneas).
        // CULTURA: ese prefijo "v" delante de la versión es la convención que normalizas en Ej310 (formatoConV).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para acercaDeCompleto");
    }
}
