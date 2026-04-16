package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 88: Chain of Responsibility — Middleware HTTP
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.8
 *
 * CONTEXTO:
 * Un servidor HTTP recibe peticiones que pasan por una cadena de middleware:
 * 1. LoggingMiddleware: logea la petición.
 * 2. AuthMiddleware: verifica autenticación (token válido).
 * 3. ValidationMiddleware: valida el cuerpo de la petición.
 * 4. RateLimitMiddleware: limita peticiones por usuario.
 *
 * Si un middleware rechaza la petición, la cadena se DETIENE.
 *
 * Implementa:
 * - Clase abstracta Middleware: handle(Request), setNext(Middleware).
 * - Request: método, ruta, token, body, ip.
 * - Response simulada con código de estado.
 *
 * RESTRICCIONES:
 * - Cada middleware puede: procesar y pasar al siguiente, o detener la cadena.
 * - La cadena se construye encadenando setNext().
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio88_ChainMiddleware {

    static class Request {
        String metodo, ruta, token, body, ip;
        Request(String metodo, String ruta, String token, String body, String ip) {
            this.metodo = metodo; this.ruta = ruta; this.token = token;
            this.body = body; this.ip = ip;
        }
    }

    // TODO 1: Implementar clase abstracta Middleware:
    //         - Middleware next.
    //         - Middleware setNext(Middleware next): encadena y RETORNA next (para chaining).
    //         - boolean handle(Request req): procesa y si ok, delega al next.
    //         - abstract boolean procesar(Request req).

    // TODO 2: Implementar LoggingMiddleware:
    //         - Siempre imprime "[LOG] <metodo> <ruta> desde <ip>".
    //         - Siempre pasa al siguiente (retorna true).

    // TODO 3: Implementar AuthMiddleware:
    //         - Si token es null o vacío → "[AUTH] ❌ No autenticado." → retorna false.
    //         - Si token == "valid-token-123" → "[AUTH] ✅ Autenticado." → pasa.
    //         - Otro token → "[AUTH] ❌ Token inválido." → retorna false.

    // TODO 4: Implementar ValidationMiddleware:
    //         - Si body es null o vacío y método es POST → "[VAL] ❌ Body vacío." → false.
    //         - Si ok → pasa.

    // TODO 5: Implementar RateLimitMiddleware:
    //         - Almacena IPs y contadores (arrays, max 10 IPs).
    //         - Si una IP supera 3 peticiones → "[RATE] ❌ Límite excedido." → false.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 88: Chain Middleware ===\n");

        // TODO 6: Construir la cadena:
        //         Middleware chain = new LoggingMiddleware();
        //         chain.setNext(new AuthMiddleware())
        //              .setNext(new ValidationMiddleware())
        //              .setNext(new RateLimitMiddleware());
        //
        //         // Petición válida
        //         chain.handle(new Request("GET", "/api/users", "valid-token-123", "", "192.168.1.1"));
        //
        //         // Sin token
        //         chain.handle(new Request("GET", "/api/users", null, "", "10.0.0.1"));
        //
        //         // POST sin body
        //         chain.handle(new Request("POST", "/api/users", "valid-token-123", "", "192.168.1.1"));
        //
        //         // POST válido
        //         chain.handle(new Request("POST", "/api/users", "valid-token-123", "{\"name\":\"Alice\"}", "192.168.1.1"));

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 88 ===");
    }
}
