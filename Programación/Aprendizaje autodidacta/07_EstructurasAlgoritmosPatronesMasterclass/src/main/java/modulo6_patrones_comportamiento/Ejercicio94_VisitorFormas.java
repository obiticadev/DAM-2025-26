package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 94: Visitor — Calcular Área y Perímetro de Formas
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.11
 *
 * CONTEXTO:
 * Tienes una jerarquía de formas geométricas (Circulo, Rectangulo, Triangulo).
 * Quieres añadir operaciones (calcular área, perímetro, dibujar) SIN
 * modificar las clases de las formas. Visitor permite esto.
 *
 * Implementa:
 * - Interfaz Visitor: visitarCirculo(), visitarRectangulo(), visitarTriangulo().
 * - Interfaz Forma: aceptar(Visitor v).
 * - AreaVisitor: calcula el área de cada forma.
 * - PerimetroVisitor: calcula el perímetro.
 * - DibujarVisitor: imprime arte ASCII de cada forma.
 *
 * RESTRICCIONES:
 * - Cada Forma solo tiene aceptar(Visitor v) → v.visitarX(this).
 * - La lógica de cálculo está SOLO en el Visitor, no en la Forma.
 * - Double Dispatch: forma.aceptar(visitor) → visitor.visitarForma(this).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio94_VisitorFormas {

    interface FormaVisitor {
        // TODO 1: void visitarCirculo(Circulo94 c)
        //         void visitarRectangulo(Rectangulo94 r)
        //         void visitarTriangulo(Triangulo94 t)
    }

    interface Forma94 {
        // TODO 2: void aceptar(FormaVisitor v)
    }

    // TODO 3: Implementar Circulo94(double radio), Rectangulo94(double ancho, alto),
    //         Triangulo94(double a, double b, double c — lados).
    //         Cada uno tiene aceptar(): v.visitarCirculo(this), etc.

    // TODO 4: Implementar AreaVisitor:
    //         visitarCirculo → Math.PI * r * r
    //         visitarRectangulo → ancho * alto
    //         visitarTriangulo → fórmula de Herón: s = (a+b+c)/2, area = sqrt(s(s-a)(s-b)(s-c))
    //         Imprimir resultado.

    // TODO 5: Implementar PerimetroVisitor:
    //         visitarCirculo → 2 * Math.PI * r
    //         visitarRectangulo → 2 * (ancho + alto)
    //         visitarTriangulo → a + b + c

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 94: Visitor Formas ===\n");

        // TODO 6:
        //         Forma94[] formas = {
        //             new Circulo94(5),
        //             new Rectangulo94(10, 4),
        //             new Triangulo94(3, 4, 5)
        //         };
        //
        //         FormaVisitor areaCalc = new AreaVisitor();
        //         FormaVisitor periCalc = new PerimetroVisitor();
        //
        //         System.out.println("=== ÁREAS ===");
        //         for (Forma94 f : formas) f.aceptar(areaCalc);
        //
        //         System.out.println("\n=== PERÍMETROS ===");
        //         for (Forma94 f : formas) f.aceptar(periCalc);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 94 ===");
    }
}
