package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 01: Array Dinámico Manual (MiArrayList)
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.3
 *
 * CONTEXTO:
 * Java te ofrece ArrayList listo para usar. Pero un ingeniero de verdad
 * debe conocer qué ocurre debajo del capó. En este ejercicio vas a construir
 * tu propia implementación de un array dinámico desde cero, sin usar
 * ninguna clase de java.util.
 *
 * Tu MiArrayList debe:
 * - Arrancar con una capacidad interna inicial de 4.
 * - Duplicar la capacidad automáticamente (x2) cuando se llena.
 * - Permitir agregar elementos al final, obtener por índice,
 *   consultar el tamaño lógico (size) y la capacidad real (capacity).
 *
 * RESTRICCIONES:
 * - Prohibido usar ArrayList, LinkedList o cualquier Collection de java.util.
 * - Solo arrays primitivos de Java (int[]).
 * - No se permite System.arraycopy() — copia manual con bucle.
 *
 * COMPLEJIDAD OBJETIVO:
 * - add():  O(1) amortizado
 * - get():  O(1)
 * - size(): O(1)
 * ============================================================================
 */
public class Ejercicio01_ArrayDinamicoManual {

    private int[] datos;
    private int size;

    public Ejercicio01_ArrayDinamicoManual() {
        // TODO 1: Inicializar el array interno 'datos' con una capacidad inicial de 4.
        //         Inicializar 'size' a 0 (no hay elementos lógicos aún).
    }

    public void add(int elemento) {
        // TODO 2: Comprobar si el array interno está lleno (size == datos.length).
        //         Si lo está, invocar el método privado 'redimensionar()' para
        //         expandir la capacidad al doble antes de insertar.
        //         Después, almacenar el elemento en la posición 'size' e incrementar size.
    }

    public int get(int indice) {
        // TODO 3: Validar que el índice esté dentro del rango lógico [0, size).
        //         Si no lo está, lanzar una IndexOutOfBoundsException con un mensaje
        //         descriptivo que indique el índice solicitado y el tamaño actual.
        //         Si es válido, retornar datos[indice].
        return -1;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        // TODO 4: Retornar la longitud real del array interno (datos.length),
        //         que representa la capacidad máxima antes de la próxima expansión.
        return -1;
    }

    private void redimensionar() {
        // TODO 5: Crear un nuevo array con el doble de longitud que 'datos'.
        //         Copiar manualmente (con un bucle for) cada elemento del array
        //         antiguo al nuevo. Reasignar la referencia 'datos' al nuevo array.
        //         Imprimir un mensaje de depuración indicando la expansión:
        //         "[RESIZE] Capacidad expandida de X a Y"
    }

    // ========================================================================
    //  ZONA DE PRUEBAS — Ejecuta esta clase directamente para verificar.
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 01: Array Dinámico Manual ===\n");

        Ejercicio01_ArrayDinamicoManual lista = new Ejercicio01_ArrayDinamicoManual();

        System.out.println("Capacidad inicial: " + lista.capacity());   // Esperado: 4
        System.out.println("Tamaño inicial:    " + lista.size());       // Esperado: 0

        // Insertar 10 elementos para forzar redimensionamientos
        for (int i = 1; i <= 10; i++) {
            lista.add(i * 10);
            System.out.printf("  add(%d) → size=%d, capacity=%d%n",
                    i * 10, lista.size(), lista.capacity());
        }

        System.out.println("\nContenido del array:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("  [%d] = %d%n", i, lista.get(i));
        }

        // Prueba de acceso fuera de rango
        try {
            lista.get(99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n✅ Excepción capturada: " + e.getMessage());
        }

        System.out.println("\n=== FIN EJERCICIO 01 ===");
    }
}
