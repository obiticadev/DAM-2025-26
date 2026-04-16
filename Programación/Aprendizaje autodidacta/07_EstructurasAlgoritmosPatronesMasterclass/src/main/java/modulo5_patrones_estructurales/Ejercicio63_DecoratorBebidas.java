package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 63: Decorator — Decoradores de Bebidas (Estilo Starbucks)
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.3
 *
 * CONTEXTO:
 * Una cafetería tiene bebidas base (Espresso, Latte, Té) y extras
 * (Leche, Chocolate, Crema, Caramelo). Cada extra añade coste y
 * modifica la descripción. Los decoradores se APILAN: puedes tener
 * un "Espresso + Leche + Chocolate + Crema extra".
 *
 * Sin Decorator necesitarías: Espresso, EspressoConLeche,
 * EspressoConLecheYChocolate... la explosión combinatoria es imposible.
 *
 * Implementa:
 * - Interfaz Bebida: getDescripcion(), getPrecio().
 * - Bebidas base: Espresso($1.99), Latte($2.49), Te($1.50).
 * - Clase abstracta DecoradorBebida: envuelve una Bebida.
 * - Decoradores: Leche(+$0.50), Chocolate(+$0.75), Crema(+$0.30),
 *   Caramelo(+$0.60), TamanioGrande(+$1.00).
 *
 * RESTRICCIONES:
 * - Cada decorador ENVUELVE a otro componente (composición, no herencia).
 * - Los precios se suman al delegar al componente envuelto.
 * - Las descripciones se concatenan.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio63_DecoratorBebidas {

    // TODO 1: Definir la interfaz Bebida:
    //         String getDescripcion();
    //         double getPrecio();

    // TODO 2: Implementar tres bebidas base:
    //         Espresso: descripcion="Espresso", precio=1.99
    //         Latte: descripcion="Latte", precio=2.49
    //         Te: descripcion="Té Verde", precio=1.50

    // TODO 3: Implementar la clase abstracta DecoradorBebida:
    //         - Implementa Bebida.
    //         - Campo protegido: Bebida bebidaEnvuelta.
    //         - Constructor que recibe la Bebida a envolver.
    //         - Por defecto, delega getDescripcion() y getPrecio() al envuelto.

    // TODO 4: Implementar decoradores concretos:
    //         Leche: getDescripcion() → envuelta.getDescripcion() + " + Leche"
    //                getPrecio() → envuelta.getPrecio() + 0.50
    //         Chocolate: +$0.75
    //         Crema: +$0.30
    //         Caramelo: +$0.60
    //         TamanioGrande: " (Grande)" +$1.00

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 63: Decorator Bebidas ===\n");

        // TODO 5: Crear bebidas decoradas y mostrar precio final:
        //
        //         // Espresso simple
        //         Bebida b1 = new Espresso();
        //         imprimirPedido(b1);
        //
        //         // Latte + Leche + Chocolate
        //         Bebida b2 = new Chocolate(new Leche(new Latte()));
        //         imprimirPedido(b2);
        //
        //         // Espresso + Leche + Crema + Caramelo + Grande
        //         Bebida b3 = new TamanioGrande(
        //                       new Caramelo(
        //                         new Crema(
        //                           new Leche(
        //                             new Espresso()))));
        //         imprimirPedido(b3);
        //
        //         // Té simple + Leche
        //         Bebida b4 = new Leche(new Te());
        //         imprimirPedido(b4);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 63 ===");
    }

    static void imprimirPedido(Object bebida) {
        // Se usará con la interfaz Bebida
        System.out.println("  (Implementa para ver el pedido formateado)");
    }
}
