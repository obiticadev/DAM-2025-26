package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 30 — instanceof Avanzado: HashMap con Despacho por Interfaz
 * ======================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 5)
 *
 * Objetivo: Construir un sistema de indexación que organiza objetos
 * heterogéneos en un HashMap usando instanceof como criterio de despacho.
 */
public class Ejercicio30_HashMapConDespacho {

    // TODO 1: Implementa `indexarPorId`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, Object> donde la clave es getId()
    //     de cada Identificable y el valor es el objeto original.
    //   - Los no-Identificable se ignoran.
    //   - Si hay IDs duplicados, el último gana.
    public static HashMap<String, Object> indexarPorId(ArrayList<Object> lista) {
        return null;
    }

    // TODO 2: Implementa `indexarPorTipoYCategoria`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada Clasificable, crea una clave compuesta: "categoria:tipo"
    //   - Retorna un HashMap<String, ArrayList<Object>> agrupando por esa clave.
    //   - Usa computeIfAbsent para los ArrayList internos.
    public static HashMap<String, ArrayList<Object>> indexarPorTipoYCategoria(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 3: Implementa `buscarPorIdYVerificarInterfaz`.
    //   - Recibe el índice del TODO 1 y un id (String) y un nombre de interfaz (String).
    //   - Busca el objeto por id en el mapa.
    //   - Si no existe, retorna "NO_ENCONTRADO".
    //   - Si existe, comprueba si implementa la interfaz indicada:
    //     "Identificable", "Auditable", "Procesable", "Clasificable".
    //   - Retorna "CUMPLE" si la implementa, "NO_CUMPLE" si no.
    //   - Si el nombre de interfaz no es válido, retorna "INTERFAZ_DESCONOCIDA".
    public static String buscarPorIdYVerificarInterfaz(HashMap<String, Object> indice,
                                                        String id, String interfaz) {
        return null;
    }

    // TODO 4: Implementa `obtenerPrioridadMaxima`.
    //   - Recibe un ArrayList<Object>.
    //   - De todos los Procesable, retorna la MAYOR prioridad (getPrioridad()).
    //   - Si no hay ningún Procesable, retorna -1.
    public static int obtenerPrioridadMaxima(ArrayList<Object> lista) {
        return -1;
    }

    // TODO 5: Implementa `filtrarPorPrioridadMinima`.
    //   - Recibe un ArrayList<Object> y un int minPrioridad.
    //   - Retorna un ArrayList<Object> con los Procesable cuya prioridad sea >= minPrioridad.
    //   - Los no-Procesable no se incluyen.
    public static ArrayList<Object> filtrarPorPrioridadMinima(ArrayList<Object> lista,
                                                               int minPrioridad) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 30 — HashMap con Despacho por Interfaz ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        lista.add(new Pedido("D02", "C02", 200.0, 5));

        HashMap<String, Object> indice = indexarPorId(lista);
        System.out.println("Índice por ID: " + indice.keySet());

        System.out.println("Verificar E01 Auditable: " +
                buscarPorIdYVerificarInterfaz(indice, "E01", "Auditable"));
        System.out.println("Verificar P01 Procesable: " +
                buscarPorIdYVerificarInterfaz(indice, "P01", "Procesable"));

        System.out.println("\nPrioridad máxima: " + obtenerPrioridadMaxima(lista));
        System.out.println("Prioridad >= 3: " + filtrarPorPrioridadMinima(lista, 3).size());
    }
}
