package com.masterclass.nivel1_fundamentos_genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 4: Genéricos y Tipos Primitivos
 * 
 * OBJETIVO:
 * Entender las limitaciones subyacentes de la JVM al combinar genéricos 
 * con memoria de pila (Stack).
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '01_Fundamentos_Genericos.md' (Investigación Libre: Limitación de primitivos)
 */
public class Ejercicio04_TiposPrimitivos {

    // TODO 1: Este método expone directamente un Raw Type en el IDE. Debes requerir
    // estructuralmente una lista de números enteros, considerando la limitación 
    // de los genéricos de Java respecto a primitivos (int, double...).
    public List exponerEdadesBaseDatos() {
        
        // TODO 2: Instancia correctamente la colección aplicando parametrismo y el operador Diamante.
        List lasEdadesBBDD = new ArrayList();
        
        // TODO 3: Inserta manualmente tus edades (ej. 25, 45, 60). Analiza el concepto de 
        // Autoboxing que ocurre bajo la arquitectura interna del compilador.
        
        return lasEdadesBBDD;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRIMITIVOS vs WRAPPERS ---");
        
        // TODO 4 (PRUEBA): Corrige el tipo devuelto aquí para que deje de comportarse de manera enmascarada.
        Ejercicio04_TiposPrimitivos repo = new Ejercicio04_TiposPrimitivos();
        List extraido = repo.exponerEdadesBaseDatos();
        
        /* for(Object e : extraido) {
            // Fíjate en cómo te obliga el Raw Type a instanciarlo de Object si no lo resuelves...
            System.out.println("Edad procesada " + e.getClass().getName() + " -> " + e);
        } */
    }
}
