package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 43: BFS — Breadth-First Search (Recorrido en Amplitud)
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.4
 *
 * CONTEXTO:
 * BFS explora un grafo nivel por nivel: primero la raíz, luego todos sus
 * vecinos directos, luego los vecinos de los vecinos, etc. Usa una QUEUE
 * para decidir qué nodo visitar a continuación.
 *
 * BFS es ideal para encontrar el camino más corto en grafos NO PONDERADOS.
 *
 * Implementa:
 * - bfs(int inicio): recorrido BFS desde un vértice, imprimiendo el orden.
 * - bfsConNiveles(int inicio): BFS imprimiendo nivel por nivel.
 * - estaConectado(int origen, int destino): ¿existe un camino de A a B?
 *
 * RESTRICCIONES:
 * - Usar una queue manual (array circular o array simple con indices).
 * - Usar un array boolean[] visitado para evitar ciclos.
 * - El grafo se representa como matriz de adyacencia (más simple que lista).
 * - Sin java.util.Queue, LinkedList, ArrayDeque.
 *
 * COMPLEJIDAD: O(V + E) — visita cada vértice y arista una vez.
 * ============================================================================
 */
public class Ejercicio43_BFS {

    private int[][] matrizAdy;
    private String[] nombres;
    private int numVertices;

    public Ejercicio43_BFS(String[] nombres) {
        this.numVertices = nombres.length;
        this.nombres = nombres;
        this.matrizAdy = new int[numVertices][numVertices];
    }

    public void agregarArista(int origen, int destino) {
        matrizAdy[origen][destino] = 1;
        matrizAdy[destino][origen] = 1; // no dirigido
    }

    public void bfs(int inicio) {
        // TODO 1: Crear array boolean[] visitado de tamaño numVertices.
        //         Crear una queue manual (array int[] de tamaño numVertices).
        //         Marcar inicio como visitado, enqueue(inicio).
        //         Mientras la queue no esté vacía:
        //           int actual = dequeue().
        //           Imprimir nombres[actual].
        //           Para cada vecino de actual (recorrer fila matrizAdy[actual]):
        //             Si matrizAdy[actual][vecino] == 1 Y !visitado[vecino]:
        //               Marcar como visitado, enqueue(vecino).
    }

    public void bfsConNiveles(int inicio) {
        // TODO 2: Similar a bfs pero imprimiendo separaciones por nivel.
        //         Técnica: tras iniciar, en cada iteración principal,
        //         procesar TODOS los nodos del nivel actual (tamaño de la queue
        //         en ese momento) antes de avanzar al siguiente nivel.
        //         Imprimir: "Nivel 0: Madrid"
        //                   "Nivel 1: Barcelona, Valencia, Sevilla"
        //                   "Nivel 2: Bilbao"
    }

    public boolean estaConectado(int origen, int destino) {
        // TODO 3: Ejecutar BFS desde 'origen'. Si en algún momento
        //         el nodo dequeue-ado es 'destino', retornar true.
        //         Si la queue se vacía sin encontrarlo, retornar false.
        return false;
    }

    public int componentesConexas() {
        // TODO 4: Contar cuántos grupos desconectados hay en el grafo.
        //         Crear un boolean[] visitado global.
        //         Para cada vértice no visitado, lanzar un BFS (marca todos
        //         los alcanzables) e incrementar el contador de componentes.
        //         Retornar el total de componentes.
        return 0;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 43: BFS ===\n");

        String[] ciudades = {"Madrid", "Barcelona", "Valencia", "Sevilla", "Bilbao"};
        Ejercicio43_BFS grafo = new Ejercicio43_BFS(ciudades);

        grafo.agregarArista(0, 1); // Madrid — Barcelona
        grafo.agregarArista(0, 2); // Madrid — Valencia
        grafo.agregarArista(0, 3); // Madrid — Sevilla
        grafo.agregarArista(1, 4); // Barcelona — Bilbao
        grafo.agregarArista(2, 3); // Valencia — Sevilla

        System.out.println("--- BFS desde Madrid ---");
        grafo.bfs(0);

        System.out.println("\n--- BFS con niveles desde Madrid ---");
        grafo.bfsConNiveles(0);

        System.out.println("\n--- Conexiones ---");
        System.out.println("Madrid → Bilbao: " + grafo.estaConectado(0, 4)); // true
        System.out.println("Componentes conexas: " + grafo.componentesConexas()); // 1

        System.out.println("\n=== FIN EJERCICIO 43 ===");
    }
}
