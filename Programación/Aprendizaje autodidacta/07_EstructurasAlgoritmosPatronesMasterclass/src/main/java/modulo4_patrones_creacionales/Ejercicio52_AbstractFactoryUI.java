package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 52: Abstract Factory — Familias de Componentes UI
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.4
 *
 * CONTEXTO:
 * Abstract Factory crea FAMILIAS de objetos relacionados. Aquí modelamos
 * un toolkit de interfaz gráfica que soporta dos estilos: Windows y Mac.
 * Cada estilo tiene su propia versión de Botón, Checkbox y TextField.
 *
 * La clave es que TODOS los componentes de una familia son consistentes:
 * si usas la WindowsFactory, obtienes Botón Windows + Checkbox Windows, etc.
 * Nunca mezclas un Botón de Windows con un Checkbox de Mac.
 *
 * Arquitectura:
 * - Interfaces: Boton, Checkbox, TextField.
 * - Familia Windows: WindowsBoton, WindowsCheckbox, WindowsTextField.
 * - Familia Mac: MacBoton, MacCheckbox, MacTextField.
 * - Interfaz UIFactory: crearBoton(), crearCheckbox(), crearTextField().
 * - Implementaciones: WindowsUIFactory, MacUIFactory.
 *
 * RESTRICCIONES:
 * - El cliente SOLO conoce las interfaces (Boton, Checkbox, TextField).
 * - El cliente recibe un UIFactory y crea componentes sin saber si son Win o Mac.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio52_AbstractFactoryUI {

    // ==== INTERFACES DE PRODUCTOS ====

    interface Boton {
        // TODO 1: Definir métodos:
        //         void pintar();  — imprime representación visual del botón.
        //         void click();   — imprime reacción al click.
        //         String getEstilo();
    }

    interface Checkbox {
        // TODO 2: Definir métodos:
        //         void pintar();
        //         void marcar();
        //         void desmarcar();
        //         boolean estaMarcado();
    }

    interface TextField {
        // TODO 3: Definir métodos:
        //         void pintar();
        //         void escribir(String texto);
        //         String getTexto();
    }

    // ==== PRODUCTOS CONCRETOS WINDOWS ====

    // TODO 4: Implementar WindowsBoton, WindowsCheckbox, WindowsTextField.
    //         Cada uno imprime mensajes con estilo Windows:
    //         WindowsBoton.pintar() → "[Win Botón] ┌──────────┐ │  Click   │ └──────────┘"
    //         WindowsCheckbox.pintar() → "[Win Checkbox] [☐] Opción"
    //         WindowsTextField.pintar() → "[Win TextField] |_______________|"
    //         Almacenar estado interno (marcado, texto).

    // ==== PRODUCTOS CONCRETOS MAC ====

    // TODO 5: Implementar MacBoton, MacCheckbox, MacTextField.
    //         Cada uno imprime mensajes con estilo Mac:
    //         MacBoton.pintar() → "[Mac Botón] (  Click  )"  (bordes redondeados)
    //         MacCheckbox.pintar() → "[Mac Checkbox] ◻ Opción"
    //         MacTextField.pintar() → "[Mac TextField] ⌈               ⌉"

    // ==== ABSTRACT FACTORY ====

    interface UIFactory {
        // TODO 6: Definir métodos factory:
        //         Boton crearBoton();
        //         Checkbox crearCheckbox();
        //         TextField crearTextField();
    }

    // TODO 7: Implementar WindowsUIFactory y MacUIFactory.
    //         Cada una retorna los productos de SU familia.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 52: Abstract Factory UI ===\n");

        // TODO 8: Crear un método renderizarFormulario(UIFactory factory) que:
        //         1. Cree un Boton, un Checkbox y un TextField con la factory.
        //         2. Llame a pintar() en cada uno.
        //         3. Interactúe: click en botón, marcar checkbox, escribir en textfield.
        //
        //         Luego en main():
        //         System.out.println("=== WINDOWS ===");
        //         renderizarFormulario(new WindowsUIFactory());
        //         System.out.println("\n=== MAC ===");
        //         renderizarFormulario(new MacUIFactory());
        //
        //         NOTA: renderizarFormulario NO conoce las clases concretas.
        //         Solo trabaja con interfaces.

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 52 ===");
    }
}
