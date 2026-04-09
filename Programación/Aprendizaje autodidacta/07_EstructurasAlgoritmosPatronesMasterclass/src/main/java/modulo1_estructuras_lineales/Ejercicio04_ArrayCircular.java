package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 04: Array Circular (Ring Buffer)
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.6
 *
 * CONTEXTO:
 * Un Ring Buffer (buffer circular) es un array de tamaño fijo que reutiliza
 * posiciones mediante aritmética modular. Cuando el puntero llega al final
 * del array, "da la vuelta" al principio. Es la base de las colas circulares
 * y se usa extensamente en sistemas de streaming, audio y networking.
 *
 * Implementa un RingBuffer de enteros con capacidad fija:
 * - write(int valor): escribe un valor en la posición del puntero de escritura.
 * - read(): lee y retira el valor más antiguo (puntero de lectura).
 * - isFull(): ¿está lleno?
 * - isEmpty(): ¿está vacío?
 * - size(): número de elementos almacenados actualmente.
 *
 * RESTRICCIONES:
 * - Capacidad fija (no crece). Se pasa por constructor.
 * - Usar aritmética modular: (indice + 1) % capacity.
 * - Mantener dos punteros: 'readPos' y 'writePos'.
 * - Si se intenta escribir en un buffer lleno, lanzar IllegalStateException.
 * - Si se intenta leer de un buffer vacío, lanzar IllegalStateException.
 *
 * COMPLEJIDAD OBJETIVO:
 * - write(): O(1)
 * - read():  O(1)
 * ============================================================================
 */
public class Ejercicio04_ArrayCircular {

    private int[] buffer;
    private int readPos;
    private int writePos;
    private int count;
    private int capacity;

    public Ejercicio04_ArrayCircular(int capacity) {
        // TODO 1: Inicializar el array 'buffer' con la capacidad recibida.
        //         Inicializar readPos, writePos y count a 0.
        //         Almacenar la capacidad en el campo 'capacity'.
    }

    public void write(int valor) {
        // TODO 2: Verificar si el buffer está lleno. Si lo está, lanzar
        //         IllegalStateException("Buffer lleno").
        //         Escribir 'valor' en buffer[writePos].
        //         Avanzar writePos con aritmética modular: (writePos + 1) % capacity.
        //         Incrementar count.
    }

    public int read() {
        // TODO 3: Verificar si el buffer está vacío. Si lo está, lanzar
        //         IllegalStateException("Buffer vacío").
        //         Leer el valor de buffer[readPos].
        //         Avanzar readPos con aritmética modular: (readPos + 1) % capacity.
        //         Decrementar count. Retornar el valor leído.
        return -1;
    }

    public boolean isFull() {
        // TODO 4: Retornar true si count es igual a capacity.
        return false;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        // TODO 5: Construir una representación visual del buffer circular.
        //         Recorrer desde readPos 'count' veces usando aritmética modular
        //         para mostrar los elementos en orden lógico (del más antiguo al más nuevo).
        //         Formato: "RingBuffer[A, B, C] (size=3/cap=5)"
        return "RingBuffer[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 04: Array Circular (Ring Buffer) ===\n");

        Ejercicio04_ArrayCircular ring = new Ejercicio04_ArrayCircular(5);

        ring.write(10);
        ring.write(20);
        ring.write(30);
        System.out.println("Después de escribir 10,20,30: " + ring);

        int leido1 = ring.read();
        int leido2 = ring.read();
        System.out.println("read() = " + leido1 + ", read() = " + leido2); // 10, 20

        ring.write(40);
        ring.write(50);
        ring.write(60);
        System.out.println("Tras más escrituras: " + ring);

        System.out.println("size()=" + ring.size() + ", isFull()=" + ring.isFull());

        // Probar overflow
        try {
            ring.write(70);
            ring.write(80); // Debería fallar si está lleno
        } catch (IllegalStateException e) {
            System.out.println("✅ Buffer lleno: " + e.getMessage());
        }

        // Vaciar completamente
        while (!ring.isEmpty()) {
            System.out.println("  read() = " + ring.read());
        }

        // Probar underflow
        try {
            ring.read();
        } catch (IllegalStateException e) {
            System.out.println("✅ Buffer vacío: " + e.getMessage());
        }

        System.out.println("\n=== FIN EJERCICIO 04 ===");
    }
}
