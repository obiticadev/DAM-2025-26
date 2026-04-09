package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 53: Abstract Factory — Sistema de Temas (Dark / Light)
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.4
 *
 * CONTEXTO:
 * Una aplicación soporta dos temas visuales: Dark Mode y Light Mode.
 * Cada tema define una FAMILIA coherente de colores, fuentes e iconos.
 * Abstract Factory garantiza que nunca se mezclen estilos de diferentes temas.
 *
 * Productos:
 * - ColorPalette: getBackground(), getForeground(), getAccent().
 * - Typography: getFont(), getSize(), getBold().
 * - IconSet: getIconoGuardar(), getIconoEliminar(), getIconoEditar().
 *
 * Familias:
 * - DarkTheme: fondos oscuros, letras claras, iconos outline.
 * - LightTheme: fondos claros, letras oscuras, iconos filled.
 *
 * RESTRICCIONES:
 * - Abstract Factory: ThemeFactory con tres factory methods.
 * - Nunca instanciar productos directamente desde el cliente.
 * - Cada color se representa como String hex ("#1a1a2e").
 * ============================================================================
 */
public class Ejercicio53_AbstractFactoryTemas {

    // ==== INTERFACES DE PRODUCTOS ====

    interface ColorPalette {
        // TODO 1: Métodos: getBackground(), getForeground(), getAccent()
        //         Todos retornan String (color hex o nombre).
        //         void mostrar() — imprime la paleta formateada.
    }

    interface Typography {
        // TODO 2: Métodos: getFont(), getSize(), isBold()
        //         void mostrar() — imprime la tipografía.
    }

    interface IconSet {
        // TODO 3: Métodos que retornan String (emoji o texto):
        //         getIconoGuardar(), getIconoEliminar(), getIconoEditar(), getIconoBuscar()
        //         void mostrar() — imprime todos los iconos.
    }

    // ==== PRODUCTOS DARK THEME ====
    // TODO 4: Implementar DarkColorPalette:
    //         background="#1a1a2e", foreground="#e0e0e0", accent="#e94560"
    //         Implementar DarkTypography: font="JetBrains Mono", size=14, bold=false
    //         Implementar DarkIconSet: guardar="💾", eliminar="🗑️", editar="✏️", buscar="🔍"

    // ==== PRODUCTOS LIGHT THEME ====
    // TODO 5: Implementar LightColorPalette:
    //         background="#ffffff", foreground="#333333", accent="#0066cc"
    //         Implementar LightTypography: font="Inter", size=16, bold=true
    //         Implementar LightIconSet: guardar="■💾", eliminar="■🗑️", editar="■✏️", buscar="■🔍"

    // ==== ABSTRACT FACTORY ====
    interface ThemeFactory {
        ColorPalette crearColores();
        Typography crearTipografia();
        IconSet crearIconos();
    }

    // TODO 6: Implementar DarkThemeFactory y LightThemeFactory.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    static void aplicarTema(ThemeFactory factory, String nombre) {
        System.out.println("═══════ Tema: " + nombre + " ═══════");
        // TODO 7: Crear colores, tipografía e iconos usando la factory.
        //         Llamar a mostrar() en cada uno.
        //         Esto simula aplicar un tema consistente a la aplicación.
    }

    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 53: Abstract Factory Temas ===\n");

        // aplicarTema(new DarkThemeFactory(), "DARK MODE");
        // System.out.println();
        // aplicarTema(new LightThemeFactory(), "LIGHT MODE");

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 53 ===");
    }
}
