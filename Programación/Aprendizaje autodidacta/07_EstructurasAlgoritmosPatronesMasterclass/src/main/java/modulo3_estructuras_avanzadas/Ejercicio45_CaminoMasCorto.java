package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 45: Camino Más Corto en Grafo No Ponderado (BFS)
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.4
 *
 * CONTEXTO:
 * En un grafo NO PONDERADO (todas las aristas tienen el mismo "peso"),
 * BFS garantiza encontrar el camino más corto (menor número de aristas).
 * Esto se debe a que BFS explora nivel por nivel: primero los vecinos a
 * distancia 1, luego a distancia 2, etc.
 *
 * Implementa un sistema completo de caminos mínimos:
 * - caminoMasCorto(int origen, int destino): retorna el camino como String.
 * - distancia(int origen, int destino): retorna la longitud (nº de aristas).
 * - distanciasTodas(int origen): distancias desde origen a TODOS los demás.
 * - todosLosCaminosMasCortos(int origen): imprimir caminos a todos los nodos.
 *
 * RESTRICCIONES:
 * - Usar BFS con array padre[] para reconstruir caminos.
 * - Usar array distancia[] inicializado a -1 (no alcanzado).
 * - Queue manual (sin java.util).
 * - Grafo no ponderado y no dirigido.
 *
 * COMPLEJIDAD: O(V + E).
 * ============================================================================
 */
public class Ejercicio45_CaminoMasCorto {

    private int[][] matrizAdy;
    private String[] nombres;
    private int numVertices;

    public Ejercicio45_CaminoMasCorto(String[] nombres) {
        this.numVertices = nombres.length;
        this.nombres = nombres;
        this.matrizAdy = new int[numVertices][numVertices];
    }

    public void agregarArista(int v1, int v2) {
        matrizAdy[v1][v2] = 1;
        matrizAdy[v2][v1] = 1;
    }

    public String caminoMasCorto(int origen, int destino) {
        // TODO 1: Ejecutar BFS desde origen, manteniendo:
        //         - boolean[] visitado
        //         - int[] padre (inicializado a -1): padre[v] = nodo anterior
        //         - Queue manual para el BFS
        //         Si se alcanza destino, reconstruir el camino recorriendo
        //         padre[] desde destino hasta origen y revirtiéndolo.
        //         Formato: "A → B → D → E"
        //         Si no hay camino, retornar "No hay camino".
        return "No hay camino";
    }

    public int distancia(int origen, int destino) {
        // TODO 2: Ejecutar BFS desde origen manteniendo un array int[] dist
        //         inicializado a -1. dist[origen] = 0.
        //         Al visitar un vecino: dist[vecino] = dist[actual] + 1.
        //         Retornar dist[destino] (o -1 si no es alcanzable).
        return -1;
    }

    public int[] distanciasTodas(int origen) {
        // TODO 3: BFS completo desde origen. Retornar el array dist[]
        //         con la distancia mínima a cada vértice.
        //         dist[i] = -1 significa que i no es alcanzable desde origen.
        return new int[numVertices];
    }

    public void todosLosCaminosMasCortos(int origen) {
        // TODO 4: Para cada vértice destino != origen:
        //         Imprimir el camino más corto y su longitud.
        //         Formato:
        //         "  A → B (distancia: 1)"
        //         "  A → B → D (distancia: 2)"
        //         "  A → X: No alcanzable"
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 45: Camino Más Corto (BFS) ===\n");

        //    A --- B --- E
        //    |     |
        //    C --- D --- F
        String[] nodos = {"A", "B", "C", "D", "E", "F"};
        Ejercicio45_CaminoMasCorto grafo = new Ejercicio45_CaminoMasCorto(nodos);

        grafo.agregarArista(0, 1); // A-B
        grafo.agregarArista(0, 2); // A-C
        grafo.agregarArista(1, 3); // B-D
        grafo.agregarArista(1, 4); // B-E
        grafo.agregarArista(2, 3); // C-D
        grafo.agregarArista(3, 5); // D-F

        // Camino más corto
        System.out.println("Camino A → F: " + grafo.caminoMasCorto(0, 5));
        // Esperado: A → B → D → F (distancia 3) o A → C → D → F (distancia 3)

        System.out.println("Camino A → E: " + grafo.caminoMasCorto(0, 4));
        // Esperado: A → B → E (distancia 2)

        // Distancias
        System.out.println("\nDistancia A → F: " + grafo.distancia(0, 5)); // 3
        System.out.println("Distancia A → E: " + grafo.distancia(0, 4));   // 2
        System.out.println("Distancia A → A: " + grafo.distancia(0, 0));   // 0

        // Todas las distancias desde A
        System.out.println("\nDistancias desde A:");
        int[] dists = grafo.distanciasTodas(0);
        for (int i = 0; i < nodos.length; i++) {
            System.out.printf("  A → %s: %d%n", nodos[i], dists[i]);
        }

        // Todos los caminos
        System.out.println("\nTodos los caminos desde A:");
        grafo.todosLosCaminosMasCortos(0);

        System.out.println("\n=== FIN EJERCICIO 45 ===");
    }
}
