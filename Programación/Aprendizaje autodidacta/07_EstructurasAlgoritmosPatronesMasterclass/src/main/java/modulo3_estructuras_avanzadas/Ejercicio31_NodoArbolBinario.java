package modulo3_estructuras_avanzadas;

/**
 * ============================================================================
 * EJERCICIO 31: Nodo de Árbol Binario y Recorridos Básicos
 * ============================================================================
 * 📚 Teoría: teoria/03_Estructuras_Avanzadas_Grafos.md - Sección 3.1
 *
 * CONTEXTO:
 * Antes de construir un BST completo, necesitas entender la unidad atómica:
 * el NodoArbol. Cada nodo tiene un valor, un hijo izquierdo y un hijo derecho.
 * Construirás un árbol manualmente y lo recorrerás con los 3 órdenes clásicos.
 *
 * Implementa:
 * - Clase interna NodoArbol con valor, izquierdo, derecho.
 * - inorder(NodoArbol nodo): recorrido Izq → Nodo → Der.
 * - preorder(NodoArbol nodo): recorrido Nodo → Izq → Der.
 * - postorder(NodoArbol nodo): recorrido Izq → Der → Nodo.
 * - contarNodos(NodoArbol nodo): total de nodos en el árbol.
 * - altura(NodoArbol nodo): altura del árbol.
 *
 * RESTRICCIONES:
 * - Todos los recorridos deben ser RECURSIVOS.
 * - El árbol se construye manualmente en main().
 * - Sin usar java.util.
 * ============================================================================
 */
public class Ejercicio31_NodoArbolBinario {

    static class NodoArbol {
        int valor;
        NodoArbol izquierdo;
        NodoArbol derecho;

        NodoArbol(int valor) {
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    public static void inorder(NodoArbol nodo) {
        // TODO 1: Caso base: si nodo es null, retornar.
        //         Llamar recursivamente inorder(nodo.izquierdo).
        //         Imprimir nodo.valor (con System.out.print).
        //         Llamar recursivamente inorder(nodo.derecho).
        //         Resultado para BST: valores en ORDEN ASCENDENTE.
    }

    public static void preorder(NodoArbol nodo) {
        // TODO 2: Caso base: si nodo es null, retornar.
        //         Imprimir nodo.valor.
        //         Llamar preorder(nodo.izquierdo).
        //         Llamar preorder(nodo.derecho).
        //         Uso: serializar/copiar un árbol.
    }

    public static void postorder(NodoArbol nodo) {
        // TODO 3: Caso base: si nodo es null, retornar.
        //         Llamar postorder(nodo.izquierdo).
        //         Llamar postorder(nodo.derecho).
        //         Imprimir nodo.valor.
        //         Uso: eliminar un árbol (hijos antes que padre).
    }

    public static int contarNodos(NodoArbol nodo) {
        // TODO 4: Caso base: si nodo es null, retornar 0.
        //         Retornar: 1 + contarNodos(izq) + contarNodos(der).
        return 0;
    }

    public static int altura(NodoArbol nodo) {
        // TODO 5: Caso base: si nodo es null, retornar -1 (convención)
        //         o retornar 0 si se considera la altura de un nodo hoja como 0.
        //         Retornar: 1 + max(altura(izq), altura(der)).
        return 0;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 31: Nodo Árbol Binario ===\n");

        // Construir árbol manualmente:
        //        50
        //       /  \
        //      30    70
        //     / \   / \
        //    20 40 60  80
        NodoArbol raiz = new NodoArbol(50);
        raiz.izquierdo = new NodoArbol(30);
        raiz.derecho = new NodoArbol(70);
        raiz.izquierdo.izquierdo = new NodoArbol(20);
        raiz.izquierdo.derecho = new NodoArbol(40);
        raiz.derecho.izquierdo = new NodoArbol(60);
        raiz.derecho.derecho = new NodoArbol(80);

        System.out.print("Inorder:   "); inorder(raiz);   System.out.println();
        // Esperado: 20 30 40 50 60 70 80

        System.out.print("Preorder:  "); preorder(raiz);  System.out.println();
        // Esperado: 50 30 20 40 70 60 80

        System.out.print("Postorder: "); postorder(raiz); System.out.println();
        // Esperado: 20 40 30 60 80 70 50

        System.out.println("Total nodos: " + contarNodos(raiz));  // 7
        System.out.println("Altura:      " + altura(raiz));        // 2

        System.out.println("\n=== FIN EJERCICIO 31 ===");
    }
}
