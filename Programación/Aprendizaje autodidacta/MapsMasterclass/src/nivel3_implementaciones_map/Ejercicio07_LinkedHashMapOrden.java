package nivel3_implementaciones_map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * EJERCICIO 07 - LINKEDHASHMAP Y LA INSERCIÓN PRESERVADA
 * 
 * Objetivo: Ver el poder de LinkedHashMap cuando el negocio exige
 * saber quién llegó primero (FIFO).
 */
public class Ejercicio07_LinkedHashMapOrden {

    public static void demostracion() {
        System.out.println("--- DEMO: ORDEN DE INSERCIÓN EN LINKEDHASHMAP ---");
        Map<Integer, String> carrera = new LinkedHashMap<>();
        carrera.put(103, "Carlos (Llegó 1º)");
        carrera.put(55, "Ana (Llegó 2ª)");
        carrera.put(8, "Pedro (Llegó 3º)");

        System.out.println("El LinkedHashMap garantizará que si iteramos, salgan en ese estricto orden:");
        carrera.forEach((dorsal, nombre) -> System.out.println("Dorsal " + dorsal + ": " + nombre));
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 07: COLA DE IMPRESIÓN ---");
        // Tenemos que respetar el orden exacto en el que llegaron los trabajos de impresión.
        // LLAVE: ID Trabajo (Integer), VALOR: Documento (String)
        
        // TODO 1: Instancia 'colaImpresion' obligatoriamente como LinkedHashMap
        Map<Integer, String> colaImpresion = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Inserta en este orden exacto:
        // Trab 100 -> "Nomina_CEO.pdf"
        // Trab 101 -> "Fotos_Gato.png"
        // Trab 102 -> "Factura_Enero.pdf"
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Supongamos que el CEO actualiza su nómina mientras espera.
        // Haces un put() sobre la llave 100 con el valor "Nomina_Rev2.pdf".
        // ¿Cambiará esto el orden? Actualiza el valor y pruébalo.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre el Map usando forEach() para simular qué se va imprimiendo.
        // También, carga las llaves generadas en la variable 'resultadoOrden' concatenándolas.
        StringBuilder resultadoOrden = new StringBuilder();
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        if (colaImpresion != null && colaImpresion.get(100).equals("Nomina_Rev2.pdf") && resultadoOrden.toString().contains("100101102")) {
            System.out.println(">> CORRECTO: Comprendes que modificar un valor existente no altera su posición histórica en LinkedHashMap.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa la instanciación o el recorrido del LinkedHashMap.");
        }
    }
}
