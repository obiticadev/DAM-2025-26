package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 08: Detección de Ciclos — Algoritmo de Floyd
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.4
 *
 * CONTEXTO:
 * Un ciclo en una lista enlazada ocurre cuando un nodo apunta a un nodo
 * anterior en la cadena, creando un bucle infinito. Detectar esto es
 * crítico en sistemas reales (evitar bucles infinitos, memory leaks, etc.).
 *
 * El Algoritmo de Floyd (Tortuga y Liebre) usa dos punteros:
 * - Tortuga: avanza 1 nodo por iteración.
 * - Liebre:  avanza 2 nodos por iteración.
 * Si hay ciclo, la liebre alcanzará a la tortuga. Si no hay ciclo,
 * la liebre llegará a null.
 *
 * Implementa:
 * - tieneCiclo(Nodo head): detecta si hay ciclo. Retorna boolean.
 * - encontrarInicioCiclo(Nodo head): si hay ciclo, retorna el nodo donde
 *   empieza el ciclo. Si no hay, retorna null.
 * - longitudCiclo(Nodo head): si hay ciclo, retorna su longitud.
 *
 * RESTRICCIONES:
 * - Complejidad espacial O(1) — NO usar HashSet ni estructuras auxiliares.
 * - Solo dos punteros (tortuga y liebre).
 * - Reutilizar la clase Nodo interna.
 *
 * COMPLEJIDAD OBJETIVO:
 * - tieneCiclo():           O(n) tiempo, O(1) espacio
 * - encontrarInicioCiclo(): O(n) tiempo, O(1) espacio
 * ============================================================================
 */
public class Ejercicio08_DeteccionCiclos {

    static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    public static boolean tieneCiclo(Nodo head) {
        // TODO 1: Inicializar dos punteros: tortuga = head, liebre = head.
        //         Mientras liebre != null Y liebre.siguiente != null:
        //           tortuga avanza 1 posición (tortuga = tortuga.siguiente).
        //           liebre avanza 2 posiciones (liebre = liebre.siguiente.siguiente).
        //           Si tortuga == liebre (se encontraron), retornar true.
        //         Si el bucle termina (liebre llegó a null), retornar false.
        return false;
    }

    public static Nodo encontrarInicioCiclo(Nodo head) {
        // TODO 2: Primero, detectar si hay ciclo usando la técnica de Floyd.
        //         Si no hay ciclo, retornar null.
        //         Si hay ciclo (tortuga y liebre se encuentran):
        //           Mover tortuga de vuelta a head.
        //           Avanzar AMBOS punteros 1 posición a la vez.
        //           El nodo donde se reúnan es el INICIO del ciclo.
        //         Retornar ese nodo.
        return null;
    }

    public static int longitudCiclo(Nodo head) {
        // TODO 3: Si no hay ciclo, retornar 0.
        //         Si hay ciclo, encontrar el punto de encuentro (Floyd).
        //         Desde el punto de encuentro, mantener uno fijo y mover el otro
        //         contando saltos hasta que vuelvan a encontrarse.
        //         Ese contador es la longitud del ciclo.
        return 0;
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 08: Detección de Ciclos (Floyd) ===\n");

        // TODO 4: Construir una cadena de nodos: 1 → 2 → 3 → 4 → 5 → 6
        //         Crear un ciclo haciendo que el nodo 6 apunte al nodo 3.
        //         (6.siguiente = nodo3)

        // TODO 5: Invocar las tres funciones y mostrar resultados:
        //         tieneCiclo(head)           → Esperado: true
        //         encontrarInicioCiclo(head) → Esperado: nodo con valor 3
        //         longitudCiclo(head)        → Esperado: 4 (ciclo: 3→4→5→6→3)

        System.out.println("--- Prueba SIN ciclo ---");
        Nodo a = new Nodo(1);
        Nodo b = new Nodo(2);
        Nodo c = new Nodo(3);
        a.siguiente = b;
        b.siguiente = c;
        System.out.println("¿Tiene ciclo? " + tieneCiclo(a));  // false

        System.out.println("\n--- Prueba CON ciclo ---");
        System.out.println("(Implementa los TODOs 4 y 5 para la prueba con ciclo)");

        System.out.println("\n=== FIN EJERCICIO 08 ===");
    }
}
