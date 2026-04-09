package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 75: Integración — Mini-Framework con Múltiples Patrones
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md
 *
 * CONTEXTO:
 * Este ejercicio INTEGRADOR combina 4 patrones estructurales en un sistema
 * de gestión de plugins para una aplicación:
 *
 * - Composite: plugins simples y grupos de plugins (compuestos).
 * - Facade: el PluginManager como fachada para cargar/ejecutar/descargar.
 * - Proxy: PluginProxy que verifica la licencia antes de ejecutar.
 * - Decorator: LoggingPluginDecorator que logea cada ejecución.
 *
 * Implementa el sistema completo con los 4 patrones interactuando.
 *
 * RESTRICCIONES:
 * - Interfaz Plugin: ejecutar(), getNombre(), getVersion().
 * - Todo funcional y testeable desde main().
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio75_IntegracionEstructurales {

    // ==== INTERFAZ BASE ====
    interface Plugin {
        // TODO 1: void ejecutar(), String getNombre(), String getVersion()
        //         boolean estaActivo()
    }

    // ==== COMPOSITE: grupos de plugins ====
    // TODO 2: Implementar PluginSimple (Leaf):
    //         - nombre, version, activo.
    //         - ejecutar(): imprime "[Plugin] Ejecutando <nombre> v<version>..."

    // TODO 3: Implementar GrupoPlugins (Composite):
    //         - nombre, Plugin[] hijos (max 10), int numHijos.
    //         - agregar(Plugin p): añadir al grupo.
    //         - ejecutar(): ejecutar TODOS los plugins del grupo.

    // ==== PROXY: verificación de licencia ====
    // TODO 4: Implementar PluginProxy:
    //         - Envuelve un Plugin.
    //         - Tiene boolean licenciaValida.
    //         - ejecutar(): si licenciaValida → delegar. Si no → "⛔ Licencia inválida".

    // ==== DECORATOR: logging ====
    // TODO 5: Implementar LoggingPluginDecorator:
    //         - Envuelve un Plugin.
    //         - ejecutar(): imprime timestamp, delega, imprime duración.

    // ==== FACADE: PluginManager ====
    // TODO 6: Implementar PluginManager (Facade):
    //         - Plugin[] pluginsCargados (max 20).
    //         - cargarPlugin(Plugin p): añade al array.
    //         - ejecutarTodos(): ejecuta todos los cargados.
    //         - ejecutarPorNombre(String nombre): busca y ejecuta uno.
    //         - listarPlugins(): imprime todos con estado.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 75: Integración Estructurales ===\n");

        // TODO 7: Construir el sistema:
        //         Plugin auth = new PluginSimple("Auth", "2.1.0");
        //         Plugin cache = new PluginSimple("Cache", "1.0.3");
        //         Plugin analytics = new PluginSimple("Analytics", "3.0.0");
        //
        //         // Proxy: analytics requiere licencia
        //         Plugin analyticsProxy = new PluginProxy(analytics, true);
        //         Plugin piratePlugin = new PluginProxy(
        //             new PluginSimple("ProPlugin", "1.0.0"), false);
        //
        //         // Decorator: logging para auth
        //         Plugin authConLog = new LoggingPluginDecorator(auth);
        //
        //         // Composite: grupo de plugins
        //         GrupoPlugins grupo = new GrupoPlugins("Seguridad");
        //         grupo.agregar(authConLog);
        //         grupo.agregar(analyticsProxy);
        //
        //         // Facade: manager
        //         PluginManager manager = new PluginManager();
        //         manager.cargarPlugin(grupo);
        //         manager.cargarPlugin(cache);
        //         manager.cargarPlugin(piratePlugin);
        //
        //         manager.listarPlugins();
        //         System.out.println();
        //         manager.ejecutarTodos();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 75 ===");
    }
}
