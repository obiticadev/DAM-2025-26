package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 02: Insertar en Posición con Desplazamiento
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.3
 *
 * CONTEXTO:
 * Añadir al final de un array es trivial, pero insertar en una posición
 * arbitraria requiere desplazar todos los elementos posteriores una posición
 * a la derecha para abrir hueco. Esta operación es O(n) en el peor caso.
 *
 * Construye un array dinámico que soporte inserción posicional:
 * - insertAt(int indice, int elemento): inserta en la posición dada.
 * - add(int elemento): inserta al final (delegando en insertAt).
 * - get(int indice): accede por índice.
 * - toString(): representación visual del contenido.
 *
 * RESTRICCIONES:
 * - Sin usar java.util.* (ni ArrayList, ni Arrays.copyOf).
 * - Desplazamiento manual con bucle descendente (de derecha a izquierda).
 * - Capacidad inicial de 4, redimensionar al doble cuando se llene.
 *
 * COMPLEJIDAD OBJETIVO:
 * - insertAt(): O(n) peor caso (desplazamiento)
 * - add():      O(1) amortizado
 * - get():      O(1)
 * ============================================================================
 */
public class Ejercicio02_InsertarEnPosicion {

    private int[] datos;
    private int size;

    public Ejercicio02_InsertarEnPosicion() {
        this.datos = new int[4];
        this.size = 0;
    }

    public void insertAt(int indice, int elemento) {
        // TODO 1: Validar que el índice esté en el rango [0, size] (inclusive size
        //         para permitir inserción al final). Lanzar IndexOutOfBoundsException
        //         si no cumple.

        // TODO 2: Comprobar si el array necesita redimensionarse (size == datos.length).
        //         Si es así, crear un nuevo array con el doble de capacidad y copiar
        //         los elementos existentes.

        // TODO 3: Desplazar todos los elementos desde 'size-1' hasta 'indice'
        //         una posición a la derecha (bucle descendente: i = size; i > indice; i--).
        //         Luego colocar el nuevo elemento en datos[indice] e incrementar size.
    }

    public void add(int elemento) {
        // TODO 4: Delegar en insertAt pasando 'size' como índice (inserción al final).
    }

    public int get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Índice " + indice + " fuera de rango. Size=" + size);
        }
        return datos[indice];
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        // TODO 5: Construir un String con el formato "[10, 20, 30]" recorriendo
        //         solo los primeros 'size' elementos (no toda la capacidad).
        //         Usar StringBuilder para eficiencia.
        return "[]";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 02: Insertar en Posición ===\n");

        Ejercicio02_InsertarEnPosicion arr = new Ejercicio02_InsertarEnPosicion();

        arr.add(10);
        arr.add(20);
        arr.add(30);
        arr.add(40);
        System.out.println("Después de add(10,20,30,40): " + arr);  // [10, 20, 30, 40]

        arr.insertAt(2, 25);
        System.out.println("Después de insertAt(2, 25):  " + arr);  // [10, 20, 25, 30, 40]

        arr.insertAt(0, 5);
        System.out.println("Después de insertAt(0, 5):   " + arr);  // [5, 10, 20, 25, 30, 40]

        arr.insertAt(arr.size(), 50);
        System.out.println("Después de insertAt(final):  " + arr);  // [5, 10, 20, 25, 30, 40, 50]

        System.out.println("Tamaño: " + arr.size());                // 7

        try {
            arr.insertAt(100, 999);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n✅ Excepción capturada: " + e.getMessage());
        }

        System.out.println("\n=== FIN EJERCICIO 02 ===");
    }
}
