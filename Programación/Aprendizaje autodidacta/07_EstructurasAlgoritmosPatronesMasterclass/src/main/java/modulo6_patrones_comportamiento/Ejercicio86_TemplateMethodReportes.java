package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 86: Template Method — Generador de Reportes
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.7
 *
 * CONTEXTO:
 * Generar un reporte siempre sigue los MISMOS pasos:
 * 1. Conectar a la fuente de datos.
 * 2. Extraer datos.
 * 3. Formatear datos.
 * 4. Exportar al formato final.
 * 5. Cerrar conexión.
 *
 * El Template Method define el esqueleto (orden de pasos) y permite
 * que las subclases personalicen cada paso sin cambiar la estructura.
 *
 * Subclases: ReporteCSV, ReporteHTML, ReporteJSON.
 *
 * RESTRICCIONES:
 * - La clase padre tiene el templateMethod generar() como FINAL.
 * - Los pasos abstractos (formatear, exportar) los implementan las subclases.
 * - Hook opcional: agregarEncabezado() — por defecto vacío.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio86_TemplateMethodReportes {

    // TODO 1: Implementar clase abstracta GeneradorReporte:
    //         public final void generar(String fuente):
    //           conectar(fuente) → extraerDatos() → hook agregarEncabezado() →
    //           formatear() → exportar() → desconectar()
    //         Pasos concretos: conectar(), desconectar() (igual para todos).
    //         Pasos abstractos: extraerDatos(), formatear(), exportar().
    //         Hook: agregarEncabezado() — vacío por defecto.

    // TODO 2: Implementar ReporteCSV:
    //         formatear() → "Formateando a CSV: campo1,campo2,campo3..."
    //         exportar() → "Exportando reporte.csv"

    // TODO 3: Implementar ReporteHTML:
    //         formatear() → "Formateando a HTML: <table>...</table>"
    //         exportar() → "Exportando reporte.html"
    //         agregarEncabezado() → "Añadiendo cabecera HTML con estilos"

    // TODO 4: Implementar ReporteJSON:
    //         formatear() → "Formateando a JSON: {\"datos\": [...]}"
    //         exportar() → "Exportando reporte.json"

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 86: Template Method Reportes ===\n");

        // TODO 5:
        //         GeneradorReporte[] reportes = {
        //             new ReporteCSV(), new ReporteHTML(), new ReporteJSON()
        //         };
        //         for (GeneradorReporte r : reportes) {
        //             r.generar("base_datos_ventas");
        //             System.out.println("---");
        //         }

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 86 ===");
    }
}
