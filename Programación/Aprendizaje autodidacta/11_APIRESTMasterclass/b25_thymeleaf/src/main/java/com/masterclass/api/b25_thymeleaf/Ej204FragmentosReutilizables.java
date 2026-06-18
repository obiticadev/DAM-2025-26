package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 204 · Composición de plantillas: Cabeceras y Pies de página.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md} (sección 25.4)
 *
 * <p>Evitar copiar y pegar el mismo logo e info fiscal en cada PDF usando
 * th:fragment + th:replace/th:insert. Principio DRY aplicado a plantillas.
 */
public final class Ej204FragmentosReutilizables {

    private Ej204FragmentosReutilizables() {
    }

    public static void paso01CrearArchivoHeader() {
        // GUÍA: teoría 25.4. Crea /templates/fragments/header.html con el logo y
        // un hueco para el título del documento.
        // PISTA: un fragmento es un .html normal; lo especial es el atributo
        // th:fragment del paso 2. Puede contener <img>, <h1>, etc.
        // OJO: ponlo bajo templates/fragments/ (paso 7); el resolver del Ej201 lo
        // localiza por classpath igual que cualquier plantilla.
    }

    public static void paso02DefinirThFragment() {
        // GUÍA: teoría 25.4. Marca el bloque reutilizable con th:fragment y un
        // parámetro:
        //   <header th:fragment="cabecera(titulo)">
        //     <img src="logo.png"/>
        //     <h1 th:text="${titulo}">Documento</h1>
        //   </header>
        // PISTA: cabecera(titulo) declara un parámetro 'titulo' que recibes al
        // invocar (paso 3). Dentro del fragmento lo usas como ${titulo}.
    }

    public static void paso03InvocarThReplace() {
        // GUÍA: teoría 25.4, sintaxis ~{plantilla :: fragmento}. En factura.html:
        //   <div th:replace="~{fragments/header :: cabecera('Factura')}"></div>
        // PISTA: ~{ruta :: nombreFragmento}. La ruta es relativa a /templates/ sin
        // .html; el nombre es el del th:fragment.
        // OJO: 'Factura' (con comillas simples) es el literal que recibe 'titulo'.
    }

    public static void paso04PasoDeParametros() {
        // GUÍA: teoría 25.4. Comprueba que 'Factura' sustituye a 'titulo' dentro
        // del fragmento: el <h1 th:text="${titulo}"> renderiza "Factura".
        // PISTA: el MISMO header sirve para cabecera('Factura') y
        // cabecera('Albarán') — un fragmento, muchos documentos.
        // CULTURA: esto es exactamente lo que hace un componente parametrizado en
        // React/Vue; Thymeleaf lo trae para HTML de servidor.
    }

    public static void paso05CrearArchivoFooter() {
        // GUÍA: teoría 25.4. Crea /templates/fragments/footer.html con el pie legal:
        //   <footer th:fragment="pieLegal">
        //     <hr/><p>NIF: B-12345678 · IBAN: ES00 1234 5678 9012</p>
        //   </footer>
        // PISTA: este fragmento NO necesita parámetros: el NIF y el IBAN son fijos
        // de la empresa. th:fragment="pieLegal" a secas.
    }

    public static void paso06InvocarThInsert() {
        // GUÍA: teoría 25.4, tabla "th:replace vs th:insert". Inserta el footer:
        //   <div th:insert="~{fragments/footer :: pieLegal}"></div>
        // OJO/CUIDADO: th:insert CONSERVA el <div> host y mete el <footer> DENTRO;
        // th:replace habría SUSTITUIDO el <div> por el <footer>. Si cuentas <div>
        // en el resultado, insert deja uno de más respecto a replace.
        // PISTA: replace cuando el fragmento ya es semántico; insert cuando
        // necesitas el contenedor host por su clase/estructura.
    }

    public static void paso07EstructuraDirectorios() {
        // GUÍA: teoría 25.4. Documenta la estructura ideal de carpetas:
        //   src/main/resources/templates/
        //     ├─ fragments/   (header.html, footer.html)  ← reutilizables
        //     └─ documentos/  (factura.html, albaran.html) ← plantillas finales
        // PISTA: separar fragments/ de documentos/ deja claro qué es pieza
        // compartida y qué es documento completo. No hay código aquí: es diseño.
    }

    public static void paso08AislamientoDeCambios() {
        // GUÍA: teoría 25.4, "Aislamiento de cambios" (DRY). Demuestra que cambiar
        // el NIF en footer.html actualiza Facturas Y Albaranes a la vez.
        // PISTA: como ambos documentos hacen th:replace/insert del MISMO footer,
        // editas un fichero y se propaga a todos. Sin fragmentos, tendrías que
        // tocar N plantillas y se te olvidaría una (el bug que esto evita).
    }

    public static void paso09ProcesarPlantillaCompuesta() {
        // GUÍA: teoría 25.4. Procesa la plantilla base para que el motor resuelva
        // los fragmentos anidados:
        //   String html = engine.process("documentos/factura", ctx);
        // PISTA: Thymeleaf resuelve los ~{...} de forma recursiva; un fragmento
        // puede a su vez incluir otros. Un solo process() ensambla todo.
        // OJO: si la ruta del ~{...} está mal, salta TemplateInputException
        // apuntando al fragmento que no encuentra.
    }

    public static void paso10ValidarEnsamblaje() {
        // GUÍA: teoría 25.4. Devuelve el HTML completo y verifica que contiene
        // tanto la cabecera como el pie:
        //   return html.contains("Factura") && html.contains("NIF: B-12345678");
        // PISTA: si ambos textos aparecen, el motor resolvió header Y footer.
        // CULTURA: esto certifica el DRY: un único render trae piezas de tres
        // ficheros distintos ensambladas sin copy-paste.
    }

    public static boolean ejecutar() {
        // Llama a todos los pasos para comprobar la ejecución
        paso01CrearArchivoHeader();
        paso02DefinirThFragment();
        paso03InvocarThReplace();
        paso04PasoDeParametros();
        paso05CrearArchivoFooter();
        paso06InvocarThInsert();
        paso07EstructuraDirectorios();
        paso08AislamientoDeCambios();
        paso09ProcesarPlantillaCompuesta();
        paso10ValidarEnsamblaje();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Validando los 10 pasos: " + ejecutar());
    }
}
