package com.masterclass.nivel3_bounded_types;

import java.io.Serializable;

/**
 * EJERCICIO 11: Múltiples Exigencias (Multi-Bounds Intersections)
 * 
 * OBJETIVO: 
 * Acorazar una de las metodologías núcleo simulando lo que exige Spring Boot (El framework Backend N1). 
 * Se te requiere un genérico que no sólo encaje dentro de un entorno matemático (un Number), 
 * sino que además la arquitectura permita la clonación o retransmisión por serialización.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '03_Bounded_Types.md' (Sección: "Múltiples Bounds")
 */
// TODO 1: Formula y restringe la inyección tipo <T> en la firma aplicando las dos reglas exigidas superiormente (heredar y serializar).
// TODO 2: ¡Recuerda la regla de oro! Existen un orden entre la clase primitiva restrictora y la interfaz restrictora en Java.
public class Ejercicio11_GuardadorMultiple {

    // TODO 3: En el bloque inferior de método, modifica la directriz de clase pura base indicando nuestro 
    // genérico restringido de T. Así te asegurarás que nadie pase un Object generalista.
    public void simularGuardadoDeDisco(Object objeto) {
        
        // TODO 4: Al estar garantizado por la asignatura, comprueba en esta impresión cómo el método subyacente
        // a T interactúa inteligentemente con funciones core obteniendo el SimpleName del tipo enmascarado.
        System.out.println("Procesando serialización para el tipo numérico: " + objeto.getClass().getSimpleName());
        System.out.println(">> Simulacro Finalizado en Buffer Virtual.");
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- CACHEADOR MULTI-RESTRICTIVO ---");
        
        // TODO 5 (PRUEBA): Evalua si el JDK identifica internamente a Integer como serializable y como número.
        // Modifica los comentarios pertinentes para evaluar los escenarios en el Run de Visual Studio.
        
        /*
        Ejercicio11_GuardadorMultiple<Integer> guardadorEnteros = new Ejercicio11_GuardadorMultiple<>();
        guardadorEnteros.simularGuardadoDeDisco(500);
        */

        // Intenta instanciar 'Object' u otras clases básicas para que vislumbres el escudado total en VSC.
    }
}
