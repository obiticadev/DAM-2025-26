package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 89: Chain of Responsibility — Soporte Técnico Multi-Nivel
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.8
 *
 * CONTEXTO:
 * El soporte técnico tiene tres niveles:
 * - Nivel 1 (Bot): resuelve FAQs simples (contraseña, estado pedido).
 * - Nivel 2 (Técnico): problemas técnicos (conectividad, errores).
 * - Nivel 3 (Especialista): problemas complejos (bugs, escalaciones).
 *
 * Cada nivel intenta resolver el ticket. Si no puede, lo escala.
 *
 * Implementa:
 * - Clase Ticket: id, prioridad (1-3), descripcion, categoria.
 * - Clase abstracta NivelSoporte: con atender(Ticket) y setNext.
 * - Tres niveles concretos que procesan según categoría y prioridad.
 *
 * RESTRICCIONES:
 * - Nivel 1 resuelve: "contraseña", "estado_pedido", "horario".
 * - Nivel 2 resuelve: "error_conexion", "configuracion", "instalacion".
 * - Nivel 3 resuelve: todo lo que llegue.
 * - Cada nivel imprime si resuelve o escala.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio89_ChainSoporte {

    static class Ticket {
        int id;
        int prioridad;
        String categoria;
        String descripcion;
        Ticket(int id, int prioridad, String categoria, String descripcion) {
            this.id = id; this.prioridad = prioridad;
            this.categoria = categoria; this.descripcion = descripcion;
        }
    }

    // TODO 1: Implementar clase abstracta NivelSoporte:
    //         String nombre, NivelSoporte siguiente.
    //         void atender(Ticket t): si puedeResolver(t) → resolver, si no → escalar.
    //         abstract boolean puedeResolver(Ticket t).

    // TODO 2: Implementar Nivel1Bot, Nivel2Tecnico, Nivel3Especialista.

    // TODO 3: Imprimir formato:
    //         "[Nivel 1 - Bot] Ticket #1: ✅ Resuelto — Tu contraseña se ha reseteado."
    //         "[Nivel 1 - Bot] Ticket #2: ⬆️ Escalando a Nivel 2..."

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 89: Chain Soporte ===\n");

        // TODO 4:
        //         NivelSoporte cadena = new Nivel1Bot();
        //         cadena.setNext(new Nivel2Tecnico())
        //               .setNext(new Nivel3Especialista());
        //
        //         Ticket[] tickets = {
        //             new Ticket(1, 1, "contraseña", "No puedo entrar"),
        //             new Ticket(2, 2, "error_conexion", "No carga la página"),
        //             new Ticket(3, 3, "bug_produccion", "Datos corruptos"),
        //             new Ticket(4, 1, "estado_pedido", "¿Dónde está mi pedido?"),
        //             new Ticket(5, 2, "instalacion", "No puedo instalar la app")
        //         };
        //         for (Ticket t : tickets) {
        //             cadena.atender(t);
        //             System.out.println();
        //         }

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 89 ===");
    }
}
