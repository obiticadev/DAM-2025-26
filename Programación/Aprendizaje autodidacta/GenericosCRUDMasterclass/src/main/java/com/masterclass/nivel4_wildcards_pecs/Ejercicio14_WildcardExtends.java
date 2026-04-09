package com.masterclass.nivel4_wildcards_pecs;

import java.util.List;

/**
 * EJERCICIO 14: Lectura Segura ("Producer Extends")
 * 
 * OBJETIVO: 
 * Implementar el modelo Provider (Productor). Configurarás un método para
 * aceptar familias de listas numéricas asumiendo un nivel de solo y estricta lectura.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '04_Wildcards_PECS.md' (Sección: "Upper Bounded Wildcard")
 */
public class Ejercicio14_WildcardExtends {

    // TODO 1: Transmuta la firma del método asumiendo el paradigma mágico PECS. Necesitas leer Numbers, pero el 
    // usuario te inyectará Lists de Integer, Floats o Doubles reales en Runtime.
    public double sumarTodo(List<Number> lista) {
        double total = 0.0;
        
        // TODO 2: Analiza el bucle for. A pesar de los comodines que inyectaste, ¿Por qué 
        // Java no emite un System Error de Casting? ¿Qué seguridad de frontera aplica el Upper Bound?
        for (Number act : lista) {
            total += act.doubleValue();
        }
        
        // TODO 3: Dentro del método, intenta insertar algo: `lista.add(10.5);`. 
        // Documenta la reacción defensiva del IDE en tu mente (Java blindando la inyección externa).
        
        return total;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- LEYENDO DATOS MÚLTIPLES ---");
        
        List<Double> precios = List.of(9.99, 10.50);
        List<Integer> edades = List.of(18, 20);
        
        // TODO 4 (PRUEBA): El sistema debe aceptar las colecciones abstractas subyacentes. Ejecuta.
        /*
        Ejercicio14_WildcardExtends ej = new Ejercicio14_WildcardExtends();
        System.out.println("Total Precios Dobles: " + ej.sumarTodo(precios));
        System.out.println("Total Edades Enteras: " + ej.sumarTodo(edades));
        */
    }
}
