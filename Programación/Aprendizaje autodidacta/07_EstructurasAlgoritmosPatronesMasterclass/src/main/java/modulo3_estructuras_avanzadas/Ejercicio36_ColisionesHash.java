package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 36: Colisiones Hash — Análisis de Chaining
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.2
 *
 * CONTEXTO:
 * Las colisiones son inevitables en cualquier HashMap. Este ejercicio
 * se enfoca en visualizar y analizar cómo se distribuyen las entradas
 * en los buckets y qué pasa cuando la función hash es mala.
 *
 * Implementa:
 * - Un HashMap con capacidad PEQUEÑA (4 buckets) para forzar colisiones.
 * - visualizarBuckets(): muestra gráficamente la distribución.
 * - longitudMaximaCadena(): la cadena más larga (peor bucket).
 * - distribucion(): array con la longitud de cada cadena.
 * - hashMalo(String clave): una función hash intencionalmente mala.
 * - hashBueno(String clave): una función hash bien distribuida.
 *
 * RESTRICCIONES:
 * - Capacidad fija de 4 buckets (para forzar colisiones masivas).
 * - Implementar DOS funciones hash y comparar la distribución.
 * - Sin java.util.HashMap.
 * ============================================================================
 */
public class Ejercicio36_ColisionesHash {

    private static class Entry {
        String clave;
        int valor;
        Entry siguiente;
        Entry(String clave, int valor) { this.clave = clave; this.valor = valor; }
    }

    private Entry[] buckets;
    private int capacidad;
    private boolean usarHashMalo;

    public Ejercicio36_ColisionesHash(int capacidad, boolean usarHashMalo) {
        this.capacidad = capacidad;
        this.buckets = new Entry[capacidad];
        this.usarHashMalo = usarHashMalo;
    }

    private int hash(String clave) {
        if (usarHashMalo) return hashMalo(clave);
        return hashBueno(clave);
    }

    private int hashMalo(String clave) {
        // TODO 1: Implementar una función hash intencionalmente mala:
        //         Retornar siempre el mismo bucket (ej: clave.length() % capacidad).
        //         Esto hará que muchas claves colisionen en el mismo bucket,
        //         degradando el HashMap a una lista enlazada O(n).
        return 0;
    }

    private int hashBueno(String clave) {
        // TODO 2: Implementar una función hash decente:
        //         Usar Math.abs(clave.hashCode() % capacidad).
        //         hashCode() de String ya es un buen hash, distribuye bien.
        return 0;
    }

    public void put(String clave, int valor) {
        int indice = hash(clave);
        Entry nuevo = new Entry(clave, valor);
        nuevo.siguiente = buckets[indice];
        buckets[indice] = nuevo;
    }

    public void visualizarBuckets() {
        // TODO 3: Para cada bucket, imprimir su contenido visual:
        //         "[0]: Alice→Bob→Charlie (longitud: 3)"
        //         "[1]: (vacío)"
        //         "[2]: Diana (longitud: 1)"
        //         "[3]: Eve→Frank (longitud: 2)"
        //         Esto permite ver la distribución a simple vista.
    }

    public int longitudMaximaCadena() {
        // TODO 4: Recorrer todos los buckets y encontrar la cadena más larga.
        //         Un bucket vacío tiene longitud 0.
        //         Retornar la longitud máxima encontrada.
        return 0;
    }

    public int[] distribucion() {
        // TODO 5: Retornar un array de tamaño 'capacidad' donde cada posición
        //         contiene la longitud de la cadena en ese bucket.
        //         Ejemplo: [3, 0, 1, 2] → bucket 0 tiene 3 entries, bucket 1 vacío, etc.
        return new int[capacidad];
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 36: Colisiones Hash ===\n");

        String[] nombres = {"Alice", "Bob", "Charlie", "Diana", "Eve",
                            "Frank", "Grace", "Hank", "Ivy", "Jack"};

        // Hash MALO
        System.out.println("=== HASH MALO (distribución pobre) ===");
        Ejercicio36_ColisionesHash mapaMalo = new Ejercicio36_ColisionesHash(4, true);
        for (int i = 0; i < nombres.length; i++) mapaMalo.put(nombres[i], i);
        mapaMalo.visualizarBuckets();
        System.out.println("Cadena más larga: " + mapaMalo.longitudMaximaCadena());

        // Hash BUENO
        System.out.println("\n=== HASH BUENO (distribución uniforme) ===");
        Ejercicio36_ColisionesHash mapaBueno = new Ejercicio36_ColisionesHash(4, false);
        for (int i = 0; i < nombres.length; i++) mapaBueno.put(nombres[i], i);
        mapaBueno.visualizarBuckets();
        System.out.println("Cadena más larga: " + mapaBueno.longitudMaximaCadena());

        System.out.println("\n=== FIN EJERCICIO 36 ===");
    }
}
