package com.masterclass.collections.nivel02_arraylist_intermedio;

import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.Optional;

/**
 * EJERCICIO 06 — ArrayList con Objetos Propios
 * ==============================================
 * Teoría de referencia: teoria/01_ArrayList_Fundamentos.md  (§ 7 — equals y hashCode)
 *
 * Objetivo: Aplicar ArrayList sobre instancias de la clase Producto, que ya
 * implementa equals() basado en el id. Practicar búsqueda, filtrado y ordenación
 * sobre colecciones de objetos.
 *
 * Restricción: NO se permite usar Streams. Solo bucles, Comparator y métodos de ArrayList.
 */
public class Ejercicio06_ArrayListConObjetos {

    // TODO 1: Implementa `filtrarPorPrecioMaximo`.
    //   - Retorna un NUEVO ArrayList<Producto> con solo los productos cuyo precio
    //     sea estrictamente menor o igual a `precioMax`.
    //   - La lista original no se modifica.
    //   - Usa un bucle for-each con condicional.
    public static ArrayList<Producto> filtrarPorPrecioMaximo(ArrayList<Producto> productos,
                                                             double precioMax) {
        return null;
    }

    // TODO 2: Implementa `buscarPorNombre`.
    //   - Recorre la lista y retorna el primer Producto cuyo nombre sea igual a `nombre`
    //     (equals, case-sensitive).
    //   - Si no existe ninguno, retorna Optional.empty().
    //   - Usa Optional.of() cuando lo encuentres y Optional.empty() si no.
    public static Optional<Producto> buscarPorNombre(ArrayList<Producto> productos, String nombre) {
        return Optional.empty();
    }

    // TODO 3: Implementa `ordenarPorPrecioAscendente`.
    //   - Ordena la lista EN SU LUGAR de menor a mayor precio.
    //   - Usa lista.sort() con Comparator.comparingDouble.
    public static void ordenarPorPrecioAscendente(ArrayList<Producto> productos) {
        // implementa aquí
    }

    // TODO 4: Implementa `encontrarMasBarato`.
    //   - Retorna el Producto con el precio más bajo de la lista.
    //   - Si la lista está vacía, retorna null.
    //   - Usa un bucle for-each; actualiza una variable "candidato" al encontrar uno menor.
    public static Producto encontrarMasBarato(ArrayList<Producto> productos) {
        return null;
    }

    // TODO 5: Implementa `agruparPorCategoria`.
    //   - Recorre la lista y retorna un String resumen con el formato:
    //     "Categoría X: N productos" para cada categoría, separados por saltos de línea.
    //   - Puedes usar un segundo ArrayList<String> para acumular las categorías ya vistas
    //     o bien un simple conteo manual.
    //   - Sugerencia: el orden de aparición de categorías en el resultado es el de
    //     primera aparición en la lista.
    //   - Este ejercicio es una introducción manual a lo que HashMap hace en el Bloque II.
    public static String resumenPorCategoria(ArrayList<Producto> productos) {
        return "";
    }

    // TODO 6: Implementa `contienePorId`.
    //   - Retorna true si existe algún Producto con el id dado.
    //   - Fíjate: Producto.equals() compara por id. Puedes aprovechar ese contrato.
    //   - Usa un bucle for-each (no streams).
    public static boolean contienePorId(ArrayList<Producto> productos, String id) {
        return false;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run para comprobar visualmente
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 06 — ArrayList con Objetos Propios ===\n");

        ArrayList<Producto> catalogo = new ArrayList<>();
        catalogo.add(new Producto("P001", "Teclado Mecánico", 89.99, "Periféricos", "Hardware", 15));
        catalogo.add(new Producto("P002", "Ratón Ergonómico", 45.50, "Periféricos", "Hardware", 30));
        catalogo.add(new Producto("P003", "Monitor 27\"", 320.00, "Pantallas", "Hardware", 8));
        catalogo.add(new Producto("P004", "Auriculares BT", 79.99, "Audio", "Hardware", 20));
        catalogo.add(new Producto("P005", "Antivirus Pro", 29.99, "Software", "Licencia", 100));
        catalogo.add(new Producto("P006", "Silla Gaming", 199.00, "Mobiliario", "Accesorio", 5));
        catalogo.add(new Producto("P007", "Hub USB-C", 35.00, "Periféricos", "Hardware", 50));

        System.out.println("Catálogo completo:");
        catalogo.forEach(p -> System.out.println("  " + p));

        // -- filtrarPorPrecioMaximo --
        System.out.println("\nProductos <= 80€:");
        filtrarPorPrecioMaximo(catalogo, 80.0)
                .forEach(p -> System.out.println("  " + p));

        // -- buscarPorNombre --
        System.out.println("\nBuscar 'Monitor 27\"': " + buscarPorNombre(catalogo, "Monitor 27\""));
        System.out.println("Buscar 'Tablet': " + buscarPorNombre(catalogo, "Tablet"));

        // -- ordenarPorPrecioAscendente --
        ArrayList<Producto> copia = new ArrayList<>(catalogo);
        ordenarPorPrecioAscendente(copia);
        System.out.println("\nOrdenado por precio (asc):");
        copia.forEach(p -> System.out.println("  " + p));

        // -- encontrarMasBarato --
        System.out.println("\nProducto más barato: " + encontrarMasBarato(catalogo));

        // -- resumenPorCategoria --
        System.out.println("\nResumen por categoría:\n" + resumenPorCategoria(catalogo));

        // -- contienePorId --
        System.out.println("¿Contiene P003? " + contienePorId(catalogo, "P003"));
        System.out.println("¿Contiene P999? " + contienePorId(catalogo, "P999"));
    }
}
