package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 33: BST — Recorridos Completos (Iterativos + Level Order)
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.1
 *
 * CONTEXTO:
 * En el Ej.31 hiciste los recorridos recursivos. Ahora implementarás las
 * versiones ITERATIVAS (usando stacks manuales) y el recorrido por niveles
 * (Level Order / BFS) usando una queue manual.
 *
 * Implementa:
 * - inorderIterativo(): usando un stack manual.
 * - preorderIterativo(): usando un stack manual.
 * - levelOrder(): recorrido por niveles (BFS) usando una queue manual.
 * - esHoja(nodo): determinar si un nodo es hoja.
 * - contarHojas(): contar el total de nodos hoja.
 *
 * RESTRICCIONES:
 * - Para los iterativos, usar arrays como stacks/queues manuales
 *   (o usar las implementaciones del Módulo 1).
 * - Sin usar java.util.Stack, java.util.Queue, java.util.LinkedList.
 * - El level order imprime nivel por nivel con separación visual.
 * ============================================================================
 */
public class Ejercicio33_BSTRecorridos {

    static class Nodo {
        int valor;
        Nodo izquierdo, derecho;
        Nodo(int valor) { this.valor = valor; }
    }

    private Nodo raiz;

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);
        if (valor < nodo.valor) nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        else if (valor > nodo.valor) nodo.derecho = insertarRec(nodo.derecho, valor);
        return nodo;
    }

    public void inorderIterativo() {
        // TODO 1: Crear un array de Nodos como stack manual (tamaño 100 es suficiente).
        //         Inicializar un puntero 'actual' = raiz.
        //         Bucle while (actual != null O stack no vacío):
        //           Mientras actual != null → push(actual), actual = actual.izquierdo.
        //           actual = pop() del stack.
        //           Imprimir actual.valor.
        //           actual = actual.derecho.
    }

    public void preorderIterativo() {
        // TODO 2: Crear un stack manual. Push de la raíz.
        //         Mientras el stack no esté vacío:
        //           Nodo actual = pop().
        //           Imprimir actual.valor.
        //           Si actual.derecho != null → push (DERECHO primero, sale después).
        //           Si actual.izquierdo != null → push (IZQUIERDO después, sale primero).
    }

    public void levelOrder() {
        // TODO 3: Crear un array de Nodos como queue manual (tamaño 100).
        //         Enqueue la raíz.
        //         Mientras la queue no esté vacía:
        //           int nivelSize = tamaño actual de la queue (nodos en este nivel).
        //           Para i desde 0 hasta nivelSize:
        //             Nodo actual = dequeue().
        //             Imprimir actual.valor.
        //             Si actual.izquierdo != null → enqueue.
        //             Si actual.derecho != null → enqueue.
        //           Salto de línea (separar niveles).
    }

    public int contarHojas() {
        // TODO 4: Recorrer el árbol (recursivo o iterativo) contando
        //         los nodos que no tienen hijos (izq == null Y der == null).
        return 0;
    }

    public boolean esBST() {
        // TODO 5: Verificar que el árbol cumple la propiedad BST.
        //         Usar la técnica de rango: esBSTRec(nodo, min, max)
        //         Cada nodo debe estar entre un rango [min, max].
        //         Raíz: rango [Integer.MIN_VALUE, Integer.MAX_VALUE].
        //         Hijo izquierdo: rango [min, padre.valor - 1].
        //         Hijo derecho: rango [padre.valor + 1, max].
        return false;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 33: BST Recorridos ===\n");

        Ejercicio33_BSTRecorridos bst = new Ejercicio33_BSTRecorridos();
        int[] vals = {50, 30, 70, 20, 40, 60, 80};
        for (int v : vals) bst.insertar(v);

        System.out.print("Inorder iterativo:  "); bst.inorderIterativo(); System.out.println();
        System.out.print("Preorder iterativo: "); bst.preorderIterativo(); System.out.println();

        System.out.println("Level Order (por niveles):");
        bst.levelOrder();
        // Nivel 0: 50
        // Nivel 1: 30 70
        // Nivel 2: 20 40 60 80

        System.out.println("Hojas: " + bst.contarHojas());   // 4
        System.out.println("¿Es BST válido? " + bst.esBST()); // true

        System.out.println("\n=== FIN EJERCICIO 33 ===");
    }
}
