package com.masterclass.nivel3_bounded_types;

/**
 * EJERCICIO 10: Extensión Interfaz (Comparable Limit)
 * 
 * OBJETIVO: 
 * Exigir constructivamente mediante el tipado genérico que el elemento transferido entienda las directrices
 * comparativas nativas de Java.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '03_Bounded_Types.md' (Sección: Exigencias sobre Genéricos).
 */
// TODO 1: Configura la clase inyectando la letra paramétrica transversal base, pero aplica un limitante estricto
// donde se obligue a que T cumpla contrato con el ecosistema 'Comparable' (de a sí mismo).
public class Ejercicio10_BuscadorMayor {

    // TODO 2: Configura la asignatura del algoritmo indicando tu genérico adaptado.
    public Object encontrarMayor(Object a, Object b) {
        
        // TODO 3: Implementa la matemática de comparación.
        // HINT: Una vez consigas que T extienda a Comparable (TODO 1), podrás usar 'a.compareTo(b)' internamente.
        // Si no está bloqueado por el límite, Java te indicará un error de "unresolved symbol".
        
        /* 
        if (a.compareTo(b) >= 0) {
            return a;
        } else {
            return b;
        } 
        */

        return null; // TODO 4: Elimina este retorno provisional tras programar el algoritmo real.
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- BUSCADOR MAYOR LÉXICO/MÁTEMATICO ---");
        
        // TODO 5 (PRUEBA): Evalúa Strings (alfabéticamente) o Integrers. Descomenta el bloque e intenta 
        // arrancar un test en la terminal de VSC.
        
        /*
        Ejercicio10_BuscadorMayor<String> buscadorNombres = new Ejercicio10_BuscadorMayor<>();
        String resultado = buscadorNombres.encontrarMayor("Zorro", "Abeja");
        System.out.println("Mayor lexicográficamente: " + resultado); // Debería salir Zorro
        */
    }
}
