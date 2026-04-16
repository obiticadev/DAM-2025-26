package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 85: State — Flujo de Publicación de Documento
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.6
 *
 * CONTEXTO:
 * Un documento sigue un flujo de publicación:
 * Borrador → En Revisión → Publicado.
 * Cada estado permite diferentes acciones:
 * - Borrador: editar, enviarARevision.
 * - EnRevision: aprobar, rechazar (vuelve a Borrador).
 * - Publicado: no se puede editar ni rechazar.
 *
 * Implementa:
 * - Interfaz EstadoDocumento: editar(String), enviarARevision(), aprobar(), rechazar().
 * - Tres estados concretos.
 * - Documento (Context): titulo, contenido, estado.
 *
 * RESTRICCIONES:
 * - Acciones no permitidas imprimen " Acción no permitida en estado X".
 * - Aprobar en Borrador no es posible (debe pasar por revisión).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio85_StatePublicacion {

    interface EstadoDocumento {
        // TODO 1: void editar(Documento85 doc, String nuevoContenido)
        //         void enviarARevision(Documento85 doc)
        //         void aprobar(Documento85 doc)
        //         void rechazar(Documento85 doc)
        //         String getNombre()
    }

    // TODO 2: Implementar Documento85 (Context):
    //         String titulo, String contenido, EstadoDocumento estado.
    //         Los 4 métodos de acción delegan al estado.
    //         setEstado(EstadoDocumento e): transiciona e imprime el nuevo estado.

    // TODO 3: Implementar BorradorState:
    //         editar → actualiza contenido.
    //         enviarARevision → cambia a EnRevisionState.
    //         aprobar/rechazar → "No permitido en Borrador".

    // TODO 4: Implementar EnRevisionState:
    //         editar → "No se puede editar en revisión".
    //         enviarARevision → "Ya está en revisión".
    //         aprobar → cambia a PublicadoState.
    //         rechazar → cambia a BorradorState con mensaje "Rechazado. Volviendo a borrador."

    // TODO 5: Implementar PublicadoState:
    //         Todas excepto lectura → "No permitido, documento ya publicado."

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 85: State Publicación ===\n");

        // TODO 6:
        //         Documento85 doc = new Documento85("Mi Artículo");
        //         doc.editar("Contenido inicial...");
        //         doc.enviarARevision();
        //         doc.editar("Intento editar en revisión"); // ❌
        //         doc.rechazar(); // Vuelve a borrador
        //         doc.editar("Contenido corregido");
        //         doc.enviarARevision();
        //         doc.aprobar();  // ✅ Publicado
        //         doc.editar("Intento editar publicado");   // ❌

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 85 ===");
    }
}
