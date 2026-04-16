package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 97: COMBINADO — Mini MVC Framework
 * ============================================================================
 * 📚 Combina: Observer + Strategy + Command
 *
 * CONTEXTO:
 * Construye un mini framework Model-View-Controller que:
 * - Model: datos con notificación Observer a las vistas.
 * - View: muestra datos (Strategy para diferentes formatos).
 * - Controller: recibe acciones como Commands (con undo).
 *
 * Escenario: Gestión de una lista de tareas (Todo List).
 * - Modelo: TodoList con tareas.
 * - Vistas: VistaSimple (texto), VistaDetallada (con fechas y estados).
 * - Comandos: AgregarTareaCommand, CompletarTareaCommand, EliminarTareaCommand.
 *
 * RESTRICCIONES:
 * - Al modificar el modelo, las vistas se actualizan automáticamente (Observer).
 * - Las vistas usan Strategy para formatear la salida.
 * - Las acciones son Commands con undo.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio97_MVCTodoList {

    // ====== OBSERVER ======
    // TODO 1: Interfaz VistaObserver: void modeloCambio(TodoModel model)

    // ====== MODEL ======
    // TODO 2: Implementar TodoModel (Subject):
    //         String[] tareas, boolean[] completadas, int numTareas.
    //         VistaObserver[] observadores.
    //         agregar(String tarea), completar(int idx), eliminar(int idx).
    //         Notificar a observadores tras cada cambio.

    // ====== STRATEGY para formato de vista ======
    // TODO 3: Interfaz FormatoVista: String formatear(TodoModel model)
    //         FormatoSimple: "1. [ ] Tarea"  / "2. [x] Tarea"
    //         FormatoDetallado: "📋 #1 | ⬜ Tarea | Pendiente"

    // ====== VIEWS ======
    // TODO 4: Implementar Vista (Observer):
    //         - Tiene un FormatoVista (Strategy).
    //         - modeloCambio() → re-renderiza usando el formato.

    // ====== COMMANDS ======
    // TODO 5: Implementar AgregarTareaCommand, CompletarTareaCommand, EliminarTareaCommand.
    //         Cada uno tiene ejecutar() y deshacer().

    // ====== CONTROLLER ======
    // TODO 6: Implementar TodoController:
    //         Ejecuta commands y mantiene historial para undo.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 97: MVC Todo List ===\n");

        // TODO 7:
        //         TodoModel model = new TodoModel();
        //         Vista vistaSimple = new Vista("Simple", new FormatoSimple());
        //         Vista vistaDetallada = new Vista("Detallada", new FormatoDetallado());
        //         model.registrar(vistaSimple);
        //         model.registrar(vistaDetallada);
        //
        //         TodoController ctrl = new TodoController(model);
        //         ctrl.ejecutar(new AgregarTareaCommand(model, "Estudiar Observer"));
        //         ctrl.ejecutar(new AgregarTareaCommand(model, "Estudiar Strategy"));
        //         ctrl.ejecutar(new CompletarTareaCommand(model, 0));
        //         ctrl.undo(); // Deshace completar

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 97 ===");
    }
}
