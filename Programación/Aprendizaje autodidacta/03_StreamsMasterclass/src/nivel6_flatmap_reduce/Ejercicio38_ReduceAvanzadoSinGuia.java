package nivel6_flatmap_reduce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import modelos.Pedido;
import modelos.Producto;

/**
 * EJERCICIO 38 — REDUCE AVANZADO CON PEDIDOS (SIN GUÍA)
 * 
 * Objetivo: Aplicar reduce en escenarios reales con objetos complejos.
 */
public class Ejercicio38_ReduceAvanzadoSinGuia {

    public static void demostracion() {
        System.out.println("--- REDUCE AVANZADO ---");
        System.out.println("reduce no es solo para sumar números. Puedes reducir cualquier cosa:");
        System.out.println("  - Encontrar el pedido más caro");
        System.out.println("  - Combinar listas");
        System.out.println("  - Construir un resumen textual complejo\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 38: ANÁLISIS DE PEDIDOS CON REDUCE ---");
        Producto laptop = new Producto("Laptop Pro", "Electrónica", 1200.0, 15, true);
        Producto raton = new Producto("Ratón Gamer", "Electrónica", 45.0, 120, true);
        Producto camiseta = new Producto("Camiseta Dev", "Ropa", 18.0, 200, true);
        Producto cafe = new Producto("Café Premium", "Alimentación", 12.0, 500, true);
        Producto monitor = new Producto("Monitor 4K", "Electrónica", 350.0, 8, true);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("P001", "Cliente A", Arrays.asList(laptop, raton), false));       // 1245
        pedidos.add(new Pedido("P002", "Cliente B", Arrays.asList(camiseta, cafe, monitor), true)); // 380
        pedidos.add(new Pedido("P003", "Cliente C", Arrays.asList(raton, cafe), false));          // 57

        // TODO 1: Usa reduce para encontrar el Pedido con el total más alto.
        // Devuelve el ID del pedido o "Ninguno".
        String pedidoMasCaro = "Ninguno"; // <- Escribe aquí

        // TODO 2: Calcula el total combinado de TODOS los pedidos usando reduce sobre calcularTotal().
        double totalGlobal = 0; // <- Escribe aquí

        // TODO 3: Cuenta el número total de artículos en todos los pedidos usando reduce.
        int totalArticulos = 0; // <- Escribe aquí

        System.out.println("Pedido más caro: " + pedidoMasCaro);
        System.out.println("Total global: " + totalGlobal);
        System.out.println("Total artículos: " + totalArticulos);

        // --- VALIDACIÓN ---
        if (pedidoMasCaro.equals("P001") && totalGlobal == 1682.0 && totalArticulos == 7) {
            System.out.println(">> CORRECTO: reduce avanzado sobre objetos Pedido.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] pedidoMasCaro=P001(1245€), totalGlobal=1682, totalArticulos=7.");
        }
    }
}
