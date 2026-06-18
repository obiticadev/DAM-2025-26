package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 202 · Inyección de variables en HTML de factura.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md} (sección 25.2)
 *
 * <p>Uso de th:text y th:utext para interpolar datos simples, más los objetos
 * de utilidad #temporals (fechas) y #numbers (dinero).
 *
 * <p>Reutiliza el motor y el resolver montados en {@code Ej201ThymeleafStandalone}.
 */
public final class Ej202VariablesSimplesFactura {

    private Ej202VariablesSimplesFactura() {
    }

    public static void paso01DisenoDtoCabecera() {
        // GUÍA: teoría 25.2. Diseña el DTO de cabecera como un record (bloque 1.1):
        //   public record FacturaCabeceraDTO(String numero, java.time.LocalDate fecha,
        //                                     String cliente, double total) {}
        // PISTA: usa record, no una clase con getters: Thymeleaf accede a 'numero'
        // tanto por getNumero() como por el accesor numero() del record.
        // CULTURA: este DTO es justo lo que devolvería tu repositorio JPA (b12)
        // tras leer la factura de la BD.
    }

    public static void paso02CargarContexto() {
        // GUÍA: teoría 25.2. Mete el DTO en el contexto bajo un nombre:
        //   ctx.setVariable("factura", dto);
        // PISTA: la clave "factura" es EXACTAMENTE el prefijo que usarás en la
        // plantilla con ${factura.numero}. Si la llamas "f", la vista usa ${f...}.
        // OJO: reutiliza el Context del Ej201 (paso09); no crees un motor nuevo.
    }

    public static void paso03InyeccionTextoPlano() {
        // GUÍA: teoría 25.2. En la plantilla, escribe el número con th:text:
        //   <span th:text="${factura.numero}">000-XYZ</span>
        // PISTA: el "000-XYZ" es un placeholder de diseño: lo ves al abrir el HTML
        // crudo, pero Thymeleaf lo SUSTITUYE por el valor real al procesar.
        // OJO: th:text ESCAPA el HTML (seguro por defecto): es lo que quieres.
    }

    public static void paso04FormateoDeFechas() {
        // GUÍA: teoría 25.2, tabla de utilidades. Formatea el LocalDate con
        // #temporals:
        //   <p th:text="${#temporals.format(factura.fecha, 'dd/MM/yyyy')}">01/01/2026</p>
        // PISTA: #temporals trabaja con tipos java.time (b1.10). El patrón
        // 'dd/MM/yyyy' es el del DateTimeFormatter del JDK.
        // OJO: no formatees la fecha en el DTO con un String; deja el LocalDate y
        // formatea en la VISTA, que es donde importa la presentación.
    }

    public static void paso05InyeccionTextoHtml() {
        // GUÍA: teoría 25.2, "th:text vs th:utext". th:utext NO escapa el HTML:
        //   <strong th:utext="${factura.cliente}">Nombre</strong>
        // OJO/CUIDADO: con un cliente "Acme & Hijos", th:text escribe "Acme &amp;
        // Hijos" (correcto y seguro); th:utext lo mete crudo. Reserva utext SOLO
        // para HTML que generas TÚ (un <strong> propio), nunca para datos de un
        // cliente: si su nombre trae <script>, te lo ejecuta (XSS).
        // PISTA: en la duda, usa th:text. utext es la excepción, no la regla.
    }

    public static void paso06FormateoMonetario() {
        // GUÍA: teoría 25.2. Formatea el total con #numbers, formato español:
        //   <p th:text="${#numbers.formatDecimal(factura.total, 1, 'POINT', 2, 'COMMA')} + ' €'">1.234,50 €</p>
        // PISTA: formatDecimal(num, minEnteros, sepMiles, nDecimales, sepDecimal).
        // Para España: miles='POINT', decimales='COMMA' → 1.234,50.
        // OJO: el orden de los separadores es (miles, decimales); invertirlos da
        // "1,234.50" (formato inglés). El test/factura espera el español.
    }

    public static void paso07ProcesarFactura() {
        // GUÍA: teoría 25.1-25.2. Procesa la plantilla con el motor del Ej201:
        //   String html = engine.process("factura_test", ctx);
        // PISTA: necesita /templates/factura_test.html con los th:text de los
        // pasos 3-6. El nombre va SIN extensión.
        // OJO: reutiliza 'engine' y 'ctx'; este paso solo dispara el render.
    }

    public static void paso08VerificarInyeccion() {
        // GUÍA: teoría 25.2. Valida que el String HTML contiene los valores reales:
        //   assert html.contains(dto.numero());
        //   assert html.contains("1.234,50");
        // PISTA: usa String.contains sobre el HTML resultante; los placeholders
        // de diseño ("000-XYZ") ya NO deben aparecer si la inyección funcionó.
        // CULTURA: en b19 esto sería un assertThat(html).contains(...) con JUnit.
    }

    public static void paso09ManejoNulos() {
        // GUÍA: teoría 25.2, "Nulos en la vista" y error común nº 9. Si una
        // variable puede ser null, protege la vista con navegación segura + Elvis:
        //   <span th:text="${factura?.numero} ?: 'Sin número'">—</span>
        // PISTA: ${x?.campo} devuelve null en vez de explotar; ?: pone el default.
        // OJO: mejor aún, sanea en el Service y no metas nulos en el contexto: la
        // navegación segura es el cinturón, no la norma.
    }

    public static void paso10RetornoHtml() {
        // GUÍA: teoría 25.2. Devuelve el String HTML renderizado final; será la
        // entrada del Ej206 (HTML → PDF).
        // PISTA: este método debería 'return html;' (cambia la firma a String si
        // lo implementas de verdad). Reutiliza el resultado del paso 7.
        // CULTURA: este String es lo que en producción pasa de tu PlantillaService
        // al PdfGeneratorService — el eslabón entre 25.2 y 25.6.
    }

    public static boolean ejecutar() {
        // Llama a todos los pasos para comprobar la ejecución
        paso01DisenoDtoCabecera();
        paso02CargarContexto();
        paso03InyeccionTextoPlano();
        paso04FormateoDeFechas();
        paso05InyeccionTextoHtml();
        paso06FormateoMonetario();
        paso07ProcesarFactura();
        paso08VerificarInyeccion();
        paso09ManejoNulos();
        paso10RetornoHtml();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Validando los 10 pasos: " + ejecutar());
    }
}
