package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 29 — instanceof: Filtrado Avanzado por Combinaciones
 * ================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 3, 4, 5)
 *
 * Objetivo: Construir filtros complejos que combinan múltiples checks de
 * instanceof para seleccionar objetos que cumplan combinaciones específicas.
 *
 * Restricción: Usa pattern matching. No uses getClass().
 */
public class Ejercicio29_FiltradoAvanzado {

    // TODO 1: Implementa `filtrarIdentificablesNoProcesables`.
    //   - Retorna los objetos que SON Identificable pero NO son Procesable.
    //   - Ejemplo: Empleado (Identif+Audit) y Producto (Identif+Clasif) pasan.
    //              Pedido (Identif+Proces) NO pasa.
    public static ArrayList<Object> filtrarIdentificablesNoProcesables(ArrayList<Object> lista) {
        return null;
    }

    // TODO 2: Implementa `filtrarSoloUnInterfaz`.
    //   - Retorna los objetos que implementan EXACTAMENTE UNA de las 4 interfaces
    //     conocidas (Identificable, Auditable, Clasificable, Procesable).
    //   - Cuenta cuántas de las 4 interfaces implementa cada objeto.
    //   - Solo incluye los que cumplen exactamente 1.
    public static ArrayList<Object> filtrarSoloUnInterfaz(ArrayList<Object> lista) {
        return null;
    }

    // TODO 3: Implementa `agruparPorClaseConcreta`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, ArrayList<Object>> donde la clave es el
    //     nombre simple de la clase (getClass().getSimpleName()) y el valor
    //     es la lista de objetos de esa clase.
    //   - Ejemplo: {"Empleado" → [...], "Producto" → [...], "String" → [...]}
    public static HashMap<String, ArrayList<Object>> agruparPorClaseConcreta(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 4: Implementa `obtenerIdsDeProcesablesPendientes`.
    //   - Retorna un ArrayList<String> con los IDs de los objetos que:
    //     a) Implementan Procesable Y Identificable simultáneamente
    //     b) NO están procesados (isProcesado() == false)
    //   - Usa pattern matching para ambos checks.
    public static ArrayList<String> obtenerIdsDeProcesablesPendientes(ArrayList<Object> lista) {
        return null;
    }

    // TODO 5: Implementa `transformarAMapa`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada Identificable de la lista, crea una entrada en un
    //     HashMap<String, String> con clave=getId() y valor=getNombre().
    //   - Los que no son Identificable se ignoran.
    //   - Si hay ids duplicados, el último sobrescribe al anterior.
    public static HashMap<String, String> transformarAMapa(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 29 — Filtrado Avanzado ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        lista.add("texto");

        System.out.println("Identif no Procesables: " +
                filtrarIdentificablesNoProcesables(lista).size());
        System.out.println("Solo 1 interfaz: " + filtrarSoloUnInterfaz(lista).size());

        HashMap<String, ArrayList<Object>> agrupados = agruparPorClaseConcreta(lista);
        agrupados.forEach((k, v) -> System.out.println(k + " -> " + v.size()));

        System.out.println("\nProcesables pendientes: " +
                obtenerIdsDeProcesablesPendientes(lista));
        System.out.println("Mapa de Identificables: " + transformarAMapa(lista));
    }
}
