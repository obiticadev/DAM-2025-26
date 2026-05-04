package com.masterclass.collections.nivel05_hashmap_avanzado;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 15 — Agrupación Manual con HashMap
 * =============================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 8 — Agrupación manual)
 *
 * Objetivo: Implementar el patrón "groupingBy" de forma manual usando
 * HashMap<K, ArrayList<V>>. Este es el núcleo de cómo funciona
 * Collectors.groupingBy() internamente.
 *
 * Restricción: NO usar Streams ni Collectors. Solo bucles y computeIfAbsent.
 */
public class Ejercicio15_AgrupacionManual {

    // TODO 1: Implementa `agruparPorCategoria`.
    //   - Agrupa los productos por su categoría.
    //   - Retorna un HashMap<String, ArrayList<Producto>> donde la clave es la categoría.
    //   - Para cada producto, usa computeIfAbsent(categoria, k -> new ArrayList<>()).add(producto).
    //   - Esta es la forma idiomática: evita el if-containsKey/put repetido.
    public static HashMap<String, ArrayList<Producto>> agruparPorCategoria(
            ArrayList<Producto> productos) {
        return null;
    }

    // TODO 2: Implementa `contarPorDepartamento`.
    //   - Cuenta cuántos empleados hay en cada departamento.
    //   - Retorna un HashMap<String, Integer> con departamento → conteo.
    //   - Usa merge(departamento, 1, Integer::sum) por cada empleado.
    public static HashMap<String, Integer> contarPorDepartamento(
            ArrayList<Empleado> empleados) {
        return null;
    }

    // TODO 3: Implementa `agruparStringsPorLongitud`.
    //   - Agrupa los Strings de la lista por su longitud (número de caracteres).
    //   - Retorna un HashMap<Integer, ArrayList<String>> donde la clave es la longitud.
    //   - Usa computeIfAbsent(longitud, k -> new ArrayList<>()).add(s).
    public static HashMap<Integer, ArrayList<String>> agruparStringsPorLongitud(
            ArrayList<String> palabras) {
        return null;
    }

    // TODO 4: Implementa `claveConMasElementos`.
    //   - Dado un mapa de agrupación HashMap<K, ArrayList<?>>, retorna la clave
    //     cuya lista de valores tiene más elementos.
    //   - Si hay empate, retorna cualquiera de las claves empatadas.
    //   - Itera con entrySet() comparando tamaños.
    //   - Retorna null si el mapa está vacío.
    public static <K> K claveConMasElementos(HashMap<K, ArrayList<?>> mapa) {
        return null;
    }

    // TODO 5: Implementa `sumaSalariosPorDepartamento`.
    //   - Calcula la suma de salarios de los empleados en cada departamento.
    //   - Retorna un HashMap<String, Double> con departamento → suma de salarios.
    //   - Usa merge(depto, salario, Double::sum) para acumular.
    public static HashMap<String, Double> sumaSalariosPorDepartamento(
            ArrayList<Empleado> empleados) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 15 — Agrupación Manual ===\n");

        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("P001", "Teclado",     89.99, "Periféricos", "Hardware"));
        productos.add(new Producto("P002", "Ratón",       45.50, "Periféricos", "Hardware"));
        productos.add(new Producto("P003", "Monitor",    320.00, "Pantallas",   "Hardware"));
        productos.add(new Producto("P004", "Auriculares", 79.99, "Audio",       "Hardware"));
        productos.add(new Producto("P005", "Antivirus",   29.99, "Software",    "Licencia"));

        // -- agruparPorCategoria --
        HashMap<String, ArrayList<Producto>> grupos = agruparPorCategoria(productos);
        System.out.println("Grupos por categoría:");
        grupos.forEach((cat, lista) ->
                System.out.println("  " + cat + " → " + lista.size() + " productos"));

        // -- claveConMasElementos --
        HashMap<String, ArrayList<?>> cast = new HashMap<>(grupos);
        System.out.println("Categoría con más productos: " + claveConMasElementos(cast));

        // -- empleados --
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("E01", "Ana",   "IT",      45000, "admin"));
        empleados.add(new Empleado("E02", "Luis",  "IT",      52000, "admin"));
        empleados.add(new Empleado("E03", "Marta", "RRHH",    38000, "admin"));
        empleados.add(new Empleado("E04", "Pedro", "RRHH",    40000, "admin"));
        empleados.add(new Empleado("E05", "Eva",   "IT",      61000, "admin"));
        empleados.add(new Empleado("E06", "Carlos","Ventas",  35000, "admin"));

        // -- contarPorDepartamento --
        System.out.println("\nEmpleados por departamento: " + contarPorDepartamento(empleados));

        // -- sumaSalariosPorDepartamento --
        System.out.println("Suma salarios por departamento: " + sumaSalariosPorDepartamento(empleados));

        // -- agruparStringsPorLongitud --
        ArrayList<String> palabras = new ArrayList<>();
        for (String p : new String[]{"sol", "luna", "mar", "cielo", "rio", "nube", "tierra"}) {
            palabras.add(p);
        }
        System.out.println("\nPor longitud: " + agruparStringsPorLongitud(palabras));
    }
}
