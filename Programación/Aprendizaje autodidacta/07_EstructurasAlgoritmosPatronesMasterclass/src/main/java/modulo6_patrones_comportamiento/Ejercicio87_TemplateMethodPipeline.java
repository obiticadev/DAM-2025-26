package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 87: Template Method — Pipeline de Procesamiento
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.7
 *
 * CONTEXTO:
 * Un pipeline de procesamiento de datos siempre sigue esta estructura:
 * leer → validar → transformar → cargar. Cada etapa varía según el tipo
 * de dato (CSV, XML, API REST simulada).
 *
 * Implementa:
 * - Clase abstracta DataPipeline con templateMethod procesar().
 * - Subclases: CSVPipeline, XMLPipeline, APIPipeline.
 * - Hook: logProgreso() — por defecto imprime porcentaje simulado.
 *
 * RESTRICCIONES:
 * - procesar() es final, define el esqueleto.
 * - Los datos se representan como String[][] (tabla).
 * - validar() puede rechazar filas inválidas.
 * - transformar() modifica los datos (ej: normalizar, convertir).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio87_TemplateMethodPipeline {

    // TODO 1: Implementar clase abstracta DataPipeline:
    //         public final void procesar():
    //           datos = leer() → logProgreso(25) → validar(datos) →
    //           logProgreso(50) → transformar(datos) → logProgreso(75) →
    //           cargar(datos) → logProgreso(100)
    //         Abstractos: String[][] leer(), String[][] validar(String[][]),
    //                     String[][] transformar(String[][]), void cargar(String[][]).
    //         Hook: logProgreso(int porcentaje) con implementación por defecto.

    // TODO 2: Implementar CSVPipeline:
    //         leer() → simula leer CSV, retorna datos hardcodeados.
    //         validar() → filtra filas con campos vacíos.
    //         transformar() → convierte nombres a mayúsculas.
    //         cargar() → imprime los datos finales.

    // TODO 3: Implementar XMLPipeline (similar pero con mensajes XML).

    // TODO 4: Implementar APIPipeline:
    //         leer() → simula llamada a API REST.
    //         transformar() → mapea campos JSON a modelo interno.
    //         logProgreso() sobreescrito para mayor detalle.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 87: Template Method Pipeline ===\n");

        // TODO 5:
        //         new CSVPipeline().procesar();
        //         System.out.println("===");
        //         new APIPipeline().procesar();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 87 ===");
    }
}
