package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 83: Iterator — Iterador sobre Árbol Binario
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.5
 *
 * CONTEXTO:
 * Un árbol binario puede recorrerse de muchas formas. El Iterator
 * encapsula la lógica de recorrido, permitiendo iterar con hasNext/next
 * sin importar la estructura interna del árbol.
 *
 * Implementa:
 * - Clase NodoArbol: valor, izquierdo, derecho.
 * - ArbolBinario: BST con insertar().
 * - InOrderIterador: recorre InOrder usando un stack manual.
 * - PreOrderIterador: recorre PreOrder.
 *
 * RESTRICCIONES:
 * - El iterador InOrder usa un stack manual (array) para simular
 *   la recursión de forma iterativa.
 * - Sin java.util.Stack, Queue, etc.
 * ============================================================================
 */
public class Ejercicio83_IteratorArbol {

    static class NodoArbol {
        int valor;
        NodoArbol izq, der;
        NodoArbol(int valor) { this.valor = valor; }
    }

    interface IteradorArbol {
        boolean hasNext();
        int next();
    }

    // TODO 1: Implementar ArbolBST con insertar(int valor) recursivo.

    // TODO 2: Implementar InOrderIterador:
    //         - Stack manual: NodoArbol[] stack (max 100), int top.
    //         - Constructor: pushIzquierdos(raiz) → empujar raíz y todos sus hijos izq.
    //         - hasNext(): top > 0.
    //         - next(): pop(); si nodo tiene hijo derecho → pushIzquierdos(nodo.der).
    //           Retornar nodo.valor.
    //         Esto produce la secuencia InOrder (creciente en un BST).

    // TODO 3: Implementar PreOrderIterador:
    //         - Stack manual.
    //         - Constructor: push(raiz).
    //         - next(): pop(); push hijoDer, push hijoIzq (en ese orden).

    // TODO 4: Implementar método crearIteradorInOrder() y crearIteradorPreOrder()
    //         en ArbolBST.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 83: Iterator Árbol ===\n");

        // TODO 5:
        //         ArbolBST arbol = new ArbolBST();
        //         int[] valores = {50, 30, 70, 20, 40, 60, 80};
        //         for (int v : valores) arbol.insertar(v);
        //
        //         System.out.print("InOrder:  ");
        //         IteradorArbol inOrder = arbol.crearIteradorInOrder();
        //         while (inOrder.hasNext()) System.out.print(inOrder.next() + " ");
        //         // Esperado: 20 30 40 50 60 70 80
        //
        //         System.out.print("\nPreOrder: ");
        //         IteradorArbol preOrder = arbol.crearIteradorPreOrder();
        //         while (preOrder.hasNext()) System.out.print(preOrder.next() + " ");
        //         // Esperado: 50 30 20 40 70 60 80

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 83 ===");
    }
}
