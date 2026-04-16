package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 74: Combinación — Adapter + Decorator para Streams
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md
 *
 * CONTEXTO:
 * Los patrones no viven aislados. En la práctica se combinan. Este ejercicio
 * combina Adapter y Decorator para crear un pipeline de procesamiento de texto.
 *
 * Escenario: Tienes un sistema legacy con interfaz TextoLegacy (getTexto(),
 * setTexto()) y un sistema moderno con interfaz StreamModerno (leer(), escribir()).
 * Necesitas:
 * 1. Un Adapter para que TextoLegacy funcione como StreamModerno.
 * 2. Decoradores que transforman el texto en el stream:
 *    - UpperCaseDecorator: convierte a mayúsculas.
 *    - TrimDecorator: elimina espacios extra.
 *    - PrefixDecorator: añade un prefijo a cada lectura.
 *
 * Implementa ambos patrones trabajando juntos.
 *
 * RESTRICCIONES:
 * - El pipeline debe funcionar: Legacy → Adapter → Decorator(s) → Cliente.
 * - Los decoradores se apilan sobre el StreamModerno (adaptado o directo).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio74_AdapterDecoratorCombo {

    // ==== SISTEMA LEGACY ====
    // TODO 1: Definir interfaz TextoLegacy: String getTexto(), void setTexto(String t)
    //         Implementar AlmacenTextoLegacy: almacena un String.

    // ==== SISTEMA MODERNO ====
    // TODO 2: Definir interfaz StreamModerno: String leer(), void escribir(String dato)

    // ==== ADAPTER ====
    // TODO 3: Implementar LegacyToStreamAdapter:
    //         - Implementa StreamModerno, contiene TextoLegacy.
    //         - leer() → delega en legacy.getTexto()
    //         - escribir() → delega en legacy.setTexto()

    // ==== DECORADORES ====
    // TODO 4: Implementar StreamDecorator (abstracto) que envuelve StreamModerno.

    // TODO 5: Implementar UpperCaseDecorator:
    //         leer() → envuelto.leer().toUpperCase()
    //         escribir() → envuelto.escribir(dato.toUpperCase())

    // TODO 6: Implementar TrimDecorator:
    //         leer() → envuelto.leer().trim() y reemplazar múltiples espacios.

    // TODO 7: Implementar PrefixDecorator:
    //         leer() → "[PREFIJO] " + envuelto.leer()

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 74: Adapter + Decorator ===\n");

        // TODO 8: Construir el pipeline:
        //         AlmacenTextoLegacy legacy = new AlmacenTextoLegacy("  hola   mundo  ");
        //
        //         // Pipeline: Legacy → Adapter → Trim → UpperCase → Prefix
        //         StreamModerno stream = new PrefixDecorator(
        //                                  new UpperCaseDecorator(
        //                                    new TrimDecorator(
        //                                      new LegacyToStreamAdapter(legacy))));
        //
        //         System.out.println("Resultado: " + stream.leer());
        //         // Esperado: "[PREFIJO] HOLA MUNDO"
        //
        //         stream.escribir("  nuevos   datos   aquí  ");
        //         // El dato se transforma al escribir.
        //         System.out.println("Legacy almacena: " + legacy.getTexto());

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 74 ===");
    }
}
