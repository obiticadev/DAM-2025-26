package com.masterclass.collections.nivel04_hashmap_core;

import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * EJERCICIO 13 — HashMap con Objetos Propios como Valor
 * =======================================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 7 — HashMap con objetos propios)
 *
 * Objetivo: Usar HashMap<String, Producto> como índice de acceso rápido O(1)
 * por ID. Este patrón es el repositorio más simple que existe en Java.
 *
 * Restricción: NO usar Streams. Solo métodos de HashMap y bucles for-each.
 */
public class Ejercicio13_HashMapConObjetos {

    // TODO 1: Implementa `indexarPorId`.
    //   - Recibe una lista de Productos y retorna un HashMap<String, Producto>
    //     donde la clave es el ID del producto y el valor es el Producto.
    //   - Si dos productos tuviesen el mismo ID (no debería pasar), el último prevalece.
    //   - Usa un bucle for-each y put().
    public static HashMap<String, Producto> indexarPorId(ArrayList<Producto> productos) {
        return null;
    }

    // TODO 2: Implementa `buscarPorId`.
    //   - Retorna un Optional<Producto> con el producto de ese ID, o Optional.empty() si no existe.
    //   - Usa get() y comprueba si retorna null para decidir qué Optional devolver.
    public static Optional<Producto> buscarPorId(HashMap<String, Producto> indice, String id) {
        return Optional.empty();
    }

    // TODO 3: Implementa `actualizarPrecio`.
    //   - Busca el producto con ese ID en el índice y actualiza su precio.
    //   - Retorna true si el producto existía y se actualizó, false si no existía.
    //   - Usa containsKey() antes de acceder, o comprueba si get() retorna null.
    public static boolean actualizarPrecio(HashMap<String, Producto> indice,
                                            String id, double nuevoPrecio) {
        return false;
    }

    // TODO 4: Implementa `filtrarPorCategoria`.
    //   - Retorna un NUEVO HashMap<String, Producto> con solo los productos
    //     cuya categoría coincida (equals) con `categoria`.
    //   - Itera con entrySet() y añade al nuevo mapa los que cumplan la condición.
    public static HashMap<String, Producto> filtrarPorCategoria(
            HashMap<String, Producto> indice, String categoria) {
        return null;
    }

    // TODO 5: Implementa `eliminarPorCategoria`.
    //   - Elimina del mapa (en sitio) todos los productos de la categoría dada.
    //   - Retorna el número de productos eliminados.
    //   - PISTA: no puedes eliminar dentro de un for-each con entrySet() directamente
    //     (ConcurrentModificationException). Recoge primero las claves a eliminar
    //     en un ArrayList auxiliar y luego elimínalas.
    public static int eliminarPorCategoria(HashMap<String, Producto> indice,
                                            String categoria) {
        return -1;
    }

    // TODO 6: Implementa `productoMasCaro`.
    //   - Recorre los valores del mapa y retorna el Producto con mayor precio.
    //   - Retorna null si el mapa está vacío.
    //   - Usa un bucle for-each sobre mapa.values() y actualiza un candidato.
    public static Producto productoMasCaro(HashMap<String, Producto> indice) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 13 — HashMap con Objetos Propios ===\n");

        ArrayList<Producto> lista = new ArrayList<>();
        lista.add(new Producto("P001", "Teclado",     89.99, "Periféricos", "Hardware"));
        lista.add(new Producto("P002", "Ratón",       45.50, "Periféricos", "Hardware"));
        lista.add(new Producto("P003", "Monitor",    320.00, "Pantallas",   "Hardware"));
        lista.add(new Producto("P004", "Auriculares", 79.99, "Audio",       "Hardware"));
        lista.add(new Producto("P005", "Antivirus",   29.99, "Software",    "Licencia"));
        lista.add(new Producto("P006", "Silla",      199.00, "Mobiliario",  "Accesorio"));

        HashMap<String, Producto> indice = indexarPorId(lista);
        System.out.println("Índice creado con " + indice.size() + " entradas.");

        // -- buscarPorId --
        System.out.println("\nBuscar P003: " + buscarPorId(indice, "P003"));
        System.out.println("Buscar P999: " + buscarPorId(indice, "P999"));

        // -- actualizarPrecio --
        System.out.println("\nActualizar precio P001 a 99.99: " + actualizarPrecio(indice, "P001", 99.99));
        System.out.println("P001 nuevo precio: " + indice.get("P001").getPrecio());
        System.out.println("Actualizar P999 (no existe): " + actualizarPrecio(indice, "P999", 50.0));

        // -- filtrarPorCategoria --
        HashMap<String, Producto> perifericos = filtrarPorCategoria(indice, "Periféricos");
        System.out.println("\nProductos en 'Periféricos': " + perifericos.keySet());
        System.out.println("Índice original intacto, tamaño: " + indice.size());

        // -- eliminarPorCategoria --
        HashMap<String, Producto> copia = indexarPorId(lista);
        int eliminados = eliminarPorCategoria(copia, "Periféricos");
        System.out.println("\nEliminados de 'Periféricos': " + eliminados);
        System.out.println("Tamaño restante: " + copia.size());

        // -- productoMasCaro --
        System.out.println("\nProducto más caro: " + productoMasCaro(indice));
    }
}
