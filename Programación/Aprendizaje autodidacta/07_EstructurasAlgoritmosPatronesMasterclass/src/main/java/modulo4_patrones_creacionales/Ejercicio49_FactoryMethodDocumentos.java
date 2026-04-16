package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 49: Factory Method — Fábrica de Documentos
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.3
 *
 * CONTEXTO:
 * Una aplicación de oficina genera diferentes tipos de documentos: PDF, Word
 * y Excel. Cada documento tiene un formato distinto pero comparten una
 * interfaz común. Factory Method permite que cada "creador" (fábrica)
 * produzca su tipo específico de documento.
 *
 * Arquitectura:
 * - Interfaz Documento: abrir(), guardar(), cerrar().
 * - Clases concretas: DocumentoPDF, DocumentoWord, DocumentoExcel.
 * - Clase abstracta CreadorDocumento: con factoryMethod() abstracto.
 * - Creadores concretos: CreadorPDF, CreadorWord, CreadorExcel.
 *
 * RESTRICCIONES:
 * - La interfaz Documento define los métodos comunes.
 * - Cada CreadorConcreto implementa factoryMethod() retornando su producto.
 * - El cliente NUNCA hace new DocumentoPDF() directamente, siempre pide
 *   al creador correspondiente.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio49_FactoryMethodDocumentos {

    // TODO 1: Definir la interfaz Documento con métodos:
    //         String abrir() — retorna mensaje describiendo la apertura.
    //         String guardar() — retorna mensaje describiendo el guardado.
    //         String cerrar() — retorna mensaje describiendo el cierre.
    //         String getTipo() — retorna el tipo de documento.

    // TODO 2: Implementar tres clases concretas:
    //         DocumentoPDF: abrir→"Abriendo visor PDF", guardar→"Renderizando PDF...",
    //                       cerrar→"Liberando recursos PDF", getTipo→"PDF"
    //         DocumentoWord: abrir→"Iniciando editor de texto",
    //                        guardar→"Serializando formato .docx...", etc.
    //         DocumentoExcel: abrir→"Cargando motor de cálculo",
    //                         guardar→"Generando hoja de cálculo...", etc.

    // TODO 3: Definir la clase abstracta CreadorDocumento:
    //         Método abstracto: Documento crearDocumento() — el Factory Method.
    //         Método concreto: Documento nuevoDocumento() que:
    //           1. Llama a crearDocumento() (delega la creación).
    //           2. Imprime: "[Factory] Documento <tipo> creado."
    //           3. Retorna el documento.

    // TODO 4: Implementar tres creadores concretos:
    //         CreadorPDF: crearDocumento() retorna new DocumentoPDF().
    //         CreadorWord: crearDocumento() retorna new DocumentoWord().
    //         CreadorExcel: crearDocumento() retorna new DocumentoExcel().

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 49: Factory Method Documentos ===\n");

        // TODO 5: Crear los tres creadores.
        //         Para cada uno, invocar nuevoDocumento().
        //         Luego invocar abrir(), guardar(), cerrar() en cada documento.
        //
        //         El CLIENTE no necesita saber qué clase concreta se instanció.
        //         Solo trabaja con la interfaz Documento.
        //
        //         Ejemplo:
        //         CreadorDocumento creador = new CreadorPDF();
        //         Documento doc = creador.nuevoDocumento();
        //         System.out.println(doc.abrir());
        //         System.out.println(doc.guardar());
        //         System.out.println(doc.cerrar());

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 49 ===");
    }
}
