package com.masterclass.collections.nivel07_hashset;

import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * EJERCICIO 25 — HashSet + HashMap: Integración de Colecciones
 * ==============================================================
 * Teoría de referencia: teoria/05_HashSet_y_Conjuntos.md  (§ 4, 6, 7)
 *
 * Objetivo: Combinar HashSet con HashMap y ArrayList para resolver problemas
 * que requieren unicidad + conteo + orden.
 *
 * Restricción: Usa el tipo de colección indicado en cada TODO.
 */
public class Ejercicio25_HashSetIntegracion {

    // TODO 1: Implementa `categoriasDisponibles`.
    //   - Recibe un ArrayList<Producto>.
    //   - Retorna un HashSet<String> con todas las categorías únicas presentes.
    //   - Itera los productos y añade su categoría al set.
    public static HashSet<String> categoriasDisponibles(ArrayList<Producto> productos) {
        return null;
    }

    // TODO 2: Implementa `productosPorCategoriaConUnicidad`.
    //   - Recibe un ArrayList<Producto> (puede tener productos duplicados por id).
    //   - Retorna un HashMap<String, HashSet<Producto>> donde la clave es la categoría
    //     y el valor es un HashSet con los productos ÚNICOS de esa categoría.
    //   - Usa computeIfAbsent para gestionar los sets internos.
    public static HashMap<String, HashSet<Producto>> productosPorCategoriaConUnicidad(
            ArrayList<Producto> productos) {
        return null;
    }

    // TODO 3: Implementa `clientesConPedidosMultiples`.
    //   - Recibe un HashMap<String, ArrayList<String>> donde la clave es un clienteId
    //     y el valor es la lista de productoIds que ha pedido (puede tener repetidos).
    //   - Retorna un HashSet<String> con los IDs de los clientes que han pedido
    //     al menos un producto MÁS DE UNA VEZ.
    //   - Para cada cliente, compara el tamaño de su lista con el tamaño de un
    //     HashSet creado a partir de ella. Si difieren, el cliente tiene repetidos.
    public static HashSet<String> clientesConPedidosMultiples(
            HashMap<String, ArrayList<String>> pedidos) {
        return null;
    }

    // TODO 4: Implementa `productosEnComun`.
    //   - Recibe un HashMap<String, ArrayList<String>> (cliente → lista de productoIds).
    //   - Retorna un HashSet<String> con los productoIds que TODOS los clientes han pedido.
    //   - Convierte cada lista a un HashSet, luego calcula la intersección iterativa
    //     usando retainAll sobre una copia del primer set.
    //   - Si el mapa está vacío, retorna un set vacío.
    public static HashSet<String> productosEnComun(
            HashMap<String, ArrayList<String>> pedidos) {
        return null;
    }

    // TODO 5: Implementa `historialUnicoOrdenado`.
    //   - Recibe un ArrayList<String> con acciones del usuario (ej: "login", "view", "login").
    //   - Retorna un ArrayList<String> con las acciones únicas en orden de primera aparición.
    //   - Usa un LinkedHashSet para eliminar duplicados conservando el orden.
    //   - Convierte el LinkedHashSet a ArrayList para retornarlo.
    public static ArrayList<String> historialUnicoOrdenado(ArrayList<String> acciones) {
        return null;
    }

    // TODO 6 (desafío): Implementa `analisisCompletoCatalogo`.
    //   - Recibe dos ArrayList<Producto>: catalogoA y catalogoB.
    //   - Retorna un HashMap<String, Object> con las siguientes claves:
    //     "soloA"       → HashSet<Producto> con productos exclusivos de A
    //     "soloB"       → HashSet<Producto> con productos exclusivos de B
    //     "ambos"       → HashSet<Producto> con productos en ambos catálogos
    //     "totalUnicos" → Integer con el número total de productos únicos (A ∪ B)
    //   - Convierte las listas a HashSets y opera sobre ellos.
    public static HashMap<String, Object> analisisCompletoCatalogo(
            ArrayList<Producto> catalogoA, ArrayList<Producto> catalogoB) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 25 — HashSet + HashMap: Integración ===\n");

        // Preparar datos
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        productos.add(new Producto("P02", "Ratón",   25.0, "periféricos", "input"));
        productos.add(new Producto("P03", "Monitor", 299.0, "pantallas",  "output"));
        productos.add(new Producto("P01", "Teclado2", 50.0, "periféricos", "input")); // dup

        // -- categoriasDisponibles --
        System.out.println("Categorías: " + categoriasDisponibles(productos));

        // -- productosPorCategoriaConUnicidad --
        System.out.println("Productos por categoría: " +
                productosPorCategoriaConUnicidad(productos));

        // -- clientesConPedidosMultiples --
        HashMap<String, ArrayList<String>> pedidos = new HashMap<>();
        pedidos.put("C1", new ArrayList<>(java.util.List.of("P01", "P02", "P01")));
        pedidos.put("C2", new ArrayList<>(java.util.List.of("P02", "P03")));
        System.out.println("\nClientes con repetidos: " + clientesConPedidosMultiples(pedidos));

        // -- productosEnComun --
        System.out.println("Productos en común: " + productosEnComun(pedidos));

        // -- historialUnicoOrdenado --
        ArrayList<String> acciones = new ArrayList<>(
                java.util.List.of("login", "view", "login", "purchase", "view", "logout"));
        System.out.println("\nHistorial único ordenado: " + historialUnicoOrdenado(acciones));

        // -- analisisCompletoCatalogo --
        ArrayList<Producto> catA = new ArrayList<>(java.util.List.of(
                new Producto("P01", "Teclado", 45.0, "peri", "in"),
                new Producto("P02", "Ratón", 25.0, "peri", "in")));
        ArrayList<Producto> catB = new ArrayList<>(java.util.List.of(
                new Producto("P02", "Ratón", 25.0, "peri", "in"),
                new Producto("P03", "Monitor", 299.0, "pant", "out")));
        System.out.println("Análisis: " + analisisCompletoCatalogo(catA, catB));
    }
}
