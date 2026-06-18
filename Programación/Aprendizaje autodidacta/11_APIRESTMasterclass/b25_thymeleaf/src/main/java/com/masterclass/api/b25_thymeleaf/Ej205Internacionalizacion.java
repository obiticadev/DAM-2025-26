package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 205 · Generación de albaranes multi-idioma.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md} (sección 25.5)
 *
 * <p>Externalizar los textos a messages_&lt;locale&gt;.properties y referenciarlos
 * con #{...} para emitir la misma factura en español, inglés, etc.
 */
public final class Ej205Internacionalizacion {

    private Ej205Internacionalizacion() {
    }

    public static void paso01ConfigurarMessageResolver() {
        // GUÍA: teoría 25.5. El motor necesita un resolver de mensajes para
        // entender #{...}. En standalone:
        //   engine.addMessageResolver(new org.thymeleaf.messageresolver.StandardMessageResolver());
        // PISTA: con Spring, el equivalente es un ResourceBundleMessageSource
        // (bean MessageSource). Aquí lo enchufamos a mano al engine del Ej201.
        // CULTURA: el StandardMessageResolver busca .properties junto a la
        // plantilla; el ResourceBundle de Spring centraliza todo en messages.*.
    }

    public static void paso02CrearPropertiesEs() {
        // GUÍA: teoría 25.5. Crea src/main/resources/messages_es.properties:
        //   invoice.title=Factura
        //   invoice.greeting=Estimado cliente {0}
        // PISTA: clave=valor, una por línea. La clave (invoice.title) es la que
        // referenciarás con #{invoice.title} en el paso 5.
        // OJO: el sufijo _es es lo que casa con Locale "es" en el paso 8.
    }

    public static void paso03CrearPropertiesEn() {
        // GUÍA: teoría 25.5. Crea messages_en.properties con las MISMAS claves y
        // los valores traducidos:
        //   invoice.title=Invoice
        //   invoice.greeting=Dear customer {0}
        // OJO/CUIDADO: las claves deben ser IDÉNTICAS a las del _es; si en inglés
        // falta una clave, Thymeleaf muestra ??invoice.title?? (mensaje no
        // resuelto). El conjunto de claves es el contrato.
    }

    public static void paso04FijarLocaleContexto() {
        // GUÍA: teoría 25.5. El idioma lo decide el Locale del Context:
        //   var ctxEs = new org.thymeleaf.context.Context(java.util.Locale.of("es", "ES"));
        //   var ctxEn = new org.thymeleaf.context.Context(java.util.Locale.ENGLISH);
        // PISTA: el Locale va en el CONSTRUCTOR del Context, no en setVariable.
        // OJO: Locale.of("es","ES") es la forma moderna (Java 19+); el viejo
        // new Locale("es","ES") está deprecado.
    }

    public static void paso05UsoSintaxisMensajes() {
        // GUÍA: teoría 25.5, tabla de las tres sintaxis. En la plantilla, el texto
        // estático se vuelve #{clave}:
        //   <h1 th:text="#{invoice.title}">Factura</h1>
        // OJO/CUIDADO: es ALMOHADILLA #{...}, no dólar ${...} (error común nº 8).
        // ${...} lee variables del contexto; #{...} lee del .properties; @{...} son
        // URLs. Confundir $ con # es el fallo nº 1 de este ejercicio.
    }

    public static void paso06ParametrosEnMensajes() {
        // GUÍA: teoría 25.5, "Parámetros {0}". Pasa una variable al mensaje:
        //   <p th:text="#{invoice.greeting(${cliente.nombre})}">Estimado cliente …</p>
        // PISTA: el {0} de la property se sustituye por el primer argumento; {1}
        // por el segundo, etc. Es el MessageFormat del JDK.
        // OJO: el argumento es una expresión ${...} DENTRO de los paréntesis del
        // #{...}: mezclas las dos sintaxis a propósito aquí.
    }

    public static void paso07CodificacionProperties() {
        // GUÍA: teoría 25.5, aviso de codificación. Asegúrate de que los
        // .properties se leen en UTF-8, no en ISO-8859-1.
        // OJO/CUIDADO: si "Compañía" o "Facturé" salen como "CompaÃ±Ã­a", la
        // codificación está mal. En Spring Boot moderno el default ya es UTF-8;
        // si configuras un ResourceBundleMessageSource a mano, llama a
        // setDefaultEncoding("UTF-8").
    }

    public static void paso08ProcesarVersionEspanol() {
        // GUÍA: teoría 25.5. Renderiza con el contexto español del paso 4:
        //   String htmlEs = engine.process("factura_i18n", ctxEs);
        // PISTA: Thymeleaf elige messages_es.properties por el Locale "es" del
        // ctxEs. El <h1> renderiza "Factura".
    }

    public static void paso09ProcesarVersionIngles() {
        // GUÍA: teoría 25.5. Renderiza la MISMA plantilla con el contexto inglés:
        //   String htmlEn = engine.process("factura_i18n", ctxEn);
        // PISTA: misma plantilla, distinto Locale → distinto idioma. El <h1>
        // ahora dice "Invoice". Ni una línea de HTML cambia: solo el Locale.
        // OJO: comprueba la diferencia con htmlEs.contains("Factura") vs
        // htmlEn.contains("Invoice").
    }

    public static void paso10RetornarComparativa() {
        // GUÍA: teoría 25.5. Devuelve ambos HTMLs para certificar la traducción:
        //   return java.util.Map.of("es", htmlEs, "en", htmlEn);
        // PISTA: un Map<String,String> idioma→html, o una List con los dos.
        // CULTURA: en producción NO renderizas los dos a la vez: eliges el Locale
        // según la cabecera Accept-Language o el país del cliente. Aquí los juntas
        // solo para comparar y demostrar que la i18n funciona.
    }

    public static boolean ejecutar() {
        // Llama a todos los pasos para comprobar la ejecución
        paso01ConfigurarMessageResolver();
        paso02CrearPropertiesEs();
        paso03CrearPropertiesEn();
        paso04FijarLocaleContexto();
        paso05UsoSintaxisMensajes();
        paso06ParametrosEnMensajes();
        paso07CodificacionProperties();
        paso08ProcesarVersionEspanol();
        paso09ProcesarVersionIngles();
        paso10RetornarComparativa();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Validando los 10 pasos: " + ejecutar());
    }
}
