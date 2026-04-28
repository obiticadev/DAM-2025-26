package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * EJERCICIO 31 — instanceof Avanzado: Procesador de Cola Heterogénea
 * =====================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 5)
 *
 * Objetivo: Simular un sistema de procesamiento de cola donde objetos
 * de distintos tipos se procesan según las interfaces que implementan.
 */
public class Ejercicio31_ProcesadorColaHeterogenea {

    // TODO 1: Implementa `procesarColaConPrioridad`.
    //   - Recibe un ArrayList<Object> representando una cola.
    //   - Primero procesa (llama a procesar()) los Procesable con prioridad >= 5.
    //   - Luego procesa los Procesable con prioridad < 5.
    //   - Retorna un ArrayList<String> con los IDs de los procesados (en orden),
    //     pero solo para los que TAMBIÉN son Identificable.
    //   - Los que son Procesable pero no Identificable, se procesan pero
    //     añaden "ANONIMO" al resultado en su lugar.
    public static ArrayList<String> procesarColaConPrioridad(ArrayList<Object> cola) {
        return null;
    }

    // TODO 2: Implementa `generarInformeAuditoria`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada Auditable, genera una entrada en un HashMap<String, String>:
    //     clave = "AUDIT-{indice}" (indice empezando en 0 para cada auditable)
    //     valor = "{creadoPor}|{fechaCreacion}"
    //   - Retorna el HashMap.
    public static HashMap<String, String> generarInformeAuditoria(ArrayList<Object> lista) {
        return null;
    }

    // TODO 3: Implementa `encontrarDuplicadosPorInterfaz`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashSet<String> con las clases que aparecen más de una vez
    //     (getClass().getSimpleName()).
    //   - Ejemplo: si hay 2 Empleados → "Empleado" está en el set.
    public static HashSet<String> encontrarDuplicadosPorClase(ArrayList<Object> lista) {
        return null;
    }

    // TODO 4: Implementa `contarInterfacesPromedio`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada objeto, cuenta cuántas de las 4 interfaces implementa.
    //   - Retorna el promedio (double) de interfaces por objeto.
    //   - Si la lista está vacía, retorna 0.0.
    public static double contarInterfacesPromedio(ArrayList<Object> lista) {
        return 0.0;
    }

    // TODO 5: Implementa `extraerNombresOrdenados`.
    //   - Recibe un ArrayList<Object>.
    //   - Extrae getNombre() de todos los Identificable.
    //   - Retorna un ArrayList<String> con los nombres en orden ALFABÉTICO.
    //   - Usa Collections.sort() o lista.sort() sobre el resultado.
    public static ArrayList<String> extraerNombresOrdenados(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 31 — Procesador de Cola Heterogénea ===\n");

        ArrayList<Object> cola = new ArrayList<>();
        cola.add(new Pedido("D01", "C01", 100.0, 7));
        cola.add(new Pedido("D02", "C02", 200.0, 2));
        cola.add(new Evento("EV01", "WARN", "Aviso", 8, "sistema"));
        cola.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        cola.add(new Producto("P01", "Teclado", 45.0, "peri", "input"));

        System.out.println("Procesados: " + procesarColaConPrioridad(cola));
        System.out.println("Auditoría: " + generarInformeAuditoria(cola));
        System.out.println("Duplicados por clase: " + encontrarDuplicadosPorClase(cola));
        System.out.println("Promedio interfaces: " + contarInterfacesPromedio(cola));
        System.out.println("Nombres ordenados: " + extraerNombresOrdenados(cola));
    }
}
