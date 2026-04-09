package com.masterclass.nivel4_wildcards_pecs;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 17: Consumidor Activo - Interfaces Modificadoras
 * 
 * OBJETIVO: 
 * Este patrón es crítico y lo verás en todos lados en Java nativo. Métodos abstractos core como 
 * '.sort(Comparator<? super T> c)' aplican un Consumidor de manera contínua. Apliquemos esta arquitectura de inyección.
 */
public class Ejercicio17_PECS_Consumidor {

    // TODO 1: Define el alcance del Genérico <T> transversal antes del modificador de tipo del método estático.
    // TODO 2: Convierte la "Lista Destino" local para que aplique la aserción de Consumidor (super).
    public static void inyectarDatoAislado(Object datoParaEntregar, List<Object> entornoConsumidor) {
        
        // TODO 3: Desmenuza qué pasaría internamente en tiempo de compilación si aquí pusiéramos "extends" en lugar de super.
        // Escribe la respuesta para ti mismo en un comentario.
        entornoConsumidor.add(datoParaEntregar); 
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- CASO DE USO SUPER CLÁSICO ---");
        
        // TODO 4 (PRUEBA): Entendiendo que la lista Root (Object) actúa como consumidor absorbente 
        // superior de nuestro String base. ¡Pruébalo!
        
        /*
        List<Object> contenedorSuperior = new ArrayList<>();
        inyectarDatoAislado("Transmisión de red TCP/IP completada", contenedorSuperior);
        System.out.println("LOG INYECTADO -> " + contenedorSuperior);
        */
    }
}
