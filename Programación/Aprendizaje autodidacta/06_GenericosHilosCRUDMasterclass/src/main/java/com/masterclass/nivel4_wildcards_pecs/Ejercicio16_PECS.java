package com.masterclass.nivel4_wildcards_pecs;

import java.util.List;

/**
 * EJERCICIO 16: El Arte de Combinar PECS 
 * (Producer Extends / Consumer Super)
 * 
 * OBJETIVO:
 * Traspasar todos los elementos desde una "Lista Origen" hacia una "Lista Destino".
 * Debes aplicar simultáneamente ambas leyes de los comodines que rigen a Java para dominar el Flujo de Datos.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '04_Wildcards_PECS.md' y tus experiencias en los dos ejercicios anteriores.
 */
public class Ejercicio16_PECS {

    // TODO 1: Evalúa el origen. El origen solo entrega objetos, actúa como "Productor". Asigna su Wildcard correspondiente.
    // TODO 2: Evalúa el destino. El destino es modificado recibiendo elementos, actúa como "Consumidor". Asigna su Wildcard.
    public static <T> void transferirElementos(List<T> origen, List<T> destino) {
        
        // TODO 3: Verifica el flujo. Estamos sacando (lectura -> Producer) y metiendo (escritura -> Consumer).
        for (T elemento : origen) {
            destino.add(elemento);
        }
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- TRANSFERENCIA CRÍTICA PECS ---");
        
        List<Integer> generadorHija = List.of(99, 100, 101); // Productor (Integer es hijo de Number)
        List<Number> sumideroPadre = new java.util.ArrayList<>(); // Consumidor (Number puede acoger Integers)
        
        // TODO 4 (PRUEBA): Si no aplicas el PECS, esta línea de código es un pecado mortal en Backend y no compilará.
        // Validalo una vez resueltas las firmas del TODO 1 y TODO 2.
        
        /* 
        transferirElementos(generadorHija, sumideroPadre);
        System.out.println("Datos volcados con éxito al Sumidero: " + sumideroPadre);
        */
    }
}
