package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 80: Command — Editor de Texto con Undo/Redo
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.4
 *
 * CONTEXTO:
 * Un editor de texto soporta operaciones: escribir texto, borrar texto,
 * poner en negrita. Cada operación es un Command que puede ejecutarse
 * y DESHACERSE (undo). El historial permite Undo y Redo.
 *
 * Implementa:
 * - Interfaz Command: ejecutar(), deshacer(), getDescripcion().
 * - Comandos: EscribirCommand, BorrarCommand, ReemplazarCommand.
 * - Editor (Receiver): contiene el texto y operaciones primitivas.
 * - HistorialComandos (Invoker): ejecuta, almacena historial, undo/redo.
 *
 * RESTRICCIONES:
 * - Cada command guarda el estado previo para poder deshacer.
 * - El historial usa dos pilas (arrays): undoStack y redoStack.
 * - Al hacer undo, el command se mueve al redoStack.
 * - Al ejecutar un nuevo comando, el redoStack se limpia.
 * - MAX 50 comandos en historial.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio80_CommandEditor {

    interface Command {
        // TODO 1: void ejecutar(), void deshacer(), String getDescripcion()
    }

    // TODO 2: Implementar Editor (Receiver):
    //         - String contenido (inicialmente "").
    //         - void insertar(String texto): concatena al final.
    //         - void borrar(int n): elimina los últimos n caracteres.
    //         - void reemplazar(String viejo, String nuevo).
    //         - String getContenido().

    // TODO 3: Implementar EscribirCommand:
    //         - Guarda el texto a insertar.
    //         - ejecutar(): editor.insertar(texto).
    //         - deshacer(): editor.borrar(texto.length()).

    // TODO 4: Implementar BorrarCommand:
    //         - Guarda cuántos caracteres borrar y el texto borrado (para undo).
    //         - ejecutar(): guarda los últimos n chars, luego editor.borrar(n).
    //         - deshacer(): editor.insertar(textoBorrado).

    // TODO 5: Implementar HistorialComandos (Invoker):
    //         - Command[] undoStack (max 50), int undoTop.
    //         - Command[] redoStack (max 50), int redoTop.
    //         - ejecutar(Command cmd): cmd.ejecutar(), push a undoStack, limpiar redoStack.
    //         - undo(): pop de undoStack, cmd.deshacer(), push a redoStack.
    //         - redo(): pop de redoStack, cmd.ejecutar(), push a undoStack.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 80: Command Editor ===\n");

        // TODO 6:
        //         Editor editor = new Editor();
        //         HistorialComandos historial = new HistorialComandos();
        //
        //         historial.ejecutar(new EscribirCommand(editor, "Hola "));
        //         historial.ejecutar(new EscribirCommand(editor, "Mundo"));
        //         System.out.println("Texto: " + editor.getContenido()); // "Hola Mundo"
        //
        //         historial.ejecutar(new EscribirCommand(editor, " Cruel"));
        //         System.out.println("Texto: " + editor.getContenido()); // "Hola Mundo Cruel"
        //
        //         historial.undo();
        //         System.out.println("Undo: " + editor.getContenido());  // "Hola Mundo"
        //
        //         historial.undo();
        //         System.out.println("Undo: " + editor.getContenido());  // "Hola "
        //
        //         historial.redo();
        //         System.out.println("Redo: " + editor.getContenido());  // "Hola Mundo"

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 80 ===");
    }
}
