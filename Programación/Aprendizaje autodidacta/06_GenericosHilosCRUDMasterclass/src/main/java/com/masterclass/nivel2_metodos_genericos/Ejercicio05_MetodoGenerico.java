package com.masterclass.nivel2_metodos_genericos;

/**
 * EJERCICIO 5: Tu Primer Método Genérico
 * 
 * OBJETIVO: 
 * Migrar un método estático heredado de código Legacy (que opera con Object puro) 
 * hacia un sistema robusto que conserve el tipo de dato original sin requerir casteo exterior.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '02_Metodos_Genericos.md' (Sección: "El Poder del Alcance Local")
 */
public class Ejercicio05_MetodoGenerico {

    // TODO 1: Configura la firma de este método para declarar la existencia de un genérico abstracto.
    // TODO 2: Refactoriza la firma para garantizar que devuelva el mismo tipo exacto que recibe en el Array.
    // TODO 3: Modifica el argumento de entrada para que solo acepte arrays dependientes de tu abstracción.
    public static Object obtenerPrimerElemento(Object[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        
        // TODO 4: ¿Requiere el retorno interno de esta lógica clásica alguna modificación una vez 
        // ajustada la firma del método en el punto 1 y 2? Analízalo.
        return array[0];
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EXTRACCIÓN DE ELEMENTOS ---");
        
        String[] nombres = {"Ana", "Bob"};
        Integer[] numeros = {10, 20, 30};

        // TODO 5 (PRUEBA): Una vez refactorizado el método maestro, procede a limpiar las atrocidades (Type Casting)
        // que se emplean aquí debajo, confiando plenamente en la Inferencia de Tipos de Java. Ejecútalo en VSCode.
        
        String primerNombre = (String) obtenerPrimerElemento(nombres); 
        Integer primerNumero = (Integer) obtenerPrimerElemento(numeros);

        System.out.println("Primer nombre: " + primerNombre);
        System.out.println("Primer número: " + primerNumero);
    }
}
