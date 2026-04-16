package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 92: Memento — Editor con Historial y Ctrl+Z
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.10
 *
 * CONTEXTO:
 * Un editor guarda "snapshots" (mementos) de su estado para poder
 * restaurar versiones anteriores. El Caretaker almacena los mementos
 * sin acceder a su contenido (encapsulación).
 *
 * Implementa:
 * - Clase EditorTexto (Originator): texto, cursorPos, fuente.
 *   Crea y restaura mementos.
 * - Clase Memento: estado inmutable (privado) del editor.
 * - Clase Historial (Caretaker): pila de mementos (max 30).
 *
 * RESTRICCIONES:
 * - El Memento es inmutable y su estado SOLO accesible por el Originator.
 * - El Caretaker NO conoce el contenido del Memento.
 * - Implementar undo() y múltiples niveles de deshacer.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio92_MementoEditor {

    // TODO 1: Implementar clase Memento con campos privados finales:
    //         String texto, int cursorPos, String fuente, long timestamp.
    //         Constructor package-private o accesible solo por el editor.

    // TODO 2: Implementar EditorTexto (Originator):
    //         Campos: texto, cursorPos, fuente.
    //         Memento guardar(): crea Memento con estado actual.
    //         void restaurar(Memento m): restaura estado desde memento.
    //         void escribir(String t), void moverCursor(int pos), void setFuente(String f).

    // TODO 3: Implementar Historial (Caretaker):
    //         Memento[] snapshots (max 30), int top.
    //         void push(Memento m): guarda snapshot.
    //         Memento pop(): retorna el último snapshot.
    //         boolean isEmpty(), int size().

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 92: Memento Editor ===\n");

        // TODO 4:
        //         EditorTexto editor = new EditorTexto();
        //         Historial historial = new Historial();
        //
        //         editor.escribir("Hola ");
        //         historial.push(editor.guardar()); // snapshot 1
        //
        //         editor.escribir("Mundo ");
        //         historial.push(editor.guardar()); // snapshot 2
        //
        //         editor.escribir("Cruel");
        //         System.out.println("Actual: " + editor);  // "Hola Mundo Cruel"
        //
        //         // Undo (Ctrl+Z)
        //         editor.restaurar(historial.pop());
        //         System.out.println("Undo 1: " + editor);  // "Hola Mundo "
        //
        //         editor.restaurar(historial.pop());
        //         System.out.println("Undo 2: " + editor);  // "Hola "

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 92 ===");
    }
}
