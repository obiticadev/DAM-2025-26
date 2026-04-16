package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 68: Proxy — Proxy de Caché con Lazy Loading
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.5
 *
 * CONTEXTO:
 * Consultar una base de datos es caro. Un Caching Proxy almacena los
 * resultados ya consultados y los retorna directamente si se vuelven
 * a pedir, evitando accesos innecesarios a la BD.
 *
 * Además, implementa Lazy Loading: el objeto real (la conexión a BD)
 * NO se crea hasta la primera consulta.
 *
 * Implementa:
 * - Interfaz BaseDatos: consultar(String sql), int totalConsultas().
 * - BaseDatosReal: simula consultas con delay (Thread.sleep o contador).
 * - BaseDatosProxy: cachea resultados y aplica lazy loading.
 *
 * RESTRICCIONES:
 * - Cache implementada con dos arrays paralelos: String[] sqls, String[] resultados.
 * - MAX 20 entradas de cache.
 * - La BaseDatosReal NO se instancia hasta la primera consulta (lazy).
 * - Imprimir "[CACHE HIT]" o "[CACHE MISS]" en cada consulta.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio68_ProxyCache {

    // TODO 1: Definir interfaz BaseDatos:
    //         String consultar(String sql)
    //         int totalConsultas()

    // TODO 2: Implementar BaseDatosReal:
    //         - Constructor costoso: imprime "[BD] Conectando a la base de datos... (3 seg)"
    //         - consultar(): imprime "[BD] Ejecutando: <sql>..." y retorna resultado simulado.
    //         - Mantener contador de consultas.

    // TODO 3: Implementar BaseDatosProxy:
    //         - Campo BaseDatosReal (inicializado a null — LAZY).
    //         - Arrays cache: String[] sqlCache, String[] resultadoCache, int cacheSize.
    //         - consultar():
    //           1. Buscar en cache: si sql existe → imprimir "[CACHE HIT]" y retornar.
    //           2. Si no está en cache:
    //              a. Si la BD real es null → crearla (lazy loading).
    //              b. Consultar la BD real.
    //              c. Almacenar en cache.
    //              d. Imprimir "[CACHE MISS]" y retornar resultado.

    // TODO 4: Implementar método limpiarCache() para vaciar el cache.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 68: Proxy de Caché ===\n");

        // TODO 5: Crear el proxy (NO la BD real directamente):
        //         BaseDatos db = new BaseDatosProxy();
        //
        //         // Primera consulta: MISS (y lazy loading de la BD)
        //         String r1 = db.consultar("SELECT * FROM usuarios");
        //         System.out.println("Resultado: " + r1);
        //
        //         // Segunda consulta igual: HIT
        //         String r2 = db.consultar("SELECT * FROM usuarios");
        //         System.out.println("Resultado: " + r2);
        //
        //         // Tercera consulta diferente: MISS
        //         String r3 = db.consultar("SELECT * FROM productos");
        //         System.out.println("Resultado: " + r3);
        //
        //         // Repetir: HIT
        //         String r4 = db.consultar("SELECT * FROM productos");
        //         System.out.println("Resultado: " + r4);
        //
        //         System.out.println("\nConsultas reales a BD: " + db.totalConsultas());
        //         // Debería ser 2 (no 4)

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 68 ===");
    }
}
