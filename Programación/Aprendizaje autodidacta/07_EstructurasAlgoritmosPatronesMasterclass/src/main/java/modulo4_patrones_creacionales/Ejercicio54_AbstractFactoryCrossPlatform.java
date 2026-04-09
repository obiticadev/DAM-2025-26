package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 54: Abstract Factory — Sistema Cross-Platform (BD + Cache)
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.4
 *
 * CONTEXTO:
 * Un sistema empresarial debe funcionar en dos entornos:
 * - Desarrollo (Dev): base de datos en memoria, cache local.
 * - Producción (Prod): base de datos real (simulada), cache distribuida (simulada).
 *
 * Abstract Factory crea la FAMILIA completa de infraestructura para cada
 * entorno, garantizando que nunca se mezcle una BD en memoria con un cache
 * distribuido (lo cual sería incoherente).
 *
 * Productos:
 * - Database: conectar(), ejecutarQuery(String sql), desconectar().
 * - CacheService: poner(String clave, String valor), obtener(String clave), limpiar().
 * - Logger: info(String msg), error(String msg), debug(String msg).
 *
 * Familias:
 * - DevInfraFactory → InMemoryDB + LocalCache + ConsoleLogger
 * - ProdInfraFactory → PostgresDB + RedisCache + FileLogger
 *
 * RESTRICCIONES:
 * - Todo simulado con prints (no conexiones reales).
 * - Cache almacena en un par de arrays clave-valor manuales (max 20).
 * - Logger formatea con timestamps simulados.
 * - El código de aplicación SOLO usa las interfaces, NUNCA las clases concretas.
 * ============================================================================
 */
public class Ejercicio54_AbstractFactoryCrossPlatform {

    // ==== INTERFACES ====

    interface Database {
        // TODO 1: void conectar(), String ejecutarQuery(String sql), void desconectar()
        //         String getNombre()
    }

    interface CacheService {
        // TODO 2: void poner(String clave, String valor)
        //         String obtener(String clave) — retorna null si no existe.
        //         void limpiar() — vacía todo el cache.
        //         int size()
    }

    interface Logger {
        // TODO 3: void info(String msg), void error(String msg), void debug(String msg)
    }

    // ==== FAMILIA DEV ====

    // TODO 4: Implementar:
    //         InMemoryDB: conectar→"[InMemoryDB] Conectado a BD en memoria"
    //                     ejecutarQuery→"[InMemoryDB] Simulando: <sql> → 0 filas"
    //         LocalCache: usa arrays internos clave[]/valor[], max 20.
    //                     poner/obtener imprimen "[LocalCache] ..."
    //         ConsoleLogger: imprime directamente a System.out con formato:
    //                     "[DEV][INFO] <msg>", "[DEV][ERROR] <msg>", etc.

    // ==== FAMILIA PROD ====

    // TODO 5: Implementar:
    //         PostgresDB: conectar→"[PostgreSQL] Conectando a prod-server:5432..."
    //                     ejecutarQuery→"[PostgreSQL] Ejecutando: <sql> → 42 filas"
    //         RedisCache: mismo almacenamiento pero con mensajes "[Redis] ..."
    //         FileLogger: imprime con formato "[PROD][INFO] <timestamp> <msg>"

    // ==== ABSTRACT FACTORY ====

    interface InfraFactory {
        Database crearDatabase();
        CacheService crearCache();
        Logger crearLogger();
    }

    // TODO 6: Implementar DevInfraFactory y ProdInfraFactory.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    static void ejecutarAplicacion(InfraFactory factory, String entorno) {
        System.out.println("══════ Entorno: " + entorno + " ══════");
        // TODO 7: Crear db, cache, logger usando la factory.
        //         logger.info("Iniciando aplicación...");
        //         db.conectar();
        //         db.ejecutarQuery("SELECT * FROM usuarios");
        //         cache.poner("user:1", "Alice");
        //         cache.poner("user:2", "Bob");
        //         logger.info("Cache size: " + cache.size());
        //         logger.info("user:1 = " + cache.obtener("user:1"));
        //         db.desconectar();
        //         logger.info("Aplicación finalizada.");
    }

    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 54: Abstract Factory Cross-Platform ===\n");

        // ejecutarAplicacion(new DevInfraFactory(), "DESARROLLO");
        // System.out.println();
        // ejecutarAplicacion(new ProdInfraFactory(), "PRODUCCIÓN");

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 54 ===");
    }
}
