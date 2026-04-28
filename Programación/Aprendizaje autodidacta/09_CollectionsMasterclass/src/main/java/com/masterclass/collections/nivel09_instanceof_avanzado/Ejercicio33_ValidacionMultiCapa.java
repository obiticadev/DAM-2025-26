package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * EJERCICIO 33 — instanceof Avanzado: Sistema de Validación Multi-Capa
 * ======================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 6, 7)
 *
 * Objetivo: Construir un sistema de validación que aplica distintas reglas
 * de negocio según las interfaces que cada objeto implementa, generando
 * informes de errores y advertencias.
 *
 * Restricción: Usa pattern matching. Cada método de validación debe ser
 *              independiente y testeable.
 */
public class Ejercicio33_ValidacionMultiCapa {

    // TODO 1: Implementa `validarIdentificable`.
    //   - Recibe un Object.
    //   - Si es Identificable, valida:
    //     * getId() no es null ni vacío → OK
    //     * getNombre() no es null ni vacío → OK
    //   - Retorna un ArrayList<String> con los errores encontrados:
    //     "ID_NULO_O_VACIO", "NOMBRE_NULO_O_VACIO"
    //   - Si no es Identificable, retorna lista con un solo error: "NO_IDENTIFICABLE".
    //   - Si todo es correcto, retorna lista vacía.
    public static ArrayList<String> validarIdentificable(Object obj) {
        return null;
    }

    // TODO 2: Implementa `validarProcesable`.
    //   - Recibe un Object.
    //   - Si es Procesable, valida:
    //     * getPrioridad() >= 1 && <= 10 → OK, sino "PRIORIDAD_FUERA_DE_RANGO"
    //     * Si ya está procesado (isProcesado()), advierte "YA_PROCESADO"
    //   - Si no es Procesable, retorna lista con "NO_PROCESABLE".
    //   - Si todo OK, retorna lista vacía.
    public static ArrayList<String> validarProcesable(Object obj) {
        return null;
    }

    // TODO 3: Implementa `validarCompleto`.
    //   - Recibe un Object.
    //   - Ejecuta TODAS las validaciones que correspondan:
    //     * Si es Identificable → validarIdentificable()
    //     * Si es Procesable → validarProcesable()
    //   - Retorna un HashMap<String, ArrayList<String>> con:
    //     "identificable" → resultados de validarIdentificable() (o lista vacía si no aplica)
    //     "procesable"    → resultados de validarProcesable() (o lista vacía si no aplica)
    //   - Ambas claves siempre existen.
    public static HashMap<String, ArrayList<String>> validarCompleto(Object obj) {
        return null;
    }

    // TODO 4: Implementa `validarLote`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada objeto, ejecuta validarCompleto().
    //   - Retorna un HashMap<Integer, HashMap<String, ArrayList<String>>> donde:
    //     clave = índice del objeto en la lista (0, 1, 2...)
    //     valor = resultado de validarCompleto() para ese objeto
    public static HashMap<Integer, HashMap<String, ArrayList<String>>> validarLote(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 5: Implementa `contarErroresTotales`.
    //   - Recibe el resultado de validarLote().
    //   - Cuenta el TOTAL de errores/advertencias en todo el lote.
    //   - Cada String en cualquier ArrayList de errores cuenta como 1.
    //   - Retorna el total.
    public static int contarErroresTotales(
            HashMap<Integer, HashMap<String, ArrayList<String>>> validacion) {
        return -1;
    }

    // TODO 6 (desafío): Implementa `objetosSinErrores`.
    //   - Recibe el resultado de validarLote() y la lista original.
    //   - Retorna un ArrayList<Object> con solo los objetos cuya validación
    //     no produjo NINGÚN error en NINGUNA categoría.
    //   - Un objeto sin errores tiene todas sus listas de errores vacías.
    public static ArrayList<Object> objetosSinErrores(
            HashMap<Integer, HashMap<String, ArrayList<String>>> validacion,
            ArrayList<Object> listaOriginal) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 33 — Validación Multi-Capa ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Pedido("D01", "C01", 150.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        lista.add("texto");

        // Validar uno por uno
        System.out.println("Validar Empleado: " + validarCompleto(lista.get(0)));
        System.out.println("Validar Pedido: " + validarCompleto(lista.get(1)));
        System.out.println("Validar String: " + validarCompleto(lista.get(3)));

        // Validar lote
        var lote = validarLote(lista);
        System.out.println("\n--- Lote completo ---");
        lote.forEach((i, v) -> System.out.println("  [" + i + "] " + v));

        System.out.println("\nErrores totales: " + contarErroresTotales(lote));
        System.out.println("Sin errores: " + objetosSinErrores(lote, lista).size());
    }
}
