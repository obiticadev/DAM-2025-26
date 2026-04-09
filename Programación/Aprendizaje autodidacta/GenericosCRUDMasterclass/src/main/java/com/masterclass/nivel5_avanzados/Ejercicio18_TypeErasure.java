package com.masterclass.nivel5_avanzados;

/**
 * EJERCICIO 18: El Engaño del 'instanceof' y Type Erasure
 * 
 * OBJETIVO:
 * Comprender por qué las comprobaciones de tipos fallan flagrantemente en ejecución
 * cuando usamos colecciones paramétricas debido a la mutilación de datos de Java (Type Erasure).
 * 
 * APOYO TEÓRICO: 
 * Revisa '05_Genericos_Avanzados.md' (Sección: "Restricciones Mortales")
 */
import java.util.List;

public class Ejercicio18_TypeErasure {

    // TODO 1: Esta evaluación estática intenta averiguar de qué tipo base está formada la lista 
    // en tiempo de ejecución. Ajusta el código eliminando el comodín evaluativo que el compilador rechaza.
    public static boolean esListaDeStrings(Object misterio) {
        
        // TODO 2: Fíjate que el IDE no te permite comprobar abstracciones relativas (List<String>). 
        // Modifica la evaluación inferior para simplemente comprobar si es de tipo "List" a secas o List com comodín infinito.
        /* if (misterio instanceof List<String>) {
            return true;
        } */
        
        return false; // TODO 3: Actualiza este retorno acorde a la corrección del paso anterior.
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA TYPE ERASURE ---");
        
        List<String> listaStrings = new java.util.ArrayList<>();
        List<Integer> listaEnteros = new java.util.ArrayList<>();
        
        // TODO 4 (PRUEBA): Imprime la salida del método inyectando tanto la lista de enteros como la de strings.
        // Evalúa el resultado lógico de ambas afirmaciones. ¿Ves por qué no puedes confiar en instanceof con genéricos?
        
        /* 
        System.out.println("¿Es lista válida (String)? " + esListaDeStrings(listaStrings)); 
        System.out.println("¿Es lista válida (Integer)? " + esListaDeStrings(listaEnteros));
        */
    }
}
