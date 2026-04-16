package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 100: PROYECTO FINAL — Sistema Integrador de Patrones GoF
 * ============================================================================
 * 
 * 🎓 MISIÓN FINAL: Diseña e implementa un SISTEMA DE GESTIÓN DE BIBLIOTECA
 * que integre al MENOS 10 de los 23 patrones GoF que has aprendido.
 *
 * ============================================================================
 * ESCENARIO:
 * Una biblioteca digital necesita gestionar libros, usuarios, préstamos,
 * notificaciones y reportes. Tu diseño debe demostrar maestría usando
 * múltiples patrones trabajando en armonía.
 *
 * ============================================================================
 * PATRONES SUGERIDOS (usa al menos 10):
 *
 * CREACIONALES:
 * - Singleton: CatalogoBiblioteca (catálogo único global).
 * - Builder: LibroBuilder (construir libros con muchos campos opcionales).
 * - Prototype: clonar fichas de libros para ediciones nuevas.
 *
 * ESTRUCTURALES:
 * - Facade: BibliotecaFacade → prestar(), devolver(), buscar().
 * - Decorator: LibroDecorado → LibroConResena, LibroConCalificacion.
 * - Composite: Secciones y subsecciones de la biblioteca (árbol).
 * - Proxy: AccesoLibroProxy → verificar membresía antes de prestar.
 * - Adapter: integrar catálogo legacy de otra biblioteca.
 *
 * COMPORTAMIENTO:
 * - Observer: NotificadorPrestamos → avisar vencimientos.
 * - Strategy: EstrategiaBusqueda → por título, autor, ISBN, género.
 * - Command: AccionPrestamo, AccionDevolucion (con undo).
 * - State: EstadoLibro → Disponible, Prestado, Reservado, EnReparacion.
 * - Iterator: recorrer el catálogo por diferentes criterios.
 * - Template Method: GeneradorReporte (PDF, Texto, etc.).
 * - Chain of Responsibility: SolicitudPrestamo → Verificar membresía →
 *   Verificar disponibilidad → Verificar límite préstamos → Aprobar.
 * - Memento: historial de cambios en el catálogo.
 *
 * ============================================================================
 * CLASES A IMPLEMENTAR:
 *
 * 1. Libro: isbn, titulo, autor, genero, estado (State).
 * 2. Usuario: nombre, tipo (ESTUDIANTE, PROFESOR, VISITANTE), préstamos activos.
 * 3. Prestamo: libro, usuario, fechaPrestamo, fechaDevolucion.
 * 4. CatalogoBiblioteca (Singleton): almacena todos los libros.
 * 5. BibliotecaFacade: interfaz simplificada para el sistema.
 * 6. Al menos 8 clases más implementando los patrones.
 *
 * ============================================================================
 * RESTRICCIONES:
 * - Cada patrón utilizado debe estar CLARAMENTE documentado con un comentario
 *   indicando qué patrón es y por qué se usa en ese contexto.
 * - El main() debe demostrar un flujo completo:
 *   1. Crear la biblioteca con libros.
 *   2. Registrar usuarios.
 *   3. Realizar préstamos y devoluciones.
 *   4. Buscar libros con diferentes estrategias.
 *   5. Recibir notificaciones.
 *   6. Generar un reporte.
 *   7. Demostrar undo de un préstamo.
 * - Sin java.util (excepto Math si necesario).
 * ============================================================================
 *
 * 🏆 Este es el ejercicio CULMINANTE. Demuestra todo lo que has aprendido
 * en 99 ejercicios anteriores. ¡Buena suerte, Maestro del Diseño!
 *
 * ============================================================================
 */
public class Ejercicio100_ProyectoFinalBiblioteca {

    // ==========================================================
    // PASO 1: DEFINE TUS INTERFACES Y CLASES CORE
    // ==========================================================

    // TODO 1: Interfaz/Clase Libro con campos: isbn, titulo, autor, genero.
    //         Patrón State: EstadoLibro (Disponible, Prestado, Reservado, EnReparacion).

    // TODO 2: Clase Usuario con nombre, tipo, listaPrestamoActivos.

    // TODO 3: Clase Prestamo: libro, usuario, fechas.

    // ==========================================================
    // PASO 2: PATRONES CREACIONALES
    // ==========================================================

    // TODO 4: Singleton — CatalogoBiblioteca: catálogo único de todos los libros.
    //         Array Libro[] con agregar, buscar, eliminar.

    // TODO 5: Builder — LibroBuilder con Fluent API:
    //         new LibroBuilder().isbn("978-84-...").titulo("Don Quijote")
    //                           .autor("Cervantes").genero("Novela").build();

    // ==========================================================
    // PASO 3: PATRONES ESTRUCTURALES
    // ==========================================================

    // TODO 6: Facade — BibliotecaFacade: prestar(isbn, userId), devolver(isbn),
    //         buscar(criterio), generarReporte().

    // TODO 7: Decorator — LibroConResena: envuelve Libro y añade reseñas.

    // TODO 8: Proxy — PrestamoProxy: verifica membresía antes de prestar.

    // ==========================================================
    // PASO 4: PATRONES DE COMPORTAMIENTO
    // ==========================================================

    // TODO 9: Observer — NotificadorEventos: notifica préstamos/devoluciones.

    // TODO 10: Strategy — EstrategiaBusqueda: buscar por título, autor, género.

    // TODO 11: Command — PrestamoCommand / DevolucionCommand con undo.

    // TODO 12: State — EstadoLibro con transiciones.

    // TODO 13: Iterator — IteradorCatalogo para recorrer libros.

    // TODO 14: Chain — CadenaPrestamo: verificar membresía → disponibilidad → límite.

    // ==========================================================
    // PASO 5: DEMOSTRACIÓN COMPLETA
    // ==========================================================

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   EJERCICIO 100: PROYECTO FINAL BIBLIOTECA  ║");
        System.out.println("║   🏆 Masterclass Completa — 23 Patrones GoF ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // TODO 15: Flujo completo:
        //
        // --- Inicialización (Singleton + Builder) ---
        // CatalogoBiblioteca catalogo = CatalogoBiblioteca.getInstance();
        // catalogo.agregar(new LibroBuilder()
        //     .isbn("978-1").titulo("Don Quijote").autor("Cervantes")
        //     .genero("Novela").build());
        // catalogo.agregar(new LibroBuilder()
        //     .isbn("978-2").titulo("Clean Code").autor("Robert C. Martin")
        //     .genero("Técnico").build());
        //
        // --- Registrar usuarios ---
        // Usuario alice = new Usuario("Alice", TipoUsuario.PROFESOR);
        // Usuario bob = new Usuario("Bob", TipoUsuario.ESTUDIANTE);
        //
        // --- Préstamos (Facade + Command + Chain + Observer + State) ---
        // BibliotecaFacade bib = new BibliotecaFacade(catalogo);
        // bib.prestar("978-1", alice);  // Cambia estado: Disponible → Prestado
        // bib.prestar("978-2", bob);
        //
        // --- Buscar (Strategy) ---
        // bib.buscar(new BuscarPorAutor("Cervantes"));
        //
        // --- Iterar catálogo (Iterator) ---
        // IteradorCatalogo it = catalogo.crearIterador();
        // while (it.hasNext()) { ... }
        //
        // --- Devolver (Command + undo) ---
        // bib.devolver("978-1");
        //
        // --- Reporte (Template Method) ---
        // bib.generarReporte("texto");
        //
        // --- Undo del último préstamo ---
        // bib.undo();

        System.out.println("==================================================");
        System.out.println("  Este ejercicio es tu proyecto FINAL.");
        System.out.println("  Implementa los TODOs uno por uno.");
        System.out.println("  Cada patrón refuerza los 99 ejercicios anteriores.");
        System.out.println("  ¡Demuestra tu maestría en Java y Diseño de SW!");
        System.out.println("==================================================");

        System.out.println("\n=== FIN EJERCICIO 100 — ¡FELICIDADES, MAESTRO! ===");
    }
}
