package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 46: Singleton — Variantes de Implementación
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.2
 *
 * CONTEXTO:
 * El Singleton garantiza una única instancia de una clase. Implementarás
 * TRES variantes clásicas y compararás sus características.
 *
 * Variantes a implementar:
 * 1. EagerSingleton: instancia creada al cargar la clase (estática final).
 * 2. LazySingleton: instancia creada en el primer acceso (NO thread-safe).
 * 3. ThreadSafeSingleton: Double-Check Locking con volatile.
 *
 * Cada singleton simula un Logger del sistema con un contador de mensajes.
 *
 * RESTRICCIONES:
 * - Constructor PRIVADO en las tres variantes.
 * - El método estático getInstance() es el ÚNICO punto de acceso.
 * - La variante ThreadSafe DEBE usar volatile + synchronized.
 * - Demostrar que múltiples llamadas a getInstance() retornan el MISMO objeto.
 * ============================================================================
 */
public class Ejercicio46_SingletonVariantes {

    // ====================================================================
    //  VARIANTE 1: Eager Initialization
    // ====================================================================
    static class EagerSingleton {
        // TODO 1: Declarar un campo estático FINAL con la instancia ya creada:
        //         private static final EagerSingleton INSTANCIA = new EagerSingleton();
        //         Constructor privado que imprima "[EagerSingleton] Instancia creada".
        //         Método estático getInstance() que retorne INSTANCIA.
        //         Método log(String mensaje) que imprima con un contador.
    }

    // ====================================================================
    //  VARIANTE 2: Lazy Initialization (NO Thread-Safe)
    // ====================================================================
    static class LazySingleton {
        // TODO 2: Declarar un campo estático (NO final) inicializado a null.
        //         Constructor privado.
        //         getInstance(): SI instancia es null → crear nueva instancia.
        //         Retornar instancia.
        //         NOTA: Esto NO es seguro en entornos multihilo (race condition).
    }

    // ====================================================================
    //  VARIANTE 3: Thread-Safe con Double-Check Locking
    // ====================================================================
    static class ThreadSafeSingleton {
        // TODO 3: Declarar el campo como: private static volatile ThreadSafeSingleton instancia;
        //         Constructor privado.
        //         getInstance():
        //           if (instancia == null) {                    // Primer check (sin lock)
        //               synchronized (ThreadSafeSingleton.class) {
        //                   if (instancia == null) {            // Segundo check (con lock)
        //                       instancia = new ThreadSafeSingleton();
        //                   }
        //               }
        //           }
        //           return instancia;
        //         El 'volatile' evita reordenamiento de instrucciones del JIT.

        // TODO 4: Añadir un método resetear() (solo para testing) que ponga
        //         instancia a null dentro de un bloque synchronized.
        //         En producción NUNCA se resetea un Singleton.
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 46: Singleton Variantes ===\n");

        // TODO 5: Para cada variante:
        //         1. Obtener getInstance() dos veces.
        //         2. Verificar que ambas referencias apuntan al MISMO objeto (ref1 == ref2).
        //         3. Llamar a log() con un mensaje para demostrar que funciona.
        //         4. Imprimir los hashCodes para verificar identidad.

        System.out.println("--- Eager Singleton ---");
        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n--- Lazy Singleton ---");
        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n--- Thread-Safe Singleton ---");
        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 46 ===");
    }
}
