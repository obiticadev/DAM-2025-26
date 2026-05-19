package com.masterclass.api.b25_thymeleaf;

/**
 * Ejercicio 201 · Configuración del motor Thymeleaf Standalone.
 *
 * <p>Teoría: {@code teoria/25_Plantillas_Dinamicas_Thymeleaf.md}
 *
 * <p>Aislar el motor de plantillas de la capa web MVC.
 */
public final class Ej201ThymeleafStandalone {

    private Ej201ThymeleafStandalone() {
    }

    public static void paso01AnadirDependencia() {
        // TODO: añade la dependencia spring-boot-starter-thymeleaf en pom.xml
    }

    public static void paso02CrearMotorTemplateEngine() {
        // TODO: instancia un SpringTemplateEngine manualmente sin usar @Autowired
    }

    public static void paso03ConfigurarResolver() {
        // TODO: crea un ClassLoaderTemplateResolver apuntando al prefijo '/templates/'
    }

    public static void paso04ConfigurarSufijo() {
        // TODO: establece el sufijo a '.html' y el templateMode a 'HTML' en el resolver
    }

    public static void paso05ConfigurarCodificacion() {
        // TODO: establece la codificación UTF-8 para soportar tildes (setCharacterEncoding)
    }

    public static void paso06DesactivarCache() {
        // TODO: desactiva la caché del resolver (setCacheable(false)) para evitar reiniciar en desarrollo
    }

    public static void paso07VincularResolver() {
        // TODO: inyecta el resolver en el motor mediante templateEngine.setTemplateResolver(...)
    }

    public static void paso08ExplicacionArquitectura() {
        // TODO: explica por qué no usamos @Controller aquí: la API debe ser puramente REST
    }

    public static void paso09PrepararContexto() {
        // TODO: prepara un objeto org.thymeleaf.context.Context genérico sin variables
    }

    public static void paso10ProcesarPlantillaPrueba() {
        // TODO: procesa una plantilla vacía y devuelve el resultado para validar que no hay excepciones
    }

    public static boolean ejecutarTodos() {
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
        System.out.println("Validando los 10 pasos: " + ejecutarTodos());
    }
}
