package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 201 · Configuración del motor Thymeleaf Standalone.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md} (sección 25.1)
 *
 * <p>Aislar el motor de plantillas de la capa web MVC: en una API REST no hay
 * vistas; usamos Thymeleaf como una "impresora en memoria" que devuelve String.
 *
 * <p>NOTA: este bloque es conceptual. Los pasos son guías en comentarios; el
 * test solo verifica que {@link #ejecutar()} devuelve {@code true}. Para
 * ejecutarlo de verdad tendrías que añadir {@code spring-boot-starter-thymeleaf}
 * al pom.xml y descomentar el código de cada paso.
 */
public final class Ej201ThymeleafStandalone {

    private Ej201ThymeleafStandalone() {
    }

    public static void paso01AnadirDependencia() {
        // GUÍA: teoría 25.1. En el pom.xml de este módulo añade, dentro de
        // <dependencies>, el starter de plantillas:
        //   <dependency>
        //     <groupId>org.springframework.boot</groupId>
        //     <artifactId>spring-boot-starter-thymeleaf</artifactId>
        //   </dependency>
        // PISTA: sin version (la hereda del parent api-rest-masterclass, que fija
        // las versiones vía spring-boot-dependencies, igual que en b04+).
        // CULTURA: el starter arrastra thymeleaf, thymeleaf-spring6 y la
        // autoconfiguración; nosotros la ignoramos y montamos el motor a mano.
    }

    public static void paso02CrearMotorTemplateEngine() {
        // GUÍA: teoría 25.1, pieza "Motor". Instancia el motor SIN @Autowired:
        //   var engine = new org.thymeleaf.spring6.SpringTemplateEngine();
        // PISTA: SpringTemplateEngine (paquete spring6), no el TemplateEngine
        // base: el de spring6 entiende SpEL y los beans de Spring.
        // OJO: aquí NO usamos inyección de dependencias; lo creamos con new para
        // ver las piezas que la autoconfiguración normalmente te oculta.
    }

    public static void paso03ConfigurarResolver() {
        // GUÍA: teoría 25.1, pieza "Resolver". El resolver dice DÓNDE están las
        // plantillas:
        //   var resolver = new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
        //   resolver.setPrefix("/templates/");
        // PISTA: ClassLoaderTemplateResolver (busca en el classpath), NO
        // FileTemplateResolver (rutas de disco).
        // OJO: el prefijo apunta a src/main/resources/templates/. process("factura")
        // resolverá /templates/factura.html (prefijo + nombre + sufijo del paso 4).
        // CULTURA: el ClassLoader las encuentra dentro del JAR al desplegar (b22);
        // una ruta absoluta de disco se rompería en el contenedor Docker.
    }

    public static void paso04ConfigurarSufijo() {
        // GUÍA: teoría 25.1. Sobre el MISMO resolver del paso 3:
        //   resolver.setSuffix(".html");
        //   resolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
        // PISTA: TemplateMode.HTML es un enum, no el String "HTML".
        // OJO: con sufijo .html, process("factura") busca factura.html; no incluyas
        // tú la extensión al llamar a process.
    }

    public static void paso05ConfigurarCodificacion() {
        // GUÍA: teoría 25.1 y error común nº 3. Tildes y € exigen UTF-8:
        //   resolver.setCharacterEncoding("UTF-8");
        // OJO: si lo omites, una factura con "Compañía" o "€" puede salir con
        // caracteres rotos (Â, Ã) — inaceptable en un documento legal.
    }

    public static void paso06DesactivarCache() {
        // GUÍA: teoría 25.1. En desarrollo:
        //   resolver.setCacheable(false);
        // PISTA: false → relee el fichero .html en cada process(), así ves tus
        // cambios sin reiniciar la app.
        // CULTURA: en PRODUCCIÓN se deja en true (el default): cachear la plantilla
        // parseada es una de las optimizaciones de rendimiento del bloque 21.
    }

    public static void paso07VincularResolver() {
        // GUÍA: teoría 25.1. Enchufa el resolver al motor:
        //   engine.setTemplateResolver(resolver);
        // PISTA: reutiliza el 'engine' del paso 2 y el 'resolver' del paso 3.
        // OJO: un motor sin resolver no sabe dónde buscar y lanzará
        // TemplateInputException al procesar.
    }

    public static void paso08ExplicacionArquitectura() {
        // GUÍA: teoría 25.1, párrafo introductorio. Aquí NO hay código: documenta
        // (en un comentario o javadoc) por qué evitamos @Controller.
        // 1. Un @Controller MVC devuelve el NOMBRE de una vista y Spring negocia
        //    el HTML con el navegador.
        // 2. Una API REST no tiene navegador como cliente: tiene n8n, un front o
        //    otro servicio que esperan datos o un binario (PDF).
        // 3. Por eso usamos el motor STANDALONE: recibe datos, devuelve un String,
        //    y nosotros decidimos qué hacer con él (convertirlo a PDF en Ej206).
    }

    public static void paso09PrepararContexto() {
        // GUÍA: teoría 25.1, pieza "Contexto". El contexto es la bolsa de datos de
        // UNA petición; de momento, vacío:
        //   var ctx = new org.thymeleaf.context.Context();
        // PISTA: org.thymeleaf.context.Context (no confundir con otros Context del
        // JDK). En Ej202 le añadirás variables con ctx.setVariable(...).
    }

    public static void paso10ProcesarPlantillaPrueba() {
        // GUÍA: teoría 25.1. Cierra el montaje procesando una plantilla mínima y
        // devolviendo el String para confirmar que no salta excepción:
        //   String html = engine.process("prueba", ctx);   // necesita /templates/prueba.html
        // PISTA: el nombre que pasas a process NO lleva extensión (el resolver le
        // pone .html). Para una prueba rápida sin fichero puedes usar un
        // StringTemplateResolver con HTML inline.
        // OJO: si no existe la plantilla salta TemplateInputException; ese error
        // suele ser un prefijo/sufijo mal puesto en los pasos 3-4.
    }

    public static boolean ejecutar() {
        // Llama a todos los pasos para comprobar la ejecución
        paso01AnadirDependencia();
        paso02CrearMotorTemplateEngine();
        paso03ConfigurarResolver();
        paso04ConfigurarSufijo();
        paso05ConfigurarCodificacion();
        paso06DesactivarCache();
        paso07VincularResolver();
        paso08ExplicacionArquitectura();
        paso09PrepararContexto();
        paso10ProcesarPlantillaPrueba();
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Validando los 10 pasos: " + ejecutar());
    }
}
