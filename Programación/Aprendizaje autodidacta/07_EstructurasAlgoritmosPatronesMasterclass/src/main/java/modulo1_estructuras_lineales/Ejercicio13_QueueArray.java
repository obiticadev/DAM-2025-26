package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 13: Queue Manual con Array
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.6
 *
 * CONTEXTO:
 * Una Queue (Cola) es una estructura FIFO (First In, First Out). En esta
 * implementación básica usarás un array como soporte interno. Cada vez que
 * hagas dequeue(), desplazarás los elementos a la izquierda para compactar.
 *
 * NOTA: Esta implementación "ingenua" tiene dequeue() en O(n) por el
 * desplazamiento. En el Ejercicio 14 resolverás esto con una queue circular.
 *
 * Tu MiQueue debe soportar:
 * - enqueue(int valor): añadir al final.
 * - dequeue(): retirar del frente.
 * - peek(): ver el frente sin retirarlo.
 * - isEmpty(), size(), toString().
 *
 * RESTRICCIONES:
 * - Array interno con capacidad inicial de 8.
 * - Redimensionar al doble cuando se llene.
 * - dequeue() desplaza todos los elementos 1 posición a la izquierda.
 * - Sin java.util (ni Queue, ni LinkedList, ni ArrayDeque).
 *
 * COMPLEJIDAD OBJETIVO:
 * - enqueue(): O(1) amortizado
 * - dequeue(): O(n) — por el desplazamiento ❌ (lo mejoraremos en Ej.14)
 * - peek():    O(1)
 * ============================================================================
 */
public class Ejercicio13_QueueArray {

    private int[] datos;
    private int size;

    public Ejercicio13_QueueArray() {
        // TODO 1: Inicializar el array 'datos' con capacidad 8.
        //         Inicializar 'size' a 0.
    }

    public void enqueue(int valor) {
        // TODO 2: Si el array está lleno (size == datos.length), redimensionar
        //         al doble. Luego colocar el valor en datos[size] e incrementar size.
    }

    public int dequeue() {
        // TODO 3: Si la cola está vacía, lanzar IllegalStateException("Cola vacía").
        //         Guardar datos[0] (el frente) en una variable temporal.
        //         Desplazar todos los elementos una posición a la izquierda:
        //           datos[i] = datos[i + 1] para i desde 0 hasta size - 2.
        //         Decrementar size. Limpiar datos[size] = 0.
        //         Retornar el valor guardado.
        return -1;
    }

    public int peek() {
        // TODO 4: Si la cola está vacía, lanzar IllegalStateException.
        //         Retornar datos[0] sin modificar nada.
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void redimensionar() {
        int[] nuevo = new int[datos.length * 2];
        for (int i = 0; i < size; i++) {
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }

    @Override
    public String toString() {
        // TODO 5: Construir representación visual:
        //         "Queue[FRONT → 10, 20, 30 ← REAR]"
        //         Recorrer desde 0 hasta size - 1.
        return "Queue[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 13: Queue Manual con Array ===\n");

        Ejercicio13_QueueArray cola = new Ejercicio13_QueueArray();

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.enqueue(40);
        System.out.println("Después de enqueue(10,20,30,40): " + cola);

        System.out.println("peek()    = " + cola.peek());      // 10
        System.out.println("dequeue() = " + cola.dequeue());    // 10
        System.out.println("dequeue() = " + cola.dequeue());    // 20
        System.out.println("Después:  " + cola);                // 30, 40

        cola.enqueue(50);
        cola.enqueue(60);
        System.out.println("enqueue(50,60): " + cola);          // 30, 40, 50, 60

        System.out.println("size() = " + cola.size());          // 4

        // Vaciar la cola
        while (!cola.isEmpty()) {
            System.out.println("  dequeue() = " + cola.dequeue());
        }

        try {
            cola.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("✅ " + e.getMessage());
        }

        System.out.println("\n=== FIN EJERCICIO 13 ===");
    }
}
