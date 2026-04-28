package com.masterclass.collections.nivel05_hashmap_avanzado;

import com.masterclass.collections.modelos.Empleado;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 16 — compute(), computeIfAbsent(), computeIfPresent() y HashMap Anidado
 * ====================================================================================
 * Teoría de referencia: teoria/03_HashMap_Core.md  (§ 6 — compute y merge, § 9 — HashMap anidado)
 *
 * Objetivo: Dominar la familia compute* de Map y construir estructuras
 * de datos anidadas HashMap<K, HashMap<K2, V>>.
 *
 * Restricción: Cada TODO debe usar el método de API indicado. No uses
 *              if/put como sustituto de compute*.
 */
public class Ejercicio16_ComputeYHashMapAnidado {

    // TODO 1: Implementa `incrementarContador`.
    //   - Incrementa en 1 el valor asociado a `clave` en el mapa.
    //   - Si la clave no existe, la inicializa en 1.
    //   - Usa compute(clave, (k, v) -> v == null ? 1 : v + 1).
    //   - El mapa se modifica en sitio. No retorna nada.
    public static void incrementarContador(HashMap<String, Integer> contadores, String clave) {
        // implementa aquí
    }

    // TODO 2: Implementa `agregarALista`.
    //   - Añade `valor` a la lista asociada a `clave` en el mapa.
    //   - Si la clave no tiene una lista todavía, crea una nueva y la asocia.
    //   - Usa computeIfAbsent(clave, k -> new ArrayList<>()).add(valor).
    //   - El mapa se modifica en sitio. No retorna nada.
    public static void agregarALista(HashMap<String, ArrayList<String>> mapa,
                                      String clave, String valor) {
        // implementa aquí
    }

    // TODO 3: Implementa `actualizarSiPresente`.
    //   - Si la clave existe en el mapa, añade `sufijo` al final de su valor.
    //   - Si la clave NO existe, no hace nada (no la crea).
    //   - Usa computeIfPresent(clave, (k, v) -> v + sufijo).
    //   - Retorna true si el mapa se modificó, false si la clave no existía.
    public static boolean actualizarSiPresente(HashMap<String, String> mapa,
                                                String clave, String sufijo) {
        return false;
    }

    // TODO 4: Implementa `construirIndiceAnidado`.
    //   - Recibe una lista de Empleados y construye un mapa anidado:
    //     HashMap<Departamento, HashMap<NombreEmpleado, Salario>>
    //   - Para cada empleado, usa:
    //     mapa.computeIfAbsent(depto, k -> new HashMap<>()).put(nombre, salario)
    //   - Retorna el mapa anidado construido.
    public static HashMap<String, HashMap<String, Double>> construirIndiceAnidado(
            ArrayList<Empleado> empleados) {
        return null;
    }

    // TODO 5: Implementa `salarioEmpleado`.
    //   - Dado el índice anidado del TODO 4, retorna el salario del empleado
    //     con ese nombre en ese departamento.
    //   - Si el departamento o el empleado no existe, retorna -1.0.
    //   - Usa getOrDefault() dos veces anidados: primero para el mapa de departamento,
    //     luego para el salario.
    public static double salarioEmpleado(
            HashMap<String, HashMap<String, Double>> indice,
            String departamento, String nombre) {
        return -1.0;
    }

    // TODO 6 (desafío): Implementa `eliminarEntradaSiCero`.
    //   - Usa compute(clave, (k, v) -> ...) para decrementar el contador.
    //   - Si tras decrementar el valor llega a 0 o menos, la función debe retornar null
    //     (eso hace que compute elimine la entrada del mapa automáticamente).
    //   - Si la clave no existe (v == null), no hace nada (retorna null también).
    //   - El mapa se modifica en sitio.
    public static void decrementarYEliminarSiCero(HashMap<String, Integer> contadores,
                                                   String clave) {
        // implementa aquí
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 16 — compute* y HashMap Anidado ===\n");

        // -- incrementarContador --
        HashMap<String, Integer> contadores = new HashMap<>();
        String[] eventos = {"click", "hover", "click", "scroll", "click", "hover"};
        for (String e : eventos) incrementarContador(contadores, e);
        System.out.println("Contadores: " + contadores);

        // -- agregarALista --
        HashMap<String, ArrayList<String>> grupos = new HashMap<>();
        agregarALista(grupos, "frutas", "Manzana");
        agregarALista(grupos, "frutas", "Pera");
        agregarALista(grupos, "verduras", "Zanahoria");
        agregarALista(grupos, "frutas", "Naranja");
        System.out.println("\nGrupos: " + grupos);

        // -- actualizarSiPresente --
        HashMap<String, String> estado = new HashMap<>();
        estado.put("tarea1", "Pendiente");
        estado.put("tarea2", "Pendiente");
        System.out.println("\nActualizar tarea1 (existe): " + actualizarSiPresente(estado, "tarea1", " → Completada"));
        System.out.println("Actualizar tarea3 (no existe): " + actualizarSiPresente(estado, "tarea3", " → Nueva"));
        System.out.println("Estado: " + estado);

        // -- construirIndiceAnidado --
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado("E01", "Ana",   "IT",    45000, "admin"));
        empleados.add(new Empleado("E02", "Luis",  "IT",    52000, "admin"));
        empleados.add(new Empleado("E03", "Marta", "RRHH",  38000, "admin"));

        HashMap<String, HashMap<String, Double>> indice = construirIndiceAnidado(empleados);
        System.out.println("\nÍndice anidado: " + indice);
        System.out.println("Salario Ana (IT): " + salarioEmpleado(indice, "IT", "Ana"));
        System.out.println("Salario Pepe (IT): " + salarioEmpleado(indice, "IT", "Pepe"));

        // -- decrementarYEliminarSiCero --
        HashMap<String, Integer> stocks = new HashMap<>();
        stocks.put("item", 2);
        decrementarYEliminarSiCero(stocks, "item");
        System.out.println("\nStocks tras decrement (debería ser {item=1}): " + stocks);
        decrementarYEliminarSiCero(stocks, "item");
        System.out.println("Stocks tras llegar a 0 (debe desaparecer): " + stocks);
    }
}
