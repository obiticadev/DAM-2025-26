package modulo1_estructuras_lineales;

/**
 * ============================================================================
 * EJERCICIO 12: Validador de Paréntesis Balanceados
 * ============================================================================
 * 📚 Teoría: teoria/01_Fundamentos_Estructuras_Lineales.md - Sección 1.5
 *
 * CONTEXTO:
 * Este es un clásico absoluto de las entrevistas técnicas. Dada una cadena
 * que contiene caracteres de apertura ('(', '{', '[') y cierre (')', '}', ']'),
 * determinar si la cadena está correctamente balanceada.
 *
 * Ejemplos:
 *   "({[]})"  → ✅ válido
 *   "({[}])"  → ❌ inválido (cierre desordenado)
 *   "((("     → ❌ inválido (no se cierran)
 *   ""        → ✅ válido (cadena vacía está balanceada)
 *
 * La solución clásica usa un STACK:
 * - Al encontrar un carácter de apertura → push al stack.
 * - Al encontrar uno de cierre → pop del stack y verificar que coincida.
 * - Al final, el stack debe estar vacío.
 *
 * RESTRICCIONES:
 * - Debes usar TU propio Stack (del Ejercicio 10 u 11), NO java.util.Stack.
 *   Para simplificar, puedes usar un array de char[] como stack interno.
 * - Soportar los tres tipos: (), {}, [].
 * - Ignorar cualquier carácter que no sea paréntesis/llaves/corchetes.
 *
 * COMPLEJIDAD OBJETIVO:
 * - Tiempo: O(n) — un solo recorrido de la cadena.
 * - Espacio: O(n) — en el peor caso, todos son de apertura.
 * ============================================================================
 */
public class Ejercicio12_ValidadorParentesis {

    public static boolean esValido(String expresion) {
        // TODO 1: Crear un array de char[] como stack manual (capacidad = expresion.length()).
        //         Inicializar un puntero 'top' a -1.
        //         Recorrer cada carácter de la expresión.

        // TODO 2: Si el carácter es de APERTURA ('(', '{', '['):
        //         Incrementar top y almacenar el carácter en stack[top] (push).

        // TODO 3: Si el carácter es de CIERRE (')', '}', ']'):
        //         Si el stack está vacío (top == -1), retornar false inmediatamente
        //         (hay un cierre sin apertura). Si no, hacer pop (leer stack[top] y
        //         decrementar top). Verificar que el carácter popeado sea la pareja
        //         correcta del cierre actual. Si no coincide, retornar false.

        // TODO 4: Al terminar el recorrido, retornar true solo si el stack
        //         está vacío (top == -1). Si quedan elementos, hay aperturas sin cierre.
        return false;
    }

    private static char parejaApertura(char cierre) {
        // TODO 5: Dado un carácter de cierre, retornar su pareja de apertura.
        //         ')' → '('    '}' → '{'    ']' → '['
        //         Usar un switch o if-else.
        return ' ';
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 12: Validador de Paréntesis ===\n");

        String[] pruebas = {
            "({[]})",           // ✅ válido
            "()",               // ✅ válido
            "({[}])",           // ❌ inválido
            "(((",             // ❌ inválido
            "",                 // ✅ válido (vacío)
            "([{}])({})",       // ✅ válido
            "((())",            // ❌ inválido
            "{[()]}",           // ✅ válido
            "hola(mundo[cruel{!}])", // ✅ válido (ignora letras)
            ")("                // ❌ inválido
        };

        boolean[] esperados = {
            true, true, false, false, true,
            true, false, true, true, false
        };

        int aciertos = 0;
        for (int i = 0; i < pruebas.length; i++) {
            boolean resultado = esValido(pruebas[i]);
            boolean correcto = resultado == esperados[i];
            if (correcto) aciertos++;

            System.out.printf("  \"%s\" → %s %s%n",
                    pruebas[i],
                    resultado ? "✅ válido  " : "❌ inválido",
                    correcto ? "" : "⚠️ INCORRECTO (esperado: " + esperados[i] + ")");
        }

        System.out.printf("%n  Resultado: %d/%d pruebas correctas%n", aciertos, pruebas.length);

        System.out.println("\n=== FIN EJERCICIO 12 ===");
    }
}
