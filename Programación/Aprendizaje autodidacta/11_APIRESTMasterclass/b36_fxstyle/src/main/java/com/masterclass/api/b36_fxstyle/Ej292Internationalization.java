package com.masterclass.api.b36_fxstyle;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Ejercicio 292 · Internacionalización (i18n): {@code ResourceBundle}, {@code Locale} y textos por idioma.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 6).
 *
 * <p><b>i18n</b> ("internationalization": i + 18 letras + n) es separar los TEXTOS del código para
 * poder traducir la app sin recompilar. Las cadenas viven en archivos {@code .properties} (uno por
 * idioma: {@code mensajes_es.properties}, {@code mensajes_en.properties}) y un {@code ResourceBundle}
 * carga el del {@code Locale} adecuado. Además, números, monedas y fechas se formatean según la
 * región. Aquí todo es lógica pura: cargar textos del bundle y formatear según el {@code Locale}.
 *
 * <p>Recursos: {@code src/main/resources/i18n/mensajes*.properties} (base "i18n.mensajes").
 */
public final class Ej292Internationalization {

    private Ej292Internationalization() {
    }

    /** Nombre base del bundle (la carpeta i18n del classpath + el prefijo "mensajes"). */
    private static final String BUNDLE = "i18n.mensajes";

    /**
     * Traduce una clave al idioma del {@code Locale} dado, leyendo del {@code ResourceBundle}.
     *
     * @param clave  clave del texto, p.ej. "boton.guardar"
     * @param locale idioma destino
     * @return el texto traducido; {@code ""} si la clave no existe o sin implementar
     */
    public static String traducir(String clave, Locale locale) {
        // TODO 1: obtén el bundle: ResourceBundle.getBundle(BUNDLE, locale).
        // TODO 2: envuelve la lectura en try/catch para el caso de clave inexistente.
        // TODO 3: dentro del try, devuelve bundle.getString(clave).
        // TODO 4: captura MissingResourceException (clave que no está en el .properties).
        // TODO 5: en ese caso devuelve "" (el caso límite del test: una clave que no existe).
        return "";
    }

    /**
     * Traduce una clave cuyo texto lleva huecos ({@code {0}}, {@code {1}}…) y los rellena con los
     * argumentos, usando {@link MessageFormat} y el {@code Locale} (para que números/fechas salgan
     * en el formato del idioma).
     *
     * @param clave  clave del texto con marcadores, p.ej. "saludo" = "Hola, {0}"
     * @param locale idioma destino
     * @param args   valores para los marcadores
     * @return el texto con los huecos rellenos; {@code ""} sin implementar
     */
    public static String traducirConParametros(String clave, Locale locale, Object... args) {
        // TODO 6: obtén el bundle igual que en traducir (ResourceBundle.getBundle(BUNDLE, locale)).
        // TODO 7: lee el patrón: String patron = bundle.getString(clave) (p.ej. "Hola, {0}").
        // TODO 8: crea un MessageFormat con el patrón Y el locale: new MessageFormat(patron, locale).
        // TODO 9: aplica los argumentos: mf.format(args) sustituye {0} por args[0], etc.
        // TODO 10: devuelve el resultado ("saludo" + ["Ana"] en es -> "Hola, Ana").
        return "";
    }

    public static void main(String[] args) {
        Locale es = Locale.forLanguageTag("es");
        Locale en = Locale.forLanguageTag("en");
        System.out.println("ES boton.guardar -> " + traducir("boton.guardar", es));
        System.out.println("EN boton.guardar -> " + traducir("boton.guardar", en));
        System.out.println("ES saludo(Ana)  -> " + traducirConParametros("saludo", es, "Ana"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Construir un Locale.
     * Devuelve el {@link Locale} correspondiente a una etiqueta de idioma ("es", "en", "en-US"…).
     */
    public static Locale localeDe(String etiqueta) {
        // GUÍA: teoría 6.1 (Locale.forLanguageTag interpreta etiquetas BCP-47: "es", "en-US"...).
        // 1. return Locale.forLanguageTag(etiqueta);
        // OJO: el test comprueba localeDe("en").getLanguage() == "en". Evita el constructor
        //   new Locale("es") (obsoleto desde Java 19): usa forLanguageTag.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para localeDe");
    }

    /**
     * Reto Extra 2: ¿Existe la clave?
     * Indica si el bundle del idioma contiene la clave dada.
     */
    public static boolean claveExiste(String clave, Locale locale) {
        // GUÍA: teoría 6.2 (ResourceBundle.containsKey comprueba sin lanzar excepción).
        // 1. return ResourceBundle.getBundle(BUNDLE, locale).containsKey(clave);
        // OJO: el test prueba "boton.guardar"->true y "no.existe"->false. Útil para decidir un
        //   fallback ANTES de pedir el texto (así no capturas excepciones para controlar el flujo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claveExiste");
    }

    /**
     * Reto Extra 3: Traducir con valor por defecto.
     * Devuelve la traducción si la clave existe; si no, el texto por defecto dado.
     */
    public static String traducirConFallback(String clave, Locale locale, String porDefecto) {
        // GUÍA: teoría 6.2 (en producción una clave faltante no debe romper la UI: usas un fallback).
        // 1. Obtén el bundle. 2. Si containsKey(clave), devuelve getString(clave); si no, 'porDefecto'.
        // PISTA: puedes apoyarte en claveExiste (reto 2).
        // OJO: el test prueba ("no.existe", es, "—") -> "—" y ("boton.guardar", es, "—") -> "Guardar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traducirConFallback");
    }

    /**
     * Reto Extra 4: Formatear un número según la región.
     * Devuelve el número formateado con los separadores del {@code Locale}.
     */
    public static String formatearNumero(double numero, Locale locale) {
        // GUÍA: teoría 6.3 (los separadores de miles/decimales cambian por región: es "1.234,5" vs en "1,234.5").
        // 1. return NumberFormat.getNumberInstance(locale).format(numero);
        // OJO: el test prueba 1234.5 en es -> "1.234,5" y en en -> "1,234.5". NUNCA construyas el
        //   número a mano con String: deja que NumberFormat aplique las reglas del idioma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearNumero");
    }

    /**
     * Reto Extra 5: Moneda de una región.
     * Devuelve el código ISO de moneda (EUR, USD…) de una etiqueta de país.
     */
    public static String codigoMoneda(String etiquetaPais) {
        // GUÍA: teoría 6.3 (la moneda depende del PAÍS, no solo del idioma: "es-ES"=EUR, "en-US"=USD).
        // 1. Locale locale = Locale.forLanguageTag(etiquetaPais).
        // 2. return Currency.getInstance(locale).getCurrencyCode();
        // OJO: el test prueba "es-ES"->"EUR" y "en-US"->"USD". Currency necesita PAÍS; "es" a secas
        //   (sin región) no tiene moneda y lanzaría excepción: por eso pasamos "es-ES".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para codigoMoneda");
    }

    /**
     * Reto Extra 6: Nombre del mes en un idioma.
     * Devuelve el nombre completo del mes (1–12) en el idioma del {@code Locale}.
     */
    public static String nombreMes(int mes, Locale locale) {
        // GUÍA: teoría 6.4 (las fechas también se localizan: enero/January/janvier...).
        // 1. return Month.of(mes).getDisplayName(TextStyle.FULL, locale);
        // OJO: el test prueba (1, es) -> "enero" (en español va en MINÚSCULA) y (1, en) -> "January".
        //   Month.of(0) o 13 lanzaría DateTimeException: el rango válido es 1..12.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreMes");
    }

    /**
     * Reto Extra 7: Idiomas disponibles.
     * Devuelve la lista de idiomas que la app soporta (los .properties que existen).
     */
    public static List<String> idiomasDisponibles() {
        // GUÍA: teoría 6.5 (la app declara qué idiomas ofrece; el selector de idioma se llena con esto).
        // 1. return List.of("es", "en");
        // OJO: el test compara la lista exacta. En un proyecto real esto se generaría escaneando los
        //   archivos mensajes_XX.properties, pero aquí basta declararlos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idiomasDisponibles");
    }

    /**
     * Reto Extra 8: Texto traducido con cantidad.
     * Devuelve "N clientes"/"N customers" combinando traducción y parámetro.
     */
    public static String traducirCantidad(int n, Locale locale) {
        // GUÍA: teoría 6.6 (combinas ResourceBundle + MessageFormat: el patrón "{0} clientes" se traduce).
        // 1. return traducirConParametros("clientes.cuenta", locale, n);
        // PISTA: reutiliza tu propio core traducirConParametros.
        // OJO: el test prueba (3, es) -> "3 clientes" y (3, en) -> "3 customers". El TEXTO cambia de
        //   idioma, no solo el número: por eso el patrón vive en el .properties, no en el código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traducirCantidad");
    }

    /**
     * Reto Extra 9: Cambiar de idioma (toggle).
     * Alterna el {@code Locale} entre español e inglés.
     */
    public static Locale cambiarIdioma(Locale actual) {
        // GUÍA: teoría 6.5 (un botón "ES/EN" alterna el idioma activo y se recarga la pantalla).
        // 1. Si actual.getLanguage() es "es", devuelve Locale.forLanguageTag("en"); si no, "es".
        // OJO: el test comprueba cambiarIdioma(es).getLanguage() == "en". Es el mismo patrón "toggle"
        //   que alternarTema (Ej289), pero con idiomas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cambiarIdioma");
    }

    /**
     * Reto Extra 10: Traducir una pantalla entera.
     * Devuelve un mapa clave→texto con todas las claves traducidas al idioma dado.
     */
    public static Map<String, String> traducirPantalla(List<String> claves, Locale locale) {
        // GUÍA: teoría 6.6 (al cambiar de idioma se re-traduce TODA la pantalla de una pasada).
        // 1. Crea un LinkedHashMap (mantiene el orden de las claves).
        // 2. Por cada clave, pon mapa.put(clave, traducir(clave, locale)).
        // 3. Devuelve el mapa.
        // PISTA: reutiliza tu core traducir(clave, locale).
        // OJO: el test pasa ["boton.guardar","boton.cancelar"] en es -> {"boton.guardar":"Guardar",
        //   "boton.cancelar":"Cancelar"}. Un Map.equals compara por contenido (el orden no afecta a equals).
        // CULTURA: esto es lo mismo que hiciste en b25 con Thymeleaf (#{clave} en plantillas): el
        //   concepto de Locale + bundle de mensajes se reutiliza entre el backend web y el cliente JavaFX.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traducirPantalla");
    }

    /** Helper de demostración: mapa mutable que conserva el orden de inserción. */
    static Map<String, String> mapa() {
        return new LinkedHashMap<>();
    }
}
