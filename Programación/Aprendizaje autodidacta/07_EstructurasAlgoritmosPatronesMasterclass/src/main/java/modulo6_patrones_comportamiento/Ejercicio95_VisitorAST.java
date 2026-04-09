package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 95: Visitor — Exportar AST a Distintos Formatos
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.11
 *
 * CONTEXTO:
 * Un AST (Abstract Syntax Tree) representa expresiones matemáticas como
 * árbol. Visitor permite exportar el mismo AST a diferentes formatos
 * (notación infija, postfija, XML) sin modificar las clases del AST.
 *
 * Nodos del AST:
 * - NumeroNodo: hoja con un valor numérico.
 * - OperacionNodo: nodo con operador (+,-,*,/) y dos hijos.
 *
 * Visitors:
 * - InfijaVisitor: exporta como "((2 + 3) * 4)".
 * - PostfijaVisitor: exporta como "2 3 + 4 *".
 * - XMLVisitor: exporta como "<mul><sum><num>2</num><num>3</num></sum>..."
 *
 * RESTRICCIONES:
 * - Los nodos del AST NO contienen lógica de exportación.
 * - Cada visitor recorre el árbol y construye el resultado.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio95_VisitorAST {

    interface NodoVisitor {
        // TODO 1: String visitarNumero(NumeroNodo n)
        //         String visitarOperacion(OperacionNodo n)
    }

    interface NodoAST {
        // TODO 2: String aceptar(NodoVisitor v)
    }

    // TODO 3: Implementar NumeroNodo (Leaf):
    //         double valor.
    //         aceptar(v) → v.visitarNumero(this).

    // TODO 4: Implementar OperacionNodo (Composite):
    //         char operador (+,-,*,/), NodoAST izquierdo, NodoAST derecho.
    //         aceptar(v) → v.visitarOperacion(this).

    // TODO 5: Implementar InfijaVisitor:
    //         visitarNumero → String.valueOf(n.valor)
    //         visitarOperacion → "(" + izq.aceptar(this) + " op " + der.aceptar(this) + ")"

    // TODO 6: Implementar PostfijaVisitor:
    //         visitarNumero → String.valueOf(n.valor)
    //         visitarOperacion → izq + " " + der + " " + op

    // TODO 7: Implementar EvaluadorVisitor:
    //         visitarNumero → String.valueOf(n.valor) — retorna como String.
    //         visitarOperacion → evaluar y retornar el resultado como String.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 95: Visitor AST ===\n");

        // Expresión: (2 + 3) * 4
        // TODO 8: Construir el AST:
        //         NodoAST expr = new OperacionNodo('*',
        //             new OperacionNodo('+',
        //                 new NumeroNodo(2),
        //                 new NumeroNodo(3)),
        //             new NumeroNodo(4));
        //
        //         System.out.println("Infija:   " + expr.aceptar(new InfijaVisitor()));
        //         // ((2.0 + 3.0) * 4.0)
        //         System.out.println("Postfija: " + expr.aceptar(new PostfijaVisitor()));
        //         // 2.0 3.0 + 4.0 *
        //         System.out.println("Evaluado: " + expr.aceptar(new EvaluadorVisitor()));
        //         // 20.0

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 95 ===");
    }
}
