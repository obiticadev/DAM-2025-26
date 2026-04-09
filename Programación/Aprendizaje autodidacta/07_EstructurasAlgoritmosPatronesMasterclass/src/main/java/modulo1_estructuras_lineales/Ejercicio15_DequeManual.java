package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 15: Deque Manual (Double-Ended Queue)
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.7
 *
 * CONTEXTO:
 * El Deque es la estructura lineal más versátil: permite insertar y retirar
 * por AMBOS extremos en O(1). Es la fusión de un Stack y una Queue.
 * Internamente lo implementarás con un array circular (igual que el Ej.14)
 * pero con operaciones en ambos lados.
 *
 * Tu MiDeque debe soportar:
 * - addFirst(int valor): insertar por el frente.
 * - addLast(int valor): insertar por el final.
 * - removeFirst(): retirar del frente.
 * - removeLast(): retirar del final.
 * - peekFirst(), peekLast(): ver extremos sin retirar.
 * - isEmpty(), size(), toString().
 *
 * RESTRICCIONES:
 * - Array circular con capacidad fija (pasada por constructor).
 * - Dos punteros: 'front' y 'rear'.
 * - Para addFirst, 'front' retrocede circularmente:
 *   front = (front - 1 + capacity) % capacity.
 * - Para addLast, 'rear' avanza circularmente:
 *   rear = (rear + 1) % capacity.
 * - Mantener un campo 'count' para rastrear el tamaño.
 * - Sin java.util.
 *
 * COMPLEJIDAD OBJETIVO:
 * - Todas las operaciones: O(1)
 * ============================================================================
 */
public class Ejercicio15_DequeManual {

    private int[] datos;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public Ejercicio15_DequeManual(int capacity) {
        // TODO 1: Inicializar el array 'datos' con el tamaño de 'capacity'.
        //         Inicializar front a 0, rear a 0, count a 0.
        //         Almacenar capacity.
        //         NOTA: 'front' apunta al primer elemento.
        //               'rear' apunta a la SIGUIENTE posición libre tras el último.
    }

    public void addFirst(int valor) {
        // TODO 2: Si está lleno, lanzar IllegalStateException("Deque lleno").
        //         Retroceder front circularmente: front = (front - 1 + capacity) % capacity.
        //         Colocar el valor en datos[front].
        //         Incrementar count.
        //         CLAVE: Se retrocede ANTES de insertar porque front apunta
        //         al primer elemento existente.
    }

    public void addLast(int valor) {
        // TODO 3: Si está lleno, lanzar IllegalStateException("Deque lleno").
        //         Colocar el valor en datos[rear].
        //         Avanzar rear circularmente: rear = (rear + 1) % capacity.
        //         Incrementar count.
    }

    public int removeFirst() {
        // TODO 4: Si está vacío, lanzar IllegalStateException("Deque vacío").
        //         Guardar datos[front].
        //         Avanzar front circularmente: front = (front + 1) % capacity.
        //         Decrementar count. Retornar el valor.
        return -1;
    }

    public int removeLast() {
        // TODO 5: Si está vacío, lanzar IllegalStateException("Deque vacío").
        //         Retroceder rear circularmente: rear = (rear - 1 + capacity) % capacity.
        //         Guardar datos[rear] (la nueva posición de rear ES el último elemento).
        //         Decrementar count. Retornar el valor.
        return -1;
    }

    public int peekFirst() {
        if (count == 0) throw new IllegalStateException("Deque vacío");
        return datos[front];
    }

    public int peekLast() {
        if (count == 0) throw new IllegalStateException("Deque vacío");
        return datos[(rear - 1 + capacity) % capacity];
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull()  { return count == capacity; }
    public int size()        { return count; }

    @Override
    public String toString() {
        // TODO 6 (BONUS): Construir representación visual recorriendo los
        //         elementos en orden lógico desde front, avanzando circularmente:
        //         "Deque[FIRST → 5, 10, 20, 30 ← LAST] (4/8)"
        return "Deque[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 15: Deque Manual ===\n");

        Ejercicio15_DequeManual deque = new Ejercicio15_DequeManual(6);

        // Usar como Queue (FIFO)
        System.out.println("--- Uso como Queue (FIFO) ---");
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        System.out.println("addLast(10,20,30): " + deque);
        System.out.println("removeFirst() = " + deque.removeFirst()); // 10
        System.out.println("removeFirst() = " + deque.removeFirst()); // 20
        System.out.println("Queda: " + deque);                        // 30

        // Limpiar
        deque.removeFirst();

        // Usar como Stack (LIFO)
        System.out.println("\n--- Uso como Stack (LIFO) ---");
        deque.addLast(100);
        deque.addLast(200);
        deque.addLast(300);
        System.out.println("addLast(100,200,300): " + deque);
        System.out.println("removeLast() = " + deque.removeLast()); // 300
        System.out.println("removeLast() = " + deque.removeLast()); // 200
        System.out.println("Queda: " + deque);                      // 100

        // Limpiar
        deque.removeFirst();

        // Uso mixto (inserción por ambos lados)
        System.out.println("\n--- Uso mixto ---");
        deque.addFirst(5);
        deque.addLast(10);
        deque.addFirst(1);
        deque.addLast(20);
        System.out.println("addFirst(5), addLast(10), addFirst(1), addLast(20): " + deque);
        System.out.println("peekFirst() = " + deque.peekFirst()); // 1
        System.out.println("peekLast()  = " + deque.peekLast());  // 20
        System.out.println("size()      = " + deque.size());      // 4

        System.out.println("\n=== FIN EJERCICIO 15 ===");
    }
}
