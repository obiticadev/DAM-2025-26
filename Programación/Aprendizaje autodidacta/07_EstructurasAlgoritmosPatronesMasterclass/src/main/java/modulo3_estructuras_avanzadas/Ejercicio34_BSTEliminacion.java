package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 34: BST — Eliminación, Altura y Verificación de Balanceo
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.1
 *
 * CONTEXTO:
 * Eliminar un nodo de un BST es la operación más compleja. Hay 3 casos:
 * 1. Nodo hoja: simplemente se elimina (poner a null).
 * 2. Nodo con un hijo: el hijo reemplaza al nodo eliminado.
 * 3. Nodo con dos hijos: se reemplaza por el SUCESOR INORDER (el menor
 *    del subárbol derecho) o el PREDECESOR INORDER (el mayor del izquierdo).
 *
 * Implementa:
 * - eliminar(int valor): eliminación con los 3 casos.
 * - altura(): calcular la altura del árbol.
 * - estaBalanceado(): verificar si el árbol está balanceado (la diferencia
 *   de alturas entre subárboles izq y der es <= 1 para TODOS los nodos).
 * - size(): número total de nodos.
 *
 * RESTRICCIONES:
 * - Encontrar el sucesor inorder = mínimo del subárbol derecho.
 * - La eliminación es recursiva.
 * - estaBalanceado() debe verificar TODOS los nodos, no solo la raíz.
 *
 * COMPLEJIDAD OBJETIVO:
 * - eliminar(): O(log n) promedio, O(n) peor caso.
 * ============================================================================
 */
public class Ejercicio34_BSTEliminacion {

    private static class Nodo {
        int valor;
        Nodo izquierdo, derecho;
        Nodo(int valor) { this.valor = valor; }
    }

    private Nodo raiz;
    private int size;

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
        size++;
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);
        if (valor < nodo.valor) nodo.izquierdo = insertarRec(nodo.izquierdo, valor);
        else if (valor > nodo.valor) nodo.derecho = insertarRec(nodo.derecho, valor);
        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {
        // TODO 1: Caso base: si nodo es null, retornar null (no encontrado).
        //         Si valor < nodo.valor → nodo.izquierdo = eliminarRec(izq, valor).
        //         Si valor > nodo.valor → nodo.derecho = eliminarRec(der, valor).
        //         Si valor == nodo.valor (ENCONTRADO):
        //           CASO 1 (hoja): si ambos hijos son null → retornar null.
        //           CASO 2 (un hijo): si izq es null → retornar der. Si der es null → retornar izq.
        //           CASO 3 (dos hijos): buscar el sucesor inorder (mínimo del subárbol derecho).
        //             Copiar su valor al nodo actual.
        //             Eliminar recursivamente el sucesor del subárbol derecho.
        //         Decrementar size. Retornar nodo.
        return nodo;
    }

    private int encontrarMinimo(Nodo nodo) {
        // TODO 2: Recorrer hacia la izquierda hasta que izquierdo sea null.
        //         Retornar el valor del nodo más a la izquierda.
        return -1;
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo nodo) {
        // TODO 3: Caso base: nodo null → retornar -1.
        //         Retornar 1 + Math.max(alturaRec(izq), alturaRec(der)).
        return -1;
    }

    public boolean estaBalanceado() {
        // TODO 4: Llamar a un método recursivo que retorne -1 si está desbalanceado,
        //         o la altura si está balanceado.
        //         Para cada nodo: calcular altura izq y der.
        //         Si |alturaIzq - alturaDer| > 1 → desbalanceado.
        //         Si algún subárbol es -1 (desbalanceado) → propagar -1.
        //         Si todo ok → retornar la altura real.
        //         estaBalanceado() retorna true si el resultado != -1.
        return false;
    }

    public int size() { return size; }

    public void inorder() {
        inorderRec(raiz);
        System.out.println();
    }

    private void inorderRec(Nodo n) {
        if (n == null) return;
        inorderRec(n.izquierdo);
        System.out.print(n.valor + " ");
        inorderRec(n.derecho);
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 34: BST Eliminación ===\n");

        Ejercicio34_BSTEliminacion bst = new Ejercicio34_BSTEliminacion();
        int[] vals = {50, 30, 70, 20, 40, 60, 80, 35, 45};
        for (int v : vals) bst.insertar(v);

        System.out.print("Original:  "); bst.inorder();
        System.out.println("Altura: " + bst.altura());
        System.out.println("Size: " + bst.size());
        System.out.println("Balanceado: " + bst.estaBalanceado());

        // Eliminar hoja
        bst.eliminar(35);
        System.out.print("\nEliminar 35 (hoja): "); bst.inorder();

        // Eliminar nodo con un hijo
        bst.eliminar(20);
        System.out.print("Eliminar 20 (un hijo): "); bst.inorder();

        // Eliminar nodo con dos hijos
        bst.eliminar(30);
        System.out.print("Eliminar 30 (dos hijos): "); bst.inorder();

        // Eliminar raíz
        bst.eliminar(50);
        System.out.print("Eliminar 50 (raíz): "); bst.inorder();

        System.out.println("Size final: " + bst.size());

        System.out.println("\n=== FIN EJERCICIO 34 ===");
    }
}
