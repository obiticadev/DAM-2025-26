package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 42: Grafo con Lista de Adyacencia
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.4
 *
 * CONTEXTO:
 * Un grafo se representa eficientemente con una LISTA DE ADYACENCIA:
 * para cada vértice, se almacena la lista de sus vecinos. Es más
 * eficiente en espacio que la matriz de adyacencia para grafos dispersos.
 *
 * Implementa un grafo usando arrays manuales (sin ArrayList):
 * - agregarVertice(String nombre): añadir un vértice.
 * - agregarArista(String origen, String destino): arista dirigida.
 * - agregarAristaNoDirigida(String v1, String v2): arista en ambas direcciones.
 * - obtenerVecinos(String vertice): lista de vecinos.
 * - tieneArista(String origen, String destino): verificar conexión.
 * - imprimirGrafo(): visualización completa.
 *
 * RESTRICCIONES:
 * - Usar arrays internos para los vértices y las listas de adyacencia.
 * - Máximo 20 vértices (capacidad fija para simplificar).
 * - Cada vértice tiene un array de vecinos (máximo 20).
 * - Sin usar java.util (ni ArrayList, ni HashMap, ni LinkedList).
 * ============================================================================
 */
public class Ejercicio42_GrafoListaAdyacencia {

    private String[] vertices;
    private int[][] adyacencia; // adyacencia[i] = array de índices de vecinos de i
    private int[] gradoSalida;  // cuántos vecinos tiene cada vértice
    private int numVertices;
    private static final int MAX = 20;

    public Ejercicio42_GrafoListaAdyacencia() {
        this.vertices = new String[MAX];
        this.adyacencia = new int[MAX][MAX];
        this.gradoSalida = new int[MAX];
        this.numVertices = 0;
    }

    private int indiceDeVertice(String nombre) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(nombre)) return i;
        }
        return -1;
    }

    public void agregarVertice(String nombre) {
        // TODO 1: Verificar que no exista ya (indiceDeVertice != -1).
        //         Verificar que no se exceda MAX.
        //         Almacenar el nombre en vertices[numVertices].
        //         Incrementar numVertices.
    }

    public void agregarArista(String origen, String destino) {
        // TODO 2: Obtener los índices de origen y destino.
        //         Si alguno no existe, lanzar excepción o añadirlos automáticamente.
        //         En adyacencia[indiceOrigen], añadir indiceDestino:
        //           adyacencia[idxOrigen][gradoSalida[idxOrigen]] = idxDestino
        //           gradoSalida[idxOrigen]++
        //         Esto es una arista DIRIGIDA (de origen a destino).
    }

    public void agregarAristaNoDirigida(String v1, String v2) {
        // TODO 3: Llamar a agregarArista en ambas direcciones:
        //         agregarArista(v1, v2) y agregarArista(v2, v1).
    }

    public String[] obtenerVecinos(String vertice) {
        // TODO 4: Encontrar el índice del vértice.
        //         Crear un array de Strings de tamaño gradoSalida[idx].
        //         Llenarlo con los nombres de los vecinos usando adyacencia[idx].
        //         Retornar el array.
        return new String[0];
    }

    public boolean tieneArista(String origen, String destino) {
        // TODO 5: Encontrar índice de origen. Recorrer su lista de adyacencia
        //         buscando el índice de destino. Retornar true/false.
        return false;
    }

    public void imprimirGrafo() {
        System.out.println("Grafo (" + numVertices + " vértices):");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("  " + vertices[i] + " → [");
            for (int j = 0; j < gradoSalida[i]; j++) {
                System.out.print(vertices[adyacencia[i][j]]);
                if (j < gradoSalida[i] - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 42: Grafo Lista Adyacencia ===\n");

        Ejercicio42_GrafoListaAdyacencia grafo = new Ejercicio42_GrafoListaAdyacencia();

        // Crear grafo dirigido
        String[] ciudades = {"Madrid", "Barcelona", "Valencia", "Sevilla", "Bilbao"};
        for (String c : ciudades) grafo.agregarVertice(c);

        grafo.agregarArista("Madrid", "Barcelona");
        grafo.agregarArista("Madrid", "Valencia");
        grafo.agregarArista("Madrid", "Sevilla");
        grafo.agregarArista("Barcelona", "Valencia");
        grafo.agregarArista("Valencia", "Sevilla");
        grafo.agregarArista("Sevilla", "Bilbao");
        grafo.agregarArista("Bilbao", "Barcelona");

        grafo.imprimirGrafo();

        System.out.println("\nVecinos de Madrid:");
        String[] vecinos = grafo.obtenerVecinos("Madrid");
        for (String v : vecinos) System.out.println("  → " + v);

        System.out.println("\ntieneArista(Madrid, Barcelona) = " +
                grafo.tieneArista("Madrid", "Barcelona")); // true
        System.out.println("tieneArista(Barcelona, Madrid) = " +
                grafo.tieneArista("Barcelona", "Madrid")); // false (dirigido)

        System.out.println("\n=== FIN EJERCICIO 42 ===");
    }
}
