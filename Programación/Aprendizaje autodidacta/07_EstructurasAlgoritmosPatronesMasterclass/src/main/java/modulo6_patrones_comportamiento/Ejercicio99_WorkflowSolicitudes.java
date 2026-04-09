package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 99: COMBINADO — Sistema de Workflows
 * ============================================================================
 * 📚 Combina: Chain of Responsibility + State + Command + Memento
 *
 * CONTEXTO:
 * Un sistema de aprobación de solicitudes (vacaciones, gastos, compras)
 * que combina múltiples patrones:
 * - Chain: la solicitud pasa por aprobadores de diferentes niveles.
 * - State: la solicitud tiene estados (Pendiente, EnRevision, Aprobada, Rechazada).
 * - Command: cada acción del aprobador (aprobar, rechazar, escalar) es un command.
 * - Memento: se guarda historial de estados para auditoría.
 *
 * RESTRICCIONES:
 * - Niveles: Supervisor (< €500), Director (< €5000), CEO (cualquier monto).
 * - Cada aprobador puede: aprobar, rechazar o escalar.
 * - El historial guarda cada cambio de estado (auditable).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio99_WorkflowSolicitudes {

    // ====== MEMENTO: Snapshot de solicitud ======
    // TODO 1: Implementar SolicitudSnapshot: estado, aprobador, timestamp, comentario.

    // ====== STATE: Estados de la solicitud ======
    // TODO 2: Interfaz EstadoSolicitud con transiciones.
    //         PendienteState, EnRevisionState, AprobadaState, RechazadaState.

    // ====== SOLICITUD (Context + Originator) ======
    // TODO 3: Implementar Solicitud:
    //         id, solicitante, descripcion, monto, estado.
    //         SolicitudSnapshot[] historial (auditoría).
    //         guardarSnapshot(), getHistorialCompleto().

    // ====== CHAIN: Aprobadores ======
    // TODO 4: Implementar clase abstracta Aprobador:
    //         String nombre, double limiteAprobacion, Aprobador siguiente.
    //         procesar(Solicitud s): si monto <= limite → aprobar,
    //           si no → escalar al siguiente.

    // ====== COMMAND: Acciones ======
    // TODO 5: Implementar AprobarCommand, RechazarCommand, EscalarCommand.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 99: Workflow Solicitudes ===\n");

        // TODO 6:
        //         // Cadena de aprobadores
        //         Aprobador supervisor = new Supervisor("María", 500);
        //         Aprobador director = new Director("Carlos", 5000);
        //         Aprobador ceo = new CEO("Ana");
        //         supervisor.setNext(director).setNext(ceo);
        //
        //         // Solicitudes
        //         Solicitud s1 = new Solicitud(1, "Juan", "Material oficina", 200);
        //         Solicitud s2 = new Solicitud(2, "Laura", "Servidor cloud", 3000);
        //         Solicitud s3 = new Solicitud(3, "Pedro", "Adquisición empresa", 50000);
        //
        //         supervisor.procesar(s1);  // Supervisor aprueba
        //         supervisor.procesar(s2);  // Escala a Director
        //         supervisor.procesar(s3);  // Escala a CEO
        //
        //         // Auditoría
        //         s1.getHistorialCompleto();
        //         s3.getHistorialCompleto();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 99 ===");
    }
}
