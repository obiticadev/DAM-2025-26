package com.masterclass.collections.nivel07_hashset;

import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * EJERCICIO 24 — HashSet con Objetos de Dominio (equals + hashCode)
 * ===================================================================
 * Teoría de referencia: teoria/05_HashSet_y_Conjuntos.md  (§ 5 — equals y hashCode)
 *
 * Objetivo: Practicar el uso de HashSet con objetos de dominio (Producto)
 * que ya tienen equals() y hashCode() basados en id.
 *
 * Restricción: Trabaja exclusivamente con la API de Set; no uses HashMap ni bucles
 *              para comprobar unicidad manualmente.
 */
public class Ejercicio24_HashSetConObjetos {

    // TODO 1: Implementa `crearCatalogoUnico`.
    //   - Recibe un ArrayList<Producto> que puede contener duplicados
    //     (productos con el mismo id).
    //   - Retorna un HashSet<Producto> sin duplicados.
    //   - Gracias a que Producto.equals() compara por id, el HashSet los filtra.
    public static HashSet<Producto> crearCatalogoUnico(ArrayList<Producto> productos) {
        return null;
    }

    // TODO 2: Implementa `existeProducto`.
    //   - Retorna true si el HashSet contiene un Producto con el id dado.
    //   - Crea un Producto temporal con ese id (los demás campos pueden ser dummies)
    //     y usa contains() del Set.
    //   - Esto funciona porque equals() solo compara por id.
    public static boolean existeProducto(HashSet<Producto> catalogo, String id) {
        return false;
    }

    // TODO 3: Implementa `productosComunesEntre`.
    //   - Recibe dos HashSet<Producto> (catalogo A y catalogo B).
    //   - Retorna un NUEVO HashSet<Producto> con los productos presentes en AMBOS
    //     catálogos (intersección).
    //   - No modifica los catálogos originales.
    public static HashSet<Producto> productosComunesEntre(HashSet<Producto> catalogoA,
                                                           HashSet<Producto> catalogoB) {
        return null;
    }

    // TODO 4: Implementa `productosExclusivos`.
    //   - Retorna un NUEVO HashSet<Producto> con los productos que están en
    //     catalogoA pero NO en catalogoB (diferencia A − B).
    //   - No modifica los catálogos originales.
    public static HashSet<Producto> productosExclusivos(HashSet<Producto> catalogoA,
                                                         HashSet<Producto> catalogoB) {
        return null;
    }

    // TODO 5: Implementa `filtrarPorCategoria`.
    //   - Recibe un HashSet<Producto> y una categoría (String).
    //   - Retorna un NUEVO HashSet<Producto> con solo los productos de esa categoría.
    //   - Itera el set y filtra manualmente con un bucle for-each.
    public static HashSet<Producto> filtrarPorCategoria(HashSet<Producto> catalogo,
                                                         String categoria) {
        return null;
    }

    // TODO 6 (desafío): Implementa `detectarProductosDuplicadosEnLista`.
    //   - Recibe un ArrayList<Producto>.
    //   - Retorna un ArrayList<String> con los IDs de los productos que aparecen
    //     más de una vez en la lista.
    //   - Usa la técnica del doble HashSet: uno para "vistos" y otro para "duplicados".
    //   - Cada ID duplicado debe aparecer una sola vez en el resultado.
    public static ArrayList<String> detectarProductosDuplicadosEnLista(
            ArrayList<Producto> productos) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 24 — HashSet con Objetos de Dominio ===\n");

        // Preparar productos
        Producto p1 = new Producto("P01", "Teclado", 45.0, "periféricos", "input");
        Producto p2 = new Producto("P02", "Ratón",   25.0, "periféricos", "input");
        Producto p3 = new Producto("P03", "Monitor", 299.0, "pantallas",  "output");
        Producto p1dup = new Producto("P01", "Teclado Dup", 50.0, "periféricos", "input");

        // -- crearCatalogoUnico --
        ArrayList<Producto> listaConDups = new ArrayList<>();
        listaConDups.add(p1); listaConDups.add(p2); listaConDups.add(p1dup); listaConDups.add(p3);
        HashSet<Producto> catalogo = crearCatalogoUnico(listaConDups);
        System.out.println("Catálogo único (size=3): " + catalogo.size());

        // -- existeProducto --
        System.out.println("¿Existe P01? " + existeProducto(catalogo, "P01"));
        System.out.println("¿Existe P99? " + existeProducto(catalogo, "P99"));

        // -- productosComunesEntre --
        HashSet<Producto> catA = new HashSet<>();
        catA.add(p1); catA.add(p2);
        HashSet<Producto> catB = new HashSet<>();
        catB.add(p2); catB.add(p3);
        System.out.println("\nComunes: " + productosComunesEntre(catA, catB));

        // -- productosExclusivos --
        System.out.println("Exclusivos de A: " + productosExclusivos(catA, catB));

        // -- filtrarPorCategoria --
        System.out.println("Periféricos: " + filtrarPorCategoria(catalogo, "periféricos"));

        // -- detectarProductosDuplicadosEnLista --
        System.out.println("IDs duplicados: " + detectarProductosDuplicadosEnLista(listaConDups));
    }
}
