package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 38: HashSet Manual
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.2
 *
 * CONTEXTO:
 * Un HashSet es un HashMap disfrazado donde solo importan las claves
 * (los valores se ignoran). Garantiza unicidad: no permite duplicados.
 *
 * Implementa un MiHashSet que internamente delega en un HashMap simplificado:
 * - add(String elemento): añade si no existe.
 * - contains(String elemento): verifica existencia.
 * - remove(String elemento): elimina.
 * - size(): número de elementos.
 * - toArray(): retorna los elementos como array de Strings.
 *
 * RESTRICCIONES:
 * - Basado en un array de listas enlazadas (como el HashMap del Ej.35).
 * - Capacidad inicial 16. Puede no tener rehash (simplificación).
 * - Sin java.util.HashSet ni java.util.HashMap.
 * ============================================================================
 */
public class Ejercicio38_HashSetManual {

    private static class Nodo {
        String valor;
        Nodo siguiente;
        Nodo(String valor) { this.valor = valor; }
    }

    private Nodo[] buckets;
    private int size;
    private int capacidad;

    public Ejercicio38_HashSetManual() {
        this.capacidad = 16;
        this.buckets = new Nodo[capacidad];
        this.size = 0;
    }

    private int hash(String valor) {
        return Math.abs(valor.hashCode() % capacidad);
    }

    public boolean add(String elemento) {
        // TODO 1: Si ya contiene el elemento → retornar false (no duplicados).
        //         Si no, calcular el hash, crear un nuevo Nodo y añadirlo
        //         al inicio del bucket correspondiente. Incrementar size.
        //         Retornar true (añadido exitosamente).
        return false;
    }

    public boolean contains(String elemento) {
        // TODO 2: Calcular el hash. Recorrer la lista del bucket comparando
        //         con .equals(). Retornar true si se encuentra, false si no.
        return false;
    }

    public boolean remove(String elemento) {
        // TODO 3: Calcular el hash. Buscar el elemento en el bucket.
        //         Si es el primero → buckets[idx] = nodo.siguiente.
        //         Si está en medio → anterior.siguiente = nodo.siguiente.
        //         Decrementar size. Retornar true/false según si se encontró.
        return false;
    }

    public int size() { return size; }

    public String[] toArray() {
        // TODO 4: Crear un array de Strings de tamaño 'size'.
        //         Recorrer todos los buckets y todas las cadenas,
        //         copiando cada valor al array.
        //         Retornar el array.
        return new String[0];
    }

    @Override
    public String toString() {
        // TODO 5: Representación: "MiHashSet{Alice, Bob, Charlie}"
        //         Recorrer todos los buckets recolectando valores.
        return "MiHashSet{}";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 38: HashSet Manual ===\n");

        Ejercicio38_HashSetManual set = new Ejercicio38_HashSetManual();

        System.out.println("add(Alice):   " + set.add("Alice"));   // true
        System.out.println("add(Bob):     " + set.add("Bob"));     // true
        System.out.println("add(Charlie): " + set.add("Charlie")); // true
        System.out.println("add(Alice):   " + set.add("Alice"));   // false (duplicado)

        System.out.println("contains(Bob):  " + set.contains("Bob"));  // true
        System.out.println("contains(Eve):  " + set.contains("Eve"));  // false
        System.out.println("size(): " + set.size());                    // 3
        System.out.println("Set: " + set);

        set.remove("Bob");
        System.out.println("\nTras remove(Bob):");
        System.out.println("contains(Bob): " + set.contains("Bob")); // false
        System.out.println("size(): " + set.size());                  // 2
        System.out.println("Set: " + set);

        System.out.println("\n=== FIN EJERCICIO 38 ===");
    }
}
