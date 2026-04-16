package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 37: HashMap — Redimensionamiento Automático (Load Factor)
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.2
 *
 * CONTEXTO:
 * Un HashMap eficiente debe crecer automáticamente cuando el factor de
 * carga (size/capacity) supera un umbral (típicamente 0.75). El proceso
 * de redimensionar implica REHASHEAR todas las entradas existentes.
 *
 * Implementa un HashMap completo con redimensionamiento:
 * - put(String clave, int valor): con rehash automático.
 * - get(String clave): búsqueda.
 * - El factor de carga se verifica DESPUÉS de cada put().
 * - rehash(): duplicar capacidad y redistribuir TODAS las entradas.
 *
 * RESTRICCIONES:
 * - Capacidad inicial de 4 (pequeña para forzar rehashing temprano).
 * - Umbral de load factor: 0.75.
 * - Al rehashear, TODOS los entries deben recalcular su índice con la
 *   nueva capacidad (hash % nuevaCapacidad).
 * - Imprimir un mensaje cada vez que se redimensione.
 * ============================================================================
 */
public class Ejercicio37_HashMapRedimensionable {

    private static class Entry {
        String clave;
        int valor;
        Entry siguiente;
        Entry(String clave, int valor) { this.clave = clave; this.valor = valor; }
    }

    private Entry[] buckets;
    private int size;
    private int capacidad;
    private static final double LOAD_FACTOR_UMBRAL = 0.75;

    public Ejercicio37_HashMapRedimensionable() {
        this.capacidad = 4;
        this.buckets = new Entry[capacidad];
        this.size = 0;
    }

    private int hash(String clave) {
        return Math.abs(clave.hashCode() % capacidad);
    }

    public void put(String clave, int valor) {
        // TODO 1: Calcular índice. Buscar si la clave ya existe en el bucket
        //         → si existe, actualizar el valor y retornar.
        //         Si no existe, crear nuevo Entry e insertar al inicio del bucket.
        //         Incrementar size.
        //         Verificar: si (double) size / capacidad > LOAD_FACTOR_UMBRAL → rehash().
    }

    public int get(String clave) {
        // TODO 2: Calcular índice. Recorrer el bucket buscando la clave.
        //         Retornar el valor si se encuentra. Lanzar excepción si no.
        return -1;
    }

    private void rehash() {
        // TODO 3: Imprimir: "[REHASH] Capacidad: X → Y"
        //         Guardar referencia al array de buckets antiguo.
        //         Duplicar la capacidad. Crear nuevo array de buckets vacío.
        //         Resetear size a 0.
        //         Recorrer TODOS los buckets antiguos y por cada Entry:
        //           Llamar a put(entry.clave, entry.valor) con la nueva capacidad.
        //           (put() recalculará el hash con el nuevo tamaño).
    }

    public double loadFactor() {
        return (double) size / capacidad;
    }

    public int size() { return size; }
    public int capacidad() { return capacidad; }

    @Override
    public String toString() {
        // TODO 4: Representación visual mostrando capacidad, size, load factor.
        //         "MiHashMap(size=X, cap=Y, lf=0.XX)"
        return "MiHashMap()";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 37: HashMap Redimensionable ===\n");

        Ejercicio37_HashMapRedimensionable mapa = new Ejercicio37_HashMapRedimensionable();

        System.out.println("Capacidad inicial: " + mapa.capacidad()); // 4

        String[] nombres = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                            "Frank", "Grace", "Hank", "Ivy", "Jack",
                            "Karen", "Leo", "Maria", "Nick", "Olivia"};

        for (String nombre : nombres) {
            mapa.put(nombre, nombre.length());
            System.out.printf("  put(%s) → %s%n", nombre, mapa);
        }
        // Deberían ocurrir varios REHASH: 4→8→16→32

        System.out.println("\nCapacidad final: " + mapa.capacidad());
        System.out.println("Size: " + mapa.size());
        System.out.printf("Load Factor: %.2f%n", mapa.loadFactor());

        // Verificar que los datos sobrevivieron al rehash
        System.out.println("\nVerificación post-rehash:");
        for (String nombre : nombres) {
            System.out.printf("  get(%s) = %d%n", nombre, mapa.get(nombre));
        }

        System.out.println("\n=== FIN EJERCICIO 37 ===");
    }
}
