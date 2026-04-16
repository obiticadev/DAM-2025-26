package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 35: HashMap Manual desde Cero
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.2
 *
 * CONTEXTO:
 * Un HashMap es una de las estructuras más utilizadas en la industria.
 * Internamente, es un array de "buckets". Cada clave se convierte en un
 * índice mediante una función hash. Si dos claves colisionan (mismo índice),
 * se almacenan en una lista enlazada dentro de ese bucket (chaining).
 *
 * Tu MiHashMap debe soportar:
 * - put(String clave, int valor): insertar o actualizar.
 * - get(String clave): obtener el valor asociado.
 * - remove(String clave): eliminar una entrada.
 * - containsKey(String clave): verificar existencia.
 * - size(): número de entries.
 *
 * RESTRICCIONES:
 * - Clase interna Entry con campos: clave, valor, siguiente (para chaining).
 * - Array de Entry[] como buckets (capacidad inicial 16).
 * - Función hash: Math.abs(clave.hashCode() % capacidad).
 * - Sin usar java.util.HashMap, Hashtable ni nada de java.util.
 *
 * COMPLEJIDAD OBJETIVO:
 * - put/get/remove: O(1) promedio, O(n) peor caso (todas colisionan).
 * ============================================================================
 */
public class Ejercicio35_HashMapManual {

    private static class Entry {
        String clave;
        int valor;
        Entry siguiente;

        Entry(String clave, int valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Entry[] buckets;
    private int size;
    private int capacidad;

    public Ejercicio35_HashMapManual() {
        this.capacidad = 16;
        this.buckets = new Entry[capacidad];
        this.size = 0;
    }

    private int hash(String clave) {
        // TODO 1: Calcular el índice del bucket para la clave dada.
        //         Usar: Math.abs(clave.hashCode() % capacidad).
        //         Si clave es null, retornar 0 (bucket especial para null).
        return 0;
    }

    public void put(String clave, int valor) {
        // TODO 2: Calcular el índice con hash(clave).
        //         Recorrer la lista enlazada en buckets[indice]:
        //           Si encuentras un Entry con la misma clave → actualizar su valor.
        //         Si no existe, crear un nuevo Entry y añadirlo al INICIO de la
        //         lista enlazada de ese bucket (inserción en cabeza, O(1)).
        //         Incrementar size.
    }

    public int get(String clave) {
        // TODO 3: Calcular el índice con hash(clave).
        //         Recorrer la lista enlazada en buckets[indice]:
        //           Si encuentras un Entry con la misma clave → retornar su valor.
        //         Si no existe, lanzar RuntimeException("Clave no encontrada: " + clave).
        return -1;
    }

    public boolean containsKey(String clave) {
        // TODO 4: Similar a get pero retorna boolean en vez de lanzar excepción.
        //         Recorrer el bucket correspondiente buscando la clave.
        return false;
    }

    public boolean remove(String clave) {
        // TODO 5: Calcular el índice. Recorrer la lista enlazada con un puntero
        //         'previo' para poder reconectar la cadena al eliminar.
        //         Si el Entry a eliminar es el primero del bucket:
        //           buckets[indice] = entry.siguiente.
        //         Si está en medio o al final:
        //           previo.siguiente = entry.siguiente.
        //         Decrementar size. Retornar true si se eliminó, false si no existía.
        return false;
    }

    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MiHashMap {\n");
        for (int i = 0; i < capacidad; i++) {
            if (buckets[i] != null) {
                sb.append("  [").append(i).append("]: ");
                Entry actual = buckets[i];
                while (actual != null) {
                    sb.append(actual.clave).append("=").append(actual.valor);
                    if (actual.siguiente != null) sb.append(" → ");
                    actual = actual.siguiente;
                }
                sb.append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 35: HashMap Manual ===\n");

        Ejercicio35_HashMapManual mapa = new Ejercicio35_HashMapManual();

        mapa.put("Alice", 25);
        mapa.put("Bob", 30);
        mapa.put("Charlie", 35);
        mapa.put("Diana", 28);

        System.out.println("get(Alice)   = " + mapa.get("Alice"));     // 25
        System.out.println("get(Charlie) = " + mapa.get("Charlie"));   // 35
        System.out.println("containsKey(Bob) = " + mapa.containsKey("Bob")); // true
        System.out.println("containsKey(Eve) = " + mapa.containsKey("Eve")); // false
        System.out.println("size() = " + mapa.size());                 // 4

        // Actualizar valor existente
        mapa.put("Alice", 26);
        System.out.println("get(Alice) tras update = " + mapa.get("Alice")); // 26

        // Eliminar
        mapa.remove("Bob");
        System.out.println("Tras remove(Bob): containsKey(Bob) = " + mapa.containsKey("Bob")); // false
        System.out.println("size() = " + mapa.size()); // 3

        System.out.println("\n" + mapa);

        System.out.println("\n=== FIN EJERCICIO 35 ===");
    }
}
