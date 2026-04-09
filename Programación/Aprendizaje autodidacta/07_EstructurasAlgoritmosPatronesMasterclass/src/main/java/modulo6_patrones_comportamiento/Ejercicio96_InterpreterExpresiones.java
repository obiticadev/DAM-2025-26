package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 96: Interpreter — Evaluador de Expresiones Matemáticas
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.12
 *
 * CONTEXTO:
 * Interpreter define una gramática y un intérprete. Aquí construyes
 * un evaluador de expresiones aritméticas que parsea y evalúa cadenas
 * como "3 + 5 * 2" respetando precedencia de operadores.
 *
 * Gramática simplificada (sin precedencia, izquierda a derecha):
 * expr     := numero (('+' | '-' | '*' | '/') numero)*
 * numero   := [0-9]+
 *
 * Implementa:
 * - Interfaz Expresion: int interpretar().
 * - Numero (terminal): contiene un valor int.
 * - Suma, Resta, Multiplicacion, Division (no-terminales).
 * - Parser: convierte String "3 + 5 * 2" en árbol de Expresion.
 *
 * RESTRICCIONES:
 * - Los tokens están separados por espacios para simplificar el parsing.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio96_InterpreterExpresiones {

    interface Expresion {
        // TODO 1: int interpretar()
    }

    // TODO 2: Implementar Numero (terminal):
    //         int valor, interpretar() → valor.

    // TODO 3: Implementar Suma, Resta, Multiplicacion, Division:
    //         Cada una tiene: Expresion izq, Expresion der.
    //         interpretar() → izq.interpretar() op der.interpretar().

    // TODO 4: Implementar Parser:
    //         Expresion parsear(String expresion):
    //         - Dividir la cadena por espacios: tokens.
    //         - Construir el árbol de izquierda a derecha:
    //           resultado = new Numero(tokens[0])
    //           Para cada par (operador, número) siguiente:
    //             resultado = new Operacion(resultado, new Numero(valor))
    //         - Retornar el árbol raíz.
    //         Dividir sin split(): recorrer char por char o usar indexOf.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 96: Interpreter ===\n");

        // TODO 5:
        //         String[] expresiones = {
        //             "3 + 5",
        //             "10 - 3 + 2",
        //             "4 * 5 + 2",
        //             "100 / 5 * 2",
        //             "7 + 3 * 2 - 1"
        //         };
        //         Parser parser = new Parser();
        //         for (String expr : expresiones) {
        //             Expresion arbol = parser.parsear(expr);
        //             System.out.println(expr + " = " + arbol.interpretar());
        //         }

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 96 ===");
    }
}
