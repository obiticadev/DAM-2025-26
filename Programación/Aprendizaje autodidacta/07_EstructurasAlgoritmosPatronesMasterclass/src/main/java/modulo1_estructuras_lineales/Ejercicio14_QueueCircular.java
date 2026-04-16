package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 14: Queue Circular (Circular Buffer Queue)
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.6
 *
 * CONTEXTO:
 * El Ejercicio 13 tenía un problema: dequeue() era O(n) porque desplazaba
 * todos los elementos. La solución elegante es una Queue Circular que usa
 * aritmética modular para "dar la vuelta" al array sin mover nada.
 *
 * Dos punteros controlan la cola:
 * - 'front': apunta al primer elemento (para dequeue).
 * - 'rear': apunta a la SIGUIENTE posición vacía (para enqueue).
 * - Avance circular: (puntero + 1) % capacity.
 *
 * Tu QueueCircular debe soportar:
 * - enqueue(int valor): insertar en rear.
 * - dequeue(): retirar de front.
 * - peek(): ver front sin retirarlo.
 * - isFull(), isEmpty(), size(), toString().
 *
 * RESTRICCIONES:
 * - Capacidad fija pasada por constructor (no crece).
 * - Internamente, crear un array de tamaño capacity + 1 para distinguir
 *   entre cola llena y vacía sin usar un contador. Alternativa: usar
 *   un campo 'count' para rastrear el tamaño (elige la que prefieras).
 * - Sin java.util.
 *
 * COMPLEJIDAD OBJETIVO:
 * - enqueue(): O(1) ✅
 * - dequeue(): O(1) ✅ (ya no necesita desplazamiento)
 * - peek():    O(1)
 * ============================================================================
 */
public class Ejercicio14_QueueCircular {

    private int[] datos;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Ejercicio14_QueueCircular(int capacity) {
        // TODO 1: Inicializar el array 'datos' con el tamaño de 'capacity'.
        //         Inicializar front, rear y count a 0.
        //         Almacenar capacity.
    }

    public void enqueue(int valor) {
        // TODO 2: Si la cola está llena (count == capacity), lanzar
        //         IllegalStateException("Cola circular llena").
        //         Colocar el valor en datos[rear].
        //         Avanzar rear circularmente: rear = (rear + 1) % capacity.
        //         Incrementar count.
    }

    public int dequeue() {
        // TODO 3: Si la cola está vacía (count == 0), lanzar
        //         IllegalStateException("Cola circular vacía").
        //         Guardar datos[front] en una variable temporal.
        //         Avanzar front circularmente: front = (front + 1) % capacity.
        //         Decrementar count. Retornar el valor.
    	return -1;
    }

    public int peek() {
        // TODO 4: Si está vacía, lanzar excepción.
        //         Retornar datos[front] sin mover front.
        return -1;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        // TODO 5: Construir una representación visual recorriendo los elementos
        //         en orden lógico desde front, avanzando circularmente 'count' veces.
        //         Formato: "CircularQueue[FRONT → 10, 20, 30 ← REAR] (3/5)"
        //         donde 3/5 indica size/capacity.
        //         Bucle: for (int i = 0; i < count; i++)
        //                  posición real = (front + i) % capacity
        return "CircularQueue[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 14: Queue Circular ===\n");

        Ejercicio14_QueueCircular cola = new Ejercicio14_QueueCircular(5);

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        System.out.println("enqueue(10,20,30): " + cola);

        System.out.println("dequeue() = " + cola.dequeue()); // 10
        System.out.println("dequeue() = " + cola.dequeue()); // 20

        cola.enqueue(40);
        cola.enqueue(50);
        cola.enqueue(60);
        System.out.println("enqueue(40,50,60): " + cola);

        // El wrap-around debería haber ocurrido aquí
        System.out.println("size()   = " + cola.size());    // 4
        System.out.println("isFull() = " + cola.isFull());  // false

        cola.enqueue(70);
        System.out.println("enqueue(70) → isFull() = " + cola.isFull()); // true

        try {
            cola.enqueue(80);
        } catch (IllegalStateException e) {
            System.out.println("✅ Cola llena: " + e.getMessage());
        }

        // Vaciar
        System.out.println("\nVaciando:");
        while (!cola.isEmpty()) {
            System.out.println("  dequeue() = " + cola.dequeue());
        }

        try {
            cola.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("✅ Cola vacía: " + e.getMessage());
        }

        System.out.println("\n=== FIN EJERCICIO 14 ===");
    }
}
