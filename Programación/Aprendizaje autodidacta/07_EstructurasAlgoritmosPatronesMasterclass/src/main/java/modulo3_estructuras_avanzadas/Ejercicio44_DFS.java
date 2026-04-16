package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 44: DFS — Depth-First Search (Recorrido en Profundidad)
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.4
 *
 * CONTEXTO:
 * DFS explora un grafo yendo lo más profundo posible antes de retroceder
 * (backtracking). Puede implementarse con recursión (stack implícito)
 * o con un stack manual.
 *
 * DFS es ideal para: detectar ciclos, ordenamiento topológico, laberintos,
 * generar todas las combinaciones posibles, etc.
 *
 * Implementa:
 * - dfsRecursivo(int inicio): versión con recursión.
 * - dfsIterativo(int inicio): versión con stack manual.
 * - tieneCiclo(): detectar si hay ciclo en el grafo (dirigido).
 * - encontrarCamino(int origen, int destino): encontrar UN camino (no
 *   necesariamente el más corto) entre dos vértices.
 *
 * RESTRICCIONES:
 * - Versión recursiva usa boolean[] visitado y llamadas recursivas.
 * - Versión iterativa usa un stack manual (array).
 * - Sin java.util.Stack ni java.util.Deque.
 *
 * COMPLEJIDAD: O(V + E).
 * ============================================================================
 */
public class Ejercicio44_DFS {

    private int[][] matrizAdy;
    private String[] nombres;
    private int numVertices;

    public Ejercicio44_DFS(String[] nombres) {
        this.numVertices = nombres.length;
        this.nombres = nombres;
        this.matrizAdy = new int[numVertices][numVertices];
    }

    public void agregarAristaDirigida(int origen, int destino) {
        matrizAdy[origen][destino] = 1;
    }

    public void agregarAristaNoDirigida(int origen, int destino) {
        matrizAdy[origen][destino] = 1;
        matrizAdy[destino][origen] = 1;
    }

    public void dfsRecursivo(int inicio) {
        boolean[] visitado = new boolean[numVertices];
        System.out.print("DFS Recursivo: ");
        dfsRec(inicio, visitado);
        System.out.println();
    }

    private void dfsRec(int vertice, boolean[] visitado) {
        // TODO 1: Marcar vertice como visitado.
        //         Imprimir nombres[vertice].
        //         Para cada vecino (recorrer matrizAdy[vertice]):
        //           Si matrizAdy[vertice][vecino] == 1 Y !visitado[vecino]:
        //             Llamar recursivamente dfsRec(vecino, visitado).
    }

    public void dfsIterativo(int inicio) {
        // TODO 2: Crear un stack manual (array int[] de tamaño numVertices).
        //         Crear boolean[] visitado.
        //         Push inicio al stack.
        //         Mientras el stack no esté vacío:
        //           int actual = pop().
        //           Si ya fue visitado → continuar (skip).
        //           Marcar como visitado. Imprimir nombres[actual].
        //           Para cada vecino (recorrer en ORDEN INVERSO para mantener
        //           el mismo orden que la versión recursiva):
        //             Si no visitado → push al stack.
    }

    public boolean tieneCiclo() {
        // TODO 3: Para un grafo DIRIGIDO, usar DFS con tres estados:
        //         0 = no visitado, 1 = en proceso (en el stack actual), 2 = completado.
        //         Si durante el DFS encuentras un vecino con estado 1
        //         (está en el camino actual de la recursión) → hay ciclo.
        //         Verificar desde cada vértice no visitado.
        return false;
    }

    public String encontrarCamino(int origen, int destino) {
        // TODO 4: Usar DFS para encontrar un camino de origen a destino.
        //         Mantener un array padre[] donde padre[v] = nodo anterior en el camino.
        //         Al encontrar destino, reconstruir el camino desde destino
        //         hacia origen usando padre[].
        //         Retornar como String: "Madrid → Barcelona → Bilbao"
        //         Si no hay camino, retornar "No hay camino".
        return "No hay camino";
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 44: DFS ===\n");

        String[] nodos = {"A", "B", "C", "D", "E", "F"};
        Ejercicio44_DFS grafo = new Ejercicio44_DFS(nodos);

        grafo.agregarAristaNoDirigida(0, 1); // A-B
        grafo.agregarAristaNoDirigida(0, 2); // A-C
        grafo.agregarAristaNoDirigida(1, 3); // B-D
        grafo.agregarAristaNoDirigida(1, 4); // B-E
        grafo.agregarAristaNoDirigida(2, 5); // C-F

        grafo.dfsRecursivo(0);  // A, B, D, E, C, F (puede variar)
        grafo.dfsIterativo(0);

        System.out.println("\nCamino de A a F: " + grafo.encontrarCamino(0, 5));

        // Grafo dirigido con ciclo
        System.out.println("\n--- Detección de ciclos ---");
        Ejercicio44_DFS dirigido = new Ejercicio44_DFS(new String[]{"X", "Y", "Z"});
        dirigido.agregarAristaDirigida(0, 1); // X→Y
        dirigido.agregarAristaDirigida(1, 2); // Y→Z
        dirigido.agregarAristaDirigida(2, 0); // Z→X (CICLO)
        System.out.println("¿Tiene ciclo? " + dirigido.tieneCiclo()); // true

        System.out.println("\n=== FIN EJERCICIO 44 ===");
    }
}
