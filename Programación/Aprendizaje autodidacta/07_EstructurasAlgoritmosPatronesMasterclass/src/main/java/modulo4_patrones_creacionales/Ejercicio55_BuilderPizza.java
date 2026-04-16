package modulo4_patrones_creacionales;

/**
 * ============================================================================
 * EJERCICIO 55: Builder — Construcción de Objeto Complejo (Pizza)
 * ============================================================================
 * 📚 Teoría: teoria/04_Patrones_Creacionales.md - Sección 4.5
 *
 * CONTEXTO:
 * Una pizza tiene muchos parámetros opcionales: tamaño, tipo de masa,
 * salsa, queso, y múltiples ingredientes. Sin Builder, necesitarías un
 * constructor con 10+ parámetros (anti-patrón "Telescoping Constructor").
 *
 * Builder permite construir la pizza paso a paso, con una API clara
 * y validación antes de la construcción final.
 *
 * Arquitectura:
 * - Class Pizza: objeto inmutable con todos los atributos.
 * - Class PizzaBuilder: acumula configuración y produce la Pizza.
 *
 * RESTRICCIONES:
 * - Pizza es INMUTABLE (todos los campos final, sin setters).
 * - PizzaBuilder tiene métodos setXXX() que retornan 'this' (method chaining).
 * - El método build() valida y crea la Pizza.
 * - Los ingredientes se almacenan en un array manual (max 10).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio55_BuilderPizza {

    // ====================================================================
    //  CLASE PIZZA (Inmutable)
    // ====================================================================
    static class Pizza {
        // TODO 1: Declarar campos FINAL:
        //         String tamano ("Pequeña", "Mediana", "Grande", "Familiar")
        //         String masa ("Fina", "Normal", "Gruesa", "Rellena")
        //         String salsa ("Tomate", "BBQ", "Carbonara", "Sin salsa")
        //         String queso ("Mozzarella", "Cheddar", "Cuatro quesos", "Sin queso")
        //         String[] ingredientes (max 10)
        //         int numIngredientes
        //         boolean extraQueso
        //         boolean bordeRelleno

        // TODO 2: Constructor PRIVADO que recibe un PizzaBuilder como parámetro.
        //         Copia TODOS los valores del builder a los campos del Pizza.
        //         (El constructor es privado para que solo el Builder pueda crear Pizzas).

        @Override
        public String toString() {
            // TODO 3: Retornar descripción formateada de la pizza:
            //         "🍕 Pizza [Grande] - Masa: Fina
            //            Salsa: Tomate | Queso: Mozzarella
            //            Ingredientes: Jamón, Champiñones, Aceitunas
            //            Extras: +Queso extra, +Borde relleno"
            return "Pizza sin configurar";
        }
    }

    // ====================================================================
    //  BUILDER
    // ====================================================================
    static class PizzaBuilder {
        // TODO 4: Campos mutables que espejan los de Pizza:
        //         Inicializar con valores por defecto razonables:
        //         tamano="Mediana", masa="Normal", salsa="Tomate",
        //         queso="Mozzarella", extraQueso=false, bordeRelleno=false
        //         ingredientes = new String[10], numIngredientes = 0

        // TODO 5: Métodos setters con method chaining (retornan this):
        //         PizzaBuilder tamano(String tamano) { this.tamano = tamano; return this; }
        //         PizzaBuilder masa(String masa) { ... return this; }
        //         PizzaBuilder salsa(String salsa) { ... return this; }
        //         PizzaBuilder queso(String queso) { ... return this; }
        //         PizzaBuilder ingrediente(String ingrediente) {
        //             // Añadir al array si hay espacio
        //             return this;
        //         }
        //         PizzaBuilder extraQueso(boolean extra) { ... return this; }
        //         PizzaBuilder bordeRelleno(boolean borde) { ... return this; }

        // TODO 6: Método build() que:
        //         1. Valida que tamano no sea null/vacío.
        //         2. Valida que haya al menos 1 ingrediente.
        //         3. Crea y retorna new Pizza(this).
        //         Si falla validación, lanzar IllegalStateException con mensaje claro.
    }

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 55: Builder Pizza ===\n");

        // TODO 7: Crear varias pizzas usando el builder con method chaining:
        //
        //         Pizza margarita = new PizzaBuilder()
        //             .tamano("Grande")
        //             .masa("Fina")
        //             .salsa("Tomate")
        //             .queso("Mozzarella")
        //             .ingrediente("Tomate fresco")
        //             .ingrediente("Albahaca")
        //             .build();
        //
        //         Pizza bbq = new PizzaBuilder()
        //             .tamano("Familiar")
        //             .masa("Gruesa")
        //             .salsa("BBQ")
        //             .queso("Cheddar")
        //             .ingrediente("Pollo")
        //             .ingrediente("Bacon")
        //             .ingrediente("Cebolla caramelizada")
        //             .extraQueso(true)
        //             .bordeRelleno(true)
        //             .build();
        //
        //         System.out.println(margarita);
        //         System.out.println(bbq);

        System.out.println("(Implementa los TODOs para ver resultados)");

        System.out.println("\n=== FIN EJERCICIO 55 ===");
    }
}
